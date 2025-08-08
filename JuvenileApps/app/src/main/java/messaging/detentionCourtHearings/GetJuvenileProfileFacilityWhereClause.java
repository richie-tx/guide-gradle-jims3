/* 
 */
package messaging.detentionCourtHearings;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mojo.km.messaging.IEvent;

public class GetJuvenileProfileFacilityWhereClause
{

    public String getJJSCLDetentionByDetRecResult(IEvent anEvent)
    {
	StringBuffer buf = new StringBuffer(200);

	if (anEvent instanceof GetJJSCLDetentionByDetRecResultEvent)
	{
	    GetJJSCLDetentionByDetRecResultEvent facilityInfo = (GetJJSCLDetentionByDetRecResultEvent) anEvent;

	    String detentionID = facilityInfo.getDetentionId();
	    HashMap hearingResults = facilityInfo.getHearingResults();

	    //JJS_DETENTION_ID = ? AND HEARINGRESULT in 

	    buf.append("JJS_DETENTION_ID = ");
	    buf.append(detentionID.trim());

	    if ( hearingResults != null )
	    {
		int size = hearingResults.size() -1;
		buf.append(" AND ");
		buf.append("HEARINGRESULT IN ").append("(");
		
		Iterator<Map.Entry<Integer, String>> iter = hearingResults.entrySet().iterator();
		while(iter.hasNext()){
		    
		    Map.Entry<Integer, String> values = iter.next();
		    buf.append("'").append(values.getValue() ).append("'");
		    if( size >0 ){
			buf.append(",");
			size --;
		    }		    
		}
	    }
	}
	buf.append(")");
	return buf.toString();
    }

}
