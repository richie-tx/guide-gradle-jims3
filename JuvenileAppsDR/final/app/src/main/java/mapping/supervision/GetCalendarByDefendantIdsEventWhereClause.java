/*
 * Created on Nov 21, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.supervision;

import messaging.calendar.GetCalendarByDefendantIdsEvent;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetCalendarByDefendantIdsEventWhereClause {

	/**
	 * @param event
	 * @return sql
	 */
    public String getCalendarByDefendantIdsEventWhereClause( IEvent event )
	{
    	GetCalendarByDefendantIdsEvent gEvent = (GetCalendarByDefendantIdsEvent) event;
		return this.buildSql(gEvent);
	}
	
	/**
	 * @param jims2LogonId
	 * @param jimsAccountId
	 * @param firstName
	 * @param lastName
	 * @param departmentId
	 * @return
	 */
	private String buildSql(GetCalendarByDefendantIdsEvent gEvent) {
		StringBuffer sql = new StringBuffer();
		if(gEvent.getDefendantIds() != null && !gEvent.getDefendantIds().equals("")){
			sql.append(" DEFENDANT_ID IN("); 
			sql.append(gEvent.getDefendantIds()); 
			sql.append(")");
		}
		return sql.toString();	
	}
}
