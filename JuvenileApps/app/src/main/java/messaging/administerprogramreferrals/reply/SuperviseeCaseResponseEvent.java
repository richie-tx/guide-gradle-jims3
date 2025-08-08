/*
 * Created on Mar 27, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerprogramreferrals.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SuperviseeCaseResponseEvent extends ResponseEvent 
{
	private String cdi;
	private String caseNumber;
	private String courtNumber;
	private String offense;
	private Date caseFiledDate;
	private String orderStatusCd;
	private String versionCd;
	private Date orderFiledDate;
	
	
	
	/**
	 * @return the caseFiledDate
	 */
	public Date getCaseFiledDate() {
		return caseFiledDate;
	}
	/**
	 * @param caseFiledDate the caseFiledDate to set
	 */
	public void setCaseFiledDate(Date caseFiledDate) {
		this.caseFiledDate = caseFiledDate;
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
	 * @return Returns the courtNumber.
	 */
	public String getCourtNumber() {
		return courtNumber;
	}
	/**
	 * @param courtNumber The courtNumber to set.
	 */
	public void setCourtNumber(String courtNumber) {
		this.courtNumber = courtNumber;
	}
	/**
	 * @return Returns the offense.
	 */
	public String getOffense() {
		return offense;
	}
	/**
	 * @param offense The offense to set.
	 */
	public void setOffense(String offense) {
		this.offense = offense;
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
	 * @return the orderStatusCd
	 */
	public String getOrderStatusCd() {
		return orderStatusCd;
	}
	/**
	 * @param orderStatusCd the orderStatusCd to set
	 */
	public void setOrderStatusCd(String orderStatusCd) {
		this.orderStatusCd = orderStatusCd;
	}
	/**
	 * @return the versionCd
	 */
	public String getVersionCd() {
		return versionCd;
	}
	/**
	 * @param versionCd the versionCd to set
	 */
	public void setVersionCd(String versionCd) {
		this.versionCd = versionCd;
	}
}
