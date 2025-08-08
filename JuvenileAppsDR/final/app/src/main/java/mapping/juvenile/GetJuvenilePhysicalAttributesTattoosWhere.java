package mapping.juvenile;

import messaging.codetable.GetCodesEvent;
import mojo.km.messaging.IEvent;

public class GetJuvenilePhysicalAttributesTattoosWhere
{
    public String getWhereClause(IEvent anEvent)
    {
        GetCodesEvent event = (GetCodesEvent) anEvent;        
        StringBuffer whereClause = new StringBuffer(50);   
        String[] juvenileId = event.getCodes();
        whereClause.append("JUVENILE_ID  LIKE '"+ juvenileId[0] +"' AND ENTRYDATE = (SELECT MAX (ENTRYDATE) FROM JIMS2.JCTATTOOS WHERE JUVENILE_ID  LIKE '"+juvenileId[0]+ "')");
        return whereClause.toString();
    }
}
