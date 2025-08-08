/*
 * Created on Feb 14, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.supervisionorder.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
 */
public class OrderVersionResponseEvent extends ResponseEvent
{
	private Date activationDate;
	private String lastUserId;
	private String orderTitle;
	private String orderTitleId;
	private String orderVersion;
	private String orderVersionNum;
	private String orderVersionTypeId;
	/**
	 * @return
	 */
	public Date getActivationDate()
	{
		return activationDate;
	}

	/**
	 * @return
	 */
	public String getLastUserId()
	{
		return lastUserId;
	}

	/**
	 * @return
	 */
	public String getOrderTitle()
	{
		return orderTitle;
	}

	/**
	 * @return
	 */
	public String getOrderTitleId()
	{
		return orderTitleId;
	}

	/**
	 * @return
	 */
	public String getOrderVersion()
	{
		return orderVersion;
	}

	/**
	 * @return
	 */
	public String getOrderVersionNum()
	{
		return orderVersionNum;
	}

	/**
	 * @return
	 */
	public String getOrderVersionTypeId()
	{
		return orderVersionTypeId;
	}

	/**
	 * @param aDate
	 */
	public void setActivationDate(Date aDate)
	{
		activationDate = aDate;
	}

	/**
	 * @param aUserId
	 */
	public void setLastUserId(String aUserId)
	{
		lastUserId = aUserId;
	}

	/**
	 * @param anOrderTitle
	 */
	public void setOrderTitle(String anOrderTitle)
	{
		orderTitle = anOrderTitle;
	}

	/**
	 * @param anOrderTitleId
	 */
	public void setOrderTitleId(String anOrderTitleId)
	{
		orderTitleId = anOrderTitleId;
	}

	/**
	 * @param anOrverVersion
	 */
	public void setOrderVersion(String anOrverVersion)
	{
		orderVersion = anOrverVersion;
	}

	/**
	 * @param aVersionNum
	 */
	public void setOrderVersionNum(String aVersionNum)
	{
		orderVersionNum = aVersionNum;
	}

	/**
	 * @param aVersionTypeId
	 */
	public void setOrderVersionTypeId(String aVersionTypeId)
	{
		orderVersionTypeId = aVersionTypeId;
	}

}
