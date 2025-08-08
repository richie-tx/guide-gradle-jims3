/*
 * Created on Aug 22, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenilecase.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AssignmentResponseEvent extends ResponseEvent implements Comparable {
	
	private String assignmentId;
	private String referralNum;
	private Date assignmentDate;
	private String jpoUserId;
	private String caseloadManagerId;
	private String serviceUnitId;
	private String assessmentLevelId;
	private Boolean wasMigrated;
	private Boolean wasChecked;
	private Boolean isDup;
	private String caseFileId;
	
	//added for Production Support
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	private String assignmentType;
	private String refSeqNum;
	
	/**
	 * @return Returns the referralNum.
	 */
	public String getReferralNum() {
		return referralNum;
	}
	/**
	 * @param referralNum The referralNum to set.
	 */
	public void setReferralNum(String referralNum) {
		this.referralNum = referralNum;
	}
	
	/**
	 * @param assignmentDate the assignmentDate to set
	 */
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
	/**
	 * @return the assignmentDate
	 */
	public Date getAssignmentDate() {
		return assignmentDate;
	}
	
	/**
	 * @return the assignmentId
	 */
	public String getAssignmentId() {
		return assignmentId;
	}
	/**
	 * @param assignmentId the assignmentId to set
	 */
	public void setAssignmentId(String assignmentId) {
		this.assignmentId = assignmentId;
	}
	/**
	 * @return the jpoUserId
	 */
	public String getJpoUserId() {
		return jpoUserId;
	}
	/**
	 * @param jpoUserId the jpoUserId to set
	 */
	public void setJpoUserId(String jpoUserId) {
		this.jpoUserId = jpoUserId;
	}
	/**
	 * @return the caseloadManagerId
	 */
	public String getCaseloadManagerId() {
		return caseloadManagerId;
	}
	/**
	 * @param caseloadManagerId the caseloadManagerId to set
	 */
	public void setCaseloadManagerId(String caseloadManagerId) {
		this.caseloadManagerId = caseloadManagerId;
	}
	/**
	 * @return the serviceUnitId
	 */
	public String getServiceUnitId() {
		return serviceUnitId;
	}
	/**
	 * @param serviceUnitId the serviceUnitId to set
	 */
	public void setServiceUnitId(String serviceUnitId) {
		this.serviceUnitId = serviceUnitId;
	}
	/**
	 * @return the assessmentLevelId
	 */
	public String getAssessmentLevelId() {
		return assessmentLevelId;
	}
	/**
	 * @param assessmentLevelId the assessmentLevelId to set
	 */
	public void setAssessmentLevelId(String assessmentLevelId) {
		this.assessmentLevelId = assessmentLevelId;
	}
	/**
	 * @return the caseFileId
	 */
	public String getCaseFileId() {
		return caseFileId;
	}
	/**
	 * @param caseFileId the caseFileId to set
	 */
	public void setCaseFileId(String caseFileId) {
		this.caseFileId = caseFileId;
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
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
	 * @return the wasMigrated
	 */
	public Boolean getWasMigrated() {
		return wasMigrated;
	}
	
	/**
	 * @param wasMigrated the wasMigrated to set
	 */
	public void setWasMigrated(Boolean wasMigrated) {
		this.wasMigrated = wasMigrated;
	}
	
	/**
	 * @return the wasChecked
	 */
	public Boolean getWasChecked() {
		return wasChecked;
	}
	
	/**
	 * @param wasChecked the wasChecked to set
	 */
	public void setWasChecked(Boolean wasChecked) {
		this.wasChecked = wasChecked;
	}
	
	/**
	 * @return the isDup
	 */
	public Boolean getIsDup() {
		return isDup;
	}
	/**
	 * @param isDup the isDup to set
	 */
	public void setIsDup(Boolean isDup) {
		this.isDup = isDup;
	}
	/**
	 * @param updateJIMS2UserID the updateJIMS2UserID to set
	 */
	public void setUpdateJIMS2UserID(String updateJIMS2UserID) {
		this.updateJIMS2UserID = updateJIMS2UserID;
	}
	
	public int compareTo(Object obj)
	{
		if(obj==null)
			return -1;
		AssignmentResponseEvent evt = (AssignmentResponseEvent) obj;
		return referralNum.compareToIgnoreCase(evt.getReferralNum());		
	}
	public String getAssignmentType()
	{
	    return assignmentType;
	}
	public void setAssignmentType(String assignmentType)
	{
	    this.assignmentType = assignmentType;
	}
	public String getRefSeqNum()
	{
	    return refSeqNum;
	}
	public void setRefSeqNum(String refSeqNum)
	{
	    this.refSeqNum = refSeqNum;
	}
	
}
