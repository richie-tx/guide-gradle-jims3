//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplayDepartmentPolicyCreateAction.java

package ui.supervision.SupervisionOptions.action;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.SupervisionOptions.form.DepartmentPolicyForm;

public class DisplayDepartmentPolicyCreateAction extends Action
{
   
   /**
    * @roseuid 42F7C48701C5
    */
   public DisplayDepartmentPolicyCreateAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F79A080188
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   	
		//	clean up the form upon at begining of scenario
		DepartmentPolicyForm form = (DepartmentPolicyForm)aForm;
		form.clear();
		// make sure that policyId is null
		form.setPolicyId(null);
		
		Date today = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		form.setEffectiveDate(dateFormatter.format(new Date()));
		
		form.setAllCourtsSelected(true);
		
		// TODO get agency from the user
		String agencyId = form.getAgencyId();
			  	
		if(form.getGroups() == null || form.getGroups().size() == 0)
		{
			// get groups	
			Collection groups =	UISupervisionOptionHelper.fetchGroups(agencyId);
			form.setGroups(groups);
		}
		
		
		if(form.getCourts() == null || form.getCourts().size() == 0)
		{	
			Collection courts =	UISupervisionOptionHelper.getFilteredCourtBeans();
			if(form.getAgencyId().equals(UIConstants.JUV)){
				courts=UISupervisionOptionHelper.filterCourtBeansForCrtCategory(UIConstants.JUVENILE_COURT_CATEGORY,courts);
			}
			form.setCourts(courts);
		}
   		
		form.setPageType( UIConstants.SUMMARY );
			
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
   }
}
