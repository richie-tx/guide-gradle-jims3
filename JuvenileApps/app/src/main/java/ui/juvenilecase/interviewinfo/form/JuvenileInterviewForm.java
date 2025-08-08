/*
 * Created on Jun 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.interviewinfo.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import messaging.calendar.reply.AttendeeNameResponseEvent;
import messaging.interviewinfo.reply.InterviewPersonResponseEvent;
import messaging.interviewinfo.reply.InterviewQuestionResponseEvent;
import messaging.interviewinfo.reply.InterviewReportHeaderResponseEvent;
import messaging.interviewinfo.reply.JuvenileCourtDateEvent;
import messaging.interviewinfo.to.FamilyInformationTO;
import naming.PDCodeTableConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.form.AddressValidationForm;
import ui.juvenilecase.UIJuvenileLoadCodeTables;
import ui.juvenilecase.caseplan.form.CaseplanForm.PersonResponsible;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;

/**
 * @author awidjaja
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileInterviewForm extends AddressValidationForm
{
	private static Collection emptyColl = new ArrayList();
	public static List petitionLevels = new ArrayList();
	
	public final String ADD_INTERVIEW = "addInterview";
	public final String VIEW_PENDING = "viewPending";
	public final String UPDATE_SUMMARY_NOTES = "updateSummaryNotes";
	public final String SUMMARY = "summary";
	public final String CONFIRMATION = "confirmation";

	//List of drop downs
	private Collection personsInterviewedList = new ArrayList();
	private Collection interviewLocationList = new ArrayList();
	
	private Collection todayAppointments;
	private Collection allInterviews;
	private HashMap interviewsGroupedByCasefileId;
	private HashMap todaysAppointmentGroupedByCasefileId;
	
	private HashMap guardianAddressMap = new HashMap();	//Collection of JuvenileMemberForm.MemberAddress
	private HashMap guardianPhoneMap = new HashMap();	//Collection of FamilyMemberPhoneResponseEvent
	
	private HashMap reportMap = new HashMap();	//InterviewReportHeaderResponseEvent
	
	JuvenileInterview currentInterview;
	
	private String action = "" ;
	private String status = "" ; //summary or confirmation
	private boolean allowUpdates = false;
	
	private String casefileId = "" ; //a.k.a supervision num 
	private String juvenileNum = "" ;
	private String addressStatus = "";
	
	private boolean listsSet = false;
	
	//These are for View Attorney Options scenario
	private String activeConstellationNum = "";
	private String attorneyOptionsNotes = "";
	
	//This variable is to be used in Parental Statement scenario
	private boolean spanishText = false;
	
	//Report History attributes
	private Name juvenileName = new Name();
	private InterviewReportHeaderResponseEvent currentReportHeaderInfo =
		new InterviewReportHeaderResponseEvent();
	private Collection reportHistory = emptyColl;	//InterviewReportHeaderResponseEvent
	private String emailAddress = "";
	private String emailSubject = "";
	
	//Used in Parental Rights Form
	private Collection guardians; //ui.juvenilecase.interviewinfo.form.InterviewGuardian
	private String victimsPhysicalInjuries = "";
	
	//Used in Request Attorney Appt
	private String[] selGuardianEmploymentRecord = new String[0];
	private Collection guardianEmploymentRecord = new ArrayList();//JuvenileFamilyForm.Guardian
	
	private FamilyInformationTO guardian1 = new FamilyInformationTO();
	private FamilyInformationTO guardian2 = new FamilyInformationTO();
	
	private JuvenileCourtDateEvent nextCourtDate = new JuvenileCourtDateEvent();
	
	private Collection stepParentEmploymentRecord = new ArrayList();
	
	private boolean wizardInterviewType = false ; // wizard or classic interview?
	
	//authorize rule: current logged on user should be the JPO for the casefile.
	//this variable will be set when the user is trying to view a specific interview.
	private boolean isAuthorized = false;
	
	private String attendanceStatusCd;
	private String attendanceStatus;
	private Collection attendanceStatusList = null ;
	private String addlAttendees="";
	private Collection contactNames = null;
	private String[] selectedAttendeeNames = null;
	private String selectedAttendeeNamesAsString = "";
	private Collection selectedNamesList = null;
	private String serviceEventId = "";

	public void clear()
	{
		currentInterview.clear();
		todayAppointments = emptyColl;
		allInterviews = emptyColl;
		interviewsGroupedByCasefileId = null;
		action = "";
		status = "";
		casefileId = "";
		juvenileNum = "";
		addressStatus = "";
		
		activeConstellationNum = "";
		attorneyOptionsNotes = "";
		spanishText = false;
		juvenileName = new Name();
		currentReportHeaderInfo = new InterviewReportHeaderResponseEvent();
		reportHistory = new ArrayList();
		emailAddress = "";
		emailSubject = "";
		
		guardians = emptyColl;
		victimsPhysicalInjuries = "";
		wizardInterviewType = false ;
		isAuthorized = false;
		
		attendanceStatusCd = "";
		attendanceStatus = "";
		attendanceStatusList = emptyColl ;
		addlAttendees = "";
		contactNames = emptyColl;
		selectedAttendeeNames = null;
		selectedAttendeeNamesAsString = "";
		selectedNamesList = emptyColl;
	}
	
	/*
	 * This reset method is being used to reset the checkboxes in 
	 * Parental Rights page (when the user is asked to select one
	 * or more Guardians to be notified).
	 */
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
	{
		String resetGuardians = (String)aRequest.getParameter("resetGuardians");
		
		if(resetGuardians != null && resetGuardians.equals("true"))
		{
			Collection<InterviewGuardian> gardianList = guardians ;
			for( InterviewGuardian guardian : gardianList )
			{
				guardian.setSelected(false);
			}
		}
		
		String resetGuardianEmployment = (String)aRequest.getParameter("resetGuardianEmployment");		
		if(resetGuardianEmployment != null && resetGuardianEmployment.equals("true"))
		{
			Collection<JuvenileFamilyForm.Guardian> employRecordList = guardianEmploymentRecord ;
			for( JuvenileFamilyForm.Guardian guardian : employRecordList )
			{
				//reset financialInfoSelected
				guardian.setFinancialInfoSelected(false);
				
				if(guardian.getEmploymentInfoList() != null)
				{
					Collection<JuvenileMemberForm.MemberEmployment> employmentList =  guardian.getEmploymentInfoList() ;
					for( JuvenileMemberForm.MemberEmployment employmentRecord : employmentList )
					{
						employmentRecord.setSelected(false);
					}
				}
			} // outer foreach
			
			//reset step-parent employment info (if applicable)
			if(stepParentEmploymentRecord != null)
			{
				Collection<JuvenileFamilyForm.Guardian> spEmploymentList = stepParentEmploymentRecord ;
				for( JuvenileFamilyForm.Guardian stepParent : spEmploymentList)
				{
					Collection<JuvenileMemberForm.MemberEmployment> employmentList = stepParent.getEmploymentInfoList() ;
					if( employmentList != null)
					{
						for( JuvenileMemberForm.MemberEmployment employmentRecord : employmentList )
						{
							employmentRecord.setSelected(false);
						}
					}
				} // outer foreach
			}
		}
	}
	
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return the allowUpdates
	 */
	public boolean getAllowUpdates() {
		return allowUpdates;
	}

	/**
	 * @param allowUpdates the allowUpdates to set
	 */
	public void setAllowUpdates(Boolean allowUpdates) {
		this.allowUpdates = allowUpdates;
	}

	/**
	 * @return Returns the addressStatus.
	 */
	public String getAddressStatus() {
		return addressStatus;
	}
	/**
	 * @param addressStatus The addressStatus to set.
	 */
	public void setAddressStatus(String addressStatus) {
		this.addressStatus = addressStatus;
		currentInterview.getNewAddress().setAddressStatus(addressStatus);
	}
	
	/*
	 *
	 */
	public JuvenileInterviewForm()
	{
		currentInterview = new JuvenileInterview(personsInterviewedList, interviewLocationList);
		UIJuvenileLoadCodeTables.getInstance().setJuvenileInterviewForm(this);
		
		if(petitionLevels.isEmpty()) 
		{
			petitionLevels.add(new PetitionLevelDegree(1, "CF", "Capital Felony"));
			petitionLevels.add(new PetitionLevelDegree(2, "F1", "Felony 1st degree"));
			petitionLevels.add(new PetitionLevelDegree(3, "F2", "Felony 2nd degree"));
			petitionLevels.add(new PetitionLevelDegree(4, "F3", "Felony 3rd degree"));
			petitionLevels.add(new PetitionLevelDegree(5, "JF", "State Jail Felony"));
			petitionLevels.add(new PetitionLevelDegree(6, "MA", "Misdemeanor Class A"));
			petitionLevels.add(new PetitionLevelDegree(7, "MB", "Misdemeanor Class B"));
			petitionLevels.add(new PetitionLevelDegree(8, "MC", "Misdemeanor Class C"));
			petitionLevels.add(new PetitionLevelDegree(9, "SO", "Status Offense"));
			petitionLevels.add(new PetitionLevelDegree(10, "CO", "City Ordinance"));
			petitionLevels.add(new PetitionLevelDegree(11, "AC", "Administrative Code"));
		}	
	}
	
	/**
	 * @return Returns the activeConstellationNum.
	 */
	public String getActiveConstellationNum() 
	{
		return activeConstellationNum;
	}
	
	/**
	 * @param activeConstellationNum The activeConstellationNum to set.
	 */
	public void setActiveConstellationNum(String activeConstellationNum) {
		this.activeConstellationNum = activeConstellationNum;
	}
	
	/**
	 * @return
	 */
	public Collection getAllInterviews()
	{
		return allInterviews;
	}


	/**
	 * @return
	 */
	public Collection getTodayAppointments()
	{
		return todayAppointments;
	}

	/**
	 * @param collection
	 */
	public void setAllInterviews(Collection collection)
	{
		allInterviews = collection;
	}

	/**
	 * @param collection
	 */
	public void setTodayAppointments(Collection collection)
	{
		todayAppointments = collection;
	}

	/**
	 * @return
	 */
	public Collection getInterviewLocationList()
	{
		return interviewLocationList;
		
	}

	/**
	 * @return
	 */
	public Collection getPersonsInterviewedList()
	{
		return personsInterviewedList;
	}

	/*
	 * @param aPersonsInterviewedList
	 * @return
	 */
	public Collection specialSortJuvFamMembersContacts(
			Collection<InterviewPersonResponseEvent> aPersonsInterviewedList)
	{
		if( aPersonsInterviewedList != null && aPersonsInterviewedList.size() > 1 )
		{
			ArrayList myListContacts = new ArrayList();;
			ArrayList myListFamilyMembers = new ArrayList();
			ArrayList totalList = new ArrayList();
			
			for( InterviewPersonResponseEvent myRespEvt : aPersonsInterviewedList )
			{
				String personType = myRespEvt.getTypeOfPerson().trim() ;
				
				if( personType.equalsIgnoreCase(InterviewPersonResponseEvent.PERSON_SELF))
				{
					totalList.add(myRespEvt);
				}
				else if( personType.equalsIgnoreCase(InterviewPersonResponseEvent.PERSON_CONTACT))
				{
					myListContacts.add(myRespEvt);
				}
				else if( personType.equalsIgnoreCase(InterviewPersonResponseEvent.PERSON_FAMILY))
				{
					myListFamilyMembers.add(myRespEvt);
				}
			}

			Collections.sort((List) myListContacts);
			Collections.sort((List) myListFamilyMembers);
			totalList.addAll(myListFamilyMembers);
			totalList.addAll(myListContacts);

			return totalList;
		}
		else
		{
			return aPersonsInterviewedList;
		}
	}

	/**
	 * @return
	 */
	public Collection getRecordsInventoryList()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.INTERVIEW_RECORD, true);
	}

	/**
	 * @param collection
	 */
	public void setPersonsInterviewedList(Collection collection)
	{
		personsInterviewedList = collection;
	}

	/**
	 * @return
	 */
	public JuvenileInterview getCurrentInterview()
	{
		return currentInterview;
	}

	/**
	 * @param interview
	 */
	public void setCurrentInterview(JuvenileInterview interview)
	{
		currentInterview = interview;
	}

	/**
	 * @return
	 */
	public String getCasefileId()
	{
		return casefileId;
	}

	/**
	 * @param string	
	 */
	public void setCasefileId(String string)
	{
		casefileId = string;
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
	 * @return Returns the listsSet.
	 */
	public boolean isListsSet() {
		return listsSet;
	}
	/**
	 * @param listsSet The listsSet to set.
	 */
	public void setListsSet(boolean listsSet) {
		this.listsSet = listsSet;
	}
	/**
	 * @param interviewLocationList The interviewLocationList to set.
	 */
	public void setInterviewLocationList(Collection collection) {
		interviewLocationList = collection;
	}
	
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	
	/**
	 * @return Returns the guardianAddressMap.
	 */
	public HashMap getGuardianAddressMap() {
		return guardianAddressMap;
	}
	/**
	 * @param guardianAddressMap The guardianAddressMap to set.
	 */
	public void setGuardianAddressMap(HashMap guardianAddressMap) {
		this.guardianAddressMap = guardianAddressMap;
	}
	/**
	 * @return Returns the attorneyOptionsNotes.
	 */
	public String getAttorneyOptionsNotes() {
		return attorneyOptionsNotes;
	}
	/**
	 * @param attorneyOptionsNotes The attorneyOptionsNotes to set.
	 */
	public void setAttorneyOptionsNotes(String attorneyOptionsNotes) {
		this.attorneyOptionsNotes = attorneyOptionsNotes;
	}
	/**
	 * @return Returns the spanishText.
	 */
	public boolean isSpanishText() {
		return spanishText;
	}
	/**
	 * @param spanishText The spanishText to set.
	 */
	public void setSpanishText(boolean spanishText) {
		this.spanishText = spanishText;
	}

	/*
	 * inner class
	 */
	public class InterviewQuestion extends InterviewQuestionResponseEvent
	{
		private boolean selected = false;
		
		public InterviewQuestion(InterviewQuestionResponseEvent iqre)
		{
			selected = false;
			if(iqre != null)
			{
				this.setQuestionId(iqre.getQuestionId());
				this.setQuestionText(iqre.getQuestionText());
			}
		}
		
		/**
		 * @return Returns the selected.
		 */
		public boolean isSelected() {
			return selected;
		}
		/**
		 * @param selected The selected to set.
		 */
		public void setSelected(boolean selected) {
			this.selected = selected;
		} 	
	} // end inner class InterviewQuestion
	
	/**
	 * @return Returns the interviewsGroupedByCasefileId.
	 */
	public HashMap getInterviewsGroupedByCasefileId() {
		return interviewsGroupedByCasefileId;
	}
	/**
	 * @param interviewsGroupedByCasefileId The interviewsGroupedByCasefileId to set.
	 */
	public void setInterviewsGroupedByCasefileId(HashMap interviewsGroupedByCasefileId) {
		this.interviewsGroupedByCasefileId = interviewsGroupedByCasefileId;
	}
	/**
	 * @return Returns the reportMap.
	 */
	public HashMap getReportMap() {
		return reportMap;
	}
	/**
	 * @param reportMap The reportMap to set.
	 */
	public void setReportMap(HashMap reportMap) {
		this.reportMap = reportMap;
	}
	/**
	 * @return Returns the currentReportHeaderInfo.
	 */
	public InterviewReportHeaderResponseEvent getCurrentReportHeaderInfo() {
		return currentReportHeaderInfo;
	}
	/**
	 * @param currentReportHeaderInfo The currentReportHeaderInfo to set.
	 */
	public void setCurrentReportHeaderInfo(InterviewReportHeaderResponseEvent currentReportHeaderInfo) {
		this.currentReportHeaderInfo = currentReportHeaderInfo;
	}
	/**
	 * @return Returns the juvenileName.
	 */
	public Name getJuvenileName() {
		return juvenileName;
	}
	/**
	 * @param juvenileName The juvenileName to set.
	 */
	public void setJuvenileName(Name juvenileName) {
		this.juvenileName = juvenileName;
	}
	/**
	 * @return Returns the reportHistory.
	 */
	public Collection getReportHistory() {
		return reportHistory;
	}
	/**
	 * @param reportHistory The reportHistory to set.
	 */
	public void setReportHistory(Collection reportHistory) {
		this.reportHistory = reportHistory;
	}
	/**
	 * @return Returns the emailAddress.
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress The emailAddress to set.
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return Returns the emailSubject.
	 */
	public String getEmailSubject() {
		return emailSubject;
	}
	/**
	 * @param emailSubject The emailSubject to set.
	 */
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	/**
	 * @return Returns the guardians.
	 */
	public Collection getGuardians() {
		return guardians;
	}
	/**
	 * @param guardians The guardians to set.
	 */
	public void setGuardians(Collection guardians) {
		this.guardians = guardians;
	}
	/**
	 * @return Returns the victimsPhysicalInjuries.
	 */
	public String getVictimsPhysicalInjuries() {
		return victimsPhysicalInjuries;
	}
	/**
	 * @param victimsPhysicalInjuries The victimsPhysicalInjuries to set.
	 */
	public void setVictimsPhysicalInjuries(String victimsPhysicalInjuries) {
		this.victimsPhysicalInjuries = victimsPhysicalInjuries;
	}
	/**
	 * @return Returns the explainationMethod.
	 */
	public Collection getExplanationMethodList() {
		Collection explanationMethodsList = 
			CodeHelper.getCodes(PDCodeTableConstants.EXPLANATION_METHOD, true);
		
		return explanationMethodsList;
	}
	
	
	/**
	 * @return Returns the selGuardianEmploymentRecord.
	 */
	public String[] getSelGuardianEmploymentRecord() {
		return selGuardianEmploymentRecord;
	}
	/**
	 * @param selGuardianEmploymentRecord The selGuardianEmploymentRecord to set.
	 */
	public void setSelGuardianEmploymentRecord(String[] selGuardianEmploymentRecord) {
		this.selGuardianEmploymentRecord = selGuardianEmploymentRecord;
	}
	
	/**
	 * @return Returns the guardianEmploymentRecordMap.
	 */
	public Collection getGuardianEmploymentRecord() {
		return guardianEmploymentRecord;
	}
	/**
	 * @param guardianEmploymentRecordMap The guardianEmploymentRecordMap to set.
	 */
	public void setGuardianEmploymentRecord(Collection guardianEmploymentRecordMap) {
		this.guardianEmploymentRecord = guardianEmploymentRecordMap;
	}
	/**
	 * @return Returns the guardian1.
	 */
	public FamilyInformationTO getGuardian1() {
		return guardian1;
	}
	/**
	 * @param guardian1 The guardian1 to set.
	 */
	public void setGuardian1(FamilyInformationTO guardian1) {
		this.guardian1 = guardian1;
	}
	/**
	 * @return Returns the guardian2.
	 */
	public FamilyInformationTO getGuardian2() {
		return guardian2;
	}
	/**
	 * @param guardian2 The guardian2 to set.
	 */
	public void setGuardian2(FamilyInformationTO guardian2) {
		this.guardian2 = guardian2;
	}
	/**
	 * @return Returns the nextCourtDate.
	 */
	public JuvenileCourtDateEvent getNextCourtDate() {
		return nextCourtDate;
	}
	/**
	 * @param nextCourtDate The nextCourtDate to set.
	 */
	public void setNextCourtDate(JuvenileCourtDateEvent nextCourtDate) {
		this.nextCourtDate = nextCourtDate;
	}
	/**
	 * @return Returns the stepParentEmploymentRecord.
	 */
	public Collection getStepParentEmploymentRecord() {
		return stepParentEmploymentRecord;
	}
	/**
	 * @param stepParentEmploymentRecord The stepParentEmploymentRecord to set.
	 */
	public void setStepParentEmploymentRecord(Collection stepParentEmploymentRecord) {
		this.stepParentEmploymentRecord = stepParentEmploymentRecord;
	}
	/**
	 * @return Returns the isAuthorized.
	 */
	public boolean isAuthorized() {
		return isAuthorized;
	}
	/**
	 * @param isAuthorized The isAuthorized to set.
	 */
	public void setAuthorized(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}
	/**
	 * @return Returns the todaysAppointmentGroupedByCasefileId.
	 */
	public HashMap getTodaysAppointmentGroupedByCasefileId() {
		return todaysAppointmentGroupedByCasefileId;
	}
	/**
	 * @param todaysAppointmentGroupedByCasefileId The todaysAppointmentGroupedByCasefileId to set.
	 */
	public void setTodaysAppointmentGroupedByCasefileId(HashMap todaysAppointmentGroupedByCasefileId) {
		this.todaysAppointmentGroupedByCasefileId = todaysAppointmentGroupedByCasefileId;
	}
	/**
	 * @return
	 */
	public Collection getWorkDays()
	{
		//Work days code table has to be sorted by ID, not just string comparison.
		return CodeHelper.getWorkDayCodes();
	}
	
	public HashMap getGuardianPhoneMap() 
	{
		return guardianPhoneMap;
	}
	
	public void setGuardianPhoneMap(HashMap guardianPhoneMap) 
	{
		this.guardianPhoneMap = guardianPhoneMap;
	}
	
	/*
	 * inner class
	 */
	public class PetitionLevelDegree
	{
		int level = 0;
		String levelCode;
		String levelLiteral;
		
		public PetitionLevelDegree(int level, String levelCode, String levelLiteral) 
		{
			this.level = level;
			this.levelCode = levelCode;
			this.levelLiteral = levelLiteral;
		}
		
		public int getLevel() 
		{
			return level;
		}
		
		public void setLevel(int level) 
		{
			this.level = level;
		}
		
		public String getLevelCode() 
		{
			return levelCode;
		}
		
		public void setLevelCode(String levelCode) 
		{
			this.levelCode = levelCode;
		}
		
		public String getLevelLiteral() 
		{
			return levelLiteral;
		}
		
		public void setLevelLiteral(String levelLiteral) 
		{
			this.levelLiteral = levelLiteral;
		}
	}

	public boolean isWizardInterviewType()
	{
		return wizardInterviewType;
	}

	public void setWizardInterviewType( boolean wizardInterviewType )
	{
		this.wizardInterviewType = wizardInterviewType;
	}

	public String getAttendanceStatusCd() {
		return attendanceStatusCd;
	}

	public void setAttendanceStatusCd(String attendanceStatusCd) {
		this.attendanceStatusCd = attendanceStatusCd;
	}

	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public Collection getAttendanceStatusList() {
		return attendanceStatusList;
	}

	public void setAttendanceStatusList(Collection attendanceStatusList) {
		this.attendanceStatusList = attendanceStatusList;
	}

	public String getAddlAttendees() {
		return addlAttendees;
	}

	public void setAddlAttendees(String addlAttendees) {
		this.addlAttendees = addlAttendees;
	}

	public Collection getContactNames() {
		return contactNames;
	}

	public void setContactNames(Collection contactNames) {
		this.contactNames = contactNames;
	}

	public String[] getSelectedAttendeeNames() {
		return selectedAttendeeNames;
	}

	public void setSelectedAttendeeNames(String[] selectedAttendeeNames) {
		this.selectedAttendeeNames = selectedAttendeeNames;
		HashMap attendees = new HashMap();
		for( int i = 0; i < selectedAttendeeNames.length; i++ )
		{	
			for( Iterator iter = contactNames.iterator(); iter.hasNext(); /* empty */)
			{
				AttendeeNameResponseEvent attendee = (AttendeeNameResponseEvent)iter.next();
				String fullName = attendee.getFormattedName() ;
				if( StringUtils.isNotEmpty(selectedAttendeeNames[i]) && 
					selectedAttendeeNames[i].equalsIgnoreCase(fullName) && 
					attendees.get(fullName) == null )
				{
					attendees.put( fullName, attendee );
				}
				//attendeeList.add(selectedAttendeeNames[i]);
			}

		}
		List attendeeList = new ArrayList();
		attendeeList.addAll(attendees.values());
		Collections.sort(attendeeList);
		setSelectedNamesList(attendeeList);
	}

	public Collection getSelectedNamesList() {
		return selectedNamesList;
	}

	public void setSelectedNamesList(Collection selectedNamesList) {
		this.selectedNamesList = selectedNamesList;
	}

	public String getSelectedAttendeeNamesAsString() {
		return selectedAttendeeNamesAsString;
	}

	public void setSelectedAttendeeNamesAsString(
			String selectedAttendeeNamesAsString) {
		this.selectedAttendeeNamesAsString = selectedAttendeeNamesAsString;
	}

	public String getServiceEventId() {
		return serviceEventId;
	}

	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}

	public static class PersonResponsible implements Comparable 
	{
		private String name;
		private String type;
		private String description;
		private String lastName;
		private String middleName;
		private String firstName;
		
		
		public String getName() {
			return name;
		}
		
		public void setName(String n) {
			name = n;
		}
		
		public String getType() {
			return type;
		}
		
		public void setType(String n) {
			type = n;
		}
		
		public int compareTo(Object obj) 
		{
			PersonResponsible evt = (PersonResponsible) obj;
			String person1 = name.trim();
			String person2 = evt.getName().trim();		
			
			return person1.compareToIgnoreCase(person2);
		}
		
		/**
		 * @return Returns the description.
		 */
		public String getDescription() {
			return description;
		}
		/**
		 * @param description The description to set.
		 */
		public void setDescription(String description) {
			this.description = description;
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
		public void setMiddleName(String string)
		{
			middleName = string;
		}

		public String getFormattedName()
		{
			Name name = new Name(firstName, middleName, lastName);
			return name.getFormattedName().trim();	
		}

	}
}


