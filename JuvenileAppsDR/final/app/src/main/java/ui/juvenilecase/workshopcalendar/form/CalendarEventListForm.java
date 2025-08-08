/*
 * Created on Jun 6, 2006
 *
 */
package ui.juvenilecase.workshopcalendar.form;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import messaging.calendar.reply.AttendeeNameResponseEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.DocketEventResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCalendarConstants;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.interviewinfo.form.JuvenileInterview;
import ui.juvenilecase.programreferral.UIProgramReferralBean;
import ui.security.SecurityUIHelper;
import ui.supervision.administerserviceprovider.form.ServiceProviderForm;

/**
 * @author dwilliamson
 *
 */
public class CalendarEventListForm extends ActionForm
{
	private String selectedValue = PDConstants.BLANK;
	
	private Date startDate;
	private Date endDate;
	private Date eventDate;
	private String calendarEventId;

	private String eventSessionLength;
	private String eventSessionLengthDesc;

	private String caseStatus ;
	private String eventStatus;
	private String eventType;
	private String calendarType;

	private String serviceLocationId;
	private String serviceLocationName;
	private String serviceProviderName;
	private String serviceProviderId;

	private String officerId;
	private String officerOtherID ;

	private String juvenileNum;
	private String guardianInHouse;
	
	private String attendanceStatusCd;
	private String attendanceStatus;
	private Boolean isAttendedExcusedAbsent;
	private String progressNotes;
	private String existingProgressNotes;
	private String summaryNotes ;
	private String selectedAttendeeNamesAsString;
	
	private List docketEvents;
	private DocketEventResponseEvent currentDocketEvent;
	private ServiceProviderForm serviceProvider ;

	private Collection dayEvents = null;
	// used to make sorting on jsp page on juvenile name possible
	private Collection individualDayEvents = null; 
	private Collection recordsInventoryList = null;
	private Collection attendanceStatusList = null ;
	private Collection nameSearchResults = null ;
	// selected events to cancel from future event list jsp.
	private String[] selectedCancelEvents; 
	// used display the selected cancel events.
	private Collection selectedCancelEventsList = null; 
	private List programReferralList;	

	private int listCount ;
	private UIProgramReferralBean programReferral;
	
	private CalendarServiceEventResponseEvent currentEvent;	

	private JuvenileInterview currentInterview;
	private String currentJuvenileId;
	private String action;
	
	private Collection cancelledEventsList;
	//Activity JIMS200057657 - Document number of add'l attendees
	private String addlAttendees="";
	private Collection contactNames = null;
	private String[] selectedAttendeeNames = null;
	private Collection selectedNamesList = null;
	private String secondaryAction = "";
	private boolean tentativeRefPrgmRef=false;  //added for #36737
	
	//Add Instructor Search Option ER #48490
	
	private static SearchEvent searchEvent = new SearchEvent();
	/**
	 * 
	 */
	public CalendarEventListForm()
	{
		recordsInventoryList = new ArrayList();		
		programReferralList = new ArrayList();
		programReferral = null;
		selectedCancelEventsList = new ArrayList();
		nameSearchResults = new ArrayList() ;
		contactNames = new ArrayList();
		selectedAttendeeNames = new String[0];
		selectedNamesList = new ArrayList();		
		action = PDConstants.BLANK ;	
		tentativeRefPrgmRef=false;  //added for #36737
	}
	
	/*
	 * This reset will be invoked every time the form is submitted.
	 * We will need to reset the selection of documents for appointment letter or
	 * the list will still maintain previously selected items.
	 */
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
	{
		String resetDocApptLetter = (String)aRequest.getParameter("resetDocApptLetter");

		if("true".equals(resetDocApptLetter)) 
		{
			this.getCurrentInterview().setRecordsInventoryIds(new String[0]);
		}
		
		//<KISHORE>JIMS200056905 : Service should not be required to search for future Service Provider events in View Calendar
		if(this.getSearchEvent() != null){
			this.getSearchEvent().selectedServices = null ;
			this.getSearchEvent().instructorName = UIConstants.EMPTY_STRING;
		}
	}

	/* inner class, owned by Form, to house search criteria
	 */
	public static class SearchEvent
	{
		private final List emptyCollection = new ArrayList() ;
		
		private boolean haveSearchResults ;
		private String searchType;
		
		private String juvenileLastName;
		private String juvenileFirstName;
		private String juvenileMiddleName;
		private String juvenileFullname ;
		
		private String searchLastName;
		private String searchFirstName;
		private String searchMiddleName;

		private String juvenileNum;
		
		private String serviceProviderId;
		private String serviceProviderName;
		
		private String jpoUserId;
		
		private String juvLocUnitId;
		private String eventTypeId;
		
