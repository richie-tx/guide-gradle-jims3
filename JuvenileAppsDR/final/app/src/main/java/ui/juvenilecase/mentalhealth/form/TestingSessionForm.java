/*
 * Created on Feb 12, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.mentalhealth.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import naming.PDCodeTableConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.common.SimpleCodeTableHelper;

/**
 * @author ugopinath
 *
 */
public class TestingSessionForm extends ActionForm
{
	private String testSessId = "";
	private String serviceProviderName = "";
	private String instructorName = "";
	private String actionType = "";
	private String selectedValue = "";
	private String secondaryAction = "";
	private Date referralDate;
	private String sessionDate = "";
	private String programReferralNum = "";
	private String programStatus = "";
	private String eventStatus = "";
	private String evtSessionLength = "";
	private String eventTime = "";
	private String eventType = "";
	private String eventId = "";
	private String locationDetails = "";
	private String serviceLocationUnit = "";
	private String testType = "";
	private String testTypeId = "";
	private String actualSessionLength = "";
	private String actualSessionLengthDesc = "";
	private String psychoAssessment = "";
	private String psychiatricAssessment = "";
	private String mentalRetardationDiagnosis = "";
	private String mentalIllnessDiagnosis = "";	
	private String recommendations;
	private String juvNum = ""; 
	private String confirmMessage = "";	
	private String dsmivId = "";
	private String mentalHealthRadio ="";
	private String mentalTreatmentRadio="";
	private String showIV="";	
	

	private DSMIVTest dsmRec = new DSMIVTest();
	//for search page User Story 11170
	private DSMIVTest searchDSMV = new DSMIVTest();
	private Collection dsmVcodes = new ArrayList();
	private String selectedDsmCode = "";
	private String diagnosisField = "";
	
	private IQTest iqRec = new IQTest();
	private AchievementTest atRec = new AchievementTest();
	private AdaptiveFunctioningTest afRec = new AdaptiveFunctioningTest();
	private AdaptiveBehaviorTest abRec = new AdaptiveBehaviorTest();
	
	//Collections
	private Collection testResultsList = new ArrayList();
	private Collection dsmResultsList = new ArrayList();
	private Collection iqResultsList = new ArrayList();
	private Collection achievementResultsList = new ArrayList();
	private Collection afResultsList = new ArrayList();
	private Collection abResultsList = new ArrayList();
	private List       dsmivDiagnosisList = new ArrayList();

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
	{
		if(aRequest.getParameter("resetCheckbox") != null && 
				aRequest.getParameter("resetCheckbox").equals("true"))
		{
			this.dsmRec.resetCheckboxes();
		}
	}

	/*
	 */
	public void clear()
	{	
		this.setActualSessionLength("");
		this.setTestType("");		
		this.setRecommendations("");
		this.setTestTypeId("");
		this.setPsychoAssessment("");
		this.setPsychiatricAssessment("");
		this.setMentalRetardationDiagnosis("");
		this.setMentalIllnessDiagnosis("");
		actionType = "";	
	}
	
