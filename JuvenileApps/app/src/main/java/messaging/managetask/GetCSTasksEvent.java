//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\managetask\\DisplayTasksSearchEvent.java

package messaging.managetask;

import mojo.km.messaging.RequestEvent;

public class GetCSTasksEvent extends RequestEvent 
{
    private String defendantId;
    private String criminalCaseId;
    
    
    
    /**
    * @roseuid 463F301402AE
    */
   public GetCSTasksEvent() 
   {
       
    
   }
    /**
     * 
     * @return
     */
    public String getDefendantId() {
		return defendantId;
	}
    /**
     * 
     * @param defendantId
     */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	/**
     * @return Returns the staffPositionId.
     */
    public String getCriminalCaseId()
    {
        return criminalCaseId;
    }
    /**
     * @param staffPositionId The staffPositionId to set.
     */
    public void setCriminalCaseId(String staffPositionId)
    {
        this.criminalCaseId = staffPositionId;
    }
    
}
