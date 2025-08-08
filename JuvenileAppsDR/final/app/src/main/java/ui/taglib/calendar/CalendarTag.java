/*
 * CalendarTag.java
 *
 * Created on December 6, 2005, 11:14 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package ui.taglib.calendar;
        
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.ICalendarEvent;
import messaging.calendar.reply.CalendarEventResponse;
import mojo.km.utilities.MessageUtil;

/**
 *
 * @author glyons
 */
public class CalendarTag extends TagSupport {
    
    private static final int COLUMNS = 7;
    private static final int MAX_ROWS = 6;
    
    public static String FULL_WEEKDAY_TEXT = "FULLTEXT";
    public static String ABBREV_WEEKDAY_TEXT = "ABBREV";
    public static String FIRST_LETTER_WEEKDAY_TEXT = "FIRSTLETTER";
    
    private String calendarStyle = "defaultCalendarSkin";
    private String weekdayViewType = FULL_WEEKDAY_TEXT;
    private String monthYearParamName = "startMonthYear";
    private String monthYear;
    private String prevLinkText = "< previous";
    private String nextLinkText = "next >";
    private String locale = "en";
    private String dayDisplayClass = "ui.taglib.calendar.ConsolidatedEventDayPresentation";
    private boolean showPreviousNext = true;
    private String title;
    private String sessionAttributeName;
    private String serviceEvent;
    private String eventTimeFormat;
    private String eventLink;
    private String eventLinkStyle;
    private Collection calendarEvents = new ArrayList();
    
    private ArrayList contexts = new ArrayList();
    
    /**
     * Sets the collection of CalendarEventResponses on to the session
     * @param responses
     */
    protected void setSessionAttributeValue(Collection responses) {
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
        req.getSession().setAttribute(getSessionAttributeName(), responses);
    }
    
    /**
     * Returns the collection of Calendar Events or an empty list
     * if nothing is found for the session attribute name
     * @return collection
     */
    protected Collection getSessionAttributeValue() {
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
		String attName = getSessionAttributeName();
		Collection c = new ArrayList();
		if (attName != null && !attName.equals("")) {		
        	c = (Collection) req.getSession().getAttribute(getSessionAttributeName());
        	if (c == null) {
            	c = new ArrayList();	
        	}
		}
        return c;
    }
    
    /**
     * Clears the session values byt the appropriate attribute name
     */
    protected void clearSessionAttributeValue() {
		Object o = pageContext.getRequest();
		HttpServletRequest req = (HttpServletRequest) o;
		// Remove any existing values off the session for this calendar instance
		HttpSession session = req.getSession();
		String attName = getSessionAttributeName();
		if (session != null && (attName != null && !attName.equals(""))) {
			Object att = session.getAttribute(getSessionAttributeName());
			if (att != null) {      
				session.removeAttribute(getSessionAttributeName());
			}
		}
    }
    
    /**
     * Start of the evaluation of required types.  If the user
     * is of the required user type the body is evaluted, else
     * it is skipped
     * @return body evaluation or not     */
    public int doStartTag() throws JspException {
        clearSessionAttributeValue();
        
        return EVAL_BODY_AGAIN;
    }
    
    /**
     * Executes after the body of the tag has been processed.  This
     * implementation will write out the contents of the body and determine
     * whether to perform another iteration by seearching for another instance
     * of the event.  If it finds another instance, this method will return
     * EVAL_BODY_TAG, which will re-evaluate the body with respect to the next
     * instance of the event.  Otherwise, it will return SKIP_BODY and end this
     * tag.
     * @return int
     * @exception javax.servlet.jsp.JspTagException
     */
    public int doAfterBody() throws JspTagException {
        // If a service event is specified, retrieve the events
        // for the presentation month and put them on the session
        if (this.serviceEvent != null && !this.serviceEvent.equals("")) {
            Calendar cal = getStartCalendarMonth();
            Calendar startCalDay = getStartCalendarDay();
            Calendar endCalDay = getEndCalendarDay();
            
            // Get the event to post for retrieving the Calendar Events.  Any event
            // that is set via the ServiceEvent attribute must extend GetCalendarEvents
			ICalendarEvent gce = (ICalendarEvent) this.getInstantiatedServiceEvent();
            gce.setStartDatetime(startCalDay.getTime());
            gce.setEndDatetime(endCalDay.getTime());
            //gce.setCalendarContexts(contexts);
            
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent((RequestEvent) gce);
			
			CompositeResponse response = (CompositeResponse) dispatch.getReply();
			Collection events = MessageUtil.compositeToCollection(response, CalendarEventResponse.class);
            
            if (this.getSessionAttributeName() != null && !this.getSessionAttributeName().equals("")) 
            {
                setSessionAttributeValue(events);
            }
            setCalendarEvents(events);
        }        
        return SKIP_BODY;
    }
    
