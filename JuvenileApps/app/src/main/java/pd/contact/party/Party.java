package pd.contact.party;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.contact.party.reply.PartyListResponseEvent;
import messaging.contact.to.SocialSecurityBean;
import messaging.party.GetPartyDataByBarNumEvent;
import messaging.party.GetPartyDataEvent;
import messaging.party.GetTasksByDefendantIdsEvent;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.ObjectNotFoundException;
import mojo.km.utilities.Name;
import naming.PDCodeTableConstants;
import pd.address.Address;
import pd.codetable.Code;
import pd.contact.Contact;
import pd.contact.Employer;
import pd.contact.agency.Agency;

/**
 * @author dgibler
 */
public class Party extends Contact {
	/**
	 * Properties for addresses
	 * 
	 * @useParent true
	 * @referencedType pd.address.Address
	 */
	private java.util.Collection addresses = null;

	private Address currentAddress = null;

	private String afisNum;

	private String age;

	private String alienRegistrationNum;

	private String barNum;
	
	private String jailInd;
	
	private String warrantInd ; 
	
	

	/**
	 * @return Returns the warrantInd.
	 */
	public String getWarrantInd() {
		return warrantInd;
	}
	
	/**
	 * @param warrantInd The warrantInd to set.
	 */
	public void setWarrantInd(String warrantInd) {
		this.warrantInd = warrantInd;
	}
	
	/**
	 * @return Returns the jailInd.
	 */
	public String getJailInd() {
		return jailInd;
	}
	/**
	 * @param jailInd The jailInd to set.
	 */
	public void setJailInd(String jailInd) {
		this.jailInd = jailInd;
	}
	/**
	 * Properties for build
	 * 
	 * @useParent true
	 * @contextKey BUILD
	 */
	private Code build = null;

	private String buildId;

	private String capacityId;

	/**
	 * Properties for capacities
	 * 
	 * @referencedType pd.contact.party.Capacity
	 * @useParent true
	 */
	private java.util.Collection capacities = null;

	/**
	 * Properties for citizenship
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey CITIZENSHIP
	 */
	private Code citizenship = null;

	private String citizenshipId;

	private String cjisNum;

	private String connectionId;

	/**
	 * Properties for createAgency
	 * 
	 * @referencedType pd.contact.agency.Agency
	 */
	private Agency createAgency = null;

	private String createAgencyId;

	private String currentNameInd;

	private Date dateOfBirth;

	/**
	 * Properties for descriptorSource
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey DESCRIPTOR_SOURCE
	 */
	private Code descriptorSource = null;

	private String descriptorSourceId;

	/**
	 * Properties for driversLicenseClass
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey DRIVERS_LICENSE_CLASS
	 */
	private Code driversLicenseClass = null;

	private String driversLicenseClassId;

	private String driversLicenseExpirationYear;

	private String driversLicenseNum;

	/**
	 * Properties for driversLicenseState
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey STATE_ABBR
	 */
	private Code driversLicenseState = null;

	private String driversLicenseStateId;

	/**
	 * Properties for employers
	 * 
	 * @referencedType pd.contact.Employer
	 * @useParent true
	 */
	private java.util.Collection employers = null;

	/**
	 * Properties for ethnicity
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey ETHNICITY
	 */
	private Code ethnicity = null;

	private String ethnicityId;

	/**
	 * Properties for eyeColor
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey EYE_COLOR
	 */
	private Code eyeColor = null;

	private String eyeColorId;

	private String fbiNum;

	private String fingerprintInd;

	/**
	 * Properties for haircolor
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey HAIR_COLOR
	 */
	private Code haircolor = null;

	private String haircolorId;

	private String height;

	private String idCardNum;

	/**
	 * Properties for idCardState
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey STATE_ABBR
	 */
	private Code idCardState = null;

	private String idCardStateId;

	private String inactiveInd;

	/**
	 * Properties for language
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey LANGUAGE
	 */
	private Code language = null;

	private String languageId;

	/**
	 * Properties for maritalStatus
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey MARITAL_STATUS
	 */
	private Code maritalStatus = null;

	private String maritalStatusId;

	/**
	 * Properties for miscellaneousNums
	 * 
	 * @useParent true
	 * @referencedType pd.contact.party.MiscellaneousId
	 */
	private java.util.Collection miscellaneousNums = null;

	private String namePtr;

	private String nameSeq;

	/**
	 * Properties for nameSource
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey INFO_SOURCE
	 */
	private Code nameSource = null;

	private String nameSourceId;

	/**
	 * Properties for nameType
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey ALIAS_NAME_TYPE
	 */
	private Code nameType = null;

	private String nameTypeId;

	private String nextOfKinFirstName;

	private String nextOfKinLastName;

	private String nextOfKinMiddleName;

	/**
	 * Properties for nextOfKinRelationship
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey RELATIONSHIP_TO_PARTY
	 */
	private Code nextOfKinRelationship = null;

	private String nextOfKinRelationshipId;

	private String placeOfBirth;

	/**
	 * Properties for placeOfBirthState
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey PLACE_OF_BIRTH
	 */
	private Code placeOfBirthState = null;

	private String placeOfBirthStateId;

	/**
	 * Properties for race
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey RACE
	 */
	private Code race = null;

	private String raceId;

	/**
	 * Properties for scarsMarks
	 * 
	 * @referencedType pd.contact.party.PartyScarsMarksScarsMarksTattoosCode
	 */
	private java.util.Collection scarsMarks = null;

	/**
	 * Properties for sex
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey SEX
	 */
	private Code sex = null;

	private String sexId;

	private String sheriffOfficeNum;

	private String sidNum;

	/**
	 * Properties for skinTone
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey SKIN_TONE
	 */
	private Code skinTone = null;

	private String skinToneId;

	private String spn;

	private String ssn;
	
	private String name;

	/**
	 * Properties for tattoos
	 * 
	 * @referencedType pd.codetable.person.ScarsMarksTattoosCode
	 */
	private java.util.Collection tattoos = null;

	private String weight;

	/**
	 * Returns Party object based on event attributes.
	 * 
	 * @return Party
	 * @param event
	 */
	static public Party find(GetPartyDataEvent event) {
		IHome home = new Home();
		Party party = null;
		Iterator iter = home.findAll(event, Party.class);
		if ((iter != null) && (iter.hasNext())) {
			party = (Party) iter.next();
		}
		return party;
	}

	/**
	 * Returns Party object based on event attributes.
	 * 
	 * @return Party
	 * @param event
	 */
	static public Party find(GetPartyDataByBarNumEvent event) {
		IHome home = new Home();
		Party party = null;
		Iterator iter = home.findAll(event, Party.class);
		if ((iter != null) && (iter.hasNext())) {
			party = (Party) iter.next();
		}
		return party;
	}
	/**
	 * Returns Party object by OID, which is spn + seq #.
	 * 
	 * @return Party
	 * @param anOID
	 */
	static public Party find(String anOID) {
		Party party = null;
		IHome home = new Home();
		try {
			party = (Party) home.find(anOID, Party.class);
		} catch (ObjectNotFoundException e) {
			party = null;
		}
		return party;
	}

	/**
	 * Returns Party object matching attribute name/value pair.
	 * 
	 * @return Party
	 * @param attrName
	 * @param attrValue
	 */
	static public Party find(String attrName, String attrValue) {
		IHome home = new Home();
		Party party = null;
		try {
			party = (Party) home.find(attrName, attrValue, Party.class);
		} catch (ObjectNotFoundException e) {
			party = null;
		}
		return party;
	}

