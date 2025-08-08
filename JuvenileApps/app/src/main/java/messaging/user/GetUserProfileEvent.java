//Source file: C:\\views\\dev\\app\\src\\messaging\\user\\GetUserProfileEvent.java

package messaging.user;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetUserProfileEvent extends RequestEvent
{
	public String logonId;
	public boolean thinResponseInd;
	private String smUserId;
	private String genericType;

	/**
	 * @roseuid 42E67E51014B
	 */
	public GetUserProfileEvent()
	{

	}

	/**
	 * Access method for the logonId property.
	 * 
	 * @return   the current value of the logonId property
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * Sets the value of the logonId property.
	 * 
	 * @param aLogonId the new value of the logonId property
	 */
	public void setLogonId(String aLogonId)
	{
		logonId = aLogonId;
	}

	/**
	 * @return boolean
	 */
	public boolean getThinResponseInd()
	{
		return thinResponseInd;
	}

	/**
	 * @return boolean
	 */
	public boolean isThinResponseRequested()
	{
		return thinResponseInd;
	}

	/**
	 * @param thinResponseInd
	 */
	public void setThinResponseInd(boolean thinResponseInd)
	{
		this.thinResponseInd = thinResponseInd;
	}

	public String getSmUserId()
	{
	    return smUserId;
	}

	public void setSmUserId(String smUserId)
	{
	    this.smUserId = smUserId;
	}

	public String getGenericType()
	{
	    return genericType;
	}

	public void setGenericType(String genericType)
	{
	    this.genericType = genericType;
	}

}