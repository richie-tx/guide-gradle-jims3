package mapping.administercaseload;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import messaging.administercaseload.GetInOutActivityEvent;
import mojo.km.messaging.IEvent;

public class CaseloadInOutActivityWhereClause 
{
	private static final SimpleDateFormat dateFormatWithMillis = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static int ZERO = 0;
	private static int FIFTY_NINE = 59;
	private static int NINES = 999;
	private static int TWENTY_THREE = 23;

	public String getInOutActivityClause( IEvent anEvent )
	{
    	GetInOutActivityEvent in_out_activity_event = 
            							(GetInOutActivityEvent)anEvent;

    		//retrieve properties of event
//        String begin_date = DateUtil.dateToString(in_out_activity_event.getBeginDate(), DateUtil.DATE_FMT_1);
//        String end_date = DateUtil.dateToString(in_out_activity_event.getEndDate(), DateUtil.DATE_FMT_1);

        String staff_position_id = in_out_activity_event.getAssignStaffPositionId();

        	//build where clause
        /* String where_clause = 
        	"(( inout = 'IN' and date(begdate) >= '" + begin_date 
        		+ "' and date(begdate) <= '" + end_date + "' )"
        		+ " or (inout = 'OUT' and date(enddate) >= '" + begin_date
        		+ "' and date(enddate) <= '" + end_date + "' ))"
       			+ " and assignstaffpos_id = " + staff_position_id; 
        */

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(in_out_activity_event.getBeginDate());
		calendar.set(Calendar.HOUR_OF_DAY,ZERO);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		String begDateStr = dateFormatWithMillis.format(calendar.getTime());
		
		calendar.setTime(in_out_activity_event.getEndDate());
		calendar.set(Calendar.HOUR_OF_DAY, TWENTY_THREE);
		calendar.set(Calendar.MINUTE, FIFTY_NINE);
		calendar.set(Calendar.SECOND, FIFTY_NINE);
		calendar.set(Calendar.MILLISECOND, NINES);
		String endDateStr = dateFormatWithMillis.format(calendar.getTime());
		
        StringBuffer where_clause = new StringBuffer("(( INOUT = 'IN' and BEGDATE >= '");
        where_clause.append(begDateStr);
		where_clause.append("' and BEGDATE <= '");
		where_clause.append(endDateStr);
 		where_clause.append("' ) or ( INOUT = 'OUT' and ENDDATE >= '");
 		where_clause.append(begDateStr);
 		where_clause.append("' AND ENDDATE <= '");
		where_clause.append(endDateStr);		
		where_clause.append("' ))");
		where_clause.append(" and ASSIGNSTAFFPOS_ID = " );
		where_clause.append(staff_position_id);
		
        	//return where clause
        return where_clause.toString();
	}//end of getInOutActivityClause()
}
