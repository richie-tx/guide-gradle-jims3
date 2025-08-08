/*
 * Created on Feb 18, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetLatestOrderChainNumForCaseEvent extends RequestEvent {
	private String agencyId;
	private String spn;
	private String orderStatusId;
	private String caseId;
	
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
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
	 * @return Returns the spn.
	 */
	public String getSpn() {
		return spn;
	}
	/**
	 * @param spn The spn to set.
	 */
	public void setSpn(String spn) {
		this.spn = spn;
	}
	/**
	 * @return Returns the orderStatusId.
	 */
	public String getOrderStatusId() {
		return orderStatusId;
	}
	/**
	 * @param orderStatusId The orderStatusId to set.
	 */
	public void setOrderStatusId(String orderStatusId) {
		this.orderStatusId = orderStatusId;
	}
}
