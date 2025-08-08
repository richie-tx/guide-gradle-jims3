//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\SubmitAssignUserRolesAction.java

package ui.security.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.security.UpdateSecurityRolesForUserEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.SecurityAdminControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.security.form.AssignRolesForm;

/**
 * 
 * 
 * @author awidjaja
 * @description This action update the role(s) of a User
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SubmitAssignUserRolesAction extends LookupDispatchAction
{

	/**
	 * description public constructor
	 * @roseuid 4297202E0243
	 */
	public SubmitAssignUserRolesAction()
	{

	}

	/**
	 * @return Map
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.mainPage", "mainPage");
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		AssignRolesForm assignRolesForm = (AssignRolesForm) aForm;

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		UpdateSecurityRolesForUserEvent updateEvent = (UpdateSecurityRolesForUserEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.UPDATESECURITYROLESFORUSER);
		updateEvent.setLogonId(assignRolesForm.getUserId());
		updateEvent.setRoles(assignRolesForm.getCurrentRoles());
		dispatch.postEvent(updateEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		return aMapping.findForward(UIConstants.ASSIGN_USER_ROLES_CONFIRM_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward mainPage(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.MAIN_PAGE);
	}
}