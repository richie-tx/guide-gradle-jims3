package mojo.km.messaging;

import mojo.km.messaging.exception.*;

/**
 *   Acts as single point of contact for message delievery, reciept, and management.  Message delivery types include
 *   pub/sub and point to point (queue messaging).
 *
 *   <pre>
 *   Change History:
 *   Author          Date        Explanation
 *
 *   </pre>
 *
 *   @author Matt Pomroy 9/15/2000
 * @modelguid {36DEBEAF-A126-406A-B333-75E2414658CD}
 */
public interface IMessageManager {
    /**
     *  Returns a pub sub messaging agent
     *
     *  @return IPubSubMessaging Publish/subscribe messaging agent.
     * @modelguid {E1A63356-450F-4DBB-82FE-3A032A1A9FD6}
     */
    public IPubSubMessaging getPubSubMessenger();

    /**
     *  Returns a queue messaging agent
     *
     *  @param aUrl
     * @param aUserName
     * @param aPassword
     * @param aArgs
     * @return IQueueMessaging  Queue or point to point messaging agent
     * @throws MessagingException
     * @modelguid {56F2929C-DC09-47A4-8BFB-2E1A5C604847}
     */
    public IQueueMessaging getQueueMessenger(String aUrl, String aUserName, String aPassword, String[] aArgs)
        throws MessagingException;
}
