/*
 * Created on Sep 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import messaging.address.reply.AddressResponseEvent;
import messaging.administerlocation.GetJuvenileLocationEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.DocketEventResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.LocationControllerServiceNames;
import naming.UIConstants;
import ui.common.Address;
import ui.common.UIUtil;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class CalendarSchedulePrintBean {

	private String juvenileName;
	private String todaysDate;
	private String todaysTime;
	private List calendarEvents; // CalendarServiceEventResponseEvents
	private List docketEvents;  // DocketEventResponseEvent
	private boolean hasEvents=false;
	private boolean serviceProvider=false;
	private String serviceProviderName="";
	private List allEvents;
	private String eventsDate;
	
	public void addEvent(CalendarServiceEventResponseEvent aCalServRespEvt){
		if(calendarEvents==null && aCalServRespEvt!=null){
			calendarEvents=new ArrayList();
		}
		calendarEvents.add(convert(aCalServRespEvt));
	}
	
	public CalendarPrintObj convert(CalendarServiceEventResponseEvent aCalServRespEvt){
		CalendarPrintObj myPrintObj=new CalendarPrintObj();
		myPrintObj.setEventType(aCalServRespEvt.getEventType());
		myPrintObj.setStartDateTime(aCalServRespEvt.getStartDatetime());
		myPrintObj.setEndDateTime(aCalServRespEvt.getEndDatetime());
		myPrintObj.setCalEventDate(aCalServRespEvt.getEventDate() == null?"":DateUtil.dateToString(aCalServRespEvt.getEventDate(), "MM/dd/yyyy"));
		myPrintObj.setStartTime(aCalServRespEvt.getStartDatetime() == null?"":DateUtil.dateToString(aCalServRespEvt.getEventDate(), "hh:mm a"));
		myPrintObj.setEndTime(aCalServRespEvt.getEndDatetime() == null?"":DateUtil.dateToString(aCalServRespEvt.getEndDatetime(), "hh:mm a"));
		myPrintObj.setServiceProviderName(aCalServRespEvt.getServiceProviderName());
		myPrintObj.setServiceProviderService(aCalServRespEvt.getServiceName());
		String eventComments = this.removeBrackets(aCalServRespEvt.getEventComments());
		if(eventComments!=null)
			myPrintObj.setEventComments(eventComments.trim());
		String typeCode=aCalServRespEvt.getEventTypeCode();
		if(typeCode!=null){
			if(typeCode.equals(UIConstants.HOME_VISIT) || 
				typeCode.equals(UIConstants.HOME_DIAGNOSTIC_VISIT) || 
				typeCode.equals(UIConstants.FACE_TO_FACE_CURFEW_CHECK) || 
				typeCode.equals(UIConstants.PHONE_CURFEW_CHECK) || 
				typeCode.equals(UIConstants.CURFEW_CHECK)){
				myPrintObj.setLocationName(aCalServRespEvt.getFamilyLocation());
			}
			else if(typeCode.equals(UIConstants.PLACEMENT_VISIT) ||  
				typeCode.equals(UIConstants.JOB_VISIT)){
				myPrintObj.setLocationName(aCalServRespEvt.getLocation());
			}
			else if(typeCode.equals(UIConstants.SCHOOL_VISIT)){
				myPrintObj.setLocationName(aCalServRespEvt.getSchoolName());
			}
			else {
				myPrintObj.setLocationName(aCalServRespEvt.getServiceLocationName());
				String locId=aCalServRespEvt.getLocationId();
				if(locId!=null && !locId.equals("")){
					GetJuvenileLocationEvent gle = (GetJuvenileLocationEvent)EventFactory.getInstance(LocationControllerServiceNames.GETJUVENILELOCATION);
					gle.setLocationId(locId);
					CompositeResponse response = MessageUtil.postRequest(gle);
					LocationResponseEvent lre = (LocationResponseEvent) MessageUtil.filterComposite(response, LocationResponseEvent.class);
					if(lre!=null){
						AddressResponseEvent addressResponseEvent = lre.getLocationAddress();	
						if(addressResponseEvent!=null){
							Address myAddress=UIUtil.convertAddressResponseEvent(addressResponseEvent);
							myPrintObj.setLocationAddress(myAddress);
						}
					}
				}
			}
			
		}
		return myPrintObj;
	}
	
	public void addEvent(DocketEventResponseEvent aDocketServRespEvt){
		if(docketEvents==null && aDocketServRespEvt!=null){
			docketEvents=new ArrayList();
		}
		docketEvents.add(convert(aDocketServRespEvt));
	}
	
	public CalendarPrintObj convert(DocketEventResponseEvent aDocketServRespEvt){
		CalendarPrintObj myPrintObj=new CalendarPrintObj();
		
		myPrintObj.setCourtDate(aDocketServRespEvt.getEventDate() == null?"":DateUtil.dateToString(aDocketServRespEvt.getEventDate(), "MM/dd/yyyy"));
		myPrintObj.setStartDateTime(aDocketServRespEvt.getEventDate());
		String courtTime=aDocketServRespEvt.getCourtTime();
		if(courtTime!=null && !courtTime.equals("") && courtTime.length()>=4){
			try{
				String hours=courtTime.substring(0,2);
				String minutes=courtTime.substring(2,4);
				String AM_PM=" AM";
				int intHours=Integer.parseInt(hours);
				if(intHours>11) {   // hours should be 0 - 23
					AM_PM=" PM";
					if(intHours>12)
						intHours=intHours-12;
				} 
				String finalTime= String.valueOf(intHours) + ":" + minutes + AM_PM;
				myPrintObj.setCourtTime(finalTime);
			}
			catch(Exception e){
				myPrintObj.setCourtTime(courtTime);
			}
		}else{
			myPrintObj.setCourtTime(aDocketServRespEvt.getStartDatetime() == null?"":DateUtil.dateToString(aDocketServRespEvt.getEventDate(), "hh:mm a"));
		}
		myPrintObj.setDocketType(aDocketServRespEvt.getDocketType() == null?"":aDocketServRespEvt.getDocketType().toUpperCase());
		myPrintObj.setPetitionNum(aDocketServRespEvt.getPetitionNumber());
		myPrintObj.setCourtNum(aDocketServRespEvt.getCourt());
		myPrintObj.setPetitionAllegation(aDocketServRespEvt.getAllegationDesc());
		myPrintObj.setHearingType(aDocketServRespEvt.getHearingTypeDesc());
		myPrintObj.setLocationName(aDocketServRespEvt.getLocation());
		return myPrintObj;
	}
	
	/**
	 * @return Returns the calendarEvents.
	 */
	public List getCalendarEvents() {
		return calendarEvents;
	}
	/**
	 * @param calendarEvents The calendarEvents to set.
	 */
	public void setCalendarEvents(List calendarEvents) {
		this.calendarEvents = calendarEvents;
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
	 * @return Returns the juvenileName.
	 */
	public String getJuvenileName() {
		return juvenileName;
	}
	/**
	 * @param juvenileName The juvenileName to set.
	 */
	public void setJuvenileName(String juvenileName) {
		this.juvenileName = juvenileName;
	}
	/**
	 * @return Returns the todaysDate.
	 */
	public String getTodaysDate() {
		return todaysDate;
	}
	/**
	 * @param todaysDate The todaysDate to set.
	 */
	public void setTodaysDate(String todaysDate) {
		this.todaysDate = todaysDate;
	}
	/**
	 * @return Returns the todaysTime.
	 */
	public String getTodaysTime() {
		return todaysTime;
	}
	/**
	 * @param todaysTime The todaysTime to set.
	 */
	public void setTodaysTime(String todaysTime) {
		this.todaysTime = todaysTime;
	}
	/**
	 * @return Returns the hasEvents.
	 */
	public boolean isHasEvents() {
		if( (getDocketEvents()!=null && getDocketEvents().size()>0) || (getCalendarEvents()!=null && getCalendarEvents().size()>0)){
			hasEvents=true;
		}
		else hasEvents=false;
		return hasEvents;
	}
	/**
	 * @param hasEvents The hasEvents to set.
	 */
	public void setHasEvents(boolean hasEvents) {
		this.hasEvents = hasEvents;
	}
	
	   /**
     * Removes the [] section at the end of the comments
     * @param comments The string in which the bracketed section needs to be removed
     * @return The string with the bracketed section removed.
     */
    private String removeBrackets(String comments) {
    	int index = 0;
        if (comments != null && !comments.equals("")){
        	index = comments.indexOf("[");
        	comments = comments.substring(0, index);
        }
        return comments;
    }

	public static class CalendarPrintObj{
		private Date startDateTime;
		private Date endDateTime;
		private String calEventDate;
		private String startTime;
		private String endTime;
		private String serviceProviderName;
		private String serviceProviderService;
		private String eventType;
		private String locationName;
		private Address locationAddress;
		private String eventComments;
		
		private String docketType;
		private String petitionAllegation;
		private String petitionNum;
		private String courtNum;
		private String hearingType;
		private String courtTime;
		private String courtDate;
		
//		public String getCourtDateTime(){
//			return (UIUtil.getStringFromDate(courtDate, UIConstants.DATE_FMT_1) + " " + UIUtil.nullToBlank(courtTime));
//		}
		
		/**
		 * @return Returns the courtNum.
		 */
		public String getCourtNum() {
			return UIUtil.nullToBlank(courtNum);
		}
		/**
		 * @param courtNum The courtNum to set.
		 */
		public void setCourtNum(String courtNum) {
			this.courtNum = courtNum;
		}
		/**
		 * @return Returns the docketType.
		 */
		public String getDocketType() {
			return UIUtil.nullToBlank(docketType);
		}
		/**
		 * @param docketType The docketType to set.
		 */
		public void setDocketType(String docketType) {
			this.docketType = docketType;
		}
		/**
		 * @return Returns the eventType.
		 */
		public String getEventComments() {
			return UIUtil.nullToBlank(eventComments);
		}
		/**
		 * @param eventComments The eventComments to set.
		 */
		public void setEventComments(String eventComments) {
			this.eventComments = eventComments;
		}
		
		
		public String getStartDateTimeAsStr(){
			if(startDateTime==null){
				return "";
			}
			else{
				return UIUtil.getStringFromDate(startDateTime, UIConstants.DATETIME_FMT_1AMPM);
			}
		}
		
		
		public String getEndDateTimeAsStr(){
			if(endDateTime==null){
				return "";
			}
			else{
				return UIUtil.getStringFromDate(endDateTime, UIConstants.DATETIME_FMT_1AMPM);
			}
		}
		/**
		 * @return Returns the endDateTime.
		 */
		public Date getEndDateTime() {
			return endDateTime;
		}
		/**
		 * @param endDateTime The endDateTime to set.
		 */
		public void setEndDateTime(Date endDateTime) {
			this.endDateTime = endDateTime;
		}
				
		/**
		 * @return Returns the eventType.
		 */
		public String getEventType() {
			return UIUtil.nullToBlank(eventType);
		}
		/**
		 * @param eventType The eventType to set.
		 */
		public void setEventType(String eventType) {
			this.eventType = eventType;
		}
		/**
		 * @return Returns the hearingType.
		 */
		public String getHearingType() {
			return UIUtil.nullToBlank(hearingType);
		}
		/**
		 * @param hearingType The hearingType to set.
		 */
		public void setHearingType(String hearingType) {
			this.hearingType = hearingType;
		}
		
		public String getLocationAddress1(){
			if(locationAddress==null){
				return "";
			}
			else{
				return locationAddress.getFormattedAddress();
			}
		}
		/**
		 * @return Returns the locationAddress.
		 */
		public Address getLocationAddress() {
			return locationAddress;
		}
		/**
		 * @param locationAddress The locationAddress to set.
		 */
		public void setLocationAddress(Address locationAddress) {
			this.locationAddress = locationAddress;
		}
		/**
		 * @return Returns the locationName.
		 */
		public String getLocationName() {
			return UIUtil.nullToBlank(locationName);
		}
		/**
		 * @param locationName The locationName to set.
		 */
		public void setLocationName(String locationName) {
			this.locationName = locationName;
		}
		/**
		 * @return Returns the petitionNum.
		 */
		public String getPetitionNum() {
			return UIUtil.nullToBlank(petitionNum);
		}
		/**
		 * @param petitionNum The petitionNum to set.
		 */
		public void setPetitionNum(String petitionNum) {
			this.petitionNum = petitionNum;
		}
		/**
		 * @return Returns the petitionType.
		 */
		public String getPetitionAllegation() {
			return UIUtil.nullToBlank(petitionAllegation);
		}
		/**
		 * @param petitionType The petitionType to set.
		 */
		public void setPetitionAllegation(String petitionType) {
			this.petitionAllegation = petitionType;
		}
		/**
		 * @return Returns the serviceProviderName.
		 */
		public String getServiceProviderName() {
			return UIUtil.nullToBlank(serviceProviderName);
		}
		/**
		 * @param serviceProviderName The serviceProviderName to set.
		 */
		public void setServiceProviderName(String serviceProviderName) {
			this.serviceProviderName = serviceProviderName;
		}
		/**
		 * @return Returns the serviceProviderService.
		 */
		public String getServiceProviderService() {
			return UIUtil.nullToBlank(serviceProviderService);
		}
		/**
		 * @param serviceProviderService The serviceProviderService to set.
		 */
		public void setServiceProviderService(String serviceProviderService) {
			this.serviceProviderService = serviceProviderService;
		}
		/**
		 * @return Returns the startDateTime.
		 */
		public Date getStartDateTime() {
			return startDateTime;
		}
		/**
		 * @param startDateTime The startDateTime to set.
		 */
		public void setStartDateTime(Date startDateTime) {
			this.startDateTime = startDateTime;
		}
		/**
		 * @return Returns the courtTime.
		 */
		public String getCourtTime() {
			return courtTime;
		}
		/**
		 * @param courtTime The courtTime to set.
		 */
		public void setCourtTime(String courtTime) {
			this.courtTime = courtTime;
		}
		/**
		 * @return Returns the courtDate.
		 */
		public String getCourtDate() {
			return courtDate;
		}
		/**
		 * @param courtDate The courtDate to set.
		 */
		public void setCourtDate(String courtDate) {
			this.courtDate = courtDate;
		}
		
		public String getServiceProNameNService(){
			if(serviceProviderService!=null && !serviceProviderService.equals(""))
				return (serviceProviderName + " / " + serviceProviderService);
			else{
				return (serviceProviderName);
			}
		}

		public String getCalEventDate() {
			return calEventDate;
		}

		public void setCalEventDate(String calEventDate) {
			this.calEventDate = calEventDate;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}
	}
	/**
	 * @return Returns the serviceProvider.
	 */
	public boolean isServiceProvider() {
		return serviceProvider;
	}
	/**
	 * @param serviceProvider The serviceProvider to set.
	 */
	public void setServiceProvider(boolean serviceProvider) {
		this.serviceProvider = serviceProvider;
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

	public List getAllEvents() {
		return allEvents;
	}

	public void setAllEvents(List allEvents) {
		this.allEvents = allEvents;
	}

	public String getEventsDate() {
		return eventsDate;
	}

	public void setEventsDate(String eventsDate) {
		this.eventsDate = eventsDate;
	}
}
