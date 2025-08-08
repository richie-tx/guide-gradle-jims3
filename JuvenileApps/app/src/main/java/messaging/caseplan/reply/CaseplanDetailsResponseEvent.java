/*
 * Created on Nov 3, 2006
 */
package messaging.caseplan.reply;

import java.util.Date;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.ResponseEvent;

/**
 * @author dapte
 *
 */
public class CaseplanDetailsResponseEvent extends ResponseEvent implements IAddressable {
	
	private String caseplanID;
	private Date reviewDate;
	private String statusId;
	private String placementId;
	// used for CLM Review
	private String jPORequestReviewComments;
	
	private String notificationMessage;
	private String subject;
	private String identity;
	
	
	//added for Production Support
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;

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
	 * @return Returns the createDate.
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate The createDate to set.
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return Returns the reviewDate.
	 */
	public Date getReviewDate() {
		return reviewDate;
	}
	/**
	 * @param reviewDate The reviewDate to set.
	 */
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	
	/**
	 * @return Returns the jPORequestReviewComments.
	 */
	public String getJPORequestReviewComments() {
		return jPORequestReviewComments;
	}
	
	/**
	 * @param requestReviewComments The jPORequestReviewComments to set.
	 */
	public void setJPORequestReviewComments(String requestReviewComments) {
		jPORequestReviewComments = requestReviewComments;
	}

	/**
	 * @return Returns the notificationMessage.
	 */
	public String getNotificationMessage() {
		return notificationMessage;
	}
	/**
	 * @param notificationMessage The notificationMessage to set.
	 */
	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}
	/**
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return Returns the identity.
	 */
	public String getIdentity() {
		return identity;
	}
	/**
	 * @param identity The identity to set.
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	/**
	 * @return the jPORequestReviewComments
	 */
	public String getjPORequestReviewComments() {
		return jPORequestReviewComments;
	}
	/**
	 * @param jPORequestReviewComments the jPORequestReviewComments to set
	 */
	public void setjPORequestReviewComments(String jPORequestReviewComments) {
		this.jPORequestReviewComments = jPORequestReviewComments;
	}
	
	/**
	 * @return the placementId
	 */
	public String getPlacementId() {
		return placementId;
	}
	/**
	 * @param placementId the placementId to set
	 */
	public void setPlacementId(String placementId) {
		this.placementId = placementId;
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
