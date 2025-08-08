/*
 * Created on Nov 21, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.administercasenotes;

import messaging.administercasenotes.GetCasenoteAssociatesByCasenoteIdsEvent;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetCasenoteAssociatesByCasenoteIdsEventWhereClause {

	/**
	 * @param event
	 * @return sql
	 */
    public String getCasenoteAssociatesByCasenoteIdsEventWhereClause( IEvent event )
	{
    	GetCasenoteAssociatesByCasenoteIdsEvent gEvent = (GetCasenoteAssociatesByCasenoteIdsEvent) event;
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
	private String buildSql(GetCasenoteAssociatesByCasenoteIdsEvent gEvent) {
		StringBuffer sql = new StringBuffer();
		if(gEvent.getCasenoteIds() != null && !gEvent.getCasenoteIds().equals("")){
			sql.append("CASENOTE_ID IN("); 
			sql.append(gEvent.getCasenoteIds()); 
			sql.append(") WITH UR");
		}
		return sql.toString();	
	}
}
