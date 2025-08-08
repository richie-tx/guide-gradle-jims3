//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerassessments\\transactions\\UpdateLSIRAssessmentCommand.java

package pd.supervision.administerassessments.transactions;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pd.supervision.administerassessments.Assessment;
import pd.supervision.administerassessments.AssessmentHelper;
import pd.supervision.administerassessments.AssessmentQuestionAnswer;
import pd.supervision.administerassessments.LSIR;
import pd.supervision.csts.CSTSHelper;
import messaging.administerassessments.CSCAnswer;
import messaging.administerassessments.UpdateCSAssessmentEvent;
import messaging.administerassessments.UpdateLSIRAssessmentEvent;
import messaging.administerassessments.query.GetNewestAssessmentEvent;
import messaging.administerassessments.reply.AssessmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;


public class UpdateLSIRAssessmentCommand implements ICommand 
{
   
   /**
    * @roseuid 4791072F03C8
    */
   public UpdateLSIRAssessmentCommand() 
   {
    
   }
   private static final String T21 = "T21";
   /**
    * @param event
    * @roseuid 479101C30036
    */
   public void execute(IEvent event) 
   {
       UpdateLSIRAssessmentEvent updateLSIREvent = (UpdateLSIRAssessmentEvent) event;
       
       UpdateCSAssessmentEvent updateCSAssessmentEvent = (UpdateCSAssessmentEvent) updateLSIREvent;
       
       boolean sentToState = false;
       LSIR lsirSentToState = null;
       
       if (!updateLSIREvent.isAssessmentIncomplete()){
    	   //Determine if assessment T21 has already been sent to state.
    	   GetNewestAssessmentEvent newestLSIREvt = new GetNewestAssessmentEvent();
    	   newestLSIREvt.setMasterAssessmentId(updateLSIREvent.getMasterAssessmentId());
    	   List assessmentList = LSIR.findAll(newestLSIREvt);
    	   
    	   if (assessmentList != null && assessmentList.size() > 0){
    		   lsirSentToState = (LSIR) assessmentList.get(0);
    		   sentToState = AssessmentHelper.hasCSTSRecordBeenSentToState((Assessment) lsirSentToState);
    		   if (!sentToState){
    			   lsirSentToState = null;
    		   }
    	   }
       }
       
       Assessment newAssessment = Assessment.create(updateCSAssessmentEvent);

       LSIR newLSIR = new LSIR();
       newLSIR.setAssessmentId(newAssessment.getOID());
       newLSIR.setComments(updateLSIREvent.getComments());
       newLSIR.setIsReassessment(updateLSIREvent.getIsReassessment());
       
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(updateCSAssessmentEvent.getAssessmentDate());
       calendar.roll(Calendar.YEAR, true);
       newLSIR.setDueDate(calendar.getTime());
       
       newLSIR.setAssessment(newAssessment);
       newLSIR.bind();
       
       AssessmentResponseEvent are = AssessmentHelper.getAssessmentResponseEvent(newLSIR.getMasterAssessmentId(), newLSIR.getOID());
       MessageUtil.postReply(are);
       
       AssessmentHelper.scheduleNextWisconsin(updateCSAssessmentEvent);
       
       if (!updateLSIREvent.isAssessmentIncomplete()){
    	   if (sentToState 
    			   && !lsirSentToState.getAssessmentDate().equals(updateLSIREvent.getAssessmentDate())){ 
    		   Map newScoresMap = this.scoresWereChanged(lsirSentToState, updateCSAssessmentEvent);
    		   if (newScoresMap.size() > 0){
    			//Create record with original date and new scores
    			   String newNeedsScore = (String) newScoresMap.get(NEEDS_SCORE);
    			   String newRiskScore = (String) newScoresMap.get(RISK_SCORE);
    			   CSTSHelper.createCSTS(lsirSentToState, newNeedsScore, newRiskScore); 
   				} 
				AssessmentHelper.createTaskToCSTSWorkgroup(
					 updateLSIREvent.getDefendantId(),
					 newLSIR.getIsReassessment(),
					 T21,
					 lsirSentToState.getAssessmentDate(),
					 updateLSIREvent.getAssessmentDate());
    	   } else {
    		   CSTSHelper.createCSTS(newLSIR);
    	   }
       }
   }
   private static final String NEEDS_SCORE = "NeedsScore";
   private static final String RISK_SCORE = "RiskScore";

   private Map scoresWereChanged(LSIR lsirSentToState, UpdateCSAssessmentEvent updateCSAssessmentEvent) {
		
	   String oldRiskScore = "";
	   String oldNeedsScore = "";
	   String newRiskScore = "";
	   String newNeedsScore = "";
	   Map newScoresMap = new HashMap();
	   
	   List oldQaList = CollectionUtil.iteratorToList(lsirSentToState.getAssessmentQuestionAnswers().iterator());

	   for(int i=0;i < oldQaList.size();i++){
		   AssessmentQuestionAnswer ans = (AssessmentQuestionAnswer) oldQaList.get(i);
		   if("LSIR_Q1".equals(ans.getQuestionId())){
			   if (ans.getAnswerText() != null){
				   oldRiskScore = ans.getAnswerText();
			   }
		   } else {
			   if (ans.getAnswerText() != null){
				   oldNeedsScore = ans.getAnswerText();
			   }
		   }
	   }

	   List newQaList = CollectionUtil.iteratorToList(updateCSAssessmentEvent.getQuestionAnswers().iterator());

	   for(int i=0;i < newQaList.size();i++){
		   CSCAnswer ans = (CSCAnswer) newQaList.get(i);
		   if("LSIR_Q1".equals(ans.getQuestionId())){
			   if (ans.getResponseText() != null){
				   newRiskScore = ans.getResponseText();
			   }
		   } else {
			   if (ans.getResponseText() != null){
				   newNeedsScore = ans.getResponseText();
			   }
		   }
	   }
	   if (!oldRiskScore.equals(newRiskScore)
			   || !oldNeedsScore.equals(newNeedsScore)){
		   newScoresMap.put(NEEDS_SCORE, newNeedsScore);
		   newScoresMap.put(RISK_SCORE, newRiskScore);
	   }

	return newScoresMap;
}
/**
    * @param event
    * @roseuid 479101C30038
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 479101C3003A
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 479101C30046
    */
   public void update(Object anObject) 
   {
    
   }
}
