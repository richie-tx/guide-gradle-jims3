/*
 * Created on Dec 6, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.spnsplit.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author kmurthy
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SpnSplitOrderDetailsResponseEvent extends ResponseEvent { //implements Comparable {
	private Date orderFiledDate;
    private String cdi;
    private String caseNumber;
    private String courtId;
    private String orderStatus;
    private String versionType;
    private String versionNumber;
    private String agencyId;
    private String supOrderId;
    private Date supOrderBeginDate;
    private Date supOrderEndDate;
    
    
	/**
	 * @return Returns the supOrderId.
	 */
	public String getSupOrderId() {
		return supOrderId;
	}
	/**
	 * @param supOrderId The supOrderId to set.
	 */
	public void setSupOrderId(String supOrderId) {
		this.supOrderId = supOrderId;
	}
	/**
	 */
	public SpnSplitOrderDetailsResponseEvent() {

	}
    
    
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
	 * @return Returns the caseNumber.
	 */
	public String getCaseNumber() {
		return caseNumber;
	}
	/**
	 * @param caseNumber The caseNumber to set.
	 */
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
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
	 * @return Returns the courtId.
	 */
	public String getCourtId() {
		return courtId;
	}
	/**
	 * @param courtId The courtId to set.
	 */
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
    /**
     * @return Returns the orderFiledDate.
     */
    public Date getOrderFiledDate() {
    	return orderFiledDate;
    }
    /**
     * @param orderFiledDate The orderFiledDate to set.
     */
    public void setOrderFiledDate(Date orderFiledDate) {
    	this.orderFiledDate = orderFiledDate;
    }
	/**
	 * @return Returns the orderStatus.
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus The orderStatus to set.
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @return Returns the versionType.
	 */
	public String getVersionType() {
		return versionType;
	}
	/**
	 * @param versionType The versionType to set.
	 */
	public void setVersionType(String version) {
		this.versionType = version;
	}
    /**
     * @return Returns the versionNumber.
     */
    public String getVersionNumber() {
        return versionNumber;
    }
    /**
     * @param versionNumber The versionNumber to set.
     */
    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }    
	/**
	 * @return Returns the supOrderBeginDate.
	 */
	public Date getSupOrderBeginDate() {
		return supOrderBeginDate;
	}
	/**
	 * @param supOrderBeginDate The supOrderBeginDate to set.
	 */
	public void setSupOrderBeginDate(Date supOrderBeginDate) {
		this.supOrderBeginDate = supOrderBeginDate;
	}
	/**
	 * @return Returns the supOrderEndDate.
	 */
	public Date getSupOrderEndDate() {
		return supOrderEndDate;
	}
	/**
	 * @param supOrderEndDate The supOrderEndDate to set.
	 */
	public void setSupOrderEndDate(Date supOrderEndDate) {
		this.supOrderEndDate = supOrderEndDate;
	}
}