	/**
	 * @return Returns the achievementResultsList.
	 */
	public Collection getAchievementResultsList() {
		return achievementResultsList;
	}
	/**
	 * @param achievementResultsList The achievementResultsList to set.
	 */
	public void setAchievementResultsList(Collection achievementResultsList) {
		this.achievementResultsList = achievementResultsList;
	}
	/**
	 * @return Returns the actualSessionLength.
	 */
	public String getActualSessionLength() {
		return actualSessionLength;
	}
	/**
	 * @param actualSessionLength The actualSessionLength to set.
	 */
	public void setActualSessionLength(String actualSessionLength) {
		this.actualSessionLength = actualSessionLength;
		actualSessionLengthDesc = "";
		actualSessionLengthDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SESSION_LENGTH_INTERVAL,actualSessionLength);
	}
	/**
	 * @return Returns the afResultsList.
	 */
	public Collection getAfResultsList() {
		return afResultsList;
	}
	/**
	 * @param afResultsList The afResultsList to set.
	 */
	public void setAfResultsList(Collection afResultsList) {
		this.afResultsList = afResultsList;
	}
	/**
	 * @return Returns the abResultsList.
	 */
	public Collection getAbResultsList() {
		return abResultsList;
	}
	/**
	 * @param afResultsList The afResultsList to set.
	 */
	public void setAbResultsList(Collection abResultsList) {
		this.abResultsList = abResultsList;
	}
	/**
	 * @return Returns the dsmRec.
	 */
	public DSMIVTest getDsmRec() {
		return dsmRec;
	}
	/**
	 * @param dsmRec The dsmRec to set.
	 */
	public void setDsmRec(DSMIVTest dsmRec) {
		this.dsmRec = dsmRec;
	}
	
	/**
	 * @return Returns the searchDSMV.
	 */
	public DSMIVTest getSearchDSMV() {
		return searchDSMV;
	}
	/**
	 * @param searchDSMV The searchDSMV to set.
	 */
	public void setSearchDSMV(DSMIVTest searchDSMV) {
		this.searchDSMV = searchDSMV;
	}
	
	
	/**
	 * @return the selectedDsmCode
	 */
	public String getSelectedDsmCode() {
		return selectedDsmCode;
	}

	/**
	 * @param selectedDsmCode the selectedDsmCode to set
	 */
	public void setSelectedDsmCode(String selectedDsmCode) {
		this.selectedDsmCode = selectedDsmCode;
	}

	/**
	 * @return the diagnosisField
	 */
	public String getDiagnosisField() {
		return diagnosisField;
	}

	/**
	 * @param diagnosisField the diagnosisField to set
	 */
	public void setDiagnosisField(String diagnosisField) {
		this.diagnosisField = diagnosisField;
	}

	/**
	 * @return Returns the dsmVcodes.
	 */
	public Collection getDsmVcodes() {
		return dsmVcodes;
	}
	/**
	 * @param dsmVcodes The dsmVcodes to set.
	 */
	public void setDsmVcodes(Collection dsmVcodes) {
		this.dsmVcodes = dsmVcodes;
	}
	
	/**
	 * @return Returns the afRec.
	 */
	public AdaptiveFunctioningTest getAfRec() {
		return afRec;
	}
	/**
	 * @param afRec The afRec to set.
	 */
	public void setAfRec(AdaptiveFunctioningTest afRec) {
		this.afRec = afRec;
	}
	
	/**
	 * @return Returns the abRec.
	 */
	public AdaptiveBehaviorTest getAbRec() {
		return abRec;
	}
	/**
	 * @param abRec The abRec to set.
	 */
	public void setAbRec(AdaptiveBehaviorTest abRec) {
		this.abRec = abRec;
	}
	/**
	 * @return Returns the atRec.
	 */
	public AchievementTest getAtRec() {
		return atRec;
	}
	/**
	 * @param atRec The atRec to set.
	 */
	public void setAtRec(AchievementTest atRec) {
		this.atRec = atRec;
	}
	/**
	 * @return Returns the iqRec.
	 */
	public IQTest getIqRec() {
		return iqRec;
	}
	/**
	 * @param iqRec The iqRec to set.
	 */
	public void setIqRec(IQTest iqRec) {
		this.iqRec = iqRec;
	}
	/**
	 * @return Returns the dsmResultsList.
	 */
	public Collection getDsmResultsList() {
		return dsmResultsList;
	}
	/**
	 * @param dsmResultsList The dsmResultsList to set.
	 */
	public void setDsmResultsList(Collection dsmResultsList) {
		this.dsmResultsList = dsmResultsList;
	}
	/**
	 * @return Returns the eventStatus.
	 */
	public String getEventStatus() {
		return eventStatus;
	}
	/**
	 * @param eventStatus The eventStatus to set.
	 */
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
	/**
	 * @return Returns the eventTime.
	 */
	public String getEventTime() {
		return eventTime;
	}
	/**
	 * @param eventTime The eventTime to set.
	 */
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	/**
	 * @return Returns the eventType.
	 */
	public String getEventType() {
		return eventType;
	}
	/**
	 * @param eventType The eventType to set.
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	/**
	 * @return Returns the evtSessionLength.
	 */
	public String getEvtSessionLength() {
		return evtSessionLength;
	}
	/**
	 * @param evtSessionLength The evtSessionLength to set.
	 */
	public void setEvtSessionLength(String evtSessionLength) {
		this.evtSessionLength = evtSessionLength;
	}
	/**
	 * @return Returns the instructorName.
	 */
	public String getInstructorName() {
		return instructorName;
	}
	/**
	 * @param instructorName The instructorName to set.
	 */
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	/**
	 * @return Returns the iqResultsList.
	 */
	public Collection getIqResultsList() {
		return iqResultsList;
	}
	/**
	 * @param iqResultsList The iqResultsList to set.
	 */
	public void setIqResultsList(Collection iqResultsList) {
		this.iqResultsList = iqResultsList;
	}
	/**
	 * @return Returns the locationDetails.
	 */
	public String getLocationDetails() {
		return locationDetails;
	}
	/**
	 * @param locationDetails The locationDetails to set.
	 */
	public void setLocationDetails(String locationDetails) {
		this.locationDetails = locationDetails;
	}

	/**
	 * @return Returns the programReferralNum.
	 */
	public String getProgramReferralNum() {
		return programReferralNum;
	}
	/**
	 * @param programReferralNum The programReferralNum to set.
	 */
	public void setProgramReferralNum(String programReferralNum) {
		this.programReferralNum = programReferralNum;
	}
	/**
	 * @return Returns the programStatus.
	 */
	public String getProgramStatus() {
		return programStatus;
	}
	/**
	 * @param programStatus The programStatus to set.
	 */
	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}

	/**
	 * @return Returns the recommendations.
	 */
	public String getRecommendations() {
		return recommendations;
	}
	/**
	 * @param recommendations The recommendations to set.
	 */
	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}

	/**
	 * @return Returns the serviceLocation.
	 */
	public String getServiceLocationUnit() {
		return serviceLocationUnit;
	}
	/**
	 * @param serviceLocation The serviceLocation to set.
	 */
	public void setServiceLocationUnit(String serviceLocation) {
		this.serviceLocationUnit = serviceLocation;
	}
	/**
	 * @return Returns the serviceProviderName.
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	/**
	 * @param serviceProviderName The serviceProviderName to set.
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
	/**
	 * @return Returns the sessionDate.
	 */
	public String getSessionDate() {
		return sessionDate;
	}
	/**
	 * @param sessionDate The sessionDate to set.
	 */
	public void setSessionDate(String sessionDate) {
		this.sessionDate = sessionDate;
	}
	/**
	 * @return Returns the testResultsList.
	 */
	public Collection getTestResultsList() {
		return testResultsList;
	}
	/**
	 * @param testResultsList The testResultsList to set.
	 */
	public void setTestResultsList(Collection testResultsList) {
		this.testResultsList = testResultsList;
	}
	/**
	 * @return Returns the testType.
	 */
	public String getTestType() {
		return testType;
	}
	/**
	 * @param testType The testType to set.
	 */
	public void setTestType(String testType) {		
		this.testType = testType;
	}
	/**
	 * @return Returns the juvNum.
	 */
	public String getJuvNum() {
		return juvNum;
	}
	/**
	 * @param juvNum The juvNum to set.
	 */
	public void setJuvNum(String juvNum) {
		this.juvNum = juvNum;
	}

	/**
	 * @return Returns the confirmMessage.
	 */
	public String getConfirmMessage() {
		return confirmMessage;
	}
	/**
	 * @param confirmMessage The confirmMessage to set.
	 */
	public void setConfirmMessage(String confirmMessage) {
		this.confirmMessage = confirmMessage;
	}
	
	/**
	 * @return Returns the eventId.
	 */
	public String getEventId() {
		return eventId;
	}
	/**
	 * @param eventId The eventId to set.
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	/**
	 * @return Returns the actionType.
	 */
	public String getActionType() {
		return actionType;
	}
	/**
	 * @param actionType The actionType to set.
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	/*
	 * inner class owned by TestingSessionForm
	 */
	public static class DSMIVTest
	{
		private String testDate = "";
		private String activeDiagnosisField="";
		private String axisIPrimaryScore = "";
		private String axisIPrimaryScoreDesc = "";
		private String axisISecondaryScore = "";
		private String axisISecondaryScoreDesc = "";
		private String axisITertiaryScore = "";
		private String axisITertiaryScoreDesc = "";
		private String axisIFourth = "";
		private String axisIFourthDesc = "";
		private String axisIFifth = "";
		private String axisIFifthDesc = "";
		private String axisIIPrimaryScore = "";
		private String axisIIPrimaryScoreDesc = "";
		private String axisIISecondaryScore = "";
		private String axisIISecondaryScoreDesc = "";
		private String axisIIIPrimaryScore = "";
		private String axisIIIPrimaryScoreDesc = "";
		private String axisIIISecondaryScore = "";
		private String axisIIISecondaryScoreDesc = "";
		private boolean isEducationalProblems = false;
		private boolean healthCareProblems = false;
		private boolean housingProblems = false;
		private boolean occupationalProblems = false;
		private boolean legalSystemProblems = false;
		private boolean psychoEnvProblems = false;
		private boolean socioEnvProblems = false;
		private boolean suppGrpProblems = false;
		private boolean economicProblems = false;
		private boolean mentalHealthNeeded;
		private boolean mentalHealthTreatment;
		private String dsmivDesc = "";
		private String dsmivId = "";
		private String axisIVComments = "";
		private String axisVScore = "";
		
		
		//added for change from DSM IV to DSM V ER# 75795
		private String medicalDiagnosis = "";
		private Date createDate = null;
		private String diagnosis10 = "";
		private String diagnosis10Desc = "";
		
		//for search page User Story 11170
		private String dsmCode = "";
		private String dsmCodeDescription = "";
		
		//added for Bug# 13158
		private String mentalHealthNeededStr="";
		private String mentalHealthTreatmentStr="";
		
		
		public void clearSearchCriteria()
		{	
			dsmCode="";
			dsmCodeDescription="";	
		}
		
		public void resetCheckboxes()
		{
			isEducationalProblems = false;
			healthCareProblems = false;
			housingProblems = false;
			occupationalProblems = false;
			legalSystemProblems = false;
			psychoEnvProblems = false;
			socioEnvProblems = false;
			suppGrpProblems = false;
			economicProblems = false;
			mentalHealthNeeded = false;
			mentalHealthTreatment = false;
		}
		/**
		 * @return Returns the axisIFifth.
		 */
		public String getAxisIFifth() {
			return axisIFifth;
		}
		/**
		 * @param axisIFifth The axisIFifth to set.
		 */
		public void setAxisIFifth(String axisIFifth) {
			this.axisIFifth = axisIFifth;
		}
		/**
		 * @return Returns the axisIFourth.
		 */
		public String getAxisIFourth() {
			return axisIFourth;
		}
		/**
		 * @param axisIFourth The axisIFourth to set.
		 */
		public void setAxisIFourth(String axisIFourth) {
			this.axisIFourth = axisIFourth;
		}
		/**
		 * @return Returns the axisIIIPrimaryScore.
		 */
		public String getAxisIIIPrimaryScore() {
			return axisIIIPrimaryScore;
		}
		/**
		 * @param axisIIIPrimaryScore The axisIIIPrimaryScore to set.
		 */
		public void setAxisIIIPrimaryScore(String axisIIIPrimaryScore) {
			this.axisIIIPrimaryScore = axisIIIPrimaryScore;
		}
		/**
		 * @return Returns the axisIIISecondaryScore.
		 */
		public String getAxisIIISecondaryScore() {
			return axisIIISecondaryScore;
		}
		/**
		 * @param axisIIISecondaryScore The axisIIISecondaryScore to set.
		 */
		public void setAxisIIISecondaryScore(String axisIIISecondaryScore) {
			this.axisIIISecondaryScore = axisIIISecondaryScore;
		}
		/**
		 * @return Returns the axisIIPrimaryScore.
		 */
		public String getAxisIIPrimaryScore() {
			return axisIIPrimaryScore;
		}
		/**
		 * @param axisIIPrimaryScore The axisIIPrimaryScore to set.
		 */
		public void setAxisIIPrimaryScore(String axisIIPrimaryScore) {
			this.axisIIPrimaryScore = axisIIPrimaryScore;
		}
		/**
		 * @return Returns the axisIISecondaryScore.
		 */
		public String getAxisIISecondaryScore() {
			return axisIISecondaryScore;
		}
		/**
		 * @param axisIISecondaryScore The axisIISecondaryScore to set.
		 */
		public void setAxisIISecondaryScore(String axisIISecondaryScore) {
			this.axisIISecondaryScore = axisIISecondaryScore;
		}
		/**
		 * @return Returns the axisIPrimaryScore.
		 */
		public String getAxisIPrimaryScore() {
			return axisIPrimaryScore;
		}
		/**
		 * @param axisIPrimaryScore The axisIPrimaryScore to set.
		 */
		public void setAxisIPrimaryScore(String axisIPrimaryScore) {
			this.axisIPrimaryScore = axisIPrimaryScore;
		}
		/**
		 * @return Returns the axisIPrimaryScoreDesc.
		 */
		public String getAxisIPrimaryScoreDesc() {
			return axisIPrimaryScoreDesc;
		}
		/**
		 * @param axisIPrimaryScoreDesc The axisIPrimaryScoreDesc to set.
		 */
		public void setAxisIPrimaryScoreDesc(String axisIPrimaryScoreDesc) {
			this.axisIPrimaryScoreDesc = axisIPrimaryScoreDesc;
		}
		
		/**
		 * @return the activeDiagnosisField
		 */
		public String getActiveDiagnosisField() {
			return activeDiagnosisField;
		}
		/**
		 * @param activeDiagnosisField the activeDiagnosisField to set
		 */
		public void setActiveDiagnosisField(String activeDiagnosisField) {
			this.activeDiagnosisField = activeDiagnosisField;
		}
		/**
		 * @return Returns the axisISecondaryScore.
		 */
		public String getAxisISecondaryScore() {
			return axisISecondaryScore;
		}
		/**
		 * @param axisISecondaryScore The axisISecondaryScore to set.
		 */
		public void setAxisISecondaryScore(String axisISecondaryScore) {
			this.axisISecondaryScore = axisISecondaryScore;
		}
		/**
		 * @return the axisISecondaryScoreDesc
		 */
		public String getAxisISecondaryScoreDesc() {
			return axisISecondaryScoreDesc;
		}
		/**
		 * @param axisISecondaryScoreDesc the axisISecondaryScoreDesc to set
		 */
		public void setAxisISecondaryScoreDesc(String axisISecondaryScoreDesc) {
			this.axisISecondaryScoreDesc = axisISecondaryScoreDesc;
		}
		/**
		 * @return the axisITertiaryScoreDesc
		 */
		public String getAxisITertiaryScoreDesc() {
			return axisITertiaryScoreDesc;
		}
		/**
		 * @param axisITertiaryScoreDesc the axisITertiaryScoreDesc to set
		 */
		public void setAxisITertiaryScoreDesc(String axisITertiaryScoreDesc) {
			this.axisITertiaryScoreDesc = axisITertiaryScoreDesc;
		}
		/**
		 * @return the axisIFourthDesc
		 */
		public String getAxisIFourthDesc() {
			return axisIFourthDesc;
		}
		/**
		 * @param axisIFourthDesc the axisIFourthDesc to set
		 */
		public void setAxisIFourthDesc(String axisIFourthDesc) {
			this.axisIFourthDesc = axisIFourthDesc;
		}
		/**
		 * @return the axisIFifthDesc
		 */
		public String getAxisIFifthDesc() {
			return axisIFifthDesc;
		}
		/**
		 * @param axisIFifthDesc the axisIFifthDesc to set
		 */
		public void setAxisIFifthDesc(String axisIFifthDesc) {
			this.axisIFifthDesc = axisIFifthDesc;
		}
		/**
		 * @return the axisIIPrimaryScoreDesc
		 */
		public String getAxisIIPrimaryScoreDesc() {
			return axisIIPrimaryScoreDesc;
		}
		/**
		 * @param axisIIPrimaryScoreDesc the axisIIPrimaryScoreDesc to set
		 */
		public void setAxisIIPrimaryScoreDesc(String axisIIPrimaryScoreDesc) {
			this.axisIIPrimaryScoreDesc = axisIIPrimaryScoreDesc;
		}
		/**
		 * @return the axisIISecondaryScoreDesc
		 */
		public String getAxisIISecondaryScoreDesc() {
			return axisIISecondaryScoreDesc;
		}
		/**
		 * @param axisIISecondaryScoreDesc the axisIISecondaryScoreDesc to set
		 */
		public void setAxisIISecondaryScoreDesc(String axisIISecondaryScoreDesc) {
			this.axisIISecondaryScoreDesc = axisIISecondaryScoreDesc;
		}
		/**
		 * @return the axisIIIPrimaryScoreDesc
		 */
		public String getAxisIIIPrimaryScoreDesc() {
			return axisIIIPrimaryScoreDesc;
		}
		/**
		 * @param axisIIIPrimaryScoreDesc the axisIIIPrimaryScoreDesc to set
		 */
		public void setAxisIIIPrimaryScoreDesc(String axisIIIPrimaryScoreDesc) {
			this.axisIIIPrimaryScoreDesc = axisIIIPrimaryScoreDesc;
		}
		/**
		 * @return the axisIIISecondaryScoreDesc
		 */
		public String getAxisIIISecondaryScoreDesc() {
			return axisIIISecondaryScoreDesc;
		}
		/**
		 * @param axisIIISecondaryScoreDesc the axisIIISecondaryScoreDesc to set
		 */
		public void setAxisIIISecondaryScoreDesc(String axisIIISecondaryScoreDesc) {
			this.axisIIISecondaryScoreDesc = axisIIISecondaryScoreDesc;
		}
		/**
		 * @return the diagnosis10Desc
		 */
		public String getDiagnosis10Desc() {
			return diagnosis10Desc;
		}
		/**
		 * @param diagnosis10Desc the diagnosis10Desc to set
		 */
		public void setDiagnosis10Desc(String diagnosis10Desc) {
			this.diagnosis10Desc = diagnosis10Desc;
		}
		
		/**
		 * @return the dsmCode
		 */
		public String getDsmCode() {
			return dsmCode;
		}

		/**
		 * @param dsmCode the dsmCode to set
		 */
		public void setDsmCode(String dsmCode) {
			this.dsmCode = dsmCode;
		}

		/**
		 * @return the dsmCodeDescription
		 */
		public String getDsmCodeDescription() {
			return dsmCodeDescription;
		}

		/**
		 * @param dsmCodeDescription the dsmCodeDescription to set
		 */
		public void setDsmCodeDescription(String dsmCodeDescription) {
			this.dsmCodeDescription = dsmCodeDescription;
		}
		
		/**
		 * @return Returns the axisITertiaryScore.
		 */
		public String getAxisITertiaryScore() {
			return axisITertiaryScore;
		}
		/**
		 * @param axisITertiaryScore The axisITertiaryScore to set.
		 */
		public void setAxisITertiaryScore(String axisITertiaryScore) {
			this.axisITertiaryScore = axisITertiaryScore;
		}
		/**
		 * @return Returns the axisIVComments.
		 */
		public String getAxisIVComments() {
			return axisIVComments;
		}
		/**
		 * @param axisIVComments The axisIVComments to set.
		 */
		public void setAxisIVComments(String axisIVComments) {
			this.axisIVComments = axisIVComments;
		}
		/**
		 * @return Returns the axisVScore.
		 */
		public String getAxisVScore() {
			return axisVScore;
		}
		/**
		 * @param axisVScore The axisVScore to set.
		 */
		public void setAxisVScore(String axisVScore) {
			this.axisVScore = axisVScore;
		}
		/**
		 * @return Returns the economicProblems.
		 */
		public boolean getEconomicProblems() {
			return economicProblems;
		}
		/**
		 * @param economicProblems The economicProblems to set.
		 */
		public void setEconomicProblems(boolean economicProblems) {
			this.economicProblems = economicProblems;
		}
		/**
		 * @return Returns the educationalProblems.
		 */
		public boolean getIsEducationalProblems() {
			return isEducationalProblems;
		}
		/**
		 * @param educationalProblems The educationalProblems to set.
		 */
		public void setIsEducationalProblems(boolean educationalProblems) {
			this.isEducationalProblems = educationalProblems;
		}
		/**
		 * @return Returns the healthCareProblems.
		 */
		public boolean getHealthCareProblems() {
			return healthCareProblems;
		}
		/**
		 * @param healthCareProblems The healthCareProblems to set.
		 */
		public void setHealthCareProblems(boolean healthCareProblems) {
			this.healthCareProblems = healthCareProblems;
		}
		/**
		 * @return Returns the housingProblems.
		 */
		public boolean getHousingProblems() {
			return housingProblems;
		}
		/**
		 * @param housingProblems The housingProblems to set.
		 */
		public void setHousingProblems(boolean housingProblems) {
			this.housingProblems = housingProblems;
		}
		/**
		 * @return Returns the legalSystemProblems.
		 */
		public boolean getLegalSystemProblems() {
			return legalSystemProblems;
		}
		/**
		 * @param legalSystemProblems The legalSystemProblems to set.
		 */
		public void setLegalSystemProblems(boolean legalSystemProblems) {
			this.legalSystemProblems = legalSystemProblems;
		}
		/**
		 * @return Returns the occupationalProblems.
		 */
		public boolean getOccupationalProblems() {
			return occupationalProblems;
		}
		/**
		 * @param occupationalProblems The occupationalProblems to set.
		 */
		public void setOccupationalProblems(boolean occupationalProblems) {
			this.occupationalProblems = occupationalProblems;
		}
		/**
		 * @return Returns the psychoEnvProblems.
		 */
		public boolean getPsychoEnvProblems() {
			return psychoEnvProblems;
		}
		/**
		 * @param psychoEnvProblems The psychoEnvProblems to set.
		 */
		public void setPsychoEnvProblems(boolean psychoEnvProblems) {
			this.psychoEnvProblems = psychoEnvProblems;
		}
		/**
		 * @return Returns the socioEnvProblems.
		 */
		public boolean getSocioEnvProblems() {
			return socioEnvProblems;
		}
		/**
		 * @param socioEnvProblems The socioEnvProblems to set.
		 */
		public void setSocioEnvProblems(boolean socioEnvProblems) {
			this.socioEnvProblems = socioEnvProblems;
		}
		/**
		 * @return Returns the suppGrpProblems.
		 */
		public boolean getSuppGrpProblems() {
			return suppGrpProblems;
		}
		/**
		 * @param suppGrpProblems The suppGrpProblems to set.
		 */
		public void setSuppGrpProblems(boolean suppGrpProblems) {
			this.suppGrpProblems = suppGrpProblems;
		}
		/**
		 * @return Returns the testDate.
		 */
		public String getTestDate() {
			return testDate;
		}
		/**
		 * @param testDate The testDate to set.
		 */
		public void setTestDate(String testDate) {
			this.testDate = testDate;
		}
		public boolean isMentalHealthNeeded() {
			return mentalHealthNeeded;
		}
		public void setMentalHealthNeeded(boolean mentalHealthNeeded) {
			this.mentalHealthNeeded = mentalHealthNeeded;
		}
		public boolean isMentalHealthTreatment() {
			return mentalHealthTreatment;
		}
		public void setMentalHealthTreatment(boolean mentalHealthTreatment) {
			this.mentalHealthTreatment = mentalHealthTreatment;
		}
		public void setEducationalProblems(boolean isEducationalProblems) {
			this.isEducationalProblems = isEducationalProblems;
		}
		public String getDsmivDesc() {
			return dsmivDesc;
		}
		public void setDsmivDesc(String dsmivDesc) {
			this.dsmivDesc = dsmivDesc;
		}
		public String getDsmivId() {
			return dsmivId;
		}
		public void setDsmivId(String dsmivId) {
			this.dsmivId = dsmivId;
		}
		/**
		 * @return the medicalDiagnosis
		 */
		public String getMedicalDiagnosis() {
			return medicalDiagnosis;
		}
		/**
		 * @param medicalDiagnosis the medicalDiagnosis to set
		 */
		public void setMedicalDiagnosis(String medicalDiagnosis) {
			this.medicalDiagnosis = medicalDiagnosis;
		}
		/**
		 * @return the createDate
		 */
		public Date getCreateDate() {
			return createDate;
		}
		/**
		 * @param createDate the createDate to set
		 */
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		
		/**
		 * @return Returns the diagnosis10.
		 */
		public String getDiagnosis10() {
			return diagnosis10;
		}
		/**
		 * @param diagnosis10 The diagnosis10 to set.
		 */
		public void setDiagnosis10(String diagnosis10) {
			this.diagnosis10 = diagnosis10;
		}

		public void setMentalHealthNeededStr(String mentalHealthNeededStr) {
			this.mentalHealthNeededStr = mentalHealthNeededStr;
		}

		public String getMentalHealthNeededStr() {
			return mentalHealthNeededStr;
		}

		public void setMentalHealthTreatmentStr(String mentalHealthTreatmentStr) {
			this.mentalHealthTreatmentStr = mentalHealthTreatmentStr;
		}

		public String getMentalHealthTreatmentStr() {
			return mentalHealthTreatmentStr;
		}
		
	}  // end inner public static class DSMIVTest

	/*
	 * inner class, owned by TestingSessionForm
	 */
	public static class IQTest
	{	
		private String testName = "";
		private String testNameId = "";
		private String testDate = "";
		private String fullScore = "";
		private String performanceScore = "";
		private String verbalScore = "";
		private String verbalComprehension = "";
		private String perceptualReasoning = "";
		private String nonVerbalIQ = "";
		private String processingSpeed = "";
		private String workingMemory = "";
		private String pictorialIQ = "";
		private String geometricIQ = "";
		private String recommendations = "";

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
		 * @return Returns the recommandations.
		 */
		public String getRecommendations() {
			return recommendations;
		}
		/**
		 * @param recommandations The recommandations to set.
		 */
		public void setRecommendations(String recommendations) {
			this.recommendations = recommendations;
		}
		/**
		 * @return Returns the testDate.
		 */
		public String getTestDate() {
			return testDate;
		}
		/**
		 * @param testDate The testDate to set.
		 */
		public void setTestDate(String testDate) {
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
		 * @return Returns the perceptualReasoning.
		 */
		public String getPerceptualReasoning() {
			return perceptualReasoning;
		}
		/**
		 * @param perceptualReasoning The perceptualReasoning to set.
		 */
		public void setPerceptualReasoning(String perceptualReasoning) {
			this.perceptualReasoning = perceptualReasoning;
		}
		/**
		 * @return Returns the verbalComprehension.
		 */
		public String getVerbalComprehension() {
			return verbalComprehension;
		}
		/**
		 * @param verbalComprehension The verbalComprehension to set.
		 */
		public void setVerbalComprehension(String verbalComprehension) {
			this.verbalComprehension = verbalComprehension;
		}
		/**
		 * @return Returns the nonVerbalIQ.
		 */
		public String getNonVerbalIQ() {
			return nonVerbalIQ;
		}
		/**
		 * @param nonVerbalIQ The nonVerbalIQ to set.
		 */
		public void setNonVerbalIQ(String nonVerbalIQ) {
			this.nonVerbalIQ = nonVerbalIQ;
		}
		/**
		 * @return Returns the processingSpeed.
		 */
		public String getProcessingSpeed() {
			return processingSpeed;
		}
		/**
		 * @param processingSpeed The processingSpeed to set.
		 */
		public void setProcessingSpeed(String processingSpeed) {
			this.processingSpeed = processingSpeed;
		}
		/**
		 * @return Returns the geometricIQ.
		 */
		public String getGeometricIQ() {
			return geometricIQ;
		}
		/**
		 * @param geometricIQ The geometricIQ to set.
		 */
		public void setGeometricIQ(String geometricIQ) {
			this.geometricIQ = geometricIQ;
		}
		/**
		 * @return Returns the pictorialIQ.
		 */
		public String getPictorialIQ() {
			return pictorialIQ;
		}
		/**
		 * @param pictorialIQ The pictorialIQ to set.
		 */
		public void setPictorialIQ(String pictorialIQ) {
			this.pictorialIQ = pictorialIQ;
		}
		/**
		 * @return Returns the workingMemory.
		 */
		public String getWorkingMemory() {
			return workingMemory;
		}
		/**
		 * @param workingMemory The workingMemory to set.
		 */
		public void setWorkingMemory(String workingMemory) {
			this.workingMemory = workingMemory;
		}
		/**
		 * @return Returns the testNameId.
		 */
		public String getTestNameId() {
			return testNameId;
		}
		
		/**
		 * @param testNameId The testNameId to set.
		 */
		public void setTestNameId(String testNameId) 
		{
			if( testNameId != null && (testNameId.trim().length() > 0) )
			{
				this.testName = SimpleCodeTableHelper.getDescrByCode("IQ_TEST_NAME", testNameId);
			}

			this.testNameId = testNameId;
		}
	} // end inner public static class IQTest

	/*
	 * inner class, owned by TestingSessionForm
	 */
	public static class AchievementTest
	{	
		private String testName = "";
		private String testNameId = "";
		private String testDate = "";
		private String arithmeticGradeLevel = "";
		private String arithmeticScore = "";
		private String readingGradeLevel = "";
		private String readingScore = "";
		private String spellingGradeLevel = "";
		private String spellingScore = "";
		private String recommendations = "";
		private String sentenceCompletionLevel = "";
		private String sentenceCompletionScore = "";
		private String readingCompositeLevel = "";
		private String readingCompositeScore = "";
		
		/**
		 * @return Returns the arithmeticGradeLevel.
		 */
		public String getArithmeticGradeLevel() {
			return arithmeticGradeLevel;
		}
		/**
		 * @param arithmeticGradeLevel The arithmeticGradeLevel to set.
		 */
		public void setArithmeticGradeLevel(String arithmeticGradeLevel) {
			this.arithmeticGradeLevel = arithmeticGradeLevel;
		}
		/**
		 * @return Returns the readingGradeLevel.
		 */
		public String getReadingGradeLevel() {
			return readingGradeLevel;
		}
		/**
		 * @param readingGradeLevel The readingGradeLevel to set.
		 */
		public void setReadingGradeLevel(String readingGradeLevel) {
			this.readingGradeLevel = readingGradeLevel;
		}
		/**
		 * @return Returns the readingScore.
		 */
		public String getReadingScore() {
			return readingScore;
		}
		/**
		 * @param readingScore The readingScore to set.
		 */
		public void setReadingScore(String readingScore) {
			this.readingScore = readingScore;
		}
		/**
		 * @return Returns the recommendations.
		 */
		public String getRecommendations() {
			return recommendations;
		}
		/**
		 * @param recommendations The recommendations to set.
		 */
		public void setRecommendations(String recommendations) {
			this.recommendations = recommendations;
		}
		/**
		 * @return Returns the spellingGradeLevel.
		 */
		public String getSpellingGradeLevel() {
			return spellingGradeLevel;
		}
		/**
		 * @param spellingGradeLevel The spellingGradeLevel to set.
		 */
		public void setSpellingGradeLevel(String spellingGradeLevel) {
			this.spellingGradeLevel = spellingGradeLevel;
		}
		/**
		 * @return Returns the spellingScore.
		 */
		public String getSpellingScore() {
			return spellingScore;
		}
		/**
		 * @param spellingScore The spellingScore to set.
		 */
		public void setSpellingScore(String spellingScore) {
			this.spellingScore = spellingScore;
		}
		/**
		 * @return Returns the testDate.
		 */
		public String getTestDate() {
			return testDate;
		}
		/**
		 * @param testDate The testDate to set.
		 */
		public void setTestDate(String testDate) {
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
		 * @return Returns the readingCompositeLevel.
		 */
		public String getReadingCompositeLevel() {
			return readingCompositeLevel;
		}
		/**
		 * @param readingCompositeLevel The readingCompositeLevel to set.
		 */
		public void setReadingCompositeLevel(String readingCompositeLevel) {
			this.readingCompositeLevel = readingCompositeLevel;
		}
		/**
		 * @return Returns the readingCompositeScore.
		 */
		public String getReadingCompositeScore() {
			return readingCompositeScore;
		}
		/**
		 * @param readingCompositeScore The readingCompositeScore to set.
		 */
		public void setReadingCompositeScore(String readingCompositeScore) {
			this.readingCompositeScore = readingCompositeScore;
		}
		/**
		 * @return Returns the sentenceCompletionLevel.
		 */
		public String getSentenceCompletionLevel() {
			return sentenceCompletionLevel;
		}
		/**
		 * @param sentenceCompletionLevel The sentenceCompletionLevel to set.
		 */
		public void setSentenceCompletionLevel(String sentenceCompletionLevel) {
			this.sentenceCompletionLevel = sentenceCompletionLevel;
		}
		/**
		 * @return Returns the sentenceCompletionScore.
		 */
		public String getSentenceCompletionScore() {
			return sentenceCompletionScore;
		}
		/**
		 * @param sentenceCompletionScore The sentenceCompletionScore to set.
		 */
		public void setSentenceCompletionScore(String sentenceCompletionScore) {
			this.sentenceCompletionScore = sentenceCompletionScore;
		}
		/**
		 * @return Returns the arithmeticScore.
		 */
		public String getArithmeticScore() {
			return arithmeticScore;
		}
		/**
		 * @param arithmeticScore The arithmeticScore to set.
		 */
		public void setArithmeticScore(String arithmeticScore) {
			this.arithmeticScore = arithmeticScore;
		}
		/**
		 * @return Returns the testNameId.
		 */
		public String getTestNameId() {
			return testNameId;
		}

		/**
		 * @param testNameId The testNameId to set.
		 */
		public void setTestNameId(String testNameId) 
		{
			if( testNameId != null && (testNameId.trim().length() > 0) )
			{
				this.testName = SimpleCodeTableHelper.getDescrByCode("AT_TEST_NAME", testNameId);
			}

			this.testNameId = testNameId;
		}
	} // end inner public static class AchievementTest

	/*
	 * inner class, owned by TestingSessionForm
	 */
	public static class AdaptiveBehaviorTest
	{
		
		private String communicationScore = "";
		private String compositeScore = "";
		private String livingScore = "";
		private String socialScore = "";
		private String testDate = "";
		private String testNameId = "";
		private String testName = "";

		public String getCommunicationScore()
		{
			return communicationScore;
		}

		public String getCompositeScore()
		{
			return compositeScore;
		}

		public String getLivingScore()
		{
			return livingScore;
		}

		public String getSocialScore()
		{
			return socialScore;
		}

		public String getTestDate()
		{
			return testDate;
		}
		
		public String getTestName()
		{
			return testName;
		}
		
		public String getTestNameId()
		{
			return testNameId;
		}

		public void setCommunicationScore(String communicationScore)
		{
			this.communicationScore = communicationScore;
		}

		public void setCompositeScore(String compositeScore)
		{
			this.compositeScore = compositeScore;
		}

		public void setLivingScore(String livingScore)
		{
			this.livingScore = livingScore;
		}

		public void setSocialScore(String socialScore)
		{
			this.socialScore = socialScore;
		}

		public void setTestDate(String testDate)
		{
			this.testDate = testDate;
		}
		
		public void setTestName(String testName)
		{
			this.testName = testName;
		}

		public void setTestNameId(String testNameId)
		{
			if( testNameId != null && (testNameId.trim().length() > 0) )
			{
				this.testName = SimpleCodeTableHelper.getDescrByCode("AF_TEST_NAME", testNameId);
			}

			this.testNameId = testNameId;
		}
	}
	
	/*
	 * inner class, owned by TestingSessionForm
	 */
	public static class AdaptiveFunctioningTest
	{
		private String standardScore = "";
		private String testName = "";
		private String testNameId = "";
		private String testDate = "";		
		
		/**
		 * @return Returns the standardScore.
		 */
		public String getStandardScore() {
			return standardScore;
		}
		/**
		 * @param standardScore The standardScore to set.
		 */
		public void setStandardScore(String standardScore) {
			this.standardScore = standardScore;
		}
		/**
		 * @return Returns the testDate.
		 */
		public String getTestDate() {
			return testDate;
		}
		/**
		 * @param testDate The testDate to set.
		 */
		public void setTestDate(String testDate) {
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
		 * @return Returns the testNameId.
		 */
		public String getTestNameId() {
			return testNameId;
		}
		
		/**
		 * @param testNameId The testNameId to set.
		 */
		public void setTestNameId(String testNameId) 
		{
			if( testNameId != null && (testNameId.trim().length() > 0) )
			{
				this.testName = SimpleCodeTableHelper.getDescrByCode("AF_TEST_NAME", testNameId);
			}

			this.testNameId = testNameId;
		}
	} // end inner public static class AdaptiveFunctioningTest


	/* continue public class TestingSessionForm */

	/**
	 * @return Returns the testTypeId.
	 */
	public String getTestTypeId() {
		return testTypeId;
	}

	/**
	 * @param testTypeId The testTypeId to set.
	 */
	public void setTestTypeId(String testTypeId) 
	{
		if( testTypeId != null  &&  (testTypeId.trim().length() > 0) )
		{
			this.testType = SimpleCodeTableHelper.getDescrByCode("TEST_TYPE", testTypeId);
		}

		this.testTypeId = testTypeId;
	}
		
	public Collection getDsmivDiagnosisList() {
		return dsmivDiagnosisList;
	}

	public void setDsmivDiagnosisList(List dsmivDiagnosisList) {
		this.dsmivDiagnosisList = dsmivDiagnosisList;
	}

	public String getDsmivId() {
		return dsmivId;
	}

	public void setDsmivId(String dsmivId) {
		this.dsmivId = dsmivId;
	}
	
	
	/**
	 * @return Returns the testSessId.
	 */
	public String getTestSessId() {
		return testSessId;
	}
	/**
	 * @param testSessId The testSessId to set.
	 */
	public void setTestSessId(String testSessId) {
		this.testSessId = testSessId;
	}
	/**
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue() {
		return selectedValue;
	}
	/**
	 * @param selectedValue The selectedValue to set.
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	
	/**
	 * @return Returns the mentalIllnessDiagnosis.
	 */
	public String getMentalIllnessDiagnosis() {
		return mentalIllnessDiagnosis;
	}
	/**
	 * @param mentalIllnessDiagnosis The mentalIllnessDiagnosis to set.
	 */
	public void setMentalIllnessDiagnosis(String mentalIllnessDiagnosis) {
		this.mentalIllnessDiagnosis = mentalIllnessDiagnosis;
	}
	/**
	 * @return Returns the mentalRetardationDiagnosis.
	 */
	public String getMentalRetardationDiagnosis() {
		return mentalRetardationDiagnosis;
	}
	/**
	 * @param mentalRetardationDiagnosis The mentalRetardationDiagnosis to set.
	 */
	public void setMentalRetardationDiagnosis(String mentalRetardationDiagnosis) {
		this.mentalRetardationDiagnosis = mentalRetardationDiagnosis;
	}
	/**
	 * @return Returns the psychiatricAssessment.
	 */
	public String getPsychiatricAssessment() {
		return psychiatricAssessment;
	}
	/**
	 * @param psychiatricAssessment The psychiatricAssessment to set.
	 */
	public void setPsychiatricAssessment(String psychiatricAssessment) {
		this.psychiatricAssessment = psychiatricAssessment;
	}
	/**
	 * @return Returns the psychoAssessment.
	 */
	public String getPsychoAssessment() {
		return psychoAssessment;
	}
	/**
	 * @param psychoAssessment The psychoAssessment to set.
	 */
	public void setPsychoAssessment(String psychoAssessment) {
		this.psychoAssessment = psychoAssessment;
	}
	/**
	 * @return Returns the secondaryAction.
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}
	/**
	 * @param secondaryAction The secondaryAction to set.
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}
	/**
	 * @return Returns the referralDate.
	 */
	public Date getReferralDate() {
		return referralDate;
	}
	/**
	 * @param referralDate The referralDate to set.
	 */
	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}
	/**
	 * @return Returns the actualSessionLengthDesc.
	 */
	public String getActualSessionLengthDesc() {
		return actualSessionLengthDesc;
	}
	/**
	 * @param actualSessionLengthDesc The actualSessionLengthDesc to set.
	 */
	public void setActualSessionLengthDesc(String actualSessionLengthDesc) {
		this.actualSessionLengthDesc = actualSessionLengthDesc;
	}

	public String getMentalHealthRadio() {
		return mentalHealthRadio;
	}

	public void setMentalHealthRadio(String mentalHealthRadio) {
		this.mentalHealthRadio = mentalHealthRadio;
	}

	public String getMentalTreatmentRadio() {
		return mentalTreatmentRadio;
	}

	public void setMentalTreatmentRadio(String mentalTreatmentRadio) {
		this.mentalTreatmentRadio = mentalTreatmentRadio;
	}

	/**
	 * @return the showIV
	 */
	public String getShowIV() {
		return showIV;
	}

	/**
	 * @param showIV the showIV to set
	 */
	public void setShowIV(String showIV) {
		this.showIV = showIV;
	}


}
