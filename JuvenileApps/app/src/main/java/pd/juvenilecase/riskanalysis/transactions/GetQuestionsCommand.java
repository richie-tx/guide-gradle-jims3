package pd.juvenilecase.riskanalysis.transactions;

import java.util.ArrayList;
import java.util.List;

import pd.juvenilecase.riskanalysis.PDRiskQuestionHelper;
import pd.juvenilecase.riskanalysis.RiskQuestions;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import mojo.km.util.MessageUtil;
import mojo.km.context.ICommand;
import messaging.riskanalysis.GetQuestionsEvent;
import messaging.riskanalysis.reply.GetQuestionResponseEvent;

public class GetQuestionsCommand implements ICommand
{
	
	public void execute(IEvent anEvent)
	{
		
		GetQuestionsEvent getQuestionsEvent = (GetQuestionsEvent)anEvent;
		List <RiskQuestions> questions = CollectionUtil.iteratorToList(RiskQuestions.findAll(getQuestionsEvent));
		List responses = new ArrayList();
		RiskQuestions riskQuestion = null;
		GetQuestionResponseEvent riskQuestionsResponseEvent = null;
		
		for (int i = 0; i < questions.size(); i++) {

			riskQuestion = questions.get(i);
			riskQuestionsResponseEvent = PDRiskQuestionHelper.getQuestionResponseEvent(riskQuestion);
			responses.add(riskQuestionsResponseEvent);
		}
		
		MessageUtil.postReplies(responses);
		
		getQuestionsEvent = null;
		questions = null;
		responses = null;
		riskQuestion = null;
		riskQuestionsResponseEvent = null;
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
