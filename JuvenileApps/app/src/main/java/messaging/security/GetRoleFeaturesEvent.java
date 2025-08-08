//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\messaging\\security\\GetRoleFeaturesEvent.java

package messaging.security;

import mojo.km.messaging.RequestEvent;

public class GetRoleFeaturesEvent extends RequestEvent 
{
   private String roleId;
   
   /**
    * @roseuid 4256F0C50203
    */
   public GetRoleFeaturesEvent() 
   {
    
   }
   
   /**
    * @param roleId
    * @roseuid 4256EB880022
    */
   public void setRoleId(String roleId) 
   {
      this.roleId = roleId;
   }
   
   /**
    * @return String
    * @roseuid 4256EB880024
    */
   public String getRoleId() 
   {
      return this.roleId;
   }
}
