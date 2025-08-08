/*
 * Created on Sep 12, 2005
 */
package mapping.supervision.programreferral;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import pd.common.calendar.CalendarUtil;
import java.util.TimeZone;

import messaging.programreferral.GetProgramReferralDetailsReportEvent;
import messaging.programreferral.GetProgramReferralListEvent;
import messaging.programreferral.GetProgramReferralReportEvent;
import messaging.programreferral.ProgramReferralRetrieverAttribute;
import mojo.km.config.CalendaringProperties;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

/**
 */
public class ProgramReferralWhereClause
{
	

	
	/**
	 * Default builder for constructing a where clause snippet for 
	 * the attributes.  Default behavior of the sql is the attributes
	 * will generate an AND sql statement for the attributes.  If 
	 * desired, override this method in implementation class.
	 * @param event
	 * @return sql 
	 */
	public String buildWhereClause(IEvent anEvent) 
	{
		GetProgramReferralListEvent event = (GetProgramReferralListEvent)anEvent;
	
		StringBuffer sql = new StringBuffer();	
	
			Iterator iter =  event.getReferralAttributes().iterator();
			HashMap attMap = new HashMap();
			
			while (iter.hasNext()){
				ProgramReferralRetrieverAttribute att = (ProgramReferralRetrieverAttribute)iter.next();
				if (attMap.containsKey(att.getAttributeName())){						
					ArrayList attList = (ArrayList) attMap.get(att.getAttributeName());
					attList.add(att.getAttributeValue());												
				}else{
					ArrayList attList = new ArrayList();
					attList.add(att.getAttributeValue());
					attMap.put(att.getAttributeName(),attList);
				}
			}
			iter = attMap.entrySet().iterator();
			String beginWhereClause = " ";
			while (iter.hasNext())
			{			
				
				Map.Entry entry = (Map.Entry)iter.next();
				String attributeName = (String)entry.getKey();								
				
				
				if (attributeName.equals(ProgramReferralRetrieverAttribute.CASEFILE)||
					attributeName.equals(ProgramReferralRetrieverAttribute.PROGRAM)|| 
					attributeName.equals(ProgramReferralRetrieverAttribute.SERVICEPROVIDER))
				{
					sql.append(beginWhereClause + attributeName  + " in ");
					sql.append(this.getIntegerValues((List)entry.getValue()));	
					
				}
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.JUVENILE))
				{
					sql.append(beginWhereClause + attributeName  + " in ");
					sql.append(this.getStringValues((List)entry.getValue()));
				}
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.STATUSCD))
				{
					sql.append(beginWhereClause + attributeName  + " in ");
					sql.append(this.getProgramReferralStatusValues((List)entry.getValue()));
				}
				
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.BEGINDATE)||
						attributeName.equals(ProgramReferralRetrieverAttribute.ENDDATE))
				{
					if (attributeName.equals(ProgramReferralRetrieverAttribute.BEGINDATE)) {
						sql.append(beginWhereClause + attributeName  + " >= ");
					} 
					else if (attributeName.equals(ProgramReferralRetrieverAttribute.ENDDATE) ) 
					{
						sql.append(beginWhereClause + ProgramReferralRetrieverAttribute.BEGINDATE  + " <= ");
					}
					
					sql.append(this.getDateValues((List)entry.getValue()));
				} else if (attributeName.equals(ProgramReferralRetrieverAttribute.OFFICERFIRSTNAME)||
						attributeName.equals(ProgramReferralRetrieverAttribute.OFFICERLASTNAME) ||
						attributeName.equals(ProgramReferralRetrieverAttribute.OFFICERMIDDLENAME) )
				{
					sql.append(beginWhereClause + attributeName  + " LIKE ");
					sql.append(this.getStringValues((List)entry.getValue()));
				}
				
				beginWhereClause = " AND ";				
			}
		return sql.toString();
	}	
	
	/**
	 * 
	 * @param anEvent
	 * @return
	 */
	public String buildReportWhereClause(IEvent anEvent) 
	{
		GetProgramReferralReportEvent event = (GetProgramReferralReportEvent)anEvent;
	
		StringBuffer sql = new StringBuffer();	
	
			Iterator iter =  event.getReferralAttributes().iterator();
			HashMap attMap = new HashMap();
			
			while (iter.hasNext()){
				ProgramReferralRetrieverAttribute att = (ProgramReferralRetrieverAttribute)iter.next();
				if (attMap.containsKey(att.getAttributeName())){						
					ArrayList attList = (ArrayList) attMap.get(att.getAttributeName());
					attList.add(att.getAttributeValue());												
				}else{
					ArrayList attList = new ArrayList();
					attList.add(att.getAttributeValue());
					attMap.put(att.getAttributeName(),attList);
				}
			}
			iter = attMap.entrySet().iterator();
			String beginWhereClause = " ";
			while (iter.hasNext())
			{			
				
				Map.Entry entry = (Map.Entry)iter.next();
				String attributeName = (String)entry.getKey();								
				
				
				if (attributeName.equals(ProgramReferralRetrieverAttribute.CASEFILE)||
					attributeName.equals(ProgramReferralRetrieverAttribute.PROGRAM)|| 
					attributeName.equals(ProgramReferralRetrieverAttribute.SERVICEPROVIDER))
				{
					sql.append(beginWhereClause + attributeName  + " in ");
					sql.append(this.getIntegerValues((List)entry.getValue()));	
					
				}
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.JUVENILE))
				{
					sql.append(beginWhereClause + attributeName  + " in ");
					sql.append(this.getStringValues((List)entry.getValue()));
				}
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.STATUSCD))
				{
					sql.append(beginWhereClause + attributeName  + " in ");
					sql.append(this.getProgramReferralStatusValues((List)entry.getValue()));
				}
				
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.BEGINDATE)||
						attributeName.equals(ProgramReferralRetrieverAttribute.ENDDATE))
				{
					if (attributeName.equals(ProgramReferralRetrieverAttribute.BEGINDATE)) {
						sql.append(beginWhereClause + attributeName  + " >= ");
					} 
					else if (attributeName.equals(ProgramReferralRetrieverAttribute.ENDDATE) ) 
					{
						sql.append(beginWhereClause + ProgramReferralRetrieverAttribute.BEGINDATE  + " <= ");
					}
					
					sql.append(this.getDateValues((List)entry.getValue()));
				} 
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.BCLOSEDATE)||
					attributeName.equals(ProgramReferralRetrieverAttribute.ECLOSEDATE))
				{
				if (attributeName.equals(ProgramReferralRetrieverAttribute.BCLOSEDATE)) {
					sql.append(beginWhereClause + "ENDDATE"  + " >= ");
				} 
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.ECLOSEDATE) ) 
				{
					sql.append(beginWhereClause + "ENDDATE"  + " <= ");
				}
				
				sql.append(this.getDateValues((List)entry.getValue()));
				} 
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.OFFICERFIRSTNAME)||
						attributeName.equals(ProgramReferralRetrieverAttribute.OFFICERLASTNAME) ||
						attributeName.equals(ProgramReferralRetrieverAttribute.OFFICERMIDDLENAME) )
				{
					sql.append(beginWhereClause + attributeName  + " LIKE ");
					sql.append(this.getStringValues((List)entry.getValue()));
				}
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.JUVFIRSTNAME)||
					attributeName.equals(ProgramReferralRetrieverAttribute.JUVLASTNAME) ||
					attributeName.equals(ProgramReferralRetrieverAttribute.JUVMIDDLENAME) )
				{
				sql.append(beginWhereClause + attributeName  + " LIKE ");
				sql.append(this.getStringValues((List)entry.getValue()));
				}
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.JUVLOCUNIT))
        			{
        				sql.append(beginWhereClause + attributeName  + " = ");
        				sql.append(this.getStringValues((List)entry.getValue()));
        			}
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.SPRVISIONTYPE))
        			{
        				sql.append(beginWhereClause + attributeName  + " = ");
        				sql.append(this.getStringValues((List)entry.getValue()));
        			}
				
				beginWhereClause = " AND ";				
			}
		return sql.toString();
	}
	
	/**
	 * 
	 * @param anEvent
	 * @return
	 */
	public String buildDetailedReportWhereClause(IEvent anEvent) 
	{
	    GetProgramReferralDetailsReportEvent event = (GetProgramReferralDetailsReportEvent)anEvent;
	
		StringBuffer sql = new StringBuffer();	
	
			Iterator iter =  event.getReferralAttributes().iterator();
			HashMap attMap = new HashMap();
			
			while (iter.hasNext()){
				ProgramReferralRetrieverAttribute att = (ProgramReferralRetrieverAttribute)iter.next();
				if (attMap.containsKey(att.getAttributeName())){						
					ArrayList attList = (ArrayList) attMap.get(att.getAttributeName());
					attList.add(att.getAttributeValue());												
				}else{
					ArrayList attList = new ArrayList();
					attList.add(att.getAttributeValue());
					attMap.put(att.getAttributeName(),attList);
				}
			}
			iter = attMap.entrySet().iterator();
			String beginWhereClause = " ";
			while (iter.hasNext())
			{			
				
				Map.Entry entry = (Map.Entry)iter.next();
				String attributeName = (String)entry.getKey();								
				
				
				if (attributeName.equals(ProgramReferralRetrieverAttribute.CASEFILE)||
					attributeName.equals(ProgramReferralRetrieverAttribute.PROGRAM)|| 
					attributeName.equals(ProgramReferralRetrieverAttribute.SERVICEPROVIDER))
				{
					sql.append(beginWhereClause + attributeName  + " in ");
					sql.append(this.getIntegerValues((List)entry.getValue()));	
					
				}
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.JUVENILE))
				{
					sql.append(beginWhereClause + attributeName  + " in ");
					sql.append(this.getStringValues((List)entry.getValue()));
				}
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.STATUSCD))
				{
					sql.append(beginWhereClause + attributeName  + " in ");
					sql.append(this.getProgramReferralStatusValues((List)entry.getValue()));
				}
				
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.BEGINDATE)||
						attributeName.equals(ProgramReferralRetrieverAttribute.ENDDATE))
				{
					if (attributeName.equals(ProgramReferralRetrieverAttribute.BEGINDATE)) {
						sql.append(beginWhereClause + attributeName  + " >= ");
					} 
					else if (attributeName.equals(ProgramReferralRetrieverAttribute.ENDDATE) ) 
					{
						sql.append(beginWhereClause + ProgramReferralRetrieverAttribute.BEGINDATE  + " <= ");
					}
					
					sql.append(this.getDateValues((List)entry.getValue()));
				} 
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.BCLOSEDATE)||
					attributeName.equals(ProgramReferralRetrieverAttribute.ECLOSEDATE))
				{
				if (attributeName.equals(ProgramReferralRetrieverAttribute.BCLOSEDATE)) {
					sql.append(beginWhereClause + "ENDDATE"  + " >= ");
				} 
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.ECLOSEDATE) ) 
				{
					sql.append(beginWhereClause + "ENDDATE"  + " <= ");
				}
				
				sql.append(this.getDateValues((List)entry.getValue()));
				} 
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.OFFICERFIRSTNAME)||
						attributeName.equals(ProgramReferralRetrieverAttribute.OFFICERLASTNAME) ||
						attributeName.equals(ProgramReferralRetrieverAttribute.OFFICERMIDDLENAME) )
				{
					sql.append(beginWhereClause + attributeName  + " LIKE ");
					sql.append(this.getStringValues((List)entry.getValue()));
				}
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.JUVFIRSTNAME)||
					attributeName.equals(ProgramReferralRetrieverAttribute.JUVLASTNAME) ||
					attributeName.equals(ProgramReferralRetrieverAttribute.JUVMIDDLENAME) )
				{
				sql.append(beginWhereClause + attributeName  + " LIKE ");
				sql.append(this.getStringValues((List)entry.getValue()));
				}
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.JUVLOCUNIT))
        			{
        				sql.append(beginWhereClause + attributeName  + " = ");
        				sql.append(this.getStringValues((List)entry.getValue()));
        			}
				else if (attributeName.equals(ProgramReferralRetrieverAttribute.SPRVISIONTYPE))
        			{
        				sql.append(beginWhereClause + attributeName  + " = ");
        				sql.append(this.getStringValues((List)entry.getValue()));
        			}
				
				beginWhereClause = " AND ";				
			}
		return sql.toString();
	}
	
	private String getDateValues(List attList){
		
		Iterator attIter = attList.iterator();		
		StringBuffer sql = new StringBuffer();

		while (attIter.hasNext()){
			String startDateStr = (String)attIter.next();	
			
			Calendar cal = Calendar.getInstance() ;
			cal.setTime( DateUtil.stringToDate( startDateStr, "MM/dd/yyyy" ) ) ;
			cal.set( Calendar.HOUR_OF_DAY, 0 ) ;
			cal.set( Calendar.MINUTE, 0 ) ;
			String saveTimeZone = CalendaringProperties.getInstance().getPersistenceTimeZone();
			Date gmtStart = CalendarUtil.convertDate(TimeZone.getDefault().getID(), saveTimeZone, cal.getTime());
			String formattedDate = formatDateForSql(gmtStart);
			
			sql.append("'" + formattedDate + "'");

		}

		return sql.toString();
		
	}
	
	private String getIntegerValues(List attList){
		Iterator attIter = attList.iterator();		
		StringBuffer sql = new StringBuffer();
		String comma=" ";
		sql.append(" ( ");
		while (attIter.hasNext()){
			String attributeValue = (String)attIter.next();			
			sql.append(comma + Integer.parseInt(attributeValue));
			comma = " , ";
		}				
		sql.append(" ) ");
		return sql.toString();
	}
	
	private String getStringValues(List attList){
		Iterator attIter = attList.iterator();		
		StringBuffer sql = new StringBuffer();
		String comma=" ";
		sql.append(" ( ");
		while (attIter.hasNext()){
			String attributeValue = (String)attIter.next();			
			sql.append(comma + "'" + attributeValue + "'");
			comma = " , ";
		}
		sql.append(" ) ");
		return sql.toString();
	}
	/* Sets the where clause with either (a) a combination of a 
	 * Program Referral Status and Program Referral Sub Status
	 * may have been choosen, or (b) sets where clause with an "in"  which 
	 * houses with all three major Referral Status. This does not handle 
	 * multiple combinations of Program Referral Status and Program Referral Sub Status.
	 */
	private String getProgramReferralStatusValues(List attList){
		
		StringBuffer sql = new StringBuffer();
		// if assumes a combination may have been chosen
		if (attList.size() == 1) {
			
			Iterator attIter = attList.iterator();
			String attributeValue = (String)attIter.next();	
			
			if (attributeValue.equalsIgnoreCase("TN") 
					|| attributeValue.equalsIgnoreCase("AC")
					|| attributeValue.equalsIgnoreCase("CL")) {
				
				sql.append("('" + attributeValue + "')");
				
			} 
			else if (attributeValue.equalsIgnoreCase("TNREF"))
			{
				sql.append("('" + "TN" + "')");
				sql.append(" AND SUBSTATUSCD in ('" + "REF" + "')");
			
			}
			else if (attributeValue.equalsIgnoreCase("TNACC"))
			{
				sql.append("('" + "TN" + "')");
				sql.append(" AND SUBSTATUSCD in ('" + "ACC" + "')");
			
			}
			else if (attributeValue.equalsIgnoreCase("CLREJ"))
			{
				sql.append("('" + "CL" + "')");
				sql.append(" AND SUBSTATUSCD in ('" + "REJ" + "')");
			
			}
			else if (attributeValue.equalsIgnoreCase("CLWIT"))
			{
				sql.append("('" + "CL" + "')");
				sql.append(" AND SUBSTATUSCD in ('" + "WIT" + "')");
			
			}
			else if (attributeValue.equalsIgnoreCase("CLCAN"))
			{
				sql.append("('" + "CL" + "')");
				sql.append(" AND SUBSTATUSCD in ('" + "CAN" + "')");
			
			}
			else if (attributeValue.equalsIgnoreCase("CLCLS"))
			{
				sql.append("('" + "CL" + "')");
				sql.append(" AND SUBSTATUSCD in ('" + "CLS" + "')");
			
			}
			else if (attributeValue.equalsIgnoreCase("CLCOM"))
			{
				sql.append("('" + "CL" + "')");
				sql.append(" AND SUBSTATUSCD in ('" + "COM" + "')");
			
			}
		//else assumes all three major types will be searched for
		} else {
		
			Iterator attIter = attList.iterator();		

			String comma=" ";
			sql.append(" ( ");
			while (attIter.hasNext()){
				String attributeValue = (String)attIter.next();			
				sql.append(comma + "'" + attributeValue + "'");
				comma = " , ";
			}
			sql.append(" ) ");
		}
		
		return sql.toString();
	}
	
	/**
	 * Formats a date to the respective DB SQL date format.  In the
	 * case of JIMS2, DB2.
	 * @param date
	 * @return formatted Date
	 */
	protected String formatDateForSql(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		return formatter.format(date);
	}

}
