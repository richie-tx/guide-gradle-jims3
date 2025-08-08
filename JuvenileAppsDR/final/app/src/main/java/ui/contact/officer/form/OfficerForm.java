/*
\ * Created on Aug 9, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.contact.officer.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import messaging.contact.officer.reply.OfficerTrainingResponseEvent;
import messaging.contact.officer.reply.TrainingTopicsResponseEvent;
import mojo.km.utilities.MessageUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author dwilliamson To change the template for this generated type comment go
 *         to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 *         Comments
 */

public class OfficerForm extends ActionForm
{
    private String agencyId;
    private String assignedArea;
    private String badgeNum;
    private String badgeNumPrompt;
    private String departmentId;
    private String departmentIdPrompt;
    private String departmentName;
    private String departmentNamePrompt;
    private String divisionName;
    private String extn;
    private String juvLocationId;
    private String juvLocation;
    private String juvUnitId;
    private String juvUnit;
    private String logonId;
    private String logonIdPrompt;
    private String managerId;
    private String newManagerId;
    private String managerProfilesSize;
    private String managerStatus;
    private String numberViewable;
    private String officerProfileId;
    private String officerRadioNum;
    private String officerTypeId;
    private String officerType;
    private String officerSubType;
    private String officerSubTypeId;
    private String originalBadgeNumber;
    private String originalOtherIdNumber;
    private String originalUserId;
    private String otherIdNum;
    private String otherIdNumPrompt;
    private String radioNumber;
    private String rankId;
    private String rank;
    private String selectedDepartment;
    private String selectedUserProfile;
    private String selectedManager;
    private String ssn;
    private String ssn1;
    private String ssn2;
    private String ssn3;
    private String status;
    private String statusId;
    private String userId;
    private String userIdPrompt;
    private String workShift;
    private String workExtension;

    //  Name Information	
    private String firstName;
    private String firstNamePrompt;
    private String lastName;
    private String lastNamePrompt;
    private String middleName;
    private String managerMiddleName;
    private String managerFirstName;
    private String managerLastName;
    private String newManagerMiddleName;
    private String newManagerFirstName;
    private String newManagerLastName;

    //	Contact Information
    private String homePhone;
    private String homePhoneAreaCode;
    private String homePhonePrefix;
    private String homePhoneMain;
    private String workPhone;
    private String workPhoneAreaCode;
    private String workPhonePrefix;
    private String workPhoneMain;
    private String cellPhone;
    private String cellPhoneAreaCode;
    private String cellPhonePrefix;
    private String cellPhoneMain;
    private String pager;
    private String pagerAreaCode;
    private String pagerPrefix;
    private String pagerMain;
    private String fax;
    private String faxAreaCode;
    private String faxPrefix;
    private String faxMain;
    private String faxLocation;
    private String email;
    private String updatableStatus;
    private String deletableStatus;
    private String limitedUpdatableStatus;

    private boolean isSA = false;
    private boolean isASA = false;
    private boolean isMA = false;
    private boolean isOfficer = false;

    private boolean isClearCheckBoxes = false;

    // Address Information
    private String streetName;
    private String streetNumber;
    private String streetTypeId;
    private String streetType;
    private String apartmentNumber;
    private String city;
    private String state;
    private String stateId;
    private String zipCode;
    private String additionalZipCode;

    // Work Schedule Informaton
    // sunday = 0
    private String startTime0;
    private String endTime0;
    private String dayOff0;
    private String startTimeId0;
    private String endTimeId0;
    private String workDayId0;
    private String workScheduleId0;

    // monday = 1
    private String startTime1;
    private String endTime1;
    private String dayOff1;
    private String startTimeId1;
    private String endTimeId1;
    private String workDayId1;
    private String workScheduleId1;

    // tuesday = 2
    private String startTime2;
    private String endTime2;
    private String dayOff2;
    private String startTimeId2;
    private String endTimeId2;
    private String workDayId2;
    private String workScheduleId2;

    // wednesday = 3
    private String startTime3;
    private String endTime3;
    private String dayOff3;
    private String startTimeId3;
    private String endTimeId3;
    private String workDayId3;
    private String workScheduleId3;

    // Thursday = 4
    private String startTime4;
    private String endTime4;
    private String dayOff4;
    private String startTimeId4;
    private String endTimeId4;
    private String workDayId4;
    private String workScheduleId4;

    // Friday = 4
    private String startTime5;
    private String endTime5;
    private String dayOff5;
    private String startTimeId5;
    private String endTimeId5;
    private String workDayId5;
    private String workScheduleId5;

    // Saturday = 6
    private String startTime6;
    private String endTime6;
    private String dayOff6;
    private String startTimeId6;
    private String endTimeId6;
    private String workDayId6;
    private String workScheduleId6;

    //	non-user entry fields		
    private String action;

    // Collection
    private Collection departments;
    private Collection managerProfiles;
    private List officerProfiles;
    private Collection userProfiles;
    private Collection workScedules;
    private String[] selectedUserIds;
    private List selectedList;

    // Collection for drop downlist
    private Collection officerTypes;
    private Collection juvLocations;
    private Collection juvUnits;
    private Collection ranks;
    private Collection streetTypes;
    private Collection states;
    private Collection workDays;
    private Collection officerSubTypes;
    private Collection officerStatuses;

    // Officer search type
    private String requestOrigin;
    private String officerSearchType;
    
    // Officer Training
    private Collection<OfficerTrainingResponseEvent> officerTraining;
    private Collection<TrainingTopicsResponseEvent> trainingTopics;
    private String trainingTopicId;
    private String trainingBegDate;
    private String trainingEndDate;
    private double trainingHours;
    private String survey;
    private String supervisor;