		private String nameSearchType;
		private boolean doNameSearch = false ;
		
		private String officerLastName;
		private String officerFirstName;
		private String officerMiddleName;
		private String officerFullname;
		
		private String eventStatusCd;
		
		private String startDateStr;
		private String endDateStr;
		
		private List juvUnitList;
		private List serviceTypeList;
		private List serviceTypePermList;
		private List serviceProviderList;
		
		// new
		private List preScheduledServices ;
		private List eventList ;

		// Services selected for search results
		private String[] selectedServices;

		private boolean goneToSearchResultsPage ;
		private boolean showNameSection ;
		private boolean showDateSection ;
		private boolean showStatusSection ;
		private boolean showJPOSection ;
		private boolean showSPSection ;
		private boolean showButtonSection ;
		private boolean showJuvNumSection ;
		private boolean showJuvNameSection ;
		private boolean showEventTypeSection ;
		private boolean showFutureSPSection ;
		
		private boolean showProgramSelectionSection ;
		
		//Add Instructor Search Option ER #48490
		private boolean showInstructorSelectionSection;
		private boolean showSPForInstructorSelectionSection;
		private List instructorList;
		private String instructorName;

		/**
		 * 
		 */
		public SearchEvent() 
		{
			searchType = PDConstants.BLANK ;
			juvUnitList = emptyCollection ;
			serviceTypeList = emptyCollection ;
			serviceProviderList = emptyCollection ;
			serviceTypePermList = emptyCollection ;
			preScheduledServices = emptyCollection ;
			eventList = emptyCollection ;
			selectedServices = null ;
		}
		
		public void clearSections() {
			goneToSearchResultsPage = false ;
			doNameSearch = false ;
			showNameSection = false ;
			showDateSection = false ;
			showStatusSection = false ;
			showJPOSection = false ;
			showSPSection = false ;
			showJuvNumSection = false ;
			showJuvNameSection = false  ;
			showButtonSection = false ;
			showEventTypeSection = false ;
			showFutureSPSection = false ;
			showProgramSelectionSection = false ;
			showInstructorSelectionSection = false;
			showSPForInstructorSelectionSection = false;
		}

		/*
		 */
		public void clear()
		{			
			haveSearchResults = false ;
			juvenileLastName = PDConstants.BLANK ;
			juvenileFirstName = PDConstants.BLANK ;
			juvenileMiddleName = PDConstants.BLANK ;
			juvenileFullname = PDConstants.BLANK ;
			
			juvenileNum = PDConstants.BLANK;
			
			jpoUserId = PDConstants.BLANK;
			
			String serviceProvider = SecurityUIHelper.getServiceProviderId();
			if (serviceProvider == null) {
				serviceProviderId = PDConstants.BLANK;
				serviceProviderName = PDConstants.BLANK;
			}
			
			juvLocUnitId = PDConstants.BLANK;
			eventTypeId = PDConstants.BLANK;
			
			nameSearchType = PDCalendarConstants.JPO_NAME_SEARCH ; 
						
			officerLastName = PDConstants.BLANK;
			officerFirstName = PDConstants.BLANK;
			officerMiddleName = PDConstants.BLANK;
			
			searchLastName = PDConstants.BLANK ;
			searchFirstName = PDConstants.BLANK ;
			searchMiddleName = PDConstants.BLANK ;

			eventStatusCd = PDConstants.BLANK;
			
			goneToSearchResultsPage = false ;
			doNameSearch = false ;
			showNameSection = false ;
			showDateSection = false ;
			showStatusSection = false ;
			showJPOSection = false ;
			showSPSection = false ;
			showJuvNumSection = false ;
			showJuvNameSection = false  ;
			showButtonSection = false ;
			showEventTypeSection = false ;
			showFutureSPSection = false ;
			showProgramSelectionSection = false ;
			showInstructorSelectionSection = false;
			showSPForInstructorSelectionSection = false;

			Date date = new Date();
			startDateStr = DateUtil.dateToString(date,DateUtil.DATE_FMT_1);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+1);

			date = calendar.getTime();			
			endDateStr = DateUtil.dateToString(date,DateUtil.DATE_FMT_1);
			
			selectedServices = null ;
		}

