package mojo.km.messaging;

//Source file: C:/projects/ttww/mojo/messaging/IMessageContainer.java
import java.util.*;

/** @modelguid {82381536-0631-4023-A83A-0FFA700C937C} */
public interface IMessageContainer {
    /**
     *    @param messageIdentifier
     * @param msg
     * @modelguid {62BA6ECC-FAAE-4F1E-8222-1DBEE07AD502}
     */
    public void addMapMessage(String messageIdentifier, IMapMessage msg);

    /**
     * Insert the method's description here.
     * Creation date: (5/16/00 3:13:07 PM)
     * @param name java.lang.String
     * @param msgC mojo.km.messaging.IMessageContainer
     * @modelguid {BA3DCBE5-4B48-403A-98DB-D08DFDE297B6}
     */
    public void addMessageContainer(String name, IMessageContainer msgC);

    /**
     *    @param messageIdentifier
     * @param msg
     * @modelguid {CA19F68D-929C-4798-B6E5-DD4B9AD557DD}
     */
    public void addTextMessage(String messageIdentifier, ITextMessage msg);

    /**
     *    @modelguid {88598904-9484-410C-B88B-FD5305B03527}
     */
    public void clearMessageContainer();

    /**
     *    @return
     * @modelguid {D872E647-5E32-4B97-9D39-05FF9D108055}
     */
    public Vector getMapMessages();

    /**
     * Insert the method's description here.
     * Creation date: (5/17/00 10:03:27 AM)
     * @return <{Vector}>
     * @modelguid {9648D4EA-B353-4F15-A540-08A09A88BEE4}
     */
    public Vector getMessageContainers();

    /**
     *    @return
     * @modelguid {CFE3E2E5-A4EE-4E27-81C7-C7A56C4281FF}
     */
    public IMapMessage getRootMapMessage();

    /**
     * Insert the method's description here.
     * Creation date: (5/17/00 10:02:58 AM)
     * @return <{Vector}>
     * @modelguid {7A0C0A00-1E7B-4758-BFC0-AF34F3BB4593}
     */
    public Vector getTextMessages();

    /**
     *    @param messageIdentifier
     * @modelguid {48B5271B-D8B0-4997-804A-F4222E8516DF}
     */
    public void removeMapMessage(String messageIdentifier);

    /**
     *    @param messageIdentifier
     * @modelguid {A3786318-41E9-470A-9F60-EA453551DDAD}
     */
    public void removeMessageContainer(String messageIdentifier);

    /**
     *    @param messageIdentifier
     * @modelguid {8BB8CCF7-62CB-496E-BF51-6EDB04FD022F}
     */
    public void removeTextMessage(String messageIdentifier);

    /**
     *    @param messageIdentifier
     * @return
     * @modelguid {5ED213C8-6D78-48E9-83A5-71000A23CFD8}
     */
    public IMapMessage requestMapMessage(String messageIdentifier);

    /**
     * Insert the method's description here.
     * Creation date: (5/16/00 3:14:32 PM)
     * @param name java.lang.String
     * @return <{IMessageContainer}>
     * @modelguid {209D3207-9126-413E-8582-030F1359ADD1}
     */
    public IMessageContainer requestMessageContainer(String name);

    /**
     *    @param messageIdentifier
     * @return
     * @modelguid {10C69293-E4E2-464F-9CAD-E158A890BFE0}
     */
    public ITextMessage requestTextMessage(String messageIdentifier);
}
