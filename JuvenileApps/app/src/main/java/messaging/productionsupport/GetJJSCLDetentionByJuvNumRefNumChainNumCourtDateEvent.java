//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\GetCalendarServiceEventsEvent.java

package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author aPillai
 */

public class GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent  extends RequestEvent
{
	
        private String juvenileNumber;
        private String referralNumber;
	private String chainNumber; 
	private String courtDate;	

	public String getCourtDate()
	{
	    return courtDate;
	}

	/*public void setCourtDate(String courtDate)
	{
	    this.courtDate = courtDate;
	}*/
	public void setCourtDate(String courtDate) {
		this.courtDate = courtDate;
	}

	public String getJuvenileNumber()
	{
	    return juvenileNumber;
	}

	public void setJuvenileNumber(String juvenileNumber)
	{
	    this.juvenileNumber = juvenileNumber;
	}
	public String getReferralNumber()
	{
	    return referralNumber;
	}

	public void setReferralNumber(String referralNumber)
	{
	    this.referralNumber = referralNumber;
	}
	public String getChainNumber()
	{
	    return chainNumber;
	}

	public void setChainNumber(String chainNumber)
	{
	    this.chainNumber = chainNumber;
	}

}