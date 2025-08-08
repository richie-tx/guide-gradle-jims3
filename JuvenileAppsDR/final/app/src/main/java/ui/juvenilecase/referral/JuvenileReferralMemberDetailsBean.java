package ui.juvenilecase.referral;

import java.util.ArrayList;
import java.util.List;

import ui.common.Address;
import ui.common.PhoneNumber;
import ui.common.SocialSecurity;

/**
 * @author sthyagarajan
 */
public class JuvenileReferralMemberDetailsBean implements Comparable
{
    private Address memberAddress = new Address();
    private PhoneNumber contactPhoneNumber = new PhoneNumber("");
    private List<String> relationships = new ArrayList<String>();
    private List<Address> newAddressList = new ArrayList<Address>();
    private String relationshipId;
    private String relationshipDesc;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nameSuffix;
    private String formattedName;
    private String formattedAddress;
    private SocialSecurity SSN = new SocialSecurity("");
    private String formattedSSN;
    private String formattedPhone;
    private String completeSSN;
    private String incarceratedOrDeceased;
    private String incarceratedOrDeceasedDesc;
    private boolean isIncarcerated;
    private boolean isDeceased;
    private String phoneType;
    private String phoneTypeDesc;
    private String phoneInd;
    private String phoneIndDesc;
    private String primaryContact;
    private String memberNum; //memberNum created by NM for Update Juvenile - Process Referrals
    private List<String> memberList = new ArrayList<String>();
    private String newMemflag;
    private String guardianEditedFlag;

    public void clear()
    {
	firstName = "";
	middleName = "";
	lastName = "";
	nameSuffix = "";
	incarceratedOrDeceased = "";
	memberAddress = new Address();
	SSN = new SocialSecurity("");
	contactPhoneNumber = new PhoneNumber("");
	phoneType = "";
	phoneTypeDesc = "";
	phoneInd="";
	phoneIndDesc="";
	setPrimaryContact("False");
	isIncarcerated = false;
	isDeceased = false;
	relationshipId= "";
	relationshipDesc="";
	newMemflag="";
	memberNum="";
	memberList = new ArrayList<String>();
	guardianEditedFlag="";
    }

    /**
     * @return the memberAddress
     */
    public Address getMemberAddress()
    {
	return memberAddress;
    }

    /**
     * @param memberAddress
     *            the memberAddress to set
     */
    public void setMemberAddress(Address memberAddress)
    {
	this.memberAddress = memberAddress;
    }

    /**
     * @return the relationships
     */
    public List<String> getRelationships()
    {
	return relationships;
    }

    /**
     * @param relationships
     *            the relationships to set
     */
    public void setRelationships(List<String> relationships)
    {
	this.relationships = relationships;
    }

    /**
     * @return the newAddressList
     */
    public List<Address> getNewAddressList()
    {
	return newAddressList;
    }

    /**
     * @param newAddressList
     *            the newAddressList to set
     */
    public void setNewAddressList(List<Address> newAddressList)
    {
	this.newAddressList = newAddressList;
    }

    /**
     * @return the relationshipId
     */
    public String getRelationshipId()
    {
	return relationshipId;
    }

