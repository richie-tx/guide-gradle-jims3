/*
 * Created on Oct 5, 2006
 *
 */
package mapping.supervision;

import java.util.Collection;
import java.util.Iterator;

import messaging.supervisionorder.GetOrderValuesFromRelIdsEvent;
import mojo.km.messaging.IEvent;

/**
 * @author asrvastava
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetSupervisionOrderValuesWhereClause {

	public String getByRelIdsClause( IEvent anEvent )
	{
		GetOrderValuesFromRelIdsEvent evt = (GetOrderValuesFromRelIdsEvent)anEvent;
		StringBuffer buf = new StringBuffer();

		// RelIds
		Collection conditionRelIds = evt.getRelIds(); 
		if (conditionRelIds.size() > 0)
		{
			buf.append( "SPRVNORDRCNDRL_ID in(" ); 
			boolean hasFirst = false;
			Iterator iter = conditionRelIds.iterator();
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

}
