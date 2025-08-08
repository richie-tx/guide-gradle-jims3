package pd.juvenilecase.family;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.juvenilecase.reply.AssociatedJuvenilesResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberListResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import naming.PDCodeTableConstants;
import pd.address.Address;
import pd.codetable.Code;
import pd.security.PDSecurityHelper;

/**
* @version 1.0.0
* @referencedType pd.codetable.Code
* @contextKey LANGUAGE
* @author Anand Thorat
* @detailerDoNotGenerate false
*/
public class FamilyMember extends mojo.km.persistence.PersistentObject {
	private String familyMemberId;
	private String suspiciousMember;
	private String comments;
	private String causeofDeathId;
	private String alienNum;
	private String sexId;
	private String driverLicenseStateId;
	private String psportIssueCountryId; //added for passport details
	private String driverLicenseNum;
	private String suspiciousMemberMatch; //added for US 181437
	/**
	* Properties for traits
	* @referencedType pd.juvenilecase.family.FamilyMemberTrait
	* @detailerDoNotGenerate false
	*/
	private Collection traits;
	private String idCardNum;
	private String psportNum; //added for passport details
	private String secondaryLanguageId;
	private Code isUSCitizen;
	private String isUSCitizenId;
	private boolean isDeceased;
	private String sidNum;
	/**
	* Properties for addresses
	* @associationType simple
	* @referencedType pd.address.Address
	* @detailerDoNotGenerate true
	*/
	private Collection addresses;
	private String primaryLanguageId;
	/**
	* Properties for contacts
	* @referencedType pd.Contact.Contact
	* @detailerDoNotGenerate true
	*/
	private Collection contacts;
	/**
	* @referencedType pd.codetable.Code
	* @contextKey PLACE_OF_BIRTH_COUNTRY
	* @detailerDoNotGenerate true
	*/
	private Code nationality = null;
	/**
	* @referencedType pd.codetable.Code
	* @contextKey STATE
	* @detailerDoNotGenerate true
	*/
	private Code idCardState = null;
	/**
	* Properties for phoneNumbers
	* @referencedType pd.juvenilecase.family.FamilyMemberPhone
	* @detailerDoNotGenerate true
	*/
	private Collection phoneNumbers;
	
	/**
	* Properties for emailAddresses
	* @referencedType pd.juvenilecase.family.FamilyMemberEmail
	* @detailerDoNotGenerate true
	*/
	private Collection emailAddresses;
	/**
	* @referencedType pd.codetable.Code
	* @contextKey LANGUAGE
	* @detailerDoNotGenerate true
	*/
	public Code primaryLanguage = null;
	private String lastName;
	/**
	* @referencedType pd.codetable.Code
	* @contextKey SEX
	* @detailerDoNotGenerate true
	*/
	public Code sex = null;
	/**
	* @referencedType pd.codetable.Code
	* @contextKey STATE
	* @detailerDoNotGenerate true
	*/
	private Code driverLicenseState = null;
	/**
	* @referencedType pd.codetable.Code
	* @contextKey Country
	* @detailerDoNotGenerate true
	*/
	private Code psportIssueCountry = null; //added for passport details
	/**
	* Properties for employments
	* @referencedType pd.juvenilecase.family.FamilyMemberEmployment
	* @detailerDoNotGenerate false
	*/
	private Collection employments;
	private String ethnicityId;
	/**
	* @referencedType pd.codetable.Code
	* @contextKey CAUSE_OF_DEATH
	* @detailerDoNotGenerate true
	*/
	public Code causeofDeath = null;
	private String familyMemberComments;
	/**
	* @referencedType pd.codetable.Code
	* @contextKey LANGUAGE
	* @detailerDoNotGenerate true
	*/
	public Code secondaryLanguage = null;
	/**
	* Properties for benefits
	* @referencedType pd.juvenilecase.family.FamilyMemberBenefit
	* @detailerDoNotGenerate false
	*/
	private Collection benefits;
	private String middleName;
	private String nationalityId;
	private String firstName;
	private String driverLicenseClass;
	/**
	* @referencedType pd.codetable.Code
	* @contextKey ETHNICITY
	* @detailerDoNotGenerate true
	*/
	public Code ethnicity = null;
	/**
	* Properties for insurances
	* @referencedType pd.juvenilecase.family.FamilyMemberInsurance
	* @detailerDoNotGenerate false
	*/
	private Collection insurances;
	private String idCardStateId;
	private Date dateOfBirth;
	private String dateOfBirthString;
	private Date driverLicenseExpirationDate;
	private Date psportExpiryDate; //added for passport details
	private String ssn;
	
	private boolean isIncarcerated;
	
	//added for User Story 43892
	private boolean over21;
	
