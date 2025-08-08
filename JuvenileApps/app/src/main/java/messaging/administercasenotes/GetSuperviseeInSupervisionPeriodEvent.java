//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercasenotes\\GetSuperviseeInSupervisionPeriodEvent.java

package messaging.administercasenotes;

import mojo.km.messaging.RequestEvent;

public class GetSuperviseeInSupervisionPeriodEvent extends RequestEvent 
{
   
	private String spn;
	private String supervisionPeriodId;
	private String userAgencyId;
	private boolean activeSupervisionPeriod;

	/**
    * @roseuid 44F4617301E7
    */
   public GetSuperviseeInSupervisionPeriodEvent() 
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
	 * @return boolean  indicates whether the request is for the active supervision period.
	 */
	public boolean isActiveSupervisionPeriod()
	{
		return activeSupervisionPeriod;
	}

	/**
	 * @return Returns the userAgencyId.
	 */
	public String getUserAgencyId()
	{
		return userAgencyId;
	}
	
	/**
	 * @param activeSupervisionPeriod The activeSupervisionPeriod to set.
	 */
	public void setActiveSupervisionPeriod(boolean activeSupervisionPeriod)
	{
		this.activeSupervisionPeriod = activeSupervisionPeriod;
	}

	/**
	 * @param value
	 */
	public void setSpn(String value)
	{
		spn = value;
	}

	/**
	 * @param aSupervisionPeriodId
	 */
	public void setSupervisionPeriodId(String aSupervisionPeriodId)
	{
		supervisionPeriodId = aSupervisionPeriodId;
	}

	/**
	 * @param anAgencyId The userAgencyId to set.
	 */
	public void setUserAgencyId(String anAgencyId)
	{
		this.userAgencyId = anAgencyId;
	}
}
