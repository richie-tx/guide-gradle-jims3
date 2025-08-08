//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\managesupervisioncase\\action\\DisplayOutOfCountyCaseSearchAction.java

package ui.supervision.managesupervisioncase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.managesupervisioncase.form.OutOfCountyCaseSearchForm;

public class DisplayOutOfCountyCaseSearchAction extends Action
{

	/**
	 * @roseuid 4443EFD1012E
	 */
	public DisplayOutOfCountyCaseSearchAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 443D14B30071
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OutOfCountyCaseSearchForm oocSearchForm = (OutOfCountyCaseSearchForm) aForm;
		oocSearchForm.clear(true, true);
		
		// establish a helper for the form / session
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}
}
