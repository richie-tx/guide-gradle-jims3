// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\transactions\\GetCSOfficeVisitsCommand.java

package pd.supervision.cscdcalendar.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.lang.StringUtils;

import naming.PDCodeTableConstants;
import pd.supervision.administercaseload.SuperviseeName;
import pd.supervision.cscdcalendar.CSEvent;
import pd.supervision.cscdcalendar.CSEventBuilder;
import pd.supervision.cscdcalendar.CSEventHelper;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderHelper;
import messaging.contact.party.reply.PartyResponseEvent;
import messaging.cscdcalendar.CSEventTypeAttribute;
import messaging.cscdcalendar.CSPositionAttribute;
import messaging.cscdcalendar.CSSuperviseeAttribute;
import messaging.cscdcalendar.GetCSEventRetrieverEvent;
import messaging.cscdcalendar.GetCSOfficeVisitsEvent;
import messaging.cscdcalendar.reply.CSEventTypeResponseEvent;
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

public class GetCSOfficeVisitsCommand implements ICommand {

	/**
	 * @roseuid 479A0EB601F0
	 */
	public GetCSOfficeVisitsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4798EEA402CB
	 */
	public void execute(IEvent event) {
		GetCSOfficeVisitsEvent cov = (GetCSOfficeVisitsEvent) event;
		if(CSEventHelper.isEventDateError(cov.getEventDate())) {
			return;		
		}
		if(CSEventHelper.isEventContextError(cov.getCurrentContext())) {
			return;		
		}
		GetCSEventRetrieverEvent retrieverEvent = new GetCSEventRetrieverEvent();
    	
    	// Calendar Start and End Date
		retrieverEvent.setStartDatetime(cov.getEventDate());
		retrieverEvent.setEndDatetime(cov.getEventDate());    	
		
		// Calendar Attributes
    	List eventTypes = CSEventHelper.getInstance().getCSEventTypes(PDCodeTableConstants.CS_OFFICE_VISIT_CATEGORY, false);
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
		
		if(cov.getCurrentContext().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION)) {
			if(CSEventHelper.isPositionError(cov.getPositionId())) {
				return;		
			}
			CSPositionAttribute positionAttr = new CSPositionAttribute();
			positionAttr.setPositionId(new Integer(cov.getPositionId()));
			as[attrCounter] = positionAttr;
		} else {
			if(CSEventHelper.isSuperviseeError(cov.getSuperviseeId())) {
				return;		
			}
			CSSuperviseeAttribute superviseeAttr = new CSSuperviseeAttribute();
			superviseeAttr.setPartyId(cov.getSuperviseeId());
			as[attrCounter] = superviseeAttr;
		}
		
		IHome home = new Home();
        Iterator csEventsIte = home.findAll(retrieverEvent, CSEvent.class);
        if(cov.getCurrentContext().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION)) {
        	postForPosition(csEventsIte);
		} else {
			postForSupervisee(csEventsIte);
		}
	}
	
	private void postForSupervisee(Iterator csEventsIte) {
		List superviseeIds = new ArrayList();
		List postionIds = new ArrayList();
		Map supervisees = new HashMap();
		Map positions = new HashMap();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		 	
    	while (csEventsIte.hasNext()) {
    		CSEvent e = (CSEvent) csEventsIte.next();
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
    			CSCDSupervisionStaffResponseEvent  positionResp = CSEventBuilder.getOfficerResponse(e);
    			positions.put(positionId, positionResp);
    			postionIds.add(positionId);
    			resp.setPositionEvent(positionResp);
    		}
			dispatch.postEvent(resp);
    	}
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
    			List orderList = SupervisionOrderHelper.getActiveSupervisionOrders(superviseeId, "CSC");

    			String printedName = "";
    			PartyResponseEvent partyResp = new PartyResponseEvent();
				partyResp.setOID(superviseeId);
				partyResp.setPartyId(superviseeId);
    			for (int i = 0; i < orderList.size(); i++) {
    				
	    			SupervisionOrder order = null;
    				order = (SupervisionOrder) orderList.get(i);
					if(StringUtils.isNotEmpty(order.getPrintedName())){
						// get defendant's name from supervision order printed name
		    			printedName = order.getPrintedName().trim();
		    			int firstSpace = printedName.indexOf(" ");
		    			int secondSpace = printedName.indexOf(" ", firstSpace + 1);
		    			String firstName = printedName.substring(0, firstSpace);
		    			String middleName = "";
		    			String lastName = "";
		    			if ( secondSpace == -1 ){		                    
		                    lastName = printedName.substring(firstSpace +1);
		    			}else{
		                    middleName = printedName.substring(firstSpace, secondSpace);
		                    lastName = printedName.substring(secondSpace + 1);
		    			}

						partyResp.setFirstName(firstName);
						partyResp.setMiddleName(middleName);
						partyResp.setLastName(lastName);
							
		    			supervisees.put(superviseeId, partyResp);
		    	    	superviseeIds.add(superviseeId);
		    	    	resp.setPartyEvent(partyResp);
		    	    	break;
					}
				 }
    			if(!StringUtils.isNotEmpty(printedName)){
					// get defendant's name from M204
                    SuperviseeName sname = SuperviseeName.findByDefendantId(superviseeId);
                    if (sname != null){
                    	partyResp.setFirstName(sname.getFirstName());
                    	partyResp.setMiddleName(sname.getMiddleName());
                    	partyResp.setLastName(sname.getLastName());
                    	
                    	supervisees.put(superviseeId, partyResp);
		    	    	superviseeIds.add(superviseeId);
		    	    	resp.setPartyEvent(partyResp);
                    }
				}
    			printedName = null;
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
    		groupEvent.setAtleaseOneEventOpen(false);
    		groupEvent.setAtleaseOneEventClosed(false);
    		groupEvent.setOfficeVisits(new ArrayList());
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
	 * @roseuid 4798EEA402CD
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4798EEA402CF
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4798EEA402DB
	 */
	public void update(Object anObject) {

	}

	/**
	 * @param updateObject
	 * @roseuid 479A0EB60210
	 */
	/*
	 * public void update(Object updateObject) {
	 *  }
	 */
}
