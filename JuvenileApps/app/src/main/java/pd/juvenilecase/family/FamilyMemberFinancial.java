package pd.juvenilecase.family;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;


import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @author athorat
To change the template for this generated type comment go to
Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
*/
public class FamilyMemberFinancial extends PersistentObject
{
	/**
	* Properties for familyMember
	* @detailerDoNotGenerate false
	* @referencedType pd.juvenilecase.family.FamilyConstellationMember
	*/
	private FamilyConstellationMember familyConstellationMember = null;
	private String childSupportPayorMiddleName;
	private double tanfAfdc;
	private double medicalExpenses;
//	private String jobTitle;
	private double  otherAdultIncome;
		private double  childSupportPaid;
		private double  childSupportReceived;
		private String notes="";
	private double groceryExpenses;
	private int numberLivingInHome;
	private double utilitiesExpenses;
	private double propertyValue;
	private double otherIncome;
	private double rentExpenses;
	private double ssi;
//	private double totalExpenses;
//	private String placeOfEmpoyment;
	private String childSupportPayorLastName;
	private String childSupportPayorFirstName;
	private int numberOfDependents;
	private double foodStamps;
	private double lifeInsurancePremium;
	private double intangibleValue;
	private double schoolExpenses;
	private String familyConstellationMemberId;
//	private double annualNetIncome;
	private double savings;
	private Date entryDate;
	private Timestamp entryDateTimestamp;
	private int consRelationId;
	private int familyNum = 0;

	
	/**
	* @return 
	*/
//	public double getAnnualNetIncome()
//	{
//		fetch();
//		return annualNetIncome;
//	}
	/**
	* @return 
	*/
	public String getChildSupportPayorFirstName()
	{
		fetch();
		return childSupportPayorFirstName;
	}
	/**
	* @return 
	*/
	public String getChildSupportPayorLastName()
	{
		fetch();
		return childSupportPayorLastName;
	}
	/**
	* @return 
	*/
	public String getChildSupportPayorMiddleName()
	{
		fetch();
		return childSupportPayorMiddleName;
	}
	
	/**
	* @return 
	*/
	public double getFoodStamps()
	{
		fetch();
		return foodStamps;
	}
	
	/**
	* @return 
	*/
	public double getGroceryExpenses()
	{
		fetch();
		return groceryExpenses;
	}
	/**
	* @return 
	*/
	public double getIntangibleValue()
	{
		fetch();
		return intangibleValue;
	}
	/**
	* @return 
	*/
//	public String getJobTitle()
//	{
//		fetch();
//		return jobTitle;
//	}
	/**
	* @return 
	*/
	public double getLifeInsurancePremium()
	{
		fetch();
		return lifeInsurancePremium;
	}
	/**
	* @return 
	*/
	public double getMedicalExpenses()
	{
		fetch();
		return medicalExpenses;
	}
	/**
	* @return 
	*/
	public int getNumberLivingInHome()
	{
		fetch();
		return numberLivingInHome;
	}
	/**
	* @return 
	*/
	public double getOtherIncome()
	{
		fetch();
		return otherIncome;
	}
	/**
	* @return 
	*/
//	public String getPlaceOfEmpoyment()
//	{
//		fetch();
//		return placeOfEmpoyment;
//	}
	/**
	* @return 
	*/
	public double getPropertyValue()
	{
		fetch();
		return propertyValue;
	}
	/**
	* @return 
	*/
	public double getRentExpenses()
	{
		fetch();
		return rentExpenses;
	}
	/**
	* @return 
	*/
	public double getSavings()
	{
		fetch();
		return savings;
	}
	/**
	* @return 
	*/
	public double getSchoolExpenses()
	{
		fetch();
		return schoolExpenses;
	}
	

		
	/**
	* @return 
	*/
	public double getTanfAfdc()
	{
		fetch();
		return tanfAfdc;
	}
	/**
	* @return 
	*/
/*	public double getTotalExpenses()
	{
		fetch();
		return totalExpenses;
	}
*/
	
