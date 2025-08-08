/*
 * Created on Nov 21, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.administercasenotes;

import messaging.administercasenotes.GetCasenotesByIdsEvent;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
*/
public class GetCasenotesByIdsEventWhereClause {

	/**
	 * @param event
	 * @return sql
	 */
    public String getCasenotesByIdsEventWhereClause( IEvent event )
	{
    	GetCasenotesByIdsEvent gEvent = (GetCasenotesByIdsEvent) event;
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
	private String buildSql(GetCasenotesByIdsEvent gEvent) {
		StringBuffer sql = new StringBuffer();
		if(gEvent.getSpn() != null && !gEvent.getSpn().equals("")){
			sql.append("DEFENDANT_ID='"); 
			sql.append(gEvent.getSpn()); 
			sql.append("'");
		}
		
		if(gEvent.getSupervisionPeriodId() != null && !gEvent.getSupervisionPeriodId().equals("") && !gEvent.getSupervisionPeriodId().equals("0")){
			sql.append(" AND SPRVSNPERIOD_ID="); 
			sql.append(Integer.parseInt(gEvent.getSupervisionPeriodId())); 
		}
		sql.append(" WITH UR");
		return sql.toString();	
	}
}