    /**
     * @param relationshipId
     *            the relationshipId to set
     */
    public void setRelationshipId(String relationshipId)
    {
	this.relationshipId = relationshipId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName()
    {
	return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName)
    {
	this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName()
    {
	return middleName;
    }

    /**
     * @param middleName
     *            the middleName to set
     */
    public void setMiddleName(String middleName)
    {
	this.middleName = middleName;
    }

    /**
     * @return the lastName
     */
    public String getLastName()
    {
	return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName)
    {
	this.lastName = lastName;
    }

    /**
     * @return the nameSuffix
     */
    public String getNameSuffix()
    {
	return nameSuffix;
    }

    /**
     * @param nameSuffix
     *            the nameSuffix to set
     */
    public void setNameSuffix(String nameSuffix)
    {
	this.nameSuffix = nameSuffix;
    }

    /**
     * @return the formattedName
     */
    public String getFormattedName()
    {
	return formattedName;
    }

    /**
     * @param formattedName
     *            the formattedName to set
     */
    public void setFormattedName(String formattedName)
    {
	this.formattedName = formattedName;
    }

    /**
     * @return the formattedAddress
     */
    public String getFormattedAddress()
    {
	return formattedAddress;
    }

    /**
     * @param formattedAddress
     *            the formattedAddress to set
     */
    public void setFormattedAddress(String formattedAddress)
    {
	this.formattedAddress = formattedAddress;
    }

    /**
     * @return the sSN
     */
    public SocialSecurity getSSN()
    {
	return SSN;
    }

    /**
     * @param sSN
     *            the sSN to set
     */
    public void setSSN(SocialSecurity sSN)
    {
	SSN = sSN;
    }

    /**
     * @return the formattedSSN
     */
    public String getFormattedSSN()
    {
	return formattedSSN;
    }

    /**
     * @param formattedSSN
     *            the formattedSSN to set
     */
    public void setFormattedSSN(String formattedSSN)
    {
	this.formattedSSN = formattedSSN;
    }

    /**
     * @return the completeSSN
     */
    public String getCompleteSSN()
    {
	return completeSSN;
    }

    /**
     * @param completeSSN
     *            the completeSSN to set
     */
    public void setCompleteSSN(String completeSSN)
    {
	this.completeSSN = completeSSN;
    }

    /**
     * @return the memberList
     */
    public List<String> getMemberList()
    {
	return memberList;
    }

    /**
     * @param memberList
     *            the memberList to set
     */
    public void setMemberList(List<String> memberList)
    {
	this.memberList = memberList;
    }

    /**
     * @return the contactPhoneNumber
     */
    public PhoneNumber getContactPhoneNumber()
    {
	return contactPhoneNumber;
    }

    /**
     * @param contactPhoneNumber
     *            the contactPhoneNumber to set
     */
    public void setContactPhoneNumber(PhoneNumber contactPhoneNumber)
    {
	this.contactPhoneNumber = contactPhoneNumber;
    }

    @Override
    public int compareTo(Object o)
    {
	// TODO Auto-generated method stub
	return 0;
    }

 
    /**
     * @return the phoneType
     */
    public String getPhoneType()
    {
	return phoneType;
    }

    /**
     * @param phoneType
     *            the phoneType to set
     */
    public void setPhoneType(String phoneType)
    {
	this.phoneType = phoneType;
    }

    public String getIncarceratedOrDeceased()
    {
	return incarceratedOrDeceased;
    }

    public void setIncarceratedOrDeceased(String incarceratedOrDeceased)
    {
	this.incarceratedOrDeceased = incarceratedOrDeceased;
    }

    public String getRelationshipDesc()
    {
	return relationshipDesc;
    }

    public void setRelationshipDesc(String relationshipDesc)
    {
	this.relationshipDesc = relationshipDesc;
    }

    public String getFormattedPhone()
    {
	return formattedPhone;
    }

    public void setFormattedPhone(String formattedPhone)
    {
	this.formattedPhone = formattedPhone;
    }

    public String getIncarceratedOrDeceasedDesc()
    {
	return incarceratedOrDeceasedDesc;
    }

    public void setIncarceratedOrDeceasedDesc(String incarceratedOrDeceasedDesc)
    {
	this.incarceratedOrDeceasedDesc = incarceratedOrDeceasedDesc;
    }

    public String getPhoneTypeDesc()
    {
	return phoneTypeDesc;
    }

    public void setPhoneTypeDesc(String phoneTypeDesc)
    {
	this.phoneTypeDesc = phoneTypeDesc;
    }

    /**
     * @return the isIncarcerated
     */
    public boolean isIncarcerated()
    {
        return isIncarcerated;
    }

    /**
     * @param isIncarcerated the isIncarcerated to set
     */
    public void setIncarcerated(boolean isIncarcerated)
    {
        this.isIncarcerated = isIncarcerated;
    }

    /**
     * @return the isDeceased
     */
    public boolean isDeceased()
    {
        return isDeceased;
    }

    /**
     * @param isDeceased the isDeceased to set
     */
    public void setDeceased(boolean isDeceased)
    {
        this.isDeceased = isDeceased;
    }

    public String getPhoneInd()
    {
	return phoneInd;
    }

    public void setPhoneInd(String phoneInd)
    {
	this.phoneInd = phoneInd;
    }

    public String getPhoneIndDesc()
    {
	return phoneIndDesc;
    }

    public void setPhoneIndDesc(String phoneIndDesc)
    {
	this.phoneIndDesc = phoneIndDesc;
    }

    /**
     * @return the primaryContact
     */
    public String getPrimaryContact()
    {
	return primaryContact;
    }

    /**
     * @param primaryContact the primaryContact to set
     */
    public void setPrimaryContact(String primaryContact)
    {
	this.primaryContact = primaryContact;
    }

    public String getMemberNum()
    {
	return memberNum;
    }

    public void setMemberNum(String memberNum)
    {
	this.memberNum = memberNum;
    }

    public String getNewMemflag()
    {
	return newMemflag;
    }

    public void setNewMemflag(String newMemflag)
    {
	this.newMemflag = newMemflag;
    }

    public String getGuardianEditedFlag()
    {
	return guardianEditedFlag;
    }

    public void setGuardianEditedFlag(String guardianEditedFlag)
    {
	this.guardianEditedFlag = guardianEditedFlag;
    }

    
}
