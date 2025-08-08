/*
 * Created on Jun 23, 2005
 *
  */
package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

import messaging.codetable.person.reply.JuvenileSchoolDistrictCodeResponseEvent;
import messaging.interviewinfo.to.SocialHistoryReportDataTO;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.SchoolHistoryComparator;

/**
 * @author jfisher
 */
public class JuvenileSchoolHistoryForm extends ActionForm
{
	private String[] removeSchoolHistory;
	private String action;
	private String gradeLevelId;
	private String gradeLevelDescription;
	private Collection gradeLevels;
	private String appropriateGradeLevelId;
	private String appropriateGradeLevelDescription;
	private Collection appropriateGradeLevels;
	private String reasonForGradeLevelChange;// task 118647
	private String exitTypeId;
	private String exitTypeDescription;
	private Collection exitTypes;
	private String schoolId;
	private String schoolName; //added for Bug 11307
	private String instructionType;//added for bug 11307

	private Map schools;
	private String schoolDescription;
	private String schoolDisplayLiteral;
	private String schoolDistrictId;
	private String schoolDistrictDescription;

	private String homeSchoolDescription;
	private String homeSchoolDisplayLiteral;
	private String homeSchoolId;
	private String homeSchoolDistrictId;
	private String homeSchoolDistrictDescription;
	
	private String specificSchoolAddress;
	private String specificSchoolName;
	private String streetNum;
	private String streetName;
	private String city;
	private String stateId;
	private String stateDesc;
	private String zipCode;
	private String zipCodeExt;
	private List states;
	private String unknownNameInd;
	private String backFlowIndicator;
	
	private Map schoolDistricts;
	private String lastAttendedDateString;
	private String verifiedDateString;
	//ER Changes JIMS200077279 starts 
	private String eligibilityEnrollmentDate; 
	private String juvenileNum;
	private Collection newSchoolHistory;
	private boolean isSuccess = false;
	private String gradeAverageId;
	private String gradesRepeatNumber;
	private Collection gradesRepeated;
	private String gradesRepeatedId;
	private String gradesRepeatedDescription;
	private Collection participation;
	private String participationId;
	private String participationDescription;
	private Collection programAttending;
	private String programAttendingId;
	private String programAttendingDescription;
	private Collection ruleInfraction;
	private String ruleInfractionId;
	private String ruleInfractionDescription;
	private Collection schoolAttendanceStatus;
	private Collection allSchoolAttendanceStatus;
	private String schoolAttendanceStatusId;
	private String schoolAttendanceStatusDescription;
	private String truancyHistory;
	private String entryDate;

	private JuvenileSchoolHistoryResponseEvent currentSchoolDetails;
	private String secondaryAction;
	
	private Collection schoolHistory;
	
	private Collection schoolDistrictList; // List of SchoolNames and corresponding District Names.
	private String schoolSearchName;
	private String selectedSchool;
	private String selectedSchoolId;

	private String homeSchoolSearchInd;
	
	private List existingGEDPrograms;
	// TEA report variables
	public SocialHistoryReportDataTO	socialHistoryData;
	private String reportGenerated;
	private String errorMsg;
	private List documents;
	
	//added for Education ID display
	private String educationId;
	// Changes for JIMS200077276 Starts
	private String studentId;
	 //Changes for JIMS200077276 ends
	//added for spl edu category 
	private Collection splEduCategory;
	private String splEduCategoryId;
	private String splEduCategoryDescription;
	// Changes for JIMS200077276 Starts
	private boolean hasFeature=false;
	// Changes for JIMS200077276 ends
	//added for spl edu category 
	
	private String createDate;// Task 105452
	private String educationService;//Task115689
	
	private int gradeRepeatTotal;
	private Collection verificationCodes;
	private Collection academicCodes;
	private String academicPerformance;
	private String academicPerformanceDesc;
	private String schoolInfoVerifiedBy;
	private String schoolInfoVerifiedByDesc;
	private String schoolVerfifyOther;
	private boolean truancy;
	
	// GED addition
	 private boolean awarded;
	 private String awardedDateStr;
	 private String completionDateStr;
	 private boolean gedCompleted;
	 
	 
	/**
	 * 
	 */
	public void clear()
	{
		this.schoolDistrictId = null;
		this.schoolId = null;
		this.appropriateGradeLevelId = null;
		this.exitTypeId = null;
		this.gradeLevelId = null;
		this.juvenileNum = null;
		this.lastAttendedDateString = null;
		this.newSchoolHistory = new ArrayList();
		this.removeSchoolHistory = null;
		this.isSuccess = false;
		this.schoolHistory = new ArrayList();
		this.gradeAverageId = null;
		this.gradesRepeatNumber = null;
		this.gradesRepeatedId = null;
		this.participationId = null;
		this.splEduCategoryId = null;
		this.programAttendingId = null;
		this.ruleInfractionId = null;
		this.schoolAttendanceStatusId = null;		
		this.verifiedDateString = null;
		this.eligibilityEnrollmentDate=null;
		this.truancyHistory = null;				
		this.entryDate = null;		
		this.homeSchoolDistrictId = null;
		this.homeSchoolId = null;
		this.setHomeSchoolSearchInd(null);
		this.unknownNameInd = "";
		this.backFlowIndicator = "";
		// Changes for JIMS200077276 Starts
		this.hasFeature=false;
		// Changes for JIMS200077276 ends
		this.createDate = "";
		this.reasonForGradeLevelChange = null;
		this.gradeRepeatTotal = 0;
		this.gradesRepeatNumber = null;
		this.academicPerformance = "";
		this.schoolInfoVerifiedBy = "";
		this.truancy = false;

	}

