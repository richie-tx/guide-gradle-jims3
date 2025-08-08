package ui.juvenilecase.action;

import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.form.JuvenileMemberSearchForm;

/**
 * @author jjose
 *
 */
public class DisplayFamilyMemberSearchAction extends LookupDispatchAction
{

	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		JuvenileMemberSearchForm mySearchForm=(JuvenileMemberSearchForm)aForm;  
		if ("Bypass".equalsIgnoreCase(mySearchForm.getSelectedValue() ) )
		{
			mySearchForm.setSelectedValue("");
			return aMapping.findForward("bypass");
		}	  
		return displaySearch(aMapping,aForm,aRequest,aResponse);
	}

		  
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.addNewMember", "displaySearch");
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
		
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		return forward;
	}
			
	public ActionForward displaySearch(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileMemberSearchForm mySearchForm=(JuvenileMemberSearchForm)aForm;
		mySearchForm.clear();
		mySearchForm.clearSearchResults();
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}
	/**
	* @param aRequest
	*/
   private void sendToErrorPage(HttpServletRequest aRequest, String msg)
   {
	   ActionErrors errors = new ActionErrors();
	   errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
	   saveErrors(aRequest, errors);
   }

}// END-CLASS
