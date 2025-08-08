/*
 * Created on Apr 25, 2005
 */
package messaging.juvenilecase.reply;

import java.util.Date;

import ui.common.Address;

import mojo.km.messaging.ResponseEvent;

/**
 * @author glyons
 */
public class JuvenileCasefileSearchResponseEvent extends ResponseEvent implements Comparable
{
	private String supervisionNum;
	private String juvenileNum;
	private String sequenceNum;
	private String juvenileFirstName;
	private String juvenileMiddleName;
	private String juvenileLastName;
	private String preferredFirstName;
	private String juvenileCurrentAge;
	private String probationOfficerFirstName;
	private String probationOfficerMiddleName;
	private String probationOfficerLastName;
	private String probationOfficerFullName;
	private String supervisionType;
	private String supervisionTypeId; //3 digit code.
	private Date activationDate; //added for ER JIMS200076597
	private String caseStatus;
	private String juvenileFullName;
	private String juvenileNameType;
	private String nameSuffix;
	private String zipCode; //#32659 changes
	private boolean primaryContact; //#32659 changes # hot fix changes.
	private Date fmMemCreateDate; //#32659 changes # hot fix changes.
	private boolean isInHomeStatus; //#32659 changes # hot fix changes.
	//#32659 changes
	private int zipCode_juvenileCount;
	private int officer_juvenileCount;
	private int supType_juvenileCount;


	private String officerFullName;
	private Date supervisionEndDate;
	private Date closedDate;
	

	private String location;
	private String jpoId;
	private String officerLoginId;
	
	//need supervision category for casefile closing
	private String supervisionCategory;
	
	//US 71173
	//need supervision category description
	private String supervisionCatDesc;
	
	//Added for US 32107 - Restricted Access marker
	private String restrictedAccess;
	
	//Added for #US 35786 - Case Status Search 
	private String dispositionDate;
	
	//added for US #34663
	private String supervisionEndDateStr;
	
	//added for US #28666
	private Date casefileCreateDate;
	
	
	//added for US 40492
	private Date assignmentDate;
	
	//added for US 87986
	private String juvRectype;
	
	//added for US 153691
	private Address memberAddress ;
	

	public String getJuvenileNameType() {
		return juvenileNameType;
	}

	public void setJuvenileNameType(String juvenileNameType) {
		this.juvenileNameType = juvenileNameType;
	}
	/**
	 * @return caseStatus
	 */
	public String getCaseStatus()
	{
		return caseStatus;
	}

	/**
	 * @return juvenileCurrentAge
	 */
	public String getJuvenileCurrentAge()
	{
		return juvenileCurrentAge;
	}

	/**
	 * @return juvenileFirstName
	 */
	public String getJuvenileFirstName()
	{
		return juvenileFirstName;
	}

	/**
	 * @return juvenileLastName
	 */
	public String getJuvenileLastName()
	{
		return juvenileLastName;
	}

	/**
	 * @return juvenileMiddleName
	 */
	public String getJuvenileMiddleName()
	{
		return juvenileMiddleName;
	}

	/**
	 * Returns the formatted full name of the juvenile
	 * @return fullName
	 */
	public String getJuvenileFullName()
	{
		return juvenileFullName;
	}

	public void setJuvenileFullName(String aFullName)
	{
		juvenileFullName = aFullName;
	}

	/**
	 * @return juvenileNum
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @return probationOfficerFirstName
	 */
	public String getProbationOfficerFirstName()
	{
		return probationOfficerFirstName;
	}

	/**
	 * @return probationOfficerLastName
	 */
	public String getProbationOfficerLastName()
	{
		return probationOfficerLastName;
	}

	/**
	 * @return probationOfficerMiddleName
	 */
	public String getProbationOfficerMiddleName()
	{
		return probationOfficerMiddleName;
	}


	/**
	 * @return supervisionNum
	 */
	public String getSupervisionNum()
	{
		return supervisionNum;
	}

	/**
	 * @return supervisionType
	 */
	public String getSupervisionType()
	{
		return supervisionType;
	}

	/**
	 * @param caseStatus
	 */
	public void setCaseStatus(String aCaseStatus)
	{
		caseStatus = aCaseStatus;
	}

	/**
	 * @param juvenileCurrentAge
	 */
	public void setJuvenileCurrentAge(String aJuvenileCurrentAge)
	{
		juvenileCurrentAge = aJuvenileCurrentAge;
	}

	/**
	 * @param juvenileFirstName
	 */
	public void setJuvenileFirstName(String aJuvenileFirstName)
	{
		juvenileFirstName = aJuvenileFirstName;
	}

