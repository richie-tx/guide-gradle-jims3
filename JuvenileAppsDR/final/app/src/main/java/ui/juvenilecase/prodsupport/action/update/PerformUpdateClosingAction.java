package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerlocation.GetLocationByJuvLocUnitIdEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.GetOfficerProfilesByAttributeEvent;
import messaging.productionsupport.UpdateProductionSupportCasefileClosingEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.LocationControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.form.ProdSupportForm;

/**
 * @author rcarter
 */

public class PerformUpdateClosingAction extends Action {

	private Logger log = Logger.getLogger("PerformUpdateClosingAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		boolean isChanged = false; // keep track of whether there are any changes
		ProdSupportForm regform = (ProdSupportForm) form;
		
		String casefileId = regform.getCasefileId();

		if (casefileId == null || casefileId.equals("")) {
			regform.setMsg("PerformUpdateClosingAction.java - Casefile ID was null.");
			return (mapping.findForward("error"));
		}
				
		/**Retrieve the CASEFILECLOSING_ID **/
		CasefileClosingResponseEvent currentCasefileClosing = retrieveFirstRecord(regform);
		
		UpdateProductionSupportCasefileClosingEvent updateCasefileClosingEvent = (UpdateProductionSupportCasefileClosingEvent)
		EventFactory.getInstance( ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTCASEFILECLOSING);
		updateCasefileClosingEvent.setCasefileClosingInfoId(currentCasefileClosing.getCasefileClosingInfoId());
		// Supervision Outcome
		if( (currentCasefileClosing.getSupervisionOutcomeId() != null && regform.getOutcomeBox() != null &&
				!currentCasefileClosing.getSupervisionOutcomeId().equalsIgnoreCase(regform.getOutcomeBox()) )
				|| (regform.getOutcomeBox() != null && !regform.getOutcomeBox().equals("")) ){
			updateCasefileClosingEvent.setSupervisionOutcomeId(regform.getOutcomeBox());
			regform.setOldSupervisionOutcome(currentCasefileClosing.getSupervisionOutcomeId());
			isChanged = true;
		}else{
			updateCasefileClosingEvent.setSupervisionOutcomeId("");
			regform.setOldSupervisionOutcome("");
		}
		// Controlling Referral		
		if( (currentCasefileClosing.getControllingReferralId() != null && regform.getNewControllingReferral() != null &&
				!currentCasefileClosing.getControllingReferralId().equalsIgnoreCase(regform.getNewControllingReferral())
				&& regform.getNewControllingReferral().length() > 0 ) || 
				(regform.getNewControllingReferral() != null && regform.getNewControllingReferral().length() > 0 )	){
			updateCasefileClosingEvent.setControllingReferralId( regform.getNewControllingReferral());
			regform.setOldControllingReferral(currentCasefileClosing.getControllingReferralId());
			isChanged = true;
		}else{
			updateCasefileClosingEvent.setControllingReferralId("");
			regform.setOldControllingReferral("");
		}
		// Supervision Outcome Description
		if(  (currentCasefileClosing.getSupervisionOutcomeDescriptionId() != null && regform.getOutcomeDescBox() != null &&
				!currentCasefileClosing.getSupervisionOutcomeDescriptionId().equalsIgnoreCase(regform.getOutcomeDescBox())) ||
				(regform.getOutcomeDescBox() != null && !regform.getOutcomeDescBox().equals("")) ){
			updateCasefileClosingEvent.setSupervisionOutcomeDescriptionId(regform.getOutcomeDescBox());
			regform.setOldSupervisionOutcomeDescCd(currentCasefileClosing.getSupervisionOutcomeDescriptionId());
			isChanged = true;
		}else{
			updateCasefileClosingEvent.setSupervisionOutcomeDescriptionId("");
			regform.setOldSupervisionOutcomeDescCd("");
		}
		// Supervision End Date
		String newSupervisionEndDate = null;	
		String closingEndDate = regform.getClosingEndDate();
		boolean isClosingEndDateChanged = checkIfTwoValuesChanged(closingEndDate, DateUtil.dateToString(currentCasefileClosing.getSupervisionEndDate(),DateUtil.DATE_FMT_1));
		if (isClosingEndDateChanged){
			newSupervisionEndDate = closingEndDate;
			updateCasefileClosingEvent.setSupervisionEndDate(DateUtil.stringToDate(newSupervisionEndDate,DateUtil.DATE_FMT_1));
			regform.setNewEndDate(newSupervisionEndDate);
			regform.setOldSupervisionEndDate(currentCasefileClosing.getSupervisionEndDate());
			isChanged = true;
		}else{
			newSupervisionEndDate = null;
			updateCasefileClosingEvent.setSupervisionEndDate(null);
			regform.setOldSupervisionEndDate(null);
			regform.setNewEndDate("");
		}
		
		if( regform.getNewRecordCLM() != null && StringUtils.isNotEmpty(regform.getNewRecordCLM())){
		    
		    GetOfficerProfilesByAttributeEvent event = (GetOfficerProfilesByAttributeEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILESBYATTRIBUTE);
		    event.setAttributeName("logonId");
		    event.setAttributeValue( regform.getNewRecordCLM() );
		    List<OfficerProfileResponseEvent> officerprofiles = MessageUtil.postRequestListFilter(event, OfficerProfileResponseEvent.class);
		    if (officerprofiles.size() > 0)
		    {
			updateCasefileClosingEvent.setRecordCLM(regform.getNewRecordCLM());
		    }else{
			
			regform.setMsg("PerformUpdateClosing - No officer record found for: " + regform.getNewRecordCLM() );
			regform.setNewRecordCLM("");
			return (mapping.findForward("error"));
			
		    }		    
		    
		}
		
		if( regform.getNewJuvLocationUnitId() != null && StringUtils.isNotEmpty(regform.getNewJuvLocationUnitId())){
		    
		    try
		    {
			int test = Integer.parseInt(regform.getNewJuvLocationUnitId());
		    }
		    catch (Exception e)
		    {
			regform.setMsg("PerformUpdateClosing - JuvLocUnitId must be numeric.");
			return (mapping.findForward("error"));
		    }
		    
		    GetLocationByJuvLocUnitIdEvent validateEvent = (GetLocationByJuvLocUnitIdEvent) 
			    		EventFactory.getInstance(LocationControllerServiceNames.GETLOCATIONBYJUVLOCUNITID);
		   validateEvent.setJuvLocUnitId(regform.getNewJuvLocationUnitId());
			
		    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		    dispatch.postEvent(validateEvent);

		    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		    MessageUtil.processReturnException(compositeResponse);
		    Collection juvlocations = MessageUtil.compositeToCollection(compositeResponse, LocationResponseEvent.class);

		    Iterator<LocationResponseEvent> iter = juvlocations.iterator();
		    if( iter.hasNext()){
			
			updateCasefileClosingEvent.setJuvLocUnitId(regform.getNewJuvLocationUnitId());
		    }else{
			regform.setMsg("PerformUpdateClosing - JuvLocUnitId was not found.");
			return (mapping.findForward("error"));
		    }		    
		}
		
		if(!isChanged) {
			regform.setMsg("There were no changes entered.");
			return mapping.findForward("error");
		}else{
			IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
			dispatch.postEvent( updateCasefileClosingEvent );
			
			// if no result return error
			if(!getCasefileClosingResponse(regform, casefileId)){
				return mapping.findForward("error");
			}else{
				return mapping.findForward("success");
			}
		}		
	}
	
