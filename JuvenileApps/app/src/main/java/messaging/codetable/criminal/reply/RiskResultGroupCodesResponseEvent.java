/**
 * 
 */
package messaging.codetable.criminal.reply;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;

/**
 * @author cshimek
 *
 */
public class RiskResultGroupCodesResponseEvent extends ResponseEvent implements Comparable, ICode {

	private String code;
	private String description;
	
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
	
}
