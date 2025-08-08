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
public class CSCDTaipReferralFormDataTO extends ReferralFormReportDataTO
{
	private String medicalInsurance = "";
	private String publicAssistance = "";
	private String monthlyIncome = "";
	private String occupation = "";
	private String numOfDependants = "";
	
	private Date offenseDate = null;
	private Date previousTreatmentDate1 = null;
	private String previousTreatmentPgm1 = "";
	private Date previousTreatmentDate2 = null;
	private String previousTreatmentPgm2 = "";
	

	private String assessScreenSource1 = "";
	private String assessScreenInstrument1 = "";
	private String assessScreenSource2 = "";
	private String assessScreenInstrument2 = "";	

	
	private String mentalHealthPgmDt1 = "";
	private String mentalHealthPgmDiag1 = "";
	private String mentalHealthPgmDt2 = "";
	private String mentalHealthPgmDiag2 = "";
	
	private String medicationComments = "";
	private String medicalProblemsComments = "";
	
	private String scsScore = "";
	private String riskScore = "";
	private String needsScore = "";
	
	private List priorOffenseRecordList = new ArrayList();
	private List substanceAbuseDatesList = new ArrayList();
	private List substanceAbusedList = new ArrayList();
	private List prevTreatmentDatesList = new ArrayList();
	private List prevTreatmentProgramsList = new ArrayList();
	
	/**
	 * @return the medicalInsurance
	 */
	public String getMedicalInsurance() {
		return medicalInsurance;
	}

	/**
	 * @param medicalInsurance the medicalInsurance to set
	 */
	public void setMedicalInsurance(String medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}

	/**
	 * @return the publicAssistance
	 */
	public String getPublicAssistance() {
		return publicAssistance;
	}

	/**
	 * @param publicAssistance the publicAssistance to set
	 */
	public void setPublicAssistance(String publicAssistance) {
		this.publicAssistance = publicAssistance;
	}

	/**
	 * @return the monthlyIncome
	 */
	public String getMonthlyIncome() {
		return monthlyIncome;
	}

	/**
	 * @param monthlyIncome the monthlyIncome to set
	 */
	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * @return the numOfDependants
	 */
	public String getNumOfDependants() {
		return numOfDependants;
	}

