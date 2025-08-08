
package pd.juvenilecase.caseplan.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.casefile.CreateActivityEvent;
import messaging.caseplan.PersonResponsibleEvent;
import messaging.caseplan.SaveCaseplanDataEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.RuleResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.ActivityConstants;
import naming.CasePlanConstants;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.casefile.ActivityHelper;
import pd.juvenilecase.caseplan.CasePlan;
import pd.juvenilecase.caseplan.PersonResponsible;
import pd.juvenilecase.factory.IJuvenileCaseworkResponseFactory;
import pd.juvenilecase.factory.JuvenileCaseworkResponseFactory;
import pd.juvenilecase.rules.RuleGroupConditionView;

public class SaveCaseplanDataCommand implements ICommand 
{
   
   /**
    * @roseuid 4533B84000AF
    */
   public SaveCaseplanDataCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 452FE42F0103
    */
   public void execute(IEvent event) 
   {
	   SaveCaseplanDataEvent composite = (SaveCaseplanDataEvent)event;
   		// get the caseplan 
   		String casefileID = composite.getSupervisionNumber();
   		JuvenileCasefile casefile = JuvenileCasefile.find(casefileID);
   		// find Caseplan from id
   		String caseplanID = casefile.getCaseplanId();
   		boolean isCreate = false;
   		if(caseplanID == null || caseplanID.equals("0")) {
   			isCreate = true;
   		}
   		CasePlan cp = initCaseplan(caseplanID, casefileID, isCreate);
   		
   		if(cp==null){
   			ErrorResponseEvent myEvt=new ErrorResponseEvent();
   			myEvt.setMessage("Problems creating/retrieving caseplan for casefile: " + casefileID);
   		 EventManager.getSharedInstance(EventManager.REPLY).postEvent(myEvt);
   		 return;
   		}
   		cp.setPriorServices(composite.getPriorServices());
   		cp.setSupLevelId(composite.getSupLevelId());
   		cp.setContactInfo(composite.getContactInfo());
   		//added for user story 11146
   		cp.setOthername(composite.getOthername());
   		cp.setChildDtNotified(composite.getChildDtNotified());
   		cp.setFamilyDtNotified(composite.getFamilyDtNotified());
   		cp.setCaregiverDtNotified(composite.getCaregiverDtNotified());
   		cp.setOtherDtNotified(composite.getOtherDtNotified());
   		cp.setChildNotifMethod(composite.getChildNotifMethod());
   		cp.setFamilyNotifMethod(composite.getFamilyNotifMethod());
   		cp.setCaregiverNotifMethod(composite.getCaregiverNotifMethod());
   		cp.setOtherNameNotifMethod(composite.getOtherNameNotifMethod());
   		cp.setChildDtOfParticipation(composite.getChildDtOfParticipation());
   		cp.setFamilyDtOfParticipation(composite.getFamilyDtOfParticipation());
   		cp.setCaregiverDtOfParticipation(composite.getCaregiverDtOfParticipation());
   		cp.setOtherNameDtOfParticipation(composite.getOtherNameDtOfParticipation());
   		cp.setChildMailedDt(composite.getChildMailedDt());
   		cp.setFamilyMailedDt(composite.getFamilyMailedDt());
   		cp.setCaregiverMailedDt(composite.getCaregiverMailedDt());
   		cp.setOtherNameMailedDt(composite.getOtherNameMailedDt());
   		//ended
   		//added for User story 11191 Add Title IV in caseplan
   		cp.setJuvFosterCareCandidate(composite.isJuvFosterCareCandidate());
   		boolean isFosterCareCandidate = cp.isJuvFosterCareCandidate();
   		if(isFosterCareCandidate == true){
	   		cp.setSocialHistDated(composite.getSocialHistDated());   		
	   		cp.setPsychologicalRepDated(composite.getPsychologicalRepDated());
	   		cp.setRiskAssesmentDated(composite.getRiskAssesmentDated());
	   		cp.setOtherDated(composite.getOtherDated());
	   		cp.setExplanation(composite.getExplanation());
	   		cp.setTitleIVEComment(composite.getTitleIVEComment());
   		}
   		else{
   			cp.setSocialHistDated(null);   		
	   		cp.setPsychologicalRepDated(null);
	   		cp.setRiskAssesmentDated(null);
	   		cp.setOtherDated(null);
	   		cp.setExplanation(null);
	   		
   		}
   		cp.setDtDeterminationMade(composite.getDtDeterminationMade());
   		cp.setTitleIVEComment(composite.getTitleIVEComment());
   		//

   		
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
