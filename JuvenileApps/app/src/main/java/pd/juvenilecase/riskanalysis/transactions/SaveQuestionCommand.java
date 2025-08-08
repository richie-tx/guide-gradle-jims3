package pd.juvenilecase.riskanalysis.transactions;

import java.util.List;

import pd.juvenilecase.riskanalysis.PDRiskQuestionHelper;
import pd.juvenilecase.riskanalysis.RiskWeightedResponse;
import pd.juvenilecase.riskanalysis.RiskQuestions;
import mojo.km.messaging.IEvent;
import mojo.km.util.MessageUtil;
import mojo.km.context.ICommand;
import messaging.riskanalysis.SaveQuestionEvent;
import messaging.riskanalysis.reply.SaveQuestionResponseEvent;

public class SaveQuestionCommand implements ICommand
{
	
	public void execute(IEvent anEvent)
	{
		SaveQuestionEvent saveQuestionEvent = (SaveQuestionEvent)anEvent;

		RiskQuestions riskQuestion = PDRiskQuestionHelper.saveRiskQuestion(saveQuestionEvent);	
		
		SaveQuestionResponseEvent sqre = PDRiskQuestionHelper.getSaveQuestionResponseEvent(riskQuestion);

		List <RiskWeightedResponse> answers = PDRiskQuestionHelper.saveAnswers(saveQuestionEvent, riskQuestion.getOID());
		
		List answerResponseEvents = PDRiskQuestionHelper.getSaveAnswerResponseEvents(answers);
		
		MessageUtil.postReply(sqre);
		MessageUtil.postReplies(answerResponseEvents);
		
		saveQuestionEvent = null;
		riskQuestion = null;
		sqre = null;
		answers = null;
		answerResponseEvents = null;
		
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
