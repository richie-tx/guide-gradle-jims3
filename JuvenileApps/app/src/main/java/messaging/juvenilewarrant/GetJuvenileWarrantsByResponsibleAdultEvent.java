//Source file: C:\\views\\MJW\\app\\src\\messaging\\juvenilewarrant\\GetJuvenileWarrantsByResponsibleAdultEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileWarrantsByResponsibleAdultEvent extends RequestEvent 
{
   private String associateFirstName;
   private String associateLastName;
   
   
   /**
    * @roseuid 4462395A00E7
    */
   public GetJuvenileWarrantsByResponsibleAdultEvent() 
   {
    
   }
/**
 * @return
 */
public String getAssociateFirstName()
{
	return associateFirstName;
}

/**
 * @return
 */
public String getAssociateLastName()
{
	return associateLastName;
}

/**
 * @param string
 */
public void setAssociateFirstName(String associateFirstName)
{
	this.associateFirstName = associateFirstName;
}

/**
 * @param string
 */
public void setAssociateLastName(String associateLastName)
{
	this.associateLastName = associateLastName;
}

}
