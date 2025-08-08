//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\suggestedorder\\GetSuggestedOrdersForCourtEvent.java

package messaging.suggestedorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetSuggestedOrdersForCourtEvent extends RequestEvent
{
//	private String courtDiv;
//	private String courtNum;
	private String courtId;
	private String offenseId;
	private String agencyId;

	/**
	 * @roseuid 4395E71301FB
	 */
	public GetSuggestedOrdersForCourtEvent()
	{

	}

//	/**
//	 * @return
//	 */
//	public String getCourtDiv()
//	{
//		return courtDiv;
//	}
//
//	/**
//	 * Access method for the courtId property.
//	 * 
//	 * @return   the current value of the courtId property
//	 */
//	public java.lang.String getCourtNum()
//	{
//		return courtNum;
//	}

	/**
	 * @return
	 */
	public String getOffenseId()
	{
		return offenseId;
	}

//	/**
//	 * @param aCourtDiv
//	 */
//	public void setCourtDiv(String aCourtDiv)
//	{
//		courtDiv = aCourtDiv;
//	}
//
//	/**
//	 * Sets the value of the courtId property.
//	 * 
//	 * @param aCourtId the new value of the courtId property
//	 */
//	public void setCourtNum(java.lang.String aCourtNum)
//	{
//		courtNum = aCourtNum;
//	}

	/**
	 * @param anOffenseId
	 */
	public void setOffenseId(String anOffenseId)
	{
		offenseId = anOffenseId;
	}

	/**
	 * @return
	 */
	public String getCourtId()
	{
		return courtId;
	}
	
	/**
	 * @param string
	 */
	public void setCourtId(String string)
	{
		courtId = string;
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
