package ui.juvenilewarrant.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilewarrant.form.JuvenileWarrantForm;

/**
 * @author ryoung
 *
 */
public class DisplayWarrantViewSearchAction extends Action
{

	/**
	 * @roseuid 41F7C3730314
	 */
	public DisplayWarrantViewSearchAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 41F7BE6C027A
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{			
		JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
		
		jwForm.clear();					

		return aMapping.findForward(UIConstants.SUCCESS);
	}

}
