package messaging.casefile;

import mojo.km.messaging.ResponseEvent;

public class GetCasefileDocumentsEvent extends ResponseEvent {

	private String juvenileId;
	private String casefileId;
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
	 * @return the casefileId
	 */
	public String getCasefileId() {
		return casefileId;
	}
	/**
	 * @param casefileId the casefileId to set
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
}
