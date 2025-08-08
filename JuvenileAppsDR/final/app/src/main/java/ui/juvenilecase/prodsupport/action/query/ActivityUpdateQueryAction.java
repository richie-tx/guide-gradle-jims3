package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.reply.ActivityResponseEvent;
import messaging.productionsupport.GetProductionSupportActivityEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;


/**
 * @author rcarter
 */
public class ActivityUpdateQueryAction extends Action {
	private Logger log = Logger.getLogger("ActivityUpdateQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
			
		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk!=null && clrChk.equalsIgnoreCase("Y"))
		{
			regform.clearAllResults();
			regform.setActivityId("");
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
			if(activities.size()>0)
			{
        			ActivityResponseEvent resp=(ActivityResponseEvent) activities.get(0);
        			regform.setCasefileId(resp.getCasefileId());
        			regform.setActivityCd(resp.getCodeId());
        			regform.setNewComments(resp.getComments());
        			regform.setNewupdatedComments(resp.getUpdateComments());
        			regform.setActivityDate(DateUtil.dateToString(resp.getActivityDate(),DateUtil.DATE_FMT_1));
        			regform.setDetentionId(resp.getDetentionId());
        			regform.setLatitude(resp.getLatitude());
        			regform.setLongitude( resp.getLongitude());
        			/*regform.setDetentionTime(resp.getDetentionTime());
        			regform.setActivityTime(resp.getActivityTime());
        			regform.setActivityendTime(resp.getActivityendTime());*/
        			if(resp.getDetentionTime()!=null&&!resp.getDetentionTime().isEmpty())
        			    regform.setDetentionTime(resp.getDetentionTime().substring(0, 8));
        			if(resp.getActivityTime()!=null&&!resp.getActivityTime().isEmpty())
        			    regform.setActivityTime(resp.getActivityTime().substring(0, 8));
        			if(resp.getActivityendTime()!=null&&!resp.getActivityendTime().isEmpty())
        			    regform.setActivityendTime(resp.getActivityendTime().substring(0, 8));
			}
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
		ArrayList<ActivityResponseEvent> activityList = (ArrayList) MessageUtil.compositeToCollection(composite, ActivityResponseEvent.class);
		
		return activityList;
	}
}
