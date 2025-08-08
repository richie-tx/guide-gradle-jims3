/*
 * Created on Oct 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.common.calendar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import mojo.km.config.CalendaringProperties;
import mojo.km.config.MojoProperties;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.RequestEvent;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.ICalendarAttribute;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class CalendarRetriever extends RequestEvent
{
	/* Default for the CalendarView - can be changed using the accessors */
	private String calendarViewName = "JCCALEVENTCONTVIEW";
	private Date startDatetime;
	private Date endDatetime;
	private boolean spEventsOnly;
	private CalendarContextEvent context = new CalendarContextEvent();
	private ICalendarAttribute[] attributes = new ICalendarAttribute[0];
	private StringBuffer whereClause = new StringBuffer();
	
	/**
	 * Returns the view that is mapped to the specific problem domain
	 * implementation of the Calendar Event.  Default is set in the 
	 * constructor of the calendarViewName property.  If a custom view
	 * is needed, create the view using the default view and add
	 * custom columns and set the CalendarViewName 
	 * @return view
	 */
	public String getCalendarViewName() {
		return calendarViewName;
	}
	
	/**
	 * Used to set the custom view that is being used for the domain
	 * specific implementation of the retriever
	 * @param viewName
	 */
	public void setCalendarViewName(String viewName) {
		calendarViewName = viewName;
	}
	
	/**
	 * Return the Start Date and Time to retrieve
	 * @return date
	 */
	public Date getStartDatetime() {
		return startDatetime;
	}
	
	/**
	 * Set the start date time to retrieve
	 * @param startDate
	 */
	public void setStartDatetime(Date startDate) {
		startDatetime = startDate;
	}
	
	/**
	 * Return the End Date and Time to retrieve
	 * @return date
	 */
	public Date getEndDatetime() {
		return endDatetime;
	}
	
	/**
	 * Set the end date time to retrieve
	 * @param endDate
	 */
	public void setEndDatetime(Date endDate) {
		endDatetime = endDate;
	}
	
	/**
	 * @return spEventsOnly
	 */
	public boolean isSpEventsOnly() {
		return spEventsOnly;
	}

	/**
	 * @param spEventsOnly
	 */
	public void setSpEventsOnly(boolean spEventsOnly) {
		this.spEventsOnly = spEventsOnly;
	}
	
	/**
	 * Return the contexts that will be used to create the sql.  
	 * @return array
	 */
	public CalendarContextEvent getCalendarContext() {
		return context;
	}
	
	/**
	 * Set the contexts to be used for this calendar retrieval
	 * @param cons
	 */
	public void setCalendarContext(CalendarContextEvent context) {
		this.context = context;
	}
	
	/**
	 * Return the custom attributes that will be used for 
	 * generating the sql.  These are custom columns that
	 * can be used to constrain the sql results 
	 * @return attributes
	 */
	public ICalendarAttribute[] getCalendarAttributes() {
		return attributes;
	}
	
	/**
	 * Set the attributes that will constrain the sql that
	 * is generated for specific columns
	 * @param atts
	 */
	public void setCalendarAttributes(ICalendarAttribute[] atts) {
		attributes = atts;
	}
	
	/**
	 * Retrieve method is used by the specific implementation to
	 * retrieve Calendar Events based of specific problem domain 
	 * logic.  
	 */
	public abstract Object retrieve();	

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
		return MojoProperties.getInstance().getConnectionProperties("JDBCMSSQL").getDb2Schema();
	}
	
	/**
	 * Default abstract behavior of building the where clause for using dynamic sql for
	 * finding the Calendar Events for a particular retriever. 
	 * @param event.   Note:  Must be public for the dynamic sql in mojo to invoke the
	 * method dynamically
	 * @return dynamic where clause
	 */
	public String buildWhereClause(IEvent event) 
	{
		initializeWhereClause();

		whereClause.append(buildContextSql((CalendarRetriever) event));
		whereClause.append(buildDateSql((CalendarRetriever) event));
		whereClause.append(buildAttributeSql((CalendarRetriever) event));
		return whereClause.toString();
	}
	
	/**
	 * Default builder for constructing a where clause snippet for 
	 * the attributes.  Default behavior of the sql is the attributes
	 * will generate an AND sql statement for the attributes.  If 
	 * desired, override this method in implementation class.
	 * @param event
	 * @return sql 
	 */
	protected String buildAttributeSql(CalendarRetriever event) 
	{
		StringBuffer sql = new StringBuffer();
		String start = getWhereClauseStart();
		
		ICalendarAttribute[] atts = event.getCalendarAttributes();
		if (atts.length > 0) {
			for (int x=0; x < atts.length; x++) {
				ICalendarAttribute a = atts[x];
				if (a != null) 
				{
					if (a.getAttributeValue() instanceof Integer){
						Integer i = (Integer)a.getAttributeValue();
						sql.append(start + a.getAttributeName() + " = " + i.intValue());
					}
					else if (a.getAttributeValue() instanceof String){
						sql.append(start + a.getAttributeName() + " = '" + a.getAttributeValue() + "'");
					}
					else if (a.getAttributeValue() instanceof Double){
						Double d = (Double)a.getAttributeValue();
						sql.append(start + a.getAttributeName() + " = " + d.doubleValue());
					}
					else if (a.getAttributeValue() instanceof Float){
						Float f = (Float)a.getAttributeValue();
						sql.append(start + a.getAttributeName() + " = " + f.floatValue());
					}
				}				
			}
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
	protected String buildDateSql(CalendarRetriever event) 
	{
		String and = " ";
		if (getWhereClause() != null && getWhereClause().length() > 0)
		{
			and = " AND ";
		}
		String saveTimeZone = CalendaringProperties.getInstance().getPersistenceTimeZone();
		StringBuffer sql = new StringBuffer();
		if (event.getStartDatetime() != null) {
			
			Date gmtStart = CalendarUtil.convertDate(TimeZone.getDefault().getID(), saveTimeZone, event.getStartDatetime());
			String startDate = formatDateForSql(gmtStart);
			sql.append(and + "STARTDATETIME >= '" + startDate + "'");
			and = " AND ";
			
		}
		if (event.getEndDatetime() != null) {
			Date gmtEnd = CalendarUtil.convertDate(TimeZone.getDefault().getID(), saveTimeZone, event.getEndDatetime());
			String endDate = formatDateForSql(gmtEnd);
//			sql.append(and + "ENDDATETIME <= '" + endDate + "'");
			sql.append(and + "STARTDATETIME <= '" + endDate + "'");
		}		
		return sql.toString();
	}
	
	/**
	 * Creates the sql around the contexts for the retriever for retrieving contexts 
	 * for a calendar event.  The sql is specific to retrieving contexts that share
	 * calendar events 
	 * @param event
	 * @return sql snippet
	 */
	protected String buildContextSql(CalendarRetriever event) 
	{
		return "";
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
	
	/**
	 * Formats a date to the respective DB SQL date format.  In the
	 * case of JIMS2, DB2.
	 * @param date
	 * @return formatted Date
	 */
	protected String formatDateForSql(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}
}
