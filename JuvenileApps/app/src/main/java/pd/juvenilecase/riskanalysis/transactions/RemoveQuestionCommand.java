package pd.juvenilecase.riskanalysis.transactions;

import java.util.Iterator;

import pd.juvenilecase.riskanalysis.RiskQuestions;
import pd.juvenilecase.riskanalysis.RiskWeightedResponse;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import messaging.riskanalysis.GetAnswersEvent;
import messaging.riskanalysis.RemoveQuestionEvent;
import messaging.riskanalysis.reply.RemoveQuestionResponseEvent;

public class RemoveQuestionCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		//Get Event
		RemoveQuestionEvent removeQuestionEvent = (RemoveQuestionEvent)anEvent;
		//IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		
		
		//Delete Answers
		GetAnswersEvent getAnswersEvent = new GetAnswersEvent();
		getAnswersEvent.setQuestionId(removeQuestionEvent.getRiskQuestionId());
		getAnswersEvent.setReturnAnswersBasedOnQuestionId(true);
		getAnswersEvent.setReturnAnswersWithASubordinateQuestionAttached(false);
		
		Iterator answers = RiskWeightedResponse.findAll(getAnswersEvent);
		while (answers.hasNext())
		{
			RiskWeightedResponse  riskWeightedResponse = (RiskWeightedResponse)answers.next();
			riskWeightedResponse.delete();
			IHome homeRiskWeightedResponse=new Home();
			homeRiskWeightedResponse.bind(riskWeightedResponse);
		}
		
		//Delete Question
		RiskQuestions riskQuestion = RiskQuestions.find(removeQuestionEvent.getRiskQuestionId());
		riskQuestion.delete();
		IHome homeRiskQuestion=new Home();
		homeRiskQuestion.bind(riskQuestion);
		
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
