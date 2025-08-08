package ui.juvenilecase.caseplan.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.Name;
import ui.juvenilecase.caseplan.form.CaseplanForm;
import ui.security.SecurityUIHelper;

/**
 * 
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCaseplanCommentsSummaryAction extends LookupDispatchAction
{
	private final String STATUS_STR = "status" ;
	private final String SUMMARY_STR = "summary" ;
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		//added for User story 11146 values based on show and hide
		CaseplanForm form = (CaseplanForm) aForm;
		if (form.getJpoMaintainContactStr() != null && form.getJpoMaintainContactStr().equalsIgnoreCase("Yes") ){
        	form.setJpoMaintainExplain(null);
		}
		if (form.getSupLevelApproStr() != null && form.getSupLevelApproStr().equalsIgnoreCase("Yes") ){
        	form.setRecomSupervisionLevelId(null);
		}
		aRequest.setAttribute(STATUS_STR, SUMMARY_STR );
		
		return( aMapping.findForward(UIConstants.SUCCESS) );
	}
	
	/*
	 * 
	 */
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		aRequest.setAttribute(STATUS_STR, SUMMARY_STR );
		
		return( aMapping.findForward(UIConstants.SUCCESS) );
	}

	/*
	 * 
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)	
	{
		return( aMapping.findForward(UIConstants.CANCEL) );
	}

	/*
	 * 
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	
	/*
	 * 
	 */
	public ActionForward notification(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
  {
		 return aMapping.findForward(UIConstants.NOTIFICATION);
  }

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() 
	{
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.notification", "notification");
		keyMap.put("button.submit", "submit");
		
		return keyMap;
	}
}
