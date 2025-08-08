//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\manageworkgroup\\SaveWorkGroupEvent.java

package messaging.manageworkgroup;

import mojo.km.messaging.RequestEvent;

public class SaveWorkGroupEvent extends RequestEvent 
{
    private String workGroupId;
	private String name;
	private String description;
	private String agencyId;
	private String type;
	private String[] userIds; 
    
    /**
     * @return Returns the userIds.
     */
    public String[] getUserIds() {
        return userIds;
    }
    /**
     * @param userIds The userIds to set.
     */
    public void setUserIds(String[] userIds) {
        this.userIds = userIds;
    }
    
   /**
    * @roseuid 45EF3286020D
    */
   public SaveWorkGroupEvent() 
   {
    
   }

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
    * @return Returns the description.
    */
   public String getDescription() {
       return description;
   }
   /**
    * @param description The description to set.
    */
   public void setDescription(String description) {
       this.description = description;
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
     * @return Returns the workGroupId.
     */
    public String getWorkGroupId() {
        return workGroupId;
    }
    /**
     * @param workGroupId The workGroupId to set.
     */
    public void setWorkGroupId(String workGroupId) {
        this.workGroupId = workGroupId;
    }
}
