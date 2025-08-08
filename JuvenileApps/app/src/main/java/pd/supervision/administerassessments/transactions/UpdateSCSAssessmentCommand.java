//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerassessments\\transactions\\UpdateSCSAssessmentCommand.java

package pd.supervision.administerassessments.transactions;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import messaging.administerassessments.UpdateCSAssessmentEvent;
import messaging.administerassessments.UpdateSCSAssessmentEvent;
import messaging.administerassessments.query.GetNewestAssessmentEvent;
import messaging.administerassessments.query.GetSCSVersionAssessmentEvent;
import messaging.administerassessments.reply.AssessmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CSCAssessmentConstants;
import naming.PDConstants;
import pd.common.util.StringUtil;
import pd.supervision.administerassessments.Assessment;
import pd.supervision.administerassessments.AssessmentHelper;
import pd.supervision.administerassessments.AssessmentQuestionAnswer;
import pd.supervision.administerassessments.ForceFieldAnalysis;
import pd.supervision.administerassessments.LSIR;
import pd.supervision.administerassessments.SCSInterview;
import pd.supervision.administerassessments.StrategiesForCaseSupervision;
import pd.supervision.csts.CSTSHelper;


public class UpdateSCSAssessmentCommand implements ICommand 
{
   
   /**
    * @roseuid 47910730007D
    */
   public UpdateSCSAssessmentCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 479101BF0131
    */
   public void execute(IEvent event) 
   {
       UpdateSCSAssessmentEvent updateSCSEvent = (UpdateSCSAssessmentEvent) event;
       
       UpdateCSAssessmentEvent updateCSAssessmentEvent = (UpdateCSAssessmentEvent) updateSCSEvent;
       
       boolean sentToState = false;
       StrategiesForCaseSupervision scsSentToState = null;
       
       if (!updateSCSEvent.isAssessmentIncomplete()){
    	   //Determine if assessment T22 has already been sent to state.
    	   GetNewestAssessmentEvent newestScsEvt = new GetNewestAssessmentEvent();
    	   newestScsEvt.setMasterAssessmentId(updateSCSEvent.getMasterAssessmentId());
    	   List assessmentList = StrategiesForCaseSupervision.findAll(newestScsEvt);
    	   
    	   if (assessmentList != null && assessmentList.size() > 0){
    		   scsSentToState = (StrategiesForCaseSupervision) assessmentList.get(0);
    		   sentToState = AssessmentHelper.hasCSTSRecordBeenSentToState((Assessment) scsSentToState);
    		   if (!sentToState){
    			   scsSentToState = null;
    		   }
    	   }
       }
       
       //Determine if there is a force field assessment tied to the SCS being updated.
       ForceFieldAnalysis existingForceField = null;
       if (updateSCSEvent.getScsId() != null && !PDConstants.BLANK.equals(updateSCSEvent.getScsId())){
           StrategiesForCaseSupervision scsToBeUpdated = StrategiesForCaseSupervision.findByOid(updateSCSEvent.getScsId());
           existingForceField = scsToBeUpdated.getForceFieldAnalysis();
       }
       else
       {
    	   existingForceField = AssessmentHelper.getMostRecentForceField(updateSCSEvent.getDefendantId());
       }
       
       Assessment newAssessment = Assessment.create(updateCSAssessmentEvent);

       StrategiesForCaseSupervision newSCSAssessment = null;
       List scsAssessList = StrategiesForCaseSupervision.findAll("assessmentId", newAssessment.getOID());
       if(scsAssessList!=null && scsAssessList.size()==1)
       {
    	   newSCSAssessment = (StrategiesForCaseSupervision)scsAssessList.get(0);
    	   
    	   if(newAssessment.getVersionNum()==1)
           {
        	  SCSInterview scsInterview = AssessmentHelper.getMostRecentSCSInterview(newAssessment.getDefendantId());
        	  if(scsInterview == null)
        	  {
        		  newSCSAssessment.setScsInventoryFrceFld(true);
        	  }
           }
           else if(newAssessment.getVersionNum()>1)
           {
        	   GetSCSVersionAssessmentEvent versionQueryEvt = new GetSCSVersionAssessmentEvent();
        	   versionQueryEvt.setDefendantId(updateSCSEvent.getDefendantId());
        	   versionQueryEvt.setVersionNumber(newAssessment.getVersionNum()-1);
        	   List scsAssessmentList = StrategiesForCaseSupervision.findAll(versionQueryEvt);
			   if(scsAssessmentList != null && scsAssessmentList.size()>0)
			   {
				   StrategiesForCaseSupervision previousSCSAssess = (StrategiesForCaseSupervision)scsAssessmentList.get(0);
				   newSCSAssessment.setScsInventoryFrceFld(previousSCSAssess.isScsInventoryFrceFld());
			   }
           }
       }
       else
       {
    	   newSCSAssessment = new StrategiesForCaseSupervision();
    	   newSCSAssessment.setMasterAssessmentId(newAssessment.getMasterAssessmentId());
           newSCSAssessment.setAssessmentId(newAssessment.getOID());
           newSCSAssessment.setScsInventoryFrceFld(false);
           
           if(newAssessment.getVersionNum()==1)
           {
        	  SCSInterview scsInterview = AssessmentHelper.getMostRecentSCSInterview(newAssessment.getDefendantId());
        	  if(scsInterview == null)
        	  {
        		  newSCSAssessment.setScsInventoryFrceFld(true);
        	  }
           }
           else if(newAssessment.getVersionNum()>1)
           {
        	   GetSCSVersionAssessmentEvent versionQueryEvt = new GetSCSVersionAssessmentEvent();
        	   versionQueryEvt.setDefendantId(updateSCSEvent.getDefendantId());
        	   versionQueryEvt.setVersionNumber(newAssessment.getVersionNum()-1);
        	   List scsAssessmentList = StrategiesForCaseSupervision.findAll(versionQueryEvt);
			   if(scsAssessmentList != null && scsAssessmentList.size()>0)
			   {
				   StrategiesForCaseSupervision previousSCSAssess = (StrategiesForCaseSupervision)scsAssessmentList.get(0);
				   newSCSAssessment.setScsInventoryFrceFld(previousSCSAssess.isScsInventoryFrceFld());
			   }
           }
       }

       newSCSAssessment.setSiTotal(updateSCSEvent.getSiTotal());
       newSCSAssessment.setCcTotal(updateSCSEvent.getCcTotal());
       newSCSAssessment.setEsTotal(updateSCSEvent.getEsTotal());
       newSCSAssessment.setLsTotal(updateSCSEvent.getLsTotal());
       newSCSAssessment.setComments(updateSCSEvent.getComments());
       newSCSAssessment.setPrimaryClassificationId(updateSCSEvent.getPrimaryClassCd());
       newSCSAssessment.setSecondaryClassificationId(updateSCSEvent.getSecondaryClassCd());
       newSCSAssessment.setAssessment(newAssessment);
       newSCSAssessment.bind();
       
       AssessmentResponseEvent are = AssessmentHelper.getAssessmentResponseEvent(newSCSAssessment.getMasterAssessmentId(), newSCSAssessment.getOID());
       MessageUtil.postReply(are);
       
       if ((!updateCSAssessmentEvent.isAssessmentIncomplete()) && (existingForceField != null))
       {
    	   boolean isForceFieldAssociated = newSCSAssessment.isScsInventoryFrceFld();
    	   
    	 //Copy existing forceField and attach to new version of SCS, if SCS assessment date is changed
    	   if(isForceFieldAssociated)
    	   {
    		   int newVersionNum = newAssessment.getVersionNum();
    		   if(newVersionNum>1)
    		   {
    			   GetSCSVersionAssessmentEvent versionQueryEvt = new GetSCSVersionAssessmentEvent();
            	   versionQueryEvt.setDefendantId(updateSCSEvent.getDefendantId());
            	   versionQueryEvt.setVersionNumber(newAssessment.getVersionNum()-1);
    			   List scsAssessmentList = StrategiesForCaseSupervision.findAll(versionQueryEvt);
    			   if(scsAssessmentList != null && scsAssessmentList.size()>0)
    			   {
    				   StrategiesForCaseSupervision previousSCSAssess = (StrategiesForCaseSupervision)scsAssessmentList.get(0);
    				   int result = DateUtil.compare(newAssessment.getAssessmentDate(), previousSCSAssess.getAssessmentDate(), DateUtil.DATE_FMT_1);
    				   if(result != 0)
    				   {
    					   ForceFieldAnalysis newForceField = this.copyForceField(existingForceField, newSCSAssessment, newAssessment);
    					   
//    					   Copy the most recent SCS Interview and associate it to newly created ForceField
    			    	   SCSInterview	mostRecentSCSInterview = AssessmentHelper.getMostRecentSCSInterview(newAssessment.getDefendantId());
    			    	   
    			    	   if(mostRecentSCSInterview != null)
    			    	   {
    			    		   if(mostRecentSCSInterview.getStatusCd().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_COMPLETE))
    			    		   {
    					    	   if(!StringUtil.isEmpty(mostRecentSCSInterview.getForceFieldAnalysisId()))
    					    	   {
    					    			this.copySCSInterviewToNewForceField(mostRecentSCSInterview, newForceField.getOID());
    					    	   }
    			    		   }
    			    	   }
    				   }
    				   else
    				   {
    					   newSCSAssessment.setForceFieldAnalysisId(existingForceField.getOID());
    				   }
    			   }
    		   }
    	   }
    	 //else attach the OID of existing forceField and to new version of SCS
    	   else
    	   {
    		   newSCSAssessment.setForceFieldAnalysisId(existingForceField.getOID());
    	   }
       }
      
