/*
 * Created on Mar 11, 2008
 *
 */
package ui.taglib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import messaging.cscdcalendar.reply.MonthlyCSCalendarResponseEvent;
import ui.taglib.calendar.CalendarDayDisplayProperties;
import ui.taglib.calendar.ICalendarDayDisplay;

/**
 * @author awidjaja
 * This presentation is specific to CS calendars, since events for a specific day
 * has to be grouped into 3 categories: Field Visit, Office Visit and Other Event.
 * The original Presentation class groups all event into 1 list, and totals them up. 
 * 
 */
public class ConsolidatedCSEventDayPresentation implements ICalendarDayDisplay {

	public String getHTML(CalendarDayDisplayProperties props) 
    {
        StringBuffer b = new StringBuffer();
        Collection col = props.getEvents();
        List officeVisitList = new ArrayList();
        List otherEventList = new ArrayList();
        List fieldVisitList = new ArrayList();
        List eventList = new ArrayList();
        
        HashMap eventMap = new HashMap();
        
        if(col.size() > 0) {
	        for(Iterator eventsIter = col.iterator();eventsIter.hasNext();) {
	        	MonthlyCSCalendarResponseEvent event = 
	        		(MonthlyCSCalendarResponseEvent)eventsIter.next();
	        	
	        	String key = event.getCategoryCd();
	        	eventList = (List)eventMap.get(key);
	        	
	        	if( eventList == null ) {
	        		
	        		eventList = new ArrayList();
	        		eventMap.put(key, eventList);
	        	}
	        	eventList.add(event);
	        }
        }
        
        if (col != null && col.size() > 0) {
        	String href = props.getHref();
        	if (href == null) {
				href = "eventLink attribute was not defined in the calendar tag";
				System.err.println("[ConsolidatedEventDayPresentation] - eventLink attribute was not defined in the calendar tag");
				StringBuffer link = new StringBuffer(href);
				b.append("<table class=\"" + props.getCssStyle() +  "\">");
				
				
				officeVisitList = (List)eventMap.get("OV");
				otherEventList = (List)eventMap.get("OE");
				fieldVisitList = (List)eventMap.get("FV");
				
				if(officeVisitList != null && officeVisitList.size() > 0) {
					b.append("<tr><td><a href=\"" + link + "\" class=\"" + props.getHrefCssStyle() + "\">Office Visit (" + officeVisitList.size() + ")</a></td></tr>");
				}
				if(fieldVisitList != null && fieldVisitList.size() > 0) {
					b.append("<tr><td><a href=\"" + link + "\" class=\"" + props.getHrefCssStyle() + "\">Field Visit (" + fieldVisitList.size() + ")</a></td></tr>");
				}
				if(otherEventList != null && otherEventList.size() > 0) {
					b.append("<tr><td><a href=\"" + link + "\" class=\"" + props.getHrefCssStyle() + "\">Other Event (" + otherEventList.size() + ")</a></td></tr>");
				}
				
				b.append("</table>");				
        	} else {
				
				b.append("<table class=\"" + props.getCssStyle() +  "\">");
				
				
				officeVisitList = (List)eventMap.get("OV");
				otherEventList = (List)eventMap.get("OE");
				fieldVisitList = (List)eventMap.get("FV");
				
				StringBuffer link = new StringBuffer(href);
				if(href.indexOf("?") < 0)
					link.append("?");
				else
					link.append("&");
				link.append("sessionevents=" + props.getSessionAttributeName());
				link.append("&calendardate=" + props.getDayDate().getTime());
				
				if(officeVisitList != null && officeVisitList.size() > 0) {
					b.append("<tr><td><a href=\"" + link + "&eventCategory=OV" + "\" class=\"" + props.getHrefCssStyle() + "\">Office Visit (" + officeVisitList.size() + ")</a></td></tr>");
				}
				if(fieldVisitList != null && fieldVisitList.size() > 0) {
					b.append("<tr><td><a href=\"" + link + "&eventCategory=FV" + "\" class=\"" + props.getHrefCssStyle() + "\">Field Visit (" + fieldVisitList.size() + ")</a></td></tr>");
				}
				if(otherEventList != null && otherEventList.size() > 0) {
					b.append("<tr><td><a href=\"" + link + "&eventCategory=OE" + "\" class=\"" + props.getHrefCssStyle() + "\">Other Event (" + otherEventList.size() + ")</a></td></tr>");
				}
				
				b.append("</table>");
        	}
        } 
        return b.toString();
    }

}
