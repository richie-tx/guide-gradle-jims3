/*
 * Created on Nov 28, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.common.calendar;

import java.util.Hashtable;
import java.util.Iterator;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import messaging.calendar.CalendarContextEvent;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SharedCalendarEventRetriever extends CalendarRetriever
{
	
	/** 
	 * Retrieve implementation that will return a distinct list of calendar 
	 * events that are SHARED between the contexts.  Attributes and Start/End
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
			sql.append(getWhereClauseStart());
			buildTheSQL = true;
		} else if (context.getCaseFileId().length() > 0) {
			sql.append(getWhereClauseStart());
			buildTheSQL = true;
		} else if (context.getJuvenileId().length() > 0) {
			sql.append(getWhereClauseStart());
			buildTheSQL = true;
		}
		
		if (buildTheSQL) {
			String and = "";
			
			if ( (context.getProbationOfficerId() != null) && (context.getProbationOfficerId().length() > 0)) {
				sql.append("officer_id = '" + context.getProbationOfficerId() + "'");
				and = " and ";
			}
			if ( (context.getCaseFileId() != null) && (context.getCaseFileId().length() > 0)) {
				sql.append(and).append("casefile_id = '" + context.getCaseFileId() + "'");
				and = " and ";
			}
			if ( (context.getJuvenileId() != null) && (context.getJuvenileId().length() > 0)) {
				sql.append(and).append("juvenile_id = '" + context.getJuvenileId() + "'");
			}
		
		}
				
		return sql.toString();
	}
}