    protected void checkServiceEvent() throws JspTagException {
		// If a service event is specified, retrieve the events
		// for the presentation month and put them on the session
		if (this.serviceEvent != null && !this.serviceEvent.equals("")) {
			Calendar cal = getStartCalendarMonth();
			Calendar startCalDay = getStartCalendarDay();
			Calendar endCalDay = getEndCalendarDay();
            
			// Get the event to post for retrieving the Calendar Events.  Any event
			// that is set via the ServiceEvent attribute must extend GetCalendarEvents
			ICalendarEvent gce = (ICalendarEvent) this.getInstantiatedServiceEvent();
			gce.setStartDatetime(startCalDay.getTime());
			gce.setEndDatetime(endCalDay.getTime());
			//gce.setCalendarContexts(contexts);
            
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent((RequestEvent) gce);
			
			CompositeResponse response = (CompositeResponse) dispatch.getReply();
			Collection events = MessageUtil.compositeToCollection(response, CalendarEventResponse.class);
            
			if (this.getSessionAttributeName() != null && !this.getSessionAttributeName().equals("")) 
			{
				setSessionAttributeValue(events);
			}
			setCalendarEvents(events);
		} 
    }
    
    /**
     * Executes after the body of the tag has finished being processed.  It is
     * only executed once for every instance of the tag in the MSP file.  This
     * implemenation removes the page-level attribute that held the event
     * instance.
     * @return int
     * @exception javax.servlet.jsp.JspException
     */
    public int doEndTag() throws JspException {
        try {
            ServletResponse res = pageContext.getResponse();
            
            pageContext.getRequest().setCharacterEncoding("UTF-8");
			//checkServiceEvent();
            String html = getMonthlyCalendar();
            
            pageContext.getOut().println(html);
            cleanUp();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new JspException(e.getMessage());
        }
        return EVAL_PAGE;
    }
    
    /**
     * Does any cleanup of attributes after the tag has finished
     *
     */
    protected void cleanUp() {
        contexts = new ArrayList();
    }
    
    /**
     * Returns the start month for this calendar that is going to be
     * displayed based off the StartMonthYear.  Format expected is
     * MMYYYY (ex: 012005 would be January 2005)
     * @return Calendar
     */
    protected Calendar getStartCalendarMonth() {
        Calendar cal = Calendar.getInstance(getLocaleFromLanguage(getLocale()));
        
        String monthYear = getStartMonthYear();
        if (monthYear != null && monthYear.length() == 6) {
            String mon = monthYear.substring(0, 2);
            String year = monthYear.substring(2, monthYear.length());
            cal.clear();
            cal.set(Calendar.MONTH, Integer.parseInt(mon)-1);
            cal.set(Calendar.YEAR, Integer.parseInt(year));
        }
        return cal;
    }
    
