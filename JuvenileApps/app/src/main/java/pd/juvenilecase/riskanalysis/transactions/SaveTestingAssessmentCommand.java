//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\SaveReferralAssessmentCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import java.util.Enumeration;

import messaging.riskanalysis.SaveTestingAssessmentEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.RiskAnalysisConstants;
import pd.exception.ComputationValidationException;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskAnalysis;
import pd.juvenilecase.riskanalysis.RiskAnalysisTest;

public class SaveTestingAssessmentCommand implements ICommand 
{
   
   /**
    * @roseuid 4342C33D0081
    */
   public SaveTestingAssessmentCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 433C3D3D0335
    */
   public void execute(IEvent event) throws ComputationValidationException 
   {
		
	   SaveTestingAssessmentEvent saveTestAssessEvent = (SaveTestingAssessmentEvent)event;
		
		if ( saveTestAssessEvent.isUpdateOverRiddenStatus() ) {
			   
		   if ( (saveTestAssessEvent.getAssessmentID() != null) && (saveTestAssessEvent.getAssessmentID().length() > 0) ) {
			   PDRiskAnalysisHelper.updateTestingRiskAnalysisOverrideStatus(saveTestAssessEvent);
		   }
			   	   
	   } else {
		
		   if (saveTestAssessEvent.isUpdate()) {
			   saveTestingUpdate(saveTestAssessEvent);
		   } else {
			   saveTesting(saveTestAssessEvent);
		   }
		  
	   }
			
   }
   
   /**
    * This will be for updating existing interviews 
	 * @param saveIntAssessEvent
	 * @throws ComputationValidationException
	 */
	private void saveTestingUpdate(SaveTestingAssessmentEvent saveTestAssessEvent)
			throws ComputationValidationException {
		
		//Update Risk Analysis with latest Information
		RiskAnalysis riskAnalysis = RiskAnalysis.find( saveTestAssessEvent.getRiskAnalysisId() );

		riskAnalysis.setCreateUserID(saveTestAssessEvent.getJpoUserID());
		
		if ((saveTestAssessEvent.getModReason() != null) && (saveTestAssessEvent.getModReason().length() > 0)) {
        	riskAnalysis.setModReason(saveTestAssessEvent.getModReason());
        } else {
        	riskAnalysis.setModReason("");
        }
			
		IHome home=new Home();
	   	home.bind(riskAnalysis);
		
	    //Update Risk Analysis Test with latest Information   	
	   	RiskAnalysisTest riskTesting = RiskAnalysisTest.findByRiskAnalysisId( saveTestAssessEvent.getRiskAnalysisId() );
	   	
        //Delete all old RiskAnalysisRecommendations by RiskAnalysisId
	   	PDRiskAnalysisHelper.bindDeleteRiskAnalysisRecommendations(riskAnalysis);
		//Delete all old RiskFinalScores by RiskAnalysisId
	   	PDRiskAnalysisHelper.bindDeleteRiskFinalScores(riskAnalysis);

	   	riskTesting.setRiskAnalysis(riskAnalysis);
       
 		PDRiskAnalysisHelper.setCalculatedScoresTesting(riskTesting, saveTestAssessEvent, riskAnalysis);
        
		PDRiskAnalysisHelper.bindDeleteRiskResponses(riskAnalysis);
		
		//Insert new RiskReponses by RiskAnalysisId
	    Enumeration events = saveTestAssessEvent.getRequests();
	    PDRiskAnalysisHelper.bindCreateRiskReponses(riskAnalysis.getOID(), events);

		PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamically(riskAnalysis, RiskAnalysisConstants.RISK_TYPE_TESTING, false);

		PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(riskAnalysis);
	}

	/**
	 * @param event
	 * @throws ComputationValidationException
	 */
	private void saveTesting(IEvent event) throws ComputationValidationException {
		SaveTestingAssessmentEvent saveTestAssessEvent = (SaveTestingAssessmentEvent)event;
		
		RiskAnalysis riskAnalysis = new RiskAnalysis();
		RiskAnalysisTest riskTesting = new RiskAnalysisTest();
		riskAnalysis.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_TESTING);
		riskAnalysis.setJuvenileNum(saveTestAssessEvent.getJuvenileNum());
		riskAnalysis.setCasefileID(Integer.parseInt(saveTestAssessEvent.getCasefileID()));
		riskAnalysis.setEnteredDate(saveTestAssessEvent.getAssessmentDate());
		riskTesting.setEnteredDate(saveTestAssessEvent.getAssessmentDate());
		IHome home=new Home();
		home.bind(riskAnalysis);
		
		saveTestAssessEvent.setRiskAnalysisId(riskAnalysis.getOID());
		PDRiskAnalysisHelper.setCalculatedScoresTesting(riskTesting, saveTestAssessEvent, riskAnalysis);
	
		PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamically(riskAnalysis, RiskAnalysisConstants.RISK_TYPE_TESTING, false);
		
		riskTesting.setRiskAnalysis(riskAnalysis);
		
		Enumeration events = saveTestAssessEvent.getRequests();
	
		PDRiskAnalysisHelper.bindCreateRiskReponses(riskAnalysis.getOID(), events);

		PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(riskAnalysis);

		JuvenileCasefile myCasefile=JuvenileCasefile.find(saveTestAssessEvent.getCasefileID());
		//commented out for US 14459
		/*if(myCasefile!=null){			
			myCasefile.setIsTestingRiskNeeded(false);
			PDRiskAnalysisHelper.turnRiskFlagOffOnAllCasefiles(riskAnalysis.getJuvenileNum(),riskAnalysis.getAssessmentType(),myCasefile.getSupervisionCategoryId());
		}*/
	}
   
   /**
    * @param event
    * @roseuid 433C3D3D033E
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 433C3D3D0340
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 433C3D3D0342
    */
   public void update(Object anObject) 
   {
    
   }

}
