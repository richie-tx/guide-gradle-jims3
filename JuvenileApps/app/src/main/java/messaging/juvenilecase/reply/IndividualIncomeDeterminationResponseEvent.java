package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;

public class IndividualIncomeDeterminationResponseEvent extends ResponseEvent 
{
	private String memberId;
	private String name;
	private int age;
	private String relationshipToJuvenileId;
	private String comments;
	private String incomeSourceId;
	private int grossMonthyIncome;


	
	/**
	 * @return
	 */
	public int getAge()
	{
		return age;
	}

	/**
	 * @return
	 */
	public String getComments()
	{
		return comments;
	}

	/**
	 * @return
	 */
	public int getGrossMonthyIncome()
	{
		return grossMonthyIncome;
	}

	/**
	 * @return
	 */
	public String getIncomeSourceId()
	{
		return incomeSourceId;
	}

	/**
	 * @return
	 */
	public String getMemberId()
	{
		return memberId;
	}

	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return
	 */
	public String getRelationshipToJuvenileId()
	{
		return relationshipToJuvenileId;
	}

	/**
	 * @param i
	 */
	public void setAge(int i)
	{
		age = i;
	}

	/**
	 * @param string
	 */
	public void setComments(String string)
	{
		comments = string;
	}

	/**
	 * @param i
	 */
	public void setGrossMonthyIncome(int i)
	{
		grossMonthyIncome = i;
	}

	/**
	 * @param string
	 */
	public void setIncomeSourceId(String string)
	{
		incomeSourceId = string;
	}

	/**
	 * @param string
	 */
	public void setMemberId(String string)
	{
		memberId = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
	}

	/**
	 * @param string
	 */
	public void setRelationshipToJuvenileId(String string)
	{
		relationshipToJuvenileId = string;
	}

}
