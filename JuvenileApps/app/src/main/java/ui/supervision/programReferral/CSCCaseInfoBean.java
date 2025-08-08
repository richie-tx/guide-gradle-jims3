/*
 * Created on Apr 6, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.programReferral;

import java.util.Date;

import mojo.km.utilities.DateUtil;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSCCaseInfoBean {
	private String criminalCaseId;
	private String caseNum;
	private String cdi;
	private String crt;
	private String offenseId;
	private String offenseDesc;
	private Date casefileDate;
	private String casefileDateAsStr;
	private String orderStatusId;
	private String orderStatusDesc;
	private String versionId;
	private String versionDesc;
	private Date orderFileDate;
	private String orderFileDateAsStr;
	
	
	
	/**
	 * @return the criminalCaseId
	 */
	public String getCriminalCaseId() {
		return criminalCaseId;
	}
	/**
	 * @param criminalCaseId the criminalCaseId to set
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
	/**
	 * @return Returns the casefileDate.
	 */
	public Date getCasefileDate() {
		return casefileDate;
	}
	/**
	 * @param casefileDate The casefileDate to set.
	 */
	public void setCasefileDate(Date casefileDate)
	{
		this.casefileDate = casefileDate;
		this.casefileDateAsStr = "";
		
		if(casefileDate != null)
		{
			try
			{
				this.casefileDateAsStr = DateUtil.dateToString(casefileDate, DateUtil.DATE_FMT_1);
			}
			catch(Exception ex)
			{
				this.casefileDateAsStr = "";
			}
		}
	}
	/**
	 * @return Returns the casefileDateAsStr.
	 */
	public String getCasefileDateAsStr() {
		return casefileDateAsStr;
	}
	/**
	 * @param casefileDateAsStr The casefileDateAsStr to set.
	 */
	public void setCasefileDateAsStr(String casefileDateAsStr) {
		this.casefileDateAsStr = casefileDateAsStr;
	}
	/**
	 * @return Returns the caseNum.
	 */
	public String getCaseNum() {
		return caseNum;
	}
	/**
	 * @param caseNum The caseNum to set.
	 */
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
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
	 * @return Returns the crt.
	 */
	public String getCrt() {
		return crt;
	}
	/**
	 * @param crt The crt to set.
	 */
	public void setCrt(String crt) {
		this.crt = crt;
	}
	/**
	 * @return Returns the offenseId.
	 */
	public String getOffenseId() {
		return offenseId;
	}
	/**
	 * @param offenseId The offenseId to set.
	 */
	public void setOffenseId(String offenseId) {
		this.offenseId = offenseId;
	}
	/**
	 * @return Returns the orderFileDate.
	 */
	public Date getOrderFileDate() {
		return orderFileDate;
	}
	/**
	 * @param orderFileDate The orderFileDate to set.
	 */
	public void setOrderFileDate(Date orderFileDate) 
	{
		this.orderFileDate = orderFileDate;
		this.orderFileDateAsStr = "";
		
		if(orderFileDate != null)
		{
			try
			{
				this.orderFileDateAsStr = DateUtil.dateToString(orderFileDate, DateUtil.DATE_FMT_1);
			}
			catch(Exception ex)
			{
				this.orderFileDateAsStr = "";
			}
		}
	}
	
	/**
	 * @return Returns the orderFileDateAsStr.
	 */
	public String getOrderFileDateAsStr() {
		return orderFileDateAsStr;
	}
	/**
	 * @param orderFileDateAsStr The orderFileDateAsStr to set.
	 */
	public void setOrderFileDateAsStr(String orderFileDateAsStr)
	{
		this.orderFileDateAsStr = orderFileDateAsStr;
	}
	/**
	 * @return Returns the orderStatusDesc.
	 */
	public String getOrderStatusDesc() {
		return orderStatusDesc;
	}
	/**
	 * @param orderStatusDesc The orderStatusDesc to set.
	 */
	public void setOrderStatusDesc(String orderStatusDesc) {
		this.orderStatusDesc = orderStatusDesc;
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
	/**
	 * @return Returns the versionId.
	 */
	public String getVersionId() {
		return versionId;
	}
	/**
	 * @param versionId The versionId to set.
	 */
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	/**
	 * @return Returns the offenseDesc.
	 */
	public String getOffenseDesc() {
		return offenseDesc;
	}
	/**
	 * @param offenseDesc The offenseDesc to set.
	 */
	public void setOffenseDesc(String offenseDesc) {
		this.offenseDesc = offenseDesc;
	}
	/**
	 * @return Returns the versionDesc.
	 */
	public String getVersionDesc() {
		return versionDesc;
	}
	/**
	 * @param versionDesc The versionDesc to set.
	 */
	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
	}
}// END CLASS
