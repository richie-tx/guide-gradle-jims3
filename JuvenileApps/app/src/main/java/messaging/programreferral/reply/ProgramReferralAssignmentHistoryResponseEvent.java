package messaging.programreferral.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class ProgramReferralAssignmentHistoryResponseEvent extends ResponseEvent  implements Comparable {
	private String programReferralAssignmentHistoryId;
	private String programReferralId;
	private String casefileId;
	private Date programReferralAssignmentDate;
	
	//Task 39399 
	private String casefileAssignDate;	
	private String casefileEndDate;
	private String cntrlRefNum;
	
	//added for Production Support
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	
	
	/**
	 * @return the programReferralAssignmentHistoryId
	 */
	public String getProgramReferralAssignmentHistoryId() {
		return programReferralAssignmentHistoryId;
	}
	/**
	 * @param programReferralAssignmentHistoryId the programReferralAssignmentHistoryId to set
	 */
	public void setProgramReferralAssignmentHistoryId(
			String programReferralAssignmentHistoryId) {
		this.programReferralAssignmentHistoryId = programReferralAssignmentHistoryId;
	}
	public String getProgramReferralId() {
		return programReferralId;
	}
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}
	public String getCasefileId() {
		return casefileId;
	}
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	public Date getProgramReferralAssignmentDate() {
		return programReferralAssignmentDate;
	}
	public void setProgramReferralAssignmentDate(Date programReferralAssignmentDate) {
		this.programReferralAssignmentDate = programReferralAssignmentDate;
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
	 * @param updateJIMS2UserID the updateJIMS2UserID to set
	 */
	public void setUpdateJIMS2UserID(String updateJIMS2UserID) {
		this.updateJIMS2UserID = updateJIMS2UserID;
	}
	public int compareTo(Object obj) {
		if(obj==null)
			return -1;
		ProgramReferralAssignmentHistoryResponseEvent evt = (ProgramReferralAssignmentHistoryResponseEvent) obj;
		int result = 0;
		if(getProgramReferralAssignmentDate() != null && evt.getProgramReferralAssignmentDate() != null )
		{
			try{
				if(this.programReferralAssignmentDate!=null || evt.getProgramReferralAssignmentDate()!=null){
					if(evt.getProgramReferralAssignmentDate()==null)
						return 1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
					if(this.programReferralAssignmentDate==null)
						return -1; // this makes any null objects go to the bottom change this to 1 if you want the top of the list
					
					if(this.programReferralAssignmentDate==null)
						return -1;
					if(evt.getProgramReferralAssignmentDate()==null)
						return 1;
					result= evt.getProgramReferralAssignmentDate().compareTo(this.programReferralAssignmentDate); // backwards in order to get list to show up most recent first
				}
			}
			catch(NumberFormatException e){
				result = 0;
			}
		}
		return result;
	}
	public String getCasefileAssignDate() {
		return casefileAssignDate;
	}
	public void setCasefileAssignDate(String string) {
		this.casefileAssignDate = string;
	}
	
	public String getCasefileEndDate() {
		return casefileEndDate;
	}
	public void setCasefileEndDate(String string) {
		this.casefileEndDate = string;
	}
	public String getCntrlRefNum() {
		return cntrlRefNum;
	}
	public void setCntrlRefNum(String cntrlRefNum) {
		this.cntrlRefNum = cntrlRefNum;
	}
	
}
