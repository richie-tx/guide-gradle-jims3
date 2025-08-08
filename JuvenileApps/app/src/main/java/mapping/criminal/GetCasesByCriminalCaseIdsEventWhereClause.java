/*
 * Created on Nov 21, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.criminal;

import messaging.criminalcase.GetCasesByCriminalCaseIdsEvent;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetCasesByCriminalCaseIdsEventWhereClause {

	/**
	 * @param event
	 * @return sql
	 */
    public String getCasesByCriminalCaseIdsEventWhereClause( IEvent event )
	{
    	GetCasesByCriminalCaseIdsEvent gEvent = (GetCasesByCriminalCaseIdsEvent) event;
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
	private String buildSql(GetCasesByCriminalCaseIdsEvent gEvent) {
		StringBuffer sql = new StringBuffer();
		if(gEvent.getCriminalCaseIds() != null && !gEvent.getCriminalCaseIds().equals("")){
			sql.append(" CRIMINALCASE_ID IN("); 
			sql.append(gEvent.getCriminalCaseIds()); 
			sql.append(")");
		}
		return sql.toString();	
	}
}