	/**
	 * retrieve the case file closing event saved on the form
	 * @param regform
	 * @return
	 */
	private CasefileClosingResponseEvent retrieveFirstRecord(ProdSupportForm regform){
		
		CasefileClosingResponseEvent casefileclosing = regform.getCasefileClosing();		
		return casefileclosing;
	}
	
	
	private boolean getCasefileClosingResponse(ProdSupportForm regform, String casefileId){
		CasefileClosingResponseEvent respEvent = UIJuvenileCasefileClosingHelper.getCasefileClosingDetails(casefileId);
		ArrayList<CasefileClosingResponseEvent> casefileClosings = new ArrayList();
		casefileClosings.add(respEvent);
		regform.setCasefileclosings(casefileClosings);
		regform.setCasefileClosingCount(casefileClosings.size());
		List outcomeCodes = CodeHelper.getSupervisionOutcomeCodes();
		List outcomeDescCodes = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_OUTCOME_DESC");
		regform.setOutcomeDescCodes(new ArrayList(outcomeDescCodes));
		regform.setOutcomeCodes(new ArrayList(outcomeCodes));
		if (respEvent==null)
		{
			regform.setMsg("No casefile closing records returned for casefileID "+casefileId + ". This casefileId should exist.");
			return false;
		}else{
			regform.setMsg("");
			return true;
		}
	}
	/**(
	 * compare two string values and determine if they are equal
	 * @param value
	 * @param compareValue
	 * @return
	 */
	private boolean checkIfTwoValuesChanged(String newValue, String OlderValue){
		boolean result = false;
		
		if(newValue != null && OlderValue != null && !newValue.equals("")){
			if(!newValue.equals(OlderValue)){
				result = true;
			}
		}else if (newValue != null && OlderValue == null){
				result = true;
		}
		
		return result;
	}
	
}
