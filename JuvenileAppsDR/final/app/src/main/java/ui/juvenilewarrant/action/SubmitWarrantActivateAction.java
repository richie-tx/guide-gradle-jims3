//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilewarrant\\action\\SubmitWarrantActivateAction.java

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
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SubmitWarrantActivateAction extends Action 
{

	/**
	 * @roseuid 41E7D9D802B6
	 */
	public SubmitWarrantActivateAction()
	{

	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 41E7D8E7016F
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
		//String warrantTypeBaseInd = jwForm.getWarrantTypeBaseInd();

		/*default value to failure in case no warrantType present */
		String success = UIConstants.FAILURE;

		/*getting warrantType out of request and setting it in the form */
		if (jwForm.getWarrantTypeUI() == null)
		{
			String warrantTypeUI = aRequest.getParameter(UIConstants.WARRANT_TYPE_UI);
			jwForm.setWarrantTypeUI(warrantTypeUI);
		}

		/*depending on warrantTypeUI set correct forward for struts-config.xml */
		//Activate Arrest (ARR) Warrant
		if (jwForm.getWarrantTypeUI().equals(UIConstants.ACTARR))
		{
			success = UIConstants.ACTARR_SUCCESS;
		}
		//Activate DTA Warrant
		if (jwForm.getWarrantTypeUI().equals(UIConstants.ACTDTA))
		{
			success = UIConstants.ACTDTA_SUCCESS;
		}
		forward = aMapping.findForward(success);
		return forward;
	}
}