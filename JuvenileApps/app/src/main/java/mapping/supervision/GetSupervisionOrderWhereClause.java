/*
 * Created on Oct 31, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.supervision;

import java.util.Collection;
import java.util.Iterator;

import messaging.supervisionorder.GetOrdersForSupervisionPeriodIdsEvent;
import mojo.km.messaging.IEvent;

/**
 * @author asrvastava
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetSupervisionOrderWhereClause {

	public String getOrdersByPeriodIdsClause(IEvent anEvent)
	{
	    GetOrdersForSupervisionPeriodIdsEvent evt = (GetOrdersForSupervisionPeriodIdsEvent)anEvent;
		StringBuffer buf = new StringBuffer();

		// RelIds
		Collection periodIds = evt.getPeriodIds(); 
		if (periodIds.size() > 0)
		{
			buf.append( "SPRVSIONPERIOD_ID in(" ); 
			boolean hasFirst = false;
			Iterator iter = periodIds.iterator();
			while ( iter.hasNext() )
			{
				String periodId = (String)iter.next();
				if ( hasFirst )
				{
					buf.append( "," ); 
				}
				buf.append( periodId ); 
				hasFirst = true;
			}
			buf.append( ")" );
		}

		return buf.toString();
	}

    
}
