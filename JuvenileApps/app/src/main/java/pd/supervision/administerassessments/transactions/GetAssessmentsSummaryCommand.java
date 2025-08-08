// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerassessments\\transactions\\GetAssessmentsSummaryCommand.java

package pd.supervision.administerassessments.transactions;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import naming.CSCAssessmentConstants;
import pd.security.PDSecurityHelper;
import pd.supervision.administerassessments.AssessmentQuestionAnswer;
import pd.supervision.administerassessments.Assessments;
import pd.supervision.supervisionorder.SupervisionPeriod;
import messaging.administerassessments.GetAssessmentsSummaryEvent;
import messaging.administerassessments.query.GetAssessmentsEvent;
import messaging.administerassessments.reply.AssessmentSummaryResponseEvent;
import messaging.administerassessments.reply.HistoricalAssessmentResponseEvent;
import messaging.supervisionorder.GetPriorSupervisionPeriodsEvent;
import messaging.supervisionorder.reply.SupervisionPeriodResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;

public class GetAssessmentsSummaryCommand implements ICommand {

    private static int ZERO = 0;
    private static String BEGIN_DATE = "BEGIN_DATE";
    private static String END_DATE = "END_DATE";
    private static String RISK_QUESTION_ID = "LSIR_Q1";
    private static String NEEDS_QUESTION_ID = "LSIR_Q2";
    
    
    /**
     * @roseuid 4791072F0251
     */
    public GetAssessmentsSummaryCommand() {
    }

    /**
     * @param event
     * @roseuid 479101C40055
     */
    public void execute(IEvent event) {
        
        GetAssessmentsSummaryEvent reqEvent = (GetAssessmentsSummaryEvent) event;

        StringBuffer defendantId = new StringBuffer(reqEvent.getDefendantId());
        while (defendantId.length() < 8) {
            defendantId.insert(ZERO, ZERO);
        }

        Map dateRangeMap = null;
        Date beginDate = null;
        Date endDate = null;
         
        if (reqEvent.isSearchOnActiveSupervisionPeriod()) {
            dateRangeMap = this.getActiveSupervisionPeriodDateRange(defendantId.toString());
            Map priorSupMap = this.getPriorSupervisionPeriodDateRange(defendantId.toString());
            if (priorSupMap.size() > 0){
                HistoricalAssessmentResponseEvent hare = new HistoricalAssessmentResponseEvent();
                beginDate = (Date) priorSupMap.get(BEGIN_DATE);
                hare.setPriorSupervisionPeriodBeginDate(beginDate);
                endDate = (Date) priorSupMap.get(END_DATE);
                hare.setPriorSupervisionPeriodEndDate(endDate);
                MessageUtil.postReply(hare);
            } else {
                }
        } else {
            dateRangeMap = this.getPriorSupervisionPeriodDateRange(defendantId.toString());
        }
        
        beginDate = (Date) dateRangeMap.get(BEGIN_DATE);
        endDate = (Date) dateRangeMap.get(END_DATE);
        
        if (beginDate != null){
            Calendar cal= Calendar.getInstance();
            cal.setTime(beginDate);
            //Clear out time on begin date of supervision period.  Assessments have time stored as 00:00:00
            cal.set(Calendar.HOUR_OF_DAY,0);
            cal.clear(Calendar.MINUTE);
            cal.clear(Calendar.SECOND);
            cal.clear(Calendar.MILLISECOND);
            beginDate = cal.getTime();

            SupervisionPeriodResponseEvent spre = new SupervisionPeriodResponseEvent();
            
            spre.setSupervisionBeginDate(beginDate);
            spre.setSupervisionEndDate(endDate);
            MessageUtil.postReply(spre);
            
            GetAssessmentsEvent ga = new GetAssessmentsEvent();
            ga.setBeginDate(beginDate);
            ga.setDefendantId(defendantId.toString());
            ga.setEndDate(endDate);
            
            List aList = Assessments.findAll(ga);
            
            this.processAssessments(aList, reqEvent.isSearchOnActiveSupervisionPeriod());
        }
    }

