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
public class JuvenileCodeTableChildCodesResponseEvent extends ResponseEvent implements Comparable, ICode {

	private String code;
	private String codeTableName;
	private String description;
	private String parentId;
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
		return description.compareTo(evt.getDescription());
	}
	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the codeTableName
	 */
	public String getCodeTableName() {
		return codeTableName;
	}
	/**
	 * @param codeTableName the codeTableName to set
	 */
	public void setCodeTableName(String codeTableName) {
		this.codeTableName = codeTableName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the descriptionWithCode
	 */
	public String getDescriptionWithCode()
	{
	    return descriptionWithCode;
	}
	/**
	 * @param descriptionWithCode the descriptionWithCode to set
	 */
	public void setDescriptionWithCode(String descriptionWithCode)
	{
	    this.descriptionWithCode = descriptionWithCode;
	}
}