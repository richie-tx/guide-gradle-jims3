package mapping.juvenile;

import messaging.juvenile.ValidateJuvSSNNameEvent;
import mojo.km.messaging.IEvent;

public class ValidateJuvSSNNameWhereClause
{

    public String validateJuvSSNNameClause(IEvent anEvent)
    {
	StringBuffer buf = new StringBuffer(200);
	if (anEvent instanceof ValidateJuvSSNNameEvent)
	{
	    ValidateJuvSSNNameEvent validateJuvSSNNameEvt = (ValidateJuvSSNNameEvent) anEvent;
	  
	   /* if (validateJuvSSNNameEvt.getLastName() != null && !"".equals(validateJuvSSNNameEvt.getLastName()))
	    {
		buf.append("JUVENILELNAME LIKE '");
		buf.append(validateJuvSSNNameEvt.getLastName().trim());
		buf.append("%'");
	    }
	    if (validateJuvSSNNameEvt.getFirstName() != null && !"".equals(validateJuvSSNNameEvt.getFirstName()))
	    {
		if(validateJuvSSNNameEvt.getLastName() == null)
		    buf.append("JUVENILEFNAME = '");
		else
		    buf.append(" AND JUVENILEFNAME = '");
		
		buf.append(validateJuvSSNNameEvt.getFirstName().trim());
		buf.append("'");
	    }
	    if (validateJuvSSNNameEvt.getJuvRecType() != null && !"".equals(validateJuvSSNNameEvt.getJuvRecType()))
	    {
		if(validateJuvSSNNameEvt.getLastName() == null && validateJuvSSNNameEvt.getFirstName()==null)
		    buf.append("RECTYPE = '");
		else
		    buf.append(" AND RECTYPE = '");
		
		buf.append("JUVENILE");
		buf.append("'");
	    }*/
	    if (validateJuvSSNNameEvt.getSsn() != null && !"".equals(validateJuvSSNNameEvt.getSsn()))
	    {
		buf.append("JUVENILESSN = '");
		buf.append(validateJuvSSNNameEvt.getSsn().trim());
		buf.append("'");
	    }
	}
	return buf.toString();
    }
}
