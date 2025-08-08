/*
 * Created on Nov 21, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.supervision;

import messaging.administercompliance.GetNCResponsesEvent;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetNCResponsesWhereClause {

	/**
	 * @param event
	 * @return sql
	 */
    public String getNCResponsesWhereClause( IEvent event )
	{
    	GetNCResponsesEvent gEvent = (GetNCResponsesEvent) event;
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
	private String buildSql(GetNCResponsesEvent gEvent) {
		StringBuffer sql = new StringBuffer();
		sql.append("REPORTTYPE='"); 
		sql.append(gEvent.getReportType()); 
		sql.append("'"); 
		
		if(gEvent.getSprOrderId() != null && !gEvent.getSprOrderId().equals("")){
			sql.append(" AND SPRVISIONORDER_ID="); 
			sql.append(gEvent.getSprOrderId()); 
		}else if(gEvent.getCriminalcaseId() != null && !gEvent.getCriminalcaseId().equals("")){
			sql.append(" AND CRIMINALCASE_ID='"); 
			sql.append(gEvent.getCriminalcaseId()); 
			sql.append("'"); 
		}
		return sql.toString();	
	}
}
