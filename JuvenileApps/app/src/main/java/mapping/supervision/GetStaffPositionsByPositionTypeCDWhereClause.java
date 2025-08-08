/*
 * Created on Nov 21, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.supervision;

import messaging.cscdstaffposition.GetStaffPositionsByPositionTypeCDEvent;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetStaffPositionsByPositionTypeCDWhereClause {

	/**
	 * @param event
	 * @return sql
	 */
    public String getStaffPositionsByPositionTypeCDWhereClause( IEvent event )
	{
    	GetStaffPositionsByPositionTypeCDEvent gEvent = (GetStaffPositionsByPositionTypeCDEvent) event;
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
	private String buildSql(GetStaffPositionsByPositionTypeCDEvent gEvent) {
		StringBuffer sql = new StringBuffer();
		sql.append("AGENCY_ID ='"); 
		sql.append(gEvent.getAgencyId()); 
		sql.append("'"); 
		
		if(gEvent.getOrganizationId() != null && !gEvent.getOrganizationId().equals("")){
			sql.append(" AND ORGANIZATION_ID ="); 
			sql.append(gEvent.getOrganizationId()); 
		}
		
		sql.append(" AND (POSITIONTYPECD = 'SU' OR POSITIONTYPECD = 'AS')");
		
		if(gEvent.getStatusId() != null && !gEvent.getStatusId().equals("")){
			sql.append(" AND POSITIONSTATUSCD='"); 
			sql.append(gEvent.getStatusId()); 
			sql.append("'"); 
		}
		return sql.toString();	
	}
}
