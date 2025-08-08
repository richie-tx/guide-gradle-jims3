/*
 * Created on Jul 18, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.casefile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import naming.PDCodeTableConstants;
import ui.common.Address;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.casefile.form.CommonAppForm.CourtOrder;
import ui.juvenilecase.form.JuvenileMemberForm;
/**
 * @author C_NAggarwal
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CommonAppReportPrintBean
{
	
	// Page 1 - first section - Screening profile
	
	private String juvName;
	private String stateID;
	private String juvDOB;
	private String juvAge;
	private String juvSSN;
	private String juvSex;
	private String juvEthnicity;
	private String juvPrimaryLanguage;
	private String juvBirthCity;
	private String juvBirthState;
	private String juvBirthCounty;
	private String juvAgencyID; //This is Juvenile Number
	private String juvHeight;
	private String juvWeight;
	private String juvReligiousPreference;
	private String juvCurrentLocation; //Facility Stay
	private String juvCountryOfCitizenship; //County of Citizenship
	private String contrlrefID;// Controlling refernece Number * 35996*
	
	// Page 1 - second section - Special needs, Programs, Behaviours
	
	private String childConsideredDangerToSelf; //Trait Type DR02
	private String childConsideredDangerToOthers; //Trait Type DR01
	private int noRunawaysFromHome; // Behaviour History.numberOfRunaways
	private String historyOfSettingFires; //Trait Type DV14
	
	private Collection specialNeedsSpecify; //If Trait Type 65 is yes, then print all comments for this trait type for juv
	private String specialNeedsMaternity="NO"; //If Trait Type 65 is yes, then check for trait type 104
	private String specialNeedsPreparationForAdultLiving="NO"; //If Trait Type 65 is yes, then check for trait type 106
	private String specialNeedsOthers="NO"; //If Trait Type 65 is yes, then check for trait type with parenttype_id 65
	
	// Page 1 - section 5 - Substance Abuse History
	private String substanceAbuseHistory="NO"; //default to NO
	private Collection drugTypeAndDegree;
	private String specializedProgramReq="NO"; //Trait Type 16
	private String specializedProgramSpecify; //default to NO
	
	// Page 1 - section 3 - Juvenile Justice History
	
	private int countAdjudicationsTotal = 0; //deliquent acts tot count
	private int countCINSAdjudication = 0;
	private int countReferrals;
	//bug 31958 fix 
	private int totalNumberOfAdjudications=0; //total no.of adjudications/certifications.
	private String currentOffense;
	private Collection referrals; 
	
	private String childAdmitGangAffiliation;
	private List juvenileGangInfo; 
	private String anyFamilyMemberHaveGangAffiliation;
	private List familyMemberGangInfo;
	
	//Section 2 Special Needs Data
	private List suicideHistoryTraitsInfo;
	private List assaultiveBehaviorTraitsInfo;
	private List runawayHistoryTraitsInfo;
	
	//Section 3 - TYC Commitment
	CourtOrder courtOrder = new CourtOrder();
	private String juvenileNumber = "";
	private String sidNumber = "";
		
	//Section 4 - Placement History
	private double averageNumberOfChildCareDays = 0;
	private String numberOfFailedAdoptionPlacement = "";
	private Collection placements;
	private int outofHomePlacements;
	private String recentOutOfHomeDischargeDate; //added for JIMS200077404
	private String locCurrentOutofHomePlacement;
	private String dischargeReason;
	
	// History of Abuse neglect of referred Child
	private String abuseNeglectHistory="NO"; //Default to NO.
	private Collection abuseTypeAndDegree;
	private String abandonment = "NO"; //Trait Type 16 // default to NO
	private Collection juvenileAbuseResponseEvent = null;

	private Collection JUVENILE_EXIT_REPORT;
	private Map answerMap;
	
	//Section 7 - Family History
	private Address familyAddress = new Address();
	private JuvenileMemberForm.MemberContact familyPhone;
	private String birthParentsMaritalStatus;
	
	//ER Changes 13442
	private String adoptedParentsMaritalStatus;
	private String adoptionComments;
	private String familyMemberComments;
	private String managingConservator;
	private List deceasedFamilyMembers;
	
	private List personsInHome;
	private List personsOutHome;
	
	private List familyMembersTraits;
	private List familyDynamicTraits;
	
	private String MotherParentalRightsTerminated="NO";//default to NO
	private String FatherParentalRightsTerminated="NO";//default to NO
	
	
//Section 9 Education
	//data from record having most recent entry date
	private String currentGradeLevel;
	private String currentlyAttending;
	private String programAttending;
	private String trauncyHistory; // true if there is trauncy history in any record
	private String teaSchoolNumber;
	private String schoolName;
	private String schoolDistrict;
	//Bug Fix : 11239 starts
	private String schoolAddress;
	private String schoolCity;
	private String schoolState;
	private String schoolZip;
	//Bug Fix : 11239 ends
	
	//Following date for Education section is from mental health use case
	private String fullScore;
	private String performanceScore;
	private String verbalScore;
	private Date testDate;
	private String testName;
	
//Section 10 Physical Health/ Disabilities
	private Collection healthIssues;
	private Collection medications;
	private String hasMedicalIssue="NO"; //Er changes 11454 //Default to NO
	
// Section 11 mental health
	private Date testingSessionDate;
	
//Section 8 Financial information
	private Collection guardianFinancialInfo=new ArrayList();
	private List insuranceApplicableToChild = new ArrayList();
		
	private String EligibleForMedicaid;
	private String ReceivingMedicaid;	
	
	// Page 1 - Section 11 Mental Health
	
	private Collection dsmResults;
	private String userCompletingCommonAppFormName;
	
	/**
	 * @param juvenile_exit_report The jUVENILE_EXIT_REPORT to set.
	 */
	public void setJUVENILE_EXIT_REPORT(Collection juvenile_exit_report) {
		JUVENILE_EXIT_REPORT = juvenile_exit_report;
	}

	
	public void setRow(CommonAppReportTableRowBean tableRow)
	{
		if(JUVENILE_EXIT_REPORT == null)
		{
			this.JUVENILE_EXIT_REPORT = new ArrayList();
		}
		JUVENILE_EXIT_REPORT.add(tableRow);
	}

	/**
	 * @return
	 */
	public Collection getJUVENILE_EXIT_REPORT()
	{
		return JUVENILE_EXIT_REPORT;
	}

	public String getAnswers(String answerKey)
	{
		if (answerMap == null)
		{
			answerMap = new HashMap();
		}
		return (String) answerMap.get(answerKey);
	}

	public void addAnswer(String answerKey, String answer)
	{
		if (answerMap == null)
		{
			answerMap = new HashMap();
		}
		answerMap.put(answerKey, answer);
	}
	
	/**
	 * @return Returns the answerMap.
	 */
	public Map getAnswerMap() {
		return answerMap;
	}
	/**
	 * @param answerMap The answerMap to set.
	 */
	public void setAnswerMap(Map answerMap) {
		this.answerMap = answerMap;
	}
	/**
	 * @return Returns the schoolDistrict.
	 */
	public String getSchoolDistrict() {
		return schoolDistrict;
	}
	/**
	 * @param schoolDistrict The schoolDistrict to set.
	 */
	public void setSchoolDistrict(String schoolDistrict) {
		this.schoolDistrict = schoolDistrict;
	}
	/**
	 * @return Returns the schoolName.
	 */
	public String getSchoolName() {
		return schoolName;
	}
	/**
	 * @param schoolName The schoolName to set.
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

		

	/**
	 * @return Returns the juvAge.
	 */
	public String getJuvAge() {
		return juvAge;
	}
	/**
	 * @param juvAge The juvAge to set.
	 */
	public void setJuvAge(String juvAge) {
		this.juvAge = juvAge;
	}
	/**
	 * @return Returns the juvAgencyID.
	 */
	public String getJuvAgencyID() {
		return juvAgencyID;
	}
	/**
	 * @param juvAgencyID The juvAgencyID to set.
	 */
	public void setJuvAgencyID(String juvAgencyID) {
		this.juvAgencyID = juvAgencyID;
	}
	/**
	 * @return Returns the juvBirthCity.
	 */
	public String getJuvBirthCity() {
		return juvBirthCity;
	}
	/**
	 * @param juvBirthCity The juvBirthCity to set.
	 */
	public void setJuvBirthCity(String juvBirthCity) {
		this.juvBirthCity = juvBirthCity;
	}
	/**
	 * @return Returns the juvBirthCounty.
	 */
	public String getJuvBirthCounty() {
		return juvBirthCounty;
	}
	/**
	 * @param juvBirthCounty The juvBirthCounty to set.
	 */
	public void setJuvBirthCounty(String juvBirthCounty) {
		this.juvBirthCounty = juvBirthCounty;
	}
	/**
	 * @return Returns the juvBirthState.
	 */
	public String getJuvBirthState() {
		return juvBirthState;
	}
	/**
	 * @param juvBirthState The juvBirthState to set.
	 */
	public void setJuvBirthState(String juvBirthState) {
		this.juvBirthState = juvBirthState;
	}
	/**
	 * @return Returns the juvCountryOfCitizenship.
	 */
	public String getJuvCountryOfCitizenship() {
		return juvCountryOfCitizenship;
	}
	/**
	 * @param juvCountryOfCitizenship The juvCountryOfCitizenship to set.
	 */
	public void setJuvCountryOfCitizenship(String juvCountryOfCitizenship) {
		this.juvCountryOfCitizenship = juvCountryOfCitizenship;
	}
	/**
	 * @return Returns the juvCurrentLocation.
	 */
	public String getJuvCurrentLocation() {
		return juvCurrentLocation;
	}
	/**
	 * @param juvCurrentLocation The juvCurrentLocation to set.
	 */
	public void setJuvCurrentLocation(String juvCurrentLocation) {
		this.juvCurrentLocation = juvCurrentLocation;
	}
	/**
	 * @return Returns the juvDOB.
	 */
	public String getJuvDOB() {
		return juvDOB;
	}
	/**
	 * @param juvDOB The juvDOB to set.
	 */
	public void setJuvDOB(String juvDOB) {
		this.juvDOB = juvDOB;
	}
	/**
	 * @return Returns the juvEthnicity.
	 */
	public String getJuvEthnicity() {
		return juvEthnicity;
	}
	/**
	 * @param juvEthnicity The juvEthnicity to set.
	 */
	public void setJuvEthnicity(String juvEthnicity) {
		this.juvEthnicity = juvEthnicity;
	}
	/**
	 * @return Returns the juvHeight.
	 */
	public String getJuvHeight() {
		return juvHeight;
	}
	/**
	 * @param juvHeight The juvHeight to set.
	 */
	public void setJuvHeight(String juvHeight) {
		this.juvHeight = juvHeight;
	}
	/**
	 * @return Returns the juvName.
	 */
	public String getJuvName() {
		return juvName;
	}
	/**
	 * @param juvName The juvName to set.
	 */
	public void setJuvName(String juvName) {
		this.juvName = juvName;
	}
	/**
	 * @return Returns the juvPrimaryLanguage.
	 */
	public String getJuvPrimaryLanguage() {
		return juvPrimaryLanguage;
	}
	/**
	 * @param juvPrimaryLanguage The juvPrimaryLanguage to set.
	 */
	public void setJuvPrimaryLanguage(String juvPrimaryLanguage) {
		this.juvPrimaryLanguage = juvPrimaryLanguage;
	}
	/**
	 * @return Returns the juvReligiousPreference.
	 */
	public String getJuvReligiousPreference() {
		return juvReligiousPreference;
	}
	/**
	 * @param juvReligiousPreference The juvReligiousPreference to set.
	 */
	public void setJuvReligiousPreference(String juvReligiousPreference) {
		this.juvReligiousPreference = juvReligiousPreference;
	}
	/**
	 * @return Returns the juvSex.
	 */
	public String getJuvSex() {
		return juvSex;
	}
	/**
	 * @param juvSex The juvSex to set.
	 */
	public void setJuvSex(String juvSex) {
		this.juvSex = juvSex;
	}
	/**
	 * @return Returns the juvSSN.
	 */
	public String getJuvSSN() {
		return juvSSN;
	}
	/**
	 * @param juvSSN The juvSSN to set.
	 */
	public void setJuvSSN(String juvSSN) {
		this.juvSSN = juvSSN;
	}
	/**
	 * @return Returns the juvWeight.
	 */
	public String getJuvWeight() {
		return juvWeight;
	}
	/**
	 * @param juvWeight The juvWeight to set.
	 */
	public void setJuvWeight(String juvWeight) {
		this.juvWeight = juvWeight;
	}
	/**
	 * @return Returns the stateID.
	 */
	public String getStateID() {
		return stateID;
	}
	/**
	 * @param stateID The stateID to set.
	 */
	public void setStateID(String stateID) {
		this.stateID = stateID;
	}


	/**
	 * @return Returns the noRunawaysFromHome.
	 */
	public int getNoRunawaysFromHome() {
		return noRunawaysFromHome;
	}
	/**
	 * @param noRunawaysFromHome The noRunawaysFromHome to set.
	 */
	public void setNoRunawaysFromHome(int noRunawaysFromHome) {
		this.noRunawaysFromHome = noRunawaysFromHome;
	}


	/**
	 * @return Returns the specialNeedsSpecify.
	 */
	public Collection getSpecialNeedsSpecify() {
		return specialNeedsSpecify;
	}
	/**
	 * @param specialNeedsSpecify The specialNeedsSpecify to set.
	 */
	public void setSpecialNeedsSpecify(Collection specialNeedsSpecify) {
		this.specialNeedsSpecify = specialNeedsSpecify;
	}

	/**
	 * @return Returns the specializedProgramSpecify.
	 */
	public String getSpecializedProgramSpecify() {
		return specializedProgramSpecify;
	}
	/**
	 * @param specializedProgramSpecify The specializedProgramSpecify to set.
	 */
	public void setSpecializedProgramSpecify(String specializedProgramSpecify) {
		this.specializedProgramSpecify = specializedProgramSpecify;
	}

	/**
	 * @return Returns the drugTypeAndDegree.
	 */
	public Collection getDrugTypeAndDegree() {
		return drugTypeAndDegree;
	}
	/**
	 * @param drugTypeAndDegree The drugTypeAndDegree to set.
	 */
	public void setDrugTypeAndDegree(Collection drugTypeAndDegree) {
		this.drugTypeAndDegree = drugTypeAndDegree;
	}


	/**
	 * @return Returns the abuseTypeAndDegree.
	 */
	public Collection getAbuseTypeAndDegree() {
		return abuseTypeAndDegree;
	}
	/**
	 * @param abuseTypeAndDegree The abuseTypeAndDegree to set.
	 */
	public void setAbuseTypeAndDegree(Collection abuseTypeAndDegree) {
		this.abuseTypeAndDegree = abuseTypeAndDegree;
	}