	/**
	 * Returns collection of Party objects.
	 * 
	 * @return java.util.Collection
	 * @param event
	 */
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		Iterator iter = home.findAll(event, Party.class);
		return iter;
	}

	/**
	 * @roseuid 41C0B04E022F
	 */
	public Party() {
	}

	/**
	 * @roseuid 41C0A79F0215
	 */
	public void bind() {
		markModified();
	}

	/**
	 * Clears all pd.address.Address from class relationship collection.
	 */
	public void clearAddresses() {
		initAddresses();
		addresses.clear();
	}

	/**
	 * Clears all pd.contact.party.Capacity from class relationship collection.
	 */
	public void clearCapacities() {
		initCapacities();
		capacities.clear();
	}

	/**
	 * Clears all pd.contact.Employer from class relationship collection.
	 */
	public void clearEmployers() {
		initEmployers();
		employers.clear();
	}

	/**
	 * Clears all pd.contact.party.MiscellaneousId from class relationship
	 * collection.
	 */
	public void clearMiscellaneousNums() {
		initMiscellaneousNums();
		miscellaneousNums.clear();
	}

	/**
	 * Clears all pd.codetable.person.ScarsMarksTattoosCode from class
	 * relationship collection.
	 */
	public void clearScarsMarks() {
		initScarsMarks();
		scarsMarks.clear();
	}

	/**
	 * Clears all pd.codetable.person.ScarsMarksTattoosCode from class
	 * relationship collection.
	 */
	public void clearTattoos() {
		initTattoos();
		tattoos.clear();
	}

	/**
	 * @roseuid 41C0A79F021F
	 */
	public void findAll() {
		fetch();
	}

	/**
	 * Returns current address.
	 * 
	 * @return Address
	 */
	public Address getCurrentAddress() {
		fetch();
		initAddresses();
		Address address = null;
		if (this.addresses != null) {
			Iterator iter = addresses.iterator();
			while (iter.hasNext()) {
				address = (Address) iter.next();
				if ((address.getCurrentAddressInd() != null)
						&& (address.getCurrentAddressInd().equals("Y"))) {
					break;
				}

			}
		}
		return address;
	}

	/**
	 * Returns current employer
	 * 
	 * @return Employer
	 */
	public Employer getCurrentEmployer() {
		fetch();
		initEmployers();
		Employer employer = null;
		if (this.employers != null) {
			Iterator iter = employers.iterator();
			while (iter.hasNext()) {
				employer = (Employer) iter.next();
				if ((employer.getCurrentEmployerInd() != null)
						&& (employer.getCurrentEmployerInd().equals("Y"))) {
					break;
				}
			}
		}
		return employer;
	}

	/**
	 * returns a collection of pd.address.Address
	 * 
	 * @return java.util.Collection
	 */
	public java.util.Collection getAddresses() {
		fetch();
		initAddresses();
		return addresses;
	}

	/**
	 * Access method for the afisNum property.
	 * 
	 * @return the current value of the afisNum property
	 */
	public String getAfisNum() {
		fetch();
		return afisNum;
	}

	/**
	 * Access method for the age property.
	 * 
	 * @return the current value of the age property
	 */
	public String getAge() {
		fetch();
		return age;
	}

	/**
	 * Access method for the alienRegistrationNum property.
	 * 
	 * @return the current value of the alienRegistrationNum property
	 */
	public String getAlienRegistrationNum() {
		fetch();
		return alienRegistrationNum;
	}

	/**
	 * Access method for the barNum property.
	 * 
	 * @return String
	 */
	public String getBarNum() {
		fetch();
		return barNum;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getBuild() {
		fetch();
		initBuild();
		return build;
	}

	/**
	 * Get the current value of the capacityId property
	 * 
	 * @return pd.codetable.Code
	 */
	public String getBuildId() {
		fetch();
		return buildId;
	}

	/**
	 * Get the current value of the capacityId property
	 * 
	 * @return String
	 */
	public String getCapacityId() {
		fetch();
		return capacityId;
	}

	/**
	 * returns a collection of pd.contact.party.Capacity
	 * 
	 * @return java.util.Collection
	 */
	public java.util.Collection getCapacities() {
		fetch();
		initCapacities();
		return capacities;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getCitizenship() {
		fetch();
		initCitizenship();
		return citizenship;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getCitizenshipId() {
		fetch();
		return citizenshipId;
	}

	/**
	 * @return
	 */
	public String getCjisNum() {
		fetch();
		return cjisNum;
	}

	/**
	 * @return
	 */
	public String getConnectionId() {
		fetch();
		return connectionId;
	}

	/**
	 * Gets referenced type pd.contact.agency.Agency
	 * 
	 * @return Agency
	 */
	public Agency getCreateAgency() {
		fetch();
		initCreateAgency();
		return createAgency;
	}

	/**
	 * Get the reference value to class :: pd.contact.agency.Agency
	 * 
	 * @return java.lang.String
	 */
	public String getCreateAgencyId() {
		fetch();
		return createAgencyId;
	}

	/**
	 * Access method for the currentNameInd property.
	 * 
	 * @return the current value of the currentNameInd property
	 */
	public String getCurrentNameInd() {
		fetch();
		return currentNameInd;
	}

	/**
	 * Access method for the dateOfBirth property.
	 * 
	 * @return the current value of the dateOfBirth property
	 */
	public Date getDateOfBirth() {
		fetch();
		return dateOfBirth;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getDescriptorSource() {
		fetch();
		initDescriptorSource();
		return descriptorSource;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getDescriptorSourceId() {
		fetch();
		return descriptorSourceId;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getDriversLicenseClass() {
		fetch();
		initDriversLicenseClass();
		return driversLicenseClass;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getDriversLicenseClassId() {
		fetch();
		return driversLicenseClassId;
	}

	/**
	 * Access method for the driversLicenseExpirationYear property.
	 * 
	 * @return the current value of the driversLicenseExpirationYear property
	 */
	public String getDriversLicenseExpirationYear() {
		fetch();
		return driversLicenseExpirationYear;
	}

	/**
	 * Access method for the driversLicenseNum property.
	 * 
	 * @return the current value of the driversLicenseNum property
	 */
	public String getDriversLicenseNum() {
		fetch();
		return driversLicenseNum;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getDriversLicenseState() {
		fetch();
		initDriversLicenseState();
		return driversLicenseState;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getDriversLicenseStateId() {
		fetch();
		return driversLicenseStateId;
	}

	/**
	 * returns a collection of pd.contact.Employer
	 * 
	 * @return java.util.Collection
	 */
	public java.util.Collection getEmployers() {
		fetch();
		initEmployers();
		return employers;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return java.util.Collection
	 */
	public Code getEthnicity() {
		fetch();
		initEthnicity();
		return ethnicity;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getEthnicityId() {
		fetch();
		return ethnicityId;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getEyeColor() {
		fetch();
		initEyeColor();
		return eyeColor;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getEyeColorId() {
		fetch();
		return eyeColorId;
	}

	/**
	 * Access method for the fbiNum property.
	 * 
	 * @return the current value of the fbiNum property
	 */
	public String getFbiNum() {
		fetch();
		return fbiNum;
	}

	/**
	 * Access method for the fingerprintInd property.
	 * 
	 * @return the current value of the fingerprintInd property
	 */
	public String getFingerprintInd() {
		fetch();
		return fingerprintInd;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getHaircolor() {
		fetch();
		initHaircolor();
		return haircolor;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getHaircolorId() {
		fetch();
		return haircolorId;
	}

	/**
	 * Access method for the height property.
	 * 
	 * @return the current value of the height property
	 */
	public String getHeight() {
		fetch();
		return height;
	}

	/**
	 * Access method for the idCardNum property.
	 * 
	 * @return the current value of the idCardNum property
	 */
	public String getIdCardNum() {
		fetch();
		return idCardNum;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getIdCardState() {
		fetch();
		initIdCardState();
		return idCardState;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getIdCardStateId() {
		fetch();
		return idCardStateId;
	}

	/**
	 * Access method for the inactiveInd property.
	 * 
	 * @return the current value of the inactiveInd property
	 */
	public String getInactiveInd() {
		fetch();
		return inactiveInd;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getLanguage() {
		fetch();
		initLanguage();
		return language;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getLanguageId() {
		fetch();
		return languageId;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getMaritalStatus() {
		fetch();
		initMaritalStatus();
		return maritalStatus;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getMaritalStatusId() {
		fetch();
		return maritalStatusId;
	}

	/**
	 * returns a collection of pd.contact.party.MiscellaneousId
	 * 
	 * @return java.util.Collection
	 */
	public java.util.Collection getMiscellaneousNums() {
		fetch();
		initMiscellaneousNums();
		return miscellaneousNums;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getNameSource() {
		fetch();
		initNameSource();
		return nameSource;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getNameSourceId() {
		fetch();
		return nameSourceId;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getNameType() {
		fetch();
		initNameType();
		return nameType;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getNameTypeId() {
		fetch();
		return nameTypeId;
	}

	/**
	 * Access method for the nextOfKinFirstName property.
	 * 
	 * @return the current value of the nextOfKinFirstName property
	 */
	public String getNextOfKinFirstName() {
		fetch();
		return nextOfKinFirstName;
	}

	/**
	 * Access method for the nextOfKinLastName property.
	 * 
	 * @return the current value of the nextOfKinLastName property
	 */
	public String getNextOfKinLastName() {
		fetch();
		return nextOfKinLastName;
	}

	/**
	 * Access method for the nextOfKinMiddleName property.
	 * 
	 * @return the current value of the nextOfKinMiddleName property
	 */
	public String getNextOfKinMiddleName() {
		fetch();
		return nextOfKinMiddleName;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getNextOfKinRelationship() {
		fetch();
		initNextOfKinRelationship();
		return nextOfKinRelationship;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getNextOfKinRelationshipId() {
		fetch();
		return nextOfKinRelationshipId;
	}

	/**
	 * Access method for the placeOfBirth property.
	 * 
	 * @return the current value of the placeOfBirth property
	 */
	public String getPlaceOfBirth() {
		fetch();
		return placeOfBirth;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getPlaceOfBirthState() {
		fetch();
		initPlaceOfBirthState();
		return placeOfBirthState;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getPlaceOfBirthStateId() {
		fetch();
		return placeOfBirthStateId;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getRace() {
		fetch();
		initRace();
		return race;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getRaceId() {
		fetch();
		return raceId;
	}

	/**
	 * returns a collection of pd.contact.party.PartyScarsMarksScarsMarksTattoosCode
	 * 
	 * @return java.util.Collection
	 */
	public java.util.Collection getScarsMarks() {
		fetch();
		initScarsMarks();
		java.util.ArrayList retVal = new java.util.ArrayList();
		if (scarsMarks != null && scarsMarks.size() > 0) {
			Iterator i = scarsMarks.iterator();
			while (i.hasNext())
			{
				PartyScarsMarksScarsMarksTattoosCode actual = (PartyScarsMarksScarsMarksTattoosCode) i
						.next();
				retVal.add(actual.getChild());
			}
		}
		return retVal;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getSex() {
		fetch();
		initSex();
		return sex;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getSexId() {
		fetch();
		return sexId;
	}

	/**
	 * Access method for the sheriffOfficeNum property.
	 * 
	 * @return the current value of the sheriffOfficeNum property
	 */
	public String getSheriffOfficeNum() {
		fetch();
		return sheriffOfficeNum;
	}

	/**
	 * Access method for the sidNum property.
	 * 
	 * @return the current value of the sidNum property
	 */
	public String getSidNum() {
		fetch();
		return sidNum;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * 
	 * @return pd.codetable.Code
	 */
	public Code getSkinTone() {
		fetch();
		initSkinTone();
		return skinTone;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * 
	 * @return java.lang.String
	 */
	public String getSkinToneId() {
		fetch();
		return skinToneId;
	}

	/**
	 * Access method for the spn property.
	 * 
	 * @return the current value of the spn property
	 */
	public String getSpn() {
		fetch();
		return spn;
	}

	/**
	 * Access method for the ssn property.
	 * 
	 * @return the current value of the ssn property
	 */
	public String getSsn() {
		fetch();
		return ssn;
	}

	/**
	 * returns a collection of pd.codetable.person.ScarsMarksTattoosCode
	 * 
	 * @return java.util.Collection
	 */
	public java.util.Collection getTattoos() {
		fetch();
		initTattoos();
		java.util.ArrayList retVal = new java.util.ArrayList();
		if (tattoos != null && tattoos.size() > 0) {
			Iterator i = tattoos.iterator();
			while (i.hasNext())
			{
				PartyTattoosScarsMarksTattoosCode actual = (PartyTattoosScarsMarksTattoosCode) i
						.next();
				retVal.add(actual.getChild());
			}
		}
		return retVal;
	}

	/**
	 * Access method for the weight property.
	 * 
	 * @return the current value of the weight property
	 */
	public String getWeight() {
		fetch();
		return weight;
	}

	/**
	 * Access method for the namePtr property.
	 * 
	 * @return the current value of the namePtr property
	 */
	public String getNamePtr() {
		fetch();
		return namePtr;
	}

	/**
	 * Access method for the nameSeq property.
	 * 
	 * @return the current value of the nameSeq property
	 */
	public String getNameSeq() {
		fetch();
		return nameSeq;
	}

	/**
	 * Initialize class relationship implementation for pd.address.Address
	 */
	private void initAddresses() {
		if (addresses == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			try {
				addresses = new mojo.km.persistence.ArrayList(
						Address.class, "criteria",
						(mojo.km.persistence.PersistentObject) this,
						"addresses");
			} catch (Throwable t) {
				addresses = new java.util.ArrayList();
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initBuild() {
		if (build == null) {
			try {
				build = (Code) new mojo.km.persistence.Reference(
						buildId, Code.class, PDCodeTableConstants.BUILD).getObject();
			} catch (Throwable t) {
				build = null;
			}
		}
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.contact.party.Capacity
	 */
	private void initCapacities() {
		if (capacities == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			try {
				capacities = new mojo.km.persistence.ArrayList(
						Capacity.class, "partyId",
						(mojo.km.persistence.PersistentObject) this,
						"capacities");

			} catch (Throwable t) {
				capacities = new java.util.ArrayList();
			}
		}

	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initCitizenship() {
		if (citizenship == null) {
			try {
				citizenship = (Code) new mojo.km.persistence.Reference(
						citizenshipId, Code.class, "CITIZENSHIP")
						.getObject();
			} catch (Throwable t) {
				citizenship = null;
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.contact.agency.Agency
	 */
	private void initCreateAgency() {
		//		if (createAgency == null)
		//		{
		//			try
		//			{
		//				createAgency =
		//					(pd.contact.agency.Agency) new mojo
		//						.km
		//						.persistence
		//						.Reference(
		//							createAgencyId,
		//							pd.contact.agency.Agency.class,
		//							(mojo.km.persistence.PersistentObject) this,
		//							"createAgency")
		//						.getObject();
		//			}
		//			catch (Throwable t)
		//			{
		//			}
		//		}
		if (createAgency == null) {
			try {
				createAgency = Agency.find(createAgencyId);//(pd.contact.agency.Agency) new mojo.km.persistence.Reference(
						//createAgencyId, pd.contact.agency.Agency.class)
						//.getObject();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}

	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initDescriptorSource() {
		if (descriptorSource == null) {
			try {
				descriptorSource = (Code) new mojo.km.persistence.Reference(
						descriptorSourceId, Code.class,
						"DESCRIPTOR_SOURCE").getObject();
			} catch (Throwable t) {
				descriptorSource = null;
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initDriversLicenseClass() {
		if (driversLicenseClass == null) {
			try {
				driversLicenseClass = (Code) new mojo.km.persistence.Reference(
						driversLicenseClassId, Code.class,
						"DRIVERS_LICENSE_CLASS").getObject();
			} catch (Throwable t) {
				driversLicenseClass = null;
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initDriversLicenseState() {
		if (driversLicenseState == null) {
			try {
				driversLicenseState = (Code) new mojo.km.persistence.Reference(
						driversLicenseStateId, Code.class,
						PDCodeTableConstants.STATE_ABBR).getObject();
			} catch (Throwable t) {
				driversLicenseState = null;
			}
		}
	}

	/**
	 * Initialize class relationship implementation for pd.contact.Employer
	 */
	private void initEmployers() {
		if (employers == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			try {
				employers = new mojo.km.persistence.ArrayList(
						Employer.class, "partyId",
						(mojo.km.persistence.PersistentObject) this,
						"employers");
			} catch (Throwable t) {
				employers = new java.util.ArrayList();
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initEthnicity() {
		if (ethnicity == null) {
			try {
				ethnicity = (Code) new mojo.km.persistence.Reference(
						ethnicityId, Code.class, PDCodeTableConstants.ETHNICITY)
						.getObject();
			} catch (Throwable t) {
				ethnicity = null;
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initEyeColor() {
		if (eyeColor == null) {
			try {
				eyeColor = (Code) new mojo.km.persistence.Reference(
						eyeColorId, Code.class, PDCodeTableConstants.EYE_COLOR)
						.getObject();
			} catch (Throwable t) {
				eyeColor = null;
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initHaircolor() {
		if (haircolor == null) {
			try {
				haircolor = (Code) new mojo.km.persistence.Reference(
						haircolorId, Code.class, PDCodeTableConstants.HAIR_COLOR)
						.getObject();
			} catch (Throwable t) {
				haircolor = null;
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initIdCardState() {
		if (idCardState == null) {
			try {
				idCardState = (Code) new mojo.km.persistence.Reference(
						idCardStateId, Code.class, PDCodeTableConstants.STATE_ABBR)
						.getObject();
			} catch (Throwable t) {
				idCardState = null;
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initLanguage() {
		if (language == null) {
			try {
				language = (Code) new mojo.km.persistence.Reference(
						languageId, Code.class, PDCodeTableConstants.LANGUAGE)
						.getObject();
			} catch (Throwable t) {
				language = null;
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initMaritalStatus() {
		if (maritalStatus == null) {
			try {
				maritalStatus = (Code) new mojo.km.persistence.Reference(
						maritalStatusId, Code.class,
						PDCodeTableConstants.MARITAL_STATUS).getObject();
			} catch (Throwable t) {
				maritalStatus = null;
			}
		}
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.contact.party.MiscellaneousId
	 */
	private void initMiscellaneousNums() {
		if (miscellaneousNums == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			try {
				miscellaneousNums = new mojo.km.persistence.ArrayList(
						MiscellaneousId.class, "partyId",
						(mojo.km.persistence.PersistentObject) this,
						"miscellaneousNums");
			} catch (Throwable t) {
				miscellaneousNums = new java.util.ArrayList();
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initNameSource() {
		if (nameSource == null) {
			try {
				nameSource = (Code) new mojo.km.persistence.Reference(
						nameSourceId, Code.class, "NAME_SOURCE")
						.getObject();
			} catch (Throwable t) {
				nameSource = null;
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initNameType() {
		if (nameType == null) {
			try {
				nameType = (Code) new mojo.km.persistence.Reference(
						nameTypeId, Code.class, "ALIAS_NAME_TYPE")
						.getObject();
			} catch (Throwable t) {
				nameType = null;
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initNextOfKinRelationship() {
		if (nextOfKinRelationship == null) {
			try {
				nextOfKinRelationship = (Code) new mojo.km.persistence.Reference(
						nextOfKinRelationshipId, Code.class,
						"RELATIONSHIP_TO_PARTY").getObject();
			} catch (Throwable t) {
				nextOfKinRelationship = null;
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initPlaceOfBirthState() {
		if (placeOfBirthState == null) {
			try {
				placeOfBirthState = (Code) new mojo.km.persistence.Reference(
						placeOfBirthStateId, Code.class,
						PDCodeTableConstants.PLACE_OF_BIRTH).getObject();
			} catch (Throwable t) {
				placeOfBirthState = null;
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initRace() {
		if (race == null) {
			try {
				race = (Code) new mojo.km.persistence.Reference(
						raceId, Code.class, PDCodeTableConstants.RACE).getObject();
			} catch (Throwable t) {
				race = null;
			}
		}
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.codetable.person.ScarsMarksTattoosCode
	 */
	private void initScarsMarks() {
		if (scarsMarks == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			try {
//				scarsMarks = new mojo.km.persistence.ArrayList(
//						pd.codetable.person.ScarsMarksTattoosCode.class,
//						"partyId", "" + getOID());
				scarsMarks = new mojo.km.persistence.ArrayList(
					PartyScarsMarksScarsMarksTattoosCode.class,
					"OID", "" + this.getOID());
						
			} catch (Throwable t) {
				scarsMarks = new java.util.ArrayList();
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initSex() {
		if (sex == null) {
			try {
				sex = (Code) new mojo.km.persistence.Reference(
						sexId, Code.class, "SEX").getObject();
			} catch (Throwable t) {
				sex = null;
			}
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initSkinTone() {
		if (skinTone == null) {
			try {
				skinTone = (Code) new mojo.km.persistence.Reference(
						skinToneId, Code.class, PDCodeTableConstants.SKIN_TONE)
						.getObject();
			} catch (Throwable t) {
				skinTone = null;
			}
		}
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.codetable.person.ScarsMarksTattoosCode
	 */
	private void initTattoos() {
		if (tattoos == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			try {
//				tattoos = new mojo.km.persistence.ArrayList(
//						pd.codetable.person.ScarsMarksTattoosCode.class,
//						"partyId", "" + getOID());
				tattoos = new mojo.km.persistence.ArrayList(
						PartyTattoosScarsMarksTattoosCode.class,
						"OID", "" + this.getOID());

			} catch (Throwable t) {
				tattoos = new java.util.ArrayList();
			}
		}
	}

	/**
	 * insert a pd.address.Address into class relationship collection.
	 * 
	 * @param anObject
	 */
	public void insertAddresses(Address anObject) {
		initAddresses();
		addresses.add(anObject);
	}

	/**
	 * insert a pd.contact.party.Capacity into class relationship collection.
	 * 
	 * @param anObject
	 */
	public void insertCapacities(Capacity anObject) {
		initCapacities();
		capacities.add(anObject);
	}

	/**
	 * insert a pd.contact.Employer into class relationship collection.
	 * 
	 * @param anObject
	 */
	public void insertEmployers(Employer anObject) {
		initEmployers();
		employers.add(anObject);
	}

	/**
	 * insert a pd.contact.party.MiscellaneousId into class relationship
	 * collection.
	 * 
	 * @param anObject
	 */
	public void insertMiscellaneousNums(
			MiscellaneousId anObject) {
		initMiscellaneousNums();
		miscellaneousNums.add(anObject);
	}

	/**
	 * insert a pd.codetable.person.ScarsMarksTattoosCode into class
	 * relationship collection.
	 * 
	 * @param anObject
	 */
	public void insertScarsMarks(
			pd.codetable.person.ScarsMarksTattoosCode anObject) {
		initScarsMarks();
		scarsMarks.add(anObject);
	}

	/**
	 * insert a pd.codetable.person.ScarsMarksTattoosCode into class
	 * relationship collection.
	 * 
	 * @param anObject
	 */
	public void insertTattoos(pd.codetable.person.ScarsMarksTattoosCode anObject) {
		initTattoos();
		tattoos.add(anObject);
	}

	/**
	 * Removes a pd.address.Address from class relationship collection.
	 * 
	 * @param anObject
	 */
	public void removeAddresses(Address anObject) {
		initAddresses();
		addresses.remove(anObject);
	}

	/**
	 * Removes a pd.contact.party.Capacity from class relationship collection.
	 * 
	 * @param anObject
	 */
	public void removeCapacities(Capacity anObject) {
		initCapacities();
		capacities.remove(anObject);
	}

	/**
	 * Removes a pd.contact.Employer from class relationship collection.
	 * 
	 * @param anObject
	 */
	public void removeEmployers(Employer anObject) {
		initEmployers();
		employers.remove(anObject);
	}

	/**
	 * Removes a pd.contact.party.MiscellaneousId from class relationship
	 * collection.
	 * 
	 * @param anObject
	 */
	public void removeMiscellaneousNums(
			MiscellaneousId anObject) {
		initMiscellaneousNums();
		miscellaneousNums.remove(anObject);
	}

	/**
	 * Removes a pd.codetable.person.ScarsMarksTattoosCode from class
	 * relationship collection.
	 * 
	 * @param anObject
	 */
	public void removeScarsMarks(
			pd.codetable.person.ScarsMarksTattoosCode anObject) {
		initScarsMarks();
		scarsMarks.remove(anObject);
	}

	/**
	 * Removes a pd.codetable.person.ScarsMarksTattoosCode from class
	 * relationship collection.
	 * 
	 * @param anObject
	 */
	public void removeTattoos(pd.codetable.person.ScarsMarksTattoosCode anObject) {
		initTattoos();
		tattoos.remove(anObject);
	}

	/**
	 * Sets the value of the afisNum property.
	 * 
	 * @param aAfisNum
	 *            the new value of the afisNum property
	 */
	public void setAfisNum(String aAfisNum) {
		if (this.afisNum == null || !this.afisNum.equals(aAfisNum)) {
			markModified();
		}
		afisNum = aAfisNum;
	}

	/**
	 * Sets the value of the age property.
	 * 
	 * @param aAge
	 *            the new value of the age property
	 */
	public void setAge(String aAge) {
		if (this.age == null || !this.age.equals(aAge)) {
			markModified();
		}
		age = aAge;
	}

	/**
	 * Sets the value of the alienRegistrationNum property.
	 * 
	 * @param aAlienRegistrationNum
	 *            the new value of the alienRegistrationNum property
	 */
	public void setAlienRegistrationNum(String aAlienRegistrationNum) {
		if (this.alienRegistrationNum == null
				|| !this.alienRegistrationNum.equals(aAlienRegistrationNum)) {
			markModified();
		}
		alienRegistrationNum = aAlienRegistrationNum;
	}

	/**
	 * Sets the value of the barNum property.
	 * 
	 * @param aBarNum
	 *            the new value of the barNum property
	 */
	public void setBarNum(String aBarNum) {
		if (this.barNum == null || !this.barNum.equals(aBarNum)) {
			markModified();
		}
		barNum = aBarNum;
	}

	/**
	 * set the type reference for class member build
	 * 
	 * @param aBuild
	 */
	public void setBuild(Code aBuild) {
		if (this.build == null || !this.build.equals(aBuild)) {
			markModified();
		}
		if (build.getOID() == null) {
			new mojo.km.persistence.Home().bind(aBuild);
		}
		setBuildId("" + aBuild.getOID());
		this.build = (Code) new mojo.km.persistence.Reference(
				aBuild).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param aBuildId
	 */
	public void setBuildId(String aBuildId) {
		if (this.buildId == null || !this.buildId.equals(aBuildId)) {
			markModified();
		}
		build = null;
		this.buildId = aBuildId;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param aCapacityId
	 */
	public void setCapacityId(String aCapacityId) {
		if (this.capacityId == null || !this.capacityId.equals(aCapacityId)) {
			markModified();
		}
		build = null;
		this.capacityId = aCapacityId;
	}

	/**
	 * set the type reference for class member citizenship
	 * 
	 * @param aCitizenship
	 */
	public void setCitizenship(Code aCitizenship) {
		if (this.citizenship == null || !this.citizenship.equals(aCitizenship)) {
			markModified();
		}
		if (aCitizenship.getOID() == null) {
			new mojo.km.persistence.Home().bind(aCitizenship);
		}
		setCitizenshipId("" + aCitizenship.getOID());
		this.citizenship = (Code) new mojo.km.persistence.Reference(
				aCitizenship).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param aCitizenshipId
	 */
	public void setCitizenshipId(String aCitizenshipId) {
		if (this.citizenshipId == null
				|| !this.citizenshipId.equals(aCitizenshipId)) {
			markModified();
		}
		citizenship = null;
		this.citizenshipId = aCitizenshipId;
	}

	/**
	 * @param aCjisNum
	 */
	public void setCjisNum(String aCjisNum) {
		if (this.cjisNum == null || !this.cjisNum.equals(aCjisNum)) {
			markModified();
		}
		cjisNum = null;
		this.cjisNum = aCjisNum;
	}

	/**
	 * @param aConnectionId
	 */
	public void setConnectionId(String aConnectionId) {
		if (this.connectionId == null
				|| !this.connectionId.equals(aConnectionId)) {
			markModified();
		}
		connectionId = null;
		this.connectionId = aConnectionId;

	}

	/**
	 * set the type reference for class member createAgency
	 * 
	 * @param theCreateAgency
	 */
	public void setCreateAgency(Agency theCreateAgency) {
		/*if (this.createAgency == null
				|| !this.createAgency.equals(theCreateAgency)) {
			markModified();
		}
		if (theCreateAgency.getOID() == null) {
			new mojo.km.persistence.Home().bind(theCreateAgency);
		}*/
		setCreateAgencyId("" + theCreateAgency.getAgencyId());
		this.createAgency = theCreateAgency;//(pd.contact.agency.Agency) new mojo.km.persistence.Reference(
				//theCreateAgency).getObject();
	}

	/**
	 * Set the reference value to class :: pd.contact.agency.Agency
	 * 
	 * @param theCreateAgencyId
	 */
	public void setCreateAgencyId(String theCreateAgencyId) {
		if (this.createAgencyId == null
				|| !this.createAgencyId.equals(theCreateAgencyId)) {
			markModified();
		}
		createAgency = null;
		this.createAgencyId = theCreateAgencyId;
	}

	/**
	 * Sets the value of the currentNameInd property.
	 * 
	 * @param aCurrentNameInd
	 *            the new value of the currentNameInd property
	 */
	public void setCurrentNameInd(String aCurrentNameInd) {
		if (this.currentNameInd == null
				|| !this.currentNameInd.equals(aCurrentNameInd)) {
			markModified();
		}
		currentNameInd = aCurrentNameInd;
	}

	/**
	 * Sets the value of the dateOfBirth property.
	 * 
	 * @param aDateOfBirth
	 *            the new value of the dateOfBirth property
	 */
	public void setDateOfBirth(Date aDateOfBirth) {
		if (this.dateOfBirth == null || !this.dateOfBirth.equals(aDateOfBirth)) {
			markModified();
		}
		dateOfBirth = aDateOfBirth;
	}

	/**
	 * set the type reference for class member descriptorSource
	 * 
	 * @param theDescriptorSource
	 */
	public void setDescriptorSource(Code theDescriptorSource) {
		if (this.descriptorSource == null
				|| !this.descriptorSource.equals(theDescriptorSource)) {
			markModified();
		}
		if (theDescriptorSource.getOID() == null) {
			new mojo.km.persistence.Home().bind(theDescriptorSource);
		}
		setDescriptorSourceId("" + theDescriptorSource.getOID());
		this.descriptorSource = (Code) new mojo.km.persistence.Reference(
				theDescriptorSource).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param theDescriptorSourceId
	 */
	public void setDescriptorSourceId(String theDescriptorSourceId) {
		if (this.descriptorSourceId == null
				|| !this.descriptorSourceId.equals(theDescriptorSourceId)) {
			markModified();
		}
		descriptorSource = null;
		this.descriptorSourceId = theDescriptorSourceId;
	}

	/**
	 * set the type reference for class member driversLicenseClass
	 * 
	 * @param theDriversLicenseClass
	 */
	public void setDriversLicenseClass(Code theDriversLicenseClass) {
		if (this.driversLicenseClass == null
				|| !this.driversLicenseClass.equals(theDriversLicenseClass)) {
			markModified();
		}
		if (theDriversLicenseClass.getOID() == null) {
			new mojo.km.persistence.Home().bind(theDriversLicenseClass);
		}
		setDriversLicenseClassId("" + theDriversLicenseClass.getOID());
		this.driversLicenseClass = (Code) new mojo.km.persistence.Reference(
				theDriversLicenseClass).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param theDriversLicenseClassId
	 */
	public void setDriversLicenseClassId(String theDriversLicenseClassId) {
		if (this.driversLicenseClassId == null
				|| !this.driversLicenseClassId.equals(theDriversLicenseClassId)) {
			markModified();
		}
		driversLicenseClass = null;
		this.driversLicenseClassId = theDriversLicenseClassId;
	}

	/**
	 * Sets the value of the driversLicenseExpirationYear property.
	 * 
	 * @param aDriversLicenseExpirationYear
	 *            the new value of the driversLicenseExpirationYear property
	 */
	public void setDriversLicenseExpirationYear(
			String aDriversLicenseExpirationYear) {
		if (this.driversLicenseExpirationYear == null
				|| !this.driversLicenseExpirationYear
						.equals(aDriversLicenseExpirationYear)) {
			markModified();
		}
		driversLicenseExpirationYear = aDriversLicenseExpirationYear;
	}

	/**
	 * Sets the value of the driversLicenseNum property.
	 * 
	 * @param aDriversLicenseNum
	 *            the new value of the driversLicenseNum property
	 */
	public void setDriversLicenseNum(String aDriversLicenseNum) {
		if (this.driversLicenseNum == null
				|| !this.driversLicenseNum.equals(aDriversLicenseNum)) {
			markModified();
		}
		driversLicenseNum = aDriversLicenseNum;
	}

	/**
	 * set the type reference for class member driversLicenseState
	 * 
	 * @param aDriversLicenseState
	 */
	public void setDriversLicenseState(Code aDriversLicenseState) {
		if (this.driversLicenseState == null
				|| !this.driversLicenseState.equals(aDriversLicenseState)) {
			markModified();
		}
		if (aDriversLicenseState.getOID() == null) {
			new mojo.km.persistence.Home().bind(aDriversLicenseState);
		}
		setDriversLicenseStateId("" + aDriversLicenseState.getOID());
		this.driversLicenseState = (Code) new mojo.km.persistence.Reference(
				aDriversLicenseState).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param aDriversLicenseStateId
	 */
	public void setDriversLicenseStateId(String aDriversLicenseStateId) {
		if (this.driversLicenseStateId == null
				|| !this.driversLicenseStateId.equals(aDriversLicenseStateId)) {
			markModified();
		}
		driversLicenseState = null;
		this.driversLicenseStateId = aDriversLicenseStateId;
	}

	/**
	 * set the type reference for class member ethnicity
	 * 
	 * @param aEhnicity
	 */
	public void setEthnicity(Code aEhnicity) {
		if (this.ethnicity == null || !this.ethnicity.equals(aEhnicity)) {
			markModified();
		}
		if (ethnicity.getOID() == null) {
			new mojo.km.persistence.Home().bind(aEhnicity);
		}
		setEthnicityId("" + aEhnicity.getOID());
		this.ethnicity = (Code) new mojo.km.persistence.Reference(
				aEhnicity).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param aEthnicityId
	 */
	public void setEthnicityId(String aEthnicityId) {
		if (this.ethnicityId == null || !this.ethnicityId.equals(aEthnicityId)) {
			markModified();
		}
		ethnicity = null;
		this.ethnicityId = aEthnicityId;
	}

	/**
	 * set the type reference for class member eyeColor
	 * 
	 * @param aEyeColor
	 */
	public void setEyeColor(Code aEyeColor) {
		if (this.eyeColor == null || !this.eyeColor.equals(aEyeColor)) {
			markModified();
		}
		if (aEyeColor.getOID() == null) {
			new mojo.km.persistence.Home().bind(aEyeColor);
		}
		setEyeColorId("" + aEyeColor.getOID());
		this.eyeColor = (Code) new mojo.km.persistence.Reference(
				aEyeColor).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param anEyeColorId
	 */
	public void setEyeColorId(String anEyeColorId) {
		if (this.eyeColorId == null || !this.eyeColorId.equals(anEyeColorId)) {
			markModified();
		}
		eyeColor = null;
		this.eyeColorId = anEyeColorId;
	}

	/**
	 * Sets the value of the fbiNum property.
	 * 
	 * @param aFbiNum
	 *            the new value of the fbiNum property
	 */
	public void setFbiNum(String aFbiNum) {
		if (this.fbiNum == null || !this.fbiNum.equals(aFbiNum)) {
			markModified();
		}
		fbiNum = aFbiNum;
	}

	/**
	 * Sets the value of the fingerprintInd property.
	 * 
	 * @param aFingerprintInd
	 *            the new value of the fingerprintInd property
	 */
	public void setFingerprintInd(String aFingerprintInd) {
		if (this.fingerprintInd == null
				|| !this.fingerprintInd.equals(aFingerprintInd)) {
			markModified();
		}
		fingerprintInd = aFingerprintInd;
	}

	/**
	 * set the type reference for class member haircolor
	 * 
	 * @param aHairColor
	 */
	public void setHaircolor(Code aHairColor) {
		if (this.haircolor == null || !this.haircolor.equals(aHairColor)) {
			markModified();
		}
		if (aHairColor.getOID() == null) {
			new mojo.km.persistence.Home().bind(aHairColor);
		}
		setHaircolorId("" + aHairColor.getOID());
		this.haircolor = (Code) new mojo.km.persistence.Reference(
				aHairColor).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param aHairColorId
	 */
	public void setHaircolorId(String aHairColorId) {
		if (this.haircolorId == null || !this.haircolorId.equals(aHairColorId)) {
			markModified();
		}
		haircolor = null;
		this.haircolorId = aHairColorId;
	}

	/**
	 * Sets the value of the height property.
	 * 
	 * @param aHeight
	 *            the new value of the height property
	 */
	public void setHeight(String aHeight) {
		if (this.height == null || !this.height.equals(aHeight)) {
			markModified();
		}
		height = aHeight;
	}

	/**
	 * Sets the value of the idCardNum property.
	 * 
	 * @param aIdCardNum
	 *            the new value of the idCardNum property
	 */
	public void setIdCardNum(String aIdCardNum) {
		if (this.idCardNum == null || !this.idCardNum.equals(aIdCardNum)) {
			markModified();
		}
		idCardNum = aIdCardNum;
	}

	/**
	 * set the type reference for class member idCardState
	 * 
	 * @param anIdCardState
	 */
	public void setIdCardState(Code anIdCardState) {
		if (this.idCardState == null || !this.idCardState.equals(anIdCardState)) {
			markModified();
		}
		if (anIdCardState.getOID() == null) {
			new mojo.km.persistence.Home().bind(anIdCardState);
		}
		setIdCardStateId("" + anIdCardState.getOID());
		this.idCardState = (Code) new mojo.km.persistence.Reference(
				anIdCardState).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param anIdCardStateId
	 */
	public void setIdCardStateId(String anIdCardStateId) {
		if (this.idCardState == null
				|| !this.idCardState.equals(anIdCardStateId)) {
			markModified();
		}
		idCardState = null;
		this.idCardStateId = anIdCardStateId;
	}

	/**
	 * Sets the value of the inactiveInd property.
	 * 
	 * @param aInactiveInd
	 *            the new value of the inactiveInd property
	 */
	public void setInactiveInd(String aInactiveInd) {
		if (this.inactiveInd == null || !this.inactiveInd.equals(aInactiveInd)) {
			markModified();
		}
		inactiveInd = aInactiveInd;
	}

	/**
	 * set the type reference for class member language
	 * 
	 * @param aLanguage
	 */
	public void setLanguage(Code aLanguage) {
		if (this.language == null || !this.language.equals(aLanguage)) {
			markModified();
		}
		if (aLanguage.getOID() == null) {
			new mojo.km.persistence.Home().bind(aLanguage);
		}
		setLanguageId("" + aLanguage.getOID());
		this.language = (Code) new mojo.km.persistence.Reference(
				aLanguage).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param aLanguageId
	 */
	public void setLanguageId(String aLanguageId) {
		if (this.languageId == null || !this.languageId.equals(aLanguageId)) {
			markModified();
		}
		language = null;
		this.languageId = aLanguageId;
	}

	/**
	 * set the type reference for class member maritalStatus
	 * 
	 * @param aMaritalStatus
	 */
	public void setMaritalStatus(Code aMaritalStatus) {
		if (this.maritalStatus == null
				|| !this.maritalStatus.equals(aMaritalStatus)) {
			markModified();
		}
		if (aMaritalStatus.getOID() == null) {
			new mojo.km.persistence.Home().bind(aMaritalStatus);
		}
		setMaritalStatusId("" + aMaritalStatus.getOID());
		this.maritalStatus = (Code) new mojo.km.persistence.Reference(
				aMaritalStatus).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param aMaritalStatusId
	 */
	public void setMaritalStatusId(String aMaritalStatusId) {
		if (this.maritalStatus == null
				|| !this.maritalStatus.equals(aMaritalStatusId)) {
			markModified();
		}
		maritalStatus = null;
		this.maritalStatusId = aMaritalStatusId;
	}

	/**
	 * set the type reference for class member nameSource
	 * 
	 * @param aNameSource
	 */
	public void setNameSource(Code aNameSource) {
		if (this.nameSource == null || !this.nameSource.equals(aNameSource)) {
			markModified();
		}
		if (aNameSource.getOID() == null) {
			new mojo.km.persistence.Home().bind(aNameSource);
		}
		setNameSourceId("" + aNameSource.getOID());
		this.nameSource = (Code) new mojo.km.persistence.Reference(
				aNameSource).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param aNameSourceId
	 */
	public void setNameSourceId(String aNameSourceId) {
		if (this.nameSourceId == null
				|| !this.nameSourceId.equals(aNameSourceId)) {
			markModified();
		}
		nameSource = null;
		this.nameSourceId = aNameSourceId;
	}

	/**
	 * set the type reference for class member nameType
	 * 
	 * @param aNameType
	 */
	public void setNameType(Code aNameType) {
		if (this.nameType == null || !this.nameType.equals(aNameType)) {
			markModified();
		}
		if (nameType.getOID() == null) {
			new mojo.km.persistence.Home().bind(aNameType);
		}
		setNameTypeId("" + aNameType.getOID());
		this.nameType = (Code) new mojo.km.persistence.Reference(
				aNameType).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param aNameTypeId
	 */
	public void setNameTypeId(String aNameTypeId) {
		if (this.nameTypeId == null || !this.nameTypeId.equals(aNameTypeId)) {
			markModified();
		}
		nameType = null;
		this.nameTypeId = aNameTypeId;
	}

	/**
	 * Sets the value of the nextOfKinFirstName property.
	 * 
	 * @param aNextOfKinFirstName
	 *            the new value of the nextOfKinFirstName property
	 */
	public void setNextOfKinFirstName(String aNextOfKinFirstName) {
		if (this.nextOfKinFirstName == null
				|| !this.nextOfKinFirstName.equals(aNextOfKinFirstName)) {
			markModified();
		}
		nextOfKinFirstName = aNextOfKinFirstName;
	}

	/**
	 * Sets the value of the nextOfKinLastName property.
	 * 
	 * @param aNextOfKinLastName
	 *            the new value of the nextOfKinLastName property
	 */
	public void setNextOfKinLastName(String aNextOfKinLastName) {
		if (this.nextOfKinLastName == null
				|| !this.nextOfKinLastName.equals(aNextOfKinLastName)) {
			markModified();
		}
		nextOfKinLastName = aNextOfKinLastName;
	}

	/**
	 * Sets the value of the nextOfKinMiddleName property.
	 * 
	 * @param aNextOfKinMiddleName
	 *            the new value of the nextOfKinMiddleName property
	 */
	public void setNextOfKinMiddleName(String aNextOfKinMiddleName) {
		if (this.nextOfKinMiddleName == null
				|| !this.nextOfKinMiddleName.equals(aNextOfKinMiddleName)) {
			markModified();
		}
		nextOfKinMiddleName = aNextOfKinMiddleName;
	}

	/**
	 * set the type reference for class member nextOfKinRelationship
	 * 
	 * @param aNextOfKinRelationship
	 */
	public void setNextOfKinRelationship(
			Code aNextOfKinRelationship) {
		if (this.nextOfKinRelationship == null
				|| !this.nextOfKinRelationship.equals(aNextOfKinRelationship)) {
			markModified();
		}
		if (aNextOfKinRelationship.getOID() == null) {
			new mojo.km.persistence.Home().bind(nextOfKinRelationship);
		}
		setNextOfKinRelationshipId("" + aNextOfKinRelationship.getOID());
		this.nextOfKinRelationship = (Code) new mojo.km.persistence.Reference(
				aNextOfKinRelationship).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param aNextOfKinRelationshipId
	 */
	public void setNextOfKinRelationshipId(String aNextOfKinRelationshipId) {
		if (this.nextOfKinRelationship == null
				|| !this.nextOfKinRelationship.equals(aNextOfKinRelationshipId)) {
			markModified();
		}
		nextOfKinRelationship = null;
		this.nextOfKinRelationshipId = aNextOfKinRelationshipId;
	}

	/**
	 * Sets the value of the placeOfBirth property.
	 * 
	 * @param aPlaceOfBirth
	 *            the new value of the placeOfBirth property
	 */
	public void setPlaceOfBirth(String aPlaceOfBirth) {
		if (this.placeOfBirth == null
				|| !this.placeOfBirth.equals(aPlaceOfBirth)) {
			markModified();
		}
		placeOfBirth = aPlaceOfBirth;
	}

	/**
	 * set the type reference for class member placeOfBirthState
	 * 
	 * @param aPlaceOfBirthState
	 */
	public void setPlaceOfBirthState(Code aPlaceOfBirthState) {
		if (this.placeOfBirthState == null
				|| !this.placeOfBirthState.equals(aPlaceOfBirthState)) {
			markModified();
		}
		if (aPlaceOfBirthState.getOID() == null) {
			new mojo.km.persistence.Home().bind(aPlaceOfBirthState);
		}
		setPlaceOfBirthStateId("" + aPlaceOfBirthState.getOID());
		this.placeOfBirthState = (Code) new mojo.km.persistence.Reference(
				aPlaceOfBirthState).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param aPlaceOfBirthStateId
	 */
	public void setPlaceOfBirthStateId(String aPlaceOfBirthStateId) {
		if (this.placeOfBirthState == null
				|| !this.placeOfBirthState.equals(aPlaceOfBirthStateId)) {
			markModified();
		}
		placeOfBirthState = null;
		this.placeOfBirthStateId = aPlaceOfBirthStateId;
	}

	/**
	 * set the type reference for class member race
	 * 
	 * @param aRace
	 */
	public void setRace(Code aRace) {
		if (this.race == null || !this.race.equals(aRace)) {
			markModified();
		}
		if (aRace.getOID() == null) {
			new mojo.km.persistence.Home().bind(aRace);
		}
		setRaceId("" + aRace.getOID());
		this.race = (Code) new mojo.km.persistence.Reference(aRace)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param aRaceId
	 */
	public void setRaceId(String aRaceId) {
		if (this.raceId == null || !this.raceId.equals(aRaceId)) {
			markModified();
		}
		race = null;
		this.raceId = aRaceId;
	}

	/**
	 * set the type reference for class member sex
	 * 
	 * @param aSex
	 */
	public void setSex(Code aSex) {
		if (this.sex == null || !this.sex.equals(aSex)) {
			markModified();
		}
		if (aSex.getOID() == null) {
			new mojo.km.persistence.Home().bind(aSex);
		}
		setSexId("" + aSex.getOID());
		this.sex = (Code) new mojo.km.persistence.Reference(aSex)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param aSexId
	 */
	public void setSexId(String aSexId) {
		if (this.sexId == null || !this.sexId.equals(aSexId)) {
			markModified();
		}
		sex = null;
		this.sexId = aSexId;
	}

	/**
	 * Sets the value of the sheriffOfficeNum property.
	 * 
	 * @param aSheriffOfficeNum
	 *            the new value of the sheriffOfficeNum property
	 */
	public void setSheriffOfficeNum(String aSheriffOfficeNum) {
		if (this.sheriffOfficeNum == null
				|| !this.sheriffOfficeNum.equals(aSheriffOfficeNum)) {
			markModified();
		}
		sheriffOfficeNum = aSheriffOfficeNum;
	}

	/**
	 * Sets the value of the sidNum property.
	 * 
	 * @param aSidNum
	 *            the new value of the sidNum property
	 */
	public void setSidNum(String aSidNum) {
		if (this.sidNum == null || !this.sidNum.equals(aSidNum)) {
			markModified();
		}
		sidNum = aSidNum;
	}

	/**
	 * set the type reference for class member skinTone
	 * 
	 * @param aSkinTone
	 */
	public void setSkinTone(Code aSkinTone) {
		if (this.skinTone == null || !this.skinTone.equals(aSkinTone)) {
			markModified();
		}
		if (aSkinTone.getOID() == null) {
			new mojo.km.persistence.Home().bind(aSkinTone);
		}
		setSkinToneId("" + aSkinTone.getOID());
		this.skinTone = (Code) new mojo.km.persistence.Reference(
				aSkinTone).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * 
	 * @param aSkinToneId
	 */
	public void setSkinToneId(String aSkinToneId) {
		if (this.skinToneId == null || !this.skinToneId.equals(aSkinToneId)) {
			markModified();
		}
		skinTone = null;
		this.skinToneId = aSkinToneId;
	}

	/**
	 * Sets the value of the spn property.
	 * 
	 * @param aSpn
	 *            the new value of the spn property
	 */
	public void setSpn(String aSpn) {
		if (this.spn == null || !this.spn.equals(aSpn)) {
			markModified();
		}
		spn = aSpn;
	}

	/**
	 * Sets the value of the ssn property.
	 * 
	 * @param aSsn
	 *            the new value of the ssn property
	 */
	public void setSsn(String aSsn) {
		if (this.ssn == null || !this.ssn.equals(aSsn)) {
			markModified();
		}
		ssn = aSsn;
	}

	/**
	 * Sets the value of the weight property.
	 * 
	 * @param aWeight
	 *            the new value of the weight property
	 */
	public void setWeight(String aWeight) {
		if (this.weight == null || !this.weight.equals(aWeight)) {
			markModified();
		}
		weight = aWeight;
	}

	/**
	 * Sets the value of the namePtr property.
	 * 
	 * @param aNamePtr
	 *            the new value of the namePtr property
	 */
	public void setNamePtr(String aNamePtr) {
		if (this.namePtr == null || !this.namePtr.equals(aNamePtr)) {
			markModified();
		}
		namePtr = aNamePtr;
	}

	/**
	 * Sets the value of the nameSeq property.
	 * 
	 * @param aNameSeq
	 *            the new value of the nameSeq property
	 */
	public void setNameSeq(String aNameSeq) {
		if (this.nameSeq == null || !this.nameSeq.equals(aNameSeq)) {
			markModified();
		}
		nameSeq = aNameSeq;
	}

	/**
	 * @param party
	 * @return
	 */
	public PartyListResponseEvent getPartyListResponseEvent() {
		PartyListResponseEvent re = new PartyListResponseEvent();
		re.setConnectionId(this.getConnectionId());
		re.setDateOfBirth(this.getDateOfBirth());
		if (this.getLastName() != null) {
			Name name = new Name(this.getFirstName(), this.getMiddleName(),
					this.getLastName());
			re.setName(name.getFormattedName());
		}
		re.setOid(this.getOID().toString());
		re.setRaceId(this.getRaceId());
		re.setSexId(this.getSexId());
		re.setSpn(this.getSpn());
		re.setSsn(new SocialSecurityBean(this.getSsn()));
		re.setSid(this.getSidNum());

		return re;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		fetch();
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String aName) {
		if (this.name == null || !this.name.equals(aName)) {
			markModified();
		}
		this.name = aName;
	}
	/**
	 * @param deptEvent
	 * @param class1
	 * @return
	 */
	public static MetaDataResponseEvent findMeta(IEvent anEvent) {
		IHome home = new Home();
		MetaDataResponseEvent iter = home.findMeta(anEvent, Party.class);
		return iter;
	}	
	
	public static Map getPartiesbyIds(String defendantIds){
        GetTasksByDefendantIdsEvent tEvent = new GetTasksByDefendantIdsEvent();
		tEvent.setDefendantIds(defendantIds);
		Iterator partyIterator = Party.findAll(tEvent);
		Map partyMap = new HashMap();
		while(partyIterator.hasNext()){
			Party party = (Party) partyIterator.next();
			if(!partyMap.containsKey(party.getSpn())){
				partyMap.put(party.getSpn(), party);
			}
		}
		return partyMap;
	}

}