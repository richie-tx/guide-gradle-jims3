//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\GetCalendarUserTypeEvent.java

package messaging.calendar;

import mojo.km.messaging.RequestEvent;

public class GetCalendarUserTypeEvent extends RequestEvent 
{
   public String logonId;
   
   /**
    * @roseuid 45F1B0F003A7
    */
   public GetCalendarUserTypeEvent() 
   {
    
   }
   
   /**
    * @param logonId
    * @roseuid 45F080F0037C
    */
   public void setLogonId(String logonId) 
   {
    this.logonId = logonId;
   }
   
   /**
    * @return String
    * @roseuid 45F080F0037E
    */
   public String getLogonId() 
   {
    return logonId;
   }
}
