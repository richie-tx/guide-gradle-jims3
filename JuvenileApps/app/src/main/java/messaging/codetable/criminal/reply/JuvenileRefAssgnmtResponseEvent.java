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
public class JuvenileRefAssgnmtResponseEvent extends ResponseEvent implements Comparable, ICode {

	private String code;
	private String description;
	private String casefileGenerate; 	
	private String status;
	//US 71173
	private String codeWithDescription;
	
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
		return description.compareTo(evt.getDescription());
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
	public String getCasefileGenerate()
	{
	    return casefileGenerate;
	}
	/**
	 * @param casefileGenerate the casefileGenerate to set
	 */
	public void setCasefileGenerate(String casefileGenerate)
	{
	    this.casefileGenerate = casefileGenerate;
	}
	/**
	 * @return the codeWithDescription
	 */
	public String getCodeWithDescription()
	{
	    return codeWithDescription;
	}
	/**
	 * @param codeWithDescription the codeWithDescription to set
	 */
	public void setCodeWithDescription(String codeWithDescription)
	{
	    this.codeWithDescription = codeWithDescription;
	}

	
}
