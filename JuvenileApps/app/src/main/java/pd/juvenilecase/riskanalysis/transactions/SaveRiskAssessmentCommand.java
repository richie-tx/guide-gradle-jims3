package pd.juvenilecase.riskanalysis.transactions;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import naming.PDConstants;
import naming.RiskAnalysisConstants;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskAnalysis;
import pd.juvenilecase.riskanalysis.RiskAnalysisCourtReferral;
import messaging.riskanalysis.RiskQuestionAnswerEvent;
import messaging.riskanalysis.SaveRiskAssessmentEvent;
import messaging.riskanalysis.reply.RiskComputationReponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;

public class SaveRiskAssessmentCommand implements ICommand {
	private static final String COURT_REFERRAL = "COURTREFERRAL";
	public void execute(IEvent event) 
	{
		SaveRiskAssessmentEvent saveRiskAssessEvent = (SaveRiskAssessmentEvent)event;
		
		if ( saveRiskAssessEvent.isUpdateOverRiddenStatus() ) {
			   
			   if ( (saveRiskAssessEvent.getAssessmentID() != null) && (saveRiskAssessEvent.getAssessmentID().length() > 0) ) {
				   PDRiskAnalysisHelper.updateRiskAnalysisOverrideStatus(saveRiskAssessEvent);
			   }
			   	   
		} else {
			
		   if (saveRiskAssessEvent.isUpdate()) {
			   saveRiskUpdate(saveRiskAssessEvent);
		   } else {
			   saveRisk(saveRiskAssessEvent);
		   }
		  
	   }
	}
	
	/**
     * This will be for updating existing risk analysis 
	 * @param saveRiskAssessEvent
	 */
	private void saveRiskUpdate(SaveRiskAssessmentEvent saveRiskAssessEvent) {
		//Update Risk Analysis with latest Information
		RiskAnalysis riskAnalysis = RiskAnalysis.find( saveRiskAssessEvent.getRiskAnalysisId() );

		if ((saveRiskAssessEvent.getModReason() != null) && (saveRiskAssessEvent.getModReason().length() > 0)) {
        	riskAnalysis.setModReason(saveRiskAssessEvent.getModReason());
        } else {
        	riskAnalysis.setModReason(PDConstants.BLANK);
        }
		//saving the new case file for TJJD Risk by Sruti
		String sCasefileId = saveRiskAssessEvent.getCasefileID();
		if ((sCasefileId != null) && (sCasefileId.length() > 0)) {
			
			int iCasefileId = Integer.parseInt(sCasefileId);
        	riskAnalysis.setCasefileID(iCasefileId);
        } 
		//	
		IHome home=new Home();
	   	home.bind(riskAnalysis);
		
        //Delete all old RiskAnalysisRecommendations by RiskAnalysisId
	   	PDRiskAnalysisHelper.bindDeleteRiskAnalysisRecommendations(riskAnalysis);
	   	
		//Delete all old RiskFinalScores by RiskAnalysisId
	   	PDRiskAnalysisHelper.bindDeleteRiskFinalScores(riskAnalysis);

	   	saveRiskAssessEvent.setRiskFormulaId(new Integer(riskAnalysis.getRiskFormulaId()).toString());
		RiskComputationReponseEvent rcre = PDRiskAnalysisHelper.setCalculatedScores(saveRiskAssessEvent);
       
		PDRiskAnalysisHelper.bindDeleteRiskResponses(riskAnalysis);
		
		boolean isInCustody = isInCustody(saveRiskAssessEvent.getAssessmentType(), saveRiskAssessEvent.getRequests());
		if (saveRiskAssessEvent.getAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_INTERVIEW) && isInCustody){
			RiskAnalysis latestReferralRiskAnalysis = 
				PDRiskAnalysisHelper.getLatestReferralRiskAnalysisOnUpdate(
						saveRiskAssessEvent.getJuvenileNum(), saveRiskAssessEvent.getAssessmentDate());
			if (!latestReferralRiskAnalysis.isCustody()){
				isInCustody = false;
			}
		} 
		
		//Insert new RiskReponses by RiskAnalysisId
		PDRiskAnalysisHelper.bindCreateRiskReponses(riskAnalysis.getOID(), saveRiskAssessEvent.getRequests());

		List recommendationList = PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamically(rcre.getTotalScores(), Integer.toString(riskAnalysis.getRiskFormulaId()), isInCustody);

		PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(rcre.getTotalScores(), recommendationList);

