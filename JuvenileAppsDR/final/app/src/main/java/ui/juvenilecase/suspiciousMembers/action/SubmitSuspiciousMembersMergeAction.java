//Source file: C:\\views\\juvenilecasework\\app\\src\\ui\\juvenilecase\\suspiciousMembers\\action\\SubmitSuspiciousMemberMergeAction.java

package ui.juvenilecase.suspiciousMembers.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.MergeSuspiciousFamilyMembersEvent;
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

public class SubmitSuspiciousMembersMergeAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public SubmitSuspiciousMembersMergeAction() {

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
		String forward = UIConstants.FAILURE;
		smForm.setReturnToSearch(UIConstants.EMPTY_STRING);
		List jposList = UIJuvenileHelper.loadJPOs(smForm.getSelectedFromId(),smForm.getSelectedToId());
		MergeSuspiciousFamilyMembersEvent reqEvent = 
			(MergeSuspiciousFamilyMembersEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.MERGESUSPICIOUSFAMILYMEMBERS);
		
		reqEvent.setFromFamilyMemberId(smForm.getSelectedFromId());
		reqEvent.setToFamilyMemberId(smForm.getSelectedToId());
		
		MessageUtil.postRequest(reqEvent);

		List matchList = new ArrayList();
		String fromMemberName = UIConstants.EMPTY_STRING;
		String toMemberName = UIConstants.EMPTY_STRING;
		
		for (int x=0; x < smForm.getMatchingMembersList().size(); x++)
		{
			FamilyMemberListResponseEvent fmre = (FamilyMemberListResponseEvent) smForm.getMatchingMembersList().get(x);
			if (!smForm.getSelectedFromId().equals(fmre.getMemberNum() ) )
			{	
				matchList.add(fmre);
			}
			if (smForm.getSelectedFromId().equals(fmre.getMemberNum() ) )
			{	
				fromMemberName = fmre.getFirstName() + " " + fmre.getLastName();
			}
			if (smForm.getSelectedToId().equals(fmre.getMemberNum() ) )
			{	
				toMemberName = fmre.getFirstName() + " " + fmre.getLastName();
			}
			fmre = null;
		}
		smForm.setConfirmationMsg("Family Member data has been successfully merged but no JPO found to notify. The merged family member record has been deleted.");
		if( jposList != null && !jposList.isEmpty() ) {
			UIJuvenileHelper.sendOutJPONotificationForMemberMerge(jposList, smForm.getSelectedFromId(), fromMemberName, smForm.getSelectedToId(), toMemberName, smForm.getAssociatedJuvenilesList());
			smForm.setConfirmationMsg("Family Member data has been successfully merged and notices sent. The merged family member record has been deleted.");
		}
		smForm.setSecondaryAction(UIConstants.CONFIRM);
		smForm.setReturnToSearch(UIConstants.YES);
		
		fromMemberName = null;
		toMemberName = null;
		jposList = null;
		forward = UIConstants.SUCCESS;
		
		return aMapping.findForward(forward);
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
		smForm.setSelectedValue(UIConstants.EMPTY_STRING);  //need to fix this.
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
		smForm.setSelectedOrigMemberNum(UIConstants.EMPTY_STRING);  // need to fix this
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
//		SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
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
		SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
		smForm.clearSearch();
		return aMapping.findForward(UIConstants.CANCEL);
	}	
}