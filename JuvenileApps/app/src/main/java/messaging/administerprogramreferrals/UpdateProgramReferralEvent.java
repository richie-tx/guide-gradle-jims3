package messaging.administerprogramreferrals;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_cwalters
 *
 */

public class UpdateProgramReferralEvent extends RequestEvent 
{
	private String programReferralId; 
	
	private boolean isUpdateSubmitRef;
	
	private Date programBeginDate;
	private boolean contractProgram;
	private String tracerNumber;
	private String placementReasonCd;
	private int confinementYears;
	private int confinementMonths;
	private int confinementDays;
	
	private Date programEndDate;
	private String dischargeReasonCd;
	
	
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
	 * @return the isUpdateSubmitRef
	 */
	public boolean isUpdateSubmitRef() {
		return isUpdateSubmitRef;
	}
	/**
	 * @param isUpdateSubmitRef the isUpdateSubmitRef to set
	 */
	public void setUpdateSubmitRef(boolean isUpdateSubmitRef) {
		this.isUpdateSubmitRef = isUpdateSubmitRef;
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
	
}
