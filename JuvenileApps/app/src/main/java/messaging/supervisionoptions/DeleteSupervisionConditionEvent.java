//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\DeleteSupervisionConditionEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class DeleteSupervisionConditionEvent extends RequestEvent 
{
    private String conditionId;
    private String reasonToInactivate;
    private boolean isTrueDelete;
    
	/**
	 * @return Returns the isTrueDelete.
	 */
	public boolean isTrueDelete() {
		return isTrueDelete;
	}
	/**
	 * @param isTrueDelete The isTrueDelete to set.
	 */
	public void setTrueDelete(boolean isTrueDelete) {
		this.isTrueDelete = isTrueDelete;
	}
	/**
	 * @return Returns the reasonToInactivate.
	 */
	public String getReasonToInactivate() {
		return reasonToInactivate;
	}
	/**
	 * @param reasonToInactivate The reasonToInactivate to set.
	 */
	public void setReasonToInactivate(String reasonToInactivate) {
		this.reasonToInactivate = reasonToInactivate;
	}

	/**
    * @roseuid 42F7C4EA0271
    */
   public DeleteSupervisionConditionEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getConditionId()
	{
		return conditionId;
	}
	
	/**
	 * @param string
	 */
	public void setConditionId(String string)
	{
		conditionId = string;
	}

}
