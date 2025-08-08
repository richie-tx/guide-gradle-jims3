/*
 * Created on Oct 17, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import mojo.km.messaging.RequestEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveFamilyMemberFinancialEvent extends RequestEvent
{

	private int 	constellationMemberId;
//	private String  jobTitle;
//	private String  placeOfEmpoyment;
//	private double  annualNetIncome;
	private double  otherAdultIncome;
	private double ssi;
	private double  childSupportPaid;
	private double  childSupportReceived;
	private String notes="";
	private double  tanfAfdc;
	private double  foodStamps;
	private double  rentExpenses;
	private double  utilitiesExpenses;
	private double  groceryExpenses;
	private double  lifeInsurancePremium;
	private double  medicalExpenses;
	private double  schoolExpenses;
//	private double  totalExpenses;
	
	private double  propertyValue;
	private double  intangibleValue;
	private double  savings;
	private double  otherIncome;
	
	private int numberLivingInHome;
	private int numberOfDependents;
	private int numberInFamily;
	
	private String  constellationNum;
	private String  memberNum;
	
	
	
	private String  childSupportPayorFirstName;
	private String  childSupportPayorMiddleName;
	private String  childSupportPayorLastName;

	
	
	
	
	
	
	

	/**
	 * 
	 */
	public SaveFamilyMemberFinancialEvent()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return
	 */
//	public double getAnnualNetIncome()
//	{
//		return annualNetIncome;
//	}

	/**
	 * @return
	 */
	public String getChildSupportPayorFirstName()
	{
		return childSupportPayorFirstName;
	}

	/**
	 * @return
	 */
	public String getChildSupportPayorLastName()
	{
		return childSupportPayorLastName;
	}

	/**
	 * @return
	 */
	public String getChildSupportPayorMiddleName()
	{
		return childSupportPayorMiddleName;
	}

	/**
	 * @return
	 */
	public int getConstellationMemberId()
	{
		return constellationMemberId;
	}

	/**
	 * @return
	 */
	public double getFoodStamps()
	{
		return foodStamps;
	}

	/**
	 * @return
	 */
	public double getGroceryExpenses()
	{
		return groceryExpenses;
	}

	/**
	 * @return
	 */
	public double getIntangibleValue()
	{
		return intangibleValue;
	}

	/**
	 * @return
	 */
//	public String getJobTitle()
//	{
//		return jobTitle;
//	}

	/**
	 * @return
	 */
	public double getLifeInsurancePremium()
	{
		return lifeInsurancePremium;
	}

	/**
	 * @return
	 */
	public double getMedicalExpenses()
	{
		return medicalExpenses;
	}

	/**
	 * @return
	 */
	public int getNumberLivingInHome()
	{
		return numberLivingInHome;
	}

	/**
	 * @return
	 */
	public double getOtherIncome()
	{
		return otherIncome;
	}

	/**
	 * @return
	 */
//	public String getPlaceOfEmpoyment()
//	{
//		return placeOfEmpoyment;
//	}

	/**
	 * @return
	 */
	public double getPropertyValue()
	{
		return propertyValue;
	}

	/**
	 * @return
	 */
	public double getRentExpenses()
	{
		return rentExpenses;
	}

	/**
	 * @return
	 */
	public double getSavings()
	{
		return savings;
	}

	/**
	 * @return
	 */
	public double getSchoolExpenses()
	{
		return schoolExpenses;
	}

	/**
	 * @return
	 */
	public double getTanfAfdc()
	{
		return tanfAfdc;
	}

	/**
	 * @return
	 */
/*	public double getTotalExpenses()
	{
		return totalExpenses;
	}
*/
	/**
	 * @return
	 */
	public double getUtilitiesExpenses()
	{
		return utilitiesExpenses;
	}

	/**
	 * @param d
	 */
