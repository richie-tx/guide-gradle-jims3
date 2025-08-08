package pd.productionsupport.transactions;

import java.util.Iterator;

import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefile;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.NoJuvenileCasefilesResponseEvent;
import messaging.productionsupport.GetProductionSupportCasefilesByJuvEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetProductionSupportCasefilesByJuvCommand implements ICommand 
{
    public void execute(IEvent event) {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetProductionSupportCasefilesByJuvEvent casefileEvent = (GetProductionSupportCasefilesByJuvEvent) event;
	Iterator<JuvenileCasefile>casefileIter = JuvenileCasefile.findAll("juvenileId", casefileEvent.getJuvenileNumber() );
	if (casefileIter.hasNext())
	{
		while (casefileIter.hasNext())
		{
			JuvenileCasefile casefile = (JuvenileCasefile) casefileIter.next();
			JuvenileCasefileResponseEvent casefileResponse =
				JuvenileCaseHelper.getJuvenileCasefileResponse(casefile);
			dispatch.postEvent(casefileResponse);
		}
	}
	else
	{
		NoJuvenileCasefilesResponseEvent none = new NoJuvenileCasefilesResponseEvent();
		none.setMessage("No casefiles found for Juvenile Number: " + casefileEvent.getJuvenileNumber());
		dispatch.postEvent(none);
	}
    }
}
