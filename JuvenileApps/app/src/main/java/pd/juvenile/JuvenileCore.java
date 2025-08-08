package pd.juvenile;

import gnu.regexp.RE;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import messaging.juvenile.GetJuvenileSchoolEvent;
import messaging.juvenile.reply.JuvenileCoreLightResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.exceptionhandling.ParseRuntimeException;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.persistence.Reference;
import mojo.km.security.ISecurityManager;
import mojo.km.util.DateUtil;
import naming.PDCodeTableConstants;
import pd.codetable.Code;
import pd.codetable.person.JuvenileSchoolDistrictCode;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.family.FamilyConstellation;

/**
 * This entity reprents a Juvenile master Core record (only core data from M204)
 */

public class JuvenileCore extends PersistentObject
{
    /**
     * @param searchEvent
     * @return
     */
    private Collection familyConstellationList;
    private Collection juvenileContactList;
    private JJSJuvenile jjsJuvInfo = null;
    private Collection caseFiles;

    static public Iterator findAll(IEvent event)
    {
	IHome home = new Home();
	ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	boolean grantedFeature = securityManager.isAllowed("JCW-SEALPURGE-VIEW");
	if (grantedFeature)
	    return home.findAll(event, JuvenileCore.class);
	else
	    return filterSealedPurged(home.findAll(event, JuvenileCore.class));

    }

