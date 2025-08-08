//Source file: C:\\views\\dev\\app\\src\\messaging\\authorization\\DeleteRoleEvent.java

package messaging.authorization;

import mojo.km.messaging.RequestEvent;

public class DeleteRoleEvent extends RequestEvent 
{
   public String roleId;
   
   /**
   @roseuid 4107BED20173
    */
   public DeleteRoleEvent() 
   {
    
   }
   
   /**
   @param roleId
   @roseuid 4106B5D6009B
    */
   public void setRoleId(String roleId) 
   {
    this.roleId = roleId;
   }
   
   /**
   @return String
   @roseuid 4106B5D6009D
    */
   public String getRoleId() 
   {
    return this.roleId;
   }
}
