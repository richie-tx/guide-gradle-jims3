package ui.juvenilecase.interviewinfo.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.interviewinfo.form.SocialHistoryForm;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCourtDispositionAlternativesAction extends LookupDispatchAction
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.link", "link");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}
	
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)	
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}
		
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SocialHistoryForm form = (SocialHistoryForm) aForm;
		form.getSocialHistoryData().setUseCourtDispositionAlternatives(true);
		form.getSocialHistoryData().setUseDetentionReason(false);
		aRequest.setAttribute("state", "display");
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	
	
}