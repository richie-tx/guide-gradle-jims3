/**
 * 
 */
package messaging.codetable.criminal.reply;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;

/**
 * @author ryoung
 *
 */
public class JuvenileCourtDecisionCodeResponseEvent extends ResponseEvent implements Comparable, ICode {

	private String code;
	private String decision;
	private String description;
	private String action;
	private String resetAllowed;
	private String status;
	private String descriptionWithCode;
	
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

	public String getDescriptionWithCode()
	{
	    return descriptionWithCode;
	}
	public void setDescriptionWithCode(String descriptionWithCode)
	{
	    this.descriptionWithCode = descriptionWithCode;
	}
	public int compareTo(Object obj) throws ClassCastException {
		ICode evt = (ICode)obj;
		return description.compareTo(evt.getDescription());
	}
	/**
	 * @return the parentId
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setAction(String parentId) {
		this.action = parentId;
	}
	/**
	 * @return the codeTableName
	 */
	public String getDecision() {
		return decision;
	}
	/**
	 * @param codeTableName the codeTableName to set
	 */
	public void setDecision(String codeTableName) {
		this.decision = codeTableName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getResetAllowed()
	{
	    return resetAllowed;
	}
	public void setResetAllowed(String resetAllowed)
	{
	    this.resetAllowed = resetAllowed;
	}
	
	
}