    /**
     * Clear Form
     */
    public void clear()
    {
	assignedArea = "";
	badgeNum = "";
	departmentId = "";
	departmentName = "";
	divisionName = "";
	extn = "";
	juvLocationId = "";
	juvUnitId = "";
	logonId = "";
	logonIdPrompt = "";
	managerId = "";
	newManagerId = "";
	managerStatus = "";
	officerProfileId = "";
	officerRadioNum = "";
	officerTypeId = "";
	officerSubTypeId = "";
	officerSubType = "";
	otherIdNum = "";
	numberViewable = "";
	radioNumber = "";
	rankId = "";
	ssn = "";
	ssn1 = "";
	ssn2 = "";
	ssn3 = "";
	status = "";
	userId = "";
	workShift = "";
	this.originalBadgeNumber = "";
	this.originalUserId = "";
	originalOtherIdNumber = "";

	// Contact Information
	homePhone = "";
	homePhoneAreaCode = "";
	homePhoneMain = "";
	homePhonePrefix = "";
	workPhone = "";
	workPhoneAreaCode = "";
	workPhoneMain = "";
	workPhonePrefix = "";
	extn = "";
	workExtension = "";
	cellPhone = "";
	cellPhoneAreaCode = "";
	cellPhoneMain = "";
	cellPhonePrefix = "";
	pager = "";
	pagerAreaCode = "";
	pagerMain = "";
	pagerPrefix = "";
	fax = "";
	faxAreaCode = "";
	faxMain = "";
	faxPrefix = "";
	faxLocation = "";
	email = "";

	// Address
	streetNumber = "";
	streetName = "";
	streetTypeId = "";
	apartmentNumber = "";
	city = "";
	stateId = "";
	zipCode = "";
	additionalZipCode = "";

	// Name Information
	firstName = "";
	lastName = "";
	middleName = "";
	managerMiddleName = "";
	managerFirstName = "";
	managerLastName = "";
	newManagerMiddleName = "";
	newManagerFirstName = "";
	newManagerLastName = "";

	// Work Schedule Information
	startTime0 = "";
	startTimeId0 = "";
	endTimeId0 = "";
	endTime0 = "";
	dayOff0 = "N";
	workScheduleId0 = "";
	startTime1 = "";
	startTimeId1 = "";
	endTimeId1 = "";
	endTime1 = "";
	dayOff1 = "N";
	workScheduleId1 = "";
	startTime2 = "";
	startTimeId2 = "";
	endTimeId2 = "";
	endTime2 = "";
	dayOff2 = "N";
	workScheduleId2 = "";
	startTime3 = "";
	startTimeId3 = "";
	endTimeId3 = "";
	endTime3 = "";
	dayOff3 = "N";
	workScheduleId3 = "";
	startTime4 = "";
	startTimeId4 = "";
	endTimeId4 = "";
	endTime4 = "";
	dayOff4 = "N";
	workScheduleId4 = "";
	startTime5 = "";
	startTimeId5 = "";
	endTimeId5 = "";
	endTime5 = "";
	dayOff5 = "N";
	workScheduleId5 = "";
	startTime6 = "";
	startTimeId6 = "";
	endTimeId6 = "";
	endTime6 = "";
	dayOff6 = "N";
	workScheduleId6 = "";
	userIdPrompt = "";
	lastNamePrompt = "";
	firstNamePrompt = "";
	departmentNamePrompt = "";
	badgeNumPrompt = "";
	otherIdNumPrompt = "";
	departmentIdPrompt = "";
	managerProfilesSize = "";
	selectedDepartment = "";
	selectedManager = "";
	agencyId = "";
	updatableStatus = "";
	deletableStatus = "";
	limitedUpdatableStatus = "";

	Collection emptyColl = new ArrayList();
	emptyColl = MessageUtil.processEmptyCollection(emptyColl);
	officerProfiles = (List) emptyColl;
	selectedList = (List) emptyColl;
	selectedUserIds = null;
	this.setDepartments(emptyColl);
	this.setManagerProfiles(emptyColl);
	this.setUserProfiles(emptyColl);
	this.setWorkScedules(emptyColl);
	
	//Officer Training clear
	this.trainingBegDate = null;
	this.trainingEndDate = null;
	this.trainingHours = 0.0;
	this.setTrainingTopicId("");
	this.setTrainingTopics(emptyColl);
	this.setOfficerTraining(emptyColl);

	// Officer search type
	setRequestOrigin("");
	officerSearchType = "";
	survey = "";
	supervisor = "";
    }

    /**
     * Calls Clear for the form
     */
    public void reset()
    {
	clear();
    }

    private void checkBoxFix()
    {
	startTime0 = "";
	startTimeId0 = "";
	endTimeId0 = "";
	endTime0 = "";
	dayOff0 = "N";
	startTime1 = "";
	startTimeId1 = "";
	endTimeId1 = "";
	endTime1 = "";
	dayOff1 = "N";
	startTime2 = "";
	startTimeId2 = "";
	endTimeId2 = "";
	endTime2 = "";
	dayOff2 = "N";
	startTime3 = "";
	startTimeId3 = "";
	endTimeId3 = "";
	endTime3 = "";
	dayOff3 = "N";
	startTime4 = "";
	startTimeId4 = "";
	endTimeId4 = "";
	endTime4 = "";
	dayOff4 = "N";
	startTime5 = "";
	startTimeId5 = "";
	endTimeId5 = "";
	endTime5 = "";
	dayOff5 = "N";
	startTime6 = "";
	startTimeId6 = "";
	endTimeId6 = "";
	endTime6 = "";
	dayOff6 = "N";
    }

    public void reset(ActionMapping mapping, javax.servlet.http.HttpServletRequest request)
    {

	Object obj = request.getAttribute("clearCheckBoxes");
	if (obj != null)
	{
	    checkBoxFix();
	}
	obj = null;
	obj = request.getParameter("clearCheckBoxes");
	if (obj != null)
	{
	    checkBoxFix();
	}
    }

    /**
     * @return
     */
    public String getAssignedArea()
    {
	return assignedArea;
    }

    /**
     * @return
     */
    public String getBadgeNum()
    {
	return badgeNum;
    }

    /**
     * @return
     */
    public String getDepartmentId()
    {
	return departmentId;
    }

    /**
     * @return
     */
    public String getDivisionName()
    {
	return divisionName;
    }

    /**
     * @return
     */
    public String getEmail()
    {
	return email;
    }

    /**
     * @return /**
     * @return
     */
    public String getFaxLocation()
    {
	return faxLocation;
    }

    /**
     * @return
     */
    public String getJuvLocationId()
    {
	return juvLocationId;
    }

    /**
     * @return
     */
    public Collection getJuvLocations()
    {
	return juvLocations;
    }

    /**
     * @return
     */
    public String getJuvUnitId()
    {
	return juvUnitId;
    }

    /**
     * @return
     */
    public Collection getJuvUnits()
    {
	return juvUnits;
    }

    /**
     * @return
     */
    public String getLogonId()
    {
	return logonId;
    }

    /**
     * @return
     */
    public String getManagerId()
    {
	return managerId;
    }

    /**
     * @return
     */
    public Collection getOfficerIdTypes()
    {
	return officerTypes;
    }

    /**
     * @return
     */
    public String getOfficerProfileId()
    {
	return officerProfileId;
    }

