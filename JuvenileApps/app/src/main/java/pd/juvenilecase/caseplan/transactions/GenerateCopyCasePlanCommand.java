/*
 * Created on Jun 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.caseplan.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.casefile.CreateActivityEvent;
import messaging.caseplan.GenerateCopyCasePlanEvent;
import messaging.caseplan.PersonResponsibleEvent;
import messaging.caseplan.reply.CaseplanDetailsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.utilities.DateUtil;
import naming.ActivityConstants;
import naming.CasePlanConstants;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.casefile.ActivityHelper;
import pd.juvenilecase.caseplan.CasePlan;
import pd.juvenilecase.caseplan.Goal;
import pd.juvenilecase.caseplan.PersonResponsible;
import pd.juvenilecase.caseplan.Placement;
import pd.juvenilecase.rules.JuvenileCaseSupervisionRule;

/**
 * @author ugopinath
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GenerateCopyCasePlanCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		
		GenerateCopyCasePlanEvent copyEvent=(GenerateCopyCasePlanEvent)event;
   		String casefileID = copyEvent.getCasefileId();
   		JuvenileCasefile casefile = JuvenileCasefile.find(casefileID);
   		String oldCaseplanID = copyEvent.getCopyCaseplanId();
   		CasePlan oldCasePlan=CasePlan.find(oldCaseplanID);
   		CasePlan newCasePlan= new CasePlan();
   			//newCasePlan.setStatusId(CasePlanConstants.CP_STATUS_PENDING); //commented to change as per new req ER 11204
   			newCasePlan.setStatusId(oldCasePlan.getStatusId()); //keeping the status of the caseplan same as it was before being Copied ER 11204
   			newCasePlan.setSupervisionNumber(casefileID);
   		Home home=new Home();
		home.bind(newCasePlan);
   		String newCaseplanId=newCasePlan.getCaseplanID();
   		casefile.setCaseplanId(newCasePlan.getOID().toString());
   		Collection myOldGoals=oldCasePlan.getTheGoal();	
   		if(myOldGoals!=null && myOldGoals.size()>0){
   			Iterator myOldGoalIter=myOldGoals.iterator();
   			while(myOldGoalIter.hasNext()){	
   				Goal oldGoal=(Goal)myOldGoalIter.next();
   				Goal newGoal = new Goal();
   				//newGoal.setStatusId(CasePlanConstants.GOAL_STATUS_PENDING);	
   				newGoal.setStatusId(oldGoal.getStatusId()); //keeping the status of the goal same as it was before being Copied ER 11204
   				newGoal.setDomainTypeId(oldGoal.getDomainTypeId());
   				newGoal.setEntryDate(DateUtil.getCurrentDate());
   				newGoal.setTimeFrameId(oldGoal.getTimeFrameId());
   				newGoal.setGoalDescription(oldGoal.getGoalDescription());
   				newGoal.setProgressNotes(oldGoal.getProgressNotes()); 
   				newGoal.setGoalIntervention(oldGoal.getGoalIntervention()); //added for ER JIMS200075816 MJCW
   				newGoal.setCasePlanId(newCaseplanId);
   				oldGoal.setStatusId(CasePlanConstants.GOAL_STATUS_COPIED); // making the old goal status as copied ER 11204
   				Home home1=new Home();
   				home1.bind(newGoal);
   				// do the rules associations
   				Collection oldRulesList=oldGoal.getRules();
   				if(oldRulesList!=null && oldRulesList.size()>0){
   					Iterator rulesIter=oldRulesList.iterator();
	   				while(rulesIter.hasNext()) {
	   					JuvenileCaseSupervisionRule rule = (JuvenileCaseSupervisionRule)rulesIter.next();
	   		   			newGoal.insertRules(rule);
	   				}
   				}
   				newCasePlan.insertTheGoal(newGoal);   				
   				Collection persons = oldGoal.getPersonResponsible();
   				if(persons!=null && persons.size()>0){
   					Iterator personsIter=persons.iterator();
	   				while(personsIter.hasNext()) {
	   					PersonResponsible oldPerson= (PersonResponsible)personsIter.next();
	   					PersonResponsible newPerson = new PersonResponsible();
	   		   			newPerson.setName(oldPerson.getName());
	   		   			newPerson.setPersonResponsibleType(oldPerson.getPersonResponsibleType());
	   		   			newGoal.insertPersonResponsible(newPerson);
	   				}
   				}
   			}
   		}
   		
   		CreateActivityEvent activityEvt = new CreateActivityEvent();
		activityEvt.setSupervisionNumber(casefileID);
		activityEvt.setActivityCodeId(ActivityConstants.CASE_PLAN_COPIED);
		activityEvt.setRecordActivity(true);
		activityEvt.setActivityDate(DateUtil.getCurrentDate());
		activityEvt.setComments("Caseplan copied from casefile " + copyEvent.getCopyFromCasefileId()+" to casefile "+copyEvent.getCasefileId()
				+" for juvenile " + casefile.getJuvenileNum());
		ActivityHelper helper = new ActivityHelper();
		helper.createActivity(activityEvt);
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		
   		
   		
   		// send the caseplan OID back for the find
   		CaseplanDetailsResponseEvent casePlanRespEvt = new CaseplanDetailsResponseEvent();
   		casePlanRespEvt.setCaseplanID(newCaseplanId);
   		dispatch.postEvent(casePlanRespEvt);
   	
		
		
   		Placement oldPlacement = oldCasePlan.getThePlacement();
   		Placement newPlacement;
   		if(oldPlacement != null) {
   			newPlacement = new Placement();
   			newPlacement.setSupervisionNumber(casefileID);
   			newPlacement.setCaseplanId(newCaseplanId);
   			newPlacement.setFacilityReleaseReasonId(oldPlacement.getFacilityReleaseReasonId());
   			newPlacement.setFacilityId(oldPlacement.getFacilityId());
   			newPlacement.setLevelOfCareId(oldPlacement.getLevelOfCareId());
   			newPlacement.setPermanencyPlanId(oldPlacement.getPermanencyPlanId());
   			newPlacement.setReasonForPlacement(oldPlacement.getReasonForPlacement());
   			newPlacement.setSpecialnotes(oldPlacement.getSpecialnotes());
   			newPlacement.setSpecificServices(oldPlacement.getSpecificServices());
   			newPlacement.setWhyOutsideTexas(oldPlacement.getWhyOutsideTexas());
   			newPlacement.setProximityToSchoolDist(oldPlacement.getProximityToSchoolDist());
   			newPlacement.setLeaseRestrEnvAvailable(oldPlacement.getLeaseRestrEnvAvailable());
   			newPlacement.setClosestFacilityAvailable(oldPlacement.getClosestFacilityAvailable());
   			newPlacement.setExpectedReleaseDate(oldPlacement.getExpectedReleaseDate());
   			newPlacement.setEntryDate(DateUtil.getCurrentDate());
   			newPlacement.setSupervisionNumber(casefileID);
   			newCasePlan.setThePlacement(newPlacement);
   		}
   		
   		helper = null;

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}

}
