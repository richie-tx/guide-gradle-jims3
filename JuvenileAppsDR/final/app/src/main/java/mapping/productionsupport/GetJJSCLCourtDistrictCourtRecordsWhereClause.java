/*
 * Created on Sep 27, 2006
 *
 */
package mapping.productionsupport;

import messaging.productionsupport.GetProductionSupportCourtRecordsEvent;
import mojo.km.messaging.IEvent;

/**
 * @author aPillai
 * 
 */
public class GetJJSCLCourtDistrictCourtRecordsWhereClause {


	public String GetJJSCLCourtDistrictCourtRecordsClause( IEvent anEvent )
	{
	    GetProductionSupportCourtRecordsEvent evt = (GetProductionSupportCourtRecordsEvent)anEvent;
	    StringBuffer whereClause = new StringBuffer(200);		
		String juvenileNum =evt.getJuvenileNumber();
		//String courtId = evt.getCourtId();
		String courtDate = evt.getCourtDate();
		
		if (juvenileNum!=null && juvenileNum.trim().length() > 0 )
		{
		    whereClause.append("JUVENILENUM = '");
		    whereClause.append(juvenileNum + "'");
		}
		/*if (courtId!=null && courtId.trim().length() > 0 )
		{
		    whereClause.append(" AND COURTID = '");
		    whereClause.append(courtId + "'");
		}*/
	if (courtDate != null && courtDate.trim().length() > 0)
	{
	    whereClause.append(" AND COURTDATE = '");
	    whereClause.append(courtDate + "'");
	}	
		
		return whereClause.toString();
	}
}
