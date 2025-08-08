//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJJSResultsEvent.java

package messaging.districtCourtHearings;

import mojo.km.messaging.RequestEvent;
/**
 * 81390 changes
 * @author sthyagarajan
 *
 */
public class GetJJSCourtByDateRangeEvent extends RequestEvent
{
	public String juvenileNumber;
	public String startDate;
	public String endDate;

	/**
	* @roseuid 41ACD56B01EF
	*/
	public GetJJSCourtByDateRangeEvent()
	{
	}



	/**
	 * @return
	 */
	public String getJuvenileNumber()
	{
		return juvenileNumber;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNumber(String string)
	{
		juvenileNumber = string;
	}

	/**
	 * @return Returns the endDate.
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return Returns the startDate.
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
}
