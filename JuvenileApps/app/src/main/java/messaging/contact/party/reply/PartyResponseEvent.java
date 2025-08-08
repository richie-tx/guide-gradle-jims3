/*
 * Created on Oct 19, 2004
 */
package messaging.contact.party.reply;

import java.util.Collection;
import java.util.Date;

import messaging.contact.reply.ContactResponseEvent;
import messaging.domintf.contact.party.IParty;

/**
 * @author dnikolis
 */
public class PartyResponseEvent extends ContactResponseEvent implements IParty
{
	private String addressId;
	private String afisNum;
	private String age;
	private String alienRegistrationNum;
	private String barNum;
	private String buildId;
	private String capacityId;
	private String citizenshipId;
	private String connectionId;
	private String currentAddressStreetNum;
	private String currentAddressTypeId;
	private String currentAddressStreetName;
	private String currentAddressStreetName2;
	private String currentAddressAptNum;
	private String currentAddressCity;
	private String currentAddressStateId;
	private String currentAddressZipCode;
	private String currentNameInd;
	private Date dateOfBirth;
	private String descriptorSourceId;
	private String driversLicenseClassId;
	private String driversLicenseExpirationYear;
	private String driversLicenseNum;
	private String driversLicenseStateId;
	private String employerAddressId;
	private String employerName;
	private String employerPhoneNum;
	private String employerAddressStreetNum;
	private String employerAddressStreetName;
	private String employerAddressStreetName2;
	private String employerAddressCity;
	private String employerAddressStateId;
	private String employerAddressZipCode;
	private String employmentStatusId;
	private String ethnicityId;
	private String eyeColorId;
	private String fbiNum;
	private String fingerPrintedInd;
	private String hairColorId;
	private String height;
	private String idCardNum;
	private String idCardStateId;
	private String languageId;
	private String maritalStatusId;
	private String miscellaneousNum;
	private String miscellaneousNumTypeId;
	private String namePtr;
	private String nameSourceId;
	private String nameTypeId;
	private String nextOfKinFirstName;
	private String nextOfKinLastName;
	private String nextOfKinMiddleName;
	private String nextOfKinRelationshipId;
	private String occupation;
	private String OID;
	private String partyId;
	private String placeOfBirth;
	private String placeOfBirthStateId;
	private String raceId;
	private String scarsMarksId;
	private String sexId;
	private String sheriffOfficeNum;
	private String sidNum;
	private String skinToneId;
	private String spn;
	private String ssn;
	private String weight;
	private String name;
	private Collection scarsMarks;
	private Collection tattoos;
	private String nameSeqNum;
	/**
	 * @return
	 */
	public String getAddressId()
	{
		return addressId;
	}

	/**
	 * @return
	 */
	public String getAfisNum()
	{
		return afisNum;
	}

	/**
	 * @return
	 */
	public String getAge()
	{
		return age;
	}

	/**
	 * @return
	 */
	public String getAlienRegistrationNum()
	{
		return alienRegistrationNum;
	}

	/**
	 * @return
	 */
	public String getBarNum()
	{
		return barNum;
	}

	/**
	 * @return
	 */
	public String getBuildId()
	{
		return buildId;
	}

	/**
	 * @return
	 */
	public String getCapacityId()
	{
		return capacityId;
	}

	/**
	 * @return
	 */
	public String getCitizenshipId()
	{
		return citizenshipId;
	}

	/**
	 * @return
	 */
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	/**
	 * @return
	 */
	public String getDescriptorSourceId()
	{
		return descriptorSourceId;
	}

	/**
	 * @return
	 */
	public String getDriversLicenseClassId()
	{
		return driversLicenseClassId;
	}

	/**
	 * @return
	 */
	public String getDriversLicenseExpirationYear()
	{
		return driversLicenseExpirationYear;
	}

	/**
	 * @return
	 */
	public String getDriversLicenseNum()
	{
		return driversLicenseNum;
	}

	/**
	 * @return
	 */
	public String getDriversLicenseStateId()
	{
		return driversLicenseStateId;
	}

	/**
	 * @return
	 */
	public String getEmployerAddressId()
	{
		return employerAddressId;
	}

