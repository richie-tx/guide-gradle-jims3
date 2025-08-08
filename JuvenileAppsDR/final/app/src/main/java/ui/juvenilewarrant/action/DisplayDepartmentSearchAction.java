//Source file: C:\\views\\MSA\\app\\src\\ui\\contact\\user\\action\\DisplayDepartmentSearchAction.java

package ui.juvenilewarrant.action;

import java.util.ArrayList;
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


public class DisplayDepartmentSearchAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 43F4FB9803BE
    */
   public DisplayDepartmentSearchAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 43F4EE420104
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   	JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
//    jwFormForm.setAction("create");
    jwForm.setSearchDepartmentName("");
    jwForm.setSearchDepartmentId("");
    jwForm.setDepartments(new ArrayList());
   	return aMapping.findForward(UIConstants.SEARCH_DEPT_SUCCESS);
   }

   /* (non-Javadoc)
	* @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	*/
   protected Map getKeyMethodMap()
   {
	   Map buttonMap = new HashMap();
	   buttonMap.put("button.back", "back");
	   return buttonMap;
   }

   /**
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
	   return aMapping.findForward(UIConstants.BACK);
   }
	
}
