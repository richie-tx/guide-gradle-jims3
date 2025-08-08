/*
 * Created on May 09, 2008
 *
 */
package mapping.juvenilecase;

import java.util.Date;
import messaging.casefile.GetJournalEntriesEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author ugopinath
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetJournalWhereClause {

	String datePattern = new String("yyyy-MM-dd hh:mm:ss:000");	
	String datePatternStart = new String("yyyy-MM-dd HH:mm:ss:000");
	String datePatternEnd = new String("yyyy-MM-dd HH:mm:ss:999");

	
	public String getRiskAnalysisJournalClause( IEvent anEvent )
	{	
		GetJournalEntriesEvent evt = (GetJournalEntriesEvent)anEvent;
		StringBuffer buf = getQueryString(evt);	
		Date startDate = evt.getFromDate();
		Date endDate = evt.getEndDate();
		String juvId = evt.getJuvenileId();	
		
		if ( buf != null ) {
			if (juvId!= null && !juvId.equals("") )
			{
				buf.append( " and JUVENILE_ID LIKE '" ); 
				buf.append(juvId); 
				buf.append( "'" ); 
				
			}
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
				
				buf.append( " and DATEENTERED between '" ); 
				buf.append(DateUtil.dateToString(startDate, datePatternStart));  
				buf.append( "' and '" ); 
				buf.append(DateUtil.dateToString(endDate, datePatternEnd));
				buf.append( "'" ); 
			}
		}
		
		return buf.toString();
	}
	
	public String getJournalClause( IEvent anEvent )
	{	
		GetJournalEntriesEvent evt = (GetJournalEntriesEvent)anEvent;
		StringBuffer buf = getQueryString(evt);	
		Date startDate = evt.getFromDate();
		Date endDate = evt.getEndDate();		
	
		if ( buf != null ) {
			if ( startDate != null && !startDate.equals("") && endDate != null && !endDate.equals("") )
			{
				startDate.setHours(00);
				startDate.setMinutes(00);
				startDate.setSeconds(00);
								
				endDate.setHours(23); 
				endDate.setMinutes(59);
				endDate.setSeconds(59);
				
				buf.append( " and CREATEDATE between '" ); 
				buf.append(DateUtil.dateToString(startDate, datePatternStart));  
				buf.append( "' and '" ); 
				buf.append(DateUtil.dateToString(endDate, datePatternEnd));
				buf.append( "'" ); 
			}
		}
		
		return buf.toString();
	}
	
	public String getGoalJournalClause( IEvent anEvent )
	{	
		GetJournalEntriesEvent evt = (GetJournalEntriesEvent)anEvent;
		StringBuffer buf = getQueryString(evt);
		Date startDate = evt.getFromDate();
		Date endDate = evt.getEndDate();		
	
		if ( buf != null ) {
			if ( startDate != null && !startDate.equals("") && endDate != null && !endDate.equals("") )
			{
				startDate.setHours(00);
				startDate.setMinutes(00);
				startDate.setSeconds(00);
								
				endDate.setHours(23); 
				endDate.setMinutes(59);
				endDate.setSeconds(59);
				
				buf.append( " and ENDRCMNDTNDATE between '" ); 
				buf.append(DateUtil.dateToString(startDate, datePatternStart));  
				buf.append( "' and '" ); 
				buf.append(DateUtil.dateToString(endDate, datePatternEnd));
				buf.append( "'" ); 
			}
		}
		
		return buf.toString();
	}

	public String getCalEventJournalClause( IEvent anEvent )
	{	
		GetJournalEntriesEvent evt = (GetJournalEntriesEvent)anEvent;
		String strCasefileId = this.escape(evt.getCasefileId());
		StringBuffer buf = new StringBuffer();		
		String user = evt.getUserId();	
		Date startDate = evt.getFromDate();
		Date endDate = evt.getEndDate();	
		
		if ( strCasefileId != null && strCasefileId.trim().length() > 0 )
		{
			buf.append( " CASEFILE_ID = " ); 
			buf.append( strCasefileId ); 
			buf.append( "" ); 
		}
		
		if ( buf != null ) {
			if (user!= null && !user.equals("") )
			{
				buf.append( " and CREATEUSER LIKE '" ); 
				buf.append(user.toUpperCase()); 
				buf.append( "'" ); 
				
			}
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
				
				buf.append( " and startDatetime between '" ); 
				buf.append(DateUtil.dateToString(startDate, datePatternStart));  
				buf.append( "' and '" ); 
				buf.append(DateUtil.dateToString(endDate, datePatternEnd));
				buf.append( "'" ); 
			}
		}
		
		return buf.toString();
	}
	public String getTraitJournalClause( IEvent anEvent )
	{	
		GetJournalEntriesEvent evt = (GetJournalEntriesEvent)anEvent;
		String strCasefileId = this.escape(evt.getCasefileId());
		StringBuffer buf = new StringBuffer();
		Date startDate = evt.getFromDate();
		Date endDate = evt.getEndDate();
		String user = evt.getUserId();	
		if ( strCasefileId != null && strCasefileId.trim().length() > 0 )
		{
			buf.append( " CASEFILE_ID LIKE '" ); 
			buf.append( strCasefileId ); 
			buf.append( "'" ); 
		}
	
		if ( buf != null ) {
			if (user!= null && !user.equals("") )
			{
				buf.append( " and CREATEUSER LIKE '" ); 
				buf.append(user.toUpperCase()); 
				buf.append( "'" ); 
				
			}
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
				
				buf.append( " and CREATEDATE between '" ); 
				buf.append(DateUtil.dateToString(startDate, datePatternStart));  
				buf.append( "' and '" ); 
				buf.append(DateUtil.dateToString(endDate, datePatternEnd));
				buf.append( "'" ); 
			}
		}
		return buf.toString();
	}
	
	/**
	 * retrieve MAYSI 2 records for the Case Review Journal Summary Report
	 * @param anEvent
	 * @return
	 */
	public String getMAYSIJournalSummaryClause( IEvent anEvent )
	{	
		GetJournalEntriesEvent evt = (GetJournalEntriesEvent)anEvent;
		Date startDate = evt.getFromDate();
		Date endDate = evt.getEndDate();
		String juvId = evt.getJuvenileId();	
		String referralNum = evt.getReferralNumber();
		StringBuffer buf = new StringBuffer();
		if ( buf != null ) {
			if (juvId!= null && !juvId.equals("") )
			{
				buf.append( " JUVENILE_ID LIKE '" ); 
				buf.append(juvId); 
				buf.append( "'" ); 
				
			}
			if (referralNum!= null && !referralNum.equals("") )
			{
				buf.append( " and REFERRALNUMBER LIKE '" ); 
				buf.append(referralNum); 
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
	
	private StringBuffer getQueryString(GetJournalEntriesEvent evt)
	{
		StringBuffer buf = new StringBuffer();
		String strCasefileId = this.escape(evt.getCasefileId());
		String user = evt.getUserId();			

		if ( strCasefileId != null && strCasefileId.trim().length() > 0 )
		{
			buf.append( " CASEFILE_ID = " ); 
			buf.append( strCasefileId ); 
		}
		
		if ( buf != null ) {
			if (user!= null && !user.equals("") )
			{
				buf.append( " and CREATEUSER LIKE '" ); 
				buf.append(user.toUpperCase()); 
				buf.append( "'" ); 
				
			}
		}
		return buf;
	}
}