	/**
	 * @return
	 */
	public String getEmployerName()
	{
		return employerName;
	}

	/**
	 * @return
	 */
	public String getEmployerPhoneNum()
	{
		return employerPhoneNum;
	}

	/**
	 * @return
	 */
	public String getEmploymentStatusId()
	{
		return employmentStatusId;
	}

	/**
	 * @return
	 */
	public String getEthnicityId()
	{
		return ethnicityId;
	}

	/**
	 * @return
	 */
	public String getEyeColorId()
	{
		return eyeColorId;
	}

	/**
	 * @return
	 */
	public String getFbiNum()
	{
		return fbiNum;
	}

	/**
	 * @return
	 */
	public String getFingerPrintedInd()
	{
		return fingerPrintedInd;
	}

	/**
	 * @return
	 */
	public String getHairColorId()
	{
		return hairColorId;
	}

	/**
	 * @return
	 */
	public String getHeight()
	{
		return height;
	}

	/**
	 * @return
	 */
	public String getIdCardNum()
	{
		return idCardNum;
	}

	/**
	 * @return
	 */
	public String getIdCardStateId()
	{
		return idCardStateId;
	}

	/**
	 * @return
	 */
	public String getLanguageId()
	{
		return languageId;
	}

	/**
	 * @return
	 */
	public String getMaritalStatusId()
	{
		return maritalStatusId;
	}

	/**
	 * @return
	 */
	public String getMiscellaneousNum()
	{
		return miscellaneousNum;
	}

	/**
	 * @return
	 */
	public String getMiscellaneousNumTypeId()
	{
		return miscellaneousNumTypeId;
	}

	/**
	 * @return
	 */
	public String getNameSourceId()
	{
		return nameSourceId;
	}

	/**
	 * @return
	 */
	public String getNameTypeId()
	{
		return nameTypeId;
	}

	/**
	 * @return
	 */
	public String getNextOfKinFirstName()
	{
		return nextOfKinFirstName;
	}

	/**
	 * @return
	 */
	public String getNextOfKinLastName()
	{
		return nextOfKinLastName;
	}

	/**
	 * @return
	 */
	public String getNextOfKinMiddleName()
	{
		return nextOfKinMiddleName;
	}

	/**
	 * @return
	 */
	public String getNextOfKinRelationshipId()
	{
		return nextOfKinRelationshipId;
	}

	/**
	 * @return
	 */
	public String getPlaceOfBirth()
	{
		return placeOfBirth;
	}

	/**
	 * @return
	 */
	public String getPlaceOfBirthStateId()
	{
		return placeOfBirthStateId;
	}

	/**
	 * @return
	 */
	public String getRaceId()
	{
		return raceId;
	}

	/**
	 * @return
	 */
	public String getScarsMarksId()
	{
		return scarsMarksId;
	}

	/**
	 * @return
	 */
	public String getSexId()
	{
		return sexId;
	}

	/**
	 * @return
	 */
	public String getSheriffOfficeNum()
	{
		return sheriffOfficeNum;
	}

	/**
	 * @return
	 */
	public String getSidNum()
	{
		return sidNum;
	}

	/**
	 * @return
	 */
	public String getSkinToneId()
	{
		return skinToneId;
	}

	/**
	 * @return
	 */
	public String getSsn()
	{
		return ssn;
	}

	/**
	 * @return
	 */
	public String getWeight()
	{
		return weight;
	}

	/**
	 * @param addressId
	 */
	public void setAddressId(String addressId)
	{
		this.addressId = addressId;
	}

	/**
	 * @param afisNum
	 */
	public void setAfisNum(String afisNum)
	{
		this.afisNum = afisNum;
	}

	/**
	 * @param age
	 */
	public void setAge(String age)
	{
		this.age = age;
	}

	/**
	 * @param alienRegistrationNum
	 */
	public void setAlienRegistrationNum(String alienRegistrationNum)
	{
		this.alienRegistrationNum = alienRegistrationNum;
	}

	/**
	 * @param barNum
	 */
	public void setBarNum(String barNum)
	{
		this.barNum = barNum;
	}

