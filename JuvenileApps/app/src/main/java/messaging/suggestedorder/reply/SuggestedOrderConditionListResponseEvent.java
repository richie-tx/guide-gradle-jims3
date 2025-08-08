/*
 * Created on Oct 3, 2005
 *
 */
package messaging.suggestedorder.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
  */
public class SuggestedOrderConditionListResponseEvent extends ResponseEvent implements Comparable
{
	private String conditionId;
	private String suggestedOrderConditionId;
	private String suggestedOrderConditionName;
	private String suggestedOrderId;
	private String suggestedOrderName;
	private String suggestedOrderDescription;

	/**
	 * @return
	 */
	public String getConditionId()
	{
		return conditionId;
	}

	/**
	 * @return
	 */
	public String getSuggestedOrderConditionId()
	{
		return suggestedOrderConditionId;
	}
	/**
	 * @return
	 */
	public String getSuggestedOrderConditionName()
	{
		return suggestedOrderConditionName;
	}

	/**
	 * @return
	 */
	public String getSuggestedOrderId()
	{
		return suggestedOrderId;
	}

	/**
	 * @return
	 */
	public String getSuggestedOrderName()
	{
		return suggestedOrderName;
	}

	/**
	 * @param theConditionId
	 */
	public void setConditionId(String theConditionId)
	{
		conditionId = theConditionId;
	}

	/**
	 * @param theSuggestedOrderConditionId
	 */
	public void setSuggestedOrderConditionId(String theSuggestedOrderConditionId)
	{
		suggestedOrderConditionId = theSuggestedOrderConditionId;
	}

	/**
	 * @param theConditionName
	 */
	public void setSuggestedOrderConditionName(String theConditionName)
	{
		suggestedOrderConditionName = theConditionName;
	}

	/**
	 * @param theSuggestedOrderId
	 */
	public void setSuggestedOrderId(String theSuggestedOrderId)
	{
		suggestedOrderId = theSuggestedOrderId;
	}

	/**
	 * @param theSuggestedOrderName
	 */
	public void setSuggestedOrderName(String theSuggestedOrderName)
	{
		suggestedOrderName = theSuggestedOrderName;
	}

	/**
	 * @return
	 */
	public String getSuggestedOrderDescription()
	{
		return suggestedOrderDescription;
	}

	/**
	 * @param aSuggestedOrderDescription
	 */
	public void setSuggestedOrderDescription(String aSuggestedOrderDescription)
	{
		suggestedOrderDescription = aSuggestedOrderDescription;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0)
	{
		SuggestedOrderConditionListResponseEvent soclre = (SuggestedOrderConditionListResponseEvent) arg0;
		String newString = "";

		if (this.suggestedOrderName == null)
		{
			this.suggestedOrderName = newString;
		}
		if (this.suggestedOrderConditionName == null)
		{
			this.suggestedOrderConditionName = newString;
		}
		if (soclre.getSuggestedOrderName() == null)
		{
			soclre.setSuggestedOrderName(newString);
		}
		if (soclre.getSuggestedOrderConditionName() == null)
		{
			soclre.setSuggestedOrderConditionName(newString);
		}
		int comparisonResult = 0;
		if (this.suggestedOrderName.compareTo(soclre.getSuggestedOrderName()) == 0)
		{
			comparisonResult = this.suggestedOrderConditionName.compareTo(soclre.getSuggestedOrderConditionName());
		}
		else
		{
			comparisonResult = this.suggestedOrderName.compareTo(soclre.getSuggestedOrderName());
		}

		return comparisonResult;
	}

}
