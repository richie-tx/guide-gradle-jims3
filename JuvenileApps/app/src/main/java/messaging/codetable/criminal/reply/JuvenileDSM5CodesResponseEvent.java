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
public class JuvenileDSM5CodesResponseEvent extends ResponseEvent implements Comparable, ICode {

	private String code;
	private String description;
	private String tjjdId; 	
	private String status;
	
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
	public String getTjjdId() {
		return tjjdId;
	}
	public void setTjjdId(String tjjdId) {
		this.tjjdId = tjjdId;
	}
	
}
