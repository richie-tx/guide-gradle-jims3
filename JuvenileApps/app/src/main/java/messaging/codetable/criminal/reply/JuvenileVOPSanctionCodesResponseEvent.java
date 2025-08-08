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
public class JuvenileVOPSanctionCodesResponseEvent extends ResponseEvent implements Comparable, ICode {

	private String code;
	private String description;
	private String level;
	private String sanctionLevel;
	private String comments;
	// get only the active codes. bug fix;21770 part 2
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
	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	public int compareTo(Object obj) throws ClassCastException {
		ICode evt = (ICode)obj;
		return description.compareTo(evt.getDescription());
	}
	/**
	 * @return the sanctionLevel
	 */
	public String getSanctionLevel() {
		return sanctionLevel;
	}
	/**
	 * @param sanctionLevel the sanctionLevel to set
	 */
	public void setSanctionLevel(String sanctionLevel) {
		this.sanctionLevel = sanctionLevel;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
