package pd.juvenilecase.riskanalysis.transactions;

import java.util.List;

import pd.juvenilecase.riskanalysis.PDRiskQuestionHelper;
import pd.juvenilecase.riskanalysis.RiskWeightedResponse;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import mojo.km.util.MessageUtil;
import mojo.km.context.ICommand;
import messaging.riskanalysis.GetAnswersEvent;

public class GetAnswersCommand implements ICommand
{
	
	public void execute(IEvent anEvent)
	{
		GetAnswersEvent getAnswersEvent = (GetAnswersEvent)anEvent;
		List <RiskWeightedResponse> answers = CollectionUtil.iteratorToList(RiskWeightedResponse.findAll(getAnswersEvent));
		
		//List replies = new ArrayList();
//		RiskWeightedResponse riskWeightedResponse = null;
//		GetAnswerResponseEvent riskAnswersResponseEvents = null;
//		RiskQuestions question =  null;
		
	/*	for (int i = 0; i < answers.size(); i++) {
			riskWeightedResponse = answers.get(i);
			riskAnswersResponseEvents = new GetAnswerResponseEvent();
			riskAnswersResponseEvents.setRiskAnswerId(riskWeightedResponse.getOID());
			riskAnswersResponseEvents.setAction(riskWeightedResponse.getAction());
			riskAnswersResponseEvents.setResponse(riskWeightedResponse.getResponse());
			riskAnswersResponseEvents.setRiskQuestionId(Integer.parseInt(riskWeightedResponse.getRiskQuestionId()));
			riskAnswersResponseEvents.setSuggestedCasePlainDomiainId(riskWeightedResponse.getSuggestedCasePlanDomainId());
			riskAnswersResponseEvents.setWeight(riskWeightedResponse.getWeight());
			riskAnswersResponseEvents.setSortOrder(riskWeightedResponse.getSortOrder());
			riskAnswersResponseEvents.setSubordinateQuestionId(riskWeightedResponse.getSubordinateQuestionId());
			
			if (riskAnswersResponseEvents.getRiskQuestionId() > 0 ) 
			{
				question = RiskQuestions.find(String.valueOf(riskAnswersResponseEvents.getRiskQuestionId()));
				riskAnswersResponseEvents.setRiskQuestionName(question.getQuestionName());
			}
			
			if (riskWeightedResponse.getSubordinateQuestionId() != null
					&& !riskWeightedResponse.getSubordinateQuestionId().equalsIgnoreCase(PDConstants.BLANK)
					&& !riskWeightedResponse.getSubordinateQuestionId().equals("0")) 
			{
				question = RiskQuestions.find(riskAnswersResponseEvents.getSubordinateQuestionId());
				riskAnswersResponseEvents.setSubordinateQuestionName(question.getQuestionName());
			}
			
			riskAnswersResponseEvents.setAnswerEntryDate(RiskAnalysisConstants.DATE_FORMAT.format(riskWeightedResponse.getCreateTimestamp()));
			
			replies.add( riskAnswersResponseEvents );
		}
*/		
		List replies = PDRiskQuestionHelper.getAnswerResponseEvents(answers);
		
		MessageUtil.postReplies(replies);
		
		getAnswersEvent = null;
		answers = null;
		replies = null;
//		riskWeightedResponse = null;
//		riskAnswersResponseEvents = null;
//		question = null;
	}

	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
}
