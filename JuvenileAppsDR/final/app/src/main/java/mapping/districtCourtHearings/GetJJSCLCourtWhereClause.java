package mapping.districtCourtHearings;

import messaging.districtCourtHearings.GetJJSCourtByDateRangeEvent;
import mojo.km.messaging.IEvent;
/**
 * 81390 changes
 * @author sthyagarajan
 *
 */
public class GetJJSCLCourtWhereClause
{

    public String getJJSCLCourtByDateRangeClause(IEvent anEvent)
    {
	GetJJSCourtByDateRangeEvent dateRange = (GetJJSCourtByDateRangeEvent) anEvent;
	
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
