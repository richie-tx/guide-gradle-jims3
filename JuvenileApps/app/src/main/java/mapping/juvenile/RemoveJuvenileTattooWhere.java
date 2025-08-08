package mapping.juvenile;

import messaging.codetable.GetAllCodesForTheDate;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

public class RemoveJuvenileTattooWhere {
	
    public String getWhereClause(IEvent anEvent)
    {
    	GetAllCodesForTheDate event = (GetAllCodesForTheDate) anEvent;        
        StringBuffer whereClause = new StringBuffer(50);
        //whereClause.append("JUVENILE_ID  LIKE '"+ event.getParentId() +"' AND DATE(ENTRYDATE) = DATE('"+ DateUtil.dateToString(event.getEntryDate(), "yyyy-MM-dd")+"')");
        whereClause.append("JUVENILE_ID  LIKE '"+ event.getParentId() +"' AND (ENTRYDATE) = (SELECT MAX(ENTRYDATE) FROM JIMS2.JCTATTOOS WHERE JUVENILE_ID  LIKE '" +event.getParentId()+"')" );
        return whereClause.toString();
    }

}
