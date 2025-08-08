/*
 * Created on Jun 20, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.administerserviceprovider.reply;
import java.util.Collection ;
import java.util.Comparator;
import java.util.Date;

import pd.supervision.administerserviceprovider.ProgramSourceFund;

import mojo.km.messaging.ResponseEvent;

/**
 * @author ugopinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProgramSourceFundResponseEvent extends ResponseEvent implements Comparable
{
	private String provProgramId;
	
	private String programSourceFundId;
	
	private String programSourceFund;
	
	private String programSourceFundCd;
	
	private Date fundStartDate;
	
	private Date fundEndDate;
	
	private String fundStatus;
	
	private Date fundEntryDate;

	/**
	 * @return the provProgramId
	 */
	public String getProvProgramId() {
		return provProgramId;
	}

	/**
	 * @param provProgramId the provProgramId to set
	 */
	public void setProvProgramId(String provProgramId) {
		this.provProgramId = provProgramId;
	}

	/**
	 * @return the programSourceFund
	 */
	public String getProgramSourceFund() {
		return programSourceFund;
	}

	/**
	 * @param programSourceFund the programSourceFund to set
	 */
	public void setProgramSourceFund(String programSourceFund) {
		this.programSourceFund = programSourceFund;
	}

	/**
	 * @return the fundStartDate
	 */
	public Date getFundStartDate() {
		return fundStartDate;
	}

	/**
	 * @param fundStartDate the fundStartDate to set
	 */
	public void setFundStartDate(Date fundStartDate) {
		this.fundStartDate = fundStartDate;
	}

	/**
	 * @return the fundEndDate
	 */
	public Date getFundEndDate() {
		return fundEndDate;
	}

	/**
	 * @param fundEndDate the fundEndDate to set
	 */
	public void setFundEndDate(Date fundEndDate) {
		this.fundEndDate = fundEndDate;
	}

	/**
	 * @return the fundStatus
	 */
	public String getFundStatus() {
		return fundStatus;
	}

	/**
	 * @param fundStatus the fundStatus to set
	 */
	public void setFundStatus(String fundStatus) {
		this.fundStatus = fundStatus;
	}

	/**
	 * @return the fundEntryDate
	 */
	public Date getFundEntryDate() {
		return fundEntryDate;
	}

	/**
	 * @param fundEntryDate the fundEntryDate to set
	 */
	public void setFundEntryDate(Date fundEntryDate) {
		this.fundEntryDate = fundEntryDate;
	}

	public String getProgramSourceFundCd() {
		return programSourceFundCd;
	}

	public void setProgramSourceFundCd(String programSourceFundCd) {
		this.programSourceFundCd = programSourceFundCd;
	}

	public String getProgramSourceFundId() {
		return programSourceFundId;
	}

	public void setProgramSourceFundId(String programSourceFundId) {
		this.programSourceFundId = programSourceFundId;
	}

	public int compareTo(Object o)
	{
		if(o==null)
			return 1;
		if(this.fundEntryDate==null)
			return -1;
		ProgramSourceFundResponseEvent resp = (ProgramSourceFundResponseEvent)o;
		return resp.getFundEntryDate().compareTo(fundEntryDate);
	}
	
}