/**
 * @return Returns the currentGradeLevel.
 */
public String getCurrentGradeLevel() {
	return currentGradeLevel;
}
/**
 * @param currentGradeLevel The currentGradeLevel to set.
 */
public void setCurrentGradeLevel(String currentGradeLevel) {
	this.currentGradeLevel = currentGradeLevel;
}

	/**
	 * @return Returns the teaSchoolNumber.
	 */
	public String getTeaSchoolNumber() {
		return teaSchoolNumber;
	}
	/**
	 * @param teaSchoolNumber The teaSchoolNumber to set.
	 */
	public void setTeaSchoolNumber(String teaSchoolNumber) {
		this.teaSchoolNumber = teaSchoolNumber;
	}
	/**
	 * @return Returns the testDate.
	 */
	public Date getTestDate() {
		return testDate;
	}
	/**
	 * @param testDate The testDate to set.
	 */
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	/**
	 * @return Returns the testName.
	 */
	public String getTestName() {
		return testName;
	}
	/**
	 * @param testName The testName to set.
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}
	
	/**
	 * @return Returns the juvenileAbuseResponseEvent.
	 */
	public Collection getJuvenileAbuseResponseEvent() {
		return juvenileAbuseResponseEvent;
	}
	/**
	 * @param juvenileAbuseResponseEvent The juvenileAbuseResponseEvent to set.
	 */
	public void setJuvenileAbuseResponseEvent(Collection juvenileAbuseResponseEvent) {
		this.juvenileAbuseResponseEvent = juvenileAbuseResponseEvent;
	}
	/**
	 * @return Returns the programAttending.
	 */
	public String getProgramAttending() {
		return programAttending;
	}
	/**
	 * @param programAttending The programAttending to set.
	 */
	public void setProgramAttending(String programAttending) {
		this.programAttending = programAttending;
	}

	/**
	 * @return Returns the fullScore.
	 */
	public String getFullScore() {
		return fullScore;
	}
	/**
	 * @param fullScore The fullScore to set.
	 */
	public void setFullScore(String fullScore) {
		this.fullScore = fullScore;
	}
	/**
	 * @return Returns the performanceScore.
	 */
	public String getPerformanceScore() {
		return performanceScore;
	}
	/**
	 * @param performanceScore The performanceScore to set.
	 */
	public void setPerformanceScore(String performanceScore) {
		this.performanceScore = performanceScore;
	}
	/**
	 * @return Returns the verbalScore.
	 */
	public String getVerbalScore() {
		return verbalScore;
	}
	/**
	 * @param verbalScore The verbalScore to set.
	 */
	public void setVerbalScore(String verbalScore) {
		this.verbalScore = verbalScore;
	}
	/**
	 * @return Returns the healthIssues.
	 */
	public Collection getHealthIssues() {
		return healthIssues;
	}
	/**
	 * @param healthIssues The healthIssues to set.
	 */
	public void setHealthIssues(Collection healthIssues) {
		this.healthIssues = healthIssues;
	}
	/**
	 * @return Returns the medications.
	 */
	public Collection getMedications() {
		return medications;
	}
	/**
	 * @param medications The medications to set.
	 */
	public void setMedications(Collection medications) {
		this.medications = medications;
	}
