// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\GetCSFVItenaryDetailsEvent.java

package messaging.cscdcalendar;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class GetCSFVItineraryDetailsEvent extends RequestEvent {

	private String itineraryId;

	private Date itineraryDate;

	private String positionId;

	/**
	 * @roseuid 479A0E1F03B6
	 */
	public GetCSFVItineraryDetailsEvent() {

	}

	/**
	 * @return Returns the itineraryId.
	 */
	public String getItineraryId() {
		return itineraryId;
	}

	/**
	 * @param itineraryId
	 *            The itineraryId to set.
	 */
	public void setItineraryId(String itineraryId) {
		this.itineraryId = itineraryId;
	}

	/**
	 * @return Returns the positionId.
	 */
	public String getPositionId() {
		return positionId;
	}

	/**
	 * @param positionId
	 *            The positionId to set.
	 */
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	/**
	 * @return Returns the itineraryDate.
	 */
	public Date getItineraryDate() {
		return itineraryDate;
	}

	/**
	 * @param itineraryDate
	 *            The itineraryDate to set.
	 */
	public void setItineraryDate(Date itineraryDate) {
		this.itineraryDate = itineraryDate;
	}
}
