/*
 * Created on Sep 29, 2005
 */
package messaging.suggestedorder.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
 */
public class SuggestedOrderCourtResponseEvent extends ResponseEvent
{
	private String courtId;
	private String suggestedOrderId;
	private String suggestedOrderCourtId;
	private String courtCategory;
	/**
	 * @return
	 */
	public String getSuggestedOrderCourtId()
	{
		return suggestedOrderCourtId;
	}

	/**
	 * @return
	 */
	public String getSuggestedOrderId()
	{
		return suggestedOrderId;
	}

	/**
	 * @param string
	 */
	public void setSuggestedOrderCourtId(String string)
	{
		suggestedOrderCourtId = string;
	}

	/**
	 * @param string
	 */
	public void setSuggestedOrderId(String string)
	{
		suggestedOrderId = string;
	}
	/**
	 * @return
	 */
	public String getCourtCategory()
	{
		return courtCategory;
	}

	/**
	 * @param aCourtCategory
	 */
	public void setCourtCategory(String aCourtCategory)
	{
		courtCategory = aCourtCategory;
	}

	/**
	 * @return
	 */
	public String getCourtId()
	{
		return courtId;
	}

	/**
	 * @param aCourtId
	 */
	public void setCourtId(String aCourtId)
	{
		courtId = aCourtId;
	}

}
