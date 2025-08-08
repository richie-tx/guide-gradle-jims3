//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\GetAllRiskAssessmentsCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import naming.RiskAnalysisConstants;

import messaging.juvenilecase.reply.RiskAssessmentListResponseEvent;
import messaging.riskanalysis.GetAllRiskAssessmentsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import mojo.km.util.MessageUtil;
import pd.codetable.Code;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskAnalysis;

public class GetAllRiskAssessmentsCommand implements ICommand 
{
   
   /**
    * @roseuid 4342C2F201B0
    */
   public GetAllRiskAssessmentsCommand() 
   {
   }
   
   /**
    * @param event
    * @roseuid 433C3D3E005C
    */
   public void execute(IEvent event) throws Exception
   {
		GetAllRiskAssessmentsEvent reqEvent = (GetAllRiskAssessmentsEvent) event;
		Iterator iter = RiskAnalysis.findAllByJuvenileNum(reqEvent.getJuvenileNumber());
		List <RiskAnalysis> risks = CollectionUtil.iteratorToList(iter);
		RiskAnalysis risk = null;
		RiskAssessmentListResponseEvent riskAssessList = null;
		List responseList = new ArrayList();
		//IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		Date effectiveDate = sdformat.parse("2021-02-15");
		Map assessmentTypeMap = new HashMap();
		if (risks.size() > 0){
			Collection c = Code.findAll(RiskAnalysisConstants.JUV_RISK_ASSESSMENT_TYPE);
			List <Code> codes = CollectionUtil.iteratorToList(c.iterator());
			Code aCode = null;
			for (int i = 0; i < codes.size(); i++) {
				aCode = codes.get(i);
				assessmentTypeMap.put(aCode.getCode(), aCode.getDescription());
			}
		}
		//while(iter.hasNext())
		for (int i = 0; i < risks.size(); i++) 
		{
			//RiskAnalysis risk = (RiskAnalysis)iter.next();
		    	
			risk = risks.get(i);
			riskAssessList = new RiskAssessmentListResponseEvent();
			riskAssessList.setAssessmentDate(risk.getEnteredDate());
			riskAssessList.setAssessmentID(risk.getOID());
			riskAssessList.setAssessmentType(risk.getAssessmentType());
			riskAssessList.setAssessmentTypeDesc( (String) assessmentTypeMap.get(risk.getAssessmentType()));
			if ( risk.getEnteredDate().compareTo( effectiveDate ) >= 0
				&& RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL.equalsIgnoreCase(risk.getAssessmentType())){
			    riskAssessList.setEffectiveDate( true );
			    
			}
			//riskAssessList.setRecommendation(risk.getRecommendation());
			riskAssessList.setCasefileId(String.valueOf(risk.getCasefileID()));
			riskAssessList.setCompleted(risk.isCompleted());
			
			riskAssessList.setJuvenileNumber(risk.getJuvenileNum());
			riskAssessList.setCreateDate(risk.getEnteredDate());
			
			/* List riskRecommendationResponseEvents = new ArrayList();
			Collection recommendations = risk.getRecommendations();
			Collection finalscores = risk.getFinalScores();
			Iterator<RiskFinalScore> iteFinalscores = finalscores.iterator();
			while(iteFinalscores.hasNext()) 
			{
				RiskFinalScore riskFinalScore = iteFinalscores.next();
				
				Iterator<RiskAnalysisRecommendation> iterRecommendations = recommendations.iterator();
				while(iterRecommendations.hasNext())
				{
					RiskAnalysisRecommendation riskAnalysisRecommendation = iterRecommendations.next();
					RiskRecommendation riskRecommend = riskAnalysisRecommendation.getRecommendation();
					
					if (riskFinalScore.getRiskResultGroup().getDescription()
							.equalsIgnoreCase(riskRecommend.getRiskResultGroup().getDescription())) 
					{
						RiskRecommendationResponseEvent riskRecommendScore = new RiskRecommendationResponseEvent();
						riskRecommendScore.setRiskAnalysisRecommendation(riskRecommend.getRecommendation());
						riskRecommendScore.setRiskAnalysisScore(riskFinalScore.getFinalScore());
						riskRecommendScore.setRiskAnalysisId(risk.getOID().toString());
						riskRecommendScore.setResultGroup(riskRecommend.getRiskResultGroup().getDescription());
						riskRecommendationResponseEvents.add(riskRecommendScore);
					}
				}
			}*/
			List riskRecommendationResponseEvents = PDRiskAnalysisHelper.retrieveRiskRecommendationResponseEvents(risk);
			
			riskAssessList.setRecommendations(riskRecommendationResponseEvents);
			
			//dispatch.postEvent(riskAssessList);
			responseList.add(riskAssessList);
		}
		MessageUtil.postReplies(responseList);
   }
   
   /**
    * @param event
    * @roseuid 433C3D3E005E
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 433C3D3E0060
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 433C3D3E0065
    */
   public void update(Object anObject) 
   {
    
   }
}
