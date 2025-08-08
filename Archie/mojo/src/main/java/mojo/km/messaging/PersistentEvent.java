/*
 * Created on Oct 27, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.messaging;

import java.net.InetAddress;
import java.net.*;
import java.security.Principal;
import mojo.km.config.MojoProperties;
import mojo.km.context.ContextManager;
import mojo.km.persistence.PersistentObject;
import mojo.km.service.SessionService;

/**
 * @author eamundson
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PersistentEvent  extends RequestEvent implements IEvent, Principal{
   /*
   Interface required by receiving container.
   */

	/**
	 * Listener topic.
	 *     @return hash code.
	 * @modelguid {1CDC6466-F4BD-4EF7-A89B-B502D61D807F}
	 */
	public String hashKey() {
		StringBuffer aStr = new StringBuffer(50);
		aStr.append(service).append("::").append(this.getClass().getName()).toString();
		return aStr.toString();
	}

   /*
   Interface required by receiving container.
   */

	/**
	 * Access the service name.
	 *     @return event publishing topic
	 * @modelguid {3913A21A-FAA1-497C-BA67-9E03BB28BAB4}
	 */
	public String getTopic() {
		return service;
	}

	/**
	 *     Set the service the event is to be associated with.
	 *     @param aService - event topic.
	 * @modelguid {95963F79-EA37-4EDB-8499-20A8F5B357E9}
	 */
	public void setTopic(String aService) {
		service = aService;
	}

	/**
	 * set source id of request.
	 * @modelguid {465837D5-AF5B-4DEB-8762-1553E8F238A5}
	 */
	public void setSource(Object aSource) {
		sourceID = "" + aSource.hashCode();
	}

	/**
	 * Return source ID.
	 * @modelguid {F1663719-5763-41B1-B52E-122F837D34EF}
	 */
	public String getSourceID()
	{
		return sourceID;
	}


	/**
	 * Properties that houses the String to indicate what context is originating the event. (Accessor)
	 *     @return host URL.
	 * @modelguid {50C81D24-02A8-4116-94B1-E556160B015C}
	 */
	public String getOriginator() {
		return hostID;
	}

	/**
	 * Construct a request event with the given service
	 *     @param _service - server name.
	 * @modelguid {37635FD0-C922-4D06-8E75-DDAED4F1B42A}
	 */
	public PersistentEvent(String _service) {
		init(_service);
	}

	/** Default constructor 
	 * @modelguid {FE26FFC4-E7DA-48FF-A8CE-9BE33781B7BC}
	 */
	public PersistentEvent() {
		init(null);
	}
    
	/*
	 * Initialization function called by constructors. 
	 */
	private void init(String service)
	{
		userID = ContextManager.getSession().getUserID();
		role = SessionService.getRole();
		
		if(service != null)
		{
			this.service = service;
			try 
			{
				hostID = InetAddress.getLocalHost().getHostAddress() + ":8000";
			} 
			catch (UnknownHostException e) 
			{
				//TODO: this should do something! Long run this code
				// should probably be moved out of the constructor call
				// path
			}			
		}
		else
		{
			String lServiceName = MojoProperties.getInstance().getServiceNameForEvent(this); 
			setTopic(lServiceName);
			String serverName = MojoProperties.getInstance().getServerForService(lServiceName);
			setServer(serverName);
		}	
	}

	/**
	 * Access the user name property.
	 *    @return user id.
	 * @modelguid {4383D7A6-495E-4BC1-B907-FCB22D04171E}
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * Set the value of the server context name.
	 *    @param name - server logical name, from Naming.ServerNames.
	 * @modelguid {19BF024A-CB69-4234-9963-D74C71182E05}
	 */
	public void setServer(String name) {
		mServer = name;
	}

	/**
	 * Return the server context name.
	 *    @return server logical name
	 *    @reference Naming.ServerNames
	 * @modelguid {F29D0ACB-C504-41F0-9CC4-1C233D8808F5}
	 */
	public String getServer() {
		return mServer;
	}

	/**
	 * Compares this principal to the specified object.
	 *    @param another - object to compare to
	 *    @return true if objects are the same.
	 */
/*
	public boolean equals(Object another) {
		return false;
	}
	*/

	/**
	 * Returns the name of this principal.
	 *    @return name of requesting user.
	 * @modelguid {2C634646-FBF2-4C29-89BD-5D709B7EE18F}
	 */
	public String getName() {
		return service;
	}

	/**
	 * 
	 * return hashcode.
	 *    @return hash code.
	 * @modelguid {1D0C3128-21C0-4F8F-A8EB-619CCFA17A86}
	 */
	public int hashcode() {
		return hashKey().hashCode();
	}


	/** String representation.
	@return string representation of this object.
	 * @modelguid {7F4BD4CF-FC9A-4F68-853B-0F12470CE391}
	*/
	public String toString() {
		return hashKey();
	}
	/**
	@param userID - name of user.
	 * @modelguid {8203D722-0C0C-4E31-8F27-E041B1D08F45}
	*/
	public void setUserID(String userID) { this.userID = userID; }

	/**
	 * Set the value of the current role for the user.
	 *    @param aRole - role under which the user logged into the application.
	 * @modelguid {69896DAD-820A-4449-8F55-13AD62CAC546}
	 */
	public void setRole(String aRole) {
		role = aRole;
	}

	/**
	 * Return the current role for the user.
	 *    @return role under which the user logged into the application
	 * @modelguid {53235FA5-1C2C-42CA-97B7-8F5BBC553F2E}
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @return 
	 * @modelguid {CC35C9F7-639C-44D4-97A5-0B349112CD2F}
	 */
	public String getWorkflowID(){ return workflowID; }

	/**
	 * @param workflowID 
	 * @modelguid {1A640F3A-D6BC-4F1D-A638-EA3911CFF236}
	 */
	public void setWorkflowID(String workflowID){ this.workflowID = workflowID; }

	/** Property that has the value of the service the event is being sent to.  (Topic) 
	 * @modelguid {F3B53BF5-BB54-403B-94F1-9BF3FF99B71F}
	 */
	private String service;
	/** @modelguid {FF59C7EA-670A-4E9E-820A-883EC910952A} */
	private String hostID = null;

	/** Id of the requesting client or user. 
	 * @modelguid {19CF45ED-113C-454D-827F-C6288F7D9BD0}
	 */
	protected String userID;
	/** @modelguid {AD564E78-4B70-4A2B-B8CB-D7F7F9D6235C} */
	protected String role;
	/** @modelguid {7B03408D-E58D-46D8-AF61-7F72AB4A938D} */
	private String mServer = "";
	/** assigned by the WorkflowManager if event is part of a workflow 
	 * @modelguid {AAAC7921-A3BB-4A0E-ACE4-5C1C88884B2D}
	 */
	private String workflowID;
	/** @modelguid {605853CA-7EA8-4837-A723-890DD6C9E2C5} */
	private String sourceID = "None";
}
