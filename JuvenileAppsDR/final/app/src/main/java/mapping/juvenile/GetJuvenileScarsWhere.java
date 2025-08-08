package mapping.juvenile;

import messaging.juvenile.GetTattoosScarsPhysicalAttributesEvent;
import mojo.km.messaging.IEvent;

public class GetJuvenileScarsWhere {
    public String getWhereClause(IEvent anEvent)
    {
    	GetTattoosScarsPhysicalAttributesEvent event = (messaging.juvenile.GetTattoosScarsPhysicalAttributesEvent) anEvent;        
        StringBuffer whereClause = new StringBuffer(50);   
        String juvenileId = event.getJuvenileNum();
        whereClause.append("JUVENILE_ID  LIKE '"+ juvenileId +"' AND ENTRYDATE = (SELECT MAX(ENTRYDATE) FROM JIMS2.JCSCARSMARKS WHERE JUVENILE_ID  LIKE '"+juvenileId+ "')");
        return whereClause.toString();
    }

}
