package ui.juvenilewarrant.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilewarrant.form.JuvenileWarrantForm;

/* @author ryoung
*
*/
public class DisplayWarrantVOPActivateSummaryAction extends LookupDispatchAction
{

	/**
	 * @roseuid 4211007C01B2
	 */
	public DisplayWarrantVOPActivateSummaryAction()
	{

	}
	/**
	 * @return buttonMap
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", UIConstants.NEXT);
		buttonMap.put("button.back", UIConstants.BACK);
		buttonMap.put("button.cancel", UIConstants.CANCEL);
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4210F85F0201
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;
		
		form.setWarrantTypeUI(UIConstants.ACTVOP_SUMMARY);
		form.setAction(UIConstants.SUMMARY);
		
		form.setBackToWarrantUrl(aMapping.findForward(UIConstants.NEXT).getPath());
		
		return aMapping.findForward(UIConstants.NEXT);
	}
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		// TODO Handle cancel
		return aMapping.findForward(UIConstants.CANCEL);
	}
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		// TODO Handle cancel
		return aMapping.findForward(UIConstants.BACK);
	}

}
