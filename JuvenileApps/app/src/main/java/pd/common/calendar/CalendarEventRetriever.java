/*
 * Created on Nov 28, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.common.calendar;

import java.util.Hashtable;
import java.util.Iterator;
import messaging.calendar.CalendarContextEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CalendarEventRetriever extends CalendarRetriever
{
	
	/** 
	 * Retrieve implementation that will return a distinct list of calendar 
	 * events for all contexts specified.  Attributes and Start/End
	 * Dates could constrain the results.
	 * @return CalendarEventResponse Iterator
	 */
	public Object retrieve() {
		IHome home = new Home();
		Iterator i = home.findAll(this, CalendarEventContextView.class);
		CalendarEventHelper helper = new CalendarEventHelper();
		// filter out the duplicate events
		Hashtable singles = new Hashtable();
		while (i.hasNext()) 
		{
			CalendarEventContextView v = (CalendarEventContextView) i.next();
			if (!singles.containsKey(v.getCalendarEventId())) 
			{
				CalendarEvent calevent = helper.buildCalendarEvent(v);
				singles.put(v.getCalendarEventId(), calevent);
			}			
		}
		return singles.values().iterator();
		
	}
	
	/**
	 * Creates the sql around the contexts for the retriever for retrieving contexts 
	 * for a calendar event.  The sql is specific to retrieving contexts that SHARE
	 * calendar events 
	 * @param event
	 * @return sql snippet
	 */
	protected String buildContextSql(CalendarRetriever event) 
	{		
		StringBuffer sql = new StringBuffer();
		CalendarContextEvent context = event.getCalendarContext();
		boolean buildTheSQL = false;
		
		if (context.getProbationOfficerId().length() > 0) {
			buildTheSQL = true;
		} else if (context.getCaseFileId().length() > 0) {
			buildTheSQL = true;
		} else if (context.getJuvenileId().length() > 0) {
			buildTheSQL = true;
		}
		
		if (buildTheSQL) {
			String or = "";
			
			sql.append("(");
			if ( (context.getProbationOfficerId() != null) && (context.getProbationOfficerId().length() > 0)) {
				sql.append("officer_id = '" + context.getProbationOfficerId() + "'");
				or = " or ";
			}
			if ( (context.getCaseFileId() != null) && (context.getCaseFileId().length() > 0)) {
				sql.append(or).append("casefile_id = '" + context.getCaseFileId() + "'");
				or = " or ";
			}
			if ( (context.getJuvenileId() != null) && (context.getJuvenileId().length() > 0)) {
				sql.append(or).append("juvenile_id = '" + context.getJuvenileId() + "'");
			}
			sql.append(")");
		}
		
		return sql.toString();
	}
}
