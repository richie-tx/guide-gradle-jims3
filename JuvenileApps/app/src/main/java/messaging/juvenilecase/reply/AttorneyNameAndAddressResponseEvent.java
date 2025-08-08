package messaging.juvenilecase.reply;

import messaging.calendar.reply.DocketEventResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * 
 * @author sthyagarajan
 *
 */
public class AttorneyNameAndAddressResponseEvent  extends ResponseEvent implements Comparable<AttorneyNameAndAddressResponseEvent>
{
    private String attyName;
    private String barNum;
    private String attyNameHistory;
    /**
     * @return the attyName
     */
    public String getAttyName()
    {
        return attyName;
    }
    /**
     * @param attyName the attyName to set
     */
    public void setAttyName(String attyName)
    {
        this.attyName = attyName;
    }
    /**
     * @return the barNum
     */
    public String getBarNum()
    {
        return barNum;
    }
    /**
     * @param barNum the barNum to set
     */
    public void setBarNum(String barNum)
    {
        this.barNum = barNum;
    }
    /**
     * @return the attyNameHistory
     */
    public String getAttyNameHistory()
    {
        return attyNameHistory;
    }
    /**
     * @param attyNameHistory the attyNameHistory to set
     */
    public void setAttyNameHistory(String attyNameHistory)
    {
        this.attyNameHistory = attyNameHistory;
    }
    
    @Override
    public int compareTo(AttorneyNameAndAddressResponseEvent obj)
    {
	if (obj == null)
	    return -1;

	AttorneyNameAndAddressResponseEvent evt = obj;
	int result = 0;

	try
	{
	    if (this.getBarNum() != null || evt.getBarNum() != null)
	    {
		if (this.getBarNum() == null)
		{ /*
		   * this makes any null objects go to the bottom. change this
		   * to 1 if you want the top of the list
		   */
		    return -1;
		}

		if (this.getBarNum() == null)
		{ /*
		   * this makes any null objects go to the bottom. change this
		   * to -1 if you want the top of the list
		   */
		    return 1;
		}
		result = this.getBarNum().compareTo(evt.getBarNum());
		// backwards in order to get list to show up most recent first
		// result = evt.getEventDate().compareTo(getEventDate());
	    }

	} catch (NumberFormatException e)
	{
	    result = 0;
	}

	return result;
    }
    
}
