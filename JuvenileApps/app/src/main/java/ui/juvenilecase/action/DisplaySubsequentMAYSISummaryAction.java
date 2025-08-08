//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\DisplaySubsequentMAYSISummaryAction.java

package ui.juvenilecase.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

/**
 * @author dgibler
 *
 */
public class DisplaySubsequentMAYSISummaryAction extends LookupDispatchAction {
   
   /**
    * @roseuid 42791FCE038A
    */
   public DisplaySubsequentMAYSISummaryAction() 
   {
    
   }
   
   protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", "next");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.cancel", "cancel");
		return buttonMap;
	}
		
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42791D7B0208
    */
   public ActionForward next(ActionMapping aMapping, 
   								ActionForm aForm, HttpServletRequest aRequest, 
   								HttpServletResponse aResponse) {
    	return aMapping.findForward(UIConstants.SUCCESS);
   }
   
   public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
							   HttpServletRequest aRequest, 
							   HttpServletResponse aResponse)
   {
			 return aMapping.findForward(UIConstants.BACK);
   }
  
   public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, 
							   HttpServletRequest aRequest, 
							   HttpServletResponse aResponse)
   {
			   return aMapping.findForward(UIConstants.CANCEL);
   }
   
   
}
