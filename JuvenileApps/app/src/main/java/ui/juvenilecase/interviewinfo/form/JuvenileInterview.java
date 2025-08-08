/*
 * Created on Jan 29, 2007
 *
 */
package ui.juvenilecase.interviewinfo.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.contact.to.Address;
import messaging.interviewinfo.reply.InterviewDetailResponseEvent;
import messaging.interviewinfo.reply.InterviewPersonResponseEvent;
import messaging.interviewinfo.reply.InterviewResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import ui.common.CodeHelper;

/**
 * @author awidjaja
 * 
 */
public class JuvenileInterview
{
	private static Collection emptyColl = new ArrayList();

	private String casefileId = "";
	// A reference of those in JuvenileInterviewForm
	// Note: These are dynamic list
	private Collection personsInterviewedList;
	private Collection interviewLocationList;

	// used for view
	private String interviewId;

	// user input
	private String interviewTimeStr = "";
	private String interviewDateStr = "";
	private String[] personsInterviewed;
	private String[] recordsInventoryIds;
	private Collection currentRecordsInventoryIds;
	private String otherInventoryRecords = "";
	private Collection currentOtherInventoryRecords;
	private String interviewTypeId = "";
	private String juvLocUnitId = "";
	private String juvLocUnitDescription = "";
	private String summaryNote = "";
	private String userComments = "";
	private String progressReport = "";

	private String[] selectedCategories;
	private Collection interviewQuestions;
	private Collection positiveAnsweredQuestions;
	private Collection interviewTasks;
	private String[] selectedTasks;

	// Processed Data
	private Date interviewDate;
	private Collection selectedPersonsInterviewed;
	private Collection recordsInventoryDisplay;
	private Collection currentRecordsInventory;

	private String recordsInventory = "";
	private String interviewType = "";
	private String interviewStatusCd = "";
	private String interviewStatusDescription = "";

	private String eventStatusCd = "";
	private String eventStatusDescription = "";

	private String attendanceStatusCd = "";
	private String attendanceStatusDescription = "";
	private String calEventId = "";

	private int taskCount;

	private Address newAddress = new Address();
	
	//added for Bug #32185
	private String personsInterviewedStr="";

	private JuvenileInterview()
	{
	}

	/*
	 * 
	 */
	public JuvenileInterview(Collection personsInterviewedList, Collection interviewLocationList)
	{
		this.personsInterviewedList = personsInterviewedList;
		this.interviewLocationList = interviewLocationList;
	}

	/**
	 * 
	 */
	public void clear()
	{
		// used for view
		interviewId = "";

		// user input
		interviewTimeStr = "";
		interviewDate = null;
		personsInterviewed = new String[0];
		recordsInventoryIds = new String[0];
		currentRecordsInventoryIds = new ArrayList();
		otherInventoryRecords = "";
		currentOtherInventoryRecords = new ArrayList();

		interviewTypeId = "";
		summaryNote = "";
		userComments = "";
		progressReport = "";
		juvLocUnitId = "";
		juvLocUnitDescription = "";
		selectedCategories = new String[0];

		positiveAnsweredQuestions = emptyColl;
		interviewTasks = emptyColl;

		// Processed Data
		// interviewDate;
		selectedPersonsInterviewed = emptyColl;
		recordsInventoryDisplay = emptyColl;

		recordsInventory = "";
		interviewType = "";
		interviewStatusCd = "";
		interviewStatusDescription = "";

		newAddress = new Address();
	}

