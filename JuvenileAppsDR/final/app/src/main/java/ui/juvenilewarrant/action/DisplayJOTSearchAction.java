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
 * @author ldeen
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayJOTSearchAction extends Action
{

	/**
	@roseuid 40D89C070110
	 */
	public DisplayJOTSearchAction()
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
		// uncomment this line when/if error logic is added    	
		//	ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		//	default value to failure in case no warrantType present
		String success = UIConstants.FAILURE;

		// getting warrantType out of request and setting it in the form
		String warrantTypeUI = aRequest.getParameter(UIConstants.WARRANT_TYPE_UI);
		if( warrantTypeUI == null ){
			warrantTypeUI = (String) aRequest.getAttribute(UIConstants.WARRANT_TYPE_UI);
		}
		JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;
		
		form.clear();
		
		form.setWarrantTypeUI(warrantTypeUI);
		form.setPetitionType(PDJuvenileWarrantConstants.PETITION_JOT);

		if (warrantTypeUI.equalsIgnoreCase(PDJuvenileWarrantConstants.WARRANT_TYPE_DTA))
		{
			success = "dtaSuccess";
		}
		if (warrantTypeUI.equalsIgnoreCase(PDJuvenileWarrantConstants.WARRANT_TYPE_ARR))
		{
			success = "arrSuccess";
		}
		if (warrantTypeUI.equalsIgnoreCase(PDJuvenileWarrantConstants.WARRANT_TYPE_PCW))
		{
			success = "pcSuccess";
		}

		forward = aMapping.findForward(success);

		return forward;
	}
}
