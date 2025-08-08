/*
 * Created on Mar 13, 2006
 *
 */
package mojo.km.notification.transactions;

import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import messaging.notification.SendNotificationEvent;
import messaging.notification.domintf.INotification;
import messaging.notification.to.NotificationBean;

import mojo.km.naming.NotificationConstants;
import mojo.km.notification.Notification;
import mojo.km.notification.NotificationIdentityAddress;
import mojo.km.notification.PendingNotification;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;

/**
 * This supports a single transport delivery (SMTP email), future iterations may include behavior to
 * deliver other transport types (i.e. pager, voicemail, etc...). Notices are not represented here
 * because notices are not pulled in batch as with email but delivered on demand.
 * 
 * @author Jim Fisher
 */
public class PullNotificationsCommand implements ICommand
{
    private static final String INVALID = "INVALID";

    private static final String SEND_EMAIL_NOTIFICATION = "SendEmailNotification";

    private static final String SEND_NOTICE_NOTIFICATION = "SendNoticeNotification";

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event) throws Exception
    {
        this.sendEmailNotifications();

        // other methods may be added (i.e. sendPagerNotifications, etc...)
    }

    /**
     * @param event
     */
    private void sendEmailNotifications()
    {
        // for now, PendingNotification.findAll only returns emails (because mapping queries for
        // emails)
        Iterator n = PendingNotification.findAll();

        SendNotificationEvent notificationEvent = null;
        List destinations = new ArrayList();

        PendingNotification prev = null;

        Set sentIdentities = new HashSet();

        while (n.hasNext())
        {
            PendingNotification notification = (PendingNotification) n.next();

            sentIdentities.add(notification.getDestinationIdentityAddrId());

            if (n.hasNext() == false)
            {
                // end of destination list
                List lastDestination = new ArrayList();

                if (prev != null && notification.getNotificationId().equals(prev.getNotificationId()) == false)
                {
                    // the last notification is different, need to send the prev also
                    this.sendEmail(prev, destinations, sentIdentities);
                }
                else
                {
                    // the last notification is not different, need to include previously collected
                    // destinations
                    lastDestination.addAll(destinations);
                }

                // send the last notification
                lastDestination.add(notification.getDestinationIdentityValue());

                this.sendEmail(notification, lastDestination, sentIdentities);

            }
            else if (prev != null && notification.getNotificationId().equals(prev.getNotificationId()) == false)
            {
                // new notification instance, send out the previous
                this.sendEmail(prev, destinations, sentIdentities);
                destinations = new ArrayList();
                destinations.add(notification.getDestinationIdentityValue());
            }
            else
            {
                destinations.add(notification.getDestinationIdentityValue());
            }

            prev = notification;
        }

        this.updateSent(sentIdentities);
    }

    /**
     * @param sentDestinations
     */
    private void updateSent(Set sentIdentities)
    {

        Set notifications = new HashSet();

        Iterator i = sentIdentities.iterator();
        while (i.hasNext())
        {
            String oid = (String) i.next();
            NotificationIdentityAddress identityAddress = NotificationIdentityAddress.find(oid);

            if (identityAddress.getStatus().equals(NotificationConstants.PENDING))
            {
                notifications.add(identityAddress.getNotificationId());
                identityAddress.setStatus(NotificationConstants.CLOSED);
            }
        }

        i = notifications.iterator();
        while (i.hasNext())
        {
            String notificationId = (String) i.next();
            Notification notification = Notification.find(notificationId);
            if (notification.getStatusCode().equals(NotificationConstants.PENDING))
            {
                notification.setStatusCode(NotificationConstants.CLOSED);
            }
        }
    }

    private void sendEmail(PendingNotification notification, List destinations, Set sentIdentities)
    {
        SendNotificationEvent sendEvent = (SendNotificationEvent) EventFactory
                .getInstance(NotificationControllerSerivceNames.SENDEMAILNOTIFICATION);

        sendEvent.setSourceIdentity(notification.getSourceIdentityValue());
        sendEvent.setDestinationIdentities(destinations);

        Notification notif = Notification.find(notification.getNotificationId());

        INotification notificationBean = new NotificationBean();
        notif.fill(notificationBean);

        sendEvent.setNotification(notificationBean);

        MessageUtil.postRequest(sendEvent);

        sentIdentities.add(notification.getSourceIdentityAddrId());
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
