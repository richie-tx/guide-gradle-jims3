/*
 * Created on Feb 8, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.cscdcalendar;

import java.text.SimpleDateFormat;

import messaging.cscdcalendar.GetCSFVItineraryDetailsEvent;
import mojo.km.messaging.IEvent;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FieldVisitRetriever {
	public String getSearchFieldVisitItinerary(IEvent anEvent) {
		GetCSFVItineraryDetailsEvent mEvent = (GetCSFVItineraryDetailsEvent) anEvent;
		String beginWhereClause = " ";
		SimpleDateFormat dfmt = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer sql = new StringBuffer();
		String and = " ";
		if (mEvent.getItineraryDate() != null && mEvent.getPositionId() != null && !mEvent.getPositionId().equals("")) {
			String itineraryDate = dfmt.format(mEvent.getItineraryDate());
			sql.append("POSITION_ID= " + mEvent.getPositionId());
			sql.append(" AND ");
			sql.append("DATE(ITINERARYDATE)= '" + itineraryDate + "'");
		}
		return sql.toString();
	}
}
