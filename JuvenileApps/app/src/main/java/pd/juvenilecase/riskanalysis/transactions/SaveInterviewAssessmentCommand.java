//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\SaveInterviewAssessmentCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

import messaging.riskanalysis.SaveInterviewAssessmentEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.RiskAnalysisConstants;
import pd.exception.ComputationValidationException;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskAnalysis;
import pd.juvenilecase.riskanalysis.RiskAnalysisInterview;
import pd.juvenilecase.riskanalysis.RiskFinalScore;

public class SaveInterviewAssessmentCommand implements ICommand 
{
   
   /**
    * @roseuid 4342C32E01E8
    */
   public SaveInterviewAssessmentCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 433C3D3D00DD
    */
   public void execute(IEvent event) throws ComputationValidationException
   {
    
		SaveInterviewAssessmentEvent saveIntAssessEvent = (SaveInterviewAssessmentEvent)event;
		
		if ( saveIntAssessEvent.isUpdateOverRiddenStatus() ) {
			   
			   if ( (saveIntAssessEvent.getAssessmentID() != null) && (saveIntAssessEvent.getAssessmentID().length() > 0) ) {
				   PDRiskAnalysisHelper.updateInterviewRiskAnalysisOverrideStatus(saveIntAssessEvent);
			   }
			   	   
		} else {
			
		   if (saveIntAssessEvent.isUpdate()) {
			   saveInterviewUpdate(saveIntAssessEvent);
		   } else {
			   saveInterview(saveIntAssessEvent);
		   }
		  
	   }
			

   }
   
