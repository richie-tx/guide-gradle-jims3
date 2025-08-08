/*
 * Created on Sept 12, 2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author C_Shimek
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileReferralCourtDateResponseEvent extends ResponseEvent implements Comparable
{
	
	private Date courtDate;
	private String referralNumber;
	private String courtId;
	private String sequenceNum;
	
	/**
	 * @return the courtDate
	 */
	public Date getCourtDate() {
		return courtDate;
	}

	/**
	 * @param courtDate the courtDate to set
	 */
	public void setCourtDate(Date courtDate) {
		this.courtDate = courtDate;
	}

	/**
	 * @return the referralNumber
	 */
	public String getReferralNumber() {
		return referralNumber;
	}

	/**
	 * @param referralNumber the referralNumber to set
	 */
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}
	
	/**
	* Set the reference value to class :: pd.codetable.criminal.JuvenileCourt
	*/
	public void setCourtId(String courtId)
	{
		this.courtId = courtId;
	}
	/**
	* Get the reference value to class :: pd.codetable.criminal.JuvenileCourt
	*/
	public String getCourtId()
	{
		
		return courtId;
	}
	
    /**
     * Access method for the sequenceNum property.
     * 
     * @return the current value of the sequenceNum property
     */
    public String getSequenceNum()
    {

	return sequenceNum;
    }

    /**
     * Sets the value of the sequenceNum property.
     * 
     * @param aSequenceNum
     *            the new value of the sequenceNum property
     */
    public void setSequenceNum(String aSequenceNum)
    {
	sequenceNum = aSequenceNum;
    }


	public int compareTo(Object obj) throws ClassCastException
	{
		JuvenileReferralCourtDateResponseEvent evt = (JuvenileReferralCourtDateResponseEvent) obj;
		return referralNumber.compareTo(evt.getReferralNumber());
	}	
}
