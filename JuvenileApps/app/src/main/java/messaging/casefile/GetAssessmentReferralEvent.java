package messaging.casefile;

import mojo.km.messaging.RequestEvent;

public class GetAssessmentReferralEvent extends RequestEvent {
	
	private String juvenileId;
	
	private String assessmentId;
	
	public GetAssessmentReferralEvent(){
		
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
	 * @param assessmentId the assessmentId to set
	 */
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}
	/**
	 * @return the assessmentId
	 */
	public String getAssessmentId() {
		return assessmentId;
	}
}
