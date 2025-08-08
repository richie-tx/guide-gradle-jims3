//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\suggestedorder\\action\\DisplaySuggestedOrderIndexAction.java

package ui.supervision.suggestedorder.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.suggestedorder.form.SuggestedOrderForm;

/**
 * @author dgibler
 *
 */
public class DisplaySuggestedOrderIndexAction extends Action
{

	/**
		* @roseuid 42F7C48C03D8
		*/
	public DisplaySuggestedOrderIndexAction()
	{

	}

	/**
		* @param aMapping
		* @param aForm
		* @param aRequest
		* @param aResponse
		* @return ActionForward
		* @roseuid 42F79A090158
		*/
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;
		sugOrderForm.clear();

		return aMapping.findForward(UIConstants.SUCCESS);

	}
}
