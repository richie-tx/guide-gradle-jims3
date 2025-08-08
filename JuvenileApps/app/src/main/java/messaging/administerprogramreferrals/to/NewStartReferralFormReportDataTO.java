/*
 * Created on Jul 2, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerprogramreferrals.to;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author cc_bjangay
 *
 */
public class NewStartReferralFormReportDataTO extends ReferralFormReportDataTO
{
	private String superviseeTDCNumber = "";
	private String superviseeContMethDesc = "";
	private String newStartCrtOrderIndicator = "";
	private String supervisedByIndicator = "";
	private Date expiryDate = null;
	private Date offenseDate = null;
	private String revcRiskIndicator = "";
	private String revcRiskComments = "";
	private String mentalHealthComments = "";
	private String medicationComments = "";
	private String subsAbuseHistIndicator = "";
	private String subsAbuseCurrUsrIndicator ="";
	private String referralNeeds = "";
	private List priorOffenseRecordList = new ArrayList();
	
	//Current Needs Responses
	private String psychIndicator = "";
	private String medicalIndicator = "";
	private String drugAlcoholIndicator = "";
	private String housingIndicator = "";
	private String educationIndicator = "";
	private String empTrainingIndicator = "";
	private String groupProgramIndicator = "";
	private String indvCounselingIndicator = "";
	private String benefitsAppIndicator = "";	
	private String otherComments ="";
	
