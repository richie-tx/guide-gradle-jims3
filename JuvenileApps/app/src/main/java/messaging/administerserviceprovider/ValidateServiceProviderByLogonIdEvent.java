//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\ValidateServiceCodeEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class ValidateServiceProviderByLogonIdEvent extends RequestEvent 
{
   private String logonId;
   private int serviceProviderId;
   private String employeeId;
   
   /**
    * @roseuid 447458820291
    */
   public ValidateServiceProviderByLogonIdEvent() 
   {
    
   }
 
	/**
	 * @return
	 */
	public String getLogonId()
	{
		return logonId;
	}
	
	/**
	 * @param string
	 */
	public void setLogonId(String string)
	{
		logonId = string;
	}

	/**
	 * @return
	 */
	public int getServiceProviderId()
	{
		return serviceProviderId;
	}
	
	/**
	 * @param i
	 */
	public void setServiceProviderId(int i)
	{
		serviceProviderId = i;
	}


	/**
	 * @return
	 */
	public String getEmployeeId()
	{
		return employeeId;
	}
	
	/**
	 * @param string
	 */
	public void setEmployeeId(String string)
	{
		employeeId = string;
	}

}
