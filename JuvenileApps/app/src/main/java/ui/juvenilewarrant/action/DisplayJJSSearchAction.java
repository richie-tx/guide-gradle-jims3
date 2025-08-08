//Source file: C:\\views\\dev\\app\\src\\ui\\warrants\\action\\DisplayJOTSearchFormAction.java

package ui.juvenilewarrant.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.PDJuvenileWarrantConstants;
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
public class DisplayJJSSearchAction extends Action
{

	/**
	@roseuid 40D89C070110
	 */
	public DisplayJJSSearchAction()
	{

	}

	/**
	@param aMapping
	@param aForm
	@param aRequest
	@param aResponse
	@return ActionForward
	@roseuid 40D891250148
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;
		form.clear();
		form.setSelectedCharge("");
		
		//	default value to failure in case no warrantType present
		String success = UIConstants.FAILURE;

		//getting warrantType out of request and setting it in the form
		String warrantTypeUI = aRequest.getParameter(UIConstants.WARRANT_TYPE_UI);		
		
		form.setWarrantTypeUI(warrantTypeUI);		
		form.setPetitionType(PDJuvenileWarrantConstants.PETITION_JJS);

		if (warrantTypeUI.equals("oic"))
		{
			success = "oicSuccess";
			form.setWarrantTypeId(PDJuvenileWarrantConstants.WARRANT_TYPE_OIC);
		}
		if (warrantTypeUI.equals("vop"))
		{
			form.setWarrantTypeId(PDJuvenileWarrantConstants.WARRANT_TYPE_VOP);
			success = "vopSuccess";
		}

		return aMapping.findForward(success);
	}
}
