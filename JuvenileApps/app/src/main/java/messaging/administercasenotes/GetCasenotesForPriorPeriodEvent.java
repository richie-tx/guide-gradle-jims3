//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercasenotes\\GetSuperviseesInSupervisionPeriodEvent.java

package messaging.administercasenotes;

import mojo.km.messaging.RequestEvent;

public class GetCasenotesForPriorPeriodEvent extends RequestEvent 
{
   
	private String spn;
	private String supervisionPeriodId;
	private String userAgencyId;

   /**
    *
    */
	public GetCasenotesForPriorPeriodEvent() 
	{  
	}

	/**
	 * @return
	 */
	public String getSpn()
	{
		return spn;
	}

	/**
	 * @return
	 */
	public String getSupervisionPeriodId()
	{
		return supervisionPeriodId;
	}

	/**
	 * @return Returns the userAgencyId.
	 */
	public String getUserAgencyId()
	{
		return userAgencyId;
	}
	
	/**
	 * @param value
	 */
	public void setSpn(String value)
	{
		spn = value;
	}

	/**
	 * @param aPeriodId
	 */
	public void setSupervisionPeriodId(String aPeriodId)
	{
		supervisionPeriodId = aPeriodId;
	}

	/**
	 * @param anAgencyId The userAgencyId to set.
	 */
	public void setUserAgencyId(String anAgencyId)
	{
		this.userAgencyId = anAgencyId;
	}
}
