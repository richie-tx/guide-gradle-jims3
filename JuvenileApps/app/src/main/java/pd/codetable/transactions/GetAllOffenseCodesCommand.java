package pd.codetable.transactions;

import java.util.Iterator;

import pd.codetable.criminal.JuvenileOffenseCode;
import ui.common.StringUtil;
import messaging.codetable.GetAllJuvenileOffenseCodesEvent;
import messaging.codetable.GetAllOffenseCodesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetAllOffenseCodesCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	JuvenileCasefileOffenseCodeResponseEvent codeResponse = null;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetAllOffenseCodesEvent request = (GetAllOffenseCodesEvent) event;
	
	Iterator iter = JuvenileOffenseCode.findAll();
	while(iter.hasNext())
	{
	    
	    JuvenileOffenseCode offense = (JuvenileOffenseCode) iter.next();
		codeResponse = offense.valueObject();
		dispatch.postEvent(codeResponse);
	    
	}
	
    }
 
}
