/**
 * 
 */
package messaging.codetable.criminal.reply;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;

/**
 * @author ugopinath
 *
 */
public class JuvenileReleasedFromResponseEvent extends ResponseEvent implements Comparable, ICode {

	private String code;
	private String description;
	private String rectype; 	
	private String status;
	//US 71173
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

	public int compareTo(Object obj) throws ClassCastException {
		ICode evt = (ICode)obj;
		return code.compareTo(evt.getCode());
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the casefileGenerate
	 */
	public String getRectype()
	{
	    return rectype;
	}
	/**
	 * @param casefileGenerate the casefileGenerate to set
	 */
	public void setRectype(String casefileGenerate)
	{
	    this.rectype = casefileGenerate;
	}
	/**
	 * @return the codeWithDescription
	 */
	public String getDescriptionWithCode()
	{
	    return descriptionWithCode;
	}
	/**
	 * @param codeWithDescription the codeWithDescription to set
	 */
	public void setDescriptionWithCode(String codeWithDescription)
	{
	    this.descriptionWithCode = codeWithDescription;
	}
	
	
	
	
	

	
}