	/**
	 * @param juvenileLastName
	 */
	public void setJuvenileLastName(String aJuvenileLastName)
	{
		juvenileLastName = aJuvenileLastName;
	}

	/**
	 * @param juvenileMiddleName
	 */
	public void setJuvenileMiddleName(String aJuvenileMiddleName)
	{
		juvenileMiddleName = aJuvenileMiddleName;
	}

	/**
	 * @param juvenileNum
	 */
	public void setJuvenileNum(String aJuvenileNum)
	{
		juvenileNum = aJuvenileNum;
	}

	/**
	 * @param probationOfficerFirstName
	 */
	public void setProbationOfficerFirstName(String aProbationOfficerFirstName)
	{
		probationOfficerFirstName = aProbationOfficerFirstName;
	}

	/**
	 * @param probationOfficerLastName
	 */
	public void setProbationOfficerLastName(String aProbationOfficerLastName)
	{
		probationOfficerLastName = aProbationOfficerLastName;
	}

	/**
	 * @param probationOfficerMiddleName
	 */
	public void setProbationOfficerMiddleName(String aProbationOfficerMiddleName)
	{
		probationOfficerMiddleName = aProbationOfficerMiddleName;
	}

	/**
	 * @param supervisionNum
	 */
	public void setSupervisionNum(String aSupervisionNum)
	{
		supervisionNum = aSupervisionNum;
	}

	/**
	 * @param supervisionType
	 */
	public void setSupervisionType(String aSupervisionType)
	{
		supervisionType = aSupervisionType;
	}

	/**
	 * @return the activationDate
	 */
	public Date getActivationDate() {
		return activationDate;
	}

	/**
	 * @param activationDate the activationDate to set
	 */
	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	/**
	 * @return
	 */
	public Date getSupervisionEndDate()
	{
		return supervisionEndDate;
	}

	/**
	 * @param date
	 */
	public void setSupervisionEndDate(Date date)
	{
		supervisionEndDate = date;
	}

	/**
	 * @return
	 */
	public String getOfficerFullName()
	{
		return this.probationOfficerLastName + ", " + this.probationOfficerFirstName + " " + this.probationOfficerMiddleName;
		//return this.officerFullName;
	}
	
	/**
	 * @param String
	 */
		public void setLocation(String location)
		{
			this.location = location;
		}
		
	/**
	 * @return
	 */
		public String getLocation()
		{
			return this.location;
		}

	/**
	 * @return
	 */
	public String getSequenceNum()
	{
		return sequenceNum;
	}

	/**
	 * @param string
	 */
	public void setSequenceNum(String sequenceNum)
	{
		this.sequenceNum = sequenceNum;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj)
	{
		JuvenileCasefileSearchResponseEvent c = (JuvenileCasefileSearchResponseEvent)obj;
		//changed to sequence number sort - Bug #50187
		int i = Integer.parseInt(c.getSequenceNum());
		int j = Integer.parseInt(this.sequenceNum);
		if (i > j)
		{
			return 1;
		}
		else
			if (i < j)
			{
				return -1;
			}
			else
			{
				return 0;
			}
									
	}

	/**
	 * @return Returns the supervisionCategory.
	 */
	public String getSupervisionCategory() {
		return supervisionCategory;
	}
	/**
	 * @param supervisionCategory The supervisionCategory to set.
	 */
	public void setSupervisionCategory(String supervisionCategory) {
		this.supervisionCategory = supervisionCategory;
	}

	public void setOfficerFullName( String officerFullName )
	{
		this.officerFullName = officerFullName ;
	}

	/**
	 * @return the nameSuffix
	 */
	public String getNameSuffix() {
		return nameSuffix;
	}

	/**
	 * @param nameSuffix the nameSuffix to set
	 */
	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	/**
	 * @return the jpoId
	 */
	public String getJpoId() {
		return jpoId;
	}

	/**
	 * @param jpoId the jpoId to set
	 */
	public void setJpoId(String jpoId) {
		this.jpoId = jpoId;
	}

	/**
	 * @return the officerLoginId
	 */
	public String getOfficerLoginId() {
		return officerLoginId;
	}

