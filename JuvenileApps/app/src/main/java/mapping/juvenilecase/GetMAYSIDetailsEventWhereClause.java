/*
 * Created on Jul 03, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.juvenilecase;

import messaging.mentalhealth.GetMAYSIDetailsEvent;
import mojo.km.messaging.IEvent;

/**
 * @author palcocer
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetMAYSIDetailsEventWhereClause {

	/**
	 * @param event
	 * @return sql
	 */
    public String getJuvenilesWhereClause( IEvent event )
	{
    	GetMAYSIDetailsEvent jEvent = (GetMAYSIDetailsEvent) event;
    	
    	String assessmentId = jEvent.getAssessmentId();
    	String subAssessId = jEvent.getSubAssessId();
    	String maysiDetailId = jEvent.getMaysiDetailId();;
    			
		return this.buildSql(assessmentId, subAssessId, maysiDetailId);
	}
	
	/**
	 * @param juvenileId
	 * @param statusId
	 * @return
	 */
	private String buildSql(String assessmentId, String subAssessId, String maysiDetailId) {
		
		StringBuffer sql = new StringBuffer();
		 
		sql.append("MAYSIASSESSMNT_ID = ").append(assessmentId);
		 
		if ( (subAssessId != null) && (!subAssessId.trim().equals("")) ) {
			sql.append(" AND MAYSISUBASSMNT_ID = ").append(subAssessId);
		} else {
			sql.append(" AND MAYSISUBASSMNT_ID IS NULL"); 
		}
		
		if ( (maysiDetailId != null) && (!maysiDetailId.trim().equals("")) ) {
			sql.append(" AND MAYSIDETAIL_ID = ").append(maysiDetailId);
		} else {
			sql.append(" AND MAYSIDETAIL_ID IS NULL"); 
		}
		
		return sql.toString();		
	}
}
