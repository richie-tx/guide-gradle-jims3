/*
 * Created on Aug 20, 2007
 *
 */
package messaging.transferobjects;



/**
 * @author cc_mdsouza
 *
 */
public class SupervisionCodeTO
extends PersistentObjectTO 
{

	private String codeTableName;
	private String agencyId;
	private String code;
	private String description;
	private String codeId;

	public SupervisionCodeTO() 
	{
	}
	
	
	
	
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code The code to set.
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return Returns the codeId.
	 */
	public String getCodeId() {
		return codeId;
	}
	/**
	 * @param codeId The codeId to set.
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	/**
	 * @return Returns the codeTableName.
	 */
	public String getCodeTableName() {
		return codeTableName;
	}
	/**
	 * @param codeTableName The codeTableName to set.
	 */
	public void setCodeTableName(String codeTableName) {
		this.codeTableName = codeTableName;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
