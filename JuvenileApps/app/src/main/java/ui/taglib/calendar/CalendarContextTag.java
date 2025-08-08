/*
 * CalendarContextTag.java
 *
 * Created on December 12, 2005, 9:53 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package ui.taglib.calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Tag that is used along with the CalendarTag to specify the
 * specific CalendarContexts that should be found for the 
 * Calendar Events.
 * @author glyons
 */
public class CalendarContextTag extends BodyTagSupport {
    
    private String contextType;
    private String contextId;
    
    /** Creates a new instance of CalendarEventTag */
    public CalendarContextTag() {}
    
    /**
     * Gets the parent CalendarTag for this tag.  A Parent CalendarTag
     * is required for the CalendarContextTag
     * @return
     */
    private CalendarTag getCalendarTag() {
        return (CalendarTag) findAncestorWithClass(this, CalendarTag.class);
    }
    
    /**
     * @see javax.servlet.jsp.tagext.Tag#doStartTag()
     * @return int
     */
    public int doStartTag() throws JspException {
        CalendarTag calendarTag = getCalendarTag();
        if (calendarTag == null) {
            throw new JspException("CalendarContextTag requires a parent CalendarTag when defining in the jsp");
        }
        
        return super.doStartTag();
    }
    
    /**
     * End Tag done at the end of the Tag
     * @return int
     */
    public int doEndTag() throws JspException {
        CalendarTag calendarTag = getCalendarTag();
        addContext(calendarTag);
        
        return super.doEndTag();
    }
    
    /**
     * Adds a context to the parent CalendarTag
     * @param calendarTag
     */
    private void addContext(CalendarTag calendarTag) {
        if (calendarTag != null) {
        	//TODO Investigate if this is still needed
        	//CalendarEventContext context = new CalendarEventContext();
        	//context.setContextId(contextId);
        	//context.setContextType(contextType);
            //calendarTag.addContextTag(context);
        } 
    }
    
    /**
     * Gets the ContextType for the Context
     * @return String
     */
    public String getContextType() {
        return contextType;
    }
    
    /**
     * Sets the calendar context for this tag
     * @param contextType
     */
    public void setContextType(String contextType) {
        this.contextType = contextType;
    }
    
    /**
     * Gets the context Id associated with this Calendar Context
     * @return string
     */
    public String getContextId() {
        return contextId;
    }
    
    /**
     * Sets the context Id for this calendar context
     * @param contextId
     */
    public void setContextId(String contextId) {
        this.contextId = contextId;
    }
    
}
