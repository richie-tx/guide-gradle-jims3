package ui.juvenilecase.referral;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import pd.juvenilecase.JuvenileCasefileReferral;
import ui.common.Address;

public class ReferralReceiptPrintBean
{

    private String action;

    /** Juvenile Identification **/

    private String juvenileNum;
    
    private String formattedName;

    private String raceSexAge;

    private String dateOfBirth;
    
    private String verifiDOB;
    
    private String status;
    
    private String refNum;
    
    private String caseNumber;
    
    private String formattedSSN;
    
    private String alias;
    
    private String inAssignUnitOfficer;
    
    private String comments;

    /** Juvenile Address Information **/
    
    private Address juvAddress;
    
    private String juvFormattedAddress;
    
    private String county;
    
    private String school;
    
    private String keyMap;
    
    /** Family and Social Information **/
    
    private String fathersName;
    
    private String fathersAddress;
    
    private String fathersPhone;
    
    private String mothersName;
    
    private String mothersAddress;
    
    private String mothersPhone;
	
    private String otherName;
    
    private String otherAddress;
    
    private String otherPhone;
    
    private String otherRelationship;
    
    private Collection<JuvenileProfileReferralListResponseEvent> juvProfRefList;
    
    private Collection<JuvenileCasefileReferral> casefileReferrals;
    
    private String contactPhone;
    
    private String lastJuvenileNum;

    private boolean hasCasefiles;
    
    /** Current Referral Data **/
    
    private int refCnt;
    
    private String referralSource;
    
    private Date referralDate;
    
    private Date offenseDate;
    
    private String lcUser;
    
    private String investigationNumber;
    
    private String numberOfCoActors;
    
    private String detained;
    
    private String facility;
    
    private String offenseAlphaCode;
    
    private String inAssignJpoId;
    
    private String inAssignLevel;
    
    //US 71171
    private String guardianEditFlag;
    
    private String addGuradianFlag = "N";
    
    private String juvFromM204Flag;

    /**
     * Clear
     */
    public void clear()
    {
	juvenileNum = "";
	formattedName = "";
	raceSexAge="";
	dateOfBirth = "";
	verifiDOB = "";
	status = "";
	refNum = "";
	caseNumber = "";
	formattedSSN = "";
	alias = "";
	inAssignUnitOfficer = "";
	county = "";
	school = "";
	keyMap = "";
	fathersName = "";
	fathersAddress = "";
	fathersPhone = "";
	mothersName = "";
	mothersAddress = "";
	mothersPhone = "";
	otherName = "";
	otherAddress = "";
	otherPhone = "";
	otherRelationship = "";
	juvProfRefList = new ArrayList<JuvenileProfileReferralListResponseEvent>();
	casefileReferrals = new ArrayList<JuvenileCasefileReferral>();
	contactPhone = "";
	refCnt = 0;
	referralSource = "";
	inAssignJpoId = "";
	inAssignLevel = "";
	referralDate = new Date();
	investigationNumber = "";
	numberOfCoActors = "";
	detained = "";
	facility = "";
	offenseDate = new Date();
	lcUser = "";
	offenseAlphaCode = "";
	comments="";
	guardianEditFlag="N";
	addGuradianFlag = "N";
    }
   
    /**
     * @return the juvenileNum
     */
    public String getJuvenileNum()
    {
	return juvenileNum;
    }

