/*
 * Created on Mar 25, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.cscdcalendar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.cscdcalendar.GetCSFVEventsEvent;
import messaging.cscdcalendar.GetCSFVItineraryDetailsEvent;
import messaging.cscdcalendar.reply.CSFVItineraryResponseEvent;
import messaging.cscdcalendar.reply.CSFieldVisitResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSEventControllerServiceNames;
import naming.PDCodeTableConstants;
import ui.common.ComplexCodeTableHelper;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class cscdCalendarUIUtil {
	public static final String[] FV_OUTCOME_USER_SELECTABLE = {"CO","IC"};
	public static final String[] OV_OUTCOME_USER_SELECTABLE = {"AT","AB","EX"};
	
	
	public static List getFilteredFVOutcomeList(String agencyId) {
		List ovOutcomeList = getFVOutcomeList(agencyId);
		List tobeRemoved = new ArrayList();
		
		List selectableList = Arrays.asList(FV_OUTCOME_USER_SELECTABLE);
		
		for(Iterator iter = ovOutcomeList.iterator();iter.hasNext();) {
			CodeResponseEvent code = (CodeResponseEvent)iter.next();
			
			if(!selectableList.contains(code.getSupervisionCode())) {
				tobeRemoved.add(code);
			}
		}
		ovOutcomeList.removeAll(tobeRemoved);
		
		return ovOutcomeList;
	}
	
	private static List getFVOutcomeList(String agencyId) {
		return ComplexCodeTableHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.FV_OUTCOME);
	}
	
	public static List getFilteredOVOutcomeList(String agencyId) {
		List ovOutcomeList = getOVOutcomeList(agencyId);
		List tobeRemoved = new ArrayList();
		
		List selectableList = Arrays.asList(OV_OUTCOME_USER_SELECTABLE);
		
		for(Iterator iter = ovOutcomeList.iterator();iter.hasNext();) {
			CodeResponseEvent code = (CodeResponseEvent)iter.next();
			
			if(!selectableList.contains(code.getSupervisionCode())) {
				tobeRemoved.add(code);
			}
		}
		ovOutcomeList.removeAll(tobeRemoved);
		
		return ovOutcomeList;
	}
	
	public static List getOVOutcomeList(String agencyId) {
		return ComplexCodeTableHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.OV_OUTCOME);
	}
	
	public static CSFVItineraryResponseEvent getItinerary(String positionId, Date eventDate) {
		GetCSFVItineraryDetailsEvent getCSFVItinerary = 
			(GetCSFVItineraryDetailsEvent)EventFactory.getInstance(
					CSEventControllerServiceNames.GETCSFVITINERARYDETAILS);
   		getCSFVItinerary.setItineraryDate(eventDate);
   		getCSFVItinerary.setPositionId(positionId);
		
   		CompositeResponse response = MessageUtil.postRequest(getCSFVItinerary);
   		CSFVItineraryResponseEvent itineraryRE = 
   			(CSFVItineraryResponseEvent)MessageUtil.filterComposite(
   					response, CSFVItineraryResponseEvent.class);
   		return itineraryRE;
	}
	
	public static List getEventsForItinerary(String itineraryId, String positionId, Date eventDate) {
		GetCSFVEventsEvent getCSFVEvents = 
				(GetCSFVEventsEvent)EventFactory.getInstance(
						CSEventControllerServiceNames.GETCSFVEVENTS);
   		getCSFVEvents.setFvIteneraryId(itineraryId);
   		
   		//At this point, we want to search for all the events attached to 
   		//the itineraryId, therefore belonging to this specific officer
   		getCSFVEvents.setCurrentContext(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION);
   		getCSFVEvents.setPositionId(positionId);
		getCSFVEvents.setEventDate(eventDate);
	   		
   		CompositeResponse response = MessageUtil.postRequest(getCSFVEvents);
   		List events = (List)
   			MessageUtil.compositeToCollection(
   					response, CSFieldVisitResponseEvent.class);
   		
   		return events;
	}
	
}
