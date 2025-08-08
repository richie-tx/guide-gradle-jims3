package messaging.interviewinfo.to;

import java.util.ArrayList;
import java.util.List;

import messaging.contact.to.PhoneNumberBean;

/**
 *
 */
public class FamilyInformationTO extends ExcludedTO
{
	private String familyConstellationMemberId = "";
	private String familyMemberId = "";
	private String guardian = "";
	private boolean guardianStatus = false; 
	private String guardianStatusAsString = "";
	private String inHomeAsString = "";
	private String guardianStatusAsStringYesOnly = "";
	private String inHomeAsStringYesOnly = "";
	private String deceasedAsString = "";
	private String incarceratedAsString = "";
	
	private String deceasedAsStringYesOnly = "";
	private String incarceratedAsStringYesOnly = "";
	
	private String primaryCareGiverAsStringYesOnly =""; //ER 11063 changes
	private boolean parentalRightsTerminated = true;
	private String parentalRightsTerminatedAsString = "";
	private String parentalRightsTerminatedAsStringYesOnly = "";
	
	private boolean memberOfCurrentConstellation = false;
	private String memberOfCurrentConstellationAsString = "";
	private String familyMemberName;
	private String relationship = "";
	private String address = "";
	private String phone = "";
	private double totalGross = 0;
	private double annualNetIncome = 0;
	private double TANFAssistance = 0;
	private double otherIncome = 0;
	private boolean primaryCareGiver = false; //ER 11063 changes
	private boolean inHome = false;
	private boolean deceased = false;
	private boolean incarcerated = false;
	
	private double rentExpenses;
	private double utilitiesExpenses;
	private double groceryExpenses;
	private double schoolExpenses;
	private double childSupportPaid;
	private double medicalExpenses;
	private double lifeInsurancePremium;
	private double propertyValue;
	private double intangibleValue;
	private double savings;
	private double childSupportReceived;
	private double foodStamps;
	private int numberLivingInHome;
	private boolean financialInfoSelected = false;
	
	private List employmentHistory = new ArrayList();		// of EmploymentHistoryTO
	
	/**
	 * @return Returns the address.
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address The address to set.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return Returns the annualNetIncome.
	 */
	public double getAnnualNetIncome() {
		return annualNetIncome;
	}
	/**
	 * @param annualNetIncome The annualNetIncome to set.
	 */
	public void setAnnualNetIncome(double annualNetIncome) {
		this.annualNetIncome = annualNetIncome;
	}
	
	/**
	 * @return Returns the guardian.
	 */
	public String getGuardian() {
		return guardian;
	}
	
	/**
	 * @return Returns the familyMemberName.
	 */
	public String getFamilyMemberName() {
		return familyMemberName;
	}
	
