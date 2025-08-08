package pd.juvenilewarrant;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import pd.codetable.Code;

/**
 * @roseuid 467FBBD803BC
 */
public class JuvenileOffenderTrackingComplainant extends PersistentObject
{
	private String age;
	private String aptNum;
	private String city;
	private String complainantType;
	private String daLogNum;
	private Date dateOfBirth;
	private String driversLicenseNum;
	private String employer;
	private String name;
	private int name2;
	private String occupation;
	private String otherAptNum;
	private String otherCity;
	private String otherIdNumbers;
	private String otherPhone;
	private String otherStreetName;
	private String otherStreetNumber;
	private String otherZip;
	
	private String phone;
	private String relationshipToJuvenile;
	private String sequenceNum;
	private String socialSecurityNum;
	private String stateComplainantInd;
	private String streetName;
	private String streetNum;
	private String transactionNum;
	private String zip;
	private String otherInd;
	/**
	 * Properties for driversLicenseState
	 */
	private Code driversLicenseState = null;
	/**
	 * Properties for otherState
	 */
	private Code otherState = null;
	/**
	 * Properties for state
	 */
	private Code state = null;
	private String driversLicenseStateId;
	private String otherStateId;
	private String stateId;

	/**
	 * @roseuid 467FBBD803BC
	 */
	public JuvenileOffenderTrackingComplainant()
	{
	}

	/**
	 * @return Returns the age.
	 */
	public String getAge()
	{
		fetch();
		return age;
	}

	/**
	 * @param age The age to set.
	 */
	public void setAge(String age)
	{
		if (this.age == null || !this.age.equals(age))
		{
			markModified();
		}
		this.age = age;
	}

	/**
	 * @return Returns the aptNum.
	 */
	public String getAptNum()
	{
		fetch();
		return aptNum;
	}

	/**
	 * @param aptNum The aptNum to set.
	 */
	public void setAptNum(String aptNum)
	{
		if (this.aptNum == null || !this.aptNum.equals(aptNum))
		{
			markModified();
		}
		this.aptNum = aptNum;
	}

	/**
	 * @return Returns the city.
	 */
	public String getCity()
	{
		fetch();
		return city;
	}

	/**
	 * @param city The city to set.
	 */
	public void setCity(String city)
	{
		if (this.city == null || !this.city.equals(city))
		{
			markModified();
		}
		this.city = city;
	}

	/**
	 * @return Returns the complainantType.
	 */
	public String getComplainantType()
	{
		fetch();
		return complainantType;
	}

	/**
	 * @param complainantType The complainantType to set.
	 */
	public void setComplainantType(String complainantType)
	{
		if (this.complainantType == null || !this.complainantType.equals(complainantType))
		{
			markModified();
		}
		this.complainantType = complainantType;
	}

	/**
	 * @return Returns the daLogNum.
	 */
	public String getDaLogNum()
	{
		fetch();
		return daLogNum;
	}

	/**
	 * @param daLogNum The daLogNum to set.
	 */
	public void setDaLogNum(String daLogNum)
	{
		if (this.daLogNum == null || !this.daLogNum.equals(daLogNum))
		{
			markModified();
		}
		this.daLogNum = daLogNum;
	}

