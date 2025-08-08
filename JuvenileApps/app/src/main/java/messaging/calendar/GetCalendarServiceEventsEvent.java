//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\GetCalendarServiceEventsEvent.java

package messaging.calendar;

public class GetCalendarServiceEventsEvent extends GetCalendarEventsEvent
{
	/**
	 * Constructor is used to set the default retriever for this event. It can be changed later if
	 * required.
	 * 
	 */
	private String responseType;
	
	private boolean spEventsOnly;

	private boolean filterInvalidContexts;
	
	//<KISHORE>JIMS200060153 : MJCW - Schedule Calendar Event is Timing out on SP Events
	private String requestType;

	public GetCalendarServiceEventsEvent()
	{
		setRetriever("pd.supervision.calendar.ServiceEventRetriever");
	}
	
	/**
	 * @return Returns the responseType.
	 */
	public String getResponseType()
	{
		return responseType;
	}

	/**
	 * @param responseType
	 *            The responseType to set.
	 */
	public void setResponseType(String responseType)
	{
		this.responseType = responseType;
	}

	public boolean isFilterInvalidContexts()
	{
		return filterInvalidContexts;
	}

	public void setFilterInvalidContexts(boolean filterInvalidContexts)
	{
		this.filterInvalidContexts = filterInvalidContexts;
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
	 * @return the requestType
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

}