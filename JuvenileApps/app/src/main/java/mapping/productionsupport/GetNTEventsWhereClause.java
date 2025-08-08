/*
 * Created on Sep 27, 2006
 *
 */
package mapping.productionsupport;

import messaging.productionsupport.GetProductionSupportNTTasksEvent;
import mojo.km.messaging.IEvent;

/**
 * @author rcarter
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetNTEventsWhereClause {


	public String getNTEventsClause( IEvent anEvent )
	{
		GetProductionSupportNTTasksEvent evt = (GetProductionSupportNTTasksEvent)anEvent;
		StringBuffer buf = new StringBuffer();

		String strCasefileId = evt.getCasefileId();

		if ( strCasefileId != null && strCasefileId.trim().length() > 0 )
		{
			buf.append( " TASKSUBJECT LIKE '%"); 
			buf.append( strCasefileId ); 
			buf.append( "%'" );	
		}
		return buf.toString();
	}
}
