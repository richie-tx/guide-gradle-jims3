/*
 * Created on Feb 11, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.cscdcalendar;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map; 

import messaging.cscdcalendar.GetCSEventRetrieverEvent;
import messaging.calendar.ICalendarAttribute;
import mojo.km.config.MojoProperties;
import mojo.km.messaging.IEvent;


/**
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSEventRetriever 
{
	
	private StringBuffer whereClause = new StringBuffer();
	

	/**
	 * Initializes the where clause to a new
	 * StringBuffer.  Should be called initially in
	 * any implementation of retrieve()
	 *
	 */
	protected void initializeWhereClause() {
		whereClause = new StringBuffer();
	}
	
	/** 
	 * Returns the current state of the whereclause
	 * that is being generated.  Could be used to check
	 * if any sql has been created.
	 * @return sql
	 */
	protected String getWhereClause() {
		return whereClause.toString();
	}
	
	/**
	 * Returns the schema name for the JDBC Connection Properties.
	 * @return jdbcSchemaName
	 */
	protected String getJDBCSchema() {
		return MojoProperties.getInstance().getConnectionProperties("JDBC").getDb2Schema();
	}
	
	/**
	 * Default abstract behavior of building the where clause for using dynamic sql for
	 * finding the CS Calendar Events for a particular retriever. 
	 * @param event.   Note:  Must be public for the dynamic sql in mojo to invoke the
	 * method dynamically
	 * @return dynamic where clause
	 */
	public String buildWhereClause(IEvent event) 
	{
		initializeWhereClause();
		
		whereClause.append(buildDateSql((GetCSEventRetrieverEvent) event));
		whereClause.append(buildDeletedSql());
		whereClause.append(buildAttributeSql((GetCSEventRetrieverEvent) event));
		
		return whereClause.toString();
	}
	
	protected String buildDeletedSql() {
		String and = " ";
		if (getWhereClause() != null && getWhereClause().length() > 0)
		{
			and = " AND ";
		}
		return and + " MARKEDDELETEON IS NULL ";
	}
	
	/**
     * Default builder for constructing a where clause snippet for the attributes. Default behavior
     * of the sql is the attributes will generate an AND sql statement for the attributes. If
     * desired, override this method in implementation class.
     * 
     * @param event
     * @return sql
     */
    protected String buildAttributeSql(GetCSEventRetrieverEvent event)
    {
        StringBuffer sql = new StringBuffer();
        ICalendarAttribute[] atts = event.getCalendarAttributes();

        HashMap attMap = new HashMap();

        if (atts.length > 0)
        {
            for (int x = 0; x < atts.length; x++)
            {
                ICalendarAttribute a = atts[x];
                if (a != null)
                {
                    if (attMap.containsKey(a.getAttributeName()))
                    {
                        ArrayList attList = (ArrayList) attMap.get(a.getAttributeName());
                        attList.add(a.getAttributeValue());
                    }
                    else
                    {
                        ArrayList attList = new ArrayList();
                        attList.add(a.getAttributeValue());
                        attMap.put(a.getAttributeName(), attList);
                    }
                }
            }
        }

        Iterator iter = attMap.entrySet().iterator();
        String start = getWhereClauseStart();
        while (iter.hasNext())
        {
            Map.Entry entry = (Map.Entry) iter.next();
            String attributeName = (String) entry.getKey();
            ArrayList attList = (ArrayList) entry.getValue();
            Iterator attIter = attList.iterator();
            sql.append(start + attributeName + " in (");
            String comma = " ";
            while (attIter.hasNext())
            {
                Object attributeValue = attIter.next();
                if (attributeValue instanceof Integer)
                {
                    Integer i = (Integer) attributeValue;
                    sql.append(comma + i.intValue());
                }
                else if (attributeValue instanceof String)
                {
                    sql.append(comma + "'" + attributeValue + "'");
                }
                else if (attributeValue instanceof Double)
                {
                    Double d = (Double) attributeValue;
                    sql.append(comma + d.doubleValue());
                }
                else if (attributeValue instanceof Float)
                {
                    Float f = (Float) attributeValue;
                    sql.append(comma + f.floatValue());
                }
                comma = " , ";
            }
            sql.append(" ) ");
            start = " AND ";
        }
        return sql.toString();
    }
	
	/**
	 * Creates the sql around a start and end date.  Verifies if any sql has already been
	 * created to format correctly.  Will change the start and end dates to the expected
	 * TimeZone datetime for each based off the configuration
	 * @param event
	 * @param existingWhere
	 * @return sql snippet for start and end date
	 */
	
    protected String buildDateSql(GetCSEventRetrieverEvent event) {
		String and = " ";
		if (getWhereClause() != null && getWhereClause().length() > 0)
		{
			and = " AND ";
		}
		StringBuffer sql = new StringBuffer();
		if (event.getStartDatetime() != null) {				
			String startDate = CSEventHelper.formatDateForSql(event.getStartDatetime());
			sql.append(and + "EVENTDATE >= '" + startDate + " 00:00:00.0000000'");
			and = " AND ";
			
		}
		if (event.getEndDatetime() != null) {			
			String endDate = CSEventHelper.formatDateForSql(event.getEndDatetime());
			sql.append(and + "EVENTDATE <= '" + endDate + " 23:59:59.9999999'"); 
		}		
		return sql.toString(); 
	}
		
	/**
	 * Checks to see if any sql was generated for the where clause.  If so,
	 * it puts an AND statement for formatting the correct sql to be added
	 * to the next part of the where clause
	 * @return String
	 */
	protected String getWhereClauseStart() {
		String and = " ";
		if (getWhereClause() != null && getWhereClause().length() > 0)
		{
			and = " AND ";
		}	
		return and;
	}	
	
}