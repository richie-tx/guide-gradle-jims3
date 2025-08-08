//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\domintf\\contact\\party\\IParty.java

package messaging.domintf.contact.party;

import java.util.Collection;
import java.util.Date;

public interface IParty
{
	/**
	 * @return
	 */
	String getAddressId();

	/**
	 * @return
	 */
	String getAfisNum();

	/**
	 * @return
	 */
	String getAge();

	/**
	 * @return
	 */
	String getAlienRegistrationNum();

	/**
	 * @return
	 */
	String getBarNum();

	/**
	 * @return
	 */
	String getBuildId();

	/**
	 * @return
	 */
	String getCapacityId();

	/**
	 * @return
	 */
	String getCitizenshipId();

	/**
	 * @return
	 */
	Date getDateOfBirth();

	/**
	 * @return
	 */
	String getDescriptorSourceId();

	/**
	 * @return
	 */
	String getDriversLicenseClassId();

	/**
	 * @return
	 */
	String getDriversLicenseExpirationYear();

	/**
	 * @return
	 */
	String getDriversLicenseNum();

	/**
	 * @return
	 */
	String getDriversLicenseStateId();

	/**
	 * @return
	 */
	String getEmployerAddressId();

	/**
	 * @return
	 */
	String getEmployerName();

	/**
	 * @return
	 */
	String getEmployerPhoneNum();

	/**
	 * @return
	 */
	String getEmploymentStatusId();

	/**
	 * @return
	 */
	String getEthnicityId();

	/**
	 * @return
	 */
	String getEyeColorId();

	/**
	 * @return
	 */
	String getFbiNum();

	/**
	 * @return
	 */
	String getFingerPrintedInd();

	/**
	 * @return
	 */
	String getHairColorId();
	/**
	 * @return
	 */
	String getHeight();

	/**
	 * @return
	 */
	String getIdCardNum();

	/**
	 * @return
	 */
	String getIdCardStateId();

	/**
	 * @return
	 */
	String getLanguageId();

	/**
	 * @return
	 */
	String getMaritalStatusId();

	/**
	 * @return
	 */
	String getMiscellaneousNum();

	/**
	 * @return
	 */
	String getMiscellaneousNumTypeId();

	/**
	 * @return
	 */
	String getNameSourceId();

	/**
	 * @return
	 */
	String getNameTypeId();

	/**
	 * @return
	 */
	String getNextOfKinFirstName();

	/**
	 * @return
	 */
	String getNextOfKinLastName();

	/**
	 * @return
	 */
	String getNextOfKinMiddleName();

	/**
	 * @return
	 */
	String getNextOfKinRelationshipId();

	/**
	 * @return
	 */
	String getPlaceOfBirth();

	/**
	 * @return
	 */
	String getPlaceOfBirthStateId();

	/**
	 * @return
	 */
	String getRaceId();

	/**
	 * @return
	 */
	String getScarsMarksId();
	/**
	 * @return
	 */
	Collection getScarsMarks();
	/**
	 * @return
	 */
	Collection getTattoos();	

	/**
	 * @return
	 */
	String getSexId();

	/**
	 * @return
	 */
	String getSheriffOfficeNum();

	/**
	 * @return
	 */
	String getSidNum();

	/**
	 * @return
	 */
	String getSkinToneId();

	/**
	 * @return
	 */
	String getSsn();

	/**
	 * @return
	 */
	String getWeight();

	/**
	 * @param addressId
	 */
	void setAddressId(String addressId);

	/**
	 * @param afisNum
	 */
	void setAfisNum(String afisNum);

	/**
	 * @param age
	 */
	void setAge(String age);

	/**
	 * @param alienRegistrationNum
	 */
	void setAlienRegistrationNum(String alienRegistrationNum);

	/**
	 * @param barNum
	 */
	void setBarNum(String barNum);

	/**
	 * @param buildId
	 */
	void setBuildId(String buildId);

	/**
	 * @param capacityId
	 */
	void setCapacityId(String capacityId);

	/**
	 * @param citizenshipId
	 */
	void setCitizenshipId(String citizenshipId);

	/**
	 * @param dateOfBirth
	 */
	void setDateOfBirth(Date dateOfBirth);

	/**
	 * @param descriptorSourceId
	 */
	void setDescriptorSourceId(String descriptorSourceId);

	/**
	 * @param driversLicenseClassId
	 */
	void setDriversLicenseClassId(String driversLicenseClassId);

