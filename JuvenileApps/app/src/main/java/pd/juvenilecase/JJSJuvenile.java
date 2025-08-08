package pd.juvenilecase;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import mojo.km.context.ContextManager;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.security.ISecurityManager;
import naming.PDCodeTableConstants;
import pd.address.Address;
import pd.codetable.Code;
import pd.codetable.person.JuvenileLevelUnitCode;
import pd.codetable.person.JuvenileSchoolDistrictCode;
import pd.codetable.person.SocialElementCode;
import pd.contact.user.UserProfile;
import pd.juvenile.JuvenileMasterStatus;
import pd.km.util.Formatter;

/**
 * Properties for actionOperator
 * 
 * @referencedType pd.contact.user.UserProfile
 * @detailerDoNotGenerate true
 */
public class JJSJuvenile extends PersistentObject
{
    /**
     * @param juvenileNum
     * @roseuid 427136D900BB
     */
    static public JJSJuvenile find(String juvenileNum)
    {
	IHome home = new Home();
	//return (JJSJuvenile) home.find("juvenileNum", juvenileNum, JJSJuvenile.class);
	JJSJuvenile juvenile = (JJSJuvenile) home.find(juvenileNum, JJSJuvenile.class);
	if (juvenile != null && (juvenile.getRectype() == null || juvenile.getRectype().equalsIgnoreCase("JUVENILE")))
	    return juvenile;
	else
	{
	    ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	    boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
	    if (grantedFeature)
		return juvenile;
	    else
		return null;
	}
    }
    
    static public Iterator findByDetentionStatus( String detentionStatusId ){
	IHome home = new Home();
	return home.findAll("detentionStatusId", detentionStatusId , JJSJuvenile.class);
    }
    
    
    /**
     * @return
     * @param event
     */
    static public Iterator findAll(IEvent event)
    {
	IHome home = new Home();
	//return home.findAll(event, JJSJuvenile.class);
	ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
	if (grantedFeature)
	    return home.findAll(event, JJSJuvenile.class);
	else
	    return filterSealedPurged(home.findAll(event, JJSJuvenile.class));
    }    

    protected static Iterator filterSealedPurged(Iterator iter)
    {
	ArrayList<JJSJuvenile> detDocs = new ArrayList<JJSJuvenile>();
	while (iter.hasNext())
	{
	    JJSJuvenile juv = (JJSJuvenile) iter.next();
	    if (juv != null && juv.getRectype()!= null && juv.getRectype().equalsIgnoreCase("JUVENILE"))
		detDocs.add(juv);
	}
	return detDocs.iterator();
    }
    
    /**
     * 
     * @param attributeName
     * @param attributeValue
     * @return
     */
    public static Iterator findAll(String attributeName, String attributeValue)
	{
	   IHome home = new Home();
	   ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	   boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
	   if (grantedFeature)
	       return home.findAll(attributeName, attributeValue,JJSJuvenile.class);
		else
	       return filterSealedPurged(home.findAll(attributeName, attributeValue,JJSJuvenile.class));
	}	
    
    private Date actionDate;
    /**
     * Properties for actionOperator
     * 
     * @referencedType pd.contact.user.UserProfile
     * @detailerDoNotGenerate true
     */
    private UserProfile actionOperator = null;
    private String actionOperatorId;
    private String age;
    /**
     * Properties for agency
     * 
     * @referencedType pd.codetable.Code
     * @contextKey JUVENILE_AGENCY
     * @detailerDoNotGenerate true
     */
    private Code agency = null;
    private String agencyId;
    private String attentionFlag;
    private Date birthDate;
    private Date realDOB;
    private String caseNotePart1;
    private String caseNotePart2;
    private Date checkedOutDate;
    private String checkedOutTo;
    /**
     * Properties for county
     * 
     * @referencedType pd.codetable.Code
     * @contextKey JUVENILE_COUNTY
     * @detailerDoNotGenerate true
     */
    private Code county = null;
    private String countyId;
    /**
     * Properties for custodyStatus
     * 
     * @referencedType pd.codetable.Code
     * @contextKey JUVENILE_CUSTODY_STATUS
     * @detailerDoNotGenerate true
     */
    private Code custodyStatus = null;
    private String custodyStatusId;
    private String detentionAddInd;
    /**
     * Properties for detentionFacility
     * 
     * @referencedType pd.codetable.Code
     * @contextKey JUVENILE_DETENTION_FACILITY
     * @detailerDoNotGenerate true
     */
    private Code detentionFacility = null;
    private String detentionFacilityId;
    /**
     * Properties for detentionStatus
     * 
     * @referencedType pd.codetable.Code
     * @contextKey JUVENILE_DETENTION_STATUS
     * @detailerDoNotGenerate true
     */
    private Code detentionStatus = null;
    private String detentionStatusId;
    private String dobVerification;
    private String firstName;
    private String preferredFirstName;
    /**
     * Properties for gang
     * 
     * @referencedType pd.codetable.Code
     * @contextKey JUVENILE_GANG
     * @detailerDoNotGenerate true
     */
    private Code gang = null;
    private String gangId;
    /**
     * Properties for juvenileAddress
     * 
     * @useParent true
     * @referencedType pd.address.Address
     */
    private Address juvenileAddress = null;
    private String juvenileAddressId;
    private String juvenileNum;
    private String juvenileSsn;
    private String juvenileTitle;
    /**
     * Properties for lastActionBy
     * 
     * @referencedType pd.contact.user.UserProfile
     * @detailerDoNotGenerate true
     */
    private UserProfile lastActionBy = null;
    private String lastActionById;
    private String lastName;
    private String lastReferralNum;
    private String middleName;
    private String offenseSeverity = null;
    private String offenseTotal;
    /**
     * Properties for propointJPOpId
     * 
     * @referencedType pd.contact.user.UserProfile
     * @detailerDoNotGenerate true
     */
    private UserProfile propointJPOpId = null;
    private String propointJPOpIdId;
    /**
     * Properties for propointLevel
     * 
     * @referencedType pd.codetable.person.JuvenileLevelUnitCode
     * @detailerDoNotGenerate true
     */
    private JuvenileLevelUnitCode propointLevel = null;
    private String propointLevelId;
    /**
     * Properties for propointUnit
     * 
     * @referencedType pd.codetable.person.JuvenileLevelUnitCode
     * @detailerDoNotGenerate true
     */
    private JuvenileLevelUnitCode propointUnit = null;
    private String propointUnitId;
    private String provisionSeverity = null;
    private String provisionTotal;
    private String purgeBoxNum;
    private Date purgeDate;
    private String purgeFlag;
    private String purgeSerNum;
    /**
     * Properties for race
     * 
     * @referencedType pd.codetable.Code
     * @contextKey RACE
     * @detailerDoNotGenerate true
     */
    private Code race = null;
    private String raceId;
    private String rectype;
    private String referralCount;
    private String referralHistory;
    private String referralNum;
    private String referralOfficer;
    private String ncicRaceCode;
    private String ncicEthnicity;
    private String sealedDate;
    private String sealComments;
    private String liveWithTjjd;
    /**
     * Properties for school
     * 
     * @referencedType pd.codetable.person.JuvenileSchoolDistrictCode
     * @detailerDoNotGenerate true
     */
    private JuvenileSchoolDistrictCode school = null;
    /**
     * Properties for schoolDistrict
     * 
     * @referencedType pd.codetable.person.JuvenileSchoolDistrictCode
     * @detailerDoNotGenerate true
     */
    private JuvenileSchoolDistrictCode schoolDistrict = null;
    private String schoolDistrictId;
    private String schoolId;
    /**
     * Properties for sex
     * 
     * @referencedType pd.codetable.Code
     * @contextKey SEX
     * @detailerDoNotGenerate true
     */
    private Code sex = null;
    private String sexId;
    private String ssn1;
    private String ssn2;
    /**
     * Properties for ssnRelation1
     * 
     * @referencedType pd.codetable.person.SocialElementCode
     * @detailerDoNotGenerate true
     */
    private SocialElementCode ssnRelation1 = null;
    private String ssnRelation1Id;
    /**
     * Properties for ssnRelation2
     * 
     * @referencedType pd.codetable.person.SocialElementCode
     * @detailerDoNotGenerate true
     */
    private SocialElementCode ssnRelation2 = null;
    private String ssnRelation2Id;
    /**
     * Properties for status
     * 
     * @referencedType pd.codetable.Code
     * @contextKey JUVENILE_STATUS
     * @detailerDoNotGenerate true
     */
    private Code status = null;
    private String statusId;

