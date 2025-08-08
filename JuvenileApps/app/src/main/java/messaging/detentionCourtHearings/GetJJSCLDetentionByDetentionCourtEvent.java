package messaging.detentionCourtHearings;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * U.S #11645 
 * @author sthyagarajan
 *
 */
public class GetJJSCLDetentionByDetentionCourtEvent extends RequestEvent{
	
	private Date courtDate; //hearingdate or courtDate
	private String courtId;
	
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
	 * @return the courtId
	 */
	public String getCourtId() {
		return courtId;
	}
	/**
	 * @param courtId the courtId to set
	 */
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
}
