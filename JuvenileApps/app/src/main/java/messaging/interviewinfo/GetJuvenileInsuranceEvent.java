//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\interviewinfo\\GetJuvenileInsuranceEvent.java

package messaging.interviewinfo;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileInsuranceEvent extends RequestEvent 
{
   public String juvenileNum;
   
   /**
    * @roseuid 43F37938019E
    */
   public GetJuvenileInsuranceEvent() 
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
 
}