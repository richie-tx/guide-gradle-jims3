package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;


/**
 * @author rcarter
 */

public class GetProductionSupportFacilityDetentionEvent extends RequestEvent 
{	
	private String headerId;
	private String juvenileId;
	private String referralNum;
	private String sequenceNum;
	/**
	 * @return the headerId
	 */
	public String getHeaderId() {
		return headerId;
	}
	/**
	 * @param headerId the headerId to set
	 */
	public void setHeaderId(String headerId) {
		this.headerId = headerId;
	}
	/**
	 * @return the juvenileId
	 */
	public String getJuvenileId() {
		return juvenileId;
	}
	/**
	 * @param juvenileId the juvenileId to set
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
	/**
	 * @return the referralNum
	 */
	public String getReferralNum() {
		return referralNum;
	}
	/**
	 * @param referralNum the referralNum to set
	 */
	public void setReferralNum(String referralNum) {
		this.referralNum = referralNum;
	}
	/**
	 * @return the sequenceNum
	 */
	public String getSequenceNum() {
		return sequenceNum;
	}
	/**
	 * @param sequenceNum the sequenceNum to set
	 */
	public void setSequenceNum(String sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

}