		if (saveRiskAssessEvent.getAssessmentType().startsWith(COURT_REFERRAL)){
			PDRiskAnalysisHelper.sendSuggestedCasePlanDomains(riskAnalysis.getOID());
		}

	}

	/**
	 * @param event
	 */
	private void saveRisk(SaveRiskAssessmentEvent saveRiskAssessEvent) {

		RiskAnalysis riskAnalysis = new RiskAnalysis();

		riskAnalysis.setAssessmentType(saveRiskAssessEvent.getAssessmentType());
		riskAnalysis.setJuvenileNum(saveRiskAssessEvent.getJuvenileNum());
		riskAnalysis.setCasefileID(Integer.parseInt(saveRiskAssessEvent.getCasefileID()));
		riskAnalysis.setEnteredDate(new Date());
		riskAnalysis.setRiskFormulaId(Integer.parseInt(saveRiskAssessEvent.getRiskFormulaId()));
		
		IHome home = new Home();
		home.bind(riskAnalysis);
		
		boolean isCustody = isInCustody(saveRiskAssessEvent.getAssessmentType(), saveRiskAssessEvent.getRequests());
		
		if (saveRiskAssessEvent.getAssessmentType().equals(RiskAnalysisConstants.RISK_TYPE_INTERVIEW) && isCustody){
			RiskAnalysis latestReferralRiskAnalysis = 
				PDRiskAnalysisHelper.getLatestReferralRiskAnalysisOnUpdate(
						saveRiskAssessEvent.getJuvenileNum(), saveRiskAssessEvent.getAssessmentDate());
			if (!latestReferralRiskAnalysis.isCustody()){
				isCustody = false;
			}
		} else if (saveRiskAssessEvent.getAssessmentType().startsWith(COURT_REFERRAL)){
			RiskAnalysisCourtReferral riskCourtReferral = new RiskAnalysisCourtReferral();
			riskCourtReferral.setEnteredDate(riskAnalysis.getEnteredDate());
			riskCourtReferral.setRiskAnalysisId(Integer.parseInt(riskAnalysis.getOID()));
		}
		
		saveRiskAssessEvent.setRiskAnalysisId(riskAnalysis.getOID());
		RiskComputationReponseEvent rcre = PDRiskAnalysisHelper.setCalculatedScores(saveRiskAssessEvent);
		
		List recommendationList = PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamically(rcre.getTotalScores(), saveRiskAssessEvent.getRiskFormulaId(), isCustody);

		PDRiskAnalysisHelper.bindCreateRiskReponses(riskAnalysis.getOID(), saveRiskAssessEvent.getRequests());
		
		PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(rcre.getTotalScores(), recommendationList);
		
		if (saveRiskAssessEvent.getAssessmentType().startsWith(COURT_REFERRAL)){
			PDRiskAnalysisHelper.sendSuggestedCasePlanDomains(riskAnalysis.getOID());
		}
		
		PDRiskAnalysisHelper.turnRiskFlagOffOnAllCasefiles(riskAnalysis);
		
		PDRiskAnalysisHelper.setFlagsBasedOnNewRiskAssessmentType(null, riskAnalysis.getAssessmentType(), String.valueOf(riskAnalysis.getCasefileID()));
		
	}

	/**
	 * @param assessmentType
	 * @param events
	 */
	private boolean isInCustody(String assessmentType, Enumeration <RiskQuestionAnswerEvent> events)
	{
		//Interview and Progress have different recommendations depending on whether the juvenile is in custody.
		
		final String CUSTODY_TEXT = "IN CJPO CUSTODY?";
		boolean isInCustody = false;
		RiskQuestionAnswerEvent answer = null;
		
		List <RiskQuestionAnswerEvent> answers = CollectionUtil.enumerationToList(events);
		
		for (int i = 0; i < answers.size(); i++) {
			answer = answers.get(i);
			if (assessmentType.equals(RiskAnalysisConstants.RISK_TYPE_PROGRESS)){
				if (answer.getQuestionText().toUpperCase().contains(CUSTODY_TEXT)){
					if (answer.getAnswerText().equalsIgnoreCase(RiskAnalysisConstants.YES)){
						isInCustody = true;
					}
					break;
				}
			} else if (assessmentType.equals(RiskAnalysisConstants.RISK_TYPE_INTERVIEW)){
				if (answer.getAnswerText().equals(RiskAnalysisConstants.PROBABLE_CAUSE_HEARING)){
					isInCustody = true;
				}
				break;
			} else {
				break;
			}
		}
	
		
		
		return isInCustody;

	}

	/**
	 * @param event
	 * @roseuid 433C3D3D00DF
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 433C3D3D00E1
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 433C3D3D00E3
	 */
	public void update(Object anObject)
	{

	}
}
