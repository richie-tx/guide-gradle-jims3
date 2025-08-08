package messaging.administerprogramreferrals;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class ExitProgramReferralEvent extends RequestEvent
{
	private String programReferralId; 
	
	private Date programEndDate;
	private String dischargeReasonCd;
	
	private String exitComments;
	
	/**
	 * @return the programEndDate
	 */
	public Date getProgramEndDate() {
		return programEndDate;
	}
	/**
	 * @param programEndDate the programEndDate to set
	 */
	public void setProgramEndDate(Date programEndDate) {
		this.programEndDate = programEndDate;
	}
	/**
	 * @return the dischargeReasonCd
	 */
	public String getDischargeReasonCd() {
		return dischargeReasonCd;
	}
	/**
	 * @param dischargeReasonCd the dischargeReasonCd to set
	 */
	public void setDischargeReasonCd(String dischargeReasonCd) {
		this.dischargeReasonCd = dischargeReasonCd;
	}
	/**
	 * @return the programReferralId
	 */
	public String getProgramReferralId() {
		return programReferralId;
	}
	/**
	 * @param programReferralId the programReferralId to set
	 */
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}
	/**
	 * @return the exitComments
	 */
	public String getExitComments() {
		return exitComments;
	}
	/**
	 * @param exitComments the exitComments to set
	 */
	public void setExitComments(String exitComments) {
		this.exitComments = exitComments;
	}
}