	/**
	 * @param numOfDependants the numOfDependants to set
	 */
	public void setNumOfDependants(String numOfDependants) {
		this.numOfDependants = numOfDependants;
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
	 * @return the previousTreatmentDate1
	 */
	public Date getPreviousTreatmentDate1() {
		return previousTreatmentDate1;
	}

	/**
	 * @param previousTreatmentDate1 the previousTreatmentDate1 to set
	 */
	public void setPreviousTreatmentDate1(Date previousTreatmentDate1) {
		this.previousTreatmentDate1 = previousTreatmentDate1;
	}

	/**
	 * @return the previousTreatmentPgm1
	 */
	public String getPreviousTreatmentPgm1() {
		return previousTreatmentPgm1;
	}

	/**
	 * @param previousTreatmentPgm1 the previousTreatmentPgm1 to set
	 */
	public void setPreviousTreatmentPgm1(String previousTreatmentPgm1) {
		this.previousTreatmentPgm1 = previousTreatmentPgm1;
	}

	/**
	 * @return the previousTreatmentDate2
	 */
	public Date getPreviousTreatmentDate2() {
		return previousTreatmentDate2;
	}

	/**
	 * @param previousTreatmentDate2 the previousTreatmentDate2 to set
	 */
	public void setPreviousTreatmentDate2(Date previousTreatmentDate2) {
		this.previousTreatmentDate2 = previousTreatmentDate2;
	}

	/**
	 * @return the previousTreatmentPgm2
	 */
	public String getPreviousTreatmentPgm2() {
		return previousTreatmentPgm2;
	}

	/**
	 * @param previousTreatmentPgm2 the previousTreatmentPgm2 to set
	 */
	public void setPreviousTreatmentPgm2(String previousTreatmentPgm2) {
		this.previousTreatmentPgm2 = previousTreatmentPgm2;
	}	

	/**
	 * @return the assessScreenSource1
	 */
	public String getAssessScreenSource1() {
		return assessScreenSource1;
	}

	/**
	 * @param assessScreenSource1 the assessScreenSource1 to set
	 */
	public void setAssessScreenSource1(String assessScreenSource1) {
		this.assessScreenSource1 = assessScreenSource1;
	}

	/**
	 * @return the assessScreenInstrument1
	 */
	public String getAssessScreenInstrument1() {
		return assessScreenInstrument1;
	}

	/**
	 * @param assessScreenInstrument1 the assessScreenInstrument1 to set
	 */
	public void setAssessScreenInstrument1(String assessScreenInstrument1) {
		this.assessScreenInstrument1 = assessScreenInstrument1;
	}

	/**
	 * @return the assessScreenSource2
	 */
	public String getAssessScreenSource2() {
		return assessScreenSource2;
	}

	/**
	 * @param assessScreenSource2 the assessScreenSource2 to set
	 */
	public void setAssessScreenSource2(String assessScreenSource2) {
		this.assessScreenSource2 = assessScreenSource2;
	}

	/**
	 * @return the assessScreenInstrument2
	 */
	public String getAssessScreenInstrument2() {
		return assessScreenInstrument2;
	}

	/**
	 * @param assessScreenInstrument2 the assessScreenInstrument2 to set
	 */
	public void setAssessScreenInstrument2(String assessScreenInstrument2) {
		this.assessScreenInstrument2 = assessScreenInstrument2;
	}


	/**
	 * @return the mentalHealthPgmDt1
	 */
	public String getMentalHealthPgmDt1() {
		return mentalHealthPgmDt1;
	}

	/**
	 * @param mentalHealthPgmDt1 the mentalHealthPgmDt1 to set
	 */
	public void setMentalHealthPgmDt1(String mentalHealthPgmDt1) {
		this.mentalHealthPgmDt1 = mentalHealthPgmDt1;
	}

	/**
	 * @return the mentalHealthPgmDiag1
	 */
	public String getMentalHealthPgmDiag1() {
		return mentalHealthPgmDiag1;
	}

	/**
	 * @param mentalHealthPgmDiag1 the mentalHealthPgmDiag1 to set
	 */
	public void setMentalHealthPgmDiag1(String mentalHealthPgmDiag1) {
		this.mentalHealthPgmDiag1 = mentalHealthPgmDiag1;
	}

	/**
	 * @return the mentalHealthPgmDt2
	 */
	public String getMentalHealthPgmDt2() {
		return mentalHealthPgmDt2;
	}

	/**
	 * @param mentalHealthPgmDt2 the mentalHealthPgmDt2 to set
	 */
	public void setMentalHealthPgmDt2(String mentalHealthPgmDt2) {
		this.mentalHealthPgmDt2 = mentalHealthPgmDt2;
	}

	/**
	 * @return the mentalHealthPgmDiag2
	 */
	public String getMentalHealthPgmDiag2() {
		return mentalHealthPgmDiag2;
	}

	/**
	 * @param mentalHealthPgmDiag2 the mentalHealthPgmDiag2 to set
	 */
	public void setMentalHealthPgmDiag2(String mentalHealthPgmDiag2) {
		this.mentalHealthPgmDiag2 = mentalHealthPgmDiag2;
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
	 * @return the medicalProblemsComments
	 */
	public String getMedicalProblemsComments() {
		return medicalProblemsComments;
	}

	/**
	 * @param medicalProblemsComments the medicalProblemsComments to set
	 */
	public void setMedicalProblemsComments(String medicalProblemsComments) {
		this.medicalProblemsComments = medicalProblemsComments;
	}

	/**
	 * @return the scsScore
	 */
	public String getScsScore() {
		return scsScore;
	}

	/**
	 * @param scsScore the scsScore to set
	 */
	public void setScsScore(String scsScore) {
		this.scsScore = scsScore;
	}

	/**
	 * @return the riskScore
	 */
	public String getRiskScore() {
		return riskScore;
	}

	/**
	 * @param riskScore the riskScore to set
	 */
	public void setRiskScore(String riskScore) {
		this.riskScore = riskScore;
	}

	/**
	 * @return the needsScore
	 */
	public String getNeedsScore() {
		return needsScore;
	}

	/**
	 * @param needsScore the needsScore to set
	 */
	public void setNeedsScore(String needsScore) {
		this.needsScore = needsScore;
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
	 * @return the substanceAbuseDatesList
	 */
	public List getSubstanceAbuseDatesList() {
		return substanceAbuseDatesList;
	}

	/**
	 * @param substanceAbuseDatesList the substanceAbuseDatesList to set
	 */
	public void setSubstanceAbuseDatesList(List substanceAbuseDatesList) {
		this.substanceAbuseDatesList = substanceAbuseDatesList;
	}

	/**
	 * @return the substanceAbusedList
	 */
	public List getSubstanceAbusedList() {
		return substanceAbusedList;
	}

	/**
	 * @param substanceAbusedList the substanceAbusedList to set
	 */
	public void setSubstanceAbusedList(List substanceAbusedList) {
		this.substanceAbusedList = substanceAbusedList;
	}

	/**
	 * @return the prevTreatmentDatesList
	 */
	public List getPrevTreatmentDatesList() {
		return prevTreatmentDatesList;
	}

	/**
	 * @param prevTreatmentDatesList the prevTreatmentDatesList to set
	 */
	public void setPrevTreatmentDatesList(List prevTreatmentDatesList) {
		this.prevTreatmentDatesList = prevTreatmentDatesList;
	}

	/**
	 * @return the prevTreatmentProgramsList
	 */
	public List getPrevTreatmentProgramsList() {
		return prevTreatmentProgramsList;
	}

	/**
	 * @param prevTreatmentProgramsList the prevTreatmentProgramsList to set
	 */
	public void setPrevTreatmentProgramsList(List prevTreatmentProgramsList) {
		this.prevTreatmentProgramsList = prevTreatmentProgramsList;
	}
	
		
}
