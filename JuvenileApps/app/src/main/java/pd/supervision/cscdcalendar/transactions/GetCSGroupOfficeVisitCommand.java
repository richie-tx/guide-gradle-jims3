// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\transactions\\GetCSGroupOfficeVisitCommand.java

package pd.supervision.cscdcalendar.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import naming.PDCodeTableConstants;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;

import pd.supervision.cscdcalendar.CSEvent;
import pd.supervision.cscdcalendar.CSEventBuilder;
import pd.supervision.cscdcalendar.CSEventHelper;
import messaging.contact.party.reply.PartyResponseEvent;
import messaging.cscdcalendar.CSEventNameAttribute;
import messaging.cscdcalendar.CSEventTypeAttribute;
import messaging.cscdcalendar.CSPositionAttribute;
import messaging.cscdcalendar.GetCSEventRetrieverEvent;
import messaging.cscdcalendar.GetCSGroupOfficeVisitEvent;
import messaging.cscdcalendar.reply.CSGroupOfficeVisitResponseEvent;
import messaging.cscdcalendar.reply.CSOfficeVisitResponseEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.calendar.ICalendarAttribute;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class GetCSGroupOfficeVisitCommand implements ICommand {

	/**
	 * @roseuid 479A0EB6006A
	 */
	public GetCSGroupOfficeVisitCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4798EEA500D9
	 */
	public void execute(IEvent event) {

		GetCSGroupOfficeVisitEvent cov = (GetCSGroupOfficeVisitEvent) event;
		if(CSEventHelper.isEventDateError(cov.getEventDate())) {
			return;		
		}
		if(cov.getEventName()==null || cov.getEventName().trim().length() == 0 ) {
			CSEventHelper.postCSEventError("Please specify Group Office visit name.");
			return;
		}
		if(CSEventHelper.isPositionError(cov.getPositionId())) {
			return;		
		}		
		GetCSEventRetrieverEvent retrieverEvent = new GetCSEventRetrieverEvent();
    	
    	// Calendar Start and End Date
		retrieverEvent.setStartDatetime(cov.getEventDate());
		retrieverEvent.setEndDatetime(cov.getEventDate());    	
		
		// Calendar Attributes    	
		ICalendarAttribute[] as = new ICalendarAttribute[3];		
		retrieverEvent.setCalendarAttributes(as);		
		
		CSEventTypeAttribute eventTypeAttr = new CSEventTypeAttribute();
		eventTypeAttr.setEventTypeId(PDCodeTableConstants.CS_GROUP_OFFICE_VISIT_TYPE);
		as[0] = eventTypeAttr;	
		
		CSPositionAttribute positionAttr = new CSPositionAttribute();
		positionAttr.setPositionId(new Integer(cov.getPositionId()));
		as[1] = positionAttr;
		
		CSEventNameAttribute nameAttr = new CSEventNameAttribute();
		nameAttr.setEventName(cov.getEventName());
		as[2] = nameAttr;
		
		
		IHome home = new Home();
        Iterator csEventsIte = home.findAll(retrieverEvent, CSEvent.class);       
        postForPosition(csEventsIte);        
	}
	
	private void postForPosition(Iterator csEventsIte) {
		List superviseeIds = new ArrayList();
		List postionIds = new ArrayList();
		Map supervisees = new HashMap();
		Map positions = new HashMap();
		MultiMap groupVisits = new MultiHashMap();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		 	
    	while (csEventsIte.hasNext()) {
    		CSEvent e = (CSEvent) csEventsIte.next();
    		String eventName = e.getEventName();
    		String superviseeId = e.getPartyId();
    		String positionId = e.getPositionId();    		
    		CSOfficeVisitResponseEvent resp = CSEventBuilder.buildOfficeVisit(e, false);
    		if(superviseeIds.contains(superviseeId)) {
    			resp.setPartyEvent((PartyResponseEvent)supervisees.get(superviseeId));
    		} else {
    			PartyResponseEvent partyResp = CSEventBuilder.getPartyResponse(e);
    			supervisees.put(superviseeId, partyResp);
    			superviseeIds.add(superviseeId);
    			resp.setPartyEvent(partyResp);
    		}
    		if(postionIds.contains(positionId)) {
    			resp.setPositionEvent((CSCDSupervisionStaffResponseEvent)positions.get(positionId));
    		} else {
    			CSCDSupervisionStaffResponseEvent positionResp = CSEventBuilder.getOfficerResponse(e);
    			positions.put(positionId, positionResp);
    			postionIds.add(positionId);
    			resp.setPositionEvent(positionResp);
    		}
    		if(e.getEventTypeId().equalsIgnoreCase(PDCodeTableConstants.CS_OFFICE_VISIT_TYPE)) {
    			dispatch.postEvent(resp);
    		} else {
    			groupVisits.put(eventName, resp);
    		}
    	}
    	Iterator groupKeys = groupVisits.keySet().iterator();
    	while(groupKeys.hasNext()) {
    		String key = (String) groupKeys.next();
    		Iterator groupOVs = ((Collection)groupVisits.get(key)).iterator();
    		CSGroupOfficeVisitResponseEvent groupEvent = new CSGroupOfficeVisitResponseEvent();
    		groupEvent.setOfficeVisits(new ArrayList());
    		groupEvent.setAtleaseOneEventOpen(false);
    		groupEvent.setAtleaseOneEventClosed(false);
    		boolean gotHeader = false;
    		while(groupOVs.hasNext()) {
    			CSOfficeVisitResponseEvent ov = (CSOfficeVisitResponseEvent) groupOVs.next();
    			if(!gotHeader) {
    				groupEvent.setEventName(ov.getEventName());
    				groupEvent.setEventDate(ov.getEventDate());
    				groupEvent.setStartTime(ov.getStartTime());
    				groupEvent.setEndTime(ov.getEndTime());
    				groupEvent.setEventType(ov.getEventType());
    				gotHeader = true;
    			}
    			if(ov.getStatus()!=null) { 
    				if(ov.getStatus().trim().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_STATUS_OPEN))
    					groupEvent.setAtleaseOneEventOpen(true);
    				if(ov.getStatus().trim().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE))
    					groupEvent.setAtleaseOneEventClosed(true);
    			}
    			groupEvent.getOfficeVisits().add(ov);
    		}
    		dispatch.postEvent(groupEvent);    		
    	}
	}

	/**
	 * @param event
	 * @roseuid 4798EEA500DB
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4798EEA500E8
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4798EEA500EA
	 */
	public void update(Object anObject) {

	}

	/**
	 * @param updateObject
	 * @roseuid 479A0EB60089
	 */
	/*
	 * public void update(Object updateObject) {
	 *  }
	 */
}
