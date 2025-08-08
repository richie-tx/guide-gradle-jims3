//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercasenotes\\GetSuperviseesInSupervisionPeriodEvent.java

package messaging.administercasenotes;

import mojo.km.messaging.RequestEvent;

public class GetCasenotesJournalEvent extends RequestEvent 
{
   
	private String spn;
	private String supervisionPeriodId;
	private boolean activeSupervisionPeriod;
	private String userAgencyId;

   /**
    * @roseuid 44F461740289
    */
	public GetCasenotesJournalEvent() 
	{  
	}

	/**
	 * @return boolean  indicates whether the request is for the active supervision period.
	 */
	public boolean isActiveSupervisionPeriod()
	{
		return activeSupervisionPeriod;
	}

	/**
	 * @param activeSupervisionPeriod The activeSupervisionPeriod to set.
	 */
	public void setActiveSupervisionPeriod(boolean activeSupervisionPeriod)
	{
		this.activeSupervisionPeriod = activeSupervisionPeriod;
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
