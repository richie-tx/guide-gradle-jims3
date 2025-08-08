package mojo.km.messaging;

import java.io.*;

/**
 *   Responsible for handling the creation of a command message.
 *   Houses the name of a command class and is generally used in a MessageHolder that
 *   is to be serialized before dispatch.
 *
 *   <pre>
 *   Change History:
 *   Author          Date        Explanation
 *
 *   </pre>
 *
 *   @author Matt Pomroy 12/27/1999
 * @modelguid {B21662CD-3D94-40D2-A9C2-61B25FCDD62E}
 */
public class CommandMessage extends Message implements Serializable {
////
//     /**
//      * CommandMessage default constructor.
//      */
// private CommandMessage() {
//         super();
//     }


    /**
     * CommandMessage constructor comment.
     * @param aCommandClass
     * @modelguid {9CB812AF-F9D4-4253-B100-3EBF7CF066B8}
     */
    public CommandMessage(String aCommandClass) {
        addField("Command Class", 0);
        addValue("Command Class", aCommandClass);
    }
}
