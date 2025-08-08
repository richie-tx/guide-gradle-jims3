/*
 * Created on Sep 19, 2005
 *
 */
package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import messaging.contact.domintf.IName;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SimpleCodeTableHelper;
import ui.common.SocialSecurity;
import ui.common.UIUtil;
import ui.common.form.AddressValidationForm;
import ui.juvenilecase.UIJuvenileLoadCodeTables;

/**
 * @author jjose
 *  
 */
// *******************************************************************************
// *            WARNING  WARNING  WARNING  WARNING  WARNING  WARNING
// *  This form has 6 embedded classes which is a non-standard coding practice
// *******************************************************************************
public class JuvenileMemberForm extends AddressValidationForm
{
    private static List emptyColl = new ArrayList();

    private IName relatedFamMemName = new Name();

    private boolean listsSet = false;

    //	Drop Downs
    private String primaryLanguageId = "";

    private String causeofDeathId = "";

    private String ethnicityId = "";

    private String sexId = "";

    private String secondaryLanguageId = "";

    private String driverLicenseStateId = "";

    private String driverLicenseClassId = "";

    private String issuedByStateId = "";

    private String nationalityId = "";
    
    private String countryOfIssuanceId = ""; //passport details

    private String relationToJuvId = "";

    private String involvementLevelId = "";

    private String maritalStatusId = "";

    // Fields
    private String searchJuvenileNumber = "";
    
    private Name origName = new Name();

    private SocialSecurity origSSN = new SocialSecurity("");

    private String juvenileAgeAtDeath = "";

    private String primaryLanguage = "";

    private String causeofDeath = "";

    private String ethnicity = "";

    private String sex = "";

    private String secondaryLanguage = "";

    private String familyMemberComments = "";

    private String familyMemberNumber = "";

    private String memberNumber = "";

    private String familyNumber = "";

    private boolean deceased = false;
    private boolean deceasedValue = false;
    
    private boolean incarcerated = false;
    private boolean incarceratedValue = false;
    
    private boolean hasActiveCasefile = false;

    private Name name = new Name();

    private String driverLicenseNum = "";

    private String driverLicenseState = "";

    private String driverLicenseClass = "";

    private String driverLicenseExpirationDate = "";

    private String issuedByState = "";

    private String stateIssuedIdNum = "";
    
    private String passportNum = ""; //added for passport detail
    
    private String countryOfIssuance = ""; //added for passport details
    
    private String passportExpirationDate = ""; //added for passport details

    private boolean guardian = false;

    private String nationality = "";

    private String isUSCitizenId = "";

    private String isUSCitizen = "";

    private SocialSecurity ssn = new SocialSecurity("");

    private String dateOfBirth = "";

    private String alienNum = "";

    private String sidNum = "";

    private String relationToJuv = "";

    private String action = "";
    
    private String secondaryAction = "";

    private boolean update = false;

    private boolean delete = false;

    private String confirmDate = "";

    private boolean inHomeStatus = false;

    private String involvementLevel = "";

    private String juvenileNumber = "";

    private String selectedValue = "";

    private String maritalStatus = "";

    private String entryDate = "";

    private String marriageDate = "";

    private String divorceDate = "";

    private String numOfChildren = "";

    private String relatedFamMemId = "";

    private MemberAddress currentAddress = new MemberAddress();
    private MemberAddress previousAddress = new MemberAddress();

    private MemberContact currentContact = new MemberContact();

    private JuvenileFamilyForm.Trait currentTrait = new JuvenileFamilyForm.Trait();

    private MemberEmployment currentEmployment = new MemberEmployment();

    private MemberBenefit currentBenefit = new MemberBenefit();

    private MemberInsurance currentInsurance = new MemberInsurance();

    private List guardianList = new ArrayList();

    private JuvenileFamilyForm.Guardian currentGuardian = null;

    private String selectedConsRelationId = "";

    private String selectedEntryDate = "";

    private String suspiciousMember;
    private String suspiciousMemberMatchType; //Added for US 181437

    // Collections and Drop Down Lists
    private List contactList = new ArrayList();

    private List phoneNumberList = new ArrayList();

    private List addressList = new ArrayList();
    
    private List newAddressList = new ArrayList();

    private List traitList = new ArrayList();

    private List maritalRelWithList = new ArrayList(); // List of FamilyMemberListResponseEvent

    private List employmentInfoList = new ArrayList();

    private List benefitsInfoList = new ArrayList();

    private List insuranceInfoList = new ArrayList();

    private List maritalList = new ArrayList();

    private static List languageList = emptyColl;

    private static List causeOfDeathList = emptyColl;

    private static List ethnicityList = emptyColl;

    private static List sexList = emptyColl;

    private static List stateList = emptyColl;
    
    private static List countryOfIssuanceList = emptyColl; //passport details

    private static List nationalityList = emptyColl;

    private static List relationshipToJuvenileList = emptyColl;

    private static List involvementLevelList = emptyColl;

    public static List traitDescList = emptyColl;

    private static List traitStatusList = emptyColl;

    private static List traitLevelList = emptyColl;

    private static List driversLicenseClassList = emptyColl;

    private static List maritalStatusList = emptyColl;

    private static List streetTypeList = emptyColl;
    
    private static List streetNumSuffixList = emptyColl;

    private static List addressTypeList = emptyColl;

    private static List countyList = emptyColl;

    private static List contactTypeList = emptyColl;

    private static List salaryRateList = emptyColl;

    private static List employmentStatusList = emptyColl;

    private static List isUSCitizenList = emptyColl;

    // need these 2 lists to be public because MemberBenefit and MemberInsurance 
    // will be accessed without creating JuvenileMemberForm
    public static List benefitEligibilityList = emptyColl;

    public static List insuranceTypeList = emptyColl;

    public List associatedJuveniles = emptyColl;
    
    private String primaryInd="";
    private String primaryIndEmail = "";
    
    private boolean bypassMatches = false;
    
    //Added for US 27022
   	Collection benefitsReceivers = new ArrayList();
   	
   	//added for User Story 43892
   	private boolean over21 = false;
   	private String over21Str;
   	
   	private SocialSecurity completeSSN = new SocialSecurity("");  //US 39892
   
   private boolean hasFamilyMemberAddressChanged = false;

    public void clearAll()
    {
        origName = new Name();
        origSSN = new SocialSecurity("");
        primaryLanguageId = "";
        causeofDeathId = "";
        ethnicityId = "";
        sexId = "";
        secondaryLanguageId = "";
        driverLicenseStateId = "";
        driverLicenseClassId = "";
        issuedByStateId = "";
        nationalityId = "";
        relationToJuvId = "";
        involvementLevelId = "";
        maritalStatusId = "";
        relatedFamMemId = "";
        relatedFamMemName = new Name();
        juvenileAgeAtDeath = "";
        primaryLanguage = "";
        causeofDeath = "";
        ethnicity = "";
        sex = "";
        secondaryLanguage = "";
        familyMemberComments = "";
        familyMemberNumber = "";
        memberNumber = "";
        familyNumber = "";
        deceased = false;
        incarcerated = false;
        this.deceasedValue = false;
        this.incarceratedValue = false;
        name = new Name();
        driverLicenseNum = "";
        driverLicenseState = "";
        driverLicenseClass = "";
        driverLicenseExpirationDate = "";
        issuedByState = "";
        stateIssuedIdNum = "";
        passportNum = ""; //added for passport details
        countryOfIssuanceId = ""; //added for passport details
        countryOfIssuance = ""; //added for passport details
        passportExpirationDate = ""; //added for passport details
        guardian = false;
        nationality = "";
        isUSCitizenId = "";
        isUSCitizen = "";
        ssn = new SocialSecurity("");
        completeSSN = new SocialSecurity(""); //88726
        dateOfBirth = "";
        alienNum = "";
        sidNum = "";
        relationToJuv = "";
        action = "";
        update = false;
        delete = false;
        confirmDate = "";
        inHomeStatus = false;
        hasActiveCasefile = false;
        involvementLevel = "";
        juvenileNumber = "";
        selectedValue = "";
        maritalStatus = "";
        entryDate = "";
        marriageDate = "";
        divorceDate = "";
        numOfChildren = "";
        clearAddress();
        clearTrait();
        clearContact();
        clearEmployment();
        clearBenefit();
        clearInsurance();
        suspiciousMember = "";
        setSuspiciousMemberMatchType("");
        associatedJuveniles = null;
        primaryInd = "";
        primaryIndEmail = "";
        bypassMatches = false;
        over21=false;
    }
    
    public void reset(){
    	primaryInd = "";
    	primaryIndEmail = "";
    }

	/**
	 * @return the primaryInd
	 */
	public String getPrimaryInd() {
		return primaryInd;
	}

	/**
	 * @param primaryInd the primaryInd to set
	 */
	public void setPrimaryInd(String primaryInd) {
		this.primaryInd = primaryInd;
	}
	
	public String getPrimaryIndEmail() {
		return this.primaryIndEmail;
	}

	public void setPrimaryIndEmail(String primaryInd) {
		this.primaryIndEmail = primaryInd;
	}

	public JuvenileMemberForm()
    {
        UIJuvenileLoadCodeTables.getInstance().setJuvenileMemberForm(this);
    }

