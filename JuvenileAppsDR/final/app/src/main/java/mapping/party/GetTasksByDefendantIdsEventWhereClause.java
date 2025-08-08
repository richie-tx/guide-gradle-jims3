/*
 * Created on Nov 21, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.party;

import messaging.party.GetTasksByDefendantIdsEvent;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetTasksByDefendantIdsEventWhereClause {

	/**
	 * @param event
	 * @return sql
	 */
    public String getTasksByDefendantIdsEventWhereClause( IEvent event )
	{
    	GetTasksByDefendantIdsEvent gEvent = (GetTasksByDefendantIdsEvent) event;
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
	private String buildSql(GetTasksByDefendantIdsEvent gEvent) {
		StringBuffer sql = new StringBuffer();
		if(gEvent.getDefendantIds() != null && !gEvent.getDefendantIds().equals("")){
			sql.append(" PM_KEY_SPN IN("); 
			sql.append(gEvent.getDefendantIds()); 
			sql.append(")");
		}
		return sql.toString();	
	}
}