    /**
     * @roseuid 42A882800158
     * @param juvNum
     *            . juvenile number is the unique primary key of this table.
     * @param juvNum
     * @return Juvenile.
     * @return pd.juvenilecase.Juvenile
     */
    static public JuvenileCore findCore(String juvNum)
    {
	IHome home = new Home();
	JuvenileCore juvenile = (JuvenileCore) home.find(juvNum, JuvenileCore.class);
	if (juvenile != null && (juvenile.getRecType() == null || juvenile.getRecType().equalsIgnoreCase("JUVENILE")))
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

    protected static Iterator filterSealedPurged(Iterator iter)
    {
	ArrayList<JuvenileCore> detDocs = new ArrayList<JuvenileCore>();
	while (iter.hasNext())
	{
	    JuvenileCore core = (JuvenileCore) iter.next();
	    if (core != null && core.getRecType() != null && core.getRecType().equalsIgnoreCase("JUVENILE"))
		detDocs.add(core);
	}
	return detDocs.iterator();
    }

    protected Date dateOfBirth;
    protected Date realDOB;
    protected String firstName;
    
    private String preferredFirstName;

    protected String hispanicInd;

    protected String lastName;

    protected String middleName;

    protected String nameSuffix;

    private String propointJPOpIdId;

    /**
     * Properties for school
     * 
     * @referencedType pd.codetable.person.JuvenileSchoolDistrictCode
     * @detailerDoNotGenerate true
     */
    private JuvenileSchoolDistrictCode school = null;
    /**
     * /** Properties for schoolDistrict
     * 
     * @referencedType pd.codetable.person.JuvenileSchoolDistrictCode
     * @detailerDoNotGenerate true
     */
    private JuvenileSchoolDistrictCode schoolDistrict = null;
    private String schoolDistrictId;
    private String schoolId;

    /**
     * @detailerDoNotGenerate true
     * @referencedType pd.codetable.Code
     * @contextKey RACE
     */
    protected Code race;

    protected String raceId;
    
    protected Code originalRace;
    
    //Added for US 90441
    protected String originalRaceId;    
    

    protected String sexId;

    /**
     * @detailerDoNotGenerate true
     * @referencedType pd.codetable.Code
     * @contextKey SEX
     */
    private Code sex;

    private String SSN;

    protected String juvenileType;

    //US 70421 Referrals Conversion
    protected String juvAddress;
    protected int addressId;
    protected String juvCounty;
    protected String juvSchoolDist;
    protected String juvSchoolName;
    private String recType;
    //US 70421 Referrals Conversion for Migrated Legacy records without a casefile
    private String ssn1;
    private String ssn2;
    private String ssnRel1;
    private String ssnRel2;

    private String statusId;

    private String comments;
    private String checkOutTo;
    private Date checkedOutDate;
    private String lastActionBy;
    private Date lastUpdate;
    private String operator;

    private String lcuser; //US 71171 added to identify the M204 legacy created record in update juvenile
    private Date lcDate;
    private Date lcTime;

    private String sealComments;
    private Date sealedDate;
    
    private Date createDate;
    
    private String purgeComments;
    private Date purgeDate;
    private String purgeexecutedDate;
    private String purgeFlag;
    private String purgeBoxnum;
    private String purgeSernum;   
    
    private String youthDeathReason;
    private String youthDeathVerification;
    private String youthDeathReasonDesc;
    private String youthDeathVerificationDesc;   
    private Date deathDate;
    private int deathAge;
    private String  SSNUpdateUser;
    private Date SSNUpdateDate;
    private int juvExcludedReporting;

    private String zip;
    private String liveWithTjjd;
    private String updateUser;
    private Date updateDate;

    /**
     * @roseuid 42A882800157
     */
    public JuvenileCore()
    {
    }

    /**
     * @return the lastUpdate
     */
    public Date getLastUpdate()
    {
	fetch();
	return lastUpdate;
    }

    public String getComments()
    {
	fetch();
	return comments;
    }

    public void setComments(String comments)
    {
	this.comments = comments;
    }

    public String getCheckOutTo()
    {
	fetch();
	return checkOutTo;
    }

    public void setCheckOutTo(String checkOutTo)
    {
	this.checkOutTo = checkOutTo;
    }

    /**
     * @return the checkedOutDate
     */
    public Date getCheckedOutDate()
    {
	fetch();
	return checkedOutDate;
    }

    /**
     * @param checkedOutDate
     *            the checkedOutDate to set
     */
    public void setCheckedOutDate(Date checkedOutDate)
    {
	this.checkedOutDate = checkedOutDate;
    }

    /**
     * @param lastUpdate
     *            the lastUpdate to set
     */
    public void setLastUpdate(Date lastUpdate)
    {
	this.lastUpdate = lastUpdate;
    }

    public String getLastActionBy()
    {
	fetch();
	return lastActionBy;
    }

    public void setLastActionBy(String lastActionBy)
    {
	this.lastActionBy = lastActionBy;
    }

    public String getOperator()
    {
	fetch();
	return operator;
    }

    public void setOperator(String operator)
    {
	this.operator = operator;
    }

    public String getJuvenileType()
    {
	fetch();
	return juvenileType;
    }

    public void setJuvenileType(String juvenileType)
    {
	this.juvenileType = juvenileType;
    }

    public String calculateStatusId()
    {
	String statusId = "";
	JuvenileStatus status = JuvenileStatus.find(this.getOID());
	if (status != null)
	{
	    statusId = status.getStatusId();
	}

	return statusId;

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

    private void computeSSN()
    {
	if (this.SSN != null && this.SSN.trim().length() > 0)
	{
	    try
	    {
		String tmp = this.SSN.trim();

		// validate fomat
		RE numbersOnly = new RE("^\\d{9}$");
		RE withDashes = new RE("^\\d{3}-\\d{2}-\\d{4}$");

		if (numbersOnly.isMatch(this.SSN))
		{
		    this.SSN = tmp;
		}
		else
		    if (withDashes.isMatch(this.SSN))
		    {
			StringBuilder buffer = new StringBuilder();
			buffer.append(tmp.substring(0, 3));
			buffer.append(tmp.substring(4, 6));
			buffer.append(tmp.substring(7));
			this.SSN = buffer.toString();
		    }
	    }
	    catch (Exception ex)
	    {
		ex.printStackTrace();
	    }
	}
    }

    /**
     * Access method for the dateOfBirth property.
     * 
     * @roseuid 42A882800168
     * @return the current value of the dateOfBirth property
     */
    public Date getDateOfBirth()
    {
	fetch();
	return dateOfBirth;
    }

    /**
     * Access method for the firstName property.
     * 
     * @roseuid 42A8828001B5
     * @return the current value of the firstName property
     */
    public String getFirstName()
    {
	fetch();
	return firstName;
    }

    /**
     * Access method for the sex property.
     * 
     * @return the current value of the sex property
     */
    public Code getSex()
    {
	fetch();
	initSex();
	return sex;
    }

    public int getAgeInYears(Date ageDate)
    {
	if (ageDate == null)
	{
	    return 0;
	}
	Calendar birthdate = Calendar.getInstance();
	birthdate.setTime(ageDate);
	Calendar now = Calendar.getInstance();

	int age = 0;
	age = now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
	birthdate.add(Calendar.YEAR, age);
	if (now.before(birthdate))
	{
	    age--;
	}
	return age;
    }

    /**
	* 
	*/
    public String getPropointJPOpIdId()
    {
	fetch();
	return propointJPOpIdId;
    }

    /**
     * set the type reference for class member sex
     * 
     * @roseuid 42A8828002CF
     * @param theSex
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
	this.sex = (Code) new Reference(sex).getObject();
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     * 
     * @roseuid 42A8828002CE
     */
    private void initSex()
    {
	if (sex == null)
	{
	    sex = (Code) new Reference(this.getSexId(), Code.class, "SEX").getObject();
	}
    }

    public String getHispanicInd()
    {
	fetch();
	return hispanicInd;
    }

    /**
     * Access method for the juvenileNum property.
     * 
     * @roseuid 42A882800204
     * @return the current value of the juvenileNum property
     */
    public String getJuvenileNum()
    {
	fetch();
	return getOID();
    }

    /**
     * Access method for the lastName property.
     * 
     * @roseuid 42A8828001C6
     * @return the current value of the lastName property
     */
    public String getLastName()
    {
	fetch();
	return lastName;
    }

    /**
     * Access method for the middleName property.
     * 
     * @roseuid 42A8828001E5
     * @return the current value of the middleName property
     */
    public String getMiddleName()
    {
	fetch();
	return middleName;
    }

    public String getNameSuffix()
    {
	fetch();
	return nameSuffix;
    }

    /**
     * Access method for the race property.
     * 
     * @roseuid 42A882800222
     * @return the current value of the race property
     */
    public Code getRace()
    {
	fetch();
	initRace();
	return race;
    }
    
    /**
     * The logic for prod support race is different from others.
     * It doesn't take into account original race
     * it just displays what it gets from the database
     */
    public Code getRaceProdSupport()
    {
	fetch();
	initRaceProdSupport();
	return race;
    }
    
    public Code getOriginalRace()
    {
	fetch();
	initOriginalRace();
	return originalRace;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @roseuid 42A882800272
     * @return java.lang.String
     */
    public String getRaceId()
    {
	fetch();
	return raceId;
    }
    
    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @roseuid 42A882800272
     * @return java.lang.String
     */
    public String getOriginalRaceId()
    {
	fetch();
	return originalRaceId;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @roseuid 42A8828002BF
     * @return java.lang.String
     */
    public String getSexId()
    {
	fetch();
	return sexId;
    }

    /**
     * @return Returns the sSN.
     */
    public String getSSN()
    {
	fetch();
	return SSN;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     * 
     * @roseuid 42A882800280
     */
    private void initRace()
    {
	if (race == null)
	{
	    if ((this.originalRaceId != null 
		    && this.originalRaceId.length() > 0) ) {
		
		race = (Code) new mojo.km.persistence.Reference(originalRaceId, Code.class, PDCodeTableConstants.RACE).getObject();
		
	    }
	   else {
		
		if (this.raceId != null && this.raceId.length() > 0 ) {
			
			race = (Code) new mojo.km.persistence.Reference(this.getRaceId(), Code.class, PDCodeTableConstants.RACE).getObject();
		}
		//race = (Code) new Reference( this.raceId 
		//		, Code.class, PDCodeTableConstants.RACE_ALT).getObject();
	    }
	    
	}
    }
    
    /**
     * Logic does not factor in original race as is 
     * the case with initRace
     */
    private void initRaceProdSupport()
    {
	if (race == null)
	{		
	    if (this.raceId != null && this.raceId.length() > 0 ) {
			
		race = (Code) new mojo.km.persistence.Reference(this.getRaceId(), Code.class, PDCodeTableConstants.RACE).getObject();
	    }
	    
	}
    }
    
    private void initOriginalRace()
    {
	if (originalRace == null)
	{
	    if ( (this.originalRaceId != null 
		    && this.originalRaceId.length() > 0) ) {
		
		originalRace = (Code) new mojo.km.persistence.Reference(originalRaceId, Code.class, PDCodeTableConstants.RACE).getObject();
		
	    } else {
		
		if (this.originalRaceId == null && this.getRaceId() != null 
			    && this.getRaceId().length() > 0 ) {
			
		    originalRace = (Code) new mojo.km.persistence.Reference(this.getRaceId(), Code.class, PDCodeTableConstants.RACE).getObject();
		}
	    }
	    
	}
    }

    /**
     * Sets the value of the dateOfBirth property.
     * 
     * @roseuid 42A882800196
     * @param aDateOfBirth
     *            the new value of the dateOfBirth property
     */
    public void setDateOfBirth(Date aDateOfBirth)
    {
	if (this.dateOfBirth == null || !this.dateOfBirth.equals(aDateOfBirth))
	{
	    markModified();
	}
	dateOfBirth = aDateOfBirth;
    }

    /**
     * setter with integer
     * 
     * @param DateOfBirth
     */
    public void setDateOfBirth(Integer aDateOfBirth)
    {
	Date calculatedDate = null;
	try
	{
	    if (aDateOfBirth != null)
		calculatedDate = DateUtil.IntToDate(aDateOfBirth, DateUtil.DATE_FMT_2);
	}
	catch (ParseRuntimeException e)
	{
	    e.printStackTrace();
	}
	this.dateOfBirth = calculatedDate;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @roseuid 42A8828001B6
     * @param aFirstName
     *            the new value of the firstName property
     */
    public void setFirstName(String aFirstName)
    {
	if (this.firstName == null || !this.firstName.equals(aFirstName))
	{
	    markModified();
	}
	firstName = aFirstName;
    }

    public void setHispanicInd(String aHispanicInd)
    {
	if (this.hispanicInd == null || !this.hispanicInd.equals(aHispanicInd))
	{
	    markModified();
	}
	this.hispanicInd = aHispanicInd;
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
	this.propointJPOpIdId = thePropointJPOpIdId;
    }

    /**
     * Sets the value of the juvenileNum property.
     * 
     * @roseuid 42A882800213
     * @param aJuvenileNum
     *            the new value of the juvenileNum property
     */
    public void setJuvenileNum(String aJuvenileNum)
    {
	setOID(aJuvenileNum);
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @roseuid 42A8828001D4
     * @param aLastName
     *            the new value of the lastName property
     */
    public void setLastName(String aLastName)
    {
	if (this.lastName == null || !this.lastName.equals(aLastName))
	{
	    markModified();
	}
	lastName = aLastName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @roseuid 42A8828001F4
     * @param aMiddleName
     *            the new value of the middleName property
     */
    public void setMiddleName(String aMiddleName)
    {
	if (this.middleName == null || !this.middleName.equals(aMiddleName))
	{
	    markModified();
	}
	middleName = aMiddleName;
    }

    public void setNameSuffix(String aNameSuffix)
    {
	if (this.nameSuffix == null || !this.nameSuffix.equals(aNameSuffix))
	{
	    markModified();
	}
	nameSuffix = aNameSuffix;
    }

    /**
     * set the type reference for class member race
     * 
     * @roseuid 42A882800290
     * @param theRace
     */
    public void setRace(Code theRace)
    {
	if (this.race == null || !this.race.equals(theRace))
	{
	    markModified();
	}
	if (theRace.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(theRace);
	}
	setRaceId("" + theRace.getOID());
	this.race = (Code) new mojo.km.persistence.Reference(race).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     * 
     * @roseuid 42A882800262
     * @param theRaceId
     *            _ retrieval
     */
    public void setRaceId(String theRaceId)
    {
	if (this.raceId == null || !this.raceId.equals(theRaceId))
	{
	    markModified();
	}
	if (theRaceId != null && theRaceId.equals("L"))
	{

	    //per US 90441 read the original race and set it
	    // theRaceId = this.originalRaceId;
	    this.hispanicInd = "Y";
	}
	this.raceId = theRaceId;
    }

    /**
     * Added for #89144 - hotFix _ persistence
     * 
     * @param theRaceId
     */
    public void setRaceIdForHispanic(String theRaceId)
    {
	if (this.raceId == null || !this.raceId.equals(theRaceId))
	{
	    markModified();
	}
	this.raceId = theRaceId;
    }

    /**
     * Added for #90441
     * 
     * @param theRaceId
     */
    public void setOriginalRaceId(String theOriginalRaceId)
    {
	if (this.originalRaceId == null || !this.originalRaceId.equals(theOriginalRaceId))
	{
	    markModified();
	}
	this.originalRaceId = theOriginalRaceId;
	//if (this.raceId == null && this.originalRaceId != null)
	 //   this.raceId = this.originalRaceId;
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     * 
     * @roseuid 42A8828002AF
     * @param theSexId
     */
    public void setSexId(String theSexId)
    {
	if (this.sexId == null || !this.sexId.equals(theSexId))
	{
	    markModified();
	}
	this.sexId = theSexId;
    }

    /**
     * @param string
     *            accepts null, "123456789", or "123-45-6789", otherwise value
     *            is not changed.
     */
    public void setSSN(String string)
    {
	if (this.SSN == null || !this.SSN.equals(string))
	{
	    markModified();
	}
	this.SSN = string;
	this.computeSSN();
    }

    public String getRaceCodeDescription()
    {
	String desc = "";
	if ( (this.originalRaceId != null && this.originalRaceId.equals("") == false)
		|| (this.raceId != null && this.raceId.length() > 0 ))
	{
	    Code raceCode = this.getRace();
	    if (raceCode != null)
	    {
		return raceCode.getDescription();
	    }
	}
	return desc;

    }
    
    /**
     * The logic for prod support race is different from others.
     * It doesn't take into account original race
     * it just displays what it gets from the database
     */
    public String getRaceCodeDescriptionProdSupport()
    {
	String desc = "";
	if ((this.raceId != null && this.raceId.length() > 0 ))
	{
	    Code raceCode = this.getRaceProdSupport();
	    if (raceCode != null)
	    {
		return raceCode.getDescription();
	    }
	}
	return desc;

    }
    
    public String getOriginalRaceCodeDescription()
    {
	String desc = "";
	if ( (this.originalRaceId != null && !this.originalRaceId.equals("")))
	{
	    Code raceCode = this.getOriginalRace();
	    if (raceCode != null)
	    {
		return raceCode.getDescription();
	    }
	}
	return desc;

    }

    public String getSexCodeDescription()
    {
	String desc = "";
	if (this.sexId != null && !this.sexId.equals(""))
	{
	    Code sexCode = this.getSex();
	    if (sexCode != null)
	    {
		return sexCode.getDescription();
	    }
	}
	return desc;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getSchoolDistrictId()
    {
	fetch();
	//US 71184 - Referral Conversion
	GetJuvenileSchoolEvent schoolEvent = new GetJuvenileSchoolEvent();
	schoolEvent.setJuvenileNum(this.getOID());
	Iterator i = JuvenileSchoolHistory.findJuvenileSchoolHistory(schoolEvent);
	if (i.hasNext())
	{
	    JuvenileSchoolHistory school = (JuvenileSchoolHistory) i.next();
	    this.schoolDistrictId = school.getSchoolDistrictId();
	}
	return schoolDistrictId;
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
    public String getSchoolId()
    {
	fetch();
	//US 71184 - Referral Conversion
	GetJuvenileSchoolEvent schoolEvent = new GetJuvenileSchoolEvent();
	schoolEvent.setJuvenileNum(this.getOID());
	Iterator i = JuvenileSchoolHistory.findJuvenileSchoolHistory(schoolEvent);
	if (i.hasNext())
	{
	    JuvenileSchoolHistory school = (JuvenileSchoolHistory) i.next();
	    this.schoolId = school.getSchoolId();
	}
	return schoolId;
    }

    // Profile stripping fix task 97538
    private void initJjsJuvInfo()
    {

	if (jjsJuvInfo == null)
	{
	    jjsJuvInfo = (JJSJuvenile) new Reference(getOID(), JJSJuvenile.class).getObject();
	}
    }

    public JJSJuvenile getJjsJuvInfo()
    {
	fetch();
	initJjsJuvInfo();
	return jjsJuvInfo;
    }

    //
    // Profile stripping fix - task 97643
    /**
     * returns a collection of pd.juvenileFamily.FamilyConstellation Returns
     * only active family constellation
     */
    public FamilyConstellation getCurrentFamilyConstellation()
    {
	Iterator fams = getFamilyConstellationList().iterator();
	while (fams.hasNext())
	{
	    FamilyConstellation fam = (FamilyConstellation) fams.next();
	    if (fam.isActive())
	    {
		return fam;
	    }
	}
	return null;
    }

    /**
     * returns a collection of pd.juvenileFamily.FamilyConstellation Returns all
     * family constellations
     */
    public Collection getFamilyConstellationList()
    {
	initFamilyConstellationList();
	return familyConstellationList;
    }

    /**
     * Initialize class relationship implementation for
     * pd.juvenileFamily.FamilyConstellation
     */
    private void initFamilyConstellationList()
    {
	if (familyConstellationList == null)
	{
	    if (this.getOID() == null)
	    {
		new mojo.km.persistence.Home().bind(this);
	    }
	    familyConstellationList = new mojo.km.persistence.ArrayList(FamilyConstellation.class, "juvenileId", "" + getOID());
	}
    }

    /////////////
    // Profile stripping fix task 97541
    /**
	 * returns a collection of pd.juvenilecase.JuvenileCasefile
	 * 
	 * @return java.util.Collection
	 * @roseuid 4107DFB603A1
	 */
	public Collection getCaseFiles() {
		fetch();
		initCaseFiles();
		return caseFiles;
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.juvenilecase.JuvenileCasefile
	 * 
	 * @roseuid 4107DFB60397
	 */
	private void initCaseFiles() {
		if (caseFiles == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			try {
				caseFiles = new mojo.km.persistence.ArrayList(
						pd.juvenilecase.JuvenileCasefile.class, "juvenileId",
						"" + getOID());
			} catch (Throwable t) {
				caseFiles = new ArrayList();
			}
		}
	}

    /**
     * Initialize class relationship implementation for
     * pd.juvenileFamily.FamilyConstellation
     */
    private void initJuvenileContactList()
    {
	if (juvenileContactList == null)
	{
	    if (this.getOID() == null)
	    {
		new mojo.km.persistence.Home().bind(this);
	    }
	    juvenileContactList = new mojo.km.persistence.ArrayList(JuvenileContact.class, "juvenileId", "" + getOID());
	}
    }

    /**
     * returns a collection of pd.juvenile.JuvenileContact
     */
    public Collection getJuvenileContactList()
    {
	initJuvenileContactList();
	return juvenileContactList;
    }

    ///

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

    public String getJuvAddress()
    {
	fetch();
	return juvAddress;
    }

    public void setJuvAddress(String juvAddress)
    {
	this.juvAddress = juvAddress;
    }

    public int getAddressId()
    {
	fetch();
	return addressId;
    }

    public void setAddressId(int addressId)
    {
	this.addressId = addressId;
    }

    public String getJuvCounty()
    {
	fetch();
	return juvCounty;
    }

    public void setJuvCounty(String juvCounty)
    {
	this.juvCounty = juvCounty;
    }

    public String getJuvSchoolDist()
    {
	fetch();
	return juvSchoolDist;
    }

    public void setJuvSchoolDist(String juvSchoolDist)
    {
	this.juvSchoolDist = juvSchoolDist;
    }

    public String getJuvSchoolName()
    {
	fetch();
	return juvSchoolName;
    }

    public void setJuvSchoolName(String juvSchoolName)
    {
	this.juvSchoolName = juvSchoolName;
    }

    public String getStatusId()
    {
	fetch();
	return statusId;
    }

    public void setStatusId(String statusId)
    {
	this.statusId = statusId;
    }

    public String getSsn1()
    {
	fetch();
	return ssn1;
    }

    public void setSsn1(String ssn1)
    {
	this.ssn1 = ssn1;
    }

    public String getSsn2()
    {
	fetch();
	return ssn2;
    }

    public void setSsn2(String ssn2)
    {
	this.ssn2 = ssn2;
    }

    public String getSsnRel1()
    {
	fetch();
	return ssnRel1;
    }

    public void setSsnRel1(String ssnRel1)
    {
	this.ssnRel1 = ssnRel1;
    }

    public String getSsnRel2()
    {
	fetch();
	return ssnRel2;
    }

    public void setSsnRel2(String ssnRel2)
    {
	this.ssnRel2 = ssnRel2;
    }

    public String getRecType()
    {
	fetch();
	return recType;
    }

    public void setRecType(String recType)
    {
	this.recType = recType;
    }

    public String getLcuser()
    {
	fetch();
	return lcuser;
    }

    public void setLcuser(String lcuser)
    {
	this.lcuser = lcuser;
    }


    /**
     * @return the lcDate
     */
    public Date getLcDate()
    {
	fetch();
	return lcDate;
    }

    /**
     * @param lcDate
     *            the lcDate to set
     */
    public void setLcDate(Date lcDate)
    {
	this.lcDate = lcDate;
    }

    /**
     * @return the lcTime
     */
    public Date getLcTime()
    {
	fetch();
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

    public String getSealComments()
    {
	return sealComments;
    }

    public void setSealComments(String sealComments)
    {
	this.sealComments = sealComments;
    }

    public Date getSealedDate()
    {
	return sealedDate;
    }

    public void setSealedDate(Date sealedDate)
    {
	this.sealedDate = sealedDate;
    }

    public Date getCreateDate()
    {
	return createDate;
    }

    public void setCreateDate(Date createDate)
    {
	this.createDate = createDate;
    }
    //purging
    public String getPurgeComments()
    {
        return purgeComments;
    }

    public void setPurgeComments(String purgeComments)
    {
        this.purgeComments = purgeComments;
    }
    public Date getPurgeDate()
    {
        return purgeDate;
    }

    public void setPurgeDate(Date purgeDate)
    {
        this.purgeDate = purgeDate;
    }
    public String getPurgeexecutedDate()
    {
        return purgeexecutedDate;
    }

    public void setPurgeexecutedDate(String purgeexecutedDate)
    {
        this.purgeexecutedDate = purgeexecutedDate;
    }
    public String getPurgeFlag()
    {
        return purgeFlag;
    }

    public void setPurgeFlag(String purgeFlag)
    {
        this.purgeFlag = purgeFlag;
    }
    public String getPurgeBoxnum()
    {
        return purgeBoxnum;
    }

    public void setPurgeBoxnum(String purgeBoxnum)
    {
        this.purgeBoxnum = purgeBoxnum;
    }
    public String getPurgeSernum()
    {
        return purgeSernum;
    }

    public void setPurgeSernum(String purgeSernum)
    {
        this.purgeSernum = purgeSernum;
    }

    public String getPreferredFirstName()
    {
	fetch();
	return preferredFirstName;
    }

    public void setPreferredFirstName(String preferredFirstName)
    {
	if (this.preferredFirstName == null
		|| !this.preferredFirstName.equals( preferredFirstName ) ) {
	    markModified();
	}
	
	this.preferredFirstName = preferredFirstName;
    }

    public String getYouthDeathReason()
    {
	fetch();
	return youthDeathReason;
    }

    public void setYouthDeathReason(String youthDeathReason)
    {
	if ( this.youthDeathReason == null
			|| !this.youthDeathReason.equals(youthDeathReason) ){
	    markModified();
	}
	this.youthDeathReason = youthDeathReason;
    }

    public String getYouthDeathVerification()
    {
	fetch();
	return youthDeathVerification;
    }

    public void setYouthDeathVerification(String youthDeathVerification)
    {
	if ( this.youthDeathVerification == null 
			|| !this.youthDeathVerification.equals(youthDeathVerification) ) {
	    markModified();
	}
	this.youthDeathVerification = youthDeathVerification;
    }

    public String getYouthDeathReasonDesc()
    {
	fetch();
	return youthDeathReasonDesc;
    }

    public void setYouthDeathReasonDesc(String youthDeathReasonDesc)
    {
	if ( this.youthDeathReasonDesc == null
			|| !this.youthDeathReasonDesc.equals(youthDeathReasonDesc) ){
	    markModified();
	}
	this.youthDeathReasonDesc = youthDeathReasonDesc;
    }

    public String getYouthDeathVerificationDesc()
    {
	fetch();
	return youthDeathVerificationDesc;
    }

    public void setYouthDeathVerificationDesc(String youthDeathVerificationDesc)
    {
	if ( this.youthDeathVerificationDesc == null 
			|| !this.youthDeathVerificationDesc.equals(youthDeathVerificationDesc) ) {
	    markModified();
	}
	this.youthDeathVerificationDesc = youthDeathVerificationDesc;
    }
    
    public Date getDeathDate()
    {
	fetch();
	return deathDate;
    }

    public void setDeathDate(Date deathDate)
    {
	if ( this.deathDate == null 
		|| this.deathDate != deathDate ) {
	    markModified();
	}
	this.deathDate = deathDate;
    }

    public int getDeathAge()
    {
	fetch();
	return deathAge;
    }

    public void setDeathAge(int deathAge)
    {
	this.deathAge = deathAge;
    }

    public String getSSNUpdateUser()
    {
	fetch();
	return SSNUpdateUser;
    }

    public void setSSNUpdateUser(String sSNUpdateUser)
    {
	if ( this.SSNUpdateUser == null 
		|| !this.SSNUpdateUser.equals(sSNUpdateUser) ) {
	    markModified();
	}
	SSNUpdateUser = sSNUpdateUser;
    }

    public Date getSSNUpdateDate()
    {
	fetch();
	return SSNUpdateDate;
    }

    public void setSSNUpdateDate(Date sSNUpdateDate)
    {
	if ( this.SSNUpdateDate == null 
		|| this.SSNUpdateDate != sSNUpdateDate ) {
	    markModified();
	}
	SSNUpdateDate = sSNUpdateDate;
    }

    public int getJuvExcludedReporting()
    {
	return juvExcludedReporting;
    }

    public void setJuvExcludedReporting(int juvExcludedReporting)
    {
	if ( this.juvExcludedReporting == 0
		|| this.juvExcludedReporting != juvExcludedReporting) {
	    markModified();
	}
	this.juvExcludedReporting = juvExcludedReporting;
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
    
    public String getZip()
    {
	fetch();
        return this.zip;
    }

    public void setZip(String zipCode)
    {
	if ( this.zip == null || !this.zip.equals(zipCode) ) {
            markModified();
        }
	
        this.zip = zipCode;
    }
    
    
 public String getLiveWithTjjd()
    {
     	fetch();
        return liveWithTjjd;
    }

    public void setLiveWithTjjd(String liveWithTjjd)
    {
	if ( this.liveWithTjjd == null || !this.liveWithTjjd.equals(liveWithTjjd) ) {
            markModified();
        }
        this.liveWithTjjd = liveWithTjjd;
    }
    
    public String getUpdateUser()
    {
	fetch();
	return this.updateUser;
    }
    
    public void setUpdateUser(String updateUser)
    {
	if ( this.updateUser == null || !this.updateUser.equals(updateUser) ) {
            markModified();
        }
	this.updateUser = updateUser;
    }
    
    public Date getUpdateDate()
    {
	fetch();
	return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate)
    {
	if ( this.updateDate == null || !this.updateDate.equals(updateDate) ) {
            markModified();
        }
	this.updateDate = updateDate;
    }
    
    

/**
  * 
  * @return JuvenileCoreLightResponseEvent
  */
    public JuvenileCoreLightResponseEvent valueObject(){
	
	JuvenileCoreLightResponseEvent response = new JuvenileCoreLightResponseEvent();
	
	response.setAddressId(this.getAddressId());
	response.setDateOfBirth(this.getDateOfBirth());
	response.setFirstName(this.getFirstName());
	response.setHispanicInd(this.getHispanicInd());
	response.setJuvAddress(this.getJuvAddress());
	response.setJuvCounty(this.getJuvCounty());
	response.setJuvenileType(this.getJuvenileType());
	response.setJuvSchoolDist(this.getJuvSchoolDist());
	response.setJuvSchoolName(this.getJuvSchoolName());
	response.setLastName(this.getLastName());
	response.setMiddleName(this.getMiddleName());
	response.setNameSuffix(this.getNameSuffix());
	response.setOriginalRace(this.getOriginalRace());
	response.setOriginalRaceId(this.getOriginalRaceId());
	response.setPreferredFirstName(this.getPreferredFirstName());
	response.setPropointJPOpIdId(this.getPropointJPOpIdId());
	response.setRace(this.getRace());
	response.setRaceId(this.getRaceId());
	response.setRecType(this.getRecType());	
	response.setCreateDate(this.getCreateDate());
	
	return response;
    }

}
