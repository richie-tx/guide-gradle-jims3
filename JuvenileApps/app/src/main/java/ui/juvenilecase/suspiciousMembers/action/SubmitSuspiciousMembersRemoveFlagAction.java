//Source file: C:\\views\\juvenilecasework\\app\\src\\ui\\juvenilecase\\suspiciousMembers\\action\\SubmitSuspiciousMemberReplaceAction.java

package ui.juvenilecase.suspiciousMembers.action;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.RemoveSuspiciousFamilyMemberFlagsEvent;
import messaging.juvenilecase.reply.FamilyMemberListResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.suspiciousMembers.form.SuspiciousMemberForm;


/*
 * 
 * @author cShimek
 * 
 */

public class SubmitSuspiciousMembersRemoveFlagAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public SubmitSuspiciousMembersRemoveFlagAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.backToSearch", "backToSearch");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
		
		List <FamilyMemberListResponseEvent> matchingMembersList = smForm.getMatchingMembersList();
		
		RemoveSuspiciousFamilyMemberFlagsEvent compReqEvent = (RemoveSuspiciousFamilyMemberFlagsEvent)
			EventFactory.getInstance(JuvenileFamilyControllerServiceNames.REMOVESUSPICIOUSFAMILYMEMBERFLAGS);
		
		FamilyMemberListResponseEvent re = null;
		RemoveSuspiciousFamilyMemberFlagsEvent reqEvent;
		
		for (int i = 0; i < matchingMembersList.size(); i++) {
			re = matchingMembersList.get(i);
			reqEvent = new RemoveSuspiciousFamilyMemberFlagsEvent();
			reqEvent.setFamilyMemberId(re.getMemberNum());
			compReqEvent.addRequest(reqEvent);
		}
		
		MessageUtil.postRequest(compReqEvent);

		for (int i = 0; i < matchingMembersList.size(); i++) {
			re = matchingMembersList.get(i);
		    UIJuvenileHelper.sendOutJPONotificationForMemberRemove(re.getMemberNum(), smForm.getAssociatedJuvenilesList());
		}

		smForm.setConfirmationMsg("Suspicious member flag(s) have been successfully removed and notices sent.");
		smForm.setSecondaryAction(UIConstants.CONFIRM);
		smForm.setReturnToSearch(UIConstants.YES);
		
		re = null;
		reqEvent = null;
		compReqEvent = null;
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward backToSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
		smForm.clear();
		return aMapping.findForward(UIConstants.BACK_TO_SEARCH);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
		smForm.setMatchingMembersList(new ArrayList());
		smForm.setAssociatedJuvenilesList(smForm.getAllAssociatedJuvenilesList());
		return aMapping.findForward(UIConstants.BACK);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.CANCEL);
	}	
}