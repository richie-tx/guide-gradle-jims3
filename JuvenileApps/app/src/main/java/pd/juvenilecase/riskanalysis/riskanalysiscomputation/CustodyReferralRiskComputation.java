package pd.juvenilecase.riskanalysis.riskanalysiscomputation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import naming.RiskAnalysisConstants;
import messaging.riskanalysis.SaveReferralAssessmentEvent;
import messaging.riskanalysis.reply.RiskComputationReponseEvent;
import messaging.riskanalysis.reply.CustodyReferralRiskComputationReponseEvent;
import pd.exception.ComputationValidationException;
import pd.juvenilecase.riskanalysis.JuvenileDelinquencyHistory;
import pd.juvenilecase.riskanalysis.RiskFinalScore;
import pd.juvenilecase.riskanalysis.RiskResultGroup;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/** 
 * @deprecated
 * @author PAlcocer
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class CustodyReferralRiskComputation extends RiskComputation {
	
	private CustodyReferralRiskComputationReponseEvent refRiskCompRespEvent;
	private SaveReferralAssessmentEvent saveRefAssessEvent;
	private JuvenileDelinquencyHistory juvenileDelinquencyHistory;
	
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
	
	public CustodyReferralRiskComputation(SaveReferralAssessmentEvent saveRefAssessEvent) {
		this.saveRefAssessEvent = saveRefAssessEvent; 
		this.juvenileDelinquencyHistory = 
			JuvenileDelinquencyHistory.findbyRiskAnalysisId(saveRefAssessEvent.getRiskAnalysisId());
		setWeightOfQuestions();
	}
	
	/** 
	 * (non-Javadoc)
	 * @see RiskComputation#compute()
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public RiskComputationReponseEvent compute() throws ComputationValidationException {
		
		validate(saveRefAssessEvent);
		
		refRiskCompRespEvent = new CustodyReferralRiskComputationReponseEvent();
		refRiskCompRespEvent.setCustodyStatus(RiskAnalysisConstants.CUSTODY);
		refRiskCompRespEvent.setOnProbation(Boolean.parseBoolean(saveRefAssessEvent.getProbationStatus()));
		refRiskCompRespEvent.setPendingCourt(Boolean.parseBoolean(saveRefAssessEvent.getPendingCourt()));
		refRiskCompRespEvent.setVOPPendingCourt(Boolean.parseBoolean(saveRefAssessEvent.getVopPendingCourt()));
		refRiskCompRespEvent.setTotalCapitalFelony(juvenileDelinquencyHistory.getCapFelonyTotal());
		refRiskCompRespEvent.setTotalClassC(juvenileDelinquencyHistory.getMisdCTotal());
		refRiskCompRespEvent.setTotalClassABScore(juvenileDelinquencyHistory.getMisdABTotal());
		refRiskCompRespEvent.setTotalCurrentStatus(juvenileDelinquencyHistory.getScoOffensesTotal());
		refRiskCompRespEvent.setTotalFelony1(juvenileDelinquencyHistory.getFelony1Total());
		refRiskCompRespEvent.setTotalFelony2(juvenileDelinquencyHistory.getFelony2Total());
		refRiskCompRespEvent.setTotalFelony3(juvenileDelinquencyHistory.getFelony3Total());
		refRiskCompRespEvent.setTotalLevel(calculateTotalLevelScore());
		refRiskCompRespEvent.setTotalOffenses(juvenileDelinquencyHistory.getTotalOffenses());
		refRiskCompRespEvent.setTotalReferralsHistory(juvenileDelinquencyHistory.getReferralHistoryTotal());
		refRiskCompRespEvent.setTotalStateJailFelony(juvenileDelinquencyHistory.getJailFelonyTotal());
		refRiskCompRespEvent.setTotalStatusCO(juvenileDelinquencyHistory.getScoOffensesTotal());
		refRiskCompRespEvent.setTotalSupervision(calculateTotalSupervisionScore());
		refRiskCompRespEvent.setAdditionalCharges(saveRefAssessEvent.getAdditionalCharges());
		refRiskCompRespEvent.setSeriousnessIndex(juvenileDelinquencyHistory.getSeriousnessIndex());
		//refRiskCompRespEvent.setTotalScore(finalScore());
		refRiskCompRespEvent.setTotalScores(finalScores());
		
		return (RiskComputationReponseEvent)refRiskCompRespEvent;
	}
	private RiskFinalScore riskAssessmentScore() {
		RiskFinalScore riskFinalScore = new RiskFinalScore();
		RiskResultGroup riskResultGroup = null;
		
		Iterator<RiskResultGroup> riskResultGroups = RiskResultGroup.findAllByAttributeName("description", "ReferralRisk");
		
		while ( riskResultGroups.hasNext() ){
			riskResultGroup = riskResultGroups.next();
		}
		
		if (riskResultGroup != null) {
			riskFinalScore.setRiskResultGroup(riskResultGroup);
		}
		
		riskFinalScore.setRiskAnalysisId(Integer.parseInt(saveRefAssessEvent.getRiskAnalysisId()));
		riskFinalScore.setFinalScore(finalScore());
		//Explicitly bind to database so that a Risk Final Score OID is generated
		IHome home=new Home();
		home.bind(riskFinalScore);

		return riskFinalScore;
	}
	/** 
	 * (non-Javadoc)
	 * @see RiskComputation#validate(IEvent event)
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	protected void validate(IEvent event) throws ComputationValidationException {
		
		boolean failed = false;
		
		if (failed) {
			throw new ComputationValidationException("CustodyReferralRiskComputation failed validation.");
		}
		
	}
	
	/* (non-Javadoc)
	 * @see pd.juvenilecase.riskanalysis.riskanalysiscomputation.RiskComputation#finalScore()
	 */
	protected int finalScore() {
		int finalScore = question1Weight + question2Weight + question3Weight 
				+ question4Weight + question5Weight + question6Weight+ question7Weight+ question8Weight+ question9Weight;
		return finalScore;
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
		//Enumeration riskQuestionAnswerEvents = saveRefAssessEvent.getRequests();
		
		//RiskComputationUtil riskComputationUtil = new RiskComputationUtil();
		HashMap hmQuestionWeights = RiskComputationUtil.getQuestionWeights(saveRefAssessEvent);
				
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
			    }
		   	}
		}
	}
	
	
	/**
	 * @return int total
	 */
	private int calculateTotalSupervisionScore() {
		int total = 0;
		//PendingCourt + OnProbation
		if (refRiskCompRespEvent.isOnProbation()) {
			total = total + 3;
		} 
		if (refRiskCompRespEvent.isPendingCourt()) {
			total = total + 3;
		} 		
		return total;
	}
	
	/**
	 * @return int total
	 */
	private int calculateTotalLevelScore() {
		int total = 0;
		//SeveriousnessIndex + AdditionalCharges
		total = juvenileDelinquencyHistory.getSeriousnessIndex() + saveRefAssessEvent.getAdditionalCharges(); 
		return total;
	}

}