	/**
	 * @param driversLicenseExpirationDate
	 */
	void setDriversLicenseExpirationYear(String driversLicenseExpirationYear);

	/**
	 * @param driversLicenseNum
	 */
	void setDriversLicenseNum(String driversLicenseNum);

	/**
	 * @param driversLicenseStateId
	 */
	void setDriversLicenseStateId(String driversLicenseStateId);

	/**
	 * @param employerAddressId
	 */
	void setEmployerAddressId(String employerAddressId);

	/**
	 * @param employerName
	 */
	void setEmployerName(String employerName);

	/**
	 * @param employerPhoneNum
	 */
	void setEmployerPhoneNum(String employerPhoneNum);

	/**
	 * @param employmentStatusId
	 */
	void setEmploymentStatusId(String employmentStatusId);

	/**
	 * @param ethnicityId
	 */
	void setEthnicityId(String ethnicityId);

	/**
	 * @param eyeColorId
	 */
	void setEyeColorId(String eyeColorId);

	/**
	 * @param fbiNum
	 */
	void setFbiNum(String fbiNum);

	/**
	 * @param fingerprintInd
	 */
	void setFingerPrintedInd(String fingerPrintedInd);

	/**
	 * @param haircolorId
	 */
	void setHairColorId(String haircolorId);

	/**
	 * @param height
	 */
	void setHeight(String height);

	/**
	 * @param idCardNum
	 */
	void setIdCardNum(String idCardNum);

	/**
	 * @param idCardStateId
	 */
	void setIdCardStateId(String idCardStateId);

	/**
	 * @param languageId
	 */
	void setLanguageId(String languageId);

	/**
	 * @param maritalStatusId
	 */
	void setMaritalStatusId(String maritalStatusId);

	/**
	 * @param miscellaneousNum
	 */
	void setMiscellaneousNum(String miscellaneousNum);

	/**
	 * @param miscellaneousNumTypeId
	 */
	void setMiscellaneousNumTypeId(String miscellaneousNumTypeId);

	/**
	 * @param nameSourceId
	 */
	void setNameSourceId(String nameSourceId);

	/**
	 * @param nameTypeId
	 */
	void setNameTypeId(String nameTypeId);

	/**
	 * @param nextOfKinFirstName
	 */
	void setNextOfKinFirstName(String nextOfKinFirstName);

	/**
	 * @param nextOfKinLastName
	 */
	void setNextOfKinLastName(String nextOfKinLastName);

	/**
	 * @param nextOfKinMiddleName
	 */
	void setNextOfKinMiddleName(String nextOfKinMiddleName);

	/**
	 * @param nextOfKinRelationshipId
	 */
	void setNextOfKinRelationshipId(String nextOfKinRelationshipId);

	/**
	 * @param placeOfBirth
	 */
	void setPlaceOfBirth(String placeOfBirth);

	/**
	 * @param placeOfBirthStateId
	 */
	void setPlaceOfBirthStateId(String placeOfBirthStateId);

	/**
	 * @param raceId
	 */
	void setRaceId(String raceId);

	/**
	 * @param scarsMarksId
	 */
	void setScarsMarksId(String scarsMarksId);
	/**
	 * @param scarsMarks
	 */
	void setScarsMarks(Collection scarsMarks);
	/**
	 * @param tattoos
	 */
	void setTattoos(Collection tattoos);

	/**
	 * @param sexId
	 */
	void setSexId(String sexId);

	/**
	 * @param sheriffOfficeNum
	 */
	void setSheriffOfficeNum(String sheriffOfficeNum);
	
	/**
	 * @param sidNum
	 */
	void setSidNum(String sidNum);

	/**
	 * @param skinToneId
	 */
	void setSkinToneId(String skinToneId);

	/**
	 * @param ssn
	 */
	void setSsn(String ssn);

	/**
	 * @param weight
	 */
	void setWeight(String weight);
	
	/**
	 * @return
	 */
	String getEmployerAddressCity();

	/**
	 * @return
	 */
	String getEmployerAddressStateId();

	/**
	 * @return
	 */
	String getEmployerAddressStreetName();

	/**
	 * @return
	 */
	String getEmployerAddressStreetName2();

	/**
	 * @return
	 */
	String getEmployerAddressStreetNum();

	/**
	 * @return
	 */
	String getEmployerAddressZipCode();

	/**
	 * @param string
	 */
	void setEmployerAddressCity(String string);

