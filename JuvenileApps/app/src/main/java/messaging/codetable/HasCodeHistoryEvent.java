package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class HasCodeHistoryEvent extends RequestEvent 
{
   public String code;
   
   /**
    * @roseuid 4164282803C3
    */
   public HasCodeHistoryEvent() 
   {
    
   }
   
   /**
    * @param code
    */
   public void setCode(String aCode) 
   {
   this.code = aCode;
   }
   
   /**
    * @return String
    */
   public String getCode() 
   {
    return this.code;
   }
   
}
