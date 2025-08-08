// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerassessments\\transactions\\GetAssessmentDetailsCommand.java

package pd.supervision.administerassessments.transactions;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import naming.CSCAssessmentConstants;

import pd.supervision.administerassessments.Assessment;
import pd.supervision.administerassessments.AssessmentHelper;
import pd.supervision.administerassessments.AssessmentQuestionAnswer;
import pd.supervision.administerassessments.Assessments;
import pd.supervision.administerassessments.ForceFieldAnalysis;
import pd.supervision.administerassessments.LSIR;
import pd.supervision.administerassessments.SCSInterview;
import pd.supervision.administerassessments.StrategiesForCaseSupervision;
import pd.supervision.administerassessments.Wisconsin;
import pd.supervision.administercaseload.SupervisionLevelOfServiceCode;
import messaging.administerassessments.GetAssessmentDetailsEvent;
import messaging.administerassessments.reply.AssessmentDetailsResponseEvent;
import messaging.administerassessments.reply.CSCQuestionAnswerResponseEvent;
import messaging.administerassessments.reply.CSCQuestionGroupResponseEvent;
import messaging.administerassessments.reply.CSCQuestionResponseEvent;
import messaging.administerassessments.reply.PriorAssessmentVersionResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;

public class GetAssessmentDetailsCommand implements ICommand {

    /**
     * @roseuid 4791072F0109
     */
    public GetAssessmentDetailsCommand() {

    }

    /**
     * @param qGroupList
     * @param assessmentResponsesMap
     */
    private void applyActualAnswersToQuestions(List qGroupList, Map assessmentResponsesMap) {
        
        CSCQuestionGroupResponseEvent qGroupRe = null;
        CSCQuestionResponseEvent qRe = null;
        AssessmentQuestionAnswer actualAnswer = null;
        List questionReList = null;
        Map aQaMap = null;
        
        for (int i = 0; i < qGroupList.size(); i++) {
            qGroupRe = (CSCQuestionGroupResponseEvent) qGroupList.get(i);
            questionReList = CollectionUtil.iteratorToList(qGroupRe.getQuestionResponseEvents().iterator());
            
            for (int j = 0; j < questionReList.size(); j++) {
                qRe = (CSCQuestionResponseEvent) questionReList.get(j);
                aQaMap = (Map) assessmentResponsesMap.get(qGroupRe.getId()); 
                if (aQaMap != null){
                	actualAnswer = (AssessmentQuestionAnswer) aQaMap.get(qRe.getId());
                	if (actualAnswer != null){
                		qRe.setSelectedResponseId(actualAnswer.getAnswerId());
                		qRe.setSelectedResponseText(actualAnswer.getAnswerText());
                	}
                }
            }
        }
        
    }

    /**
     * @param event
     * @roseuid 479101BA00D5
     */
    public void execute(IEvent event) {
        
        GetAssessmentDetailsEvent reqEvent = (GetAssessmentDetailsEvent) event;

        String fileName = AssessmentHelper.getAssessmentFileName(reqEvent.getAssessmentTypeId(), reqEvent.isInitial());
        CSCQuestionAnswerResponseEvent qre = AssessmentHelper.getQuestions(fileName);
        
        if (qre != null) {
            Collection qGroupRes = qre.getQuestionGroupResponseEvents();
            if (reqEvent.isAssessmentCreate()) {
                this.postQuestionResponses(null, qGroupRes);
            } else {
                this.processQuestionsAndAnswers(qGroupRes, reqEvent);
            }
        }
    }

    /**
     * @param answerList
     * @return
     */
    private Map getAnswerMap(List answerList) {
        
       Map questionGroupMap = new HashMap();
       AssessmentQuestionAnswer aQa = null;
       Map questionMap = null;
       
       for (int i = 0; i < answerList.size(); i++) {
           aQa = (AssessmentQuestionAnswer) answerList.get(i);
           if (questionGroupMap.get(aQa.getQuestionGroupId()) != null){
               questionMap = (Map) questionGroupMap.get(aQa.getQuestionGroupId());
           } else {
               questionMap = new HashMap();
               questionGroupMap.put(aQa.getQuestionGroupId(), questionMap);
           }
           questionMap.put(aQa.getQuestionId(), aQa);
       }
       
       return questionGroupMap;
    }

