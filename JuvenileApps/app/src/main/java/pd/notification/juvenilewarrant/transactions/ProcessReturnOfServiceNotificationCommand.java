//Source file: C:\\views\\dev\\app\\src\\pd\\notification\\juvenilewarrant\\transactions\\ProcessReturnOfServiceNotificationCommand.java

package pd.notification.juvenilewarrant.transactions;


import java.util.Date;

import naming.PDJuvenileWarrantConstants;
import naming.PDNotificationConstants;

import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantService;
import pd.notification.PDNotificationHelper;
import pd.notification.juvenilewarrant.PDJuvenileWarrantNotificationHelper;
import messaging.juvenilewarrant.ProcessReturnOfServiceNotificationEvent;
import messaging.scheduling.UnregisterTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.messaging.mailrequestsevents.SendEmailEvent;

/**
 * 
 * @author asrvastava
 *
 * This command is used to send notifications that return of service
 * needs to be signed.
 */
public class ProcessReturnOfServiceNotificationCommand implements ICommand
{
	/**
	 * @roseuid 4213995C00DA
	 */
	public ProcessReturnOfServiceNotificationCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 421395680215
	 */
	public void execute(IEvent event)
	{
		ProcessReturnOfServiceNotificationEvent requestEvent = (ProcessReturnOfServiceNotificationEvent) event;

		switch (requestEvent.getNotificationType())
		{
			case PDNotificationConstants.PROCESS_RETURN_OF_SERVICE :
				this.sendServiceReturnNeedsFilingNotification(requestEvent);
				break;
			case PDNotificationConstants.REMINDER_RETURN_NOT_SIGNED :
				this.sendReminder(requestEvent);
				break;

			case PDNotificationConstants.EXPIRED_RETUNRED_NOT_SIGNED :
				this.sendExpirationNotification(requestEvent);
				break;
		}
	}

