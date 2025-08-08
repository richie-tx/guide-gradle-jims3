package pd.notification.juvenilewarrant.transactions;

import naming.PDJuvenileWarrantConstants;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.notification.PDNotificationHelper;
import messaging.juvenilewarrant.SendReleaseDecisionNotificationEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.messaging.mailrequestsevents.SendEmailEvent;

/**
 * @author ryoung
 *
 */
public class SendReleaseDecisionNotificationCommand implements ICommand
{
	private static final String BLANK = " ";

	/**
	 * @roseuid 4223751500DA
	 */
	public SendReleaseDecisionNotificationCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 422372B3030F
	 */
	public void execute(IEvent event)
	{
		SendReleaseDecisionNotificationEvent notificationEvent = (SendReleaseDecisionNotificationEvent) event;
		String warrantNum = notificationEvent.getWarrantNum();
		JuvenileWarrant warrant = JuvenileWarrant.find(warrantNum);

		SendEmailEvent sendEmailEvent = new SendEmailEvent();
		String message = createEmailMessage(warrant);
		sendEmailEvent.setMessage(message);

		StringBuffer buffer = new StringBuffer(100);
    	buffer.append(notificationEvent.getWarrantTypeId());
    	buffer.append(" Warrant #");
    	buffer.append(warrantNum);
    	buffer.append(", ");
    	buffer.append(warrant.getReleaseDecisionId());
    	buffer.append(" for ");
    	buffer.append(notificationEvent.getNameLastFirstMiddleSuffix());
    	sendEmailEvent.setSubject(buffer.toString());
		sendEmailEvent.setFromAddress(notificationEvent.getEmailFrom());
		sendEmailEvent.addToAddress(notificationEvent.getEmailTo());
		PDNotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, notificationEvent.getEmailTo());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(sendEmailEvent);
	}

	/**
	 * @param warrant
	 * @param notificationEvent
	 * @return
	 */
	private String createEmailMessage(JuvenileWarrant warrant)
	{
		// After reviewing the information of [first name last name] on [Warrant number] 
		// it has been determined that the juvenile needs to be brought to the Juvenile 
		// Detention Center at 3540 W. Dallas, Houston, Texas.  Please enter the details 
		// documenting the release to Juvenile Probation.

		// or 

		// After reviewing the information of [first name last name] on [Warrant number]  
		// it has been determined that the juvenile can be released to a parent or guardian.  
		// Please the details documenting the release to Person details.

		StringBuffer message = new StringBuffer();
		message.append("After reviewing the information of ");
		message.append(warrant.getNameFirstMiddleLastSuffix());
		message.append(" on ");
		message.append(warrant.getWarrantNum());
		message.append(BLANK);
		if (PDJuvenileWarrantConstants.RELEASE_DECISION_TO_JUVENILE_PROBATION.equals(warrant.getReleaseDecisionId()))
		{
			message.append(
				"it has been determined that the juvenile needs to be brought to the Juvenile Detention Center at 3540 W. Dallas, Houston, Texas.  Please enter the details documenting the release to Juvenile Probation.");
		}
		else if (PDJuvenileWarrantConstants.RELEASE_DECISION_TO_PERSON.equals(warrant.getReleaseDecisionId()))
		{
			message.append(
				"it has been determined that the juvenile can be released to a parent or guardian.  Please enter the details documenting the release to Person details.");
		}
		return message.toString();
	}

	/**
	 * @param event
	 * @roseuid 422372B30311
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 422372B30313
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 422372B30315
	 */
	public void update(Object anObject)
	{

	}

}