    /**
     * @return
     */
    public String getOfficerRadioNum()
    {
	return officerRadioNum;
    }

    /**
     * @return
     */
    public String getOfficerTypeId()
    {
	return officerTypeId;
    }

    /**
     * @return
     */
    public String getOtherIdNum()
    {
	return otherIdNum;
    }

    /**
     * @return
     */
    public String getRankId()
    {
	return rankId;
    }

    /**
     * @return
     */
    public Collection getRanks()
    {
	return ranks;
    }

    /**
     * @return
     */
    public String getSsn()
    {
	String threeBlanks = "   ";
	String socialSeparator = "-";
	StringBuffer social = new StringBuffer();
	if (ssn1 != null || ssn2 != null || ssn3 != null)
	{
	    if ((ssn1.trim().length() > 0) || (ssn2.trim().length() > 0) || (ssn3.trim().length() > 0))
	    {
		if (ssn1.trim().length() > 0)
		    social.append(getSsn1() + socialSeparator);
		else
		    social.append(threeBlanks + socialSeparator);
		if (ssn2.trim().length() > 0)
		    social.append(getSsn2() + socialSeparator);
		else
		    social.append(threeBlanks + socialSeparator);
		if (ssn3.trim().length() > 0)
		    social.append(getSsn3());
		else
		    social.append(threeBlanks);
	    }
	}
	setSsn(social.toString());
	return ssn;
    }

    /**
     * @return
     */
    public Collection getStates()
    {
	return states;
    }

    /**
     * @return
     */
    public Collection getStreetTypes()
    {
	return streetTypes;
    }

    /**
     * @return
     */
    public Collection getWorkDays()
    {
	return workDays;
    }

    /**
     * @return
     */
    public String getWorkShift()
    {
	return workShift;
    }
    
    public String getSupervisor()
    {
	return supervisor;
    }
    
    public void setSupervisor(String string)
    {
	supervisor = string;
    }
    
    public String getSurvey()
    {
	return survey;
    }
    
    public void setSurvey(String string)
    {
	survey = string;
    }
    

    /**
     * @param string
     */
    public void setAssignedArea(String string)
    {
	assignedArea = string;
    }

    /**
     * @param string
     */
    public void setBadgeNum(String string)
    {
	badgeNum = string;
    }

    /**
     * @param string
     */
    public void setDepartmentId(String string)
    {
	departmentId = string;
    }

    /**
     * @param string
     */
    public void setDivisionName(String string)
    {
	divisionName = string;
    }

    /**
     * @param string
     */
    public void setEmail(String string)
    {
	email = string;
    }

    /**
     * @param string
     */
    public void setFaxLocation(String string)
    {
	faxLocation = string;
    }

    /**
     * @param string
     */
    public void setJuvLocationId(String string)
    {
	juvLocationId = string;
    }

    /**
     * @param string
     */
    public void setJuvUnitId(String string)
    {
	juvUnitId = string;
    }

    /**
     * @param string
     */
    public void setLogonId(String string)
    {
	logonId = string;
    }

    /**
     * @param string
     */
    public void setManagerId(String string)
    {
	managerId = string;
    }

    /**
     * @param string
     */
    public void setOfficerProfileId(String string)
    {
	officerProfileId = string;
    }

    /**
     * @param string
     */
    public void setOfficerRadioNum(String string)
    {
	officerRadioNum = string;
    }

    /**
     * @param string
     */
    public void setOfficerTypeId(String string)
    {
	officerTypeId = string;
    }

    /**
     * @param string
     */
    public void setOtherIdNum(String string)
    {
	otherIdNum = string;
    }

    /**
     * @param string
     */
    public void setRankId(String string)
    {
	rankId = string;
    }

    /**
     * @param string
     */
    public void setSsn(String security)
    {
	ssn = security;
    }

    /**
     * @param string
     */
    public void setWorkShift(String string)
    {
	workShift = string;
    }

    /**
     * @return
     */
    public String getAction()
    {
	return action;
    }

    /**
     * @param string
     */
    public void setAction(String string)
    {
	action = string;
    }

    /**
     * @return
     */
    public String getWorkExtension()
    {
	return workExtension;
    }

    /**
     * @param string
     */
    public void setWorkExtension(String string)
    {
	workExtension = string;
    }

    /**
     * @return
     */
    public String getDepartmentName()
    {
	return departmentName;
    }

    /**
     * @return
     */
    public String getJuvLocation()
    {
	return juvLocation;
    }

    /**
     * @return
     */
    public String getJuvUnit()
    {
	return juvUnit;
    }

    /**
     * @return
     */
    public String getOfficerType()
    {
	return officerType;
    }

    /**
     * @return
     */
    public String getRank()
    {
	return rank;
    }

    /**
     * @param string
     */
    public void setDepartmentName(String string)
    {
	departmentName = string;
    }

    /**
     * @param string
     */
    public void setJuvLocation(String string)
    {
	juvLocation = string;
    }

    /**
     * @param string
     */
    public void setJuvUnit(String string)
    {
	juvUnit = string;
    }

    /**
     * @param string
     */
    public void setOfficerType(String string)
    {
	officerType = string;
    }

    /**
     * @param string
     */
    public void setRank(String string)
    {
	rank = string;
    }

    /**
     * @param string
     */
    public void setStatus(String string)
    {
	status = string;
    }

    /**
     * @return
     */
    public String getFirstName()
    {
	return firstName;
    }

    /**
     * @return
     */
    public String getLastName()
    {
	return lastName;
    }

    /**
     * @return
     */
    public String getManagerFirstName()
    {
	return managerFirstName;
    }

    /**
     * @return
     */
    public String getManagerLastName()
    {
	return managerLastName;
    }

    /**
     * @return
     */
    public String getManagerMiddleName()
    {
	return managerMiddleName;
    }

    /**
     * @return
     */
    public String getMiddleName()
    {
	return middleName;
    }

    /**
     * @param string
     */
    public void setFirstName(String string)
    {
	firstName = string;
    }

    /**
     * @param string
     */
    public void setLastName(String string)
    {
	lastName = string;
    }

    /**
     * @param string
     */
    public void setManagerFirstName(String string)
    {
	managerFirstName = string;
    }

    /**
     * @param string
     */
    public void setManagerLastName(String string)
    {
	managerLastName = string;
    }

    /**
     * @param string
     */
    public void setManagerMiddleName(String string)
    {
	managerMiddleName = string;
    }