	// this method will copy data from response event to UI Object
	public void populateInterviewData(InterviewDetailResponseEvent evt)
	{
		// Date isn't editable, so it can be directly displayed using bean:write
		setInterviewDate(evt.getInterviewDate());
		setCalEventId(evt.getCalEventId());
		java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat(UIConstants.DISPLAY_PRESENTATION_TYPE_TIME_12HOUR_AM_PM);
		setInterviewTimeStr(fmt.format(evt.getInterviewDate()));

		setJuvLocUnitDescription(evt.getLocationDescription());

		setJuvLocUnitId(evt.getJuvLocUnitId());
		setInterviewStatusCd(evt.getInterviewStatusCd());
		setInterviewStatusDescription(evt.getInterviewStatusDescription());
		setInterviewTypeId(evt.getInterviewTypeId());

		// display formatting for inventory record
		Collection recordsInventoryDisplay = new ArrayList();
		for( Iterator iter = evt.getInventoryRecordsIds().iterator(); iter.hasNext(); /* empty */)
		{
			String recordInventoryDescription = CodeHelper.getCodeDescription(PDCodeTableConstants.INTERVIEW_RECORD, (String)iter.next());
			// only add valid entries
			if( recordInventoryDescription != null && recordInventoryDescription.length() > 0 )
			{
				recordsInventoryDisplay.add(recordInventoryDescription);
			}
		}

		setRecordsInventoryDisplay(recordsInventoryDisplay);
		// For UI Display only, so that the drop down is pre-selected with previous
		// data
		setRecordsInventoryIds((String[])evt.getInventoryRecordsIds().toArray(new String[0]));
		setOtherInventoryRecords(evt.getOtherInventoryRecords());

		Collections.sort(evt.getInterviewPersons());
		setSelectedPersonsInterviewed(evt.getInterviewPersons());

		String[] persons = new String[evt.getInterviewPersons().size()];
		int count = 0;
		Iterator iter = evt.getInterviewPersons().iterator();
		while( iter.hasNext() )
		{
			InterviewPersonResponseEvent ipre = (InterviewPersonResponseEvent)iter.next();
			persons[count] = ipre.getFormattedName();
			count++;
		}

		personsInterviewed = persons;

		setSummaryNote(evt.getSummaryNotes());
		setProgressReport( evt.getProgressReport() ) ;

		if( evt.getJuvLocUnitId() == null )
		{
			setJuvLocUnitId("newaddress");
			setNewAddress((Address)evt.getAddress());
		}

	}

	/**
	 * @return Returns the juvLocUnitId.
	 */
	public String getJuvLocUnitId()
	{
		return juvLocUnitId;
	}

	public String getJuvLocUnitDescription()
	{
		return juvLocUnitDescription;
	}

	/**
	 * @return Returns the selectedTasks.
	 */
	public String[] getSelectedTasks()
	{
		return selectedTasks;
	}

	/**
	 * @param selectedTasks
	 *          The selectedTasks to set.
	 */
	public void setSelectedTasks(String[] selectedTasks)
	{
		this.selectedTasks = selectedTasks;
	}

	/**
	 * @return Returns the interviewQuestions.
	 */
	public Collection getInterviewQuestions()
	{
		return interviewQuestions;
	}

	/**
	 * @param interviewQuestions
	 *          The interviewQuestions to set.
	 */
	public void setInterviewQuestions(Collection interviewQuestions)
	{
		this.interviewQuestions = interviewQuestions;
	}

	/**
	 * @return Returns the interviewId.
	 */
	public String getInterviewId()
	{
		return interviewId;
	}

	/**
	 * @param interviewId
	 *          The interviewId to set.
	 */
	public void setInterviewId(String interviewId)
	{
		this.interviewId = interviewId;
	}

	/**
	 * @return Returns the personsInterviewedDisplay.
	 */
	public Collection getSelectedPersonsInterviewed()
	{
		return selectedPersonsInterviewed;
	}

	/**
	 * @param personsInterviewedDisplay
	 *          The personsInterviewedDisplay to set.
	 */
	public void setSelectedPersonsInterviewed(Collection selectedPersonsInterviewed)
	{
		this.selectedPersonsInterviewed = selectedPersonsInterviewed;
	}

	/**
	 * @return Returns the recordsInventoryDisplay.
	 */
	public Collection getRecordsInventoryDisplay()
	{
		return recordsInventoryDisplay;
	}

	/**
	 * @param recordsInventoryDisplay
	 *          The recordsInventoryDisplay to set.
	 */
	public void setRecordsInventoryDisplay(Collection recordsInventoryDisplay)
	{
		this.recordsInventoryDisplay = recordsInventoryDisplay;
	}

