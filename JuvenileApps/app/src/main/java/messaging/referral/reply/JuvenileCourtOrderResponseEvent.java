/*
 * Created on July 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.referral.reply;

import java.util.Date;

import messaging.mentalhealth.reply.DSMIVTestResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author UGopinath
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JuvenileCourtOrderResponseEvent extends ResponseEvent implements Comparable{
	
	private String hearingType; 
	private Date courtDate; 
	private String court; 
	private String attorneyName; 
	private String offense;


	/**
	 * @return Returns the attorneyName.
	 */
	public String getAttorneyName() {
		return attorneyName;
	}
	/**
	 * @param attorneyName The attorneyName to set.
	 */
	public void setAttorneyName(String attorneyName) {
		this.attorneyName = attorneyName;
	}
	/**
	 * @return Returns the court.
	 */
	public String getCourt() {
		return court;
	}
	/**
	 * @param court The court to set.
	 */
	public void setCourt(String court) {
		this.court = court;
	}
	/**
	 * @return Returns the courtDate.
	 */
	public Date getCourtDate() {
		return courtDate;
	}
	/**
	 * @param courtDate The courtDate to set.
	 */
	public void setCourtDate(Date courtDate) {
		this.courtDate = courtDate;
	}
	/**
	 * @return Returns the hearingType.
	 */
	public String getHearingType() {
		return hearingType;
	}
	/**
	 * @param hearingType The hearingType to set.
	 */
	public void setHearingType(String hearingType) {
		this.hearingType = hearingType;
	}
	/**
	 * @return Returns the offense.
	 */
	public String getOffense() {
		return offense;
	}
	/**
	 * @param offense The offense to set.
	 */
	public void setOffense(String offense) {
		this.offense = offense;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		// TODO Auto-generated method stub
		int result = 0;
		JuvenileCourtOrderResponseEvent rsp = (JuvenileCourtOrderResponseEvent)obj;
		Date eventDateA = getCourtDate();
		Date eventDateB = rsp.getCourtDate();
		
		if(obj==null)
			return -1;
		if(eventDateA==null)
			return 1;		
		if(eventDateB == null)
			return -1;
		return eventDateB.compareTo(eventDateA);
	}
}
