package mapping.juvenilecase;

import messaging.productionsupport.GetProductionSupportMaysiDetailEvent;
import mojo.km.messaging.IEvent;

/**
 * @author rcarter
 *
 */
public class GetProductionSupportMaysiDetailsEventWhereClause {

	/**
	 * @param event
	 * @return sql
	 */
    public String getMaysiWhereClause( IEvent event )
	{
    	GetProductionSupportMaysiDetailEvent jEvent = (GetProductionSupportMaysiDetailEvent) event;
    	
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
		 
		if ( (referralNumber != null) && (!referralNumber.trim().equals("")) ) {
			sql.append(" AND REFERRALNUMBER = '").append(referralNumber).append("'");
		} 
		
		return sql.toString();		
	}
}
