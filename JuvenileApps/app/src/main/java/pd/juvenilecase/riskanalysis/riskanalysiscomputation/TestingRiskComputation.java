package pd.juvenilecase.riskanalysis.riskanalysiscomputation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pd.exception.ComputationValidationException;
import pd.juvenilecase.riskanalysis.RiskFinalScore;
import pd.juvenilecase.riskanalysis.RiskResultGroup;
import messaging.riskanalysis.RiskQuestionAnswerEvent;
import messaging.riskanalysis.SaveTestingAssessmentEvent;
import messaging.riskanalysis.reply.RiskComputationReponseEvent;
import messaging.riskanalysis.reply.TestingRiskComputationReponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
/** 
 * @deprecated
 * @author PAlcocer
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class TestingRiskComputation extends RiskComputation {
	
	private TestingRiskComputationReponseEvent testingRiskCompRespEvent; 
	private SaveTestingAssessmentEvent saveTestingAssessEvent;
	
	//Questions which will be used in formula(s)
	private int question1Weight;
	private int question2Weight;
	private int question3Weight;
	private int question4Weight;
	private int question5Weight;
	private int question6Weight;
	private int question7Weight;
	private int question8Weight;
	private int question9Weight;
	private int question10Weight;
	private int question11Weight;
	private int question12Weight;
	private int question13Weight;
		
	public TestingRiskComputation (SaveTestingAssessmentEvent saveTestingAssessEvent) {
		this.saveTestingAssessEvent = saveTestingAssessEvent; 
		setWeightOfQuestions();
	}
	private RiskFinalScore riskAssessmentScore() {
		RiskFinalScore riskFinalScore = new RiskFinalScore();
		RiskResultGroup riskResultGroup = null;
		
		Iterator<RiskResultGroup> riskResultGroups = RiskResultGroup.findAllByAttributeName("description", "TestingRisk");
		
		while ( riskResultGroups.hasNext() ){
			riskResultGroup = riskResultGroups.next();
		}
		
		if (riskResultGroup != null) {
			riskFinalScore.setRiskResultGroup(riskResultGroup);
		}
		
		riskFinalScore.setRiskAnalysisId(Integer.parseInt(saveTestingAssessEvent.getRiskAnalysisId()));
		riskFinalScore.setFinalScore(finalScore());
		//Explicitly bind to database so that a Risk Final Score OID is generated
		IHome home=new Home();
		home.bind(riskFinalScore);

		return riskFinalScore;
	}	
	/** 
	 * (non-Javadoc)
	 * @see RiskComputation#compute()
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public RiskComputationReponseEvent compute() throws ComputationValidationException {
		
		validate(saveTestingAssessEvent);
		
		testingRiskCompRespEvent = new TestingRiskComputationReponseEvent();
		testingRiskCompRespEvent.setTotalAbuseHistory(question6Weight);
		testingRiskCompRespEvent.setTotalAppearence(question9Weight);
		testingRiskCompRespEvent.setTotalBehviorHistory(question10Weight);
		testingRiskCompRespEvent.setTotalDevelopmental(question4Weight);
		testingRiskCompRespEvent.setTotalFamilyProblems(question12Weight);
		testingRiskCompRespEvent.setTotalFamilyRelationship(question5Weight);
		testingRiskCompRespEvent.setTotalPeerRelationship(question2Weight);
		testingRiskCompRespEvent.setTotalSchoolAcademic(question1Weight);
		testingRiskCompRespEvent.setTotalSchoolBehavior(question7Weight);
		testingRiskCompRespEvent.setTotalSchoolAttendance(question8Weight);
		testingRiskCompRespEvent.setTotalSelfImage(question3Weight);
		testingRiskCompRespEvent.setTotalSubstance(question11Weight);
		//Risk Calculator was using question 14 for totalStrength. Question 14 does not exist.
		testingRiskCompRespEvent.setTotalStrengths(0); 
		testingRiskCompRespEvent.setTotalScores(finalScores());
		
		return testingRiskCompRespEvent;
	}

	/** 
	 * (non-Javadoc)
	 * @see RiskComputation#validate(IEvent event)
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected void validate(IEvent event) throws ComputationValidationException {
		boolean failed = false;
		
		if (failed) {
			throw new ComputationValidationException("TestingRiskComputation failed validation.");
		}
	}
	
	/* (non-Javadoc)
	 * @see pd.juvenilecase.riskanalysis.riskanalysiscomputation.RiskComputation#finalScore()
	 */
	protected int finalScore() {
		int total = 0;
		// TotalAbuseHistory+TotalAppearance+TotalBehaviorHistory+TotalDevelopmental+TotalEducation+TotalFamilyProblems
		// +TotalRelationship+TotalParentalSupervision+TotalPeerRelationship+TotalSchoolAcademic+TotalSchoolAttendance
		// +TotalSchoolBehavior+TotalSelfImage+TotalSubstance+TotalStrengths
		total = question6Weight + question9Weight + question10Weight
				+ question4Weight + question12Weight
				+ question2Weight + question1Weight
				+ question8Weight + question7Weight + question3Weight
				+ question11Weight + question5Weight + question13Weight;
		return total;
	}
	
	protected List finalScores() {
		List finalScores = new ArrayList();
		finalScores.add(riskAssessmentScore());
		
		return finalScores;	
	}
	
	/* (non-Javadoc)
	 * @see pd.juvenilecase.riskanalysis.riskanalysiscomputation.RiskComputation#setWeightOfQuestions()
	 */
	protected void setWeightOfQuestions() {
		//Enumeration riskQuestionAnswerEvents = saveTestingAssessEvent.getRequests();
		
		//RiskComputationUtil riskComputationUtil = new RiskComputationUtil();
		HashMap hmQuestionWeights = RiskComputationUtil.getQuestionWeights(saveTestingAssessEvent);
				
		Set entries = hmQuestionWeights.entrySet();
		Iterator it = entries.iterator();
        while (it.hasNext()) {
        	Map.Entry entry = (Map.Entry) it.next();
            //System.out.println( "Key: " +  entry.getKey() +  " Value: " + entry.getValue());
            int questionNumber = (Integer)entry.getKey();
            int weight = (Integer)entry.getValue();
            switch(questionNumber) {
				case 1: {
					question1Weight = weight;
					break;
				} case 2: {
					question2Weight = weight;
					break;
				} case 3: {
					question3Weight = weight;
					break;
				} case 4: {
					question4Weight = weight;
					break;
				} case 5: {
					question5Weight = weight;
					break;
	   			} case 6: {
					question6Weight = weight;
					break;
				} case 7: {
					question7Weight = weight;
					break;
				} case 8: {
					question8Weight = weight;
					break;
				} case 9: {
					question9Weight = weight;
					break;
				} case 10: {
					question10Weight = weight;
					break;
				} case 11: {
					question11Weight = weight;
					break;
				} case 12: {
					question12Weight = weight;
					break;
				} case 13: {
					question13Weight = weight;
					break;
				} 
	   		}
		}
	}
	
}