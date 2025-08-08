package pd.juvenile.transactions;

import java.util.Iterator;

import pd.codetable.person.JuvenileSchoolDistrictCode;
import pd.codetable.person.PDPersonCodeTableHelper;
import messaging.codetable.person.reply.JuvenileSchoolDistrictCodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class GetSchoolDistrictsCommand implements ICommand
{

	/**
	 * @roseuid 42C18211036B
	 */
	public GetSchoolDistrictsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42C181B30139
	 */
	public void execute(IEvent event)
	{
		IDispatch rdispatch = EventManager.getSharedInstance(EventManager.REPLY);
		IHome home = new Home();
		Iterator i = home.findAll(JuvenileSchoolDistrictCode.class);

		while (i.hasNext())
		{
			JuvenileSchoolDistrictCode jsdc = (JuvenileSchoolDistrictCode) i.next();
			if ( !"Y".equalsIgnoreCase( jsdc.getInactiveInd()) ) {
			    
			    JuvenileSchoolDistrictCodeResponseEvent replyEvent =
			    PDPersonCodeTableHelper.getJuvenileSchoolDistrictCodeResponseEvent(jsdc);
			    rdispatch.postEvent(replyEvent);
			}
		}
	}

	/**
	 * @param event
	 * @roseuid 42C181B30149
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42C181B3014B
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 42C18211038A
	 */
	public void update(Object updateObject)
	{

	}
}
