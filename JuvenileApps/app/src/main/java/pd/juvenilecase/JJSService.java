package pd.juvenilecase;// no longer in use. Migrated to SM. Refer US #87188. REFER JJSSVINTAKEHISTORY
    /*MIGRATED SQL-PART OF REFERRAL CONVERSION*/

/*package pd.juvenilecase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import messaging.juvenilecase.GetNewJuvenileCasefileAssignmentsEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import naming.PDContactConstants;
import pd.codetable.Code;
import pd.codetable.person.JuvenileLevelUnitCode;
import pd.codetable.person.SocialElementCode;
import pd.contact.user.UserProfile;
import pd.km.util.Formatter;

*//**
 * @return
 * @param event
 *//*
public class JJSService extends PersistentObject implements Comparable
{
    *//**
     * @return
     * @param event
     *//*
    static public Iterator findAll(IEvent event)
    {
	IHome home = new Home();
	return home.findAll(event, JJSService.class);
    }

    private Date addDate;
    private String amount;
    *//**
     * Properties for assignmentLevel
     * 
     * @referencedType pd.codetable.person.JuvenileLevelUnitCode
     * @detailerDoNotGenerate true
     *//*
    private JuvenileLevelUnitCode assignmentLevel = null;
    private String assignmentLevelId;
    *//**
     * Properties for caseLoadManager
     * 
     * @referencedType pd.contact.user.UserProfile
     * @detailerDoNotGenerate true
     *//*
    private UserProfile caseLoadManager = null;
    private String caseLoadManagerId;
    *//**
     * Properties for category
     * 
     * @referencedType pd.codetable.Code
     * @contextKey JUVENILE_SERVICE_CATEGORY
     * @detailerDoNotGenerate true
     *//*
    private Code category = null;
    private String categoryId;
    *//**
     * Properties for dept
     * 
     * @referencedType pd.codetable.Code
     * @contextKey JUVENILE_DEPT
     * @detailerDoNotGenerate true
     *//*
    private Code dept = null;
    private String deptId;
    *//**
     * Properties for fundingSource
     * 
     * @referencedType pd.codetable.Code
     * @contextKey JUVENILE_FUNDING_SOURCE
     * @detailerDoNotGenerate true
     *//*
    private Code fundingSource = null;
    private String fundingSourceId;
    *//**
     * Indicates whether the case file has been created for this service record.
     * This is a new field that must be added to the m204 record. it will be
     * used to determine whether to extract the record to JIMS2.
     *//*
    private String jims2ExtractInd;
    private String juvenileNum;
    *//**
     * Properties for probationOfficer
     * 
     * @referencedType pd.contact.user.UserProfile
     * @detailerDoNotGenerate true
     *//*
    private UserProfile probationOfficer = null;
    private String probationOfficerId;
    private String referralNum;
    *//**
     * Properties for schoolProgram
     * 
     * @referencedType pd.codetable.person.SocialElementCode
     * @detailerDoNotGenerate true
     *//*
    private SocialElementCode schoolProgram = null;
    private String schoolProgramId;
    private String seqNum;
    private Date serviceDate;
    *//**
     * Properties for serviceType
     * 
     * @referencedType pd.codetable.Code
     * @contextKey JUVENILE_SERVICE_TYPE
     * @detailerDoNotGenerate true
     *//*
    private Code serviceType = null;
    private String serviceTypeId;
    private String sessionLength;
    private String sessionTime;
    *//**
     * Properties for unit
     * 
     * @referencedType pd.codetable.person.JuvenileLevelUnitCode
     * @detailerDoNotGenerate true
     *//*
    private JuvenileLevelUnitCode unit = null;
    private String unitId;
    *//**
     * Properties for vendor
     * 
     * @referencedType pd.codetable.Code
     * @contextKey JUVENILE_VENDOR
     * @detailerDoNotGenerate true
     *//*
    private Code vendor = null;
    private String vendorId;
    private String assignByUserId;

    *//**
     * @roseuid 4279054901C5
     *//*
    public JJSService()
    {
    }

    *//**
     * @roseuid 427904D002EE
     *//*
    public void bind()
    {
	markModified();
    }

    *//**
     * @return
     *//*
    public Date getAddDate()
    {
	fetch();
	return addDate;
    }

    *//**
     * @return
     *//*
    public String getAmount()
    {
	fetch();
	return amount;
    }

    *//**
     * Gets referenced type pd.codetable.person.JuvenileLevelUnitCode
     *//*
    public pd.codetable.person.JuvenileLevelUnitCode getAssignmentLevel()
    {
	fetch();
	initAssignmentLevel();
	return assignmentLevel;
    }

    *//**
     * Get the reference value to class ::
     * pd.codetable.person.JuvenileLevelUnitCode
     *//*
    public String getAssignmentLevelId()
    {
	fetch();
	return assignmentLevelId;
    }

    *//**
     * Gets referenced type pd.contact.user.UserProfile
     *//*
    public pd.contact.user.UserProfile getCaseLoadManager()
    {
	fetch();
	initCaseLoadManager();
	return caseLoadManager;
    }

    *//**
     * Get the reference value to class :: pd.contact.user.UserProfile
     *//*
    public String getCaseLoadManagerId()
    {
	fetch();
	return caseLoadManagerId;
    }

    *//**
     * Gets referenced type pd.codetable.Code
     *//*
    public pd.codetable.Code getCategory()
    {
	fetch();
	initCategory();
	return category;
    }

    *//**
     * Get the reference value to class :: pd.codetable.Code
     *//*
    public String getCategoryId()
    {
	fetch();
	return categoryId;
    }

    *//**
     * Gets referenced type pd.codetable.Code
     *//*
    public pd.codetable.Code getDept()
    {
	fetch();
	initDept();
	return dept;
    }

    *//**
     * Get the reference value to class :: pd.codetable.Code
     *//*
    public String getDeptId()
    {
	fetch();
	return deptId;
    }

    *//**
     * Gets referenced type pd.codetable.Code
     *//*
    public pd.codetable.Code getFundingSource()
    {
	fetch();
	initFundingSource();
	return fundingSource;
    }

    *//**
     * Get the reference value to class :: pd.codetable.Code
     *//*
    public String getFundingSourceId()
    {
	fetch();
	return fundingSourceId;
    }

    *//**
     * @return
     *//*
    public String getJims2ExtractInd()
    {
	fetch();
	return jims2ExtractInd;
    }

    *//**
     * @return
     *//*
    public String getJuvenileNum()
    {
	fetch();
	return juvenileNum;
    }

    *//**
     * Retrieves new case file assignments.
     * 
     * @return
     * @roseuid 427904D002EF
     *//*
    public Collection getNewCaseFileAssignments()
    {
	fetch();
	GetNewJuvenileCasefileAssignmentsEvent getAssignmentsEvent = new GetNewJuvenileCasefileAssignmentsEvent();
	Iterator iterator = JJSService.findAll(getAssignmentsEvent);
	Collection assignments = new ArrayList();
	if (iterator != null)
	{
	    while (iterator.hasNext())
	    {
		JJSService service = (JJSService) iterator.next();
		assignments.add(service);
	    }
	}
	return assignments;
    }

    *//**
     * Gets referenced type pd.contact.user.UserProfile
     *//*
    public pd.contact.user.UserProfile getProbationOfficer()
    {
	fetch();
	initProbationOfficer();
	return probationOfficer;
    }

    *//**
     * Get the reference value to class :: pd.contact.user.UserProfile
     *//*
    public String getProbationOfficerId()
    {
	fetch();
	return probationOfficerId;
    }

    *//**
     * @return
     *//*
    public String getReferralNum()
    {
	fetch();
	return referralNum;
    }

    *//**
     * Gets referenced type pd.codetable.person.SocialElementCode
     *//*
    public pd.codetable.person.SocialElementCode getSchoolProgram()
    {
	fetch();
	initSchoolProgram();
	return schoolProgram;
    }

    *//**
     * Get the reference value to class :: pd.codetable.person.SocialElementCode
     *//*
    public String getSchoolProgramId()
    {
	fetch();
	return schoolProgramId;
    }

    *//**
     * @return
     *//*
    public String getSeqNum()
    {
	fetch();
	return seqNum;
    }

    *//**
     * @return
     *//*
    public Date getServiceDate()
    {
	fetch();
	return serviceDate;
    }

    *//**
     * Gets referenced type pd.codetable.Code
     *//*
    public pd.codetable.Code getServiceType()
    {
	fetch();
	initServiceType();
	return serviceType;
    }

    *//**
     * Get the reference value to class :: pd.codetable.Code
     *//*
    public String getServiceTypeId()
    {
	fetch();
	return serviceTypeId;
    }

    *//**
     * @return
     *//*
    public String getSessionLength()
    {
	fetch();
	return sessionLength;
    }

    *//**
     * @return
     *//*
    public String getSessionTime()
    {
	fetch();
	return sessionTime;
    }

    *//**
     * Gets referenced type pd.codetable.person.JuvenileLevelUnitCode
     *//*
    public pd.codetable.person.JuvenileLevelUnitCode getUnit()
    {
	fetch();
	initUnit();
	return unit;
    }

    *//**
     * Get the reference value to class ::
     * pd.codetable.person.JuvenileLevelUnitCode
     *//*
    public String getUnitId()
    {
	fetch();
	return unitId;
    }

    *//**
     * Gets referenced type pd.codetable.Code
     *//*
    public pd.codetable.Code getVendor()
    {
	fetch();
	initVendor();
	return vendor;
    }

    *//**
     * Get the reference value to class :: pd.codetable.Code
     *//*
    public String getVendorId()
    {
	fetch();
	return vendorId;
    }

    *//**
     * Initialize class relationship to class
     * pd.codetable.person.JuvenileLevelUnitCode
     *//*
    private void initAssignmentLevel()
    {
	if (assignmentLevel == null)
	{
	    if (assignmentLevelId != null)
	    {
		try
		{
		    StringBuffer derivedOid = new StringBuffer("");
		    derivedOid.append(Formatter.pad(assignmentLevelId, 4, '0', true));
		    if (unitId != null)
		    {
			derivedOid.append(Formatter.pad(unitId, 4, '0', true));
		    }
		    assignmentLevel = (pd.codetable.person.JuvenileLevelUnitCode) new mojo.km.persistence.Reference(derivedOid.toString(), pd.codetable.person.JuvenileLevelUnitCode.class).getObject();
		}
		catch (Throwable t)
		{
		    assignmentLevelId = null;
		}
	    }
	}
    }

    *//**
     * Initialize class relationship to class pd.contact.user.UserProfile
     *//*
    private void initCaseLoadManager()
    {
	if (caseLoadManager == null)
	{
	    try
	    {
		caseLoadManager = (pd.contact.user.UserProfile) new mojo.km.persistence.Reference(caseLoadManagerId, pd.contact.user.UserProfile.class).getObject();
	    }
	    catch (Throwable t)
	    {
		caseLoadManager = null;
	    }
	}
    }

    *//**
     * Initialize class relationship to class pd.codetable.Code
     *//*
    private void initCategory()
    {
	if (category == null)
	{
	    try
	    {
		category = (pd.codetable.Code) new mojo.km.persistence.Reference(categoryId, pd.codetable.Code.class, "JUVENILE_SERVICE_CATEGORY").getObject();
	    }
	    catch (Throwable t)
	    {
		category = null;
	    }
	}
    }

    *//**
     * Initialize class relationship to class pd.codetable.Code
     *//*
    private void initDept()
    {
	if (dept == null)
	{
	    try
	    {
		dept = (pd.codetable.Code) new mojo.km.persistence.Reference(deptId, pd.codetable.Code.class, PDContactConstants.DEPARTMENT).getObject();
	    }
	    catch (Throwable t)
	    {
		dept = null;
	    }
	}
    }

    *//**
     * Initialize class relationship to class pd.codetable.Code
     *//*
    private void initFundingSource()
    {
	if (fundingSource == null)
	{
	    try
	    {
		fundingSource = (pd.codetable.Code) new mojo.km.persistence.Reference(fundingSourceId, pd.codetable.Code.class, "FUNDING.SOURCE").getObject();
	    }
	    catch (Throwable t)
	    {
		fundingSource = null;
	    }
	}
    }

    *//**
     * Initialize class relationship to class pd.contact.user.UserProfile
     *//*
    private void initProbationOfficer()
    {
	if (probationOfficer == null)
	{
	    try
	    {
		probationOfficer = (pd.contact.user.UserProfile) new mojo.km.persistence.Reference(probationOfficerId, pd.contact.user.UserProfile.class).getObject();
	    }
	    catch (Throwable t)
	    {
		probationOfficer = null;
	    }
	}
    }

    *//**
     * Initialize class relationship to class
     * pd.codetable.person.SocialElementCode
     *//*
    private void initSchoolProgram()
    {
	if (schoolProgram == null)
	{
	    try
	    {
		if (schoolProgramId != null)
		{
		    StringBuffer derivedOid = new StringBuffer();
		    derivedOid.append(Formatter.pad(PDCodeTableConstants.SOCIAL_ELEMENT_SCHOOL_PROGRAM, 4, '0', true));
		    derivedOid.append(Formatter.pad(schoolProgramId, 4, '0', true));
		    schoolProgram = (pd.codetable.person.SocialElementCode) new mojo.km.persistence.Reference(derivedOid.toString(), pd.codetable.person.SocialElementCode.class).getObject();
		}
	    }
	    catch (Throwable t)
	    {
		schoolProgramId = null;
	    }
	}
    }

    *//**
     * Initialize class relationship to class pd.codetable.Code
     *//*
    private void initServiceType()
    {
	if (serviceType == null)
	{
	    try
	    {
		serviceType = (pd.codetable.Code) new mojo.km.persistence.Reference(serviceTypeId, pd.codetable.Code.class, "JUVENILE_SERVICE_TYPE").getObject();
	    }
	    catch (Throwable t)
	    {
		serviceType = null;
	    }
	}
    }

    *//**
     * Initialize class relationship to class
     * pd.codetable.person.JuvenileLevelUnitCode
     *//*
    private void initUnit()
    {
	if (unit == null)
	{
	    if (unitId != null)
	    {
		StringBuffer derivedOid = new StringBuffer();
		if (assignmentLevelId != null)
		{
		    derivedOid.append(Formatter.pad(assignmentLevelId, 4, '0', true));
		}
		else
		{
		    return;
		}
		derivedOid.append(Formatter.pad(unitId, 4, '0', true));
		try
		{
		    unit = (pd.codetable.person.JuvenileLevelUnitCode) new mojo.km.persistence.Reference(derivedOid.toString(), pd.codetable.person.JuvenileLevelUnitCode.class).getObject();
		}
		catch (Throwable t)
		{
		    unit = null;
		}
	    }
	}
    }

    *//**
     * Initialize class relationship to class pd.codetable.Code
     *//*
    private void initVendor()
    {
	if (vendor == null)
	{
	    try
	    {
		vendor = (pd.codetable.Code) new mojo.km.persistence.Reference(vendorId, pd.codetable.Code.class, "VENDOR").getObject();
	    }
	    catch (Throwable t)
	    {
		vendor = null;
	    }
	}
    }

    *//**
     * @param theAddDate
     *//*
    public void setAddDate(Date theAddDate)
    {
	if (this.addDate == null || !this.addDate.equals(theAddDate))
	{
	    markModified();
	}
	addDate = theAddDate;
    }

    *//**
     * @param theAmount
     *//*
    public void setAmount(String theAmount)
    {
	if (this.amount == null || !this.amount.equals(theAmount))
	{
	    markModified();
	}
	amount = theAmount;
    }

    *//**
     * set the type reference for class member assignmentLevel
     *//*
    public void setAssignmentLevel(pd.codetable.person.JuvenileLevelUnitCode theAssignmentLevel)
    {
	if (this.assignmentLevel == null || !this.assignmentLevel.equals(theAssignmentLevel))
	{
	    markModified();
	}
	if (theAssignmentLevel.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theAssignmentLevel);
	}
	setAssignmentLevelId("" + theAssignmentLevel.getOID());
	this.assignmentLevel = (pd.codetable.person.JuvenileLevelUnitCode) new mojo.km.persistence.Reference(theAssignmentLevel).getObject();
    }

    *//**
     * Set the reference value to class ::
     * pd.codetable.person.JuvenileLevelUnitCode
     *//*
    public void setAssignmentLevelId(String theAssignmentLevelId)
    {
	if (this.assignmentLevelId == null || !this.assignmentLevelId.equals(theAssignmentLevelId))
	{
	    markModified();
	}
	assignmentLevel = null;
	this.assignmentLevelId = theAssignmentLevelId;
    }

    *//**
     * set the type reference for class member caseLoadManager
     *//*
    public void setCaseLoadManager(pd.contact.user.UserProfile theCaseLoadManager)
    {
	if (this.caseLoadManager == null || !this.caseLoadManager.equals(theCaseLoadManager))
	{
	    markModified();
	}
	if (theCaseLoadManager.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(caseLoadManager);
	}
	setCaseLoadManagerId("" + theCaseLoadManager.getOID());
	this.caseLoadManager = (pd.contact.user.UserProfile) new mojo.km.persistence.Reference(theCaseLoadManager).getObject();
    }

    *//**
     * Set the reference value to class :: pd.contact.user.UserProfile
     *//*
    public void setCaseLoadManagerId(String theCaseLoadManagerId)
    {
	if (this.caseLoadManagerId == null || !this.caseLoadManagerId.equals(theCaseLoadManagerId))
	{
	    markModified();
	}
	caseLoadManager = null;
	this.caseLoadManagerId = theCaseLoadManagerId;
    }

    *//**
     * set the type reference for class member category
     *//*
    public void setCategory(pd.codetable.Code theCategory)
    {
	if (this.category == null || !this.category.equals(theCategory))
	{
	    markModified();
	}
	if (theCategory.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theCategory);
	}
	setCategoryId("" + theCategory.getOID());
	this.category = (pd.codetable.Code) new mojo.km.persistence.Reference(theCategory).getObject();
    }

    *//**
     * Set the reference value to class :: pd.codetable.Code
     *//*
    public void setCategoryId(String theCategoryId)
    {
	if (this.categoryId == null || !this.categoryId.equals(theCategoryId))
	{
	    markModified();
	}
	category = null;
	this.categoryId = theCategoryId;
    }

    *//**
     * set the type reference for class member dept
     *//*
    public void setDept(pd.codetable.Code theDept)
    {
	if (this.dept == null || !this.dept.equals(theDept))
	{
	    markModified();
	}
	if (theDept.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theDept);
	}
	setDeptId("" + theDept.getOID());
	this.dept = (pd.codetable.Code) new mojo.km.persistence.Reference(theDept).getObject();
    }

    *//**
     * Set the reference value to class :: pd.codetable.Code
     *//*
    public void setDeptId(String theDeptId)
    {
	if (this.deptId == null || !this.deptId.equals(theDeptId))
	{
	    markModified();
	}
	dept = null;
	this.deptId = theDeptId;
    }

    *//**
     * set the type reference for class member fundingSource
     *//*
    public void setFundingSource(pd.codetable.Code theFundingSource)
    {
	if (this.fundingSource == null || !this.fundingSource.equals(theFundingSource))
	{
	    markModified();
	}
	if (theFundingSource.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theFundingSource);
	}
	setFundingSourceId("" + theFundingSource.getOID());
	this.fundingSource = (pd.codetable.Code) new mojo.km.persistence.Reference(theFundingSource).getObject();
    }

    *//**
     * Set the reference value to class :: pd.codetable.Code
     *//*
    public void setFundingSourceId(String theFundingSourceId)
    {
	if (this.fundingSourceId == null || !this.fundingSourceId.equals(theFundingSourceId))
	{
	    markModified();
	}
	fundingSource = null;
	this.fundingSourceId = theFundingSourceId;
    }

    *//**
     * @param theJims2ExtractInd
     *//*
    public void setJims2ExtractInd(String theJims2ExtractInd)
    {
	if (this.jims2ExtractInd == null || !this.jims2ExtractInd.equals(theJims2ExtractInd))
	{
	    markModified();
	}
	jims2ExtractInd = theJims2ExtractInd;
    }

    *//**
     * @param theJuvenileNum
     *//*
    public void setJuvenileNum(String theJuvenileNum)
    {
	if (this.juvenileNum == null || !this.juvenileNum.equals(theJuvenileNum))
	{
	    markModified();
	}
	juvenileNum = theJuvenileNum;
    }

    *//**
     * set the type reference for class member probationOfficer
     *//*
    public void setProbationOfficer(pd.contact.user.UserProfile theProbationOfficer)
    {
	if (this.probationOfficer == null || !this.probationOfficer.equals(theProbationOfficer))
	{
	    markModified();
	}
	if (theProbationOfficer.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theProbationOfficer);
	}
	setProbationOfficerId("" + theProbationOfficer.getOID());
	this.probationOfficer = (pd.contact.user.UserProfile) new mojo.km.persistence.Reference(theProbationOfficer).getObject();
    }

    *//**
     * Set the reference value to class :: pd.contact.user.UserProfile
     *//*
    public void setProbationOfficerId(String theProbationOfficerId)
    {
	if (this.probationOfficerId == null || !this.probationOfficerId.equals(theProbationOfficerId))
	{
	    markModified();
	}
	probationOfficer = null;
	this.probationOfficerId = theProbationOfficerId;
    }

    *//**
     * @param theReferralNum
     *//*
    public void setReferralNum(String theReferralNum)
    {
	if (this.referralNum == null || !this.referralNum.equals(theReferralNum))
	{
	    markModified();
	}
	referralNum = theReferralNum;
    }

    *//**
     * set the type reference for class member schoolProgram
     *//*
    public void setSchoolProgram(pd.codetable.person.SocialElementCode theSchoolProgram)
    {
	if (this.schoolProgram == null || !this.schoolProgram.equals(theSchoolProgram))
	{
	    markModified();
	}
	if (theSchoolProgram.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theSchoolProgram);
	}
	setSchoolProgramId("" + schoolProgram.getOID());
	this.schoolProgram = (pd.codetable.person.SocialElementCode) new mojo.km.persistence.Reference(theSchoolProgram).getObject();
    }

    *//**
     * Set the reference value to class :: pd.codetable.person.SocialElementCode
     *//*
    public void setSchoolProgramId(String theSchoolProgramId)
    {
	if (this.schoolProgramId == null || !this.schoolProgramId.equals(theSchoolProgramId))
	{
	    markModified();
	}
	schoolProgram = null;
	this.schoolProgramId = theSchoolProgramId;
    }

    *//**
     * @param theSeqNum
     *//*
    public void setSeqNum(String theSeqNum)
    {
	if (this.seqNum == null || !this.seqNum.equals(theSeqNum))
	{
	    markModified();
	}
	seqNum = theSeqNum;
    }

    *//**
     * @param theServiceDate
     *//*
    public void setServiceDate(Date theServiceDate)
    {
	if (this.serviceDate == null || !this.serviceDate.equals(theServiceDate))
	{
	    markModified();
	}
	serviceDate = theServiceDate;
    }

    *//**
     * set the type reference for class member serviceType
     *//*
    public void setServiceType(pd.codetable.Code theServiceType)
    {
	if (this.serviceType == null || !this.serviceType.equals(theServiceType))
	{
	    markModified();
	}
	if (theServiceType.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theServiceType);
	}
	setServiceTypeId("" + theServiceType.getOID());
	this.serviceType = (pd.codetable.Code) new mojo.km.persistence.Reference(theServiceType).getObject();
    }

    *//**
     * Set the reference value to class :: pd.codetable.Code
     *//*
    public void setServiceTypeId(String theServiceTypeId)
    {
	if (this.serviceTypeId == null || !this.serviceTypeId.equals(theServiceTypeId))
	{
	    markModified();
	}
	serviceType = null;
	this.serviceTypeId = theServiceTypeId;
    }

    *//**
     * @param theSessionLength
     *//*
    public void setSessionLength(String theSessionLength)
    {
	if (this.sessionLength == null || !this.sessionLength.equals(theSessionLength))
	{
	    markModified();
	}
	sessionLength = theSessionLength;
    }

    *//**
     * @param theSessionTime
     *//*
    public void setSessionTime(String theSessionTime)
    {
	if (this.sessionTime == null || !this.sessionTime.equals(theSessionTime))
	{
	    markModified();
	}
	sessionTime = theSessionTime;
    }

    *//**
     * set the type reference for class member unit
     *//*
    public void setUnit(pd.codetable.person.JuvenileLevelUnitCode theUnit)
    {
	if (this.unit == null || !this.unit.equals(theUnit))
	{
	    markModified();
	}
	if (theUnit.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theUnit);
	}
	setUnitId("" + theUnit.getOID());
	this.unit = (pd.codetable.person.JuvenileLevelUnitCode) new mojo.km.persistence.Reference(theUnit).getObject();
    }

    *//**
     * Set the reference value to class ::
     * pd.codetable.person.JuvenileLevelUnitCode
     *//*
    public void setUnitId(String theUnitId)
    {
	if (this.unitId == null || !this.unitId.equals(theUnitId))
	{
	    markModified();
	}
	unit = null;
	this.unitId = theUnitId;
    }

    *//**
     * set the type reference for class member vendor
     *//*
    public void setVendor(pd.codetable.Code theVendor)
    {
	if (this.vendor == null || !this.vendor.equals(theVendor))
	{
	    markModified();
	}
	if (theVendor.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theVendor);
	}
	setVendorId("" + theVendor.getOID());
	this.vendor = (pd.codetable.Code) new mojo.km.persistence.Reference(theVendor).getObject();
    }

    *//**
     * Set the reference value to class :: pd.codetable.Code
     *//*
    public void setVendorId(String theVendorId)
    {
	if (this.vendorId == null || !this.vendorId.equals(theVendorId))
	{
	    markModified();
	}
	vendor = null;
	this.vendorId = theVendorId;
    }

    public int compareTo(Object obj) throws ClassCastException
    {
	JJSService service = (JJSService) obj;
	return this.getOID().compareTo(service.getOID());
    }

    public String getAssignByUserId()
    {
	return assignByUserId;
    }

    public void setAssignByUserId(String assignByUserId)
    {
	this.assignByUserId = assignByUserId;
    }
}
*/