    /**
     * Returns the start calendar day for the presentation.  This could be
     * a day in the prior month.  Since the claendar display starts with
     * Sunday through Saturday.  If that Sunday is in the prior month
     * that will be the presentation start day.
     * @return Calendar
     */
    protected Calendar getStartCalendarDay() {
        // Get the presentation month
        Calendar cal = getStartCalendarMonth();
        
        // Get the start to the first day that will be displayed in the month (i.e. the
        // first Sunday of the month calendar, even if its not in the selected month)
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfMonth = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, (firstDayOfMonth-1) * -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);   //Bug #71670
        cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
        return cal;
    }
    
    /**
     * Gets the last day that will be displayed for the presentation month.  This
     * could be the next month for the selected presentation month.  
     * @return Calendar
     */
    protected Calendar getEndCalendarDay() {
        // Get the presentation month
        Calendar cal = getStartCalendarMonth();
        // Get the last day that will be displayed in a month calendar.  This will be
        // a saturday and could extend into the next month.
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        int lastDayOfMonth = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, 7-lastDayOfMonth);
        //cal.set(Calendar.HOUR, cal.getActualMaximum(Calendar.HOUR)); //based on civilian time
        //cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE)); //based on civilian time
        cal.set(Calendar.HOUR_OF_DAY, 23); //based on military time
        cal.set(Calendar.MINUTE, 59); //based on military time
        cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));        
        return cal;
    }
    
    /** 
     * Creates the HTML to display the month calendar.
     * 
     * @return String
     */
    protected String getMonthlyCalendar() {
        Calendar calendar = getStartCalendarDay();
        
        DateFormatSymbols dfs = new DateFormatSymbols(getLocaleFromLanguage(getLocale()));
        String[] weekdays = dfs.getWeekdays();
        String[] months = dfs.getMonths();
        
        Calendar currentMonthCal = Calendar.getInstance();
        Calendar presentationCal = getStartCalendarMonth();
  
        String month = months[presentationCal.get(Calendar.MONTH)];
        StringBuffer html = new StringBuffer();
        
        html.append(includeStyleSheet());
        html.append(getCalendarHeading(month, presentationCal));
        html.append(getWeekdayHeading(weekdays));

        int presentationMonth = presentationCal.get(Calendar.MONTH);
        int presentationYear = presentationCal.get(Calendar.YEAR);
        for (int j=0; j < MAX_ROWS; j++) {
            html.append("<tr>\n");
            for (int day = 0; day < COLUMNS; day++) {
                String style = "";
                if (calendar.get(Calendar.YEAR) != presentationYear) {
                    style = "NotCurrentYear";
                } else if (calendar.get(Calendar.MONTH) != presentationMonth) {
                    style = "NotCurrentMonth";
                } else if (calendar.get(Calendar.DAY_OF_YEAR) == currentMonthCal.get(Calendar.DAY_OF_YEAR)
                    && calendar.get(Calendar.YEAR) == currentMonthCal.get(Calendar.YEAR)) {
                    style = "Today";
                } else if (calendar.get(Calendar.DAY_OF_WEEK) == 1 || calendar.get(Calendar.DAY_OF_WEEK) == 7) {
                    style = "Weekend";
                } else {
                    style = "Weekday";
                }
                
                html.append(getCalendarDayCell(style, calendar));
                calendar.add(Calendar.DATE, 1);
            }
            html.append("</tr>\n");
            if (calendar.get(Calendar.MONTH) != presentationMonth) {
                break;
            }
        }
        
        html.append("</table>");
        return html.toString();
    }
    
    /**
     * Used to retrieve the next or previous month parameter to move forward
     * or backward via the UI.  Format is MMYYYY (ex: 012005 would be January 2005)
     * @param cal
     * @param incrementMonth
     * @return String
     */
    protected String incrementStartMonthYear(Calendar cal, int incrementMonth) {
        Calendar work = (Calendar) cal.clone();
        int add = incrementMonth;
        work.add(Calendar.MONTH, add);
        int calMonth = work.get(Calendar.MONTH) + 1;
        String mon = calMonth + "";
        if (calMonth < 10) {
            mon = "0" + calMonth;
        }
        return mon + work.get(Calendar.YEAR);
    }
    
    /**
     * Will build the URL for navigating the previous or next months
     * @param cal
     * @param increment
     * @return String
     */
    protected String buildPrevNextURL(Calendar cal, int increment) {
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
        String servletPath = req.getServletPath();
        Map map = req.getParameterMap();
        
        StringBuffer url = new StringBuffer();
        if (map.values().isEmpty() == false) {
            //Iterator i = map.values().iterator();
            Set set = map.keySet();
            Iterator i = set.iterator();
            while (i.hasNext()) {
                String key = (String) i.next();
                String[] value = (String[]) map.get(key);
                
                if (key.equalsIgnoreCase(this.getStartMonthYearParamName()) == false) {
                	if (key.equalsIgnoreCase("submitAction") == true){
	                    if (url.length() > 0) {
	                        url.append("&" + key + "=" + value[0]);
	                    } else {
	                        url.append("?" + key + "=" + value[0]);
	                    }
                	}
                }
            }
        }
        if (url.length() > 0) {
            url.append("&" + getStartMonthYearParamName() + "=" + incrementStartMonthYear(cal, increment));
        } else {
            url.append("?" + getStartMonthYearParamName() + "=" + incrementStartMonthYear(cal, increment));
        }
        return url.toString();
    }
    
    /**
     * Gets the requested URL that was invoked.  
     * @return String
     */
    protected String getRequestedURLPath() {
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
        String servletPath = req.getServletPath();
        StringBuffer requestURL = req.getRequestURL();
        if (requestURL.toString().endsWith("/")) {
            
            requestURL.append(servletPath.substring(1));
        }
        return requestURL.toString();
    }
    
    /**
     * Used to add Calendar Contexts to the this tag.  These are used to constrain the
     * Calendar Events that are found and are invoked by the CalendarContextTag
     * @param context
     */
    public void addContextTag(String context) {
        contexts.add(context);
    }
    
	public void addContextTag(CalendarContextEvent context) {
		contexts.add(context);
	}
	
    /**
     * Convenience method to get the Weekday Name text.  3 ways to display
     * the text are available
     * ABBREV - Mon, Tues, Wed, etc...
     * FIRSTLETTER - M, T, W, etc...
     * FULLTEXT (default) - Monday, Tuesday, Wednesday, etc...
     * @param day
     * @return string
     */
    protected String getWeekDayText(String day) {
        String view = getWeekDayViewType();
        if (view.equalsIgnoreCase(ABBREV_WEEKDAY_TEXT)) {
            return day.substring(0, 3);
        } else if (view.equalsIgnoreCase(FIRST_LETTER_WEEKDAY_TEXT)) {
            return day.substring(0, 1);
        }
        return day;
    }
    
    /**
     * Creates the html for the included style sheet for this calendar presentation
     * @return String
     */
    protected String includeStyleSheet() {
        StringBuffer link = new StringBuffer();
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
        link.append("<link href=\"" + req.getContextPath() + "/css/" + getCalendarStyleSheet() + ".css\"");
        link.append(" rel=\"stylesheet\"");
        link.append(" type=\"text/css\">\n");
        return link.toString();
    }
    
    /** 
     * Returns the class style for a particular element in the calendar UI.  
     * Format is of the following convention:
     * stylesheet_<style>
     * Each style sheet will have the same style elements prefixed with the style
     * sheet name.  This allows for multiple calendars on a page with different style
     * sheets.
     * @param style
     * @return String
     */
    protected String getClassStyle(String style) {
        return getCalendarStyleSheet() + "_" + style;
    }
    
    /**
     * Creates the HTML for the calendar heading.  This includes the 
     * Previous/Next links and the Month/Year display
     * @param month
     * @param cal
     * @return String
     */
    protected String getCalendarHeading(String month, Calendar cal) {
        StringBuffer prevURL = new StringBuffer(getRequestedURLPath());
        prevURL.append(buildPrevNextURL(cal, -1));
        StringBuffer nextURL = new StringBuffer(getRequestedURLPath());
        nextURL.append(buildPrevNextURL(cal, 1));
        
        int year = cal.get(Calendar.YEAR);
        
        StringBuffer buf = new StringBuffer();
        //added for nidhish 5/17/2007 RAC
        String startMo = getStartMonthYear();
        if (startMo==null){
         startMo = "";
        }
        buf.append("<input type=\"hidden\" name=\"" + getStartMonthYearParamName() + "\" value=\"" +  startMo +"\" />");

        //end
        buf.append("<table class=\"" + getClassStyle("CalendarTable") + "\">\n");
        if (this.title != null) {
            buf.append("<tr><td colspan=\"" + COLUMNS + "\" class=\"" + getClassStyle("Title") + "\">");
            buf.append(getTitle());
            buf.append("</td></tr>");
        }
        buf.append("<tr>\n");
        if (isShowPreviousNext()) {
            buf.append("<td align=\"left\"><a href=\"" + prevURL + "\" class=\"" + getClassStyle("PrevNextLink") + "\">" + getPreviousLinkText() + "</a></td>\n");
        } else {
            buf.append("<td></td>");
        }
        buf.append("<td colspan=\"5\" align=\"center\" class=\"" + getClassStyle("MonthHeading") + "\">" + month + " " + year + "</td>\n");
        if (isShowPreviousNext())  {
            buf.append("<td align=\"right\"><a href=\"" + nextURL + "\" class=\"" + getClassStyle("PrevNextLink") + "\">" + getNextLinkText() + "</a></td>\n");
        } else {
            buf.append("<td></td>");
        }
        buf.append("</tr>\n");
        return buf.toString();
    }
    
    /**
     * Creates the HTML for the Weekday heading of the calendar.
     * @param weekdays
     * @return String
     */
    protected String getWeekdayHeading(String[] weekdays) {
        StringBuffer buf = new StringBuffer();
        buf.append("<tr>\n");
        for (int x=1; x < weekdays.length; x++) {
            buf.append("<td align=\"center\" class=\"" + getClassStyle("WeekdayHeading") + "\">" + getWeekDayText(weekdays[x]) + "</td>\n");
        }
        buf.append("</tr>\n");
        return buf.toString();
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
     * Will eventually create the HTML for any special events once it has been
     * defined in the future
     * @param cal
     * @return String
     */
    protected String getSpecialEvent(Calendar cal) {
        return "&nbsp;";
    }
    
    /**
     * Gets a collection of Events for a particular date.
     * @param cal
     * @return Collection
     */
    protected Collection getEventsForDate(Calendar cal) {
        
        Collection c = getCalendarEvents();
        Iterator i = c.iterator();
        ArrayList matches = new ArrayList();
        while (i.hasNext()) {
            Object o = i.next();
            if (o instanceof CalendarEventResponse) {
                CalendarEventResponse res = (CalendarEventResponse) o;
                
                Calendar startCal = Calendar.getInstance();
                startCal.setTime(res.getStartDatetime());
                if (startCal.get(Calendar.YEAR) == cal.get(Calendar.YEAR) 
                && startCal.get(Calendar.MONTH) == cal.get(Calendar.MONTH)
                && startCal.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)) {
                    matches.add(res);
                }
            }
        }
        return matches;
    }
    
    /**
     * Returns a locale from a language.  If the locale cannot
     * be determined from the language ENGLISH is returned
     * @param language
     * @return Locale
     */
    protected Locale getLocaleFromLanguage(String language) {
    	Locale[] locales = Locale.getAvailableLocales();
    	for (int x=0; x < locales.length; x++)
    	{
    		String localeLanguage = locales[x].getLanguage();
    		if (localeLanguage.equalsIgnoreCase(language)) {
    			return locales[x];
    		}
    	}
    	return Locale.ENGLISH;
    }
    
    /**
     * Returns a class name for displaying the day for the UI month.  
     * This class will implement the ICalendarDayDisplay interface
     * @return String
     */
    public String getDayDisplayClass() {
        return dayDisplayClass;
    }
    
    /**
     * Sets the dayDisplayClass
     * @param dayDisplayClass
     */
    public void setDayDisplayClass(String dayDisplayClass) {
        this.dayDisplayClass = dayDisplayClass;
    }
    
    /**
     * Returns an instance of the DayDisplayClass that is specified in the
     * DayDisplayClass Attribute of the tag.  This class will implement the
     * ICalendarDayDisplay interface.  If the class does not implement
     * this interface the default CalendarEventDayPresentation class will
     * be returned
     * @return ICalendarDayDisplay
     */
    public ICalendarDayDisplay getDisplay() {
        try {
            Class c = Class.forName(getDayDisplayClass());
            
            return (ICalendarDayDisplay) c.newInstance();
        } catch (Exception e) {
            System.err.println("[CalendarTag] The display class specified in the CalendarTag, " + getDayDisplayClass() + " is invalid.");
            return new CalendarEventDayPresentation();
        }
    }
    
    /**
     * To display the previous/next links on the calendar or not
     * @return boolean
     */
    public boolean isShowPreviousNext() {
        return showPreviousNext;
    }
    
    /**
     * Sets the boolean to display the next/previous links on the UI
     * @param showPreviousNext
     */
    public void setShowPreviousNext(boolean showPreviousNext) {
        this.showPreviousNext = showPreviousNext;
    }
    
    /**
     * Returns the title to display at the top of the calendar
     * @return String
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Sets the title to display at the top of the calendar
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Returns the session attribute name where the collection of
     * calendar events will be stored 
     * @return String
     */
    public String getSessionAttributeName() {
        return sessionAttributeName;
    }
    
    /**
     * Sets the session attribute name where the collection of
     * calendar events will be stored 
     * @param attribute
     */
    public void setSessionAttributeName(String attribute) {
        this.sessionAttributeName = attribute;
    }
    
    /**
     * Sets the Language Locale for the UI presentation
     * @param loc
     */
    public void setLocale(String loc) {
        this.locale = loc;
    }
    
    /**
     * Returns the language Locale for the presentation
     * @return String
     */
    public String getLocale() {
        return this.locale;
    }
    
    /**
     * Sets the calendar style sheet for the UI
     * @param style
     */
    public void setCalendarStyleSheet(String style) {
        this.calendarStyle = style;
    }
    
    /**
     * Returns the calendar style sheet for the UI
     * @return String
     */
    public String getCalendarStyleSheet() {
        if (this.calendarStyle.endsWith(".css")) {
            this.calendarStyle = calendarStyle.substring(0, calendarStyle.indexOf(".css"));
        }
        return this.calendarStyle;
    }
    
    /**
     * Sets the weekday heading view type. Expected values:
     * FULLTEXT (default), ABBREV (Mon, Tues, etc), FIRSTLETTER (M, T, etc)
     * @param type
     */
    public void setWeekDayViewType(String type) {
        this.weekdayViewType = type;
    }
    
    /**
     * Returns the view type for the weekday headings
     * @return String
     */
    public String getWeekDayViewType() {
        return this.weekdayViewType;
    }
    
    /**
     * Sets the start monthyear for the presentation to display.
     * Format should be MMYYYY.  Default is current monthyear
     * @param start
     */    
    public void setStartMonthYear(String start) {
        this.monthYear = start;
    }
    
    /**
     * Returns the startMonthYear for the presentation.  If the request
     * contains the paramname for the startmonth year it will retrieve
     * that value and return it
     * @return String
     */
    public String getStartMonthYear() {
        String start = pageContext.getRequest().getParameter(getStartMonthYearParamName());
        if (start != null) {
            return start;
        }
        return monthYear;
    }
    
    /**
     * The StartMonth Year Param name is the name of the paramater that will be 
     * used to determine the display month year for when the previous and next
     * links are clicked.  This allows for multiple calendars on a page display
     * seperate months/years
     * @return String
     */
    public String getStartMonthYearParamName() {
        return this.monthYearParamName;
    }
    /**
     * Sets the StartMonth Year Param name is the name of the paramater that will be 
     * used to determine the display month year for when the previous and next
     * links are clicked.  This allows for multiple calendars on a page display
     * seperate months/years
     * @param paramName
     */
    public void setStartMonthYearParamName(String paramName) {
        this.monthYearParamName = paramName;
    }
    
    /**
     * Sets the value of what should display for the Next link text.  Default is Next >
     * @param text
     */
    public void setNextLinkText(String text) {
        this.nextLinkText = text;
    }
    
    /**
     * Returns the value to display for next link
     * @return String
     */
    public String getNextLinkText() {
        return this.nextLinkText;
    }
    
    /**
     * Sets the value that should display for the Previous link text.  
     * Default is Previous >
     * @param text
     */
    public void setPreviousLinkText(String text) {
        this.prevLinkText = text;
    }
    
    /**
     * Returns the vlaue to display in the UI for the previous link
     * @return String
     */
    public String getPreviousLinkText() {
        return this.prevLinkText;
    }
    
    /**
     * Gets the service event that will be used to get the Calendar Events
     * for this Calendar.  
     * @return String 
     */
    public String getServiceEvent() {
        return serviceEvent;
    }
    
    /**
	 * sets the service event that will be used to get the Calendar Events
	 * for this Calendar.
	 */    
    public void setServiceEvent(String serviceEvent) {
        this.serviceEvent = serviceEvent;
    }
    
    /**
     * Will instantiate the service event that is specified in the serviceevent attribute
     * @return IEvent
     * @throws JspException
     */
    protected ICalendarEvent getInstantiatedServiceEvent() throws JspTagException {
    	if (getServiceEvent() == null) {
    		return null;
    	}
		ICalendarEvent event = null;
    	try {
    
			Class c = Class.forName(getServiceEvent());
			Class[] ints = c.getInterfaces();
			for (int x = 0; x < ints.length; x++) {
				Class cc = ints[x];
			}
			event = (ICalendarEvent) c.newInstance();
			
    	} catch (ClassNotFoundException cnfe) {
    		throw new JspTagException("[CalendarTag] Instantiating Service Event - ClassNotFoundException (" + getServiceEvent() + ").");
    	} catch (IllegalAccessException iae) {
			throw new JspTagException("[CalendarTag] Instantiating Service Event - IllegalAccessException (" + getServiceEvent() + ").");
    	} catch (InstantiationException ie) {
			throw new JspTagException("[CalendarTag] Instantiating Service Event - InstantiationException (" + getServiceEvent() + ").");
    	} catch (ClassCastException cce) {
			throw new JspTagException("[CalendarTag] Instantiating Service Event - ClassCastException (" + getServiceEvent() + ") is not of type ICalendarEvent.");
    	}
		
		return event;
    }
    
    /**
     * Gets the display for te time format for events.  Default is HH:mm
     * @return String
     */
    public String getEventTimeFormat() {
        if (eventTimeFormat == null || eventTimeFormat.equals("")) {
            eventTimeFormat = "HH:mm";
        }
        return eventTimeFormat;
    }
    
    /**
     * Sets the display for te time format for events.
     * @param eventTimeFormat
     */
    public void setEventTimeFormat(String eventTimeFormat) {
        this.eventTimeFormat = eventTimeFormat;
    }

	/**
	 * Gets the value for the link for events for this calendar.  It is
	 * used for directing to event details etc.  Is explicitly used by
	 * the CalendarDayDisplay instance
	 * @return String
	 */
    public String getEventLink() {
        return eventLink;
    }

	/**
	 * Sets the value for the link for events for this calendar.  It is
	 * used for directing to event details etc.  Is explicitly used by
	 * the CalendarDayDisplay instance
	 * @param eventLink
	 */
    public void setEventLink(String eventLink) {
        this.eventLink = eventLink;
    }

	/**
	 * Gets the Style for the event link
	 * @return String
	 */
    public String getEventLinkStyle() {
        return eventLinkStyle;
    }

	/**
	 * Sets the style for the event(s) link
	 * @param eventLinkStyle
	 */
    public void setEventLinkStyle(String eventLinkStyle) {
        this.eventLinkStyle = eventLinkStyle;
    }

	/**
	 * Sets Calendar Events that were returned by the
	 * Service Event
	 * @param events
	 */
    public void setCalendarEvents(Collection events) {
        if (calendarEvents == null) {
            events = new ArrayList();
        }
        this.calendarEvents = events;
    }
    
    /**
     * Returns all the Calendar Events that were returned 
     * by the Service Event
     * @return Collection
     */
    public Collection getCalendarEvents() {
        return this.calendarEvents;
    }
}
