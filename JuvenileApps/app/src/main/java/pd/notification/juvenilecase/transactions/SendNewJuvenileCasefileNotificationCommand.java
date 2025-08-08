package pd.notification.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.SendNewJuvenileCasefileNotificationEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;

/**
 * @author asrvastava
 * 
 * This class is used to send a notification message when a new juvenile case file is
 * initiated
 */
public class SendNewJuvenileCasefileNotificationCommand implements ICommand
{

    /**
     * @roseuid 427FAC2B00DA
     */
    public SendNewJuvenileCasefileNotificationCommand()
    {

    }

    /**
     * @param event
     * @roseuid 427FA9E10021
     */
    public void execute(IEvent event)
    {
        SendNewJuvenileCasefileNotificationEvent requestEvent = (SendNewJuvenileCasefileNotificationEvent) event;

        SendEmailEvent sendEmailEvent = new SendEmailEvent();
        sendEmailEvent.setSubject("New Juvenile Casefile assigned");

        StringBuffer message = new StringBuffer();
        message.append(requestEvent.getJuvenileFirstName() + " ");
        message.append(requestEvent.getJuvenileLastName() + " ");
        message.append(requestEvent.getJuvenileNumber() + " ");
        message.append(requestEvent.getSupervisionNumber() + " ");
        message.append("has been assigned to ");
        message.append(requestEvent.getJpoFirstName() + " ");
        message.append(requestEvent.getJpoLastName() + ".");
        message.append("This casefile includes the folowwing referrals: ");
        Iterator referrals = requestEvent.getAssignments().iterator();
        while (referrals.hasNext())
        {
            JuvenileCasefileReferralsResponseEvent referralEvent = (JuvenileCasefileReferralsResponseEvent) referrals.next();
            message.append(referralEvent.getReferralNumber());
            if (referrals.hasNext())
            {
                message.append(",");
            }
        }
        message.append(".");
        message.append("The MAYSI indicator is set to" + requestEvent.isMAYSINeeded() + ".");
        message.append("The Title 4e assessment indicator is set to" + requestEvent.isBenefitAssessmentNeeded());
        message.append("The RISK analysis indicator is set to" + requestEvent.isRiskAssessmentNeeded());

        sendEmailEvent.setMessage(message.toString());

        sendEmailEvent.setFromAddress(requestEvent.getEmailFrom());
        sendEmailEvent.addToAddress(requestEvent.getEmailTo());

        MessageUtil.postRequest(sendEmailEvent);
    }

    /**
     * @param event
     * @roseuid 427FA9E10023
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 427FA9E10025
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 427FAC2B00EA
     */
    public void update(Object updateObject)
    {

    }
}
