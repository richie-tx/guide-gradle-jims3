package messaging.administerprogramreferrals;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SubmitProgramReferralEvent extends RequestEvent
{
	private String programReferralId; 
	private Date referralDate;
	private Date programBeginDate;
	private boolean contractProgram;
	private String tracerNumber;
	private String placementReasonCd;
	private int confinementYears;
	private int confinementMonths;
	private int confinementDays;
	private boolean programUnitReferral;
	private String submitComments;
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
	 * @return the programBeginDate
	 */
	public Date getProgramBeginDate() {
		return programBeginDate;
	}
	/**
	 * @param programBeginDate the programBeginDate to set
	 */
	public void setProgramBeginDate(Date programBeginDate) {
		this.programBeginDate = programBeginDate;
	}
	/**
	 * @return the contractProgram
	 */
	public boolean isContractProgram() {
		return contractProgram;
	}
	/**
	 * @param contractProgram the contractProgram to set
	 */
	public void setContractProgram(boolean contractProgram) {
		this.contractProgram = contractProgram;
	}
	/**
	 * @return the tracerNumber
	 */
	public String getTracerNumber() {
		return tracerNumber;
	}
	/**
	 * @param tracerNumber the tracerNumber to set
	 */
	public void setTracerNumber(String tracerNumber) {
		this.tracerNumber = tracerNumber;
	}
	/**
	 * @return the placementReasonCd
	 */
	public String getPlacementReasonCd() {
		return placementReasonCd;
	}
	/**
	 * @param placementReasonCd the placementReasonCd to set
	 */
	public void setPlacementReasonCd(String placementReasonCd) {
		this.placementReasonCd = placementReasonCd;
	}
	/**
	 * @return the confinementYears
	 */
	public int getConfinementYears() {
		return confinementYears;
	}
	/**
	 * @param confinementYears the confinementYears to set
	 */
	public void setConfinementYears(int confinementYears) {
		this.confinementYears = confinementYears;
	}
	/**
	 * @return the confinementMonths
	 */
	public int getConfinementMonths() {
		return confinementMonths;
	}
	/**
	 * @param confinementMonths the confinementMonths to set
	 */
	public void setConfinementMonths(int confinementMonths) {
		this.confinementMonths = confinementMonths;
	}
	/**
	 * @return the confinementDays
	 */
	public int getConfinementDays() {
		return confinementDays;
	}
	/**
	 * @param confinementDays the confinementDays to set
	 */
	public void setConfinementDays(int confinementDays) {
		this.confinementDays = confinementDays;
	}
	/**
	 * @return the submitComments
	 */
	public String getSubmitComments() {
		return submitComments;
	}
	/**
	 * @param submitComments the submitComments to set
	 */
	public void setSubmitComments(String submitComments) {
		this.submitComments = submitComments;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isProgramUnitReferral() {
		return programUnitReferral;
	}
	
	/**
	 * 
	 * @param programUnitReferral
	 */
	public void setProgramUnitReferral(boolean programUnitReferral) {
		this.programUnitReferral = programUnitReferral;
	}
	/**
	 * 
	 * @return
	 */
	public Date getReferralDate() {
		return referralDate;
	}
	/**
	 * 
	 * @param referralDate
	 */
	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}
	
}
