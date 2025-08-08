/*
 * Created on May 5, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.appshell;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UserEvent {
	private String userID;
	private String userName;
	private String server;

	/**
	 * @return Returns the server.
	 */
	
	
	public UserEvent() {
	}
	
	public UserEvent(String uid, String uname, String serverName) {
		userID = uid;
		userName = uname;
		server = serverName;
	}
	
	/**
	 * @return
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param string
	 */
	public void setUserID(String string) {
		userID = string;
	}

	/**
	 * @param string
	 */
	public void setUserName(String string) {
		userName = string;
	}

	/**
	 * @return Returns the server.
	 */
	public String getServer() {
		return server;
	}
	/**
	 * @param server The server to set.
	 */
	public void setServer(String server) {
		this.server = server;
	}
}
