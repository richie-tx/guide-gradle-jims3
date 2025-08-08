package pd.juvenilecase.riskanalysis.riskanalysiscomputation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import pd.exception.ComputationValidationException;
import pd.juvenilecase.riskanalysis.RiskFinalScore;
import pd.juvenilecase.riskanalysis.RiskResultGroup;
import messaging.riskanalysis.SaveResidentialAssessmentEvent;
import messaging.riskanalysis.reply.RiskComputationReponseEvent;
import messaging.riskanalysis.reply.ResidentialRiskComputationReponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
/** 
 * @deprecated
 * @author PAlcocer
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class ResidentialRiskComputation extends RiskComputation {
	
	private ResidentialRiskComputationReponseEvent residentialRiskCompRespEvent; 
	private SaveResidentialAssessmentEvent saveResidentialAssessEvent;
	private HashMap questionWeightsMap;
	//Questions which will be used in formula(s)
/*	private int question1Weight;
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
	private int question14Weight;
	private int question15Weight;
	private int question16Weight;
*/		
	public ResidentialRiskComputation (SaveResidentialAssessmentEvent saveResidentialAssessEvent) {
		this.saveResidentialAssessEvent = saveResidentialAssessEvent;
		setWeightOfQuestions();
	}
	private RiskFinalScore riskAssessmentScore() {
		RiskFinalScore riskFinalScore = new RiskFinalScore();
		RiskResultGroup riskResultGroup = null;
		
		Iterator<RiskResultGroup> riskResultGroups = RiskResultGroup.findAllByAttributeName("description", "ResidentialRisk");
		
		while ( riskResultGroups.hasNext() ){
			riskResultGroup = riskResultGroups.next();
		}
		
		if (riskResultGroup != null) {
			riskFinalScore.setRiskResultGroup(riskResultGroup);
		}
		
		riskFinalScore.setRiskAnalysisId(Integer.parseInt(saveResidentialAssessEvent.getRiskAnalysisId()));
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
		
		validate(saveResidentialAssessEvent);
		residentialRiskCompRespEvent = new ResidentialRiskComputationReponseEvent();
		
/*		residentialRiskCompRespEvent.setTotalEvaluation(question5Weight);
		residentialRiskCompRespEvent.setTotalReview(calculateTotalSpecialCautionScore());
		residentialRiskCompRespEvent.setTotalSchoolRecords(calculateTotalEducationScore());
		*/
		residentialRiskCompRespEvent.setTotalScores(finalScores());
		
		return residentialRiskCompRespEvent;
	}

	/** 
	 * (non-Javadoc)
	 * @see RiskComputation#validate(IEvent event)
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected void validate(IEvent event) throws ComputationValidationException {
		
		boolean failed = false;
		
		if (failed) {
			throw new ComputationValidationException("ResidentialRiskComputation failed validation.");
		}
		
	}
	
	/* (non-Javadoc)
	 * @see pd.juvenilecase.riskanalysis.riskanalysiscomputation.RiskComputation#finalScore()
	 */
	protected int finalScore() {
		int total = 0;
		//TotalReview+CautionSuicide+CautionAssault+CautionSmuggling+CautionFighting+CautionSexuallyActive+
		//CautionRunaway+CautionSelfMutilation
		//Need to change this after confirming with Eve Tran
		//total =calculateTotalSpecialCautionScore() + calculateTotalEducationScore() + question5Weight;
		total = RiskComputationUtil.calculateScore(getQuestionWeightsMap());
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
		
		HashMap hmQuestionWeights = RiskComputationUtil.getQuestionWeights(saveResidentialAssessEvent);
		setQuestionWeightsMap(hmQuestionWeights);

		//Enumeration riskQuestionAnswerEvents = saveResidentialAssessEvent.getRequests();
		//Enumeration riskQuestionAnswerEvents2 = saveResidentialAssessEvent.getRequests();
		
		//RiskComputationUtil riskComputationUtil = new RiskComputationUtil();
		//HashMap hmQuestionWeights = RiskComputationUtil.getQuestionWeights(saveResidentialAssessEvent);

		
/*		Set entries = hmQuestionWeights.entrySet();
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
				} case 14: {
					question14Weight = weight;
					break;
				} case 15: {
					while( riskQuestionAnswerEvents2.hasMoreElements() ) {
						Object obj = riskQuestionAnswerEvents2.nextElement();
						if( obj instanceof RiskQuestionAnswerEvent ) {	
							RiskQuestionAnswerEvent riskReqEvent = (RiskQuestionAnswerEvent)obj;
							if (riskReqEvent.getQuestionNumber() == 15 && 
									(riskReqEvent.getAnswerText() != null && 
											(riskReqEvent.getAnswerText().length() > 0 && riskReqEvent.getAnswerText() != "" ) ) ) {
								weight = 2;
								break;
							}
						}
					}
					question15Weight = weight;
					break;
				} case 16: {
					question16Weight = weight;
					break;
				} 
		  	}
		}*/
	}
	public void setQuestionWeightsMap(HashMap questionWeightsMap) {
		this.questionWeightsMap = questionWeightsMap;
	}
	public HashMap getQuestionWeightsMap() {
		return questionWeightsMap;
	}
	
	/**
	 * @return int total
	 */
/*	private int calculateTotalSpecialCautionScore() {
		int total = 0;
		// CautionAssault + CautionFighting + Caution Runaway + Caution Self-Mutilation + CautionSexuallyActive + Caution Smuggling 
		//+ CautionOther (calculate as yes if not null)+ CautionSuicide+CurrentSchoolSuspension+CautionPsychologicalDiagnosis (if yes=2)
			total = question9Weight + question12Weight + question13Weight + 
			question7Weight + question10Weight + question11Weight + question8Weight + 
			question15Weight + question6Weight + question14Weight;
		return total;
	}*/
	
	/**
	 * @return int total
	 */
/*	private int calculateTotalEducationScore() {
		int total = 0;
		// TotalEducation=This activity uses the educational  traits  
		// [Type=Educational Performance, School Behavior and School Attendance] 
		// currently assigned to determine the points for the total. 
		// Use all the education traits to calculate the total, 
		// count each repeating trait only once. 
		//return RiskAnalysisResidential.getEducationTotalForResidential(juvenileNum);
		total = question1Weight + question2Weight + question3Weight + question4Weight;
		return total;
	}*/

}