	/**
	 * @param string
	 */
	void setEmployerAddressStateId(String string);

	/**
	 * @param string
	 */
	void setEmployerAddressStreetName(String string);

	/**
	 * @param string
	 */
	void setEmployerAddressStreetName2(String string);

	/**
	 * @param string
	 */
	void setEmployerAddressStreetNum(String string);

	/**
	 * @param string
	 */
	void setEmployerAddressZipCode(String string);
	/**
	 * @return
	 */
	String getCurrentAddressCity();

	/**
	 * @return
	 */
	String getCurrentAddressStateId();

	/**
	 * @return
	 */
	String getCurrentAddressStreetName();

	/**
	 * @return
	 */
	String getCurrentAddressAptNum();
	
	/**
	 * @return
	 */
	String getCurrentAddressStreetNum();

	/**
	 * @return
	 */
	String getCurrentAddressTypeId();
	
	/**
	 * @return
	 */
	String getCurrentAddressZipCode();

	/**
	 * @param string
	 */
	void setCurrentAddressCity(String string);

	/**
	 * @param string
	 */
	void setCurrentAddressStateId(String string);

	/**
	 * @param string
	 */
	void setCurrentAddressStreetName(String string);
	/**
	 * @param string
	 */
	void setCurrentAddressAptNum(String string);	

	/**
	 * @param string
	 */
	void setCurrentAddressStreetNum(String string);
	
	/**
	 * @param string
	 */
	void setCurrentAddressTypeId(String string);

	/**
	 * @param string
	 */
	void setCurrentAddressZipCode(String string);

	/**
	 * @return
	 */
	String getCurrentAddressStreetName2();

	/**
	 * @param string
	 */
	void setCurrentAddressStreetName2(String string);

	/**
	 * @return
	 */
	String getOccupation();

	/**
	 * @param string
	 */
	void setOccupation(String string);

	/**
	 * @return
	 */
	String getPartyId();

	/**
	 * @return
	 */
	String getSpn();

	/**
	 * @param string
	 */
	void setPartyId(String string);

	/**
	 * @param string
	 */
	void setSpn(String string);

	/**
	 * @return
	 */
	String getCurrentNameInd();

	/**
	 * @param string
	 */
	void setCurrentNameInd(String string);

	/**
	 * @return
	 */
	String getOID();

	/**
	 * @param string
	 */
	void setOID(String string);

	/**
	 * @return
	 */
	String getConnectionId();

	/**
	 * @param string
	 */
	void setConnectionId(String string);

	/**
	 * @return
	 */
	String getNamePtr();

	/**
	 * @param string
	 */
	void setNamePtr(String string);

	/**
	 * @return
	 */
	String getEmail();

	/**
	 * @param string
	 */
	void setEmail(String string);

	/**
	 * @return
	 */
	String getCellPhone();

	/**
	 * @param string
	 */
	void setCellPhone(String string);

	/**
	 * @return
	 */
	String getFaxLocation();

	/**
	 * @param string
	 */
	void setFaxLocation(String string);

	/**
	 * @return
	 */
	String getFaxNum();

	/**
	 * @param string
	 */
	void setFaxNum(String string);

	/**
	 * @return
	 */
	String getFirstName();

	/**
	 * @param string
	 */
	void setFirstName(String string);

	/**
	 * @return
	 */
	String getMiddleName();

	/**
	 * @param string
	 */
	void setMiddleName(String string);

	/**
	 * @return
	 */
	String getLastName();

	/**
	 * @param string
	 */
	void setLastName(String string);

	/**
	 * @return
	 */
	String getPhoneNum();

	/**
	 * @param string
	 */
	void setPhoneNum(String string);

	/**
	 * @return
	 */
	String getPhoneExt();
	
	/**
	 * @param string
	 */
	void setPhoneExt(String string);

	/**
	 * @return
	 */
	String getPager();
	
	/**
	 * @param string
	 */
	void setPager(String string);

	/**
	 * @return
	 */
	String getTitle();
	
	/**
	 * @param string
	 */
	void setTitle(String string);

	/**
	 * @return
	 */
	String getHomePhoneNum();
	
	/**
	 * @param string
	 */
	void setHomePhoneNum(String string);

	/**
	 * @return
	 */
	String getWorkPhoneNum();
	
	/**
	 * @param string
	 */
	void setWorkPhoneNum(String string);

	/**
	 * @return
	 */
	String getTopic();
	
	/**
	 * @param string
	 */
	void setTopic(String string);

}
