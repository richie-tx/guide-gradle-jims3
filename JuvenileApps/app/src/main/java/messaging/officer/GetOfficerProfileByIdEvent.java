/*
 * Created on Jan 13, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.officer;

import mojo.km.messaging.RequestEvent;

/**
 * @author dnikolis
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetOfficerProfileByIdEvent extends RequestEvent
{
	private String badgeNum;
	private String departmentId;
	private String otherIdNum;
	/**
	 * @return
	 */
	public String getBadgeNum()
	{
		return badgeNum;
	}

	/**
	 * @return
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	/**
	 * @return
	 */
	public String getOtherIdNum()
	{
		return otherIdNum;
	}

	/**
	 * @param badgeNum
	 */
	public void setBadgeNum(String badgeNum)
	{
		this.badgeNum = badgeNum;
	}

	/**
	 * @param departmentId
	 */
	public void setDepartmentId(String departmentId)
	{
		this.departmentId = departmentId;
	}

	/**
	 * @param otherIdNum
	 */
	public void setOtherIdNum(String otherIdNum)
	{
		this.otherIdNum = otherIdNum;
	}
}