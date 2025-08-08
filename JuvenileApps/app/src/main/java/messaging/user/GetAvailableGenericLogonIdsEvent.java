//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\user\\GetAvailableGenericLogonIdsEvent.java

package messaging.user;

import mojo.km.messaging.RequestEvent;

public class GetAvailableGenericLogonIdsEvent extends RequestEvent 
{
   public String departmentId;
   
   /**
    * @roseuid 447C46610388
    */
   public GetAvailableGenericLogonIdsEvent() 
   {
    
   }
   

/**
 * @return
 */
public String getDepartmentId()
{
	return departmentId;
}

/**
 * @param string
 */
public void setDepartmentId(String string)
{
	departmentId = string;
}

}
