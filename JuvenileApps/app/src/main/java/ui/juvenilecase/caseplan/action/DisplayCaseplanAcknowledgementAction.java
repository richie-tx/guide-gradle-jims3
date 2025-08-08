package ui.juvenilecase.caseplan.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;

import ui.juvenilecase.caseplan.form.CaseplanForm;

/**
 * 
 * @author ugopinath
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCaseplanAcknowledgementAction extends JIMSBaseAction
{
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward addCaseplanAcknowledgement(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		CaseplanForm form = (CaseplanForm) aForm;
		//get previous caseplan acknowledgments
		return aMapping.findForward("success");
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		CaseplanForm form = (CaseplanForm) aForm;
		form.setAction("review");
		form.clearComments();
		return aMapping.findForward("success");
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
	
	
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	  protected void addButtonMapping(Map buttonMap)
	   {
	
		  buttonMap.put("button.next", "next");
		  buttonMap.put("button.addCaseplanAcknowledgement", "addCaseplanAcknowledgement");		 
		  buttonMap.put("button.cancel", "cancel");
		  buttonMap.put("button.back", "back");
		
	   }
	
	
}