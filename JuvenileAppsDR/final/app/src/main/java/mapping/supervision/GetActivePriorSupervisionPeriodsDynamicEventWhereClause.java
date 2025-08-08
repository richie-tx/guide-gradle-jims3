/*
 * Created on Nov 11, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.supervision;

import messaging.supervisionorder.GetActivePriorSupervisionPeriodsDynamicEvent;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetActivePriorSupervisionPeriodsDynamicEventWhereClause {

	/**
	 * @param event
	 * @return sql
	 */
    public String getActivePriorSupervisionPeriodsDynamicEventWhereClause( IEvent event )
	{
    	GetActivePriorSupervisionPeriodsDynamicEvent gEvent = (GetActivePriorSupervisionPeriodsDynamicEvent) event;
		return this.buildSql(gEvent.getAgencyId(), gEvent.getDefendantIds(), gEvent.isActive());
	}
	
	private String buildSql(String agencyId, String defendantIds, boolean isActive) {
		StringBuffer sql = new StringBuffer();
		sql.append("DEFENDANT_ID IN("); 
		sql.append(defendantIds); 
	    sql.append(") AND AGENCY_ID = '" );
		sql.append(agencyId); 
		if(isActive){
			sql.append("' AND SPRVISIONENDDATE IS NULL"); 
		}else{
			sql.append("' AND SPRVISIONENDDATE IS NOT NULL"); 
		}
        return sql.toString();	
	}
}