	/**
	 * @param personsInterviewed
	 *          The personsInterviewed to set.
	 */
	public void setPersonsInterviewed(String[] personsInterviewed)
	{
		HashMap persons = new HashMap();

		this.personsInterviewed = personsInterviewed;
		for( int i = 0; i < personsInterviewed.length; i++ )
		{
			for( Iterator iter = personsInterviewedList.iterator(); iter.hasNext(); /* empty */)
			{
				InterviewPersonResponseEvent person = (InterviewPersonResponseEvent)iter.next();
				if(person!=null)
				{
					String fullName = person.getFormattedName() ;
					if( personsInterviewed[i] != null && personsInterviewed[i].equalsIgnoreCase(fullName) && 
							persons.get(fullName) == null )
					{
						persons.put( fullName, person );
					}
				}
			}
		}

		List personsList = new ArrayList();
		personsList.addAll(persons.values());
		Collections.sort(personsList);
		setSelectedPersonsInterviewed(personsList);
	}

	/**
	 * @return Returns the summaryNote.
	 */
	public String getSummaryNote()
	{
		return summaryNote;
	}

	/**
	 * @param summaryNote
	 *          The summaryNote to set.
	 */
	public void setSummaryNote(String summaryNote)
	{
		this.summaryNote = summaryNote;
	}

	/**
	 * @return
	 */
	public Date getInterviewDate()
	{
		return interviewDate;
	}

	/**
	 * @return
	 */
	public String getInterviewType()
	{
		if( interviewTypeId.length() < 1 && interviewType.length() > 0 )
		{
			return interviewType;
		}
		else
		{
			return CodeHelper.getCodeDescription(PDCodeTableConstants.INTERVIEW_TYPE, interviewTypeId);
		}
	}

	/**
	 * @return
	 */
	public String getInterviewTypeId()
	{
		return interviewTypeId;
	}

	/**
	 * @return
	 */
	public Address getNewAddress()
	{
		return newAddress;
	}

	/**
	 * @return
	 */
	public String getOtherInventoryRecords()
	{
		return otherInventoryRecords;
	}

	/**
	 * @return
	 */
	public String getRecordsInventory()
	{
		return recordsInventory;
	}

	/**
	 * @return
	 */
	public String[] getRecordsInventoryIds()
	{
		return recordsInventoryIds;
	}

	/**
	 * @param date
	 */
	public void setInterviewDateStr(String inputDate)
	{
		if( inputDate != null )
		{
			interviewDateStr = inputDate;
			Date iDate = DateUtil.stringToDate(inputDate, UIConstants.DATE_FMT_1);

			if( iDate != null )
			{
				interviewDate = iDate;
			}
		}
	}

	/*
	 * 
	 */
	public String getInterviewDateStr()
	{
		if( interviewDate != null )
		{
			return DateUtil.dateToString(interviewDate, UIConstants.DATE_FMT_1);
		}
		else
		{
			return( "" );
		}
	}

	/**
	 * @param string
	 */
	public void setJuvLocUnitId(String string)
	{
		this.juvLocUnitId = string;

		if( juvLocUnitId != null && !juvLocUnitId.equals("newaddress") )
		{
			for( Iterator locUnitIter = interviewLocationList.iterator(); locUnitIter.hasNext(); /*empty*/ )
			{
				LocationResponseEvent lre = (LocationResponseEvent)locUnitIter.next();
				if( lre.getJuvLocationUnitId().equals(this.getJuvLocUnitId()) )
				{
					this.juvLocUnitDescription = lre.getLocationUnitName();
				}
			}
		}
		else
		{
			this.juvLocUnitDescription = "New Address";
		}
	}

	/**
	 * @param string
	 */
	public void setInterviewType(String string)
	{
		interviewType = string;
	}

	/**
	 * @param string
	 */
	public void setInterviewTypeId(String string)
	{
		interviewTypeId = string;

		if( interviewTypeId == null || interviewTypeId.equals("") )
		{
			interviewTypeId = "";
			return;
		}

		if( getInterviewTypeList() != null && getInterviewTypeList().size() > 0 )
		{
			interviewType = CodeHelper.getCodeDescriptionByCode(getInterviewTypeList(), interviewTypeId);
		}
	}

