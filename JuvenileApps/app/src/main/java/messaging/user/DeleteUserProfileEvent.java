//Source file: C:\\views\\MSA\\app\\src\\messaging\\user\\DeleteUserProfileEvent.java

package messaging.user;

import mojo.km.messaging.RequestEvent;

public class DeleteUserProfileEvent extends RequestEvent 
{
   public String logonId;
   
   /**
    * @roseuid 43F4F58E03BB
    */
   public DeleteUserProfileEvent() 
   {
    
   }
   
   /**
    * @param logonId
    * @roseuid 43EA4DE503CE
    */
   public void setLogonId(String logonId) 
   {
    	this.logonId = logonId;
   }
   
   /**
    * @return String
    * @roseuid 43EA4DE503D0
    */
   public String getLogonId() 
   {
    return logonId;
   }
}
