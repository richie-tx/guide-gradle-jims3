//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\messaging\\security\\GetRoleUsersEvent.java

package messaging.security;

import mojo.km.messaging.RequestEvent;

public class GetRoleUsersAndUserGroupsEvent extends RequestEvent 
{
   public String roleId;
   
   /**
    * @roseuid 4256F0C502DE
    */
   public GetRoleUsersAndUserGroupsEvent() 
   {
    
   }
   
   /**
    * @param roleId
    * @roseuid 4256EB880109
    */
   public void setRoleId(String roleId) 
   {
      this.roleId = roleId;
   }
   
   /**
    * @return String
    * @roseuid 4256EB88010B
    */
   public String getRoleId() 
   {
      return this.roleId;
   }
}
