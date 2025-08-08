package messaging.productionsupport.reply;

import java.util.Date;

import messaging.juvenilecase.reply.BenefitsAssessmentDetailResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author rcarter
 */
public class ProductionSupportSupervisionRulesResponseEvent extends ResponseEvent
{

	private String supervisionRuleId;
	private String completionStatusId;
	private String monitorFrequencyId;
	private String conditionId;
	private Date completionDate;
	private String ruleTypeCd;
	
	//added for Production Support
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	
	
	/**
	 * @return the supervisionRuleId
	 */
	public String getSupervisionRuleId() {
		return supervisionRuleId;
	}

	/**
	 * @param supervisionRuleId the supervisionRuleId to set
	 */
	public void setSupervisionRuleId(String supervisionRuleId) {
		this.supervisionRuleId = supervisionRuleId;
	}

	/**
	 * @return the completionStatusId
	 */
	public String getCompletionStatusId() {
		return completionStatusId;
	}

	/**
	 * @param completionStatusId the completionStatusId to set
	 */
	public void setCompletionStatusId(String completionStatusId) {
		this.completionStatusId = completionStatusId;
	}

	/**
	 * @return the monitorFrequencyId
	 */
	public String getMonitorFrequencyId() {
		return monitorFrequencyId;
	}

	/**
	 * @param monitorFrequencyId the monitorFrequencyId to set
	 */
	public void setMonitorFrequencyId(String monitorFrequencyId) {
		this.monitorFrequencyId = monitorFrequencyId;
	}

	/**
	 * @return the conditionId
	 */
	public String getConditionId() {
		return conditionId;
	}

	/**
	 * @param conditionId the conditionId to set
	 */
	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}

	/**
	 * @return the completionDate
	 */
	public Date getCompletionDate() {
		return completionDate;
	}

	/**
	 * @param completionDate the completionDate to set
	 */
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	/**
	 * @return the ruleTypeCd
	 */
	public String getRuleTypeCd() {
		return ruleTypeCd;
	}

	/**
	 * @param ruleTypeCd the ruleTypeCd to set
	 */
	public void setRuleTypeCd(String ruleTypeCd) {
		this.ruleTypeCd = ruleTypeCd;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the createUserID
	 */
	public String getCreateUserID() {
		return createUserID;
	}

	/**
	 * @param createUserID the createUserID to set
	 */
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}

	/**
	 * @return the createJIMS2UserID
	 */
	public String getCreateJIMS2UserID() {
		return createJIMS2UserID;
	}

	/**
	 * @param createJIMS2UserID the createJIMS2UserID to set
	 */
	public void setCreateJIMS2UserID(String createJIMS2UserID) {
		this.createJIMS2UserID = createJIMS2UserID;
	}

	/**
	 * @return the updateJIMS2UserID
	 */
	public String getUpdateJIMS2UserID() {
		return updateJIMS2UserID;
	}

	/**
	 * @param updateJIMS2UserID the updateJIMS2UserID to set
	 */
	public void setUpdateJIMS2UserID(String updateJIMS2UserID) {
		this.updateJIMS2UserID = updateJIMS2UserID;
	}
	
}



