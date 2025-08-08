//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\department\\action\\DisplayDepartmentDetailsAction.java

package ui.contact.department.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetDepartmentEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.PDAddressConstants;
import naming.PDSecurityConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.contact.department.LoadManageDepartmentCodeTables;
import ui.contact.department.UIDepartmentHelper;
import ui.contact.department.form.DepartmentForm;

public class DisplayDepartmentDetailsAction extends LookupDispatchAction
{

	/**
	 * @roseuid 430630E80132
	 */
	public DisplayDepartmentDetailsAction()
	{

	}
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.update", "update");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.copy", "copy");
		buttonMap.put("button.delete", "delete");
		buttonMap.put("button.view", "view");
		buttonMap.put("button.createContact", "createContact");
		buttonMap.put("button.modifyContact", "modifyContact");
		return buttonMap;
	}

	/**
		* @param aMapping
		* @param aForm
		* @param aRequest
		* @param aResponse
		* @return ActionForward
		*/
	public ActionForward modifyContact(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm departmentForm = (DepartmentForm) aForm;
		departmentForm.setAction(UIConstants.CONTACT_MODIFY);
		ActionForward commonForward = common(aMapping, aForm, aRequest, aResponse);
		if (commonForward != null)
			return commonForward;
		departmentForm.clearContactFields();
		Collection contacts = departmentForm.getContactList();
		departmentForm.setContactList(UIDepartmentHelper.sortContactNames(contacts));
		return aMapping.findForward(UIConstants.MODIFY_CONTACT_SUCCESS);
	}

	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	*/
	public ActionForward createContact(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm departmentForm = (DepartmentForm) aForm;

		ActionForward commonForward = common(aMapping, aForm, aRequest, aResponse);
		if (commonForward != null){
			return commonForward;
		}
		departmentForm.setAction(UIConstants.CONTACT_CREATE);
		departmentForm.clearContactFields();
		Collection contacts = departmentForm.getContactList();
		departmentForm.setContactList(UIDepartmentHelper.sortContactNames(contacts));
		
		return aMapping.findForward(UIConstants.CREATE_CONTACT_SUCCESS);

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
	public ActionForward common(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm departmentForm = (DepartmentForm) aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		// Sending PD Request Event
		GetDepartmentEvent event = (GetDepartmentEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENT);
		event.setDepartmentId(departmentForm.getDepartmentId());
		dispatch.postEvent(event);

		// Getting PD Response Event	 
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		
		if (dataMap == null || dataMap.size() < 1)
		{
			this.sendToErrorPage(aRequest, "error.noRecords");
			return aMapping.findForward(UIConstants.FAILURE);
		}

		if (dataMap.containsKey(PDSecurityConstants.DEPARTMENT_EVENT_TOPIC))
		{
			ArrayList departments = (ArrayList) dataMap.get(PDSecurityConstants.DEPARTMENT_EVENT_TOPIC);
			UIDepartmentHelper.setDepartmentProfileForm((DepartmentResponseEvent) departments.get(0), departmentForm);
		}

		if (dataMap.containsKey(PDAddressConstants.ADDRESS_EVENT_TOPIC))
		{
			// set Address Response Events
			Collection addresses = (Collection) dataMap.get(PDAddressConstants.ADDRESS_EVENT_TOPIC);
			UIDepartmentHelper.setAddressResponseEvents(departmentForm, addresses);
		}

		if (dataMap.containsKey(PDSecurityConstants.DEPARTMENT_CONTACT_LISTITEM_EVENT_TOPIC))
		{
			Collection contacts = (Collection) dataMap.get(PDSecurityConstants.DEPARTMENT_CONTACT_LISTITEM_EVENT_TOPIC);
			UIDepartmentHelper.setContactResponseEvents(departmentForm, contacts);
		}

		if (dataMap.containsKey(PDSecurityConstants.DEPARTMENT_SETCIC_CONTACT_LISTITEM_EVENT_TOPIC))
		{
			Collection setcicContacts =
				(Collection) dataMap.get(PDSecurityConstants.DEPARTMENT_SETCIC_CONTACT_LISTITEM_EVENT_TOPIC);
			UIDepartmentHelper.setSetcicContactResponseEvent(departmentForm, setcicContacts);
		}
		LoadManageDepartmentCodeTables instance = LoadManageDepartmentCodeTables.getInstance();
				instance.setDepartmentForm(departmentForm);
		// set the original value of critical data
		UIDepartmentHelper.setManageDepartmentValuesFromDropDownList(departmentForm);
		return null; // Note this is a good return
	}

	/**
		* @param aMapping
		* @param aForm
		* @param aRequest
		* @param aResponse
		* @return ActionForward
		*/
	public ActionForward view(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm departmentForm = (DepartmentForm) aForm;
		departmentForm.setAction(UIConstants.DEPT_VIEW);
		ActionForward commonForward = common(aMapping, aForm, aRequest, aResponse);
		if (commonForward != null){
			return commonForward;
		}
		Collection contacts = departmentForm.getContactList();
		departmentForm.setContactList(UIDepartmentHelper.sortContactNames(contacts));			
		return aMapping.findForward(UIConstants.VIEW_DEPARTMENT_SUCCESS);
	}

	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	*/
	public ActionForward copy(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm departmentForm = (DepartmentForm) aForm;
		departmentForm.setAction(UIConstants.DEPT_COPY);
		ActionForward commonForward = common(aMapping, aForm, aRequest, aResponse);
		if (commonForward != null){
			return commonForward;
		}
		Collection contacts = departmentForm.getContactList();
		departmentForm.setContactList(UIDepartmentHelper.sortContactNames(contacts));
		return aMapping.findForward(UIConstants.COPY_DEPT_SUCCESS);
	}

	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	*/
	public ActionForward delete(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm departmentForm = (DepartmentForm) aForm;
		departmentForm.setAction(UIConstants.DEPT_DELETE);
		ActionForward commonForward = common(aMapping, aForm, aRequest, aResponse);
		if (commonForward != null){
			return commonForward;
		}
		Collection contacts = departmentForm.getContactList();
		departmentForm.setContactList(UIDepartmentHelper.sortContactNames(contacts));		
		return aMapping.findForward(UIConstants.DELETE_DEPT_SUCCESS);
	}

	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	*/
	public ActionForward update(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm departmentForm = (DepartmentForm) aForm;
		departmentForm.setAction(UIConstants.DEPT_UPDATE);
		ActionForward commonForward = common(aMapping, aForm, aRequest, aResponse);
		if (commonForward != null){
			return commonForward;
		}
		Collection contacts = departmentForm.getContactList();
		departmentForm.setContactList(UIDepartmentHelper.sortContactNames(contacts));
		return aMapping.findForward(UIConstants.UPDATE_DEPT_SUCCESS);
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

} // END CLASS
