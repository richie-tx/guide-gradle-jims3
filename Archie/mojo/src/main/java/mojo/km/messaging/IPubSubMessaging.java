package mojo.km.messaging;

import mojo.km.messaging.exception.*;
import java.util.Vector;

/**
 * This interface provides extensions to the core messaging specific to a publish and subscribe messaging model.
 * @modelguid {75432495-12FE-4D40-907B-9EA7BCA688D5}
 */
public interface IPubSubMessaging extends ICoreMessaging {
    /**
     * Insert the method's description here.
     * Creation date: (5/19/00 10:41:38 AM)
     * @param sID java.lang.String
     * @param selectorName java.lang.String
     * @param listener mojo.km.messaging.IMessageListener
     * @throws InvalidSessionException
     * @modelguid {A418855E-95F0-483A-9729-536510D7BCA9}
     */
    public void addMessageListener(String sID, String selectorName, IMessageListener listener) throws InvalidSessionException;

    /**
     * Insert the method's description here.
     * Creation date: (5/19/00 10:41:11 AM)
     * @param sID java.lang.String
     * @param selectorName java.lang.String
     * @param durable boolean
     * @param listener mojo.km.messaging.IMessageListener
     * @throws InvalidSessionException
     * @modelguid {6119E1E6-7200-454D-A716-04F4A5581A24}
     */
    public void addMessageListener(String sID, String selectorName, boolean durable, IMessageListener listener)
        throws InvalidSessionException;

    /**
     * Insert the method's description here.
     * Creation date: (5/23/00 2:19:42 PM)
     * @modelguid {112D5786-0026-4654-87F6-283A4B66FA60}
     */
    public void close();

    /**
     *    @param sID
     * @return
     * @modelguid {BD377644-CB8F-4460-9693-573C52E3983E}
     */
    public Vector getTopicsForSession(String sID);

    /**
     *    @param sID
     * @param selectorName
     * @param msg
     * @throws InvalidSessionException
     * @throws InvalidMessageTypeException
     * @modelguid {50B52F87-1EED-422E-B38F-EAF885F20BB6}
     */
    public void publishMessage(String sID, String selectorName, Object msg) throws InvalidSessionException,
        InvalidMessageTypeException;

    /**
     *    @param sID
     * @param topic
     * @return
     * @throws InvalidSessionException
     * @throws InvalidMessageTypeException
     * @modelguid {C4219A83-8A09-4825-AE18-F432B1B8B6B5}
     */
    public Object receiveMessage(String sID, String topic) throws InvalidSessionException, InvalidMessageTypeException;

    /**
     *    @param sID
     * @param topic
     * @param timeout
     * @return
     * @throws InvalidSessionException
     * @throws InvalidMessageTypeException
     * @modelguid {56991BB5-A4D7-4708-A5DE-FAD9B1DA04CA}
     */
    public Object receiveMessage(String sID, String topic, long timeout) throws InvalidSessionException,
        InvalidMessageTypeException;

    /**
     *    @param sID
     * @param selectorName
     * @param durable
     * @return
     * @throws InvalidSessionException
     * @throws InvalidMessageTypeException
     * @modelguid {47472373-86B1-4E8C-A237-2FC1A2350DDE}
     */
    public Object receiveMessage(String sID, String selectorName, boolean durable) throws InvalidSessionException,
        InvalidMessageTypeException;

    /**
     *    @param sID
     * @param selectorName
     * @param durable
     * @param timeout
     * @return
     * @throws InvalidSessionException
     * @throws InvalidMessageTypeException
     * @modelguid {A9D7D499-5A3B-40F2-ADE2-7A9CC31AEA36}
     */
    public Object receiveMessage(String sID, String selectorName, boolean durable, long timeout) throws InvalidSessionException,
        InvalidMessageTypeException;

    /**
     *    @param sID
     * @param selectorName
     * @throws InvalidSessionException
     * @modelguid {DACE7238-7F4F-425E-89C7-EDD801044D62}
     */
    public void unsubscribe(String sID, String selectorName) throws InvalidSessionException;
}
