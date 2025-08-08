package mapping.juvenile;

import messaging.codetable.GetAllCodesForTheDate;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

public class RemoveJuvenileScarsWhere {

	   public String getWhereClause(IEvent anEvent)
	    {
		   messaging.codetable.GetAllScarCodesForDate event = (messaging.codetable.GetAllScarCodesForDate) anEvent;        
	        StringBuffer whereClause = new StringBuffer(50);
	        whereClause.append("JUVENILE_ID  LIKE '"+ event.getParentId() +"' AND (ENTRYDATE) = (SELECT MAX(ENTRYDATE) FROM JIMS2.JCSCARSMARKS WHERE JUVENILE_ID  LIKE '" +event.getParentId()+"')" );
	        return whereClause.toString();
	    }
}
