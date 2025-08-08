package messaging.calendar;

import mojo.km.messaging.RequestEvent;

public class GetSchoolAdjudicationNotificationEvent extends RequestEvent {
	
	private String juvenileId;
	private String docTypeCd;
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
	 * @return the docTypeCd
	 */
	public String getDocTypeCd() {
		return docTypeCd;
	}
	/**
	 * @param docTypeCd the docTypeCd to set
	 */
	public void setDocTypeCd(String docTypeCd) {
		this.docTypeCd = docTypeCd;
	}
}
