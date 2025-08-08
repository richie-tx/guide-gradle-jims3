package messaging.codetable;

import mojo.km.messaging.RequestEvent;
/*
 * Added for 12533 user story
 */
public class GetJuvenileFacilityByCodeEvent extends RequestEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;

	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code The code to set.
	 */
	public void setCode(String code) {
		this.code = code;
	}
}