    private Code oldStatus = null;
    private String oldStatusId;

    //added for Facility
    private String keyMap;
    private String zip;
    private Date lcTime;
    private String juvAddress;
    
    //US 71582
    private String originalRaceId;   
    private int addressId;
    private Date lcDate;
    private String lcuser;
    
    //US 189772 / 190449 
    private String causeOfDeath;
    private String deathVerification;
    private Date dateOfDeath;
    private int ageAtDeath;
    private int juvExcluded;
    

    /**
     * @roseuid 42777D7801FA
     */
    public JJSJuvenile()
    {
    }

    /**
     * @return
     */
    public Date getActionDate()
    {
	fetch();
	return actionDate;
    }

    /**
     * @return the keyMap
     */
    public String getKeyMap()
    {
	return keyMap;
    }

    /**
     * @param keyMap
     *            the keyMap to set
     */
    public void setKeyMap(String keyMap)
    {
	this.keyMap = keyMap;
    }

    /**
     * @return the zip
     */
    public String getZip()
    {
	fetch();
	return zip;
    }

    /**
     * @param zip
     *            the zip to set
     */
    public void setZip(String zip)
    {
	this.zip = zip;
    }

    /**
     * @return the lcTime
     */
    public Date getLcTime()
    {
	return lcTime;
    }

    /**
     * @param lcTime
     *            the lcTime to set
     */
    public void setLcTime(Date lcTime)
    {
	this.lcTime = lcTime;
    }

    /**
     * Gets referenced type pd.contact.user.UserProfile
     */
    public UserProfile getActionOperator()
    {
	fetch();
	initActionOperator();
	return actionOperator;
    }

    /**
     * Get the reference value to class :: pd.contact.user.UserProfile
     */
    public String getActionOperatorId()
    {
	fetch();
	return actionOperatorId;
    }

