package mapping.administercaseload;

import messaging.administercaseload.GetInOutActivityViewEvent;
import mojo.km.messaging.IEvent;

public class CaseloadInOutActivityViewWhereClause 
{
	
	public String getInOutActivityClause( IEvent anEvent )
	{
    	GetInOutActivityViewEvent in_out_activity_event = 
            							(GetInOutActivityViewEvent)anEvent;

    		//retrieve properties of event
        String inOutVal = in_out_activity_event.getInOut();

        //build where clause
        StringBuffer where_clause = new StringBuffer();
		where_clause.append("INOUT = " );
		where_clause.append("'" + inOutVal + "'");
		where_clause.append(" AND DEFENDANT_ID = " );
		where_clause.append("'" + in_out_activity_event.getDefendantId() + "'" );
		where_clause.append(" AND CRIMINALCASE_ID = " );
		where_clause.append("'" + in_out_activity_event.getCriminalCaseId()+ "'" );
		where_clause.append(" AND ENDDATE IS NULL " );
		
        //return where clause
        return where_clause.toString();
	}//end of getInOutActivityClause()
}
