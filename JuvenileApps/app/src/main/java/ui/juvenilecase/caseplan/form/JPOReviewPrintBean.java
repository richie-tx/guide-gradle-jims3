/*
 * Created on Jun 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.caseplan.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import messaging.caseplan.reply.CaseplanDocJuvDetailsResponseEvent;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JPOReviewPrintBean {
	private CaseplanDocJuvDetailsResponseEvent juvDetails;
	private String casefileId;
	private Date expectedSupervisionEndDate;
	
	private List goals;
	private List riskAnalysisProgressAssessments;
	private List programReferrals;
	private List rules;
	private List msrActivities;
	private List revActivities;
	private String comments;
	
	//added for user story 11146
	private String supervisionLevel;
	private String recomSupervisionLevel;
	private String supLevelApproStr;
	private String contactInformation;
	private String jpoMaintainContactStr;
	private String jpoMaintainExplain;
	
	public JPOReviewPrintBean(){
		casefileId = "";
		comments = "";
		goals = new ArrayList();
		riskAnalysisProgressAssessments = new ArrayList();
		programReferrals = new ArrayList();
		rules = new ArrayList();
		msrActivities = new ArrayList();
		revActivities = new ArrayList();
	}
	
	/**
	 * @return Returns the casefileId.
	 */
	public String getCasefileId() {
		return casefileId;
	}
	/**
	 * @param casefileId The casefileId to set.
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	/**
	 * @return Returns the expectedSupervisionEndDate.
	 */
	public Date getExpectedSupervisionEndDate() {
		return expectedSupervisionEndDate;
	}
	/**
	 * @param expectedSupervisionEndDate The expectedSupervisionEndDate to set.
	 */
	public void setExpectedSupervisionEndDate(Date expectedSupervisionEndDate) {
		this.expectedSupervisionEndDate = expectedSupervisionEndDate;
	}
	/**
	 * @return Returns the juvDetails.
	 */
	public CaseplanDocJuvDetailsResponseEvent getJuvDetails() {
		return juvDetails;
	}
	/**
	 * @param juvDetails The juvDetails to set.
	 */
	public void setJuvDetails(CaseplanDocJuvDetailsResponseEvent juvDetails) {
		this.juvDetails = juvDetails;
	}
	/**
	 * @return Returns the goals.
	 */
	public List getGoals() {
		return goals;
	}
	/**
	 * @param goals The goals to set.
	 */
	public void setGoals(List goals) {
		this.goals = goals;
	}
	/**
	 * @return Returns the msrActivities.
	 */
	public List getMsrActivities() {
		return msrActivities;
	}
	/**
	 * @param msrActivities The msrActivities to set.
	 */
	public void setMsrActivities(List msrActivities) {
		this.msrActivities = msrActivities;
	}
	/**
	 * @return Returns the programReferrals.
	 */
	public List getProgramReferrals() {
		return programReferrals;
	}
	/**
	 * @param programReferrals The programReferrals to set.
	 */
	public void setProgramReferrals(List programReferrals) {
		this.programReferrals = programReferrals;
	}
	/**
	 * @return Returns the revActivities.
	 */
	public List getRevActivities() {
		return revActivities;
	}
	/**
	 * @param revActivities The revActivities to set.
	 */
	public void setRevActivities(List revActivities) {
		this.revActivities = revActivities;
	}
	/**
	 * @return Returns the riskAnalysisProgressAssessments.
	 */
	public List getRiskAnalysisProgressAssessments() {
		return riskAnalysisProgressAssessments;
	}
	/**
	 * @param riskAnalysisProgressAssessments The riskAnalysisProgressAssessments to set.
	 */
	public void setRiskAnalysisProgressAssessments(List riskAnalysisProgressAssessments) {
		this.riskAnalysisProgressAssessments = riskAnalysisProgressAssessments;
	}
	/**
	 * @return Returns the rules.
	 */
	public List getRules() {
		return rules;
	}
	/**
	 * @param rules The rules to set.
	 */
	public void setRules(List rules) {
		this.rules = rules;
	}
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	/**
	 * @return the supervisionLevel
	 */
	public String getSupervisionLevel() {
		return supervisionLevel;
	}

	/**
	 * @param supervisionLevel the supervisionLevel to set
	 */
	public void setSupervisionLevel(String supervisionLevel) {
		this.supervisionLevel = supervisionLevel;
	}

	/**
	 * @return the recomSupervisionLevel
	 */
	public String getRecomSupervisionLevel() {
		return recomSupervisionLevel;
	}

	/**
	 * @param recomSupervisionLevel the recomSupervisionLevel to set
	 */
	public void setRecomSupervisionLevel(String recomSupervisionLevel) {
		this.recomSupervisionLevel = recomSupervisionLevel;
	}

	/**
	 * @return the supLevelApproStr
	 */
	public String getSupLevelApproStr() {
		return supLevelApproStr;
	}

	/**
	 * @param supLevelApproStr the supLevelApproStr to set
	 */
	public void setSupLevelApproStr(String supLevelApproStr) {
		this.supLevelApproStr = supLevelApproStr;
	}

	/**
	 * @return the contactInformation
	 */
	public String getContactInformation() {
		return contactInformation;
	}

	/**
	 * @param contactInformation the contactInformation to set
	 */
	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	/**
	 * @return the jpoMaintainContactStr
	 */
	public String getJpoMaintainContactStr() {
		return jpoMaintainContactStr;
	}

	/**
	 * @param jpoMaintainContactStr the jpoMaintainContactStr to set
	 */
	public void setJpoMaintainContactStr(String jpoMaintainContactStr) {
		this.jpoMaintainContactStr = jpoMaintainContactStr;
	}

	/**
	 * @return the jpoMaintainExplain
	 */
	public String getJpoMaintainExplain() {
		return jpoMaintainExplain;
	}

	/**
	 * @param jpoMaintainExplain the jpoMaintainExplain to set
	 */
	public void setJpoMaintainExplain(String jpoMaintainExplain) {
		this.jpoMaintainExplain = jpoMaintainExplain;
	}

	public Date getTodaysDate() {
		return new Date();
	}
	
}
