/*
 * Created on Jan 11, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.taglib.calendar;

import java.util.Collection;

/**
 *
 * @author glyons
 */
public class ConsolidatedEventDayPresentation  implements ICalendarDayDisplay { 
    
    /**
     * Creates one link in the calendar day square for all events
     * @return string
     */
    public String getHTML(CalendarDayDisplayProperties props) 
    {
        StringBuffer b = new StringBuffer();
        Collection col = props.getEvents();
        if (col != null && col.size() > 0) {
        	String href = props.getHref();
        	if (href == null) {
				href = "eventLink attribute was not defined in the calendar tag";
				System.err.println("[ConsolidatedEventDayPresentation] - eventLink attribute was not defined in the calendar tag");
				StringBuffer link = new StringBuffer(href);
				b.append("<table class=\"" + props.getCssStyle() +  "\"");
				b.append("<tr><td>\n");
				b.append("<a href=\"" + link + "\" class=\"" + props.getHrefCssStyle() + "\">Events (" + col.size() + ")</a></td></tr>");
				b.append("</table>");				
        	} else {
				StringBuffer link = new StringBuffer(href);
				if(href.indexOf("?") < 0)
					link.append("?");
				else
					link.append("&");
				link.append("sessionevents=" + props.getSessionAttributeName());
				link.append("&calendardate=" + props.getDayDate().getTime());
				b.append("<table class=\"" + props.getCssStyle() +  "\"");
				b.append("<tr><td>\n");
				b.append("<a href=\"" + link + "\" class=\"" + props.getHrefCssStyle() + "\">Events (" + col.size() + ")</a></td></tr>");
				b.append("</table>");
        	}
        } 
        return b.toString();
    }
}
