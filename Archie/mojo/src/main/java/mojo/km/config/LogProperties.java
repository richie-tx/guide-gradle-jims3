package mojo.km.config;

/** @modelguid {EB3858DD-A16B-428F-8136-F89F1FD6EEE5} */
public class LogProperties extends GenericProperties {
	/** @modelguid {63F095A3-1AFB-4ED0-9A45-5B5ED7E5FF83} */
	public static final String REQUEST = "request";
	/** @modelguid {44BA2802-A7D8-43F2-B1C3-F17745EAC13C} */
	public static final String REPLY = "reply";
	/** @modelguid {50D5FA10-493D-47EF-946E-C07D425663C0} */
	public static final String ASYNC = "async";
	/** @modelguid {2752997C-74B3-4816-8C47-B66CB5C6F1F1} */
	public static final String PUBSUB = "pubsub";
	/** @modelguid {36FC4B5F-8234-4624-BB3A-584577391219} */
	public static final String QUEUE = "queue";
	/** @modelguid {C048484F-2261-40F6-9136-1C164E2C430B} */
	public static final String CONTEXT = "context";

	/** @modelguid {6433236D-063D-4BC7-94B6-6E1D6F54BCB9} */
	public static LogProperties getInstance() {
		ServerProperties lServer = ServerProperties.getInstance();
		if (lServer != null) { 
			return lServer.getLogProperties();
		} else {
			return null;
		}
	}
    
	/** @modelguid {06EDB32C-6222-4BE5-B6B3-5C508688AAAD} */
	public static LogProperties getInstance(String aServerName) {
		ServerProperties lServer = ServerProperties.getInstance(aServerName);
		if (lServer != null) { 
			return lServer.getLogProperties();
		} else {
			return null;
		}
	}
	
}