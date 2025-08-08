package pd.juvenilecase.riskanalysis.transactions;

import java.util.Iterator;

import naming.RiskAnalysisConstants;
import pd.juvenilecase.riskanalysis.RiskQuestions;
import mojo.km.messaging.IEvent;
import mojo.km.context.ICommand;
import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
import messaging.riskanalysis.GetQuestionEvent;
import messaging.riskanalysis.reply.GetQuestionResponseEvent;

public class GetQuestionCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		
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
