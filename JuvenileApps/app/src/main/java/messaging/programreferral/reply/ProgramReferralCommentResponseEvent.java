/*
 * Created on May 17, 2007
 *
 */
package messaging.programreferral.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;


/**
 *
 */
public class ProgramReferralCommentResponseEvent extends ResponseEvent implements Comparable{
	
	private String programReferralCommentId;
	
	private String commentText;

	private String userName;
	
	private String programReferralId;
	
	private Date commentsDate;
	
	//added for Production Support
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	
	
	/**
	 * @return the programReferralCommentId
	 */
	public String getProgramReferralCommentId() {
		return programReferralCommentId;
	}
	
	/**
	 * @param programReferralCommentId the programReferralCommentId to set
	 */
	public void setProgramReferralCommentId(String programReferralCommentId) {
		this.programReferralCommentId = programReferralCommentId;
	}
	
	/**
	 * @return Returns the commentsDate.
	 */
	public Date getCommentsDate() {
		return commentsDate;
	}
	/**
	 * @param commentsDate The commentsDate to set.
	 */
	public void setCommentsDate(Date commentsDate) {
		this.commentsDate = commentsDate;
	}
	/**
	 * @return Returns the commentText.
	 */
	public String getCommentText() {
		return commentText;
	}
	/**
	 * @param commentText The commentText to set.
	 */
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	/**
	 * @return Returns the programReferralId.
	 */
	public String getProgramReferralId() {
		return programReferralId;
	}
	/**
	 * @param programReferralId The programReferralId to set.
	 */
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}
	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		if (obj==null || ! (obj instanceof ProgramReferralCommentResponseEvent))
			return 0;		
		ProgramReferralCommentResponseEvent resp = (ProgramReferralCommentResponseEvent)obj;
		return ((this.getCommentsDate()==null || resp.getCommentsDate()==null) ? 0 : this.getCommentsDate().compareTo(resp.getCommentsDate()));
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
	 * @param updateJIMS2UserID the updateJIMS2UserID to set
	 */
	public void setUpdateJIMS2UserID(String updateJIMS2UserID) {
		this.updateJIMS2UserID = updateJIMS2UserID;
	}
	
}
