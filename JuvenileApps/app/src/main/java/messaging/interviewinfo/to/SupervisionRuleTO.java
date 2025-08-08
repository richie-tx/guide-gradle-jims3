package messaging.interviewinfo.to;

import java.util.Date;

/**
 *
 */
public class SupervisionRuleTO extends ExcludedTO
{
	private String oid = "";
	private String ruleId = "";
	private String category = "";
	private String type = "";
	private String subtype = "";
	private Date statusChangeDate = null;
	private String completionStatusId = "";
	private String completionStatus = "";
	private String resolvedDesc = "";
	
	
	/**
	 * @return Returns the category.
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category The category to set.
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return Returns the completionStatus.
	 */
	public String getCompletionStatus() {
		return completionStatus;
	}
	/**
	 * @param completionStatus The completionStatus to set.
	 */
	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus;
	}
	
	/**
	 * @return Returns the ruleId.
	 */
	public String getRuleId() {
		return ruleId;
	}
	/**
	 * @param ruleId The ruleId to set.
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	/**
	 * @return Returns the statusChangeDate.
	 */
	public Date getStatusChangeDate() {
		return statusChangeDate;
	}
	/**
	 * @param statusChangeDate The statusChangeDate to set.
	 */
	public void setStatusChangeDate(Date statusChangeDate) {
		this.statusChangeDate = statusChangeDate;
	}
	/**
	 * @return Returns the subtype.
	 */
	public String getSubtype() {
		return subtype;
	}
	/**
	 * @param subtype The subtype to set.
	 */
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return Returns the oid.
	 */
	public String getOID() {
		return oid;
	}
	/**
	 * @param oid The oid to set.
	 */
	public void setOID(String oid) {
		this.oid = oid;
	}
	/**
	 * @return Returns the completionStatusId.
	 */
	public String getCompletionStatusId() {
		return completionStatusId;
	}
	/**
	 * @param completionStatusId The completionStatusId to set.
	 */
	public void setCompletionStatusId(String completionStatusId) {
		this.completionStatusId = completionStatusId;
	}
	public String getResolvedDesc() {
		return resolvedDesc;
	}
	public void setResolvedDesc(String resolvedDesc) {
		this.resolvedDesc = resolvedDesc;
	}
}
