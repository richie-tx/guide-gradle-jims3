package messaging.codetable.reply;

import mojo.km.messaging.ResponseEvent;

public class JuvenileRuleCompletionStatusCodeResponseEvent extends ResponseEvent implements Comparable,ICode{

	private String code;
	private String type;
	private String description;
	private String status;
	
	public JuvenileRuleCompletionStatusCodeResponseEvent()
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

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public int compareTo(Object obj) {
		if (obj==null || ! (obj instanceof JuvenileRuleCompletionStatusCodeResponseEvent))
			return 0;		
		JuvenileRuleCompletionStatusCodeResponseEvent resp = (JuvenileRuleCompletionStatusCodeResponseEvent)obj;
		return description.compareTo(resp.getDescription());
	}

	/**
	 * @return the descriptionType
	 */
	public String getDescriptionType() {
		return description+" ("+type+") ";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
