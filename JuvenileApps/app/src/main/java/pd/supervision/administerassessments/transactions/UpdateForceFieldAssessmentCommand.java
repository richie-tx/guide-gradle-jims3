	//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerassessments\\transactions\\UpdateForceFieldAssessmentCommand.java

package pd.supervision.administerassessments.transactions;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import messaging.administerassessments.UpdateCSAssessmentEvent;
import messaging.administerassessments.UpdateForceFieldAssessmentEvent;
import messaging.administerassessments.reply.AssessmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import naming.CSCAssessmentConstants;
import pd.common.util.StringUtil;
import pd.supervision.administerassessments.Assessment;
import pd.supervision.administerassessments.AssessmentHelper;
import pd.supervision.administerassessments.AssessmentQuestionAnswer;
import pd.supervision.administerassessments.ForceFieldAnalysis;
import pd.supervision.administerassessments.SCSInterview;
import pd.supervision.administerassessments.StrategiesForCaseSupervision;


public class UpdateForceFieldAssessmentCommand implements ICommand 
{
   
   /**
    * @roseuid 4791072F02EE
    */
   public UpdateForceFieldAssessmentCommand() 
   {
    
   }
   

   
   /**
    * @param event
    * @roseuid 479101C2021C
    */
   public void execute(IEvent event) 
   {
       UpdateForceFieldAssessmentEvent updateForceFieldEvent = (UpdateForceFieldAssessmentEvent) event;
       
       UpdateCSAssessmentEvent updateCSAssessmentEvent = (UpdateCSAssessmentEvent) updateForceFieldEvent;
       
       Assessment newAssessment = Assessment.create(updateCSAssessmentEvent);
       
       ForceFieldAnalysis newForceField = null;
       List forceFieldAssessList = ForceFieldAnalysis.findAll("assessmentId", newAssessment.getOID());
       if(forceFieldAssessList!=null && forceFieldAssessList.size()==1)
       {
    	   newForceField = (ForceFieldAnalysis)forceFieldAssessList.get(0);
       }
       else
       {
    	   newForceField = new ForceFieldAnalysis();
    	   newForceField.setMasterAssessmentId(newAssessment.getMasterAssessmentId()); 
    	   newForceField.setAssessmentId(newAssessment.getOID());
    	   
    	   newForceField.bind();
       }
       
       if (updateForceFieldEvent.getScsAssessId() != null)
       {
    	 //Update the previously "copied" force field OID.  User chose to progress to forceField create immediately after SCS.
    	   StrategiesForCaseSupervision latestCreatedScsAssessment = StrategiesForCaseSupervision.findByOid(updateForceFieldEvent.getScsAssessId());
    	   latestCreatedScsAssessment.setForceFieldAnalysisId(newForceField.getOID());
       }
       else
       {
//    	   Copy the most recent SCS and associate it to newly created ForceField
    	   StrategiesForCaseSupervision	mostRecentSCS = AssessmentHelper.getMostRecentSCS(newAssessment.getDefendantId());
    	   
    	   if(mostRecentSCS != null)
    	   {
    		   if(mostRecentSCS.getStatusCd().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_COMPLETE))
    		   {
		    	   if(!StringUtil.isEmpty(mostRecentSCS.getForceFieldAnalysisId()))
		    	   {
		    		   if(!mostRecentSCS.getForceFieldAnalysisId().equalsIgnoreCase(newForceField.getOID()))
		    		   {
		    			   this.copySCSToNewForceField(mostRecentSCS, newForceField.getOID());
		    		   }
		    	   }
		    	   else
		    	   {   
		    		   this.copySCSToNewForceField(mostRecentSCS, newForceField.getOID());
		    	   }
    		   }
    		   else
    		   {
    			   mostRecentSCS.setForceFieldAnalysisId(newForceField.getOID());
    		   }
    	   }
       }
       