    /**
     * @return
     */
    public String getAge()
    {
	fetch();
	return age;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getAgency()
    {
	fetch();
	initAgency();
	return agency;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getAgencyId()
    {
	fetch();
	return agencyId;
    }

    /**
     * @return
     */
    public String getAttentionFlag()
    {
	fetch();
	return attentionFlag;
    }

    /**
     * @return
     */
    public Date getBirthDate()
    {
	fetch();
	return birthDate;
    }

    /**
     * @return
     */
    public String getCaseNotePart1()
    {
	fetch();
	return caseNotePart1;
    }

    /**
     * @return
     */
    public String getCaseNotePart2()
    {
	fetch();
	return caseNotePart2;
    }

    /**
     * @return
     */
    public Date getCheckedOutDate()
    {
	fetch();
	return checkedOutDate;
    }

    /**
     * @return
     */
    public String getCheckedOutTo()
    {
	fetch();
	return checkedOutTo;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getCounty()
    {
	fetch();
	initCounty();
	return county;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getCountyId()
    {
	fetch();
	return countyId;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getCustodyStatus()
    {
	fetch();
	initCustodyStatus();
	return custodyStatus;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getCustodyStatusId()
    {
	fetch();
	return custodyStatusId;
    }

    /**
     * @return
     */
    public String getDetentionAddInd()
    {
	fetch();
	return detentionAddInd;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getDetentionFacility()
    {
	fetch();
	initDetentionFacility();
	return detentionFacility;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getDetentionFacilityId()
    {
	fetch();
	return detentionFacilityId;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getDetentionStatus()
    {
	fetch();
	initDetentionStatus();
	return detentionStatus;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getDetentionStatusId()
    {
	fetch();
	return detentionStatusId;
    }

    /**
     * @return
     */
    public String getDobVerification()
    {
	fetch();
	return dobVerification;
    }

    /**
     * @return
     */
    public String getFirstName()
    {
	fetch();
	return firstName;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getGang()
    {
	fetch();
	initGang();
	return gang;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getGangId()
    {
	fetch();
	return gangId;
    }

    /**
     * Gets referenced type pd.address.Address
     */
    public Address getJuvenileAddress()
    {
	fetch();
	initJuvenileAddress();
	return juvenileAddress;
    }

    /**
     * Get the reference value to class :: pd.address.Address
     */
    public String getJuvenileAddressId()
    {
	fetch();
	return juvenileAddressId;
    }

    /**
     * @return
     */
    public String getJuvenileNum()
    {
	fetch();
	return juvenileNum;
    }

    /**
     * @return
     */
    public String getJuvenileSsn()
    {
	fetch();
	return juvenileSsn;
    }

    /**
     * @return
     */
    public String getJuvenileTitle()
    {
	fetch();
	return juvenileTitle;
    }

    /**
     * Gets referenced type pd.contact.user.UserProfile
     */
    public UserProfile getLastActionBy()
    {
	fetch();
	initLastActionBy();
	return lastActionBy;
    }

    /**
     * Get the reference value to class :: pd.contact.user.UserProfile
     */
    public String getLastActionById()
    {
	fetch();
	return lastActionById;
    }

    /**
     * @return
     */
    public String getLastName()
    {
	fetch();
	return lastName;
    }

    /**
     * @return
     */
    public String getLastReferralNum()
    {
	fetch();
	return lastReferralNum;
    }

    /**
     * @return
     */
    public String getMiddleName()
    {
	fetch();
	return middleName;
    }

    /**
     * @return
     */
    public String getOffenseSeverity()
    {
	fetch();
	return offenseSeverity;
    }

    /**
     * @return
     */
    public String getOffenseTotal()
    {
	fetch();
	return offenseTotal;
    }

    /**
     * Gets referenced type pd.contact.user.UserProfile
     */
    public UserProfile getPropointJPOpId()
    {
	fetch();
	initPropointJPOpId();
	return propointJPOpId;
    }

    /**
     * Get the reference value to class :: pd.contact.user.UserProfile
     */
    public String getPropointJPOpIdId()
    {
	fetch();
	return propointJPOpIdId;
    }

    /**
     * Gets referenced type pd.codetable.person.JuvenileLevelUnitCode
     */
    public JuvenileLevelUnitCode getPropointLevel()
    {
	fetch();
	initPropointLevel();
	return propointLevel;
    }

    /**
     * Get the reference value to class ::
     * pd.codetable.person.JuvenileLevelUnitCode
     */
    public String getPropointLevelId()
    {
	fetch();
	return propointLevelId;
    }

    /**
     * Gets referenced type pd.codetable.person.JuvenileLevelUnitCode
     */
    public JuvenileLevelUnitCode getPropointUnit()
    {
	fetch();
	initPropointUnit();
	return propointUnit;
    }

    /**
     * Get the reference value to class ::
     * pd.codetable.person.JuvenileLevelUnitCode
     */
    public String getPropointUnitId()
    {
	fetch();
	return propointUnitId;
    }

    /**
     * @return
     */
    public String getProvisionSeverity()
    {
	fetch();
	return provisionSeverity;
    }

    /**
     * @return
     */
    public String getProvisionTotal()
    {
	fetch();
	return provisionTotal;
    }

    /**
     * @return
     */
    public String getPurgeBoxNum()
    {
	fetch();
	return purgeBoxNum;
    }

    /**
     * @return
     */
    public Date getPurgeDate()
    {
	fetch();
	return purgeDate;
    }

    /**
     * @return
     */
    public String getPurgeFlag()
    {
	fetch();
	return purgeFlag;
    }

    /**
     * @return
     */
    public String getPurgeSerNum()
    {
	fetch();
	return purgeSerNum;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getRace()
    {
	fetch();
	initRace();
	return race;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getRaceId()
    {
	fetch();
	return raceId;
    }

    /**
     * 
     * @return
     */
    public String getRectype()
    {
	fetch();
        return rectype;
    }


    /**
     * 
     * @param rectype
     */
    public void setRectype(String rectype)
    {
	if (this.rectype == null || !this.rectype.equals(rectype))
	{
	    markModified();
	}
        this.rectype = rectype;
    }

    /**
     * @return
     */
    public String getReferralCount()
    {
	fetch();
	return referralCount;
    }

    /**
     * @return
     */
    public String getReferralHistory()
    {
	fetch();
	return referralHistory;
    }

    /**
     * @return
     */
    public String getReferralNum()
    {
	fetch();
	return referralNum;
    }

    /**
     * @return
     */
    public String getReferralOfficer()
    {
	fetch();
	return referralOfficer;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public JuvenileSchoolDistrictCode getSchool()
    {
	fetch();
	initSchool();
	return school;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public JuvenileSchoolDistrictCode getSchoolDistrict()
    {
	fetch();
	initSchoolDistrict();
	return schoolDistrict;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getSchoolDistrictId()
    {
	fetch();
	return schoolDistrictId;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getSchoolId()
    {
	fetch();
	return schoolId;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getSex()
    {
	fetch();
	initSex();
	return sex;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getSexId()
    {
	fetch();
	return sexId;
    }

    /**
     * @return
     */
    public String getSsn1()
    {
	fetch();
	return ssn1;
    }

    /**
     * @return
     */
    public String getSsn2()
    {
	fetch();
	return ssn2;
    }

    /**
     * Gets referenced type pd.codetable.person.SocialElementCode
     */
    public SocialElementCode getSsnRelation1()
    {
	fetch();
	initSsnRelation1();
	return ssnRelation1;
    }

    /**
     * Get the reference value to class :: pd.codetable.person.SocialElementCode
     */
    public String getSsnRelation1Id()
    {
	fetch();
	return ssnRelation1Id;
    }

    /**
     * Gets referenced type pd.codetable.person.SocialElementCode
     */
    public SocialElementCode getSsnRelation2()
    {
	fetch();
	initSsnRelation2();
	return ssnRelation2;
    }

    /**
     * Get the reference value to class :: pd.codetable.person.SocialElementCode
     */
    public String getSsnRelation2Id()
    {
	fetch();
	return ssnRelation2Id;
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getStatus()
    {
	fetch();
	initStatus();
	return status;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getStatusId()
    {
	fetch();
	return statusId;
    }

    /**
     * Initialize class relationship to class pd.contact.user.UserProfile
     */
    private void initActionOperator()
    {
	if (actionOperator == null)
	{
	    try
	    {
	    //87191
		actionOperator = UserProfile.find(actionOperatorId);//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(actionOperatorId, pd.contact.user.UserProfile.class).getObject();
	    }
	    catch (Throwable t)
	    {
		actionOperator = null;
	    }
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initAgency()
    {
	if (agency == null)
	{
	    try
	    {
		agency = (Code) new mojo.km.persistence.Reference(agencyId, Code.class, "JUVENILE_AGENCY").getObject();
	    }
	    catch (Throwable t)
	    {
		agency = null;
	    }
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initCounty()
    {
	if (county == null)
	{
	    try
	    {
		county = (Code) new mojo.km.persistence.Reference(countyId, Code.class, PDCodeTableConstants.JUVENILE_COUNTY).getObject();
	    }
	    catch (Throwable t)
	    {
		county = null;
	    }
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initCustodyStatus()
    {
	if (custodyStatus == null)
	{
	    try
	    {
		custodyStatus = (Code) new mojo.km.persistence.Reference(custodyStatusId, Code.class, "CUSTODY_STATUS").getObject();
	    }
	    catch (Throwable t)
	    {
		custodyStatus = null;
	    }
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initDetentionFacility()
    {
	if (detentionFacility == null)
	{
	    try
	    {
		detentionFacility = (Code) new mojo.km.persistence.Reference(detentionFacilityId, Code.class, PDCodeTableConstants.JUVENILE_DETENTION_FACILITY).getObject();
	    }
	    catch (Throwable t)
	    {
		detentionFacility = null;
	    }
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initDetentionStatus()
    {
	if (detentionStatus == null)
	{
	    try
	    {
		detentionStatus = (Code) new mojo.km.persistence.Reference(detentionStatusId, Code.class, "JUVENILE_DETENTION_STATUS").getObject();
	    }
	    catch (Throwable t)
	    {
		detentionStatus = null;
	    }
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initGang()
    {
	if (gang == null)
	{
	    try
	    {
		gang = (Code) new mojo.km.persistence.Reference(gangId, Code.class, "JUVENILE_GANG").getObject();
	    }
	    catch (Throwable t)
	    {
		gang = null;
	    }
	}
    }

    /**
     * Initialize class relationship to class pd.address.Address
     */
    private void initJuvenileAddress()
    {
	if (juvenileAddress == null)
	{
	    try
	    {
		juvenileAddress = (Address) new mojo.km.persistence.Reference(juvenileAddressId, Address.class, (mojo.km.persistence.PersistentObject) this, "juvenileAddress").getObject();
	    }
	    catch (Throwable t)
	    {
		juvenileAddress = null;
	    }
	}
    }

    /**
     * Initialize class relationship to class pd.contact.user.UserProfile
     */
    private void initLastActionBy()
    {
	if (lastActionBy == null)
	{
	    try
	    {
	    //87191
		lastActionBy = UserProfile.find(lastActionById);//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(lastActionById, pd.contact.user.UserProfile.class).getObject();
	    }
	    catch (Throwable t)
	    {
		lastActionBy = null;
	    }
	}
    }

    /**
     * Initialize class relationship to class pd.contact.user.UserProfile
     */
    private void initPropointJPOpId()
    {
	if (propointJPOpId == null)
	{
	    try
	    {
	    //87191
		propointJPOpId = UserProfile.find(propointJPOpIdId);//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(propointJPOpIdId, pd.contact.user.UserProfile.class).getObject();
	    }
	    catch (Throwable t)
	    {
		propointJPOpId = null;
	    }
	}
    }

    /**
     * Initialize class relationship to class
     * pd.codetable.person.JuvenileLevelUnitCode
     */
    private void initPropointLevel()
    {
	if (propointLevel == null)
	{
	    if (propointLevelId != null)
	    {
		StringBuffer derivedOid = new StringBuffer("");
		/*derivedOid.append(Formatter.pad(propointLevelId, 4, '0', true));
		if (propointUnitId != null)
		{
		    derivedOid.append(Formatter.pad(propointUnitId, 4, '0', true));
		}*/

		try
		{
		    //propointLevel = (pd.codetable.person.JuvenileLevelUnitCode) new mojo.km.persistence.Reference(derivedOid.toString(), pd.codetable.person.JuvenileLevelUnitCode.class).getObject();
		    derivedOid.append(propointLevelId).append(propointUnitId);
		    propointUnit = JuvenileLevelUnitCode.find("levelUnitCD", derivedOid.toString());
		}
		catch (Throwable t)
		{
		    propointLevel = null;
		}
	    }
	}
    }

    /**
     * Initialize class relationship to class
     * pd.codetable.person.JuvenileLevelUnitCode
     */
    private void initPropointUnit()
    {
	if (propointUnit == null)
	{
	    if (propointUnitId != null)
	    {
		StringBuffer derivedOid = new StringBuffer();
		/*if (propointLevelId != null)
		{
		    derivedOid.append(Formatter.pad(propointLevelId, 4, '0', true));
		}
		else
		{
		    return;
		}
		derivedOid.append(Formatter.pad(propointUnitId, 4, '0', true));*/
		try
		{
		   // propointUnit = (pd.codetable.person.JuvenileLevelUnitCode) new mojo.km.persistence.Reference(derivedOid.toString(), pd.codetable.person.JuvenileLevelUnitCode.class).getObject();
		    
		  
			derivedOid.append(propointLevelId).append(propointUnitId);
			
			propointUnit = JuvenileLevelUnitCode.find("levelUnitCD", derivedOid.toString());
		}
		catch (Throwable t)
		{
		    propointUnit = null;
		}
	    }
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initRace()
    {
	if (race == null)
	{
	    try
	    {
		race = (Code) new mojo.km.persistence.Reference(raceId, Code.class, PDCodeTableConstants.RACE).getObject();
	    }
	    catch (Throwable t)
	    {
		race = null;
	    }
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initSchool()
    {
	if (school == null)
	{
	    try
	    {
		school = (JuvenileSchoolDistrictCode) new mojo.km.persistence.Reference(schoolId, JuvenileSchoolDistrictCode.class).getObject();
	    }
	    catch (Throwable t)
	    {
		school = null;
	    }
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initSchoolDistrict()
    {
	if (schoolDistrict == null)
	{
	    try
	    {
		schoolDistrict = (JuvenileSchoolDistrictCode) new mojo.km.persistence.Reference(schoolDistrictId, JuvenileSchoolDistrictCode.class).getObject();
	    }
	    catch (Throwable t)
	    {
		schoolDistrict = null;
	    }
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initSex()
    {
	if (sex == null)
	{
	    try
	    {
		sex = (Code) new mojo.km.persistence.Reference(sexId, Code.class, "SEX").getObject();
	    }
	    catch (Throwable t)
	    {
		sex = null;
	    }
	}
    }

    /**
     * Initialize class relationship to class
     * pd.codetable.person.SocialElementCode
     */
    private void initSsnRelation1()
    {
	if (ssnRelation1 == null)
	{
	    try
	    {
		if (ssnRelation1Id != null)
		{
		    StringBuffer derivedOid = new StringBuffer();
		    derivedOid.append(Formatter.pad(PDCodeTableConstants.SOCIAL_ELEMENT_RELATION, 4, '0', true));
		    derivedOid.append(Formatter.pad(ssnRelation1Id, 4, '0', true));
		    ssnRelation1 = (SocialElementCode) new mojo.km.persistence.Reference(derivedOid.toString(), SocialElementCode.class).getObject();
		}
	    }
	    catch (Throwable t)
	    {
		ssnRelation1 = null;
	    }
	}
    }

    /**
     * Initialize class relationship to class
     * pd.codetable.person.SocialElementCode
     */
    private void initSsnRelation2()
    {
	if (ssnRelation2 == null)
	{
	    try
	    {
		if (ssnRelation2Id != null)
		{
		    StringBuffer derivedOid = new StringBuffer();
		    derivedOid.append(Formatter.pad(PDCodeTableConstants.SOCIAL_ELEMENT_RELATION, 4, '0', true));
		    derivedOid.append(Formatter.pad(ssnRelation2Id, 4, '0', true));
		    ssnRelation2 = (SocialElementCode) new mojo.km.persistence.Reference(derivedOid.toString(), SocialElementCode.class).getObject();
		}
	    }
	    catch (Throwable t)
	    {
		ssnRelation2 = null;
	    }
	}
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initStatus()
    {
	if (status == null)
	{
	    try
	    {
		status = (Code) new mojo.km.persistence.Reference(statusId, Code.class, "JUVENILE_STATUS").getObject();
	    }
	    catch (Throwable t)
	    {
		status = null;
	    }
	}
    }

    /**
     * @param date
     */
    public void setActionDate(Date date)
    {
	if (this.actionDate == null || !this.actionDate.equals(date))
	{
	    markModified();
	}
	actionDate = date;
    }

    /**
     * set the type reference for class member theActionOperator
     */
     //87191
    public void setActionOperator(UserProfile theActionOperator)
    {
	/*if (this.actionOperator == null || !this.actionOperator.equals(theActionOperator))
	{
	    markModified();
	}
	if (actionOperator.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theActionOperator);
	}*/
	setActionOperatorId("" + theActionOperator.getUserID());
	this.actionOperator = theActionOperator;//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(theActionOperator).getObject();
    }

    /**
     * Set the reference value to class :: pd.contact.user.UserProfile
     */
    public void setActionOperatorId(String theActionOperatorId)
    {
	if (this.actionOperatorId == null || !this.actionOperatorId.equals(theActionOperatorId))
	{
	    markModified();
	}
	actionOperator = null;
	this.actionOperatorId = theActionOperatorId;
    }

    /**
     * @param theAge
     */
    public void setAge(String theAge)
    {
	if (this.age == null || !this.age.equals(theAge))
	{
	    markModified();
	}
	age = theAge;
    }

    /**
     * set the type reference for class member agency
     */
    public void setAgency(Code theAgency)
    {
	if (this.agency == null || !this.agency.equals(theAgency))
	{
	    markModified();
	}
	if (agency.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theAgency);
	}
	setAgencyId("" + theAgency.getOID());
	this.agency = (Code) new mojo.km.persistence.Reference(theAgency).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setAgencyId(String theAgencyId)
    {
	if (this.agencyId == null || !this.agencyId.equals(theAgencyId))
	{
	    markModified();
	}
	agency = null;
	this.agencyId = theAgencyId;
    }

    /**
     * @param theAttentionFlag
     */
    public void setAttentionFlag(String theAttentionFlag)
    {
	if (this.attentionFlag == null || !this.attentionFlag.equals(theAttentionFlag))
	{
	    markModified();
	}
	attentionFlag = theAttentionFlag;
    }

    /**
     * @param theBirthDate
     */
    public void setBirthDate(Date theBirthDate)
    {
	if (this.birthDate == null || !this.birthDate.equals(theBirthDate))
	{
	    markModified();
	}
	birthDate = theBirthDate;
    }

    /**
     * @param theCaseNotePart1
     */
    public void setCaseNotePart1(String theCaseNotePart1)
    {
	if (this.caseNotePart1 == null || !this.caseNotePart1.equals(theCaseNotePart1))
	{
	    markModified();
	}
	caseNotePart1 = theCaseNotePart1;
    }

    /**
     * @param theCaseNotePart2
     */
    public void setCaseNotePart2(String theCaseNotePart2)
    {
	if (this.caseNotePart2 == null || !this.caseNotePart2.equals(theCaseNotePart2))
	{
	    markModified();
	}
	caseNotePart2 = theCaseNotePart2;
    }

    /**
     * @param theCheckedOutDate
     */
    public void setCheckedOutDate(Date theCheckedOutDate)
    {
	if (this.checkedOutDate == null || !this.checkedOutDate.equals(theCheckedOutDate))
	{
	    markModified();
	}
	checkedOutDate = theCheckedOutDate;
    }

    /**
     * @param theCheckedOutTo
     */
    public void setCheckedOutTo(String theCheckedOutTo)
    {
	if (this.checkedOutTo == null || !this.checkedOutTo.equals(theCheckedOutTo))
	{
	    markModified();
	}
	checkedOutTo = theCheckedOutTo;
    }

    /**
     * set the type reference for class member county
     */
    public void setCounty(Code theCounty)
    {
	if (this.county == null || !this.county.equals(theCounty))
	{
	    markModified();
	}
	if (theCounty.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theCounty);
	}
	setCountyId("" + theCounty.getOID());
	this.county = (Code) new mojo.km.persistence.Reference(theCounty).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setCountyId(String theCountyId)
    {
	if (this.countyId == null || !this.countyId.equals(theCountyId))
	{
	    markModified();
	}
	county = null;
	this.countyId = theCountyId;
    }

    /**
     * set the type reference for class member custodyStatus
     */
    public void setCustodyStatus(Code theCustodyStatus)
    {
	if (this.custodyStatus == null || !this.custodyStatus.equals(theCustodyStatus))
	{
	    markModified();
	}
	if (theCustodyStatus.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theCustodyStatus);
	}
	setCustodyStatusId("" + custodyStatus.getOID());
	this.custodyStatus = (Code) new mojo.km.persistence.Reference(custodyStatus).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setCustodyStatusId(String theCustodyStatusId)
    {
	if (this.custodyStatusId == null || !this.custodyStatusId.equals(theCustodyStatusId))
	{
	    markModified();
	}
	custodyStatus = null;
	this.custodyStatusId = theCustodyStatusId;
    }

    /**
     * @param theDetentionAddInd
     */
    public void setDetentionAddInd(String theDetentionAddInd)
    {
	if (this.detentionAddInd == null || !this.detentionAddInd.equals(theDetentionAddInd))
	{
	    markModified();
	}
	detentionAddInd = theDetentionAddInd;
    }

    /**
     * set the type reference for class member detentionFacility
     */
    public void setDetentionFacility(Code theDetentionFacility)
    {
	if (this.detentionFacility == null || !this.detentionFacility.equals(theDetentionFacility))
	{
	    markModified();
	}
	if (theDetentionFacility.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theDetentionFacility);
	}
	setDetentionFacilityId("" + theDetentionFacility.getOID());
	this.detentionFacility = (Code) new mojo.km.persistence.Reference(theDetentionFacility).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setDetentionFacilityId(String theDetentionFacilityId)
    {
	if (this.detentionFacilityId == null || !this.detentionFacilityId.equals(theDetentionFacilityId))
	{
	    markModified();
	}
	detentionFacility = null;
	this.detentionFacilityId = theDetentionFacilityId;
    }

    /**
     * set the type reference for class member theDetentionStatus
     */
    public void setDetentionStatus(Code theDetentionStatus)
    {
	if (this.detentionStatus == null || !this.detentionStatus.equals(theDetentionStatus))
	{
	    markModified();
	}
	if (theDetentionStatus.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theDetentionStatus);
	}
	setDetentionStatusId("" + theDetentionStatus.getOID());
	this.detentionStatus = (Code) new mojo.km.persistence.Reference(theDetentionStatus).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setDetentionStatusId(String theDetentionStatusId)
    {
	if (this.detentionStatusId == null || !this.detentionStatusId.equals(theDetentionStatusId))
	{
	    markModified();
	}
	detentionStatus = null;
	this.detentionStatusId = theDetentionStatusId;
    }

    /**
     * @param theDobVerification
     */
    public void setDobVerification(String theDobVerification)
    {
	if (this.dobVerification == null || !this.dobVerification.equals(theDobVerification))
	{
	    markModified();
	}
	dobVerification = theDobVerification;
    }

    /**
     * @param theFirstName
     */
    public void setFirstName(String theFirstName)
    {
	if (this.firstName == null || !this.firstName.equals(theFirstName))
	{
	    markModified();
	}
	firstName = theFirstName;
    }

    /**
     * set the type reference for class member gang
     */
    public void setGang(Code theGang)
    {
	if (this.gang == null || !this.gang.equals(theGang))
	{
	    markModified();
	}
	if (theGang.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(gang);
	}
	setGangId("" + theGang.getOID());
	this.gang = (Code) new mojo.km.persistence.Reference(theGang).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setGangId(String theGangId)
    {
	if (this.gangId == null || !this.gangId.equals(theGangId))
	{
	    markModified();
	}
	gang = null;
	this.gangId = theGangId;
    }

    /**
     * set the type reference for class member juvenileAddress
     */
    public void setJuvenileAddress(Address theJuvenileAddress)
    {
	if (this.juvenileAddress == null || !this.juvenileAddress.equals(theJuvenileAddress))
	{
	    markModified();
	}
	if (theJuvenileAddress.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theJuvenileAddress);
	}
	setJuvenileAddressId("" + theJuvenileAddress.getOID());
	this.juvenileAddress = (Address) new mojo.km.persistence.Reference(theJuvenileAddress).getObject();
    }

    /**
     * Set the reference value to class :: pd.address.Address
     */
    public void setJuvenileAddressId(String theJuvenileAddressId)
    {
	if (this.juvenileAddressId == null || !this.juvenileAddressId.equals(theJuvenileAddressId))
	{
	    markModified();
	}
	juvenileAddress = null;
	this.juvenileAddressId = theJuvenileAddressId;
    }

    /**
     * @param theJuvenileNum
     */
    public void setJuvenileNum(String theJuvenileNum)
    {
	if (this.juvenileNum == null || !this.juvenileNum.equals(theJuvenileNum))
	{
	    markModified();
	}
	juvenileNum = theJuvenileNum;
    }

    /**
     * @param theJuvenileSsn
     */
    public void setJuvenileSsn(String theJuvenileSsn)
    {
	if (this.juvenileSsn == null || !this.juvenileSsn.equals(theJuvenileSsn))
	{
	    markModified();
	}
	juvenileSsn = theJuvenileSsn;
    }

    /**
     * @param theJuvenileTitle
     */
    public void setJuvenileTitle(String theJuvenileTitle)
    {
	if (this.juvenileTitle == null || !this.juvenileTitle.equals(theJuvenileTitle))
	{
	    markModified();
	}
	juvenileTitle = theJuvenileTitle;
    }

    /**
     * set the type reference for class member lastActionBy
     */
     //87191
    public void setLastActionBy(UserProfile theLastActionBy)
    {
	/*if (this.lastActionBy == null || !this.lastActionBy.equals(theLastActionBy))
	{
	    markModified();
	}
	if (theLastActionBy.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theLastActionBy);
	}*/
	setLastActionById("" + theLastActionBy.getUserID());
	this.lastActionBy = theLastActionBy;//(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(theLastActionBy).getObject();
    }

    /**
     * Set the reference value to class :: pd.contact.user.UserProfile
     */
    public void setLastActionById(String theLastActionById)
    {
	if (this.lastActionById == null || !this.lastActionById.equals(theLastActionById))
	{
	    markModified();
	}
	lastActionBy = null;
	this.lastActionById = theLastActionById;
    }

    /**
     * @param theLastName
     */
    public void setLastName(String theLastName)
    {
	if (this.lastName == null || !this.lastName.equals(theLastName))
	{
	    markModified();
	}
	lastName = theLastName;
    }

    /**
     * @param theLastReferralNum
     */
    public void setLastReferralNum(String theLastReferralNum)
    {
	if (this.lastReferralNum == null || !this.lastReferralNum.equals(theLastReferralNum))
	{
	    markModified();
	}
	lastReferralNum = theLastReferralNum;
    }

    /**
     * @param theMiddleName
     */
    public void setMiddleName(String theMiddleName)
    {
	if (this.middleName == null || !this.middleName.equals(theMiddleName))
	{
	    markModified();
	}
	middleName = theMiddleName;
    }

    /**
     * set the type reference for class member offenseSeverity
     */
    public void setOffenseSeverity(String theOffenseSeverity)
    {
	if (this.offenseSeverity == null || !this.offenseSeverity.equals(theOffenseSeverity))
	{
	    markModified();
	}
	offenseSeverity = theOffenseSeverity;
    }

    /**
     * @param theOffenseTotal
     */
    public void setOffenseTotal(String theOffenseTotal)
    {
	if (this.offenseTotal == null || !this.offenseTotal.equals(theOffenseTotal))
	{
	    markModified();
	}
	offenseTotal = theOffenseTotal;
    }

    /**
     * set the type reference for class member propointJPOpId
     */
     //87191
    public void setPropointJPOpId(UserProfile thePropointJPOpId)
    {
	/*if (this.propointJPOpId == null || !this.propointJPOpId.equals(thePropointJPOpId))
	{
	    markModified();
	}
	if (thePropointJPOpId.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(thePropointJPOpId);
	}*/
	setPropointJPOpIdId("" + thePropointJPOpId.getUserID());
	this.propointJPOpId =thePropointJPOpId; //(pd.contact.user.UserProfile) new mojo.km.persistence.Reference(thePropointJPOpId).getObject();
    }

    /**
     * Set the reference value to class :: pd.contact.user.UserProfile
     */
    public void setPropointJPOpIdId(String thePropointJPOpIdId)
    {
	if (this.propointJPOpIdId == null || !this.propointJPOpIdId.equals(thePropointJPOpIdId))
	{
	    markModified();
	}
	propointJPOpId = null;
	this.propointJPOpIdId = thePropointJPOpIdId;
    }

    /**
     * set the type reference for class member propointLevel
     */
    public void setPropointLevel(JuvenileLevelUnitCode thePropointLevel)
    {
	if (this.propointLevel == null || !this.propointLevel.equals(thePropointLevel))
	{
	    markModified();
	}
	if (thePropointLevel.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(thePropointLevel);
	}
	setPropointLevelId("" + thePropointLevel.getOID());
	this.propointLevel = (JuvenileLevelUnitCode) new mojo.km.persistence.Reference(thePropointLevel).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setPropointLevelId(String thePropointLevelId)
    {
	if (this.propointLevelId == null || !this.propointLevelId.equals(thePropointLevelId))
	{
	    markModified();
	}
	propointLevel = null;
	this.propointLevelId = thePropointLevelId;
    }

    /**
     * set the type reference for class member propointUnit
     */
    public void setPropointUnit(JuvenileLevelUnitCode thePropointUnit)
    {
	if (this.propointUnit == null || !this.propointUnit.equals(thePropointUnit))
	{
	    markModified();
	}
	if (thePropointUnit.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(thePropointUnit);
	}
	setPropointUnitId("" + thePropointUnit.getOID());
	this.propointUnit = (JuvenileLevelUnitCode) new mojo.km.persistence.Reference(thePropointUnit).getObject();
    }

    /**
     * Set the reference value to class member propointUnitId
     */
    public void setPropointUnitId(String thePropointUnitId)
    {
	if (this.propointUnitId == null || !this.propointUnitId.equals(thePropointUnitId))
	{
	    markModified();
	}
	propointUnit = null;
	this.propointUnitId = thePropointUnitId;
    }

    /**
     * set the type reference for class member provisionSeverity
     */
    public void setProvisionSeverity(String theProvisionSeverity)
    {
	if (this.provisionSeverity == null || !this.provisionSeverity.equals(theProvisionSeverity))
	{
	    markModified();
	}
	this.provisionSeverity = theProvisionSeverity;
    }

    /**
     * @param theProvisionTotal
     */
    public void setProvisionTotal(String theProvisionTotal)
    {
	if (this.provisionTotal == null || !this.provisionTotal.equals(theProvisionTotal))
	{
	    markModified();
	}
	provisionTotal = theProvisionTotal;
    }

    /**
     * @param thePurgeBoxNum
     */
    public void setPurgeBoxNum(String thePurgeBoxNum)
    {
	if (this.purgeBoxNum == null || !this.purgeBoxNum.equals(thePurgeBoxNum))
	{
	    markModified();
	}
	purgeBoxNum = thePurgeBoxNum;
    }

    /**
     * @param thePurgeDate
     */
    public void setPurgeDate(Date thePurgeDate)
    {
	if (this.purgeDate == null || !this.purgeDate.equals(thePurgeDate))
	{
	    markModified();
	}
	purgeDate = thePurgeDate;
    }

    /**
     * @param thePurgeFlag
     */
    public void setPurgeFlag(String thePurgeFlag)
    {
	if (this.purgeFlag == null || !this.purgeFlag.equals(thePurgeFlag))
	{
	    markModified();
	}
	purgeFlag = thePurgeFlag;
    }

    /**
     * @param thePurgeSerNum
     */
    public void setPurgeSerNum(String thePurgeSerNum)
    {
	if (this.purgeSerNum == null || !this.purgeSerNum.equals(thePurgeSerNum))
	{
	    markModified();
	}
	purgeSerNum = thePurgeSerNum;
    }

    /**
     * set the type reference for class member race
     */
    public void setRace(Code theRace)
    {
	if (this.race == null || !this.race.equals(theRace))
	{
	    markModified();
	}
	if (race.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theRace);
	}
	setRaceId("" + theRace.getOID());
	this.race = (Code) new mojo.km.persistence.Reference(theRace).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setRaceId(String theRaceId)
    {
	if (this.raceId == null || !this.raceId.equals(theRaceId))
	{
	    markModified();
	}
	race = null;
	this.raceId = theRaceId;
    }

    /**
     * @param theReferralCount
     */
    public void setReferralCount(String theReferralCount)
    {
	if (this.referralCount == null || !this.referralCount.equals(theReferralCount))
	{
	    markModified();
	}
	referralCount = theReferralCount;
    }

    /**
     * @param theReferralHistory
     */
    public void setReferralHistory(String theReferralHistory)
    {
	if (this.referralHistory == null || !this.referralHistory.equals(theReferralHistory))
	{
	    markModified();
	}
	referralHistory = theReferralHistory;
    }

    /**
     * @param theReferralNum
     */
    public void setReferralNum(String theReferralNum)
    {
	if (this.referralNum == null || !this.referralNum.equals(theReferralNum))
	{
	    markModified();
	}
	referralNum = theReferralNum;
    }

    /**
     * @param theReferralOfficer
     */
    public void setReferralOfficer(String theReferralOfficer)
    {
	if (this.referralOfficer == null || !this.referralOfficer.equals(theReferralOfficer))
	{
	    markModified();
	}
	referralOfficer = theReferralOfficer;
    }

    /**
     * set the type reference for class member school
     */
    public void setSchool(Code theSchool)
    {
	if (this.school == null || !this.school.equals(theSchool))
	{
	    markModified();
	}
	if (theSchool.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theSchool);
	}
	setSchoolId("" + theSchool.getOID());
	this.school = (JuvenileSchoolDistrictCode) new mojo.km.persistence.Reference(theSchool).getObject();
    }

    /**
     * set the type reference for class member school
     */
    public void setSchool(JuvenileSchoolDistrictCode theSchool)
    {
	if (this.school == null || !this.school.equals(theSchool))
	{
	    markModified();
	}
	if (theSchool.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theSchool);
	}
	setSchoolId("" + theSchool.getOID());
	this.school = (JuvenileSchoolDistrictCode) new mojo.km.persistence.Reference(theSchool).getObject();
    }

    /**
     * set the type reference for class member schoolDistrict
     */
    public void setSchoolDistrict(Code theSchoolDistrict)
    {
	if (this.schoolDistrict == null || !this.schoolDistrict.equals(theSchoolDistrict))
	{
	    markModified();
	}
	if (theSchoolDistrict.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theSchoolDistrict);
	}
	setSchoolDistrictId("" + theSchoolDistrict.getOID());
	this.schoolDistrict = (JuvenileSchoolDistrictCode) new mojo.km.persistence.Reference(theSchoolDistrict).getObject();
    }

    /**
     * set the type reference for class member schoolDistrict
     */
    public void setSchoolDistrict(JuvenileSchoolDistrictCode theSchoolDistrict)
    {
	if (this.schoolDistrict == null || !this.schoolDistrict.equals(theSchoolDistrict))
	{
	    markModified();
	}
	if (theSchoolDistrict.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theSchoolDistrict);
	}
	setSchoolDistrictId("" + theSchoolDistrict.getOID());
	this.schoolDistrict = (JuvenileSchoolDistrictCode) new mojo.km.persistence.Reference(theSchoolDistrict).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setSchoolDistrictId(String theSchoolDistrictId)
    {
	if (this.schoolDistrictId == null || !this.schoolDistrictId.equals(theSchoolDistrictId))
	{
	    markModified();
	}
	schoolDistrict = null;
	this.schoolDistrictId = theSchoolDistrictId;
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setSchoolId(String theSchoolId)
    {
	if (this.schoolId == null || !this.schoolId.equals(theSchoolId))
	{
	    markModified();
	}
	school = null;
	this.schoolId = theSchoolId;
    }

    /**
     * set the type reference for class member sex
     */
    public void setSex(Code theSex)
    {
	if (this.sex == null || !this.sex.equals(theSex))
	{
	    markModified();
	}
	if (theSex.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theSex);
	}
	setSexId("" + theSex.getOID());
	this.sex = (Code) new mojo.km.persistence.Reference(theSex).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setSexId(String theSexId)
    {
	if (this.sexId == null || !this.sexId.equals(theSexId))
	{
	    markModified();
	}
	sex = null;
	this.sexId = theSexId;
    }

    /**
     * @param theSsn1
     */
    public void setSsn1(String theSsn1)
    {
	if (this.ssn1 == null || !this.ssn1.equals(theSsn1))
	{
	    markModified();
	}
	ssn1 = theSsn1;
    }

    /**
     * @param theSsn2
     */
    public void setSsn2(String theSsn2)
    {
	if (this.ssn2 == null || !this.ssn2.equals(theSsn2))
	{
	    markModified();
	}
	ssn2 = theSsn2;
    }

    /**
     * set the type reference for class member ssnRelation1
     */
    public void setSsnRelation1(SocialElementCode theSsnRelation1)
    {
	if (this.ssnRelation1 == null || !this.ssnRelation1.equals(theSsnRelation1))
	{
	    markModified();
	}
	if (theSsnRelation1.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theSsnRelation1);
	}
	setSsnRelation1Id("" + theSsnRelation1.getOID());
	this.ssnRelation1 = (SocialElementCode) new mojo.km.persistence.Reference(theSsnRelation1).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setSsnRelation1Id(String theSsnRelation1Id)
    {
	if (this.ssnRelation1Id == null || !this.ssnRelation1Id.equals(theSsnRelation1Id))
	{
	    markModified();
	}
	ssnRelation1 = null;
	this.ssnRelation1Id = theSsnRelation1Id;
    }

    /**
     * set the type reference for class member ssnRelation2
     */
    public void setSsnRelation2(Code theSsnRelation2)
    {
	if (this.ssnRelation2 == null || !this.ssnRelation2.equals(theSsnRelation2))
	{
	    markModified();
	}
	if (theSsnRelation2.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theSsnRelation2);
	}
	setSsnRelation2Id("" + theSsnRelation2.getOID());
	this.ssnRelation2 = (SocialElementCode) new mojo.km.persistence.Reference(theSsnRelation2).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setSsnRelation2Id(String theSsnRelation2Id)
    {
	if (this.ssnRelation2Id == null || !this.ssnRelation2Id.equals(theSsnRelation2Id))
	{
	    markModified();
	}
	ssnRelation2 = null;
	this.ssnRelation2Id = theSsnRelation2Id;
    }

    /**
     * set the type reference for class member status
     */
    public void setStatus(Code theStatus)
    {
	if (this.status == null || !this.status.equals(theStatus))
	{
	    markModified();
	}
	if (theStatus.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theStatus);
	}
	setStatusId("" + theStatus.getOID());
	this.status = (Code) new mojo.km.persistence.Reference(theStatus).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setStatusId(String theStatusId)
    {
	if (this.statusId == null || !this.statusId.equals(theStatusId))
	{
	    markModified();
	}
	status = null;
	this.statusId = theStatusId;
    }
    
    /**
     * set the type reference for class member status
     */
    public void setOldStatus(Code theOldStatus)
    {
	if (this.oldStatus == null || !this.oldStatus.equals(theOldStatus))
	{
	    markModified();
	}
	if (theOldStatus.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theOldStatus);
	}
	setOldStatusId("" + theOldStatus.getOID());
	this.oldStatus = (Code) new mojo.km.persistence.Reference(theOldStatus).getObject();
    }
    
    /**
     * @param oldStatusId
     *            the oldStatusId to set
     */
    public void setOldStatusId(String oldStatusId)
    {

	if (this.oldStatusId == null || !this.oldStatusId.equals(oldStatusId))
	{
	    markModified();
	}
	oldStatus = null;
	this.oldStatusId = oldStatusId;
    }

    public String calculatemasterStatusId()
    {
	String statusId = "";
	JuvenileMasterStatus status = JuvenileMasterStatus.find(this.getOID());
	if (status != null)
	{
	    statusId = status.getStatusId();
	}

	return statusId;

    }

    /**
     * @return
     */
    public String getNcicEthnicity()
    {
	return ncicEthnicity;
    }

    /**
     * @return
     */
    public String getNcicRaceCode()
    {
	return ncicRaceCode;
    }

    /**
     * @param string
     */
    public void setNcicEthnicity(String ncicEthnicity)
    {
	this.ncicEthnicity = ncicEthnicity;
    }

    /**
     * @param string
     */
    public void setNcicRaceCode(String ncicRaceCode)
    {
	this.ncicRaceCode = ncicRaceCode;
    }

    public String getJuvAddress()
    {
	fetch();
	return juvAddress;
    }

    public void setJuvAddress(String juvAddress)
    {
	this.juvAddress = juvAddress;
    }

    /**
     * @return the oldStatusId
     */
    public String getOldStatusId()
    {
	fetch();
	return oldStatusId;
    }

    /**
     * @return the originalRaceId
     */
    public String getOriginalRaceId()
    {
	return originalRaceId;
    }

    /**
     * @param originalRaceId the originalRaceId to set
     */
    public void setOriginalRaceId(String originalRaceId)
    {
	this.originalRaceId = originalRaceId;
    }

    /**
     * @return the addressId
     */
    public int getAddressId()
    {
	return addressId;
    }

    /**
     * @param addressId the addressId to set
     */
    public void setAddressId(int addressId)
    {
	this.addressId = addressId;
    }

    /**
     * @return the lcDate
     */
    public Date getLcDate()
    {
	return lcDate;
    }

    /**
     * @param lcDate the lcDate to set
     */
    public void setLcDate(Date lcDate)
    {
	this.lcDate = lcDate;
    }

    /**
     * @return the lcuser
     */
    public String getLcuser()
    {
	return lcuser;
    }

    /**
     * @param lcuser the lcuser to set
     */
    public void setLcuser(String lcuser)
    {
	this.lcuser = lcuser;
    }
    public String getSealComments()
    {
	return sealComments;
    }

    public void setSealComments(String sealComments)
    {
	this.sealComments = sealComments;
    }

    public String getSealedDate()
    {
	return sealedDate;
    }

    public void setSealedDate(String sealedDate)
    {
	this.sealedDate = sealedDate;
    }

    public String getPreferredFirstName()
    {
	return preferredFirstName;
    }

    public void setPreferredFirstName(String preferredFirstName)
    {
	if (this.preferredFirstName == null
		|| !this.preferredFirstName.equals( preferredFirstName ) ) {
	    markModified();
	}
	this.preferredFirstName = null;
	this.preferredFirstName = preferredFirstName;
    }
    
    public Date getRealDOB()
    {
	fetch();
	return this.realDOB;
    }
    
    public void setRealDOB(Date realDOB)
    {
	if (this.realDOB == null || !this.realDOB.equals(realDOB))
	{
	    markModified();
	}
	this.realDOB = realDOB;
    }

    public String getLiveWithTjjd()
    {
	fetch();
        return liveWithTjjd;
    }

    public void setLiveWithTjjd(String liveWithTjjd)
    {
	if (this.liveWithTjjd == null || !this.liveWithTjjd.equals(liveWithTjjd))
	{
	    markModified();
	}
        this.liveWithTjjd = liveWithTjjd;
    }

    public String getCauseOfDeath()
    {
	fetch();
	return causeOfDeath;
    }

    public void setCauseOfDeath(String causeOfDeath)
    {
	if (this.causeOfDeath == null || !this.causeOfDeath.equals(causeOfDeath))
	{
	    markModified();
	}
	this.causeOfDeath = causeOfDeath;
    }

    public String getDeathVerification()
    {
	fetch();
	return deathVerification;
    }

    public void setDeathVerification(String deathVerification)
    {
	if (this.deathVerification == null || !this.deathVerification.equals(deathVerification))
	{
	    markModified();
	}
	this.deathVerification = deathVerification;
    }

    public Date getDateOfDeath()
    {
	fetch();
	return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath)
    {
	if (this.dateOfDeath == null || !this.dateOfDeath.equals(dateOfDeath))
	{
	    markModified();
	}
	this.dateOfDeath = dateOfDeath;
    }

    public int getAgeAtDeath()
    {
	fetch();
	return ageAtDeath;
    }

    public void setAgeAtDeath(int ageAtDeath)
    {
	if (this.ageAtDeath != ageAtDeath)
	{
	    markModified();
	}
	this.ageAtDeath = ageAtDeath;
    }

    public int getJuvExcluded()
    {
	fetch();
	return juvExcluded;
    }

    public void setJuvExcluded(int juvExcluded)
    {
	if (this.juvExcluded != juvExcluded)
	{
	    markModified();
	}
	this.juvExcluded = juvExcluded;
    }
  
}
