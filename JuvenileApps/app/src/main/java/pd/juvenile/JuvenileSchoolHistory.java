package pd.juvenile;

import java.util.Date;
import java.util.Iterator;

import messaging.juvenile.GetJuvenileSchoolEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.address.Address;
import pd.codetable.Code;
import pd.codetable.person.JuvenileSchoolDistrictCode;

public class JuvenileSchoolHistory extends PersistentObject implements Comparable<JuvenileSchoolHistory>
{
    /**
     * 
     */
    private static final long serialVersionUID = 5L;

    private String gradeLevelId;

    private String appropriateLevelId;

    private String schoolId;

    private String homeSchoolId;

    private Date lastAttendedDate;

    /**
     * School exit type
     * 
     * @referencedType pd.codetable.Code
     * @contextKey JUVENILE_SCHOOL_EXIT_TYPE
     * @detailerDoNotGenerate false
     */
    public Code exitType = null;

    private String juvenileNum;

    /**
     * appropriate grade level
     * 
     * @referencedType pd.codetable.Code
     * @contextKey APPROPRIATE_GRADE_LEVEL
     * @detailerDoNotGenerate false
     */
    private Code appropriateLevel;

    /**
     * Properties for school
     * 
     * @referencedType pd.codetable.person.JuvenileSchoolDistrictCode
     * @detailerDoNotGenerate false
     */
    private JuvenileSchoolDistrictCode school = null;

    /**
     * Properties for homeSchool
     * 
     * @referencedType pd.codetable.person.JuvenileSchoolDistrictCode
     * @detailerDoNotGenerate false
     */
    private JuvenileSchoolDistrictCode homeSchool = null;

    private String schoolHistoryId;

    /**
     * appropriate grade level
     * 
     * @referencedType pd.codetable.Code
     * @contextKey GRADE_LEVEL
     * @detailerDoNotGenerate false
     */
    private Code gradeLevel;
    private String gradeChangeReason;

    private String schoolDistrictId;

    private String homeSchoolDistrictId;

    private String teaSchoolNumber;

    private String exitTypeId;

    private Date createDate;

    private Date verifiedDate;

    private Date eligibilityEnrollmentDate; //Changes JIMS200077279 : Added for MJCW:  Add Eligible for Enrollment Dte to TEAt(UI)

    private String gradeAverage;

    private String gradesRepeatNumber;

    private Code gradesRepeated;

    private String gradesRepeatedId;
    private int gradeRepeatTotal;

    private Code participation;

    private String participationId;

    private Code programAttending;

    private String programAttendingId;

    private Code splEduCategory; //added for spl edu 

    private String splEduCategoryId; //added for spl edu 

    private Code ruleInfraction;

    private String ruleInfractionId;

    private Code schoolAttendanceStatus;

    private String schoolAttendanceStatusId;

    private String truancyHistory;

    private String specifiedSchoolName;

    private String addressId;

    //added for Bug 30616

    private Address specifiedSchoolAddress;
    
    private String educationService;
    
    private String academicPerformance;
    private String schoolInfoVerifiedBy;
    private boolean truancy;
    private String verifiedByOther;
    
    //GED Information
    private boolean gedAwarded;	
    private Date   gedAwardedDate;
    private Date   completionDate;
    private boolean gedCompleted;

    
    
    /**
     * @roseuid 42B062E7022B
     */
    public JuvenileSchoolHistory()
    {
    }

    /**
     * Access method for the juvenileNumber property.
     * 
     * @return the current value of the juvenileNumber property
     */
    public String getJuvenileNum()
    {
	fetch();
	return juvenileNum;
    }

    /**
     * Access method for the teaSchoolNumber property.
     * 
     * @return the current value of the teaSchoolNumber property
     */
    public String getTeaSchoolNumber()
    {
	fetch();
	return teaSchoolNumber;
    }

    /**
     * Sets the value of the teaSchoolNumber property.
     * 
     * @param aTeaSchoolNumber
     *            the new value of the teaSchoolNumber property
     */
    public void setTeaSchoolNumber(String aTeaSchoolNumber)
    {
	if (this.teaSchoolNumber == null || !this.teaSchoolNumber.equals(aTeaSchoolNumber))
	{
	    markModified();
	}
	teaSchoolNumber = aTeaSchoolNumber;
    }

    /**
     * Access method for the verifiedDate property.
     * 
     * @return the current value of the verifiedDate property
     */
    public Date getVerifiedDate()
    {
	fetch();
	return verifiedDate;
    }

    /**
     * Sets the value of the verifiedDate property.
     * 
     * @param aVerifiedDate
     *            the new value of the verifiedDate property
     */
    public void setVerifiedDate(Date aVerifiedDate)
    {
	if (this.verifiedDate == null || !this.verifiedDate.equals(aVerifiedDate))
	{
	    markModified();
	}
	verifiedDate = aVerifiedDate;
    }

    /**
     * Access method for the lastAttendedDate property.
     * 
     * @return the current value of the lastAttendedDate property
     */
    public Date getLastAttendedDate()
    {
	fetch();
	return lastAttendedDate;
    }

    /**
     * Sets the value of the lastAttendedDate property.
     * 
     * @param aLastAttendedDate
     *            the new value of the lastAttendedDate property
     */
    public void setLastAttendedDate(Date aLastAttendedDate)
    {
	if (this.lastAttendedDate == null || !this.lastAttendedDate.equals(aLastAttendedDate))
	{
	    markModified();
	}
	lastAttendedDate = aLastAttendedDate;
    }

