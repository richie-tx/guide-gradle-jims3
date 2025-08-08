/*
 * Created on May 3, 2005
 *
 */
package ui.juvenilecase.form;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.struts.action.ActionForm;
import ui.common.PhoneNumber;

/**
 * @author glyons
 *
 */
public class PetitionDetailsForm extends ActionForm {
	
	private String petitionNum;
	private String charge;
	private String cjisNum;
	private String cjisSuffixNum;
	private String lawEnforcementAgency;
	private Double totalPropertyLossAmount;
	private Collection dispositions;
	private Collection summaryOfFacts;
	private String juvenileNum;
	
	//Fields added for Assigned Referrals
	private Date petitionFiledDate;	
	private String penalCategory;
	private String petitionStatus;
	private String levelDegree;
	private String petitionAmendment;
	private String petitionAllegation;
	private String dpsCode; //added DPS code
	private Date petitionAmendmentDate;
	private Date arrestDate;
	private String arrestTime;
	private boolean gangRelated;
	private String underTheInfluence;
	private String offenseDegree;	
	private boolean weaponUsed;
	private String weaponType;
	private String identificationType;
	private String adultCoActor;
	private String juvenileCoActor;
	private Date offenseDate;
	private String levelOfOffense;
	private boolean notDetailed;	
	private String selectedSeqNum;
	private String selectedDALogNum;
	private String selectedTransactionNum;
	private String selectedCaseNum;
	private String selectedCodeId;
	private String daLogNum;
	private String viewAllFeePayments;
	
	private Collection propertyLosses;
	private Collection victimOrWitness;
	private Collection feePayments;
	private Collection feeReceipts;
	private Collection courtOrders;
	
	private Disposition dispositionRec = new Disposition();
	private VictimOrWitness victimWitnessRec = new VictimOrWitness();
	private FeeReceipt receipt = new FeeReceipt();
	private boolean jotChargesDisplayOnly;
	private List jotCharges;
	private String prostInd;
	private String arrestingAgency;

	/**
	 * sets all properties to null.
	 */
	public void clear()
	{
		this.petitionNum = null;
		this.charge = null;
		this.cjisNum = null;
		this.cjisSuffixNum = null;
		this.lawEnforcementAgency = null;
		this.totalPropertyLossAmount = null;
		this.summaryOfFacts = null;
		this.dispositions = null;
		this.jotCharges = null;
		this.charge = "";
		this.petitionFiledDate = new Date();	
		this.penalCategory = "";
		this.petitionStatus = "";
		this.levelDegree = "";
		this.petitionAmendment = "";
		this.petitionAllegation = "";
		this.dpsCode = "";
		this.petitionAmendmentDate = null;
		this.arrestDate = null;
		this.arrestTime = "";
		this.gangRelated = false;
		this.underTheInfluence = "";
		this.offenseDegree = "";	
		this.weaponUsed = false;
		this.weaponType = "";
		this.identificationType = "";
		this.adultCoActor = "";
		this.juvenileCoActor = "";
		this.offenseDate = null;
		this.levelOfOffense = "";
		this.selectedDALogNum = "";
		this.propertyLosses = null;
		this.jotChargesDisplayOnly = false;
		this.daLogNum = "";
	}
	
	public void resetPetitionDetails(){	    
	    
	    this.setSummaryOfFacts(null);
	    this.setArrestDate(null);
	    this.setArrestTime(null);
	    this.setArrestingAgency(null);
	    this.setJuvenileCoActor(null);
	    this.setIdentificationType(null);
	    this.setAdultCoActor(null);
	    this.setVictimOrWitness(null);
	    
	}

	/**
	 * @return cjisSuffixNum
	 */
	public String getCJISSuffixNum()
	{
		return cjisSuffixNum;
	}

	/**
	 * @return Returns the dispositionRec.
	 */
	public Disposition getDispositionRec() {
		return dispositionRec;
	}
	/**
	 * @param dispositionRec The dispositionRec to set.
	 */
	public void setDispositionRec(Disposition dispositionRec) {
		this.dispositionRec = dispositionRec;
	}
	/**
	 * @return lawEnforcementAgency
	 */
	public String getLawEnforcementAgency()
	{
		return lawEnforcementAgency;
	}

	/** 
	 * @return petitionNum
	 */
	public String getPetitionNum()
	{
		return petitionNum;
	}

	/**
	 * @return totalAmountPropertyLoss
	 */
	public Double getTotalAmountPropertyLoss()
	{
		return totalPropertyLossAmount;
	}

	/**
	 * @param string
	 */
	public void setCJISSuffixNum(String string)
	{
		cjisSuffixNum = string;
	}

	/**
	 * @param string
	 */
	public void setLawEnforcementAgency(String string)
	{
		lawEnforcementAgency = string;
	}

