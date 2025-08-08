// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerassessments\\transactions\\CalculateSCSTotalsCommand.java

package pd.supervision.administerassessments.transactions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import naming.CSCAssessmentConstants;

import pd.supervision.administerassessments.AssessmentHelper;
import pd.supervision.administerassessments.StrategiesForCaseScorer;

import messaging.administerassessments.CSCAnswer;
import messaging.administerassessments.CalculateSCSTotalsEvent;
import messaging.administerassessments.reply.CSCPossibleResponseEvent;
import messaging.administerassessments.reply.CSCQuestionAnswerResponseEvent;
import messaging.administerassessments.reply.CSCQuestionGroupResponseEvent;
import messaging.administerassessments.reply.CSCQuestionResponseEvent;
import messaging.administerassessments.reply.SCSTotalsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;

public class CalculateSCSTotalsCommand implements ICommand {
    
    private static final String ASSESSMENT_SCS_CLASSIFICATION_TYPE_CC = "CC";
    private static final String ASSESSMENT_SCS_CLASSIFICATION_TYPE_ES = "ES";
    private static final String ASSESSMENT_SCS_CLASSIFICATION_TYPE_LS = "LS";
    private static final String ASSESSMENT_SCS_CLASSIFICATION_TYPE_SI = "SI";
    private static final String PIPE = "|";
    private static final String TOTAL_ID= "totalId";
    private static final int ZERO = 0;
    private static final int FIFTY = 50;
    
    /**
     * @roseuid 4791072E031C
     */
    public CalculateSCSTotalsCommand() {

    }

    /**
     * @param event
     * @roseuid 479101BE02C6
     */
    public void execute(IEvent event) {
        
        CalculateSCSTotalsEvent reqEvent = (CalculateSCSTotalsEvent) event;

        if (reqEvent.getQuestionAnswers() != null && reqEvent.getQuestionAnswers().size() > ZERO) {
            this.processSCSTotals(CollectionUtil.iteratorToList(reqEvent.getQuestionAnswers().iterator()));
        }

    }

    /**
     * Builds map of all possible answers for each questionGroupId, QuestionId, possibleResponseId combination.
     * @return
     */
    private HashMap getAnswerMap() {
        
        HashMap answerMap = new HashMap();

        CSCQuestionAnswerResponseEvent qare = AssessmentHelper.getQuestions(CSCAssessmentConstants.ASSESSMENTTYPE_SCS, false);
        
        if (qare != null){
            List qGroupList = CollectionUtil.iteratorToList(qare.getQuestionGroupResponseEvents().iterator());
            CSCQuestionGroupResponseEvent qGroup = null;
            CSCQuestionResponseEvent qRe = null;
            List qAnswerList = null;
            List pqAnswerList = null;
            CSCPossibleResponseEvent pqRe = null;
            String aKey = null;
            
            for (int i = ZERO; i < qGroupList.size(); i++) {
                qGroup = (CSCQuestionGroupResponseEvent) qGroupList.get(i);
                qAnswerList = CollectionUtil.iteratorToList(qGroup.getQuestionResponseEvents().iterator());
                
                for (int j = ZERO; j < qAnswerList.size(); j++) {
                    qRe = (CSCQuestionResponseEvent) qAnswerList.get(j);
                    pqAnswerList = CollectionUtil.iteratorToList(qRe.getPossibleResponseEvents().iterator());
                    
                    for (int k = ZERO; k < pqAnswerList.size(); k++) {
                        pqRe = (CSCPossibleResponseEvent) pqAnswerList.get(k);
                        aKey = this.getResponseKey(qGroup.getId(), qRe.getId(), pqRe.getId());
                        answerMap.put(aKey, pqRe.getResponseValue());
                    }
                }
            }
        }
        return answerMap;
    }

