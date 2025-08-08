/*
 * Created on Feb 12, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.cscdcalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.cscdcalendar.GetFutureCalendarEvent;
import messaging.cscdcalendar.reply.CSEventTypeResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.CasenoteConstants;
import naming.CloseCaseConstants;
import naming.PDCodeTableConstants;

import org.apache.commons.lang.StringUtils;

import pd.codetable.supervision.CSEventType;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.contact.party.Party;
import pd.security.PDSecurityHelper;
import pd.supervision.administercasenotes.Casenote;
import pd.supervision.supervisionorder.SupervisionPeriod;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;


/**
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public final class CSEventHelper {
	
	private static CSEventHelper instance;
	private List cseventTypes = null;
	private Map cseventTypeMap = null;
	private Map cseventTypeToCategoryMap = null;
	
	private CSEventHelper() {
		super();		
		IHome home = new Home();
		Iterator cseventTypesIter = home.findAll(CSEventType.class);
		cseventTypes = new ArrayList();
		cseventTypeMap = new HashMap();
		cseventTypeToCategoryMap = new HashMap();
		if(cseventTypesIter!=null) {
			while (cseventTypesIter.hasNext()) {
				CSEventType eveType = (CSEventType)cseventTypesIter.next();	
				cseventTypes.add(eveType);
				cseventTypeMap.put(eveType.getCode(), eveType);
				StringBuffer categoryMap = new StringBuffer();
				categoryMap.append(eveType.getCode());
				categoryMap.append(",");
				categoryMap.append(eveType.getCategoryId());			
				cseventTypeToCategoryMap.put(categoryMap.toString(), eveType);
			}
		}
	}
	
	/**
     * @return CSEventHelper
     */
    public static CSEventHelper getInstance() {
        if (instance == null) {
            instance = new CSEventHelper();
        }
        return instance;
    }	
	
	public List getCSEventTypes(String categoryCode, boolean ignoreCategory) {
		List eventTypes = new ArrayList();			     
        for (int i=0; i<cseventTypes.size(); i++ ) {
        	CSEventType eveType = (CSEventType)cseventTypes.get(i);	
        	if(ignoreCategory) {       				
        		eventTypes.add(buildResponseType(eveType));
        	} else {
        		if(eveType.getCategoryId().equals(categoryCode)) {
        				eventTypes.add(buildResponseType(eveType));
        		}
        	}       			      		  	
        }
  
	    return eventTypes;
	}
	
	public List getCSEventTypes(String context) {
		List eventTypes = new ArrayList();	       
		 for (int i=0; i<cseventTypes.size(); i++ ) {
        	CSEventType eveType = (CSEventType)cseventTypes.get(i);       			
       		if ((eveType.getAccessContext().equals(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION_AND_SUPERVISEE)) ||
       					eveType.getAccessContext().equals(context)){
       			eventTypes.add(buildResponseType(eveType));
       		}   	
        }     
	    return eventTypes;
	}	
	
	public List getCSEventTypes(String context, String categoryCode) {
		List eventTypes = new ArrayList();        
		 for (int i=0; i<cseventTypes.size(); i++ ) {
        	CSEventType eveType = (CSEventType)cseventTypes.get(i);       			
       		if (((eveType.getAccessContext().equals(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION_AND_SUPERVISEE)) ||
       				(eveType.getAccessContext().equals(context))) && (eveType.getCategoryId().equals(categoryCode))){       				
       			eventTypes.add(buildResponseType(eveType));
       		}   	
        }
	    return eventTypes;
	}
	
	public CSEventTypeResponseEvent getCSEventType(String eventType) {
		CSEventType eveType = (CSEventType)cseventTypeMap.get(eventType);
		return buildResponseType(eveType); 
	}
	
	public CSEventTypeResponseEvent getCSEventType(String eventType, String category) {
		StringBuffer typeCategory = new StringBuffer();
		typeCategory.append(eventType);
		typeCategory.append(",");
		typeCategory.append(category);
		CSEventType eveType = (CSEventType)cseventTypeToCategoryMap.get(typeCategory.toString());
		return buildResponseType(eveType); 
	}
	
	public static CSEventTypeResponseEvent buildResponseType(CSEventType eveType) {
		CSEventTypeResponseEvent resp = null;
		if(eveType!=null) {
			resp = new CSEventTypeResponseEvent();
			resp.setEventType(eveType.getCode());
			resp.setDescription(eveType.getDescription());
			resp.setEventTypeId(eveType.getOID());
			resp.setAccessContext(eveType.getAccessContext());
			resp.setDisplayContext(eveType.getDisplayContext());
			resp.setCategoryCode(eveType.getCategoryId());
			return resp;
		}
		
		return resp;
	}
	
	/**
	 * Formats a date to the respective DB SQL date format.  In the
	 * case of JIMS2, DB2.
	 * @param date
	 * @return formatted Date
	 */
	public static String formatDateTimeForSql(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
		return formatter.format(date);
	}
	
	public static String formatDateForSql(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}
	
	public static String formatDateIntoTimeInAMPM(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
		return formatter.format(date);
	}
	
	public static Date formatStringDateToDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
		try {
			return formatter.parse(date);
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		return null;
	}
	
	public static String dateToString(Date date) {
		SimpleDateFormat dfmt = new SimpleDateFormat("MM/dd/yyyy");
		String dateStr = "";
		try {
			if (date != null) {
				dateStr = dfmt.format(date);
			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			throw ex;
		}

		return dateStr;
	}
	
	public static Date getDateForCSEvent(Date eventDate, String time) {
		String eventDateStr = formatDateForSql(eventDate);
		StringBuffer dateTimeStr = new StringBuffer();
		dateTimeStr.append(eventDateStr);
		dateTimeStr.append(" ");
		dateTimeStr.append(time);
		Date dateTime = formatStringDateToDate(dateTimeStr.toString());
		return dateTime;
	}
	
	public static String getCSEventDefaultTimezone() {
		return "AMERICA/CHICAGO";
	}
	
	public static void postCSEventError(String errorTxt) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		ErrorResponseEvent errRespEvt=new ErrorResponseEvent();
		errRespEvt.setMessage(errorTxt);				
		dispatch.postEvent(errRespEvt);
	}
	
	public static boolean isEventDateError(Date eventDate) {
		boolean isErrorReported = false;
		if(eventDate==null) {
			isErrorReported = true;
			postCSEventError("Please specify Event Date.");
		}
		return isErrorReported;
	}
	
	public static boolean isTimeError(String time, String timeMsg) {
		boolean isErrorReported = false;
		String timeHere = null;
		if(time==null || time.trim().equals(""))
			timeHere = null;
		if(timeHere == null) {
			return false;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
		try {
			formatter.parse(timeHere);			
		} catch (ParseException e) {
			isErrorReported = true;
			StringBuffer eventError = new StringBuffer();
			eventError.append("One of the ");
			eventError.append(timeMsg);
			eventError.append(" is not in valid format.");
			postCSEventError(eventError.toString());
		}
		return isErrorReported;
	}
	
	public static boolean isStatusError(String status) {
		boolean isErrorReported = false;
		if(status==null) {
			isErrorReported = true;
			postCSEventError("Please specify a valid Status.");
			return isErrorReported;
		} 
		if(status!=null) {
			if(status.equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_STATUS_OPEN) ||
					status.equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE)) {			
			} else {
				isErrorReported = true;
				postCSEventError("Please specify a valid Status.");
			}
		}
		return isErrorReported;
	}
	
	public static boolean isStatusNotOpenError(String status) {
		boolean isErrorReported = false;
		if(status==null) {
			isErrorReported = true;
			postCSEventError("Please specify a valid Status.");
			return isErrorReported;
		} 
		if(status!=null) {
			if(!status.equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_STATUS_OPEN)) {
				isErrorReported = true;
				postCSEventError("The Event Status is not Open.");
			}
		}
		return isErrorReported;
	}
	
	public static boolean isStatusNotClosedError(String status) {
		boolean isErrorReported = false;
		if(status==null) {
			isErrorReported = true;
			postCSEventError("Please specify a valid Status.");
			return isErrorReported;
		} 
		if(status!=null) {
			if(!status.equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE)) {			
				isErrorReported = true;
				postCSEventError("The Event Status is not Closed.");
			}
		}
		return isErrorReported;
	}
	
	public static boolean isEventTypeError(String eventType, String category) {
		boolean isErrorReported = false;
		try {
			CSEventTypeResponseEvent eveType = CSEventHelper.getInstance().getCSEventType(eventType,
					category);
			if(eveType==null) {
				isErrorReported = true;
				postCSEventError("Invalid Event Type");
			}
		} catch (Exception e) {
			isErrorReported = true;
			postCSEventError("Invalid Event Type");
		}
		return isErrorReported;
	}
	
	public static boolean isEventContextObjectsError(String eventType, String context, String positionId, String superviseeId) {
		boolean isErrorReported = false;
		if(context==null) {
			isErrorReported = true;
			postCSEventError("Please specify the Event context.");
			return isErrorReported;
		}
		if(context!=null) {
			if(context.equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE) || 
					context.equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION)) {
			} else {
				isErrorReported = true;
				postCSEventError("Please specify a valid Event context.");
				return isErrorReported;
			}
		}
		boolean checkPosition = false;
		boolean checkSupervisee = false;
		if(context.equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE)) {
			checkPosition = true;
			checkSupervisee = true;			
		} else if(context.equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION)) {
			CSEventTypeResponseEvent eveType = CSEventHelper.getInstance().getCSEventType(eventType);
			if(eveType.getDisplayContext().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION)) {
				checkPosition = true;			
			} else if(eveType.getDisplayContext().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION_AND_SUPERVISEE)) {
				checkPosition = true;
				checkSupervisee = true;	
			} else if(eveType.getDisplayContext().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE)) {
				checkSupervisee = true;	
			}
		}
		if(checkPosition) {
			CSCDStaffPosition position = CSCDStaffPosition.find(positionId);
			if(position==null) {
				isErrorReported = true;
				postCSEventError("The Position Id mentioned is invalid.");
			}
		}
		if(checkSupervisee) {
			Party supervisee = Party.find(superviseeId);
			if(supervisee==null) {
				isErrorReported = true;
				postCSEventError("The Supervisee Id mentioned is invalid.");
			}
		}
		return isErrorReported;
	}
	
	public static boolean isEventContextError(String context) {
		boolean isErrorReported = false;
		if(context==null) {
			isErrorReported = true;
			postCSEventError("Please specify the Event context.");
			return isErrorReported;
		}
		if(context!=null) {
			if(context.equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE) || 
					context.equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION)) {
			} else {
				isErrorReported = true;
				postCSEventError("Please specify a valid Event context.");
				return isErrorReported;
			}
		}		
		return isErrorReported;
	}
	
	public static boolean isEventIdError(String eventId) {
		boolean isErrorReported = false;		
		if(eventId==null || eventId.length()==0) {
			isErrorReported = true;
			CSEventHelper.postCSEventError("CSEvent Id provided is null or not valid.");
			return isErrorReported;
		}
		try {
			new Integer(eventId);
		} catch (NumberFormatException e) {
			isErrorReported = true;
			postCSEventError("CSEvent Id provided is null or not valid.");
		}
		return isErrorReported;
	}
	
	public static boolean isPositionError(String positionId) {
		boolean isErrorReported = false;		
		if(positionId==null || positionId.length()==0) {
			isErrorReported = true;
			CSEventHelper.postCSEventError("Position Id provided is null or not valid.");
			return isErrorReported;
		}
		try {
			new Integer(positionId);
		} catch (NumberFormatException e) {
			isErrorReported = true;
			postCSEventError("Position Id provided is null or not valid.");
		}
		return isErrorReported;
	}
	
	public static boolean isResultPositionError(String resultPositionId) {
		boolean isErrorReported = false;		
		if(resultPositionId==null || resultPositionId.length()==0) {
			isErrorReported = true;
			CSEventHelper.postCSEventError("Result Position Id provided is null or not valid.");
			return isErrorReported;
		}
		try {
			new Integer(resultPositionId);
		} catch (NumberFormatException e) {
			isErrorReported = true;
			postCSEventError("Result Position Id provided is null or not valid.");
		}
		return isErrorReported;
	}
	
	public static boolean isSuperviseeError(String superviseeId) {
		boolean isErrorReported = false;		
		if(superviseeId==null || superviseeId.trim().length()==0) {
			isErrorReported = true;
			CSEventHelper.postCSEventError("Supervisee Id provided is null or not valid.");
		}
		return isErrorReported;
	}
	
	/**
     * Creates a casenote stating results are entered for supervisee event.
     * @param defendantId
     * @param notes
     */
    public static void createCasenote(String defendantId, String notes, Collection subjects, Date casenoteDate , String eventId) {
    	
    	String agencyId = PDSecurityHelper.getUserAgencyId();
    	SupervisionPeriod supPer = SupervisionPeriod.findActiveSupervisionPeriod(defendantId, agencyId);
        
        if (supPer != null){
            UpdateCasenoteEvent updateEvent = new UpdateCasenoteEvent();
            updateEvent.setAgencyId(PDCodeTableConstants.CSCD_AGENCY);
            updateEvent.setCasenoteTypeId(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
            updateEvent.setCasenoteStatusId(CasenoteConstants.STATUS_DRAFT);
            updateEvent.setSaveAsDraft(true);
            updateEvent.setContactMethodId(PDCodeTableConstants.CASENOTE_CONTACT_METHOD_NONE);
            if (notes.startsWith("OFFICE VISIT") || notes.startsWith("GROUP OFFICE VISIT") ) {
            	int attIndex = notes.indexOf("Outcome: ATTENDED");  //outcome must be Attended
            	int narIndex = notes.indexOf("Narrative:");         //make sure constant value NOT in narrative  
            	if (attIndex > 0 && attIndex < narIndex){
            		updateEvent.setContactMethodId(PDCodeTableConstants.CASENOTE_CONTACT_METHOD_INPERSON_OV);
            	}
            }
            updateEvent.setContextType(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
            if (casenoteDate != null){
            	updateEvent.setEntryDate(casenoteDate);
            } else {
            	updateEvent.setEntryDate(new Date());
            }
            updateEvent.setHowGeneratedId(PDCodeTableConstants.CASENOTE_SYSTEM_GENERATED_ID);
            updateEvent.setNotes(notes);            
            updateEvent.setSubjects(subjects);
            updateEvent.setSupervisionPeriodId(supPer.getOID());
            updateEvent.setSuperviseeId(defendantId);
            updateEvent.setCsEventId( eventId );
            Casenote casenote = new Casenote();
            casenote.updateCasenote(updateEvent);
        }
    }
    
    /**
     * 
     * @param csEvent
     * @param isOVOutcome
     */
    public static String getNotes(CSEvent csEvent, boolean isOVOutcome) {
    	
    	String startTime = null;
		String endTime = null;
		if(csEvent.getStartTime()!=null) {
			startTime = CSEventHelper.formatDateIntoTimeInAMPM(csEvent.getStartTime());
		} 
		if(csEvent.getEndTime()!=null) {
			endTime = CSEventHelper.formatDateIntoTimeInAMPM(csEvent.getEndTime());
		} 
		
		String timeNote = getTimeNote(startTime, endTime);		
		String outcome = "";
		SupervisionCode outcomeObj = null;
		if(isOVOutcome) {
			outcomeObj = PDSupervisionCodeHelper.getSupervisionCodeByValue(
				PDCodeTableConstants.CSCD_AGENCY,
				PDCodeTableConstants.OV_OUTCOME, csEvent.getOutCome());
		} else {
			outcomeObj = PDSupervisionCodeHelper.getSupervisionCodeByValue(
					PDCodeTableConstants.CSCD_AGENCY,
					PDCodeTableConstants.FV_OUTCOME, csEvent.getOutCome());
		}
		if(outcomeObj!=null) {
			outcome = outcomeObj.getDescription();
		}	
		String narrative = "";
		if(csEvent.getNarrative()!=null) {
			narrative = csEvent.getNarrative();
		}

		StringBuffer caseNote = new StringBuffer();
		
		if(StringUtils.isNotEmpty(CSEventHelper.getInstance().getCSEventType(csEvent.getEventTypeId()).getDescription())){
			caseNote.append(CSEventHelper.getInstance().getCSEventType(csEvent.getEventTypeId()).getDescription());
			caseNote.append(" result for ");
		}
		if(StringUtils.isNotEmpty(dateToString(csEvent.getEventDate()))){
			caseNote.append(dateToString(csEvent.getEventDate()));
			caseNote.append(", ");
			caseNote.append(timeNote);
		}
		if(StringUtils.isNotEmpty(csEvent.getEventName())){
			caseNote.append("Event Name: ");
			caseNote.append(csEvent.getEventName());
			caseNote.append(", <br/>");
		}
		if(StringUtils.isNotEmpty(outcome)){
			caseNote.append("Outcome: ");
			caseNote.append(outcome);
			caseNote.append(", <br/>");
		}
		if(StringUtils.isNotEmpty(narrative)){
			caseNote.append("Narrative: ");
			caseNote.append(narrative);
		}
		String notes = caseNote.toString();

		return notes;
    }

    /**
     * 
     * @param csEvent
     * @param resCSEvent
     * @param isOVOutcome
     */
    public static String getRescheduleNotes(CSEvent csEvent, CSEvent resCSEvent, boolean isOVOutcome) {
    	
    	String startTime = null;
		String endTime = null;
		if(csEvent.getStartTime()!=null) {
			startTime = CSEventHelper.formatDateIntoTimeInAMPM(csEvent.getStartTime());
		} 
		if(csEvent.getEndTime()!=null) {
			endTime = CSEventHelper.formatDateIntoTimeInAMPM(csEvent.getEndTime());
		} 
		
    	String csTimeNote = getTimeNote(startTime, endTime);
    	
    	if(resCSEvent.getStartTime()!=null) {
			startTime = CSEventHelper.formatDateIntoTimeInAMPM(resCSEvent.getStartTime());
		} 
		if(resCSEvent.getEndTime()!=null) {
			endTime = CSEventHelper.formatDateIntoTimeInAMPM(resCSEvent.getEndTime());
		} 
		
    	String resTimeNote = getTimeNote(startTime, endTime);
    	
    	String outcome = "";
		SupervisionCode outcomeObj = null;
		if(isOVOutcome) {
			outcomeObj = PDSupervisionCodeHelper.getSupervisionCodeByValue(
				PDCodeTableConstants.CSCD_AGENCY,
				PDCodeTableConstants.OV_OUTCOME, resCSEvent.getOutCome());
		} else {
			outcomeObj = PDSupervisionCodeHelper.getSupervisionCodeByValue(
					PDCodeTableConstants.CSCD_AGENCY,
					PDCodeTableConstants.FV_OUTCOME, resCSEvent.getOutCome());
		}
		if(outcomeObj!=null) {
			outcome = outcomeObj.getDescription();
		}

		StringBuffer caseNote = new StringBuffer();
		
		if(StringUtils.isNotEmpty(CSEventHelper.getInstance().getCSEventType(resCSEvent.getEventTypeId()).getDescription())){
			caseNote.append(CSEventHelper.getInstance().getCSEventType(resCSEvent.getEventTypeId()).getDescription());
		}
		if(StringUtils.isNotEmpty(outcome) && outcome.equalsIgnoreCase("RESCHEDULED")) {
			caseNote.append(" ");
			caseNote.append(outcome);
			caseNote.append(" from ");
		}
		if(StringUtils.isNotEmpty(dateToString(resCSEvent.getEventDate()))){
			caseNote.append(dateToString(resCSEvent.getEventDate()));
			caseNote.append(", ");
			caseNote.append(resTimeNote);
		}
		if(StringUtils.isNotEmpty(dateToString(csEvent.getEventDate()))){
			caseNote.append("to ");
			caseNote.append(dateToString(csEvent.getEventDate()));
			caseNote.append(", ");
			caseNote.append(csTimeNote);
		}
		if(StringUtils.isNotEmpty(resCSEvent.getEventName())){
			caseNote.append("<br/>Event Name: ");
			caseNote.append(resCSEvent.getEventName());
			caseNote.append(", <br/>");
		}
		if(StringUtils.isNotEmpty(outcome)){
			caseNote.append("Outcome: ");
			caseNote.append(outcome);
			caseNote.append(", <br/>");
		}
		if(StringUtils.isNotEmpty(resCSEvent.getRescheduleReason())){
			caseNote.append("Reschedule Reason: ");
			caseNote.append(resCSEvent.getRescheduleReason());
		}
		String notes = caseNote.toString();
		 	
    	return notes;
    }
    
    /**
     * 
     * @param defendantId
     * @param terminationDate
     */
	public static void deleteFutureCalendarEvents(String defendantId, Date terminationDate)
	{
		GetFutureCalendarEvent futureEvent = new GetFutureCalendarEvent();
		futureEvent.setPartyId(defendantId);
		futureEvent.setEventDate(terminationDate);
		
		Iterator iterator = CSEvent.findAll(futureEvent);
		if(iterator != null)
		{
			while(iterator.hasNext())
			{
				CSEvent csEvent = (CSEvent) iterator.next();
				String csEventTypeId = csEvent.getEventTypeId();
				
				if(csEventTypeId.equalsIgnoreCase(CloseCaseConstants.CS_CALENDAR_FIELD_VISIT_EVENTTYPE))
				{
					Iterator it = FieldVisitEvent.findAll("csEventId", csEvent.getOID());
					while(it != null && it.hasNext())
					{
						FieldVisitEvent fieldVisit = (FieldVisitEvent)it.next();
						fieldVisit.delete();
					}
				}
				csEvent.delete();
			}
		}
	}//end of deleteFutureCalendarEvents()

	public static String getTimeNote(String startTime, String endTime) {
		String timeNote = null;
		StringBuffer timeNoteBuffer = new StringBuffer();
		if(startTime!=null) timeNote = startTime;
		if(timeNote == null && endTime==null)  { 
			timeNote = "";
		} else if(timeNote == null && endTime!=null)  {
			timeNote = endTime;
		} else if(timeNote != null && endTime==null) {
			timeNoteBuffer.append(timeNote);
			timeNoteBuffer.append(", ");
			timeNote = timeNoteBuffer.toString();
		} else if(timeNote != null && endTime!=null) {
			timeNoteBuffer.append(timeNote);
			timeNoteBuffer.append(" to ");
			timeNoteBuffer.append(endTime);
			timeNoteBuffer.append(", ");
			timeNote =  timeNoteBuffer.toString();
		}
		return timeNote;
	}
	    
}


