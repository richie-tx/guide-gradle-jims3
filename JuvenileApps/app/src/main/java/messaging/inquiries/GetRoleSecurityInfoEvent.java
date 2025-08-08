//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\messaging\\security\\DeleteRoleEvent.java

package messaging.inquiries;

import mojo.km.messaging.RequestEvent;

public class GetRoleSecurityInfoEvent extends RequestEvent 
{
   private String roleId;
   /**
    * @roseuid 4256F0C50138
    */
   public GetRoleSecurityInfoEvent() 
   {
    
   }

   /**
    * @return
    */
    public String getRoleId()
    {
	   return roleId;
    }

    /**
     * @param string
     */
     public void setRoleId(String string)
     {
	    roleId = string;
     }
}
