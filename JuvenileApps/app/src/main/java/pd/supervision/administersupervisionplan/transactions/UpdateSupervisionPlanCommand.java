//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administersupervisionplan\\transactions\\UpdateSupervisionPlanCommand.java

package pd.supervision.administersupervisionplan.transactions;

import java.util.List;

import naming.PDCodeTableConstants;
import naming.PDConstants;
import pd.supervision.administersupervisionplan.SupervisionPlan;
import pd.supervision.administersupervisionplan.SupervisionPlanHelper;
import pd.task.Task;
import messaging.administersupervisionplan.UpdateSupervisionPlanEvent;
import messaging.administersupervisionplan.query.GetActiveSupervisionPlansEvent;
import messaging.administersupervisionplan.reply.SupervisionPlanResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

public class UpdateSupervisionPlanCommand implements ICommand 
{
   
   /**
    * @roseuid 4817675E0072
    */
   public UpdateSupervisionPlanCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 480E21660138
    */
   public void execute(IEvent event) 
   {
	   UpdateSupervisionPlanEvent reqEvent = (UpdateSupervisionPlanEvent)event;
	   
	   SupervisionPlan supervisionPlan = null;
	   String supervisionPlanId = null;
	   
//	   create supervision plan
	   if((reqEvent.getSupervisionPlanId()==null) || (reqEvent.getSupervisionPlanId().equalsIgnoreCase("")))
	   {
		   if (!(reqEvent.getDefendantId() == null || reqEvent.getSupervisionPlanDate() == null 
				   || reqEvent.getDefendantId().equals(PDConstants.BLANK))){
			   supervisionPlan = new SupervisionPlan();
		   
			   supervisionPlan.setDefendantId(reqEvent.getDefendantId());
			   supervisionPlan.setSupervisionPlanDate(reqEvent.getSupervisionPlanDate());
			   supervisionPlan.setStatusCd(reqEvent.getStatusCd());
			   supervisionPlan.setBehaviorObjective(reqEvent.getBehaviorObjective());
			   supervisionPlan.setCsoActionPlan(reqEvent.getCsoActionPlan());
			   supervisionPlan.setOffenderActionPlan(reqEvent.getOffenderActionPlan());
			   supervisionPlan.setProblem(reqEvent.getProblem());
		   
			   supervisionPlan.bind();
		   
			   supervisionPlanId = supervisionPlan.getOID();
			   SupervisionPlanResponseEvent responseEvent = SupervisionPlanHelper.getSupervisionPlanResponseEvent(supervisionPlanId);
			   MessageUtil.postReply(responseEvent);
		   }
	   }
//	   update supervision plan
	   else
	   {
		   supervisionPlanId = reqEvent.getSupervisionPlanId();
		   
		   supervisionPlan = SupervisionPlan.find(supervisionPlanId);
		   if(supervisionPlan != null)
		   {
			   supervisionPlan.setDefendantId(reqEvent.getDefendantId());
			   supervisionPlan.setSupervisionPlanDate(reqEvent.getSupervisionPlanDate());
			   supervisionPlan.setStatusCd(reqEvent.getStatusCd());
			   supervisionPlan.setBehaviorObjective(reqEvent.getBehaviorObjective());
			   supervisionPlan.setCsoActionPlan(reqEvent.getCsoActionPlan());
			   supervisionPlan.setOffenderActionPlan(reqEvent.getOffenderActionPlan());
			   supervisionPlan.setProblem(reqEvent.getProblem());
			   
			   SupervisionPlanResponseEvent responseEvent = SupervisionPlanHelper.getSupervisionPlanResponseEvent(supervisionPlanId);
			   MessageUtil.postReply(responseEvent);
		   }
	   }
	   
	   if(supervisionPlan != null && supervisionPlan.getStatusCd().equalsIgnoreCase(PDCodeTableConstants.CSC_SUPERVISION_PLAN_STATUS_ACTIVE))
	   {
		   GetActiveSupervisionPlansEvent activeSupPlanEvt = new GetActiveSupervisionPlansEvent();
		   activeSupPlanEvt.setDefendantId(reqEvent.getDefendantId());
		   activeSupPlanEvt.setStatusCd(PDCodeTableConstants.CSC_SUPERVISION_PLAN_STATUS_ACTIVE);
		   
		   List activeSupPlanList = SupervisionPlan.findAll(activeSupPlanEvt);
		   if(activeSupPlanList != null && activeSupPlanList.size() > 0)
		   {
			   for (int i = 0; i < activeSupPlanList.size(); i++) {
				   SupervisionPlan sp = (SupervisionPlan) activeSupPlanList.get(i);
				   if((!sp.getOID().equalsIgnoreCase(supervisionPlanId))){
					   sp.setStatusCd(PDCodeTableConstants.CSC_SUPERVISION_PLAN_STATUS_INACTIVE);
				   }
			   }
		   }
		   if (reqEvent.getTaskId() != null && !reqEvent.getTaskId().equals(PDConstants.BLANK)){
			   Task ntTask = Task.find(reqEvent.getTaskId());
			   if (ntTask != null){
				   ntTask.setStatusId("C");
			   }
		   }
	   }
   }
   
   /**
    * @param event
    * @roseuid 480E2166013A
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 480E21660146
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 480E21660148
    */
   public void update(Object anObject) 
   {
    
   }
   
}
