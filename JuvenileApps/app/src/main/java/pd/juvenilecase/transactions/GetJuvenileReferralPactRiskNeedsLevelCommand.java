package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetJuvenileReferralPactRiskNeedsLevelEvent;
import messaging.juvenilecase.reply.PactRiskLevelResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.PACTRiskNeedLevel;

public class GetJuvenileReferralPactRiskNeedsLevelCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	GetJuvenileReferralPactRiskNeedsLevelEvent request = (GetJuvenileReferralPactRiskNeedsLevelEvent) event;

	Iterator riskNeedIter = PACTRiskNeedLevel.findAll(request);
	PactRiskLevelResponseEvent replyEvent = new PactRiskLevelResponseEvent();
	while (riskNeedIter.hasNext())
	{
	    PACTRiskNeedLevel rnLevel = (PACTRiskNeedLevel) riskNeedIter.next();
	    replyEvent = rnLevel.valueObj();

	    dispatch.postEvent(replyEvent);

	}
    }

}
