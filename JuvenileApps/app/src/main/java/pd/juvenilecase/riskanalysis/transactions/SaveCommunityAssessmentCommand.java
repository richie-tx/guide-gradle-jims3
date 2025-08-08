//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\SaveInterviewAssessmentCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import java.util.Enumeration;

import messaging.riskanalysis.SaveCommunityAssessmentEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.PDCodeTableConstants;
import naming.RiskAnalysisConstants;
import pd.exception.ComputationValidationException;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskAnalysis;
import pd.juvenilecase.riskanalysis.RiskAnalysisCommunity;

public class SaveCommunityAssessmentCommand implements ICommand 
{
   
   /**
    * @roseuid 4342C32E01E8
    */
   public SaveCommunityAssessmentCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 433C3D3D00DD
    */
   public void execute(IEvent event) throws ComputationValidationException
   {
    
	   SaveCommunityAssessmentEvent saveCommAssessEvent = (SaveCommunityAssessmentEvent)event;
		
		if ( saveCommAssessEvent.isUpdateOverRiddenStatus() ) {
			   
			   if ( (saveCommAssessEvent.getAssessmentID() != null) && (saveCommAssessEvent.getAssessmentID().length() > 0) ) {
				   PDRiskAnalysisHelper.updateCommunityRiskAnalysisOverrideStatus(saveCommAssessEvent);
			   }
			   	   
		} else {
			
		   if (saveCommAssessEvent.isUpdate()) {
			   saveCommunityUpdate(saveCommAssessEvent);
		   } else {
			   saveCommunity(saveCommAssessEvent);
		   }
		  
	   }
   }
   
   /**
    * This will be for updating existing interviews 
	 * @param saveIntAssessEvent
	 * @throws ComputationValidationException
	 */
	private void saveCommunityUpdate(SaveCommunityAssessmentEvent saveCommunityAssessEvent)
			throws ComputationValidationException {
		
		//Update Risk Analysis with latest Information
		RiskAnalysis riskAnalysis = RiskAnalysis.find( saveCommunityAssessEvent.getRiskAnalysisId() );

		if ((saveCommunityAssessEvent.getModReason() != null) && (saveCommunityAssessEvent.getModReason().length() > 0)) {
        	riskAnalysis.setModReason(saveCommunityAssessEvent.getModReason());
        } else {
        	riskAnalysis.setModReason("");
        }
			
		IHome home=new Home();
	   	home.bind(riskAnalysis);
		
	    //Update Risk Analysis Community with latest Information   	
	   	RiskAnalysisCommunity riskAnalysisCommunity = RiskAnalysisCommunity.findByRiskAnalysisId( saveCommunityAssessEvent.getRiskAnalysisId() );
	   	
        //Delete all old RiskAnalysisRecommendations by RiskAnalysisId
	   	PDRiskAnalysisHelper.bindDeleteRiskAnalysisRecommendations(riskAnalysis);
	   	
		//Delete all old RiskFinalScores by RiskAnalysisId
	   	PDRiskAnalysisHelper.bindDeleteRiskFinalScores(riskAnalysis);

	   	riskAnalysisCommunity.setRiskAnalysis(riskAnalysis);
       
        PDRiskAnalysisHelper.setCalculatedScoresCommunity(riskAnalysisCommunity, saveCommunityAssessEvent, riskAnalysis);
        
		PDRiskAnalysisHelper.bindDeleteRiskResponses(riskAnalysis);
		
		//Insert new RiskReponses by RiskAnalysisId
	    Enumeration events = saveCommunityAssessEvent.getRequests();
	    PDRiskAnalysisHelper.bindCreateRiskReponses(riskAnalysis.getOID(), events);

		PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamically(riskAnalysis, RiskAnalysisConstants.RISK_TYPE_COMMUNITY, false);		

		PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(riskAnalysis);
	}

	/**
	 * @param event
	 * @throws ComputationValidationException
	 */
	private void saveCommunity(IEvent event) throws ComputationValidationException {
		SaveCommunityAssessmentEvent saveCommAssessEvent = (SaveCommunityAssessmentEvent)event;
			
		RiskAnalysis riskAnalysis = new RiskAnalysis();
		RiskAnalysisCommunity riskCommunity = new RiskAnalysisCommunity();
		riskCommunity.setEnteredDate(saveCommAssessEvent.getAssessmentDate());
				
		riskAnalysis.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_COMMUNITY);
		riskAnalysis.setJuvenileNum(saveCommAssessEvent.getJuvenileNum());
		riskAnalysis.setCasefileID(Integer.parseInt(saveCommAssessEvent.getCasefileID()));
		riskAnalysis.setEnteredDate(saveCommAssessEvent.getAssessmentDate());
		
		IHome home=new Home();
		home.bind(riskAnalysis);
		
		saveCommAssessEvent.setRiskAnalysisId(riskAnalysis.getOID());
		
		PDRiskAnalysisHelper.setCalculatedScoresCommunity(riskCommunity, saveCommAssessEvent, riskAnalysis);

		PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamically(riskAnalysis, RiskAnalysisConstants.RISK_TYPE_COMMUNITY, false);		
		
		riskCommunity.setRiskAnalysis(riskAnalysis);
	
		Enumeration events = saveCommAssessEvent.getRequests();
		
		PDRiskAnalysisHelper.bindCreateRiskReponses(riskAnalysis.getOID(), events);

		PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(riskAnalysis);
		
		JuvenileCasefile myCasefile=JuvenileCasefile.find(saveCommAssessEvent.getCasefileID());
		if(myCasefile!=null){
			myCasefile.setIsCommunityRiskNeeded(false);
			PDRiskAnalysisHelper.turnRiskFlagOffOnAllCasefiles(riskAnalysis.getJuvenileNum(),riskAnalysis.getAssessmentType(),myCasefile.getSupervisionCategoryId());
			if(PDCodeTableConstants.CASEFILE_SUPERVISION_TYPE_ENHANCED_SUPERVISION.equals(myCasefile.getSupervisionTypeId())){
				myCasefile.setIsResidentialRiskNeeded(false);
			}
		}
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
