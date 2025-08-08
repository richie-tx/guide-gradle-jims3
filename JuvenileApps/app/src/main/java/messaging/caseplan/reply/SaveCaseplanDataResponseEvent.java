package messaging.caseplan.reply;

import mojo.km.messaging.ResponseEvent;

public class SaveCaseplanDataResponseEvent extends ResponseEvent{
	
	private String caseplanID;
	private String supervisionNumber;
	private String priorServices;
	private String supLevelId;
	private String contactInfo;
	
	//added for User story 11191 Add Title IV in caseplan
	private boolean juvFosterCareCandidate;
	private String socialHistDated;
	private String psychologicalRepDated;
	private String riskAssesmentDated;
	private String titleIVEComment;
	private String dtDeterminationMade;
	private String otherDated;
	private String explanation;
	
	//added for user story 11146
    private String othername;
    private String childDtNotified;
    private String familyDtNotified;
    private String caregiverDtNotified;
    private String otherDtNotified;
    private String childNotifMethod;
    private String familyNotifMethod;
    private String caregiverNotifMethod;
    private String otherNameNotifMethod;
    private String childDtOfParticipation;
    private String familyDtOfParticipation;
    private String caregiverDtOfParticipation;
    private String otherNameDtOfParticipation;
    private String childMailedDt;
    private String familyMailedDt;
    private String caregiverMailedDt;
    private String otherNameMailedDt;
	
