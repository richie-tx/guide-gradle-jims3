/*
 * Created on Dec 1, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.common.calendar;

import java.util.Iterator;

import mojo.km.config.MojoProperties;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.OIDEvent;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CalendarEventHelper
{
	/**
	 * Creates a CalendarEvent from a CalendarEventContextView
	 * @param view
	 * @return calendarEvent
	 */
	public CalendarEvent buildCalendarEvent(CalendarEventContextView view)
	{
		CalendarEvent calevent = new CalendarEvent();
		calevent.setCalendarEventId(view.getCalendarEventId());
		calevent.setCalendarEventType(view.getCalendarEventType());
		calevent.setStartDatetime(view.getStartDatetime());
		calevent.setEndDatetime(view.getEndDatetime());
		calevent.setSubject(view.getSubject());
		calevent.setBodyText(view.getBodyText());
		calevent.setCreatedBy(view.getCreatedBy());
		calevent.setStatus(view.getStatus());
		calevent.setLocation(view.getLocation());
		calevent.setTimeZone(view.getTimeZone());
		calevent.setModified(false);
		calevent.setNotNew();
		return calevent;
	}
	
	/**
	 * A helper method that takes a contextType and a contextId and resolves it into
	 * an instance of the specific context.  It uses reflection to instansiate an
	 * instance of the class and then does a find by OID (i.e. contextId).  If 
	 * found it returns the instance of that context.  The mojo.xml must 
	 * specify the ContextType and its realized class.  All Contexts must
	 * be an Entity and must implement the ICalendarContext interface.
	 * 
	 * @param contextType
	 * @param contextId
	 * @return instance of realized context
	 */
	public Object getContextInstance(String contextType, String contextId) 
	{
		Class contextClass = MojoProperties.getInstance().getCalendaringProperties().getContextClass(contextType);
		if (contextClass == null) {
			throw new RuntimeException("[CalendarHelper.getContextInstance] Could not resolve the context class for this contextType, " + contextType + ".  Check the mojo.xml and verify the context is defined. (Calendaring/Contexts/Context)");
		}

		OIDEvent oidEvent = new OIDEvent();
		oidEvent.setOID(contextId);
		
		// Find by OID
		IHome home = new Home();
		Iterator finds = home.findAll(oidEvent, contextClass);
		if (finds != null) {
			// Return the first one as this should only have 1 entity
			// since we are finding by OID
			return finds.next();				
		}
		return null;
	}
}
