package mapping.detentionCourtHearings;

import messaging.detentionCourtHearings.GetJJSCLDetentionByDateRangeEvent;
import mojo.km.messaging.IEvent;

/**
 * 
 * @author sthyagarajan
 * added for user-story 81390
 *
 */
public class GetJJSCLDetentionWhereClause
{
    /**
     * 
     * Retrieve records within the date range.
     * 
     * @param anEvent
     * @return
     */
    public String getJJSCLDetentionByDateRangeClause(IEvent anEvent)
    {
	GetJJSCLDetentionByDateRangeEvent dateRange = (GetJJSCLDetentionByDateRangeEvent) anEvent;
	
	StringBuffer whereClause = new StringBuffer(200);
	String startDate = dateRange.getStartDate();
	String endDate = dateRange.getEndDate();
	String juvenileNum =dateRange.getJuvenileNumber();
	
	if (startDate != null  && endDate != null && juvenileNum!=null )
	{
	    whereClause.append("JUVENILENUM = '");

	    whereClause.append(juvenileNum);

	    whereClause.append("' AND COURTDATE BETWEEN '");

	    whereClause.append(startDate);
	    
	    whereClause.append("' AND '");
	    
	    whereClause.append(endDate);
	    
	    whereClause.append("'");
	}
	return whereClause.toString();
    }
}
