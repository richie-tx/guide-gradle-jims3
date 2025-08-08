/*
 * Created on Sep 12, 2005
 */
package mapping.supervision;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import naming.PDCodeTableConstants;
import messaging.supervisionoptions.GetByIdsEvent;
import messaging.supervisionoptions.GetCourtPoliciesEvent;
import mojo.km.messaging.IEvent;

/**
 * @author bschwartz
 *
 */
public class GetCourtPoliciesWhereClause
{
	private static final SimpleDateFormat dateFormatWithMillis = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public String getPoliciesByIdsClause(IEvent anEvent)
	{
	    GetByIdsEvent evt = (GetByIdsEvent)anEvent;
		StringBuffer buf = new StringBuffer();

		Collection policyIds = evt.getOids(); 
		if (policyIds.size() > 0)
		{
			buf.append( "COURTPOLICY_ID in(" ); 
			boolean hasFirst = false;
			Iterator iter = policyIds.iterator();
			while ( iter.hasNext() )
			{
				String oid = (String)iter.next();
				if ( hasFirst )
				{
					buf.append( "," ); 
				}
				buf.append( oid ); 
				hasFirst = true;
			}
			buf.append( ")" );
		}
		return buf.toString();
	}
	private static int ZERO = 0;
	private static int FIFTY_NINE = 59;
	private static int NINES = 999;
	private static int TWENTY_THREE = 23;

