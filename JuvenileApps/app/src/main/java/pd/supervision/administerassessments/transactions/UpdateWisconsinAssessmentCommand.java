//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerassessments\\transactions\\UpdateWisconsinAssessmentCommand.java

package pd.supervision.administerassessments.transactions;

import java.util.Calendar;
import java.util.List;

import pd.supervision.administerassessments.Assessment;
import pd.supervision.administerassessments.AssessmentHelper;
import pd.supervision.administerassessments.Wisconsin;
import pd.supervision.administercaseload.SupervisionLevelOfServiceCode;
import pd.supervision.csts.CSTSHelper;
import messaging.administerassessments.UpdateCSAssessmentEvent;
import messaging.administerassessments.UpdateWisconsinAssessmentEvent;
import messaging.administerassessments.query.GetNewestAssessmentEvent;
import messaging.administerassessments.reply.AssessmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.MessageUtil;


public class UpdateWisconsinAssessmentCommand implements ICommand 
{
   
   /**
    * @roseuid 479107300138
    */
   public UpdateWisconsinAssessmentCommand() 
   {
    
   }
   private static final String T21 = "T21";
   /**
    * @param event
    * @roseuid 479101BE00D1
    */
   public void execute(IEvent event) 
   {
       UpdateWisconsinAssessmentEvent updateWisconsinEvent = (UpdateWisconsinAssessmentEvent) event;
       
       UpdateCSAssessmentEvent updateCSAssessmentEvent = (UpdateCSAssessmentEvent) updateWisconsinEvent;
       
       boolean sentToState = false;
       Wisconsin wisconsinSentToState = null;
       
       if (!updateWisconsinEvent.isAssessmentIncomplete()){
    	   //Determine if assessment T21 has already been sent to state.
    	   GetNewestAssessmentEvent newestWiscEvt = new GetNewestAssessmentEvent();
    	   newestWiscEvt.setMasterAssessmentId(updateWisconsinEvent.getMasterAssessmentId());
    	   List assessmentList = Wisconsin.findAll(newestWiscEvt);
    	   
    	   if (assessmentList != null && assessmentList.size() > 0){
    		   wisconsinSentToState = (Wisconsin) assessmentList.get(0);
    		   sentToState = AssessmentHelper.hasCSTSRecordBeenSentToState((Assessment) wisconsinSentToState);
    		   if (!sentToState){
    			   wisconsinSentToState = null;
    		   }
    	   }
       }
       
       Assessment assessment = Assessment.create(updateCSAssessmentEvent);
       
       Wisconsin wisconsin = null;
       List wisconsinAssessList = Wisconsin.findAll("assessmentId", assessment.getOID());
       if(wisconsinAssessList!=null && wisconsinAssessList.size()==1)
       {
    	   wisconsin = (Wisconsin)wisconsinAssessList.get(0);
       }
       else
       {
	       wisconsin = new Wisconsin();
	       wisconsin.setMasterAssessmentId(assessment.getMasterAssessmentId()); 
	       wisconsin.setAssessmentId(assessment.getOID());
	       wisconsin.setIsPending(false);
	       if (updateWisconsinEvent.getIsInitial()){
	           wisconsin.setIsReassessment(false);
	       } else {
	           wisconsin.setIsReassessment(true);
	       }
       }

       Calendar calendar = Calendar.getInstance();
       calendar.setTime(updateCSAssessmentEvent.getAssessmentDate());
       calendar.roll(Calendar.YEAR, true);
       wisconsin.setAssessmentDueDate(calendar.getTime());

       wisconsin.setNeedsLevel(updateWisconsinEvent.getNeedsLevel());
       wisconsin.setRiskLevel(updateWisconsinEvent.getRiskLevel());
       wisconsin.setTotalNeedsScore(updateWisconsinEvent.getNeedsScore());
       wisconsin.setTotalRiskScore(updateWisconsinEvent.getRiskScore());
      
       String losCodeOID = this.getLevelOfSupervisionOID(updateWisconsinEvent.getLevelOfSupervision());
       wisconsin.setSuggestedLosCdId(losCodeOID);
       
       wisconsin.setAssessment(assessment);
       wisconsin.bind();
       
       AssessmentResponseEvent are = AssessmentHelper.getAssessmentResponseEvent(wisconsin.getMasterAssessmentId(), wisconsin.getOID());
       MessageUtil.postReply(are);
       
       AssessmentHelper.scheduleNextWisconsin(updateWisconsinEvent);
       
       if (!updateWisconsinEvent.isAssessmentIncomplete()){
    	   if (sentToState 
    			   && !wisconsinSentToState.getAssessmentDate().equals(updateWisconsinEvent.getAssessmentDate())){ 
     			 if (wisconsinSentToState.getTotalNeedsScore() != updateWisconsinEvent.getNeedsScore()
    				 || wisconsinSentToState.getTotalRiskScore() != updateWisconsinEvent.getRiskScore()){
    				 //Create record with original date and new scores
    				 wisconsinSentToState.setTotalNeedsScore(updateWisconsinEvent.getNeedsScore());
    				 wisconsinSentToState.setTotalRiskScore(updateWisconsinEvent.getRiskScore());
    				 CSTSHelper.createCSTS(wisconsinSentToState);
    				 //Do not update assessment. Object was updated for CSTS record only.
    				 TransactionManager tm = TransactionManager.getInstance();
    				 tm.removeUpdated(wisconsinSentToState);
   				 } 
				 AssessmentHelper.createTaskToCSTSWorkgroup(
						 updateWisconsinEvent.getDefendantId(),
						 wisconsin.getIsReassessment(),
						 T21,
						 wisconsinSentToState.getAssessmentDate(),
						 updateWisconsinEvent.getAssessmentDate());
    	   } else {
    		   CSTSHelper.createCSTS(wisconsin);
    	   }
       }
   }
   
   /**
    * @param legacyLOSCode
    * @return
    */
   private String getLevelOfSupervisionOID(String legacyLOSCode) {
       List aList = CollectionUtil.iteratorToList( SupervisionLevelOfServiceCode.findAll("levelOfServiceLegacyCode", legacyLOSCode ));
       String losOID = null;
       if (aList != null && aList.size() > 0){
           SupervisionLevelOfServiceCode losCode = (SupervisionLevelOfServiceCode) aList.get(0);
           losOID = losCode.getOID();
       }
       return losOID;
}

/**
    * @param event
    * @roseuid 479101BE00D3
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 479101BE00D5
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param updateObject
    * @roseuid 479107300157
    */
   public void update(Object updateObject) 
   {
    
   }
}
