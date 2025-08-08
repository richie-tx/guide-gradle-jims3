package ui.juvenilecase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.JuvenileAbuseForm;

public class DisplayJuvenileAbuseDetailsAction extends Action
{

	/**
	 * @roseuid 42B1A2A600DA
	 */
	public DisplayJuvenileAbuseDetailsAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42B03B36021D
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileAbuseForm form = (JuvenileAbuseForm) aForm;
		form.setMultiplePrep(null);

		ActionForward forward = null;

		String abuseId = form.getAbuseId();

		String action = form.getAction();
		
		form.initAbuse(abuseId);

		if (UIConstants.VIEW.equals(action))
		{
			forward = aMapping.findForward(UIConstants.SUCCESS);
		}
		else
		{
			forward = aMapping.findForward(UIConstants.SUCCESS);
		}

		return forward;
	}
}