    /**
     * @param string
     */
    public void setMiddleName(String string)
    {
	middleName = string;
    }

    /**
     * @param string
     */
    public void setCellPhone(String string)
    {
	cellPhone = string;
    }

    /**
     * @param string
     */
    public void setFax(String string)
    {
	fax = string;
    }

    /**
     * @param string
     */
    public void setHomePhone(String string)
    {
	homePhone = string;
    }

    /**
     * @param string
     */
    public void setPager(String string)
    {
	pager = string;
    }

    /**
     * @param string
     */
    public void setWorkPhone(String string)
    {
	workPhone = string;
    }

    /**
     * @return
     */
    public String getCellPhone()
    {

	setCellPhone(utilPhoneDisplay(getCellPhoneAreaCode(), getCellPhonePrefix(), getCellPhoneMain()));

	return cellPhone;
    }

    /**
     * @return
     */
    public String getFax()
    {

	setFax(utilPhoneDisplay(getFaxAreaCode(), getFaxPrefix(), getFaxMain()));

	return fax;
    }

    private String utilPhoneDisplay(String anAreaCode, String aPrefix, String aMain)
    {

	String threeBlanks = "   ";
	String phoneSeparator = "-";
	StringBuffer phone = new StringBuffer();
	if (anAreaCode != null || aPrefix != null || homePhoneMain != null)
	{
	    if ((anAreaCode.trim().length() > 0) || (aPrefix.trim().length() > 0) || (aMain.trim().length() > 0))
	    {
		if (anAreaCode.trim().length() > 0)
		    phone.append(anAreaCode + phoneSeparator);
		else
		    phone.append(threeBlanks + phoneSeparator);
		if (aPrefix.trim().length() > 0)
		    phone.append(aPrefix + phoneSeparator);
		else
		    phone.append(threeBlanks + phoneSeparator);
		if (aMain.trim().length() > 0)
		    phone.append(aMain);
		else
		    phone.append(threeBlanks);
	    }
	}
	return (phone.toString());

    }

    /**
     * @return
     */
    public String getHomePhone()
    {
	setHomePhone(utilPhoneDisplay(getHomePhoneAreaCode(), getHomePhonePrefix(), getHomePhoneMain()));

	return homePhone;
    }

    /**
     * @return
     */
    public String getPager()
    {

	setPager(utilPhoneDisplay(getPagerAreaCode(), getPagerPrefix(), getPagerMain()));

	return pager;
    }

    /**
     * @return
     */

    /**
     * @return
     */
    public String getWorkPhone()
    {

	setWorkPhone(utilPhoneDisplay(getWorkPhoneAreaCode(), getWorkPhonePrefix(), getWorkPhoneMain()));
	return workPhone;
    }

    /**
     * @return
     */
    public String getExtn()
    {
	return extn;
    }

    /**
     * @param string
     */
    public void setExtn(String string)
    {
	extn = string;
    }

    /**
     * @return
     */
    public String getRadioNumber()
    {
	return radioNumber;
    }

    /**
     * @param string
     */
    public void setRadioNumber(String string)
    {
	radioNumber = string;
    }

    /**
     * @return
     */
    public String getStreetName()
    {
	return streetName;
    }

    /**
     * @return
     */
    public String getStreetNumber()
    {
	return streetNumber;
    }

    /**
     * @param string
     */
    public void setStreetName(String string)
    {
	streetName = string;
    }

    /**
     * @param string
     */
    public void setStreetNumber(String string)
    {
	streetNumber = string;
    }

    /**
     * @return
     */
    public String getStreetTypeId()
    {
	return streetTypeId;
    }

    /**
     * @param string
     */
    public void setStreetTypeId(String string)
    {
	streetTypeId = string;
    }

    /**
     * @return
     */
    public String getApartmentNumber()
    {
	return apartmentNumber;
    }

    /**
     * @param string
     */
    public void setApartmentNumber(String string)
    {
	apartmentNumber = string;
    }

    /**
     * @return
     */
    public String getCity()
    {
	return city;
    }

    /**
     * @return
     */
    public String getStateId()
    {
	return stateId;
    }

    /**
     * @return
     */
    public String getZipCode()
    {
	return zipCode;
    }

    /**
     * @param string
     */
    public void setCity(String string)
    {
	city = string;
    }

    /**
     * @param string
     */
    public void setStateId(String string)
    {
	stateId = string;
    }

    /**
     * @param string
     */
    public void setZipCode(String string)
    {
	zipCode = string;
    }

    /**
     * @return
     */
    public String getDayOff0()
    {
	return dayOff0;
    }

    /**
     * @return
     */
    public String getDayOff1()
    {
	return dayOff1;
    }

    /**
     * @return
     */
    public String getDayOff2()
    {
	return dayOff2;
    }

    /**
     * @return
     */
    public String getDayOff3()
    {
	return dayOff3;
    }

    /**
     * @return
     */
    public String getDayOff4()
    {
	return dayOff4;
    }

    /**
     * @return
     */
    public String getDayOff5()
    {
	return dayOff5;
    }

    /**
     * @return
     */
    public String getDayOff6()
    {
	return dayOff6;
    }

    /**
     * @return
     */
    public String getEndTime0()
    {
	return endTime0;
    }

    /**
     * @return
     */
    public String getEndTime1()
    {
	return endTime1;
    }

    /**
     * @return
     */
    public String getEndTime2()
    {
	return endTime2;
    }

    /**
     * @return
     */
    public String getEndTime3()
    {
	return endTime3;
    }

    /**
     * @return
     */
    public String getEndTime4()
    {
	return endTime4;
    }

    /**
     * @return
     */
    public String getEndTime5()
    {
	return endTime5;
    }

    /**
     * @return
     */
    public String getEndTime6()
    {
	return endTime6;
    }

    /**
     * @return
     */
    public String getEndTimeId0()
    {
	return endTimeId0;
    }

    /**
     * @return
     */
    public String getEndTimeId1()
    {
	return endTimeId1;
    }

    /**
     * @return
     */
    public String getEndTimeId2()
    {
	return endTimeId2;
    }

    /**
     * @return
     */
    public String getEndTimeId3()
    {
	return endTimeId3;
    }

    /**
     * @return
     */
    public String getEndTimeId4()
    {
	return endTimeId4;
    }

    /**
     * @return
     */
    public String getEndTimeId5()
    {
	return endTimeId5;
    }

    /**
     * @return
     */
    public String getEndTimeId6()
    {
	return endTimeId6;
    }

