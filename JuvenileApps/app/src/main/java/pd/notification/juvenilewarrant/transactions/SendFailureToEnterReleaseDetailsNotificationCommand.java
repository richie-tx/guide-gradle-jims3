/*
 * Created on Jan 3, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.notification.juvenilewarrant.transactions;

import naming.UIConstants;
import pd.contact.officer.OfficerProfile;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.notification.PDNotificationHelper;
import messaging.contact.to.PhoneNumberBean;
import messaging.juvenilewarrant.SendFailureToEnterReleaseDetailsNotificationEvent;
import messaging.scheduling.UnregisterTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;

/**
 * @author jfisher
 *
 */
public class SendFailureToEnterReleaseDetailsNotificationCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent anEvent) throws Exception
	{
		SendFailureToEnterReleaseDetailsNotificationEvent event =
			(SendFailureToEnterReleaseDetailsNotificationEvent) anEvent;

		String warrantNum = event.getWarrantNum();
		JuvenileWarrant warrant = JuvenileWarrant.find(warrantNum);

		if (this.validateEvent(warrant) == true)
		{
			SendEmailEvent sendEmailEvent = new SendEmailEvent();

			String message = this.createEmailMessage(warrant, event);
			sendEmailEvent.setMessage(message);
			StringBuffer buffer = new StringBuffer(100);
        	buffer.append(warrant.getWarrantTypeId());
        	buffer.append(" Warrant #");
        	buffer.append(warrantNum);
        	buffer.append(", ");
        	buffer.append(warrant.getReleaseDecisionId());
        	buffer.append(" for ");
        	buffer.append(warrant.getNameLastFirstMiddleSuffix());
        	sendEmailEvent.setSubject(buffer.toString());
			sendEmailEvent.setFromAddress(event.getEmailFrom());
			PDNotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, event.getEmailTo());

			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(sendEmailEvent);
		}
		else
		{
			this.unregister(event);
		}
	}

	private void unregister(SendFailureToEnterReleaseDetailsNotificationEvent event)
	{
		String taskName = event.getTaskName();
		UnregisterTaskEvent unRegTaskEvent = new UnregisterTaskEvent();
		unRegTaskEvent.setTaskName(taskName);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(unRegTaskEvent);
	}

	/**
	 * @param warrant
	 * @param event
	 * @return
	 */
	private String createEmailMessage(JuvenileWarrant warrant, SendFailureToEnterReleaseDetailsNotificationEvent event)
	{
		StringBuffer buffer = new StringBuffer();

		buffer.append("The details for the release decision, ");
		buffer.append(warrant.getReleaseDecision().getDescription());
		buffer.append(", from Juvenile Probation Intake on ");

		//warrant.getReleaseDecisionTimeStamp()
		String releaseDecisionDateString = DateUtil.dateToString(warrant.getReleaseDecisionTimeStamp(), UIConstants.DATETIME24_FMT_1);
		buffer.append(releaseDecisionDateString);

		buffer.append(", have not been entered for ");
		buffer.append(warrant.getNameFirstMiddleLastSuffix());
		buffer.append(" by the warrant executor.  Contact the warrant executor ");

		// TODO compute contact info

		OfficerProfile officer = warrant.getOfficer();

		if (officer != null && !officer.equals(""))
		{
			buffer.append(officer.getFirstName());
			buffer.append(" ");
			buffer.append(officer.getLastName());		
			
			if (officer.getOfficerPhoneNumbers() != null && !officer.getOfficerPhoneNumbers().equals(""))
			{
			    buffer.append(", ");
			    buffer.append(officer.getOfficerPhoneNumbers());
			}
			if(officer.getEmail() != null && !officer.getEmail().equals(""))
			{
			    buffer.append(", email: ");
			    buffer.append(officer.getEmail());
			}
		}
		else
		{
			buffer.append(event.getUserFirstName());
			buffer.append(" ");
			buffer.append(event.getUserLastName());
			if (event.getUserPhone() != null && !event.getUserPhone().equals(""))
			{
			    PhoneNumberBean userPhoneNumFormatter = new PhoneNumberBean(event.getUserPhone());
			    buffer.append(", work phone: ");
	            buffer.append(userPhoneNumFormatter.getFormattedPhoneNumber());
			}
			if(event.getUserEmail() != null && !event.getUserEmail().equals(""))
			{			
				buffer.append(", email: ");
			    buffer.append(event.getUserEmail());
			}
		}
		buffer.append(".");
		return buffer.toString();
	}

	/**
	 * @param warrant
	 * @return
	 */
	private boolean validateEvent(JuvenileWarrant warrant)
	{
		boolean valid = false;
		if (warrant.getTransferTimeStamp() == null)
		{
			valid = true;
		}
		return valid;
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
		// TODO Auto-generated method stub

	}
}