	/**
	 * @param string
	 */
	public void setPetitionNum(String string)
	{
		petitionNum = string;
	}
	
	/** 
	 * Gets a shortened version of the summary text
	 * @return string
	 */
	public String getSummaryTextShort() 
	{
		if (this.getSummaryOfFacts() != null)
		{
			Iterator i = this.getSummaryOfFacts().iterator();
			while (i.hasNext())
			{
				Object o = i.next();
				return o.toString().trim();
			//return sub.trim();
			}
		}
		return "";
	}

	/**
	 * @param string
	 */
	public void setTotalPropertyLossAmount(Double value)
	{
		totalPropertyLossAmount = value;
	}
	
	/**
	 * format that total propertylossamount into a formatted
	 * value of currency
	 * @return formatted amount
	 */
	public String getTotalPropertyLossAmountFormatted()
	{
		if (this.totalPropertyLossAmount != null)
		{
			NumberFormat currencyFormatter;

			currencyFormatter = NumberFormat.getCurrencyInstance();
			return currencyFormatter.format(totalPropertyLossAmount);
		}
		return "";
	}

	/**
	 * @return charge
	 */
	public String getCharge()
	{
		return charge;
	}

	/**
	 * @return cjisNum
	 */
	public String getCJISNum()
	{
		return cjisNum;
	}

	/**
	 * @param string
	 */
	public void setCharge(String string)
	{
		charge = string;
	}

	/**
	 * @param string
	 */
	public void setCJISNum(String string)
	{
		cjisNum = string;
	}
	public static class Disposition{
		private Date dispositionDate=new Date();
		private String disposition="";
		private Date judgementDate= new Date();
		private String judgement="";
		private String probationMonths="";
		private String deviationReason="";
		private String tycMonths="";
		private String guidelineSanction="";
		private String assignedSanction="";
		/**
		 * @return Returns the assignedSanction.
		 */
		public String getAssignedSanction() {
			return assignedSanction;
		}
		/**
		 * @param assignedSanction The assignedSanction to set.
		 */
		public void setAssignedSanction(String assignedSanction) {
			this.assignedSanction = assignedSanction;
		}
		/**
		 * @return Returns the deviationReason.
		 */
		public String getDeviationReason() {
			return deviationReason;
		}
		/**
		 * @param deviationReason The deviationReason to set.
		 */
		public void setDeviationReason(String deviationReason) {
			this.deviationReason = deviationReason;
		}
		/**
		 * @return Returns the disposition.
		 */
		public String getDisposition() {
			return disposition;
		}
		/**
		 * @param disposition The disposition to set.
		 */
		public void setDisposition(String disposition) {
			this.disposition = disposition;
		}
		/**
		 * @return Returns the dispositionDate.
		 */
		public Date getDispositionDate() {
			return dispositionDate;
		}
		/**
		 * @param dispositionDate The dispositionDate to set.
		 */
		public void setDispositionDate(Date dispositionDate) {
			this.dispositionDate = dispositionDate;
		}
		/**
		 * @return Returns the guidelineSanction.
		 */
		public String getGuidelineSanction() {
			return guidelineSanction;
		}
		/**
		 * @param guidelineSanction The guidelineSanction to set.
		 */
		public void setGuidelineSanction(String guidelineSanction) {
			this.guidelineSanction = guidelineSanction;
		}
		/**
		 * @return Returns the judgement.
		 */
		public String getJudgement() {
			return judgement;
		}
		/**
		 * @param judgement The judgement to set.
		 */
		public void setJudgement(String judgement) {
			this.judgement = judgement;
		}
		/**
		 * @return Returns the judgementDate.
		 */
		public Date getJudgementDate() {
			return judgementDate;
		}
		/**
		 * @param judgementDate The judgementDate to set.
		 */
		public void setJudgementDate(Date judgementDate) {
			this.judgementDate = judgementDate;
		}
		/**
		 * @return Returns the probationMonths.
		 */
		public String getProbationMonths() {
			return probationMonths;
		}
		/**
		 * @param probationMonths The probationMonths to set.
		 */
		public void setProbationMonths(String probationMonths) {
			this.probationMonths = probationMonths;
		}
		/**
		 * @return Returns the tycMonths.
		 */
		public String getTycMonths() {
			return tycMonths;
		}
		/**
		 * @param tycMonths The tycMonths to set.
		 */
		public void setTycMonths(String tycMonths) {
			this.tycMonths = tycMonths;
		}
	}

	
	public static class VictimOrWitness{
		private String associationType;
		private Date dateOfBirth;
		private String name;
		private String relationshipToJuvenile;
		private String relationshipToJuvenileId;
		private String age;
		private String transactionNum;
		private String sequenceNum;
		private String ssn;
		private String daLogNum;
		private boolean isTheStateTheComplainant;
		private String otherIDNumbers;
		private String employer;	
		private String occupation;
		private String dlStateId;
		private String dlState;
		private String dlNumber;
		
