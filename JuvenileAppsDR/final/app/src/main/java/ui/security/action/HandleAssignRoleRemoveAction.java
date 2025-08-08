//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\HandleAssignUserRolesAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.security.GetRoleAgencyInfoEvent;
import messaging.security.reply.RoleResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.IShoppingCart;
import ui.common.ShoppingCartImpl;
import ui.security.SecurityUIHelper;
import ui.security.form.AssignRolesForm;

/**
 * 
 * 
 * @author awidjaja
 * @description This action is to handle removal of roles from both current 
 * roles of User or User Group 
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class HandleAssignRoleRemoveAction extends Action
{

	/**
	 * description public constructor
	 * @roseuid 4297202202EF
	 */
	public HandleAssignRoleRemoveAction()
	{
	}

	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		AssignRolesForm assignRolesForm = (AssignRolesForm) aForm;
		String roleId = aRequest.getParameter("roleId");
		Collection availableRoles = assignRolesForm.getAvailableRoles();
		Collection currentRoles = assignRolesForm.getCurrentRoles();
		String[] selectedRoleIds = { roleId };
		try
		{
			String responseEventName = UIConstants.ROLE_RESPONSE_EVENT;
			String responseEventItemId = UIConstants.ROLE_RESPONSE_EVENT_ID;
			IShoppingCart sCart = new ShoppingCartImpl();

			availableRoles =
				sCart.addToAvailableShoppingItemList(
					responseEventName,
					responseEventItemId,
					selectedRoleIds,
					currentRoles,
					availableRoles);
			currentRoles =
				sCart.removeFromShoppingCart(
					responseEventName,
					responseEventItemId,
					selectedRoleIds,
					currentRoles,
					availableRoles);

			assignRolesForm.setCurrentRoles(SecurityUIHelper.sortRoleNames(currentRoles));			
			//get the agency name if that is not there
			availableRoles = this.loadAgencyInfo(availableRoles);
			assignRolesForm.setAvailableRoles(SecurityUIHelper.sortRoleNames(availableRoles));
		}
		catch (Exception e){
		   e.printStackTrace();
		   ReturnException returnException = new ReturnException(e.getMessage());
		   throw returnException;
		}
		return aMapping.findForward(UIConstants.ASSIGN_ROLE_REMOVE_SUCCESS);
	}

	/**
	 * @param roles
	 * @return Collection
	 */
	private Collection loadAgencyInfo(Collection roles)
	{
		//get agency names associated with each of those roles for display purpose
		Collection rolesList = new ArrayList();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetRoleAgencyInfoEvent getRoleAgencyInfoRequestEvent = (GetRoleAgencyInfoEvent) EventFactory.getInstance(naming.SecurityAdminControllerServiceNames.GETROLEAGENCYINFO);
		RoleResponseEvent eachRoleEvent = null;
		for (Iterator iter = roles.iterator(); iter.hasNext();)
		{
			eachRoleEvent = (RoleResponseEvent) iter.next();
			if (eachRoleEvent.getAgencyName() == null || eachRoleEvent.getAgencyName().equals(""))
			{
				getRoleAgencyInfoRequestEvent.setRoleId(eachRoleEvent.getRoleId());
				dispatch.postEvent(getRoleAgencyInfoRequestEvent);
				
				CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
				Map dataMap = MessageUtil.groupByTopic(compositeResponse);
				MessageUtil.processReturnException(dataMap);

				eachRoleEvent = (RoleResponseEvent) MessageUtil.filterComposite(compositeResponse, RoleResponseEvent.class);
			}

			rolesList.add(eachRoleEvent);
		}
		return rolesList;
	}
}