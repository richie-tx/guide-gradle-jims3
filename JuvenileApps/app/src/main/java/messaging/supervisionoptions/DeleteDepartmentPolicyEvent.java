//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\DeleteDepartmentPolicyEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class DeleteDepartmentPolicyEvent extends RequestEvent 
{
	private String departmentPolicyId;
	 private boolean inUse=false;
	   private String notes;
   
   /**
    * @roseuid 42F7C4E9030D
    */
   public DeleteDepartmentPolicyEvent() 
   {
   }
   
   
	/**
	 * @return
	 */
	public String getDepartmentPolicyId()
	{
		return departmentPolicyId;
	}

	/**
	 * @param string
	 */
	public void setDepartmentPolicyId(String string)
	{
		departmentPolicyId = string;
	}

	/**
	 * @return Returns the inUse.
	 */
	public boolean isInUse() {
		return inUse;
	}
	/**
	 * @param inUse The inUse to set.
	 */
	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}
	/**
	 * @return Returns the notes.
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes The notes to set.
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
