package ui.juvenilecase.districtCourtHearings;

import java.util.Date;
import java.util.List;

import ui.common.Address;
import ui.common.Name;
import ui.juvenilecase.form.JuvenileMemberForm;

/**
 * 
 * SubpoenaReportBean
 *
 */
public class SubpoenaReportBean
{

    private static final long serialVersionUID = 1L;

    private String courtId;
    private String courtIdWithSuffix;
    private String courtDate;
    private Date courtDateWithTime;
    private String courtTime;
   
    private Name memberName;
    private Address juvenileAddress;
    private Address memberAddress;
    private JuvenileMemberForm.MemberContact memberContact;
    private JuvenileMemberForm.MemberContact juvenileContact;

    private Name careGiver;
    private Address careGiverAddress;
    private String juvenileFirstName;
    private String juvenileLastName;
    private String juvenileMiddleName;
    private String juvenileNumber;
    private String juvenileName;
    private String plaintiffName;
    private String cert;
    private String preparationDate;
    private List<String> subpoenasToBePrinted;
    private String filingDate;
    private String amendmentDate;
    
    private String certCode;
    private String constablePct1;
    private String districtClerkName;
    private String reopenPetStatus;
    private String petitionNum;
    private String trackingNum;
    private String courtLocation;
    private String petitionAmendment;
    private String partyType;
    private String facilityName;
    private String facilityAddress;
    private String facCity;
    private String facState;
    private String facPhone;
    private String facZipCode;
    
    /**
     * @return the courtId
     */
    public String getCourtId()
    {
        return courtId;
    }
    /**
     * @param courtId the courtId to set
     */
    public void setCourtId(String courtId)
    {
        this.courtId = courtId;
    }
    /**
     * @return the courtIdWithSuffix
     */
    public String getCourtIdWithSuffix()
    {
        return courtIdWithSuffix;
    }
    /**
     * @param courtIdWithSuffix the courtIdWithSuffix to set
     */
    public void setCourtIdWithSuffix(String courtIdWithSuffix)
    {
        this.courtIdWithSuffix = courtIdWithSuffix;
    }
    /**
     * @return the courtDate
     */
    public String getCourtDate()
    {
        return courtDate;
    }
    /**
     * @param courtDate the courtDate to set
     */
    public void setCourtDate(String courtDate)
    {
        this.courtDate = courtDate;
    }
    /**
     * @return the courtDateWithTime
     */
    public Date getCourtDateWithTime()
    {
        return courtDateWithTime;
    }
    /**
     * @param courtDateWithTime the courtDateWithTime to set
     */
    public void setCourtDateWithTime(Date courtDateWithTime)
    {
        this.courtDateWithTime = courtDateWithTime;
    }
    /**
     * @return the courtTime
     */
    public String getCourtTime()
    {
        return courtTime;
    }
    /**
     * @param courtTime the courtTime to set
     */
    public void setCourtTime(String courtTime)
    {
        this.courtTime = courtTime;
    }
  
   
    /**
     * @return the careGiver
     */
    public Name getCareGiver()
    {
        return careGiver;
    }
    /**
     * @param careGiver the careGiver to set
     */
    public void setCareGiver(Name careGiver)
    {
        this.careGiver = careGiver;
    }
    /**
     * @return the careGiverAddress
     */
    public Address getCareGiverAddress()
    {
        return careGiverAddress;
    }
    /**
     * @param careGiverAddress the careGiverAddress to set
     */
    public void setCareGiverAddress(Address careGiverAddress)
    {
        this.careGiverAddress = careGiverAddress;
    }
    /**
     * @return the juvenileFirstName
     */
    public String getJuvenileFirstName()
    {
        return juvenileFirstName;
    }
    /**
     * @param juvenileFirstName the juvenileFirstName to set
     */
    public void setJuvenileFirstName(String juvenileFirstName)
    {
        this.juvenileFirstName = juvenileFirstName;
    }
    /**
     * @return the juvenileLastName
     */
    public String getJuvenileLastName()
    {
        return juvenileLastName;
    }
    /**
     * @param juvenileLastName the juvenileLastName to set
     */
    public void setJuvenileLastName(String juvenileLastName)
    {
        this.juvenileLastName = juvenileLastName;
    }
    /**
     * @return the juvenileMiddleName
     */
    public String getJuvenileMiddleName()
    {
        return juvenileMiddleName;
    }
    /**
     * @param juvenileMiddleName the juvenileMiddleName to set
     */
    public void setJuvenileMiddleName(String juvenileMiddleName)
    {
        this.juvenileMiddleName = juvenileMiddleName;
    }
    /**
     * @return the juvenileNumber
     */
    public String getJuvenileNumber()
    {
        return juvenileNumber;
    }
    /**
     * @param juvenileNumber the juvenileNumber to set
     */
    public void setJuvenileNumber(String juvenileNumber)
    {
        this.juvenileNumber = juvenileNumber;
    }
    /**
     * @return the juvenileName
     */
    public String getJuvenileName()
    {
        return juvenileName;
    }
    /**
     * @param juvenileName the juvenileName to set
     */
    public void setJuvenileName(String juvenileName)
    {
        this.juvenileName = juvenileName;
    }
    /**
     * @return the plaintiffName
     */
    public String getPlaintiffName()
    {
        return plaintiffName;
    }
    /**
     * @param plaintiffName the plaintiffName to set
     */
    public void setPlaintiffName(String plaintiffName)
    {
        this.plaintiffName = plaintiffName;
    }
    /**
     * @return the cert
     */
    public String getCert()
    {
        return cert;
    }
    /**
     * @param cert the cert to set
     */
    public void setCert(String cert)
    {
        this.cert = cert;
    }
    /**
     * @return the preparationDate
     */
    public String getPreparationDate()
    {
        return preparationDate;
    }
    /**
     * @param preparationDate the preparationDate to set
     */
    public void setPreparationDate(String preparationDate)
    {
        this.preparationDate = preparationDate;
    }
    /**
     * @return the subpoenasToBePrinted
     */
    public List<String> getSubpoenasToBePrinted()
    {
        return subpoenasToBePrinted;
    }
    /**
     * @param subpoenasToBePrinted the subpoenasToBePrinted to set
     */
    public void setSubpoenasToBePrinted(List<String> subpoenasToBePrinted)
    {
        this.subpoenasToBePrinted = subpoenasToBePrinted;
    }
    /**
     * @return the filingDate
     */
    public String getFilingDate()
    {
        return filingDate;
    }
    /**
     * @param filingDate the filingDate to set
     */
    public void setFilingDate(String filingDate)
    {
        this.filingDate = filingDate;
    }
   