    /**
     * @return
     */
    public String getStartTime0()
    {
	return startTime0;
    }

    /**
     * @return
     */
    public String getStartTime1()
    {
	return startTime1;
    }

    /**
     * @return
     */
    public String getStartTime2()
    {
	return startTime2;
    }

    /**
     * @return
     */
    public String getStartTime3()
    {
	return startTime3;
    }

    /**
     * @return
     */
    public String getStartTime4()
    {
	return startTime4;
    }

    /**
     * @return
     */
    public String getStartTime5()
    {
	return startTime5;
    }

    /**
     * @return
     */
    public String getStartTime6()
    {
	return startTime6;
    }

    /**
     * @return
     */
    public String getStartTimeId0()
    {
	return startTimeId0;
    }

    /**
     * @return
     */
    public String getStartTimeId1()
    {
	return startTimeId1;
    }

    /**
     * @return
     */
    public String getStartTimeId2()
    {
	return startTimeId2;
    }

    /**
     * @return
     */
    public String getStartTimeId3()
    {
	return startTimeId3;
    }

    /**
     * @return
     */
    public String getStartTimeId4()
    {
	return startTimeId4;
    }

    /**
     * @return
     */
    public String getStartTimeId5()
    {
	return startTimeId5;
    }

    /**
     * @return
     */
    public String getStartTimeId6()
    {
	return startTimeId6;
    }

    /**
     * @return
     */
    public String getStatus()
    {
	return status;
    }

    /**
     * @param string
     */
    public void setDayOff0(String string)
    {
	dayOff0 = string;
    }

    /**
     * @param string
     */
    public void setDayOff1(String string)
    {
	dayOff1 = string;
    }

    /**
     * @param string
     */
    public void setDayOff2(String string)
    {
	dayOff2 = string;
    }

    /**
     * @param string
     */
    public void setDayOff3(String string)
    {
	dayOff3 = string;
    }

    /**
     * @param string
     */
    public void setDayOff4(String string)
    {
	dayOff4 = string;
    }

    /**
     * @param string
     */
    public void setDayOff5(String string)
    {
	dayOff5 = string;
    }

    /**
     * @param string
     */
    public void setDayOff6(String string)
    {
	dayOff6 = string;
    }

    /**
     * @param string
     */
    public void setEndTime0(String string)
    {
	endTime0 = string;
    }

    /**
     * @param string
     */
    public void setEndTime1(String string)
    {
	endTime1 = string;
    }

    /**
     * @param string
     */
    public void setEndTime2(String string)
    {
	endTime2 = string;
    }

    /**
     * @param string
     */
    public void setEndTime3(String string)
    {
	endTime3 = string;
    }

    /**
     * @param string
     */
    public void setEndTime4(String string)
    {
	endTime4 = string;
    }

    /**
     * @param string
     */
    public void setEndTime5(String string)
    {
	endTime5 = string;
    }

    /**
     * @param string
     */
    public void setEndTime6(String string)
    {
	endTime6 = string;
    }

    /**
     * @param string
     */
    public void setEndTimeId0(String string)
    {
	endTimeId0 = string;
    }

    /**
     * @param string
     */
    public void setEndTimeId1(String string)
    {
	endTimeId1 = string;
    }

    /**
     * @param string
     */
    public void setEndTimeId2(String string)
    {
	endTimeId2 = string;
    }

    /**
     * @param string
     */
    public void setEndTimeId3(String string)
    {
	endTimeId3 = string;
    }

    /**
     * @param string
     */
    public void setEndTimeId4(String string)
    {
	endTimeId4 = string;
    }

    /**
     * @param string
     */
    public void setEndTimeId5(String string)
    {
	endTimeId5 = string;
    }

    /**
     * @param string
     */
    public void setEndTimeId6(String string)
    {
	endTimeId6 = string;
    }

    /**
     * @param string
     */
    public void setStartTime0(String string)
    {
	startTime0 = string;
    }

    /**
     * @param string
     */
    public void setStartTime1(String string)
    {
	startTime1 = string;
    }

    /**
     * @param string
     */
    public void setStartTime2(String string)
    {
	startTime2 = string;
    }

    /**
     * @param string
     */
    public void setStartTime3(String string)
    {
	startTime3 = string;
    }

    /**
     * @param string
     */
    public void setStartTime4(String string)
    {
	startTime4 = string;
    }

    /**
     * @param string
     */
    public void setStartTime5(String string)
    {
	startTime5 = string;
    }

    /**
     * @param string
     */
    public void setStartTime6(String string)
    {
	startTime6 = string;
    }

    /**
     * @param string
     */
    public void setStartTimeId0(String string)
    {
	startTimeId0 = string;
    }

    /**
     * @param string
     */
    public void setStartTimeId1(String string)
    {
	startTimeId1 = string;
    }

    /**
     * @param string
     */
    public void setStartTimeId2(String string)
    {
	startTimeId2 = string;
    }

    /**
     * @param string
     */
    public void setStartTimeId3(String string)
    {
	startTimeId3 = string;
    }

    /**
     * @param string
     */
    public void setStartTimeId4(String string)
    {
	startTimeId4 = string;
    }

    /**
     * @param string
     */
    public void setStartTimeId5(String string)
    {
	startTimeId5 = string;
    }

    /**
     * @param string
     */
    public void setStartTimeId6(String string)
    {
	startTimeId6 = string;
    }

    /**
     * @return
     */
    public List getOfficerProfiles()
    {
	return officerProfiles;
    }

    /**
     * @param collection
     */
    public void setOfficerProfiles(List collection)
    {
	officerProfiles = collection;
    }

    /**
     * @return
     */
    public Collection getDepartments()
    {
	return departments;
    }

    /**
     * @param collection
     */
    public void setDepartments(Collection collection)
    {
	departments = collection;
    }

    /**
     * @return
     */
    public Collection getManagerProfiles()
    {
	return managerProfiles;
    }

    /**
     * @param collection
     */
    public void setManagerProfiles(Collection collection)
    {
	managerProfiles = collection;
    }

    /**
     * @return
     */
    public Collection getUserProfiles()
    {
	return userProfiles;
    }

    /**
     * @param collection
     */
    public void setUserProfiles(Collection collection)
    {
	userProfiles = collection;
    }

    /**
     * @return
     */
    public String getState()
    {
	return state;
    }

    /**
     * @return
     */
    public String getStreetType()
    {
	return streetType;
    }

    /**
     * @param string
     */
    public void setState(String string)
    {
	state = string;
    }

