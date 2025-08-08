//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\supervisionorder\\ReinstateSupervisionOrderEvent.java

package messaging.supervisionorder;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class ReinstateSupervisionOrderEvent extends RequestEvent
{
	private String notes;
	private Date reinstatementDate;
	private String reinstatementReason;
	private String supervisionOrderId;

	/**
	 * @return
	 */
	public String getNotes()
	{
		return notes;
	}

	/**
	 * @return
	 */
	public Date getReinstatementDate()
	{
		return reinstatementDate;
	}

	/**
	 * @return
	 */
	public String getReinstatementReason()
	{
		return reinstatementReason;
	}
	/**
	 * @return
	 */
	public String getSupervisionOrderId()
	{
		return supervisionOrderId;
	}

	/**
	 * @param theNotes
	 */
	public void setNotes(String theNotes)
	{
		notes = theNotes;
	}

	/**
	 * @param aDate
	 */
	public void setReinstatementDate(Date aDate)
	{
		reinstatementDate = aDate;
	}

	/**
	 * @param aReinstatementReason
	 */
	public void setReinstatementReason(String aReinstatementReason)
	{
		reinstatementReason = aReinstatementReason;
	}
	/**
	 * @param aSupervisionOrderId
	 */
	public void setSupervisionOrderId(String aSupervisionOrderId)
	{
		supervisionOrderId = aSupervisionOrderId;
	}

}
