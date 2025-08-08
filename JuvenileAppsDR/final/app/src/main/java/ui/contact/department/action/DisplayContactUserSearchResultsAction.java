//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\department\\action\\DisplayContactUserSearchResultsAction.java

package ui.contact.department.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.contact.user.reply.SecurityUserResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.contact.department.UIDepartmentHelper;
import ui.contact.department.form.DepartmentForm;

public class DisplayContactUserSearchResultsAction extends LookupDispatchAction
{

	/**
	 * @roseuid 430630E60019
	 */
	public DisplayContactUserSearchResultsAction()
	{

	}

	/** 
		* @see LookupDispatchAction#getKeyMethodMap()
		*/
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.findUsers", "findUsers");
		buttonMap.put("button.selectUser", "selectUser");
		buttonMap.put("button.refresh", "refresh");		
		buttonMap.put("button.cancel", UIConstants.CANCEL);
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 430628E40274
	 */
	public ActionForward findUsers(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm deptForm = (DepartmentForm) aForm;
		String lName = deptForm.getUserName().getLastName();
		String fName = deptForm.getUserName().getFirstName();
		String userId = deptForm.getUserLogonId();
		String agencyId = deptForm.getAgencyId();
		Collection usersList = UIDepartmentHelper.fetchUsers(lName, fName, userId, agencyId);
		deptForm.setUserList(UIDepartmentHelper.sortUserNames(usersList));		
		
//		deptForm.setUserList(usersList);
		if (deptForm.getUserList() == null || deptForm.getUserList().size() < 1)
		{
			sendToErrorPage(aRequest, "error.no.userProfile.found");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		return aMapping.findForward(UIConstants.FIND_SUCCESS);
	}

	/**
		* @param aMapping
		* @param aForm
		* @param aRequest
		* @param aResponse
		* @return ActionForward
		* @roseuid 430628E40274
		*/
	public ActionForward selectUser(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm deptForm = (DepartmentForm) aForm;
		String userId = deptForm.getLogonId();
		if (userId == null || userId.equalsIgnoreCase(""))
			return aMapping.findForward(UIConstants.FAILURE);
		SecurityUserResponseEvent userEvt = UIDepartmentHelper.findUserByID(deptForm.getUserList(), userId);
		deptForm.setContactEmail(userEvt.getEmail());
		deptForm.setLogonId(userEvt.getLogonId());
		deptForm.getContactName().setFirstName(userEvt.getFirstName());
		deptForm.getContactName().setMiddleName(userEvt.getMiddleName());
		deptForm.getContactName().setLastName(userEvt.getLastName());

		return aMapping.findForward(UIConstants.FIND_SUCCESS);
	}
	/**
		* @param aMapping
		* @param aForm
		* @param aRequest
		* @param aResponse
		* @return ActionForward
		* @roseuid 430628E40274
		*/
	public ActionForward refresh(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm deptForm = (DepartmentForm) aForm;
		//empty all collection so that the display page does not display it
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		deptForm.setUserList(emptyColl);
		deptForm.getUserName().setLastName(""); 
		deptForm.getUserName().setFirstName(""); 		
		deptForm.setUserLogonId("");
		// save any values entered in contact information on form
/**		deptForm.getContactName().getLastName();  Not working
		deptForm.getContactName().getFirstName();
		deptForm.getContactName().getMiddleName();
		deptForm.getContactJobTitle();
		deptForm.getLogonId();
		deptForm.getContactPhoneNumber().getAreaCode();
		deptForm.getContactPhoneNumber().getPrefix();
		deptForm.getContactPhoneNumber().getLast4Digit();
		deptForm.getContactPhoneNumber().getExt();
		deptForm.getLiaisonTrainingInd();
		deptForm.getContactEmail(); */
		
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}
	/**
	 * @param aRequest
	 */
	private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}
}
