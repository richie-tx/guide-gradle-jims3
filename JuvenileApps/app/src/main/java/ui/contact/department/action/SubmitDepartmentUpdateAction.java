//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\department\\action\\SubmitDepartmentUpdateAction.java

package ui.contact.department.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.agency.DeleteDepartmentEvent;
import messaging.agency.UpdateContactEvent;
import messaging.agency.UpdateDepartmentEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.common.UIUtil;
import ui.contact.department.UIDepartmentHelper;
import ui.contact.department.form.DepartmentForm;

public class SubmitDepartmentUpdateAction extends LookupDispatchAction
{

	/**
	 * @roseuid 430630EF03A3
	 */
	public SubmitDepartmentUpdateAction()
	{

	}

	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.mainPage", "cancel");
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
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm departmentForm = (DepartmentForm) aForm;
		departmentForm.clear();
		return aMapping.findForward(UIConstants.CANCEL);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	private ActionForward commonFinish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm departmentForm = (DepartmentForm) aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		// Sending PD Request Event
		UpdateDepartmentEvent event = UIDepartmentHelper.getUpdateDepartmentEvent(departmentForm);
		if (!UIUtil.isGoodAddress(departmentForm.getPhysicalAddress())
			&& !(UIUtil.isGoodAddress(departmentForm.getMailingAddress())))
		{
			sendToErrorPage(aRequest, "error.manage.department.atleastOneAddressHasToBePresent");
		}
		dispatch.postEvent(event);

		// Getting PD Response Event		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		return null;

	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward deleteFinish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm departmentForm = (DepartmentForm) aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		// Sending PD Request Event
		DeleteDepartmentEvent event = new DeleteDepartmentEvent();
		event.setDepartmentId(departmentForm.getDepartmentId());

		dispatch.postEvent(event);

		// Getting PD Response Event		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		// Set correct display action value
		departmentForm.setAction(UIConstants.CONFIRM_DELETE);

		return aMapping.findForward(UIConstants.CONFIRM_DELETE_SUCCESS);

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward copyFinish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm departmentForm = (DepartmentForm) aForm;
		//loop through removing contactId's and deleted contacts prior to sending to pd

		Collection myColl = departmentForm.getContactList();
		if (myColl != null && myColl.size() > 0)
		{
			Iterator iter = myColl.iterator();
			while (iter.hasNext())
			{
				UpdateContactEvent contactEvent = (UpdateContactEvent) iter.next();
				contactEvent.setContactId("");
				contactEvent.setDepartmentId(departmentForm.getDepartmentId());
				if (contactEvent.isDeletable())
				{
					iter.remove();
				}
			}
		}
		commonFinish(aMapping, aForm, aRequest, aResponse);
		// Set correct display action value
		departmentForm.setAction(UIConstants.CONFIRM_COPY);

		return aMapping.findForward(UIConstants.CONFIRM_COPY_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward updateFinish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm departmentForm = (DepartmentForm) aForm;
		commonFinish(aMapping, aForm, aRequest, aResponse);
		// Set correct display action value
		departmentForm.setAction(UIConstants.CONFIRM_UPDATE);

		return aMapping.findForward(UIConstants.CONFIRM_UPDATE_SUCCESS);

	}

	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	*/
	public ActionForward createFinish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{

		DepartmentForm departmentForm = (DepartmentForm) aForm;
		commonFinish(aMapping, aForm, aRequest, aResponse);
		departmentForm.setAction(UIConstants.CONFIRM_CREATE);
		return aMapping.findForward(UIConstants.CONFIRM_CREATE_SUCCESS);
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

		DepartmentForm departmentForm = (DepartmentForm) aForm;
		UIDepartmentHelper.setManageDepartmentValuesFromDropDownList(departmentForm);
		if (departmentForm.getAction().equals(UIConstants.DEPT_UPDATE))
		{
			return updateFinish(aMapping, aForm, aRequest, aResponse);
		}
		else
			if (departmentForm.getAction().equals(UIConstants.DEPT_CREATE))
			{
				return createFinish(aMapping, aForm, aRequest, aResponse);
			}
			else
				if (departmentForm.getAction().equals(UIConstants.DEPT_DELETE))
				{
					return deleteFinish(aMapping, aForm, aRequest, aResponse);
				}
				else
					if (departmentForm.getAction().equals(UIConstants.DEPT_COPY))
					{
						return copyFinish(aMapping, aForm, aRequest, aResponse);
					}

		return aMapping.findForward(UIConstants.FAILURE);
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
		return aMapping.findForward(UIConstants.MAIN_MENU);
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