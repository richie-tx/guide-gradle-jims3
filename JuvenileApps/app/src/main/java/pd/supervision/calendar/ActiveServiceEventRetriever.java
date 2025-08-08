/*
 * Created on Nov 28, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.supervision.calendar;

import java.util.Iterator;

import org.apache.commons.collections.FastHashMap;
import naming.PDCalendarConstants;
import pd.common.calendar.CalendarRetriever;
import messaging.calendar.CalendarContextEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class ActiveServiceEventRetriever extends CalendarRetriever
{

	/**
	 * @return CalendarEventResponse Iterator
	 */
	public Object retrieve()
	{
		IHome home = new Home();
		Iterator i = home.findAll(this, ServiceEventContext.class);

		// filter out the duplicate events
		FastHashMap singles = new FastHashMap();
		while (i.hasNext())
		{
			ServiceEventContext v = (ServiceEventContext) i.next();
			if (!singles.containsKey(v.getCalendarEventId()))
			{
				singles.put(v.getCalendarEventId(), v);
			}
		}
		return singles.values().iterator();
	}

	/**
	 * Creates the sql around the contexts for the retriever for retrieving contexts for a calendar
	 * event. The sql is specific to retrieving contexts that SHARE calendar events
	 * 
	 * @param event
	 * @return sql snippet
	 */
	protected String buildContextSql(CalendarRetriever event)
	{
		StringBuilder sql = new StringBuilder();
		CalendarContextEvent context = event.getCalendarContext();

		boolean officerNotNull = false;
		boolean JuvenileNotNull = false;
		boolean casefileNotNull = false;
		boolean CLMOfficersNotNull = false;
		
		if (context != null) {
			if ((context.getProbationOfficerId() != null) && (context.getProbationOfficerId().length() > 0)) {
				officerNotNull = true;
				if ( (context.getCLMProbationOfficerIds() != null) && (context.getCLMProbationOfficerIds().length > 0) ) {
					CLMOfficersNotNull = true;
				}
			} else if ((context.getCaseFileId() != null) && (context.getCaseFileId().length() > 0)) {
				casefileNotNull = true;
			} else if ((context.getJuvenileId() != null) && (context.getJuvenileId().length() > 0)) {
				JuvenileNotNull = true;
			}
		}
		
		if (officerNotNull || casefileNotNull || JuvenileNotNull || CLMOfficersNotNull) {
			String or = "";
			
			//<Kishore>JIMS200056933 : Future service provider events cancelled through View Calendar no longer appear on any calendars
			//sql.append("EVENTSTATUSCD NOT LIKE '");
			//sql.append(PDCalendarConstants.SERVICE_EVENT_STATUS_CANCELLED);
			//sql.append("' AND (");
			
			sql.append("(");
			if ( officerNotNull) {
				sql.append("officer_id in (" + context.getProbationOfficerId()); 
				
				if ( CLMOfficersNotNull ) {
					boolean first = true;
					for(int i=0; i<context.getCLMProbationOfficerIds().length; i++){
						if ( first ) {
							 							 
							 if (context.getCLMProbationOfficerIds().length == 1) {
								 sql.append(", " + context.getCLMProbationOfficerIds()[i] + " ");
							 } else {
								 sql.append(", " + context.getCLMProbationOfficerIds()[i] + ", ");
							 }
							 
							 first = false;
						 }else if( (i+1) == context.getCLMProbationOfficerIds().length) {
							 sql.append(context.getCLMProbationOfficerIds()[i]);
						 } else {
							 sql.append(context.getCLMProbationOfficerIds()[i] + ", ");
						 }
					}
					sql.append(" ) ");
				} else {
					sql.append(" ) ");
				}
				
				or = " or ";
			}
			if ( casefileNotNull) {
				sql.append(or).append("casefile_id = " + context.getCaseFileId() + "");
				or = " or ";
			}
			if ( JuvenileNotNull) {
				sql.append(or).append("juvenile_id = '" + context.getJuvenileId() + "'");
			}
			sql.append(")");			
		}
		
		return sql.toString();
	}
}