//	public void setAnnualNetIncome(double d)
//	{
//		annualNetIncome = d;
//	}

	/**
	 * @param string
	 */
	public void setChildSupportPayorFirstName(String string)
	{
		childSupportPayorFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setChildSupportPayorLastName(String string)
	{
		childSupportPayorLastName = string;
	}

	/**
	 * @param string
	 */
	public void setChildSupportPayorMiddleName(String string)
	{
		childSupportPayorMiddleName = string;
	}

	/**
	 * @param i
	 */
	public void setConstellationMemberId(int i)
	{
		constellationMemberId = i;
	}

	/**
	 * @param b
	 */
	public void setFoodStamps(double d)
	{
		foodStamps = d;
	}

	/**
	 * @param d
	 */
	public void setGroceryExpenses(double d)
	{
		groceryExpenses = d;
	}

	/**
	 * @param d
	 */
	public void setIntangibleValue(double d)
	{
		intangibleValue = d;
	}

	/**
	 * @param string
	 */
//	public void setJobTitle(String string)
//	{
//		jobTitle = string;
//	}

	/**
	 * @param d
	 */
	public void setLifeInsurancePremium(double d)
	{
		lifeInsurancePremium = d;
	}

	/**
	 * @param d
	 */
	public void setMedicalExpenses(double d)
	{
		medicalExpenses = d;
	}

	/**
	 * @param i
	 */
	public void setNumberLivingInHome(int i)
	{
		numberLivingInHome = i;
	}

	/**
	 * @param d
	 */
	public void setOtherIncome(double d)
	{
		otherIncome = d;
	}

	/**
	 * @param string
	 */
//	public void setPlaceOfEmpoyment(String string)
//	{
//		placeOfEmpoyment = string;
//	}

	/**
	 * @param d
	 */
	public void setPropertyValue(double d)
	{
		propertyValue = d;
	}

	/**
	 * @param d
	 */
	public void setRentExpenses(double d)
	{
		rentExpenses = d;
	}

	/**
	 * @param d
	 */
	public void setSavings(double d)
	{
		savings = d;
	}

	/**
	 * @param d
	 */
	public void setSchoolExpenses(double d)
	{
		schoolExpenses = d;
	}

	/**
	 * @param d
	 */
	public void setTanfAfdc(double d)
	{
		tanfAfdc = d;
	}

	/**
	 * @param d
	 */
/*	public void setTotalExpenses(double d)
	{
		totalExpenses = d;
	}
*/
	/**
	 * @param d
	 */
	public void setUtilitiesExpenses(double d)
	{
		utilitiesExpenses = d;
	}

	/**
	 * @return
	 */
	public int getNumberOfDependents()
	{
		return numberOfDependents;
	}

	/**
	 * @param i
	 */
	public void setNumberOfDependents(int i)
	{
		numberOfDependents = i;
	}

	/**
	 * @return
	 */
	public int getNumberInFamily()
	{
		return numberInFamily;
	}

	/**
	 * @param i
	 */
	public void setNumberInFamily(int i)
	{
		numberInFamily = i;
	}

	/**
	 * @return
	 */
	public String getConstellationNum()
	{
		return constellationNum;
	}

	/**
	 * @return
	 */
	public String getMemberNum()
	{
		return memberNum;
	}

	/**
	 * @param string
	 */
	public void setConstellationNum(String string)
	{
		constellationNum = string;
	}

	/**
	 * @param string
	 */
	public void setMemberNum(String string)
	{
		memberNum = string;
	}

	/**
	 * @return
	 */
	public double getChildSupportPaid()
	{
		return childSupportPaid;
	}

	/**
	 * @return
	 */
	public double getChildSupportReceived()
	{
		return childSupportReceived;
	}

	/**
	 * @return
	 */
	public String getNotes()
	{
		return notes;
	}

/**
 * @return
 */
public double getOtherAdultIncome()
{
	return otherAdultIncome;
}

	/**
	 * @param d
	 */
	public void setChildSupportPaid(double d)
	{
		childSupportPaid = d;
	}

	/**
	 * @param d
	 */
	public void setChildSupportReceived(double d)
	{
		childSupportReceived = d;
	}

	/**
	 * @param string
	 */
	public void setNotes(String string)
	{
		notes = string;
	}

	/**
	 * @param d
	 */
	public void setOtherAdultIncome(double d)
	{
		otherAdultIncome = d;
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
