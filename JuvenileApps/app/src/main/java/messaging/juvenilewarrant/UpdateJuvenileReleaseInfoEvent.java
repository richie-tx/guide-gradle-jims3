//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\UpdateJuvenileReleaseInfoEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class UpdateJuvenileReleaseInfoEvent extends RequestEvent 
{
   private String releaseDecision;
   private Date releaseDecisionTimeStamp;
   private String warrantNum;
   private String logonId;
   
   /**
    * @roseuid 41FFDBFB02DE
    */
   public UpdateJuvenileReleaseInfoEvent() 
   {
    
   }
   

	/**
	 * @return
	 */
	public String getLogonId()
	{
		return logonId;
	}
	
	/**
	 * @return
	 */
	public String getReleaseDecision()
	{
		return releaseDecision;
	}
	
	/**
	 * @return
	 */
	public String getWarrantNum()
	{
		return warrantNum;
	}
	
	/**
	 * @param string
	 */
	public void setLogonId(String string)
	{
		logonId = string;
	}
	
	/**
	 * @param string
	 */
	public void setReleaseDecision(String string)
	{
		releaseDecision = string;
	}
	
	/**
	 * @param string
	 */
	public void setWarrantNum(String string)
	{
		warrantNum = string;
	}
	
	/**
	 * @return
	 */
	public Date getReleaseDecisionTimeStamp()
	{
		return releaseDecisionTimeStamp;
	}
	
	/**
	 * @param date
	 */
	public void setReleaseDecisionTimeStamp(Date date)
	{
		releaseDecisionTimeStamp = date;
	}

}