	/**
	 * 
	 */
	public void clearAddSchoolForm()
	{
		this.schoolDistrictId = null;
		this.schoolId = null;
		this.appropriateGradeLevelId = null;
		this.exitTypeId = null;
		this.gradeLevelId = null;
		this.lastAttendedDateString = null;
		this.removeSchoolHistory = null;
		this.gradeAverageId = null;
		this.gradesRepeatNumber = null;
		this.gradesRepeatedId = null;
		this.participationId = null;
		this.splEduCategoryId = null;
		this.programAttendingId = null;
		this.ruleInfractionId = null;
		this.schoolAttendanceStatusId = null;		
		this.truancyHistory = null;
		this.verifiedDateString = null;		
		this.eligibilityEnrollmentDate=null;
		this.isSuccess = false;
		this.schoolName="";
		this.instructionType="";
		//this.homeSchoolDistrictId = null;
		//this.setHomeSchoolSearchInd(null);
		this.specificSchoolAddress="";
		this.specificSchoolName="";
		this.streetNum = "";
		this.streetName = "";
		this.city = "";
		this.stateDesc = "";
		this.stateId = "";
		this.zipCode="";
		this.zipCodeExt="";
		// Changes for JIMS200077276 Starts
		this.hasFeature=false;
		// Changes for JIMS200077276 ends
		this.reasonForGradeLevelChange = null;
		this.gradeRepeatTotal = 0;
		this.academicPerformance = "";
		this.schoolInfoVerifiedBy = "";
		this.truancy = false;

	}
	
	
	
	
	
	/**
	 * @return
	 */
	public Collection getSchoolHistory()
	{
		if (schoolHistory == null)
		{
			schoolHistory = new ArrayList();
		}
		
		Collections.sort((List) schoolHistory, new SchoolHistoryComparator());
		
		return schoolHistory;
	}

	/**
	 * @param collection
	 */
	public void setSchoolHistory(Collection collection)
	{
		schoolHistory = collection;
	}

	/**
	 * @return
	 */
	public Collection getGradeLevels()
	{
		if (gradeLevels == null)
		{
			gradeLevels = new ArrayList();
		}
		return gradeLevels;
	}

	/**
	 * @return
	 */
	public Collection getExitTypes()
	{
		if (exitTypes == null)
		{
			exitTypes = new ArrayList();
		}
		return exitTypes;
	}

	/**
	 * @return
	 */
	public String getLastAttendedDateString()
	{
		return lastAttendedDateString;
	}

	/**
	 * @return
	 */
	public Collection getSchoolDistricts()
	{
//		if (schoolDistricts == null)
//		{
//			schoolDistricts = new HashMap();
//		}
//		return schoolDistricts.values();
//		
		
		if (schoolDistricts == null)
		{
			schoolDistricts = new HashMap();
		}

		ArrayList myList=new ArrayList(schoolDistricts.values());
		Collections.sort((List) myList);

		return myList;
	}

	/**
	 * @return
	 */
	public Collection getSchools()
	{
		Collection schoolCodes = null;
		if (schoolDistrictId == null || schools == null || schools.containsKey(schoolDistrictId) == false)
		{
			schoolCodes = new ArrayList();
		}
		else
		{
			schoolCodes = (Collection) schools.get(schoolDistrictId);
			Collections.sort((List)schoolCodes);
		}
		
		return schoolCodes;
	}
	
	/**
	 * 
	 * @return
	 */
	public Collection getHomeSchools()

	{

	Collection schoolCodes = null;

	Collection filteredSchools = new ArrayList();

	if (homeSchoolDistrictId == null || schools == null || schools.containsKey(homeSchoolDistrictId) == false)
	{
	    filteredSchools = new ArrayList();
	}
	   else

	   {

	schoolCodes = (Collection) schools.get(homeSchoolDistrictId);

	Iterator<JuvenileSchoolDistrictCodeResponseEvent> iter = schoolCodes.iterator();

	while(iter.hasNext()) {

	   

	   JuvenileSchoolDistrictCodeResponseEvent school = iter.next();

	   if(!"Y".equalsIgnoreCase(school.getInactiveInd()) && !"A".equalsIgnoreCase(school.getSubGroupInd()) ){


	       filteredSchools.add(school);

	   }
	}
		Collections.sort((List)filteredSchools);

	}

	return filteredSchools;

	}

