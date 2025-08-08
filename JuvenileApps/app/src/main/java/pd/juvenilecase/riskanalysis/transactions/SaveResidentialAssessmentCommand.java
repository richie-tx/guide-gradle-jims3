//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\SaveResidentialAssessmentCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import java.util.Enumeration;

import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.RiskAnalysisConstants;

import pd.exception.ComputationValidationException;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskAnalysis;
import pd.juvenilecase.riskanalysis.RiskAnalysisResidential;
import messaging.riskanalysis.SaveResidentialAssessmentEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class SaveResidentialAssessmentCommand implements ICommand 
{
   
   /**
    * @roseuid 4357DD2600BB
    */
   public SaveResidentialAssessmentCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4357D9B00177
    */
   public void execute(IEvent event) throws ComputationValidationException
   {
	   SaveResidentialAssessmentEvent saveResAssessEvent = (SaveResidentialAssessmentEvent)event;
		
		if ( saveResAssessEvent.isUpdateOverRiddenStatus() ) {
			   
			   if ( (saveResAssessEvent.getAssessmentID() != null) && (saveResAssessEvent.getAssessmentID().length() > 0) ) {
				   PDRiskAnalysisHelper.updateResidentialRiskAnalysisOverrideStatus(saveResAssessEvent);
			   }
			   	   
		   } else {
			
			   if (saveResAssessEvent.isUpdate()) {
				   saveResidentialUpdate(saveResAssessEvent);
			   } else {
				   saveResidential(saveResAssessEvent);
			   }
			  
		   }
   	
   }

	/**
	 * @param event
	 * @throws ComputationValidationException
	 */
	private void saveResidential(IEvent event)
			throws ComputationValidationException {
		SaveResidentialAssessmentEvent saveResAssessEvent = (SaveResidentialAssessmentEvent)event;
					
		RiskAnalysis riskAnalysis = new RiskAnalysis();
		RiskAnalysisResidential riskResidential = new RiskAnalysisResidential();
		riskAnalysis.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL);
		riskAnalysis.setJuvenileNum(saveResAssessEvent.getJuvenileNum());
		riskAnalysis.setCasefileID(Integer.parseInt(saveResAssessEvent.getCasefileID()));
		riskAnalysis.setEnteredDate(saveResAssessEvent.getAssessmentDate());
		IHome home=new Home();
		home.bind(riskAnalysis);
		
		saveResAssessEvent.setRiskAnalysisId(riskAnalysis.getOID());
		PDRiskAnalysisHelper.setCalculatedScoresResidential(riskResidential, saveResAssessEvent, riskAnalysis);
		PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamically(riskAnalysis, RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL, false);			
		riskResidential.setRiskAnalysis(riskAnalysis);
	
		Enumeration events = saveResAssessEvent.getRequests();
				
		PDRiskAnalysisHelper.bindCreateRiskReponses(riskAnalysis.getOID(), events);

		PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(riskAnalysis);

		JuvenileCasefile myCasefile=JuvenileCasefile.find(saveResAssessEvent.getCasefileID());
		if(myCasefile!=null){
			myCasefile.setIsResidentialRiskNeeded(false);
			PDRiskAnalysisHelper.turnRiskFlagOffOnAllCasefiles(riskAnalysis.getJuvenileNum(),riskAnalysis.getAssessmentType(),myCasefile.getSupervisionCategoryId());
			if(PDCodeTableConstants.CASEFILE_SUPERVISION_TYPE_ENHANCED_SUPERVISION.equals(myCasefile.getSupervisionTypeId())){
				myCasefile.setIsCommunityRiskNeeded(false);
			}
			
		}
	}
	
	/**
	 * This will be for updating existing interviews 
	 * @param saveIntAssessEvent
	 * @throws ComputationValidationException
	 */
	private void saveResidentialUpdate(SaveResidentialAssessmentEvent saveResAssessEvent)
			throws ComputationValidationException {
		//Update Risk Analysis with latest Information
		RiskAnalysis riskAnalysis = RiskAnalysis.find( saveResAssessEvent.getRiskAnalysisId() );

		if ((saveResAssessEvent.getModReason() != null) && (saveResAssessEvent.getModReason().length() > 0)) {
        	riskAnalysis.setModReason(saveResAssessEvent.getModReason());
        } else {
        	riskAnalysis.setModReason(PDConstants.BLANK);
        }
			
		IHome home=new Home();
	   	home.bind(riskAnalysis);
		
	    //Update Risk Analysis Progress with latest Information   	
	   	RiskAnalysisResidential riskAnalysisResidential = RiskAnalysisResidential.findByRiskAnalysisId( saveResAssessEvent.getRiskAnalysisId() );
	   	
        //Delete all old RiskAnalysisRecommendations by RiskAnalysisId
	   	PDRiskAnalysisHelper.bindDeleteRiskAnalysisRecommendations(riskAnalysis);
	   	
		//Delete all old RiskFinalScores by RiskAnalysisId
	   	PDRiskAnalysisHelper.bindDeleteRiskFinalScores(riskAnalysis);

	   	riskAnalysisResidential.setRiskAnalysis(riskAnalysis);
       
        PDRiskAnalysisHelper.setCalculatedScoresResidential(riskAnalysisResidential, saveResAssessEvent, riskAnalysis);
        
		PDRiskAnalysisHelper.bindDeleteRiskResponses(riskAnalysis);
		
		//Insert new RiskReponses by RiskAnalysisId
	    Enumeration events = saveResAssessEvent.getRequests();
	    PDRiskAnalysisHelper.bindCreateRiskReponses(riskAnalysis.getOID(), events);

		PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamically(riskAnalysis, RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL, false);		

		PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(riskAnalysis);		
	}
   
   /**
    * @param event
    * @roseuid 4357D9B0017F
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4357D9B00181
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 4357D9B00183
    */
   public void update(Object anObject) 
   {
    
   }
}
