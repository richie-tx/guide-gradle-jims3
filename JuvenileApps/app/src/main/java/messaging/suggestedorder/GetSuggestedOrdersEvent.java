//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\suggestedorder\\GetSuggestedOrdersEvent.java

package messaging.suggestedorder;

import java.util.ArrayList;

import mojo.km.messaging.RequestEvent;

public class GetSuggestedOrdersEvent extends RequestEvent
{
	private String conditionName;
	private String courtDivision;
	private java.util.Collection courts = new ArrayList();
	private String offenseCode;
	private String offenseLiteral;
	private String penalCode;
	private String suggestedOrderDescription;
	private String suggestedOrderName;
	private String agencyId;

	/**
	 * @roseuid 433AF2CD0298
	 */
	public GetSuggestedOrdersEvent()
	{

	}

	/**
	 * Access method for the conditionName property.
	 * 
	 * @return   the current value of the conditionName property
	 */
	public String getConditionName()
	{
		return conditionName;
	}

	/**
	 * Access method for the courtDivision property.
	 * 
	 * @return   the current value of the courtDivision property
	 */
	public String getCourtDivision()
	{
		return courtDivision;
	}

	/**
	 * Access method for the courts property.
	 * 
	 * @return   the current value of the courts property
	 */
	public java.util.Collection getCourts()
	{
		return courts;
	}

	/**
	 * Access method for the code property.
	 * 
	 * @return   the current value of the code property
	 */
	public String getOffenseCode()
	{
		return offenseCode;
	}

	/**
	 * Access method for the literal property.
	 * 
	 * @return   the current value of the literal property
	 */
	public String getOffenseLiteral()
	{
		return offenseLiteral;
	}

	/**
	 * Access method for the penalCode property.
	 * 
	 * @return   the current value of the penalCode property
	 */
	public String getPenalCode()
	{
		return penalCode;
	}
	/**
	 * @return
	 */
	public String getSuggestedOrderDescription()
	{
		return suggestedOrderDescription;
	}

	/**
	 * Access method for the suggestedOrderName property.
	 * 
	 * @return   the current value of the suggestedOrderName property
	 */
	public String getSuggestedOrderName()
	{
		return suggestedOrderName;
	}

	/**
	 * @param conditionName
	 * @roseuid 433AF0510344
	 */
	public void setConditionName(String theConditionName)
	{
		this.conditionName = theConditionName;
	}

	/**
	 * @param courtDivision
	 * @roseuid 433AF0510334
	 */
	public void setCourtDivision(String theCourtDivision)
	{
		this.courtDivision = theCourtDivision;
	}

	/**
	 * Sets the value of the courts property.
	 * 
	 * @param aCourts the new value of the courts property
	 */
	public void setCourts(java.util.Collection aCourts)
	{
		courts = aCourts;
	}

	/**
	 * Sets the value of the courts property.
	 * 
	 * @param courtId the courtId to be added in the collection
	 */
	public void addCourt(String courtId)
	{
		courts.add(courtId);
	}

	/**
	 * @param code
	 * @roseuid 433AF0510348
	 */
	public void setOffenseCode(String theCode)
	{
		this.offenseCode = theCode;
	}

	/**
	 * @param literal
	 * @roseuid 433AF0510355
	 */
	public void setOffenseLiteral(String theLiteral)
	{
		this.offenseLiteral = theLiteral;
	}

	/**
	 * @param penalCode
	 * @roseuid 433AF0510364
	 */
	public void setPenalCode(String thePenalCode)
	{
		this.penalCode = thePenalCode;
	}

	/**
	 * @param aSuggestedOrderDescription
	 * @roseuid 433AF0510364
	 */
	public void setSuggestedOrderDescription(String aSuggestedOrderDescription)
	{
		this.suggestedOrderDescription = aSuggestedOrderDescription;
	}

	/**
	 * @param suggestedOrderName
	 * @roseuid 433AF0510318
	 */
	public void setSuggestedOrderName(String theSuggestedOrderName)
	{
		this.suggestedOrderName = theSuggestedOrderName;
	}

	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
}
