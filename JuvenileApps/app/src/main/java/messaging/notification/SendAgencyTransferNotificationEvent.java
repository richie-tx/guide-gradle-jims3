//Source file: C:\\views\\dev\\app\\src\\messaging\\notification\\SendAgencyTransferNotificationEvent.java

package messaging.notification;

import mojo.km.messaging.RequestEvent;

public class SendAgencyTransferNotificationEvent extends RequestEvent 
{
   private String email;
   private String message;
   
   /**
   @roseuid 4107BEFD035F
    */
   public SendAgencyTransferNotificationEvent() 
   {
    
   }
   
   /**
   @param email
   @roseuid 4106B3600296
    */
   public void setEmail(String email) 
   {
    	this.email = email;
   }
   
   /**
   @return String
   @roseuid 4106B3600298
    */
   public String getEmail() 
   {
    return email;
   }
/**
 * @return
 */
public String getMessage()
{
	return message;
}

/**
 * @param string
 */
public void setMessage(String string)
{
	message = string;
}

}
