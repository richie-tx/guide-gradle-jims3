/*
 * Created on Nov 21, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.supervision;

import messaging.organization.GetProgramUnitsByDivisionIdsEvent;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetProgramUnitsByDivisionIdsEventWhereClaus {

	/**
	 * @param event
	 * @return sql
	 */
    public String getProgramUnitsByDivisionIdsEventWhereClaus( IEvent event )
	{
    	GetProgramUnitsByDivisionIdsEvent gEvent = (GetProgramUnitsByDivisionIdsEvent) event;
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
	private String buildSql(GetProgramUnitsByDivisionIdsEvent gEvent) {
		StringBuffer sql = new StringBuffer();
		if(gEvent.getDivisionIds() != null && !gEvent.getDivisionIds().equals("")){
			sql.append("PARENTORG_ID IN("); 
			sql.append(gEvent.getDivisionIds()); 
			sql.append(") WITH UR");
		}
		return sql.toString();	
	}
}
