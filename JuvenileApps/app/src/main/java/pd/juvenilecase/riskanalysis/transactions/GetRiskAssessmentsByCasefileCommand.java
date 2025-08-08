//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\GetAllRiskAssessmentsCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import naming.RiskAnalysisConstants;

import messaging.juvenilecase.reply.RiskAssessmentListResponseEvent;
import messaging.riskanalysis.GetRiskAssessmentsByCasefileEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import mojo.km.util.MessageUtil;
import pd.codetable.Code;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskAnalysis;

public class GetRiskAssessmentsByCasefileCommand implements ICommand 
{
   
   /**
    * @roseuid 4342C2F201B0
    */
   public GetRiskAssessmentsByCasefileCommand() 
   {
   }
   
   /**
    * @param event
    * @roseuid 433C3D3E005C
    */
   public void execute(IEvent event) 
   {
		GetRiskAssessmentsByCasefileEvent reqEvent = (GetRiskAssessmentsByCasefileEvent) event;
		Iterator iter = RiskAnalysis.findAllByCasefileID( reqEvent.getCasefileId() );
		List <RiskAnalysis> risks = CollectionUtil.iteratorToList(iter);
		RiskAnalysis risk = null;
		RiskAssessmentListResponseEvent riskAssessList = null;
		List responseList = new ArrayList();

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

		for ( int i = 0; i < risks.size(); i++ ) 
		{
			risk = risks.get(i);
			riskAssessList = new RiskAssessmentListResponseEvent();
			riskAssessList.setAssessmentDate(risk.getEnteredDate());
			riskAssessList.setAssessmentID(risk.getOID());
			riskAssessList.setAssessmentType(risk.getAssessmentType());
			riskAssessList.setAssessmentTypeDesc( (String) assessmentTypeMap.get(risk.getAssessmentType()));
			riskAssessList.setCasefileId(String.valueOf(risk.getCasefileID()));
			riskAssessList.setCompleted(risk.isCompleted());
			riskAssessList.setFinalScore(new Integer(risk.getFinalScore()).toString());
			if(risk.getFinalScores() != null){
				riskAssessList.setFinalScores(risk.getFinalScores().toString());
			}
			
			riskAssessList.setJuvenileNumber(risk.getJuvenileNum());
			riskAssessList.setJpoUserId(risk.getJpoUserID());
			
			
			List riskRecommendationResponseEvents = PDRiskAnalysisHelper.retrieveRiskRecommendationResponseEvents(risk);
			
			riskAssessList.setRecommendations(riskRecommendationResponseEvents);
			//production support 
			if(risk.getCreateUserID() != null){
				riskAssessList.setCreateUserID(risk.getCreateUserID());
			}
			if(risk.getCreateTimestamp() != null){
				riskAssessList.setCreateDate(new Date(risk.getCreateTimestamp().getTime()));
			}
			if(risk.getUpdateUserID() != null){
				riskAssessList.setUpdateUser(risk.getUpdateUserID());
			}
			if(risk.getUpdateTimestamp() != null){
				riskAssessList.setUpdateDate(new Date(risk.getUpdateTimestamp().getTime()));
			}
			if(risk.getCreateJIMS2UserID() != null){
				riskAssessList.setCreateJIMS2UserID(risk.getCreateJIMS2UserID());
			}
			if(risk.getUpdateJIMS2UserID() != null){
				riskAssessList.setUpdateJIMS2UserID(risk.getUpdateJIMS2UserID());
			}
			
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
