//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\casefile\\action\\SubmitCommonAppCourtOrderAction.java

package ui.juvenilecase.casefile.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.casefile.form.CommonAppForm;


public class SubmitCommonAppCourtOrderAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 46D421FC0010
    */
   public SubmitCommonAppCourtOrderAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 46D41EAD0348
    */
   public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		CommonAppForm commonAppForm = (CommonAppForm)aForm;
   		commonAppForm.setAction("confirm");
   		commonAppForm.setCourtOrderExists(true);
   		return aMapping.findForward(UIConstants.SUCCESS);
   }
   
   public ActionForward returnToCourt(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
        HttpServletResponse aResponse)
	{
	    return aMapping.findForward(UIConstants.RETURN_SUCCESS);
	}
   public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
        HttpServletResponse aResponse)
	{
	    return aMapping.findForward(UIConstants.CANCEL);
	}
	
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
	    return aMapping.findForward(UIConstants.BACK);
	}

   protected void addButtonMapping(Map keyMap)
   {
   	  keyMap.put("button.finish", "finish");   	 
   	  keyMap.put("button.returnToCourtOrder", "returnToCourt");
   	  keyMap.put("button.cancel", "cancel");
   	  keyMap.put("button.back", "back");
   }
}
