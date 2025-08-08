//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\department\\action\\DisplayAgencySearchResultsAction.java

package ui.contact.department.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.UpdateContactEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.Name;
import ui.contact.department.UIDepartmentHelper;
import ui.contact.department.form.DepartmentForm;

public class HandleContactAddRemoveAction extends LookupDispatchAction
{

	/**
	 * @roseuid 430630E101EE
	 */
	public HandleContactAddRemoveAction()
	{

	}

	/** 
	* @see LookupDispatchAction#getKeyMethodMap()
	*/
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.addContactToList", UIConstants.ADD_CONTACT);
		buttonMap.put("button.removeContactFromList", UIConstants.REMOVE_CONTACT);
		buttonMap.put("button.next", UIConstants.NEXT);
		buttonMap.put("button.cancel", UIConstants.CANCEL);
		return buttonMap;
	}
	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	* @roseuid 430628E101F8
	*/
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm deptForm = (DepartmentForm) aForm;
		if (deptForm.getAction().equals(UIConstants.CREATE_CONTACT))
		{
			return aMapping.findForward(UIConstants.UPDATE_CONTACT_SUCCESS);
		}
		return aMapping.findForward(UIConstants.UPDATE_CONTACT_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 430628E101F8
	 */
	public ActionForward addContact(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm deptform = (DepartmentForm) aForm;
		UpdateContactEvent evt = new UpdateContactEvent();
		Name cName = deptform.getContactName();
		evt.setDepartmentId(deptform.getDepartmentId());
		evt.setFirstName(cName.getFirstName());
		evt.setMiddleName(cName.getMiddleName());
		evt.setLastName(cName.getLastName());
		evt.setEmail(deptform.getContactEmail());
		evt.setTitle(deptform.getContactJobTitle());
		evt.setPhone(deptform.getContactPhoneNumber().getPhoneNumber());
		evt.setPrefix(deptform.getContactPhoneNumber().getPrefix());
		evt.setAreaCode(deptform.getContactPhoneNumber().getAreaCode());
		evt.setLast4Digit(deptform.getContactPhoneNumber().getLast4Digit());
		evt.setPhoneExt(deptform.getContactPhoneNumber().getExt());
		evt.setLogonId(deptform.getLogonId());
		evt.setLiaisonTrainingInd(deptform.getLiaisonTrainingInd());
		evt.setPrimaryContact(deptform.getPrimaryContact().toUpperCase());
		// add to the contact list
		deptform.addContactToContactList(evt);
		Collection contacts = deptform.getContactList();
		deptform.setContactList(UIDepartmentHelper.sortContactNames(contacts));
		// clear the contact fields
		deptform.clearContactFields();
		return aMapping.findForward(UIConstants.ADD_TO_LIST_SUCCESS);
	}

	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	* @roseuid 430628E101F8
	*/
	public ActionForward removeContact(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm deptform = (DepartmentForm) aForm;
	
		Iterator ite = deptform.getContactList().iterator();
		Collection newList = new ArrayList();
		UpdateContactEvent evt;
		while (ite.hasNext())
		{
			evt = (UpdateContactEvent) ite.next();
			if (evt.isDeletable())
			{
				if (evt.getContactId() == null || (evt.getContactId().equalsIgnoreCase("")))
				{
					// do nothing don't add
				}
				else
				{
					newList.add(evt);
				}
			}
			else
			{
				newList.add(evt);
			}
		}
		deptform.setContactList(UIDepartmentHelper.sortContactNames(newList));		
		deptform.setContactList(newList);
		return aMapping.findForward(UIConstants.REMOVE_FROM_LIST_SUCCESS);
	}

	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	* @roseuid 430628E101F8
	*/
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}

}