	/**
	 * @param string
	 */
	public void setOtherInventoryRecords(String string)
	{
		otherInventoryRecords = string;
	}

	/**
	 * @param string
	 */
	public void setRecordsInventory(String string)
	{
		recordsInventory = string;
	}

	/**
	 * @param collection
	 */
	public void setRecordsInventoryIds(String[] strings)
	{
		recordsInventoryIds = strings;
	}

	/**
	 * @param date
	 */
	public void setInterviewDate(Date date)
	{
		interviewDate = date;
	}

	/**
	 * @return
	 */
	public String getInterviewTimeStr()
	{
		return interviewTimeStr;
	}

	/**
	 * @param string
	 */
	public void setInterviewTimeStr(String string)
	{
		interviewTimeStr = string;
	}

	/**
	 * @return Returns the personsInterviewed.
	 */
	public String[] getPersonsInterviewed()
	{
		return personsInterviewed;
	}
	
	/**
	 * @return Returns the personsInterviewedList.
	 */
	public Collection getPersonsInterviewedList()
	{
		return personsInterviewedList;
	}

	/**
	 * @param newAddress
	 *          The newAddress to set.
	 */
	public void setNewAddress(Address newAddress)
	{
		this.newAddress = newAddress;
	}

	/**
	 * @return Returns the selectedCategories.
	 */
	public String[] getSelectedCategories()
	{
		return selectedCategories;
	}

	/**
	 * @param selectedCategories
	 *          The selectedCategories to set.
	 */
	public void setSelectedCategories(String[] selectedCategories)
	{
		this.selectedCategories = selectedCategories;
	}

	/**
	 * @return Returns the positiveAnsweredQuestions.
	 */
	public Collection getPositiveAnsweredQuestions()
	{
		return positiveAnsweredQuestions;
	}

	/**
	 * @return Returns the interviewTasks.
	 */
	public Collection getInterviewTasks()
	{
		return interviewTasks;
	}

	/**
	 * @param interviewTasks
	 *          The interviewTasks to set.
	 */
	public void setInterviewTasks(Collection interviewTasks)
	{
		this.interviewTasks = interviewTasks;
	}

	/**
	 * @param positiveAnsweredQuestions
	 *          The positiveAnsweredQuestions to set.
	 */
	public void setPositiveAnsweredQuestions(Collection positiveAnsweredQuestions)
	{
		this.positiveAnsweredQuestions = positiveAnsweredQuestions;
	}

