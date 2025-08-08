package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.reply.ActivityResponseEvent;
import messaging.productionsupport.GetProductionSupportActivityEvent;
import messaging.productionsupport.GetProductionSupportRiskAnalysisEvent;
import messaging.productionsupport.reply.ProductionSupportRiskAnalysisResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.juvenilecase.prodsupport.helpers.Constants;
import ui.security.SecurityUIHelper;


/**
 * @author rcarter
 */
public class ActivityDeleteQueryAction extends Action {
	private Logger log = Logger.getLogger("ActivityDeleteQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
			
		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk!=null && clrChk.equalsIgnoreCase("Y"))
		{
			regform.clearAllResults();
			regform.setMsg("");
			return mapping.findForward("error");		
		}
		
		
		String activityId = regform.getActivityId();

		if (activityId == null || activityId.equals(" ")) {
			regform.setMsg("You must enter a valid ActivityID.");
			return mapping.findForward("error");
		}
		
		//Clear the form	
		regform.clearAllResults();
		
		//Log the query attempt
		log.info("Activity Query ID: " + activityId + " User LogonId: " + SecurityUIHelper.getLogonId());
		
		// retrieve the activity record
		ArrayList activities = getActivityRecord(activityId);		
		
		if (activities!=null)
		{
			regform.setActivityCount(activities.size());
			regform.setActivities(activities);
		}	
		
		if (regform.getActivities()==null || regform.getActivityCount()==0)
		{
			regform.setMsg("No records returned for activityId "+activityId);
			return mapping.findForward("error");
		}
			
		regform.setMsg("");
		return mapping.findForward("success");

	}
	
	
	/**
	 * Retrieve the Activity Record from JCACTIVITY
	 * @param activityId
	 * @return
	 */
	private ArrayList<ActivityResponseEvent> getActivityRecord(String activityId){
	
		CompositeResponse composite = null;
		GetProductionSupportActivityEvent getActivityEvent = (GetProductionSupportActivityEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTACTIVITY);
		getActivityEvent.setActivityId(activityId);

		composite = MessageUtil.postRequest(getActivityEvent);
		//Gets the actual responses to questions from the composite response and places them in a collection for the UI to display
		ArrayList<ActivityResponseEvent> activityList = (ArrayList) MessageUtil.compositeToCollection(composite, ActivityResponseEvent.class);
		
		return activityList;
	}
}