    /**
     * Access method for the gradeLevel property.
     * 
     * @return the current value of the gradeLevel property
     */
    public Code getGradeLevel()
    {
	fetch();
	this.initGradeLevel();
	return gradeLevel;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initGradeLevel()
    {

	if (gradeLevel == null)
	{
	    try
	    {
		gradeLevel = (Code) new mojo.km.persistence.Reference(gradeLevelId, Code.class, "GRADE_LEVEL").getObject();
	    }
	    catch (Throwable t)
	    {
	    }
	}
    }

    /**
     * Sets the value of the gradeLevel property.
     * 
     * @param aGradeLevel
     *            the new value of the gradeLevel property
     */
    public void setGradeLevel(Code aGradeLevel)
    {
	if (this.gradeLevel == null || !this.gradeLevel.equals(aGradeLevel))
	{
	    markModified();
	}
	gradeLevel = aGradeLevel;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initAppropriateLevel()
    {
	if (appropriateLevel == null)
	{
	    try
	    {
		appropriateLevel = (Code) new mojo.km.persistence.Reference(appropriateLevelId, Code.class, "APPROPRIATE_GRADE_LEVEL").getObject();
	    }
	    catch (Throwable t)
	    {
	    }
	}
    }

    /**
     * Access method for the appropriateLevel property.
     * 
     * @return the current value of the appropriateLevel property
     */
    public Code getAppropriateLevel()
    {
	fetch();
	this.initAppropriateLevel();
	return appropriateLevel;
    }

    /**
     * Sets the value of the appropriateLevel property.
     * 
     * @param aAppropriateLevel
     *            the new value of the appropriateLevel property
     */
    public void setAppropriateLevel(Code aAppropriateLevel)
    {
	if (this.appropriateLevel == null || !this.appropriateLevel.equals(aAppropriateLevel))
	{
	    markModified();
	}
	appropriateLevel = aAppropriateLevel;
    }

    /**
     * Access method for the createDate property.
     * 
     * @return the current value of the createDate property
     */
    public Date getCreateDate()
    {
	fetch();
	return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param aCreateDate
     *            the new value of the createDate property
     */
    public void setCreateDate(Date aCreateDate)
    {
	if (this.createDate == null || !this.createDate.equals(aCreateDate))
	{
	    markModified();
	}
	createDate = aCreateDate;
    }

    /**
     * @return
     * @param JuvenileSchoolHistory
     */
    static public JuvenileSchoolHistory find(String schoolHistoryId)
    {
	return (JuvenileSchoolHistory) new Home().find(schoolHistoryId, JuvenileSchoolHistory.class);
    }

    /**
     * @return
     * @param schoolEvent
     */
    static public Iterator findJuvenileSchoolHistory(GetJuvenileSchoolEvent schoolEvent)
    {
	IHome home = new Home();
	return home.findAll(schoolEvent, JuvenileSchoolHistory.class);
    }

    /**
     * @return
     */
    public JuvenileSchoolHistoryResponseEvent getValueObject()
    {
	JuvenileSchoolHistoryResponseEvent event = new JuvenileSchoolHistoryResponseEvent();
	event.setSchoolHistoryId(this.getSchoolHistoryId());
	Code appropriateLevel = this.getAppropriateLevel();
	if (appropriateLevel != null)
	{
	    event.setAppropriateLevelCode(appropriateLevel.getCode());
	    event.setAppropriateLevelDescription(appropriateLevel.getDescription());
	}
	event.setTEASchoolNumber(this.getTeaSchoolNumber());
	event.setLastAttendedDate(this.getLastAttendedDate());
	Code gradeLevel = this.getGradeLevel();
	if (gradeLevel != null)
	{
	    event.setGradeLevelCode(gradeLevel.getCode());
	    event.setGradeLevelDescription(gradeLevel.getDescription());
	}
	event.setCreateDate(this.getCreateDate());
	Code exitType = this.getExitType();
	if (exitType != null)
	{
	    event.setExitTypeCode(exitType.getCode());
	    event.setExitTypeDescription(exitType.getDescription());
	}
	event.setJuvenileNum(this.getJuvenileNum());

	JuvenileSchoolDistrictCode schoolCode = this.getSchool();

	if (schoolCode != null)
	{
	    event.setSchoolDistrictId(schoolCode.getDistrictCode());
	    event.setSchoolDistrict(schoolCode.getDistrictDescription());
	    event.setSchoolId(schoolCode.getSchoolCode());
	    event.setSchool(schoolCode.getSchoolDescription());
	    event.setSchoolStreetName(schoolCode.getStreetName());//bug fix: 11239 changes 
	    event.setSchoolCity(schoolCode.getCity());
	    event.setSchoolState(schoolCode.getState());
	    event.setSchoolZip(schoolCode.getZip());
	    event.setSchoolPhone(schoolCode.getPhoneNum());
	    event.setInstructionType(schoolCode.getInstructionType());
	}

	schoolCode = this.getHomeSchool();
	if (schoolCode != null)
	{
	    event.setHomeSchoolDistrictId(schoolCode.getDistrictCode());
	    event.setHomeSchoolDistrict(schoolCode.getDistrictDescription());
	    event.setHomeSchoolId(schoolCode.getSchoolCode());
	    event.setHomeSchool(schoolCode.getSchoolDescription());
	}

	event.setVerifiedDate(this.getVerifiedDate());
	event.setEligibilityEnrollmentDate(this.getEligibilityEnrollmentDate());

	event.setGradeAverage(this.getGradeAverage());
	event.setGradesRepeatNumber(this.getGradesRepeatNumber());

	event.setGradesRepeatedCode(this.getGradesRepeatedId());
	Code gradesRepeated = this.getGradesRepeated();
	if (gradesRepeated != null)
	{
	    event.setGradesRepeatedDescription(gradesRepeated.getDescription());
	}
	event.setGradeRepeatTotal(this.getGradeRepeatTotal());
	event.setGradeChangeReason(this.gradeChangeReason);

	event.setParticipationCode(this.getParticipationId());
	Code participation = this.getParticipation();
	if (participation != null)
	{
	    event.setParticipationDescription(participation.getDescription());
	}

	event.setProgramAttendingCode(this.getProgramAttendingId());
	Code programAttending = this.getProgramAttending();
	if (programAttending != null)
	{
	    event.setProgramAttendingDescription(programAttending.getDescription());
	}

	event.setSplEduCategoryCode(this.getSplEduCategoryId());
	Code splEduCategory = this.getSplEduCategory();
	if (splEduCategory != null)
	{
	    event.setSplEduCategoryDescription(programAttending.getDescription());
	}

	event.setRuleInfractionCode(this.getRuleInfractionId());
	Code ruleInfraction = this.getRuleInfraction();
	if (ruleInfraction != null)
	{
	    event.setRuleInfractionDescription(ruleInfraction.getDescription());
	}

	event.setSchoolAttendanceStatusCode(this.getSchoolAttendanceStatusId());
	Code schoolAttendanceStatus = this.getSchoolAttendanceStatus();
	if (schoolAttendanceStatus != null)
	{
	    event.setSchoolAttendanceStatusDescription(schoolAttendanceStatus.getDescription());
	}

	event.setEducationService( this.getEducationService() );
	
	
	event.setTruancyHistory(this.getTruancyHistory());
	event.setAcademicPerformance(this.getAcademicPerformance());
	event.setSchoolInfoVerifiedBy(this.getSchoolInfoVerifiedBy());
	event.setTruancy(this.getTruancy());
	event.setVerifiedByOther(this.getVerifiedByOther());
	
	//GED Information
	event.setGedCompleted(this.isGedCompleted());
	event.setCompletionDate(this.getCompletionDate());
	event.setGedAwarded(this.isGedAwarded());
	event.setGedAwardedDate(this.getGedAwardedDate());
	
	event.setCreateUser(this.getCreateUserID());
	if (this.getCreateTimestamp() != null)
	{
	    event.setCreateDate(new Date(this.getCreateTimestamp().getTime()));
	}
	if (this.getUpdateUserID() != null)
	{
	    event.setUpdateUser(this.getUpdateUserID());
	}
	if (this.getCreateTimestamp() != null)
	{
	    event.setCreateDate(new Date(this.getCreateTimestamp().getTime()));
	}
	if (this.getUpdateTimestamp() != null)
	{
	    event.setUpdateDate(new Date(this.getUpdateTimestamp().getTime()));
	}
	if (this.getCreateJIMS2UserID() != null)
	{
	    event.setCreateJims2User(this.getCreateJIMS2UserID());
	}
	if (this.getUpdateJIMS2UserID() != null)
	{
	    event.setUpdateJims2User(this.getUpdateJIMS2UserID());
	}
	//added for Bug 30616
	event.setSpecificSchoolName(this.specifiedSchoolName);
	if (this.specifiedSchoolName != null && !"".equals(this.specifiedSchoolName) && !"UNKNOWN".equalsIgnoreCase(this.specifiedSchoolName))
	{
	    if (this.addressId != null)
	    {
		Address addr = Address.find(new Integer(this.addressId).intValue());
		if (addr != null)
		{
		    event.setSchoolStreetNum(addr.getStreetNum());
		    event.setSchoolStreetName(addr.getStreetName());//bug fix: 11239 changes 
		    event.setSchoolCity(addr.getCity());
		    event.setSchoolState(addr.getStateId());
		    event.setSchoolZip(addr.getZipCode());
		    event.setSchoolZipCodeExt(addr.getAdditionalZipCode());
		}
	    }
	}
	return event;
    }

    /**
     * @return
     */
    private String getSchoolHistoryId()
    {
	String schoolHistoryId = null;
	Object schoolHistoryObj = super.getOID();
	if (schoolHistoryObj != null)
	{
	    schoolHistoryId = (String) schoolHistoryObj;
	}
	return schoolHistoryId;
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setExitTypeId(String exitTypeId)
    {
	if (this.exitTypeId == null || !this.exitTypeId.equals(exitTypeId))
	{
	    markModified();
	}
	exitType = null;
	this.exitTypeId = exitTypeId;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getExitTypeId()
    {
	fetch();
	return exitTypeId;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initExitType()
    {
	if (exitType == null)
	{
	    try
	    {
		exitType = (Code) new mojo.km.persistence.Reference(exitTypeId, Code.class, "JUVENILE_SCHOOL_EXIT_TYPE").getObject();
	    }
	    catch (Throwable t)
	    {
	    }
	}
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getExitType()
    {
	fetch();
	initExitType();
	return exitType;
    }

    /**
     * set the type reference for class member exitType
     */
    public void setExitType(Code exitType)
    {
	if (this.exitType == null || !this.exitType.equals(exitType))
	{
	    markModified();
	}
	if (exitType.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(exitType);
	}
	setExitTypeId("" + exitType.getOID());
	exitType.setContext("JUVENILE_SCHOOL_EXIT_TYPE");
	this.exitType = (Code) new mojo.km.persistence.Reference(exitType).getObject();
    }

    public void setSchoolHistoryId(String schoolHistoryId)
    {
	if (this.schoolHistoryId == null || !this.schoolHistoryId.equals(schoolHistoryId))
	{
	    markModified();
	}
	this.schoolHistoryId = schoolHistoryId;
    }

    /**
     * Get the reference value to class ::
     * pd.codetable.person.JuvenileSchoolDistrictCode
     */
    public String getSchoolId()
    {
	fetch();
	return schoolId;
    }

    /**
     * Get the reference value to class ::
     * pd.codetable.person.JuvenileSchoolDistrictCode
     */
    public String getHomeSchoolId()
    {
	fetch();
	return homeSchoolId;
    }

    /**
     * Initialize class relationship to class
     * pd.codetable.person.JuvenileSchoolDistrictCode
     */
    private void initSchool()
    {
	if (school == null)
	{
	    try
	    {
		if (schoolDistrictId != null || schoolId != null)
		{
		    int dCode = Integer.parseInt(schoolDistrictId);
		    int sCode = Integer.parseInt(schoolId);
		    String derivedSchoolOID = String.valueOf(dCode) + "-" + String.valueOf(sCode);
		    school = (JuvenileSchoolDistrictCode) new mojo.km.persistence.Reference(derivedSchoolOID, JuvenileSchoolDistrictCode.class).getObject();
		}
	    }
	    catch (Throwable t)
	    {
	    }
	}
    }

    /**
     * Initialize class relationship to class
     * pd.codetable.person.JuvenileSchoolDistrictCode
     */
    private void initHomeSchool()
    {
	if (homeSchool == null)
	{
	    try
	    {
		if ( homeSchoolDistrictId != null || homeSchoolId != null ){
		    	int dCode = Integer.parseInt(homeSchoolDistrictId);
			int sCode = Integer.parseInt(homeSchoolId);
			String derivedSchoolOID = String.valueOf(dCode) + "-" + String.valueOf(sCode);
			homeSchool = (JuvenileSchoolDistrictCode) new mojo.km.persistence.Reference(derivedSchoolOID,
									JuvenileSchoolDistrictCode.class).getObject();
		}		
	    }
	    catch (Throwable t)
	    {
	    }
	}
    }

    /**
     * Gets referenced type pd.codetable.person.JuvenileSchoolDistrictCode
     */
    public JuvenileSchoolDistrictCode getSchool()
    {
	//fetch();
	initSchool();
	return school;
    }

    /**
     * Gets referenced type pd.codetable.person.JuvenileSchoolDistrictCode
     */
    public JuvenileSchoolDistrictCode getHomeSchool()
    {
	//fetch();
	initHomeSchool();
	return homeSchool;
    }

    /**
     * set the type reference for class member school
     */
    public void setSchool(JuvenileSchoolDistrictCode school)
    {
	if (this.school == null || !this.school.equals(school))
	{
	    markModified();
	}
	if (school.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(school);
	}
	setSchoolHistoryId("" + school.getOID());
	this.school = (JuvenileSchoolDistrictCode) new mojo.km.persistence.Reference(school).getObject();
    }

    /**
     * set the type reference for class member school
     */
    public void setHomeSchool(JuvenileSchoolDistrictCode homeSchool)
    {
	if (this.homeSchool == null || !this.homeSchool.equals(homeSchool))
	{
	    markModified();
	}
	if (homeSchool.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(homeSchool);
	}
	setSchoolHistoryId("" + homeSchool.getOID());
	this.homeSchool = (JuvenileSchoolDistrictCode) new mojo.km.persistence.Reference(homeSchool).getObject();
    }

    /**
     * Set the reference value to class ::
     * pd.codetable.person.JuvenileSchoolDistrictCode
     */
    public void setSchoolDistrictId(String schoolDistrictId)
    {
	if (this.schoolDistrictId == null || !this.schoolDistrictId.equals(schoolDistrictId))
	{
	    markModified();
	}
	this.schoolDistrictId = schoolDistrictId;
    }

    /**
     * Set the reference value to class ::
     * pd.codetable.person.JuvenileSchoolDistrictCode
     */
    public void setHomeSchoolDistrictId(String homeSchoolDistrictId)
    {
	if (this.homeSchoolDistrictId == null || !this.homeSchoolDistrictId.equals(homeSchoolDistrictId))
	{
	    markModified();
	}
	this.homeSchoolDistrictId = homeSchoolDistrictId;
    }

    /**
     * @param string
     */
    public void setSpecifiedSchoolName(String specifiedSchoolName)
    {
	if (this.specifiedSchoolName == null || !this.specifiedSchoolName.equals(specifiedSchoolName))
	{
	    markModified();
	}
	this.specifiedSchoolName = specifiedSchoolName;
    }

    /**
     * @param string
     */
    public void setAddressId(String addressId)
    {
	if (this.addressId == null || !this.addressId.equals(addressId))
	{
	    markModified();
	}
	this.addressId = addressId;
    }

    /**
     * Get the reference value to class ::
     * pd.codetable.person.JuvenileSchoolDistrictCode
     */
    public String getSchoolDistrictId()
    {
	fetch();
	return schoolDistrictId;
    }

    /**
     * Get the reference value to class ::
     * pd.codetable.person.JuvenileSchoolDistrictCode
     */
    public String getHomeSchoolDistrictId()
    {
	fetch();
	return homeSchoolDistrictId;
    }

    /**
     * @return
     */
    public String getSpecifiedSchoolName()
    {
	fetch();
	return specifiedSchoolName;
    }

    /**
     * @return
     */
    public String getAddressId()
    {
	fetch();
	return addressId;
    }

    /**
     * Set the reference value to class :: pd.juvenile.Juvenile
     */
    public void setJuvenileNum(String juvenileNum)
    {
	if (this.juvenileNum == null || !this.juvenileNum.equals(juvenileNum))
	{
	    markModified();
	}
	this.juvenileNum = juvenileNum;
    }

    /**
     * Get the reference value to class :: pd.juvenile.Juvenile
     */
    public String getJuvenileId()
    {
	fetch();
	return juvenileNum;
    }

    /**
     * Set the reference value to class ::
     * pd.codetable.person.JuvenileSchoolDistrictCode
     */
    public void setSchoolId(String schoolId)
    {
	if (this.schoolId == null || !this.schoolId.equals(schoolId))
	{
	    markModified();
	}
	school = null;
	this.schoolId = schoolId;
    }

    /**
     * Set the reference value to class ::
     * pd.codetable.person.JuvenileSchoolDistrictCode
     */
    public void setHomeSchoolId(String homeSchoolId)
    {
	if (this.homeSchoolId == null || !this.homeSchoolId.equals(homeSchoolId))
	{
	    markModified();
	}
	homeSchool = null;
	this.homeSchoolId = homeSchoolId;
    }

    /**
     * @return
     */
    public String getAppropriateLevelId()
    {
	fetch();
	return appropriateLevelId;
    }

    /**
     * @param string
     */
    public void setAppropriateLevelId(String appropriateLevelId)
    {
	if (this.appropriateLevelId == null || !this.appropriateLevelId.equals(appropriateLevelId))
	{
	    markModified();
	}
	appropriateLevel = null;
	this.appropriateLevelId = appropriateLevelId;
    }

    /**
     * @return
     */
    public String getGradeLevelId()
    {
	fetch();
	return gradeLevelId;
    }

    /**
     * @param string
     */
    public void setGradeLevelId(String gradeLevelId)
    {
	if (this.gradeLevelId == null || !this.gradeLevelId.equals(gradeLevelId))
	{
	    markModified();
	}
	this.gradeLevel = null;
	this.gradeLevelId = gradeLevelId;

    }

    /**
     * @param saveEvent
     * No longer allowing updates
     */
 /*   public void hydrate(JuvenileSchoolHistoryResponseEvent schoolEvent)
    {
	
	
	this.setAppropriateLevelId(schoolEvent.getAppropriateLevelCode());
	this.setExitTypeId(schoolEvent.getExitTypeCode());
	this.setGradeLevelId(schoolEvent.getGradeLevelCode());
	this.setJuvenileNum(schoolEvent.getJuvenileNum());
	this.setLastAttendedDate(schoolEvent.getLastAttendedDate());
	this.setSchoolDistrictId(schoolEvent.getSchoolDistrictId());
	this.setSchoolId(schoolEvent.getSchoolId());
	this.setTeaSchoolNumber(schoolEvent.getTEASchoolNumber());
	this.setRuleInfractionId(schoolEvent.getRuleInfractionCode());
	this.setGradeAverage(schoolEvent.getGradeAverage());
	this.setGradesRepeatNumber(schoolEvent.getGradesRepeatNumber());
	this.setGradesRepeatedId(schoolEvent.getGradesRepeatedCode());
	if ( schoolEvent.getGradeChangeReason() != null  && (StringUtils.isEmpty( schoolEvent.getGradeChangeReason()))){
	    this.setGradeChangeReason(null);
	} else {
	    this.setGradeChangeReason(schoolEvent.getGradeChangeReason());
	}
	this.setParticipationId(schoolEvent.getParticipationCode());
	this.setProgramAttendingId(schoolEvent.getProgramAttendingCode());
	this.setSplEduCategoryId(schoolEvent.getSplEduCategoryCode());
	this.setSchoolAttendanceStatusId(schoolEvent.getSchoolAttendanceStatusCode());
	this.setVerifiedDate(schoolEvent.getVerifiedDate());
	this.setEligibilityEnrollmentDate(schoolEvent.getEligibilityEnrollmentDate());
	this.setTruancyHistory(schoolEvent.getTruancyHistory());
	this.setHomeSchoolDistrictId(schoolEvent.getHomeSchoolDistrictId());
	this.setHomeSchoolId(schoolEvent.getHomeSchoolId());
	this.setSpecifiedSchoolName(schoolEvent.getSpecificSchoolName());
	this.setAddressId(schoolEvent.getAddressId());
	this.setEducationService(schoolEvent.getEducationService());
    }*/

    public boolean checkAddressFields(JuvenileSchoolHistoryResponseEvent schoolEvent)
    {
	boolean result = false;
	if (schoolEvent.getSchoolStreetNum() == null)
	{
	    schoolEvent.setSchoolStreetNum("");
	}
	if (schoolEvent.getSchoolStreetName() == null)
	{
	    schoolEvent.setSchoolStreetName("");
	}
	if (schoolEvent.getSchoolCity() == null)
	{
	    schoolEvent.setSchoolCity("");
	}
	if (schoolEvent.getSchoolState() == null)
	{
	    schoolEvent.setSchoolState("");
	}
	if (schoolEvent.getSchoolZip() == null)
	{
	    schoolEvent.setSchoolZip("");
	}
	if (schoolEvent.getSchoolZipCodeExt() == null)
	{
	    schoolEvent.setSchoolZipCodeExt("");
	}
	StringBuffer address = new StringBuffer();
	address.append(schoolEvent.getSchoolStreetNum());
	address.append(schoolEvent.getSchoolStreetName());
	address.append(schoolEvent.getSchoolCity());
	address.append(schoolEvent.getSchoolState());
	address.append(schoolEvent.getSchoolZip());
	address.append(schoolEvent.getSchoolZipCodeExt());
	String fullAddress = address.toString();
	fullAddress = fullAddress.trim();
	if (fullAddress.length() > 0)
	{
	    result = true;
	}
	fullAddress = null;
	address = null;
	return result;
    }

    /**
     * @return
     */
    public String getGradeAverage()
    {
	fetch();
	return gradeAverage;
    }

    /**
     * @return
     */
    public String getGradesRepeatedId()
    {
	fetch();
	return gradesRepeatedId;
    }

    /**
     * @return
     */
    public String getGradesRepeatNumber()
    {
	fetch();
	return gradesRepeatNumber;
    }
    
    public int getGradeRepeatTotal()
    {
	fetch();
	return gradeRepeatTotal;
    }

    /**
     * @return
     */
    public String getParticipationId()
    {
	fetch();
	return participationId;
    }

    /**
     * @return
     */
    public String getProgramAttendingId()
    {
	fetch();
	return programAttendingId;
    }

    /**
     * @return
     */
    public String getSplEduCategoryId()
    {
	fetch();
	return splEduCategoryId;
    }

    /**
     * @return
     */
    public String getRuleInfractionId()
    {
	fetch();
	return ruleInfractionId;
    }

    /**
     * @return
     */
    public String getSchoolAttendanceStatusId()
    {
	fetch();
	return schoolAttendanceStatusId;
    }

    /**
     * @return
     */
    public String getTruancyHistory()
    {
	fetch();
	return truancyHistory;
    }

    /**
     * @param gradeAverage
     */
    public void setGradeAverage(String gradeAverage)
    {
	if (this.gradeAverage == null || !this.gradeAverage.equals(gradeAverage))
	{
	    markModified();
	}
	this.gradeAverage = gradeAverage;
    }

    /**
     * @param gradesRepeatedId
     */
    public void setGradesRepeatedId(String gradesRepeatedId)
    {
	if (this.gradesRepeatedId == null || !this.gradesRepeatedId.equals(gradesRepeatedId))
	{
	    markModified();
	}
	gradesRepeated = null;
	this.gradesRepeatedId = gradesRepeatedId;
    }

    /**
     * @param gradesRepeatNumber
     */
    public void setGradesRepeatNumber(String gradesRepeatNumber)
    {
	if (this.gradesRepeatNumber == null || !this.gradesRepeatNumber.equals(gradesRepeatNumber))
	{
	    markModified();
	}
	this.gradesRepeatNumber = gradesRepeatNumber;
    }
    
    public void setGradeRepeatTotal(int gradeRepeatTotal)
    {
	if(this.gradeRepeatTotal != gradeRepeatTotal){
	    markModified();
	}
	this.gradeRepeatTotal = gradeRepeatTotal;
    }

    /**
     * @param participationId
     */
    public void setParticipationId(String participationId)
    {
	if (this.participationId == null || !this.participationId.equals(participationId))
	{
	    markModified();
	}
	participation = null;
	this.participationId = participationId;
    }

    /**
     * @param programAttendingId
     */
    public void setProgramAttendingId(String programAttendingId)
    {
	if (this.programAttendingId == null || !this.programAttendingId.equals(programAttendingId))
	{
	    markModified();
	}
	programAttending = null;
	this.programAttendingId = programAttendingId;
    }

    /**
     * @param splEduCategoryId
     */
    public void setSplEduCategoryId(String splEduCategoryId)
    {
	if (this.splEduCategoryId == null || !this.splEduCategoryId.equals(splEduCategoryId))
	{
	    markModified();
	}
	splEduCategory = null;
	this.splEduCategoryId = splEduCategoryId;
    }

    /**
     * @param ruleInfractionId
     */
    public void setRuleInfractionId(String ruleInfractionId)
    {
	if (this.ruleInfractionId == null || !this.ruleInfractionId.equals(ruleInfractionId))
	{
	    markModified();
	}
	ruleInfraction = null;
	this.ruleInfractionId = ruleInfractionId;
    }

    /**
     * @param setSchoolAttendanceStatusId
     */
    public void setSchoolAttendanceStatusId(String schoolAttendanceStatusId)
    {
	if (this.schoolAttendanceStatusId == null || !this.schoolAttendanceStatusId.equals(schoolAttendanceStatusId))
	{
	    markModified();
	}
	schoolAttendanceStatus = null;
	this.schoolAttendanceStatusId = schoolAttendanceStatusId;
    }

    /**
     * @param truancyHistory
     */
    public void setTruancyHistory(String truancyHistory)
    {
	if (this.truancyHistory == null || !this.truancyHistory.equals(truancyHistory))
	{
	    markModified();
	}
	this.truancyHistory = truancyHistory;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initGradesRepeated()
    {
	if (gradesRepeated == null)
	{
	    try
	    {
		gradesRepeated = (Code) new mojo.km.persistence.Reference(gradesRepeatedId, Code.class, "GRADE_LEVEL").getObject();
	    }
	    catch (Throwable t)
	    {
	    }
	}
    }

    /**
     * @return
     */
    public Code getGradesRepeated()
    {
	fetch();
	initGradesRepeated();
	return gradesRepeated;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initParticipation()
    {
	if (participation == null)
	{
	    try
	    {
		participation = (Code) new mojo.km.persistence.Reference(participationId, Code.class, "SCHOOL_PARTICIPATION").getObject();
	    }
	    catch (Throwable t)
	    {
	    }
	}
    }

    /**
     * @return
     */
    public Code getParticipation()
    {
	fetch();
	initParticipation();
	return participation;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initProgramAttending()
    {
	if (programAttending == null)
	{
	    try
	    {
		programAttending = (Code) new mojo.km.persistence.Reference(programAttendingId, Code.class, "SCHOOL_PROGRAM").getObject();
	    }
	    catch (Throwable t)
	    {
	    }
	}

    }

    /**
     * @return
     */
    public Code getProgramAttending()
    {
	fetch();
	initProgramAttending();
	return programAttending;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initSplEduCategory()
    {
	if (splEduCategory == null)
	{
	    try
	    {
		splEduCategory = (Code) new mojo.km.persistence.Reference(splEduCategoryId, Code.class, "JUV_SPL_EDU_HANDICAPPING_COND").getObject();
	    }
	    catch (Throwable t)
	    {
	    }
	}

    }

    /**
     * @return
     */
    public Code getSplEduCategory()
    {
	fetch();
	initSplEduCategory();
	return splEduCategory;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initRuleInfraction()
    {
	if (ruleInfraction == null)
	{
	    try
	    {
		ruleInfraction = (Code) new mojo.km.persistence.Reference(ruleInfractionId, Code.class, "SCHOOL_RULE_INFRACTION").getObject();
	    }
	    catch (Throwable t)
	    {
	    }
	}

    }

    /**
     * @return
     */
    public Code getRuleInfraction()
    {
	fetch();
	initRuleInfraction();
	return ruleInfraction;
    }

    /**
     * @return
     */
    public Code getSchoolAttendanceStatus()
    {
	fetch();
	initSchoolAttendanceStatus();
	return schoolAttendanceStatus;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initSchoolAttendanceStatus()
    {
	if (schoolAttendanceStatus == null)
	{
	    try
	    {
		schoolAttendanceStatus = (Code) new mojo.km.persistence.Reference(schoolAttendanceStatusId, Code.class, "SCHOOL_ATTENDANCE_STATUS").getObject();
	    }
	    catch (Throwable t)
	    {
	    }
	}
    }

    /**
     * set the type reference for class member gradesRepeated
     */
    public void setGradesRepeated(Code gradesRepeated)
    {
	if (this.gradesRepeated == null || !this.gradesRepeated.equals(gradesRepeated))
	{
	    markModified();
	}
	if (gradesRepeated.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(gradesRepeated);
	}
	setGradesRepeatedId("" + gradesRepeated.getOID());
	gradesRepeated.setContext("GRADE_LEVEL");
	this.gradesRepeated = (Code) new mojo.km.persistence.Reference(gradesRepeated).getObject();

	this.gradesRepeated = gradesRepeated;
    }

    /**
     * set the type reference for class member participation
     */
    public void setParticipation(Code participation)
    {
	if (this.participation == null || !this.participation.equals(participation))
	{
	    markModified();
	}
	if (participation.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(participation);
	}
	setParticipationId("" + participation.getOID());
	participation.setContext("SCHOOL_PARTICIPATION");
	this.participation = (Code) new mojo.km.persistence.Reference(participation).getObject();

    }

    /**
     * set the type reference for class member programAttending
     */
    public void setProgramAttending(Code programAttending)
    {
	if (this.programAttending == null || !this.programAttending.equals(programAttending))
	{
	    markModified();
	}
	if (programAttending.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(programAttending);
	}
	setProgramAttendingId("" + programAttending.getOID());
	programAttending.setContext("SCHOOL_PROGRAM");
	this.programAttending = (Code) new mojo.km.persistence.Reference(programAttending).getObject();

    }

    /**
     * set the type reference for class member splEduCategory
     */
    public void setsplEduCategory(Code splEduCategory)
    {
	if (this.splEduCategory == null || !this.splEduCategory.equals(splEduCategory))
	{
	    markModified();
	}
	if (splEduCategory.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(splEduCategory);
	}
	setProgramAttendingId("" + splEduCategory.getOID());
	splEduCategory.setContext("JUV_SPL_EDU_HANDICAPPING_COND");
	this.splEduCategory = (Code) new mojo.km.persistence.Reference(splEduCategory).getObject();

    }

    /**
     * set the type reference for class member ruleInfraction
     */
    public void setRuleInfraction(Code ruleInfraction)
    {
	if (this.ruleInfraction == null || !this.ruleInfraction.equals(ruleInfraction))
	{
	    markModified();
	}
	if (ruleInfraction.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(ruleInfraction);
	}
	setRuleInfractionId("" + ruleInfraction.getOID());
	ruleInfraction.setContext("SCHOOL_RULE_INFRACTION");
	this.ruleInfraction = (Code) new mojo.km.persistence.Reference(ruleInfraction).getObject();
    }

    /**
     * set the type reference for class member school
     */
    public void setSchoolAttendanceStatus(Code schoolAttendanceStatus)
    {
	if (this.schoolAttendanceStatus == null || !this.schoolAttendanceStatus.equals(schoolAttendanceStatus))
	{
	    markModified();
	}
	if (schoolAttendanceStatus.getOID() == null)
	{
	    new mojo.km.persistence.Home().bind(schoolAttendanceStatus);
	}
	setSchoolAttendanceStatusId("" + schoolAttendanceStatus.getOID());
	schoolAttendanceStatus.setContext("SCHOOL_ATTENDANCE_STATUS");
	this.schoolAttendanceStatus = (Code) new mojo.km.persistence.Reference(schoolAttendanceStatus).getObject();
    }

    /**
     * @param eligibilityEnrollmentDate
     *            the eligibilityEnrollmentDate to set
     */
    public void setEligibilityEnrollmentDate(Date eligibilityEnrollmentDate)
    {
	if (this.eligibilityEnrollmentDate == null || !this.eligibilityEnrollmentDate.equals(eligibilityEnrollmentDate))
	{
	    markModified();
	}
	this.eligibilityEnrollmentDate = eligibilityEnrollmentDate;
    }

    /**
     * @return the eligibilityEnrollmentDate
     */
    public Date getEligibilityEnrollmentDate()
    {
	fetch();
	return eligibilityEnrollmentDate;
    }

    /**
     * Gets referenced type pd.address.Address
     */
    public Address getSpecifiedSchoolAddress()
    {
	fetch();
	initSpecifiedSchoolAddress();
	return specifiedSchoolAddress;
    }

    /**
     * Initialize class relationship to class pd.address.Address
     */
    private void initSpecifiedSchoolAddress()
    {
	if (specifiedSchoolAddress == null)
	{
	    try
	    {
		if (addressId != null)
		{
		    specifiedSchoolAddress = (Address) new mojo.km.persistence.Reference(addressId, Address.class, (mojo.km.persistence.PersistentObject) this, "specifiedSchoolAddress").getObject();
		}
	    }
	    catch (Throwable t)
	    {
		specifiedSchoolAddress = null;
	    }
	}
    }

    public String getEducationService()
    {
	return educationService;
    }

    public void setEducationService(String educationService)
    {
	this.educationService = educationService;
    }

    public String getGradeChangeReason()
    {
	return gradeChangeReason;
    }

    public void setGradeChangeReason(String gradeChangeReason)
    {
	this.gradeChangeReason = gradeChangeReason;
    }
    
    
    public String getAcademicPerformance()
    {
	fetch();
        return academicPerformance;
    }

    public void setAcademicPerformance(String academicPerformance)
    {
	if (this.academicPerformance == null || !this.academicPerformance.equals(academicPerformance))
	{
	    markModified();
	}
        this.academicPerformance = academicPerformance;
    }

    public String getSchoolInfoVerifiedBy()
    {
	fetch();
        return schoolInfoVerifiedBy;
    }

    public void setSchoolInfoVerifiedBy(String schoolInfoVerifiedBy)
    {
	if (this.schoolInfoVerifiedBy == null || !this.schoolInfoVerifiedBy.equals(schoolInfoVerifiedBy))
	{
	    markModified();
	}
        this.schoolInfoVerifiedBy = schoolInfoVerifiedBy;
    }

    public boolean getTruancy()
    {
	fetch();
        return truancy;
    }

    public void setTruancy(boolean truancy)
    {
	if(this.truancy != truancy)
	{
	    markModified();
	}
        this.truancy = truancy;
    }

    public String getVerifiedByOther()
    {
	fetch();
        return verifiedByOther;
    }

    public void setVerifiedByOther(String verifiedByOther)
    {
	if (this.verifiedByOther == null || !this.verifiedByOther.equals(verifiedByOther))
	{
	    markModified();
	}
        this.verifiedByOther = verifiedByOther;
    }

    public boolean isGedAwarded()
    {
	fetch();
        return gedAwarded;
    }

    public void setGedAwarded(boolean gedAwarded)
    {
	if(this.gedAwarded != gedAwarded)
	{
	    markModified();
	}
        this.gedAwarded = gedAwarded;
    }

    public Date getGedAwardedDate()
    {
	fetch();
        return gedAwardedDate;
    }

    public void setGedAwardedDate(Date gedAwardedDate)
    {
	if (this.gedAwardedDate == null || !this.gedAwardedDate.equals(gedAwardedDate))
	{
	    markModified();
	}
        this.gedAwardedDate = gedAwardedDate;
    }

    public Date getCompletionDate()
    {
	fetch();
        return completionDate;
    }

    public void setCompletionDate(Date completionDate)
    {
        this.completionDate = completionDate;
    }

    public boolean isGedCompleted()
    {
	fetch();
        return gedCompleted;
    }

    public void setGedCompleted(boolean gedCompleted)
    {
	if(this.gedCompleted != gedCompleted)
	{
	    markModified();
	}
        this.gedCompleted = gedCompleted;
    }

    @Override public int compareTo(JuvenileSchoolHistory sh){
	
	if(this.createDate != null && sh != null && sh.createDate != null){
	    if(this.createDate.after(sh.createDate)){
		   return 1;
	    } else if(this.createDate.before(sh.createDate)){
		   return -1;
	    }
	}
	
	
	return 0;
    }
}