   /**
     * This will be for updating existing interviews 
	 * @param saveIntAssessEvent
	 * @throws ComputationValidationException
	 */
	private void saveInterviewUpdate(SaveInterviewAssessmentEvent event)
			throws ComputationValidationException {
		SaveInterviewAssessmentEvent saveInterviewAssessEvent = (SaveInterviewAssessmentEvent)event;
		
		//Update Risk Analysis with latest Information
		RiskAnalysis riskAnalysis = RiskAnalysis.find( saveInterviewAssessEvent.getRiskAnalysisId() );

		riskAnalysis.setCreateUserID(saveInterviewAssessEvent.getJpoUserID());
		
		if ((saveInterviewAssessEvent.getModReason() != null) && (saveInterviewAssessEvent.getModReason().length() > 0)) {
        	riskAnalysis.setModReason(saveInterviewAssessEvent.getModReason());
        } else {
        	riskAnalysis.setModReason("");
        }
			
		IHome home=new Home();
	   	home.bind(riskAnalysis);
		
	    //Update Risk Analysis Interview with latest Information   	
	   	RiskAnalysisInterview riskAnalysisIntrw = RiskAnalysisInterview.findByRiskAnalysisId( saveInterviewAssessEvent.getRiskAnalysisId() );
	   	
        //Delete all old RiskAnalysisRecommendations by RiskAnalysisId
	   	PDRiskAnalysisHelper.bindDeleteRiskAnalysisRecommendations(riskAnalysis);
		//Delete all old RiskFinalScores by RiskAnalysisId
	   	PDRiskAnalysisHelper.bindDeleteRiskFinalScores(riskAnalysis);

		riskAnalysisIntrw.setRiskAnalysis(riskAnalysis);
       
		RiskAnalysis latestReferralRiskAnalysis = PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile(saveInterviewAssessEvent.getJuvenileNum(),RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL);
		
		if (latestReferralRiskAnalysis == null) {
			latestReferralRiskAnalysis = PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile(saveInterviewAssessEvent.getJuvenileNum(),RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL);
		} 
		
		if (latestReferralRiskAnalysis == null) {
			latestReferralRiskAnalysis = PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile(saveInterviewAssessEvent.getJuvenileNum(),RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE);
		} 
		
		if (latestReferralRiskAnalysis == null) {
			latestReferralRiskAnalysis = PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile(saveInterviewAssessEvent.getJuvenileNum(),RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE);
		} 
		
		//TODO once risk computation is working, remove latestReferralRiskAnalysis as a paramter in setCalculatedScoresInterview
		String resultGroup = null;
		if(latestReferralRiskAnalysis.getAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL) ) {
			resultGroup = "ReferralRisk";
			
		} else if (latestReferralRiskAnalysis.getAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE) ||
				   latestReferralRiskAnalysis.getAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE) ) {
			resultGroup = "TJPCRisk";
		}
		Collection finalscores = latestReferralRiskAnalysis.getFinalScores();
		Iterator <RiskFinalScore> iteFinalscores = finalscores.iterator();
		while(iteFinalscores.hasNext()) 
		{
			RiskFinalScore riskFinalScore = iteFinalscores.next();
			
			if (riskFinalScore.getRiskResultGroup().getDescription().equalsIgnoreCase(resultGroup)) {
				saveInterviewAssessEvent.setLatestReferralFinalScore(riskFinalScore.getFinalScore());
				break;
			}
		}       
        PDRiskAnalysisHelper.setCalculatedScoresInterview(riskAnalysisIntrw, saveInterviewAssessEvent, riskAnalysis);
        
		boolean isCustody = false;
		if(latestReferralRiskAnalysis.isCustody() && saveInterviewAssessEvent.isProbableCauseHearingSelected()){
			isCustody = true;
		}

		PDRiskAnalysisHelper.bindDeleteRiskResponses(riskAnalysis);
		
		//Insert new RiskReponses by RiskAnalysisId
	    Enumeration events = saveInterviewAssessEvent.getRequests();
	    PDRiskAnalysisHelper.bindCreateRiskReponses(riskAnalysis.getOID(), events);

		PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamically(riskAnalysis, RiskAnalysisConstants.RISK_TYPE_INTERVIEW, isCustody);

		riskAnalysisIntrw.setOnsetAge(saveInterviewAssessEvent.getOnsetAge());
		riskAnalysisIntrw.setSexCd(saveInterviewAssessEvent.getSex());
		
		PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(riskAnalysis);
	}

	/**
	 * Saves new interviews
	 * @param saveIntAssessEvent
	 * @throws ComputationValidationException
	 */
	private void saveInterview(SaveInterviewAssessmentEvent saveIntAssessEvent)
			throws ComputationValidationException {
		
		RiskAnalysis riskAnalysis = new RiskAnalysis();
		RiskAnalysisInterview riskInterview = new RiskAnalysisInterview();
		riskAnalysis.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_INTERVIEW);
		riskAnalysis.setJuvenileNum(saveIntAssessEvent.getJuvenileNum());
		riskAnalysis.setCasefileID(Integer.parseInt(saveIntAssessEvent.getCasefileID()));
		riskAnalysis.setEnteredDate(saveIntAssessEvent.getAssessmentDate());
		IHome home=new Home();
		home.bind(riskAnalysis);
		riskInterview.setRiskAnalysis(riskAnalysis);
		
		//RiskAnalysis latestReferralRiskAnalysis = PDRiskAnalysisHelper.getLatestRiskAnalysis(saveIntAssessEvent.getJuvenileNum(),RiskAnalysisConstants.RISK_TYPE_REFERRAL);
		RiskAnalysis latestReferralRiskAnalysis = PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile(saveIntAssessEvent.getJuvenileNum(),RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL);
		
		if (latestReferralRiskAnalysis == null) {
			latestReferralRiskAnalysis = PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile(saveIntAssessEvent.getJuvenileNum(),RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL);
		} 
		
		if (latestReferralRiskAnalysis == null) {
			latestReferralRiskAnalysis = PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile(saveIntAssessEvent.getJuvenileNum(),RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE);
		} 
		
		if (latestReferralRiskAnalysis == null) {
			latestReferralRiskAnalysis = PDRiskAnalysisHelper.getLatestRiskAnalysisByJuvenile(saveIntAssessEvent.getJuvenileNum(),RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE);
		} 
		
		
		//TODO once risk computation is working, remove latestReferralRiskAnalysis as a paramter in setCalculatedScoresInterview
		String resultGroup = null;
		if(latestReferralRiskAnalysis.getAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL) ) {
			resultGroup = "ReferralRisk";
			
		} else if (latestReferralRiskAnalysis.getAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE) ||
				   latestReferralRiskAnalysis.getAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE) ) {
			resultGroup = "TJPCRisk";
		}
		Collection finalscores = latestReferralRiskAnalysis.getFinalScores();
		Iterator <RiskFinalScore> iteFinalscores = finalscores.iterator();
		while(iteFinalscores.hasNext()) 
		{
			RiskFinalScore riskFinalScore = iteFinalscores.next();
			
			if (riskFinalScore.getRiskResultGroup().getDescription().equalsIgnoreCase(resultGroup)) {
				saveIntAssessEvent.setLatestReferralFinalScore(riskFinalScore.getFinalScore());
				break;
			}
		}
		saveIntAssessEvent.setRiskAnalysisId(riskAnalysis.getOID());
		PDRiskAnalysisHelper.setCalculatedScoresInterview(riskInterview, saveIntAssessEvent, riskAnalysis);
		boolean isCustody = false;
		if(latestReferralRiskAnalysis.isCustody() && saveIntAssessEvent.isProbableCauseHearingSelected()){
			isCustody = true;
		}

		PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamically(riskAnalysis, RiskAnalysisConstants.RISK_TYPE_INTERVIEW, isCustody);

		riskInterview.setOnsetAge(saveIntAssessEvent.getOnsetAge());
		riskInterview.setSexCd(saveIntAssessEvent.getSex());
	
		Enumeration events = saveIntAssessEvent.getRequests();
	
		PDRiskAnalysisHelper.bindCreateRiskReponses(riskAnalysis.getOID(), events);
		
		PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(riskAnalysis);
		
		JuvenileCasefile myCasefile=JuvenileCasefile.find(saveIntAssessEvent.getCasefileID());
		//taken out for US 14459
		/*
		if(myCasefile!=null){
			myCasefile.setIsInterviewRiskNeeded(false);
			PDRiskAnalysisHelper.turnRiskFlagOffOnAllCasefiles(riskAnalysis.getJuvenileNum(),riskAnalysis.getAssessmentType(),myCasefile.getSupervisionCategoryId());
		}*/
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
