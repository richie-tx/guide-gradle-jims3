package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileDSM5CodeEvent extends RequestEvent{
	
	private String code;
	private String description;

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
