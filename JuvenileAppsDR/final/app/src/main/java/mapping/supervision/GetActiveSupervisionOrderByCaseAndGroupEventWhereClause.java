/*
 * Created on Nov 11, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.supervision;

import messaging.supervisionorder.GetActiveSupervisionOrderByCaseAndGroupEvent;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetActiveSupervisionOrderByCaseAndGroupEventWhereClause {

	String datePattern = new String("yyyy-MM-dd-00.00.00.000000");

	/**
	 * @param event
	 * @return sql
	 */
    public String getActiveSupervisionOrderByCaseAndGroupEventWhereClause( IEvent event )
	{
    	GetActiveSupervisionOrderByCaseAndGroupEvent gEvent = (GetActiveSupervisionOrderByCaseAndGroupEvent) event;
		String groupId = gEvent.getGroupId();
		String caseId = gEvent.getCaseId();
		return this.buildSql(groupId, caseId);
	}
	
	private String buildSql(String groupId, String caseId) {
		StringBuffer sql = new StringBuffer();
		sql.append("CRIMINALCASE_ID='"); 
		sql.append(caseId); 
	    sql.append("' AND " );
		sql.append(" GROUP_ID IN(SELECT GROUP_ID FROM JIMS2.CSGROUP WHERE PARENTGROUP_ID="); 
		sql.append(groupId); 
		sql.append(" OR GROUP_ID=");
		sql.append(groupId); 
		sql.append(")");
        return sql.toString();	
	}
}
