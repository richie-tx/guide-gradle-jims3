package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.reply.ActivityResponseEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.productionsupport.DeleteProductionSupportActivityEvent;
import messaging.productionsupport.GetProductionSupportActivityEvent;
import messaging.productionsupport.UpdateProductionSupportActivityRecordEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
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
import ui.juvenilecase.prodsupport.helpers.Constants;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 * 
 *         This action performs the deletes necessary to remove a Juvenile
 *         Activity and verifies that all records were deleted before returning
 *         the user to a summary screen.
 */

public class PerformActivityUpdateAction extends Action {
	
	private Logger log = Logger.getLogger("PerformActivityUpdateAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		
		String activityId = regform.getActivityId();
		
		if (activityId==null || activityId.equals("")){
			regform.setMsg("PerformActivityUpdateAction.java (63) - ActivityID was null.");
			return (mapping.findForward("error"));
		}
		
		log.info("Proceeding to Update ActivityId: " + activityId + " for LogonId: " + SecurityUIHelper.getLogonId());		
		
		CompositeResponse composite = null;
		//change to update
		UpdateProductionSupportActivityRecordEvent updateActivityEvent = (UpdateProductionSupportActivityRecordEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTACTIVITYRECORD);
		updateActivityEvent.setActivityId(activityId);
		updateActivityEvent.setCasefileId(regform.getCasefileId());
		updateActivityEvent.setActivityCd(regform.getActivityCd());
		updateActivityEvent.setComments(regform.getNewComments());
		updateActivityEvent.setUpdateComments(regform.getNewupdatedComments());
		updateActivityEvent.setActivityDate(DateUtil.stringToDate( regform.getActivityDate(),DateUtil.DATE_FMT_1));
		updateActivityEvent.setDetentionId(regform.getDetentionId());
		updateActivityEvent.setDetentionTime(regform.getDetentionTime());
		updateActivityEvent.setActivityTime(regform.getActivityTime());
		updateActivityEvent.setActivityendTime(regform.getActivityendTime());
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(updateActivityEvent);		
		// retrieve the updated activity record
		ActivityResponseEvent activityresp = getActivityRecord(activityId);
		regform.setActivityResp(activityresp);
		// retrieve the activity record before updating
		ActivityResponseEvent record = retrieveRecord(regform);
		regform.setOriginalactivityResp(record);
		return mapping.findForward("success");
		
	}
	private ActivityResponseEvent getActivityRecord(String activityId){
		
		CompositeResponse composite = null;
		GetProductionSupportActivityEvent getActivityEvent = (GetProductionSupportActivityEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTACTIVITY);
		getActivityEvent.setActivityId(activityId);

		composite = MessageUtil.postRequest(getActivityEvent);
		//ArrayList<ActivityResponseEvent> activityList = (ArrayList) MessageUtil.compositeToCollection(composite, ActivityResponseEvent.class);
		ActivityResponseEvent actresp = (ActivityResponseEvent) MessageUtil.filterComposite(composite, ActivityResponseEvent.class);
		
		
		return actresp;
	}
	private ActivityResponseEvent retrieveRecord(ProdSupportForm regform)
	    {

		ArrayList activities = regform.getActivities();
		ActivityResponseEvent record = null;

		Iterator iter = activities.iterator();
		if (iter.hasNext())
		{
		    record = (ActivityResponseEvent) iter.next();
		}
		
		return record;
	    }
	
	
	/**
	 * Delete the Activity Record from JCACTIVITY
	 * @param activityId
	 * @return
	 */
	
}
