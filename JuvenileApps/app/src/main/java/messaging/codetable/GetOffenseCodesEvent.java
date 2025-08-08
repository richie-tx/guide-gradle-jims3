/*
 * Created on Oct 3, 2005
 *
 */
package messaging.codetable;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetOffenseCodesEvent extends RequestEvent
{
	private String offenseCode;
	private String offenseLevel;
	private String offenseDegree;
	private String offenseLiteral;
	private String penalCode;
	private String stateOffenseCode;
	private String limitSearch;
	/**
	 * @return
	 */
	public String getOffenseCode()
	{
		return offenseCode;
	}

	/**
	 * @return
	 */
	public String getOffenseLiteral()
	{
		return offenseLiteral;
	}

	/**
	 * @return
	 */
	public String getPenalCode()
	{
		return penalCode;
	}

	/**
	 * @param theOffenseCode
	 */
	public void setOffenseCode(String theOffenseCode)
	{
		offenseCode = theOffenseCode;
	}

	/**
	 * @param theLiteral
	 */
	public void setOffenseLiteral(String theLiteral)
	{
		offenseLiteral = theLiteral;
	}

	/**
	 * @param thePenalCode
	 */
	public void setPenalCode(String thePenalCode)
	{
		penalCode = thePenalCode;
	}

	/**
	 * @return
	 */
	public String getOffenseDegree()
	{
		return offenseDegree;
	}

	/**
	 * @return
	 */
	public String getOffenseLevel()
	{
		return offenseLevel;
	}

	/**
	 * @param string
	 */
	public void setOffenseDegree(String string)
	{
		offenseDegree = string;
	}

	/**
	 * @param string
	 */
	public void setOffenseLevel(String string)
	{
		offenseLevel = string;
	}

	/**
	 * @return
	 */
	public String getStateOffenseCode()
	{
		return stateOffenseCode;
	}

	/**
	 * @param string
	 */
	public void setStateOffenseCode(String string)
	{
		stateOffenseCode = string;
	}

	public String getLimitSearch()
	{
		return limitSearch;
	}

	public void setLimitSearch(String limitSearch)
	{
		this.limitSearch = limitSearch;
	}

}