	/**
	 * @param guardian The guardian to set.
	 */
	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}
	/**
	 * @param guardian The guardian to set.
	 */
	public void setFamilyMemberName(String familyMemberName) {
		this.familyMemberName = familyMemberName;
	}
	/**
	 * @return Returns the monthlyTANFAssistance.
	 */
	public double getTANFAssistance() {
		return TANFAssistance;
	}
	/**
	 * @param monthlyTANFAssistance The monthlyTANFAssistance to set.
	 */
	public void setTANFAssistance(double TANFAssistance) {
		this.TANFAssistance = TANFAssistance;
	}
	/**
	 * @return Returns the otherIncome.
	 */
	public double getOtherIncome() {
		return otherIncome;
	}
	/**
	 * @param otherIncome The otherIncome to set.
	 */
	public void setOtherIncome(double otherIncome) {
		this.otherIncome = otherIncome;
	}
	/**
	 * @return Returns the phone.
	 */
	public String getPhone() {
		return phone;
	}
	
	public String getFormattedPhone() {
		PhoneNumberBean phoneNumberBean = new PhoneNumberBean(phone);
		return phoneNumberBean.getFormattedPhoneNumber();
	}
	/**
	 * @param phone The phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return Returns the relationship.
	 */
	public String getRelationship() {
		return relationship;
	}
	/**
	 * @param relationship The relationship to set.
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	/**
	 * @return Returns the totalGross.
	 */
	public double getTotalGross() {
		return totalGross;
	}
	/**
	 * @param totalGross The totalGross to set.
	 */
	public void setTotalGross(double totalGross) {
		this.totalGross = totalGross;
	}
	/**
	 * @return Returns the inHome.
	 */
	public boolean isInHome() {
		return inHome;
	}
	
	/**
	 * @param inHome The inHome to set.
	 */
	public void setInHome(boolean inHome) {
		this.inHome = inHome;
	}
	
	public String getInHomeAsString() {
		if ( !isMemberOfCurrentConstellation() )
		{
			return "";
		}
		return inHome ? "Yes" : "No";
	}
	public String getInHomeAsStringYesOnly() {
		if ( !isMemberOfCurrentConstellation() )
		{
			return "";
		}
		return inHome ? "Yes" : "";
	}
	
	/**
	 * @return Returns the inHome.
	 */
	public boolean isDeceased() {
		return deceased;
	}
	
	/**
	 * @param inHome The inHome to set.
	 */
	public void setDeceased(boolean deceased) {
		this.deceased = deceased;
	}
	
	public String getDeceasedAsString() {
		return deceased ? "Yes" : "No";
	}
	
	public String getDeceasedAsStringYesOnly() {
		return deceased ? "Yes" : "";
	}
	
	/**
	 * @return Returns the inHome.
	 */
	public boolean isIncarcerated() {
		return incarcerated;
	}
	
	/**
	 * @param inHome The inHome to set.
	 */
	public void setIncarcerated(boolean incarcerated) {
		this.incarcerated = incarcerated;
	}
	
	public String getIncarceratedAsString() {
		return incarcerated ? "Yes" : "No";
	}
	
	public String getIncarceratedAsStringYesOnly() {
		return incarcerated ? "Yes" : "";
	}
	
	/**
	 * @return Returns the childSupportPaid.
	 */
	public double getChildSupportPaid() {
		return childSupportPaid;
	}
	/**
	 * @param childSupportPaid The childSupportPaid to set.
	 */
	public void setChildSupportPaid(double childSupportPaid) {
		this.childSupportPaid = childSupportPaid;
	}
	/**
	 * @return Returns the childSupportReceived.
	 */
	public double getChildSupportReceived() {
		return childSupportReceived;
	}
	/**
	 * @param childSupportReceived The childSupportReceived to set.
	 */
	public void setChildSupportReceived(double childSupportReceived) {
		this.childSupportReceived = childSupportReceived;
	}
	/**
	 * @return Returns the foodStamps.
		Changes for 34452
	 */
	public double getFoodStamps() {
		return foodStamps;
	}
	
	/**
	 * @param foodStamps The foodStamps to set.
	 */
	public void setFoodStamps(double foodStamps) {
		this.foodStamps = foodStamps;
	}
	/**
	 * @return Returns the groceryExpenses.
	 */
	public double getGroceryExpenses() {
		return groceryExpenses;
	}
	/**
	 * @param groceryExpenses The groceryExpenses to set.
	 */
	public void setGroceryExpenses(double groceryExpenses) {
		this.groceryExpenses = groceryExpenses;
	}
	/**
	 * @return Returns the intangibleValue.
	 */
	public double getIntangibleValue() {
		return intangibleValue;
	}
	/**
	 * @param intangibleValue The intangibleValue to set.
	 */
	public void setIntangibleValue(double intangibleValue) {
		this.intangibleValue = intangibleValue;
	}
	/**
	 * @return Returns the lifeInsurancePremium.
	 */
	public double getLifeInsurancePremium() {
		return lifeInsurancePremium;
	}
	/**
	 * @param lifeInsurancePremium The lifeInsurancePremium to set.
	 */
	public void setLifeInsurancePremium(double lifeInsurancePremium) {
		this.lifeInsurancePremium = lifeInsurancePremium;
	}
	/**
	 * @return Returns the medicalExpenses.
	 */
	public double getMedicalExpenses() {
		return medicalExpenses;
	}
	/**
	 * @param medicalExpenses The medicalExpenses to set.
	 */
	public void setMedicalExpenses(double medicalExpenses) {
		this.medicalExpenses = medicalExpenses;
	}
	/**
	 * @return Returns the propertyValue.
	 */
	public double getPropertyValue() {
		return propertyValue;
	}
	/**
	 * @param propertyValue The propertyValue to set.
	 */
	public void setPropertyValue(double propertyValue) {
		this.propertyValue = propertyValue;
	}
	/**
	 * @return Returns the rentExpenses.
	 */
	public double getRentExpenses() {
		return rentExpenses;
	}
	/**
	 * @param rentExpenses The rentExpenses to set.
	 */
	public void setRentExpenses(double rentExpenses) {
		this.rentExpenses = rentExpenses;
	}
	/**
	 * @return Returns the savings.
	 */
	public double getSavings() {
		return savings;
	}
	/**
	 * @param savings The savings to set.
	 */
	public void setSavings(double savings) {
		this.savings = savings;
	}
	/**
	 * @return Returns the schoolExpenses.
	 */
	public double getSchoolExpenses() {
		return schoolExpenses;
	}
	/**
	 * @param schoolExpenses The schoolExpenses to set.
	 */
	public void setSchoolExpenses(double schoolExpenses) {
		this.schoolExpenses = schoolExpenses;
	}
	/**
	 * @return Returns the utilitiesExpenses.
	 */
	public double getUtilitiesExpenses() {
		return utilitiesExpenses;
	}
	/**
	 * @param utilitiesExpenses The utilitiesExpenses to set.
	 */
	public void setUtilitiesExpenses(double utilitiesExpenses) {
		this.utilitiesExpenses = utilitiesExpenses;
	}
	/**
	 * @return Returns the employmentHistory.
	 */
	public List getEmploymentHistory() {
		return employmentHistory;
	}
	/**
	 * @return Returns the numberLivingInHome.
	 */
	public int getNumberLivingInHome() {
		return numberLivingInHome;
	}
	/**
	 * @param numberLivingInHome The numberLivingInHome to set.
	 */
	public void setNumberLivingInHome(int numberLivingInHome) {
		this.numberLivingInHome = numberLivingInHome;
	}
	/**
	 * @return Returns the familyConstellationMemberId.
	 */
	public String getFamilyConstellationMemberId() {
		return familyConstellationMemberId;
	}
	/**
	 * @param familyConstellationMemberId The familyConstellationMemberId to set.
	 */
	public void setFamilyConstellationMemberId(
			String familyConstellationMemberId) {
		this.familyConstellationMemberId = familyConstellationMemberId;
	}
	/**
	 * @return Returns the financialInfoSelected.
	 */
	public boolean isFinancialInfoSelected() {
		return financialInfoSelected;
	}
	/**
	 * @param financialInfoSelected The financialInfoSelected to set.
	 */
	public void setFinancialInfoSelected(boolean financialInfoSelected) {
		this.financialInfoSelected = financialInfoSelected;
	}
	/**
	 * @param familyMemberId
	 */
	public void setFamilyMemberId(String familyMemberId) {
		this.familyMemberId = familyMemberId;
	}
	/**
	 * @return
	 */
	public String getFamilyMemberId() {
		return familyMemberId;
	}
	/**
	 * @param guardianStatus
	 */
	public void setGuardianStatus(boolean guardianStatus) {
		this.guardianStatus = guardianStatus;
	}
	/**
	 * @return
	 */
	public boolean isGuardianStatus() {
		return guardianStatus;
	}
	/**
	 * @return
	 */
	public String getGuardianStatusAsString() {
		if ( !isMemberOfCurrentConstellation() )
		{
			return "";
		}
		return guardianStatus ? "Yes" : "No";
	}
	/**
	 * @return
	 */
	public String getGuardianStatusAsStringYesOnly() {
		if ( !isMemberOfCurrentConstellation() )
		{
			return "";
		}
		return guardianStatus ? "Yes" : "";
	}
	/**
	 * @return
	 */
	public String getPrimaryCareGiverAsStringYesOnly() {
		if ( !isMemberOfCurrentConstellation() )
		{
			return "";
		}
		return primaryCareGiver ? "Yes" : "";
	}
	/**
	 * @param memberOfCurrentConstellation
	 */
	public void setMemberOfCurrentConstellation(boolean memberOfCurrentConstellation) {
		this.memberOfCurrentConstellation = memberOfCurrentConstellation;
	}
	/**
	 * @return
	 */
	public boolean isMemberOfCurrentConstellation() {
		return memberOfCurrentConstellation;
	}
	/**
	 * @return
	 */
	public String getMemberOfCurrentConstellationAsString() {
		return memberOfCurrentConstellation ? "Yes" : "No";
	}
	
	/**
	 * @param parentalRightsTerminated
	 */
	public void setParentalRightsTerminated(boolean parentalRightsTerminated) {
		this.parentalRightsTerminated = parentalRightsTerminated;
	}
	/**
	 * @return
	 */
	public boolean isParentalRightsTerminated() {
		return parentalRightsTerminated;
	}
	/**
	 * @return
	 */
	public String getParentalRightsTerminatdAsString() {
		if ( !isMemberOfCurrentConstellation() )
		{
			return "";
		}
		return parentalRightsTerminated ? "Yes" : "No";
	}
	/**
	 * @return
	 */
	public String getParentalRightsTerminatedAsStringYesOnly() {
		if ( !isMemberOfCurrentConstellation() )
		{
			return "";
		}
		return  parentalRightsTerminated ? "Yes" : "";
	}
	
	public boolean isPrimaryCareGiver() {
		return primaryCareGiver;
	}
	
	public void setPrimaryCareGiver(boolean primaryCareGiver) {
		this.primaryCareGiver = primaryCareGiver;
	}	
}
