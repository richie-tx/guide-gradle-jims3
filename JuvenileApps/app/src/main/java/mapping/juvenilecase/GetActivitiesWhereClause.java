/*
 * Created on Sep 27, 2006
 *
 */
package mapping.juvenilecase;

import java.util.Date;
import messaging.casefile.GetActivitiesEvent;
import messaging.casefile.GetJournalEntriesEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetActivitiesWhereClause {

	String datePattern = new String("yyyy-MM-dd hh:mm:ss:000");
	String datePatternStart = new String("yyyy-MM-dd HH:mm:ss:000");
	String datePatternEnd = new String("yyyy-MM-dd HH:mm:ss:999");
	String activityTimePattern = new String("hh:mm a");
	String activityTime24Pattern = new String ("HH:mm");

	public String getActivitiesClause( IEvent anEvent )
	{
		GetActivitiesEvent evt = (GetActivitiesEvent)anEvent;
		StringBuffer buf = new StringBuffer();

		String strCasefileId = this.escape(evt.getCasefileID());
		String strActivityCdId = "";
		Date   activityTime    = null;
		if ( evt.getActivityTime() != null ) {
		    activityTime	=  DateUtil.stringToDate(evt.getActivityTime(), activityTimePattern);
		}

		if (evt.getActivityCodeId() != null) {
			strActivityCdId = this.escape(evt.getActivityCodeId());
		}
		Date startDate = evt.getStartDate();
		Date endDate = evt.getEndDate();

		if ( strCasefileId != null && strCasefileId.trim().length() > 0 )
		{
			buf.append( " CASEFILE_ID = " ); 
			buf.append( strCasefileId ); 
		}
		
		if ( activityTime != null ) {
		    buf.append( " and ACTIVITYTIME = '" );
		    buf.append( DateUtil.dateToString(activityTime, activityTime24Pattern) );
		    buf.append( "' " );
		}

		if ( strActivityCdId != null && strActivityCdId.trim().length() > 0) {
			buf.append( " and ACTIVITYCD LIKE '"); 
			buf.append(strActivityCdId.toUpperCase()); 
			buf.append( "' " );			
		}

		if ( buf != null ) {
			if ( startDate != null && endDate != null )
			{
				startDate.setHours(00);
				startDate.setMinutes(00);
				startDate.setSeconds(00);
								
				endDate.setHours(23); 
				endDate.setMinutes(59);
				endDate.setSeconds(59);
				
				buf.append( " and ACTIVITYDATE between '" ); 
				buf.append( DateUtil.dateToString(startDate, datePatternStart) ); 
				buf.append( "' and '" ); 
				buf.append( DateUtil.dateToString(endDate, datePatternEnd) );
				buf.append( "'" ); 
			}
		}
		return buf.toString();
	}

	public String getActivitiesJournalClause( IEvent anEvent )
	{
		GetJournalEntriesEvent evt = (GetJournalEntriesEvent)anEvent;
		StringBuffer buf = new StringBuffer();

		String strCasefileId = this.escape(evt.getCasefileId());
		String user = evt.getUserId();		
		Date startDate = evt.getFromDate();
		Date endDate = evt.getEndDate();

		if ( strCasefileId != null && strCasefileId.trim().length() > 0 )
		{
			buf.append( " CASEFILE_ID = " ); 
			buf.append( strCasefileId ); 
		}

	
		if ( buf != null ) {
			if ( startDate != null && !startDate.equals("") && endDate != null && !endDate.equals("") )
			{
				startDate.setHours(00);
				startDate.setMinutes(00);
				startDate.setSeconds(00);
								
				endDate.setHours(23); 
				endDate.setMinutes(59);
				endDate.setSeconds(59);
				
				buf.append( " and ACTIVITYDATE between '" ); 
				buf.append(DateUtil.dateToString(startDate, datePatternStart));  
				buf.append( "' and '" ); 
				buf.append(DateUtil.dateToString(endDate, datePatternEnd));
				buf.append( "'" ); 
			}
		}
		if ( buf != null ) {
			if (user!= null && !user.equals("") )
			{
				buf.append( " and CREATEUSER LIKE '" ); 
				buf.append(user.toUpperCase()); 
				buf.append( "'" ); 
				
			}
		}
		return buf.toString();
	}

	public String escape(String str){
		StringBuffer sb = new StringBuffer();				
		for (int i=0;i<str.length();i++){			 
			if (str.charAt(i)=='\''){
				sb.append("\'\'");
			}else{
				sb.append(str.charAt(i));
			}
		}			    
	    return sb.toString();
	}
}
