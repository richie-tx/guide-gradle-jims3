//Source file: C:\\views\\dev\\app\\src\\pd\\notification\\juvenilewarrant\\transactions\\SendDirectiveToApprehendNotificationCommand.java

package pd.notification.juvenilewarrant.transactions;

import pd.contact.officer.OfficerProfile;
import pd.contact.user.UserProfile;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.notification.PDNotificationHelper;
import pd.notification.juvenilewarrant.PDJuvenileWarrantNotificationHelper;
import naming.JuvenileNotificationControllerServiceNames;
import naming.PDJuvenileWarrantConstants;
import naming.PDNotificationConstants;
import messaging.contact.to.PhoneNumberBean;
import messaging.juvenilewarrant.SendDirectiveToApprehendNotificationEvent;
import messaging.scheduling.UnregisterTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;

/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SendDirectiveToApprehendNotificationCommand implements ICommand
{
	private static final String BLANK = " ";
	private static final String COMMABLANK = ", ";
	private static final String PERIOD = ".";

	/**
	 * @roseuid 416C399802B5
	 */
	public SendDirectiveToApprehendNotificationCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 4166A42400DC
	 */
	public void execute(IEvent event)
	{
		SendDirectiveToApprehendNotificationEvent notificationEvent = (SendDirectiveToApprehendNotificationEvent) event;
		String warrantNum = notificationEvent.getWarrantNum();
		JuvenileWarrant warrant = JuvenileWarrant.find(warrantNum);
		
		if(this.validateEvent(warrant, notificationEvent))
		{			
			SendEmailEvent sendEmailEvent = new SendEmailEvent();
			PDNotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, notificationEvent.getEmailTo());
			String message = createEmailMessage(warrant, notificationEvent);
			sendEmailEvent.setMessage(message);
			
			//TODO what is the subject? 
			StringBuffer buffer = new StringBuffer(100);
        	buffer.append(warrant.getWarrantTypeId());
        	buffer.append(" Warrant #");
        	buffer.append(warrant.getWarrantNum());
        	buffer.append(", ");
        	buffer.append("DTA");
        	buffer.append(" for ");
        	buffer.append(warrant.getNameLastFirstMiddleSuffix());

			sendEmailEvent.setSubject(buffer.toString());
			sendEmailEvent.setFromAddress(notificationEvent.getEmailFrom());
			
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(sendEmailEvent);
		}
	}
	/**
	 * 
	 * @param notificationEvent
	 * @return String message
	 */
	public String createEmailMessage(
		JuvenileWarrant warrant,
		SendDirectiveToApprehendNotificationEvent notificationEvent)
	{
		String message = null;
		switch (notificationEvent.getNotificationType())
		{
			case PDNotificationConstants.DTA_FILED :
				message = createDTAFiledMessage(warrant);
				break;
			case PDNotificationConstants.DTA_FILED_ACKNOWLEDGEMENT_FAILURE :
				message = createDTAAcknowledgementFailureMessage(warrant);
				break;
			case PDNotificationConstants.DTA_REQUEST_ACTIVATION :
				message = createDTAActivateMessage(warrant, notificationEvent);
				break;
			case PDNotificationConstants.DTA_ACTIVATION_FAILURE :
				message = createDTAActivationFailureMessage(warrant);
				break;
			case PDNotificationConstants.DTA_WANTED :
				message = createDTAWantedMessage(warrant);
				break;
			default :
				break;
		}
		return message;
	}
	
	/**
	 * @param notificationEvent
	 * @return sendEmail
	 */
	private void unregisterFailureToAcknowledge(JuvenileWarrant warrant)
	{		
		//unregister acknowlegement failure that had been posted in the create of the DTA warrant
		IDispatch requestDispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		SendDirectiveToApprehendNotificationEvent notificationEvent =
					(SendDirectiveToApprehendNotificationEvent) EventFactory.getInstance(
						JuvenileNotificationControllerServiceNames.SENDDIRECTIVETOAPPREHENDNOTIFICATION);

		String taskName =
					PDJuvenileWarrantNotificationHelper.getTaskName(
						notificationEvent.getClass().getName(),
						warrant.getWarrantNum(),
						PDNotificationConstants.DTA_FILED_ACKNOWLEDGEMENT_FAILURE);			

		UnregisterTaskEvent unRegTaskEvent = new UnregisterTaskEvent();
		unRegTaskEvent.setTaskName(taskName);
		requestDispatch.postEvent(unRegTaskEvent);
	}

	/**
	 * @param warrant
	 * @param notificationEvent
	 */
	private void unregisterFailureToActivate(JuvenileWarrant warrant)
	{
		//unregister acknowlegement failure that had been posted in the create of the DTA warrant
		IDispatch requestDispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		SendDirectiveToApprehendNotificationEvent notificationEvent =
			(SendDirectiveToApprehendNotificationEvent) EventFactory.getInstance(
				JuvenileNotificationControllerServiceNames.SENDDIRECTIVETOAPPREHENDNOTIFICATION);

		String taskName =
					PDJuvenileWarrantNotificationHelper.getTaskName(
						notificationEvent.getClass().getName(),
						warrant.getWarrantNum(),
						PDNotificationConstants.DTA_FILED_ACKNOWLEDGEMENT_FAILURE);			

		UnregisterTaskEvent unRegTaskEvent = new UnregisterTaskEvent();
		unRegTaskEvent.setTaskName(taskName);
		requestDispatch.postEvent(unRegTaskEvent);
	}

	/**
	 * @param notificationEvent
	 * @return String message
	 */
	private String createDTAWantedMessage(JuvenileWarrant warrant)
	{
		//[Juvenile first name last name] is wanted pursuant to a Directive to Apprehend [warrant number] .  If located contact Juvenile Probation Intake at 713-222-4100.
		StringBuffer message = new StringBuffer();
		message.append(warrant.getNameFirstMiddleLastSuffix());
		message.append(" is wanted pursuant to a Directive to Apprehend ");
		message.append(warrant.getWarrantNum());
		message.append(".  If located contact Juvenile Probation Intake at 713-222-4100.");
		return message.toString();
	}

	/**
	 * @param notificationEvent
	 * @return String message
	 */
	private String createDTAActivationFailureMessage(JuvenileWarrant warrant)
	{
		//The Directive to Apprehend [warrant number] [juveile first name] [juvenile last name] has not been activated within the last 24 hours.  The Directive to Apprehend was acknowledged on [acknowledged date] by the Juvenile Intake of the District Clerk's office.  The LEA officer assigned to this warrant is [LEA Officer first and last name] of [Agency id]. 
		StringBuffer message = new StringBuffer();
		message.append("The Directive to Apprehend ");
		message.append(warrant.getWarrantNum());
		message.append(BLANK);
		message.append(warrant.getNameFirstMiddleLastSuffix());		
		message.append(
			" has not been activated within the last 24 hours.  The Directive to Apprehend was acknowledged on ");
		if ((warrant.getWarrantAcknowledgementDate() != null)
			&& (!(warrant.getWarrantAcknowledgementDate().equals(""))))
		{
			message.append(DateUtil.dateToString(warrant.getWarrantAcknowledgementDate(), "MM/dd/yyyy"));
		}
		message.append(
			" by the Juvenile Intake of the District Clerk's office.  The LEA officer assigned to this warrant is ");
		OfficerProfile officer = warrant.getOfficer();
		if (officer != null)
		{
			message.append(officer.getFirstName());
			message.append(BLANK);
			message.append(officer.getLastName());
			message.append(" of ");
			message.append(officer.getDepartmentId());
		}
		message.append(PERIOD);
		return message.toString();
	}

	/**
	 * @param notificationEvent
	 * @return String message
	 */
	private String createDTAActivateMessage(
		JuvenileWarrant warrant,
		SendDirectiveToApprehendNotificationEvent notificationEvent)
	{
		//A Juvenile Directive to Apprehend [Warrant number] is now ready and needs to be activated.
		//The Directive to Apprehend was filestamped on [filestamp date] at [filestamp time] and acknowledged on [acknowledge date] at [acknowledge time]. 
		StringBuffer message = new StringBuffer();
		message.append("A Juvenile Directive to Apprehend ");
		message.append(notificationEvent.getWarrantNum());
		message.append(" is now ready and needs to be activated.  The Directive to Apprehend was filestamped on ");
		if ((warrant.getFileStampDate() != null) && (!(warrant.getFileStampDate().equals(""))))
		{
			message.append(DateUtil.dateToString(warrant.getFileStampDate(), "MM/dd/yyyy"));
			message.append(" at ");
			message.append(DateUtil.dateToString(warrant.getFileStampDate(), "hh:mm:ss"));
		}
		message.append(" and acknowledged on ");
		if ((warrant.getWarrantAcknowledgementDate() != null)
			&& (!(warrant.getWarrantAcknowledgementDate().equals(""))))
		{
			message.append(DateUtil.dateToString(warrant.getWarrantAcknowledgementDate(), "MM/dd/yyyy"));
			message.append(" at ");
			message.append(DateUtil.dateToString(warrant.getWarrantAcknowledgementDate(), "hh:mm:ss"));
		}
		message.append(PERIOD);
		return message.toString();
	}

	/**
	 * @param notificationEvent
	 * @return String message
	 */
	private String createDTAAcknowledgementFailureMessage(JuvenileWarrant warrant)
	{
		//Directive to Apprehend [warrant number] has not been acknowledged for [juvnenile first and last name] which included an affidavit by [LEA first and last name] [LEA pager number] [LEA cell phone] [LEA phone] 
		StringBuffer message = new StringBuffer();
		message.append("Directive to Apprehend ");
		message.append(warrant.getWarrantNum());
		message.append(" has not been acknowledged for ");
		message.append(warrant.getNameFirstMiddleLastSuffix());
		UserProfile originator = warrant.getWarrantOriginatorUser();
		if (originator != null)
		{
			message.append(", which included an affidavit by ");
			message.append(originator.getFirstName());
			message.append(BLANK);
			message.append(originator.getLastName());
			if(originator.getPager() != null && !originator.getPager().equals(""))
		    {
		        PhoneNumberBean pagerFormatter = new PhoneNumberBean(originator.getPager());
		        message.append(COMMABLANK);
		        message.append("pager: ");
		        message.append(pagerFormatter.getFormattedPhoneNumber());
		    }	  
			if(originator.getCellPhone() != null && !originator.getCellPhone().equals(""))
		    {
			    PhoneNumberBean cellPhoneFormatter = new PhoneNumberBean(originator.getCellPhone());
		        message.append(COMMABLANK);
		        message.append("cell phone: ");
		        message.append(cellPhoneFormatter.getFormattedPhoneNumber());
		    }
			if(originator.getPhoneNum() != null && !originator.getPhoneNum().equals(""))
		    {
		        PhoneNumberBean phoneNumFormatter = new PhoneNumberBean(originator.getPhoneNum());
	            if(originator.getPhoneExt() != null && !originator.getPhoneExt().equals(""))
	            {
	            	phoneNumFormatter.setExtension(originator.getPhoneExt() );
	            }
		        message.append(COMMABLANK);
	            message.append("work phone: ");
	            message.append(phoneNumFormatter.getFormattedPhoneNumber());
		    }
		}
		message.append(PERIOD);
		
		return message.toString();
	}

	/**
	 * @param notificationEvent
	 * @return String message
	 */
	private String createDTAFiledMessage(JuvenileWarrant warrant)
	{
		//A Directive to Apprehend [warrant number] has been witnessed by [LEA officer name] and was file stamped [date filestamp]. Please acknowledge this notice upon his arrival to your office. 
		StringBuffer message = new StringBuffer();
		message.append("A Directive to Apprehend ");
		message.append(warrant.getWarrantNum());
		OfficerProfile officer = warrant.getOfficer();
		if (officer != null)
		{
			message.append("  has been witnessed by ");
			message.append(officer.getFirstName());
			message.append(BLANK);
			message.append(officer.getLastName());
		}
		message.append(" and was file stamped ");
		if ((warrant.getFileStampDate() != null) && (!(warrant.getFileStampDate().equals(""))))
		{
			message.append(DateUtil.dateToString(warrant.getFileStampDate(), "MM/dd/yyyy"));
		}
		message.append(".  Please acknowledge this notice upon his arrival to your office. ");
	
		return message.toString();
	}

	/**
	 * @param notificationEvent
	 * @return sendEmail
	 */
	private boolean validateEvent(JuvenileWarrant warrant, SendDirectiveToApprehendNotificationEvent notificationEvent)
	{
		boolean sendEmail = false;
		switch (notificationEvent.getNotificationType())
		{
			case PDNotificationConstants.DTA_FILED_ACKNOWLEDGEMENT_FAILURE :
			
				// validate to see if the warrant has been acknowledged
				if(PDJuvenileWarrantConstants.WARRANT_ACKNOWLEDGE_PRINTED
									.equals(warrant.getWarrantAcknowledgeStatusId()) == false)
				{
					sendEmail = true;			
				}
				else
				{
					this.unregisterFailureToAcknowledge(warrant);
				}
				break;
				
			case PDNotificationConstants.DTA_ACTIVATION_FAILURE :
				// validate to see if the warrant has been activated
				if(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE
									.equals(warrant.getWarrantActivationStatusId()) == false)
				{
					sendEmail = true;			
				}
				else
				{
					this.unregisterFailureToActivate(warrant);
				}
				break;
				
			default :
				sendEmail = true;
				break;		
		}		
		return sendEmail;
	}	

	/**
	 * @param notificationEvent
	 * @return sendEmail
	 */
/*	private boolean processDTAActivationFailure(
		JuvenileWarrant warrant,
		SendDirectiveToApprehendNotificationEvent notificationEvent)
	{
		boolean sendEmail = true;

		if ((warrant.getWarrantActivationDate() != null) && (!warrant.getWarrantActivationDate().equals("")))
		{
			sendEmail = false;
			//unregister acknowlegement failure that had been posted in the create of the DTA warrant
			IDispatch requestDispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			String taskName = notificationEvent.getTaskName();
			UnregisterTaskEvent unRegTaskEvent = new UnregisterTaskEvent();
			unRegTaskEvent.setTaskName(taskName);
			requestDispatch.postEvent(unRegTaskEvent);
		}

		return sendEmail;
	}	
*/
	/**
	 * @param event
	 * @roseuid 41ACA96601D4
	 */
	public void onRegister(IEvent event)
	{
	}

	/**
	 * @param event
	 * @roseuid 41ACA96601D6
	 */
	public void onUnregister(IEvent event)
	{
	}
	/**
	 * @param anObject
	 * @roseuid 41ACA96601DD
	 */
	public void update(Object anObject)
	{
	}
}