		private String aptNum;
		private String city;
		private String streetName;
		private String streetNum;
		private String streetType;
		private String addressType;
		private String county;
		private PhoneNumber phone = new PhoneNumber("");
		private String state; 
		private String zip;
		
		private String otherAptNum;
		private String otherCity;
		private PhoneNumber otherPhone = new PhoneNumber("");
		private String otherStreetName;
		private String otherStreetNumber;
		private String otherStreetType;
		private String otherZip;
		private String otherState;
		private String otherCounty;
		private String otherAddressType;
		
		private String otherInd;
		
		/**
		 * @return Returns the age.
		 */
		public String getAge() {
			return age;
		}
		/**
		 * @param age The age to set.
		 */
		public void setAge(String age) {
			this.age = age;
		}
		/**
		 * @return Returns the associationType.
		 */
		public String getAssociationType() {
			return associationType;
		}
		/**
		 * @param associationType The associationType to set.
		 */
		public void setAssociationType(String associationType) {
			this.associationType = associationType;
		}
		/**
		 * @return Returns the daLogNum.
		 */
		public String getDaLogNum() {
			return daLogNum;
		}
		/**
		 * @param daLogNum The daLogNum to set.
		 */
		public void setDaLogNum(String daLogNum) {
			this.daLogNum = daLogNum;
		}
		/**
		 * @return Returns the dateOfBirth.
		 */
		public Date getDateOfBirth() {
			return dateOfBirth;
		}
		/**
		 * @param dateOfBirth The dateOfBirth to set.
		 */
		public void setDateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}
		/**
		 * @return Returns the dlNumber.
		 */
		public String getDlNumber() {
			return dlNumber;
		}
		/**
		 * @param dlNumber The dlNumber to set.
		 */
		public void setDlNumber(String dlNumber) {
			this.dlNumber = dlNumber;
		}
		/**
		 * @return Returns the dlState.
		 */
		public String getDlState() {
			return dlState;
		}
		/**
		 * @param dlState The dlState to set.
		 */
		public void setDlState(String dlState) {
			this.dlState = dlState;
		}
		/**
		 * @return Returns the dlStateId.
		 */
		public String getDlStateId() {
			return dlStateId;
		}
		/**
		 * @param dlStateId The dlStateId to set.
		 */
		public void setDlStateId(String dlStateId) {
			this.dlStateId = dlStateId;
		}
		/**
		 * @return Returns the employer.
		 */
		public String getEmployer() {
			return employer;
		}
		/**
		 * @param employer The employer to set.
		 */
		public void setEmployer(String employer) {
			this.employer = employer;
		}
		/**
		 * @return Returns the isTheStateTheComplainant.
		 */
		public boolean isTheStateTheComplainant() {
			return isTheStateTheComplainant;
		}
		/**
		 * @param isTheStateTheComplainant The isTheStateTheComplainant to set.
		 */
		public void setTheStateTheComplainant(boolean isTheStateTheComplainant) {
			this.isTheStateTheComplainant = isTheStateTheComplainant;
		}
		/**
		 * @return Returns the name.
		 */
		public String getName() {
			return name;
		}
		/**
		 * @param name The name to set.
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * @return Returns the occupation.
		 */
		public String getOccupation() {
			return occupation;
		}
		/**
		 * @param occupation The occupation to set.
		 */
		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}
		/**
		 * @return Returns the otherIDNumbers.
		 */
		public String getOtherIDNumbers() {
			return otherIDNumbers;
		}
		/**
		 * @param otherIDNumbers The otherIDNumbers to set.
		 */
		public void setOtherIDNumbers(String otherIDNumbers) {
			this.otherIDNumbers = otherIDNumbers;
		}
		/**
		 * @return Returns the relationshipToJuvenile.
		 */
		public String getRelationshipToJuvenile() {
			return relationshipToJuvenile;
		}
		/**
		 * @param relationshipToJuvenile The relationshipToJuvenile to set.
		 */
		public void setRelationshipToJuvenile(String relationshipToJuvenile) {
			this.relationshipToJuvenile = relationshipToJuvenile;
		}
		/**
		 * @return Returns the relationshipToJuvenileId.
		 */
		public String getRelationshipToJuvenileId() {
			return relationshipToJuvenileId;
		}
		/**
		 * @param relationshipToJuvenileId The relationshipToJuvenileId to set.
		 */
		public void setRelationshipToJuvenileId(String relationshipToJuvenileId) {
			this.relationshipToJuvenileId = relationshipToJuvenileId;
		}
		/**
		 * @return Returns the sequenceNum.
		 */
		public String getSequenceNum() {
			return sequenceNum;
		}
		/**
		 * @param sequenceNum The sequenceNum to set.
		 */
		public void setSequenceNum(String sequenceNum) {
			this.sequenceNum = sequenceNum;
		}
		/**
		 * @return Returns the ssn.
		 */
		public String getSsn() {
			return ssn;
		}
		/**
		 * @param ssn The ssn to set.
		 */
		public void setSsn(String ssn) {
			this.ssn = ssn;
		}
		/**
		 * @return Returns the transactionNum.
		 */
		public String getTransactionNum() {
			return transactionNum;
		}
		/**
		 * @param transactionNum The transactionNum to set.
		 */
		public void setTransactionNum(String transactionNum) {
			this.transactionNum = transactionNum;
		}
		/**
		 * @return Returns the addressType.
		 */
		public String getAddressType() {
			return addressType;
		}
		/**
		 * @param addressType The addressType to set.
		 */
		public void setAddressType(String addressType) {
			this.addressType = addressType;
		}
		/**
		 * @return Returns the aptNum.
		 */
		public String getAptNum() {
			return aptNum;
		}
		/**
		 * @param aptNum The aptNum to set.
		 */
		public void setAptNum(String aptNum) {
			this.aptNum = aptNum;
		}
		/**
		 * @return Returns the city.
		 */
		public String getCity() {
			return city;
		}
		/**
		 * @param city The city to set.
		 */
		public void setCity(String city) {
			this.city = city;
		}
		/**
		 * @return Returns the county.
		 */
		public String getCounty() {
			return county;
		}
		/**
		 * @param county The county to set.
		 */
		public void setCounty(String county) {
			this.county = county;
		}
		/**
		 * @return Returns the phone.
		 */
		public PhoneNumber getPhone() {
			return phone;
		}
		/**
		 * @param phone The phone to set.
		 */
		public void setPhone(PhoneNumber phone) {
			this.phone = phone;
		}
		/**
		 * @return Returns the state.
		 */
		public String getState() {
			return state;
		}
		/**
		 * @param state The state to set.
		 */
		public void setState(String state) {
			this.state = state;
		}
		/**
		 * @return Returns the streetName.
		 */
		public String getStreetName() {
			return streetName;
		}
		/**
		 * @param streetName The streetName to set.
		 */
		public void setStreetName(String streetName) {
			this.streetName = streetName;
		}
		/**
		 * @return Returns the streetNum.
		 */
		public String getStreetNum() {
			return streetNum;
		}
		/**
		 * @param streetNum The streetNum to set.
		 */
		public void setStreetNum(String streetNum) {
			this.streetNum = streetNum;
		}
		/**
		 * @return Returns the streetType.
		 */
		public String getStreetType() {
			return streetType;
		}
		/**
		 * @param streetType The streetType to set.
		 */
		public void setStreetType(String streetType) {
			this.streetType = streetType;
		}
		/**
		 * @return Returns the zip.
		 */
		public String getZip() {
			return zip;
		}
		/**
		 * @param zip The zip to set.
		 */
		public void setZip(String zip) {
			this.zip = zip;
		}
		/**
		 * @return Returns the otherAddressType.
		 */
		public String getOtherAddressType() {
			return otherAddressType;
		}
		/**
		 * @param otherAddressType The otherAddressType to set.
		 */
		public void setOtherAddressType(String otherAddressType) {
			this.otherAddressType = otherAddressType;
		}
		/**
		 * @return Returns the otherAptNum.
		 */
		public String getOtherAptNum() {
			return otherAptNum;
		}
		/**
		 * @param otherAptNum The otherAptNum to set.
		 */
		public void setOtherAptNum(String otherAptNum) {
			this.otherAptNum = otherAptNum;
		}
		/**
		 * @return Returns the otherCity.
		 */
		public String getOtherCity() {
			return otherCity;
		}
		/**
		 * @param otherCity The otherCity to set.
		 */
		public void setOtherCity(String otherCity) {
			this.otherCity = otherCity;
		}
		/**
		 * @return Returns the otherCounty.
		 */
		public String getOtherCounty() {
			return otherCounty;
		}
		/**
		 * @param otherCounty The otherCounty to set.
		 */
		public void setOtherCounty(String otherCounty) {
			this.otherCounty = otherCounty;
		}
		/**
		 * @return Returns the otherInd.
		 */
		public String getOtherInd() {
			return otherInd;
		}
		/**
		 * @param otherInd The otherInd to set.
		 */
		public void setOtherInd(String otherInd) {
			this.otherInd = otherInd;
		}
		/**
		 * @return Returns the otherPhone.
		 */
		public PhoneNumber getOtherPhone() {
			return otherPhone;
		}
		/**
		 * @param otherPhone The otherPhone to set.
		 */
		public void setOtherPhone(PhoneNumber otherPhone) {
			this.otherPhone = otherPhone;
		}
		/**
		 * @return Returns the otherState.
		 */
		public String getOtherState() {
			return otherState;
		}
		/**
		 * @param otherState The otherState to set.
		 */
		public void setOtherState(String otherState) {
			this.otherState = otherState;
		}
		/**
		 * @return Returns the otherStreetName.
		 */
		public String getOtherStreetName() {
			return otherStreetName;
		}
		/**
		 * @param otherStreetName The otherStreetName to set.
		 */
		public void setOtherStreetName(String otherStreetName) {
			this.otherStreetName = otherStreetName;
		}
		/**
		 * @return Returns the otherStreetNumber.
		 */
		public String getOtherStreetNumber() {
			return otherStreetNumber;
		}
		/**
		 * @param otherStreetNumber The otherStreetNumber to set.
		 */
		public void setOtherStreetNumber(String otherStreetNumber) {
			this.otherStreetNumber = otherStreetNumber;
		}
		/**
		 * @return Returns the otherStreetType.
		 */
		public String getOtherStreetType() {
			return otherStreetType;
		}
		/**
		 * @param otherStreetType The otherStreetType to set.
		 */
		public void setOtherStreetType(String otherStreetType) {
			this.otherStreetType = otherStreetType;
		}
		/**
		 * @return Returns the otherZip.
		 */
		public String getOtherZip() {
			return otherZip;
		}
		/**
		 * @param otherZip The otherZip to set.
		 */
		public void setOtherZip(String otherZip) {
			this.otherZip = otherZip;
		}
	}
	
	public static class FeeReceipt{
		
		private String feeType;
		private String payorType;
		private String payor;
		private String payorAddress;
		private PhoneNumber payorPhone;
		private String petitionNum;
		private Date paidDate;
		private Double amtPaid;
		private String receiptNum;
		private String transactionNum;
		private String feeStatus;
		private Date receivedDate;
		private String codeId;
	
		
		/**
		 * @return Returns the amtPaid.
		 */
		public Double getAmtPaid() {
			return amtPaid;
		}
		/**
		 * @param amtPaid The amtPaid to set.
		 */
		public void setAmtPaid(Double amtPaid) {
			this.amtPaid = amtPaid;
		}
		/**
		 * @return Returns the feeStatus.
		 */
		public String getFeeStatus() {
			return feeStatus;
		}
		/**
		 * @param feeStatus The feeStatus to set.
		 */
		public void setFeeStatus(String feeStatus) {
			this.feeStatus = feeStatus;
		}
		/**
		 * @return Returns the feeType.
		 */
		public String getFeeType() {
			return feeType;
		}
		/**
		 * @param feeType The feeType to set.
		 */
		public void setFeeType(String feeType) {
			this.feeType = feeType;
		}
		
		/**
		 * @return Returns the payor.
		 */
		public String getPayor() {
			return payor;
		}
		/**
		 * @param payor The payor to set.
		 */
		public void setPayor(String payor) {
			this.payor = payor;
		}
		/**
		 * @return Returns the payorAddress.
		 */
		public String getPayorAddress() {
			return payorAddress;
		}
		/**
		 * @param payorAddress The payorAddress to set.
		 */
		public void setPayorAddress(String payorAddress) {
			this.payorAddress = payorAddress;
		}
		
		/**
		 * @return Returns the payorType.
		 */
		public String getPayorType() {
			return payorType;
		}
		/**
		 * @param payorType The payorType to set.
		 */
		public void setPayorType(String payorType) {
			this.payorType = payorType;
		}
		/**
		 * @return Returns the petitionNum.
		 */
		public String getPetitionNum() {
			return petitionNum;
		}
		/**
		 * @param petitionNum The petitionNum to set.
		 */
		public void setPetitionNum(String petitionNum) {
			this.petitionNum = petitionNum;
		}
		/**
		 * @return Returns the receiptNum.
		 */
		public String getReceiptNum() {
			return receiptNum;
		}
		/**
		 * @param receiptNum The receiptNum to set.
		 */
		public void setReceiptNum(String receiptNum) {
			this.receiptNum = receiptNum;
		}
		
		/**
		 * @return Returns the transactionNum.
		 */
		public String getTransactionNum() {
			return transactionNum;
		}
		/**
		 * @param transactionNum The transactionNum to set.
		 */
		public void setTransactionNum(String transactionNum) {
			this.transactionNum = transactionNum;
		}
		/**
		 * @return Returns the paidDate.
		 */
		public Date getPaidDate() {
			return paidDate;
		}
		/**
		 * @param paidDate The paidDate to set.
		 */
		public void setPaidDate(Date paidDate) {
			this.paidDate = paidDate;
		}
		/**
		 * @return Returns the receivedDate.
		 */
		public Date getReceivedDate() {
			return receivedDate;
		}
		/**
		 * @param receivedDate The receivedDate to set.
		 */
		public void setReceivedDate(Date receivedDate) {
			this.receivedDate = receivedDate;
		}
		/**
		 * @return Returns the codeId.
		 */
		public String getCodeId() {
			return codeId;
		}
		/**
		 * @param codeId The codeId to set.
		 */
		public void setCodeId(String codeId) {
			this.codeId = codeId;
		}
		/**
		 * @return Returns the payorPhone.
		 */
		public PhoneNumber getPayorPhone() {
			return payorPhone;
		}
		/**
		 * @param payorPhone The payorPhone to set.
		 */
		public void setPayorPhone(PhoneNumber payorPhone) {
			this.payorPhone = payorPhone;
		}
	}
	/**
	 * @return Returns the adultCoActor.
	 */
	public String getAdultCoActor() {
		return adultCoActor;
	}
	/**
	 * @param adultCoActor The adultCoActor to set.
	 */
	public void setAdultCoActor(String adultCoActor) {
		this.adultCoActor = adultCoActor;
	}
	/**
	 * @return Returns the arrestDate.
	 */
	public Date getArrestDate() {
		return arrestDate;
	}
	/**
	 * @param arrestDate The arrestDate to set.
	 */
	public void setArrestDate(Date arrestDate) {
		this.arrestDate = arrestDate;
	}
	/**
	 * @return Returns the arrestTime.
	 */
	public String getArrestTime() {
		if (!"".equals(arrestTime) && arrestTime != null) {
			return (arrestTime.substring(0,2) + ":" + arrestTime.substring(2,4));
		}
		return arrestTime;
	}
	/**
	 * @param arrestTime The arrestTime to set.
	 */
	public void setArrestTime(String arrestTime) {
		this.arrestTime = arrestTime;
	}
	/**
	 * @return Returns the cjisNum.
	 */
	public String getCjisNum() {
		return cjisNum;
	}
	/**
	 * @param cjisNum The cjisNum to set.
	 */
	public void setCjisNum(String cjisNum) {
		this.cjisNum = cjisNum;
	}
	/**
	 * @return Returns the cjisSuffixNum.
	 */
	public String getCjisSuffixNum() {
		return cjisSuffixNum;
	}
	/**
	 * @param cjisSuffixNum The cjisSuffixNum to set.
	 */
	public void setCjisSuffixNum(String cjisSuffixNum) {
		this.cjisSuffixNum = cjisSuffixNum;
	}
	/**
	 * @return Returns the gangRelated.
	 */
	public boolean isGangRelated() {
		return gangRelated;
	}
	/**
	 * @param gangRelated The gangRelated to set.
	 */
	public void setGangRelated(boolean gangRelated) {
		this.gangRelated = gangRelated;
	}
	/**
	 * @return Returns the identificationType.
	 */
	public String getIdentificationType() {
		return identificationType;
	}
	/**
	 * @param identificationType The identificationType to set.
	 */
	public void setIdentificationType(String identificationType) {
		this.identificationType = identificationType;
	}
	/**
	 * @return Returns the juvenileCoActor.
	 */
	public String getJuvenileCoActor() {
		return juvenileCoActor;
	}
	/**
	 * @param juvenileCoActor The juvenileCoActor to set.
	 */
	public void setJuvenileCoActor(String juvenileCoActor) {
		this.juvenileCoActor = juvenileCoActor;
	}
	/**
	 * @return Returns the levelDegree.
	 */
	public String getLevelDegree() {
		return levelDegree;
	}
	/**
	 * @param levelDegree The levelDegree to set.
	 */
	public void setLevelDegree(String levelDegree) {
		this.levelDegree = levelDegree;
	}
	/**
	 * @return Returns the offenseDegree.
	 */
	public String getOffenseDegree() {
		return offenseDegree;
	}
	/**
	 * @param offenseDegree The offenseDegree to set.
	 */
	public void setOffenseDegree(String offenseDegree) {
		this.offenseDegree = offenseDegree;
	}
	/**
	 * @return Returns the penalCategory.
	 */
	public String getPenalCategory() {
		return penalCategory;
	}
	/**
	 * @param penalCategory The penalCategory to set.
	 */
	public void setPenalCategory(String penalCategory) {
		this.penalCategory = penalCategory;
	}
	/**
	 * @return Returns the petitionAllegation.
	 */
	public String getPetitionAllegation() {
		return petitionAllegation;
	}
	/**
	 * @param petitionAllegation The petitionAllegation to set.
	 */
	public void setPetitionAllegation(String petitionAllegation) {
		this.petitionAllegation = petitionAllegation;
	}
	
	/**
	 * @return Returns the dpsCode.
	 */
	public String getDpsCode() {
		return dpsCode;
	}
	/**
	 * @param dpsCode The dpsCode to set.
	 */
	public void setDpsCode(String dpsCode) {
		this.dpsCode = dpsCode;
	}
	
	
	/**
	 * @return Returns the petitionAmendment.
	 */
	public String getPetitionAmendment() {
		return petitionAmendment;
	}
	/**
	 * @param petitionAmendment The petitionAmendment to set.
	 */
	public void setPetitionAmendment(String petitionAmendment) {
		this.petitionAmendment = petitionAmendment;
	}
	/**
	 * @return Returns the petitionAmendmentDate.
	 */
	public Date getPetitionAmendmentDate() {
		return petitionAmendmentDate;
	}
	/**
	 * @param petitionAmendmentDate The petitionAmendmentDate to set.
	 */
	public void setPetitionAmendmentDate(Date petitionAmendmentDate) {
		this.petitionAmendmentDate = petitionAmendmentDate;
	}
	/**
	 * @return Returns the petitionFiledDate.
	 */
	public Date getPetitionFiledDate() {
		return petitionFiledDate;
	}
	/**
	 * @param petitionFiledDate The petitionFiledDate to set.
	 */
	public void setPetitionFiledDate(Date petitionFiledDate) {
		this.petitionFiledDate = petitionFiledDate;
	}
	/**
	 * @return Returns the petitionStatus.
	 */
	public String getPetitionStatus() {
		return petitionStatus;
	}
	/**
	 * @param petitionStatus The petitionStatus to set.
	 */
	public void setPetitionStatus(String petitionStatus) {
		this.petitionStatus = petitionStatus;
	}

	/**
	 * @return Returns the victimOrWitness.
	 */
	public Collection getVictimOrWitness() {
		return victimOrWitness;
	}
	/**
	 * @param victimOrWitness The victimOrWitness to set.
	 */
	public void setVictimOrWitness(Collection victimOrWitness) {
		this.victimOrWitness = victimOrWitness;
	}
	/**
	 * @return Returns the weaponType.
	 */
	public String getWeaponType() {
		return weaponType;
	}
	/**
	 * @param weaponType The weaponType to set.
	 */
	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}
	
	/**
	 * @return Returns the totalPropertyLossAmount.
	 */
	public Double getTotalPropertyLossAmount() {
		return totalPropertyLossAmount;
	}
	/**
	 * @return Returns the underTheInfluence.
	 */
	public String getUnderTheInfluence() {
		return underTheInfluence;
	}
	/**
	 * @param underTheInfluence The underTheInfluence to set.
	 */
	public void setUnderTheInfluence(String underTheInfluence) {
		this.underTheInfluence = underTheInfluence;
	}
	/**
	 * @return Returns the weaponUsed.
	 */
	public boolean isWeaponUsed() {
		return weaponUsed;
	}
	/**
	 * @param weaponUsed The weaponUsed to set.
	 */
	public void setWeaponUsed(boolean weaponUsed) {
		this.weaponUsed = weaponUsed;
	}
	/**
	 * @return Returns the offenseDate.
	 */
	public Date getOffenseDate() {
		return offenseDate;
	}
	/**
	 * @param offenseDate The offenseDate to set.
	 */
	public void setOffenseDate(Date offenseDate) {
		this.offenseDate = offenseDate;
	}
	/**
	 * @return Returns the levelOfOffense.
	 */
	public String getLevelOfOffense() {
		return levelOfOffense;
	}
	/**
	 * @param levelOfOffense The levelOfOffense to set.
	 */
	public void setLevelOfOffense(String levelOfOffense) {
		this.levelOfOffense = levelOfOffense;
	}
	/**
	 * @return Returns the notDetailed.
	 */
	public boolean isNotDetailed() {
		return notDetailed;
	}
	/**
	 * @param notDetailed The notDetailed to set.
	 */
	public void setNotDetailed(boolean notDetailed) {
		this.notDetailed = notDetailed;
	}
	/**
	 * @return Returns the selectedDALogNum.
	 */
	public String getSelectedDALogNum() {
		return selectedDALogNum;
	}
	/**
	 * @param selectedDALogNum The selectedDALogNum to set.
	 */
	public void setSelectedDALogNum(String selectedDALogNum) {
		this.selectedDALogNum = selectedDALogNum;
	}
	/**
	 * @return Returns the selectedSeqNum.
	 */
	public String getSelectedSeqNum() {
		return selectedSeqNum;
	}
	/**
	 * @param selectedSeqNum The selectedSeqNum to set.
	 */
	public void setSelectedSeqNum(String selectedSeqNum) {
		this.selectedSeqNum = selectedSeqNum;
	}
	/**
	 * @return Returns the feePayments.
	 */
	public Collection getFeePayments() {
		return feePayments;
	}
	/**
	 * @param feePayments The feePayments to set.
	 */
	public void setFeePayments(Collection feePayments) {
		this.feePayments = feePayments;
	}
	/**
	 * @return Returns the selectedTransactionNum.
	 */
	public String getSelectedTransactionNum() {
		return selectedTransactionNum;
	}
	/**
	 * @param selectedTransactionNum The selectedTransactionNum to set.
	 */
	public void setSelectedTransactionNum(String selectedTransactionNum) {
		this.selectedTransactionNum = selectedTransactionNum;
	}
	/**
	 * @return Returns the daLogNum.
	 */
	public String getDaLogNum() {
		return daLogNum;
	}
	/**
	 * @param daLogNum The daLogNum to set.
	 */
	public void setDaLogNum(String daLogNum) {
		this.daLogNum = daLogNum;
	}
	/**
	 * @return Returns the propertyLosses.
	 */
	public Collection getPropertyLosses() {
		return propertyLosses;
	}
	/**
	 * @param propertyLosses The propertyLosses to set.
	 */
	public void setPropertyLosses(Collection propertyLosses) {
		this.propertyLosses = propertyLosses;
	}
	/**
	 * @return Returns the victimWitnessRec.
	 */
	public VictimOrWitness getVictimWitnessRec() {
		return victimWitnessRec;
	}
	/**
	 * @param victimWitnessRec The victimWitnessRec to set.
	 */
	public void setVictimWitnessRec(VictimOrWitness victimWitnessRec) {
		this.victimWitnessRec = victimWitnessRec;
	}
	/**
	 * @return Returns the selectedCaseNum.
	 */
	public String getSelectedCaseNum() {
		return selectedCaseNum;
	}
	/**
	 * @param selectedCaseNum The selectedCaseNum to set.
	 */
	public void setSelectedCaseNum(String selectedCaseNum) {
		this.selectedCaseNum = selectedCaseNum;
	}
	/**
	 * @return Returns the feeReceipts.
	 */
	public Collection getFeeReceipts() {
		return feeReceipts;
	}
	/**
	 * @param feeReceipts The feeReceipts to set.
	 */
	public void setFeeReceipts(Collection feeReceipts) {
		this.feeReceipts = feeReceipts;
	}
	/**
	 * @return Returns the selectedCodeId.
	 */
	public String getSelectedCodeId() {
		return selectedCodeId;
	}
	/**
	 * @param selectedCodeId The selectedCodeId to set.
	 */
	public void setSelectedCodeId(String selectedCodeId) {
		this.selectedCodeId = selectedCodeId;
	}
	/**
	 * @return Returns the viewAllFeePayments.
	 */
	public String getViewAllFeePayments() {
		return viewAllFeePayments;
	}
	/**
	 * @param viewAllFeePayments The viewAllFeePayments to set.
	 */
	public void setViewAllFeePayments(String viewAllFeePayments) {
		this.viewAllFeePayments = viewAllFeePayments;
	}
	/**
	 * @return Returns the courtOrders.
	 */
	public Collection getCourtOrders() {
		return courtOrders;
	}
	/**
	 * @param courtOrders The courtOrders to set.
	 */
	public void setCourtOrders(Collection courtOrders) {
		this.courtOrders = courtOrders;
	}

	/**
	 * @return the jotChargesDiplayOnly
	 */
	public boolean isJotChargesDisplayOnly() {
		return jotChargesDisplayOnly;
	}

	/**
	 * @param jotChargesDiplayOnly the jotChargesDiplayOnly to set
	 */
	public void setJotChargesDisplayOnly(boolean jotChargesDisplayOnly) {
		this.jotChargesDisplayOnly = jotChargesDisplayOnly;
	}

	public void setSummaryOfFacts(Collection facts)
	{
		summaryOfFacts = facts;
	}
	
	public Collection getSummaryOfFacts()
	{
		return summaryOfFacts;
	}
	/**
	 * 
	 * @return collection of dispositions
	 */
	public Collection getDispositions() {
		return dispositions;
	}
	/** 
	 * 
	 * @param aDispositions 
	 */
	public void setDispositions(Collection aDispositions) {
		this.dispositions = aDispositions;
	}

	/**
	 * @return the jotCharges
	 */
	public List getJotCharges() {
		return jotCharges;
	}

	/**
	 * @param jotCharges the jotCharges to set
	 */
	public void setJotCharges(List jotCharges) {
		this.jotCharges = jotCharges;
	}

	public String getJuvenileNum() {
		return juvenileNum;
	}

	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	//task 149503
	public String getProstInd()
	{
	    return prostInd;
	}

	public void setProstInd(String prostInd)
	{
	    this.prostInd = prostInd;
	}
	//

	public String getArrestingAgency()
	{
	    return arrestingAgency;
	}

	public void setArrestingAgency(String arrestingAgency)
	{
	    this.arrestingAgency = arrestingAgency;
	}
}
