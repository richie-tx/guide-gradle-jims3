package mapping.criminal;

import messaging.codetable.GetAttorneyNameAndBarNumEvent;
import mojo.km.messaging.IEvent;

public class SearchAttorneyNameWhereClause
{

    public String getAttorneyNameClause(IEvent anEvent)
    {
	GetAttorneyNameAndBarNumEvent attorneyEvent = (GetAttorneyNameAndBarNumEvent) anEvent;
	String attorneyName = attorneyEvent.getAttorneyName();
	String barNum = attorneyEvent.getBarNum();
	
	
	StringBuffer buf = new StringBuffer();
	if(attorneyName != null && !attorneyName.trim().equals("")){
        	if (attorneyName.trim().indexOf("*") != -1)
        	{
        	    buf.append("ATTYNAME LIKE UPPER('" + attorneyName.trim().replace('*', '%') + "%')");
        	} else if (!"*".equals(attorneyName))
        	{
        	    buf.append("ATTYNAME LIKE UPPER('" +attorneyName.trim() + "%')");
        	} else
        	{
        	    buf.append("ATTYNAME LIKE '%'");
        	}
	}else{
	    if(barNum!=null && !barNum.trim().equals("")){
		  buf.append("BARNUM="+barNum);
	    }
	}

	return buf.toString();
    }

}