	/**
	 * @return the superviseeTDCNumber
	 */
	public String getSuperviseeTDCNumber() {
		return superviseeTDCNumber;
	}
	/**
	 * @param superviseeTDCNumber the superviseeTDCNumber to set
	 */
	public void setSuperviseeTDCNumber(String superviseeTDCNumber) {
		this.superviseeTDCNumber = superviseeTDCNumber;
	}
	/**
	 * @return the superviseeContMethDesc
	 */
	public String getSuperviseeContMethDesc() {
		return superviseeContMethDesc;
	}
	/**
	 * @param superviseeContMethDesc the superviseeContMethDesc to set
	 */
	public void setSuperviseeContMethDesc(String superviseeContMethDesc) {
		this.superviseeContMethDesc = superviseeContMethDesc;
	}
	/**
	 * @return the newStartCrtOrderIndicator
	 */
	public String getNewStartCrtOrderIndicator() {
		return newStartCrtOrderIndicator;
	}
	/**
	 * @param newStartCrtOrderIndicator the newStartCrtOrderIndicator to set
	 */
	public void setNewStartCrtOrderIndicator(String newStartCrtOrderIndicator) {
		this.newStartCrtOrderIndicator = newStartCrtOrderIndicator;
	}
	/**
	 * @return the supervisedByIndicator
	 */
	public String getSupervisedByIndicator() {
		return supervisedByIndicator;
	}
	/**
	 * @param supervisedByIndicator the supervisedByIndicator to set
	 */
	public void setSupervisedByIndicator(String supervisedByIndicator) {
		this.supervisedByIndicator = supervisedByIndicator;
	}
	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * @return the revcRiskIndicator
	 */
	public String getRevcRiskIndicator() {
		return revcRiskIndicator;
	}
	/**
	 * @param revcRiskIndicator the revcRiskIndicator to set
	 */
	public void setRevcRiskIndicator(String revcRiskIndicator) {
		this.revcRiskIndicator = revcRiskIndicator;
	}
	/**
	 * @return the revcRiskComments
	 */
	public String getRevcRiskComments() {
		return revcRiskComments;
	}
	/**
	 * @param revcRiskComments the revcRiskComments to set
	 */
	public void setRevcRiskComments(String revcRiskComments) {
		this.revcRiskComments = revcRiskComments;
	}
	/**
	 * @return the mentalHealthComments
	 */
	public String getMentalHealthComments() {
		return mentalHealthComments;
	}
	/**
	 * @param mentalHealthComments the mentalHealthComments to set
	 */
	public void setMentalHealthComments(String mentalHealthComments) {
		this.mentalHealthComments = mentalHealthComments;
	}
	/**
	 * @return the medicationComments
	 */
	public String getMedicationComments() {
		return medicationComments;
	}
	/**
	 * @param medicationComments the medicationComments to set
	 */
	public void setMedicationComments(String medicationComments) {
		this.medicationComments = medicationComments;
	}
	/**
	 * @return the subsAbuseHistIndicator
	 */
	public String getSubsAbuseHistIndicator() {
		return subsAbuseHistIndicator;
	}
	/**
	 * @param subsAbuseHistIndicator the subsAbuseHistIndicator to set
	 */
	public void setSubsAbuseHistIndicator(String subsAbuseHistIndicator) {
		this.subsAbuseHistIndicator = subsAbuseHistIndicator;
	}
	/**
	 * @return the subsAbuseCurrUsrIndicator
	 */
	public String getSubsAbuseCurrUsrIndicator() {
		return subsAbuseCurrUsrIndicator;
	}
	/**
	 * @param subsAbuseCurrUsrIndicator the subsAbuseCurrUsrIndicator to set
	 */
	public void setSubsAbuseCurrUsrIndicator(String subsAbuseCurrUsrIndicator) {
		this.subsAbuseCurrUsrIndicator = subsAbuseCurrUsrIndicator;
	}
	/**
	 * @return the referralNeeds
	 */
	public String getReferralNeeds() {
		return referralNeeds;
	}
	/**
	 * @param referralNeeds the referralNeeds to set
	 */
	public void setReferralNeeds(String referralNeeds) {
		this.referralNeeds = referralNeeds;
	}
	/**
	 * @return the priorOffenseRecordList
	 */
	public List getPriorOffenseRecordList() {
		return priorOffenseRecordList;
	}
	/**
	 * @param priorOffenseRecordList the priorOffenseRecordList to set
	 */
	public void setPriorOffenseRecordList(List priorOffenseRecordList) {
		this.priorOffenseRecordList = priorOffenseRecordList;
	}
	/**
	 * @return the offenseDate
	 */
	public Date getOffenseDate() {
		return offenseDate;
	}
	/**
	 * @param offenseDate the offenseDate to set
	 */
	public void setOffenseDate(Date offenseDate) {
		this.offenseDate = offenseDate;
	}
	/**
	 * @return the psychIndicator
	 */
	public String getPsychIndicator() {
		return psychIndicator;
	}
	/**
	 * @param psychIndicator the psychIndicator to set
	 */
	public void setPsychIndicator(String psychIndicator) {
		this.psychIndicator = psychIndicator;
	}
	/**
	 * @return the medicalIndicator
	 */
	public String getMedicalIndicator() {
		return medicalIndicator;
	}
	/**
	 * @param medicalIndicator the medicalIndicator to set
	 */
	public void setMedicalIndicator(String medicalIndicator) {
		this.medicalIndicator = medicalIndicator;
	}
	/**
	 * @return the drugAlcoholIndicator
	 */
	public String getDrugAlcoholIndicator() {
		return drugAlcoholIndicator;
	}
	/**
	 * @param drugAlcoholIndicator the drugAlcoholIndicator to set
	 */
	public void setDrugAlcoholIndicator(String drugAlcoholIndicator) {
		this.drugAlcoholIndicator = drugAlcoholIndicator;
	}
	/**
	 * @return the housingIndicator
	 */
	public String getHousingIndicator() {
		return housingIndicator;
	}
	/**
	 * @param housingIndicator the housingIndicator to set
	 */
	public void setHousingIndicator(String housingIndicator) {
		this.housingIndicator = housingIndicator;
	}
	/**
	 * @return the educationIndicator
	 */
	public String getEducationIndicator() {
		return educationIndicator;
	}
	/**
	 * @param educationIndicator the educationIndicator to set
	 */
	public void setEducationIndicator(String educationIndicator) {
		this.educationIndicator = educationIndicator;
	}
	/**
	 * @return the empTrainingIndicator
	 */
	public String getEmpTrainingIndicator() {
		return empTrainingIndicator;
	}
	/**
	 * @param empTrainingIndicator the empTrainingIndicator to set
	 */
	public void setEmpTrainingIndicator(String empTrainingIndicator) {
		this.empTrainingIndicator = empTrainingIndicator;
	}
	/**
	 * @return the groupProgramIndicator
	 */
	public String getGroupProgramIndicator() {
		return groupProgramIndicator;
	}
	/**
	 * @param groupProgramIndicator the groupProgramIndicator to set
	 */
	public void setGroupProgramIndicator(String groupProgramIndicator) {
		this.groupProgramIndicator = groupProgramIndicator;
	}
	/**
	 * @return the indvCounselingIndicator
	 */
	public String getIndvCounselingIndicator() {
		return indvCounselingIndicator;
	}
	/**
	 * @param indvCounselingIndicator the indvCounselingIndicator to set
	 */
	public void setIndvCounselingIndicator(String indvCounselingIndicator) {
		this.indvCounselingIndicator = indvCounselingIndicator;
	}
	/**
	 * @return the benefitsAppIndicator
	 */
	public String getBenefitsAppIndicator() {
		return benefitsAppIndicator;
	}
	/**
	 * @param benefitsAppIndicator the benefitsAppIndicator to set
	 */
	public void setBenefitsAppIndicator(String benefitsAppIndicator) {
		this.benefitsAppIndicator = benefitsAppIndicator;
	}	
	/**
	 * @return the otherComments
	 */
	public String getOtherComments() {
		return otherComments;
	}
	/**
	 * @param otherComments the otherComments to set
	 */
	public void setOtherComments(String otherComments) {
		this.otherComments = otherComments;
	}
	
	
	
	
}
