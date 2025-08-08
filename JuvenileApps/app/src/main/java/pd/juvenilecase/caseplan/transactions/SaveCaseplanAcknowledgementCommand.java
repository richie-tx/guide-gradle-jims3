//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\caseplan\\transactions\\SaveGoalDataCommand.java

package pd.juvenilecase.caseplan.transactions;

import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;

import naming.CasePlanConstants;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.casefile.ActivityHelper;
import pd.juvenilecase.caseplan.CasePlan;
import pd.juvenilecase.caseplan.CaseplanAcknowledgement;
import pd.juvenilecase.caseplan.Goal;
import pd.juvenilecase.caseplan.PersonResponsible;
import pd.juvenilecase.factory.IJuvenileCaseworkResponseFactory;
import pd.juvenilecase.factory.JuvenileCaseworkResponseFactory;
import pd.juvenilecase.rules.RuleGroupConditionView;
import naming.ActivityConstants;
import messaging.casefile.CreateActivityEvent;
import messaging.caseplan.PersonResponsibleEvent;
import messaging.caseplan.SaveCaseplanAcknowledgementEvent;
import messaging.caseplan.SaveGoalDataEvent;
import messaging.caseplan.reply.GoalOIDEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.RuleResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.persistence.Home;

public class SaveCaseplanAcknowledgementCommand implements ICommand 
{
   
   /**
    * @roseuid 4533B84000AF
    */
   public SaveCaseplanAcknowledgementCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 452FE42F0103
    */
   public void execute(IEvent event) 
   {
	   SaveCaseplanAcknowledgementEvent composite = (SaveCaseplanAcknowledgementEvent)event;
   		// get the caseplan 
   		CasePlan cp = CasePlan.find(composite.getCaseplanID());
   		if(cp==null){
   			ErrorResponseEvent myEvt=new ErrorResponseEvent();
   			myEvt.setMessage("Problems creating/retrieving caseplan " + composite.getCaseplanID());
   		 EventManager.getSharedInstance(EventManager.REPLY).postEvent(myEvt);
   		 return;
   		}
   		else
   		{
   			CaseplanAcknowledgement ack = new CaseplanAcknowledgement();
   			ack.setCaseplanID(composite.getCaseplanID());
   			ack.setEntryDate(composite.getEntryDate());
   			ack.setExplanation(composite.getExplanation());
   			ack.setSignatureStatus(composite.getSignatureStatus());
   			if(!cp.getStatusId().equals(CasePlanConstants.CP_STATUS_SIGNED)) // bug fix:17325
   			cp.setStatusId(CasePlanConstants.CP_STATUS_ACKNOWLEDGED);
   			IHome home= new Home();
   			home.bind(ack);
   		}
   		
   		
   }
   
   private void recordActivity(String casefileID, boolean isCreate, String goalDescription, String goalStatus) {
	   	CreateActivityEvent activityEvt = new CreateActivityEvent();
		activityEvt.setSupervisionNumber(casefileID);	
		activityEvt.setComments("Goal Status: " + goalStatus + " Goal Literal: " + goalDescription);
		if (isCreate) {
			activityEvt.setActivityCodeId(ActivityConstants.CASE_PLAN_CREATED);
		}
		else {
			activityEvt.setActivityCodeId(ActivityConstants.CASE_PLAN_MODIFIED);
		}
		activityEvt.setRecordActivity(true);
		ActivityHelper helper = new ActivityHelper();
		helper.createActivity(activityEvt);
		helper = null;
   }
   
   /**
    * @param event
    * @roseuid 452FE42F0105
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 452FE42F010D
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 452FE42F010F
    */
   public void update(Object anObject) 
   {
    
   }
   

}