		/**
		 * @return Returns the endDateStr.
		 */
		public String getEndDateStr() {
			return endDateStr;
		}
		/**
		 * @param endDateStr The endDateStr to set.
		 */
		public void setEndDateStr(String endDateStr) {
			this.endDateStr = endDateStr;
		}
		/**
		 * @return Returns the startDateStr.
		 */
		public String getStartDateStr() {
			return startDateStr;
		}
		/**
		 * @param startDateStr The startDateStr to set.
		 */
		public void setStartDateStr(String startDateStr) {
			this.startDateStr = startDateStr;
		}
		/**
		 * @return Returns the caseLoadManager.
		 */
		public String getNameSearchType() {
			return nameSearchType;
		}
		/**
		 * @param caseLoadManager The caseLoadManager to set.
		 */
		public void setNameSearchType(String nameSearchType) {
			this.nameSearchType = nameSearchType;
		}
		/**
		 * @return Returns the juvenileFirstName.
		 */
		public String getJuvenileFirstName() {
			return juvenileFirstName;
		}
		/**
		 * @param juvenileFirstName The juvenileFirstName to set.
		 */
		public void setJuvenileFirstName(String juvenileFirstName) {
			this.juvenileFirstName = juvenileFirstName;
		}
		/**
		 * @return Returns the juvenileLastName.
		 */
		public String getJuvenileLastName() {
			return juvenileLastName;
		}
		/**
		 * @param juvenileLastName The juvenileLastName to set.
		 */
		public void setJuvenileLastName(String juvenileLastName) {
			this.juvenileLastName = juvenileLastName;
		}
		/**
		 * @return Returns the juvenileMiddleName.
		 */
		public String getJuvenileMiddleName() {
			return juvenileMiddleName;
		}
		/**
		 * @param juvenileMiddleName The juvenileMiddleName to set.
		 */
		public void setJuvenileMiddleName(String juvenileMiddleName) {
			this.juvenileMiddleName = juvenileMiddleName;
		}
		/**
		 * @return Returns the juvLocUnitId.
		 */
		public String getJuvLocUnitId() {
			return juvLocUnitId;
		}
		/**
		 * @param juvLocUnitId The juvLocUnitId to set.
		 */
		public void setJuvLocUnitId(String juvLocUnitId) {
			this.juvLocUnitId = juvLocUnitId;
		}
		/**
		 * @return Returns the officerFirstName.
		 */
		public String getOfficerFirstName() {
			return officerFirstName;
		}
		/**
		 * @param officerFirstName The officerFirstName to set.
		 */
		public void setOfficerFirstName(String officerFirstName) {
			this.officerFirstName = officerFirstName;
		}
		/**
		 * @return Returns the officerLastName.
		 */
		public String getOfficerLastName() {
			return officerLastName;
		}
		/**
		 * @param officerLastName The officerLastName to set.
		 */
		public void setOfficerLastName(String officerLastName) {
			this.officerLastName = officerLastName;
		}
		/**
		 * @return Returns the officerMiddleName.
		 */
		public String getOfficerMiddleName() {
			return officerMiddleName;
		}
		/**
		 * @param officerMiddleName The officerMiddleName to set.
		 */
		public void setOfficerMiddleName(String officerMiddleName) {
			this.officerMiddleName = officerMiddleName;
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
		 * @return Returns the juvUnitList.
		 */
		public List getJuvUnitList() {
			return juvUnitList;
		}
		/**
		 * @param juvUnitList The juvUnitList to set.
		 */
		public void setJuvUnitList(List juvUnitList) {
			this.juvUnitList = juvUnitList;
		}
		/**
		 * @return Returns the serviceTypeList.
		 */
		public List getServiceTypeList() {
			return serviceTypeList;
		}
		/**
		 * @param serviceTypeList The serviceTypeList to set.
		 */
		public void setServiceTypeList(List serviceTypeList) {
			this.serviceTypeList = serviceTypeList;
		}
		/**
		 * @return Returns the eventTypeId.
		 */
		public String getEventTypeId() {
			return eventTypeId;
		}
		/**
		 * @param eventTypeId The eventTypeId to set.
		 */
		public void setEventTypeId(String eventTypeId) {
			this.eventTypeId = eventTypeId;
		}
		/**
		 * @return Returns the eventStatusCd.
		 */
		public String getEventStatusCd() {
			return eventStatusCd;
		}
		/**
		 * @param eventStatusCd The eventStatusCd to set.
		 */
		public void setEventStatusCd(String eventStatusCd) {
			this.eventStatusCd = eventStatusCd;
		}
		/**
		 * @return Returns the serviceProviderList.
		 */
		public List getServiceProviderList() {
			return serviceProviderList;
		}
		/**
		 * @param serviceProviderList The serviceProviderList to set.
		 */
		public void setServiceProviderList(List serviceProviderList) {
			this.serviceProviderList = serviceProviderList;
		}
		/**
		 * @return Returns the serviceProviderId.
		 */
		public String getServiceProviderId() {
			return serviceProviderId;
		}
		/**
		 * @param serviceProviderId The serviceProviderId to set.
		 */
		public void setServiceProviderId(String serviceProviderId) {
			this.serviceProviderId = serviceProviderId;
		}
		/**
		 * @return Returns the searchType.
		 */
		public String getSearchType() {
			return searchType;
		}
		/**
		 * @param searchType The searchType to set.
		 */
		public void setSearchType(String searchType) {
			this.searchType = searchType;
		}
		/**
		 * @return Returns the jpoUserId.
		 */
		public String getJpoUserId() {
			return jpoUserId;
		}
		/**
		 * @param jpoUserId The jpoUserId to set.
		 */
		public void setJpoUserId(String jpoUserId) {
			this.jpoUserId = jpoUserId;
		}

		/**
		 * @return Returns the juvenileNum.
		 */
		public String getJuvenileNum() {
			return juvenileNum;
		}
		/**
		 * @param juvenileNum The juvenileNum to set.
		 */
		public void setJuvenileNum(String juvenileNum) {
			this.juvenileNum = juvenileNum;
		}
		
		

		/**
		 * @return Returns the serviceTypePermList.
		 */
		public List getServiceTypePermList() {
			return serviceTypePermList;
		}

		/**
		 * @param serviceTypePermList The serviceTypePermList to set.
		 */
		public void setServiceTypePermList(List serviceTypePermList) {
			this.serviceTypePermList = serviceTypePermList;
		}

		/**
		 * @return Returns the searchFirstName.
		 */
		public String getSearchFirstName() {
			return searchFirstName;
		}
		/**
		 * @param searchFirstName The searchFirstName to set.
		 */
		public void setSearchFirstName(String searchFirstName) {
			this.searchFirstName = searchFirstName;
		}
		/**
		 * @return Returns the searchLastName.
		 */
		public String getSearchLastName() {
			return searchLastName;
		}
		/**
		 * @param searchLastName The searchLastName to set.
		 */
		public void setSearchLastName(String searchLastName) {
			this.searchLastName = searchLastName;
		}
		/**
		 * @return Returns the searchMiddleName.
		 */
		public String getSearchMiddleName() {
			return searchMiddleName;
		}
		/**
		 * @param searchMiddleName The searchMiddleName to set.
		 */
		public void setSearchMiddleName(String searchMiddleName) {
			this.searchMiddleName = searchMiddleName;
		}
		/**
		 * @return Returns the showDateSection.
		 */
		public boolean isShowDateSection() {
			return showDateSection;
		}
		/**
		 * @param showDateSection The showDateSection to set.
		 */
		public void setShowDateSection(boolean showDateSection) {
			this.showDateSection = showDateSection;
		}
		/**
		 * @return Returns the showJPOSection.
		 */
		public boolean isShowJPOSection() {
			return showJPOSection;
		}
		/**
		 * @param showJPOSection The showJPOSection to set.
		 */
		public void setShowJPOSection(boolean showJPOSection) {
			this.showJPOSection = showJPOSection;
		}
		/**
		 * @return Returns the showSPSection.
		 */
		public boolean isShowSPSection() {
			return showSPSection;
		}
		/**
		 * @param showSPSection The showSPSection to set.
		 */
		public void setShowSPSection(boolean showSPSection) {
			this.showSPSection = showSPSection;
		}
		/**
		 * @return Returns the showStatusSection.
		 */
		public boolean isShowStatusSection() {
			return showStatusSection;
		}
		/**
		 * @param showStatusSection The showStatusSection to set.
		 */
		public void setShowStatusSection(boolean showStatusSection) {
			this.showStatusSection = showStatusSection;
		}
		/**
		 * @return Returns the showNameSection.
		 */
		public boolean isShowNameSection() {
			return showNameSection;
		}
		/**
		 * @param showNameSection The showNameSection to set.
		 */
		public void setShowNameSection(boolean showNameSection) {
			this.showNameSection = showNameSection;
		}
		/**
		 * @return Returns the showButtonSection.
		 */
		public boolean isShowButtonSection() {
			return showButtonSection;
		}
		/**
		 * @param showButtonSection The showButtonSection to set.
		 */
		public void setShowButtonSection(boolean showButtonSection) {
			this.showButtonSection = showButtonSection;
		}
		/**
		 * @return Returns the showJuvNameSection.
		 */
		public boolean isShowJuvNameSection() {
			return showJuvNameSection;
		}
		/**
		 * @param showJuvNameSection The showJuvNameSection to set.
		 */
		public void setShowJuvNameSection(boolean showJuvNameSection) {
			this.showJuvNameSection = showJuvNameSection;
		}

		/**
		 * @return Returns the showJuvNumSection.
		 */
		public boolean isShowJuvNumSection() {
			return showJuvNumSection;
		}
		/**
		 * @param showJuvNumSection The showJuvNumSection to set.
		 */
		public void setShowJuvNumSection(boolean showJuvNumSection) {
			this.showJuvNumSection = showJuvNumSection;
		}

		/**
		 * @return Returns the showFutureSPSection.
		 */
		public boolean isShowFutureSPSection() {
			return showFutureSPSection;
		}
		/**
		 * @param showFutureSPSection The showFutureSPSection to set.
		 */
		public void setShowFutureSPSection(boolean showFutureSPSection) {
			this.showFutureSPSection = showFutureSPSection;
		}

		/**
		 * @return Returns the showEventTypeSection.
		 */
		public boolean isShowEventTypeSection() {
			return showEventTypeSection;
		}
		/**
		 * @param showEventTypeSection The showEventTypeSection to set.
		 */
		public void setShowEventTypeSection(boolean showEventTypeSection) {
			this.showEventTypeSection = showEventTypeSection;
		}
		
		/**
		 * @return Returns the doNameSearch.
		 */
		public boolean isDoNameSearch() {
			return doNameSearch;
		}
		/**
		 * @param doNameSearch The doNameSearch to set.
		 */
		public void setDoNameSearch(boolean doNameSearch) {
			this.doNameSearch = doNameSearch;
		}

		/**
		 * @return Returns the juvenileFullname.
		 */
		public String getJuvenileFullname() {
			return juvenileFullname;
		}
		/**
		 * @param juvenileFullname The juvenileFullname to set.
		 */
		public void setJuvenileFullname(String juvenileFullname) {
			this.juvenileFullname = juvenileFullname;
		}
		/**
		 * @return Returns the officerFullname.
		 */
		public String getOfficerFullname() {
			return officerFullname;
		}
		/**
		 * @param officerFullname The officerFullname to set.
		 */
		public void setOfficerFullname(String officerFullname) {
			this.officerFullname = officerFullname;
		}

		public boolean isHaveSearchResults() {
			return haveSearchResults;
		}

		public void setHaveSearchResults(boolean haveSearchResults) {
			this.haveSearchResults = haveSearchResults;
		}

		public boolean isGoneToSearchResultsPage( )
		{
			return goneToSearchResultsPage ;
		}

		public void setGoneToSearchResultsPage( boolean goneToSearchResultsPage )
		{
			this.goneToSearchResultsPage = goneToSearchResultsPage ;
		}

		public boolean isShowProgramSelectionSection( )
		{
			return showProgramSelectionSection ;
		}

		public void setShowProgramSelectionSection( boolean showProgramSelectionSection )
		{
			this.showProgramSelectionSection = showProgramSelectionSection ;
		}

		public List getPreScheduledServices( )
		{
			return preScheduledServices ;
		}

		public void setPreScheduledServices( List preScheduledServices )
		{
			this.preScheduledServices = preScheduledServices ;
		}

		public List getEventList( )
		{
			return eventList ;
		}

		public void setEventList( List eventList )
		{
			this.eventList = eventList ;
		}

		public String [ ] getSelectedServices( )
		{
			return selectedServices ;
		}

		public void setSelectedServices( String [ ] selectedServices )
		{
			this.selectedServices = selectedServices ;
		}

		public boolean isShowInstructorSelectionSection() {
			return showInstructorSelectionSection;
		}

		public void setShowInstructorSelectionSection(
				boolean showInstructorSelectionSection) {
			this.showInstructorSelectionSection = showInstructorSelectionSection;
		}

		public List getInstructorList() {
			return instructorList;
		}

		public void setInstructorList(List instructorList) {
			this.instructorList = instructorList;
		}

		public String getInstructorName() {
			return instructorName;
		}

		public void setInstructorName(String instructorName) {
			this.instructorName = instructorName;
		}

		public boolean isShowSPForInstructorSelectionSection() {
			return showSPForInstructorSelectionSection;
		}

		public void setShowSPForInstructorSelectionSection(
				boolean showSPForInstructorSelectionSection) {
			this.showSPForInstructorSelectionSection = showSPForInstructorSelectionSection;
		}
	}// 	end public static class SearchEvent
	
