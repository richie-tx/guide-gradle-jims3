//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\caseplan\\transactions\\GetCaseplanDetailsCommand.java

package pd.juvenilecase.caseplan.transactions;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import messaging.caseplan.RequestForCaseplanReviewEvent;
import messaging.task.CreateTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.ActivityConstants;
import naming.CasePlanConstants;
import naming.TaskControllerServiceNames;
import pd.juvenilecase.casefile.Activity;
import pd.juvenilecase.caseplan.CasePlan;
import pd.juvenilecase.caseplan.Goal;
import pd.task.helper.TaskHelper;

public class RequestForCaseplanReviewCommand implements ICommand 
{
   
   /**
    * @roseuid 4533B81001A8
    */
   public RequestForCaseplanReviewCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45119A64015D
    */
   public void execute(IEvent event) throws IOException
   {
   		RequestForCaseplanReviewEvent request = (RequestForCaseplanReviewEvent)event;
   		String caseplanID = request.getCaseplanID();
   		
   		// Create task
   		CreateTaskEvent createTask = (CreateTaskEvent) EventFactory.getInstance(TaskControllerServiceNames.CREATETASK);
   		createTask.setCreatorId(request.getUserID());
   		createTask.setOwnerId(request.getClmLogonID());
   		createTask.addTaskStateItem("submitAction","Link");
   		createTask.addTaskStateItem("currentCaseplan.caseplanId",caseplanID);
   		createTask.addTaskStateItem("casefileId",request.getCasefileID());
   		createTask.setTaskTopic("JC.CASEPLAN.FWD_FOR_REVIEW");
   		StringBuffer subject = new StringBuffer("Caseplan needs CLM review for Supervision #: ");
   		subject.append(request.getCasefileID()+","+"Juvenile#"+request.getJuvenileNum());
   		createTask.setTaskSubject(subject.toString());
		//<KISHORE>JIMS200057619 : MJCW - Get Rid of Command Chaining for Tasks
		//IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		//dispatch.postEvent( createTask ) ;
        try {
            @SuppressWarnings("unused")
			String taskId = TaskHelper.createTask(createTask);
        } catch (IOException e) {
            throw e;
        }

   		
   		// create an activity
   		Activity activity = new Activity();
   		activity.setSupervisionNumber(request.getCasefileID());
   		activity.setActivityCodeId(ActivityConstants.CASE_PLAN_FORWARDED_FOR_CLM_REVIEW);
   		activity.setComments(request.getComments());   		
   		activity.setActivityDate(DateUtil.getCurrentDate());
   		
   		// change the caseplan and goal statusses
   		CasePlan cp = CasePlan.find(caseplanID);
   		if(cp != null) {
   			cp.setStatusId(CasePlanConstants.CP_STATUS_INREVIEW);
   			Iterator goals = cp.getTheGoal().iterator();
   			while(goals.hasNext()) {
   				Goal goal = (Goal)goals.next();
   				String statusId = goal.getStatusId();
   				if(CasePlanConstants.GOAL_STATUS_PENDING.equalsIgnoreCase(statusId)||CasePlanConstants.GOAL_STATUS_MODIFIED.equalsIgnoreCase(statusId)){
   					goal.setStatusId(CasePlanConstants.GOAL_STATUS_LOCKED);
   					goal.setGoalStatusChangeDate(new Date()); //updating the status change date date
   				}
   			}
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
