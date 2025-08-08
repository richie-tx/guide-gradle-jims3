package pd.notification.juvenilewarrant.transactions;

import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.contact.officer.OfficerProfile;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.notification.PDNotificationHelper;
import naming.PDNotificationConstants;
import naming.UIConstants;
import messaging.juvenilewarrant.SendJuvenileArrestWarrantNotificationEvent;
import messaging.scheduling.UnregisterTaskEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.utilities.DateUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;

/**
 * @author asrvastava
 *  
 */
public class SendJuvenileArrestWarrantNotificationCommand implements ICommand
{
    private static final String BLANK = " ";

    private static final String PERIOD = ".";

    /**
     * @roseuid 41E552170196
     */
    public SendJuvenileArrestWarrantNotificationCommand()
    {

    }

    /**
     * @param event
     * @roseuid 41BDFB4A01F3
     */
    public void execute(IEvent event)
    {
        SendJuvenileArrestWarrantNotificationEvent notificationEvent = (SendJuvenileArrestWarrantNotificationEvent) event;
        String warrantNum = notificationEvent.getWarrantNum();
        JuvenileWarrant warrant = JuvenileWarrant.find(warrantNum);

        boolean sendEmail = validateEvent(notificationEvent);

        if (sendEmail)
        {
            SendEmailEvent sendEmailEvent = new SendEmailEvent();
            String message = createEmailMessage(warrant, notificationEvent);
            sendEmailEvent.setMessage(message);

            //TODO what is the subject?
            sendEmailEvent.setSubject("Juvenile Arrest Warrant");
            sendEmailEvent.setFromAddress(notificationEvent.getEmailFrom());
            PDNotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, notificationEvent.getEmailTo());
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
            dispatch.postEvent(sendEmailEvent);
        }
        else
        {
        	this.unregister(notificationEvent);
        }
    }

    private void unregister(SendJuvenileArrestWarrantNotificationEvent event)
	{
		String taskName = event.getTaskName();
		UnregisterTaskEvent unRegTaskEvent = new UnregisterTaskEvent();
		unRegTaskEvent.setTaskName(taskName);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(unRegTaskEvent);
	}
    
    /**
     * 
     * @param notificationEvent
     * @return createEmailMessage
     */
    public String createEmailMessage(JuvenileWarrant warrant, SendJuvenileArrestWarrantNotificationEvent notificationEvent)
    {
        String message = null;
        switch (notificationEvent.getNotificationType())
        {
	        case PDNotificationConstants.ARR_FILED:
	            message = createARRFiledMessage(warrant);
	            break;
	        case PDNotificationConstants.ARR_ACTIVATE:
	            message = createARRActivateMessage(warrant, notificationEvent);
	            break;
	        case PDNotificationConstants.ARR_ACTIVATION_FAILURE:
	            message = createARRActivationFailureMessage(warrant, notificationEvent);
	            break;
	        case PDNotificationConstants.ARR_WANTED:
	            message = createARRWantedMessage(warrant);
	            break;
	        case PDNotificationConstants.ARR_NEEDS_ACTIVATION:
	            message = this.createARRActivateMessage(warrant, notificationEvent);
	            break;
	        default:
            break;
        }
        return message;
    }

    /**
     * @param notificationEvent
     * @return createARRWantedMessage
     */
    private String createARRWantedMessage(JuvenileWarrant warrant)
    {
        //[Juvenile first name last name] is wanted pursuant to a Juvenile Arrest Warrant [warrant
        // number] . If located contact Juvenile Probation Intake at 713-512-4100.
        StringBuffer message = new StringBuffer();
        message.append("The juvenile ");
        message.append(warrant.getNameFirstMiddleLastSuffix());
        message.append(" is wanted on Arrest Warrant ");
        message.append(warrant.getWarrantNum());
        message.append(".  Contact the Juvenile Probation Department at 713-512-4000 for processing information if apprehended.");
        return message.toString();
    }

    /**
     * @param notificationEvent
     * @return
     */
    private String createARRActivationFailureMessage(JuvenileWarrant warrant,
            SendJuvenileArrestWarrantNotificationEvent notificationEvent)
    {
        // The arrest warrant [warrantnumber] requested by [Officer LastName FirstName] for
        // [Juvenile FirstName MiddleName LastName] has not been activated in the past 24 hours. The
        // arrest warrant was acknowledged on [WarrantAcknowledeDate] at [WarrantAcknowledgeTime].
        // Please contact the District Clerk's Office and request activation.
        StringBuffer message = new StringBuffer();
        message.append("The arrest warrant ");
        message.append(warrant.getWarrantNum());
        message.append(" requested by ");
        OfficerProfile officer = warrant.getOfficer();
        if (officer != null)
        {
            message.append(officer.getFirstName());
            message.append(BLANK);
            message.append(officer.getLastName());
        }
        message.append(" for ");
        message.append(warrant.getNameFirstMiddleLastSuffix());
        message.append(" has not been activated in the past 24 hours.  The arrest warrant was acknowledged on ");
        if ((warrant.getWarrantAcknowledgementDate() != null) && (warrant.getWarrantAcknowledgementDate().equals("") == false))
        {
            message.append(DateUtil.dateToString(warrant.getWarrantAcknowledgementDate(), UIConstants.DATE_FMT_1));
            message.append(" at ");
            message.append(DateUtil.dateToString(warrant.getWarrantAcknowledgementDate(), UIConstants.TIME24_FMT_1));
        }
        message.append(".  Please contact the District Clerk's Office and request activation.");
        return message.toString();
    }

    /**
     * @param notificationEvent
     * @return createARRActivateMessage
     */
    private String createARRActivateMessage(JuvenileWarrant warrant, SendJuvenileArrestWarrantNotificationEvent notificationEvent)
    {
        //The Arrest Warrant [Warrant number] for juvenile [first name last name] is now ready and
        // needs to be activated.
        StringBuffer message = new StringBuffer();
        message.append("The Arrest Warrant ");
        message.append(notificationEvent.getWarrantNum());
        message.append(BLANK);
        message.append("for juvenile ");
        message.append(warrant.getNameFirstMiddleLastSuffix());
        message.append(" needs to be activated");
        message.append(PERIOD);
        return message.toString();
    }

    /**
     * @param notificationEvent
     * @return createARRFiledMessage
     */
    private String createARRFiledMessage(JuvenileWarrant warrant)
    {
        //A Directive to Apprehend [warrant number] has been witnessed by [LEA officer name] and
        // was file stamped [date filestamp]. Please acknowledge this notice upon his arrival to
        // your office.
        StringBuffer message = new StringBuffer();
        message.append("A Juvenile Arrest Warrant ");
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
     */
    private boolean validateEvent(SendJuvenileArrestWarrantNotificationEvent notificationEvent)
    {
        boolean sendEmail = false;

        switch (notificationEvent.getNotificationType())
        {
        case PDNotificationConstants.ARR_FILED:
            sendEmail = true;
            break;
        case PDNotificationConstants.ARR_ACTIVATE:
            sendEmail = true;
            break;
        case PDNotificationConstants.ARR_ACTIVATION_FAILURE:
            sendEmail = true;
            break;
        case PDNotificationConstants.ARR_WANTED:
            sendEmail = true;
            break;
        case PDNotificationConstants.ARR_NEEDS_ACTIVATION:
            sendEmail = true;
            break;
        default:
            break;
        }
        return sendEmail;
    }

    /**
     * @param event
     * @roseuid 41BDFB4A01F5
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 41BDFB4A01FD
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 41E5521701A5
     */
    public void update(Object updateObject)
    {

    }
}