    /**
     * @return Returns the relatedFamMemId.
     */
    public String getRelatedFamMemId()
    {
        return relatedFamMemId;
    }

    /**
     * @param relatedFamMemId
     *            The relatedFamMemId to set.
     */
    public void setRelatedFamMemId(String relatedFamMemId)
    {
        this.relatedFamMemId = relatedFamMemId;
    }

    public void clearAddress()
    {
        setCurrentAddress(new MemberAddress());
        setAddressStatus("");
    }

    public void clearTrait()
    {
        setCurrentTrait(new JuvenileFamilyForm.Trait());
    }

    public void clearContact()
    {
        setCurrentContact(new MemberContact());
    }

    public void clearEmployment()
    {
        setCurrentEmployment(new MemberEmployment());
    }

    public void clearBenefit()
    {
        setCurrentBenefit(new MemberBenefit());
    }

    public void clearInsurance()
    {
        setCurrentInsurance(new MemberInsurance());
    }

    /**
     * @return
     */
    public String getAction()
    {
        return action;
    }

    /**
     * @return
     */
    public List getAddressList()
    {
        return addressList;
    }

    /**
     * @return
     */
    public String getAlienNum()
    {
    	if(alienNum != null)
    		return alienNum.trim();
    	else
    		return alienNum;
    }

    /**
     * @return
     */
    public List getBenefitsInfoList()
    {
        return benefitsInfoList;
    }

    /**
     * @return
     */
    public String getCauseofDeath()
    {
        return causeofDeath;
    }

    /**
     * @return
     */
    public String getCauseofDeathId()
    {
        return causeofDeathId;
    }

    /**
     * @return
     */
    public List getCauseOfDeathList()
    {
        return causeOfDeathList;
    }

    /**
     * @return
     */
    public String getConfirmDate()
    {
        return confirmDate;
    }

    /**
     * @return
     */
    public List getContactList()
    {
        return contactList;
    }

    /**
     * @return
     */
    public String getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * @return
     */
    public boolean isDeceased()
    {
        return deceased;
    }
    
    public boolean getDeceased()
    {
        return this.deceased;
    }

    public String getDeceasedYesNo()
    {
        if (this.isDeceased())
            return "YES";
        else
            return "NO";
    }
    
    public boolean getDeceasedValue()
    {
        return this.deceasedValue;
    }
    
    public void setDeceasedValue(boolean deceasedVal){
	
	this.deceasedValue = deceasedVal;
    }
    
    public boolean getIncarceratedValue()
    {
        return this.incarceratedValue;
    }
    
    public void setIncarceratedValue(boolean incarceratedVal){
	
	this.incarceratedValue = incarceratedVal;
    }
    
    /**
     * @return
     */
    public boolean isIncarcerated()
    {
        return this.incarcerated;
    }

    public String getIncarceratedYesNo()
    {
        if (this.isIncarcerated())
            return "YES";
        else
            return "NO";
    }
    
    public boolean getIncarcerated()
    {
        return this.incarcerated;
    }

    /**
     * @return
     */
    public boolean isDelete()
    {
        return delete;
    }

    /**
     * @return
     */
    public String getDriverLicenseClass()
    {
        return driverLicenseClass;
    }

    /**
	 * @return the benefitsReceivers
	 */
	public Collection getBenefitsReceivers() {
		return benefitsReceivers;
	}

	/**
	 * @param benefitsReceivers the benefitsReceivers to set
	 */
	public void setBenefitsReceivers(Collection benefitsReceivers) {
		this.benefitsReceivers = benefitsReceivers;
	}

	/**
     * @return
     */
    public String getDriverLicenseExpirationDate()
    {
        return driverLicenseExpirationDate;
    }

    /**
     * @return
     */
    public String getDriverLicenseNum()
    {
        if(driverLicenseNum != null)
        	return driverLicenseNum.trim();
        else
        	return driverLicenseNum;
    }

    /**
     * @return
     */
    public String getDriverLicenseState()
    {
        return driverLicenseState;
    }

    /**
     * @return
     */
    public String getDriverLicenseStateId()
    {
        return driverLicenseStateId;
    }

    /**
     * @return
     */
    public List getEmploymentInfoList()
    {
        return employmentInfoList;
    }

