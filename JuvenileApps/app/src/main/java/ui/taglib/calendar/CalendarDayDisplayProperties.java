/*
 * CalendarDayDisplayProperties.java
 *
 * Created on December 14, 2005, 1:37 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package ui.taglib.calendar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author glyons
 */
public class CalendarDayDisplayProperties {
    
    private String href;
    private String hrefCssStyle;
    private String cssStyle;
    private Collection events = new ArrayList();
    private String timeFormat;
    private String sessionAttributeName;
    private Date dayDate;

    public String getHref() {
        return href;
    }

    public void setHref(String link) {
        this.href = link;
    }

    public String getCssStyle() {
        return cssStyle;
    }

    public void setCssStyle(String style) {
        this.cssStyle = style;
    }

    public Collection getEvents() {
        return events;
    }

    public void setEvents(Collection calevents) {
        this.events = calevents;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String format) {
        this.timeFormat = format;
    }

    public String getHrefCssStyle() {
        return hrefCssStyle;
    }

    public void setHrefCssStyle(String sytle) {
        this.hrefCssStyle = sytle;
    }

    public String getSessionAttributeName() {
        return sessionAttributeName;
    }

    public void setSessionAttributeName(String sessionAttributeName) {
        this.sessionAttributeName = sessionAttributeName;
    }
    
	/**
	 * @return
	 */
	public Date getDayDate()
	{
		return dayDate;
	}

	/**
	 * @param date
	 */
	public void setDayDate(Date date)
	{
		dayDate = date;
	}

}
