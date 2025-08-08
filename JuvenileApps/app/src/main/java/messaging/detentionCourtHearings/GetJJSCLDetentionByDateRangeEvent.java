//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJJSResultsEvent.java

package messaging.detentionCourtHearings;

import mojo.km.messaging.RequestEvent;
/**
 * Changes made 81390
 * @author sthyagarajan
 *
 */
public class GetJJSCLDetentionByDateRangeEvent extends RequestEvent
{
	public String juvenileNumber;
	public String startDate;
	public String endDate;

	/**
	* @roseuid 41ACD56B01EF
	*/
	public GetJJSCLDetentionByDateRangeEvent()
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