	private void sendServiceReturnNeedsFilingNotification(ProcessReturnOfServiceNotificationEvent requestEvent)
	{
		SendEmailEvent sendEmailEvent = new SendEmailEvent();

		// first service signature notification
		StringBuffer buffer = new StringBuffer(100);
    	buffer.append(requestEvent.getWarrantTypeId());
    	buffer.append(" Warrant #");
    	buffer.append(requestEvent.getWarrantNum());
    	buffer.append(", ");
    	buffer.append("FILE ROS");
    	buffer.append(" for ");
    	buffer.append(requestEvent.getNameLastFirstMiddleSuffix());
    	sendEmailEvent.setSubject(buffer.toString());
//		sendEmailEvent.setSubject("Return of Service Needs to be Filed");

    	StringBuffer message = new StringBuffer();
		message.append("The Juvenile Warrant ");
		message.append(requestEvent.getWarrantNum());
		message.append(" on ");
		message.append(requestEvent.getNameLastFirstMiddleSuffix());
		message.append(" is ready to be filed.");

		sendEmailEvent.setMessage(message.toString());
		sendEmailEvent.setFromAddress(requestEvent.getEmailFrom());
		PDNotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, requestEvent.getEmailTo());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(sendEmailEvent);
	}
	/**
	 * @param requestEvent
	 */
	private void sendReminder(ProcessReturnOfServiceNotificationEvent requestEvent)
	{
		/*EmailAddressEvent emailAddressEvent = new EmailAddressEvent();
		emailAddressEvent.setAddress(requestEvent.getEmailTo());*/

		SendEmailEvent sendEmailEvent = new SendEmailEvent();

		// first service signature notification
		StringBuffer buffer = new StringBuffer(100);
    	buffer.append(requestEvent.getWarrantTypeId());
    	buffer.append(" Warrant #");
    	buffer.append(requestEvent.getWarrantNum());
    	buffer.append(", ");
    	buffer.append("ROS NOT FILED");
    	buffer.append(" for ");
    	buffer.append(requestEvent.getNameLastFirstMiddleSuffix());
    	sendEmailEvent.setSubject(buffer.toString());
//		sendEmailEvent.setSubject("Return of Service has not been Filed");
		
    	StringBuffer message = new StringBuffer();
		message.append("The return of service on Warrant # ");
		message.append(requestEvent.getWarrantNum());
		message.append(" for ");
		message.append(requestEvent.getNameFirstMiddleLastSuffix());
		message.append(" has not been filed.  ");
		if (requestEvent.getExecutorPhoneNumString() != null && !requestEvent.getExecutorPhoneNumString().equals(""))
		{
			message.append("Contact ");
			message.append(requestEvent.getExecutorPhoneNumString());
			message.append(".");
		}

		sendEmailEvent.setMessage(message.toString());
		sendEmailEvent.setFromAddress(requestEvent.getEmailFrom());

		PDNotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, requestEvent.getEmailTo());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(sendEmailEvent);
	}
	/**
	 * 
	 * @param requestEvent
	 */
	private void sendExpirationNotification(ProcessReturnOfServiceNotificationEvent requestEvent)
	{
		this.unregisterTasks(requestEvent);
		
		/*EmailAddressEvent emailAddressEvent = new EmailAddressEvent();
		emailAddressEvent.setAddress(requestEvent.getEmailTo());
*/
		SendEmailEvent sendEmailEvent = new SendEmailEvent();

		// first service signature notification
		StringBuffer buffer = new StringBuffer(100);
    	buffer.append(requestEvent.getWarrantTypeId());
    	buffer.append(" Warrant #");
    	buffer.append(requestEvent.getWarrantNum());
    	buffer.append(", ");
    	buffer.append("ROS SIGNATURE EXPIRED");
    	buffer.append(" for ");
    	buffer.append(requestEvent.getNameLastFirstMiddleSuffix());
    	sendEmailEvent.setSubject(buffer.toString());
//		sendEmailEvent.setSubject("Return of Service Signature has Expired");

		StringBuffer message = new StringBuffer();
		message.append("The return of service on ");
		message.append(requestEvent.getWarrantNum());
		message.append(" for ");
		message.append(requestEvent.getNameFirstMiddleLastSuffix());
		message.append(" has expired.  ");
		if (requestEvent.getExecutorPhoneNumString() != null && !requestEvent.getExecutorPhoneNumString().equals(""))
		{
			message.append("Contact ");
			message.append(requestEvent.getExecutorPhoneNumString());
			message.append(".  ");
		}
		message.append("Return of service must be re-printed by the District Clerk.");

		sendEmailEvent.setMessage(message.toString());
		sendEmailEvent.setFromAddress(requestEvent.getEmailFrom());
		PDNotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, requestEvent.getEmailTo());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(sendEmailEvent);

		JuvenileWarrant warrant = JuvenileWarrant.find(requestEvent.getWarrantNum());
		warrant.setServiceReturnGeneratedStatusId(PDJuvenileWarrantConstants.SERVICE_RETURN_GEN_STATUS_NOTPRINTED);

		// Create new notifications which translates to reseting the timer
		JuvenileWarrantService service = JuvenileWarrantService.findSuccessfulService(warrant.getWarrantNum());		
		Date currentDate = new Date();
		service.setExpirationTimeStamp(currentDate);
	}

	private void unregisterTasks(ProcessReturnOfServiceNotificationEvent event)
	{
		String taskName =
			PDJuvenileWarrantNotificationHelper.getTaskName(
				ProcessReturnOfServiceNotificationEvent.class.getName(),
				event.getWarrantNum(),
				PDNotificationConstants.PROCESS_RETURN_OF_SERVICE);
		this.unregisterTask(taskName);

		taskName =
			PDJuvenileWarrantNotificationHelper.getTaskName(
				ProcessReturnOfServiceNotificationEvent.class.getName(),
				event.getWarrantNum(),
				PDNotificationConstants.REMINDER_RETURN_NOT_SIGNED);
		taskName += "_" + 24;
		this.unregisterTask(taskName);

		taskName =
			PDJuvenileWarrantNotificationHelper.getTaskName(
				ProcessReturnOfServiceNotificationEvent.class.getName(),
				event.getWarrantNum(),
				PDNotificationConstants.REMINDER_RETURN_NOT_SIGNED);
		taskName += "_" + 48;
		this.unregisterTask(taskName);

		taskName =
			PDJuvenileWarrantNotificationHelper.getTaskName(
				ProcessReturnOfServiceNotificationEvent.class.getName(),
				event.getWarrantNum(),
				PDNotificationConstants.REMINDER_RETURN_NOT_SIGNED);
		taskName += "_" + 72;
		this.unregisterTask(taskName);
	}

	private void unregisterTask(String taskName)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		UnregisterTaskEvent unRegTaskEvent = new UnregisterTaskEvent();

		unRegTaskEvent.setTaskName(taskName);
		dispatch.postEvent(unRegTaskEvent);
	}

	/**
	 * @param event
	 * @roseuid 421395680223
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 421395680225
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 4213995C00EA
	 */
	public void update(Object updateObject)
	{

	}
}