	/**
	 * @return Returns the dateOfBirth.
	 */
	public Date getDateOfBirth()
	{
		fetch();
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth The dateOfBirth to set.
	 */
	public void setDateOfBirth(Date dateOfBirth)
	{
		if (this.dateOfBirth == null || !this.dateOfBirth.equals(dateOfBirth))
		{
			markModified();
		}
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return Returns the driversLicenseNum.
	 */
	public String getDriversLicenseNum()
	{
		fetch();
		return driversLicenseNum;
	}

	/**
	 * @param driversLicenseNum The driversLicenseNum to set.
	 */
	public void setDriversLicenseNum(String driversLicenseNum)
	{
		if (this.driversLicenseNum == null || !this.driversLicenseNum.equals(driversLicenseNum))
		{
			markModified();
		}
		this.driversLicenseNum = driversLicenseNum;
	}

	/**
	 * @return Returns the driversLicenseState.
	 */
	public Code getDriversLicenseState()
	{
		fetch();
		initDriversLicenseState();
		return driversLicenseState;
	}

	/**
	 * @return Returns the employer.
	 */
	public String getEmployer()
	{
		fetch();
		return employer;
	}

	/**
	 * @param employer The employer to set.
	 */
	public void setEmployer(String employer)
	{
		if (this.employer == null || !this.employer.equals(employer))
		{
			markModified();
		}
		this.employer = employer;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName()
	{
		fetch();
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name)
	{
		if (this.name == null || !this.name.equals(name))
		{
			markModified();
		}
		this.name = name;
	}

	/**
	 * @return Returns the name2.
	 */
	public int getName2()
	{
		fetch();
		return name2;
	}

	/**
	 * @param name2 The name2 to set.
	 */
	public void setName2(int name2)
	{
		if (this.name2 != name2)
		{
			markModified();
		}
		this.name2 = name2;
	}

	/**
	 * @return Returns the occupation.
	 */
	public String getOccupation()
	{
		fetch();
		return occupation;
	}

	/**
	 * @param occupation The occupation to set.
	 */
	public void setOccupation(String occupation)
	{
		if (this.occupation == null || !this.occupation.equals(occupation))
		{
			markModified();
		}
		this.occupation = occupation;
	}

	/**
	 * @return Returns the otherAptNum.
	 */
	public String getOtherAptNum()
	{
		fetch();
		return otherAptNum;
	}

	/**
	 * @param otherAptNum The otherAptNum to set.
	 */
	public void setOtherAptNum(String otherAptNum)
	{
		if (this.otherAptNum == null || !this.otherAptNum.equals(otherAptNum))
		{
			markModified();
		}
		this.otherAptNum = otherAptNum;
	}

	/**
	 * @return Returns the otherCity.
	 */
	public String getOtherCity()
	{
		fetch();
		return otherCity;
	}

	/**
	 * @param otherCity The otherCity to set.
	 */
	public void setOtherCity(String otherCity)
	{
		if (this.otherCity == null || !this.otherCity.equals(otherCity))
		{
			markModified();
		}
		this.otherCity = otherCity;
	}

	/**
	 * @return Returns the otherIdNumbers.
	 */
	public String getOtherIdNumbers()
	{
		fetch();
		return otherIdNumbers;
	}

	/**
	 * @param otherIdNumbers The otherIdNumbers to set.
	 */
	public void setOtherIdNumbers(String otherIdNumbers)
	{
		if (this.otherIdNumbers == null || !this.otherIdNumbers.equals(otherIdNumbers))
		{
			markModified();
		}
		this.otherIdNumbers = otherIdNumbers;
	}

	/**
	 * @return Returns the otherPhone.
	 */
	public String getOtherPhone()
	{
		fetch();
		return otherPhone;
	}

	/**
	 * @param otherPhone The otherPhone to set.
	 */
	public void setOtherPhone(String otherPhone)
	{
		if (this.otherPhone == null || !this.otherPhone.equals(otherPhone))
		{
			markModified();
		}
		this.otherPhone = otherPhone;
	}

	/**
	 * @return Returns the otherState.
	 */
	public Code getOtherState()
	{
		fetch();
		initOtherState();
		return otherState;
	}

	/**
	 * @return Returns the otherStreetName.
	 */
	public String getOtherStreetName()
	{
		fetch();
		return otherStreetName;
	}

	/**
	 * @param otherStreetName The otherStreetName to set.
	 */
	public void setOtherStreetName(String otherStreetName)
	{
		if (this.otherStreetName == null || !this.otherStreetName.equals(otherStreetName))
		{
			markModified();
		}
		this.otherStreetName = otherStreetName;
	}

	/**
	 * @return Returns the otherStreetNumber.
	 */
	public String getOtherStreetNumber()
	{
		fetch();
		return otherStreetNumber;
	}

	/**
	 * @param otherStreetNumber The otherStreetNumber to set.
	 */
	public void setOtherStreetNumber(String otherStreetNumber)
	{
		if (this.otherStreetNumber == null || !this.otherStreetNumber.equals(otherStreetNumber))
		{
			markModified();
		}
		this.otherStreetNumber = otherStreetNumber;
	}

	/**
	 * @return Returns the otherZip.
	 */
	public String getOtherZip()
	{
		fetch();
		return otherZip;
	}

	/**
	 * @param otherZip The otherZip to set.
	 */
	public void setOtherZip(String otherZip)
	{
		if (this.otherZip == null || !this.otherZip.equals(otherZip))
		{
			markModified();
		}
		this.otherZip = otherZip;
	}

	/**
	 * @return Returns the phone.
	 */
	public String getPhone()
	{
		fetch();
		return phone;
	}

	/**
	 * @param phone The phone to set.
	 */
	public void setPhone(String phone)
	{
		if (this.phone == null || !this.phone.equals(phone))
		{
			markModified();
		}
		this.phone = phone;
	}

	/**
	 * @return Returns the relationshipToJuvenile.
	 */
	public String getRelationshipToJuvenile()
	{
		fetch();
		return relationshipToJuvenile;
	}

	/**
	 * @param relationshipToJuvenile The relationshipToJuvenile to set.
	 */
	public void setRelationshipToJuvenile(String relationshipToJuvenile)
	{
		if (this.relationshipToJuvenile == null || !this.relationshipToJuvenile.equals(relationshipToJuvenile))
		{
			markModified();
		}
		this.relationshipToJuvenile = relationshipToJuvenile;
	}

	/**
	 * @return Returns the sequenceNum.
	 */
	public String getSequenceNum()
	{
		fetch();
		return sequenceNum;
	}

	/**
	 * @param sequenceNum The sequenceNum to set.
	 */
	public void setSequenceNum(String sequenceNum)
	{
		if (this.sequenceNum == null || !this.sequenceNum.equals(sequenceNum))
		{
			markModified();
		}
		this.sequenceNum = sequenceNum;
	}

	/**
	 * @return Returns the socialSecurityNum.
	 */
	public String getSocialSecurityNum()
	{
		fetch();
		return socialSecurityNum;
	}

	/**
	 * @param socialSecurityNum The socialSecurityNum to set.
	 */
	public void setSocialSecurityNum(String socialSecurityNum)
	{
		if (this.socialSecurityNum == null || !this.socialSecurityNum.equals(socialSecurityNum))
		{
			markModified();
		}
		this.socialSecurityNum = socialSecurityNum;
	}

	/**
	 * @return Returns the state.
	 */
	public Code getState()
	{
		fetch();
		initState();
		return state;
	}

	/**
	 * @return Returns the stateComplainantInd.
	 */
	public String getStateComplainantInd()
	{
		fetch();
		return stateComplainantInd;
	}

	/**
	 * @param stateComplainantInd The stateComplainantInd to set.
	 */
	public void setStateComplainantInd(String stateComplainantInd)
	{
		if (this.stateComplainantInd == null || !this.stateComplainantInd.equals(stateComplainantInd))
		{
			markModified();
		}
		this.stateComplainantInd = stateComplainantInd;
	}

	/**
	 * @return Returns the streetName.
	 */
	public String getStreetName()
	{
		fetch();
		return streetName;
	}

	/**
	 * @param streetName The streetName to set.
	 */
	public void setStreetName(String streetName)
	{
		if (this.streetName == null || !this.streetName.equals(streetName))
		{
			markModified();
		}
		this.streetName = streetName;
	}

	/**
	 * @return Returns the streetNum.
	 */
	public String getStreetNum()
	{
		fetch();
		return streetNum;
	}

	/**
	 * @param streetNum The streetNum to set.
	 */
	public void setStreetNum(String streetNum)
	{
		if (this.streetNum == null || !this.streetNum.equals(streetNum))
		{
			markModified();
		}
		this.streetNum = streetNum;
	}

	/**
	 * @return Returns the transactionNum.
	 */
	public String getTransactionNum()
	{
		fetch();
		return transactionNum;
	}

	/**
	 * @param transactionNum The transactionNum to set.
	 */
	public void setTransactionNum(String transactionNum)
	{
		if (this.transactionNum == null || !this.transactionNum.equals(transactionNum))
		{
			markModified();
		}
		this.transactionNum = transactionNum;
	}

	/**
	 * @return Returns the zip.
	 */
	public String getZip()
	{
		fetch();
		return zip;
	}

	/**
	 * @param zip The zip to set.
	 */
	public void setZip(String zip)
	{
		if (this.zip == null || !this.zip.equals(zip))
		{
			markModified();
		}
		this.zip = zip;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setDriversLicenseStateId(String driversLicenseStateId)
	{
		if (this.driversLicenseStateId == null || !this.driversLicenseStateId.equals(driversLicenseStateId))
		{
			markModified();
		}
		driversLicenseState = null;
		this.driversLicenseStateId = driversLicenseStateId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getDriversLicenseStateId()
	{
		fetch();
		return driversLicenseStateId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initDriversLicenseState()
	{
		if (driversLicenseState == null)
		{
			driversLicenseState = (Code) new mojo.km.persistence.Reference(driversLicenseStateId, Code.class, PDCodeTableConstants.STATE_ABBR)
            .getObject();
		}
	}

	/**
	 * set the type reference for class member driversLicenseState
	 */
	public void setDriversLicenseState(Code driversLicenseState)
	{
		if (this.driversLicenseState == null || !this.driversLicenseState.equals(driversLicenseState))
		{
			markModified();
		}
		if (driversLicenseState.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(driversLicenseState);
		}
		setDriversLicenseStateId("" + driversLicenseState.getOID());
		this.driversLicenseState = (Code) new mojo.km.persistence.Reference(driversLicenseState)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setOtherStateId(String otherStateId)
	{
		if (this.otherStateId == null || !this.otherStateId.equals(otherStateId))
		{
			markModified();
		}
		otherState = null;
		this.otherStateId = otherStateId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getOtherStateId()
	{
		fetch();
		return otherStateId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initOtherState()
	{
		if (otherState == null)
		{
			otherState = (Code) new mojo.km.persistence.Reference(otherStateId, Code.class, PDCodeTableConstants.STATE_ABBR)
            .getObject();
		}
	}

	/**
	 * set the type reference for class member otherState
	 */
	public void setOtherState(Code otherState)
	{
		if (this.otherState == null || !this.otherState.equals(otherState))
		{
			markModified();
		}
		if (otherState.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(otherState);
		}
		setOtherStateId("" + otherState.getOID());
		this.otherState = (Code) new mojo.km.persistence.Reference(otherState).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setStateId(String stateId)
	{
		if (this.stateId == null || !this.stateId.equals(stateId))
		{
			markModified();
		}
		state = null;
		this.stateId = stateId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getStateId()
	{
		fetch();
		return stateId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initState()
	{
		if (state == null)
		{
			state = (Code) new mojo.km.persistence.Reference(stateId, Code.class, PDCodeTableConstants.STATE_ABBR)
            .getObject();
		}
	}

	/**
	 * set the type reference for class member state
	 */
	public void setState(Code state)
	{
		if (this.state == null || !this.state.equals(state))
		{
			markModified();
		}
		if (state.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(state);
		}
		setStateId("" + state.getOID());
		this.state = (Code) new mojo.km.persistence.Reference(state).getObject();
	}

	/**
	 * @return Iterator JuvenileOffenderTrackingComplainant
	 */
	static public Iterator findAllByDaLogNum(String daLogNum)
	{
		return new Home().findAll("daLogNum", daLogNum, JuvenileOffenderTrackingComplainant.class);
	}

	/**
	 * @return Iterator JuvenileOffenderTrackingComplainant
	 * @param event
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileOffenderTrackingComplainant.class);
	}

	/**
	 * 
	 * @return Returns the otherInd.
	 */
	public String getOtherInd()
	{
		fetch();
		return otherInd;
	}

	/**
	 * 
	 * @param otherInd The otherInd to set.
	 */
	public void setOtherInd(String otherInd)
	{
		if (this.otherInd == null || !this.otherInd.equals(otherInd))
		{
			markModified();
		}
		this.otherInd = otherInd;
	}
}
