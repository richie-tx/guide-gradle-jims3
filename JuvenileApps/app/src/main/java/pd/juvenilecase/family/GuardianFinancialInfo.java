package pd.juvenilecase.family;

import java.util.Date;
import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class GuardianFinancialInfo extends PersistentObject {
	private String juvenileId; // JUVENILE_ID
	private boolean constellationStatus; // CONSTSTATUS
	private boolean guardian; // GUARDIAN
	private String firstName; // FIRSTNAME
	private String middleName; // MIDDLENAME
	private String lastName; // LASTNAME
	private boolean disabled; // DISABLED
	private String occupation; // JOBDESC
	private String employerName; // EMPLOYNAME
	private String salary; // SALARY
	private String salaryRate; // SALARYRATE
	private int employerAddressId = 0; // ADDRESS_ID
	private double otherIncomeAmount; // OTHERINCOME
	private boolean eligibleForMedicaid; // ISELGFORMDC
	private boolean receivingMedicaid; // ISRVCFORMDC

	// Funds Applicable to Child
	private double annualNetIncome; // ANETINCOME
	private double foodStamps; // FAMFOODSTP
	private double intangibleProperty; // INTANIGLEPROP
	private double monthlyLifeInsurancePremium; // MNTHLIFEPREM
	private double propertyValue; // REALPROP
	private double tanf; // MNTHTANF
	private double ssi;

	// Insurance Applicable to Child
	private String healthInsuranceTypeCD; // HEALTHINSTYPECD
	private Date entryDate; // ENTRYDATE (CREATEDATE FROM MEMBER'S EMPLOYMENT
							// RECORD)
	private int famMemberId; // FAMMEMBER_ID

	/**
	 * @return
	 */
	public double getAnnualNetIncome() {
		fetch();
		return annualNetIncome;
	}

	public double getSsi() {
		fetch();
		return ssi;
	}

	public void setSsi(double d) {
		this.ssi = d;
	}

	/**
	 * @return
	 */
	public String getJuvenileId() {
		fetch();
		return juvenileId;
	}

	/**
	 * @return
	 */
	public String getFirstName() {
		fetch();
		return firstName;
	}

	/**
	 * @return
	 */
	public String getLastName() {
		fetch();
		return lastName;
	}

	/**
	 * @return
	 */
	public String getMiddleName() {
		fetch();
		return middleName;
	}

	/**
	 * @return
	 */
	public double getFoodStamps() {
		fetch();
		return foodStamps;
	}

	/**
	 * @return
	 */
	public double getIntangibleProperty() {
		fetch();
		return intangibleProperty;
	}

	/**
	 * @return
	 */
	public String getOccupation() {
		fetch();
		return occupation;
	}

	/**
	 * @return
	 */
	public double getMonthlyLifeInsurancePremium() {
		fetch();
		return monthlyLifeInsurancePremium;
	}

	/**
	 * @return
	 */
	public double getOtherIncomeAmount() {
		fetch();
		return otherIncomeAmount;
	}

	/**
	 * @return
	 */
	public double getPropertyValue() {
		fetch();
		return propertyValue;
	}

	/**
	 * @return
	 */
	public double getTanf() {
		fetch();
		return tanf;
	}

	/**
	 * @param d
	 */
	public void setAnnualNetIncome(double d) {
		this.annualNetIncome = d;
	}

	/**
	 * @param string
	 */
	public void setJuvenileId(String string) {
		this.juvenileId = string;
	}

	/**
	 * @param string
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param string
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @param b
	 */
	public void setFoodStamps(double d) {
		this.foodStamps = d;
	}

	/**
	 * @param d
	 */
	public void setIntangibleProperty(double d) {
		this.intangibleProperty = d;
	}

	/**
	 * @param string
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * @param d
	 */
	public void setMonthlyLifeInsurancePremium(double d) {
		this.monthlyLifeInsurancePremium = d;
	}

	/**
	 * @param d
	 */
	public void setOtherIncomeAmount(double d) {
		this.otherIncomeAmount = d;
	}

	/**
	 * @param d
	 */
	public void setPropertyValue(double d) {
		this.propertyValue = d;
	}

	/**
	 * @param d
	 */
	public void setTanf(double d) {
		this.tanf = d;
	}

	/**
	 * Finds all FamilyMemberFinancial by an attribute value
	 * 
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator guardianFinancialInfo = home.findAll(attributeName,
				attributeValue, GuardianFinancialInfo.class);
		return guardianFinancialInfo;
	}

	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, GuardianFinancialInfo.class);
	}

	/**
	 * @return Returns the constellationStatus.
	 */
	public boolean isConstellationStatus() {
		fetch();
		return constellationStatus;
	}

	/**
	 * @return Returns the guardian.
	 */
	public boolean isGuardian() {
		fetch();
		return guardian;
	}

	/**
	 * @param guardian
	 *            The guardian to set.
	 */
	public void setGuardian(boolean b) {
		this.guardian = b;
	}

	/**
	 * @return Returns the employerAddress.
	 */
	public int getEmployerAddressId() {
		fetch();
		return employerAddressId;
	}

	/**
	 * @param employerAddress
	 *            The employerAddress to set.
	 */
	public void setEmployerAddressId(int employerAddressId) {
		this.employerAddressId = employerAddressId;
	}

	/**
	 * @return Returns the employerName.
	 */
	public String getEmployerName() {
		fetch();
		return employerName;
	}

	/**
	 * @param employerName
	 *            The employerName to set.
	 */
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	/**
	 * @return Returns the healthInsuranceTypeCD.
	 */
	public String getHealthInsuranceTypeCD() {
		fetch();
		return healthInsuranceTypeCD;
	}

	/**
	 * @param healthInsuranceTypeCD
	 *            The healthInsuranceTypeCD to set.
	 */
	public void setHealthInsuranceTypeCD(String healthInsuranceTypeCD) {
		this.healthInsuranceTypeCD = healthInsuranceTypeCD;
	}

	/**
	 * @return Returns the isDisabled.
	 */
	public boolean isDisabled() {
		fetch();
		return disabled;
	}

	/**
	 * @param isDisabled
	 *            The isDisabled to set.
	 */
	public void setDisabled(boolean b) {
		this.disabled = b;
	}

	/**
	 * @return Returns the isEligibleForMedicaid.
	 */
	public boolean isEligibleForMedicaid() {
		fetch();
		return eligibleForMedicaid;
	}

	/**
	 * @param isEligibleForMedicaid
	 *            The isEligibleForMedicaid to set.
	 */
	public void setEligibleForMedicaid(boolean b) {
		this.eligibleForMedicaid = b;
	}

	/**
	 * @return Returns the isReceivingMedicaid.
	 */
	public boolean isReceivingMedicaid() {
		fetch();
		return receivingMedicaid;
	}

	/**
	 * @param isReceivingMedicaid
	 *            The isReceivingMedicaid to set.
	 */
	public void setReceivingMedicaid(boolean b) {
		this.receivingMedicaid = b;
	}

	/**
	 * @return Returns the salary.
	 */
	public String getSalary() {
		fetch();
		return salary;
	}

	/**
	 * @param salary
	 *            The salary to set.
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}

	/**
	 * @return Returns the salaryRate.
	 */
	public String getSalaryRate() {
		fetch();
		return salaryRate;
	}

	/**
	 * @param salaryRate
	 *            The salaryRate to set.
	 */
	public void setSalaryRate(String salaryRate) {
		this.salaryRate = salaryRate;
	}

	/**
	 * @param constellationStatus
	 *            The constellationStatus to set.
	 */
	public void setConstellationStatus(boolean constellationStatus) {
		this.constellationStatus = constellationStatus;
	}

	/**
	 * @return Returns the entryDate.
	 */
	public Date getEntryDate() {
		fetch();
		return entryDate;
	}

	/**
	 * @param entryDate
	 *            The entryDate to set.
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return Returns the famMemberId.
	 */
	public int getFamMemberId() {
		fetch();
		return famMemberId;
	}

	/**
	 * @param famMemberId
	 *            The famMemberId to set.
	 */
	public void setFamMemberId(int famMemberId) {
		this.famMemberId = famMemberId;
	}
}
