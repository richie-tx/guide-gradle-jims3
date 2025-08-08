//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilewarrant\\transactions\\UpdateJuvenileReleaseToPersonInfoCommand.java

package pd.juvenilewarrant.transactions;

import naming.PDNotificationConstants;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import pd.notification.juvenilewarrant.PDJuvenileWarrantNotificationHelper;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import messaging.juvenilewarrant.SendFailureToEnterReleaseDetailsNotificationEvent;
import messaging.juvenilewarrant.UpdateJuvenileReleaseToPersonInfoEvent;
import messaging.scheduling.UnregisterTaskEvent;

/**
 * 
 * @author asrvastava
 *
 * This command is used to record juvene's release to person data elements.
 */
public class UpdateJuvenileReleaseToPersonInfoCommand implements ICommand
{

	/**
	 * @roseuid 41FFDACB0271
	 */
	public UpdateJuvenileReleaseToPersonInfoCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 41F95F4402A1
	 */
	public void execute(IEvent event)
	{
		UpdateJuvenileReleaseToPersonInfoEvent requestEvent = (UpdateJuvenileReleaseToPersonInfoEvent) event;
		PDJuvenileWarrantHelper.updateJuvenileReleaseToPersonInfo(requestEvent);
		this.unregisterReleaseInfo(requestEvent);
	}

	public void unregisterReleaseInfo(UpdateJuvenileReleaseToPersonInfoEvent requestEvent)
	{
		SendFailureToEnterReleaseDetailsNotificationEvent notificationEvent =
			new SendFailureToEnterReleaseDetailsNotificationEvent();

		//Unregister the previous task
		String oldTaskName =
			PDJuvenileWarrantNotificationHelper.getTaskName(
				notificationEvent.getClass().getName(),
				requestEvent.getWarrantNum(),
				PDNotificationConstants.NOTIFICATION_FAILURE_TO_ENTER_RELEASE_DETAILS);

		UnregisterTaskEvent unregisterEvent = new UnregisterTaskEvent();
		unregisterEvent.setTaskName(oldTaskName);
		EventManager.getSharedInstance(EventManager.REQUEST).postEvent(unregisterEvent);

	}

	/**
	 * @param event
	 * @roseuid 41F95F4402A3
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 41F95F4402AF
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 41FFDACB0280
	 */
	public void update(Object updateObject)
	{

	}
}