	public JuvenileSchoolDistrictCodeResponseEvent getSchoolCode()
	{
		JuvenileSchoolDistrictCodeResponseEvent foundSchool = null;
		if (this.getSchoolMap().containsKey(this.schoolDistrictId))
		{
			Collection schoolCodes = (Collection) schools.get(this.schoolDistrictId);
			if (schoolCodes != null)
			{
				Iterator i = schoolCodes.iterator();
				while (i.hasNext())
				{
					JuvenileSchoolDistrictCodeResponseEvent schoolCode =
						(JuvenileSchoolDistrictCodeResponseEvent) i.next();
					if (schoolCode.getSchoolCode().equals(this.schoolId))
					{
						foundSchool = schoolCode;
						break;
					}
				}
			}
		}
		return foundSchool;
	}
	
	public JuvenileSchoolDistrictCodeResponseEvent getHomeSchoolCode()
	{
		JuvenileSchoolDistrictCodeResponseEvent foundSchool = null;
		if (this.getSchoolMap().containsKey(this.homeSchoolDistrictId))
		{
			Collection schoolCodes = (Collection) schools.get(this.homeSchoolDistrictId);
			if (schoolCodes != null)
			{
				Iterator i = schoolCodes.iterator();
				while (i.hasNext())
				{
					JuvenileSchoolDistrictCodeResponseEvent schoolCode =
						(JuvenileSchoolDistrictCodeResponseEvent) i.next();
					if (schoolCode.getSchoolCode().equals(this.homeSchoolId))
					{
						foundSchool = schoolCode;
						break;
					}
				}
			}
		}
		return foundSchool;
	}


	/**
	 * 
	 */
	private Map getSchoolMap()
	{
		if (this.schools == null)
		{
			this.schools = new HashMap();
		}
		return this.schools;

	}

	/**
	 * @return Returns the schoolDistrictList.
	 */
	public Collection getSchoolDistrictList() {
		if (this.schoolDistrictList == null)
		{
			this.schoolDistrictList = new ArrayList();
		}
		return schoolDistrictList;
	}
	/**
	 * @param schoolDistrictList The schoolDistrictList to set.
	 */
	public void setSchoolDistrictList(Collection schoolDistrictList) {
		this.schoolDistrictList = schoolDistrictList;
	}
	
	
	/**
	 * @return Returns the selectedSchool.
	 */
	public String getSelectedSchool() {
		return selectedSchool;
	}
	/**
	 * @param selectedSchool The selectedSchool to set.
	 */
	public void setSelectedSchool(String selectedSchool) {
		this.selectedSchool = selectedSchool;
	}
	
	/**
	 * @return Returns the selectedSchoolId.
	 */
	public String getSelectedSchoolId() {
		return selectedSchoolId;
	}
	/**
	 * @param selectedSchoolId The selectedSchoolId to set.
	 */
	public void setSelectedSchoolId(String selectedSchoolId) {
		this.selectedSchoolId = selectedSchoolId;
	}
	
	/**
	 * @return Returns the selectedSchool.
	 */
	public String getSelectedHomeSchool() {
		return selectedSchool;
	}
	/**
	 * @return Returns the schoolSearchName.
	 */
	public String getSchoolSearchName() {
		return schoolSearchName;
	}
	/**
	 * @param schoolSearchName The schoolSearchName to set.
	 */
	public void setSchoolSearchName(String schoolSearchName) {
		this.schoolSearchName = schoolSearchName;
	}
	/**
	 * @param collection
	 */
	public void setGradeLevels(Collection collection)
	{
		gradeLevels = collection;
	}

	/**
	 * @param collection
	 */
	public void setExitTypes(Collection collection)
	{
		exitTypes = collection;
	}

	/**
	 * @param date
	 */
	public void setLastAttendedDateString(String dateString)
	{
		lastAttendedDateString = dateString;
	}

	/**
	 * @param collection
	 */
	public void setSchoolDistricts(Map map)
	{
		schoolDistricts = map;
	}

	/**
	 * @param collection
	 */
	public void setSchools(Map map)
	{

		schools = map;
	}

	/**
	 * @return
	 */
	public String getAppropriateGradeLevelId()
	{
		return appropriateGradeLevelId;
	}

	/**
	 * @return
	 */
	public Collection getAppropriateGradeLevels()
	{
		if (appropriateGradeLevels == null)
		{
			appropriateGradeLevels = new ArrayList();
		}
		return appropriateGradeLevels;
	}

	/**
	 * @return
	 */
	public String getGradeLevelId()
	{
		return gradeLevelId;
	}

	/**
	 * @return
	 */
	public String getExitTypeId()
	{
		return exitTypeId;
	}

	/**
	 * @return
	 */
	public String getSchoolDistrictId()
	{
		return schoolDistrictId;
	}

	/**
	 * @return
	 */
	public String getSchoolId()
	{
		return schoolId;
	}

	/**
	 * @param string
	 */
	public void setAppropriateGradeLevelId(String string)
	{
		appropriateGradeLevelId = string;
	}

	/**
	 * @param collection
	 */
	public void setAppropriateGradeLevels(Collection collection)
	{
		appropriateGradeLevels = collection;
	}

	/**
	 * @param string
	 */
	public void setGradeLevelId(String string)
	{
		gradeLevelId = string;
	}