    /**
     * @return
     */
    public String getEthnicity()
    {
        return ethnicity;
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
    public List getEthnicityList()
    {
        return ethnicityList;
    }

    /**
     * @return
     */
    public String getFamilyMemberComments()
    {
        return familyMemberComments;
    }

    /**
     * @return
     */
    public String getFamilyMemberNumber()
    {
        return familyMemberNumber;
    }

    /**
     * @return
     */
    public List getsexList()
    {
        return sexList;
    }

    /**
     * @return
     */
    public boolean isGuardian()
    {
        return guardian;
    }

    public String getGuardianYesNo()
    {
        if (this.isGuardian())
            return "YES";
        else
            return "NO";
    }
   
    /**
     * @return
     */
    public boolean isInHomeStatus()
    {
        return inHomeStatus;
    }

    /**
     * @return
     */
    public List getInsuranceInfoList()
    {
        return insuranceInfoList;
    }

    /**
     * @return
     */
    public String getInvolvementLevel()
    {
        return involvementLevel;
    }

    /**
     * @return
     */
    public String getInvolvementLevelId()
    {
        return involvementLevelId;
    }

    /**
     * @return
     */
    public String getIssuedByState()
    {
        return issuedByState;
    }

    /**
     * @return
     */
    public String getIssuedByStateId()
    {
        return issuedByStateId;
    }

    /**
     * @return
     */
    public List getLanguageList()
    {
        return languageList;
    }

    /**
     * @return
     */
    public List getMaritalList()
    {
        return maritalList;
    }

    /**
     * @return
     */
    public Name getName()
    {
        return name;
    }

    /**
     * @return
     */
    public String getNationality()
    {
        return nationality;
    }

    /**
     * @return
     */
    public String getNationalityId()
    {
        return nationalityId;
    }

    /**
     * @return
     */
    public List getNationalityList()
    {
        return nationalityList;
    }

    public List getNewAddressList() {
		return newAddressList;
	}

	/**
     * @return
     */
    public List getPhoneNumberList()
    {
        return phoneNumberList;
    }

    /**
     * @return
     */
    public String getPrimaryLanguage()
    {
        return primaryLanguage;
    }

    /**
     * @return
     */
    public String getPrimaryLanguageId()
    {
        return primaryLanguageId;
    }

    /**
     * @return
     */
    public List getRelationshipToJuvenileList()
    {
        return relationshipToJuvenileList;
    }

    /**
     * @return
     */
    public String getRelationToJuv()
    {
        return relationToJuv;
    }

    /**
     * @return
     */
    public String getRelationToJuvId()
    {
        return relationToJuvId;
    }

    /**
     * @return
     */
    public String getSecondaryAction()
    {
        return secondaryAction;
    }
    
    /**
     * @return
     */
    public String getSecondaryLanguage()
    {
        return secondaryLanguage;
    }

    /**
     * @return
     */
    public String getSecondaryLanguageId()
    {
        return secondaryLanguageId;
    }

    /**
     * @return
     */
    public String getSex()
    {
        return sex;
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
    public String getSidNum()
    {
        return sidNum;
    }

    /**
     * @return
     */
    public SocialSecurity getSsn()
    {
        return ssn;
    }

    /**
     * @return
     */
    public String getStateIssuedIdNum()
    {
        if(stateIssuedIdNum != null)
        	return stateIssuedIdNum.trim();
        else
        	return stateIssuedIdNum;
    }

    /**
     * @return
     */
    public String getPassportNum()
    {
        if(passportNum != null)
        	return passportNum.trim();
        else
        	return passportNum;
    }

    /**
     * @return
     */
    public String getCountryOfIssuance()
    {
        return countryOfIssuance;
    }
    

    /**
     * @return
     */
    public String getCountryOfIssuanceId()
    {
        return countryOfIssuanceId;

    }

    /**
     * @return
     */
    public List getCountryOfIssuanceList()
    {
        return countryOfIssuanceList;
    }
    /**
     * @return
     */
    public String getPassportExpirationDate()
    {
        return passportExpirationDate;
    }

    /**
     * @return
     */
    public List getStateList()
    {
        return stateList;
    }

    /**
     * @return
     */
    public List getTraitList()
    {
        return traitList;
    }

    /**
     * @return
     */
    public boolean isUpdate()
    {
        return update;
    }

    /**
     * @param collection
     */
    public static void setEmptyColl(List collection)
    {
        emptyColl = collection;
    }

    /**
     * @param string
     */
    public void setAction(String string)
    {
        action = string;
    }

    /**
     * @param collection
     */
    public void setAddressList(List collection)
    {
        addressList = collection;
    }

    /**
     * @param string
     */
    public void setAlienNum(String string)
    {
        alienNum = string;
    }

    /**
     * @param collection
     */
    public void setBenefitsInfoList(List collection)
    {
        benefitsInfoList = collection;
    }

    /**
     * @param string
     */
    public void setCauseofDeath(String string)
    {
        causeofDeath = string;
    }

    /**
     * @param string
     */
    public void setCauseofDeathId(String string)
    {
        causeofDeathId = string;
        if (causeofDeathId == null || causeofDeathId.equals(""))
        {
            causeofDeath = "";
            return;
        }
        if (JuvenileMemberForm.causeOfDeathList != null && JuvenileMemberForm.causeOfDeathList.size() > 0)
        {
            causeofDeath = CodeHelper.getCodeDescriptionByCode(JuvenileMemberForm.causeOfDeathList, causeofDeathId);
        }
    }

    /**
     * @param collection
     */
    public void setCauseOfDeathList(List collection)
    {
        causeOfDeathList = collection;
    }

    /**
     * @param string
     */
    public void setConfirmDate(String string)
    {
        confirmDate = string;
    }

    /**
     * @param collection
     */
    public void setContactList(List collection)
    {
        contactList = collection;
    }

    /**
     * @param string
     */
    public void setDateOfBirth(String string)
    {
        dateOfBirth = string;
    }

    /**
     * @param b
     */
    public void setDeceased(boolean b)
    {
        deceased = b;
    }
    
    /**
     * @param b
     */
    public void setIncarcerated(boolean incarcerated)
    {
    	this.incarcerated = incarcerated;
    }

    /**
     * @param b
     */
    public void setDelete(boolean b)
    {
        delete = b;
    }

    /**
     * @param string
     */
    public void setDriverLicenseClass(String string)
    {
        driverLicenseClass = string;
    }

    /**
     * @param string
     */
    public void setDriverLicenseExpirationDate(String string)
    {
        driverLicenseExpirationDate = string;
    }

    /**
     * @param string
     */
    public void setDriverLicenseNum(String string)
    {
        driverLicenseNum = string;
    }

    /**
     * @param string
     */
    public void setDriverLicenseState(String string)
    {
        driverLicenseState = string;
    }

    /**
     * @param string
     */
    public void setDriverLicenseStateId(String string)
    {
        driverLicenseStateId = string;
        if (driverLicenseStateId == null || driverLicenseStateId.equals(""))
        {
            driverLicenseState = "";
            return;
        }
        if (JuvenileMemberForm.stateList != null && JuvenileMemberForm.stateList.size() > 0)
        {
            driverLicenseState = CodeHelper
                    .getCodeDescriptionByCode(JuvenileMemberForm.stateList, driverLicenseStateId);
        }
    }

    /**
     * @param collection
     */
    public void setEmploymentInfoList(List collection)
    {
        employmentInfoList = collection;
    }

    /**
     * @param string
     */
    public void setEthnicity(String string)
    {
        ethnicity = string;
    }

    /**
     * @param string
     */
    public void setEthnicityId(String string)
    {
        ethnicityId = string;
        if (ethnicityId == null || ethnicityId.equals(""))
        {
            ethnicity = "";
            return;
        }
        if (JuvenileMemberForm.ethnicityList != null && JuvenileMemberForm.ethnicityList.size() > 0)
        {
            ethnicity = CodeHelper.getCodeDescriptionByCode(JuvenileMemberForm.ethnicityList, ethnicityId);
        }
    }

    /**
     * @param collection
     */
    public void setEthnicityList(List collection)
    {
        ethnicityList = collection;
    }

    /**
     * @param string
     */
    public void setFamilyMemberComments(String string)
    {
        familyMemberComments = string;
    }

    /**
     * @param string
     */
    public void setFamilyMemberNumber(String string)
    {
        familyMemberNumber = string;
    }

    /**
     * @param b
     */
    public void setGuardian(boolean b)
    {
        guardian = b;
    }
    
    public boolean getHasFamilyMemberAddressChanged()
    {
        return this.hasFamilyMemberAddressChanged;
    }

    /**
     * @param b
     */
    public void setHasFamilyMemberAddressChanged(boolean hasFamilyMemberAddressChanged)
    {
        this.hasFamilyMemberAddressChanged = hasFamilyMemberAddressChanged;
    }

    /**
     * @param string
     */
    public void setInHomeStatus(boolean bool)
    {
        inHomeStatus = bool;
    }

    /**
     * @param collection
     */
    public void setInsuranceInfoList(List collection)
    {
        insuranceInfoList = collection;
    }

    /**
     * @param string
     */
    public void setInvolvementLevel(String string)
    {
        involvementLevel = string;
    }

    /**
     * @param string
     */
    public void setInvolvementLevelId(String string)
    {
        involvementLevelId = string;
        if (involvementLevelId == null || involvementLevelId.equals(""))
        {
            involvementLevel = "";
            return;
        }
        if (JuvenileMemberForm.involvementLevelList != null && JuvenileMemberForm.involvementLevelList.size() > 0)
        {
            involvementLevel = CodeHelper.getCodeDescriptionByCode(JuvenileMemberForm.involvementLevelList,
                    involvementLevelId);
        }
    }

    /**
     * @param string
     */
    public void setIssuedByState(String string)
    {
        issuedByState = string;
    }

    /**
     * @param string
     */
    public void setIssuedByStateId(String string)
    {
        issuedByStateId = string;
        if (issuedByStateId == null || issuedByStateId.equals(""))
        {
            issuedByState = "";
            return;
        }
        if (JuvenileMemberForm.stateList != null && JuvenileMemberForm.stateList.size() > 0)
        {
            issuedByState = CodeHelper.getCodeDescriptionByCode(JuvenileMemberForm.stateList, issuedByStateId);
        }
    }

    /**
     * @param collection
     */
    public void setLanguageList(List collection)
    {
        languageList = collection;
    }

    /**
     * @param collection
     */
    public void setMaritalList(List collection)
    {
        maritalList = collection;
    }

    /**
     * @param name
     */
    public void setName(Name name)
    {
        this.name = name;
    }

    /**
     * @param string
     */
    public void setNationality(String string)
    {
        nationality = string;
    }

    /**
     * @param string
     */
    public void setNationalityId(String string)
    {
        nationalityId = string;
        if (nationalityId == null || nationalityId.equals(""))
        {
            nationality = "";
            return;
        }
        if (JuvenileMemberForm.nationalityList != null && JuvenileMemberForm.nationalityList.size() > 0)
        {
            nationality = CodeHelper.getCodeDescriptionByCode(JuvenileMemberForm.nationalityList, nationalityId);
        }
    }

    /**
     * @param collection
     */
    public void setNationalityList(List collection)
    {
        nationalityList = collection;
    }

    /**
	 * @param newAddressList the newAddressList to set
	 */
	public void setNewAddressList(List newAddressList) {
		this.newAddressList = newAddressList;
	}

	/**
     * @param collection
     */
    public void setPhoneNumberList(List collection)
    {
        phoneNumberList = collection;
    }

    /**
     * @param string
     */
    public void setPrimaryLanguage(String string)
    {
        primaryLanguage = string;
    }

    /**
     * @param string
     */
    public void setPrimaryLanguageId(String string)
    {
        primaryLanguageId = string;
        if (primaryLanguageId == null || primaryLanguageId.equals(""))
        {
            primaryLanguage = "";
            return;
        }
        if (JuvenileMemberForm.languageList != null && JuvenileMemberForm.languageList.size() > 0)
        {
            primaryLanguage = CodeHelper.getCodeDescriptionByCode(JuvenileMemberForm.languageList, primaryLanguageId);
        }
    }

    /**
     * @param collection
     */
    public void setRelationshipToJuvenileList(List collection)
    {
        relationshipToJuvenileList = collection;
    }

    /**
     * @param string
     */
    public void setRelationToJuv(String string)
    {
        relationToJuv = string;
    }

    /**
     * @param string
     */
    public void setRelationToJuvId(String string)
    {
        relationToJuvId = string;
        if (relationToJuvId == null || relationToJuvId.equals(""))
        {
            relationToJuv = "";
            return;
        }
        if (JuvenileMemberForm.relationshipToJuvenileList != null
                && JuvenileMemberForm.relationshipToJuvenileList.size() > 0)
        {
            relationToJuv = CodeHelper.getCodeDescriptionByCode(JuvenileMemberForm.relationshipToJuvenileList,
                    relationToJuvId);
        }
    }
    /**
     * @param string
     */
    public void setSecondaryAction(String string)
    {
        secondaryAction = string;
    }
    
    /**
     * @param string
     */
    public void setSecondaryLanguage(String string)
    {
        secondaryLanguage = string;
    }

    /**
     * @param string
     */
    public void setSecondaryLanguageId(String string)
    {
        secondaryLanguageId = string;
        if (secondaryLanguageId == null || secondaryLanguageId.equals(""))
        {
            secondaryLanguage = "";
            return;
        }
        if (JuvenileMemberForm.languageList != null && JuvenileMemberForm.languageList.size() > 0)
        {
            secondaryLanguage = CodeHelper.getCodeDescriptionByCode(JuvenileMemberForm.languageList,
                    secondaryLanguageId);
        }
    }

    /**
     * @param string
     */
    public void setSex(String string)
    {
        sex = string;
    }

    /**
     * @param string
     */
    public void setSexId(String string)
    {
        sexId = string;
        if (sexId == null || sexId.equals(""))
        {
            sex = "";
            return;
        }

        if (JuvenileMemberForm.sexList != null && JuvenileMemberForm.sexList.size() > 0)
        {
            sex = CodeHelper.getCodeDescriptionByCode(JuvenileMemberForm.sexList, sexId);
        }
    }

    /**
     * @param string
     */
    public void setSidNum(String string)
    {
        sidNum = string;
    }

    /**
     * @param security
     */
    public void setSsn(SocialSecurity security)
    {
        ssn = security;
    }

    /**
     * @param string
     */
    public void setStateIssuedIdNum(String string)
    {
        stateIssuedIdNum = string;
    }
    
    /**
     * @param string
     */
    public void setPassportNum(String string)
    {
        passportNum = string;
    }
    
    /**
     * @param string
     */
    public void setCountryOfIssuance(String string)
    {
        countryOfIssuance = string;
    }

 /**
     * @param string
     */
    public void setCountryOfIssuanceId(String string)
    {
        countryOfIssuanceId= string;
        if (countryOfIssuanceId == null || countryOfIssuanceId.equals(""))
        {
            countryOfIssuance = "";
            return;
        }
        if (JuvenileMemberForm.nationalityList != null && JuvenileMemberForm.nationalityList.size() > 0)
        {
            countryOfIssuance = CodeHelper.getCodeDescriptionByCode(JuvenileMemberForm.nationalityList, countryOfIssuanceId);
        }
    }


    /**
     * @param collection
     */
    public void setCountryOfIssuanceList(List collection)
    {
        countryOfIssuanceList = collection;
    }
    

    /**
     * @param string
     */
    public void setPassportExpirationDate(String string)
    {
        passportExpirationDate = string;
    } 
    /**
     * @param collection
     */
    public void setStateList(List collection)
    {
        stateList = collection;
    }

    /**
     * @param collection
     */
    public void setTraitList(List collection)
    {
        traitList = collection;
    }

    /**
     * @param b
     */
    public void setUpdate(boolean b)
    {
        update = b;
    }

    /**
     * @return
     */
    public String getJuvenileNumber()
    {
        return juvenileNumber;
    }

    /**
     * @param string
     */
    public void setJuvenileNumber(String string)
    {
        juvenileNumber = string;
    }

    /**
     * @return
     */
    public String getJuvenileAgeAtDeath()
    {
        return juvenileAgeAtDeath;
    }

    /**
     * @param sting
     */
    public void setJuvenileAgeAtDeath(String string)
    {
        juvenileAgeAtDeath = string;
    }

    /**
     * @return
     */
    public String getSelectedValue()
    {
        return selectedValue;
    }

    /**
     * @param string
     */
    public void setSelectedValue(String string)
    {
        selectedValue = string;
    }

    /**
     * @return
     */
    public String getFamilyNumber()
    {
        return familyNumber;
    }

    /**
     * @param string
     */
    public void setFamilyNumber(String string)
    {
        familyNumber = string;
    }

    /**
     * @return
     */
    public List getDriversLicenseClassList()
    {
        return driversLicenseClassList;
    }

    /**
     * @return
     */
    public List getInvolvementLevelList()
    {
        return involvementLevelList;
    }

    /**
     * @return
     */
    public List getMaritalStatusList()
    {
        return maritalStatusList;
    }

    /**
     * @return
     */
    public List getSexList()
    {
        return sexList;
    }

    /**
     * @return
     */
    public List getTraitDescList()
    {
        return traitDescList;
    }

    /**
     * @return
     */
    public List getTraitLevelList()
    {
        return traitLevelList;
    }

    /**
     * @return
     */
    public List getTraitStatusList()
    {
        return traitStatusList;
    }

    /**
     * @param collection
     */
    public void setDriversLicenseClassList(List collection)
    {
        driversLicenseClassList = collection;
    }

    /**
     * @param collection
     */
    public void setInvolvementLevelList(List collection)
    {
        involvementLevelList = collection;
    }

    /**
     * @param collection
     */
    public void setMaritalStatusList(List collection)
    {
        maritalStatusList = collection;
    }

    /**
     * @param collection
     */
    public void setSexList(List collection)
    {
        sexList = collection;
    }

    /**
     * @param collection
     */
    public void setTraitDescList(List collection)
    {
        traitDescList = collection;
    }

    /**
     * @param collection
     */
    public void setTraitLevelList(List collection)
    {
        traitLevelList = collection;
    }

    /**
     * @param collection
     */
    public void setTraitStatusList(List collection)
    {
        traitStatusList = collection;
    }

    /**
     * @return
     */
    public boolean isListsSet()
    {
        return listsSet;
    }

    /**
     * @param b
     */
    public void setListsSet(boolean b)
    {
        listsSet = b;
    }

    /**
     * @return
     */
    public String getDriverLicenseClassId()
    {
        return driverLicenseClassId;
    }

    /**
     * @param string
     */
    public void setDriverLicenseClassId(String string)
    {
        driverLicenseClassId = string;
        if (driverLicenseClassId == null || driverLicenseClassId.equals(""))
        {
            driverLicenseClass = "";
            return;
        }
        if (JuvenileMemberForm.driversLicenseClassList != null && JuvenileMemberForm.driversLicenseClassList.size() > 0)
        {
            driverLicenseClass = CodeHelper.getCodeDescriptionByCode(JuvenileMemberForm.driversLicenseClassList,
                    driverLicenseClassId);
        }

    }

    /**
     * @return
     */
    public String getDivorceDate()
    {
        return divorceDate;
    }

    /**
     * @return
     */
    public String getEntryDate()
    {
        return entryDate;
    }

    /**
     * @return
     */
    public String getMaritalStatus()
    {
        return maritalStatus;
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
    public String getMarriageDate()
    {
        return marriageDate;
    }

    /**
     * @return
     */
    public String getNumOfChildren()
    {
        return numOfChildren;
    }

    /**
     * @param string
     */
    public void setDivorceDate(String string)
    {
        divorceDate = string;
    }

    /**
     * @param string
     */
    public void setEntryDate(String string)
    {
        entryDate = string;
    }

    /**
     * @param string
     */
    public void setMaritalStatus(String string)
    {
        maritalStatus = string;
    }

    /**
     * @param string
     */
    public void setMaritalStatusId(String string)
    {
        maritalStatusId = string;
        if (maritalStatusId == null || maritalStatusId.equals(""))
        {
            maritalStatus = "";
            return;
        }
        if (maritalStatusList != null && maritalStatusList.size() > 0)
        {
            maritalStatus = CodeHelper.getCodeDescriptionByCode(maritalStatusList, maritalStatusId);
        }
    }

    /**
     * @param string
     */
    public void setMarriageDate(String string)
    {
        marriageDate = string;
    }

    /**
     * @param string
     */
    public void setNumOfChildren(String string)
    {
        numOfChildren = string;
    }

    /**
     * @return
     */
    public MemberAddress getCurrentAddress()
    {
        return currentAddress;
    }

    /**
     * @param address
     */
    public void setCurrentAddress(MemberAddress address)
    {
        currentAddress = address;
    }
    
    public MemberAddress getPreviousAddress()
    {
        return previousAddress;
    }

    /**
     * @param address
     */
    public void setPreviousAddress(MemberAddress address)
    {
	previousAddress = address;
    }

    public void setNationalityId(boolean myVal)
    {
        if (myVal)
            nationalityId = "YES";
        else
            nationalityId = "NO";
    }

    /**
     * @return
     */
    public List getAddressTypeList()
    {
        return addressTypeList;
    }

    /**
     * @return
     */
    public List getCountyList()
    {
        return countyList;
    }

    /**
     * @return
     */
    public List getStreetTypeList()
    {
        return streetTypeList;
    }
    /**
     * @return
     */
    public List getStreetNumSuffixList()
    {
        return streetNumSuffixList;
    }

    /**
     * @param collection
     */
    public void setAddressTypeList(List collection)
    {
        addressTypeList = collection;
    }

    /**
     * @param collection
     */
    public void setCountyList(List collection)
    {
        countyList = collection;
    }

    /**
     * @param collection
     */
    public void setStreetTypeList(List collection)
    {
        streetTypeList = collection;
    }
    /**
     * @param collection
     */
    public void setStreetNumSuffixList(List collection)
    {
        streetNumSuffixList = collection;
    }

    /**
     * @return
     */
    public List getContactTypeList()
    {
        return contactTypeList;
    }

    /**
     * @param collection
     */
    public void setContactTypeList(List collection)
    {
        contactTypeList = collection;
    }

    /**
     * @return
     */
    public MemberContact getCurrentContact()
    {
        return currentContact;
    }

    /**
     * @return
     */
    public JuvenileFamilyForm.Trait getCurrentTrait()
    {
        return currentTrait;
    }

    /**
     * @param contact
     */
    public void setCurrentContact(MemberContact contact)
    {
        currentContact = contact;
    }

    /**
     * @param trait
     */
    public void setCurrentTrait(JuvenileFamilyForm.Trait trait)
    {
        currentTrait = trait;
    }

    /**
     * @return
     */
    public List getEmploymentStatusList()
    {
        return employmentStatusList;
    }

    /**
     * @return
     */
    public List getSalaryRateList()
    {
        return salaryRateList;
    }

    /**
     * @param collection
     */
    public void setEmploymentStatusList(List collection)
    {
        employmentStatusList = collection;
    }

    /**
     * @param collection
     */
    public void setSalaryRateList(List collection)
    {
        salaryRateList = collection;
    }

    /**
     * @return
     */
    public List getBenefitEligibilityList()
    {
        return benefitEligibilityList;
    }

    /**
     * @param collection
     */
    public void setBenefitEligibilityList(List collection)
    {
        benefitEligibilityList = collection;
    }

    /**
     * @return
     */
    public List getInsuranceTypeList()
    {
        return insuranceTypeList;
    }

    /**
     * @param collection
     */
    public void setInsuranceTypeList(List collection)
    {
        insuranceTypeList = collection;
    }

    /**
     * @return
     */
    public MemberBenefit getCurrentBenefit()
    {
        return currentBenefit;
    }

    /**
     * @return
     */
    public MemberEmployment getCurrentEmployment()
    {
        return currentEmployment;
    }

    /**
     * @return
     */
    public MemberInsurance getCurrentInsurance()
    {
        return currentInsurance;
    }

    /**
     * @param benefit
     */
    public void setCurrentBenefit(MemberBenefit benefit)
    {
        currentBenefit = benefit;
    }

    /**
     * @param employment
     */
    public void setCurrentEmployment(MemberEmployment employment)
    {
        currentEmployment = employment;
    }

    /**
     * @param insurance
     */
    public void setCurrentInsurance(MemberInsurance insurance)
    {
        currentInsurance = insurance;
    }

    /**
     * @return
     */
    public String getMemberNumber()
    {
        return memberNumber;
    }

    /**
     * @param string
     */
    public void setMemberNumber(String string)
    {
        memberNumber = string;
    }

    /**
     * @return
     */
    public List getGuardianList()
    {
        return guardianList;
    }

    /**
     * @param collection
     */
    public void setGuardianList(List collection)
    {
        guardianList = collection;
    }

    /**
     * @return
     */
    public JuvenileFamilyForm.Guardian getCurrentGuardian()
    {
        return currentGuardian;
    }

    /**
     * @param guardian
     */
    public void setCurrentGuardian(JuvenileFamilyForm.Guardian guardian)
    {
        currentGuardian = guardian;
    }

    /**
     * @return
     */
    public String getSelectedConsRelationId()
    {
        return selectedConsRelationId;
    }

    /**
     * @return
     */
    public String getSelectedEntryDate()
    {
        return selectedEntryDate;
    }

    /**
     * @param string
     */
    public void setSelectedConsRelationId(String string)
    {
        selectedConsRelationId = string;
    }

    /**
     * @param string
     */
    public void setSelectedEntryDate(String string)
    {
        selectedEntryDate = string;
    }

    /**
     * @return
     */
    public Name getOrigName()
    {
        return origName;
    }

    /**
     * @return
     */
    public SocialSecurity getOrigSSN()
    {
        return origSSN;
    }

    /**
     * @param name
     */
    public void setOrigName(Name name)
    {
        origName = name;
    }

    /**
     * @param security
     */
    public void setOrigSSN(SocialSecurity security)
    {
        origSSN = security;
    }

    /**
     * @return
     */
    public String getSuspiciousMember()
    {
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
     * @return Returns the isUSCitizenId.
     */
    public String getIsUSCitizenId()
    {
        return isUSCitizenId;
    }

    /**
     * @param isUSCitizenId
     *            The isUSCitizenId to set.
     */
    public void setIsUSCitizenId(String isUSCitizenId)
    {
        this.isUSCitizenId = isUSCitizenId;
        if (isUSCitizenId == null || isUSCitizenId.equals(""))
        {
            isUSCitizen = "";
            return;
        }

        if (JuvenileMemberForm.isUSCitizenList != null && JuvenileMemberForm.isUSCitizenList.size() > 0)
        {
            isUSCitizen = CodeHelper.getCodeDescriptionByCode(JuvenileMemberForm.isUSCitizenList, isUSCitizenId);
        }
    }

    /**
     * @param isUSCitizenList1
     */
    public void setIsUSCitizenList(List isUSCitizenList1)
    {
        isUSCitizenList = isUSCitizenList1;
    }

    /**
     * 
     * @return The isUSCitizen list
     */
    public List getIsUSCitizenList()
    {
        return CodeHelper.getIsUSCitizenList();
    } //end of ui.juvenilecase.form.JuvenileMainForm.getIsUSCitizenList

    /**
     * @return Returns the isUSCitizen.
     */
    public String getIsUSCitizen()
    {
        return isUSCitizen;
    }

    /**
     * @param isUSCitizen
     *            The isUSCitizen to set.
     */
    public void setIsUSCitizen(String isUSCitizen)
    {
        this.isUSCitizen = isUSCitizen;
    }

    /**
     * @return Returns the maritalRelWithList.
     */
    public List getMaritalRelWithList()
    {
        if (maritalRelWithList == null)
            return new ArrayList();
        else
            return maritalRelWithList;
    }

    /**
     * @param maritalRelWithList
     *            The maritalRelWithList to set.
     */
    public void setMaritalRelWithList(List maritalRelWithList)
    {
        this.maritalRelWithList = maritalRelWithList;
    }

    /**
     * @return Returns the relatedFamMemName.
     */
    public IName getRelatedFamMemName()
    {
        return relatedFamMemName;
    }

    /**
     * @param relatedFamMemName
     *            The relatedFamMemName to set.
     */
    public void setRelatedFamMemName(IName relatedFamMemName)
    {
        this.relatedFamMemName = relatedFamMemName;
    }

    /**
     * @return Returns the associatedJuveniles.
     */
    public List getAssociatedJuveniles()
    {
        if (associatedJuveniles == null)
            associatedJuveniles = new ArrayList();
        return associatedJuveniles;
    }

    /**
     * @param associatedJuveniles
     *            The associatedJuveniles to set.
     */
    public void setAssociatedJuveniles(List aAssociatedJuveniles)
    {
        this.associatedJuveniles = aAssociatedJuveniles;
    }

	public void setBypassMatches(boolean bypassMatches) {
		this.bypassMatches = bypassMatches;
	}

	public boolean isBypassMatches() {
		return bypassMatches;
	}

	/**
	 * @return the searchJuvenileNumber
	 */
	public String getSearchJuvenileNumber() {
		return searchJuvenileNumber;
	}

	/**
	 * @param searchJuvenileNumber the searchJuvenileNumber to set
	 */
	public void setSearchJuvenileNumber(String searchJuvenileNumber) {
		this.searchJuvenileNumber = searchJuvenileNumber;
	}
    
	
	/**
	 * @return the hasActiveCasefile
	 */
	public boolean isHasActiveCasefile() {
		return hasActiveCasefile;
	}

	/**
	 * @param hasActiveCasefile the hasActiveCasefile to set
	 */
	public void setHasActiveCasefile(boolean hasActiveCasefile) {
		this.hasActiveCasefile = hasActiveCasefile;
	}

	public boolean isOver21() {
		return over21;
	}

	public void setOver21(boolean over21) {
		this.over21 = over21;
	}

	public String getOver21Str() {
		return over21Str;
	}

	public void setOver21Str(String over21Str) {
		this.over21Str = over21Str;
	}

	public SocialSecurity getCompleteSSN() {
		return completeSSN;
	}

	public void setCompleteSSN(SocialSecurity completeSSN) {
		this.completeSSN = completeSSN;
	}

	

    public String getSuspiciousMemberMatchType()
	{
	    return suspiciousMemberMatchType;
	}

	public void setSuspiciousMemberMatchType(String suspiciousMemberMatchType)
	{
	    this.suspiciousMemberMatchType = suspiciousMemberMatchType;
	}



    // *********************************************************
// *     BEGIN MemberInsurance class
// *********************************************************
    public static class MemberInsurance implements Comparable
    {
        private String entryDate = DateUtil.dateToString(new Date(), UIConstants.DATE_FMT_1);

        private String insuranceType = "";

        private String insuranceTypeId = "";

        private String memberInsuranceId = "";

        private String insuranceCarrier = "";

        private String policyNumber = "";

        public int compareTo(Object obj) throws ClassCastException
        {
            if (obj == null)
                return -1;
            MemberInsurance evt = (MemberInsurance) obj;
            int result = 0;
            if (getEntryDate() != null && evt.getEntryDate() != null)
            {
                try
                {
                    if (this.entryDate != null || evt.getEntryDate() != null)
                    {
                        if (evt.getEntryDate() == null && !(evt.getEntryDate().trim().equals("")))
                            return 1; // this makes any null objects go to the bottom 
                        			  // change this to -1 if you want the top of the list
                        if (this.entryDate == null && !(this.getEntryDate().trim().equals("")))
                            return -1; // this makes any null objects go to the bottom 
                        			   // change this to 1 if you want the top of the list
                        Date currDate = DateUtil.stringToDate(this.entryDate, UIConstants.DATE_FMT_1);
                        Date incomingDate = DateUtil.stringToDate(evt.getEntryDate(), UIConstants.DATE_FMT_1);
                        if (currDate == null)
                            return -1;
                        if (incomingDate == null)
                            return 1;
                        // backwards in order to get list to show up most recent first
                        result = incomingDate.compareTo(currDate); 
                    }
                }
                catch (NumberFormatException e)
                {
                    result = 0;
                }
            }
            return result;
        }

        /**
         * @return
         */
        public String getEntryDate()
        {
            return entryDate;
        }

        /**
         * @return
         */
        public String getInsuranceCarrier()
        {
            return insuranceCarrier;
        }

        /**
         * @return
         */
        public String getMemberInsuranceId()
        {
            return memberInsuranceId;
        }

        /**
         * @return
         */
        public String getInsuranceType()
        {
            return insuranceType;
        }

        /**
         * @return
         */
        public String getInsuranceTypeId()
        {
            return insuranceTypeId;
        }

        /**
         * @return
         */
        public String getPolicyNumber()
        {
            return policyNumber;
        }

        /**
         * @param string
         */
        public void setEntryDate(String string)
        {
            entryDate = string;
        }

        /**
         * @param string
         */
        public void setInsuranceCarrier(String string)
        {
            insuranceCarrier = string;
        }

        /**
         * @param string
         */
        public void setMemberInsuranceId(String string)
        {
            memberInsuranceId = string;

        }

        /**
         * @param string
         */
        public void setInsuranceType(String string)
        {
            insuranceType = string;
        }

        /**
         * @param string
         */
        public void setInsuranceTypeId(String string)
        {
            insuranceTypeId = string;
            if (insuranceTypeId == null || insuranceTypeId.equals(""))
            {
                insuranceType = "";
                return;
            }

            if (insuranceTypeList != null && insuranceTypeList.size() > 0)
            {
                setInsuranceType(CodeHelper.getCodeDescriptionByCode(JuvenileMemberForm.insuranceTypeList,
                        insuranceTypeId));
            }
        }

        /**
         * @param string
         */
        public void setPolicyNumber(String string)
        {
            policyNumber = string;
        }
    }
// ***********************************************************
// *     END MemberInsurance class
// ***********************************************************
    
// ***********************************************************
// *     BEGIN MemberBenefit class
// ***********************************************************
    public static class MemberBenefit implements Comparable
    {
        private String entryDate = DateUtil.dateToString(new Date(), UIConstants.DATE_FMT_1);

        private String eligibilityType = "";

        private String eligibilityTypeId = "";

        private String eligibilityId = "";

        private boolean eligibleForBenefits = false;

        private boolean receivingBenefits = false;

        private String memberBenefitId = "";
        
        //Added for US 27022
        private int receivedAmt=0;
        
        private String idNumber="";
        
        private String receivedBy="";
       
        private String receivedByFirstName="";
        
        private String receivedByMiddleName="";
        
        private String receivedByLastName="";
        
        private String benefitStatus;
		/**
		 * @return the idNumber
		 */
		public String getIdNumber() {
			return idNumber;
		}

		/**
		 * @param idNumber the idNumber to set
		 */
		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}

		/**
		 * @return the receivedAmt
		 */
		public int getReceivedAmt() {
			return receivedAmt;
		}

		/**
		 * @param receivedAmt the receivedAmt to set
		 */
		public void setReceivedAmt(int receivedAmt) {
			this.receivedAmt = receivedAmt;
		}

		/**
		 * @return the receivedBy
		 */
		public String getReceivedBy() {
			return receivedBy;
		}

		/**
		 * @param receivedBy the receivedBy to set
		 */
		public void setReceivedBy(String receivedBy) {
			this.receivedBy = receivedBy;
		}


		public String getFormattedName()
		{
			Name name = new Name(receivedByFirstName, receivedByMiddleName, receivedByLastName);
			return name.getFormattedName().trim();	
		}
		

        public int compareTo(Object obj) throws ClassCastException
        {
            if (obj == null)
                return -1;
            MemberBenefit evt = (MemberBenefit) obj;
            int result = 0;
            if (memberBenefitId != null && evt.getMemberBenefitId() != null)
            {
                try
                {
                    if (this.entryDate != null || evt.getEntryDate() != null)
                    {
                        if (evt.getEntryDate() == null && !(evt.getEntryDate().trim().equals("")))
                        	return 1; // this makes any null objects go to the bottom 
          			  				  // change this to -1 if you want the top of the list
                        if (this.entryDate == null && !(this.getEntryDate().trim().equals("")))
                            return -1;  // this makes any null objects go to the bottom 
         			   					// change this to 1 if you want the top of the list
                        Date currDate = DateUtil.stringToDate(this.entryDate, UIConstants.DATE_FMT_1);
                        Date incomingDate = DateUtil.stringToDate(evt.getEntryDate(), UIConstants.DATE_FMT_1);
                        if (currDate == null)
                            return -1;
                        if (incomingDate == null)
                            return 1;
                        // backwards in order to get list to show up most recent first
                        result = incomingDate.compareTo(currDate); 
                    }

                }
                catch (NumberFormatException e)
                {
                    result = 0;
                }
            }
            return result;
        }

        /**
         * @return
         */
        public String getEligibilityId()
        {
            return eligibilityId;
        }

        /**
         * @return
         */
        public String getEligibilityType()
        {
            return eligibilityType;
        }

        /**
         * @return
         */
        public String getEligibilityTypeId()
        {
            return eligibilityTypeId;
        }

        /**
         * @return
         */
        public boolean isEligibleForBenefits()
        {
            return eligibleForBenefits;
        }

        /**
         * @return
         */
        public String getEntryDate()
        {
            return entryDate;
        }

        /**
         * @return
         */
        public boolean isReceivingBenefits()
        {
            return receivingBenefits;
        }

        /**
         * @param string
         */
        public void setEligibilityId(String string)
        {
            eligibilityId = string;
        }

        /**
         * @param string
         */
        public void setEligibilityType(String string)
        {
            eligibilityType = string;
        }

        /**
         * @param string
         */
        public void setEligibilityTypeId(String string)
        {
            eligibilityTypeId = string;
            if (eligibilityTypeId == null || eligibilityTypeId.equals(""))
            {
                eligibilityType = "";
                return;
            }

            setEligibilityType(CodeHelper.getCodeDescription(PDCodeTableConstants.MEMBER_BENEFIT_ELIGIBILITY_TYPE,
                    string));

        }

        /**
         * @param b
         */
        public void setEligibleForBenefits(boolean b)
        {
            eligibleForBenefits = b;
        }

        /**
         * @param string
         */
        public void setEntryDate(String string)
        {
            entryDate = string;
        }

        /**
         * @param b
         */
        public void setReceivingBenefits(boolean b)
        {
            receivingBenefits = b;
        }

        /**
         * @return
         */
        public String getMemberBenefitId()
        {
            return memberBenefitId;
        }

        /**
         * @param string
         */
        public void setMemberBenefitId(String string)
        {
            memberBenefitId = string;
        }

		/**
		 * @return the receivedByFirstName
		 */
		public String getReceivedByFirstName() {
			return receivedByFirstName;
		}

		/**
		 * @param receivedByFirstName the receivedByFirstName to set
		 */
		public void setReceivedByFirstName(String receivedByFirstName) {
			this.receivedByFirstName = receivedByFirstName;
		}

		/**
		 * @return the receivedByMiddleName
		 */
		public String getReceivedByMiddleName() {
			return receivedByMiddleName;
		}

		/**
		 * @param receivedByMiddleName the receivedByMiddleName to set
		 */
		public void setReceivedByMiddleName(String receivedByMiddleName) {
			this.receivedByMiddleName = receivedByMiddleName;
		}

		/**
		 * @return the receivedByLastName
		 */
		public String getReceivedByLastName() {
			return receivedByLastName;
		}

		/**
		 * @param receivedByLastName the receivedByLastName to set
		 */
		public void setReceivedByLastName(String receivedByLastName) {
			this.receivedByLastName = receivedByLastName;
		}

		/**
		 * @return the benefitStatus
		 */
		public String getBenefitStatus() {
			return benefitStatus;
		}

		/**
		 * @param benefitStatus the benefitStatus to set
		 */
		public void setBenefitStatus(String benefitStatus) {
			this.benefitStatus = benefitStatus;
		}
    }
//***********************************************************
// *     END MemberBenefit class
// **********************************************************
    
// **********************************************************
// *     BEGIN MemberEmployment class
// **********************************************************
    public static class MemberEmployment implements Comparable
    {
        private String entryDate = DateUtil.dateToString(new Date(), UIConstants.DATE_FMT_1);

        private String employmentStatusId = "";

        private String employmentStatus = "";

        private String currentEmployer = "";

        private String salary = "";

        private String workHours = "";

        private String jobTitle = "";

        private String salaryRate = "";

        private String salaryRateId = "";

        private String lengthOfEmployment = "";

        private String memberEmploymentId = "";

        private String annualNetIncome = "";

        private boolean isSelected = false;

        public int compareTo(Object obj) throws ClassCastException
        {
            if (obj == null)
                return -1;
            MemberEmployment evt = (MemberEmployment) obj;
            int result = 0;
            if (getEntryDate() != null && evt.getEntryDate() != null)
            {
                try
                {
                    if (this.entryDate != null || evt.getEntryDate() != null)
                    {
                        if (evt.getEntryDate() == null && !(evt.getEntryDate().trim().equals("")))
                            return 1; // this makes any null objects go to the bottom 
                        			  // change this to -1if you want the top of the list
                        if (this.entryDate == null && !(this.getEntryDate().trim().equals("")))
                            return -1;  // this makes any null objects go to the bottom 
         			   					// change this to 1 if you want the top of the list
                        Date currDate = DateUtil.stringToDate(this.entryDate, UIConstants.DATE_FMT_1);
                        Date incomingDate = DateUtil.stringToDate(evt.getEntryDate(), UIConstants.DATE_FMT_1);
                        if (currDate == null)
                            return -1;
                        if (incomingDate == null)
                            return 1;
                        // backwards in order to get list to show up most recent first
                        result = incomingDate.compareTo(currDate); 
                    }
                }
                catch (NumberFormatException e)
                {
                    result = 0;
                }
            }
            return result;
        }

        /**
         * @return
         */
        public String getCurrentEmployer()
        {
            return currentEmployer;
        }

        /**
         * @return
         */
        public String getEmploymentStatus()
        {
            return employmentStatus;
        }

        /**
         * @return
         */
        public String getMemberEmploymentId()
        {
            return memberEmploymentId;
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
        public String getEntryDate()
        {
            return entryDate;
        }

        /**
         * @return
         */
        public String getJobTitle()
        {
            return jobTitle;
        }

        /**
         * @return
         */
        public String getLengthOfEmployment()
        {
            return lengthOfEmployment;
        }

        /**
         * @return
         */
        public String getSalary()
        {
            return salary;
        }

        /**
         * @return
         */
        public String getSalaryRate()
        {
            return salaryRate;
        }

        /**
         * @return
         */
        public String getSalaryRateId()
        {
            return salaryRateId;
        }

        /**
         * @return
         */
        public String getWorkHours()
        {
            return workHours;
        }

        /**
         * @param string
         */
        public void setCurrentEmployer(String string)
        {
            currentEmployer = string;
        }

        /**
         * @param string
         */
        public void setEmploymentStatus(String string)
        {
            employmentStatus = string;
        }

        /**
         * @param string
         */
        public void setMemberEmploymentId(String string)
        {
            memberEmploymentId = string;
        }

        /**
         * @param string
         */
        public void setEmploymentStatusId(String string)
        {
            employmentStatusId = string;
            if (employmentStatusId == null || employmentStatusId.equals(""))
            {
                employmentStatus = "";
                return;
            }
            if (employmentStatusList != null && employmentStatusList.size() > 0)
            {
            }
            else
            {
                JuvenileMemberForm myForm = new JuvenileMemberForm();

            }
            if (employmentStatusList != null && employmentStatusList.size() > 0)
            {
                setEmploymentStatus(CodeHelper.getCodeDescriptionByCode(employmentStatusList, employmentStatusId));

            }

        }

        /**
         * @param string
         */
        public void setEntryDate(String string)
        {
            entryDate = string;
        }

        /**
         * @param string
         */
        public void setJobTitle(String string)
        {
            jobTitle = string;
        }

        /**
         * @param string
         */
        public void setLengthOfEmployment(String string)
        {
            lengthOfEmployment = string;
        }

        /**
         * @param string
         */
        public void setSalary(String string)
        {
            if (string != null && !(string.trim().equals("")))
            {
                salary = UIUtil.formatCurrency(string, UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, false, "");
            }
            else
                salary = string;
        }

        /**
         * @param string
         */
        public void setSalaryRate(String string)
        {
            salaryRate = string;
        }

        /**
         * @param string
         */
        public void setSalaryRateId(String string)
        {
            salaryRateId = string;
            if (salaryRateId == null || salaryRateId.equals(""))
            {
                salaryRate = "";
                return;
            }
            if (salaryRateList != null && salaryRateList.size() > 0)
            {
                setSalaryRate(CodeHelper.getCodeDescriptionByCode(salaryRateList, salaryRateId));
            }
        }

        /**
         * @param string
         */
        public void setWorkHours(String string)
        {
            workHours = string;
        }

        /**
         * @return
         */
        public String getFormattedAnnualNetIncome()
        {

            String myTemp = UIUtil.formatCurrency(annualNetIncome, UIConstants.CURRENCY_US_DEFAULT_POSITIVE_FORMAT,
                    true, "");
            if (myTemp != null && !(myTemp.trim().equals("")))
            {
                return "$" + myTemp;
            }
            else
                return "";
        }

        /**
         * @return
         */
        public String getAnnualNetIncome()
        {
            return annualNetIncome;
        }

        /**
         * @param string
         */
        public void setAnnualNetIncome(String string)
        {
            if (string != null && !(string.trim().equals("")))
            {
                annualNetIncome = UIUtil.formatCurrency(new Double(string),
                        UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT);
            }
            else
                annualNetIncome = string;
        }

        public double getAnnualNetIncomeAsDouble()
        {
            return UIUtil.getDoubleFromString(this.getAnnualNetIncome());
        }

        /**
         * @return Returns the isSelected.
         */
        public boolean isSelected()
        {
            return isSelected;
        }

        /**
         * @param isSelected
         *            The isSelected to set.
         */
        public void setSelected(boolean isSelected)
        {
            this.isSelected = isSelected;
        }
    }
// **********************************************************
// *     END MemberEmployment class
// **********************************************************
    
//***********************************************************
//*     BEGIN MemberContact class
//***********************************************************
    public static class MemberContact implements Comparable
    {
        PhoneNumber contactPhoneNumber = new PhoneNumber("");

        String contactTypeId = "";

        String contactType = "";

        String emailContactTypeId = "";

        String emailContactType = "";

        private String emailAddress = "";

        private boolean isPhone = true;
        
        private String primaryInd = "";
        private String primaryIndEmail = "";

        private String entryDate = DateUtil.dateToString(new Date(), UIConstants.DATE_FMT_1);
        
        private Date entryDateTime = new Date();

        private String memberContactId = "";

        private String validAddressInd = "";

        public int compareTo(Object obj) throws ClassCastException
        {
            if (obj == null)
                return -1;
            MemberContact evt = (MemberContact) obj;
            int result = 0;
            if (getEntryDate() != null && evt.getEntryDate() != null)
            {
                try
                {
                    if (this.entryDate != null || evt.getEntryDate() != null)
                    {
                        if (evt.getEntryDate() == null && !(evt.getEntryDate().trim().equals("")))
                            return 1; // this makes any null objects go to the bottom change this to -1
                                      // if you want the top of the list
                        if (this.entryDate == null && !(this.getEntryDate().trim().equals("")))
                            return -1; // this makes any null objects go to the bottom change this to 1
                        			   // if you want the top of the list
                        Date currDate = DateUtil.stringToDate(this.entryDate, UIConstants.DATE_FMT_1);
                        Date incomingDate = DateUtil.stringToDate(evt.getEntryDate(), UIConstants.DATE_FMT_1);
                        if (currDate == null)
                            return -1;
                        if (incomingDate == null)
                            return 1;
                     // backwards in order to get list to show up most recent first
                        result = incomingDate.compareTo(currDate); 
                    }
                }
                catch (NumberFormatException e)
                {
                    result = 0;
                }
            }
            return result;
        }

        /**
         * @return
         */
        public PhoneNumber getContactPhoneNumber()
        {
            return contactPhoneNumber;
        }

        /**
         * @return
         */
        public String getContactType()
        {
            return contactType;
        }

        /**
         * @return
         */
        public String getContactTypeId()
        {
            return contactTypeId;

        }

        /**
         * @return
         */
        public String getEntryDate()
        {
            return entryDate;
        }

        /**
         * @param number
         */
        public void setContactPhoneNumber(PhoneNumber number)
        {
            contactPhoneNumber = number;
        }

        /**
         * @param string
         */
        public void setContactType(String string)
        {
            contactType = string;
        }

        /**
         * @param string
         */
        public void setContactTypeId(String string)
        {
            contactTypeId = string;
            if (contactTypeId == null || contactTypeId.equals(""))
            {
                contactType = "";
                return;
            }
            if (contactTypeList != null && contactTypeList.size() > 0)
            {
                setContactType(CodeHelper.getCodeDescriptionByCode(contactTypeList, contactTypeId));
            }
        }

        /**
         * @param string
         */
        public void setEntryDate(String string)
        {
            entryDate = string;
        }

        /**
         * @return
         */
        public String getMemberContactId()
        {
            return memberContactId;
        }

        /**
         * @param string
         */
        public void setMemberContactId(String string)
        {
            memberContactId = string;
        }

        /**
         * @return
         */
        public String getValidAddressInd()
        {
            return validAddressInd;
        }

        /**
         * @param string
         */
        public void setValidAddressInd(String string)
        {
            validAddressInd = string;
        }

        /**
         * @return Returns the emailContactType.
         */
        public String getEmailContactType()
        {
            return emailContactType;
        }

        /**
         * @param emailContactType
         *            The emailContactType to set.
         */
        public void setEmailContactType(String emailContactType)
        {
            this.emailContactType = emailContactType;
        }

        /**
         * @return Returns the emailContactTypeId.
         */
        public String getEmailContactTypeId()
        {
            return emailContactTypeId;
        }

        /**
         * @param emailContactTypeId
         *            The emailContactTypeId to set.
         */
        public void setEmailContactTypeId(String emailContactTypeId)
        {
            this.emailContactTypeId = emailContactTypeId;
            this.emailContactType = "";
            emailContactType = (SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.EMAIL_TYPE,
                    emailContactTypeId));

        }

        /**
         * @return Returns the isPhone.
         */
        public boolean isPhone()
        {
            return isPhone;
        }

        /**
         * @param isPhone
         *            The isPhone to set.
         */
        public void setPhone(boolean isPhone)
        {
            this.isPhone = isPhone;
        }

        /**
         * @return Returns the emailAddress.
         */
        public String getEmailAddress()
        {
            return emailAddress;
        }

        /**
         * @param emailAddress
         *            The emailAddress to set.
         */
        public void setEmailAddress(String emailAddress)
        {
            this.emailAddress = emailAddress;
        }

		/**
		 * @return the primaryInd
		 */
		public String getPrimaryInd() {
			return primaryInd;
		}
		/**
		 * @param primaryInd the primaryInd to set
		 */
		public void setPrimaryInd(String primaryInd) {
			this.primaryInd = primaryInd;
		}
		
		public String getPrimaryIndEmail() {
			return this.primaryIndEmail;
		}

		public void setPrimaryIndEmail(String primaryInd) {
			this.primaryIndEmail = primaryInd;
		}

		public Date getEntryDateTime() {
			return entryDateTime;
		}

		public void setEntryDateTime(Date entryDateTime) {
			this.entryDateTime = entryDateTime;
		}
    }
// **********************************************************
// *     END MemberContact class
// **********************************************************
    
// **********************************************************
// *     BEGIN MemberAddress class
// **********************************************************
    public static class MemberAddress extends Address implements Comparable
    {
        Address address = new Address();

        private String memberAddressId = "";

        /**
         * Defaults to a descending sort. Newest dates first, nulls at the bottom.
         */
        public int compareTo(Object obj) throws ClassCastException
        {
            if (obj == null)
            {
                return -1;
            }

            MemberAddress other = (MemberAddress) obj;

            // Both dates are null.
            if (getCreateDate() == null && other.getCreateDate() == null)
            {
                return 0;
            }

            // This date is null making it larger
            if (getCreateDate() == null)
            {
                return 1;
            }

            // The other date is null making it larger
            if (other.getCreateDate() == null)
            {
                return -1;
            }

            // Neither are null
            return other.getCreateDate().compareTo(getCreateDate());
        }

        /**
         * @return
         */

        public void setStreetTypeId(String string)
        {
            super.setStreetTypeId(string);
            if (getStreetTypeId() == null || getStreetTypeId().equals(""))
            {
                setStreetType("");
                return;
            }
            if (streetTypeList != null && streetTypeList.size() > 0)
            {
                setStreetType(CodeHelper.getCodeDescriptionByCode(streetTypeList, getStreetTypeId()));
            }

        }
        /**
         * @return
         */

        public void setStreetNumSuffixId(String string)
        {
            super.setStreetNumSuffixId(string);
            if (getStreetNumSuffixId() == null || getStreetNumSuffixId().equals(""))
            {
                setStreetNumSuffix("");
                return;
            }
            if (streetNumSuffixList != null && streetNumSuffixList.size() > 0)
            {
                setStreetNumSuffix(CodeHelper.getCodeDescriptionByCode(streetNumSuffixList, getStreetNumSuffixId()));
            }

        }

        public void setStateId(String string)
        {
            super.setStateId(string);
            if (getStateId() == null || getStateId().equals(""))
            {
                setState("");
                return;
            }
            if (stateList != null && stateList.size() > 0)
            {
                setState(CodeHelper.getCodeDescriptionByCode(stateList, getStateId()));
            }
        }

        public void setAddressTypeId(String string)
        {
            super.setAddressTypeId(string);
            if (getAddressTypeId() == null || getAddressTypeId().equals(""))
            {
                setAddressType("");
                return;
            }
            if (addressTypeList != null && addressTypeList.size() > 0)
            {
                setAddressType(CodeHelper.getCodeDescriptionByCode(addressTypeList, getAddressTypeId()));
            }
        }

        public void setCountyId(String string)
        {
            super.setCountyId(string);
            if (getCountyId() == null || getCountyId().equals(""))
            {
                setCounty("");
                return;
            }
            if (countyList != null && countyList.size() > 0)
            {
                setCounty(CodeHelper.getCodeDescriptionByCode(countyList, getCountyId()));
            }
        }

        public Address getAddress()
        {
            return address;
        }

        /**
         * @return
         */
        public String getEntryDateString()
        {
            String dateString = null;
            if (getCreateDate() != null)
            {
                dateString = DateUtil.dateToString(getCreateDate(), UIConstants.DATE_FMT_1);
            }
            return dateString;
        }

        /**
         * @param address
         */
        public void setAddress(Address address)
        {
            this.address = address;
        }

        /**
         * @param string
         */
        public void setCreateDate(String string)
        {
        }

        /**
         * @return
         */
        public String getMemberAddressId()
        {
            return memberAddressId;
        }

        /**
         * @param string
         */
        public void setMemberAddressId(String string)
        {
            memberAddressId = string;
        }

    }
// **********************************************************
// *     END MemberAddress class
// *********************************************************
// *********************************************************
// *     BEGIN MaritalList class
// *********************************************************
    public static class MaritalList implements Comparable
    {
        private String entryDate = "";

        private String marriageDate = "";

        private String divorceDate = "";

        private String numOfChildren = "";

        private String maritalStatusId = "";

        private String maritalStatus = "";

        private String maritalId = "";

        private String relatedFamMemId;

        private IName relatedFamMemName = new Name();

        public int compareTo(Object obj) throws ClassCastException
        {
            if (obj == null)
                return -1;
            MaritalList evt = (MaritalList) obj;
            int result = 0;
            if (getMaritalId() != null && evt.getMaritalId() != null)
            {
                try
                {
                    if (this.entryDate != null || evt.getEntryDate() != null)
                    {
                        if (evt.getEntryDate() == null && !(evt.getEntryDate().trim().equals("")))
                            return 1; // this makes any null objects go to the bottom
                        			  // change this to -1 if you want the top of the list
                        if (this.entryDate == null && !(this.getEntryDate().trim().equals("")))
                            return -1; // this makes any null objects go to the bottom 
                        			   // change this to 1 if you want the top of the list
                        Date currDate = DateUtil.stringToDate(this.entryDate, UIConstants.DATE_FMT_1);
                        Date incomingDate = DateUtil.stringToDate(evt.getEntryDate(), UIConstants.DATE_FMT_1);
                        if (currDate == null)
                            return -1;
                        if (incomingDate == null)
                            return 1;
                     // backwards in order to get list to show up most recent first
                        result = incomingDate.compareTo(currDate); 
                    }
                    else
                    {
                        result = Integer.valueOf(getMaritalId()).compareTo(Integer.valueOf(evt.getMaritalId()));
                    }
                }
                catch (NumberFormatException e)
                {
                    result = 0;
                }
            }
            return result;
        }

        /**
         * @return
         */
        public String getDivorceDate()
        {
            return divorceDate;
        }

        /**
         * @return
         */
        public String getEntryDate()
        {
            return entryDate;
        }

        /**
         * @return
         */
        public String getMaritalStatus()
        {
            return maritalStatus;
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
        public String getMarriageDate()
        {
            return marriageDate;
        }

        /**
         * @return
         */
        public String getNumOfChildren()
        {
            return numOfChildren;
        }

        /**
         * @param string
         */
        public void setDivorceDate(String string)
        {
            divorceDate = string;
        }

        /**
         * @param string
         */
        public void setEntryDate(String string)
        {
            entryDate = string;
        }

        /**
         * @param string
         */
        public void setMaritalStatus(String string)
        {
            maritalStatus = string;
        }

        /**
         * @param string
         */
        public void setMaritalStatusId(String string)
        {
            maritalStatusId = string;
            if (maritalStatusId == null || maritalStatusId.equals(""))
            {
                setMaritalStatus("");
                return;
            }
            if (JuvenileMemberForm.maritalStatusList != null && JuvenileMemberForm.maritalStatusList.size() > 0)
            {
                maritalStatus = CodeHelper.getCodeDescriptionByCode(JuvenileMemberForm.maritalStatusList,
                        maritalStatusId);
            }
        }

        /**
         * @param string
         */
        public void setMarriageDate(String string)
        {
            marriageDate = string;
        }

        /**
         * @param string
         */
        public void setNumOfChildren(String string)
        {
            numOfChildren = string;
        }

        /**
         * @return
         */
        public String getMaritalId()
        {
            return maritalId;
        }

        /**
         * @param string
         */
        public void setMaritalId(String string)
        {
            maritalId = string;
        }

        /**
         * @return Returns the relatedFamMemId.
         */
        public String getRelatedFamMemId()
        {
            return relatedFamMemId;
        }

        /**
         * @param relatedFamMemId
         *            The relatedFamMemId to set.
         */
        public void setRelatedFamMemId(String relatedFamMemId)
        {
            this.relatedFamMemId = relatedFamMemId;
        }

        /**
         * @return Returns the relatedFamMemName.
         */
        public IName getRelatedFamMemName()
        {
            return relatedFamMemName;
        }

        /**
         * @param relatedFamMemName
         *            The relatedFamMemName to set.
         */
        public void setRelatedFamMemName(IName relatedFamMemName)
        {
            this.relatedFamMemName = relatedFamMemName;
        }
    }
// **********************************************************
// *     END MaritalList class
// **********************************************************
	
}// END_CLASS
