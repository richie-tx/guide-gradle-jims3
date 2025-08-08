/*
 * Created on Mar 9, 2006
 *
 */
package mojo.km.notification.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.notification.CreateNotificationEvent;
import messaging.notification.domintf.ICreateNotification;
import messaging.notification.domintf.INotificationIdentityAddressDef;
import messaging.notification.to.NotificationIdentityAddressDefBean;
import mojo.km.context.ICommand;
import mojo.km.identityaddress.IdentityAddress;
import mojo.km.messaging.IEvent;
import mojo.km.naming.NotificationConstants;
import mojo.km.notification.Notification;
import mojo.km.notification.NotificationDef;
import mojo.km.notification.NotificationIdentityAddressDef;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.Reflection;
import mojo.naming.IdentityAddressConstants;

/**
 * @author Jim Fisher
 *  
 */
public class CreateNotificationCommand implements ICommand
{
    private final long COUNTER_START = 1000000000000L;

    private void associateIdentities(NotificationDef notifDef, ICreateNotification createNotif, Notification notif)
    {
        // TODO This method should be further decomposed

        // Every identity definition will have a context (either specified or generated)
        Map identityDefMap = this.buildIdentityDefMap(notifDef);

        if (identityDefMap == null || identityDefMap.isEmpty())
        {
            this.throwDefNotSpecified(null, notifDef.getTopic());
        }

        Iterator j = identityDefMap.keySet().iterator();
        while (j.hasNext())
        {
            Object key = j.next();

            INotificationIdentityAddressDef identityDef = (INotificationIdentityAddressDef) identityDefMap.get(key);

            IdentityAddress identity = this.resolveIdentity(createNotif, identityDef, key);

            // returns an iterator for potentially resolving a group of identities
            if (identity == null)
            {
                notif.insertIdentityContext(identityDef);
            }
            else
            {
                Iterator i = this.getIdentities(identity).iterator();

                while (i.hasNext())
                {
                    IdentityAddress identityToken = (IdentityAddress) i.next();
                    notif.insertIdentities(identityToken, identityDef);
                }
            }
        }
    }

    private void bindNotification(Notification notification)
    {
        IHome home = new Home();
        home.bind(notification);
    }

    /**
     * @param notifDef
     * @return
     */
    private Map buildIdentityDefMap(NotificationDef notifDef)
    {
        long counter = COUNTER_START;
        Collection identityDefs = notifDef.getIdentityDefs();

        Map identityDefMap = new HashMap();

        // Create lookup of Notification Identity Address Definitions based on context
        Iterator i = identityDefs.iterator();
        while (i.hasNext())
        {
            NotificationIdentityAddressDef identityDef = (NotificationIdentityAddressDef) i.next();
            INotificationIdentityAddressDef identityDefBean = new NotificationIdentityAddressDefBean();
            identityDef.fill(identityDefBean);

            if (identityDef.getContext() == null)
            {
                counter = (long) counter + (long) 1;
                identityDefMap.put(String.valueOf(counter), identityDefBean);
            }
            else
            {
                identityDefMap.put(identityDefBean.getContext(), identityDefBean);
            }
        }
        return identityDefMap;
    }

    public void execute(IEvent event) throws Exception
    {
        CreateNotificationEvent createNotif = (CreateNotificationEvent) event;

        String topic = createNotif.getNotificationTopic();

        NotificationDef notifDef = NotificationDef.findByTopic(topic);

        if (notifDef == null)
        {
            this.throwNotifDefNotSpecified(topic);
        }

        Notification notif = notifDef.createNotification(createNotif);

        this.bindNotification(notif);

        this.associateIdentities(notifDef, createNotif, notif);
    }

    /**
     * @param identity
     * @return
     */
    private Collection getIdentities(IdentityAddress identity)
    {
        Collection identities = new ArrayList();

        if (IdentityAddressConstants.IDENTITY_GROUP.equals(identity.getType()))
        {
            Iterator i = identity.getMembers().iterator();

            while (i.hasNext())
            {
                IdentityAddress member = (IdentityAddress) i.next();
                identities.add(member);
            }
        }
        else
        {
            identities.add(identity);
        }
        return identities;
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

    private IdentityAddress resolveIdentity(ICreateNotification createNotif, INotificationIdentityAddressDef addressDef,
            Object contextObj)
    {
        String value = null;

        if (addressDef.getValue() != null)
        {
            value = addressDef.getValue();
        }
        else
        {
            Object identityBean = createNotif.getIdentities().get(contextObj);
            if (identityBean != null)
            {
                Object beanValue = Reflection.invokeAccessorMethod(identityBean, addressDef.getProperty());
                if (beanValue != null)
                {
                    value = beanValue.toString();
                }
            }
        }

        IdentityAddress address = null;
        if (value != null)
        {
            address = IdentityAddress.identityFactory(value, IdentityAddressConstants.IDENTITY_INDIVIDUAL);
        }
        return address;
    }

    private void throwDefNotSpecified(Object contextObj, String topic)
    {
        String msg;
        if (contextObj == null)
        {
            msg = "Notification definition (" + topic + ") does not have any identities defined.";
        }
        else
        {
            msg = "notification definition (" + topic + ") must have an identity context (" + contextObj + ")";
        }
        throw new IllegalArgumentException(msg);
    }

    /**
     * @param addressDef
     */
    private void throwMissingIdentityBean(String topic, INotificationIdentityAddressDef addressDef, Object contextObj)
    {
        String directionMsg;
        if (NotificationConstants.SEND.equals(addressDef.getDirection()))
        {
            directionMsg = "SEND";
        }
        else if (NotificationConstants.RECEIVE.equals(addressDef.getDirection()))
        {
            directionMsg = "RECEIVE";
        }
        else
        {
            directionMsg = null;
        }

        if (contextObj == null)
        {
            throw new IllegalArgumentException("Notification Definition: " + topic + " not configured with identity value.");
        }
        else if (directionMsg == null)
        {
            System.out.println("Provided context: " + contextObj);
            throw new IllegalArgumentException("Notification Definition: " + topic + " not configured for provided object.");
        }
        else
        {
            throw new IllegalArgumentException("Notification Definition: " + topic + " not configured to " + directionMsg
                    + " for provided object.");
        }
    }

    /**
     * @param topic
     */
    private void throwNotifDefNotSpecified(String topic)
    {
        String msg = "Notification definition (" + topic + ") has not been defined.";
        throw new IllegalArgumentException(msg);
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
