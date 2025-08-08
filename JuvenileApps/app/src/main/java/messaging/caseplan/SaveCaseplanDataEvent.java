
package messaging.caseplan;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveCaseplanDataEvent extends RequestEvent 
{
	private String caseplanID;
	private String casefileId;	
	private String supervisionNumber;
	private String priorServices;
	private String supLevelId;
	private String contactInfo;
	
	//added for User story 11191 Add Title IV in caseplan
	
	private boolean juvFosterCareCandidate;
	private Date socialHistDated;
	private Date psychologicalRepDated;
	private Date riskAssesmentDated;
	private String titleIVEComment;
	private Date dtDeterminationMade;
	private Date otherDated;
	private String explanation;
	
	//added for User Story 11146
	private String othername;
    private Date childDtNotified;
    private Date familyDtNotified;
    private Date caregiverDtNotified;
    private Date otherDtNotified;
    private String childNotifMethod;
    private String familyNotifMethod;
    private String caregiverNotifMethod;
    private String otherNameNotifMethod;
    private Date childDtOfParticipation;
    private Date familyDtOfParticipation;
    private Date caregiverDtOfParticipation;
    private Date otherNameDtOfParticipation;
    private Date childMailedDt;
    private Date familyMailedDt;
    private Date caregiverMailedDt;
    private Date otherNameMailedDt;
	
	
	   /**
	    * @roseuid 4533BD1C0310
	    */
	   public SaveCaseplanDataEvent() 
	   {
	    
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
	public Date getSocialHistDated() {
		return socialHistDated;
	}
	/**
	 * @param socialHistDated the socialHistDated to set
	 */
	public void setSocialHistDated(Date socialHistDated) {
		this.socialHistDated = socialHistDated;
	}
	/**
	 * @return the psychologicalRepDated
	 */
	public Date getPsychologicalRepDated() {
		return psychologicalRepDated;
	}
	/**
	 * @param psychologicalRepDated the psychologicalRepDated to set
	 */
	public void setPsychologicalRepDated(Date psychologicalRepDated) {
		this.psychologicalRepDated = psychologicalRepDated;
	}
	/**
	 * @return the riskAssesmentDated
	 */
	public Date getRiskAssesmentDated() {
		return riskAssesmentDated;
	}
	/**
	 * @param riskAssesmentDated the riskAssesmentDated to set
	 */
	public void setRiskAssesmentDated(Date riskAssesmentDated) {
		this.riskAssesmentDated = riskAssesmentDated;
	}
	
	/**
	 * @return the otherDated
	 */
	public Date getOtherDated() {
		return otherDated;
	}
	/**
	 * @param otherDated the otherDated to set
	 */
	public void setOtherDated(Date otherDated) {
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
	public Date getChildDtNotified() {
		return childDtNotified;
	}
	/**
	 * @param childDtNotified the childDtNotified to set
	 */
	public void setChildDtNotified(Date childDtNotified) {
		this.childDtNotified = childDtNotified;
	}
	/**
	 * @return the familyDtNotified
	 */
	public Date getFamilyDtNotified() {
		return familyDtNotified;
	}
	/**
	 * @param familyDtNotified the familyDtNotified to set
	 */
	public void setFamilyDtNotified(Date familyDtNotified) {
		this.familyDtNotified = familyDtNotified;
	}
	/**
	 * @return the caregiverDtNotified
	 */
	public Date getCaregiverDtNotified() {
		return caregiverDtNotified;
	}
	/**
	 * @param caregiverDtNotified the caregiverDtNotified to set
	 */
	public void setCaregiverDtNotified(Date caregiverDtNotified) {
		this.caregiverDtNotified = caregiverDtNotified;
	}
	/**
	 * @return the otherDtNotified
	 */
	public Date getOtherDtNotified() {
		return otherDtNotified;
	}
	/**
	 * @param otherDtNotified the otherDtNotified to set
	 */
	public void setOtherDtNotified(Date otherDtNotified) {
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
	public Date getChildDtOfParticipation() {
		return childDtOfParticipation;
	}
	/**
	 * @param childDtOfParticipation the childDtOfParticipation to set
	 */
	public void setChildDtOfParticipation(Date childDtOfParticipation) {
		this.childDtOfParticipation = childDtOfParticipation;
	}
	/**
	 * @return the familyDtOfParticipation
	 */
	public Date getFamilyDtOfParticipation() {
		return familyDtOfParticipation;
	}
	/**
	 * @param familyDtOfParticipation the familyDtOfParticipation to set
	 */
	public void setFamilyDtOfParticipation(Date familyDtOfParticipation) {
		this.familyDtOfParticipation = familyDtOfParticipation;
	}
	/**
	 * @return the caregiverDtOfParticipation
	 */
	public Date getCaregiverDtOfParticipation() {
		return caregiverDtOfParticipation;
	}
	/**
	 * @param caregiverDtOfParticipation the caregiverDtOfParticipation to set
	 */
	public void setCaregiverDtOfParticipation(Date caregiverDtOfParticipation) {
		this.caregiverDtOfParticipation = caregiverDtOfParticipation;
	}
	/**
	 * @return the otherNameDtOfParticipation
	 */
	public Date getOtherNameDtOfParticipation() {
		return otherNameDtOfParticipation;
	}
	/**
	 * @param otherNameDtOfParticipation the otherNameDtOfParticipation to set
	 */
	public void setOtherNameDtOfParticipation(Date otherNameDtOfParticipation) {
		this.otherNameDtOfParticipation = otherNameDtOfParticipation;
	}
	/**
	 * @return the childMailedDt
	 */
	public Date getChildMailedDt() {
		return childMailedDt;
	}
	/**
	 * @param childMailedDt the childMailedDt to set
	 */
	public void setChildMailedDt(Date childMailedDt) {
		this.childMailedDt = childMailedDt;
	}
	/**
	 * @return the familyMailedDt
	 */
	public Date getFamilyMailedDt() {
		return familyMailedDt;
	}
	/**
	 * @param familyMailedDt the familyMailedDt to set
	 */
	public void setFamilyMailedDt(Date familyMailedDt) {
		this.familyMailedDt = familyMailedDt;
	}
	/**
	 * @return the caregiverMailedDt
	 */
	public Date getCaregiverMailedDt() {
		return caregiverMailedDt;
	}
	/**
	 * @param caregiverMailedDt the caregiverMailedDt to set
	 */
	public void setCaregiverMailedDt(Date caregiverMailedDt) {
		this.caregiverMailedDt = caregiverMailedDt;
	}
	/**
	 * @return the otherNameMailedDt
	 */
	public Date getOtherNameMailedDt() {
		return otherNameMailedDt;
	}
	/**
	 * @param otherNameMailedDt the otherNameMailedDt to set
	 */
	public void setOtherNameMailedDt(Date otherNameMailedDt) {
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
	public Date getDtDeterminationMade() {
		return dtDeterminationMade;
	}
	/**
	 * @param dtDeterminationMade the dtDeterminationMade to set
	 */
	public void setDtDeterminationMade(Date dtDeterminationMade) {
		this.dtDeterminationMade = dtDeterminationMade;
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
	 * @return Returns the pending.
	 *//*
	public boolean isPending() {
		return pending;
	}
	*//**
	 * @param pending The pending to set.
	 *//*
	public void setPending(boolean pending) {
		this.pending = pending;
	}*/
}