    public String getCertCode()
    {
	return certCode;
    }
    public void setCertCode(String certCode)
    {
	this.certCode = certCode;
    }
    public String getConstablePct1()
    {
	return constablePct1;
    }
    public void setConstablePct1(String constablePct1)
    {
	this.constablePct1 = constablePct1;
    }
    public String getDistrictClerkName()
    {
	return districtClerkName;
    }
    public void setDistrictClerkName(String districtClerkName)
    {
	this.districtClerkName = districtClerkName;
    }
    public String getPetitionNum()
    {
	return petitionNum;
    }
    public void setPetitionNum(String petitionNum)
    {
	this.petitionNum = petitionNum;
    }
    public String getReopenPetStatus()
    {
	return reopenPetStatus;
    }
    public void setReopenPetStatus(String reopenPetStatus)
    {
	this.reopenPetStatus = reopenPetStatus;
    }
    public String getTrackingNum()
    {
	return trackingNum;
    }
    public void setTrackingNum(String trackingNum)
    {
	this.trackingNum = trackingNum;
    }
    public String getCourtLocation()
    {
	return courtLocation;
    }
    public void setCourtLocation(String courtLocation)
    {
	this.courtLocation = courtLocation;
    }
    public String getPetitionAmendment()
    {
	return petitionAmendment;
    }
    public void setPetitionAmendment(String petitionAmendment)
    {
	this.petitionAmendment = petitionAmendment;
    }
    public String getPartyType()
    {
	return partyType;
    }
    public void setPartyType(String partyType)
    {
	this.partyType = partyType;
    }
  
    public Address getJuvenileAddress()
    {
	return juvenileAddress;
    }
    public void setJuvenileAddress(Address juvenileAddress)
    {
	this.juvenileAddress = juvenileAddress;
    }
    public JuvenileMemberForm.MemberContact getJuvenileContact()
    {
	return juvenileContact;
    }
    public void setJuvenileContact(JuvenileMemberForm.MemberContact juvenileContact)
    {
	this.juvenileContact = juvenileContact;
    }
    public JuvenileMemberForm.MemberContact getMemberContact()
    {
	return memberContact;
    }
    public void setMemberContact(JuvenileMemberForm.MemberContact memberContact)
    {
	this.memberContact = memberContact;
    }
    public Address getMemberAddress()
    {
	return memberAddress;
    }
    public void setMemberAddress(Address memberAddress)
    {
	this.memberAddress = memberAddress;
    }
    public Name getMemberName()
    {
	return memberName;
    }
    public void setMemberName(Name memberName)
    {
	this.memberName = memberName;
    }
    public String getFacilityName()
    {
	return facilityName;
    }
    public void setFacilityName(String facilityName)
    {
	this.facilityName = facilityName;
    }

    public String getFacilityAddress()
    {
	return facilityAddress;
    }
    public void setFacilityAddress(String facilityAddress)
    {
	this.facilityAddress = facilityAddress;
    }
    public String getFacCity()
    {
	return facCity;
    }
    public void setFacCity(String facCity)
    {
	this.facCity = facCity;
    }
    public String getFacState()
    {
	return facState;
    }
    public void setFacState(String facState)
    {
	this.facState = facState;
    }
    public String getFacPhone()
    {
	return facPhone;
    }
    public void setFacPhone(String facPhone)
    {
	this.facPhone = facPhone;
    }
    public String getFacZipCode()
    {
	return facZipCode;
    }
    public void setFacZipCode(String facZipCode)
    {
	this.facZipCode = facZipCode;
    }
    public String getAmendmentDate()
    {
	return amendmentDate;
    }
    public void setAmendmentDate(String amendmentDate)
    {
	this.amendmentDate = amendmentDate;
    }
    
}
