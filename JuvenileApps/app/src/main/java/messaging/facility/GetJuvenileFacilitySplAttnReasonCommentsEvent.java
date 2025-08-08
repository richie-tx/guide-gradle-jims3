package messaging.facility;

import mojo.km.messaging.RequestEvent;

/**
 * GetJuvenileFacilitySplAttnReasonCommentsEvent
 * @author sthyagarajan
 *
 */
public class GetJuvenileFacilitySplAttnReasonCommentsEvent extends RequestEvent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		
	private String juvenileNum;
	private String detentionId;

	/**
	 * @return the juvenileNumber
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNumber the juvenileNumber to set
	 */
	public void setJuvenileNumber(String juvenileNum) {
		this.juvenileNum = juvenileNum;
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
