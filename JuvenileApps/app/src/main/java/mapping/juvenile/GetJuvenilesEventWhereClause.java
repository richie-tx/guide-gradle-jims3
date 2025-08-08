/*
 * Created on Jul 03, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.juvenile;

import messaging.juvenile.GetJuvenilesEvent;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetJuvenilesEventWhereClause {

	/**
	 * @param event
	 * @return sql
	 */
    public String getJuvenilesWhereClause( IEvent event )
	{
		GetJuvenilesEvent jEvent = (GetJuvenilesEvent) event;
		String juvenileId = jEvent.getJuvenileId();
		String statusId = jEvent.getStatusId();
		
		return this.buildSql(juvenileId, statusId);
	}
	
	/**
	 * @param juvenileId
	 * @param statusId
	 * @return
	 */
	private String buildSql(String juvenileId, String statusId) {
		StringBuffer sql = new StringBuffer();
		
		if(statusId != null && !"".equals(statusId.trim()) && !"N".equalsIgnoreCase(statusId)){
			sql.append("COALESCE(JUVENILESTATUSCD, '') LIKE '"); 
			sql.append(statusId); 
			sql.append("'");

		}else if(statusId != null && "N".equalsIgnoreCase(statusId)){
			sql.append("JUVENILESTATUSCD IS NULL"); 
		}else{
			sql.append("COALESCE(JUVENILESTATUSCD, '') LIKE '%'"); 
 		}
		
		if(juvenileId != null && !"".equals(juvenileId.trim())){
			sql.append(" AND JUVENILE_ID IN("); 
			sql.append(juvenileId); 
			sql.append( ")" );
		}
		return sql.toString();		
	}
}