    /**
     * @param string
     */
    public void setStreetType(String string)
    {
	streetType = string;
    }

    /**
     * @return
     */
    public Collection getWorkDayCodes()
    {
	return workDays;
    }

    /**
     * @return
     */
    public String getCellPhoneAreaCode()
    {
	return cellPhoneAreaCode;
    }

    /**
     * @return
     */
    public String getCellPhoneMain()
    {
	return cellPhoneMain;
    }

    /**
     * @return
     */
    public String getCellPhonePrefix()
    {
	return cellPhonePrefix;
    }

    /**
     * @return
     */
    public String getFaxAreaCode()
    {
	return faxAreaCode;
    }

    /**
     * @return
     */
    public String getFaxMain()
    {
	return faxMain;
    }

    /**
     * @return
     */
    public String getFaxPrefix()
    {
	return faxPrefix;
    }

    /**
     * @return
     */
    public String getHomePhoneAreaCode()
    {
	return homePhoneAreaCode;
    }

    /**
     * @return
     */
    public String getHomePhoneMain()
    {
	return homePhoneMain;
    }

    /**
     * @return
     */
    public String getHomePhonePrefix()
    {
	return homePhonePrefix;
    }

    /**
     * @return
     */
    public Collection getOfficerTypes()
    {
	return officerTypes;
    }

    /**
     * @return
     */
    public String getPagerAreaCode()
    {
	return pagerAreaCode;
    }

    /**
     * @return
     */
    public String getPagerMain()
    {
	return pagerMain;
    }

    /**
     * @return
     */
    public String getPagerPrefix()
    {
	return pagerPrefix;
    }

    /**
     * @return
     */
    public String getSsn1()
    {
	return ssn1;
    }

    /**
     * @return
     */
    public String getSsn2()
    {
	return ssn2;
    }

    /**
     * @return
     */
    public String getSsn3()
    {
	return ssn3;
    }

    /**
     * @return
     */
    public String getWorkPhoneAreaCode()
    {
	return workPhoneAreaCode;
    }

    /**
     * @return
     */
    public String getWorkPhoneMain()
    {
	return workPhoneMain;
    }

    /**
     * @return
     */
    public String getWorkPhonePrefix()
    {
	return workPhonePrefix;
    }

    /**
     * @param string
     */
    public void setCellPhoneAreaCode(String string)
    {
	cellPhoneAreaCode = string;
    }

    /**
     * @param string
     */
    public void setCellPhoneMain(String string)
    {
	cellPhoneMain = string;
    }

    /**
     * @param string
     */
    public void setCellPhonePrefix(String string)
    {
	cellPhonePrefix = string;
    }

    /**
     * @param string
     */
    public void setFaxAreaCode(String string)
    {
	faxAreaCode = string;
    }

    /**
     * @param string
     */
    public void setFaxMain(String string)
    {
	faxMain = string;
    }

    /**
     * @param string
     */
    public void setFaxPrefix(String string)
    {
	faxPrefix = string;
    }

    /**
     * @param string
     */
    public void setHomePhoneAreaCode(String string)
    {
	homePhoneAreaCode = string;
    }

    /**
     * @param string
     */
    public void setHomePhoneMain(String string)
    {
	homePhoneMain = string;
    }

    /**
     * @param string
     */
    public void setHomePhonePrefix(String string)
    {
	homePhonePrefix = string;
    }

    /**
     * @param collection
     */
    public void setJuvLocations(Collection collection)
    {
	juvLocations = collection;
    }

    /**
     * @param collection
     */
    public void setJuvUnits(Collection collection)
    {
	juvUnits = collection;
    }

    /**
     * @param collection
     */
    public void setOfficerTypes(Collection collection)
    {
	officerTypes = collection;
    }

    /**
     * @param string
     */
    public void setPagerAreaCode(String string)
    {
	pagerAreaCode = string;
    }

    /**
     * @param string
     */
    public void setPagerMain(String string)
    {
	pagerMain = string;
    }

    /**
     * @param string
     */
    public void setPagerPrefix(String string)
    {
	pagerPrefix = string;
    }

    /**
     * @param collection
     */
    public void setRanks(Collection collection)
    {
	ranks = collection;
    }

    /**
     * @param string
     */
    public void setSsn1(String string)
    {
	ssn1 = string;
    }

    /**
     * @param string
     */
    public void setSsn2(String string)
    {
	ssn2 = string;
    }

    /**
     * @param string
     */
    public void setSsn3(String string)
    {
	ssn3 = string;
    }

    /**
     * @param collection
     */
    public void setStates(Collection collection)
    {
	states = collection;
    }

    /**
     * @param collection
     */
    public void setStreetTypes(Collection collection)
    {
	streetTypes = collection;
    }

    /**
     * @param collection
     */
    public void setWorkDays(Collection collection)
    {
	workDays = collection;
    }

    /**
     * @param string
     */
    public void setWorkPhoneAreaCode(String string)
    {
	workPhoneAreaCode = string;
    }

    /**
     * @param string
     */
    public void setWorkPhoneMain(String string)
    {
	workPhoneMain = string;
    }

    /**
     * @param string
     */
    public void setWorkPhonePrefix(String string)
    {
	workPhonePrefix = string;
    }

    /**
     * @return
     */
    public String getUserId()
    {
	return userId;
    }

    /**
     * @param string
     */
    public void setUserId(String string)
    {
	userId = string;
    }

    /**
     * @return
     */
    public Collection getWorkScedules()
    {
	return workScedules;
    }

    /**
     * @param collection
     */
    public void setWorkScedules(Collection collection)
    {
	workScedules = collection;
    }

    /**
     * @return
     */
    public String getDepartmentNamePrompt()
    {
	return departmentNamePrompt;
    }

    /**
     * @return
     */
    public String getLastNamePrompt()
    {
	return lastNamePrompt;
    }

    /**
     * @return
     */
    public String getFirstNamePrompt()
    {
	return firstNamePrompt;
    }

    /**
     * @return
     */
    public String getUserIdPrompt()
    {
	return userIdPrompt;
    }

    /**
     * @param string
     */
    public void setDepartmentNamePrompt(String string)
    {
	departmentNamePrompt = string;
    }

    /**
     * @param string
     */
    public void setLastNamePrompt(String string)
    {
	lastNamePrompt = string;
    }

    /**
     * @param string
     */
    public void setFirstNamePrompt(String string)
    {
	firstNamePrompt = string;
    }

