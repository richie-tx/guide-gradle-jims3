package pd.juvenile.transactions;

import java.util.Iterator;

import messaging.juvenile.GetJuvenileInfoLightEvent;
import messaging.juvenile.reply.JuvenileCoreLightResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileCore;

/**
 * @author sthyagarajan GetJuvenileGangsCommand
 */
public class GetJuvenileInfoLightCommand implements ICommand
{

    /**
     * Constructor.
     */
    public GetJuvenileInfoLightCommand()
    {

    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    @Override
    public void execute(IEvent event) throws Exception
    {
	GetJuvenileInfoLightEvent requestEvent = (GetJuvenileInfoLightEvent) event;

	JuvenileCore core = JuvenileCore.findCore(requestEvent.getJuvenileNum());

	//Get the IDispatch to post to
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	if (core != null)
	{

	    JuvenileCoreLightResponseEvent response = core.valueObject();
	    dispatch.postEvent(response);

	}

    }
}
