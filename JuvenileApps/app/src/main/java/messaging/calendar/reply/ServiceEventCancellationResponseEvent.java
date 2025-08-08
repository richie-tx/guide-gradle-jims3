/*
 * Created on Feb 16, 2007
 *
 * 
 */
package messaging.calendar.reply;

import java.util.Date;
import java.util.List;

import mojo.km.messaging.ResponseEvent;

/**
 * @author C_NRaveendran
 *
 */
public class ServiceEventCancellationResponseEvent extends ResponseEvent {
	
	private String juvenileId;
	private String juvenileName;
	private List guardianResponseEvents;
	private String officerLogonId;

	
	/**
	 * @return Returns the juvenileId.
	 */
	public String getJuvenileId() {
		return juvenileId;
	}
	/**
	 * @param juvenileId The juvenileId to set.
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
	/**
	 * @return Returns the juvenileName.
	 */
	public String getJuvenileName() {
		return juvenileName;
	}
	/**
	 * @param juvenileName The juvenileName to set.
	 */
	public void setJuvenileName(String juvenileName) {
		this.juvenileName = juvenileName;
	}

	/**
	 * @return Returns the guardianResponseEvents.
	 */
	public List getGuardianResponseEvents() {
		return guardianResponseEvents;
	}
	/**
	 * @param guardianResponseEvents The guardianResponseEvents to set.
	 */
	public void setGuardianResponseEvents(List guardianResponseEvents) {
		this.guardianResponseEvents = guardianResponseEvents;
	}
	public String getOfficerLogonId() {
		return officerLogonId;
	}
	public void setOfficerLogonId(String officerLogonId) {
		this.officerLogonId = officerLogonId;
	}

}