    /**
     * @param lsir
     * @return
     */
    private AssessmentDetailsResponseEvent getLSIRDetails(LSIR lsir) {
        AssessmentDetailsResponseEvent detailsRe = new AssessmentDetailsResponseEvent();
        detailsRe.setComments(lsir.getComments());
        return detailsRe;
    }

    /**
     * @param scs
     * @return
     */
    private AssessmentDetailsResponseEvent getSCSDetails(StrategiesForCaseSupervision scs) {
        AssessmentDetailsResponseEvent detailsRe = new AssessmentDetailsResponseEvent();
        detailsRe.setComments(scs.getComments());
        detailsRe.setForceFieldId(scs.getForceFieldAnalysisId());
        detailsRe.setTotalSI(new Integer(scs.getSiTotal()).toString());
        detailsRe.setTotalCC(new Integer(scs.getCcTotal()).toString());
        detailsRe.setTotalES(new Integer(scs.getEsTotal()).toString());
        detailsRe.setTotalLS(new Integer(scs.getLsTotal()).toString());
        detailsRe.setPrimaryClassification(scs.getPrimaryClassificationId());
        detailsRe.setSecondaryClassification(scs.getSecondaryClassificationId());
        return detailsRe;
    }
    
    
    
    /**
     * @param scs
     * @return
     */
    private AssessmentDetailsResponseEvent getSCSInterviewDetails(SCSInterview scsInterview) {
        AssessmentDetailsResponseEvent detailsRe = new AssessmentDetailsResponseEvent();
        detailsRe.setForceFieldId(scsInterview.getForceFieldAnalysisId());
        return detailsRe;
    }
    
    

    /**
     * @param wisconsin
     * @return
     */
    private AssessmentDetailsResponseEvent getWisconsinDetails(Wisconsin wisconsin) {
        AssessmentDetailsResponseEvent detailsRe = new AssessmentDetailsResponseEvent();
        detailsRe.setRiskScore(new Integer(wisconsin.getTotalRiskScore()).toString());
        detailsRe.setRiskLevel(new Integer(wisconsin.getRiskLevel()).toString());
        detailsRe.setNeedsScore(new Integer(wisconsin.getTotalNeedsScore()).toString());
        detailsRe.setNeedsLevel(new Integer(wisconsin.getNeedsLevel()).toString());
        SupervisionLevelOfServiceCode losCode = wisconsin.getSuggestedLosCd();
        if (losCode != null){
            detailsRe.setLevelOfSupervision(losCode.getLevelOfServiceLegacyCode());
        }
        return detailsRe;
    }

    /**
     * @param event
     * @roseuid 479101BA00E1
     */
    public void onRegister(IEvent event) {

    }

    /**
     * @param event
     * @roseuid 479101BA00E3
     */
    public void onUnregister(IEvent event) {

    }

    /**
     * @param assessment
     */
    private void postPriorVersionResponses(Assessment assessment) {
        
        String assessmentId = null;

        if (assessment.getMasterAssessmentId() != null){
            assessmentId = assessment.getMasterAssessmentId();
        } else {
            assessmentId = assessment.getOID();
        }
        
        List assessments = Assessments.findAll("masterAssessmentId", assessmentId);
        
        PriorAssessmentVersionResponseEvent pVerRe = null;
        Assessments pVersAssessment = null;
        
        if (assessments != null && assessments.size() > 0){
            
            for (int i = 0; i < assessments.size(); i++) {
                pVersAssessment = (Assessments) assessments.get(i);
                pVerRe = new PriorAssessmentVersionResponseEvent();
                pVerRe.setAssessmentDate(pVersAssessment.getAssessmentDate());
                
                assessmentId = this.getActualAssessmentId(pVersAssessment); 
                
                pVerRe.setAssessmentId(assessmentId);
                pVerRe.setTransactionDate(pVersAssessment.getTransactionDate());
                pVerRe.setVersionNumber(new Integer(pVersAssessment.getVersionNum()).toString());
                
                MessageUtil.postReply(pVerRe);
            }
        }
        
        
    }

