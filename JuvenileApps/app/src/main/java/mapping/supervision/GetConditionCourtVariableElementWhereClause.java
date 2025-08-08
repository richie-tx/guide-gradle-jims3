/*
 * Created on Jan 8, 2007
 *
 */
package mapping.supervision;

import java.util.Collection;
import java.util.Iterator;

import messaging.supervisionoptions.GetConditionCourtVariableElementsEvent;
import mojo.km.messaging.IEvent;

/**
 * @author asrvastava
 */
public class GetConditionCourtVariableElementWhereClause {

	public String getClause( IEvent anEvent )
	{
	    GetConditionCourtVariableElementsEvent evt = (GetConditionCourtVariableElementsEvent)anEvent;
		StringBuffer buf = new StringBuffer();

		//agency
		String agencyId = evt.getAgencyId(); 
		if ( agencyId != null && agencyId.trim().length() > 0 )
		{
			buf.append( "AGENCY_ID = '" ); 
			buf.append( agencyId ); 
			buf.append( "'" );
		}
		
		//court
		String courtId = evt.getCourtId(); 
		if ( courtId != null && courtId.trim().length() > 0 )
		{
			buf.append( " and COURT_ID = '" ); 
			buf.append( courtId ); 
			buf.append( "'" );
		}

		// Conditions
		Collection conditionIds = evt.getConditionIds(); 
		if ( conditionIds.size() > 0 )
		{
			buf.append( " and CONDITION_ID in(" ); 
			boolean hasFirst = false;
			Iterator iter = conditionIds.iterator();
			while ( iter.hasNext() )
			{
				String conditionId = (String)iter.next();
				if ( hasFirst )
				{
					buf.append( "," ); 
				}
				buf.append( conditionId ); 
				hasFirst = true;
			}
			buf.append( ")" );
		}
		
		return buf.toString();
	}
    
}
