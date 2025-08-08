//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\interviewinfo\\GetJuvenileBenefitsEvent.java

package messaging.interviewinfo;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileBenefitsEvent extends RequestEvent 
{
   public String juvenileNum;
   private boolean title4eAndMedicaidOnly;
   
   /**
    * @roseuid 43F3782F0386
    */
   public GetJuvenileBenefitsEvent() 
   {
    
   }
   
   /**
    * Access method for the juvenileNum property.
    * 
    * @return   the current value of the juvenileNum property
    */
   public String getJuvenileNum()
   {
      return juvenileNum;
   }
   
   /**
    * Sets the value of the juvenileNum property.
    * 
    * @param aJuvenileNum the new value of the juvenileNum property
    */
   public void setJuvenileNum(String aJuvenileNum)
   {
      juvenileNum = aJuvenileNum;
   }
   
	/**
	 * @return
	 */
	public boolean isTitle4eAndMedicaidOnly()
	{
		return title4eAndMedicaidOnly;
	}
	
	/**
	 * @param b
	 */
	public void setTitle4eAndMedicaidOnly(boolean b)
	{
		title4eAndMedicaidOnly = b;
	}

}