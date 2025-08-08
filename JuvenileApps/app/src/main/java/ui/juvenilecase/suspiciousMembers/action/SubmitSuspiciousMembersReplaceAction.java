//Source file: C:\\views\\juvenilecasework\\app\\src\\ui\\juvenilecase\\suspiciousMembers\\action\\SubmitSuspiciousMemberReplaceAction.java

package ui.juvenilecase.suspiciousMembers.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.ReplaceSuspiciousFamilyMemberEvent;
import messaging.juvenilecase.reply.FamilyMemberListResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.SuspiciousFamilyMemberControllerServiceNames;
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

public class SubmitSuspiciousMembersReplaceAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public SubmitSuspiciousMembersReplaceAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.backToResolutionSelection", "backToSelect");
		keyMap.put("button.backToSearch", "backToSearch");

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
		smForm.setReturnToSearch(UIConstants.EMPTY_STRING);
		String forwardStr = UIConstants.FAILURE;
		String selectedFromName = UIConstants.EMPTY_STRING;
		String selectedToName = UIConstants.EMPTY_STRING;
// get replacement member names for notices		
		for (int x=0; x< smForm.getProcessList().size(); x++)
		{
			FamilyMemberListResponseEvent fmre = (FamilyMemberListResponseEvent) smForm.getProcessList().get(x);
			if (smForm.getSelectedFromId().equals(fmre.getMemberNum() ) )
			{	
				
				selectedFromName = fmre.getFirstName() + " " + fmre.getLastName();
			}
			if (smForm.getSelectedToId().equals(fmre.getMemberNum() ) )
			{	
				selectedToName = fmre.getFirstName() + " " + fmre.getLastName();
			}
		}
		List jposList = UIJuvenileHelper.loadJPOs(smForm.getSelectedFromId(),smForm.getSelectedToId());
		ReplaceSuspiciousFamilyMemberEvent reqEvent = (ReplaceSuspiciousFamilyMemberEvent)
			EventFactory.getInstance(SuspiciousFamilyMemberControllerServiceNames.REPLACESUSPICIOUSFAMILYMEMBER);
		reqEvent.setFamilyMemberIdToBeReplaced(smForm.getSelectedFromId());
		reqEvent.setTargetFamilyMemberId(smForm.getSelectedToId());
		
		MessageUtil.postRequest(reqEvent);
		
		smForm.setConfirmationMsg("Family Member has been successfully replaced but no JPO found to notify.");
		if( jposList != null && !jposList.isEmpty() ) {
			UIJuvenileHelper.sendOutJPONotificationForMemberReplace(jposList, smForm.getSelectedFromId(), selectedFromName, smForm.getSelectedToId(),selectedToName, smForm.getAssociatedJuvenilesList() );
			smForm.setConfirmationMsg("Family Member has been successfully replaced and notices sent.");
		}	
		forwardStr = UIConstants.SUCCESS;
		smForm.setSecondaryAction(UIConstants.CONFIRM);
		smForm.setReturnToSearch(UIConstants.YES);
		
		selectedFromName = null;
		selectedToName = null;
		jposList = null;
		return aMapping.findForward(forwardStr);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward backToSelect(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
		smForm.setSelectedValue(UIConstants.EMPTY_STRING);  // need to fix this
		return aMapping.findForward(UIConstants.BACK_TO_SELECT);
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
		smForm.clearSearch(); 
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