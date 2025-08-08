//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\messaging\\security\\DeleteRoleEvent.java

package messaging.security;

import mojo.km.messaging.Composite.CompositeRequest;

public class GetSARoleByAgencyIdEvent extends CompositeRequest 
{
   private String agencyId;
   /**
    * @roseuid 4256F0C50138
    */
   public GetSARoleByAgencyIdEvent() 
   {
    
   }


	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}
	
	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}

}
