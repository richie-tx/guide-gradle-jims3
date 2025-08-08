package ui.juvenilecase.interviewinfo.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import naming.ActivityConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.Name;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.interviewinfo.form.SocialHistoryForm;
import ui.security.SecurityUIHelper;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitCourtDispositionAlternativesAction extends LookupDispatchAction
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.finish", "finish");
		keyMap.put("button.next", "next");
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
	
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SocialHistoryForm form = (SocialHistoryForm) aForm;
		
		if(!(form.socialHistoryData.getCourtDispositionAlternative1() == null || 
				form.socialHistoryData.getCourtDispositionAlternative1().equals(""))) {
			// Adding record in activity table
			UIJuvenileHelper.createActivity(form.getCasefileId(), ActivityConstants.DISPOSITIONAL_ALTERNATIVE_FIRST, 
					form.getSocialHistoryData().getCourtDispositionAlternative1());
		}
		if(!(form.socialHistoryData.getCourtDispositionAlternative2() == null || 
				form.socialHistoryData.getCourtDispositionAlternative2().equals(""))) {
			// Adding record in activity table
			UIJuvenileHelper.createActivity(form.getCasefileId(), ActivityConstants.DISPOSITIONAL_ALTERNATIVE_SECOND, 
					form.getSocialHistoryData().getCourtDispositionAlternative2());
		}			
		if(!(form.socialHistoryData.getCourtDispositionAlternative3() == null || 
				form.socialHistoryData.getCourtDispositionAlternative3().equals(""))) {
			// Adding record in activity table
			UIJuvenileHelper.createActivity(form.getCasefileId(), ActivityConstants.DISPOSITIONAL_ALTERNATIVE_THIRD, 
					form.getSocialHistoryData().getCourtDispositionAlternative3());
		}			
		
		aRequest.setAttribute("state", "confirm");
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SocialHistoryForm form = (SocialHistoryForm) aForm;
		
/*		
		String comments = form.getSocialHistoryData().getCourtDispositionAlternative1();
		if (!comments.equals("") && comments != null) {
			IUserInfo user = SecurityUIHelper.getUser();
			Name userName = new Name(user.getFirstName(),"",user.getLastName());
			form.getSocialHistoryData().setCourtDispositionAlternative1(comments + " [" + DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) + " - " + userName.getFormattedName() + "]");
		}
		comments = form.getSocialHistoryData().getCourtDispositionAlternative2();
		if (!comments.equals("") && comments != null) {
			IUserInfo user = SecurityUIHelper.getUser();
			Name userName = new Name(user.getFirstName(),"",user.getLastName());
			form.getSocialHistoryData().setCourtDispositionAlternative2(comments + " [" + DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) + " - " + userName.getFormattedName() + "]");
		}		
		comments = form.getSocialHistoryData().getCourtDispositionAlternative3();
		if (!comments.equals("") && comments != null) {
			IUserInfo user = SecurityUIHelper.getUser();
			Name userName = new Name(user.getFirstName(),"",user.getLastName());
			form.getSocialHistoryData().setCourtDispositionAlternative3(comments + " [" + DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) + " - " + userName.getFormattedName() + "]");
		}
		*/
		
		aRequest.setAttribute("state", "summary");
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
}