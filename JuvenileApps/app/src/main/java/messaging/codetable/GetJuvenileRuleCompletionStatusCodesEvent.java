package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileRuleCompletionStatusCodesEvent extends RequestEvent{

	public String code;

	/**
	 * @roseuid 41642A51022D
	 */
	public GetJuvenileRuleCompletionStatusCodesEvent() 
	{

	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	   
}
