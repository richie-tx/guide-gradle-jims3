//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplayDepartmentPolicySearchAction.java

package ui.supervision.SupervisionOptions.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.form.DepartmentPolicyForm;
 
public class DisplayDepartmentPolicySearchAction extends Action
{
   
   /**
    * @roseuid 42F7C48A0280
    */
   public DisplayDepartmentPolicySearchAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F79A09006F
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		//	clean up the form upon at begining of scenario
		DepartmentPolicyForm form = (DepartmentPolicyForm)aForm;
		form.clear();
		form.setPolicyId("");
		
		form.setAllCourtsSelected(true);
		form.setStatus("A");
			
		// TODO get agency from the user
		String agencyId = form.getAgencyId();
				  	
//		if(form.getGroups() == null || form.getGroups().size() == 0)
//		{
//			// get groups	
//			Collection groups =	UISupervisionOptionHelper.fetchGroups(agencyId);
//			form.setGroups(groups);
//		}
//			
//			
//		if(form.getCourts() == null || form.getCourts().size() == 0)
//		{	
//			Collection courts =	UISupervisionOptionHelper.getCourtBeans();
//			form.setCourts(courts);
//		}

		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
   }
}