	public String getClause( IEvent anEvent )
	{
		GetCourtPoliciesEvent evt = (GetCourtPoliciesEvent)anEvent;
		StringBuffer buf = new StringBuffer();

		// Name
		// Note: The NAME clause is always used so that other clauses do not have to
		// check for a previous clause to know if an 'and' is required. This also gets 
		// around an issue in the JDBCMapping class that requires this method to return 
		// none-null. -Blake, 9/15/05    
		String strVal = evt.getName(); 
		if ( strVal != null && strVal.trim().length() > 0 )
		{
			strVal = this.formatApostrophe(strVal, true);
			strVal = strVal.toUpperCase();
			strVal = strVal.replace('*', '%');
			buf.append( "NAME like '" ); 
			buf.append( strVal ); 
			buf.append( "'" );
		}
		else
		{
			buf.append( "NAME like '%'" ); 
		}
		
		// Agency
		strVal = evt.getAgencyId(); 
		if ( strVal != null && strVal.trim().length() > 0 )
		{
			buf.append( " and AGENCY_ID = " ); 
			buf.append( "'" + strVal + "'" ); 
		}
		
//		// description
//		strVal = evt.getDescription(); 
//		if ( strVal != null && strVal.trim().length() > 0 )
//		{
//			buf.append( " and DESCRIPTION LIKE " ); 
//			buf.append( "'%" + strVal + "%'" ); 
//		}

		// Group1
		strVal = evt.getGroup1(); 
		if ( strVal != null && strVal.trim().length() > 0 )
		{
			buf.append( " and GROUP1 = " ); 
			buf.append( strVal ); 
		}
		
		// Group2
		strVal = evt.getGroup2(); 
		if ( strVal != null && strVal.trim().length() > 0 )
		{
			if(evt.isSearchForAssociation()){ // search for Association
				buf.append( " and (GROUP2 = 0 or GROUP2 = " ); 
			}else{
				buf.append( " and (GROUP2 = " ); 
			}
			buf.append( strVal ); 
			buf.append( ")" );
		}
		
		// Group3
		strVal = evt.getGroup3(); 
		if ( strVal != null && strVal.trim().length() > 0 )
		{
			if(evt.isSearchForAssociation()){ // search for Association
				buf.append( " and (GROUP3 = 0 or GROUP3 = " );
			}else{
				buf.append( " and (GROUP3 = " );
			}
			buf.append( strVal ); 
			buf.append( ")" );
		}

		// isStandard
		if(evt.isStandardSelected()){
			buf.append( " and ISSTANDARD = " ); 
			buf.append( evt.isStandard() ? "1" : "0"); 
		}
		Calendar calendar = null;
		// Effective Date
		Date dateVal = evt.getEffectiveDate();
		String dateStr = null;
		if ( dateVal != null )
		{
			calendar = Calendar.getInstance();
			calendar.setTime(dateVal);
            calendar.set(Calendar.HOUR_OF_DAY,ZERO);
            calendar.clear(Calendar.MINUTE);
            calendar.clear(Calendar.SECOND);
            calendar.clear(Calendar.MILLISECOND);
			buf.append(" and EFFECTIVEDATE = '");
			dateStr = dateFormatWithMillis.format(calendar.getTime());
			buf.append(dateStr);
			buf.append("'");
		}
		
		// Inactive Date
		dateVal = evt.getInactiveDate();
		if ( dateVal != null )
		{
			calendar = Calendar.getInstance();
			calendar.setTime(dateVal);
            calendar.set(Calendar.HOUR_OF_DAY,ZERO);
            calendar.clear(Calendar.MINUTE);
            calendar.clear(Calendar.SECOND);
            calendar.clear(Calendar.MILLISECOND);
            
            buf.append(" and INACTIVEDATE BETWEEN '");
            dateStr = dateFormatWithMillis.format(calendar.getTime());
			buf.append(dateStr);
			buf.append("'");
			
			calendar.set(Calendar.HOUR_OF_DAY, TWENTY_THREE);
			calendar.set(Calendar.MINUTE, FIFTY_NINE);
			calendar.set(Calendar.SECOND, FIFTY_NINE);
			calendar.set(Calendar.MILLISECOND, NINES);
			
            buf.append(" and '");
            dateStr = dateFormatWithMillis.format(calendar.getTime());
			buf.append(dateStr);
			buf.append("'");
		}
		
		// Courts
		Collection courts = evt.getCourts(); 
		if ( courts.size() > 0 )
		{
			buf.append( " and court_id in('" ); 
			boolean hasFirst = false;
			Iterator iter = courts.iterator();
			while ( iter.hasNext() )
			{
				String courtId = (String)iter.next();
				if ( hasFirst )
				{
					buf.append( "','" ); 
				}
				buf.append( courtId ); 
				hasFirst = true;
			}
			buf.append( "')" );
		}
		
		// Status ID
		if ( evt.getStatus() != null  )
		{
			calendar = Calendar.getInstance();
			if ( evt.getStatus().equals(PDCodeTableConstants.STATUS_CREATED) )
			{
	            calendar.set(Calendar.HOUR_OF_DAY,ZERO);
	            calendar.clear(Calendar.MINUTE);
	            calendar.clear(Calendar.SECOND);
	            calendar.clear(Calendar.MILLISECOND);
				dateStr = dateFormatWithMillis.format(calendar.getTime());
				buf.append(" and EFFECTIVEDATE > '");
				buf.append(dateStr); //current date
				buf.append("'");
			}
			else if ( evt.getStatus().equals(PDCodeTableConstants.STATUS_ACTIVE) )
			{
				calendar.set(Calendar.HOUR_OF_DAY, TWENTY_THREE);
				calendar.set(Calendar.MINUTE, FIFTY_NINE);
				calendar.set(Calendar.SECOND, FIFTY_NINE);
				calendar.set(Calendar.MILLISECOND, NINES);

				dateStr = dateFormatWithMillis.format(calendar.getTime());
				buf.append(" and EFFECTIVEDATE <= '");
				buf.append(dateStr); //current date
				buf.append("' and (INACTIVEDATE IS NULL OR INACTIVEDATE > '");
				buf.append(dateStr);
				buf.append("')");
			}
			else if ( evt.getStatus().equals(PDCodeTableConstants.STATUS_INACTIVE) )
			{
	            calendar.set(Calendar.HOUR_OF_DAY,ZERO);
	            calendar.clear(Calendar.MINUTE);
	            calendar.clear(Calendar.SECOND);
	            calendar.clear(Calendar.MILLISECOND);
				dateStr = dateFormatWithMillis.format(calendar.getTime());
				buf.append(" and INACTIVEDATE < '");
				buf.append(dateStr); //current date
				buf.append("'");
			}
		}
		
		return buf.toString();
	}
	private String formatApostrophe(String value, boolean formatString)
	{
		if (formatString)
		{
			if (value.indexOf("'") >= 0)
			{
				value = value.replace("'", "''");
			}
		}

		return value;
	}
}
