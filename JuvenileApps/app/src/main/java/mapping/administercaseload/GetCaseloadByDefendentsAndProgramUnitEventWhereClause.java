/*
 * Created on Nov 21, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.administercaseload;

import messaging.administercaseload.GetCaseloadByDefendentsAndProgramUnitEvent;
import messaging.administercasenotes.GetCasenoteSubjectsByCasenoteIdsEvent;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetCaseloadByDefendentsAndProgramUnitEventWhereClause {

	/**
	 * @param event
	 * @return sql
	 */
    public String getCaseloadByDefendentsAndProgramUnitEventWhereClause( IEvent event )
	{
    	GetCaseloadByDefendentsAndProgramUnitEvent gEvent = (GetCaseloadByDefendentsAndProgramUnitEvent) event;
		return this.buildSql(gEvent);
	}
	
	/**
	 * @param gEvent
	 * @return
	 */
	private String buildSql(GetCaseloadByDefendentsAndProgramUnitEvent gEvent) {
		StringBuffer sql = new StringBuffer();
		if(gEvent.getDefendantIds() != null && !gEvent.getDefendantIds().equals("")){
			sql.append("DEFENDANT_ID IN("); 
			sql.append(gEvent.getDefendantIds()); 
			sql.append(")");
		}
		
		if(gEvent.getProgramUnit() != null && !gEvent.getProgramUnit().equals("")){
			sql.append(" AND ORGANIZATION_ID="); 
			sql.append(Integer.parseInt(gEvent.getProgramUnit())); 
		}
		sql.append(" WITH UR");
		return sql.toString();	
	}
}
