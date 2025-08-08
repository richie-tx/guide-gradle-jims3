package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.reply.ActivityResponseEvent;
import messaging.productionsupport.DeleteProductionSupportActivityEvent;
import messaging.productionsupport.GetProductionSupportActivityEvent;
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
 * 
 *         This action performs the deletes necessary to remove a Juvenile
 *         Activity and verifies that all records were deleted before returning
 *         the user to a summary screen.
 */

public class PerformActivityDeleteAction extends Action {
	
	private Logger log = Logger.getLogger("PerformActivityDeleteAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		
		String activityId = regform.getActivityId();
		
		if (activityId==null || activityId.equals("")){
			regform.setMsg("PerformActivityDeleteAction.java (63) - ActivityID was null.");
			return (mapping.findForward("error"));
		}
		
		// send the delete request
		log.info("Proceeding to Delete ActivityId: " + activityId + " for LogonId: " + SecurityUIHelper.getLogonId());
		deleteActivityRecord(activityId);
		
		
		//		String deleteStatement = "delete from jims2.jcactivity where activity_id=" 
//			+ activityId;
//		boolean status = Constants.runStatement(deleteStatement);
		
		
		// check if the activity record still exists
		ArrayList existingActivityList = getActivityRecord(activityId);
		
		// if unsuccessful delete
		if(existingActivityList.size() > 0){
			log.info(" ERROR - ACTIVITY DELETE for activityId=" + regform.getActivityId()  + " was NOT successful.");
			regform.setMsg("Error - The activity was not deleted: ActivityId:" + activityId);
			return mapping.findForward("error");
		}else{
			log.info(" END - Performed an ACTIVITY DELETE for activityId=" + regform.getActivityId() + SecurityUIHelper.getLogonId());
			regform.setMsg("");
			return mapping.findForward("success");
		}
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
	
	/**
	 * Delete the Activity Record from JCACTIVITY
	 * @param activityId
	 * @return
	 */
	private void deleteActivityRecord(String activityId){
	
		CompositeResponse composite = null;
		DeleteProductionSupportActivityEvent deleteActivityEvent = (DeleteProductionSupportActivityEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTACTIVITY);
		deleteActivityEvent.setActivityId(activityId);

		composite = MessageUtil.postRequest(deleteActivityEvent);
	}
}
