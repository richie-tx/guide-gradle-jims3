package mojo.km.context;

import java.util.HashMap;
import java.util.Map;

import mojo.km.config.MojoProperties;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.noop.NoReply;

/**
 * Encapsulates client resources managed by the context. The resources bundled here service a thread
 * of execution in a current context.
 */
public class Session implements java.io.Serializable
{
	public static final String CURRENT_CONTEXT = "currentContext";

	public static final String REPLY = "reply";

	public static final String SERVER_NAME = "serverName";

	public static final String SESSIONID = "sessionID";

	public static final String TIMEZONE_OFFSET = "timezoneOffset";

	public static final String USER_FULLNAME = "fullName";

	public static final String USERID = "userID";

	private Map map = new HashMap();

	Session()
	{
	}

	/**
	 * @param aKey
	 * @return
	 */
	public Object get(String aKey)
	{
		return map.get(aKey);
	}

	/**
	 * @return
	 * @modelguid {601CE07D-9AB2-4F8A-9A7A-81CAB62F3DF4}
	 */
	public IEvent getReply()
	{
		return (IEvent) get(REPLY);
	}

	/**
	 * @return
	 * @modelguid {F34F7F7D-1FEE-4DA1-955E-508A2CA59FA4}
	 */
	public String getServerName()
	{
		return (String) get(SERVER_NAME);
	}

	/**
	 * @return
	 * @modelguid {AB591533-FC69-46A5-B18D-E0CE5AA41E68}
	 */
	public String getSessionID()
	{
		return (String) get(SESSIONID);
	}

	/**
	 * @return
	 * @modelguid {128BB34D-41C5-4F63-87AA-D7714E81DBB5}
	 */
	public int getTimeZoneOffset()
	{
		Integer lIntValue = (Integer) get(TIMEZONE_OFFSET);
		return lIntValue == null ? 0 : lIntValue.intValue();
	}

	/**
	 * @return
	 */
	public String getUserID()
	{
		return (String) get(USERID);
	}

	/**
	 * @return
	 */
	public String getUserName()
	{
		return (String) get(USER_FULLNAME);
	}

	/**
	 * @param aKey
	 * @param anObject
	 */
	public void put(String aKey, Object anObject)
	{
		map.put(aKey, anObject);
	}

	/**
	 * @param aKey
	 * @modelguid {13299D60-CB36-4314-9ED5-2ADF5D0D9525}
	 */
	public void remove(String aKey)
	{
		map.remove(aKey);
	}

	/** @modelguid {94AD8269-DF04-4B31-B2CB-86E958101216} */
	private void setReply(IEvent anEvent)
	{
		put(REPLY, anEvent);
	}

	/**
	 * @param aServerName
	 * @modelguid {EC04178D-76D4-4FE9-8CA3-891BA1121423}
	 */
	public void setServerName(String aServerName)
	{
		MojoProperties.getInstance().setActiveServerName(aServerName);
		put(SERVER_NAME, aServerName);
	}

	/**
	 * @param aUserName
	 * @modelguid {F5C6775D-8D83-4C41-8D06-6F90E882ECB1}
	 */
	public void setSessionID(String aUserName)
	{
		put(SESSIONID, aUserName);
	}

	/**
	 * @param aTimeZoneOffset
	 * @modelguid {C1F6113A-DFF1-4934-9985-528A23BE6617}
	 */
	public void setTimeZoneOffset(int aTimeZoneOffset)
	{
		put(TIMEZONE_OFFSET, new Integer(aTimeZoneOffset));
	}

	/**
	 * @param aUserName
	 */
	public void setUserID(String aUserName)
	{
		put(USERID, aUserName);
	}

	/**
	 * @param aServerName
	 */
	public void setUserName(String aUserName)
	{
		MojoProperties.getInstance().setActiveServerName(aUserName);
		put(USER_FULLNAME, aUserName);
	}

	/** @modelguid {6FD69C05-789D-46AD-AD85-57F4E7EAB0C2} */
	public void startReply()
	{
		setReply(new NoReply());
	}
}
