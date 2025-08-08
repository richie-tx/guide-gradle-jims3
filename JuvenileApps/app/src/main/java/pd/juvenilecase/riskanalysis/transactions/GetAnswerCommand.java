package pd.juvenilecase.riskanalysis.transactions;

import mojo.km.messaging.IEvent;
import mojo.km.context.ICommand;
import messaging.riskanalysis.GetAnswerEvent;
import messaging.riskanalysis.reply.GetAnswerResponseEvent;

public class GetAnswerCommand implements ICommand
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
