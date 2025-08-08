/*
 * Created on Nov 28, 2005
 */
package pd.supervision.calendar;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import naming.PDCalendarConstants;
import org.apache.commons.collections.FastArrayList;
import pd.common.calendar.CalendarRetriever;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.ICalendarAttribute;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class ServiceEventRetriever extends CalendarRetriever
{
    /**
     * Retrieve implementation that will return a distinct list of calendar events for all contexts
     * specified. Attributes and Start/End Dates could constrain the results.
     * 
     * @return CalendarEventResponse Iterator
     */
    public Object retrieve()
    {
        IHome home = new Home();
        Iterator i = home.findAll(this, ServiceEventContext.class);

        Map singles = new HashMap();
        int counter = 0;
        while (i.hasNext())
        { 
            ServiceEventContext v = (ServiceEventContext) i.next();
                        
            if(singles.containsKey(v.getCalendarEventId()))
            {
                ServiceEventContext priorContext = (ServiceEventContext) singles.get(v.getCalendarEventId());
                
                if ( (priorContext.getJuvenileId().equalsIgnoreCase(PDCalendarConstants.INVALID_CONTEXT_ID)) &&
                		(!v.getJuvenileId().equalsIgnoreCase(PDCalendarConstants.INVALID_CONTEXT_ID)) ) {
                	
                	singles.remove(v.getCalendarEventId());
                	singles.put(v.getCalendarEventId(), v);
                // If allows for context with the same CalendarEventId that are valid to be in the hash map (result set)
                } else if ( (!v.getJuvenileId().equalsIgnoreCase(priorContext.getJuvenileId())) && 
                		(!v.getCaseFileId().equalsIgnoreCase(priorContext.getCaseFileId())) &&
                		( (!v.getJuvenileId().equalsIgnoreCase(PDCalendarConstants.INVALID_CONTEXT_ID)) ) &&
                		( (!v.getCaseFileId().equalsIgnoreCase(PDCalendarConstants.INVALID_CONTEXT_ID)) )
                	) {
                	//singles.put(v.getCalendarEventId(), v);                	
                	singles.put(counter++, v);
                }
                		
            }
            else
            {
                singles.put(v.getCalendarEventId(), v);
            }
        }
        
        return singles.values().iterator();
    }

    /**
     * Default builder for constructing a where clause snippet for the attributes. Default behavior
     * of the sql is the attributes will generate an AND sql statement for the attributes. If
     * desired, override this method in implementation class.
     * 
     * @param event
     * @return sql
     */
    protected String buildAttributeSql(CalendarRetriever event)
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
                        List attList = (List) attMap.get(a.getAttributeName());
                        attList.add(a.getAttributeValue());
                    }
                    else
                    {
                        List attList = new FastArrayList();
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
            List attList = (List) entry.getValue();
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
     * Creates the sql around the contexts for the retriever for retrieving contexts for a calendar
     * event. The sql is specific to retrieving contexts that SHARE calendar events
     * 
     * @param event
     * @return sql snippet
     */
    protected String buildContextSql(CalendarRetriever event)
    {
    	
    	StringBuilder sql = new StringBuilder();
		CalendarContextEvent context = event.getCalendarContext();
		String start = getWhereClauseStart();

		boolean officerNotNull = false;
		boolean JuvenileNotNull = false;
		boolean casefileNotNull = false;
		boolean CLMOfficersNotNull = false;
		
		if (context != null) {
			if ((context.getProbationOfficerId() != null) && (context.getProbationOfficerId().length() > 0)) {
				officerNotNull = true;
				if ( (context.getCLMProbationOfficerIds() != null) && (context.getCLMProbationOfficerIds().length > 0) ) {
					CLMOfficersNotNull = true;
				}
			} else if ((context.getCaseFileId() != null) && (context.getCaseFileId().length() > 0)) {
				casefileNotNull = true;
			} else if ((context.getJuvenileId() != null) && (context.getJuvenileId().length() > 0)) {
				JuvenileNotNull = true;
			}
		}
		
		if (officerNotNull || casefileNotNull || JuvenileNotNull || CLMOfficersNotNull) {
			String or = "";
			
			sql.append(start);
			sql.append("(");
			if ( officerNotNull) {
				sql.append("officer_id in (" + context.getProbationOfficerId()); 
				
				if ( CLMOfficersNotNull ) {
					boolean first = true;
					for(int i=0; i<context.getCLMProbationOfficerIds().length; i++){
						if ( first ) {
							 
							 if (context.getCLMProbationOfficerIds().length == 1) {
								 sql.append(", " + context.getCLMProbationOfficerIds()[i] + " ");
							 } else {
								 sql.append(", " + context.getCLMProbationOfficerIds()[i] + ", ");
							 }
							 
							 first = false;
						 }else if( (i+1) == context.getCLMProbationOfficerIds().length) {
							 sql.append(context.getCLMProbationOfficerIds()[i]);
						 } else {
							 sql.append(context.getCLMProbationOfficerIds()[i] + ", ");
						 }
					}
					sql.append(" ) ");
				} else {
					sql.append(" ) ");
				}
				
				or = " or ";
			}
			if ( casefileNotNull) {
				sql.append(or).append("casefile_id = " + context.getCaseFileId() + "");
				or = " or ";
			}
			if ( JuvenileNotNull) {
				sql.append(or).append("juvenile_id = '" + context.getJuvenileId() + "'");
			}
			sql.append(")");			
		}
        //sql.append(" OPTIMIZE FOR 1 ROW ");
        return sql.toString();
    }
}
