/*
 * Created on Mar 11, 2008
 *
 */
package ui.taglib;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.servlet.jsp.JspTagException;

import messaging.cscdcalendar.GetMonthlyCSCalendarEvent;
import messaging.cscdcalendar.reply.MonthlyCSCalendarResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import ui.taglib.calendar.CalendarDayDisplayProperties;
import ui.taglib.calendar.CalendarTag;
import naming.CSEventControllerServiceNames;

/**
 * @author awidjaja
 *
 * This is an extension of CalendarTag which override the way events are being retrieved.
 * Mainly, the reason for this is because the events are stored in different tables
 * than the original intended tables.
 *  
 */
public class CSCalendarTag extends CalendarTag {
	private String currentContext;
	private String positionId;
	private String superviseeId;
	private boolean needsRefresh;
	
	public int doAfterBody() throws JspTagException {
		if (this.isNeedsRefresh()) {
			if (this.getServiceEvent() != null && !this.getServiceEvent().equals("")) {
				Calendar startCalDay = getStartCalendarDay();
				Calendar endCalDay = getEndCalendarDay();
				Date startDay = startCalDay.getTime();
				Date endDay = endCalDay.getTime();				
				
				GetMonthlyCSCalendarEvent getMonthlyEvents = 
					(GetMonthlyCSCalendarEvent)EventFactory.getInstance(
							CSEventControllerServiceNames.GETMONTHLYCSCALENDAR);
				getMonthlyEvents.setCurrentContext(this.getCurrentContext());
				if(this.positionId != null && this.positionId.length() > 0) {
					getMonthlyEvents.setPositionId(Integer.parseInt(this.getPositionId()));
				}
				getMonthlyEvents.setSuperviseeId(this.getSuperviseeId());
				getMonthlyEvents.setStartDatetime( startDay );
				getMonthlyEvents.setEndDatetime(endDay);
				
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(getMonthlyEvents);
				CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
				MessageUtil.processReturnException(compositeResponse);
				
				Collection events =
					MessageUtil.compositeToCollection(
							compositeResponse, MonthlyCSCalendarResponseEvent.class);
				setCalendarEvents(events);
				
				if (this.getSessionAttributeName() != null && !this.getSessionAttributeName().equals("")) 
	            {
	                setSessionAttributeValue(events);
	            }
			}
		}
		else {
			Collection events = getSessionAttributeValue();
			setCalendarEvents(events);
		}
		return SKIP_BODY;	
	}
	
	/**
     * Creates the HTML for each particular Day Cell in the Calendar UI
     * @param style
     * @param cal
     * @return String
     */
    protected String getCalendarDayCell(String style, Calendar cal) {
        StringBuffer buf = new StringBuffer();
        buf.append("<td class=\"" + getClassStyle(style + "DayCell") + "\">\n");
        buf.append("<table class=\"" + getClassStyle(style + "CellTable") + "\">\n");
        buf.append("<tr>\n");
        buf.append("<td class=\"" + getClassStyle(style + "Day") + "\">" + cal.get(Calendar.DAY_OF_MONTH) + "</td>\n");
        buf.append("<td class=\"" + getClassStyle(style + "SpecialEvents") + "\">" + getSpecialEvent(cal) + "</td>\n");
        buf.append("</tr>\n");
        buf.append("</table>\n");

        if (getDisplay() != null) {
        	CalendarDayDisplayProperties props = new CalendarDayDisplayProperties();
            props.setCssStyle(getClassStyle(style + "Events"));
            props.setEvents(getEventsForDate(cal));
            props.setTimeFormat(getEventTimeFormat());
            props.setHref(getEventLink());
            props.setHrefCssStyle(getClassStyle(style + "EventLink"));
            props.setSessionAttributeName(getSessionAttributeName());
            props.setDayDate(cal.getTime());
            
            buf.append(getDisplay().getHTML(props));
        } 
        buf.append("</td>\n");
        return buf.toString();
    }
	
	/**
	 * @return Returns the currentContext.
	 */
	public String getCurrentContext() {
		return currentContext;
	}
	/**
	 * @param currentContext The currentContext to set.
	 */
	public void setCurrentContext(String currentContext) {
		this.currentContext = currentContext;
	}
	/**
	 * @return Returns the positionId.
	 */
	public String getPositionId() {
		return positionId;
	}
	/**
	 * @param positionId The positionId to set.
	 */
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	/**
	 * @return Returns the superviseeId.
	 */
	public String getSuperviseeId() {
		return superviseeId;
	}
	/**
	 * @param superviseeId The superviseeId to set.
	 */
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}
	/**
	 * @return Returns the needsRefresh.
	 */
	public boolean isNeedsRefresh() {
		return needsRefresh;
	}
	/**
	 * @param needsRefresh The needsRefresh to set.
	 */
	public void setNeedsRefresh(boolean needsRefresh) {
		this.needsRefresh = needsRefresh;
	}
}
