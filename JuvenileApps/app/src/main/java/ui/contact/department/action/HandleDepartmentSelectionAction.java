//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\department\\action\\HandleDepartmentSelectionAction.java

package ui.contact.department.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
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
import org.apache.struts.actions.LookupDispatchAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.contact.department.LoadManageDepartmentCodeTables;
import ui.contact.department.UIDepartmentHelper;
import ui.contact.department.form.DepartmentForm;

public class HandleDepartmentSelectionAction extends LookupDispatchAction
{

	/**
	 * @roseuid 430630ED03A3
	 */
	public HandleDepartmentSelectionAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 430628E40090
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm departmentForm = (DepartmentForm) aForm;
		String action = departmentForm.getAction();

		if (action == null || action.equals(""))
		{
			sendToErrorPage(aRequest, "error.common");
		}

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
			Collection unsortedContacts = departmentForm.getContactList();
			departmentForm.setContactList(UIDepartmentHelper.sortContactNames(unsortedContacts));			
		}
		if (dataMap.containsKey(PDSecurityConstants.DEPARTMENT_SETCIC_CONTACT_LISTITEM_EVENT_TOPIC))
		{
			Collection setcicContacts =	(Collection) dataMap.get(PDSecurityConstants.DEPARTMENT_SETCIC_CONTACT_LISTITEM_EVENT_TOPIC);
			UIDepartmentHelper.setSetcicContactResponseEvent(departmentForm, setcicContacts);
		}
		//LoadManageDepartmentCodeTables instance = LoadManageDepartmentCodeTables.getInstance();
		//instance.setDepartmentForm(departmentForm);
		UIDepartmentHelper.setManageDepartmentValuesFromDropDownList(departmentForm);
		departmentForm.setAction(UIConstants.DEPT_VIEW);
		return aMapping.findForward(UIConstants.VIEW_DEPARTMENT_SUCCESS);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		return null;
	}

	/**
	* @param aRequest
	* @param errorKey
	*/
	private void sendToErrorPage(HttpServletRequest aRequest, String errorKey)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey));
		saveErrors(aRequest, errors);
	}
}