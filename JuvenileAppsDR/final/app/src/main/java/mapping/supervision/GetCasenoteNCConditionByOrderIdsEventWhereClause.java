/*
 * Created on Nov 21, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.supervision;

import messaging.administercasenotes.GetCasenoteNCConditionByOrderIdsEvent;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetCasenoteNCConditionByOrderIdsEventWhereClause {

	/**
	 * @param event
	 * @return sql
	 */
    public String getCasenoteNCConditionByOrderIdsEventWhereClause( IEvent event )
	{
    	GetCasenoteNCConditionByOrderIdsEvent gEvent = (GetCasenoteNCConditionByOrderIdsEvent) event;
		return this.buildSql(gEvent.getOrderIds());
	}
	
	/**
	 * @param jims2LogonId
	 * @param jimsAccountId
	 * @param firstName
	 * @param lastName
	 * @param departmentId
	 * @return
	 */
	private String buildSql(String orderId) {
		StringBuffer sql = new StringBuffer();
		if(orderId != null && !orderId.trim().equals("")){
			sql.append("SPRVISIONORDER_ID IN ("); 
			sql.append(orderId); 
			sql.append( ")" );
		}return sql.toString();	
	}
}
