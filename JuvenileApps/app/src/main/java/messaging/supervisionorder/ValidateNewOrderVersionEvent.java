/*
 * Created on Dec 21, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;


/**
 * @author asrvastava
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ValidateNewOrderVersionEvent extends RequestEvent {
    private String agencyId;
    private String criminalCaseId;
    private int orderChainNum;

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
     * @return Returns the criminalCaseId.
     */
    public String getCriminalCaseId() {
        return criminalCaseId;
    }
    /**
     * @param criminalCaseId The criminalCaseId to set.
     */
    public void setCriminalCaseId(String criminalCaseId) {
        this.criminalCaseId = criminalCaseId;
    }
	/**
	 * @return Returns the orderChainNum.
	 */
	public int getOrderChainNum() {
		return orderChainNum;
	}
	/**
	 * @param orderChainNum The orderChainNum to set.
	 */
	public void setOrderChainNum(int orderChainNum) {
		this.orderChainNum = orderChainNum;
	}
}