	// CalendarEventListForm continues ...

	/*
	 */
	public void clear()
	{
		this.juvenileNum = null;
		this.endDate = null;
		this.eventDate = null;
		this.eventSessionLength = null;
		this.eventStatus = null;
		this.eventType = null;
		this.serviceLocationId = null;
		this.serviceLocationName = null;
		//this.serviceProviderName = null;
		this.startDate = null;
		this.attendanceStatus = null;
		this.attendanceStatusCd = null;
		this.progressNotes = null;
		this.summaryNotes = null ;
		this.listCount = 0 ;
		
		if( this.nameSearchResults != null )
		{
			this.nameSearchResults.clear() ;
		}
	}	

	/*
	 */
	public void clearCancelEvents()
	{
		this.selectedCancelEventsList = new ArrayList();
		this.selectedCancelEvents = null;
	}

	/**
	 * @return
	 */
	public Collection getDayEvents()
	{
		return dayEvents;
	}

	/**
	 * @return
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * @return
	 */
	public Date getEventDate()
	{
		return eventDate;
	}

	/**
	 * @return
	 */
	public String getEventSessionLength()
	{
		return eventSessionLength;
	}

	/**
	 * @return
	 */
	public String getEventStatus()
	{
		return eventStatus;
	}

	/**
	 * @return
	 */
	public String getEventType()
	{
		return eventType;
	}

