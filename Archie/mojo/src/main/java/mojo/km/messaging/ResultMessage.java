package mojo.km.messaging;

// Source file: D:/projects/StoreProfile/src/com/asf/business/ResultMessage.java

import java.io.*;

/**
 *    A class responsible for handling the creation of a result message. It will house a fields for the name and description.
 * @modelguid {239A4EF0-A276-4484-8975-596C03F3FD0E}
 */
public class ResultMessage extends Message  implements Serializable {
	/**
	 * A constant to specify the name of this message.
	 * @modelguid {40E3521C-2715-40D1-B318-A3BE5CBF849F}
	 */
	public final static String RESULT = "Result";
	/**
	 * A constant to be used to set the 'name' field name;
	 * @modelguid {A465BA18-9080-457D-9144-83018A057248}
	 */
	public final static String NAME = "name";
	/**
	 * A constant to be used to set the 'description' feild name;
	 * @modelguid {8F35D338-4E09-4614-A8F1-5895AABEECDF}
	 */
	public final static String DESCRIPTION = "Result";

	/** @modelguid {51A8BA62-D49B-43C6-B0F8-1535A0B2EDE2} */
	public ResultMessage() { }
	/**
 * 	   Construct a Error message wth the given name and description.
 * 	   @param name
 * @param description
 * 	   @modelguid {5275A37A-F10C-4C26-9759-B1207E5D9DB8}*/
	public ResultMessage( String name ,   String description) {
		addField(NAME, 0);
		addField(DESCRIPTION, 0);
		addValue(NAME, name);
		addValue(DESCRIPTION, description);
	}
	/**
	 * Creates a output message holder with an error message
	 * holder with a error message labeled 'error' inside.
	 * 	   @param name
	 * @param description
	 * @return  
	 * @modelguid {514807CC-EEEC-4BE9-B1C0-C2C3B54EABB0}
	 */
	public static IMessageHolder createMessageHolder(String name, String description) {
		MessageHolder returnVal = new MessageHolder();
		ResultMessage msg = new ResultMessage( name, description );
		returnVal.addMessage(RESULT, msg);
		return returnVal;
	}
}