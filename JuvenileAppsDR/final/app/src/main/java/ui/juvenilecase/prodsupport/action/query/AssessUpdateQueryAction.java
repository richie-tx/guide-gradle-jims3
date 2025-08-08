package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenile.SearchJuvenileCasefileListEvent;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.officer.GetOfficerProfilesByAttributeEvent;
import messaging.productionsupport.GetProductionSupportRiskAnalysisEvent;
import messaging.productionsupport.reply.ProductionSupportRiskAnalysisResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 */
public class AssessUpdateQueryAction extends Action{

	private Logger log = Logger.getLogger("AssessUpdateQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		String juvenileNum = "";

		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk!=null && clrChk.equalsIgnoreCase("Y"))
		{
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
		
		//Reset the ID value.
		regform.setRiskanalysisId(riskanalysisId);

		// Log the query attempt
		log.info("Risk Assessment/Risk Analysis Query ID: " + riskanalysisId + "LogonId: " +  SecurityUIHelper.getLogonId());	
		// Get and set Associated Risk Analyses
		CompositeResponse composite = null;
		GetProductionSupportRiskAnalysisEvent riskAnalysisEvent = (GetProductionSupportRiskAnalysisEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTRISKANALYSIS);
		riskAnalysisEvent.setRiskAnalysisId(riskanalysisId);

		composite = MessageUtil.postRequest(riskAnalysisEvent);
		//Gets the risk analysis record from the composite response and places in a collection for the UI to display
		ArrayList<ProductionSupportRiskAnalysisResponseEvent> riskAnalysisList = (ArrayList) MessageUtil.compositeToCollection(composite, ProductionSupportRiskAnalysisResponseEvent.class);
		// set JPO id from JU Code
		
		if (riskAnalysisList != null && riskAnalysisList.size() > 0) {
			for(ProductionSupportRiskAnalysisResponseEvent event:riskAnalysisList){
				String jims2OfficerId = retrieveOfficerId(event.getJpoUserId());
				event.setJpoUserId(jims2OfficerId);
				juvenileNum = event.getJuvenileId();
			}
			regform.setRiskanalysisCount(riskAnalysisList.size());
			regform.setRiskanalyses(riskAnalysisList);
			
			//Set the JPOUSERID value using JU ID
			Iterator iter = riskAnalysisList.iterator();
		}
		
		SearchJuvenileCasefileListEvent search = new SearchJuvenileCasefileListEvent();
		search.setJuvenileId(juvenileNum);

		List<JuvenileProfileCasefileListResponseEvent> casefiles = MessageUtil.postRequestListFilter(search, JuvenileProfileCasefileListResponseEvent.class);

		Collections.sort((List<JuvenileProfileCasefileListResponseEvent>) casefiles, new Comparator<JuvenileProfileCasefileListResponseEvent>() {
		    @Override
		    public int compare(JuvenileProfileCasefileListResponseEvent evt1, JuvenileProfileCasefileListResponseEvent evt2)
		    {
			if (evt1.getSupervisionNum() != null && evt2.getSupervisionNum() != null)
			    return evt2.getSupervisionNum().compareTo(evt1.getSupervisionNum());
			else
			    return -1;
		    }
		});
		
		Map supervisionMap = new TreeMap();
		ArrayList supervisionList = new ArrayList();
		
		if ( casefiles.size() > 0 ) {
			for(JuvenileProfileCasefileListResponseEvent list:casefiles){
			    supervisionMap.put(list.getSupervisionNum(), null);
			}
		}
		Iterator iter = supervisionMap.entrySet().iterator();
		while (iter.hasNext())
		{
		    Map.Entry pair = (Map.Entry) iter.next();
		    CodeResponseEvent obj = new CodeResponseEvent();
		    obj.setDescription(pair.getKey().toString());
		    supervisionList.add(obj);
		    iter.remove();
		}
		
		regform.setBookingSprvisionNumbers(supervisionList);
		regform.setMsg("");
		return mapping.findForward("success");

	}
	
	/**
	 * 	This method is the opposite of the method above. 
	 *  1. Retrieve OFFICER_ID (OID) from supplied JU user code 
	 *  2. Return the OFFICER_ID FROM OFFICER TABLE TO VERIFY IT
	 *  
	 * @param uvcode
	 * @return
	 */
	public static String retrieveOfficerId(String officerId){
		String existingOfficerId = null;

		List officerprofiles = new ArrayList<OfficerProfileResponseEvent>();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetOfficerProfilesByAttributeEvent getOfficerEvent = new GetOfficerProfilesByAttributeEvent();
		
		getOfficerEvent.setAttributeName("logonId");
		getOfficerEvent.setAttributeValue(officerId);
		dispatch.postEvent(getOfficerEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		officerprofiles = MessageUtil.compositeToList(response, OfficerProfileResponseEvent.class);
		
		if (officerprofiles!=null){
			Iterator iter = officerprofiles.iterator();
			if (iter.hasNext())
			{
				OfficerProfileResponseEvent event = (OfficerProfileResponseEvent)iter.next();
				existingOfficerId = event.getOfficerId();
			}	
		}
		return existingOfficerId;
	}
	
}