	/**
	 * @param buildId
	 */
	public void setBuildId(String buildId)
	{
		this.buildId = buildId;
	}

	/**
	 * @param capacityId
	 */
	public void setCapacityId(String capacityId)
	{
		this.capacityId = capacityId;
	}

	/**
	 * @param citizenshipId
	 */
	public void setCitizenshipId(String citizenshipId)
	{
		this.citizenshipId = citizenshipId;
	}

	/**
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @param descriptorSourceId
	 */
	public void setDescriptorSourceId(String descriptorSourceId)
	{
		this.descriptorSourceId = descriptorSourceId;
	}

	/**
	 * @param driversLicenseClassId
	 */
	public void setDriversLicenseClassId(String driversLicenseClassId)
	{
		this.driversLicenseClassId = driversLicenseClassId;
	}

	/**
	 * @param driversLicenseExpirationDate
	 */
	public void setDriversLicenseExpirationYear(String driversLicenseExpirationYear)
	{
		this.driversLicenseExpirationYear = driversLicenseExpirationYear;
	}

	/**
	 * @param driversLicenseNum
	 */
	public void setDriversLicenseNum(String driversLicenseNum)
	{
		this.driversLicenseNum = driversLicenseNum;
	}

	/**
	 * @param driversLicenseStateId
	 */
	public void setDriversLicenseStateId(String driversLicenseStateId)
	{
		this.driversLicenseStateId = driversLicenseStateId;
	}

	/**
	 * @param employerAddressId
	 */
	public void setEmployerAddressId(String employerAddressId)
	{
		this.employerAddressId = employerAddressId;
	}

	/**
	 * @param employerName
	 */
	public void setEmployerName(String employerName)
	{
		this.employerName = employerName;
	}

	/**
	 * @param employerPhoneNum
	 */
	public void setEmployerPhoneNum(String employerPhoneNum)
	{
		this.employerPhoneNum = employerPhoneNum;
	}

	/**
	 * @param employmentStatusId
	 */
	public void setEmploymentStatusId(String employmentStatusId)
	{
		this.employmentStatusId = employmentStatusId;
	}

	/**
	 * @param ethnicityId
	 */
	public void setEthnicityId(String ethnicityId)
	{
		this.ethnicityId = ethnicityId;
	}

	/**
	 * @param eyeColorId
	 */
	public void setEyeColorId(String eyeColorId)
	{
		this.eyeColorId = eyeColorId;
	}

	/**
	 * @param fbiNum
	 */
	public void setFbiNum(String fbiNum)
	{
		this.fbiNum = fbiNum;
	}

	/**
	 * @param fingerprintInd
	 */
	public void setFingerPrintedInd(String fingerPrintedInd)
	{
		this.fingerPrintedInd = fingerPrintedInd;
	}

	/**
	 * @param hairColorId
	 */
	public void setHairColorId(String hairColorId)
	{
		this.hairColorId = hairColorId;
	}

	/**
	 * @param height
	 */
	public void setHeight(String height)
	{
		this.height = height;
	}

	/**
	 * @param idCardNum
	 */
	public void setIdCardNum(String idCardNum)
	{
		this.idCardNum = idCardNum;
	}

	/**
	 * @param idCardStateId
	 */
	public void setIdCardStateId(String idCardStateId)
	{
		this.idCardStateId = idCardStateId;
	}

	/**
	 * @param languageId
	 */
	public void setLanguageId(String languageId)
	{
		this.languageId = languageId;
	}

	/**
	 * @param maritalStatusId
	 */
	public void setMaritalStatusId(String maritalStatusId)
	{
		this.maritalStatusId = maritalStatusId;
	}

	/**
	 * @param miscellaneousNum
	 */
	public void setMiscellaneousNum(String miscellaneousNum)
	{
		this.miscellaneousNum = miscellaneousNum;
	}

	/**
	 * @param miscellaneousNumTypeId
	 */
	public void setMiscellaneousNumTypeId(String miscellaneousNumTypeId)
	{
		this.miscellaneousNumTypeId = miscellaneousNumTypeId;
	}

