/*
 * Created on Oct 6, 2005
 * 
 */
package pd.juvenilewarrant.transactions;

import java.text.Format;
import java.text.SimpleDateFormat;

import naming.UIConstants;

import pd.juvenilewarrant.JuvenileWarrant;
import pd.notification.PDNotificationHelper;
import messaging.juvenilewarrant.SendReleaseDecisionTimeExpiredNotificationEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.messaging.mailrequestsevents.SendEmailEvent;

/**
 * @author Jim Fisher
 *  
 */
public class SendReleaseDecisionTimeExpiredNotificationCommand implements ICommand
{
    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event) throws Exception
    {
        SendReleaseDecisionTimeExpiredNotificationEvent checkEvent = (SendReleaseDecisionTimeExpiredNotificationEvent) event;
        String warrantNum = checkEvent.getWarrantNum();
		JuvenileWarrant warrant = JuvenileWarrant.find(warrantNum);
        SendEmailEvent sendEmailEvent = new SendEmailEvent();
        sendEmailEvent.setFromAddress(checkEvent.getEmailFrom());
        PDNotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, checkEvent.getEmailTo());
        String message = createEmailMessage(checkEvent);
        sendEmailEvent.setMessage(message);
        
        StringBuffer buffer = new StringBuffer(100);
    	buffer.append(warrant.getWarrantTypeId());
    	buffer.append(" Warrant #");
    	buffer.append(warrantNum);
    	buffer.append(", ");
    	buffer.append("Release Decision Timer Expired");
    	buffer.append(" for ");
    	buffer.append(warrant.getNameLastFirstMiddleSuffix());
    	sendEmailEvent.setSubject(buffer.toString());
//        sendEmailEvent.setSubject("Release Decision Timer Expired");
        sendEmailEvent.setFromAddress(checkEvent.getEmailFrom());

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(sendEmailEvent);
    }

    /**
     * "It has been 48 hours since the release decision [release decision] on [release decision date] [release decision time] for
     * [juvenile first and last name] and the details for the release decision have not been entered by the warrant executor
     * ([officer first and last name] OR [user first and last name] ). Contact the warrant executor's manager ([manager first and
     * last name] OR [(No Suggestions)]) at ([contactphone]."
     * 
     * @param checkEvent
     * @return
     */
    private String createEmailMessage(SendReleaseDecisionTimeExpiredNotificationEvent checkEvent)
    {

        StringBuffer buffer = new StringBuffer();

        buffer.append("It has been 48 hours since the release decision ");
        buffer.append(checkEvent.getReleaseDecision());
        buffer.append(" on ");

        Format fmt = new SimpleDateFormat(UIConstants.DATETIME24_FMT_1);
        buffer.append(fmt.format(checkEvent.getReleaseDecisionDate()));
        buffer.append(" for ");
        buffer.append(checkEvent.getJuvenileFirstName());
        buffer.append(" ");
        buffer.append(checkEvent.getJuvenileLastName());
        buffer.append(" and the details for the release decision have not been entered by the warrant executor ");
        buffer.append(checkEvent.getOfficerFirstName());
        buffer.append(" ");
        buffer.append(checkEvent.getOfficerLastName());
        buffer.append(".\n\n");
        buffer.append("Contact ");

        if (checkEvent.getManagerFirstName() != null && checkEvent.getManagerFirstName().equals("") == false
                && checkEvent.getManagerLastName() != null && checkEvent.getManagerLastName().equals("") == false)
        {
            buffer.append("the warrant executor's manager ");
            buffer.append(checkEvent.getManagerFirstName());
            buffer.append(" ");
            buffer.append(checkEvent.getManagerLastName());
        }
        else
        {
            buffer.append(checkEvent.getContactFirstName());
            buffer.append(" ");
            buffer.append(checkEvent.getContactLastName());
        }
        buffer.append(" at ");

        String phoneNumber = checkEvent.getContactPhone();

        if (phoneNumber == null || phoneNumber.trim().equals(""))
        {
            buffer.append("[contact phone is unavailable]");
        }
        else
        {
            String areaCode = phoneNumber.substring(0, 3);
            String prefix = phoneNumber.substring(3, 6);
            String last4Digit = phoneNumber.substring(6, 10);
            buffer.append(areaCode);
            buffer.append("-");
            buffer.append(prefix);
            buffer.append("-");
            buffer.append(last4Digit);
        }

        buffer.append(".");
        return buffer.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject)
    {
        // TODO Auto-generated method stub

    }

}