/**
 * @return Returns the testingSessionDate.
 */
public Date getTestingSessionDate() {
	return testingSessionDate;
}
/**
 * @param testingSessionDate The testingSessionDate to set.
 */
public void setTestingSessionDate(Date testingSessionDate) {
	this.testingSessionDate = testingSessionDate;
}
	/**
	 * @return Returns the countAdjudicationsTotal.
	 */
	public int getCountAdjudicationsTotal() {
		return countAdjudicationsTotal;
	}
	/**
	 * @param countAdjudicationsTotal The countAdjudicationsTotal to set.
	 */
	public void setCountAdjudicationsTotal(int countAdjudicationsTotal) {
		this.countAdjudicationsTotal = countAdjudicationsTotal;
	}
	/**
	 * @return Returns the countCINSAdjudication.
	 */
	public int getCountCINSAdjudication() {
		return countCINSAdjudication;
	}
	/**
	 * @param countCINSAdjudication The countCINSAdjudication to set.
	 */
	public void setCountCINSAdjudication(int countCINSAdjudication) {
		this.countCINSAdjudication = countCINSAdjudication;
	}
	/**
	 * @return Returns the countReferrals.
	 */
	public int getCountReferrals() {
		return countReferrals;
	}
	/**
	 * @param countReferrals The countReferrals to set.
	 */
	public void setCountReferrals(int countReferrals) {
		this.countReferrals = countReferrals;
	}
	/**
	 * @return Returns the currentOffense.
	 */
	public String getCurrentOffense() {
		return currentOffense;
	}
	/**
	 * @param currentOffense The currentOffense to set.
	 */
	public void setCurrentOffense(String currentOffense) {
		this.currentOffense = currentOffense;
	}
	/**
	 * @return Returns the referrals.
	 */
	public Collection getReferrals() {
		return referrals;
	}
	/**
	 * @param referrals The referrals to set.
	 */
	public void setReferrals(Collection referrals) {
		this.referrals = referrals;
	}
	/**
	 * @return Returns the familyAddress.
	 */
	public Address getFamilyAddress() {
			return familyAddress;
	}
	/**
	 * @param familyAddress The familyAddress to set.
	 */
	public void setFamilyAddress(Address familyAddress) {
		this.familyAddress = familyAddress;
	}
	/**
	 * @return Returns the familyPhone.
	 */
	public JuvenileMemberForm.MemberContact getFamilyPhone() {
		return familyPhone;
	}
	/**
	 * @param familyPhone The familyPhone to set.
	 */
	public void setFamilyPhone(JuvenileMemberForm.MemberContact familyPhone) {
		this.familyPhone = familyPhone;
	}
	
	/**
	 * @return Returns the adoptionComments.
	 */
	public String getAdoptionComments() {
		return adoptionComments;
	}
	/**
	 * @param adoptionComments The adoptionComments to set.
	 */
	public void setAdoptionComments(String adoptionComments) {
		this.adoptionComments = adoptionComments;
	}
	/**
	 * @return Returns the birthParentsMaritalStatus.
	 */
	public String getBirthParentsMaritalStatus() {
		return birthParentsMaritalStatus;
	}
	/**
	 * @param birthParentsMaritalStatus The birthParentsMaritalStatus to set.
	 */
	public void setBirthParentsMaritalStatus(String birthParentsMaritalStatus) {
		this.birthParentsMaritalStatus = birthParentsMaritalStatus;
	}
		
	public boolean getCheckIQScore() {
		if (fullScore != null && !fullScore.equals("")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getAnswer(String questionId) {
		String answer = (String)answerMap.get(questionId);
		if(answer != null && answer.equalsIgnoreCase("true")) {
			return "Yes"; 
		}
		else if(answer != null && answer.equalsIgnoreCase("false")) {
			return "No";
		}
		else {
			return answer;
		}
	}
	public String getAnswer1() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q1");
	}
	public String getAnswer2() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q2");
	}
	public String getAnswer3() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q3");
	}
	public String getAnswer4() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q4");
	}
	public String getAnswer5() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q5");
	}
	public String getAnswer6() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q6");
	}
	public String getAnswer7() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q7");
	}
	public String getAnswer8() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q8");
	}
	public String getAnswer9() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q9");
	}
	public String getAnswer10() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q10");
	}
	public String getAnswer11() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q11");
	}
	public String getAnswer12() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q12");
	}
	public String getAnswer13() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q13");
	}
	public String getAnswer14() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q14");
	}
	public String getAnswer15() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q15");
	}
	public String getAnswer16() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q16");
	}
	public String getAnswer17() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q17");
	}
	public String getAnswer18() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q18");
	}
	public String getAnswer19() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q19");
	}
	public String getAnswer20() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q20");
	}
	public String getAnswer21() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q21");
	}
	public String getAnswer22() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q22");
	}
	public String getAnswer23() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q23");
	}
	public String getAnswer24() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q24");
	}
	public String getAnswer25() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q25");
	}
	public String getAnswer26() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q26");
	}
	public String getAnswer27() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q27");
	}
	public String getAnswer28() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q28");
	}
	public String getAnswer29() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q29");
	}
	public String getAnswer30() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q30");
	}
	public String getAnswer31() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q31");
	}
	public String getAnswer32() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q32");
	}
	public String getAnswer33() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q33");
	}
	public String getAnswer34() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q34");
	}
	public String getAnswer35() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q35");
	}
	public String getAnswer36() {
		String temp = "";
		if (!"".equals(getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q36")) && getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q36") != null) {
			temp = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q36"));
		}
		return temp;
	}
	public String getAnswer37() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q37");
	}
	public String getAnswer38() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q38");
	}
	public String getAnswer39() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q39");
	}	
	public String getAnswer40() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q40");
	}
	//added for task#34434 ,#27104
	public String getAnswer41() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q41");
	}
	public String getAnswer42() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q42");
	}
	public String getAnswer43() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q43");
	}
	public String getAnswer44() {
		return getAnswer("2005_14_12_COMMONAPPEXITREPORT_Q44");
	}
	/**
	 * @return Returns the deceasedFamilyMembers.
	 */
	public List getDeceasedFamilyMembers() {
		return deceasedFamilyMembers;
	}
	/**
	 * @param deceasedFamilyMembers The deceasedFamilyMembers to set.
	 */
	public void setDeceasedFamilyMembers(List deceasedFamilyMembers) {
		this.deceasedFamilyMembers = deceasedFamilyMembers;
	}
		
	/**
	 * @return Returns the personsInHome.
	 */
	public List getPersonsInHome() {
		return personsInHome;
	}
	/**
	 * @param personsInHome The personsInHome to set.
	 */
	public void setPersonsInHome(List personsInHome) {
		this.personsInHome = personsInHome;
	}
	/**
	 * @return Returns the personsOutHome.
	 */
	public List getPersonsOutHome() {
		return personsOutHome;
	}
	/**
	 * @param personsOutHome The personsOutHome to set.
	 */
	public void setPersonsOutHome(List personsOutHome) {
		this.personsOutHome = personsOutHome;
	}
	
	
	public List getFamilyDynamicTraits() {
		return familyDynamicTraits;
	}
	public void setFamilyDynamicTraits(List familyDynamicTraits) {
		this.familyDynamicTraits = familyDynamicTraits;
	}
	/**
	 * @return Returns the familyMembersTraits.
	 */
	public List getFamilyMembersTraits() {
		return familyMembersTraits;
	}
	/**
	 * @param familyMembersTraits The familyMembersTraits to set.
	 */
	public void setFamilyMembersTraits(List familyMembersTraits) {
		this.familyMembersTraits = familyMembersTraits;
	}
	/**
	 * @return Returns the placements.
	 */
	public Collection getPlacements() {
		return placements;
	}
	/**
	 * @param placements The placements to set.
	 */
	public void setPlacements(Collection placements) {
		this.placements = placements;
	}
	/**
	 * @return Returns the dsmResults.
	 */
	public Collection getDsmResults() {
		return dsmResults;
	}
	/**
	 * @param dsmResults The dsmResults to set.
	 */
	public void setDsmResults(Collection dsmResults) {
		this.dsmResults = dsmResults;
	}
	
	public List getFamilyMemberGangInfo() {
		return familyMemberGangInfo;
	}
	public void setFamilyMemberGangInfo(List familyMemberGangInfo) {
		this.familyMemberGangInfo = familyMemberGangInfo;
	}
	public List getJuvenileGangInfo() {
		return juvenileGangInfo;
	}
	public void setJuvenileGangInfo(List juvenileGangInfo) {
		this.juvenileGangInfo = juvenileGangInfo;
	}
	public Date getCurrentDate() {
		return new Date();
	}
	public CourtOrder getCourtOrder() {
		return courtOrder;
	}
	public void setCourtOrder(CourtOrder courtOrder) {
		this.courtOrder = courtOrder;
	}
	public String getJuvenileNumber() {
		return juvenileNumber;
	}
	public void setJuvenileNumber(String juvenileNumber) {
		this.juvenileNumber = juvenileNumber;
	}
	public String getSidNumber() {
		return sidNumber;
	}
	public void setSidNumber(String sidNumber) {
		this.sidNumber = sidNumber;
	}
	public double getAverageNumberOfChildCareDays() {
		return averageNumberOfChildCareDays;
	}
	public void setAverageNumberOfChildCareDays(double averageNumberOfChildCareDays) {
		this.averageNumberOfChildCareDays = averageNumberOfChildCareDays;
	}
