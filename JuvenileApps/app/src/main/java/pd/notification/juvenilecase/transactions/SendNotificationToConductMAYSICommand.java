package pd.notification.juvenilecase.transactions;

import messaging.juvenilecase.SendNotificationToConductMAYSIEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.messaging.mailrequestsevents.SendEmailEvent;

/**
 * @author asrvastava
 * 
 * This class is used to send a notification message to conduct the mental health
 * assessment on a juvenile.
 */
public class SendNotificationToConductMAYSICommand implements ICommand
{

    /**
     * @roseuid 427FAC2C02BF
     */
    public SendNotificationToConductMAYSICommand()
    {

    }

    /**
     * @param event
     * @roseuid 427FA9E001F5
     */
    public void execute(IEvent event)
    {
        SendNotificationToConductMAYSIEvent requestEvent = (SendNotificationToConductMAYSIEvent) event;

        SendEmailEvent sendEmailEvent = new SendEmailEvent();
        sendEmailEvent.setSubject("Mental Health assessment notice");

        StringBuffer message = new StringBuffer();
        message.append("You have received a new referral ");
        message.append(requestEvent.getReferralNumber());
        message.append("  on ");
        message.append(requestEvent.getAssignmentAddDate());
        message.append("  which does not have an associated mental health assessment.");

        sendEmailEvent.setMessage(message.toString());
        sendEmailEvent.setFromAddress(requestEvent.getEmailFrom());
        sendEmailEvent.addToAddress(requestEvent.getEmailTo());

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(sendEmailEvent);

    }

    /**
     * @param event
     * @roseuid 427FA9E001F7
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 427FA9E001F9
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 427FA9E001FB
     */
    public void update(Object anObject)
    {

    }

}
