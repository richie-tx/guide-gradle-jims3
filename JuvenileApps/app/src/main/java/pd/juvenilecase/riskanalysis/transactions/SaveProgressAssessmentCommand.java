//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\SaveInterviewAssessmentCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import messaging.riskanalysis.RiskQuestionAnswerEvent;
import messaging.riskanalysis.SaveProgressAssessmentEvent;
import messaging.riskanalysis.reply.RiskComputationReponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.PDConstants;
import naming.RiskAnalysisConstants;
import pd.exception.ComputationValidationException;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskAnalysis;


public class SaveProgressAssessmentCommand implements ICommand
{
	/**
	 * @roseuid 4342C32E01E8
	 */
	public SaveProgressAssessmentCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 433C3D3D00DD
	 */
	public void execute(IEvent event) throws ComputationValidationException
	{
		SaveProgressAssessmentEvent saveProgAssessEvent = (SaveProgressAssessmentEvent)event;
		
		if ( saveProgAssessEvent.isUpdateOverRiddenStatus() ) {
			   
			   if ( (saveProgAssessEvent.getAssessmentID() != null) && (saveProgAssessEvent.getAssessmentID().length() > 0) ) {
				   PDRiskAnalysisHelper.updateProgressRiskAnalysisOverrideStatus(saveProgAssessEvent);
			   }
			   	   
		} else {
			
		   if (saveProgAssessEvent.isUpdate()) {
			   saveProgressUpdate(saveProgAssessEvent);
		   } else {
			   saveProgress(saveProgAssessEvent);
		   }
		  
	   }
	}
	
	/**
     * This will be for updating existing interviews 
	 * @param saveIntAssessEvent
	 * @throws ComputationValidationException
	 */
	private void saveProgressUpdate(SaveProgressAssessmentEvent saveProgressAssessEvent) {
		//Update Risk Analysis with latest Information
		RiskAnalysis riskAnalysis = RiskAnalysis.find( saveProgressAssessEvent.getRiskAnalysisId() );

		if ((saveProgressAssessEvent.getModReason() != null) && (saveProgressAssessEvent.getModReason().length() > 0)) {
        	riskAnalysis.setModReason(saveProgressAssessEvent.getModReason());
        } else {
        	riskAnalysis.setModReason(PDConstants.BLANK);
        }
			
		IHome home=new Home();
	   	home.bind(riskAnalysis);
		
        //Delete all old RiskAnalysisRecommendations by RiskAnalysisId
	   	PDRiskAnalysisHelper.bindDeleteRiskAnalysisRecommendations(riskAnalysis);
	   	
		//Delete all old RiskFinalScores by RiskAnalysisId
	   	PDRiskAnalysisHelper.bindDeleteRiskFinalScores(riskAnalysis);

        //PDRiskAnalysisHelper.setCalculatedScoresProgress(saveProgressAssessEvent);
		RiskComputationReponseEvent rcre = PDRiskAnalysisHelper.setCalculatedScores(saveProgressAssessEvent);
       
		PDRiskAnalysisHelper.bindDeleteRiskResponses(riskAnalysis);
		
		//Insert new RiskReponses by RiskAnalysisId
	    Enumeration events = saveProgressAssessEvent.getRequests();
		boolean isCustody = isCustody(events);
		
		events = saveProgressAssessEvent.getRequests();
	    PDRiskAnalysisHelper.bindCreateRiskReponses(riskAnalysis.getOID(), events);

		//PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamically(riskAnalysis, RiskAnalysisConstants.RISK_TYPE_PROGRESS, isCustody);		
		List recommendationList = PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamically(rcre.getTotalScores(), RiskAnalysisConstants.RISK_TYPE_PROGRESS, isCustody);

		//PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(riskAnalysis);
		PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(rcre.getTotalScores(), recommendationList);

	}

	/**
	 * @param event
	 * @throws ComputationValidationException
	 */
	private void saveProgress(IEvent event) {
		SaveProgressAssessmentEvent saveProgressAssessEvent = (SaveProgressAssessmentEvent)event;
		RiskAnalysis riskAnalysis = new RiskAnalysis();
		//RiskAnalysisProgress riskProgress = new RiskAnalysisProgress();

		riskAnalysis.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_PROGRESS);
		riskAnalysis.setJuvenileNum(saveProgressAssessEvent.getJuvenileNum());
		riskAnalysis.setCasefileID(Integer.parseInt(saveProgressAssessEvent.getCasefileID()));
		riskAnalysis.setEnteredDate(saveProgressAssessEvent.getAssessmentDate());
		riskAnalysis.setRiskFormulaId(Integer.parseInt(saveProgressAssessEvent.getRiskFormulaId()));
		
		IHome home = new Home();
		home.bind(riskAnalysis);
		
		Enumeration events = saveProgressAssessEvent.getRequests();
		boolean isCustody = isCustody(events);
		
		saveProgressAssessEvent.setRiskAnalysisId(riskAnalysis.getOID());
		//PDRiskAnalysisHelper.setCalculatedScoresProgress(saveProgressAssessEvent);
		RiskComputationReponseEvent rcre = PDRiskAnalysisHelper.setCalculatedScores(saveProgressAssessEvent);
		
		PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamically(riskAnalysis, RiskAnalysisConstants.RISK_TYPE_PROGRESS, isCustody);
		List recommendationList = PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamically(rcre.getTotalScores(), RiskAnalysisConstants.RISK_TYPE_PROGRESS, isCustody);
		events = saveProgressAssessEvent.getRequests();
		
		PDRiskAnalysisHelper.bindCreateRiskReponses(riskAnalysis.getOID(), events);
		
		//PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(riskAnalysis);
		PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(rcre.getTotalScores(), recommendationList);
		
		JuvenileCasefile myCasefile = JuvenileCasefile.find(saveProgressAssessEvent.getCasefileID());
		if( myCasefile != null )
		{
			myCasefile.setIsProgressRiskNeeded(false);
			PDRiskAnalysisHelper.turnRiskFlagOffOnAllCasefiles(
					riskAnalysis.getJuvenileNum(), riskAnalysis.getAssessmentType(), 
					myCasefile.getSupervisionCategoryId());
		}
	}

	/**
	 * @param events
	 */
	private boolean isCustody(Enumeration<RiskQuestionAnswerEvent> events)
	{
		while( events.hasMoreElements() )
		{
			Object obj = events.nextElement();
			if( obj instanceof RiskQuestionAnswerEvent ) {	
				RiskQuestionAnswerEvent riskReqEvent = (RiskQuestionAnswerEvent)obj;
				if( riskReqEvent.getQuestionNumber() == 2 && 
						riskReqEvent.getAnswerText().equalsIgnoreCase(RiskAnalysisConstants.NO) )
				{
					return false;
				}
			}
		}
		
		return true;
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
