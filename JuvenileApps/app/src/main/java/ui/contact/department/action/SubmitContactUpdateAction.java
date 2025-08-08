//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\department\\action\\SubmitContactUpdateAction.java

package ui.contact.department.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.agency.DeleteContactEvent;
import messaging.agency.UpdateContactEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.contact.department.form.DepartmentForm;

public class SubmitContactUpdateAction extends Action
{

	/**
	* @roseuid 430630EE03A3
	*/
	public SubmitContactUpdateAction()
	{

	}

	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	* @roseuid 430628E1015E
	*/
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = null;
		DepartmentForm deptForm = (DepartmentForm) aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		if (deptForm.getAction().equals(UIConstants.DELETE))
		{
			if (deptForm.getContactList() != null && deptForm.getContactList().size() <= 1)
			{
				sendToErrorPage(aRequest, "error.needOneContact.Department");
				return aMapping.findForward(UIConstants.DELETE_FAILURE);
			}
			DeleteContactEvent deleteRequestEvent =	(DeleteContactEvent) EventFactory.getInstance(AgencyControllerServiceNames.DELETECONTACT);
			deleteRequestEvent.setContactId(deptForm.getContactId());
			deleteRequestEvent.setDepartmentId(deptForm.getDepartmentId());
			dispatch.postEvent(deleteRequestEvent);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);
			if (deptForm.getContactList() != null && deptForm.getContactList().size() > 0)
			{
				Iterator iter = deptForm.getContactList().iterator();
				Collection newList = new ArrayList();
				while (iter.hasNext())
				{
					UpdateContactEvent event = (UpdateContactEvent) iter.next();
					if (deptForm.getContactId().equalsIgnoreCase(event.getContactId()))
					{
						// do nothing don't remove
					}
					else
					{
						newList.add(event);
					}
				}
				deptForm.setContactList(newList);
			}
			deptForm.setAction(UIConstants.CONFIRM_DELETE);
			forward = aMapping.findForward(UIConstants.CONFIRM_DELETE_SUCCESS);
		}
		else
			if (deptForm.getAction().equals(UIConstants.UPDATE))
			{
				UpdateContactEvent eventNew = new UpdateContactEvent();
				setUpdateContactEventFromForm(deptForm, eventNew);
				dispatch.postEvent(eventNew);
				// Getting PD Response Event		
				CompositeResponse response = (CompositeResponse) dispatch.getReply();
				Map map = MessageUtil.groupByTopic(response);
				MessageUtil.processReturnException(map);

				if (deptForm.getContactList() != null && deptForm.getContactList().size() > 0)
				{
					Iterator iter = deptForm.getContactList().iterator();
					while (iter.hasNext())
					{
						UpdateContactEvent event = (UpdateContactEvent) iter.next();
						if (deptForm.getContactId().equalsIgnoreCase(event.getContactId()))
						{
							setUpdateContactEventFromForm(deptForm, event);
							break;
						}
					}
				}
				deptForm.setAction(UIConstants.CONFIRM_UPDATE);
				forward = aMapping.findForward(UIConstants.CONFIRM_CREATE_SUCCESS);
			}
			else
				if (deptForm.getAction().equals(UIConstants.CONTACT_CREATE))
				{
					if (deptForm.getContactList() != null && deptForm.getContactList().size() > 0)
					{
						Iterator iter = deptForm.getContactList().iterator();
						while (iter.hasNext())
						{
							UpdateContactEvent event = (UpdateContactEvent) iter.next();
							if (event.getContactId() == null || event.getContactId().equalsIgnoreCase(""))
							{
								dispatch.postEvent(event);
								// Getting PD Response Event		
								CompositeResponse responses = (CompositeResponse) dispatch.getReply();
								Map maps = MessageUtil.groupByTopic(responses);
								MessageUtil.processReturnException(maps);
							}
						}
					}
					deptForm.setAction(UIConstants.CONFIRM_CREATE);
					forward = aMapping.findForward(UIConstants.CONFIRM_CREATE_SUCCESS);
				}
		return forward;
	}

	private void setUpdateContactEventFromForm(DepartmentForm deptForm, UpdateContactEvent event)
	{
		event.setContactId(deptForm.getContactId());
		event.setDepartmentId(deptForm.getDepartmentId());
		event.setFirstName(deptForm.getContactName().getFirstName());
		event.setMiddleName(deptForm.getContactName().getMiddleName());
		event.setLastName(deptForm.getContactName().getLastName());
		event.setEmail(deptForm.getContactEmail());
		event.setUserID(deptForm.getUserLogonId());
		event.setLogonId(deptForm.getLogonId());
		event.setPhone(deptForm.getContactPhoneNumber().getPhoneNumber());
		event.setPhoneExt(deptForm.getContactPhoneNumber().getExt());
		event.setTitle(deptForm.getContactJobTitle());
		event.setLiaisonTrainingInd(deptForm.getLiaisonTrainingInd());
		event.setLiaisonTraining(deptForm.getLiaisonTraining());
		event.setPrimaryContact(deptForm.getPrimaryContact());
		return;
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