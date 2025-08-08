/*
 * Created on May 1, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RefreshNCResponseComponentsEvent extends RequestEvent {
    
	private String ncResponseId;
	private String requestType;
	private String caseId;
	private String cdi;
	private String defendantId;
	private String activationDate;
	
	/**
	 * @return the activationDate
	 */
	public String getActivationDate() {
		return activationDate;
	}
	/**
	 * @param activationDate the activationDate to set
	 */
	public void setActivationDate(String activationDate) {
		this.activationDate = activationDate;
	}
	/**
	 * @return Returns the ncResponseId.
	 */
	public String getNcResponseId() {
		return ncResponseId;
	}
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
	/**
	 * @return Returns the requestType.
	 */
	public String getRequestType() {
		return requestType;
	}
	/**
	 * @param requestType The requestType to set.
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	/**
	 * @return Returns the caseId.
	 */
	public String getCaseId() {
		return caseId;
	}
	/**
	 * @param caseId The caseId to set.
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	/**
	 * @return Returns the cdi.
	 */
	public String getCdi() {
		return cdi;
	}
	/**
	 * @param cdi The cdi to set.
	 */
	public void setCdi(String cdi) {
		this.cdi = cdi;
	}
	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
}
