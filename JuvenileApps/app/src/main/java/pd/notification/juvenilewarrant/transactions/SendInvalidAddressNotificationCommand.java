//Source file: C:\\views\\dev\\app\\src\\pd\\notification\\juvenilewarrant\\transactions\\SendInvalidAddressNotificationCommand.java

package pd.notification.juvenilewarrant.transactions;

import pd.contact.user.UserProfile;
import pd.juvenilewarrant.JuvenileWarrant;
import messaging.juvenilewarrant.SendInvalidAddressNotificationEvent;
import messaging.notification.CreateNotificationEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;

/**
 * 
 * @author asrvastava
 * 
 * This command is used to send invalid address notification
 */
public class SendInvalidAddressNotificationCommand implements ICommand
{

    /**
     * @roseuid 420A72DF000F
     */
    public SendInvalidAddressNotificationCommand()
    {
    }

    /**
     * @param event
     * @roseuid 41F95051018C
     */
    public void execute(IEvent event)
    {
        SendInvalidAddressNotificationEvent requestEvent = (SendInvalidAddressNotificationEvent) event;

        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance("CreateNotification");

        notificationEvent.setSubject("Invalid address notification");

        notificationEvent.setNotificationTopic("JW.INVALID.ADDRESS");

        UserProfile warrantOriginator = UserProfile.find(requestEvent.getOriginator());
        notificationEvent.addIdentity("warrantOriginator", warrantOriginator);

        JuvenileWarrant warrant = JuvenileWarrant.find(requestEvent.getWarrantNum());
        notificationEvent.addContentBean(warrant);

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(notificationEvent);
    }

    /**
     * @param event
     * @roseuid 41F950510197
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 41F950510199
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 41F95051019B
     */
    public void update(Object anObject)
    {

    }

}
