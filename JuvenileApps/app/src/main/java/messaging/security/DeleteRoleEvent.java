//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\messaging\\security\\DeleteRoleEvent.java

package messaging.security;

import mojo.km.messaging.Composite.CompositeRequest;

public class DeleteRoleEvent extends CompositeRequest 
{
   private String roleId;
   /**
    * @roseuid 4256F0C50138
    */
   public DeleteRoleEvent() 
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