	/**
	 * @param string
	 */
	public void setExitTypeId(String string)
	{
		exitTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setSchoolDistrictId(String string)
	{
		schoolDistrictId = string;
	}

	/**
	 * @param string
	 */
	public void setSchoolId(String string)
	{
		schoolId = string;
	}
	
	/**
	 * @return the schoolName
	 */
	public String getSchoolName() {
		return schoolName;
	}

	/**
	 * @param schoolName the schoolName to set
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	/**
	 * @return the instructionType
	 */
	public String getInstructionType() {
		return instructionType;
	}

	/**
	 * @param instructionType the instructionType to set
	 */
	public void setInstructionType(String instructionType) {
		this.instructionType = instructionType;
	}

	/**
	 * @param string
	 */
	public void setHomeSchoolDistrictId(String string)
	{
		homeSchoolDistrictId = string;
	}

	/**
	 * @param string
	 */
	public void setHomeSchoolId(String string)
	{
		homeSchoolId = string;
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
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @return
	 */
	public Collection getNewSchoolHistory()
	{
		if (this.newSchoolHistory == null)
		{
			this.newSchoolHistory = new ArrayList();
		}
		return this.newSchoolHistory;
	}

	/**
	 * @return
	 */
	public String[] getRemoveSchoolHistory()
	{
		// TODO Auto-generated method stub
		return removeSchoolHistory;
	}

	/**
	 * @param strings
	 */
	public void setRemoveSchoolHistory(String[] strings)
	{
		removeSchoolHistory = strings;
	}

	public String getTeaCode()
	{
		JuvenileSchoolDistrictCodeResponseEvent schoolCode = this.getSchoolCode();
		String teaCode = schoolCode.getTeaCode();
		return teaCode;
	}
	public String getHomeSchoolId() {
		return homeSchoolId;
	}


	/**
	 * @return
	 */
	public boolean isSuccess()
	{
		return isSuccess;
	}

	/**
	 * @param b
	 */
	public void setSuccess(boolean b)
	{
		isSuccess = b;
	}

	/**
	 * @return
	 */
	public String getGradeAverageId()
	{
		return gradeAverageId;
	}

	/**
	 * @return
	 */
	public Collection getGradesRepeated()
	{
		if (gradesRepeated == null)
		{
			gradesRepeated = new ArrayList();
		}
		return gradesRepeated;
	}

	/**
	 * @return
	 */
	public String getGradesRepeatedId()
	{
		return gradesRepeatedId;
	}

	/**
	 * @return
	 */
	public String getGradesRepeatNumber()
	{		
		return gradesRepeatNumber;
	}
	
	public int getGradeRepeatTotal()
	{
	    return gradeRepeatTotal;
	}

	public void setGradeRepeatTotal(int gradeRepeatTotal)
	{
	    this.gradeRepeatTotal = gradeRepeatTotal;
	}

	/**
	 * @return
	 */
	public Collection getParticipation()
	{
		if (participation == null)
		{
			participation = new ArrayList();
		}		
		return participation;
	}

	/**
	 * @return
	 */
	public String getSplEduCategoryId()
	{
		return splEduCategoryId;
	}
	
	/**
	 * @return
	 */
	public Collection getSplEduCategory()
	{
		if (splEduCategory == null)
		{
			splEduCategory = new ArrayList();
		}		
		return splEduCategory;
	}

	/**
	 * @return
	 */
	public String getParticipationId()
	{
		return participationId;
	}

	/**
	 * @return
	 */
	public Collection getProgramAttending()
	{
		if (programAttending == null)
		{
			programAttending = new ArrayList();
		}			
		return programAttending;
	}

	/**
	 * @return
	 */
	public String getProgramAttendingId()
	{
		return programAttendingId;
	}

	/**
	 * @return
	 */
	public Collection getRuleInfraction()
	{
		if (ruleInfraction == null)
		{
			ruleInfraction = new ArrayList();
		}	
		return ruleInfraction;
	}

	/**
	 * @return
	 */
	public String getRuleInfractionId()
	{
		return ruleInfractionId;
	}

	/**
	 * @return
	 */
	public String getSchoolDistrictDescription()
	{
		schoolDistrictDescription = null;				
		ArrayList myList=new ArrayList(schoolDistricts.values());				
		schoolDistrictDescription = CodeHelper.getCodeDescriptionByCode(myList, schoolDistrictId);		
		return schoolDistrictDescription;	
	}
	public String getHomeSchoolDistrictDescription()
	{
		homeSchoolDistrictDescription = null;				
		ArrayList myList=new ArrayList(schoolDistricts.values());				
		homeSchoolDistrictDescription = CodeHelper.getCodeDescriptionByCode(myList, homeSchoolDistrictId);		
		return homeSchoolDistrictDescription;	
	}
	
	/**
	 * @return
	 */
	public String getSchoolDisplayLiteral()
	{	schoolDisplayLiteral = null;
		JuvenileSchoolDistrictCodeResponseEvent js = this.getSchoolCode();
		if (js !=null){
			schoolDisplayLiteral =  js.getSchoolDisplayLiteral(); 
		}
		return schoolDisplayLiteral;
	}

	public String getHomeSchoolDisplayLiteral()
	{	homeSchoolDisplayLiteral = null;
		JuvenileSchoolDistrictCodeResponseEvent js = this.getHomeSchoolCode();
		if (js !=null){
			homeSchoolDisplayLiteral =  js.getSchoolDisplayLiteral(); 
		}
		return homeSchoolDisplayLiteral;
	}

	/**
	 * @return
	 */
	public String getSchoolAttendanceStatusId()
	{
		return schoolAttendanceStatusId;
	}

	/**
	 * @return
	 */
	public Collection getSchoolAttendanceStatus()
	{
		if (schoolAttendanceStatus == null)
		{
			schoolAttendanceStatus = new ArrayList();
		}	
		return schoolAttendanceStatus;
	}

	/**
	 * @return
	 */
	public String getTruancyHistory()
	{
		return truancyHistory;
	}
	/**
	 * @param gradeAverageId
	 */
	public void setGradeAverageId(String gradeAverageId)
	{
		this.gradeAverageId = gradeAverageId;
	}

	/**
	 * @param gradesRepeated
	 */
	public void setGradesRepeated(Collection gradesRepeated)
	{
		this.gradesRepeated = gradesRepeated;
	}

	/**
	 * @param gradesRepeatedId
	 */
	public void setGradesRepeatedId(String gradesRepeatedId)
	{
		this.gradesRepeatedId = gradesRepeatedId;
	}

	/**
	 * @param gradesRepeatNumber
	 */
	public void setGradesRepeatNumber(String gradesRepeatNumber)
	{
		this.gradesRepeatNumber = gradesRepeatNumber;
	}

	/**
	 * @param participation
	 */
	public void setParticipation(Collection participation)
	{
		this.participation = participation;
	}

	/**
	 * @param participationId
	 */
	public void setParticipationId(String participationId)
	{
		this.participationId = participationId;
	}
	
	/**
	 * @param participation
	 */
	public void setSplEduCategory(Collection splEduCategory)
	{
		this.splEduCategory = splEduCategory;
	}

	/**
	 * @param participationId
	 */
	public void setSplEduCategoryId(String splEduCategoryId)
	{
		this.splEduCategoryId = splEduCategoryId;
	}

	/**
	 * @param programAttending
	 */
	public void setProgramAttending(Collection programAttending)
	{
		this.programAttending = programAttending;
	}

	/**
	 * @param programAttendingId
	 */
	public void setProgramAttendingId(String programAttendingId)
	{
		this.programAttendingId = programAttendingId;
	}

	/**
	 * @param ruleInfraction
	 */
	public void setRuleInfraction(Collection ruleInfraction)
	{
		this.ruleInfraction = ruleInfraction;
	}

	/**
	 * @param ruleInfractionId
	 */
	public void setRuleInfractionId(String ruleInfractionId)
	{
		this.ruleInfractionId = ruleInfractionId;
	}



	/**
	 * @param schoolAttendanceId
	 */
	public void setSchoolAttendanceStatusId(String schoolAttendanceStatusId)
	{
		this.schoolAttendanceStatusId = schoolAttendanceStatusId;
	}

	/**
	 * @param schoolAttendanceStatus
	 */
	public void setSchoolAttendanceStatus(Collection schoolAttendanceStatus)
	{
		this.schoolAttendanceStatus = schoolAttendanceStatus;
	}

	/**
	 * @param truancyHistory
	 */
	public void setTruancyHistory(String truancyHistory)
	{
		this.truancyHistory = truancyHistory;
	}

	/**
	 * @return
	 */
	public String getVerifiedDateString()
	{
		return verifiedDateString;
	}

	/**
	 * @param string
	 */
	public void setVerifiedDateString(String verifiedDateString)
	{
		this.verifiedDateString = verifiedDateString;
	}

	/**
	 * @return
	 */
	public String getAppropriateGradeLevelDescription()
	{
		appropriateGradeLevelDescription =
			CodeHelper.getCodeDescriptionByCode(appropriateGradeLevels,appropriateGradeLevelId);	
		return appropriateGradeLevelDescription;
	}

	/**
	 * @return
	 */
	public String getExitTypeDescription()
	{
		exitTypeDescription =
			CodeHelper.getCodeDescriptionByCode(exitTypes,exitTypeId);	
		return exitTypeDescription;
	}

	/**
	 * @return
	 */
	public String getGradeLevelDescription()
	{
		gradeLevelDescription =
			CodeHelper.getCodeDescriptionByCode(gradeLevels,gradeLevelId);	
		return gradeLevelDescription;
	}
	
	

	/**
	 * @return
	 */
	public String getParticipationDescription()
	{
		participationDescription =
			CodeHelper.getCodeDescriptionByCode(participation,participationId);	
		return participationDescription;
	}
	
	/**
	 * @return
	 */
	public String getSplEduCategoryDescription()
	{
		splEduCategoryDescription = CodeHelper.getCodeDescription(PDCodeTableConstants.JUV_SPL_EDU_HANDICAPPING_COND, splEduCategoryId); //Bug Report #29768	
		return splEduCategoryDescription;
	}

	/**
	 * @return
	 */
	public String getProgramAttendingDescription()
	{
		programAttendingDescription =
			CodeHelper.getCodeDescriptionByCode(programAttending,programAttendingId);	
		return programAttendingDescription;
	}

	/**
	 * @return
	 */
	public String getRuleInfractionDescription()
	{
		ruleInfractionDescription =
			CodeHelper.getCodeDescriptionByCode(ruleInfraction,ruleInfractionId);	
		return ruleInfractionDescription;
	}

	/**
	 * @return
	 */
	public String getSchoolAttendanceStatusDescription()
	{
		schoolAttendanceStatusDescription =
			CodeHelper.getCodeDescriptionByCode(allSchoolAttendanceStatus,schoolAttendanceStatusId);	
		return schoolAttendanceStatusDescription;

	}

	/**
	 * @return
	 */
	public String getGradesRepeatedDescription()
	{
		gradesRepeatedDescription =
			CodeHelper.getCodeDescriptionByCode(gradesRepeated,gradesRepeatedId);
		return gradesRepeatedDescription;
	}

	/**
	 * @return
	 */
	public String getSecondaryAction()
	{
		return secondaryAction;
	}

	/**
	 * @param string
	 */
	public void setSecondaryAction(String string)
	{
		secondaryAction = string;
	}

	/**
	 * @return Returns the entryDate.
	 */
	public String getEntryDate() 
	{
		return entryDate;
	}
	/**
	 * @param entryDate The entryDate to set.
	 */
	public void setEntryDate(String entryDate) 
	{
		this.entryDate = entryDate;
	}
	public String getHomeSchoolDistrictId() {
		return homeSchoolDistrictId;
	}
	
	/**
	 * @return Returns the currentSchoolDetails.
	 */
	public JuvenileSchoolHistoryResponseEvent getCurrentSchoolDetails() {
		return currentSchoolDetails;
	}
	/**
	 * @param currentSchoolDetails The currentSchoolDetails to set.
	 */
	public void setCurrentSchoolDetails(JuvenileSchoolHistoryResponseEvent currentSchoolDetails) {
		this.currentSchoolDetails = currentSchoolDetails;
	}
	public void setSchoolHistoryDetails(JuvenileSchoolHistoryResponseEvent schoolHistory)
	{
		this.setEntryDate(DateUtil.dateToString(schoolHistory.getCreateDate(), UIConstants.DATE_FMT_1));
		this.setSchoolId(schoolHistory.getSchoolId());
		this.setSchoolDistrictId(schoolHistory.getSchoolDistrictId());
		//added for the bug 11307
		this.setSchoolName(schoolHistory.getSchool());
		this.setInstructionType(schoolHistory.getInstructionType());
		//end of bug 11307
		this.setLastAttendedDateString(schoolHistory.getLastAttendedDateString());
		if (schoolHistory.getVerifiedDate()!=null){
			this.setVerifiedDateString(DateUtil.dateToString(schoolHistory.getVerifiedDate(), UIConstants.DATE_FMT_1));
		}
		//ER Changes JIMS200077279 starts
		if (schoolHistory.getEligibilityEnrollmentDate()!=null){
			this.setEligibilityEnrollmentDate(DateUtil.dateToString(schoolHistory.getEligibilityEnrollmentDate(), UIConstants.DATE_FMT_1));
		}
		//ER Changes JIMS200077279 ends
		this.setGradeLevelId(schoolHistory.getGradeLevelCode());
		this.setGradesRepeatedId(schoolHistory.getGradesRepeatedCode());
		this.setAppropriateGradeLevelId(schoolHistory.getAppropriateLevelCode());
		this.setReasonForGradeLevelChange(schoolHistory.getGradeChangeReason());
		this.setExitTypeId(schoolHistory.getExitTypeCode());
		this.setGradeAverageId(schoolHistory.getGradeAverage());
		this.setGradesRepeatNumber(schoolHistory.getGradesRepeatNumber());
		this.setParticipationId(schoolHistory.getParticipationCode());
		this.setSplEduCategoryId(schoolHistory.getSplEduCategoryCode()); //added for spl edu
		this.setProgramAttendingId(schoolHistory.getProgramAttendingCode());
		this.setRuleInfractionId(schoolHistory.getRuleInfractionCode());
		this.setSchoolAttendanceStatusId(schoolHistory.getSchoolAttendanceStatusCode());
		this.setTruancyHistory(schoolHistory.getTruancyHistory());
		this.setTruancy(schoolHistory.getTruancy());
		this.setCurrentSchoolDetails(schoolHistory);
		this.setHomeSchoolId(schoolHistory.getHomeSchoolId());
		this.setHomeSchoolDistrictId(schoolHistory.getHomeSchoolDistrictId());
		this.setSpecificSchoolName(schoolHistory.getSpecificSchoolName());
		this.setEducationService(schoolHistory.getEducationService());
		this.setGradeRepeatTotal(schoolHistory.getGradeRepeatTotal());
		this.setAcademicPerformanceDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ACADEMIC_PERFORMANCE,schoolHistory.getAcademicPerformance()));
		this.setSchoolInfoVerifiedByDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SCHOOL_INFO_VERIFICATION,schoolHistory.getSchoolInfoVerifiedBy()));
		
		if (!"".equals(this.getSpecificSchoolName() ) )
		{
			String temp = "";
			StringBuffer addr = new StringBuffer();
			if (schoolHistory.getSchoolStreetNum()!=null && !"".equals(schoolHistory.getSchoolStreetNum()))
	    	{	
		    	addr.append(schoolHistory.getSchoolStreetNum());
		    	addr.append(" ");
	    	}
	    	if (schoolHistory.getSchoolStreetName()!=null && !"".equals(schoolHistory.getSchoolStreetName()))
	    	{	
	    		addr.append(schoolHistory.getSchoolStreetName());
	    		addr.append(", ");
	    	}
	    	if(schoolHistory.getSchoolCity()!=null && !"".equals(schoolHistory.getSchoolCity()))
	    	{
		    	addr.append(schoolHistory.getSchoolCity());
		    	addr.append(" ");
	    	}
	    	if (schoolHistory.getSchoolState()!=null && !"".equals(schoolHistory.getSchoolState()))
	    	{
	    		temp = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.STATE_ABBR,schoolHistory.getSchoolState());
		    	addr.append(temp);
		    	addr.append(" ");
	    	}
	    	addr.append(schoolHistory.getSchoolZip());
	    	if (schoolHistory.getSchoolZipCodeExt()!=null && !"".equals(schoolHistory.getSchoolZipCodeExt()))
	    	{	
	    		addr.append("-");
	    		addr.append(schoolHistory.getSchoolZipCodeExt());
	    	}
	    	temp = addr.toString();
	    	this.setSpecificSchoolAddress(temp.trim());
	    	addr = null;
	    	temp = null;
			
		}
		else
			this.setSpecificSchoolAddress("");
	}

	public Collection getAllSchoolAttendanceStatus() {
		return allSchoolAttendanceStatus;
	}

	public void setAllSchoolAttendanceStatus(Collection allSchoolAttendanceStatus) {
		this.allSchoolAttendanceStatus = allSchoolAttendanceStatus;
	}
	
	public boolean getIsAlternativeSchool() {
		
		boolean isAlternativeSchool = false;
		JuvenileSchoolDistrictCodeResponseEvent re = getSchoolCode();
		
		if (re != null ){
			String districtCode = re.getDistrictCode();
			if ( "053".equals(districtCode )
				|| "054".equals(districtCode )
				|| "055".equals(districtCode )
				|| "A".equals(re.getSubGroupInd() ) ) {
					isAlternativeSchool = true;
			}
			districtCode = null;
		}
		
		return isAlternativeSchool;
	}

	public void setHomeSchoolSearchInd(String homeSchoolSearchInd) {
		this.homeSchoolSearchInd = homeSchoolSearchInd;
	}

	public String getHomeSchoolSearchInd() {
		return homeSchoolSearchInd;
	}

	/**
	 * @return the existingGEDPrograms
	 */
	public List getExistingGEDPrograms() {
		return existingGEDPrograms;
	}

	/**
	 * @param existingGEDPrograms the existingGEDPrograms to set
	 */
	public void setExistingGEDPrograms(List existingGEDPrograms) {
		this.existingGEDPrograms = existingGEDPrograms;
	}

	/**
	 * @return the specificSchoolAddress
	 */
	public String getSpecificSchoolAddress() {
		return specificSchoolAddress;
	}

	/**
	 * @param specificSchoolAddress the specificSchoolAddress to set
	 */
	public void setSpecificSchoolAddress(String specificSchoolAddress) {
		this.specificSchoolAddress = specificSchoolAddress;
	}

	/**
	 * @return the specificSchoolName
	 */
	public String getSpecificSchoolName() {
		return specificSchoolName;
	}

	/**
	 * @param specificSchoolName the specificSchoolName to set
	 */
	public void setSpecificSchoolName(String specificSchoolName) {
		this.specificSchoolName = specificSchoolName;
	}

	/**
	 * @return the streetNum
	 */
	public String getStreetNum() {
		return streetNum;
	}

	/**
	 * @param streetNum the streetNum to set
	 */
	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the stateId
	 */
	public String getStateId() {
		return stateId;
	}

	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the stateDesc
	 */
	public String getStateDesc() {
		return stateDesc;
	}

	/**
	 * @param stateDesc the stateDesc to set
	 */
	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
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
	 * @return the zipCodeExt
	 */
	public String getZipCodeExt() {
		return zipCodeExt;
	}

	/**
	 * @param zipCodeExt the zipCodeExt to set
	 */
	public void setZipCodeExt(String zipCodeExt) {
		this.zipCodeExt = zipCodeExt;
	}

	/**
	 * @return the states
	 */
	public List getStates() {
		return states;
	}

	/**
	 * @param states the states to set
	 */
	public void setStates(List states) {
		this.states = states;
	}


	/**
	 * @return the unknownNameInd
	 */
	public String getUnknownNameInd() {
		return unknownNameInd;
	}

	/**
	 * @param unknownNameInd the unknownNameInd to set
	 */
	public void setUnknownNameInd(String unknownNameInd) {
		this.unknownNameInd = unknownNameInd;
	}

	/**
	 * @return the backFlowIndicator
	 */
	public String getBackFlowIndicator() {
		return backFlowIndicator;
	}

	/**
	 * @param backFlowIndicator the backFlowIndicator to set
	 */
	public void setBackFlowIndicator(String backFlowIndicator) {
		this.backFlowIndicator = backFlowIndicator;
	}
