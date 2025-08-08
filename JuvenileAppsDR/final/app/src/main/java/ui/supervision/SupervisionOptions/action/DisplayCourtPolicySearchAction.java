//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplayCourtPolicySearchAction.java

package ui.supervision.SupervisionOptions.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.form.CourtPolicyForm;

public class DisplayCourtPolicySearchAction extends Action
{
   
   /**
    * @roseuid 42F7C482000F
    */
   public DisplayCourtPolicySearchAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F7997D038F
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		//	lean up the form upon at begining of scenario
		CourtPolicyForm form = (CourtPolicyForm)aForm;
	  	form.clear();
		
		form.setAllCourtsSelected(true); //default choice of radio button to all courts
		form.setStandardSearchCriteria("true");
		form.setCourtPolicyStatus("A");
			
//		GetAllGroupsEvent event = new GetAllGroupsEvent(); 
//		event.setAgencyId("CSC");
//
//	  	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
//
//	  	dispatch.postEvent(event);
//
//	  	Collection groups =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), GroupResponseEvent.class );
//
//	  	form.setGroups(groups);
//	  	
//		if(form.getCourts() == null || form.getCourts().size() == 0)
//		{	
//			Collection courtBeans =	UISupervisionOptionHelper.getCourtBeans();
//			form.setCourts(courtBeans);
//		}
	
	
	 	return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
		 
   }
}
