//Source file: C:\\views\\juvenilecasework\\app\\src\\ui\\juvenilecase\\suspiciousMembers\\action\\DisplaySuspiciousMemberSearchAction.java

package ui.juvenilecase.suspiciousMembers.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.FamilyMemberListResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.suspiciousMembers.UISuspiciousMemberDetailsHelper;
import ui.juvenilecase.suspiciousMembers.form.SuspiciousMemberForm;


/*
 * 
 * @author cShimek
 * 
 */

public class DisplaySuspiciousMembersReplaceSummaryAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplaySuspiciousMembersReplaceSummaryAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
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
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
		int len = smForm.getProcessList().size();
		List matchList = new ArrayList();
		for (int x=0; x< len; x++ )
		{
			FamilyMemberListResponseEvent fmre = (FamilyMemberListResponseEvent) smForm.getProcessList().get(x);
			if (smForm.getSelectedToId().equals(fmre.getMemberNum() ) )
			{
				matchList.add(fmre);
				break;
			}
		}
		smForm.setMergeMemberToList(matchList);
		
		List filteredList = UISuspiciousMemberDetailsHelper.filterJuvenilesByMemberNumber(smForm.getSelectedFromId(), smForm.getSelectedToId(), smForm.getAllAssociatedJuvenilesList());
		filteredList = UISuspiciousMemberDetailsHelper.eliminateDuplicateJuveniles(filteredList);
		smForm.setAssociatedJuvenilesListOrig(filteredList);
		smForm.setAssociatedJuvenilesList(filteredList);

		matchList = null;
		smForm.setSecondaryAction(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.SUCCESS);
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
		SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
		smForm.clear();
		return aMapping.findForward(UIConstants.CANCEL);
	}	
}