/*
 * Created on Sep 27, 2006
 *
 */
package mapping.productionsupport;

import messaging.productionsupport.GetProductionSupportEventTasksEvent;
import mojo.km.messaging.IEvent;

/**
 * @author rcarter
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetTaskEventsWhereClause {


	public String getTaskEventsClause( IEvent anEvent )
	{
		GetProductionSupportEventTasksEvent evt = (GetProductionSupportEventTasksEvent)anEvent;
		StringBuffer buf = new StringBuffer();

		String strCasefileId = evt.getCasefileId();

		if ( strCasefileId != null && strCasefileId.trim().length() > 0 )
		{
			buf.append( " NOTIFICATION_EVENT LIKE '%"); 
			buf.append( strCasefileId ); 
			buf.append( "%'" );	
		}
		return buf.toString();
	}
}