    /**
     * @param assessment
     * @return
     */
    private String getActualAssessmentId(Assessments assessment) {
        
        String assessmentId = null;
        if (assessment.getAssessmentTypeId().equals(CSCAssessmentConstants.ASSESSMENTTYPE_FORCEFIELD)){
            assessmentId = assessment.getForceFieldId();
        } else if (assessment.getAssessmentTypeId().equals(CSCAssessmentConstants.ASSESSMENTTYPE_LSIR)){
            assessmentId = assessment.getLsirId();
        } else if (assessment.getAssessmentTypeId().equals(CSCAssessmentConstants.ASSESSMENTTYPE_SCS)){
            assessmentId = assessment.getScsId();    
        } else if (assessment.getAssessmentTypeId().equals(CSCAssessmentConstants.ASSESSMENTTYPE_SCS_INTERVIEW)){
            assessmentId = assessment.getScsInterviewId();   
        } else {
            assessmentId = assessment.getWisconsinId();
        }
        return assessmentId;
    }

    /**
     * @param qGroupRes
     */
    private void postQuestionResponses(AssessmentDetailsResponseEvent adre, Collection qGroupRes) {
        
    	if(adre == null)
    	{
    		adre = new AssessmentDetailsResponseEvent();
    	}
        adre.setQuestionAnswerList(qGroupRes);
       
        MessageUtil.postReply(adre);
    }

    /**
     * @param qgres
     * @param reqEvent
     */
    private void processQuestionsAndAnswers(Collection qgres, GetAssessmentDetailsEvent reqEvent) {
        
        Assessment assessment = null;
        AssessmentDetailsResponseEvent detailsRe = null;
        
        if (CSCAssessmentConstants.ASSESSMENTTYPE_WISCONSIN.equals(reqEvent.getAssessmentTypeId())){
            Wisconsin wisconsin = Wisconsin.findByOid(reqEvent.getAssessmentId());
            detailsRe = this.getWisconsinDetails(wisconsin);
            assessment = wisconsin.getAssessment();
         } else if (CSCAssessmentConstants.ASSESSMENTTYPE_LSIR.equals(reqEvent.getAssessmentTypeId())){
             LSIR lsir = LSIR.findByOid(reqEvent.getAssessmentId());
             detailsRe = this.getLSIRDetails(lsir);
             assessment = lsir.getAssessment();
         } else if (CSCAssessmentConstants.ASSESSMENTTYPE_SCS.equals(reqEvent.getAssessmentTypeId())){
             StrategiesForCaseSupervision scs = StrategiesForCaseSupervision.findByOid(reqEvent.getAssessmentId());
             detailsRe = this.getSCSDetails(scs);
             assessment = scs.getAssessment();
         } 
         else if (CSCAssessmentConstants.ASSESSMENTTYPE_SCS_INTERVIEW.equals(reqEvent.getAssessmentTypeId())){
        	 SCSInterview scsInterview = SCSInterview.findByOid(reqEvent.getAssessmentId());
             detailsRe = this.getSCSInterviewDetails(scsInterview);
             assessment = scsInterview.getAssessment();
         } 
         else if (CSCAssessmentConstants.ASSESSMENTTYPE_FORCEFIELD.equals(reqEvent.getAssessmentTypeId())){
             ForceFieldAnalysis forceField = ForceFieldAnalysis.findByOid(reqEvent.getAssessmentId());
             assessment = forceField.getAssessment();
         }
        boolean sentToState = AssessmentHelper.hasCSTSRecordBeenSentToState(assessment);
        if (detailsRe == null){
            detailsRe = new AssessmentDetailsResponseEvent();
        }
        detailsRe.setSentToState(sentToState);
         
        List answerList = CollectionUtil.iteratorToList(assessment.getAssessmentQuestionAnswers().iterator());
        Map selectedResponsesMap = this.getAnswerMap(answerList);
        
        List qGroupList = CollectionUtil.iteratorToList(qgres.iterator());
        
        this.applyActualAnswersToQuestions(qGroupList, selectedResponsesMap);

        this.postQuestionResponses(detailsRe, qGroupList);
        
        if (reqEvent.isRetrievePriorVersion()){
            this.postPriorVersionResponses(assessment);
        }
    }

    /**
     * @param updateObject
     * @roseuid 4791072F0128
     */
    public void update(Object updateObject) {

    }
}
