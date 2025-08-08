//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\department\\action\\HandleContactSelectionAction.java

package ui.contact.department.action;

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
import ui.common.PhoneNumber;
import ui.contact.department.form.DepartmentForm;

public class HandleContactSelectionAction extends LookupDispatchAction
{

	/**
	 * @roseuid 430630EC0394
	 */
	public HandleContactSelectionAction()
	{

	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.view", "viewContact");
		buttonMap.put("button.modifyContact", "modifyContact");
		buttonMap.put("button.deleteContact", "deleteContact");
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

	public ActionForward viewContact(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception
	{

		ActionForward forward = null;
		DepartmentForm deptForm = (DepartmentForm) form;

		//Collection conList = deptForm.getContactList();

		prepareForm(deptForm);
		deptForm.setAction(UIConstants.VIEW);
		forward = mapping.findForward(UIConstants.VIEW_CONTACT_SUCCESS);
		// Finish with
		return forward;

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */

	public ActionForward modifyContact(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception
	{
		ActionForward forward = null;
		DepartmentForm deptForm = (DepartmentForm) form;
		prepareForm(deptForm);
		deptForm.setAction(UIConstants.UPDATE);

		forward = mapping.findForward(UIConstants.UPDATE_CONTACT_SUCCESS);

		// Finish with
		return forward;

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */

	public ActionForward deleteContact(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception
	{
		ActionForward forward = null;
		DepartmentForm deptForm = (DepartmentForm) form;
		prepareForm(deptForm);
		deptForm.setAction(UIConstants.DELETE);
		forward = mapping.findForward(UIConstants.DELETE_CONTACT_SUCCESS);

		// Finish with
		return forward;

	}

	private void prepareForm(DepartmentForm deptForm)
	{
		Collection conList = deptForm.getContactList();
		String contactId = deptForm.getContactId();
		Iterator conListIter = conList.iterator();
		UpdateContactEvent conRespEvent = null;
		while (conListIter.hasNext())
		{
			conRespEvent = (UpdateContactEvent) conListIter.next();
			if (contactId.equals(conRespEvent.getContactId()))
			{
				deptForm.setContactId(conRespEvent.getContactId());
				deptForm.setContactEmail(conRespEvent.getEmail());
				deptForm.setLogonId(conRespEvent.getLogonId());
				Name contactName = new Name();
				contactName.setFirstName(conRespEvent.getFirstName());
				contactName.setLastName(conRespEvent.getLastName());
				contactName.setMiddleName(conRespEvent.getMiddleName());
				deptForm.setContactName(contactName);
				PhoneNumber contactPhone = new PhoneNumber(conRespEvent.getPhone());
				contactPhone.setExt(conRespEvent.getPhoneExt());
				deptForm.setContactPhoneNumber(contactPhone);
				deptForm.setContactJobTitle(conRespEvent.getTitle());
				deptForm.setLiaisonTrainingInd(conRespEvent.getLiaisonTrainingInd());
				deptForm.setPrimaryContact(conRespEvent.getPrimaryContact());
				break;
			}
		}
	}
} 