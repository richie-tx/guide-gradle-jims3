package messaging.facility.reply;

import java.util.Date;

import messaging.identityaddress.domintf.IAddressable;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import mojo.km.messaging.ResponseEvent;

public class JuvenileFacilitySplAttnReasonResponseEvent extends ResponseEvent implements IAddressable,Comparable<JuvenileFacilitySplAttnReasonResponseEvent>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Attributes
	 */
	private String entryDate;
	private String entryTime;
	private String Comments;
	private String createUser;
	private String detentionId;
	private Date createDate;
	
	@Override
	public int compareTo(JuvenileFacilitySplAttnReasonResponseEvent o) {
		if(o == null)
			return 1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
		if(this.createDate==null)
			return -1; // this makes any null objects go to the bottom change this to 1 if you want the top of the list
		JuvenileFacilitySplAttnReasonResponseEvent evt = (JuvenileFacilitySplAttnReasonResponseEvent)o;
		return evt.getCreateDate().compareTo(createDate);
	}
	
	/**
	 * @return the entryDate
	 */
	public String getEntryDate() {
		return entryDate;
	}
	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return Comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		Comments = comments;
	}
	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * @return the entryTime
	 */
	public String getEntryTime() {
		return entryTime;
	}
	/**
	 * @param entryTime the entryTime to set
	 */
	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	/**
	 * @return the detentionId
	 */
	public String getDetentionId() {
		return detentionId;
	}

	/**
	 * @param detentionId the detentionId to set
	 */
	public void setDetentionId(String detentionId) {
		this.detentionId = detentionId;
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


	
}
