package mapping.supervision;

import messaging.supervisionorder.GetUnfinishedOrdersEvent;
import mojo.km.messaging.IEvent;

public class GetUnfinishedOrdersWhereClause {
	
	public String getUnfinishedOrders(IEvent event)
	{
		GetUnfinishedOrdersEvent evt = (GetUnfinishedOrdersEvent) event;
	    StringBuffer buf = new StringBuffer();
	    buf.append("AGENCYCD = '");
	    buf.append(evt.getAgencyId());
	    buf.append("'");

		String strVal = evt.getCourtId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND CURRENTCOURT_ID LIKE '");
			buf.append(strVal);
			buf.append("'");
		}
		strVal = evt.getCriminalCaseId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND CRIMINALCASE_ID = '");
			buf.append(strVal);
			buf.append("'");
		}
		
		buf.append(" AND ORDERSTATUSCD IN ('N','D','P')");

		return buf.toString();
	}
}
