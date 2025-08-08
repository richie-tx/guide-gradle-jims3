//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefilePetitionsEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCasefilePetitionDispositionsEvent extends RequestEvent 
{
   private String juvenileNum;
   private String referralNum;
   
   /**
    * @roseuid 42A9A16E02BE
    */
   public GetJuvenileCasefilePetitionDispositionsEvent() {}
   
	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}
	
	/**
	 * @return
	 */
	public String getReferralNum()
	{
		return referralNum;
	}
	
	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}
	
	/**
	 * @param string
	 */
	public void setReferralNum(String string)
	{
		referralNum = string;
	}

}
