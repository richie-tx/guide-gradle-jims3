//Source file: C:\\views\\dev\\app\\src\\pd\\notification\\transactions\\SendAgencyTransferNotificationCommand.java

package pd.notification.transactions;

import messaging.notification.SendAgencyTransferNotificationEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SendAgencyTransferNotificationCommand implements ICommand
{

	/**
	@roseuid 4107BFB0032B
	 */
	public SendAgencyTransferNotificationCommand()
	{

	}

	/**
	@param event
	@roseuid 4106B3600281
	 */
	public void execute(IEvent event)
	{
		//TODO Notification needs to be implemented.
		SendAgencyTransferNotificationEvent transferEvent =
			(SendAgencyTransferNotificationEvent) event;
	}

	/**
	@param event
	@roseuid 4106B3600283
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	@param event
	@roseuid 4106B3600285
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	@param updateObject
	@roseuid 4107BFB00335
	 */
	public void update(Object updateObject)
	{

	}
}
