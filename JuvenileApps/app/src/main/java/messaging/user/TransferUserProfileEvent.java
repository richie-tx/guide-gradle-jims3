//Source file: C:\\views\\dev\\app\\src\\messaging\\user\\TransferUserProfileEvent.java

package messaging.user;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author jmcnabb
 *
 * Event for doing a department transfer on a user profile.
 */
public class TransferUserProfileEvent extends RequestEvent
{
	private String logonId;
	private Date transferDate;
	private String transferTime;
	private String newDepartmentId;
	private Date deptTransferRequestDate;
	private String deptTransferRequestTime;

	/**
	 *
	 */
	public TransferUserProfileEvent()
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
	 * @return java.util.Date
	 */
	public Date getDeptTransferRequestDate()
	{
		return deptTransferRequestDate;
	}

	/**
	 * @return String
	 */
	public String getDeptTransferRequestTime()
	{
		return deptTransferRequestTime;
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
	 * @return String
	 */
	public String getNewDepartmentId()
	{
		return newDepartmentId;
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
	 * @param transferDate
	 */
	public void setDeptTransferRequestDate(Date transferReqDate)
	{
		this.deptTransferRequestDate = transferReqDate;
	}

	/**
	 * @param transferTime
	 */
	public void setDeptTransferRequestTime(String transferReqTime)
	{
		this.deptTransferRequestTime = transferReqTime;
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
	 * @param newDepartmentId
	 */
	public void setNewDepartmentId(String newDepartmentId)
	{
		this.newDepartmentId = newDepartmentId;
	}

}