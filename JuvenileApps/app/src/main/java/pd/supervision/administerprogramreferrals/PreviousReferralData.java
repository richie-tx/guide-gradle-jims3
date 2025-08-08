package pd.supervision.administerprogramreferrals;

import java.util.Date;

import messaging.administerprogramreferrals.UpdateProgramReferralEvent;
import mojo.km.utilities.DateUtil;

import pd.supervision.administerprogramreferrals.PreviousReferralData;

public class PreviousReferralData {
	private StringBuffer changeMessage;
	private int confinementDays;
	private int confinementMonths;
	private int confinementYears;
	private boolean contractProgram;
	private String dischargeReasonCd;
	private String placementReasonCd;
	private Date programBeginDate;
	private Date programEndDate;
	private Date referralDate;
	public String getChangeMessage(){
		return changeMessage.toString();
	}
	public int getConfinementDays() {
		return confinementDays;
	}
	public int getConfinementMonths() {
		return confinementMonths;
	}
	public int getConfinementYears() {
		return confinementYears;
	}
	public String getDischargeReasonCd() {
		return dischargeReasonCd;
	}
	public String getPlacementReasonCd() {
		return placementReasonCd;
	}
	public Date getProgramBeginDate() {
		return programBeginDate;
	}
	public Date getProgramEndDate() {
		return programEndDate;
	}
	public Date getReferralDate() {
		return referralDate;
	}
	public boolean isContractProgram() {
		return contractProgram;
	}
	public void setChangedMessage(StringBuffer changeMessage){
		this.changeMessage = changeMessage;
	}
	public void setConfinementDays(int confinementDays) {
		this.confinementDays = confinementDays;
	}
	public void setConfinementMonths(int confinementMonths) {
		this.confinementMonths = confinementMonths;
	}
	public void setConfinementYears(int confinementYears) {
		this.confinementYears = confinementYears;
	}
	public void setContractProgram(boolean contractProgram) {
		this.contractProgram = contractProgram;
	}
	public void setDischargeReasonCd(String dischargeReasonCd) {
		this.dischargeReasonCd = dischargeReasonCd;
	}
	public void setPlacementReasonCd(String placementReasonCd) {
		this.placementReasonCd = placementReasonCd;
	}
	public void setProgramBeginDate(Date programBeginDate) {
		this.programBeginDate = programBeginDate;
	}
	public void setProgramEndDate(Date programEndDate) {
		this.programEndDate = programEndDate;
	}
	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}
	private static final String PROG_BEGIN_DATE_MSG = "Program Begin Date has been corrected from ";
	private static final String PROG_END_DATE_MSG = "Program End Date has been corrected from ";
	private static final String PLACEMENT_REASON_MSG = "Reason for Placement has been corrected from ";
	private static final String DISCHARGE_REASON_MSG = "Reason for Discharge has been corrected from ";
	private static final String CONTRACT_PROG_MSG = "Contract Program has been corrected from ";
	private static final String CONFINMENT_DAYS = "Confinement length (days) has been corrected from ";
	private static final String CONFINMENT_MONTHS = "Confinement length (months) has been corrected from ";
	private static final String CONFINMENT_YEARS = "Confinement length (years) has been corrected from ";
	private static final String TO = " to ";
	private static final String SEMICOLON = "; ";

	
	public PreviousReferralData savePreviousReferralValues(CSProgramReferral programReferral){
		this.setConfinementDays(programReferral.getConfinementDays());
		this.setConfinementMonths(programReferral.getConfinementMonths());
		this.setConfinementYears(programReferral.getConfinementYears());
		this.setContractProgram(programReferral.isContractProgram());
		this.setDischargeReasonCd(programReferral.getDischargeReasonCd());
		this.setPlacementReasonCd(programReferral.getPlacementReasonCd());
		this.setProgramBeginDate(programReferral.getProgramBeginDate());
		this.setProgramEndDate(programReferral.getProgramEndDate());
		this.setReferralDate(programReferral.getReferralDate());
		return this;
	}
	
	public boolean hasSubmitDataChanged(
			UpdateProgramReferralEvent updateProgRefEvent) {
		boolean hasDataChanged = false;
		StringBuffer chgMsg = new StringBuffer();
		if (!this.getProgramBeginDate().equals(updateProgRefEvent.getProgramBeginDate())){
			chgMsg.append(PROG_BEGIN_DATE_MSG);
			chgMsg.append(DateUtil.dateToString(this.getProgramBeginDate(), DateUtil.DATE_FMT_1));
			chgMsg.append(TO);
			chgMsg.append(DateUtil.dateToString(updateProgRefEvent.getProgramBeginDate(), DateUtil.DATE_FMT_1));
			hasDataChanged = true;
		}
		if (!this.getPlacementReasonCd().equals(updateProgRefEvent.getPlacementReasonCd())){
			if (chgMsg.length() > 0){
				chgMsg.append(SEMICOLON);
			}
			chgMsg.append(PLACEMENT_REASON_MSG);
			chgMsg.append(this.getPlacementReasonCd());
			chgMsg.append(TO);
			chgMsg.append(updateProgRefEvent.getPlacementReasonCd());
			hasDataChanged = true;
		}
		if (this.getConfinementDays() != updateProgRefEvent.getConfinementDays()){
			if (chgMsg.length() > 0){
				chgMsg.append(SEMICOLON);
			}
			chgMsg.append(CONFINMENT_DAYS);
			chgMsg.append(this.getConfinementDays());
			chgMsg.append(TO);
			chgMsg.append(updateProgRefEvent.getConfinementDays());
			hasDataChanged = true;
		}
		if (this.getConfinementMonths() != updateProgRefEvent.getConfinementMonths()){
			if (chgMsg.length() > 0){
				chgMsg.append(SEMICOLON);
			}
			chgMsg.append(CONFINMENT_MONTHS);
			chgMsg.append(this.getConfinementMonths());
			chgMsg.append(TO);
			chgMsg.append(updateProgRefEvent.getConfinementMonths());
			hasDataChanged = true;
		}
		if (this.getConfinementYears() != updateProgRefEvent.getConfinementYears()){
			if (chgMsg.length() > 0){
				chgMsg.append(SEMICOLON);
			}
			chgMsg.append(CONFINMENT_YEARS);
			chgMsg.append(this.getConfinementYears());
			chgMsg.append(TO);
			chgMsg.append(updateProgRefEvent.getConfinementYears());
			hasDataChanged = true;
		}
		if (this.isContractProgram() != updateProgRefEvent.isContractProgram()){
			if (chgMsg.length() > 0){
				chgMsg.append(SEMICOLON);
			}
			chgMsg.append(CONTRACT_PROG_MSG);
			chgMsg.append(this.isContractProgram());
			chgMsg.append(TO);
			chgMsg.append(updateProgRefEvent.isContractProgram());
			hasDataChanged = true;
		}
		if (hasDataChanged){
			this.setChangedMessage(chgMsg);
		}
		return hasDataChanged;
	}
	public boolean hasExitDataChanged(
			UpdateProgramReferralEvent updateProgRefEvent) {
		boolean hasDataChanged = false;
		StringBuffer chgMsg = new StringBuffer();
		if (!this.getProgramEndDate().equals(updateProgRefEvent.getProgramEndDate())){
			chgMsg.append(PROG_END_DATE_MSG);
			chgMsg.append(DateUtil.dateToString(this.getProgramEndDate(), DateUtil.DATE_FMT_1));
			chgMsg.append(TO);
			chgMsg.append(DateUtil.dateToString(updateProgRefEvent.getProgramEndDate(), DateUtil.DATE_FMT_1));
			hasDataChanged = true;
		}
		if (!this.getDischargeReasonCd().equals(updateProgRefEvent.getDischargeReasonCd())){
			if (chgMsg.length() > 0){
				chgMsg.append(SEMICOLON);
			}
			chgMsg.append(DISCHARGE_REASON_MSG);
			chgMsg.append(this.getDischargeReasonCd());
			chgMsg.append(TO);
			chgMsg.append(updateProgRefEvent.getDischargeReasonCd());
			hasDataChanged = true;
		}
		if (hasDataChanged){
			this.setChangedMessage(chgMsg);
		}
		return hasDataChanged;
	}
}
