/*
 * Created on Jun 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.caseplan.transactions;


import java.util.Date;
import java.util.Iterator;

import messaging.caseplan.GenerateSignCaseplanEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.ActivityConstants;
import naming.CasePlanConstants;
import pd.juvenilecase.casefile.Activity;
import pd.juvenilecase.caseplan.CasePlan;
import pd.juvenilecase.caseplan.Goal;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GenerateSignCaseplanCommand implements ICommand 
{
	   
	   /**
	    * @roseuid 4533B81001A8
	    */
	   public GenerateSignCaseplanCommand() 
	   {
	    
	   }
	   
	   /**
	    * @param event
	    * @roseuid 45119A64015D
	    */
	   public void execute(IEvent event) 
	   {
	   	GenerateSignCaseplanEvent request = (GenerateSignCaseplanEvent)event;
	   		String caseplanID = request.getCaseplanId();
	   		// send the notification
	   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

//	   	 change the caseplan and goal statusses
	   		CasePlan cp = CasePlan.find(caseplanID);
	   		if(cp==null){
	   			return;
	   		}
	   		// create an activity
	   		cp.setReviewDate(DateUtil.getCurrentDate());
	   		String message="Final Caseplan Generated.";
	   		if(request.isSigned()){
	   			message="Caseplan e-signed.";
	   		}
	   		Activity activity = new Activity();
	   		activity.setSupervisionNumber(cp.getSupervisionNumber());
	   		activity.setActivityCodeId(ActivityConstants.CASE_PLAN_GENERATED);
	   		activity.setComments(message);   		
	   		activity.setActivityDate(DateUtil.getCurrentDate());
	   		
	   		// change the caseplan and goal statusses of approved to accepted
	   		if(cp != null) {
	   			cp.setStatusId(CasePlanConstants.CP_STATUS_SIGNED);
	   			Iterator goals = cp.getTheGoal().iterator();
	   			while(goals.hasNext()) {
	   				Goal goal = (Goal)goals.next();
	   				String statusId = goal.getStatusId();
	   				if(CasePlanConstants.GOAL_STATUS_APPROVED.equalsIgnoreCase(statusId)){
	   					goal.setStatusId(CasePlanConstants.GOAL_STATUS_ACCEPTED);
	   					goal.setGoalStatusChangeDate(new Date()); // updating the status change date date 
	   				}
	   			}
	   		}
	   		if(request.getReport()!=null && request.getReport().length>0){
	   			cp.setReport(request.getReport());
	   		}
	   		
	   		
	   }
	   
	   /**
	    * @param event
	    * @roseuid 45119A64015F
	    */
	   public void onRegister(IEvent event) 
	   {
	    
	   }
	   
	   /**
	    * @param event
	    * @roseuid 45119A640166
	    */
	   public void onUnregister(IEvent event) 
	   {
	    
	   }
	   
	   /**
	    * @param anObject
	    * @roseuid 45119A64016F
	    */
	   public void update(Object anObject) 
	   {
	    
	   }
	   
	}

