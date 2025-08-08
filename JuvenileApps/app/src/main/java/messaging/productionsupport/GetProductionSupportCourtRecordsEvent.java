//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\GetCalendarServiceEventsEvent.java

package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author aPillai
 */

public class GetProductionSupportCourtRecordsEvent  extends RequestEvent
{
	
        private String juvenileNumber;
        //private String courtId;
        private String referralNum;        
	private String courtDate;
        private String chainNumber;

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

	public String getChainNumber()
	{
	    return chainNumber;
	}

	public void setChainNumber(String chainNumber)
	{
	    this.chainNumber = chainNumber;
	}
	public String getReferralNum()
	{
	    return referralNum;
	}

	public void setReferralNum(String referralNum)
	{
	    this.referralNum = referralNum;
	}


}