package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.RiskResponseEvent;
import messaging.productionsupport.DeleteProductionSupportRiskAnalysisEvent;
import messaging.productionsupport.DeleteProductionSupportRiskResponseEvent;
import messaging.productionsupport.GetProductionSupportRiskAnalysisEvent;
import messaging.productionsupport.GetProductionSupportRiskResponsesEvent;
import messaging.productionsupport.reply.ProductionSupportRiskAnalysisResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.juvenilecase.prodsupport.helpers.Constants;
import ui.juvenilecase.prodsupport.helpers.QueryObject;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 */

public class PerformAssessDeleteAction extends Action {

	private Logger log = Logger.getLogger("PerformAssessDeleteAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		String riskanalysisId = regform.getRiskanalysisId();

		if (riskanalysisId == null || riskanalysisId.equals("")) {
			regform.setMsg("PerformAssessDeleteAction.java (63) - RiskAnalysisID was null.");
			return (mapping.findForward("error"));
		}	
		
		/**
		 * Assessment deletes require two statements because of referential integrity constraints.
		 * All child records in JCRISKRESPONSES must first be deleted before the parent record 
		 * in JCRISKANALYSIS can be deleted.
		 */
		
		
		/**
		 * First, log all the IDs of the dependent files that will be deleted
		 * automatically via RI
		 **/
		writeLogEntries(regform, riskanalysisId, SecurityUIHelper.getLogonId());
	
		/**
		 * Second, delete all related risk responses, and check that the delete was successful. If not throw error.
		 **/
		// delete the risk analysis record and associated risk response children	
		DeleteProductionSupportRiskResponseEvent riskResponseDeleteEvent = (DeleteProductionSupportRiskResponseEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTRISKRESPONSE);
		riskResponseDeleteEvent.setRiskAnalysisId(riskanalysisId);
		MessageUtil.postRequest(riskResponseDeleteEvent);
		// check with query to see that delete risk response was successful
		ArrayList<RiskResponseEvent> responses = getRiskResponses(riskanalysisId);
		if(responses == null || responses.size() != 0){
			for(RiskResponseEvent event:responses){
				if(event != null){
					log.info("There was an error deleting the Risk Responses for RiskanalysisID="
							+ riskanalysisId +  " for LogonId: " + SecurityUIHelper.getLogonId());
					regform.setMsg("Error - The Risk Analysis and Risk Responses could not be deleted.");
					return mapping.findForward("error");
				}
			}
		}
		
		/**
		 * Third, delete risk analysis assessment, and check that the delete was successful. If not throw error.
		 **/
		// delete the risk analysis record
		DeleteProductionSupportRiskAnalysisEvent riskAnalysisDeleteEvent = (DeleteProductionSupportRiskAnalysisEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTRISKANALYSIS);
		riskAnalysisDeleteEvent.setRiskAnalysisId(riskanalysisId);
		MessageUtil.postRequest(riskAnalysisDeleteEvent);
		
		// check with query to see that delete analysis was successful
		ArrayList<ProductionSupportRiskAnalysisResponseEvent> riskAnalysesList= getRiskAnalysisRecord(riskanalysisId);
		if(riskAnalysesList == null || riskAnalysesList.size() != 0){
			for(ProductionSupportRiskAnalysisResponseEvent event:riskAnalysesList){
				if(event != null){
					log.info("There was an error during ASSESSMENT DELETE for RiskanalysisID="
							+ riskanalysisId +  " for LogonId: " + SecurityUIHelper.getLogonId());
					regform.setMsg("Error - The Risk Analysis and Risk Responses could not be deleted.");
					return mapping.findForward("error");
				}
			}
		}			
		
			/** Log for auditing purposes **/
			log.info("Performed an ASSESSMENT DELETE for RiskanalysisID="
					+ riskanalysisId +  " for LogonId:" + SecurityUIHelper.getLogonId());

			regform.setMsg("");
			return mapping.findForward("success");
	}

	/**
	 * Print out records found in Risk Response table that are children of Risk Analysis Record
	 * @param regform
	 * @param logonid
	 */
	private void writeLogEntries(ProdSupportForm regform, String riskId, String logonid) {

		if (regform.getRiskresponses() != null) {
			Iterator iter = regform.getRiskresponses().iterator();
			log.info("Delete RiskAnalysisId: " + riskId + " and Associated Risk Responses. LogonId: " +  SecurityUIHelper.getLogonId());
			while (iter.hasNext()) {
				RiskResponseEvent next = (RiskResponseEvent) iter.next();
				log.info("[" + new Date() + "] ProdSupport Delete Risk Responses - LogonId: ("
						+ logonid + ") JCRISKRESPONSES ID: " + next.getResponseID());
			}
		}
	}
	
	/**
	 * Retrieve the Risk Analysis Records from JCRISKANALYSIS
	 * @param riskId
	 * @return
	 */
	private ArrayList<ProductionSupportRiskAnalysisResponseEvent> getRiskAnalysisRecord(String riskId){
	
		CompositeResponse composite = null;
		GetProductionSupportRiskAnalysisEvent riskAnalysisEvent = (GetProductionSupportRiskAnalysisEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTRISKANALYSIS);
		riskAnalysisEvent.setRiskAnalysisId(riskId);

		composite = MessageUtil.postRequest(riskAnalysisEvent);
		//Gets the actual responses to questions from the composite response and places them in a collection for the UI to display
		ArrayList<ProductionSupportRiskAnalysisResponseEvent> riskAnalysisList = (ArrayList) MessageUtil.compositeToCollection(composite, ProductionSupportRiskAnalysisResponseEvent.class);
		
		return riskAnalysisList;
	}		
	
	/**
	 * Retrieve List of Risk Responses
	 * Fill in Final Score from JCRISKRESPONSES
	 * @param riskId
	 * @return
	 */
	private ArrayList<RiskResponseEvent> getRiskResponses(String riskId){
	
	// retrieve the risk final score records
	CompositeResponse composite = null;
	GetProductionSupportRiskResponsesEvent riskResponsesEvent = (GetProductionSupportRiskResponsesEvent)EventFactory.
			getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTRISKRESPONSES);
	riskResponsesEvent.setRiskAssessmentId(riskId);
	composite = MessageUtil.postRequest(riskResponsesEvent);
	//Gets the risk analysis final score records from the composite response and places in a collection
	ArrayList<RiskResponseEvent> riskResponseList = (ArrayList) MessageUtil.compositeToCollection(composite, RiskResponseEvent.class);	

	return riskResponseList;
	}

}
