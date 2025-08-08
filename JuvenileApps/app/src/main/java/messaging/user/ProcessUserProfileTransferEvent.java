//Source file: C:\\views\\dev\\app\\src\\messaging\\user\\TransferUserProfileEvent.java

package messaging.user;

import java.util.Date;

import mojo.km.messaging.PersistentEvent;

/**
 * @author jmcnabb
 *
 * Event for doing a department transfer on a user profile.
 */
public class ProcessUserProfileTransferEvent extends PersistentEvent
{
	private String logonId;
	private String newDepartmentId;
	private Date transferDate;
	private String transferTime;
	private Date requestDate;
	private String requestTime;

	/**
	 *
	 */
	public ProcessUserProfileTransferEvent()
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
	 * @return String
	 */
	public String getNewDepartmentId()
	{
		return newDepartmentId;
	}

	/**
	 * @return java.util.Date
	 */
	public Date getTransferDate()
	{
		return transferDate;
	}

	/**
	 * @return String
	 */
	public String getTransferTime()
	{
		return transferTime;
	}

	/**
	 * @return java.util.Date
	 */
	public Date getRequestDate()
	{
		return requestDate;
	}
	
	/**
	 * @return String
	 */
	public String getRequestTime()
	{
		return requestTime;
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
	 * @param newDepartmentId
	 */
	public void setNewDepartmentId(String newDepartmentId)
	{
		this.newDepartmentId = newDepartmentId;
	}

	/**
	 * @param transferDate
	 */
	public void setTransferDate(Date transferDate)
	{
		this.transferDate = transferDate;
	}

	/**
	 * @param transferTime
	 */
	public void setTransferTime(String transferTime)
	{
		this.transferTime = transferTime;
	}

	/**
	 * @param requestDate
	 */
	public void setRequestDate(Date requestDate)
	{
		this.requestDate = requestDate;
	}
	
	/**
	 * @param requestTime
	 */
	public void setRequestTime(String requestTime)
	{
		this.requestTime = requestTime;
	}

}