/*
 * Created on Nov 3, 2006
 */
package messaging.caseplan.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dapte
 * 
 */
public class GoalRuleDetailsResponseEvent extends ResponseEvent {
	private String caseGoalId;

	private String goalDesc;

	private String goalTimeFrameCd;

	private String caseplanId;

	private String responsibleName;
	private String intervention; //added Intervention ER JIMS200075816 

	private String supRuleId;

	private String caseGoalPrespId;

	private String unformattedDesc;

	private String resolveDesc;

	private String goalDomainTypeCd;

	private String goalStatusCd;

	private String ruleMonitorFreqId;

	private String ruleMonitorFreqDescr;
	
	private String explainOtherText;

	public GoalRuleDetailsResponseEvent() {
	}

	/**
	 * @return Returns the caseGoalId.
	 */
	public String getCaseGoalId() {
		return caseGoalId;
	}

	/**
	 * @param caseGoalId
	 *            The caseGoalId to set.
	 */
	public void setCaseGoalId(String caseGoalId) {
		this.caseGoalId = caseGoalId;
	}

	/**
	 * @return Returns the caseplanId.
	 */
	public String getCaseplanId() {
		return caseplanId;
	}

	/**
	 * @param caseplanId
	 *            The caseplanId to set.
	 */
	public void setCaseplanId(String caseplanId) {
		this.caseplanId = caseplanId;
	}

	/**
	 * @return Returns the goalDesc.
	 */
	public String getGoalDesc() {
		return goalDesc;
	}

	/**
	 * @param goalDesc
	 *            The goalDesc to set.
	 */
	public void setGoalDesc(String goalDesc) {
		this.goalDesc = goalDesc;
	}

	/**
	 * @return Returns the goalTimeFrameCd.
	 */
	public String getGoalTimeFrameCd() {
		return goalTimeFrameCd;
	}

	/**
	 * @param goalTimeFrameCd
	 *            The goalTimeFrameCd to set.
	 */
	public void setGoalTimeFrameCd(String goalTimeFrameCd) {
		this.goalTimeFrameCd = goalTimeFrameCd;
	}

	/**
	 * @return Returns the responsibleName.
	 */
	public String getResponsibleName() {
		return responsibleName;
	}

	/**
	 * @param responsibleName
	 *            The responsibleName to set.
	 */
	public void setResponsibleName(String responsibleName) {
		this.responsibleName = responsibleName;
	}
	
	/**
	 * @return Returns the intervention.
	 */
	public String getIntervention() {
		return intervention;
	}

	/**
	 * @param responsibleName
	 *            The responsibleName to set.
	 */
	public void setIntervention(String intervention) {
		this.intervention = intervention;
	}

	/**
	 * @return Returns the supRuleId.
	 */
	public String getSupRuleId() {
		return supRuleId;
	}

	/**
	 * @param supRuleId
	 *            The supRuleId to set.
	 */
	public void setSupRuleId(String supRuleId) {
		this.supRuleId = supRuleId;
	}

	/**
	 * @return Returns the caseGoalPrespId.
	 */
	public String getCaseGoalPrespId() {
		return caseGoalPrespId;
	}

	/**
	 * @param caseGoalPrespId
	 *            The caseGoalPrespId to set.
	 */
	public void setCaseGoalPrespId(String caseGoalPrespId) {
		this.caseGoalPrespId = caseGoalPrespId;
	}

	/**
	 * @return Returns the resolveDesc.
	 */
	public String getResolveDesc() {
		return resolveDesc;
	}

	/**
	 * @param resolveDesc
	 *            The resolveDesc to set.
	 */
	public void setResolveDesc(String resolveDesc) {
		this.resolveDesc = resolveDesc;
	}

	/**
	 * @return Returns the unformattedDesc.
	 */
	public String getUnformattedDesc() {
		return unformattedDesc;
	}

	/**
	 * @param unformattedDesc
	 *            The unformattedDesc to set.
	 */
	public void setUnformattedDesc(String unformattedDesc) {
		this.unformattedDesc = unformattedDesc;
	}

	/**
	 * @return Returns the goalDomainTypeCd.
	 */
	public String getGoalDomainTypeCd() {
		return goalDomainTypeCd;
	}

	/**
	 * @param goalDomainTypeCd
	 *            The goalDomainTypeCd to set.
	 */
	public void setGoalDomainTypeCd(String goalDomainTypeCd) {
		this.goalDomainTypeCd = goalDomainTypeCd;
	}

	/**
	 * @return Returns the goalStatusCd.
	 */
	public String getGoalStatusCd() {
		return goalStatusCd;
	}

	/**
	 * @param goalStatusCd
	 *            The goalStatusCd to set.
	 */
	public void setGoalStatusCd(String goalStatusCd) {
		this.goalStatusCd = goalStatusCd;
	}

	/**
	 * @return the ruleMonitorFreqDescr
	 */
	public String getRuleMonitorFreqDescr() {
		return ruleMonitorFreqDescr;
	}

	/**
	 * @param ruleMonitorFreqDescr
	 *            the ruleMonitorFreqDescr to set
	 */
	public void setRuleMonitorFreqDescr(String ruleMonitorFreqDescr) {
		this.ruleMonitorFreqDescr = ruleMonitorFreqDescr;
	}

	/**
	 * @return the ruleMonitorFreqId
	 */
	public String getRuleMonitorFreqId() {
		return ruleMonitorFreqId;
	}

	/**
	 * @param ruleMonitorFreqId
	 *            the ruleMonitorFreqId to set
	 */
	public void setRuleMonitorFreqId(String ruleMonitorFreqId) {
		this.ruleMonitorFreqId = ruleMonitorFreqId;
	}
	/**
	 * @return the explainOtherText
	 */
	public String getExplainOtherText() {
		return explainOtherText;
	}

	/**
	 * @param explainOtherText the explainOtherText to set
	 */
	public void setExplainOtherText(String explainOtherText) {
		this.explainOtherText = explainOtherText;
	}
}
