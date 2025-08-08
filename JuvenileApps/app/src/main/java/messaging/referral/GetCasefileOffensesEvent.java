//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefileOffensesEvent.java

package messaging.referral;

import mojo.km.messaging.RequestEvent;

public class GetCasefileOffensesEvent extends RequestEvent 
{
   private String casefileId;
   private String juvenileNum;
   
   /**
    * @roseuid 42A9A16B0396
    */
   public GetCasefileOffensesEvent() 
   {
    
   }

	/**
	 * @return
	 */
	public String getCasefileId()
	{
		return casefileId;
	}
	
	/**
	 * @param string
	 */
	public void setCasefileId(String string)
	{
		casefileId = string;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}
	
	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

}
