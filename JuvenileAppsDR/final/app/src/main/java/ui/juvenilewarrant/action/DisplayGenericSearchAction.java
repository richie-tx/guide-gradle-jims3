//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilewarrant\\action\\DisplayGenericSearchAction.java
//
// HRodriguez - 02/03/2005 - Add new warrantTypeUI Recall & Inactivate   

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
 * @author HRodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayGenericSearchAction extends Action
{

	/**
	 * @roseuid 416D2AFD0035
	 */
	public DisplayGenericSearchAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 416D2A0C0056
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{    			
		// default value to failure in case no warrantType present
		String success = UIConstants.FAILURE;

		JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;
		form.clear();

		// getting warrantType out of request and setting it in the form
		String warrantTypeUI = aRequest.getParameter(UIConstants.WARRANT_TYPE_UI);
		form.setWarrantTypeUI(warrantTypeUI);

		if (warrantTypeUI.equals(UIConstants.REQACKDTA))
		{
			success = UIConstants.REQACKDTA_SUCCESS;
			form.setWarrantTypeId(PDJuvenileWarrantConstants.WARRANT_TYPE_DTA);
		}
		else if (warrantTypeUI.equals(UIConstants.ACTDTA))
		{
			success = UIConstants.ACTDTA_SUCCESS;
			form.setWarrantTypeId(PDJuvenileWarrantConstants.WARRANT_TYPE_DTA);
		}
		else if (warrantTypeUI.equals(UIConstants.ACTARR))
		{
			success = UIConstants.ACTARR_SUCCESS;
			form.setWarrantTypeId(PDJuvenileWarrantConstants.WARRANT_TYPE_ARR);
		}
		else if (warrantTypeUI.equals(UIConstants.ACTVOP))
		{
			success = UIConstants.ACTVOP_SUCCESS;
			form.setWarrantTypeId(PDJuvenileWarrantConstants.WARRANT_TYPE_VOP);
		}
		else if (warrantTypeUI.equals(UIConstants.REQSIGOIC))
		{
			success = UIConstants.REQSIGOIC_SUCCESS;
			form.setWarrantTypeId(PDJuvenileWarrantConstants.WARRANT_TYPE_OIC);
		}
		else if (warrantTypeUI.equals(UIConstants.UPDATE_OIC))
		{
			success = UIConstants.UPDATE_OIC_SUCCESS;
			form.setWarrantTypeId(PDJuvenileWarrantConstants.WARRANT_TYPE_OIC);
		}
		else if (warrantTypeUI.equals(UIConstants.RECALL))
		{
			success = UIConstants.RECALL_SUCCESS;
		}
		else if (warrantTypeUI.equals(UIConstants.INACTIVATE))
		{
			success = UIConstants.INACTIVATE_SUCCESS;
		}
		else if (warrantTypeUI.equals(UIConstants.RELEASE_DECISION))
		{
			success = UIConstants.RELEASE_DECISION_SUCCESS;
		}
		else if (warrantTypeUI.equals(UIConstants.RELEASE_TOJP))
		{
			success = UIConstants.RELEASE_TOJP_SUCCESS;
		}
		else if (warrantTypeUI.equals(UIConstants.RELEASE_TOPERSON))
		{
			success = UIConstants.RELEASE_TOPERSON_SUCCESS;
		}
		else if (warrantTypeUI.equals(UIConstants.PROCESS_RETURN))
		{
			success = UIConstants.PROCESS_RETURN_SUCCESS;
		}
		else if (warrantTypeUI.equals(UIConstants.WARRANT_SERVICE))
		{
			success = UIConstants.WARRANT_SERVICE_SUCCESS;
		}
		else if (warrantTypeUI.equals(UIConstants.RETURN_SIGNATURE_STATUS))
		{
			success = UIConstants.RETURN_SIGNATURE_STATUS_SUCCESS;
		}
		else if (warrantTypeUI.equals(UIConstants.UPDATE_VOP))
		{
			success = UIConstants.UPDATE_VOP_SUCCESS;
			form.setWarrantTypeId(PDJuvenileWarrantConstants.WARRANT_TYPE_VOP);
		}		

		return aMapping.findForward(success);
	}
}