// SocialHistoryReportDataTO

	/**
	 * @return the socialHistoryData
	 */
	public SocialHistoryReportDataTO getSocialHistoryData() {
		return socialHistoryData;
	}

	/**
	 * @param socialHistoryData the socialHistoryData to set
	 */
	public void setSocialHistoryData(SocialHistoryReportDataTO socialHistoryData) {
		this.socialHistoryData = socialHistoryData;
	}

	/**
	 * @return the documents
	 */
	public List getDocuments() {
		return documents;
	}

	/**
	 * @param documents the documents to set
	 */
	public void setDocuments(List documents) {
		this.documents = documents;
	}
	
	/**
	 * @return the reportGenerated
	 */
	public String getReportGenerated() {
		return reportGenerated;
	}

	/**
	 * @param reportGenerated the reportGenerated to set
	 */
	public void setReportGenerated(String reportGenerated) {
		this.reportGenerated = reportGenerated;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	/**
	 * @return the educationId
	 */
	public String getEducationId() {
		return educationId;
	}
	/**
	 * @param educationId the educationId to set
	 */
	public void setEducationId(String educationId) {
		this.educationId = educationId;
	}

	/**
	 * @param eligibilityEnrollmentDate the eligibilityEnrollmentDate to set
	 */
	public void setEligibilityEnrollmentDate(String eligibilityEnrollmentDate) {
		this.eligibilityEnrollmentDate = eligibilityEnrollmentDate;
	}

	/**
	 * @return the eligibilityEnrollmentDate
	 */
	public String getEligibilityEnrollmentDate() {
		return eligibilityEnrollmentDate;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * @param hasFeature the hasFeature to set
	 */
	public void setHasFeature(boolean hasFeature) {
		this.hasFeature = hasFeature;
	}

	/**
	 * @return the hasFeature
	 */
	public boolean isHasFeature() {
		return hasFeature;
	}

	public String getCreateDate()
	{
	    return createDate;
	}

	public void setCreateDate(String createDate)
	{
	    this.createDate = createDate;
	}

	public String getEducationService()
	{
	    return educationService;
	}

	public void setEducationService(String educationService)
	{
	    this.educationService = educationService;
	}

	public String getReasonForGradeLevelChange()
	{
	    return reasonForGradeLevelChange;
	}

	public void setReasonForGradeLevelChange(String reasonForGradeLevelChange)
	{
	    this.reasonForGradeLevelChange = reasonForGradeLevelChange;
	}

	public Collection getVerificationCodes()
	{
	    return CodeHelper.getCodes(PDCodeTableConstants.SCHOOL_INFO_VERIFICATION, true);
	}

	public void setVerificationCodes(Collection verificationCodes)
	{
	    this.verificationCodes = verificationCodes;
	}

	public Collection getAcademicCodes()
	{
	    return CodeHelper.getCodes(PDCodeTableConstants.ACADEMIC_PERFORMANCE, true);
	}

	public void setAcademicCodes(Collection academicCodes)
	{
	    this.academicCodes = academicCodes;
	}

	public String getAcademicPerformance()
	{
	    return academicPerformance;
	}

	public void setAcademicPerformance(String academicPerformance)
	{
	    this.academicPerformance = academicPerformance;
	}

	public String getAcademicPerformanceDesc()
	{
	    return academicPerformanceDesc;
	}

	public void setAcademicPerformanceDesc(String academicPerformanceDesc)
	{
	    this.academicPerformanceDesc = academicPerformanceDesc;
	}

	public String getSchoolInfoVerifiedByDesc()
	{
	    return schoolInfoVerifiedByDesc;
	}

	public void setSchoolInfoVerifiedByDesc(String schoolInfoVerifiedByDesc)
	{
	    this.schoolInfoVerifiedByDesc = schoolInfoVerifiedByDesc;
	}

	public String getSchoolInfoVerifiedBy()
	{
	    return schoolInfoVerifiedBy;
	}

	public void setSchoolInfoVerifiedBy(String schoolInfoVerifiedBy)
	{
	    this.schoolInfoVerifiedBy = schoolInfoVerifiedBy;
	}

	public String getSchoolVerfifyOther()
	{
	    return schoolVerfifyOther;
	}

	public void setSchoolVerfifyOther(String schoolVerfifyOther)
	{
	    this.schoolVerfifyOther = schoolVerfifyOther;
	}

	public boolean isTruancy()
	{
	    return truancy;
	}

	public void setTruancy(boolean truancy)
	{
	    this.truancy = truancy;
	}

	public boolean isAwarded()
	{
	    return awarded;
	}

	public void setAwarded(boolean awarded)
	{
	    this.awarded = awarded;
	}

	public String getAwardedDateStr()
	{
	    return awardedDateStr;
	}

	public void setAwardedDateStr(String awardedDateStr)
	{
	    this.awardedDateStr = awardedDateStr;
	}

	public String getCompletionDateStr()
	{
	    return completionDateStr;
	}

	public void setCompletionDateStr(String completionDateStr)
	{
	    this.completionDateStr = completionDateStr;
	}

	public boolean isGedCompleted()
	{
	    return gedCompleted;
	}

	public void setGedCompleted(boolean gedCompleted)
	{
	    this.gedCompleted = gedCompleted;
	}	
}