	/**
	 * @param officerLoginId the officerLoginId to set
	 */
	public void setOfficerLoginId(String officerLoginId) {
		this.officerLoginId = officerLoginId;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	/**
	 * @return the zip_juvenileCount
	 */
	public int getZipCode_juvenileCount() {
		return zipCode_juvenileCount;
	}

	/**
	 * @param zip_juvenileCount the zip_juvenileCount to set
	 */
	public void setZipCode_juvenileCount(int zipCode_juvenileCount) {
		this.zipCode_juvenileCount = zipCode_juvenileCount;
	}

	/**
	 * @return the officer_juvenileCount
	 */
	public int getOfficer_juvenileCount() {
		return officer_juvenileCount;
	}

	/**
	 * @param officer_juvenileCount the officer_juvenileCount to set
	 */
	public void setOfficer_juvenileCount(int officer_juvenileCount) {
		this.officer_juvenileCount = officer_juvenileCount;
	}

	/**
	 * @return the suprvdsn_juvenileCount
	 */
	public int getSupType_juvenileCount() {
		return supType_juvenileCount;
	}

	/**
	 * @param suprvdsn_juvenileCount the suprvdsn_juvenileCount to set
	 */
	public void setSupType_juvenileCount(int supType_juvenileCount) {
		this.supType_juvenileCount = supType_juvenileCount;
	}

	/**
	 * @return the probationOfficerFullName
	 */
	public String getProbationOfficerFullName() {
		return probationOfficerFullName;
	}

	/**
	 * @param probationOfficerFullName the probationOfficerFullName to set
	 */
	public void setProbationOfficerFullName(String probationOfficerFullName) {
		this.probationOfficerFullName = probationOfficerFullName;
	}

	/**
	 * @return the primaryContact
	 */
	public boolean isPrimaryContact() {
		return primaryContact;
	}

	/**
	 * @param primaryContact the primaryContact to set
	 */
	public void setPrimaryContact(boolean primaryContact) {
		this.primaryContact = primaryContact;
	}

	/**
	 * @return the fmMemCreateDate
	 */
	public Date getFmMemCreateDate() {
		return fmMemCreateDate;
	}

	/**
	 * @param fmMemCreateDate the fmMemCreateDate to set
	 */
	public void setFmMemCreateDate(Date fmMemCreateDate) {
		this.fmMemCreateDate = fmMemCreateDate;
	}

	/**
	 * @return the isInHomeStatus
	 */
	public boolean isInHomeStatus() {
		return isInHomeStatus;
	}

	/**
	 * @param isInHomeStatus the isInHomeStatus to set
	 */
	public void setInHomeStatus(boolean isInHomeStatus) {
		this.isInHomeStatus = isInHomeStatus;
	}

	public String getRestrictedAccess() {
		return restrictedAccess;
	}

	public void setRestrictedAccess(String restrictedAccess) {
		this.restrictedAccess = restrictedAccess;
	}

	/**
	 * @return the dispositionDate
	 */
	public String getDispositionDate() {
		return dispositionDate;
	}

	/**
	 * @param dispositionDate the dispositionDate to set
	 */
	public void setDispositionDate(String dispositionDate) {
		this.dispositionDate = dispositionDate;
	}

	public String getSupervisionEndDateStr() {
		return supervisionEndDateStr;
	}

	public void setSupervisionEndDateStr(String supervisionEndDateStr) {
		this.supervisionEndDateStr = supervisionEndDateStr;
	}

	public Date getCasefileCreateDate() {
		return casefileCreateDate;
	}

	public void setCasefileCreateDate(Date casefileCreateDate) {
		this.casefileCreateDate = casefileCreateDate;
	}

	/**
	 * @return the supervisionTypeId
	 */
	public String getSupervisionTypeId() {
		return supervisionTypeId;
	}

	/**
	 * @param supervisionTypeId the supervisionTypeId to set
	 */
	public void setSupervisionTypeId(String supervisionTypeId) {
		this.supervisionTypeId = supervisionTypeId;
	}

	/**
	 * @return the assignmentDate
	 */
	public Date getAssignmentDate() {
		return assignmentDate;
	}

	/**
	 * @param assignmentDate the assignmentDate to set
	 */
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	/**
	 * @return the supervisionCatDesc
	 */
	public String getSupervisionCatDesc()
	{
	    return supervisionCatDesc;
	}

	/**
	 * @param supervisionCatDesc the supervisionCatDesc to set
	 */
	public void setSupervisionCatDesc(String supervisionCatDesc)
	{
	    this.supervisionCatDesc = supervisionCatDesc;
	}
	public String getJuvRectype()
	{
	    return juvRectype;
	}

	public void setJuvRectype(String juvRectype)
	{
	    this.juvRectype = juvRectype;
	}

	public Address getMemberAddress()
	{
	    return memberAddress;
	}

	public void setMemberAddress(Address memberAddress)
	{
	    this.memberAddress = memberAddress;
	}

	public String getPreferredFirstName()
	{
	    return preferredFirstName;
	}

	public void setPreferredFirstName(String preferredFirstName)
	{
	    this.preferredFirstName = preferredFirstName;
	}
	// task 170950
	public Date getClosedDate()
	{
	    return closedDate;
	}

	public void setClosedDate(Date closedDate)
	{
	    this.closedDate = closedDate;
	}
	
}