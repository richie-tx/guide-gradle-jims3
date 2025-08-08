// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\SaveCSFVReorderItenaryEvent.java

package messaging.cscdcalendar;

import java.util.Map;

import mojo.km.messaging.RequestEvent;

public class SaveCSFVReorderItineraryEvent extends RequestEvent {
	private Map fvIdToOrderMap;

	private String fvIteneraryId;
	
	private Map fvStartEndTimeMap;
	
	

	

	

	/**
	 * @roseuid 479A0E2100F6
	 */
	public SaveCSFVReorderItineraryEvent() {

	}

	/**
	 * @return Returns the fvIdToOrderMap.
	 */
	public Map getFvIdToOrderMap() {
		return fvIdToOrderMap;
	}

	/**
	 * @param fvIdToOrderMap
	 *            The fvIdToOrderMap to set.
	 */
	public void setFvIdToOrderMap(Map fvIdToOrderMap) {
		this.fvIdToOrderMap = fvIdToOrderMap;
	}

	/**
	 * @return Returns the fvIteneraryId.
	 */
	public String getFvIteneraryId() {
		return fvIteneraryId;
	}
	
	public Map getFvStartEndTimeMap() {
		return fvStartEndTimeMap;
	}

	public void setFvStartTimeMap(Map fvStartEndTimeMap) {
		this.fvStartEndTimeMap = fvStartEndTimeMap;
	}

	/**
	 * @param fvIteneraryId The fvIteneraryId to set.
	 */
	public void setFvIteneraryId(String fvIteneraryId) {
		this.fvIteneraryId = fvIteneraryId;
	}
	

	
	
}
