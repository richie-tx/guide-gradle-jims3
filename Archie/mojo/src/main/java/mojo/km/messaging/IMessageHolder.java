package mojo.km.messaging;

import java.util.*;

/**
 *   The interface for housing a heirarchy of messages
 *   to be passed between UI and PD partions.
 *
 *   <PRE>
 *   Change History:
 *   Author             Date      Explanation
 *   ================ ==========  ===============================================
 *   Shannon Ewing    5/28/1999   Added methods getRoot and getMessages
 *   Matt Pomroy  	  8/03/2000   Added requestErrorMessage
 *   </PRE>
 *
 *   @author Shannon Ewing 3/24/1999
 * @modelguid {7856F288-498E-4EE9-BAB7-64C45CC357E6}
 */
public interface IMessageHolder extends IEvent {
    /**
     *    Add a new message to the container
     *
     *    @param name
     * @param message
     *    @modelguid {DDB0D50B-63F5-4358-A278-148FEB901543}
     */
    public void addMessage(Object name, IMessage message);

    /**
     * 	   Add a new message to the container
     *
     * 	   @param name
     * @param message
     * 	   @modelguid {D8B1C451-80C6-4D31-926F-1F394AD1EEC7}
     */
    public void addMessage(String name, IMessage message);

    /**
     * 	   Add a new message holder to the container
     *
     * 	   @param name
     * @param message
     * 	   @modelguid {0DC4DCCF-044D-42E4-B7F6-1A8A53002E99}
     */
    public void addMessageHolder(String name, IMessageHolder message);

    /**
     * 	   Clears the container of all messages
     * 	   @modelguid {A0C2A973-14DE-405D-B95F-7FF7601D5B26}
     */
    public void clear();

    /**
     * 	 Returns the message names stored in the MessageHolder.
     *
     * 	 @return Vector - The message names.
     * @modelguid {EDBA9A81-2983-4D18-B539-28ED3BC5CEBF}
     */
    public Vector getMessageNames();

    /**
     * 	 Returns the messages stored in the MessageHolder.
     *
     * 	 @return Vector - The messages.
     * @modelguid {85C38612-5A57-414D-82B9-6F32A645A8F5}
     */
    public Vector getMessages();

    /**
     * 	   Returns the "root" message of a message tree.  This root message is the only
     * 	   message that can reference other messages and not be referenced itself.
     *
     * 	   @return Message - The root message.
     * @modelguid {D0D2960F-3A54-4592-B6B5-C672C5490961}
     */
    public IMessage getRoot();

    /**
     * 	   Deletes the specified message from the container
     *
     * 	   @param name
     * 	   @modelguid {02363C6B-FAFB-4918-BDB8-6333E1CA99E6}
     */
    public void remove(String name);

    /**
     * Returns an error message contained within this message holder.
     *
     * @return IMessage  An error message if it exists, null otherwise.
     * @modelguid {31E79C77-B983-4366-88CD-1E401FE40139}
     */
    public IMessage requestErrorMessage();

    /**
     * 	   Retreives a message from the container
     *
     * 	   @param name
     * 	   @return
     * @retrun Message - The message requested
     * 	   @modelguid {0F573D51-B826-40AA-A914-C5D3156B5C6E}
     */
    public IMessage requestMessage(String name);

    /**
     * 	   Retreives a message from the container
     *
     * 	   @param name
     * 	   @return
     * @retrun Message - The message requested
     * 	   @modelguid {016D727D-12EE-48A2-AE99-D0C69F6FE0BC}
     */
    public IMessageHolder requestMessageHolder(String name);
}
