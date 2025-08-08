/*
 * CalendarEventDayPresentation.java
 *
 * Created on December 12, 2005, 11:31 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package ui.taglib.calendar;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import messaging.calendar.reply.CalendarEventResponse;
import ui.taglib.calendar.CalendarDayDisplayProperties;
import ui.taglib.calendar.ICalendarDayDisplay;

/**
 *
 * @author glyons
 */
public class CalendarEventDayPresentation implements ICalendarDayDisplay {
    
    public String getHTML(CalendarDayDisplayProperties props) {
        StringBuffer b = new StringBuffer();
        Collection col = props.getEvents();
        if (col != null && col.size() > 0) {
            b.append("<table class=\"" + props.getCssStyle() +  "\">\n");
            Iterator i = col.iterator();
            while (i.hasNext()) {
                CalendarEventResponse res = (CalendarEventResponse) i.next();
                if (props.getHref() == null || props.getHref().equals("")) {
                    b.append("<tr><td>" + getEventTime(res.getStartDatetime(), props.getTimeFormat()) + " " + res.getSubject() + "</td></tr>\n");
                } else {
					StringBuffer link = new StringBuffer(props.getHref());
					link.append("&calendarEventId=" + res.getCalendarEventId());
					b.append("<tr><td><a href=\"" + link + "\" class=\"" + props.getHrefCssStyle() + "\">\n");
                    b.append(getEventTime(res.getStartDatetime(), props.getTimeFormat()) + " " + res.getSubject() + "\n");
                    b.append("</a></td></tr>\n");
                }
            }
            b.append("</table>\n");
        }
        return b.toString();
    }
    
    private String getEventTime(Date start, String timeFormat) {
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        String dateString = dateString = format.format(start);
        return dateString.toLowerCase(); 
    }
    
}
