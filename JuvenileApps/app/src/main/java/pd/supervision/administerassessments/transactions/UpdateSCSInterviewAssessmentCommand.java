/*
 * Created on Jun 24, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerassessments.transactions;

import java.util.List;

import messaging.administerassessments.UpdateCSAssessmentEvent;
import messaging.administerassessments.UpdateSCSInterviewAssessmentEvent;
import messaging.administerassessments.query.GetSCSVersionAssessmentEvent;
import messaging.administerassessments.reply.AssessmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.PDConstants;
import pd.supervision.administerassessments.Assessment;
import pd.supervision.administerassessments.AssessmentHelper;
import pd.supervision.administerassessments.AssessmentQuestionAnswer;
import pd.supervision.administerassessments.ForceFieldAnalysis;
import pd.supervision.administerassessments.SCSInterview;
import pd.supervision.administerassessments.StrategiesForCaseSupervision;

/**
 * @author cc_bjangay
 *
 */
public class UpdateSCSInterviewAssessmentCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
	   UpdateSCSInterviewAssessmentEvent updateSCSInterviewEvent = (UpdateSCSInterviewAssessmentEvent) event;
	       
       UpdateCSAssessmentEvent updateCSAssessmentEvent = (UpdateCSAssessmentEvent) updateSCSInterviewEvent;
       
       //Determine if there is a force field assessment tied to the SCS Interview being updated.
       ForceFieldAnalysis existingForceField = null;
       if (updateSCSInterviewEvent.getScsInterviewId() != null && !PDConstants.BLANK.equals(updateSCSInterviewEvent.getScsInterviewId()))
       {
           SCSInterview scsInterviewToBeUpdated = SCSInterview.findByOid(updateSCSInterviewEvent.getScsInterviewId());
           existingForceField = scsInterviewToBeUpdated.getForceFieldAnalysis();
       }
       else
       {
    	   existingForceField = AssessmentHelper.getMostRecentForceField(updateSCSInterviewEvent.getDefendantId());
       }
       
       Assessment newAssessment = Assessment.create(updateCSAssessmentEvent);

       SCSInterview newSCSInterviewAssessment = null;
       List scsAssessList = SCSInterview.findAll("assessmentId", newAssessment.getOID());
       if(scsAssessList!=null && scsAssessList.size()==1)
       {
    	   newSCSInterviewAssessment = (SCSInterview)scsAssessList.get(0);
    	   
    	   if(newAssessment.getVersionNum()==1)
           {
        	   StrategiesForCaseSupervision scsInventory = AssessmentHelper.getMostRecentSCS(newAssessment.getDefendantId());
        	  if(scsInventory == null)
        	  {
        		  newSCSInterviewAssessment.setScsInterviewFrceFld(true);
        	  }
           }
           else if(newAssessment.getVersionNum()>1)
           {
        	   GetSCSVersionAssessmentEvent versionQueryEvt = new GetSCSVersionAssessmentEvent();
        	   versionQueryEvt.setDefendantId(updateSCSInterviewEvent.getDefendantId());
        	   versionQueryEvt.setVersionNumber(newAssessment.getVersionNum()-1);
        	   List scsIntervwAssessmentList = SCSInterview.findAll(versionQueryEvt);
			   if(scsIntervwAssessmentList != null && scsIntervwAssessmentList.size()>0)
			   {
				   SCSInterview previousSCSIntrvwAssess = (SCSInterview)scsIntervwAssessmentList.get(0);
				   newSCSInterviewAssessment.setScsInterviewFrceFld(previousSCSIntrvwAssess.isScsInterviewFrceFld());
			   }
           }
       }
       else
       {
    	   newSCSInterviewAssessment = new SCSInterview();
    	   newSCSInterviewAssessment.setMasterAssessmentId(newAssessment.getMasterAssessmentId());
    	   newSCSInterviewAssessment.setAssessmentId(newAssessment.getOID());
    	   newSCSInterviewAssessment.setScsInterviewFrceFld(false);
           
           if(newAssessment.getVersionNum()==1)
           {
        	   StrategiesForCaseSupervision scsInventory = AssessmentHelper.getMostRecentSCS(newAssessment.getDefendantId());
        	  if(scsInventory == null)
        	  {
        		  newSCSInterviewAssessment.setScsInterviewFrceFld(true);
        	  }
           }
           else if(newAssessment.getVersionNum()>1)
           {
        	   GetSCSVersionAssessmentEvent versionQueryEvt = new GetSCSVersionAssessmentEvent();
        	   versionQueryEvt.setDefendantId(updateSCSInterviewEvent.getDefendantId());
        	   versionQueryEvt.setVersionNumber(newAssessment.getVersionNum()-1);
        	   List scsIntervwAssessmentList = SCSInterview.findAll(versionQueryEvt);
			   if(scsIntervwAssessmentList != null && scsIntervwAssessmentList.size()>0)
			   {
				   SCSInterview previousSCSIntrvwAssess = (SCSInterview)scsIntervwAssessmentList.get(0);
				   newSCSInterviewAssessment.setScsInterviewFrceFld(previousSCSIntrvwAssess.isScsInterviewFrceFld());
			   }
           }
       }
       
       newSCSInterviewAssessment.setAssessment(newAssessment);
       newSCSInterviewAssessment.bind();
       
       AssessmentResponseEvent are = AssessmentHelper.getAssessmentResponseEvent(newSCSInterviewAssessment.getMasterAssessmentId(), newSCSInterviewAssessment.getOID());
       MessageUtil.postReply(are);
       
     //Copy existing forceField and attach to new version of SCS Interview, if SCS interview assessment date is changed
       if ((!updateCSAssessmentEvent.isAssessmentIncomplete()) && (existingForceField != null))
       {
    	   boolean isForceFieldAssociated = newSCSInterviewAssessment.isScsInterviewFrceFld();
    	   
    	   if(isForceFieldAssociated)
    	   {
    		   int newVersionNum = newAssessment.getVersionNum();
    		   if(newVersionNum>1)
    		   {
    			   GetSCSVersionAssessmentEvent versionQueryEvt = new GetSCSVersionAssessmentEvent();
            	   versionQueryEvt.setDefendantId(updateSCSInterviewEvent.getDefendantId());
            	   versionQueryEvt.setVersionNumber(newAssessment.getVersionNum()-1);
    			   List scsIntervwAssessmentList = SCSInterview.findAll(versionQueryEvt);
    			   if(scsIntervwAssessmentList != null && scsIntervwAssessmentList.size()>0)
    			   {
    				   SCSInterview previousSCSIntervwAssess = (SCSInterview)scsIntervwAssessmentList.get(0);
    				   int result = DateUtil.compare(newAssessment.getAssessmentDate(), previousSCSIntervwAssess.getAssessmentDate(), DateUtil.DATE_FMT_1);
    				   if(result != 0)
    				   {
    					   this.copyForceField(existingForceField, newSCSInterviewAssessment, newAssessment);
    				   }
    				   else
    				   {
    					   newSCSInterviewAssessment.setForceFieldAnalysisId(existingForceField.getOID());
    				   }
    			   }
    		   }
    	   }
    	 //else attach the OID of existing forceField and to new version of SCS Interview
    	   else
    	   {
    		   newSCSInterviewAssessment.setForceFieldAnalysisId(existingForceField.getOID());
    	   }
       }
	}
	
	
	
	/**
	 * 
	 * @param origForceField
	 * @param newScsInterview
	 * @param newAssess
	 */
	   private void copyForceField(ForceFieldAnalysis origForceField, SCSInterview newScsInterview, Assessment newAssess) {
	       
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
	       newScsInterview.setForceFieldAnalysis(copiedForceField);
	       
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
	   }
	
}
