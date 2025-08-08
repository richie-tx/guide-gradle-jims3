//Source file: C:\\views\\MSA\\app\\src\\ui\\contact\\user\\action\\HandleDepartmentSelectionAction.java

package ui.contact.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.contact.department.form.DepartmentForm;
import ui.contact.user.form.UserProfileForm;

public class HandleDepartmentSelectionAction 
{
   
   /**
    * @roseuid 43F4FC4101D6
    */
   public HandleDepartmentSelectionAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 43F4EE2F00C1
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   	DepartmentForm deptForm = (DepartmentForm)aForm;
   	
	UserProfileForm userProfileForm = null;
	HttpSession session = aRequest.getSession();
	Object obj = session.getAttribute(UIConstants.USERPROFILE_FORM);
	if (obj != null || obj instanceof UserProfileForm)
	{
		userProfileForm = (UserProfileForm) obj;
	}
	else
	{
		userProfileForm = new UserProfileForm();
		session.setAttribute(UIConstants.USERPROFILE_FORM, userProfileForm);
	}
	userProfileForm.setDepartmentId(deptForm.getDepartmentId());
	userProfileForm.setDepartmentName(deptForm.getDepartmentName());
	
    return aMapping.findForward(UIConstants.SELECT_DEPT_SUCCESS);
   }
}
