//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\manageworkgroup\\ValidateWorkGroupEvent.java

package messaging.manageworkgroup;

import mojo.km.messaging.RequestEvent;

public class ValidateWorkGroupEvent extends RequestEvent 
{
    private String name;
    private String agencyId;
    private String workGroupId;
   
   /**
    * @roseuid 45DB615503A4
    */
   public ValidateWorkGroupEvent() 
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
