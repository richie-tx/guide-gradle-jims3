// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerassessments\\transactions\\DeleteAssessmentCommand.java

package pd.supervision.administerassessments.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import messaging.administerassessments.DeleteAssessmentEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import naming.CSCAssessmentConstants;
import pd.supervision.administerassessments.Assessment;
import pd.supervision.administerassessments.ForceFieldAnalysis;
import pd.supervision.administerassessments.SCSInterview;
import pd.supervision.administerassessments.StrategiesForCaseSupervision;
import pd.supervision.administerassessments.Wisconsin;
import pd.supervision.legacyupdates.ILegacyUpdates;
import pd.supervision.legacyupdates.LegacyUpdatesImpl;

public class DeleteAssessmentCommand implements ICommand {

    /**
     * @roseuid 4791072F004E
     */
    public DeleteAssessmentCommand() {
    }

    /**
     * @param event
     * @roseuid 479101C70085
     */
    public void execute(IEvent event) {
        DeleteAssessmentEvent delEvt = (DeleteAssessmentEvent) event;
        
        //Find all assessments with the given masterAssessmentId.
        List pObjsToBeDeleted = Assessment.findAll("masterAssessmentId", delEvt.getMasterAssessmentId());
        
        String assessmentId = null;
        List delIds = new ArrayList();
        Assessment assessment = null;

        if (pObjsToBeDeleted != null && pObjsToBeDeleted.size() > 0) {
            for (int i = 0; i < pObjsToBeDeleted.size(); i++) {
                assessment = (Assessment) pObjsToBeDeleted.get(i);
                delIds.add(assessment.getOID());
            }
            for (int i = 0; i < delIds.size(); i++) {
                assessmentId = (String)delIds.get(i);
                assessment = Assessment.find(assessmentId);
                if (assessment.getAssessmentTypeId().equals(CSCAssessmentConstants.ASSESSMENTTYPE_FORCEFIELD)){
                    this.removeForceFieldFromAssociatedSCS(assessment);
                } else if (assessment.getAssessmentTypeId().equals(CSCAssessmentConstants.ASSESSMENTTYPE_SCS)){
                    this.deleteAssociatedForceField(assessment);
                }
                assessment.delete();
                ILegacyUpdates assessmentHandler = new LegacyUpdatesImpl();
                if (assessment.getAssessmentTypeId().equals(CSCAssessmentConstants.ASSESSMENTTYPE_SCS)){
                	try{
                		assessmentHandler.delete(assessment.getMasterAssessmentId(),"T22",assessment.getDefendantId());
                	}catch(Exception e){
                		e.printStackTrace();
                	}
                } else {
                	try{
                		assessmentHandler.delete(assessment.getMasterAssessmentId(),"T21",assessment.getDefendantId());
                	}catch(Exception e){
                		e.printStackTrace();
                	}                	
                }
            }
        }
        String defendantId = assessment.getDefendantId();
        
        //Find all assessments for given defendant/supervisee in order to determine whether only scheduled Wisconsin is left.
        //Will include previously deleted objects since they will not be deleted from the database until this transaction 
        //is complete.  
        List assessList = Assessment.findAll("defendantId", defendantId);
        for (int i = 0; i < pObjsToBeDeleted.size(); i++) {
            assessment = (Assessment) pObjsToBeDeleted.get(i);
            if (assessList.contains(assessment)){
                assessList.remove(assessment);
            }
        }
        //Delete the scheduled Wisconsin if it is the only assessment left for this supervisee.
        if (assessList.size() == 1){
            assessment = (Assessment) assessList.get(0);
            if (assessment.getAssessmentTypeId().equals(CSCAssessmentConstants.ASSESSMENTTYPE_WISCONSIN)){
                List aList = Wisconsin.findAll("assessmentId", assessment.getOID());
                Wisconsin wisc = (Wisconsin) aList.get(0);
                if (wisc.getIsPending()){
                    assessment.delete();
                } else {
                    //There's a problem.  We should never have anything other than a scheduled wisconsin if all
                    //other assessments have been deleted.
                }
            }
        } else {//If only SCS assessments exist, delete scheduled Wisconsin record
        	Assessment pendingWisconsin = null;
        	boolean wiscOrLSIRAssessmentExists = false;
        	
        	for (int i = 0; i < assessList.size(); i++) {
				assessment =(Assessment) assessList.get(i);
				if (assessment.getAssessmentTypeId().equals(CSCAssessmentConstants.ASSESSMENTTYPE_WISCONSIN)){
					List aList = Wisconsin.findAll("assessmentId", assessment.getOID());
	                Wisconsin wisc = (Wisconsin) aList.get(0);
	                if (wisc.getIsPending()){
	                	pendingWisconsin = assessment;
	                } else { //A Wisconsin assessment exists.  Cannot delete scheduled Wisconsin.
	                	wiscOrLSIRAssessmentExists = true;
	                	break;
	                }
				} else if (assessment.getAssessmentTypeId().equals(CSCAssessmentConstants.ASSESSMENTTYPE_LSIR)){
					wiscOrLSIRAssessmentExists = true;
					break;//An LSIR assessment exists.  Cannot delete scheduled Wisconsin.
				}
					
        	}
        	if (!wiscOrLSIRAssessmentExists && pendingWisconsin != null){
        		pendingWisconsin.delete();
        	}
        }
     }

    /**
     * @param assessment
     */
    private void deleteAssociatedForceField(Assessment assessment) {
        
        List aList = StrategiesForCaseSupervision.findAll("assessmentId", assessment.getOID());
        StrategiesForCaseSupervision scs = (StrategiesForCaseSupervision) aList.get(0);
        ForceFieldAnalysis ff = scs.getForceFieldAnalysis();
        
        if (ff != null){
            ff.delete();
        }
        
    }

    /**
     * @param assessment
     */
    private void removeForceFieldFromAssociatedSCS(Assessment assessment) {
        
        List aList = ForceFieldAnalysis.findAll("assessmentId", assessment.getOID());
        ForceFieldAnalysis forceField = (ForceFieldAnalysis) aList.get(0);
        
        List scsList = StrategiesForCaseSupervision.findAll("forceFieldAnalysisId", forceField.getOID());
        if(scsList!=null && scsList.size()>0)
        {
        	Iterator scsIter = scsList.iterator();
        	while(scsIter.hasNext())
        	{
        		StrategiesForCaseSupervision scs = (StrategiesForCaseSupervision)scsIter.next();
        		scs.setForceFieldAnalysisId(null);
        	}
        }
        List scsInterviewList = SCSInterview.findAll("forceFieldAnalysisId", forceField.getOID());
        if(scsInterviewList!=null && scsInterviewList.size()>0)
        {
        	Iterator scsIter = scsInterviewList.iterator();
        	while(scsIter.hasNext())
        	{
        		SCSInterview scsInterview = (SCSInterview)scsIter.next();
        		scsInterview.setForceFieldAnalysisId(null);
        	}
        }
    }

    /**
     * @param event
     * @roseuid 479101C70094
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 479101C70096
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param anObject
     * @roseuid 479101C70098
     */
    public void update(Object anObject) {

    }
}
