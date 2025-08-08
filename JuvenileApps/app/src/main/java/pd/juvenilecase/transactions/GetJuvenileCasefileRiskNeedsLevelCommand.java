package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetJuvenileCasefileRiskNeedsLevelEvent;
import messaging.juvenilecase.reply.PactRiskLevelResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.PACTRiskNeedLevel;

public class GetJuvenileCasefileRiskNeedsLevelCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	GetJuvenileCasefileRiskNeedsLevelEvent request = (GetJuvenileCasefileRiskNeedsLevelEvent) event;
	
	if( request.getCaseFileId() != null ) {
	    
	    Iterator riskNeedIter = PACTRiskNeedLevel.findAll("caseFileId", request.getCaseFileId());
		PactRiskLevelResponseEvent replyEvent = new PactRiskLevelResponseEvent();
		while (riskNeedIter.hasNext())
		{
		    PACTRiskNeedLevel rnLevel = (PACTRiskNeedLevel) riskNeedIter.next();
		    replyEvent = rnLevel.valueObj();

		    dispatch.postEvent(replyEvent);

		}
	} else {
	    
	    Iterator riskNeedIter = PACTRiskNeedLevel.findAll("juvenileNumber", request.getJuvenileNum() );
		PactRiskLevelResponseEvent replyEvent = new PactRiskLevelResponseEvent();
		while (riskNeedIter.hasNext())
		{
		    PACTRiskNeedLevel rnLevel = (PACTRiskNeedLevel) riskNeedIter.next();
		    replyEvent = rnLevel.valueObj();

		    dispatch.postEvent(replyEvent);
		}
	}

	
    }

}
