package messaging.facility;

import mojo.km.messaging.RequestEvent;

/**
 * SaveJuvenileFacilitySplAttnReasonCommentsEvents
 * @author sthyagarajan
 *
 */
public class SaveJuvenileFacilitySplAttnReasonCommentsEvent extends RequestEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String juvenileNum;
	private String comments;
	private String detentionId;
	
	
	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
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
}
