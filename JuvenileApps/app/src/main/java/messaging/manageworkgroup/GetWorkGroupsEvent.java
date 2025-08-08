//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\manageworkgroup\\GetWorkGroupsEvent.java

package messaging.manageworkgroup;

import mojo.km.messaging.RequestEvent;

public class GetWorkGroupsEvent extends RequestEvent 
{
	private String name;
	private String type;
	private String agencyId;
	
    /**
     * @return Returns the agencyId.
     */
    public String getAgencyId() {
        return agencyId;
    }
    /**
     * @param agencyId The agencyId to set.
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }
    /**
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }
   /**
    * @roseuid 45DB615500C6
    */
   public GetWorkGroupsEvent() 
   {
    
   }
}
