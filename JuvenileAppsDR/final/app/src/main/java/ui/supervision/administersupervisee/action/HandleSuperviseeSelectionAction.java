//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administersupervisee\\action\\HandleSuperviseeSelectionAction.java

package ui.supervision.administersupervisee.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetActiveCasesEvent;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.contact.party.reply.PartyListResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.EventFactory;
import mojo.km.security.ISecurityManager;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.StringUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.BasicSupervisee;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administercasenotes.form.SuperviseeSearchForm;
import ui.supervision.administersupervisee.UIAdministerSuperviseeHelper;
import ui.supervision.supervisee.form.SuperviseeForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class HandleSuperviseeSelectionAction extends JIMSBaseAction
{
	
	public static final String ACTIVE = "A";
   /**
    * @roseuid 484E86E8037D
    */
   public HandleSuperviseeSelectionAction() 
   {
    
   }
   
   protected void addButtonMapping(Map keyMap) {
		keyMap.put("prompt.viewHistory", "viewHistoryLOS");
		keyMap.put("prompt.add", "add");
		keyMap.put("button.viewActiveCases", "viewActiveCases");
		keyMap.put("button.casenotes", "casenotes");
		keyMap.put("prompt.viewProgramTrackerHistory", "viewProgramTrackerHistory");
		keyMap.put("prompt.addProgramTracker", "addProgramTracker");
		keyMap.put("prompt.removeProgramTracker", "removeProgramTracker");
		keyMap.put("prompt.addDNA", "addDNA");
		keyMap.put("prompt.viewDnaHistory", "viewHistoryDNA");	
		keyMap.put("prompt.viewDna", "viewDNA");
		
		
	}
	
   /**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward viewHistoryLOS(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		
			SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
			String superviseeId=superviseeForm.getSuperviseeId();
			SuperviseeHeaderForm myHeaderForm=(SuperviseeHeaderForm)getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
			myHeaderForm.setSuperviseeId(superviseeId);
			UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
			UIAdministerSuperviseeHelper.populateSuperviseeForm(superviseeForm);
			superviseeForm.setAllowLOSUpdates(false);
			ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
			Set userFeatures = securityManager.getFeatures(); 
			if (userFeatures.contains("CSCD-SUPERVISEE-LOS-CORRECT") ||
				userFeatures.contains("CSCD-SUPERVISEE-LOS-DELETE-WITHOUT-RESTRC") ||
				userFeatures.contains("CSCD-SUPERVISEE-LOS-DELETE-WITH-RESTRC") ) {
					superviseeForm.setAllowLOSUpdates(true);
			}		
			return aMapping.findForward(UIConstants.VIEW_HISTORY_LOS);
			
	}
	   /**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward viewProgramTrackerHistory(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		
			SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
			String superviseeId=superviseeForm.getSuperviseeId();
			SuperviseeHeaderForm myHeaderForm=(SuperviseeHeaderForm)getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
			myHeaderForm.setSuperviseeId(superviseeId);
			UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
			UIAdministerSuperviseeHelper.populateSuperviseeForm(superviseeForm);
			superviseeForm.setAllowLOSUpdates(false);
			ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
			Set userFeatures = securityManager.getFeatures(); 
			if (userFeatures.contains("CSCD-SUPERVISEE-PROGRAMTRACKER-ADD") ||
				userFeatures.contains("CSCD-SUPERVISEE-PROGRAMTRACKER-CORRECT") ||
				userFeatures.contains("CSCD-SUPERVISEE-PROGRAMTRACKER-DELETE") ||
				userFeatures.contains("CSCD-SUPERVISEE-PROGRAMTRACKER-REMOVE") ) {
					superviseeForm.setAllowProgramTrackerUpdates(true);
			}	
//			superviseeForm.setFlowType("");
			if ("".equals(superviseeForm.getSecondaryAction())){
				superviseeForm.setConfirmMessage("");
			}
			return aMapping.findForward(UIConstants.VIEW_HISTORY_PROGRAM_TRACKER);
			
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward add(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		
		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		superviseeForm.setSupervisionLevelHistoryId("");
		superviseeForm.setEffectiveDate(DateUtil.dateToString(DateUtil.getCurrentDate(), UIConstants.DATE_FMT_1));
		superviseeForm.setSupervisionLevel("");
		superviseeForm.setLosComments("");
		superviseeForm.setMostRecentEffectiveDate("");
		
		return aMapping.findForward(UIConstants.ADD_LOS);
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward addProgramTracker(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		
		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		superviseeForm.setProgramTrackerDesc("");
		superviseeForm.setProgramTrackerEffectiveDate(DateUtil.dateToString(DateUtil.getCurrentDate(), UIConstants.DATE_FMT_1));
		superviseeForm.setProgramTrackerEndDate(null);
		superviseeForm.setProgramTrackerId("");
		superviseeForm.setMostRecentEffectiveDate(null);
		superviseeForm.setMostRecentProgramTrackerEffectiveDate(null);
		superviseeForm.setMostRecentProgramTrackerEndDate(null);
		return aMapping.findForward(UIConstants.ADD_PROGRAM_TRACKER);
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward removeProgramTracker(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		
		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		superviseeForm.setProgramTrackerEndDate(DateUtil.dateToString(DateUtil.getCurrentDate(), UIConstants.DATE_FMT_1));

		return aMapping.findForward(UIConstants.REMOVE_PROGRAM_TRACKER);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward addDNA(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		
		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		superviseeForm.setDnaCollectedDate(DateUtil.dateToString(DateUtil.getCurrentDate(), UIConstants.DATE_FMT_1));
		superviseeForm.setDnaFlagInd(false);
						
		return aMapping.findForward(UIConstants.ADD_DNA);
	}

	   /**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward viewDNA(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		
			SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
			String superviseeId=superviseeForm.getSuperviseeId();
			SuperviseeHeaderForm myHeaderForm=(SuperviseeHeaderForm)getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
			myHeaderForm.setSuperviseeId(superviseeId);
			UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
			UIAdministerSuperviseeHelper.populateSuperviseeForm(superviseeForm);
			if (superviseeForm.getDnaHistories() != null) {
        		UIAdministerSuperviseeHelper.populateCurrentDNAInformationFromHistory(superviseeForm);
        	} 
			superviseeForm.setAllowDNAUpdates(false);
			ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
			Set userFeatures = securityManager.getFeatures(); 
			if (userFeatures.contains("CSCD-SUPERVISEE-DNA-ADD") ||
				userFeatures.contains("CSCD-SUPERVISEE-DNA-CORRECT") ||
				userFeatures.contains("CSCD-SUPERVISEE-DNA-DELETE")) {	
					superviseeForm.setAllowDNAUpdates(true);
			}
			if ("".equals(superviseeForm.getSecondaryAction())){
				superviseeForm.setConfirmMessage("");
			}
			return aMapping.findForward(UIConstants.VIEW_DNA);
			
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward viewActiveCases(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		
        SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
        String superviseeId = superviseeForm.getSuperviseeId();
        
        GetActiveCasesEvent event = (GetActiveCasesEvent) EventFactory.getInstance(	CaseloadControllerServiceNames.GETACTIVECASES);
        event.setDefendantId( superviseeId );

        CaseAssignmentResponseEvent assignmentResponse = (CaseAssignmentResponseEvent) 
        MessageUtil.postRequest( event , CaseAssignmentResponseEvent.class );
         
        List activeCases = assignmentResponse.getCaseAssignments();
        if( activeCases == null || activeCases.isEmpty() ){
	    	
        	sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"No Active Cases Found");
	    	return aMapping.findForward(UIConstants.VIEW_ACTIVE_CASES_FAILURE);
	    }
        for ( int x = 0; x <activeCases.size(); x++ ){
        	CaseAssignmentTO cato = ( CaseAssignmentTO ) activeCases.get(x);
        	
        	if ( cato.getCriminalCaseId() != null && !cato.getCriminalCaseId().equals("") ){
        		cato.setDisplayCaseNum(cato.getCriminalCaseId().substring(3,cato.getCriminalCaseId().length()));
        	}
        }
               
        SuperviseeSearchForm superviseeSearchForm =(SuperviseeSearchForm)getSessionForm( aMapping,aRequest,UIConstants.SUPERVISEE_SEARCH_FORM,true);
		superviseeSearchForm.setSpn(superviseeId);
		superviseeSearchForm.setRace(superviseeForm.getRace());
		superviseeSearchForm.setSex(superviseeForm.getSex());
		superviseeSearchForm.setSsn(superviseeForm.getSsn());
		superviseeSearchForm.setDateOfBirth(DateUtil.stringToDate(superviseeForm.getDateOfBirth(), DateUtil.DATE_FMT_1));
        if(superviseeSearchForm.getSearchResults() != null){
	     	Iterator iterator = superviseeSearchForm.getSearchResults().iterator();
	        while(iterator.hasNext()){
	        	Object obj = iterator.next();
	        	if(obj instanceof PartyListResponseEvent){
		        	PartyListResponseEvent pResp = (PartyListResponseEvent) obj;		    		
//		        	if(pResp.getSpn().equals(superviseeSearchForm.getSelectedValue())){
	        		if(pResp.getSpn().equals(superviseeId)) {	
		        		superviseeSearchForm.setSpn(pResp.getSpn());
		        		superviseeSearchForm.setRace(pResp.getRace());
		        		superviseeSearchForm.setSex(pResp.getSex());
		        		superviseeSearchForm.setSsn(pResp.getSsn());
		        		superviseeSearchForm.setDateOfBirth(pResp.getDateOfBirth());
		        		break;
		        	} 
	        	}else if(obj instanceof BasicSupervisee){
	        		BasicSupervisee bResp = (BasicSupervisee) obj;		    		
//		        	if(bResp.getSpn().equals(superviseeSearchForm.getSelectedValue())){
	        		if(bResp.getSpn().equals(superviseeId)) {		
		        		superviseeSearchForm.setSpn(bResp.getSpn());
		        		superviseeSearchForm.setRace(bResp.getRace());
		        		superviseeSearchForm.setSex(bResp.getSex());
		        		superviseeSearchForm.setSsn(bResp.getSsn());
		        		superviseeSearchForm.setDateOfBirth(bResp.getDateOfBirth());
		        		break;
		        	} 
	        	}
	        }
        } else {
    		superviseeSearchForm.setSpn(superviseeForm.getSuperviseeId());
    		superviseeSearchForm.setRace(superviseeForm.getRace());
    		superviseeSearchForm.setSex(superviseeForm.getSex());
    		superviseeSearchForm.setSsn(superviseeForm.getSsn());
    		if (superviseeForm.getDateOfBirth() != null){
    			superviseeSearchForm.setDateOfBirth(DateUtil.stringToDate(superviseeForm.getDateOfBirth(), DateUtil.DATE_FMT_1));
    		}
        }
        superviseeSearchForm.setActiveCases( activeCases );  
        return aMapping.findForward(UIConstants.VIEW_ACTIVE_CASES_SUCCESS); 
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward casenotes(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		
		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		
		SuperviseeSearchForm superviseeSearchForm=(SuperviseeSearchForm)getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_SEARCH_FORM,true);
		superviseeSearchForm.clearAll();
		if(!StringUtil.isEmpty(superviseeForm.getSuperviseeId())){
			superviseeSearchForm.setSuperviseeId(superviseeForm.getSuperviseeId());
			superviseeSearchForm.setSpn(superviseeForm.getSuperviseeId());
		} else {
			superviseeSearchForm.setSuperviseeId("");
			superviseeSearchForm.setSpn("");
		}	
		superviseeSearchForm.setAction(UIConstants.BASIC);
		superviseeSearchForm.setSelectedValue(UIConstants.BASIC);
		superviseeSearchForm.setSearchBy("SPN");
		superviseeSearchForm.setSupervisionPeriod(ACTIVE);
			
		return aMapping.findForward(UIConstants.CASENOTES_SUCCESS); 
	}	
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequloest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		SuperviseeForm superviseeForm = (SuperviseeForm) aForm;
		String forwardStr = UIConstants.CANCEL;
		if (superviseeForm.getFromPage() != null){
			if (superviseeForm.getFromPage().equalsIgnoreCase(UIConstants.FROM_CASELOAD)){
				forwardStr = UIConstants.CANCEL_CASELOAD;
			}
		}
		return aMapping.findForward(forwardStr); 
	}
}
