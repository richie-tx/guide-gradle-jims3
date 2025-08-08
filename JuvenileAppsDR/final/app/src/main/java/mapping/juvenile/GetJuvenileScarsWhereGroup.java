package mapping.juvenile;

import messaging.juvenile.GetTattoosScarsPhysicalAttributesEventGroup;
import mojo.km.messaging.IEvent;

public class GetJuvenileScarsWhereGroup {
    public String getWhereClause(IEvent anEvent)
    {
    	GetTattoosScarsPhysicalAttributesEventGroup event = (messaging.juvenile.GetTattoosScarsPhysicalAttributesEventGroup) anEvent;        
        StringBuffer whereClause = new StringBuffer(50);   
        String juvenileId = event.getJuvenileNum();
        whereClause.append("JUVENILE_ID  LIKE '"+ juvenileId +"' AND ENTRYDATE >= DATEADD(second,-5,(SELECT MAX(ENTRYDATE) FROM JIMS2.JCSCARSMARKS WHERE JUVENILE_ID  LIKE '"+juvenileId+ "'))");
        return whereClause.toString();
    }

}