       if (!updateSCSEvent.isAssessmentIncomplete()){
    	   if (sentToState 
    			   && !scsSentToState.getAssessmentDate().equals(updateSCSEvent.getAssessmentDate())){ 
     			 if (!scsSentToState.getPrimaryClassificationId().equals(updateSCSEvent.getPrimaryClassCd())){
    				 //Create record with original date and new primary classification
     				 scsSentToState.setPrimaryClassificationId(updateSCSEvent.getPrimaryClassCd());
    				 CSTSHelper.createCSTS(scsSentToState);
    				 //Do not update assessment. Object was updated for CSTS record only.
    				 TransactionManager tm = TransactionManager.getInstance();
    				 tm.removeUpdated(scsSentToState);
   				 } 
				 AssessmentHelper.createTaskToCSTSWorkgroup(
						 updateSCSEvent.getDefendantId(),
						 FALSE,
						 T22,
						 scsSentToState.getAssessmentDate(),
						 updateSCSEvent.getAssessmentDate());
    	   } else {
    		   CSTSHelper.createCSTS(newSCSAssessment);
    	   }
       }

   }
   
   private static final String T22 = "T22";
   private static final boolean FALSE = false;
   
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
    * 
    * @param origForceField
    * @param newScs
    * @param newAssess
    */
   private ForceFieldAnalysis copyForceField(ForceFieldAnalysis origForceField, StrategiesForCaseSupervision newScs, Assessment newAssess) {
       
       UpdateCSAssessmentEvent ffUpdateEvent = new UpdateCSAssessmentEvent();
       ffUpdateEvent.setAssessmentDate(newAssess.getAssessmentDate());
       ffUpdateEvent.setAssessmentTypeCd(origForceField.getAssessmentTypeId());
       ffUpdateEvent.setDefendantId(origForceField.getDefendantId());
       ffUpdateEvent.setMasterAssessmentId(origForceField.getMasterAssessmentId());
       
       //Create new Assessment
       Assessment newAssessment = Assessment.create(ffUpdateEvent);

       ForceFieldAnalysis copiedForceField = new ForceFieldAnalysis();
       copiedForceField.setAssessmentId(newAssessment.getOID());
       
       //Attach copied force field to new SCS
       newScs.setForceFieldAnalysis(copiedForceField);
       
       //Retrieve answers to original force field
       Assessment origAssessment = origForceField.getAssessment();
       Assessment.copyAnswers(origAssessment, newAssessment);
       List qaList = CollectionUtil.iteratorToList(origAssessment.getAssessmentQuestionAnswers().iterator());
       AssessmentQuestionAnswer aQa = null;
       AssessmentQuestionAnswer newQa =  null;
       
       //Attach answers from original force field to new copy.
       for (int i = 0; i < qaList.size(); i++) {
           aQa = (AssessmentQuestionAnswer) qaList.get(i);
           newQa = new AssessmentQuestionAnswer();
           newQa.setAnswerId(aQa.getAnswerId());
           newQa.setAnswerText(aQa.getAnswerText());
           newQa.setQuestionGroupId(aQa.getQuestionGroupId());
           newQa.setQuestionId(aQa.getQuestionId());
           newAssessment.insertAssessmentQuestionAnswers(newQa);
       }
       
       return copiedForceField;
   }

   
   
   /**
    * @param event
    * @roseuid 479101BF0133
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 479101BF013F
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 479101BF0141
    */
   public void update(Object anObject) 
   {
    
   }
}