	/**
	* @return 
	*/
	public double getTotalExpenses()
	{
		double totalExpenses = utilitiesExpenses + lifeInsurancePremium + schoolExpenses + rentExpenses + groceryExpenses + medicalExpenses + childSupportPaid;
		return totalExpenses;
	}
	/**
	* @return 
	*/
	public double getUtilitiesExpenses()
	{
		fetch();
		return utilitiesExpenses;
	}
	/**
	* @param d
	*/
//	public void setAnnualNetIncome(double d)
//	{
//		if (this.annualNetIncome != d)
//		{
//			markModified();
//		}
//		annualNetIncome = d;
//	}
	/**
	* @param string
	*/
	public void setChildSupportPayorFirstName(String string)
	{
		if (this.childSupportPayorFirstName == null || !this.childSupportPayorFirstName.equals(string))
		{
			markModified();
		}
		childSupportPayorFirstName = string;
	}
	/**
	* @param string
	*/
	public void setChildSupportPayorLastName(String string)
	{
		if (this.childSupportPayorLastName == null || !this.childSupportPayorLastName.equals(string))
		{
			markModified();
		}
		childSupportPayorLastName = string;
	}
	/**
	* @param string
	*/
	public void setChildSupportPayorMiddleName(String string)
	{
		if (this.childSupportPayorMiddleName == null || !this.childSupportPayorMiddleName.equals(string))
		{
			markModified();
		}
		childSupportPayorMiddleName = string;
	}
	/**
	* @param amount
	*/
	public void setFoodStamps(double amount)
	{
		if (this.foodStamps != amount)
		{
			markModified();
		}
		foodStamps = amount;
	}
	