       if (updateForceFieldEvent.getScsInterviewId() != null)
       {
      	 //Update the previously "copied" force field OID.  User chose to progress to forceField create immediately after SCS Interview.
    	   SCSInterview latestCreatedScsIntrvAssessment = SCSInterview.findByOid(updateForceFieldEvent.getScsAssessId());
    	   latestCreatedScsIntrvAssessment.setForceFieldAnalysisId(newForceField.getOID());
        }
       else
       {
//    	   Copy the most recent SCS Interview and associate it to newly created ForceField
    	   SCSInterview	mostRecentSCSInterview = AssessmentHelper.getMostRecentSCSInterview(newAssessment.getDefendantId());
    	   
    	   if(mostRecentSCSInterview != null)
    	   {
    		   if(mostRecentSCSInterview.getStatusCd().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_COMPLETE))
    		   {
		    	   if(!StringUtil.isEmpty(mostRecentSCSInterview.getForceFieldAnalysisId()))
		    	   {
		    		   if(!mostRecentSCSInterview.getForceFieldAnalysisId().equalsIgnoreCase(newForceField.getOID()))
		    		   {
		    			   this.copySCSInterviewToNewForceField(mostRecentSCSInterview, newForceField.getOID());
		    		   }
		    	   }
		    	   else
		    	   {   
		    		   this.copySCSInterviewToNewForceField(mostRecentSCSInterview, newForceField.getOID());
		    	   }
    		   }
    		   else
    		   {
    			   mostRecentSCSInterview.setForceFieldAnalysisId(newForceField.getOID());
    		   }
    	   }
       }
       
       AssessmentResponseEvent are = AssessmentHelper.getAssessmentResponseEvent(newForceField.getMasterAssessmentId(), newForceField.getOID());
       MessageUtil.postReply(are);
   }
   
   
   /**
    * 
    * @param originalSCS
    * @param newFordeField
    */
   private void copySCSToNewForceField(StrategiesForCaseSupervision originalSCS, String newForceFieldId)
   {
//   	create Assessment
   		UpdateCSAssessmentEvent updateCSAssessEvt = new UpdateCSAssessmentEvent();
   		updateCSAssessEvt.setAssessmentDate(originalSCS.getAssessmentDate());
   		updateCSAssessEvt.setDefendantId(originalSCS.getDefendantId());
   		updateCSAssessEvt.setAssessmentTypeCd(originalSCS.getAssessmentTypeId());
   		updateCSAssessEvt.setMasterAssessmentId(originalSCS.getMasterAssessmentId());
   		
   		Assessment newAssessment = Assessment.create(updateCSAssessEvt);
   		
//   		create SCS
   		StrategiesForCaseSupervision newSCS = new StrategiesForCaseSupervision();
   		newSCS.setAssessmentId(newAssessment.getOID());
   		newSCS.setSiTotal(originalSCS.getSiTotal());
   		newSCS.setCcTotal(originalSCS.getCcTotal());
   		newSCS.setEsTotal(originalSCS.getEsTotal());
   		newSCS.setLsTotal(originalSCS.getLsTotal());
   		newSCS.setComments(originalSCS.getComments());
   		newSCS.setPrimaryClassificationId(originalSCS.getPrimaryClassificationId());
   		newSCS.setSecondaryClassificationId(originalSCS.getSecondaryClassificationId());
   		newSCS.setScsInventoryFrceFld(originalSCS.isScsInventoryFrceFld());
   		newSCS.bind();
   		
//   		copy all question/answers
   		Assessment origAssessment = originalSCS.getAssessment();
   		Collection questionAnswers = origAssessment.getAssessmentQuestionAnswers();
   		if(questionAnswers != null)
   		{
   			Iterator questionAnsIt = questionAnswers.iterator();
   			while(questionAnsIt.hasNext())
   			{
   				AssessmentQuestionAnswer origQA = (AssessmentQuestionAnswer)questionAnsIt.next();
   				AssessmentQuestionAnswer newQA = new AssessmentQuestionAnswer();
   				newQA.setAssessmentId(newAssessment.getOID());
   				newQA.setQuestionGroupId(origQA.getQuestionGroupId());
   				newQA.setQuestionId(origQA.getQuestionId());
   				newQA.setAnswerText(origQA.getAnswerText());
   				newQA.setAnswerId(origQA.getAnswerId());
   				newAssessment.insertAssessmentQuestionAnswers(newQA);
   			}
   		}
   		
   		newSCS.setForceFieldAnalysisId(newForceFieldId);
   }
   
   
  
   /**
    * 
    * @param originalSCS
    * @param newFordeField
    */
   private void copySCSInterviewToNewForceField(SCSInterview originalSCSIntervw, String newForceFieldId)
   {
//   	create Assessment
   		UpdateCSAssessmentEvent updateCSAssessEvt = new UpdateCSAssessmentEvent();
   		updateCSAssessEvt.setAssessmentDate(originalSCSIntervw.getAssessmentDate());
   		updateCSAssessEvt.setDefendantId(originalSCSIntervw.getDefendantId());
   		updateCSAssessEvt.setAssessmentTypeCd(originalSCSIntervw.getAssessmentTypeId());
   		updateCSAssessEvt.setMasterAssessmentId(originalSCSIntervw.getMasterAssessmentId());
   		
   		Assessment newAssessment = Assessment.create(updateCSAssessEvt);
   		
//   		create SCS
   		SCSInterview newSCSInterview = new SCSInterview();
   		newSCSInterview.setAssessmentId(newAssessment.getOID());
   		newSCSInterview.setScsInterviewFrceFld(originalSCSIntervw.isScsInterviewFrceFld());
   		newSCSInterview.bind();
   		
//   		copy all question/answers
   		Assessment origAssessment = newSCSInterview.getAssessment();
   		Collection questionAnswers = origAssessment.getAssessmentQuestionAnswers();
   		if(questionAnswers != null)
   		{
   			Iterator questionAnsIt = questionAnswers.iterator();
   			while(questionAnsIt.hasNext())
   			{
   				AssessmentQuestionAnswer origQA = (AssessmentQuestionAnswer)questionAnsIt.next();
   				AssessmentQuestionAnswer newQA = new AssessmentQuestionAnswer();
   				newQA.setAssessmentId(newAssessment.getOID());
   				newQA.setQuestionGroupId(origQA.getQuestionGroupId());
   				newQA.setQuestionId(origQA.getQuestionId());
   				newQA.setAnswerText(origQA.getAnswerText());
   				newQA.setAnswerId(origQA.getAnswerId());
   				newAssessment.insertAssessmentQuestionAnswers(newQA);
   			}
   		}
   		
   		newSCSInterview.setForceFieldAnalysisId(newForceFieldId);
   }
   
   
   
   
   
   
   

/**
    * @param event
    * @roseuid 479101C20229
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 479101C2022B
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 479101C2022D
    */
   public void update(Object anObject) 
   {
    
   }
}
