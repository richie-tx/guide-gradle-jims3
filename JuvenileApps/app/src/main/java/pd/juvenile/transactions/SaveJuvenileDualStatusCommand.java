//Source file: C:\\views\\dev\\app\\src\\pd\\juvenile\\transactions\\SaveJuvenileAbuseCommand.java

package pd.juvenile.transactions;

import pd.juvenile.JuvenileAbuse;
import pd.juvenile.JuvenileDualStatus;
import messaging.juvenile.SaveJuvenileAbuseEvent;
import messaging.juvenile.SaveJuvenileDualStatusEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SaveJuvenileDualStatusCommand implements ICommand
{

	/**
	 * @roseuid 42BC4D3B003A
	 */
	public SaveJuvenileDualStatusCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42BC4BE40339
	 */
	public void execute(IEvent event)
	{
	    SaveJuvenileDualStatusEvent saveEvent = (SaveJuvenileDualStatusEvent) event;
	    JuvenileDualStatus dual = new JuvenileDualStatus();
	    dual.hydrate(saveEvent);
	}

	/**
	 * @param event
	 * @roseuid 42BC4BE4033B
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42BC4BE4033D
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 42BC4D3B0049
	 */
	public void update(Object updateObject)
	{

	}
}
