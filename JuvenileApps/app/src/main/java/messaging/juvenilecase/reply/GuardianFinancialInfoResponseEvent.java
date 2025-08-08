/*
 * Created on Aug 28, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenilecase.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GuardianFinancialInfoResponseEvent extends ResponseEvent {
	
	private boolean constellationStatus;	//STATUS
	private boolean guardian;				//GUARDIAN
	private String firstName;				//FIRSTNAME
	private String middleName;				//MIDDLENAME
	private String lastName;				//LASTNAME	
	private boolean isDisabled;				//DISABLED
	private String occupation;				//JOBDESC
	private String employerName;			//EMPLOYNAME
	private String salary;					//SALARY
	private String salaryRate;				//SALARYRATE
	private int employerAddressId;			//ADDRESS_ID
	
	private String streetNumber;			//STREETNUMBER
	private String streetname;				//STREETNAME
	private String streetAddress2;			//STREETADDRESS2
	private String apartmentNumber;			//APARTMENTNUMBER
	private String city;					//CITY
	private String state;					//STATE
	private String zipcode;					//ZIPCODE
	
	private double otherIncomeAmount;		//OTHERINCOME
	
	private boolean isEligibleForMedicaid;	//ISELGFORMDC
	private boolean isReceivingMedicaid;	//ISRVCFORMDC
	
	// Funds Applicable to Child
	private double annualNetIncome;			//ANETINCOME
	private double foodStamps;				//FAMFOODSTP
	private double intangibleProperty;			//INTANIGLEPROP
	private double monthlyLifeInsurancePremium;//MNTHLIFEPREM
	private double propertyValue;				//REALPROP
	private double tanf;						//MNTHTANF
	private double ssi;						//MNTHTANF
	
	// Insurance Applicable to Child
	private String healthInsuranceTypeCD;	//HEALTHINSTYPECD	
	
	private Date entryDate;						//ENTRYDATE (CREATEDATE FROM MEMBER'S EMPLOYMENT RECORD)
	private int famMemberId;					//FAMMEMBER_ID
	
	/**
	 * @return Returns the annualNetIncome.
	 */
	public double getAnnualNetIncome() {
		return annualNetIncome;
	}
	/**
	 * @return Returns the constellationStatus.
	 */
	public boolean isConstellationStatus() {
		return constellationStatus;
	}
	/**
	 * @return Returns the employerAddress.
	 */
	public int getEmployerAddressId() {
		return employerAddressId;
	}
	/**
	 * @return Returns the employerName.
	 */
	public String getEmployerName() {
		return employerName;
	}
	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @return Returns the foodStamps.
	 */
	public double isFoodStamps() {
		return foodStamps;
	}
	/**
	 * @return Returns the healthInsuranceTypeCD.
	 */
	public String getHealthInsuranceTypeCD() {
		return healthInsuranceTypeCD;
	}
	/**
	 * @return Returns the intangibleProperty.
	 */
	public double getIntangibleProperty() {
		return intangibleProperty;
	}
	/**
	 * @return Returns the isDisabled.
	 */
	public boolean isDisabled() {
		return isDisabled;
	}
	/**
	 * @return Returns the isEligibleForMedicaid.
	 */
	public boolean isEligibleForMedicaid() {
		return isEligibleForMedicaid;
	}
	/**
	 * @return Returns the isReceivingMedicaid.
	 */
	public boolean isReceivingMedicaid() {
		return isReceivingMedicaid;
	}
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @return Returns the middleName.
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @return Returns the monthlyLifeInsurancePremium.
	 */
	public double getMonthlyLifeInsurancePremium() {
		return monthlyLifeInsurancePremium;
	}
	/**
	 * @return Returns the occupation.
	 */
	public String getOccupation() {
		return occupation;
	}
	/**
	 * @return Returns the otherIncomeAmount.
	 */
	public double getOtherIncomeAmount() {
		return otherIncomeAmount;
	}
	/**
	 * @return Returns the propertyValue.
	 */
	public double getPropertyValue() {
		return propertyValue;
	}
	/**
	 * @return Returns the salary.
	 */
	public String getSalary() {
		return salary;
	}
	/**
	 * @return Returns the salaryRate.
	 */
	public String getSalaryRate() {
		return salaryRate;
	}
	/**
	 * @return Returns the tanf.
	 */
	public double getTanf() {
		return tanf;
	}
	/**
	 * @param annualNetIncome The annualNetIncome to set.
	 */
	public void setAnnualNetIncome(double annualNetIncome) {
		this.annualNetIncome = annualNetIncome;
	}
	/**
	 * @param constellationStatus The constellationStatus to set.
	 */
	public void setConstellationStatus(boolean constellationStatus) {
		this.constellationStatus = constellationStatus;
	}
	/**
	 * @param employerAddress The employerAddress to set.
	 */
	public void setEmployerAddressId(int employerAddressId) {
		this.employerAddressId = employerAddressId;
	}
	/**
	 * @param employerName The employerName to set.
	 */
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @param foodStamps The foodStamps to set.
	 */
	public void setFoodStamps(double foodStamps) {
		this.foodStamps = foodStamps;
	}
	/**
	 * @param healthInsuranceTypeCD The healthInsuranceTypeCD to set.
	 */
	public void setHealthInsuranceTypeCD(String healthInsuranceTypeCD) {
		this.healthInsuranceTypeCD = healthInsuranceTypeCD;
	}
	/**
	 * @param intangibleProperty The intangibleProperty to set.
	 */
	public void setIntangibleProperty(double intangibleProperty) {
		this.intangibleProperty = intangibleProperty;
	}
	/**
	 * @param isDisabled The isDisabled to set.
	 */
	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
	/**
	 * @param isEligibleForMedicaid The isEligibleForMedicaid to set.
	 */
	public void setEligibleForMedicaid(boolean isEligibleForMedicaid) {
		this.isEligibleForMedicaid = isEligibleForMedicaid;
	}
	/**
	 * @param isReceivingMedicaid The isReceivingMedicaid to set.
	 */
	public void setReceivingMedicaid(boolean isReceivingMedicaid) {
		this.isReceivingMedicaid = isReceivingMedicaid;
	}
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @param middleName The middleName to set.
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @param monthlyLifeInsurancePremium The monthlyLifeInsurancePremium to set.
	 */
	public void setMonthlyLifeInsurancePremium(double monthlyLifeInsurancePremium) {
		this.monthlyLifeInsurancePremium = monthlyLifeInsurancePremium;
	}
	/**
	 * @param occupation The occupation to set.
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	/**
	 * @param otherIncomeAmount The otherIncomeAmount to set.
	 */
	public void setOtherIncomeAmount(double otherIncomeAmount) {
		this.otherIncomeAmount = otherIncomeAmount;
	}
	/**
	 * @param propertyValue The propertyValue to set.
	 */
	public void setPropertyValue(double propertyValue) {
		this.propertyValue = propertyValue;
	}
	/**
	 * @param salary The salary to set.
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}
	/**
	 * @param salaryRate The salaryRate to set.
	 */
	public void setSalaryRate(String salaryRate) {
		this.salaryRate = salaryRate;
	}
	/**
	 * @param tanf The tanf to set.
	 */
	public void setTanf(double tanf) {
		this.tanf = tanf;
	}
	/**
	 * @return Returns the guardian.
	 */
	public boolean isGuardian() {
		return guardian;
	}
	/**
	 * @param guardian The guardian to set.
	 */
	public void setGuardian(boolean guardian) {
		this.guardian = guardian;
	}
	/**
	 * @return Returns the entryDate.
	 */
	public Date getEntryDate() {
		return entryDate;
	}
	/**
	 * @param entryDate The entryDate to set.
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	/**
	 * @return Returns the famMemberId.
	 */
	public int getFamMemberId() {
		return famMemberId;
	}
	/**
	 * @param famMemberId The famMemberId to set.
	 */
	public void setFamMemberId(int famMemberId) {
		this.famMemberId = famMemberId;
	}
	/**
	 * @return Returns the ssi.
	 */
	public double getSsi() {
		return ssi;
	}
	/**
	 * @param ssi The ssi to set.
	 */
	public void setSsi(double ssi) {
		this.ssi = ssi;
	}
}
