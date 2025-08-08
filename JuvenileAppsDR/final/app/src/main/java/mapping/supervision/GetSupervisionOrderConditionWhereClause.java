/*
 * Created on Oct 5, 2006
 *
 */
package mapping.supervision;

import java.util.Collection;
import java.util.Iterator;

import naming.PDConstants;

import messaging.supervisionorder.GetOrderConditionsFromIdsEvent;
import messaging.supervisionorder.GetSupervisionOrderConditionValuesEvent;
import mojo.km.messaging.IEvent;

/**
 * @author asrvastava
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetSupervisionOrderConditionWhereClause {

	public String getByIdsClause( IEvent anEvent )
	{
		GetOrderConditionsFromIdsEvent evt = (GetOrderConditionsFromIdsEvent)anEvent;
		StringBuffer buf = new StringBuffer();

		// RelIds
		Collection orderConditionIds = evt.getOrderConditionIds(); 
		if (orderConditionIds.size() > 0)
		{
			buf.append( "SPRVSNORDRCOND_ID in(" ); 
			boolean hasFirst = false;
			Iterator iter = orderConditionIds.iterator();
			while ( iter.hasNext() )
			{
				String condRelId = (String)iter.next();
				if ( hasFirst )
				{
					buf.append( "," ); 
				}
				buf.append( condRelId ); 
				hasFirst = true;
			}
			buf.append( ")" );
		}

		return buf.toString();
	}
	public String getOrderConditionValues (IEvent anEvent){
		
		GetSupervisionOrderConditionValuesEvent evt = (GetSupervisionOrderConditionValuesEvent) anEvent;
		//Agency and defendantId will never be blank.
		StringBuffer whereClause = new StringBuffer("AGENCYCD = '");
		whereClause.append(evt.getAgencyId()); 
		whereClause.append("'");
		
		whereClause.append(" AND DEFENDANT_ID = '");
		whereClause.append(evt.getDefendantId());
		whereClause.append("'");
		
		if (evt.getCourtId() != null && !evt.getCourtId().equals(PDConstants.BLANK)){
			whereClause.append(" AND COURT_ID = '");
			whereClause.append(evt.getCourtId());
			whereClause.append("'");
		}
		
		if (evt.getOrderStatus() !=  null && !evt.getOrderStatus().equals(PDConstants.BLANK)){
			whereClause.append(" AND ORDERSTATUSCD = '");
			whereClause.append(evt.getOrderStatus());
			whereClause.append("'");
		}
		
		return whereClause.toString();
	}
}
