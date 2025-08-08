/*
 * Created on April 15, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.supervision;

import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetViolationReportCalEventsEventWhereClause {

	/**
	 * @param event
	 * @return sql
	 */
    public String getWhereClause( IEvent event )
	{
		messaging.cscdcalendar.GetViolationReportCalEventsEvent gEvent = (messaging.cscdcalendar.GetViolationReportCalEventsEvent) event;
		String defendantId = gEvent.getDefendantId();		
		return this.buildSql(defendantId);
	}
	
	/**
	 * @param defendantId
	 * @return
	 */
	private String buildSql(String defendantId) {
		StringBuffer sql = new StringBuffer();
		sql.append("CSEVENTTYPE IN ('OV','GV','FV') AND OUTCOME IN ('AT', 'CO') AND EVENTDATE < (current timestamp - 90 days) AND DEFENDANT_ID = '");
		sql.append(defendantId);
		sql.append("' ORDER BY EVENTDATE DESC FETCH FIRST ROW ONLY");
		return sql.toString();		
	}
}
