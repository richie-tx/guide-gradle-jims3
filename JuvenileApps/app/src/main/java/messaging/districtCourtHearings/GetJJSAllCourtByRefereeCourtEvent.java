package messaging.districtCourtHearings;

import mojo.km.messaging.RequestEvent;

/**
 * 
 * @author sthyagarajan
 *
 */
public class GetJJSAllCourtByRefereeCourtEvent extends RequestEvent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String courtDate;
	String courtId;

	/**
	 * @return the courtDate
	 */
	public String getCourtDate() {
		return courtDate;
	}
	/**
	 * @param courtDate the courtDate to set
	 */
	public void setCourtDate(String courtDate) {
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
