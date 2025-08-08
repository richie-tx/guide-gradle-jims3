//Source file: C:\\views\\dev\\app\\src\\messaging\\agency\\GetDepartmentEvent.java

package messaging.agency;

import mojo.km.messaging.RequestEvent;

public class GetDepartmentEvent extends RequestEvent 
{
	private String departmentId;
	private boolean getAddressAndContact = true;

   /**
    * @roseuid 42E67E4001F7
    */
   public GetDepartmentEvent() 
   {
    
   }
   
   /**
    * Access method for the departmentId property.
    * 
    * @return   the current value of the departmentId property
    */
   public String getDepartmentId()
   {
      return departmentId;
   }
   
   /**
    * Sets the value of the departmentId property.
    * 
    * @param aDepartmentId the new value of the departmentId property
    */
   public void setDepartmentId(String aDepartmentId)
   {
      departmentId = aDepartmentId;
   }
   
	/**
	 * @return
	 */
	public boolean isGetAddressAndContact()
	{
		return getAddressAndContact;
	}

	/**
	 * @param b
	 */
	public void setGetAddressAndContact(boolean value)
	{
		getAddressAndContact = value;
	}

	}