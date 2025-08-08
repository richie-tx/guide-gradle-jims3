//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\managesupervisioncase\\GetOutOfCountyCasesEvent.java

package messaging.managesupervisioncase;

import mojo.km.messaging.RequestEvent;

public class GetOutOfCountyCasesEvent extends RequestEvent
{
	private String spn;
	private String defendantId;
	private String userAgencyId;
	public String caseNum;
	public String courtDivisionId;

	/**
	 * @roseuid 4447D37E013D
	 */
	public GetOutOfCountyCasesEvent()
	{

	}

	/**
	 * Access method for the spn property.
	 * 
	 * @return   the current value of the spn property
	 */
	public String getSpn()
	{
		return spn;
	}

	/**
	 * Sets the value of the spn property.
	 * 
	 * @param aSpn the new value of the spn property
	 */
	public void setSpn(String aSpn)
	{
		spn = aSpn;
	}

	/**
	 * Access method for the userAgencyId property.
	 * 
	 * @return  the current value of the userAgencyId property
	 */
	public String getUserAgencyId()
	{
		return userAgencyId;
	}

	/**
	 * Sets the value of the userAgencyId property.
	 * 
	 * @param anAgencyId the new value of the userAgencyId property
	 */
	public void setUserAgencyId(String anAgencyId)
	{
		userAgencyId = anAgencyId;
	}

	/**
	 * Access method for the caseNum property.
	 * 
	 * @return String
	 */
	public String getCaseNum()
	{
		return caseNum;
	}

	/**
	 * Sets the value of the caseNum property.
	 * 
	 * @param aCaseNum
	 */
	public void setCaseNum(String aCaseNum)
	{
		caseNum = aCaseNum;
	}

	/**
	 * Access method for the courtDivisionId property.
	 * 
	 * @return String
	 */
	public String getCourtDivisionId()
	{
		return courtDivisionId;
	}

	/**
	 * Sets the value of the courtDivisionId property.
	 * 
	 * @param aCourtDivisionId
	 */
	public void setCourtDivisionId(String aCourtDivisionId)
	{
		courtDivisionId = aCourtDivisionId;
	}

	/**
	 * Access method for the defendantId property.
	 * 
	 * @return  the current value of the defendantId property
	 */
	public String getDefendantId()
	{
		return defendantId;
	}

	/**
	 * Sets the value of the defendantId property.
	 * 
	 * @param aDefendantId the new value of the defendantId property
	 */
	public void setDefendantId(String aDefendantId)
	{
		defendantId = aDefendantId;
	}

}
