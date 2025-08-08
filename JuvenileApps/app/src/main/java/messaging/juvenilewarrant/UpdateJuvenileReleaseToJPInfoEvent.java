//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\UpdateJuvenileReleaseToJPInfoEvent.java

package messaging.juvenilewarrant;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateJuvenileReleaseToJPInfoEvent extends RequestEvent
{
	private Date transferDate;
	private String transferLocation;
	private String warrantNum;
	private String transferOfficerId;
	private String transferOfficerDepartmentId;

	/**
	 * @roseuid 41FFDC0901B5
	 */
	public UpdateJuvenileReleaseToJPInfoEvent()
	{

	}

	/**
	 * @return
	 */
	public String getTransferOfficerId()
	{
		return transferOfficerId;
	}

	/**
	 * @return
	 */
	public Date getTransferDate()
	{
		return transferDate;
	}

	/**
	 * @return
	 */
	public String getTransferLocation()
	{
		return transferLocation;
	}

	/**
	 * @return
	 */
	public String getWarrantNum()
	{
		return warrantNum;
	}

	/**
	 * @param string
	 */
	public void setTransferOfficerId(String string)
	{
		this.transferOfficerId = string;
	}

	/**
	 * @param string
	 */
	public void setTransferDate(Date string)
	{
		transferDate = string;
	}

	/**
	 * @param string
	 */
	public void setTransferLocation(String string)
	{
		transferLocation = string;
	}

	/**
	 * @param string
	 */
	public void setWarrantNum(String string)
	{
		warrantNum = string;
	}

	/**
	 * @return
	 */
	public String getTransferOfficerDepartmentId()
	{
		return transferOfficerDepartmentId;
	}

	/**
	 * @param string
	 */
	public void setTransferOfficerDepartmentId(String string)
	{
		transferOfficerDepartmentId = string;
	}

}
