//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\department\\action\\DisplayContactUserSearchAction.java

package ui.contact.department.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
//import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.contact.department.UIDepartmentHelper;
import ui.contact.department.form.DepartmentForm;

public class DisplayContactUserSearchAction extends LookupDispatchAction
{

	/**
	 * @roseuid 430630E40394
	 */
	public DisplayContactUserSearchAction()
	{

	}
	/** 
	 * @see LookupDispatchAction#getKeyMethodMap()
	 */
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
	* @roseuid 430628E40198
	*/
	public ActionForward validateDepartmentAndSetValues(
		ActionMapping aMapping,
		DepartmentForm dForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		CompositeResponse response = response =	UIDepartmentHelper.validateDepartmentCreateRequirements(dForm.getDepartmentId(),dForm.getDepartmentName(),dForm.getOrgCode());

		Object obj = MessageUtil.filterComposite(response, DuplicateRecordErrorResponseEvent.class);
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
			return aMapping.findForward(UIConstants.FAILURE);
		}

		// set the original value of critical data
		UIDepartmentHelper.setManageDepartmentValuesFromDropDownList(dForm);
		return null;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 430628E40198
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm dForm = (DepartmentForm) aForm;
		ActionForward fwd = null;
		if (dForm.getAction().equalsIgnoreCase("deptCreate"))
		{
			fwd = validateDepartmentAndSetValues(aMapping, dForm, aRequest, aResponse);
		}
		// add logic for create contact coming in from other places...

		// if any error occurred during pre-processing
		// forward it to wherever it is supposed to go
		if (fwd != null)
		{
			return fwd;
		}
		// fwd it to the create contact page.
		return aMapping.findForward(UIConstants.CREATE_CONTACT);
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