    /**
     * @param aList
     */
    private void processAssessments(List aList, boolean isSearchOnActiveSupervisionPeriod) {
        
        Assessments assessment = null;
        Assessments masterAssessment = null;
        Map assessmentsMap = new HashMap();
        
        //Find most recent version per masterAssessmentId
        for (int i = 0; i < aList.size(); i++) {
            assessment = (Assessments) aList.get(i);
            if (assessment.getMasterAssessmentId() != null){
                 masterAssessment = (Assessments) assessmentsMap.get(assessment.getMasterAssessmentId());
                 if(masterAssessment==null)
                 {
                 	 assessmentsMap.put(assessment.getMasterAssessmentId(), assessment);
                 }
                 else
                if (assessment.getVersionNum() > masterAssessment.getVersionNum()){
                    assessmentsMap.put(assessment.getMasterAssessmentId(), assessment);
                }
            } 
        }        
        List filteredAssessmentList = CollectionUtil.iteratorToList(assessmentsMap.values().iterator());
        
        Map forceFieldMap = this.getForceFieldMap(filteredAssessmentList);
        boolean isForceFieldFound = false;
        if(forceFieldMap.size()>0)
        {
        	isForceFieldFound = true;
        }
        
        AssessmentSummaryResponseEvent asre = null;
        
        //Build and post response events for Wisconsin, LSIR and SCS.  Force Field summary response is not needed since it is
        //attached to the SCS.
        for (int i = 0; i < filteredAssessmentList.size(); i++) {
            
            assessment = (Assessments) filteredAssessmentList.get(i);

            asre = new AssessmentSummaryResponseEvent();
            asre.setAssessmentDate(assessment.getAssessmentDate());
    		asre.setMasterAssessmentId(assessment.getMasterAssessmentId()); 
            asre.setAssessmentTypeId(assessment.getAssessmentTypeId());
            asre.setAssessmentStatusCd(assessment.getStatusCd());
            asre.setMigratedAssessmentDate(assessment.getMigratedAssessmentDate());
            
            if (CSCAssessmentConstants.ASSESSMENTTYPE_WISCONSIN.equals(assessment.getAssessmentTypeId())){
                if (assessment.isWiscIsPending() && !isSearchOnActiveSupervisionPeriod){
                    continue;
                } else {
                    this.processWisconsin(asre, assessment);
                }
            } else if (CSCAssessmentConstants.ASSESSMENTTYPE_LSIR.equals(assessment.getAssessmentTypeId())){
                this.processLSIR(asre, assessment);
            } else if (CSCAssessmentConstants.ASSESSMENTTYPE_SCS.equals(assessment.getAssessmentTypeId())){
            	if(!isForceFieldFound)
            	{
            		if(assessment.isScsInventoryFrceFld() && assessment.getStatusCd().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_COMPLETE))
            		{
	            		AssessmentSummaryResponseEvent ffasre = new AssessmentSummaryResponseEvent();
	            		ffasre.setAssessmentDate(assessment.getAssessmentDate());
	            		ffasre.setMasterAssessmentId(null); 
	            		ffasre.setAssessmentId(null);
	            		ffasre.setAssessmentTypeId(CSCAssessmentConstants.ASSESSMENTTYPE_FORCEFIELD);
	            		ffasre.setScsInventoryForceFld(true);
	            		MessageUtil.postReply(ffasre);
            		}
            	}
            	else
            	{
            		asre.setForceFieldFound(true);
            	}
                this.processSCS(asre, assessment, forceFieldMap);
            }else if (CSCAssessmentConstants.ASSESSMENTTYPE_SCS_INTERVIEW.equals(assessment.getAssessmentTypeId())){
            	if(!isForceFieldFound)
            	{
            		if(assessment.isScsInterviewFrceFld() && assessment.getStatusCd().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_COMPLETE))
            		{
	            		AssessmentSummaryResponseEvent ffasre = new AssessmentSummaryResponseEvent();
	            		ffasre.setAssessmentDate(assessment.getAssessmentDate());
	            		ffasre.setMasterAssessmentId(null); 
	            		ffasre.setAssessmentId(null);
	            		ffasre.setAssessmentTypeId(CSCAssessmentConstants.ASSESSMENTTYPE_FORCEFIELD);
	            		ffasre.setScsInterviewForceFld(true);
	            		MessageUtil.postReply(ffasre);
            		}
            	}
            	else
            	{	
            		asre.setForceFieldFound(true);
            	}
                this.processSCSInterview(asre, assessment, forceFieldMap);
            }else if (CSCAssessmentConstants.ASSESSMENTTYPE_FORCEFIELD.equals(assessment.getAssessmentTypeId())){
                this.processForceField(asre, assessment);
//            	continue;
            }
            MessageUtil.postReply(asre);
        }
    }
	/**
     * @param filteredAssessmentList
     * @return
     */
    private Map getForceFieldMap(List filteredAssessmentList) {
        
        Assessments assessments = null;
        Map forceFieldMap = new HashMap();
        
        for (int i = 0; i < filteredAssessmentList.size(); i++) {
            assessments = (Assessments) filteredAssessmentList.get(i);
            if (assessments.getAssessmentTypeId().equals(CSCAssessmentConstants.ASSESSMENTTYPE_FORCEFIELD)){
                forceFieldMap.put(assessments.getForceFieldId(), assessments);
            }
        }
        
        return forceFieldMap;
    }

    /**
	 * @param asre
	 * @param assessments
	 */
	private void processLSIR(AssessmentSummaryResponseEvent asre, Assessments assessments){
	    
        asre.setAssessmentId(assessments.getLsirId());
        if (assessments.isLsirIReassessment()) {
            asre.setDueDate(assessments.getLsirDueDate());
        } else {
            asre.setInitial(true);
        }
        List qList = AssessmentQuestionAnswer.findAll("assessmentId", assessments.getAssessmentId());
        AssessmentQuestionAnswer aqa = null;
        for (int i = 0; i < qList.size(); i++) {
            aqa = (AssessmentQuestionAnswer) qList.get(i);
            if (aqa.getQuestionId().equals(RISK_QUESTION_ID)){
                asre.setRiskScore(aqa.getAnswerText());
            } else if (aqa.getQuestionId().equals(NEEDS_QUESTION_ID)){
                asre.setNeedsScore(aqa.getAnswerText());
            }
        }
	}
    /**
     * @param asre
     * @param assessments
     * @param forceFieldMap
     */
	private void processSCS(AssessmentSummaryResponseEvent asre, Assessments assessments, Map forceFieldMap){
	    
	    asre.setAssessmentId(assessments.getScsId());
	    asre.setForceFieldAssessmentId(assessments.getScsForceFieldId());
	    asre.setPrimaryClassificationTypeId(assessments.getScsPrimaryClassificationCd());
	    asre.setSecondaryClassificationTypeId(assessments.getScsSecondaryClassificationCd());
	    
	    if (assessments.getScsForceFieldId() != null){
	        Assessments forceField = (Assessments) forceFieldMap.get(assessments.getScsForceFieldId());
	        if(forceField != null){
	        	asre.setForceFieldMasterAssessmentId(forceField.getMasterAssessmentId());
	        	 asre.setForceFieldAssessStatusCd(forceField.getStatusCd());
	        }
	    }
	}
	/**
     * @param asre
     * @param assessments
     * @param forceFieldMap
     */
	private void processSCSInterview(AssessmentSummaryResponseEvent asre, Assessments assessments, Map forceFieldMap){
	    
	    asre.setAssessmentId(assessments.getScsInterviewId());
	    asre.setForceFieldAssessmentId(assessments.getScsIntrvwForceFieldId());
	    
	    if (assessments.getScsIntrvwForceFieldId() != null)
	    {
	        Assessments forceField = (Assessments) forceFieldMap.get(assessments.getScsIntrvwForceFieldId());
	        if(forceField != null)
	        {
	        	asre.setForceFieldMasterAssessmentId(forceField.getMasterAssessmentId());
	        	 asre.setForceFieldAssessStatusCd(forceField.getStatusCd());
	        }
	    }
	}
	
	
	/**
	 * @param asre
	 * @param assessments
	 */
	private void processForceField(AssessmentSummaryResponseEvent asre, Assessments assessments){
        asre.setAssessmentId(assessments.getForceFieldId());
	}
	
	
    /**
     * @param asre
     * @param assessments
     */
    private void processWisconsin(AssessmentSummaryResponseEvent asre, Assessments assessments) {
        
        asre.setAssessmentId(assessments.getWisconsinId());
        asre.setRiskScore(new Integer(assessments.getWiscTotalRiskScore()).toString());
        asre.setNeedsScore(new Integer(assessments.getWiscTotalNeedsScore()).toString());
        
        if (assessments.isWiscIsReassessment()) {
            asre.setDueDate(assessments.getWiscAssessmentDueDate());
        } else if (assessments.isWiscIsPending()){
            asre.setDueDate(assessments.getWiscAssessmentDueDate());
            asre.setAssessmentDate(null);
        } else {
            asre.setInitial(true);
        } 
    }

    /**
     * @param defendantId
     * @return
     */
    private Map getPriorSupervisionPeriodDateRange(String defendantId) {

        GetPriorSupervisionPeriodsEvent theEvent = new GetPriorSupervisionPeriodsEvent();
        theEvent.setSpn(defendantId);

        List aList = CollectionUtil.iteratorToList(SupervisionPeriod.findAll(theEvent));

        SupervisionPeriod sp = null;
        Map dateRangeMap = new HashMap();

        for (int i = 0; i < aList.size(); i++) {
            
            sp = (SupervisionPeriod) aList.get(i);
            if (dateRangeMap.get(BEGIN_DATE) == null){
                dateRangeMap.put(BEGIN_DATE, sp.getSupervisionBeginDate());
                dateRangeMap.put(END_DATE, sp.getSupervisionEndDate());
            } else {
                Date begDate = (Date) dateRangeMap.get(BEGIN_DATE);
                if (sp.getSupervisionBeginDate().before(begDate)){
                    dateRangeMap.put(BEGIN_DATE, sp.getSupervisionBeginDate());
                }
                Date endDate = (Date) dateRangeMap.get(END_DATE);
                if (sp.getSupervisionEndDate().after(endDate)){
                    dateRangeMap.put(END_DATE, sp.getSupervisionEndDate());
                }
            }
        }

        return dateRangeMap;
    }

    /**
     * @param defendantId
     * @return
     */
    private Map getActiveSupervisionPeriodDateRange(String defendantId) {

        SupervisionPeriod sp = SupervisionPeriod.findActiveSupervisionPeriod(
                defendantId, PDSecurityHelper.getUserAgencyId());

        Map dateRangeMap = new HashMap();
        
        if (sp != null) {
            dateRangeMap.put(BEGIN_DATE, sp.getSupervisionBeginDate());
            dateRangeMap.put(END_DATE, new Date());
        }
        return dateRangeMap;
    }

    /**
     * @param event
     * @roseuid 479101C40057
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 479101C40059
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param anObject
     * @roseuid 479101C40064
     */
    public void update(Object anObject) {

    }
}