	private String assocJuvenileId;
	private String assocJuvenileName;
	private String assocRelation;
	private String juvRelation;
	private String juvenileId;
	/**
	* @roseuid 4321A6B90232
	*/
	public FamilyMember() {
	}
	/**
	* @roseuid 4321A38C00DA
	*/
	public void bind() {
		markModified();
	}
	/**
	* @param familyMemberNum The family member num.
	* @roseuid 4321A38C00DB
	*/
	static public FamilyMember find(String memberId) {
		IHome home = new Home();
		FamilyMember familyMember = (FamilyMember) home.find(memberId, FamilyMember.class);
		return familyMember;
	}
	/**
	* @roseuid 4321A38C00EB
	*/
	public void findAll() {
		fetch();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param primaryLanguageId The primary language id.
	*/
	public void setPrimaryLanguageId(String primaryLanguageId) {
		if (this.primaryLanguageId == null || !this.primaryLanguageId.equals(primaryLanguageId)) {
			markModified();
		}
		primaryLanguage = null;
		this.primaryLanguageId = primaryLanguageId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return The primary language id.
	*/
	public String getPrimaryLanguageId() {
		fetch();
		return primaryLanguageId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return The primary language.
	*/
	public Code getPrimaryLanguage() {
		fetch();
		initPrimaryLanguage();
		return primaryLanguage;
	}
	/**
	* set the type reference for class member primaryLanguage
	* @param primaryLanguage The primary language.
	*/
	public void setPrimaryLanguage(Code primaryLanguage) {
		if (this.primaryLanguage == null || !this.primaryLanguage.equals(primaryLanguage)) {
			markModified();
		}
		if (primaryLanguage.getOID() == null) {
			new mojo.km.persistence.Home().bind(primaryLanguage);
		}
		setPrimaryLanguageId("" + primaryLanguage.getOID());
		primaryLanguage.setContext(PDCodeTableConstants.LANGUAGE);
		this.primaryLanguage = (Code) new mojo.km.persistence.Reference(primaryLanguage).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param causeofDeathId The causeof death id.
	*/
	public void setCauseofDeathId(String causeofDeathId) {
		if (this.causeofDeathId == null || !this.causeofDeathId.equals(causeofDeathId)) {
			markModified();
		}
		causeofDeath = null;
		this.causeofDeathId = causeofDeathId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return The causeof death id.
	*/
	public String getCauseofDeathId() {
		fetch();
		return causeofDeathId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return The causeof death.
	*/
	public Code getCauseofDeath() {
		fetch();
		initCauseofDeath();
		return causeofDeath;
	}
	/**
	* set the type reference for class member causeofDeath
	* @param causeofDeath The causeof death.
	*/
	public void setCauseofDeath(Code causeofDeath) {
		if (this.causeofDeath == null || !this.causeofDeath.equals(causeofDeath)) {
			markModified();
		}
		if (causeofDeath.getOID() == null) {
			new mojo.km.persistence.Home().bind(causeofDeath);
		}
		setCauseofDeathId("" + causeofDeath.getOID());
		causeofDeath.setContext("CAUSE_OF_DEATH");
		this.causeofDeath = (Code) new mojo.km.persistence.Reference(causeofDeath).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param ethnicityId The ethnicity id.
	*/
	public void setEthnicityId(String ethnicityId) {
		if (this.ethnicityId == null || !this.ethnicityId.equals(ethnicityId)) {
			markModified();
		}
		ethnicity = null;
		this.ethnicityId = ethnicityId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return The ethnicity id.
	*/
	public String getEthnicityId() {
		fetch();
		return ethnicityId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return The ethnicity.
	*/
	public Code getEthnicity() {
		fetch();
		initEthnicity();
		return ethnicity;
	}
	/**
	* set the type reference for class member ethnicity
	* @param ethnicity The ethnicity.
	*/
	public void setEthnicity(Code ethnicity) {
		if (this.ethnicity == null || !this.ethnicity.equals(ethnicity)) {
			markModified();
		}
		if (ethnicity.getOID() == null) {
			new mojo.km.persistence.Home().bind(ethnicity);
		}
		setEthnicityId("" + ethnicity.getOID());
		ethnicity.setContext(PDCodeTableConstants.ETHNICITY);
		this.ethnicity = (Code) new mojo.km.persistence.Reference(ethnicity).getObject();
	}
	
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param sexId The sex id.
	*/
	public void setSexId(String sexId) {
		if (this.sexId == null || !this.sexId.equals(sexId)) {
			markModified();
		}
		sex = null;
		this.sexId = sexId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return The sex id.
	*/
	public String getSexId() {
		fetch();
		return sexId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return The sex.
	*/
	public Code getSex() {
		fetch();
		initSex();
		return sex;
	}
	/**
	* set the type reference for class member sex
	* @param sex The sex.
	*/
	public void setSex(Code sex) {
		if (this.sex == null || !this.sex.equals(sex)) {
			markModified();
		}
		if (sex.getOID() == null) {
			new mojo.km.persistence.Home().bind(sex);
		}
		setSexId("" + sex.getOID());
		sex.setContext("SEX");
		this.sex = (Code) new mojo.km.persistence.Reference(sex).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param nationalityId The nationality id.
	*/
	public void setNationalityId(String nationalityId) {
		if (this.nationalityId == null || !this.nationalityId.equals(nationalityId)) {
			markModified();
		}
		nationality = null;
		this.nationalityId = nationalityId;
	}

	/**
		* Set the reference value to class :: pd.codetable.Code
		* @param psportIssueCountryId The passport issuance country id.
		*/
		public void setPsportIssueCountryId(String psportIssueCountryId) {
			if (this.psportIssueCountryId == null || !this.psportIssueCountryId.equals(psportIssueCountryId)) {
				markModified();
			}
			psportIssueCountry = null;
			this.psportIssueCountryId = psportIssueCountryId;
		}
	
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return The nationality id.
	*/
	public String getNationalityId() {
		fetch();
		return nationalityId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return The passport issuance country id.
	*/
	public String getPsportIssueCountryId() {
		fetch();
		return psportIssueCountryId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return The nationality.
	*/
	public Code getNationality() {
		fetch();
		initNationality();
		return nationality;
	}

	/**
		* Gets referenced type pd.codetable.Code
		* @return The passport issuance country.
		*/
		public Code getPsportIssueCountry() {
			fetch();
			initPsportIssueCountry();
			return psportIssueCountry;
		}
	/**
	* set the type reference for class member nationality
	* @param nationality The nationality.
	*/
	public void setNationality(Code nationality) {
		if (this.nationality == null || !this.nationality.equals(nationality)) {
			markModified();
		}
		if (nationality.getOID() == null) {
			new mojo.km.persistence.Home().bind(nationality);
		}
		setNationalityId("" + nationality.getOID());
		nationality.setContext(PDCodeTableConstants.PLACE_OF_BIRTH);
		this.nationality = (Code) new mojo.km.persistence.Reference(nationality).getObject();
	}
	/**
	* set the type reference for class member psportIssueCountry
	* @param psportIssueCountry The passport issuance country.
	*/
	public void setPsportIssueCountry(Code psportIssueCountry) {
		if (this.psportIssueCountry == null || !this.psportIssueCountry.equals(psportIssueCountry)) {
			markModified();
		}
		if (psportIssueCountry.getOID() == null) {
			new mojo.km.persistence.Home().bind(psportIssueCountry);
		}
		setPsportIssueCountryId("" + psportIssueCountry.getOID());
		psportIssueCountry.setContext(PDCodeTableConstants.PLACE_OF_BIRTH);
		this.psportIssueCountry = (Code) new mojo.km.persistence.Reference(psportIssueCountry).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param idCardStateId The id num state id.
	*/
	public void setIdCardStateId(String idNumStateId) {
		if (this.idCardStateId == null || !this.idCardStateId.equals(idNumStateId)) {
			markModified();
		}
		idCardState = null;
		this.idCardStateId = idNumStateId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return The id num state id.
	*/
	public String getIdCardStateId() {
		fetch();
		return idCardStateId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return The id num state.
	*/
	public Code getIdCardState() {
		fetch();
		initIdNumState();
		return idCardState;
	}
	/**
	* set the type reference for class member idCardState
	* @param idCardState The id num state.
	*/
	public void setIdCardState(Code idNumState) {
		if (this.idCardState == null || !this.idCardState.equals(idNumState)) {
			markModified();
		}
		if (idNumState.getOID() == null) {
			new mojo.km.persistence.Home().bind(idNumState);
		}
		setIdCardStateId("" + idNumState.getOID());
		idNumState.setContext("STATE");
		this.idCardState = (Code) new mojo.km.persistence.Reference(idNumState).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param secondaryLanguageId The secondary language id.
	*/
	public void setSecondaryLanguageId(String secondaryLanguageId) {
		if (this.secondaryLanguageId == null || !this.secondaryLanguageId.equals(secondaryLanguageId)) {
			markModified();
		}
		secondaryLanguage = null;
		this.secondaryLanguageId = secondaryLanguageId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return The secondary language id.
	*/
	public String getSecondaryLanguageId() {
		fetch();
		return secondaryLanguageId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return The secondary language.
	*/
	public Code getSecondaryLanguage() {
		fetch();
		initSecondaryLanguage();
		return secondaryLanguage;
	}
	/**
	* set the type reference for class member secondaryLanguage
	* @param secondaryLanguage The secondary language.
	*/
	public void setSecondaryLanguage(Code secondaryLanguage) {
		if (this.secondaryLanguage == null || !this.secondaryLanguage.equals(secondaryLanguage)) {
			markModified();
		}
		if (secondaryLanguage.getOID() == null) {
			new mojo.km.persistence.Home().bind(secondaryLanguage);
		}
		setSecondaryLanguageId("" + secondaryLanguage.getOID());
		secondaryLanguage.setContext(PDCodeTableConstants.LANGUAGE);
		this.secondaryLanguage = (Code) new mojo.km.persistence.Reference(secondaryLanguage).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param driverLicenseStateId The driver license state id.
	*/
	public void setDriverLicenseStateId(String driverLicenseStateId) {
		if (this.driverLicenseStateId == null || !this.driverLicenseStateId.equals(driverLicenseStateId)) {
			markModified();
		}
		driverLicenseState = null;
		this.driverLicenseStateId = driverLicenseStateId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return The driver license state id.
	*/
	public String getDriverLicenseStateId() {
		fetch();
		return driverLicenseStateId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return The driver license state.
	*/
	public Code getDriverLicenseState() {
		fetch();
		initDriverLicenseState();
		return driverLicenseState;
	}
	/**
	* set the type reference for class member driverLicenseState
	* @param driverLicenseState The driver license state.
	*/
	public void setDriverLicenseState(Code driverLicenseState) {
		if (this.driverLicenseState == null || !this.driverLicenseState.equals(driverLicenseState)) {
			markModified();
		}
		if (driverLicenseState.getOID() == null) {
			new mojo.km.persistence.Home().bind(driverLicenseState);
		}
		setDriverLicenseStateId("" + driverLicenseState.getOID());
		driverLicenseState.setContext("STATE");
		this.driverLicenseState = (Code) new mojo.km.persistence.Reference(driverLicenseState).getObject();
	}
	/**
	* returns a collection of pd.address.Address
	* @return The addresses.
	*/
	public Collection getAddresses() {
		fetch();
		initAddresses();
		ArrayList retVal = new ArrayList();
		Iterator i = addresses.iterator();
		while (i.hasNext()) {
			FamilyMemberAddressesAddress actual = (FamilyMemberAddressesAddress) i.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}
	/**
	* insert a pd.address.Address into class relationship collection.
	* @param anObject The an object.
	*/
	public void insertAddresses(Address anObject) {
		initAddresses();
		FamilyMemberAddressesAddress actual = new FamilyMemberAddressesAddress();
		if (this.getOID() == null) {
			new Home().bind(this);
		}
		if (anObject.getOID() == null) {
			new Home().bind(anObject);
		}
		actual.setParent(this);
		actual.setChild(anObject);
		String logonId = PDSecurityHelper.getLogonId();
		//89637 logonId = (logonId == null || logonId.equals(""))?PDJuvenileCaseConstants.CASEFILE_CREATOR:logonId;
		actual.setCreateUserID(logonId);
		addresses.add(actual);
	}
	/**
	* Removes a pd.address.Address from class relationship collection.
	* @param anObject The an object.
	*/
	public void removeAddresses(Address anObject) {
		initAddresses();
		mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
		assocEvent.setChildId((String) anObject.getOID());
		assocEvent.setParentId((String) this.getOID());
		FamilyMemberAddressesAddress actual =
			(FamilyMemberAddressesAddress) new mojo
				.km
				.persistence
				.Reference(assocEvent, FamilyMemberAddressesAddress.class)
				.getObject();
		addresses.remove(actual);
	}
	/**
	* Clears all pd.address.Address from class relationship collection.
	*/
	public void clearAddresses() {
		initAddresses();
		addresses.clear();
	}
	/**
	* @return The alien num.
	*/
	public String getAlienNum() {
		fetch();
		return alienNum;
	}
	/**
	* @return The comments.
	*/
	public String getComments() {
		fetch();
		return comments;
	}
	/**
	* @return The date of birth.
	*/
	public Date getDateOfBirth() {
		fetch();
		return dateOfBirth;
	}
	/**
	* @return The driver license expiration date.
	*/
	public Date getDriverLicenseExpirationDate() {
		fetch();
		return driverLicenseExpirationDate;
	}
	/**
	* @return The passport expiration date.
	*/
	public Date getPsportExpiryDate() {
		fetch();
		return psportExpiryDate;
	}
	/**
	* @return The driver license num.
	*/
	public String getDriverLicenseNum() {
		fetch();
		return driverLicenseNum;
	}
	/**
	* @return The family member comments.
	*/
	public String getFamilyMemberComments() {
		fetch();
		return familyMemberComments;
	}
	/**
	* @return The family member id.
	*/
	public String getFamilyMemberId() {
		return "" + getOID();
	}
	/**
	* @return The first name.
	*/
	public String getFirstName() {
		fetch();
		return firstName;
	}
	/**
	* @return The boolean.
	*/
	public boolean isDeceased() {
		fetch();
		return isDeceased;
	}
	/**
	* @return The last name.
	*/
	public String getLastName() {
		fetch();
		return lastName;
	}
	/**
	* @return The middle name.
	*/
	public String getMiddleName() {
		fetch();
		return middleName;
	}
	/**
	* @return The sid num.
	*/
	public String getSidNum() {
		fetch();
		return sidNum;
	}
	/**
	* @return The s s n.
	*/
	public String getSSN() {
		fetch();
		return ssn;
	}

	/**
	* @param string The alien num.
	*/
	public void setAlienNum(String string) {
		if (this.alienNum == null || !this.alienNum.equals(string)) {
			markModified();
		}
		alienNum = string;
	}
	/**
	* @param string The comments.
	*/
	public void setComments(String string) {
		if (this.comments == null || !this.comments.equals(string)) {
			markModified();
		}
		comments = string;
	}
	/**
	* @param collections The contacts array.
	*/
	public void setContacts(Collection collections) {
		if (this.contacts == null || !this.contacts.equals(collections)) {
			markModified();
		}
		contacts = collections;
	}
	/**
	* @param date The date of birth.
	*/
	public void setDateOfBirth(Date date) {
		if (this.dateOfBirth == null || !this.dateOfBirth.equals(date)) {
			markModified();
		}
		dateOfBirth = date;
	}
	/**
	* @param date The driver license expiration date.
	*/
	public void setDriverLicenseExpirationDate(Date date) {
		if (this.driverLicenseExpirationDate == null || !this.driverLicenseExpirationDate.equals(date)) {
			markModified();
		}
		driverLicenseExpirationDate = date;
	}
	
	/**
	* @param date The passport expiration date.
	*/
	public void setPsportExpiryDate(Date date) {
		if (this.psportExpiryDate == null || !this.psportExpiryDate.equals(date)) {
			markModified();
		}
		psportExpiryDate = date;
	}
	/**
	* @param string The driver license num.
	*/
	public void setDriverLicenseNum(String string) {
		if (this.driverLicenseNum == null || !this.driverLicenseNum.equals(string)) {
			markModified();
		}
		driverLicenseNum = string;
	}
	/**
	* @param string The family member comments.
	*/
	public void setFamilyMemberComments(String string) {
		if (this.familyMemberComments == null || !this.familyMemberComments.equals(string)) {
			markModified();
		}
		familyMemberComments = string;
	}
	/**
	* @param string The family member id.
	*/
	public void setFamilyMemberId(String string) {
		if (this.familyMemberId == null || !this.familyMemberId.equals(string)) {
			markModified();
		}
		familyMemberId = string;
	}
	/**
	* @param string The first name.
	*/
	public void setFirstName(String string) {
		if (this.firstName == null || !this.firstName.equals(string)) {
			markModified();
		}
		firstName = string;
	}
	/**
	* @param b The deceased.
	*/
	public void setDeceased(boolean b) {
		if (this.isDeceased != b) {
			markModified();
		}
		isDeceased = b;
	}
	/**
	* @param string The last name.
	*/
	public void setLastName(String string) {
		if (this.lastName == null || !this.lastName.equals(string)) {
			markModified();
		}
		lastName = string;
	}
	/**
	* @param string The middle name.
	*/
	public void setMiddleName(String string) {
		if (this.middleName == null || !this.middleName.equals(string)) {
			markModified();
		}
		middleName = string;
	}
	/**
	* @param string The sid num.
	*/
	public void setSidNum(String string) {
		if (this.sidNum == null || !this.sidNum.equals(string)) {
			markModified();
		}
		sidNum = string;
	}
	/**
	* @param string The ssn.
	*/
	public void setSsn(String string) {
		if (this.ssn == null || !this.ssn.equals(string)) {
			markModified();
		}
		ssn = string;
	}
	
	public void setSSN(String string) {
		if (this.ssn == null || !this.ssn.equals(string)) {
			markModified();
		}
		ssn = string;
	}

	/**
	* @return The driver license class.
	*/
	public String getDriverLicenseClass() {
		fetch();
		return driverLicenseClass;
	}
	/**
	* @param string The driver license class.
	*/
	public void setDriverLicenseClass(String string) {
		if (this.driverLicenseClass == null || !this.driverLicenseClass.equals(string)) {
			markModified();
		}
		driverLicenseClass = string;
	}
	/**
	* returns a collection of pd.juvenilecase.family.FamilyMemberPhone
	* @return The phone numbers.
	*/
	public Collection getPhoneNumbers() {
		fetch();
		initPhoneNumbers();
		ArrayList retVal = new ArrayList();
		Iterator i = phoneNumbers.iterator();
		while (i.hasNext()) {
			FamilyMemberPhone actual = (FamilyMemberPhone) i.next();
			retVal.add(actual);
		}
		return retVal;
	}
	/**
	* insert a pd.juvenilecase.family.FamilyMemberPhone into class relationship collection.
	* @param anObject The an object.
	*/
	public void insertPhoneNumbers(FamilyMemberPhone anObject) {
		initPhoneNumbers();
		phoneNumbers.add(anObject);
	}
	/**
	* Removes a pd.juvenilecase.family.FamilyMemberPhone from class relationship collection.
	* @param anObject The an object.
	*/
	public void removePhoneNumbers(FamilyMemberPhone anObject) {
		initPhoneNumbers();
		phoneNumbers.remove(anObject);
	}
	/**
	* Clears all pd.juvenilecase.family.FamilyMemberPhone from class relationship collection.
	*/
	public void clearPhoneNumbers() {
		initPhoneNumbers();
		phoneNumbers.clear();
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initPrimaryLanguage() {
		if (primaryLanguage == null) {
			primaryLanguage = (Code) new mojo.km.persistence.Reference(primaryLanguageId, Code.class, PDCodeTableConstants.LANGUAGE).getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initCauseofDeath() {
		if (causeofDeath == null) {
			causeofDeath = (Code) new mojo.km.persistence.Reference(causeofDeathId, Code.class, "CAUSE_OF_DEATH").getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initEthnicity() {
		if (ethnicity == null) {
			ethnicity = (Code) new mojo.km.persistence.Reference(ethnicityId, Code.class, PDCodeTableConstants.ETHNICITY).getObject();
		}
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initSex() {
		if (sex == null) {
			sex = (Code) new mojo.km.persistence.Reference(sexId, Code.class, "SEX").getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initNationality() {
		if (nationality == null) {
			nationality = (Code) new mojo.km.persistence.Reference(nationalityId, Code.class, PDCodeTableConstants.PLACE_OF_BIRTH).getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initPsportIssueCountry() {
		if (psportIssueCountry == null) {
			psportIssueCountry = (Code) new mojo.km.persistence.Reference(psportIssueCountryId, Code.class, PDCodeTableConstants.PLACE_OF_BIRTH).getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initIdNumState() {
		if (idCardState == null) {
			idCardState = (Code) new mojo.km.persistence.Reference(idCardStateId, Code.class, "STATE").getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initSecondaryLanguage() {
		if (secondaryLanguage == null) {
			secondaryLanguage = (Code) new mojo.km.persistence.Reference(secondaryLanguageId, Code.class, PDCodeTableConstants.LANGUAGE).getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initDriverLicenseState() {
		if (driverLicenseState == null) {
			driverLicenseState = (Code) new mojo.km.persistence.Reference(driverLicenseStateId, Code.class, "STATE").getObject();
		}
	}
	/**
	* Initialize class relationship implementation for pd.address.Address
	*/
	private void initAddresses() {
		if (addresses == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			addresses = new mojo.km.persistence.ArrayList(FamilyMemberAddressesAddress.class, "parentId", "" + getOID());
		}
	}
	/**
	* Initialize class relationship implementation for pd.juvenilecase.family.FamilyMemberPhone
	*/
	private void initPhoneNumbers() {
		if (phoneNumbers == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			phoneNumbers = new mojo.km.persistence.ArrayList(FamilyMemberPhone.class, "familyMemberId", "" + getOID());
		}
	}
	
	private void initEmailAddresses() {
		if (emailAddresses == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			emailAddresses = new mojo.km.persistence.ArrayList(FamilyMemberEmail.class, "familyMemberId", "" + getOID());
		}
	}
	
	/**
	* @return 
	* @param searchEvent
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, FamilyMember.class);
	}
	/**
	* @return 
	*/
	public String getIdCardNum() {
		fetch();
		return idCardNum;
	}
	/**
	* @param string
	*/
	public void setIdCardNum(String string) {
		if (this.idCardNum == null || !this.idCardNum.equals(string)) {
			markModified();
		}
		idCardNum = string;
	}
		
		/**
		* @return 
		*/
		public String getPsportNum() {
			fetch();
			return psportNum;
		}
		/**
		* @param string
		*/
		public void setPsportNum(String string) {
			if (this.psportNum == null || !this.psportNum.equals(string)) {
				markModified();
			}
			psportNum = string;
	}
	/**
	* Initialize class relationship implementation for pd.juvenilecase.family.FamilyMemberTrait
	*/
	private void initTraits() {
		if (traits == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			traits = new mojo.km.persistence.ArrayList(FamilyMemberTrait.class, "familyMemberId", "" + getOID());
		}
	}
	/**
	* returns a collection of pd.juvenilecase.family.FamilyMemberTrait
	*/
	public Collection getTraits() {
		initTraits();
		return traits;
	}
	/**
	* insert a pd.juvenilecase.family.FamilyMemberTrait into class relationship collection.
	*/
	public void insertTraits(FamilyMemberTrait anObject) {
		initTraits();
		traits.add(anObject);
	}
	/**
	* Removes a pd.juvenilecase.family.FamilyMemberTrait from class relationship collection.
	*/
	public void removeTraits(FamilyMemberTrait anObject) {
		initTraits();
		traits.remove(anObject);
	}
	/**
	* Clears all pd.juvenilecase.family.FamilyMemberTrait from class relationship collection.
	*/
	public void clearTraits() {
		initTraits();
		traits.clear();
	}
	/**
	* Initialize class relationship implementation for pd.juvenilecase.family.FamilyMemberEmployment
	*/
	private void initEmployments() {
		if (employments == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			employments = new mojo.km.persistence.ArrayList(FamilyMemberEmployment.class, "familyMemberId", "" + getOID());
		}
	}
	/**
	* returns a collection of pd.juvenilecase.family.FamilyMemberEmployment
	*/
	public Collection getEmployments() {
		initEmployments();
		return employments;
	}
	/**
	* insert a pd.juvenilecase.family.FamilyMemberEmployment into class relationship collection.
	*/
	public void insertEmployments(FamilyMemberEmployment anObject) {
		initEmployments();
		employments.add(anObject);
	}
	/**
	* Removes a pd.juvenilecase.family.FamilyMemberEmployment from class relationship collection.
	*/
	public void removeEmployments(FamilyMemberEmployment anObject) {
		initEmployments();
		employments.remove(anObject);
	}
	/**
	* Clears all pd.juvenilecase.family.FamilyMemberEmployment from class relationship collection.
	*/
	public void clearEmployments() {
		initEmployments();
		employments.clear();
	}
	/**
	* Initialize class relationship implementation for pd.juvenilecase.family.FamilyMemberBenefit
	*/
	private void initBenefits() {
		if (benefits == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			benefits = new mojo.km.persistence.ArrayList(FamilyMemberBenefit.class, "familyMemberId", "" + getOID());
		}
	}
	/**
	* returns a collection of pd.juvenilecase.family.FamilyMemberBenefit
	*/
	public Collection getBenefits() {
		initBenefits();
		return benefits;
	}
	/**
	* insert a pd.juvenilecase.family.FamilyMemberBenefit into class relationship collection.
	*/
	public void insertBenefits(FamilyMemberBenefit anObject) {
		initBenefits();
		benefits.add(anObject);
	}
	/**
	* Removes a pd.juvenilecase.family.FamilyMemberBenefit from class relationship collection.
	*/
	public void removeBenefits(FamilyMemberBenefit anObject) {
		initBenefits();
		benefits.remove(anObject);
	}
	/**
	* Clears all pd.juvenilecase.family.FamilyMemberBenefit from class relationship collection.
	*/
	public void clearBenefits() {
		initBenefits();
		benefits.clear();
	}
	/**
	* Initialize class relationship implementation for pd.juvenilecase.family.FamilyMemberInsurance
	*/
	private void initInsurances() {
		if (insurances == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			insurances = new mojo.km.persistence.ArrayList(FamilyMemberInsurance.class, "familyMemberId", "" + getOID());
		}
	}
	/**
	* returns a collection of pd.juvenilecase.family.FamilyMemberInsurance
	*/
	public Collection getInsurances() {
		initInsurances();
		return insurances;
	}
	/**
	* insert a pd.juvenilecase.family.FamilyMemberInsurance into class relationship collection.
	*/
	public void insertInsurances(FamilyMemberInsurance anObject) {
		initInsurances();
		insurances.add(anObject);
	}
	/**
	* Removes a pd.juvenilecase.family.FamilyMemberInsurance from class relationship collection.
	*/
	public void removeInsurances(FamilyMemberInsurance anObject) {
		initInsurances();
		insurances.remove(anObject);
	}
	/**
	* Clears all pd.juvenilecase.family.FamilyMemberInsurance from class relationship collection.
	*/
	public void clearInsurances() {
		initInsurances();
		insurances.clear();
	}
	/**
	 * @return
	 */
	public String getSuspiciousMember()
	{
		fetch();
		return suspiciousMember;
	}

	/**
	 * @param string
	 */
	public void setSuspiciousMember(String string)
	{
		suspiciousMember = string;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initIsUSCitizen()
	{
		if (isUSCitizen == null)
		{
			try
			{
				isUSCitizen =
					(Code) new mojo
						.km
						.persistence
						.Reference(isUSCitizenId, Code.class, "IS_US_CITIZEN")
						.getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}	
	/**
	 * @return Returns the isUSCitizenId.
	 */
	public String getIsUSCitizenId() {
		fetch();
		return isUSCitizenId;
	}
	/**
	 * @param isUSCitizenId The isUSCitizenId to set.
	 */
	public void setIsUSCitizenId(String isUSCitizenId) {
		if (this.isUSCitizenId == null || !this.isUSCitizenId.equals(isUSCitizenId))
		{
			markModified();
		}
		isUSCitizen = null;
		this.isUSCitizenId = isUSCitizenId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getIsUSCitizen()
	{
		fetch();
		initIsUSCitizen();
		return isUSCitizen;
	}
	/**
	* set the type reference for class member IsUSCitizen
	*/
	public void setIsUSCitizen(Code isUSCitizen)
	{
		if (this.isUSCitizen == null || !this.isUSCitizen.equals(isUSCitizen))
		{
			markModified();
		}
		if (isUSCitizen.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(isUSCitizen);
		}
		setIsUSCitizenId("" + isUSCitizen.getOID());
		isUSCitizen.setContext("IS_US_CITIZEN");
		this.isUSCitizen = (Code) new mojo.km.persistence.Reference(isUSCitizen).getObject();
	}
	
	/**
	* returns a collection of pd.juvenilecase.family.FamilyMemberPhone
	* @return The phone numbers.
	*/
	public Collection getEmailAddresses() {
		fetch();
		initEmailAddresses();
		return emailAddresses;
	}
	/**
	* insert a pd.juvenilecase.family.FamilyMemberPhone into class relationship collection.
	* @param anObject The an object.
	*/
	public void insertEmailAddresses(FamilyMemberEmail anObject) {
		initEmailAddresses();
		emailAddresses.add(anObject);
	}
	/**
	* Removes a pd.juvenilecase.family.FamilyMemberPhone from class relationship collection.
	* @param anObject The an object.
	*/
	public void removeEmailAddresses(FamilyMemberEmail anObject) {
		initEmailAddresses();
		emailAddresses.remove(anObject);
	}
	/**
	* Clears all pd.juvenilecase.family.FamilyMemberPhone from class relationship collection.
	*/
	public void clearEmailAddresses() {
		initEmailAddresses();
		emailAddresses.clear();
	}
	
	/**
	 * @param b
	 */
	public void setIncarcerated(boolean b) {
		if (this.isIncarcerated != b) {
			isIncarcerated();
		}
		isIncarcerated = b;
	}
	
	/**
	 * @return
	 */
	public boolean isIncarcerated() {
		fetch();
		return isIncarcerated;
	}
	public boolean isOver21() {
		fetch();
		return over21;
	}
	public void setOver21(boolean over21) {
		if (this.over21!=over21)
		{
			markModified();
		}
		this.over21 = over21;
	}
	
	
	public String getAssocJuvenileId()
	{
	    fetch();
	    return assocJuvenileId;
	}
	public void setAssocJuvenileId(String assocJuvenileId)
	{
	    this.assocJuvenileId = assocJuvenileId;
	}
	public String getAssocJuvenileName()
	{
	    fetch();
	    return assocJuvenileName;
	}
	public void setAssocJuvenileName(String assocJuvenileName)
	{
	    this.assocJuvenileName = assocJuvenileName;
	}
	
	
	public String getAssocRelation()
	{
	    fetch();
	    return assocRelation;
	}
	public void setAssocRelation(String assocRelation)
	{
	    this.assocRelation = assocRelation;
	}
	
	public String getJuvRelation()
	{
	    fetch();
	    return juvRelation;
	}
	
	public void setJuvRelation(String juvRelation)
	{
	    if (this.juvRelation == null || !this.juvRelation.equals(juvRelation)) {
		markModified();
	    }
	    this.juvRelation = juvRelation;
	}
	
	public String getDateOfBirthString()
	{
	    return this.dateOfBirthString;
	}
	
	public void setDateOfBirthString(String date) 
	{
		this.dateOfBirthString = date;
	}
	
	public String getJuvenileId()
	{
	    fetch();
	    return this.juvenileId;
	}
	
	public void setJuvenileId(String juvenileId)
	{
	    if (this.juvenileId == null || !this.juvenileId.equals(juvenileId)) {
		markModified();
	    }
	    this.juvenileId = juvenileId;
	}
	
	/**
	 * 
	 * @return
	 */
	public FamilyMemberListResponseEvent valueObject(){
	    
	    FamilyMemberListResponseEvent response = new FamilyMemberListResponseEvent(); 
	    
	    response.setDateOfBirth(this.getDateOfBirth());
	    response.setEthnicityDesc( this.getEthnicityId() );
	    response.setMemberNum(this.getFamilyMemberId());
	    response.setFirstName(this.getFirstName());
	    response.setMiddleName(this.getMiddleName());
	    response.setLastName(this.getLastName());
	    response.setOriginalSSN(this.getSSN());
	    response.setSex( this.getSexId() );
	    response.setSuspiciousMemberMatch(this.getSuspiciousMemberMatch());
	    
	    List<AssociatedJuvenilesResponseEvent> juvReplies = new ArrayList<AssociatedJuvenilesResponseEvent>();
	    AssociatedJuvenilesResponseEvent myRespEvt = new AssociatedJuvenilesResponseEvent();
	    myRespEvt.setJuvId(this.getAssocJuvenileId());
	    myRespEvt.setRelationType(this.getAssocRelation());
	    myRespEvt.setJuvFullName(this.getAssocJuvenileName());
	    juvReplies.add(myRespEvt);
	    response.setAssociatedJuveniles(juvReplies);
	    
	    return response;
	    
	    
	}
	public String getSuspiciousMemberMatch()
	{
	    return suspiciousMemberMatch;
	}
	public void setSuspiciousMemberMatch(String suspiciousMemberMatch)
	{
	    this.suspiciousMemberMatch = suspiciousMemberMatch;
	}
}
