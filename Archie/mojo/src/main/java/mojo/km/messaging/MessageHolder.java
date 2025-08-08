package mojo.km.messaging;

import java.util.*;
import java.io.*;

/**
 *   This class is responsible for housing a heirarchy of messages
 * to be passed between UI and PD partions.  It is the the main data routing facility of the software. <PRE>
 *    Change History:
 *    Author             Date      Explanation
 *    ================ ==========  ===============================================
 *    Shannon Ewing    3/24/1999   Eliminated the reliance of NAS objects making
 *    |                            the class generic. Server specific conversions
 *    |                            are now the responsibility of the approperiate
 *    |                            BusinessContext.class
 *    Shannon Ewing    4/16/1999   Fix JavaDoc comments. Removed dead methods.
 *    Shannon Ewing    5/3/1999    Added getMessages method.
 *    Matt Pomroy	   12/30/1999	Added toString method
 *    Shannon Ewing    1/28/2000   Mod toString method for better String concatenation
 *    |                            Mod the following methods to trap for null params
 *    |                               addMessage
 *    |                               remove
 *    |                               requestMessage
 *    Shannon Ewing    2/1/2000    Mod addMessage to handle a null message param.
 *    Matt Pomroy      4/27/2000   Mod getMessages to return the actual messages in the
 *    |                            this instance as opposed to returning only the names.
 *    Matt Pomroy  	8/03/2000   Added requestErrorMessage
 *
 *    </PRE>
 *   @author Eric A Amundson
 * @modelguid {E5A4007A-1DA1-480D-AC7D-68A7409989BA}
 */
public class MessageHolder extends RequestEvent implements IMessageHolder {
	/** @modelguid {D6CE29F1-F8C9-40F9-A288-48F98A322DAB} */
    private Hashtable hashTable = new Hashtable();

    /**
     * 	   Default contstructor.
     * 	   @modelguid {28179469-53F7-42E4-9114-01AC5EF6BF44}
     */
    public MessageHolder() {
    }

    /**
     * 	  Add a new message to the heirarchy of message housed within this container.
     * 	  @param name  The name of the message.
     * 	  @param message The message to be added.
     * 	  @modelguid {28A52EC8-1A5C-492E-88D7-6A5F9612BD42}
     */
    public void addMessage(Object name, IMessage message) {
        if (message == null) {
            return;
        }
        hashTable.put(name, message);
    }

    /**
     * 	  Add a new message to the heirarchy of message housed within this container.
     * 	  @param name  The name of the message.
     * 	  @param message The message to be added.
     * 	  @modelguid {B49BD176-3071-4334-B66F-9C0DFE331719}
     */
    public void addMessage(String name, IMessage message) {
        if (name == null || name.trim().length() < 1) {
            return;
        }
        if (message == null) {
            return;
        }
        hashTable.put(name, message);
    }

    /**
     * 	  Add a new message to the heirarchy of message housed within this container.
     * 	  @param name  The name of the message.
     * 	  @param message The message to be added.
     * 	  @modelguid {AB2EFCF8-34D6-4540-B64A-488CA3925025}
     */
    public void addMessageHolder(String name, IMessageHolder message) {
        if (name == null || name.trim().length() < 1) {
            return;
        }
        if (message == null) {
            return;
        }
        hashTable.put(name, message);
    }

    /**
     * 	  Clear all messages from the message holder.
     * 	  @modelguid {98D09AA7-A2C7-4F11-A16B-00100ED6B901}
     */
    public void clear() {
        hashTable.clear();
    }

    /**
     * 	 Returns the messages stored in the MessageHolder.
     * 	 @return Vector - The messages
     * @modelguid {8483E20D-D1ED-460F-9FD5-F2D33EAF1428}
     */
    public Vector getMessages() {
        Vector theRetValue = new Vector();
        for (Iterator e = hashTable.values().iterator(); e.hasNext(); ) {
            theRetValue.addElement(e.next());
        }
        return theRetValue;
    }

    /**
     * 	 Returns the names of the messages stored in the MessageHolder.
     * 	 @return Vector - The message names
     * @modelguid {9E4F3543-A2F8-42B1-8706-153703194FAE}
     */
    public Vector getMessageNames() {
        Vector theRetValue = new Vector();
        for (Enumeration e = hashTable.keys(); e.hasMoreElements(); ) {
            theRetValue.addElement(e.nextElement());
        }
        return theRetValue;
    }

    /**
     * 	   Returns the "root" message of a message tree.  This root message is the only message that can reference other
     * messages and not be referenced itself.
     * 	   @return Message The root message.
     * 	   @modelguid {45D9BF2C-F674-4C64-9DFB-8F993465EE94}
     */
    public IMessage getRoot() {
        IMessage tempMessage = null;
        if (hashTable.size() != 0) {
            tempMessage = this.requestMessage("ROOT");
            if (tempMessage == null) {
                Enumeration e = hashTable.elements();
                tempMessage = (Message)e.nextElement();
            }
        }
        return tempMessage;
    }

    /**
     * 	  Remove a specified message from the message holder.
     * 	  @param name The name of message to remove.
     * 	  @modelguid {57050BC5-979F-4E22-963A-447452D93526}
     */
    public void remove(String name) {
        if (name == null || name.trim().length() < 1) {
            return;
        }
        hashTable.remove(name);
    }

    /**
     * Returns an error message contained within this message holder.
     * @return IMessage  An error message if it exists, null otherwise.
     * @modelguid {D5657274-ED9E-4455-A70B-A6D2FB74FCFD}
     */
    public IMessage requestErrorMessage() {
        return requestMessage("error");
    }

    /**
     * 	  Make a request for a message by it's name.
     * 	  @param name The name of the message to retrieve.
     * 	  @return
     * @modelguid {40D61A33-F138-4356-AA99-48356EB4534B}
     */
    public IMessage requestMessage(String name) {
        if (name == null || name.trim().length() < 1) {
            return (Message)null;
        }
        return (Message)hashTable.get(name);
    }

    /**
     * 	  Make a request for a message by it's name.
     * 	  @param name The name of the message to retrieve.
     * 	  @return
     * @modelguid {7FA2ABDC-587C-43D1-A8DE-E61BD258955B}
     */
    public IMessageHolder requestMessageHolder(String name) {
        if (name == null || name.trim().length() < 1) {
            return (IMessageHolder)null;
        }
        return (IMessageHolder)hashTable.get(name);
    }

    /**
     * 	 Produces formatted output of all of the contents in this message holder. Each message is printed along with all of
     * the message's fields and all of the field's element data.
     * 	 @return String - The contents of this message holder. If there are no messages
     * in the message holder, an empty <code>String</code> is returned.
     * @modelguid {1DD38883-6359-47AF-BFB1-A7BCDACD2782}
     */
    public String toString() {
        Vector msgNames = getMessages();
        String lReturn = "";
        for (Enumeration e = hashTable.keys(); e.hasMoreElements(); ) {
            Object msgName = e.nextElement();
            if (msgName instanceof String) {
                lReturn += "Message Name: " + msgName + "\n";
            }
            lReturn += hashTable.get(msgName).toString() + "\n";
        }
        return lReturn;
    }
}