	public String getPriorServices() {
		return priorServices;
	}
	public void setPriorServices(String priorServices) {
		this.priorServices = priorServices;
	}
	public String getSupLevelId() {
		return supLevelId;
	}
	public void setSupLevelId(String supLevelId) {
		this.supLevelId = supLevelId;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	/**
	 * @return the juvFosterCareCandidate
	 */
	public boolean isJuvFosterCareCandidate() {
		return juvFosterCareCandidate;
	}
	/**
	 * @param juvFosterCareCandidate the juvFosterCareCandidate to set
	 */
	public void setJuvFosterCareCandidate(boolean juvFosterCareCandidate) {
		this.juvFosterCareCandidate = juvFosterCareCandidate;
	}
	/**
	 * @return the socialHistDated
	 */
	public String getSocialHistDated() {
		return socialHistDated;
	}
	/**
	 * @param socialHistDated the socialHistDated to set
	 */
	public void setSocialHistDated(String socialHistDated) {
		this.socialHistDated = socialHistDated;
	}
	/**
	 * @return the psychologicalRepDated
	 */
	public String getPsychologicalRepDated() {
		return psychologicalRepDated;
	}
	/**
	 * @param psychologicalRepDated the psychologicalRepDated to set
	 */
	public void setPsychologicalRepDated(String psychologicalRepDated) {
		this.psychologicalRepDated = psychologicalRepDated;
	}
	/**
	 * @return the riskAssesmentDated
	 */
	public String getRiskAssesmentDated() {
		return riskAssesmentDated;
	}
	/**
	 * @param riskAssesmentDated the riskAssesmentDated to set
	 */
	public void setRiskAssesmentDated(String riskAssesmentDated) {
		this.riskAssesmentDated = riskAssesmentDated;
	}
	
	/**
	 * @return the otherDated
	 */
	public String getOtherDated() {
		return otherDated;
	}
	/**
	 * @param otherDated the otherDated to set
	 */
	public void setOtherDated(String otherDated) {
		this.otherDated = otherDated;
	}
	
	/**
	 * @return the explanation
	 */
	public String getExplanation() {
		return explanation;
	}
	/**
	 * @param explanation the explanation to set
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	/**
	 * @return the othername
	 */
	public String getOthername() {
		return othername;
	}
	/**
	 * @param othername the othername to set
	 */
	public void setOthername(String othername) {
		this.othername = othername;
	}
	/**
	 * @return the childDtNotified
	 */
	public String getChildDtNotified() {
		return childDtNotified;
	}
	/**
	 * @param childDtNotified the childDtNotified to set
	 */
	public void setChildDtNotified(String childDtNotified) {
		this.childDtNotified = childDtNotified;
	}
	/**
	 * @return the familyDtNotified
	 */
	public String getFamilyDtNotified() {
		return familyDtNotified;
	}
	/**
	 * @param familyDtNotified the familyDtNotified to set
	 */
	public void setFamilyDtNotified(String familyDtNotified) {
		this.familyDtNotified = familyDtNotified;
	}
	/**
	 * @return the caregiverDtNotified
	 */
	public String getCaregiverDtNotified() {
		return caregiverDtNotified;
	}
	/**
	 * @param caregiverDtNotified the caregiverDtNotified to set
	 */
	public void setCaregiverDtNotified(String caregiverDtNotified) {
		this.caregiverDtNotified = caregiverDtNotified;
	}
	/**
	 * @return the otherDtNotified
	 */
	public String getOtherDtNotified() {
		return otherDtNotified;
	}
	/**
	 * @param otherDtNotified the otherDtNotified to set
	 */
	public void setOtherDtNotified(String otherDtNotified) {
		this.otherDtNotified = otherDtNotified;
	}
	/**
	 * @return the childNotifMethod
	 */
	public String getChildNotifMethod() {
		return childNotifMethod;
	}
	/**
	 * @param childNotifMethod the childNotifMethod to set
	 */
	public void setChildNotifMethod(String childNotifMethod) {
		this.childNotifMethod = childNotifMethod;
	}
	/**
	 * @return the familyNotifMethod
	 */
	public String getFamilyNotifMethod() {
		return familyNotifMethod;
	}
	/**
	 * @param familyNotifMethod the familyNotifMethod to set
	 */
	public void setFamilyNotifMethod(String familyNotifMethod) {
		this.familyNotifMethod = familyNotifMethod;
	}
	/**
	 * @return the caregiverNotifMethod
	 */
	public String getCaregiverNotifMethod() {
		return caregiverNotifMethod;
	}
	/**
	 * @param caregiverNotifMethod the caregiverNotifMethod to set
	 */
	public void setCaregiverNotifMethod(String caregiverNotifMethod) {
		this.caregiverNotifMethod = caregiverNotifMethod;
	}
	/**
	 * @return the otherNameNotifMethod
	 */
	public String getOtherNameNotifMethod() {
		return otherNameNotifMethod;
	}
	/**
	 * @param otherNameNotifMethod the otherNameNotifMethod to set
	 */
	public void setOtherNameNotifMethod(String otherNameNotifMethod) {
		this.otherNameNotifMethod = otherNameNotifMethod;
	}
	/**
	 * @return the childDtOfParticipation
	 */
	public String getChildDtOfParticipation() {
		return childDtOfParticipation;
	}
	/**
	 * @param childDtOfParticipation the childDtOfParticipation to set
	 */
	public void setChildDtOfParticipation(String childDtOfParticipation) {
		this.childDtOfParticipation = childDtOfParticipation;
	}
	/**
	 * @return the familyDtOfParticipation
	 */
	public String getFamilyDtOfParticipation() {
		return familyDtOfParticipation;
	}
	/**
	 * @param familyDtOfParticipation the familyDtOfParticipation to set
	 */
	public void setFamilyDtOfParticipation(String familyDtOfParticipation) {
		this.familyDtOfParticipation = familyDtOfParticipation;
	}
	/**
	 * @return the caregiverDtOfParticipation
	 */
	public String getCaregiverDtOfParticipation() {
		return caregiverDtOfParticipation;
	}
	/**
	 * @param caregiverDtOfParticipation the caregiverDtOfParticipation to set
	 */
	public void setCaregiverDtOfParticipation(String caregiverDtOfParticipation) {
		this.caregiverDtOfParticipation = caregiverDtOfParticipation;
	}
	/**
	 * @return the otherNameDtOfParticipation
	 */
	public String getOtherNameDtOfParticipation() {
		return otherNameDtOfParticipation;
	}
	/**
	 * @param otherNameDtOfParticipation the otherNameDtOfParticipation to set
	 */
	public void setOtherNameDtOfParticipation(String otherNameDtOfParticipation) {
		this.otherNameDtOfParticipation = otherNameDtOfParticipation;
	}
	/**
	 * @return the childMailedDt
	 */
	public String getChildMailedDt() {
		return childMailedDt;
	}
	/**
	 * @param childMailedDt the childMailedDt to set
	 */
	public void setChildMailedDt(String childMailedDt) {
		this.childMailedDt = childMailedDt;
	}
	/**
	 * @return the familyMailedDt
	 */
	public String getFamilyMailedDt() {
		return familyMailedDt;
	}
	/**
	 * @param familyMailedDt the familyMailedDt to set
	 */
	public void setFamilyMailedDt(String familyMailedDt) {
		this.familyMailedDt = familyMailedDt;
	}
	/**
	 * @return the caregiverMailedDt
	 */
	public String getCaregiverMailedDt() {
		return caregiverMailedDt;
	}
	/**
	 * @param caregiverMailedDt the caregiverMailedDt to set
	 */
	public void setCaregiverMailedDt(String caregiverMailedDt) {
		this.caregiverMailedDt = caregiverMailedDt;
	}
	/**
	 * @return the otherNameMailedDt
	 */
	public String getOtherNameMailedDt() {
		return otherNameMailedDt;
	}
	/**
	 * @param otherNameMailedDt the otherNameMailedDt to set
	 */
	public void setOtherNameMailedDt(String otherNameMailedDt) {
		this.otherNameMailedDt = otherNameMailedDt;
	}
	/**
	 * @return the titleIVEComment
	 */
	public String getTitleIVEComment() {
		return titleIVEComment;
	}
	/**
	 * @param titleIVEComment the titleIVEComment to set
	 */
	public void setTitleIVEComment(String titleIVEComment) {
		this.titleIVEComment = titleIVEComment;
	}
	/**
	 * @return the dtDeterminationMade
	 */
	public String getDtDeterminationMade() {
		return dtDeterminationMade;
	}
	/**
	 * @param dtDeterminationMade the dtDeterminationMade to set
	 */
	public void setDtDeterminationMade(String dtDeterminationMade) {
		this.dtDeterminationMade = dtDeterminationMade;
	}
	/**
	 * @return Returns the caseplanID.
	 */
	public String getCaseplanID() {
		return caseplanID;
	}
	/**
	 * @param caseplanID The caseplanID to set.
	 */
	public void setCaseplanID(String caseplanID) {
		this.caseplanID = caseplanID;
	}
	/**
	 * @return Returns the supervisionNumber.
	 */
	public String getSupervisionNumber() {
		return supervisionNumber;
	}
	/**
	 * @param supervisionNumber The supervisionNumber to set.
	 */
	public void setSupervisionNumber(String supervisionNumber) {
		this.supervisionNumber = supervisionNumber;
	}

}