    /**
     * @param string
     */
    public void setUserIdPrompt(String string)
    {
	userIdPrompt = string;
    }

    /**
     * @return
     */
    public String getBadgeNumPrompt()
    {
	return badgeNumPrompt;
    }

    /**
     * @return
     */
    public String getOtherIdNumPrompt()
    {
	return otherIdNumPrompt;
    }

    /**
     * @param string
     */
    public void setBadgeNumPrompt(String string)
    {
	badgeNumPrompt = string;
    }

    /**
     * @param string
     */
    public void setOtherIdNumPrompt(String string)
    {
	otherIdNumPrompt = string;
    }

    /**
     * @return
     */
    public String getDepartmentIdPrompt()
    {
	return departmentIdPrompt;
    }

    /**
     * @param string
     */
    public void setDepartmentIdPrompt(String string)
    {
	departmentIdPrompt = string;
    }

    /**
     * @return
     */
    public String getLogonIdPrompt()
    {
	return logonIdPrompt;
    }

    /**
     * @param string
     */
    public void setLogonIdPrompt(String string)
    {
	logonIdPrompt = string;
    }

    /**
     * @return
     */
    public String getSelectedUserProfile()
    {
	return selectedUserProfile;
    }

    /**
     * @param string
     */
    public void setSelectedUserProfile(String string)
    {
	selectedUserProfile = string;
    }

    /**
     * @return
     */
    public String getManagerProfilesSize()
    {
	return managerProfilesSize;
    }

    /**
     * @param string
     */
    public void setManagerProfilesSize(String string)
    {
	managerProfilesSize = string;
    }

    /**
     * @return
     */
    public String getSelectedDepartment()
    {
	return selectedDepartment;
    }

    /**
     * @param string
     */
    public void setSelectedDepartment(String string)
    {
	selectedDepartment = string;
    }

    /**
     * @return
     */
    public String getSelectedManager()
    {
	return selectedManager;
    }

    /**
     * @param string
     */
    public void setSelectedManager(String string)
    {
	selectedManager = string;
    }

    /**
     * @return
     */
    public String getAgencyId()
    {
	return agencyId;
    }

    /**
     * @param string
     */
    public void setAgencyId(String string)
    {
	agencyId = string;
    }

    /**
     * @return
     */
    public String getOriginalBadgeNumber()
    {
	return originalBadgeNumber;
    }

    /**
     * @return
     */
    public String getOriginalOtherIdNumber()
    {
	return originalOtherIdNumber;
    }

    /**
     * @return
     */
    public String getOriginalUserId()
    {
	return originalUserId;
    }

    /**
     * @param string
     */
    public void setOriginalBadgeNumber(String string)
    {
	originalBadgeNumber = string;
    }

    /**
     * @param string
     */
    public void setOriginalOtherIdNumber(String string)
    {
	originalOtherIdNumber = string;
    }

    /**
     * @param string
     */
    public void setOriginalUserId(String string)
    {
	originalUserId = string;
    }

    /**
     * @return
     */
    public boolean isASA()
    {
	return isASA;
    }

    /**
     * @return
     */
    public boolean isMA()
    {
	return isMA;
    }

    /**
     * @return
     */
    public boolean isOfficer()
    {
	return isOfficer;
    }

    /**
     * @return
     */
    public boolean isSA()
    {
	return isSA;
    }

    /**
     * @param b
     */
    public void setASA(boolean b)
    {
	isASA = b;
    }

    /**
     * @param b
     */
    public void setMA(boolean b)
    {
	isMA = b;
    }

    /**
     * @param b
     */
    public void setOfficer(boolean b)
    {
	isOfficer = b;
    }

    /**
     * @param b
     */
    public void setSA(boolean b)
    {
	isSA = b;
    }

    /**
     * @return
     */
    public String getDeletableStatus()
    {
	return deletableStatus;
    }

    /**
     * @return
     */
    public String getUpdatableStatus()
    {
	return updatableStatus;
    }

    /**
     * @param string
     */
    public void setDeletableStatus(String string)
    {
	deletableStatus = string;
    }

    /**
     * @param string
     */
    public void setUpdatableStatus(String string)
    {
	updatableStatus = string;
    }

    /**
     * @return
     */
    public boolean isClearCheckBoxes()
    {
	return isClearCheckBoxes;
    }

    /**
     * @param b
     */
    public void setClearCheckBoxes(boolean b)
    {
	isClearCheckBoxes = b;
    }

    /**
     * @return
     */
    public String getWorkDayId0()
    {
	return workDayId0;
    }

    /**
     * @return
     */
    public String getWorkDayId1()
    {
	return workDayId1;
    }

    /**
     * @return
     */
    public String getWorkDayId2()
    {
	return workDayId2;
    }

    /**
     * @return
     */
    public String getWorkDayId3()
    {
	return workDayId3;
    }

    /**
     * @return
     */
    public String getWorkDayId4()
    {
	return workDayId4;
    }

    /**
     * @return
     */
    public String getWorkDayId5()
    {
	return workDayId5;
    }

    /**
     * @return
     */
    public String getWorkDayId6()
    {
	return workDayId6;
    }

    /**
     * @param string
     */
    public void setWorkDayId0(String string)
    {
	workDayId0 = string;
    }

    /**
     * @param string
     */
    public void setWorkDayId1(String string)
    {
	workDayId1 = string;
    }

    /**
     * @param string
     */
    public void setWorkDayId2(String string)
    {
	workDayId2 = string;
    }

    /**
     * @param string
     */
    public void setWorkDayId3(String string)
    {
	workDayId3 = string;
    }

    /**
     * @param string
     */
    public void setWorkDayId4(String string)
    {
	workDayId4 = string;
    }

    /**
     * @param string
     */
    public void setWorkDayId5(String string)
    {
	workDayId5 = string;
    }

    /**
     * @param string
     */
    public void setWorkDayId6(String string)
    {
	workDayId6 = string;
    }

    /**
     * @return
     */
    public String getAdditionalZipCode()
    {
	return additionalZipCode;
    }

    /**
     * @param string
     */
    public void setAdditionalZipCode(String string)
    {
	additionalZipCode = string;
    }

    /**
     * @return
     */
    public String getWorkScheduleId0()
    {
	return workScheduleId0;
    }

    /**
     * @return
     */
    public String getWorkScheduleId1()
    {
	return workScheduleId1;
    }

    /**
     * @return
     */
    public String getWorkScheduleId2()
    {
	return workScheduleId2;
    }

    /**
     * @return
     */
    public String getWorkScheduleId3()
    {
	return workScheduleId3;
    }

