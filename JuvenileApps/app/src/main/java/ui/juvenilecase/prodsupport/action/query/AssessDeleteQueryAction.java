package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.RiskResponseEvent;
import messaging.productionsupport.GetProductionSupportRiskAnalysisEvent;
import messaging.productionsupport.GetProductionSupportRiskAnalysisFinalScoreEvent;
import messaging.productionsupport.GetProductionSupportRiskResponsesEvent;
import messaging.productionsupport.reply.ProductionSupportRiskAnalysisFinalScoreResponseEvent;
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
public class AssessDeleteQueryAction extends Action {
	
	private Logger log = Logger.getLogger("AssessDeleteQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;

		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
			regform.clearAllResults();
			regform.setMsg("");
			return mapping.findForward("error");
		}

		String riskanalysisId = regform.getRiskanalysisId();

		if (riskanalysisId == null || riskanalysisId.equals("")) {
			regform.setMsg("RiskAnalysisID was null.");
			return (mapping.findForward("error"));
		}

		// Clear the form
		regform.clearAllResults();

		// Reset the ID value.
		regform.setRiskanalysisId(riskanalysisId);

		// Log the query attempt
		log.info("Retrieve Risk Assessment/Risk Analysis Query ID For Deletion: "+ riskanalysisId + " LogonId: " + SecurityUIHelper.getLogonId());

		/**
		 * Search for riskanalyses. There should be only one for this Action.
		 */
		ArrayList riskAnalysesList= getRiskAnalysisRecord(riskanalysisId);

		if (riskAnalysesList != null) {
			
			riskAnalysesList = getFinalScores(riskAnalysesList, regform.getRiskanalysisId());
						
			regform.setRiskanalysisCount(riskAnalysesList.size());			
			regform.setRiskanalyses(riskAnalysesList);
			
		}

		/**
		 * Search for JCRISKRESPONSES records
		 */
		ArrayList responses = getRiskResponses(riskanalysisId);

		if (responses != null) {
			regform.setRiskresponsesCount(responses.size());			
			regform.setRiskresponses(responses);
		}
		
		regform.setMsg("");
		return mapping.findForward("success");

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
	 * Fill in Final Score from JCRISKANALYFNSCR
	 * @param riskAssessments
	 * @param riskId
	 * @return
	 */
	private ArrayList getFinalScores(ArrayList riskAssessmentsList, String riskId){
	
		// retrieve the risk final score records
		CompositeResponse composite = null;
		GetProductionSupportRiskAnalysisFinalScoreEvent riskAnalysisFinalScoreEvent = (GetProductionSupportRiskAnalysisFinalScoreEvent)EventFactory.
				getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTRISKANALYSISFINALSCORE);
		riskAnalysisFinalScoreEvent.setRiskAnalysisId(riskId);
		composite = MessageUtil.postRequest(riskAnalysisFinalScoreEvent);
		//Gets the risk analysis final score records from the composite response and places in a collection
		ArrayList<ProductionSupportRiskAnalysisFinalScoreResponseEvent> riskAnalysisFinalScoreList = (ArrayList) MessageUtil.compositeToCollection(composite, ProductionSupportRiskAnalysisFinalScoreResponseEvent.class);
		Iterator riskAnalysisIter = riskAssessmentsList.iterator();
		if (riskAssessmentsList !=null && riskAssessmentsList.size() > 0){				
		   	while (riskAnalysisIter.hasNext()){
		   		ProductionSupportRiskAnalysisResponseEvent currentRiskAnalysis = (ProductionSupportRiskAnalysisResponseEvent)riskAnalysisIter.next();
		   		Iterator riskAnalysisFinalScoreIter = riskAnalysisFinalScoreList.iterator();			
				while (riskAnalysisFinalScoreIter.hasNext())
					{
						ProductionSupportRiskAnalysisFinalScoreResponseEvent riskAnalysisFinalScore = (ProductionSupportRiskAnalysisFinalScoreResponseEvent)riskAnalysisFinalScoreIter.next();
						if (riskAnalysisFinalScore!= null && riskAnalysisFinalScore.getRiskAnalysisId().equals(currentRiskAnalysis.getRiskAnalysisId()));
						currentRiskAnalysis.setFinalScore(riskAnalysisFinalScore.getFinalScore());
					}	
			}
		}
		
		
		return riskAssessmentsList;
	}
	
	/**
	 * Retrieve List of Risk Responses
	 * Fill in Final Score from JCRISKRESPONSES
	 * @param riskId
	 * @return
	 */
	private ArrayList getRiskResponses(String riskId){
	
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
