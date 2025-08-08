//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\suggestedorder\\action\\DisplaySuggestedOrderSearchAction.java

package ui.supervision.suggestedorder.action;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.suggestedorder.form.SuggestedOrderForm;
import ui.supervision.suggestedorder.helper.UISuggestedOrderHelper;

/**
 * @author dgibler
 *
 */
public class DisplaySuggestedOrderSearchAction extends Action
{

	/**
	 * @roseuid 433AF4A202C7
	 */
	public DisplaySuggestedOrderSearchAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 433AF051029D
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;
		sugOrderForm.clear();

		sugOrderForm.setAllCourtsSelected(true); //default choice of radio button to all courts
		if (sugOrderForm.getCourts() == null || sugOrderForm.getCourts().size() == 0)
		{
			Collection courtBeans = UISuggestedOrderHelper.getCourtBeans();
			sugOrderForm.setCourts(courtBeans);
		}

		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
}
