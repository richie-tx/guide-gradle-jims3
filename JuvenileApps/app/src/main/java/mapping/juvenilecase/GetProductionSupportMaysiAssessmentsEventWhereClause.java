package mapping.juvenilecase;

import messaging.productionsupport.GetProductionSupportMaysiAssessmentEvent;
import messaging.productionsupport.GetProductionSupportMaysiDetailEvent;
import mojo.km.messaging.IEvent;

/**
 * @author rcarter
 *
 */
public class GetProductionSupportMaysiAssessmentsEventWhereClause {

	/**
	 * @param event
	 * @return sql
	 */
    public String getMaysiWhereClause( IEvent event )
	{
    	GetProductionSupportMaysiAssessmentEvent jEvent = (GetProductionSupportMaysiAssessmentEvent) event;
    	
    	String juvenileId = jEvent.getJuvenileId();
    	String referralNumber = jEvent.getReferralNumber();
    			
		return this.buildSql(juvenileId, referralNumber);
	}
	
	/**
	 * @param juvenileId
	 * @param statusId
	 * @return
	 */
	private String buildSql(String juvenileId, String referralNumber) {
		
		StringBuffer sql = new StringBuffer();
		 
		sql.append("JUVENILE_ID = '").append(juvenileId).append("'");
		
		if(referralNumber != null && !referralNumber.trim().equalsIgnoreCase("")){
			sql.append(" AND REFERRALNUMBER = '").append(referralNumber).append("'");
		}
		
		return sql.toString();		
	}
}
