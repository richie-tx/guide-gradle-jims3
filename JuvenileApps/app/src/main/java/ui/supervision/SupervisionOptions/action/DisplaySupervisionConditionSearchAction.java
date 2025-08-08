//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplaySupervisionConditionSearchAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;
import ui.supervision.suggestedorder.helper.UISuggestedOrderHelper;

public class DisplaySupervisionConditionSearchAction extends Action
{
   
   /**
    * @roseuid 42F7C49302CE
    */
   public DisplaySupervisionConditionSearchAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F79A3900AD
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		//lean up the form upon at begining of scenario
		SupervisionConditionForm form = (SupervisionConditionForm)aForm;
		form.clear();
		
		//all courts is selected by default
		form.setAllCourtsSelected(true);
		//if (form.getCourts() == null || form.getCourts().size() == 0)
		//{
			Collection courtBeans = UISuggestedOrderHelper.getCourtBeans();
			form.setCourts(courtBeans);
		//}
		
		form.setStandardSearchCriteria("false");
		if(!(form.getAgencyId().equalsIgnoreCase("JUV"))){
			form.setJurisdictionId("HC");
		}
		else
			form.setJurisdictionId("");
		form.setConditionStatusId("A");
		form.setSpecialCondition(false); 
	
//		GetAllGroupsEvent event = new GetAllGroupsEvent(); 
//		event.setAgencyId("CSC");
//
//		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
//
//		dispatch.postEvent(event);
//
//		Collection groups =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), GroupResponseEvent.class );
//	
//		
//		form.setGroups(groups);
//		
//
//		if(form.getCourts() == null || form.getCourts().size() == 0)
//		{	
//			Collection courtBeans =	UISupervisionOptionHelper.getCourtBeans();
//			form.setCourts(courtBeans);
//		}
  		
  		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
   }
}
