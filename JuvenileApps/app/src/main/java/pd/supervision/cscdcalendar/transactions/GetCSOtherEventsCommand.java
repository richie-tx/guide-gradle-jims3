// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\transactions\\GetCSOtherEventsCommand.java

package pd.supervision.cscdcalendar.transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import naming.PDCodeTableConstants;

import pd.supervision.cscdcalendar.CSEventBuilder;
import pd.supervision.cscdcalendar.CSEventHelper;
import pd.supervision.cscdcalendar.CSEvent;

import messaging.contact.party.reply.PartyResponseEvent;
import messaging.cscdcalendar.CSEventTypeAttribute;
import messaging.cscdcalendar.CSPositionAttribute;
import messaging.cscdcalendar.CSSuperviseeAttribute;
import messaging.cscdcalendar.GetCSEventRetrieverEvent;
import messaging.cscdcalendar.GetCSOtherEventsEvent;
import messaging.cscdcalendar.reply.CSEventTypeResponseEvent;
import messaging.cscdcalendar.reply.CSOtherResponseEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;

import messaging.calendar.ICalendarAttribute;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class GetCSOtherEventsCommand implements ICommand {

	/**
	 * @roseuid 479A0EB603C5
	 */
	public GetCSOtherEventsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4798EE8A0231
	 */
	public void execute(IEvent event) {		
		GetCSOtherEventsEvent cee = (GetCSOtherEventsEvent) event;
		if(CSEventHelper.isEventDateError(cee.getEventDate())) {
			return;		
		}
		if(CSEventHelper.isEventContextError(cee.getCurrentContext())) {
			return;		
		}
		GetCSEventRetrieverEvent retrieverEvent = new GetCSEventRetrieverEvent();
    	
    	// Calendar Start and End Date
		retrieverEvent.setStartDatetime(cee.getEventDate());
		retrieverEvent.setEndDatetime(cee.getEventDate());    	
		
		// Calendar Attributes
    	List eventTypes = CSEventHelper.getInstance().getCSEventTypes(PDCodeTableConstants.CS_OTHER_EVENT_CATEGORY, false);
	   	Iterator evtTypeiter = eventTypes.iterator();
		ICalendarAttribute[] as = new ICalendarAttribute[eventTypes.size()+1];		
		retrieverEvent.setCalendarAttributes(as);
		int attrCounter = 0;
		while(evtTypeiter.hasNext()) {
			CSEventTypeResponseEvent resp = (CSEventTypeResponseEvent) evtTypeiter.next();
			CSEventTypeAttribute eventTypeAttr = new CSEventTypeAttribute();
			eventTypeAttr.setEventTypeId(resp.getEventType());
			as[attrCounter++] = eventTypeAttr;
		}
		
		if(cee.getCurrentContext().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION)) {
			if(CSEventHelper.isPositionError(cee.getPositionId())) {
				return;		
			}
			CSPositionAttribute positionAttr = new CSPositionAttribute();
			positionAttr.setPositionId(new Integer(cee.getPositionId()));
			as[attrCounter] = positionAttr;
		} else {
			if(CSEventHelper.isSuperviseeError(cee.getSuperviseeId())) {
				return;		
			}
			CSSuperviseeAttribute superviseeAttr = new CSSuperviseeAttribute();
			superviseeAttr.setPartyId(cee.getSuperviseeId());
			as[attrCounter] = superviseeAttr;
		}
		
		List superviseeIds = new ArrayList();
		List postionIds = new ArrayList();
		Map supervisees = new HashMap();
		Map positions = new HashMap();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
    	
		IHome home = new Home();
        Iterator i = home.findAll(retrieverEvent, CSEvent.class);    	
    	while (i.hasNext()) {
    		CSEvent e = (CSEvent) i.next();
    		String superviseeId = e.getPartyId();
    		String positionId = e.getPositionId();    		
    		CSOtherResponseEvent resp = CSEventBuilder.buildOtherEvent(e, false);
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
			dispatch.postEvent(resp);
    	}

	}

	/**
	 * @param event
	 * @roseuid 4798EE8A0240
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4798EE8A0242
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4798EE8A025F
	 */
	public void update(Object anObject) {

	}

	/**
	 * @param updateObject
	 * @roseuid 479A0EB603E4
	 */
	/*
	 * public void update(Object updateObject) {
	 *  }
	 */
}