	/**
	 * @param nameSourceId
	 */
	public void setNameSourceId(String nameSourceId)
	{
		this.nameSourceId = nameSourceId;
	}

	/**
	 * @param nameTypeId
	 */
	public void setNameTypeId(String nameTypeId)
	{
		this.nameTypeId = nameTypeId;
	}

	/**
	 * @param nextOfKinFirstName
	 */
	public void setNextOfKinFirstName(String nextOfKinFirstName)
	{
		this.nextOfKinFirstName = nextOfKinFirstName;
	}

	/**
	 * @param nextOfKinLastName
	 */
	public void setNextOfKinLastName(String nextOfKinLastName)
	{
		this.nextOfKinLastName = nextOfKinLastName;
	}

	/**
	 * @param nextOfKinMiddleName
	 */
	public void setNextOfKinMiddleName(String nextOfKinMiddleName)
	{
		this.nextOfKinMiddleName = nextOfKinMiddleName;
	}

	/**
	 * @param nextOfKinRelationshipId
	 */
	public void setNextOfKinRelationshipId(String nextOfKinRelationshipId)
	{
		this.nextOfKinRelationshipId = nextOfKinRelationshipId;
	}

	/**
	 * @param placeOfBirth
	 */
	public void setPlaceOfBirth(String placeOfBirth)
	{
		this.placeOfBirth = placeOfBirth;
	}

	/**
	 * @param placeOfBirthStateId
	 */
	public void setPlaceOfBirthStateId(String placeOfBirthStateId)
	{
		this.placeOfBirthStateId = placeOfBirthStateId;
	}

	/**
	 * @param raceId
	 */
	public void setRaceId(String raceId)
	{
		this.raceId = raceId;
	}

	/**
	 * @param scarsMarksId
	 */
	public void setScarsMarksId(String scarsMarksId)
	{
		this.scarsMarksId = scarsMarksId;
	}

	/**
	 * @param sexId
	 */
	public void setSexId(String sexId)
	{
		this.sexId = sexId;
	}

	/**
	 * @param sheriffOfficeNum
	 */
	public void setSheriffOfficeNum(String sheriffOfficeNum)
	{
		this.sheriffOfficeNum = sheriffOfficeNum;
	}

	/**
	 * @param sidNum
	 */
	public void setSidNum(String sidNum)
	{
		this.sidNum = sidNum;
	}

	/**
	 * @param skinToneId
	 */
	public void setSkinToneId(String skinToneId)
	{
		this.skinToneId = skinToneId;
	}