	/**
	 * @return
	 */
	public String getServiceLocationId()
	{
		return serviceLocationId;
	}

	/**
	 * @return
	 */
	public String getServiceLocationName()
	{
		return serviceLocationName;
	}

	/**
	 * @return
	 */
	public String getServiceProviderName()
	{
		return serviceProviderName;
	}

	/**
	 * @return
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * @param collection
	 */
	public void setDayEvents(Collection collection)
	{
		dayEvents = collection;
	}

	/**
	 * @param date
	 */
	public void setEndDate(Date date)
	{
		endDate = date;
	}

	/**
	 * @param date
	 */
	public void setEventDate(Date date)
	{
		eventDate = date;
	}
	
	public String getCalendarEventId( )
	{
		return calendarEventId ;
	}

	/**
	 * @param serviceProviderId
	 *          The serviceProviderId to set.
	 */
	public void setCalendarEventId( String calendarEventId )
	{
		this.calendarEventId = calendarEventId ;
	}

	/**
	 * @param string
	 */
	public void setEventSessionLength(String string)
	{
		eventSessionLength = string;
		eventSessionLengthDesc = PDConstants.BLANK ;
		eventSessionLengthDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SESSION_LENGTH_INTERVAL,eventSessionLength);
	}

	/**
	 * @param string
	 */
	public void setEventStatus(String string)
	{
		eventStatus = string;
	}

	/**
	 * @param string
	 */
	public void setEventType(String string)
	{
		eventType = string;
	}

	/**
	 * @param string
	 */
	public void setServiceLocationId(String string)
	{
		serviceLocationId = string;
	}

	/**
	 * @param string
	 */
	public void setServiceLocationName(String string)
	{
		serviceLocationName = string;
	}

	/**
	 * @param string
	 */
	public void setServiceProviderName(String string)
	{
		serviceProviderName = string;
	}

	/**
	 * @param date
	 */
	public void setStartDate(Date date)
	{
		startDate = date;
	}
	
	public void setCalendardate(String date)
	{
		eventDate = new Date(Long.parseLong(date));	
	}
	
	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	
	/**
	 * @return the guardianInHouse
	 */
	public String getGuardianInHouse() {
		return guardianInHouse;
	}

	/**
	 * @param guardianInHouse the guardianInHouse to set
	 */
	public void setGuardianInHouse(String guardianInHouse) {
		this.guardianInHouse = guardianInHouse;
	}

	/**
	 * @return Returns the officerId.
	 */
	public String getOfficerId() {
		return officerId;
	}
	/**
	 * @param officerId The officerId to set.
	 */
	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}
	/**
	 * @return Returns the calendarType.
	 */
	public String getCalendarType() {
		return calendarType;
	}
	/**
	 * @param calendarType The calendarType to set.
	 */
	public void setCalendarType(String calendarType) {
		this.calendarType = calendarType;
	}
	/**
	 * @return Returns the currentEvent.
	 */
	public CalendarServiceEventResponseEvent getCurrentEvent() {
		return currentEvent;
	}
	/**
	 * @param currentEvent The currentEvent to set.
	 */
	public void setCurrentEvent(CalendarServiceEventResponseEvent currentEvent) {
		this.currentEvent = currentEvent;
	}
	/**
	 * @return Returns the currentInterview.
	 */
	public JuvenileInterview getCurrentInterview() {
		return currentInterview;
	}
	/**
	 * @param currentInterview The currentInterview to set.
	 */
	public void setCurrentInterview(JuvenileInterview currentInterview) {
		this.currentInterview = currentInterview;
	}
	/**
	 * @return Returns the recordsInventoryList.
	 */
	public Collection getRecordsInventoryList() {
		return recordsInventoryList;
	}
	/**
	 * @param recordsInventoryList The recordsInventoryList to set.
	 */
	public void setRecordsInventoryList(Collection recordsInventoryList) {
		this.recordsInventoryList = recordsInventoryList;
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
	 * @return Returns the attendanceStatus.
	 */
	public String getAttendanceStatus() {
		return attendanceStatus;
	}
	/**
	 * @param attendanceStatus The attendanceStatus to set.
	 */
	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	/**
	 * @return Returns the attendanceStatusCd.
	 */
	public String getAttendanceStatusCd() {
		return attendanceStatusCd;
	}
	/**
	 * @param attendanceStatusCd The attendanceStatusCd to set.
	 */
	public void setAttendanceStatusCd(String attendanceStatusCd) 
	{
		this.attendanceStatusCd = attendanceStatusCd;		
		if( getAttendanceStatusList() != null  &&  getAttendanceStatusList().size() > 0 )
		{
			attendanceStatus = CodeHelper.getCodeDescriptionByCode(getAttendanceStatusList(),attendanceStatusCd);					
		}
	}
	
	public Boolean getIsAttendedExcusedAbsent() {
		return isAttendedExcusedAbsent;
	}

	public void setIsAttendedExcusedAbsent(String attendanceStatusCd) {	    
	    this.isAttendedExcusedAbsent = false;
	    
	    if(attendanceStatusCd != null && !attendanceStatusCd.isEmpty())
	    {
		if(attendanceStatusCd.equalsIgnoreCase("AT") || 
			attendanceStatusCd.equalsIgnoreCase("EX") || 
			attendanceStatusCd.equalsIgnoreCase("AB")){
		    this.isAttendedExcusedAbsent = true;
		}
	    }		
	    
	}

	/**
	 * @return Returns the attendanceStatusList.
	 */
	public Collection getAttendanceStatusList() {
		return attendanceStatusList;
	}
	/**
	 * @param attendanceStatusList The attendanceStatusList to set.
	 */
	public void setAttendanceStatusList(Collection attendanceStatusList) {
		this.attendanceStatusList = attendanceStatusList;
	}
	/**
	 * @return Returns the progressNotes.
	 */
	public String getProgressNotes() {
		return progressNotes;
	}
	/**
	 * @param progressNotes The progressNotes to set.
	 */
	public void setProgressNotes(String progressNotes) {
		this.progressNotes = progressNotes;
	}
	/**
	 * @return Returns the currentJuvenileId.
	 */
	public String getCurrentJuvenileId() {
		return currentJuvenileId;
	}
	/**
	 * @param currentJuvenileId The currentJuvenileId to set.
	 */
	public void setCurrentJuvenileId(String currentJuvenileId) {
		this.currentJuvenileId = currentJuvenileId;
	}
	/**
	 * @return Returns the searchEvent.
	 */
	public SearchEvent getSearchEvent() {
		return searchEvent;
	}
	/**
	 * @param searchEvent The searchEvent to set.
	 */
	public void setSearchEvent(SearchEvent aSearchEvent) {
		searchEvent = aSearchEvent;
	}
	/**
	 * @return Returns the docketEvents.
	 */
	public List getDocketEvents() {
		return docketEvents;
	}
	/**
	 * @param docketEvents The docketEvents to set.
	 */
	public void setDocketEvents(List docketEvents) {
		this.docketEvents = docketEvents;
	}

	/**
	 * @return Returns the currentDocketEvent.
	 */
	public DocketEventResponseEvent getCurrentDocketEvent() {
		return currentDocketEvent;
	}
	/**
	 * @param currentDocketEvent The currentDocketEvent to set.
	 */
	public void setCurrentDocketEvent(DocketEventResponseEvent currentDocketEvent) {
		this.currentDocketEvent = currentDocketEvent;
	}
	/**
	 * @return Returns the programReferralList.
	 */
	public List getProgramReferralList() {
		return programReferralList;
	}
	/**
	 * @param programReferralList The programReferralList to set.
	 */
	public void setProgramReferralList(List programReferralList) {
		this.programReferralList = programReferralList;
	}
	/**
	 * @return Returns the programReferral.
	 */
	public UIProgramReferralBean getProgramReferral() {
		return programReferral;
	}
	/**
	 * @param programReferral The programReferral to set.
	 */
	public void setProgramReferral(UIProgramReferralBean programReferral) {
		this.programReferral = programReferral;
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
	 * @return Returns the eventSessionLengthDesc.
	 */
	public String getEventSessionLengthDesc() {
		return eventSessionLengthDesc;
	}
	/**
	 * @param eventSessionLengthDesc The eventSessionLengthDesc to set.
	 */
	public void setEventSessionLengthDesc(String eventSessionLengthDesc) {
		this.eventSessionLengthDesc = eventSessionLengthDesc;
	}
	/**
	 * @return Returns the individualDayEvents.
	 */
	public Collection getIndividualDayEvents() {
		return individualDayEvents;
	}
	/**
	 * @param individualDayEvents The individualDayEvents to set.
	 */
	public void setIndividualDayEvents(Collection individualDayEvents) {
		this.individualDayEvents = individualDayEvents;
	}
	

	public String[] getSelectedCancelEvents() {
		return selectedCancelEvents;
	}

	public void setSelectedCancelEvents(String[] selectedCancelEvents) {
		this.selectedCancelEvents = selectedCancelEvents;
	}

	public Collection getSelectedCancelEventsList() {
		return selectedCancelEventsList;
	}

	public void setSelectedCancelEventsList(Collection selectedCancelEventsList) {
		this.selectedCancelEventsList = selectedCancelEventsList;
	}

	/**
	 * @return Returns the nameSearchResults.
	 */
	public Collection getNameSearchResults() {
		return nameSearchResults;
	}
	/**
	 * @param nameSearchResults The nameSearchResults to set.
	 */
	public void setNameSearchResults(Collection nameSearchResults) {
		this.nameSearchResults = nameSearchResults;
	}

    /**
     * @return
     */
    public boolean isCaseloadManagerSearch()
    {
        return PDCalendarConstants.CLM_NAME_SEARCH.equals(searchEvent.getNameSearchType());
    }
	/**
	 * @return Returns the caseStatus.
	 */
	public String getCaseStatus() {
		return caseStatus;
	}
	/**
	 * @param caseStatus The caseStatus to set.
	 */
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	/**
	 * @return Returns the listCount.
	 */
	public int getListCount() {
		return listCount;
	}
	/**
	 * @param listCount The listCount to set.
	 */
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	/**
	 * @return Returns the officerOtherID.
	 */
	public String getOfficerOtherID() {
		return officerOtherID;
	}
	/**
	 * @param officerOtherID The officerOtherID to set.
	 */
	public void setOfficerOtherID(String officerOtherID) {
		this.officerOtherID = officerOtherID;
	}

	/*
	 * @return
	 */
	public Collection getCancelledEventsList() {
		return cancelledEventsList;
	}

	/*
	 * @param cancelledEventsList
	 */
	public void setCancelledEventsList(Collection cancelledEventsList) {
		this.cancelledEventsList = cancelledEventsList;
	}

	/*
	 * @return
	 */
	public String getSummaryNotes( )
	{
		return summaryNotes ;
	}

	/*
	 * @param summaryNotes
	 */
	public void setSummaryNotes( String summaryNotes )
	{
		this.summaryNotes = summaryNotes ;
	}

	/*
	 * @return
	 */
	public ServiceProviderForm getServiceProvider( )
	{
		return serviceProvider ;
	}

	/*
	 * @param serviceProvider
	 */
	public void setServiceProvider( ServiceProviderForm serviceProvider )
	{
		this.serviceProvider = serviceProvider ;
	}
	
	/**
	 * @return Returns the serviceProviderId.
	 */
	public String getServiceProviderId( )
	{
		return serviceProviderId ;
	}

	/**
	 * @param serviceProviderId
	 *          The serviceProviderId to set.
	 */
	public void setServiceProviderId( String serviceProviderId )
	{
		this.serviceProviderId = serviceProviderId ;
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
		//this.selectedAttendeeNames = selectedAttendeeNames;
		HashMap attendees = new HashMap();
		
		this.selectedAttendeeNames = selectedAttendeeNames;
		for( int i = 0; i < selectedAttendeeNames.length; i++ )
		{	
			for( Iterator iter = contactNames.iterator(); iter.hasNext(); /* empty */)
			{
				AttendeeNameResponseEvent attendee = (AttendeeNameResponseEvent)iter.next();
				String fullName = attendee.getFormattedName() ;
				if( selectedAttendeeNames[i].equalsIgnoreCase(fullName) && 
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

	public String getSecondaryAction() {
		return secondaryAction;
	}

	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}
	
	public String getSelectedAttendeeNamesAsString() {
		return selectedAttendeeNamesAsString;
	}

	public void setSelectedAttendeeNamesAsString(
			String selectedAttendeeNamesAsString) {
		this.selectedAttendeeNamesAsString = selectedAttendeeNamesAsString;
	}

	public String getExistingProgressNotes() {
		return existingProgressNotes;
	}

	public void setExistingProgressNotes(String existingProgressNotes) {
		this.existingProgressNotes = existingProgressNotes;
	}

	/**
	 * @return the tentativeRefPrgmRef
	 */
	public boolean isTentativeRefPrgmRef() {
		return tentativeRefPrgmRef;
	}

	/**
	 * @param tentativeRefPrgmRef the tentativeRefPrgmRef to set
	 */
	public void setTentativeRefPrgmRef(boolean tentativeRefPrgmRef) {
		this.tentativeRefPrgmRef = tentativeRefPrgmRef;
	}
	
}
