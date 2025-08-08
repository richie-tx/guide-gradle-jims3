package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class GetAllAliasNameTypeCodesEvent extends RequestEvent{
	private String code;
	private String decision;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the decision
	 */
	public String getDecision()
	{
	    return decision;
	}

	/**
	 * @param decision the decision to set
	 */
	public void setDecision(String decision)
	{
	    this.decision = decision;
	}
}