	/**
	 * @param ssn
	 */
	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}

	/**
	 * @param weight
	 */
	public void setWeight(String weight)
	{
		this.weight = weight;
	}
	/**
	 * @return
	 */
	public String getEmployerAddressCity()
	{
		return employerAddressCity;
	}

	/**
	 * @return
	 */
	public String getEmployerAddressStateId()
	{
		return employerAddressStateId;
	}

	/**
	 * @return
	 */
	public String getEmployerAddressStreetName()
	{
		return employerAddressStreetName;
	}

	/**
	 * @return
	 */
	public String getEmployerAddressStreetName2()
	{
		return employerAddressStreetName2;
	}

	/**
	 * @return
	 */
	public String getEmployerAddressStreetNum()
	{
		return employerAddressStreetNum;
	}

	/**
	 * @return
	 */
	public String getEmployerAddressZipCode()
	{
		return employerAddressZipCode;
	}

	/**
	 * @param string
	 */
	public void setEmployerAddressCity(String string)
	{
		employerAddressCity = string;
	}

	/**
	 * @param string
	 */
	public void setEmployerAddressStateId(String string)
	{
		employerAddressStateId = string;
	}

	/**
	 * @param string
	 */
	public void setEmployerAddressStreetName(String string)
	{
		employerAddressStreetName = string;
	}

	/**
	 * @param string
	 */
	public void setEmployerAddressStreetName2(String string)
	{
		employerAddressStreetName2 = string;
	}

	/**
	 * @param string
	 */
	public void setEmployerAddressStreetNum(String string)
	{
		employerAddressStreetNum = string;
	}

	/**
	 * @param string
	 */
	public void setEmployerAddressZipCode(String string)
	{
		employerAddressZipCode = string;
	}

	/**
	 * @return
	 */
	public String getCurrentAddressCity()
	{
		return currentAddressCity;
	}

	/**
	 * @return
	 */
	public String getCurrentAddressStateId()
	{
		return currentAddressStateId;
	}

	/**
	 * @return
	 */
	public String getCurrentAddressStreetName()
	{
		return currentAddressStreetName;
	}
	
	/**
	 * @return
	 */
	public String getCurrentAddressAptNum()
	{
		return currentAddressAptNum;
	}	

	/**
	 * @return
	 */
	public String getCurrentAddressStreetNum()
	{
		return currentAddressStreetNum;
	}
	
	/**
	 * @return
	 */
	public String getCurrentAddressTypeId()
	{
		return currentAddressTypeId;
	}

	/**
	 * @return
	 */
	public String getCurrentAddressZipCode()
	{
		return currentAddressZipCode;
	}

	/**
	 * @param string
	 */
	public void setCurrentAddressCity(String string)
	{
		currentAddressCity = string;
	}

	/**
	 * @param string
	 */
	public void setCurrentAddressStateId(String string)
	{
		currentAddressStateId = string;
	}

	/**
	 * @param string
	 */
	public void setCurrentAddressStreetName(String string)
	{
		currentAddressStreetName = string;
	}
	
	/**
	 * @param string
	 */
	public void setCurrentAddressAptNum(String string)
	{
		currentAddressAptNum = string;
	}	

	/**
	 * @param string
	 */
	public void setCurrentAddressStreetNum(String string)
	{
		currentAddressStreetNum = string;
	}
	/**
	 * @param string
	 */
	public void setCurrentAddressTypeId(String string)
	{
		currentAddressTypeId = string;
	}
	/**
	 * @param string
	 */
	public void setCurrentAddressZipCode(String string)
	{
		currentAddressZipCode = string;
	}

	/**
	 * @return
	 */
	public String getCurrentAddressStreetName2()
	{
		return currentAddressStreetName2;
	}

	/**
	 * @param string
	 */
	public void setCurrentAddressStreetName2(String string)
	{
		currentAddressStreetName2 = string;
	}

	/**
	 * @return
	 */
	public String getOccupation()
	{
		return occupation;
	}

	/**
	 * @param string
	 */
	public void setOccupation(String string)
	{
		occupation = string;
	}

	/**
	 * @return
	 */
	public String getPartyId()
	{
		return partyId;
	}

	/**
	 * @return
	 */
	public String getSpn()
	{
		return spn;
	}

	/**
	 * @param string
	 */
	public void setPartyId(String string)
	{
		partyId = string;
	}

	/**
	 * @param string
	 */
	public void setSpn(String string)
	{
		spn = string;
	}

	/**
	 * @return
	 */
	public String getCurrentNameInd()
	{
		return currentNameInd;
	}

	/**
	 * @return
	 */
	public String getOID()
	{
		return OID;
	}

	/**
	 * @param string
	 */
	public void setCurrentNameInd(String string)
	{
		currentNameInd = string;
	}

	/**
	 * @param string
	 */
	public void setOID(String string)
	{
		OID = string;
	}

	/**
	 * @return
	 */
	public String getConnectionId()
	{
		return connectionId;
	}

	/**
	 * @param string
	 */
	public void setConnectionId(String string)
	{
		connectionId = string;
	}

	/**
	 * @return
	 */
	public String getNamePtr()
	{
		return namePtr;
	}

	/**
	 * @param string
	 */
	public void setNamePtr(String string)
	{
		namePtr = string;
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

	public Collection getScarsMarks() {
		return scarsMarks;
	}

	public void setScarsMarks(Collection scarsMarks) {
		this.scarsMarks = scarsMarks;
	}

	public Collection getTattoos() {
		return tattoos;
	}

	public void setTattoos(Collection tattoos) {
		this.tattoos = tattoos;
	}

	public void setNameSeqNum(String nameSeqNum) {
		this.nameSeqNum = nameSeqNum;
	}

	public String getNameSeqNum() {
		return nameSeqNum;
	}
}