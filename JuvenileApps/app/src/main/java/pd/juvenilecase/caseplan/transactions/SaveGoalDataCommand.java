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
import pd.juvenilecase.caseplan.Goal;
import pd.juvenilecase.caseplan.PersonResponsible;
import pd.juvenilecase.factory.IJuvenileCaseworkResponseFactory;
import pd.juvenilecase.factory.JuvenileCaseworkResponseFactory;
import pd.juvenilecase.rules.RuleGroupConditionView;
import naming.ActivityConstants;
import messaging.casefile.CreateActivityEvent;
import messaging.caseplan.PersonResponsibleEvent;
import messaging.caseplan.SaveGoalDataEvent;
import messaging.caseplan.reply.GoalOIDEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.RuleResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class SaveGoalDataCommand implements ICommand 
{
   
   /**
    * @roseuid 4533B84000AF
    */
   public SaveGoalDataCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 452FE42F0103
    */
   public void execute(IEvent event) 
   {
   		SaveGoalDataEvent composite = (SaveGoalDataEvent)event;
   		// get the caseplan and add goal
   		String casefileID = composite.getSupervisionNumber();
   		JuvenileCasefile casefile = JuvenileCasefile.find(casefileID);
   		// find Caseplan from id
   		String caseplanID = casefile.getCaseplanId();
   		boolean isCreate = false;
   		if(caseplanID == null || caseplanID.equals("0")) {
   			isCreate = true;
   		}
   		CasePlan cp = initCaseplan(caseplanID, casefileID, isCreate);
   		// get the goal
   		if(cp==null){
   			ErrorResponseEvent myEvt=new ErrorResponseEvent();
   			myEvt.setMessage("Problems creating/retrieving caseplan for casefile: " + casefileID);
   		 EventManager.getSharedInstance(EventManager.REPLY).postEvent(myEvt);
   		 return;
   		}
   		String goalID = composite.getGoalID();
   		Goal goal = initGoal(goalID);
   		// set goal attributes
   		goal.setDomainTypeId(composite.getDomainTypeID());
   		//goal.setEntryDate(new Date());
   		goal.setTimeFrameId(composite.getTimeframeID());
   		goal.setExplainOtherTxt(composite.getExplainOtherTxt());
   		goal.setGoalDescription(composite.getGoalDescription());
   		goal.setGoalIntervention(composite.getGoalIntervention()); //added Intervention field for ER  JIMS200075816 
   		goal.setProgressNotes(composite.getProgressNotes());
   		goal.setEndRecommendations(composite.getEndRecommendations());
   		if(composite.isGoalEnded()) {
   			goal.setStatusId(CasePlanConstants.GOAL_STATUS_ENDED);
   			goal.setEndRecommendationDate(new Date());
   			goal.setGoalStatusChangeDate(new Date()); //updating the status change date 
   		}
   		if(composite.isPending()) {
   			//added the if condition to make the goal status modified after update if the caseplan is Reviewd or Modified 
   			if(composite.getCaseplanStatus().equals("REVIEWED") ||composite.getCaseplanStatus().equals("MODIFIED")){
   				goal.setStatusId(CasePlanConstants.GOAL_STATUS_MODIFIED);
   				cp.setStatusId(CasePlanConstants.CP_STATUS_MODIFIED);
   			}
   			else{
   				goal.setStatusId(CasePlanConstants.GOAL_STATUS_PENDING);
   			}
   			goal.setGoalStatusChangeDate(new Date()); //updating the status change date date 
   		}
   		//If a goal is pending set the corresponding caseplan to pending if the caseplan already not reviewed or modified.
   		if(goal.getStatusId().equals(CasePlanConstants.GOAL_STATUS_PENDING)){
   			if(composite.getCaseplanStatus()!= null && (composite.getCaseplanStatus().equals("REVIEWED")||composite.getCaseplanStatus().equals("MODIFIED"))){  
   				cp.setStatusId(CasePlanConstants.CP_STATUS_MODIFIED);
   			}
   			else{
   				cp.setStatusId(CasePlanConstants.CP_STATUS_PENDING);
   			}
   		}
   		
   		if(goalID == null || goalID.equals("")) { // a new goal was created.. so insert it.
   			goal.setEntryDate(new Date());//added the create date while creating the goal for first time.ER JIMS200076486
   			cp.insertTheGoal(goal);   			
   		}
   		casefile.setCaseplanId(cp.getOID().toString());
   		Enumeration requests = composite.getRequests();
   		savePersonsResponsible(goal, requests);
   		
   		// create activity for caseplan created/modified
   		recordActivity(casefileID, isCreate, goal.getGoalDescription(), goal.getStatus().getDescription());
   		   		
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		// send the goal OID
   		GoalOIDEvent idEvent = new GoalOIDEvent();
   		idEvent.setGoalID(goal.getOID().toString());
   		dispatch.postEvent(idEvent);
   		
//   	 if this command is being called for update, then no need to send back the rules
   		if(goalID != null && !goalID.equals("")) {
   			return;
   		}
   		// for create, send back the rules for this casefile
		sendRules(casefileID, dispatch);
   		
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
   
   private void savePersonsResponsible(Goal goal, Enumeration requests) {
   		PersonResponsibleEvent prEvt;
		PersonResponsible person;
		Collection persons = goal.getPersonResponsible();
		
		while(requests.hasMoreElements()) {
			prEvt = (PersonResponsibleEvent)requests.nextElement();
			persons.removeAll(persons); //ER JIMS200075816 MJCW for updating the person responsible list
			// if not already associated, associate the person
			if(!alreadyAssigned(prEvt, persons)) {				
   			person = new PersonResponsible();
   			person.setName(prEvt.getName());
   			person.setPersonResponsibleType(prEvt.getType());
   			goal.insertPersonResponsible(person);
			}
		}
   }
   
   private Goal initGoal(String goalID) {
   		Goal goal = null;
   		if(goalID == null || goalID.equals("")) {
   			goal = new Goal();
   			goal.setStatusId(CasePlanConstants.GOAL_STATUS_PENDING);
   		} else {
   			goal = Goal.find(goalID);
   		}
   		return goal;
   }
   
   private CasePlan initCaseplan(String caseplanID, String casefileID, boolean isCreate) {
   		CasePlan cp = null;
		if(isCreate) { // if no caseplan, create it
   			cp = new CasePlan();
   			cp.setStatusId(CasePlanConstants.CP_STATUS_PENDING);
   			cp.setSupervisionNumber(casefileID);
   		} else {
   			cp = CasePlan.find(caseplanID);
   			if(cp != null){
	   			String statusId = cp.getStatusId();
//	   			if(CasePlanConstants.CP_STATUS_REVIEWED.equals(statusId) || CasePlanConstants.CP_STATUS_ACCEPTED.equals(statusId)) {
//	   			    cp.setStatusId(CasePlanConstants.CP_STATUS_PENDING);
//	   			}
   			}
   		}
		return cp;
   }
   
   private void sendRules(String casefileID, IDispatch dispatch) {
	   	IJuvenileCaseworkResponseFactory responseFactory =  new JuvenileCaseworkResponseFactory();
		Iterator rulesIterator = RuleGroupConditionView.findAll("casefileId", casefileID);
		while(rulesIterator.hasNext())
		{
			RuleGroupConditionView ruleView = (RuleGroupConditionView) rulesIterator.next();
			RuleResponseEvent response = responseFactory.getRuleResponseEvent(ruleView);
			dispatch.postEvent(response);
			
		}
   	
   }
   
   private boolean alreadyAssigned(PersonResponsibleEvent prEvt, Collection persons) {
   		boolean assigned = false;
   		Iterator ite = persons.iterator();
   		while(ite.hasNext()) {
   			PersonResponsible person = (PersonResponsible)ite.next();
   			if(person.getName().equalsIgnoreCase(prEvt.getName()) &&
   				person.getPersonResponsibleType().equalsIgnoreCase(prEvt.getType())) {
   					assigned = true;
   					break;
   			}
   		}
   		
   		return assigned;
   
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