    /**
     * @return
     */
    public String getWorkScheduleId4()
    {
	return workScheduleId4;
    }

    /**
     * @return
     */
    public String getWorkScheduleId5()
    {
	return workScheduleId5;
    }

    /**
     * @return
     */
    public String getWorkScheduleId6()
    {
	return workScheduleId6;
    }

    /**
     * @param string
     */
    public void setWorkScheduleId0(String string)
    {
	workScheduleId0 = string;
    }

    /**
     * @param string
     */
    public void setWorkScheduleId1(String string)
    {
	workScheduleId1 = string;
    }

    /**
     * @param string
     */
    public void setWorkScheduleId2(String string)
    {
	workScheduleId2 = string;
    }

    /**
     * @param string
     */
    public void setWorkScheduleId3(String string)
    {
	workScheduleId3 = string;
    }

    /**
     * @param string
     */
    public void setWorkScheduleId4(String string)
    {
	workScheduleId4 = string;
    }

    /**
     * @param string
     */
    public void setWorkScheduleId5(String string)
    {
	workScheduleId5 = string;
    }

    /**
     * @param string
     */
    public void setWorkScheduleId6(String string)
    {
	workScheduleId6 = string;
    }

    /**
     * @return
     */
    public Collection getOfficerSubTypes()
    {
	return officerSubTypes;
    }

    /**
     * @param collection
     */
    public void setOfficerSubTypes(Collection collection)
    {
	officerSubTypes = collection;
    }

    /**
     * @return
     */
    public String getOfficerSubType()
    {
	return officerSubType;
    }

    /**
     * @return
     */
    public String getOfficerSubTypeId()
    {
	return officerSubTypeId;
    }

    /**
     * @param string
     */
    public void setOfficerSubType(String string)
    {
	officerSubType = string;
    }

    /**
     * @param string
     */
    public void setOfficerSubTypeId(String string)
    {
	officerSubTypeId = string;
    }

    /**
     * @return Returns the officerStatuses.
     */
    public Collection getOfficerStatuses()
    {
	return officerStatuses;
    }

    /**
     * @param officerStatuses
     *            The officerStatuses to set.
     */
    public void setOfficerStatuses(Collection officerStatuses)
    {
	this.officerStatuses = officerStatuses;
    }

    /**
     * @return Returns the statusId.
     */
    public String getStatusId()
    {
	return statusId;
    }

    /**
     * @param statusId
     *            The statusId to set.
     */
    public void setStatusId(String statusId)
    {
	this.statusId = statusId;
	if (statusId.equalsIgnoreCase("I"))
	    this.status = "Inactive";
	else
	    if (statusId.equalsIgnoreCase("A"))
		this.status = "Active";
	    else
		this.status = "";
    }

    /**
     * @return Returns the numberViewable.
     */
    public String getNumberViewable()
    {
	return numberViewable;
    }

    /**
     * @param numberViewable
     *            The numberViewable to set.
     */
    public void setNumberViewable(String numberViewable)
    {
	this.numberViewable = numberViewable;
    }

    /**
     * @return Returns the managerStatus.
     */
    public String getManagerStatus()
    {
	return managerStatus;
    }

    /**
     * @param managerStatus
     *            The managerStatus to set.
     */
    public void setManagerStatus(String managerStatus)
    {
	this.managerStatus = managerStatus;
    }

    public String[] getSelectedUserIds()
    {
	return selectedUserIds;
    }

    public void setSelectedUserIds(String[] selectedUserIds)
    {
	this.selectedUserIds = selectedUserIds;
    }

    public List getSelectedList()
    {
	return selectedList;
    }

    public void setSelectedList(List selectedList)
    {
	this.selectedList = selectedList;
    }

    public String getNewManagerId()
    {
	return newManagerId;
    }

    public void setNewManagerId(String newManagerId)
    {
	this.newManagerId = newManagerId;
    }

    public String getNewManagerMiddleName()
    {
	return newManagerMiddleName;
    }

    public void setNewManagerMiddleName(String newManagerMiddleName)
    {
	this.newManagerMiddleName = newManagerMiddleName;
    }

    public String getNewManagerFirstName()
    {
	return newManagerFirstName;
    }

    public void setNewManagerFirstName(String newManagerFirstName)
    {
	this.newManagerFirstName = newManagerFirstName;
    }

    public String getNewManagerLastName()
    {
	return newManagerLastName;
    }

    public void setNewManagerLastName(String newManagerLastName)
    {
	this.newManagerLastName = newManagerLastName;
    }

    public String getRequestOrigin()
    {
	return requestOrigin;
    }

    public void setRequestOrigin(String requestOrigin)
    {
	this.requestOrigin = requestOrigin;
    }

    public String getOfficerSearchType()
    {
	return officerSearchType;
    }

    public void setOfficerSearchType(String officerSearchType)
    {
	this.officerSearchType = officerSearchType;
    }

    public String getLimitedUpdatableStatus()
    {
	return limitedUpdatableStatus;
    }

    public void setLimitedUpdatableStatus(String limitedUpdatableStatus)
    {
	this.limitedUpdatableStatus = limitedUpdatableStatus;
    }

    public Collection<OfficerTrainingResponseEvent> getOfficerTraining()
    {
        return officerTraining;
    }

    public void setOfficerTraining(Collection<OfficerTrainingResponseEvent> officerTraining)
    {
        this.officerTraining = officerTraining;
    }

    public Collection<TrainingTopicsResponseEvent> getTrainingTopics()
    {
        return trainingTopics;
    }

    public void setTrainingTopics(Collection<TrainingTopicsResponseEvent> trainingTopics)
    {
        this.trainingTopics = trainingTopics;
    }

    public String getTrainingTopicId()
    {
        return trainingTopicId;
    }

    public void setTrainingTopicId(String trainingTopicId)
    {
        this.trainingTopicId = trainingTopicId;
    }

    public String getTrainingBegDate()
    {
        return trainingBegDate;
    }

    public void setTrainingBegDate(String trainingBegDate)
    {
        this.trainingBegDate = trainingBegDate;
    }

    public String getTrainingEndDate()
    {
        return trainingEndDate;
    }

    public void setTrainingEndDate(String trainingEndDate)
    {
        this.trainingEndDate = trainingEndDate;
    }

    public double getTrainingHours()
    {
        return trainingHours;
    }

    public void setTrainingHours(double trainingHours)
    {
        this.trainingHours = trainingHours;
    }
   
}