    /**
     * @param juvenileNum
     *            the juvenileNum to set
     */
    public void setJuvenileNum(String juvenileNum)
    {
	this.juvenileNum = juvenileNum;
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
     * @return the raceSexAge
     */
    public String getRaceSexAge()
    {
	return raceSexAge;
    }

    /**
     * @param raceSexAge
     *            the raceSexAge to set
     */
    public void setRaceSexAge(String raceSexAge)
    {
	this.raceSexAge = raceSexAge;
    }
    
    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth()
    {
	return dateOfBirth;
    }

    /**
     * @param dateOfBirth
     *            the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth)
    {
	this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the verifiDOB
     */
    public String getVerifiDOB()
    {
	return verifiDOB;
    }

    /**
     * @param verifiDOB the verifiDOB to set
     */
    public void setVerifiDOB(String verifiDOB)
    {
	this.verifiDOB = verifiDOB;
    }

    /**
     * @return the status
     */
    public String getStatus()
    {
	return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status)
    {
	this.status = status;
    }

    /**
     * @return the refNum
     */
    public String getRefNum()
    {
	return refNum;
    }

    /**
     * @param refNum the refNum to set
     */
    public void setRefNum(String refNum)
    {
	this.refNum = refNum;
    }

    /**
     * @return the caseNumber
     */
    public String getCaseNumber()
    {
	return caseNumber;
    }

    /**
     * @param caseNumber the caseNumber to set
     */
    public void setCaseNumber(String caseNumber)
    {
	this.caseNumber = caseNumber;
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
     * @return the alias
     */
    public String getAlias()
    {
	return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias)
    {
	this.alias = alias;
    }

    /**
     * @return the inAssignUnitOfficer
     */
    public String getInAssignUnitOfficer()
    {
	return inAssignUnitOfficer;
    }

    /**
     * @param inAssignUnitOfficer the inAssignUnitOfficer to set
     */
    public void setInAssignUnitOfficer(String inAssignUnitOfficer)
    {
	this.inAssignUnitOfficer = inAssignUnitOfficer;
    }

    /**
     * @return the action
     */
    public String getAction()
    {
	return action;
    }

    /**
     * @param action
     *            the action to set
     */
    public void setAction(String action)
    {
	this.action = action;
    }

    /**
     * @return the comments
     */
    public String getComments()
    {
	return comments;
    }

    /**
     * @param comments
     *            the comments to set
     */
    public void setComments(String comments)
    {
	this.comments = comments;
    }

    /**
     * @return the juvAddress
     */
    public Address getJuvAddress()
    {
	return juvAddress;
    }

    /**
     * @param juvAddress
     *            the juvAddress to set
     */
    public void setJuvAddress(Address juvAddress)
    {
	this.juvAddress = juvAddress;
    }

    public String getJuvFormattedAddress()
    {
	return juvFormattedAddress;
    }

    public void setJuvFormattedAddress(String juvFormattedAddress)
    {
	this.juvFormattedAddress = juvFormattedAddress;
    }

    /**
     * @return the county
     */
    public String getCounty()
    {
	return county;
    }

    /**
     * @param county the county to set
     */
    public void setCounty(String county)
    {
	this.county = county;
    }

    /**
     * @return the school
     */
    public String getSchool()
    {
	return school;
    }


    /**
     * @param school the school to set
     */
    public void setSchool(String school)
    {
	String resolvedString = school;
	resolvedString.replaceAll("&",  "&#38;");
	this.school = resolvedString;
    }


    /**
     * @return the keyMap
     */
    public String getKeyMap()
    {
	return keyMap;
    }

    /**
     * @param keyMap the keyMap to set
     */
    public void setKeyMap(String keyMap)
    {
	this.keyMap = keyMap;
    }

    /**
     * @return the fathersName
     */
    public String getFathersName()
    {
	return fathersName;
    }

    /**
     * @param fathersName the fathersName to set
     */
    public void setFathersName(String fathersName)
    {
	this.fathersName = fathersName;
    }

    /**
     * @return the fathersAddress
     */
    public String getFathersAddress()
    {
	return fathersAddress;
    }

    /**
     * @param fathersAddress the fathersAddress to set
     */
    public void setFathersAddress(String fathersAddress)
    {
	this.fathersAddress = fathersAddress;
    }

    /**
     * @return the fathersPhone
     */
    public String getFathersPhone()
    {
	return fathersPhone;
    }

    /**
     * @param fathersPhone the fathersPhone to set
     */
    public void setFathersPhone(String fathersPhone)
    {
	this.fathersPhone = fathersPhone;
    }

    /**
     * @return the mothersName
     */
    public String getMothersName()
    {
	return mothersName;
    }

    /**
     * @param mothersName the mothersName to set
     */
    public void setMothersName(String mothersName)
    {
	this.mothersName = mothersName;
    }

    /**
     * @return the mothersAddress
     */
    public String getMothersAddress()
    {
	return mothersAddress;
    }

    /**
     * @param mothersAddress the mothersAddress to set
     */
    public void setMothersAddress(String mothersAddress)
    {
	this.mothersAddress = mothersAddress;
    }

    /**
     * @return the mothersPhone
     */
    public String getMothersPhone()
    {
	return mothersPhone;
    }

    /**
     * @param mothersPhone the mothersPhone to set
     */
    public void setMothersPhone(String mothersPhone)
    {
	this.mothersPhone = mothersPhone;
    }

    /**
     * @return the otherName
     */
    public String getOtherName()
    {
	return otherName;
    }

    /**
     * @param otherName the otherName to set
     */
    public void setOtherName(String otherName)
    {
	this.otherName = otherName;
    }

    /**
     * @return the otherAddress
     */
    public String getOtherAddress()
    {
	return otherAddress;
    }

    /**
     * @param otherAddress the otherAddress to set
     */
    public void setOtherAddress(String otherAddress)
    {
	this.otherAddress = otherAddress;
    }

    /**
     * @return the otherPhone
     */
    public String getOtherPhone()
    {
	return otherPhone;
    }

    /**
     * @param otherPhone the otherPhone to set
     */
    public void setOtherPhone(String otherPhone)
    {
	this.otherPhone = otherPhone;
    }

    /**
     * @return the otherRelationship
     */
    public String getOtherRelationship()
    {
	return otherRelationship;
    }

    /**
     * @param otherRelationship the otherRelationship to set
     */
    public void setOtherRelationship(String otherRelationship)
    {
	this.otherRelationship = otherRelationship;
    }

    /**
     * @return the juvProfRefList
     */
    public Collection<JuvenileProfileReferralListResponseEvent> getJuvProfRefList()
    {
	return juvProfRefList;
    }

    /**
     * @param juvProfRefList the juvProfRefList to set
     */
    public void setJuvProfRefList(Collection<JuvenileProfileReferralListResponseEvent> juvProfRefList)
    {
	this.juvProfRefList = juvProfRefList;
    }

    /**
     * @return the casefileReferrals
     */
    public Collection<JuvenileCasefileReferral> getCasefileReferrals()
    {
	return casefileReferrals;
    }

    /**
     * @param casefileReferrals the casefileReferrals to set
     */
    public void setCasefileReferrals(Collection<JuvenileCasefileReferral> casefileReferrals)
    {
	this.casefileReferrals = casefileReferrals;
    }

    /**
     * @return the contactPhone
     */
    public String getContactPhone()
    {
	return contactPhone;
    }

    /**
     * @param contactPhone the contactPhone to set
     */
    public void setContactPhone(String contactPhone)
    {
	this.contactPhone = contactPhone;
    }

    public String getLastJuvenileNum()
    {
	return lastJuvenileNum;
    }

    public void setLastJuvenileNum(String lastJuvenileNum)
    {
	this.lastJuvenileNum = lastJuvenileNum;
    }

    /**
     * @return the referralSource
     */
    public String getReferralSource()
    {
	return referralSource;
    }

    /**
     * @param referralSource the referralSource to set
     */
    public void setReferralSource(String referralSource)
    {
	this.referralSource = referralSource;
    }

    /**
     * @return the inAssignJpoId
     */
    public String getInAssignJpoId()
    {
	return inAssignJpoId;
    }

    /**
     * @param inAssignJpoId the inAssignJpoId to set
     */
    public void setInAssignJpoId(String inAssignJpoId)
    {
	this.inAssignJpoId = inAssignJpoId;
    }

    /**
     * @return the inAssignLevel
     */
    public String getInAssignLevel()
    {
	return inAssignLevel;
    }

    /**
     * @param inAssignLevel the inAssignLevel to set
     */
    public void setInAssignLevel(String inAssignLevel)
    {
	this.inAssignLevel = inAssignLevel;
    }

    /**
     * @return the referralDate
     */
    public Date getReferralDate()
    {
	return referralDate;
    }

    /**
     * @param referralDate the referralDate to set
     */
    public void setReferralDate(Date referralDate)
    {
	this.referralDate = referralDate;
    }

    /**
     * @return the investigationNumber
     */
    public String getInvestigationNumber()
    {
	return investigationNumber;
    }

    /**
     * @param investigationNumber the investigationNumber to set
     */
    public void setInvestigationNumber(String investigationNumber)
    {
	this.investigationNumber = investigationNumber;
    }
 
    /**
     * @return the numberOfCoActors
     */
    public String getNumberOfCoActors()
    {
	return numberOfCoActors;
    }

    /**
     * @param numberOfCoActors the numberOfCoActors to set
     */
    public void setNumberOfCoActors(String numberOfCoActors)
    {
	this.numberOfCoActors = numberOfCoActors;
    }

    /**
     * @return the detained
     */
    public String getDetained()
    {
	return detained;
    }

    /**
     * @param detained the detained to set
     */
    public void setDetained(String detained)
    {
	this.detained = detained;
    }

    /**
     * @return the facility
     */
    public String getFacility()
    {
	return facility;
    }

    /**
     * @param facility the facility to set
     */
    public void setFacility(String facility)
    {
	this.facility = facility;
    }

    /**
     * @return the offenseDate
     */
    public Date getOffenseDate()
    {
	return offenseDate;
    }

    /**
     * @param offenseDate the offenseDate to set
     */
    public void setOffenseDate(Date offenseDate)
    {
	this.offenseDate = offenseDate;
    }

    /**
     * @return the lcUser
     */
    public String getLcUser()
    {
	return lcUser;
    }

    /**
     * @param lcUser the lcUser to set
     */
    public void setLcUser(String lcUser)
    {
	this.lcUser = lcUser;
    }

    /**
     * @return the offenseAlphaCode
     */
    public String getOffenseAlphaCode()
    {
	return offenseAlphaCode;
    }

    /**
     * @param offenseAlphaCode the offenseAlphaCode to set
     */
    public void setOffenseAlphaCode(String offenseAlphaCode)
    {
	this.offenseAlphaCode = offenseAlphaCode;
    }

    public boolean isHasCasefiles()
    {
	return hasCasefiles;
    }

    public void setHasCasefiles(boolean hasCasefiles)
    {
	this.hasCasefiles = hasCasefiles;
    }

    /**
     * @return the refCnt
     */
    public int getRefCnt()
    {
	return refCnt;
    }

    /**
     * @param refCnt the refCnt to set
     */
    public void setRefCnt(int refCnt)
    {
	this.refCnt = refCnt;
    }

    public String getGuardianEditFlag()
    {
	return guardianEditFlag;
    }

    public void setGuardianEditFlag(String guardianEditFlag)
    {
	this.guardianEditFlag = guardianEditFlag;
    }

    public String getAddGuradianFlag()
    {
	return addGuradianFlag;
    }

    public void setAddGuradianFlag(String addGuradianFlag)
    {
	this.addGuradianFlag = addGuradianFlag;
    }

    /**
     * @return the juvFromM204Flag
     */
    public String getJuvFromM204Flag()
    {
	return juvFromM204Flag;
    }

    /**
     * @param juvFromM204Flag the juvFromM204Flag to set
     */
    public void setJuvFromM204Flag(String juvFromM204Flag)
    {
	this.juvFromM204Flag = juvFromM204Flag;
    }    
}
