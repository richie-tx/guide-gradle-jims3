package pd.notification.juvenilewarrant.transactions;

import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.contact.officer.OfficerProfile;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.notification.PDNotificationHelper;
import naming.PDNotificationConstants;
import messaging.juvenilewarrant.SendProbableCauseWarrantNotificationEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.utilities.DateUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;

/**
 * @author dnikolis
 *  
 */
public class SendProbableCauseWarrantNotificationCommand implements ICommand
{
    private static final String BLANK = " ";

    private static final String PERIOD = ".";

    /**
     * @roseuid 41E5521F00EA
     */
    public SendProbableCauseWarrantNotificationCommand()
    {

    }

    /**
     * @param event
     * @roseuid 41E54F790263
     */
    public void execute(IEvent event)
    {
        SendProbableCauseWarrantNotificationEvent notificationEvent = (SendProbableCauseWarrantNotificationEvent) event;
        String warrantNum = notificationEvent.getWarrantNum();
        JuvenileWarrant warrant = JuvenileWarrant.find(warrantNum);

        boolean sendEmail = validateEvent(notificationEvent);

        if (sendEmail)
        {
            SendEmailEvent sendEmailEvent = new SendEmailEvent();
            String message = createEmailMessage(warrant, notificationEvent);
            sendEmailEvent.setMessage(message);
            StringBuffer buffer = new StringBuffer(100);
        	buffer.append(notificationEvent.getWarrantTypeId());
        	buffer.append(" Warrant #");
        	buffer.append(warrantNum);
        	buffer.append(", NOTIFICATION TYPE ");
        	buffer.append(notificationEvent.getNotificationType());
        	buffer.append(" for ");
        	buffer.append(notificationEvent.getNameLastFirstMiddleSuffix());
        	sendEmailEvent.setSubject(buffer.toString());
            sendEmailEvent.setFromAddress(notificationEvent.getEmailFrom());
            sendEmailEvent.addToAddress(notificationEvent.getEmailTo());
            PDNotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, notificationEvent.getEmailTo());
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
            dispatch.postEvent(sendEmailEvent);
        }
    }

    /**
     * 
     * @param notificationEvent
     * @return String message
     */
    public String createEmailMessage(JuvenileWarrant warrant, SendProbableCauseWarrantNotificationEvent notificationEvent)
    {
        String message = null;
        switch (notificationEvent.getNotificationType())
        {
        case PDNotificationConstants.PC_FILED:
            message = createPCFiledMessage(warrant);
            break;
        case PDNotificationConstants.PC_ACTIVATE:
            message = createPCActivateMessage(warrant, notificationEvent);
            break;
        case PDNotificationConstants.PC_WANTED:
            message = createPCWantedMessage(warrant);
            break;
        default:
            break;
        }

        return message;
    }

    /**
     * @param notificationEvent
     * @return String message
     */
    private String createPCWantedMessage(JuvenileWarrant warrant)
    {
        StringBuffer message = new StringBuffer();
        message.append("A Probable Cause Warrant #");
        message.append(warrant.getWarrantNum());
        message.append(BLANK);
        message.append("has been issued for ");
        message.append(warrant.getNameFirstMiddleLastSuffix());
        message.append(".  If located contact Juvenile Probation Intake at 713-512-4100.");
        return message.toString();
    }

    /**
     * @param notificationEvent
     * @return String message
     */
    private String createPCActivateMessage(JuvenileWarrant warrant, SendProbableCauseWarrantNotificationEvent notificationEvent)
    {
        StringBuffer message = new StringBuffer();
        message.append("The Probable Cause Warrant #");
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
     * @return String message
     */
    private String createPCFiledMessage(JuvenileWarrant warrant)
    {
        StringBuffer message = new StringBuffer();
        message.append("A Probable Cause Warrant #");
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
     * @return boolean sendEmail
     */
    private boolean validateEvent(SendProbableCauseWarrantNotificationEvent notificationEvent)
    {
        boolean sendEmail = false;

        switch (notificationEvent.getNotificationType())
        {
        case PDNotificationConstants.PC_FILED:
            sendEmail = true;
            break;
        case PDNotificationConstants.PC_ACTIVATE:
            sendEmail = true;
            break;
        case PDNotificationConstants.PC_WANTED:
            sendEmail = true;
            break;
        default:
            break;
        }
        return sendEmail;
    }

    /**
     * @param event
     * @roseuid 41E54F790265
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 41E54F790271
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 41E5521F00FA
     */
    public void update(Object updateObject)
    {

    }
}