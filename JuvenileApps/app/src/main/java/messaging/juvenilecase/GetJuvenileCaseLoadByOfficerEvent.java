package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCaseLoadByOfficerEvent extends RequestEvent {
	private String officerId;
	private String caseStatusCd;
    //<KISHORE>JIMS200060469 : MJCW CF: Add Caseload Search date range(UI)-KK
    private String casefileActivationStDate;
    private String casefileActivationEndDate;
    
    private String casefileExpectedStartDate;
    private String casefileExpectedEndDate;

	/**
	 * @return the caseStatusCd
	 */
	public String getCaseStatusCd() {
		return caseStatusCd;
	}

	/**
	 * @param caseStatusCd the caseStatusCd to set
	 */
	public void setCaseStatusCd(String caseStatusCd) {
		this.caseStatusCd = caseStatusCd;
	}

	/**
	 * @return the officerId
	 */
	public String getOfficerId() {
		return officerId;
	}

	/**
	 * @param officerId the officerId to set
	 */
	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}

	/**
	 * @return the casefileActivationStDate
	 */
	public String getCasefileActivationStDate() {
		return casefileActivationStDate;
	}

	/**
	 * @param casefileActivationStDate the casefileActivationStDate to set
	 */
	public void setCasefileActivationStDate(String casefileActivationStDate) {
		this.casefileActivationStDate = casefileActivationStDate;
	}

	/**
	 * @return the casefileActivationEndDate
	 */
	public String getCasefileActivationEndDate() {
		return casefileActivationEndDate;
	}

	/**
	 * @param casefileActivationEndDate the casefileActivationEndDate to set
	 */
	public void setCasefileActivationEndDate(String casefileActivationEndDate) {
		this.casefileActivationEndDate = casefileActivationEndDate;
	}

	/**
	 * @return the casefileExpectedStartDate
	 */
	public String getCasefileExpectedStartDate() {
		return casefileExpectedStartDate;
	}

	/**
	 * @param casefileExpectedStartDate the casefileExpectedStartDate to set
	 */
	public void setCasefileExpectedStartDate(String casefileExpectedStartDate) {
		this.casefileExpectedStartDate = casefileExpectedStartDate;
	}

	/**
	 * @return the casefileExpectedEndDate
	 */
	public String getCasefileExpectedEndDate() {
		return casefileExpectedEndDate;
	}

	/**
	 * @param casefileExpectedEndDate the casefileExpectedEndDate to set
	 */
	public void setCasefileExpectedEndDate(String casefileExpectedEndDate) {
		this.casefileExpectedEndDate = casefileExpectedEndDate;
	}
	
}
