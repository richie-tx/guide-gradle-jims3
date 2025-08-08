//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\suggestedorder\\action\\DisplaySuggestedOrderCreateUpdateAction.java
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
public class DisplaySuggestedOrderCreateUpdateAction extends Action
{

	/**
	 * @roseuid 433AF4840363
	 */
	public DisplaySuggestedOrderCreateUpdateAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 433AF0500181
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{

		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;

		String action = sugOrderForm.getAction();
		if (action == null || action.equals(""))
		{
			sugOrderForm.clear();
			return aMapping.findForward(UIConstants.FAILURE);
		}

		if (action.equals(UIConstants.CREATE))
		{
			sugOrderForm.clear();
			forward = aMapping.findForward(UIConstants.SUCCESS);
		}
		else
			if (action.equals(UIConstants.UPDATE))
			{
				forward = aMapping.findForward(UIConstants.SUCCESS);
			}
			else
				if (action.equals(UIConstants.COPY))
				{
					forward = aMapping.findForward(UIConstants.SUCCESS);
				}
				else
				{
					sugOrderForm.clear();
					forward = aMapping.findForward(UIConstants.FAILURE);
				}
		return forward;

	}
}
