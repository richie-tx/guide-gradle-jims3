package mojo.km.messaging;

import java.io.Serializable;

/**
 *   A class responsible for handling the creation of a error message.
 *   It houses the name and description of the error.
 *
 *   <pre>
 *   Change History:
 *   Author          Date        Explanation
 *   Shannon Ewing  10/18/1999   Added CreateMessage method and doc cleanup.
 *
 *   </pre>
 *
 *   @author Eric A Amundson 1/27/1999
 * @modelguid {DE24100A-FBCA-4A53-B1F2-086E23A8C4E0}
 */
public class ErrorMessage extends Message implements Serializable {
    /**
     * 	 A constant to specify the name of this message.
     * @modelguid {259998AD-14CA-4696-9966-1CA3C3EBADDB}
     */
    public final static String ERROR = "error";

    /**
     * 	 A constant to be used to set the name of the error;
     * @modelguid {BCEC9119-5116-46F2-8DC4-0FB7E0A180C6}
     */
    public final static String NAME = "name";

    /**
     * 	 A constant to be used to set the description of the error;
     * @modelguid {F2B6B57D-BC55-4FD0-A48A-C698CA4ADE80}
     */
    public final static String DESCRIPTION = "Error";

    /**
     * Main constructior. Does nothing.
     * @modelguid {19754101-1109-48E9-B9C6-760EECADF5C6}
     */
    public ErrorMessage() {
    }

    /**
     * Construct a Error message wth the given name and description.
     *
     * @param name The name of the error.
     * @param description The description of the error.
     * @modelguid {A92CE951-2D85-4FAB-9309-FFC8D46D9278}
     */
    public ErrorMessage(String name, String description) {
        addField(NAME, 0);
        addField(DESCRIPTION, 0);
        addValue(NAME, name);
        addValue(DESCRIPTION, description);
    }

    /**
     * Returns a error message.
     *
     * @param name The name of the error.
     * @param description The description of the error.
     * @return Message - The error message.
     * @modelguid {84BD41B4-4386-41E6-AF84-1023D75D8DE6}
     */
    public static IMessage createMessage(String name, String description) {
        ErrorMessage msg = new ErrorMessage(name, description);
        return msg;
    }

    /**
     * Returns a <code>MessageHolder</code> with an error message
     * labeled 'error' inside.
     *
     * @param name The name of the error.
     * @param description The description of the error.
     * @return MessageHolder - A <code>MessageHolder</code> with the error
     *                         message inside.
     * @modelguid {B872BB45-9FAC-4814-8C84-D56ED69892C7}
     */
    public static IMessageHolder createMessageHolder(String name, String description) {
        MessageHolder returnVal = new MessageHolder();
        ErrorMessage msg = (ErrorMessage)createMessage(name, description);
        returnVal.addMessage(ERROR, msg);
        return returnVal;
    }
}
