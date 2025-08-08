package mojo.km.messaging;

import java.util.*;
import mojo.km.messaging.exception.*;

/**
 * 	This interface provides extensions to the core messaging specific to a queue
 * 	(PTP) messaging model.
 *
 * 	@author: D. Marple
 * @modelguid {EFC88AE9-DE12-4656-AF1A-D721729AEA77}
 */
public interface IQueueMessaging extends ICoreMessaging {
    /**
     * 	 Method add a JMS Message Listener for PTP message retrieval.
     * 	 Creation date: (5/19/00 10:41:47 AM)
     * 	 @param sID java.lang.String
     * @param selectorName java.lang.String
     * 	 @param listener mojo.km.messaging.IMessageListener
     * @throws InvalidSessionException
     * @modelguid {D63B95AA-4E15-4374-9759-9DF642EF71E8}
     */
    public void addMessageListener(String sID, String selectorName, IMessageListener listener) throws InvalidSessionException;

    /**
     * 	  Method closes a JMS session for a PTP messaging session.
     * 	  Creation date: (5/23/00 2:20:13 PM)
     * @modelguid {5BC25293-0927-4ECC-8D02-0B4270935C25}
     */
    public void close();

    /**
     * 	  Method gets all messages in a the PTP queue.
     * 	  @param sID
     * @param selectorName
     * @return
     * @throws InvalidSessionException
     * @modelguid {564152EA-DF6B-4E93-AA38-6445EC240EFB}
     */
    public Enumeration getMessagesInQueue(String sID, String selectorName) throws InvalidSessionException;

    /**
     * 	  Method to return all queues for a given session.
     * 	  @param sID
     * @return
     * @modelguid {41596501-E7AB-4AD2-A540-E560700106BA}
     */
    public Vector getQueuesForSession(String sID);

    /**
     * 	  Method to receive a message from the session queue.
     * 	  @param sID
     * @param selectorName
     * @return
     * @throws InvalidSessionException
     * @throws InvalidMessageTypeException
     * @modelguid {CAA67B8B-752E-4C89-869E-268C384F1197}
     */
    public Object receiveMessage(String sID, String selectorName) throws InvalidSessionException, InvalidMessageTypeException;

    /**
     * 	  Method to receive a message from the session queue with a timeout factor.
     * 	  @param sID
     * @param selectorName
     * @param timeout
     * @return
     * @throws InvalidSessionException
     * @throws InvalidMessageTypeException
     * @modelguid {E8A3F4AB-C2FA-4D7D-B3B7-B002A6572CE2}
     */
    public Object receiveMessage(String sID, String selectorName, long timeout) throws InvalidSessionException,
        InvalidMessageTypeException;

    /**
     * 	  Method to send a JMS message to the queue for a given session.
     * 	  @param sID
     * @param selectorName
     * @param msg
     * @throws InvalidSessionException
     * @throws InvalidMessageTypeException
     * @modelguid {93AB3B22-BE71-43EC-A784-3DB777E3AC9B}
     */
    public void sendMessageToQueue(String sID, String selectorName, Object msg) throws InvalidSessionException,
        InvalidMessageTypeException;
}