	/**
	* @param d
	*/
	public void setGroceryExpenses(double d)
	{
		if (this.groceryExpenses != d)
		{
			markModified();
		}
		groceryExpenses = d;
	}
	/**
	* @param d
	*/
	public void setIntangibleValue(double d)
	{
		if (this.intangibleValue != d)
		{
			markModified();
		}
		intangibleValue = d;
	}
	/**
	* @param string
	*/
//	public void setJobTitle(String string)
//	{
//		if (this.jobTitle == null || !this.jobTitle.equals(string))
//		{
//			markModified();
//		}
//		jobTitle = string;
//	}
	/**
	* @param d
	*/
	public void setLifeInsurancePremium(double d)
	{
		if (this.lifeInsurancePremium != d)
		{
			markModified();
		}
		lifeInsurancePremium = d;
	}
	/**
	* @param d
	*/
	public void setMedicalExpenses(double d)
	{
		if (this.medicalExpenses != d)
		{
			markModified();
		}
		medicalExpenses = d;
	}
	/**
	* @param i
	*/
	public void setNumberLivingInHome(int i)
	{
		if (this.numberLivingInHome != i)
		{
			markModified();
		}
		numberLivingInHome = i;
	}
	/**
	* @param d
	*/
	public void setOtherIncome(double d)
	{
		if (this.otherIncome != d)
		{
			markModified();
		}
		otherIncome = d;
	}
	/**
	* @param string
	*/
//	public void setPlaceOfEmpoyment(String string)
//	{
//		if (this.placeOfEmpoyment == null || !this.placeOfEmpoyment.equals(string))
//		{
//			markModified();
//		}
//		placeOfEmpoyment = string;
//	}
	/**
	* @param d
	*/
	public void setPropertyValue(double d)
	{
		if (this.propertyValue != d)
		{
			markModified();
		}
		propertyValue = d;
	}
	/**
	* @param d
	*/
	public void setRentExpenses(double d)
	{
		if (this.rentExpenses != d)
		{
			markModified();
		}
		rentExpenses = d;
	}
	/**
	* @param d
	*/
	public void setSavings(double d)
	{
		if (this.savings != d)
		{
			markModified();
		}
		savings = d;
	}
	/**
	* @param d
	*/
	public void setSchoolExpenses(double d)
	{
		if (this.schoolExpenses != d)
		{
			markModified();
		}
		schoolExpenses = d;
	}
	/**
	* @param d
	*/
	public void setTanfAfdc(double d)
	{
		if (this.tanfAfdc != d)
		{
			markModified();
		}
		tanfAfdc = d;
	}
	/**
	* @param d
	*/
/*	public void setTotalExpenses(double d)
	{
		if (this.totalExpenses != d)
		{
			markModified();
		}
		totalExpenses = d;
	}
*/	
	/**
	* @param d
	*/
	public void setUtilitiesExpenses(double d)
	{
		if (this.utilitiesExpenses != d)
		{
			markModified();
		}
		utilitiesExpenses = d;
	}
	/**
	* Finds all FamilyMemberFinancial by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		Iterator familyMemberFinancials = home.findAll(attributeName, attributeValue, FamilyMemberFinancial.class);
		return familyMemberFinancials;
	}
	/**
	* Set the reference value to class :: pd.juvenilecase.family.FamilyConstellationMember
	*/
	public void setFamilyConstellationMemberId(String familyConstellationMemberId)
	{
		if (this.familyConstellationMemberId == null
			|| !this.familyConstellationMemberId.equals(familyConstellationMemberId))
		{
			markModified();
		}
		familyConstellationMember = null;
		this.familyConstellationMemberId = familyConstellationMemberId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.family.FamilyConstellationMember
	*/
	public String getFamilyConstellationMemberId()
	{
		fetch();
		return familyConstellationMemberId;
	}
	/**
	* Initialize class relationship to class pd.juvenilecase.family.FamilyConstellationMember
	*/
	private void initFamilyConstellationMember()
	{
		if (familyConstellationMember == null)
		{
			familyConstellationMember =
				(FamilyConstellationMember) new mojo
					.km
					.persistence
					.Reference(familyConstellationMemberId, FamilyConstellationMember.class)
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.juvenilecase.family.FamilyConstellationMember
	*/
	public FamilyConstellationMember getFamilyConstellationMember()
	{
		fetch();
		initFamilyConstellationMember();
		return familyConstellationMember;
	}
	/**
	* set the type reference for class member familyConstellationMember
	*/
	public void setFamilyConstellationMember(FamilyConstellationMember familyConstellationMember)
	{
		if (this.familyConstellationMember == null
			|| !this.familyConstellationMember.equals(familyConstellationMember))
		{
			markModified();
		}
		if (familyConstellationMember.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(familyConstellationMember);
		}
		setFamilyConstellationMemberId("" + familyConstellationMember.getOID());
		this.familyConstellationMember =
			(FamilyConstellationMember) new mojo
				.km
				.persistence
				.Reference(familyConstellationMember)
				.getObject();
	}
	/**
	* @return 
	*/
	public int getNumberOfDependents()
	{
		fetch();
		return numberOfDependents;
	}
	/**
	* @param i
	*/
	public void setNumberOfDependents(int i)
	{
		if (this.numberOfDependents != i)
		{
			markModified();
		}
		numberOfDependents = i;
	}
	/**
	 * @param getConstelltionMemberFinacialEvent
	 * @return
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, FamilyMemberFinancial.class);
	}
	/**
	 * @return
	 */
	public Date getEntryDate()
	{
		fetch();
		return entryDate;
	}
	
	/**
     *  
     * @param date The entry date.
     */
    public void setEntryDate(Date date) {
        if (this.entryDate == null || !this.entryDate.equals(date)) {
        	markModified();
        }
        entryDate = date;
    }
    
	/**
	 * @param i
	 */
	public void setConsRelationId(int i)
	{
		if (this.consRelationId != i) {
			markModified();
		}
		this.consRelationId = i;
	}
	

	/**
	 * @param timestamp
	 */
	public void setEntryDateTimestamp(Timestamp timestamp)
	{
	
	   if (this.entryDateTimestamp == null || !this.entryDateTimestamp.equals(timestamp)) {
		   markModified();
	   }
	   entryDateTimestamp = timestamp;
	}
	
	/**
	 * @return
	 */
	public int getConsRelationId()
	{
		fetch();
		return consRelationId;
	}

	/**
	 * @return
	 */
	public Timestamp getEntryDateTimestamp()
	{
		fetch();
		return entryDateTimestamp;
	}
	/**
	 * @return
	 */
	public int getFamilyNum()
	{
		fetch();
		return familyNum;
	}

	/**
	 * @param i
	 */
	public void setFamilyNum(int i)
	{
		if (this.familyNum != i) {
			markModified();
		}
		this.familyNum = i;
	}

		/**
		 * @return
		 */
		public double getChildSupportPaid()
		{
			fetch();
			return childSupportPaid;
		}

		/**
		 * @return
		 */
		public double getChildSupportReceived()
		{
			fetch();
			return childSupportReceived;
		}

		/**
		 * @return
		 */
		public String getNotes()
		{
			fetch();
			return notes;
		}

/**
 * @return
 */
public double getOtherAdultIncome()
{
	fetch();
	return otherAdultIncome;
}

		/**
		 * @param d
		 */
		public void setChildSupportPaid(double d)
		{
			childSupportPaid = d;
				
			if (this.childSupportPaid != d)
			{
				markModified();
				childSupportPaid = d;
			}
		}

		/**
		 * @param d
		 */
		public void setChildSupportReceived(double d)
		{
			childSupportReceived = d;
				
						if (this.childSupportReceived != d)
						{
							markModified();
							childSupportReceived = d;
						}
		}

		/**
		 * @param string
		 */
		public void setNotes(String string)
		{
			notes = string;
			if (this.notes != string)
									{
										markModified();
										notes = string;
									}
		}

/**
 * @param d
 */
public void setOtherAdultIncome(double d)
{
	otherAdultIncome = d;
	if (this.otherAdultIncome != d)
							{
								markModified();
								otherAdultIncome = d;
							}
}

public double getSsi()
{
	fetch();
	return ssi;
}

public void setSsi(double d)
{
	ssi = d;
	if (this.ssi != d)
							{
								markModified();
								ssi = d;
							}
}

}
