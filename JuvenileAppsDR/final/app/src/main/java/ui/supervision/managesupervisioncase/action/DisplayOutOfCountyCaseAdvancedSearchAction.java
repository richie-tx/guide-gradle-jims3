//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\managesupervisioncase\\action\\DisplayOutOfCountyCaseAdvancedSearchAction.java

package ui.supervision.managesupervisioncase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.managesupervisioncase.form.OutOfCountyCaseSearchForm;

public class DisplayOutOfCountyCaseAdvancedSearchAction extends Action
{

	/**
	 * @roseuid 4443EFC2029F
	 */
	public DisplayOutOfCountyCaseAdvancedSearchAction()
	{

	}

	/**
	 * @see org.apache.struts.actions.Action#execute()
	 * @return ActionForward
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		OutOfCountyCaseSearchForm oocSearchForm = (OutOfCountyCaseSearchForm) aForm;
		oocSearchForm.clear(true, true);
		return aMapping.findForward(UIConstants.ADVANCED_SEARCH_SUCCESS);
	}

}
