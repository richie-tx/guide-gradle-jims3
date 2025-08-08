//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenile\\GetJuvenileJobsEvent.java

//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileJobsEvent.java

package messaging.juvenile;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileJobsEvent extends RequestEvent 
{
   private String juvenileNum;
   
   /**
    * @roseuid 42B196830128
    */
   public GetJuvenileJobsEvent() 
   {
    
   }
   
   /**
    * @param juvenileNum
    * @roseuid 42B18307037C
    */
   public void setJuvenileNum(String string) 
   {
    	juvenileNum = string;
   }
   
   /**
    * @return String
    * @roseuid 42B18307037E
    */
   public String getJuvenileNum() 
   {
    return juvenileNum;
   }
}