/**
 * @return Returns the guardianFinancialInfo.
 */
public Collection getGuardianFinancialInfo() {
	return guardianFinancialInfo;
}
/**
 * @param guardianFinancialInfo The guardianFinancialInfo to set.
 */
public void setGuardianFinancialInfo(Collection guardianFinancialInfo) {
	this.guardianFinancialInfo = guardianFinancialInfo;
}
	
	public String getNumberOfFailedAdoptionPlacement() {
		return numberOfFailedAdoptionPlacement;
	}
	public void setNumberOfFailedAdoptionPlacement(String numberOfFailedAdoptionPlacement) {
		this.numberOfFailedAdoptionPlacement = numberOfFailedAdoptionPlacement;
	}
	
	public List getInsuranceApplicableToChild() {
		return insuranceApplicableToChild;
	}
	public void setInsuranceApplicableToChild(List insuranceApplicableToChild) {
		this.insuranceApplicableToChild = insuranceApplicableToChild;
	}
	/**
	 * @return Returns the assaultiveBehaviorTraitsInfo.
	 */
	public List getAssaultiveBehaviorTraitsInfo() {
		return assaultiveBehaviorTraitsInfo;
	}
	/**
	 * @param assaultiveBehaviorTraitsInfo The assaultiveBehaviorTraitsInfo to set.
	 */
	public void setAssaultiveBehaviorTraitsInfo(List assaultiveBehaviorTraitsInfo) {
		this.assaultiveBehaviorTraitsInfo = assaultiveBehaviorTraitsInfo;
	}
	/**
	 * @return Returns the runawayHistoryTraitsInfo.
	 */
	public List getRunawayHistoryTraitsInfo() {
		return runawayHistoryTraitsInfo;
	}
	/**
	 * @param runawayHistoryTraitsInfo The runawayHistoryTraitsInfo to set.
	 */
	public void setRunawayHistoryTraitsInfo(List runawayHistoryTraitsInfo) {
		this.runawayHistoryTraitsInfo = runawayHistoryTraitsInfo;
	}
	/**
	 * @return Returns the suicideHistoryTraitsInfo.
	 */
	public List getSuicideHistoryTraitsInfo() {
		return suicideHistoryTraitsInfo;
	}
	/**
	 * @param suicideHistoryTraitsInfo The suicideHistoryTraitsInfo to set.
	 */
	public void setSuicideHistoryTraitsInfo(List suicideHistoryTraitsInfo) {
		this.suicideHistoryTraitsInfo = suicideHistoryTraitsInfo;
	}
	/**
	 * @return Returns the userCompletingCommonAppFormName.
	 */
	public String getUserCompletingCommonAppFormName() {
		return userCompletingCommonAppFormName;
	}
	/**
	 * @param userCompletingCommonAppFormName The userCompletingCommonAppFormName to set.
	 */
	public void setUserCompletingCommonAppFormName(String userCompletingCommonAppFormName) {
		this.userCompletingCommonAppFormName = userCompletingCommonAppFormName;
	}
	/**
	 * @return Returns the familyMemberComments.
	 */
	public String getFamilyMemberComments() {
		return familyMemberComments;
	}
	/**
	 * @param familyMemberComments The familyMemberComments to set.
	 */
	public void setFamilyMemberComments(String familyMemberComments) {
		this.familyMemberComments = familyMemberComments;
	}
	/**
	 * @return Returns the managingConservator.
	 */
	public String getManagingConservator() {
		return managingConservator;
	}
	/**
	 * @param managingConservator The managingConservator to set.
	 */
	public void setManagingConservator(String managingConservator) {
		this.managingConservator = managingConservator;
	}


	public int getOutofHomePlacements() {
		return outofHomePlacements;
	}


	public void setOutofHomePlacements(int outofHomePlacements) {
		this.outofHomePlacements = outofHomePlacements;
	}


	/**
	 * @return the recentOutOfHomeDischargeDate
	 */
	public String getRecentOutOfHomeDischargeDate() {
		return recentOutOfHomeDischargeDate;
	}


	/**
	 * @param recentOutOfHomeDischargeDate the recentOutOfHomeDischargeDate to set
	 */
	public void setRecentOutOfHomeDischargeDate(String recentOutOfHomeDischargeDate) {
		this.recentOutOfHomeDischargeDate = recentOutOfHomeDischargeDate;
	}


	/**
	 * @param schoolAddress the schoolAddress to set
	 */
	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}


	/**
	 * @return the schoolAddress
	 */
	public String getSchoolAddress() {
		return schoolAddress;
	}


	/**
	 * @param schoolCity the schoolCity to set
	 */
	public void setSchoolCity(String schoolCity) {
		this.schoolCity = schoolCity;
	}


	/**
	 * @return the schoolCity
	 */
	public String getSchoolCity() {
		return schoolCity;
	}


	/**
	 * @param schoolState the schoolState to set
	 */
	public void setSchoolState(String schoolState) {
		this.schoolState = schoolState;
	}


	/**
	 * @return the schoolState
	 */
	public String getSchoolState() {
		return schoolState;
	}


	/**
	 * @param schoolZip the schoolZip to set
	 */
	public void setSchoolZip(String schoolZip) {
		this.schoolZip = schoolZip;
	}


	/**
	 * @return the schoolZip
	 */
	public String getSchoolZip() {
		return schoolZip;
	}


	/**
	 * @param adoptedParentsMaritalStatus the adoptedParentsMaritalStatus to set
	 */
	public void setAdoptedParentsMaritalStatus(
			String adoptedParentsMaritalStatus) {
		this.adoptedParentsMaritalStatus = adoptedParentsMaritalStatus;
	}


	/**
	 * @return the adoptedParentsMaritalStatus
	 */
	public String getAdoptedParentsMaritalStatus() {
		return adoptedParentsMaritalStatus;
	}


	/**
	 * @param locCurrentOutofHomePlacement the locCurrentOutofHomePlacement to set
	 */
	public void setLocCurrentOutofHomePlacement(
			String locCurrentOutofHomePlacement) {
		this.locCurrentOutofHomePlacement = locCurrentOutofHomePlacement;
	}


	/**
	 * @return the locCurrentOutofHomePlacement
	 */
	public String getLocCurrentOutofHomePlacement() {
		return locCurrentOutofHomePlacement;
	}


	/**
	 * @param dischargeReason the dischargeReason to set
	 */
	public void setDischargeReason(String dischargeReason) {
		this.dischargeReason = dischargeReason;
	}


	/**
	 * @return the dischargeReason
	 */
	public String getDischargeReason() {
		return dischargeReason;
	}

	public int getTotalNumberOfAdjudications() {
		return totalNumberOfAdjudications;
	}


	public void setTotalNumberOfAdjudications(int totalNumberOfAdjudications) {
		this.totalNumberOfAdjudications = totalNumberOfAdjudications;
	}


	/**
	 * @return the childConsideredDangerToSelf
	 */
	public String getChildConsideredDangerToSelf() {
		return childConsideredDangerToSelf;
	}


	/**
	 * @param childConsideredDangerToSelf the childConsideredDangerToSelf to set
	 */
	public void setChildConsideredDangerToSelf(
			String childConsideredDangerToSelf) {
		this.childConsideredDangerToSelf = childConsideredDangerToSelf;
	}


	/**
	 * @return the childConsideredDangerToOthers
	 */
	public String getChildConsideredDangerToOthers() {
		return childConsideredDangerToOthers;
	}


	/**
	 * @param childConsideredDangerToOthers the childConsideredDangerToOthers to set
	 */
	public void setChildConsideredDangerToOthers(
			String childConsideredDangerToOthers) {
		this.childConsideredDangerToOthers = childConsideredDangerToOthers;
	}


	/**
	 * @return the historyOfSettingFires
	 */
	public String getHistoryOfSettingFires() {
		return historyOfSettingFires;
	}


	/**
	 * @param historyOfSettingFires the historyOfSettingFires to set
	 */
	public void setHistoryOfSettingFires(String historyOfSettingFires) {
		this.historyOfSettingFires = historyOfSettingFires;
	}


	/**
	 * @return the specialNeedsMaternity
	 */
	public String getSpecialNeedsMaternity() {
		return specialNeedsMaternity;
	}


	/**
	 * @param specialNeedsMaternity the specialNeedsMaternity to set
	 */
	public void setSpecialNeedsMaternity(String specialNeedsMaternity) {
		this.specialNeedsMaternity = specialNeedsMaternity;
	}


	/**
	 * @return the specialNeedsPreparationForAdultLiving
	 */
	public String getSpecialNeedsPreparationForAdultLiving() {
		return specialNeedsPreparationForAdultLiving;
	}


	/**
	 * @param specialNeedsPreparationForAdultLiving the specialNeedsPreparationForAdultLiving to set
	 */
	public void setSpecialNeedsPreparationForAdultLiving(
			String specialNeedsPreparationForAdultLiving) {
		this.specialNeedsPreparationForAdultLiving = specialNeedsPreparationForAdultLiving;
	}


	/**
	 * @return the specialNeedsOthers
	 */
	public String getSpecialNeedsOthers() {
		return specialNeedsOthers;
	}


	/**
	 * @param specialNeedsOthers the specialNeedsOthers to set
	 */
	public void setSpecialNeedsOthers(String specialNeedsOthers) {
		this.specialNeedsOthers = specialNeedsOthers;
	}


	/**
	 * @return the substanceAbuseHistory
	 */
	public String getSubstanceAbuseHistory() {
		return substanceAbuseHistory;
	}


	/**
	 * @param substanceAbuseHistory the substanceAbuseHistory to set
	 */
	public void setSubstanceAbuseHistory(String substanceAbuseHistory) {
		this.substanceAbuseHistory = substanceAbuseHistory;
	}


	/**
	 * @return the specializedProgramReq
	 */
	public String getSpecializedProgramReq() {
		return specializedProgramReq;
	}


	/**
	 * @param specializedProgramReq the specializedProgramReq to set
	 */
	public void setSpecializedProgramReq(String specializedProgramReq) {
		this.specializedProgramReq = specializedProgramReq;
	}


	/**
	 * @return the childAdmitGangAffiliation
	 */
	public String getChildAdmitGangAffiliation() {
		return childAdmitGangAffiliation;
	}


	/**
	 * @param childAdmitGangAffiliation the childAdmitGangAffiliation to set
	 */
	public void setChildAdmitGangAffiliation(String childAdmitGangAffiliation) {
		this.childAdmitGangAffiliation = childAdmitGangAffiliation;
	}


	/**
	 * @return the anyFamilyMemberHaveGangAffiliation
	 */
	public String getAnyFamilyMemberHaveGangAffiliation() {
		return anyFamilyMemberHaveGangAffiliation;
	}


	/**
	 * @param anyFamilyMemberHaveGangAffiliation the anyFamilyMemberHaveGangAffiliation to set
	 */
	public void setAnyFamilyMemberHaveGangAffiliation(
			String anyFamilyMemberHaveGangAffiliation) {
		this.anyFamilyMemberHaveGangAffiliation = anyFamilyMemberHaveGangAffiliation;
	}


	/**
	 * @return the abuseNeglectHistory
	 */
	public String getAbuseNeglectHistory() {
		return abuseNeglectHistory;
	}


	/**
	 * @param abuseNeglectHistory the abuseNeglectHistory to set
	 */
	public void setAbuseNeglectHistory(String abuseNeglectHistory) {
		this.abuseNeglectHistory = abuseNeglectHistory;
	}


	/**
	 * @return the abandonment
	 */
	public String getAbandonment() {
		return abandonment;
	}


	/**
	 * @param abandonment the abandonment to set
	 */
	public void setAbandonment(String abandonment) {
		this.abandonment = abandonment;
	}


	/**
	 * @return the motherParentalRightsTerminated
	 */
	public String getMotherParentalRightsTerminated() {
		return MotherParentalRightsTerminated;
	}


	/**
	 * @param motherParentalRightsTerminated the motherParentalRightsTerminated to set
	 */
	public void setMotherParentalRightsTerminated(
			String motherParentalRightsTerminated) {
		MotherParentalRightsTerminated = motherParentalRightsTerminated;
	}


	/**
	 * @return the fatherParentalRightsTerminated
	 */
	public String getFatherParentalRightsTerminated() {
		return FatherParentalRightsTerminated;
	}


	/**
	 * @param fatherParentalRightsTerminated the fatherParentalRightsTerminated to set
	 */
	public void setFatherParentalRightsTerminated(
			String fatherParentalRightsTerminated) {
		FatherParentalRightsTerminated = fatherParentalRightsTerminated;
	}


	/**
	 * @return the currentlyAttending
	 */
	public String getCurrentlyAttending() {
		return currentlyAttending;
	}


	/**
	 * @param currentlyAttending the currentlyAttending to set
	 */
	public void setCurrentlyAttending(String currentlyAttending) {
		this.currentlyAttending = currentlyAttending;
	}


	/**
	 * @return the trauncyHistory
	 */
	public String getTrauncyHistory() {
		return trauncyHistory;
	}


	/**
	 * @param trauncyHistory the trauncyHistory to set
	 */
	public void setTrauncyHistory(String trauncyHistory) {
		this.trauncyHistory = trauncyHistory;
	}


	/**
	 * @return the hasMedicalIssue
	 */
	public String getHasMedicalIssue() {
		return hasMedicalIssue;
	}


	/**
	 * @param hasMedicalIssue the hasMedicalIssue to set
	 */
	public void setHasMedicalIssue(String hasMedicalIssue) {
		this.hasMedicalIssue = hasMedicalIssue;
	}


	/**
	 * @return the eligibleForMedicaid
	 */
	public String getEligibleForMedicaid() {
		return EligibleForMedicaid;
	}


	/**
	 * @param eligibleForMedicaid the eligibleForMedicaid to set
	 */
	public void setEligibleForMedicaid(String eligibleForMedicaid) {
		EligibleForMedicaid = eligibleForMedicaid;
	}


	/**
	 * @return the receivingMedicaid
	 */
	public String getReceivingMedicaid() {
		return ReceivingMedicaid;
	}


	/**
	 * @param receivingMedicaid the receivingMedicaid to set
	 */
	public void setReceivingMedicaid(String receivingMedicaid) {
		ReceivingMedicaid = receivingMedicaid;
	}


	public String getcontrlrefID() {
		return contrlrefID;
	}


	public void setcontrlrefID(String contrlrefID) {
		this.contrlrefID = contrlrefID;
	}

		
}