    /**
     * Determine answer weight by responseValue.
     * @param responseValue
     * @param scorer
     */
    private int getAnswerWeight(String responseValue, StrategiesForCaseScorer scorer) {
        
 	    char answerChar = responseValue.charAt(ZERO);
 	    int theWeight = ZERO;
 	   
 	    switch (answerChar) {
 	    
        case 'A':
            theWeight = scorer.getAnsA();
            break;
        case 'B':
            theWeight = scorer.getAnsB();
            break;
        case 'C':
            theWeight = scorer.getAnsC();
            break;
        case 'D':
            theWeight = scorer.getAnsD();
            break;
        case 'E':
            theWeight = scorer.getAnsE();
            break;
        case 'F':
            theWeight = scorer.getAnsF();
            break;
        case ' ':
            theWeight = scorer.getNoAns();
            break;
        default:
            theWeight = ZERO;;
        }
 	    
        return theWeight;
    }
    /**
     * Determine answer weight for a given total type.
     * @param totalTypeCd
     * @param answer
     * @param answerMap
     * @param weightMap
     * @return
     */
    private int getAnswerWeightByTotalType(String totalTypeCd, CSCAnswer answer, Map answerMap, Map weightMap){
        
        int theWeight = ZERO;
     	
        String scorerKey = this.getScorerKey(totalTypeCd, answer.getQuestionId());
        
     	StrategiesForCaseScorer scorer = (StrategiesForCaseScorer) weightMap.get(scorerKey);
     	
     	if (scorer != null){
     	    String aKey = this.getResponseKey(answer.getQuestionGroupId(), answer.getQuestionId(), answer.getResponseId());
     	    //Answer will be null if question was not answered.
     	    if (answerMap.get(aKey) != null){
     	        String responseValue = (String) answerMap.get(aKey);
     	        theWeight = this.getAnswerWeight(responseValue, scorer);
     	    }
     	   //Few Blank Answers have weights which affects the scores
     	    else{
    	        String responseValue = " ";
    	        theWeight = this.getAnswerWeight(responseValue, scorer);
    	    }
     	    
     	} 
     	
       return theWeight;
    }
    /**
     * Build map containing all possible totalId, question number combinations.
     * @param totalTypeCd
     */
    private Map getQuestionWeightMap() {
        
        List weights = StrategiesForCaseScorer.findAll();
        Map aMap = new HashMap();
        StrategiesForCaseScorer scorer = null;
        String aKey = null;
        
        for (int i = 0; i < weights.size(); i++) {
            scorer = (StrategiesForCaseScorer) weights.get(i);
            aKey = this.getScorerKey(scorer.getTotalId(), scorer.getQuestionNum());
            aMap.put(aKey, scorer);
       }
        
        return aMap;
    }

    /**
     * Build composite key of groupId, questionId, responseId.
     * @param groupId
     * @param questionId
     * @param responseId
     * @return
     */
    private String getResponseKey(String groupId, String questionId, String responseId) {
        
        StringBuffer aKey = new StringBuffer(groupId);
        aKey.append(PIPE);
        aKey.append(questionId);
        aKey.append(PIPE);
        aKey.append(responseId);

        return aKey.toString();
    }

    /**
     * Build composite key of totalType and question number.
     * @param totalTypeCd
     * @param questionNum
     * @return
     */
    private String getScorerKey(String totalTypeCd, String questionNum) {
        StringBuffer sb = new StringBuffer(totalTypeCd);
        sb.append(PIPE);
        sb.append(questionNum);
        return sb.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event) {
    }

    /**
     * @param list
     */
    private void processSCSTotals(List list) {
        
        CSCAnswer answer = null;
        
        //Initialize each total to 50
        int ccTotal = FIFTY;
        int esTotal = FIFTY;
        int lsTotal = FIFTY;
        int siTotal = FIFTY;
        int theWeight = ZERO;
        
        //Build map containing all SCS answers.
        HashMap answerMap = this.getAnswerMap();
        
        //Build map containing all SCS Scorer weights.
        Map weightMap = this.getQuestionWeightMap();
        
        //Calculate totals by SCS classification type.
        for (int i = ZERO; i < list.size(); i++) {
            
         	answer = (CSCAnswer) list.get(i);
         	
         	theWeight = this.getAnswerWeightByTotalType(ASSESSMENT_SCS_CLASSIFICATION_TYPE_CC, answer, answerMap, weightMap);
         	ccTotal = ccTotal + theWeight;
         	
         	theWeight = this.getAnswerWeightByTotalType(ASSESSMENT_SCS_CLASSIFICATION_TYPE_ES, answer, answerMap, weightMap);
         	esTotal = esTotal + theWeight;
         	
         	theWeight = this.getAnswerWeightByTotalType(ASSESSMENT_SCS_CLASSIFICATION_TYPE_LS, answer, answerMap, weightMap);
         	lsTotal = lsTotal + theWeight;
         	
         	theWeight = this.getAnswerWeightByTotalType(ASSESSMENT_SCS_CLASSIFICATION_TYPE_SI, answer, answerMap, weightMap);
         	siTotal = siTotal + theWeight;
        }
        
        //Create response event
        SCSTotalsResponseEvent re = new SCSTotalsResponseEvent();
        
        Map totalMap = new HashMap();
        totalMap.put(ASSESSMENT_SCS_CLASSIFICATION_TYPE_CC, new Integer(ccTotal));
        totalMap.put(ASSESSMENT_SCS_CLASSIFICATION_TYPE_ES, new Integer(esTotal));
        totalMap.put(ASSESSMENT_SCS_CLASSIFICATION_TYPE_LS, new Integer(lsTotal));
        totalMap.put(ASSESSMENT_SCS_CLASSIFICATION_TYPE_SI, new Integer(siTotal));
        re.setClassificationTypeIdTotalMap(totalMap);
        
        MessageUtil.postReply(re);
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject) {

    }
}
