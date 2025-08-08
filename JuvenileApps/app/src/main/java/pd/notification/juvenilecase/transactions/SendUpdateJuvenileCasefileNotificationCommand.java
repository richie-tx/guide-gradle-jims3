package pd.notification.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.SendUpdateJuvenileCasefileNotificationEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;

/**
 * @author asrvastava
 * 
 * This class is used to send notification if new assignment referrals are associated to
 * the supervision casefile
 */
public class SendUpdateJuvenileCasefileNotificationCommand implements ICommand
{

    /**
     * @roseuid 427FAC2D03A9
     */
    public SendUpdateJuvenileCasefileNotificationCommand()
    {

    }

    /**
     * @param event
     * @roseuid 427FA9E100BC
     */
    public void execute(IEvent event)
    {
        SendUpdateJuvenileCasefileNotificationEvent requestEvent = (SendUpdateJuvenileCasefileNotificationEvent) event;

        SendEmailEvent sendEmailEvent = new SendEmailEvent();
        sendEmailEvent.setSubject("New Assignments added to the Casefile");

        StringBuffer message = new StringBuffer();
        message.append("The following referral(s)");
        Iterator referrals = requestEvent.getAssignments().iterator();
        while (referrals.hasNext())
        {
            JuvenileCasefileReferralsResponseEvent referralEvent = (JuvenileCasefileReferralsResponseEvent) referrals.next();
            message.append(referralEvent.getReferralNumber());
            message.append(" on ");
            message.append(referralEvent.getAssignmentDate());
            if (referrals.hasNext())
            {
                message.append(",");
            }
        }
        message.append(" have been added to ");
        message.append(requestEvent.getJuvenileNumber() + " ");
        message.append(requestEvent.getJuvenileFirstName() + " ");
        message.append(requestEvent.getJuvenileLastName() + " ");
        message.append("'s supervision casefile.");

        sendEmailEvent.setMessage(message.toString());
        sendEmailEvent.setFromAddress(requestEvent.getEmailFrom());
        sendEmailEvent.addToAddress(requestEvent.getEmailTo());

        MessageUtil.postRequest(sendEmailEvent);
    }

    /**
     * @param event
     * @roseuid 427FA9E100BE
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 427FA9E100C0
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 427FA9E100C2
     */
    public void update(Object anObject)
    {

    }

}
