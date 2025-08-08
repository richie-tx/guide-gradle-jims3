/*
 * Created on Sep 12, 2005
 *
 */
package mapping.supervision;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.supervisionoptions.GetSupervisionConditionsByGroup1Event;
import messaging.supervisionoptions.GetSupervisionConditionsByIdsEvent;
import messaging.supervisionoptions.GetSupervisionConditionsEvent;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;

/**
 * @author bschwartz
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetSupervisionConditionsWhereClause
{

	//private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat dateFormatWithMillis = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static int ZERO = 0;
	private static int FIFTY_NINE = 59;
	private static int NINES = 999;
	private static int TWENTY_THREE = 23;

	public String getByIdsClause( IEvent anEvent )
	{
		GetSupervisionConditionsByIdsEvent evt = (GetSupervisionConditionsByIdsEvent)anEvent;
		StringBuffer buf = new StringBuffer();

		// Courts
		Collection conditionIds = evt.getConditionIds();
		if ( conditionIds.size() > 0 )
		{
			buf.append( " CONDITION_ID in(" );
			boolean hasFirst = false;
			Iterator iter = conditionIds.iterator();
			while ( iter.hasNext() )
			{
				String conditionId = (String)iter.next();
				if ( hasFirst )
				{
					buf.append( "," );
				}
				buf.append( conditionId );
				hasFirst = true;
			}
			buf.append( ")" );
		}

		// Status Active
		if(!evt.isSearchInactive()){
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, TWENTY_THREE);
			calendar.set(Calendar.MINUTE, FIFTY_NINE);
			calendar.set(Calendar.SECOND, FIFTY_NINE);
			calendar.set(Calendar.MILLISECOND, NINES);

			String dateStr = dateFormatWithMillis.format(calendar.getTime());
			buf.append(" and EFFECTIVEDATE <= '");
			buf.append(dateStr); //current date
			buf.append("' and (INACTIVEDATE IS NULL OR INACTIVEDATE > '");
			buf.append(dateStr);
			buf.append("')");

		}

		return buf.toString();
	}
	private String removeBadData(String aString, String aBadChar){
    	boolean finished = false;
    	int loc = 0;
    	StringBuffer sb = null;
    	while (!finished){
    		loc = aString.indexOf(aBadChar);
    		if (loc > 0){
    			loc = aString.indexOf(aBadChar);
    			sb = new StringBuffer(aString);
    			sb.replace(loc, loc + 1, "%");
    			aString = sb.toString();
    		} else {
    			finished = true;
    		}
    	}
    	return aString;
    }

	public String getClause( IEvent anEvent )
	{
		GetSupervisionConditionsEvent evt = (GetSupervisionConditionsEvent)anEvent;
		StringBuffer buf = new StringBuffer();

		// Name
		// Note: The NAME clause is always used so that other clauses do not have to
		// check for a previous clause to know if an 'and' is required. This also gets
		// around an issue in the JDBCMapping class that requires this method to return
		// none-null. -Blake, 9/15/05
		String strVal = evt.getName();
		if ( strVal != null && strVal.trim().length() > 0 )
		{
			strVal = strVal.toUpperCase();
			strVal = strVal.replace('*', '%');
			strVal = this.removeBadData(strVal, "'");
			buf.append( "NAME like '%" );
			buf.append( strVal );
			buf.append( "%'" );
		}
		else
		{
			buf.append( "NAME like '%'" );
		}

		// Agency
		strVal = evt.getAgencyId();
		if ( strVal != null && strVal.trim().length() > 0 )
		{
			if ("CSC".equals( strVal ) ){
				
				buf.append( " AND ( AGENCY_ID = " );
				buf.append( "'" + strVal + "'" );
				buf.append( " OR AGENCY_ID is NULL) " );
			}else{
				
				buf.append( " and AGENCY_ID = " );
				buf.append( "'" + strVal + "'" );
			}
		}

		// description
		strVal = evt.getUnformattedDesc();

		if ( strVal != null && strVal.trim().length() > 0 )
		{
			strVal = strVal.replace('*', '%');
			strVal = this.removeBadData(strVal, "'");
			//Cannot take out this upper. unformatteddesc is not all caps.
			buf.append( " and UPPER(UNFORMATTEDDESC) LIKE " );
			buf.append( "'%" + strVal.toUpperCase() + "%'" );
		}

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
			buf.append(evt.isStandard()  ? "1" : "0");
		}

		// isSpecialCondition
		buf.append( " and ISSPECIALCONDITION = " );
		buf.append( evt.isSpecialCondition() ? "1" : "0");

		// Effective Date
		Date dateVal = evt.getEffectiveDate();
		if ( dateVal != null )
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateVal);
            calendar.set(Calendar.HOUR_OF_DAY,ZERO);
            calendar.clear(Calendar.MINUTE);
            calendar.clear(Calendar.SECOND);
            calendar.clear(Calendar.MILLISECOND);
			buf.append(" and EFFECTIVEDATE = '");
			String dateStr = dateFormatWithMillis.format(calendar.getTime());
			buf.append(dateStr);
			buf.append("'");

		}

		// Inactive Date
		dateVal = evt.getInactiveDate();
		if ( dateVal != null )
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateVal);
            calendar.set(Calendar.HOUR_OF_DAY,ZERO);
            calendar.clear(Calendar.MINUTE);
            calendar.clear(Calendar.SECOND);
            calendar.clear(Calendar.MILLISECOND);
            
            buf.append(" and INACTIVEDATE = '");
            String dateStr = dateFormatWithMillis.format(calendar.getTime());
			buf.append(dateStr);
			buf.append("'");
			
//			calendar.set(Calendar.HOUR_OF_DAY, TWENTY_THREE);
//			calendar.set(Calendar.MINUTE, FIFTY_NINE);
//			calendar.set(Calendar.SECOND, FIFTY_NINE);
//			calendar.set(Calendar.MILLISECOND, NINES);
//			
//            buf.append(" and '");
//            dateStr = dateFormatWithMillis.format(calendar.getTime());
//			buf.append(dateStr);
//			buf.append("'");
		}

		// Jurisdiction ID
		strVal = evt.getJurisdiction();
		if ( strVal != null && strVal.trim().length() > 0 )
		{
			buf.append( " and JURISDICTION_ID = '");
			buf.append( strVal );
			buf.append( "'" );
		}

		// isDeleted
//		buf.append( " and ISDELETED = 0" );

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
			Calendar calendar = Calendar.getInstance();
			String dateStr = null;
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
				buf.append(" and INACTIVEDATE <= '");
				buf.append(dateStr); //current date
				buf.append("'");
			}
		}
		if(evt.getSupervisionTypeCd() != null && !evt.getSupervisionTypeCd().equals("")) {
			buf.append(" and CONDITION_ID IN ( SELECT CONDITION_ID FROM JIMS2.CSCONDSUPTYPECD WHERE CODE = '" + evt.getSupervisionTypeCd() + "')"  );
		}
		return buf.toString();
	}


	/**
	 *
	 * @param anEvent
	 * @return
	 */
	public String getSupervisionCondsByGroup1Clause(IEvent anEvent)
	{
		GetSupervisionConditionsByGroup1Event queryEvt = (GetSupervisionConditionsByGroup1Event)anEvent;
		StringBuffer whereBuffer = new StringBuffer();

		String group1Id = queryEvt.getGroup1ID();
		if((group1Id!=null) && (!group1Id.equalsIgnoreCase("")))
		{
			whereBuffer.append(" GROUP1 = " + group1Id + " AND ");
		}

		List orderConditionIdsList =  queryEvt.getConditionIdsList();
		int conditionCounter = 1;
		if(orderConditionIdsList!=null && orderConditionIdsList.size()>0)
		{
			whereBuffer.append(" CONDITION_ID IN ( ");

			Iterator condIter = orderConditionIdsList.iterator();
			while(condIter.hasNext())
			{
				String conditionId = (String)condIter.next();
				whereBuffer.append(conditionId);

				if(conditionCounter < orderConditionIdsList.size())
				{
					whereBuffer.append(" , ");
				}
				else
				{
					whereBuffer.append(" )");
				}

				conditionCounter++;
			}
		}
		return whereBuffer.toString();
	}


}
