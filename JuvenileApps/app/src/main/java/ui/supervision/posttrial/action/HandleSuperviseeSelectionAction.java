//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\action\\DisplayCaseHistoryListAction.java

package ui.supervision.posttrial.action;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetActiveCasesEvent;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.contact.party.reply.PartyListResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.BasicSupervisee;
import ui.supervision.administercasenotes.form.SuperviseeSearchForm;

/*
 * 
 * @author mchowdhury
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class HandleSuperviseeSelectionAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public HandleSuperviseeSelectionAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.viewActiveCases", "viewActiveCases");
		keyMap.put("button.viewProfile", "viewProfile");
		keyMap.put("button.cancel", "cancel");
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
		SuperviseeSearchForm form = (SuperviseeSearchForm) aForm;
		String superviseeId = form.getSuperviseeId();
		if (!StringUtils.isNotEmpty(superviseeId)){
			superviseeId = form.getSelectedValue();
		}
		
        GetActiveCasesEvent event = (GetActiveCasesEvent) EventFactory.getInstance(CaseloadControllerServiceNames.GETACTIVECASES);
        event.setDefendantId(superviseeId);

        CaseAssignmentResponseEvent assignmentResponse = (CaseAssignmentResponseEvent) 
        MessageUtil.postRequest(event, CaseAssignmentResponseEvent.class);
        
        List activeCases = assignmentResponse.getCaseAssignments();
        if(activeCases == null || activeCases.isEmpty()){
	    	sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"No Active Cases Found");
	    	return aMapping.findForward(UIConstants.VIEW_ACTIVE_CASES_FAILURE);
	    }
        for (int x = 0; x <activeCases.size(); x++){
        	CaseAssignmentTO cato = (CaseAssignmentTO) activeCases.get(x);
        	if (cato.getCriminalCaseId() != null && !cato.getCriminalCaseId().equals("")){
        		cato.setDisplayCaseNum(cato.getCriminalCaseId().substring(3,cato.getCriminalCaseId().length()));
        	}
        }
        
        
		Iterator iterator = form.getSearchResults().iterator();
        while(iterator.hasNext()){
        	Object obj = (Object) iterator.next();
        	
        	if(obj instanceof PartyListResponseEvent){
            	PartyListResponseEvent pResp = (PartyListResponseEvent) obj;
        		
            	if(pResp.getSpn().equals(form.getSelectedValue())){
            		form.setSpn(pResp.getSpn());
            		form.setRace(pResp.getRace());
            		form.setSex(pResp.getSex());
            		form.setSsn(pResp.getSsn());
            		form.setDateOfBirth(pResp.getDateOfBirth());
            		break;
            	} 
        	}else if(obj instanceof BasicSupervisee){
        		BasicSupervisee bResp = (BasicSupervisee) obj;		    		
            	if(bResp.getSpn().equals(form.getSelectedValue())){
            		form.setSpn(bResp.getSpn());
            		form.setRace(bResp.getRace());
            		form.setSex(bResp.getSex());
            		form.setSsn(bResp.getSsn());
            		form.setDateOfBirth(bResp.getDateOfBirth());
            		break;
            	} 
        	}
        }
        form.setActiveCases(activeCases);
        return aMapping.findForward(UIConstants.VIEW_ACTIVE_CASES_SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public ActionForward viewProfile(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
			
		// Forwards to DisplaySuperviseeInfoAction and rebuilds the form
		return aMapping.findForward(UIConstants.VIEW_PROFILE_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse){
		
		SuperviseeSearchForm form = (SuperviseeSearchForm) aForm;
		form.setSuperviseeId("");
// add clear for basic search fields		
		return aMapping.findForward(UIConstants.CANCEL);
	}
}