	public Collection getInterviewTypeList()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.INTERVIEW_TYPE, true);
	}

	/**
	 * @return Returns the addressTypeList.
	 */
	public Collection getAddressTypeList()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.ADDRESS_TYPE, true);
	}

	/**
	 * @return Returns the countyList.
	 */
	public Collection getCountyList()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.COUNTY, true);
	}

	/**
	 * @return Returns the interviewStatusList.
	 */
	public Collection getInterviewStatusList()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.COMPLETION_STATUS, true);
	}

	/**
	 * @return Returns the stateList.
	 */
	public Collection getStateList()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.STATE_ABBR, true);
	}

	/**
	 * @return Returns the streetTypeList.
	 */
	public Collection getStreetTypeList()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.STREET_TYPE, true);
	}
	/**
	 * @return Returns the streetNumSuffixList.
	 */
	public Collection getStreetNumSuffixList()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.STREET_SUFFIX, true);
	}

	/**
	 * @return Returns the interviewQuestionCategory.
	 */
	public Collection getInterviewQuestionCategory()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.INTERVIEW_QUESTION_CATEGORY, true);
	}

	public String getState()
	{
		String state = "";
		if( newAddress.getStateCode() != null )
		{
			state = CodeHelper.getCodeDescription(PDCodeTableConstants.STATE_ABBR, newAddress.getStateCode());
		}
		
		return state;
	}

	public String getStreetType()
	{
		String streetType = "";
		if( newAddress.getStreetTypeCode() != null )
		{
			streetType = CodeHelper.getCodeDescription(PDCodeTableConstants.STREET_TYPE, newAddress.getStreetTypeCode());
		}
		
		return streetType;
	}
	
	public String getStreetNumSuffix()
	{
		String streetNumSuffix = "";
		if( newAddress.getStreetNumSuffixCode() != null )
		{
			streetNumSuffix = CodeHelper.getCodeDescription(PDCodeTableConstants.STREET_SUFFIX, newAddress.getStreetNumSuffixCode());
		}
		
		return streetNumSuffix;
	}

	/**
	 * @return Returns the casefileId.
	 */
	public String getCasefileId()
	{
		return casefileId;
	}

	/**
	 * @param casefileId
	 *          The casefileId to set.
	 */
	public void setCasefileId(String casefileId)
	{
		this.casefileId = casefileId;
	}

	public static Collection convertRE(Collection interviewList, 
			Collection interviewLocationList, Collection personsInterviewedList)
	{
		Collection col = new ArrayList();

		for( Iterator iter = interviewList.iterator(); iter.hasNext(); /*empty*/ )
		{
			JuvenileInterview interview = new JuvenileInterview(personsInterviewedList, interviewLocationList);
			InterviewResponseEvent ire = (InterviewResponseEvent)iter.next();
			
			interview.setInterviewStatusCd(ire.getInterviewStatusCd());
			interview.setInterviewStatusDescription(ire.getInterviewStatusDescription());
			interview.setInterviewDate(ire.getInterviewDate());
			interview.setInterviewId(ire.getInterviewId());
			interview.setJuvLocUnitDescription(ire.getLocationDescription());
			interview.setInterviewTypeId(ire.getInterviewTypeId());
			interview.setCasefileId(ire.getCasefileId());
			interview.setInterviewStatusCd(ire.getInterviewStatusCd());
			interview.setInterviewStatusDescription(ire.getInterviewStatusDescription());
			interview.setSummaryNote(ire.getSummaryNotes());
			interview.setUserComments(ire.getUserComments());
			interview.setProgressReport(ire.getProgressReport());
			interview.setAttendanceStatusCd(ire.getAttendanceStatusCd());
			interview.setCalEventId(ire.getCalEventId());
			interview.setTaskCount(ire.getTaskCount());
			col.add(interview);
		}
		
		return col;
	}

	/**
	 * @return Returns the currentRecordsInventory.
	 */
	public Collection getCurrentRecordsInventory()
	{
		return currentRecordsInventory;
	}

	/**
	 * @param currentRecordsInventory
	 *          The currentRecordsInventory to set.
	 */
	public void setCurrentRecordsInventory(Collection currentRecordsInventory)
	{
		this.currentRecordsInventory = currentRecordsInventory;
	}

	/**
	 * @return Returns the currentOtherInventoryRecords.
	 */
	public Collection getCurrentOtherInventoryRecords()
	{
		return currentOtherInventoryRecords;
	}

	/**
	 * @param currentOtherInventoryRecords
	 *          The currentOtherInventoryRecords to set.
	 */
	public void setCurrentOtherInventoryRecords(Collection currentOtherInventoryRecords)
	{
		this.currentOtherInventoryRecords = currentOtherInventoryRecords;
	}

	/**
	 * @return Returns the currentRecordsInventoryIds.
	 */
	public Collection getCurrentRecordsInventoryIds()
	{
		return currentRecordsInventoryIds;
	}

	/**
	 * @param currentRecordsInventoryIds
	 *          The currentRecordsInventoryIds to set.
	 */
	public void setCurrentRecordsInventoryIds(Collection currentRecordsInventoryIds)
	{
		this.currentRecordsInventoryIds = currentRecordsInventoryIds;
	}

	/**
	 * @param juvLocUnitDescription
	 *          The juvLocUnitDescription to set.
	 */
	public void setJuvLocUnitDescription(String juvLocUnitDescription)
	{
		this.juvLocUnitDescription = juvLocUnitDescription;
	}

	public String getFormattedAddress(Address address)
	{
		String str = "" ;
		StringBuffer sb = new StringBuffer();

		sb.append(address.getStreetNum());
		sb.append(" ");
		sb.append(address.getStreetName());
		sb.append(" ");
		
		str = address.getStreetTypeCode() ;
		if( str != null && str.trim().length() > 0 )
		{
			sb.append(CodeHelper.getCodeDescription(PDCodeTableConstants.STREET_TYPE, str) );
			sb.append(" ");
		}

		str = address.getAptNum() ;
		if( str != null && str.trim().length() > 0 )
		{
			// no need to put any sign before aptNum since 
			// it can be interpreted as suite number as well
			sb.append(str);
			sb.append(" ");
		}

		sb.append(", ");
		sb.append(address.getCity());
		sb.append(", ");

		str = address.getStateCode();
		if( str != null && str.trim().length() > 0 )
		{
			sb.append(CodeHelper.getCodeDescription(PDCodeTableConstants.STATE_ABBR, str));
			sb.append(", ");
		}

		sb.append(address.getZipCode());

		str = address.getAdditionalZipCode() ;
		if( str != null && str.length() > 0 )
		{
			sb.append("-");
			sb.append(str);
		}

		return sb.toString();

	}

	/**
	 * @return Returns the interviewStatusCd.
	 */
	public String getInterviewStatusCd()
	{
		return interviewStatusCd;
	}

	/**
	 * @param interviewStatusCd
	 *          The interviewStatusCd to set.
	 */
	public void setInterviewStatusCd(String interviewStatusCd)
	{
		this.interviewStatusCd = interviewStatusCd;
	}

	/**
	 * @return Returns the interviewStatusDescription.
	 */
	public String getInterviewStatusDescription()
	{
		return interviewStatusDescription;
	}

	/**
	 * @param interviewStatusDescription
	 *          The interviewStatusDescription to set.
	 */
	public void setInterviewStatusDescription(String interviewStatusDescription)
	{
		this.interviewStatusDescription = interviewStatusDescription;
	}

	/**
	 * @return Returns the eventStatusCd.
	 */
	public String getEventStatusCd()
	{
		return eventStatusCd;
	}

	/**
	 * @param eventStatusCd
	 *          The eventStatusCd to set.
	 */
	public void setEventStatusCd(String eventStatusCd)
	{
		this.eventStatusCd = eventStatusCd;
	}

	/**
	 * @return Returns the eventStatusDescription.
	 */
	public String getEventStatusDescription()
	{
		return eventStatusDescription;
	}

	/**
	 * @param eventStatusDescription
	 *          The eventStatusDescription to set.
	 */
	public void setEventStatusDescription(String eventStatusDescription)
	{
		this.eventStatusDescription = eventStatusDescription;
	}

	public String getAttendanceStatusCd()
	{
		return attendanceStatusCd;
	}

	public void setAttendanceStatusCd(String attendanceStatusCd)
	{
		this.attendanceStatusCd = attendanceStatusCd;

		if( attendanceStatusCd != null && attendanceStatusCd.length() > 0 )
		{
			this.attendanceStatusDescription = CodeHelper.getCodeDescription(PDCodeTableConstants.SERVEVENT_ATTENDANCE_STATUS, attendanceStatusCd);
		}
	}

	public String getAttendanceStatusDescription()
	{
		return attendanceStatusDescription;
	}

	public void setAttendanceStatusDescription(String attendanceStatusDescription)
	{
		this.attendanceStatusDescription = attendanceStatusDescription;
	}

	public String getCalEventId()
	{
		return calEventId;
	}

	public void setCalEventId(String calEventId)
	{
		this.calEventId = calEventId;
	}

	public int getTaskCount()
	{
		return taskCount;
	}

	public void setTaskCount(int taskCount)
	{
		this.taskCount = taskCount;
	}

	public String getUserComments() {
		return userComments;
	}

	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	public String getProgressReport() {
		return progressReport;
	}

	public void setProgressReport(String progressReport) {
		this.progressReport = progressReport;
	}

	public String getPersonsInterviewedStr() {
		return personsInterviewedStr;
	}

	public void setPersonsInterviewedStr(String personsInterviewedStr) {
		this.personsInterviewedStr = personsInterviewedStr;
	}

	
}
