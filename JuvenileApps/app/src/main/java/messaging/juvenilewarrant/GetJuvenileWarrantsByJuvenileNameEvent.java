//Source file: C:\\views\\MJW\\app\\src\\messaging\\juvenilewarrant\\GetJuvenileWarrantsByResponsibleAdultEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileWarrantsByJuvenileNameEvent extends RequestEvent 
{
   private String firstName;
   private String lastName;
   
   
   /**
    * 
    */
   public GetJuvenileWarrantsByJuvenileNameEvent() 
   {
    
   }
/**
 * @return
 */
public String getFirstName()
{
	return firstName;
}

/**
 * @return
 */
public String getLastName()
{
	return lastName;
}

/**
 * @param string
 */
public void setFirstName(String firstName)
{
	this.firstName = firstName;
}

/**
 * @param string
 */
public void setLastName(String lastName)
{
	this.lastName = lastName;
}

}
