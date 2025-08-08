//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\department\\action\\DisplayDepartmentSummaryAction.java

package ui.contact.department.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.agency.UpdateContactEvent;
import messaging.agency.ValidateDepartmentCreateRequirementsEvent;
import messaging.agency.ValidateDepartmentUpdateRequirementsEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.contact.department.UIDepartmentHelper;
import ui.contact.department.form.DepartmentForm;

public class DisplayDepartmentSummaryAction extends LookupDispatchAction
{

	/**
	 * @roseuid 430630EB0365
	 */
	public DisplayDepartmentSummaryAction()
	{

	}

	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", "next");
		buttonMap.put("button.cancel", "cancel");
		return buttonMap;
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
		return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
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
//		DepartmentForm departmentForm = (DepartmentForm) aForm;
//		departmentForm.clear();
		return aMapping.findForward(UIConstants.CANCEL);
	}

	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	*/
	private ActionForward commonNext(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{

		DepartmentForm departmentForm = (DepartmentForm) aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		// Sending PD Request Event
		if (departmentForm.getAction().equals(UIConstants.DEPT_COPY))
		{
			ValidateDepartmentCreateRequirementsEvent event = (ValidateDepartmentCreateRequirementsEvent) EventFactory.getInstance(AgencyControllerServiceNames.VALIDATEDEPARTMENTCREATEREQUIREMENTS);
			event.setDepartmentId(departmentForm.getDepartmentId());
			event.setDepartmentName(departmentForm.getDepartmentName());
			event.setOrgCode(departmentForm.getOrgCode());
			dispatch.postEvent(event);
		}

		else
		{
			ValidateDepartmentUpdateRequirementsEvent event = (ValidateDepartmentUpdateRequirementsEvent) EventFactory.getInstance(AgencyControllerServiceNames.VALIDATEDEPARTMENTUPDATEREQUIREMENTS);
			event.setDepartmentId(departmentForm.getDepartmentId());
			event.setDepartmentName(departmentForm.getDepartmentName());
			event.setOrgCode(departmentForm.getOrgCode());
			dispatch.postEvent(event);
		}

		// Getting PD Response Event		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		
		Object obj = MessageUtil.filterComposite(compositeResponse, DuplicateRecordErrorResponseEvent.class);
		if (obj != null)
		{
			DuplicateRecordErrorResponseEvent duplicateEvent = (DuplicateRecordErrorResponseEvent) obj;
			if (duplicateEvent.getMessage() != null)
			{
				this.sendToErrorPage(aRequest, duplicateEvent.getMessage());
			}
			else
			{
				this.sendToErrorPage(aRequest, "error.common");
			}
			return aMapping.findForward(UIConstants.UPDATE_FAILURE);
		}
		//set the original value of critical data
		UIDepartmentHelper.setManageDepartmentValuesFromDropDownList(departmentForm);
		return null;
	}
	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	*/
	public ActionForward copyNext(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm deptForm = (DepartmentForm) aForm;
		if (deptForm.getContactList() != null && deptForm.getContactList().size() > 0)
		{
			Collection myContacts = deptForm.getContactList();
			Iterator myIter = myContacts.iterator();
			boolean atLeastOneNotDeleted = false;
			boolean missingPhoneNumberRequired=false;
			while (myIter.hasNext())
			{
				UpdateContactEvent event = (UpdateContactEvent) myIter.next();
				if(event.getAreaCode()==null || event.getAreaCode().equals("") ||
					event.getPrefix()==null || event.getPrefix().equals("") ||
					event.getLast4Digit() ==null || event.getLast4Digit().equals(""))
					missingPhoneNumberRequired=true;
				if (!(event.isDeletable()))
					atLeastOneNotDeleted = true;
			}
			while (myIter.hasNext())
			{
				UpdateContactEvent event = (UpdateContactEvent) myIter.next();
				if (!(event.isDeletable()))
					atLeastOneNotDeleted = true;
			}
			if (!atLeastOneNotDeleted)
			{
				sendToErrorPage(aRequest, "error.needOneContact.Department");
				return aMapping.findForward(UIConstants.UPDATE_FAILURE);
			}
			if (missingPhoneNumberRequired)
			{
				sendToErrorPage(aRequest, "error.allContactsPhoneNumberRequired");
				return aMapping.findForward(UIConstants.UPDATE_FAILURE);
			}
		}
		ActionForward myForward = commonNext(aMapping, aForm, aRequest, aResponse);
		if (myForward != null)
			return myForward;
		return aMapping.findForward(UIConstants.COPY_SUCCESS);

	}

	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	*/
	public ActionForward updateNext(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm deptForm = (DepartmentForm) aForm;
		if (deptForm.getContactList() != null && deptForm.getContactList().size() > 0)
		{
			Collection myContacts = deptForm.getContactList();
			Iterator myIter = myContacts.iterator();
			boolean atLeastOneNotDeleted = false;
			boolean missingPhoneNumberRequired=false;
			while (myIter.hasNext())
			{
				UpdateContactEvent event = (UpdateContactEvent) myIter.next();
				if(event.getAreaCode()==null || event.getAreaCode().equals("") ||
					event.getPrefix()==null || event.getPrefix().equals("") ||
					event.getLast4Digit() ==null || event.getLast4Digit().equals(""))
					missingPhoneNumberRequired=true;
				if (!(event.isDeletable()))
					atLeastOneNotDeleted = true;
			}
			if (!atLeastOneNotDeleted)
			{
				sendToErrorPage(aRequest, "error.needOneContact.Department");
				return aMapping.findForward(UIConstants.UPDATE_FAILURE);
			}
			if (missingPhoneNumberRequired)
			{
				sendToErrorPage(aRequest, "error.allContactsPhoneNumberRequired");
				return aMapping.findForward(UIConstants.UPDATE_FAILURE);
			}
		}
		ActionForward myForward = commonNext(aMapping, aForm, aRequest, aResponse);
		if (myForward != null)
			return myForward;
		return aMapping.findForward(UIConstants.UPDATE_SUCCESS);

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward createNext(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm deptForm = (DepartmentForm) aForm;
		if (deptForm.getContactList() == null || deptForm.getContactList().size() < 1)
		{
			sendToErrorPage(aRequest, "error.needOneContact.Department");
			return aMapping.findForward(UIConstants.CREATE_CONTACT_FAILURE);
		}
		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{

		DepartmentForm deptForm = (DepartmentForm) aForm;
		if (deptForm.getAction().equals(UIConstants.DEPT_UPDATE))
		{
			return updateNext(aMapping, aForm, aRequest, aResponse);
		}
		else
			if (deptForm.getAction().equals(UIConstants.DEPT_CREATE))
			{
				return createNext(aMapping, aForm, aRequest, aResponse);
			}
			else
				if (deptForm.getAction().equals(UIConstants.DEPT_COPY))
				{
					return copyNext(aMapping, aForm, aRequest, aResponse);
				}

		return aMapping.findForward(UIConstants.FAILURE);
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