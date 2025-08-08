package mapping.juvenile;

import messaging.codetable.GetCodesEventGroup;
import mojo.km.messaging.IEvent;

public class GetJuvenilePhysicalAttributesTattoosWhereGroup
{
    public String getWhereClause(IEvent anEvent)
    {
    	GetCodesEventGroup event = (GetCodesEventGroup) anEvent;        
        StringBuffer whereClause = new StringBuffer(50);   
        String[] juvenileId = event.getCodes();
        whereClause.append("JUVENILE_ID  LIKE '"+ juvenileId[0] +"' AND ENTRYDATE >= DATEADD(second,-5,(SELECT MAX(ENTRYDATE) FROM JIMS2.JCTATTOOS WHERE JUVENILE_ID  LIKE '"+juvenileId[0]+ "'))");
        return whereClause.toString();
    }
}