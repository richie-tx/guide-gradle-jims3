//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\HandleManageUserGroupUserRemoveAction.java

package ui.security.action;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mojo.km.messaging.exception.ReturnException;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.common.IShoppingCart;
import ui.common.ShoppingCartImpl;
import ui.security.form.UserGroupForm;

public class HandleUserGroupUserRemoveAction extends Action
{

	/**
	 * @roseuid 425AB74701C5
	 */
	public HandleUserGroupUserRemoveAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4256D6310196
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		String userId = aRequest.getParameter("logonId");
		String pageType = aRequest.getParameter("pageType");
		Collection availableUserList = userGroupForm.getAvailableUsers();
		Collection currentUserList = userGroupForm.getCurrentUsers();
		String[] selectedUsersId = { userId };
		aRequest.setAttribute("pageType", "");

		IShoppingCart sCart = new ShoppingCartImpl();
		String userEventName = UIConstants.USER_RESPONSE_EVENT;
		String userEventIdName = UIConstants.USER_RESPONSE_EVENT_ID;
		try
		{
			availableUserList =
				sCart.addToAvailableShoppingItemList(
					userEventName,
					userEventIdName,
					selectedUsersId,
					currentUserList,
					availableUserList);

			userGroupForm.setAvailableUsers(availableUserList);

			currentUserList =
				sCart.removeFromShoppingCart(
					userEventName,
					userEventIdName,
					selectedUsersId,
					currentUserList,
					availableUserList);

			userGroupForm.setCurrentUsers(currentUserList);
		}
		catch (Exception e){
		   e.printStackTrace();
		   ReturnException returnException = new ReturnException(e.getMessage());
		   throw returnException;
		}
		int size = userGroupForm.getAvailableUsers().size();
		userGroupForm.setUserSearchResultSize(String.valueOf(size));

		String forward = UIConstants.CREATE_REMOVE_USER_SUCCESS;
		if (userGroupForm.getAction().equals(UIConstants.UPDATE))
		{
			forward = UIConstants.UPDATE_REMOVE_USER_SUCCESS;
			if (pageType != null)
			{
				if (pageType.equalsIgnoreCase("userUpdate"))
				{
					forward = UIConstants.UPDATE_USER_REMOVE_SUCCESS;
				}
			}
		}
		return aMapping.findForward(forward);
	}
}