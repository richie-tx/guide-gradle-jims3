package pd.juvenilecase.riskanalysis.transactions;

import java.util.Iterator;

import pd.juvenilecase.riskanalysis.RiskWeightedResponse;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import messaging.riskanalysis.RemoveAnswerEvent;
import messaging.riskanalysis.reply.RemoveAnswerResponseEvent;

public class RemoveAnswerCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		
		//Get Event
		RemoveAnswerEvent removeAnswerEvent = (RemoveAnswerEvent)anEvent;
		//IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		RiskWeightedResponse riskWeightedResponse = RiskWeightedResponse.find(removeAnswerEvent.getRiskAnswerId());
		riskWeightedResponse.delete();
		IHome homeRiskWeightedResponse=new Home();
		homeRiskWeightedResponse.bind(riskWeightedResponse);
		
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
