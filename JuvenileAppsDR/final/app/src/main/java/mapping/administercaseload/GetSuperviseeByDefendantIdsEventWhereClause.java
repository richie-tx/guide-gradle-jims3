/*
 * Created on Nov 21, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.administercaseload;

import messaging.administercaseload.GetSuperviseeByDefendantIdsEvent;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetSuperviseeByDefendantIdsEventWhereClause {

	/**
	 * @param event
	 * @return sql
	 */
    public String getSuperviseeByDefendantIdsEventWhereClause( IEvent event )
	{
    	GetSuperviseeByDefendantIdsEvent gEvent = (GetSuperviseeByDefendantIdsEvent) event;
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
	private String buildSql(GetSuperviseeByDefendantIdsEvent gEvent) {
		StringBuffer sql = new StringBuffer();
		if(gEvent.getDefendantIds() != null && !gEvent.getDefendantIds().equals("")){
			sql.append(" DEFENDANT_ID IN("); 
			sql.append(gEvent.getDefendantIds()); 
			sql.append(")");
		}
		return sql.toString();	
	}
}
