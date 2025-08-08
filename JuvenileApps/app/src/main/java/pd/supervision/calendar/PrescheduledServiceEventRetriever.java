package pd.supervision.calendar;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.apache.commons.collections.FastHashMap;
import naming.PDCalendarConstants;
import pd.common.calendar.CalendarRetriever;
import pd.common.calendar.CalendarUtil;
import pd.security.SecurityService;
import mojo.km.config.CalendaringProperties;
import mojo.km.context.ContextManager;
import messaging.calendar.CalendarContextEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.security.ISecurityManager;
import mojo.km.util.DateUtil;

/**
 * @author glyons
 *  
 */
public class PrescheduledServiceEventRetriever extends CalendarRetriever
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

        // filter out the duplicate events
        Map singles = new FastHashMap();
        while (i.hasNext())
        {
            ServiceEventContext v = (ServiceEventContext) i.next();
            if (!singles.containsKey(v.getCalendarEventId()))
            {
                singles.put(v.getCalendarEventId(), v);
            }
        }        
        return singles.values().iterator();
    }

    // 	get all future events.

    protected String buildDateSql(CalendarRetriever event)
    {
        String and = " ";
        if (getWhereClause() != null && getWhereClause().length() > 0)
        {
            and = " AND ";
        }
        String saveTimeZone = CalendaringProperties.getInstance().getPersistenceTimeZone();
        StringBuffer sql = new StringBuffer();
        Date gmtStart = CalendarUtil.convertDate(TimeZone.getDefault().getID(), saveTimeZone, new Date());
        Date gmtEnd = CalendarUtil.addOrSubstractYearsFromDate(gmtStart, 2);
        boolean startDateTimePresent=false;
        if(event.getStartDatetime() != null && (Math.abs(((event.getStartDatetime().getTime()- DateUtil.getCurrentDate().getTime())/(1000*60*60*24)))>7))
        	startDateTimePresent=true;     
        	
        if (event.getStartDatetime() != null) {
        	gmtStart = CalendarUtil.convertDate(TimeZone.getDefault().getID(), saveTimeZone, event.getStartDatetime());         	
        }
        if (event.getEndDatetime() != null) {
        	gmtEnd = CalendarUtil.convertDate(TimeZone.getDefault().getID(), saveTimeZone, event.getEndDatetime()); 
        }
 
        //If SPEventsOnly is true, then allow events from the past 7 days to show up.
        //Changed for User Story 11297 - bypass 7-day restriction for Data Control users
        //provided a date range is not entered by user
        if (event.isSpEventsOnly()) {
        	 ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
 			if (mgr != null)
 			{
 				Set ufeatures = mgr.getFeatures();
 				if (!ufeatures.contains("JCW-CFC-SP-EVENTS-MORETHAN7DAYSOLD"))
 				{	
 					gmtStart = CalendarUtil.addOrSubstractDaysFromDate(gmtStart, -7);
 				}
 				else if(!startDateTimePresent)
 					gmtStart = CalendarUtil.addOrSubstractDaysFromDate(gmtStart, -180);
 			}
        	
        	//gmtStart = CalendarUtil.addOrSubstractDaysFromDate(gmtStart, -7);
        }
        
        String startDate = formatDateForSql(gmtStart);
        String endDate = formatDateForSql(gmtEnd);
        
        sql.append(and);        
        sql.append("(STARTDATETIME >= '");
        sql.append(startDate);
        sql.append("' and STARTDATETIME < '");
        sql.append(endDate);        
        sql.append("') ");
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
		
        sql.append("EVENTSTATUSCD NOT LIKE '");
        sql.append(PDCalendarConstants.SERVICE_EVENT_STATUS_CANCELLED);
        
        if (!event.isSpEventsOnly()) {
        	sql.append("' AND EVENTSTATUSCD NOT LIKE '");
        	sql.append(PDCalendarConstants.SERVICE_EVENT_STATUS_COMPLETED);
        }
        //The following code removes the event if the juvenile is already scheduled for it.
        sql.append("' AND SERVEVENT_ID NOT IN (SELECT SERVEVENT_ID FROM JIMS2.JCSERVEVENTCONTXT WHERE ");
        if (officerNotNull || casefileNotNull || JuvenileNotNull || CLMOfficersNotNull) {
			String or = "";
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
        sql.append(")");
        return sql.toString();
    }
}
