/*
 * Created on Aug 20, 2007
 *
 */
package messaging.transferobjects;

import java.util.Date;

/**
 * @author cc_mdsouza
 *
 */
public class CodeTO 
extends PersistentObjectTO
{

    private Date activeDate;
    private String code;
    private String codeTableName;
    private String description;
    private Date inactiveDate;
    private Date inactiveEffectiveDate;
    private String status;

    private CodeTableTO codeTable = null;

    private java.util.Collection codeHistories = null;

    public CodeTO()
    {}
   
  
	
	
	/**
	 * @return Returns the activeDate.
	 */
	public Date getActiveDate() {
		return activeDate;
	}
	/**
	 * @param activeDate The activeDate to set.
	 */
	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
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
	 * @return Returns the codeHistories.
	 */
	public java.util.Collection getCodeHistories() {
		return codeHistories;
	}
	/**
	 * @param codeHistories The codeHistories to set.
	 */
	public void setCodeHistories(java.util.Collection codeHistories) {
		this.codeHistories = codeHistories;
	}
	/**
	 * @return Returns the codeTable.
	 */
	public CodeTableTO getCodeTable() {
		return codeTable;
	}
	/**
	 * @param codeTable The codeTable to set.
	 */
	public void setCodeTable(CodeTableTO codeTable) {
		this.codeTable = codeTable;
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
	/**
	 * @return Returns the inactiveDate.
	 */
	public Date getInactiveDate() {
		return inactiveDate;
	}
	/**
	 * @param inactiveDate The inactiveDate to set.
	 */
	public void setInactiveDate(Date inactiveDate) {
		this.inactiveDate = inactiveDate;
	}
	/**
	 * @return Returns the inactiveEffectiveDate.
	 */
	public Date getInactiveEffectiveDate() {
		return inactiveEffectiveDate;
	}
	/**
	 * @param inactiveEffectiveDate The inactiveEffectiveDate to set.
	 */
	public void setInactiveEffectiveDate(Date inactiveEffectiveDate) {
		this.inactiveEffectiveDate = inactiveEffectiveDate;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
