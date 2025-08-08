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
import messaging.riskanalysis.SaveCommunityAssessmentEvent;
import messaging.riskanalysis.reply.RiskComputationReponseEvent;
import messaging.riskanalysis.reply.CommunityRiskComputationReponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
/** 
 * @deprecated
 * @author PAlcocer
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class CommunityRiskComputation extends RiskComputation  {
	
	private CommunityRiskComputationReponseEvent riskCommunityCompRespEvent;
	private SaveCommunityAssessmentEvent saveCommunityAssessEvent;
	
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
	private int question14Weight;
	private int question15Weight;
	private int question16Weight;
	private int question17Weight;
	private int question18Weight;
	  
	public CommunityRiskComputation (SaveCommunityAssessmentEvent saveCommunityAssessEvent) {
		this.saveCommunityAssessEvent = saveCommunityAssessEvent; 		
		setWeightOfQuestions();
	}
	private RiskFinalScore riskAssessmentScore() {
		RiskFinalScore riskFinalScore = new RiskFinalScore();
		RiskResultGroup riskResultGroup = null;
		
		Iterator<RiskResultGroup> riskResultGroups = RiskResultGroup.findAllByAttributeName("description", "CommunityRisk");
		
		while ( riskResultGroups.hasNext() ){
			riskResultGroup = riskResultGroups.next();
		}
		
		if (riskResultGroup != null) {
			riskFinalScore.setRiskResultGroup(riskResultGroup);
		}
		
		riskFinalScore.setRiskAnalysisId(Integer.parseInt(saveCommunityAssessEvent.getRiskAnalysisId()));
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
		
		validate(saveCommunityAssessEvent);
		riskCommunityCompRespEvent = new CommunityRiskComputationReponseEvent();
		
		riskCommunityCompRespEvent.setTotalPeer(question3Weight);
		riskCommunityCompRespEvent.setTotalAttitude(question2Weight);
		riskCommunityCompRespEvent.setTotalSubstanceAbuse(calculateTotalSubstanceAbuseScore());
		riskCommunityCompRespEvent.setTotalClassesFailing(calculateTotalClassesFailingScore());
		riskCommunityCompRespEvent.setTotalGradeLevel(question9Weight);
		riskCommunityCompRespEvent.setTotalChild(calculateTotalChildCommunityScore());
		riskCommunityCompRespEvent.setTotalSchoolBehavior(calculateTotalSchoolBehaviorCommunityScore());
		riskCommunityCompRespEvent.setTotalEducationStatus(calculateTotalEducationStatusScore());
		riskCommunityCompRespEvent.setTotalChildParent(calculateTotalChildParentScore());
		riskCommunityCompRespEvent.setTotalFamilyAttitude(calculateTotalFamilyAttitudeScore());
		//riskCommunityCompRespEvent.setTotalScore(finalScore());
		riskCommunityCompRespEvent.setTotalScores(finalScores());
		
		return (RiskComputationReponseEvent)riskCommunityCompRespEvent;
	}

	/** 
	 * (non-Javadoc)
	 * @see RiskComputation#validate(IEvent event)
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected void validate(IEvent event) throws ComputationValidationException {
		
		boolean failed = false;
		
		if (failed) {
			throw new ComputationValidationException("CommunityRiskComputation failed validation.");
		}
		
	}
	
	/* (non-Javadoc)
	 * @see pd.juvenilecase.riskanalysis.riskanalysiscomputation.RiskComputation#finalScore()
	 */
	protected int finalScore() {
		int total = 0;
		// If JUVENILE_RISK_ANALYSIS.PartNumber=4a then Community Supervision and JUVENILE_RISK_ANALYSIS.PartScore=
		//{JUVENILE_RISK_ANALYSIS_4a.TotalSubstance+TotalPeer
		//+TotalAttitude+TotalChild+TotalSchoolBehavior+TotalEducationStatus+TotalChildParent+TotalFamilyAttitude}
		total = calculateTotalSubstanceAbuseScore() + question3Weight + question2Weight
			+ /*calculateTotalChildCommunityScore() +*/ calculateTotalSchoolBehaviorCommunityScore() 
			+ calculateTotalEducationStatusScore() + calculateTotalChildParentScore() + calculateTotalFamilyAttitudeScore();
		
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
		HashMap hmQuestionWeights = RiskComputationUtil.getQuestionWeights(saveCommunityAssessEvent);
				
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
				} case 14: {
					question14Weight = weight;
					break;
				} case 15: {
					question15Weight = weight;
					break;
				} case 16: {
					question16Weight = weight;
					break;
				} case 17: {
					question17Weight = weight;
					break;
				} case 18: {
					question18Weight = weight;
					break;
				}
	   		}		
		}
	}
	
	/**
	 * @return into total
	 */
	private int calculateTotalSubstanceAbuseScore() {
		int total = 0;
		//MarijuanaAlcoholUse + OtherDrugUse
		total = question4Weight + question5Weight;
		return total;
	}
	
	/**
	 * @return int total
	 */
	private int calculateTotalClassesFailingScore() {
		int total = 0;
		// The number of points associated with the response selected 
		//for NumberFailingClasses.  (# of failed classes=number of points ; 15+ = 15 points)
		if(question6Weight > 15) {
			total = 15;
		} else {
			total = question6Weight;
		}
		return total;
	}
	
	/**
	 * @return int total
	 */
	private int calculateTotalChildCommunityScore() {
		int total = 0;
		// JUVENILE_RISK_4a.TotalChild=TotalSubstanceAbuse + Total Peer + TotalAttitude
		total = calculateTotalSubstanceAbuseScore() + question3Weight + question2Weight;
		return total;
	}
	
	/**
	 * @return int total
	 */
	private int calculateTotalSchoolBehaviorCommunityScore() {
		int total = 0;
		// JUVENILE_RISK_4a.TotalSchoolBehavior=Expelled + Suspended + Attendance
		total = question1Weight + question10Weight + question11Weight;
		return total;
	}
	
	/**
	 * @return int total
	 */
	private int calculateTotalEducationStatusScore() {
		int total = 0;
		// JUVENILE_RISK_4a.TotalEducationStatus=TotalGradeLevel + TotalClassesFailing
		total = question9Weight + calculateTotalClassesFailingScore() ;
		return total;
	}
	
	/**
	 * @return int total
	 */
	private int calculateTotalChildParentScore() {
		// JUVENILE_RISK_4a.TotalChildParent=Cooperative (yes=-1 points)
		//+ DisrespectHostile (yes=1 points)+ VerballAssaultive (yes=2 points)+ PhysicalAssaultive(yes=3  points)
		return question7Weight + question8Weight + question13Weight + question14Weight;
	}
	
	/**
	 * @return int total
	 */
	private int calculateTotalFamilyAttitudeScore() {
		// JUVENILE_RISK_4a.TotalFamilyAttitude=CooperativeFamily (yes=0 points)
		//+ HostileFamily (yes=2 points)+ PhysicalFamily (yes=10 points)+ VerbalFamily (yes=5 points)
		return question16Weight + question15Weight + question17Weight + question18Weight;
	}
	
}