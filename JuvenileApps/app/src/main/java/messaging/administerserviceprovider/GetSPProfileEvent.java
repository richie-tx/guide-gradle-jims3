//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetContactsEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetSPProfileEvent extends RequestEvent 
{
   private String employeeId;
   private String logonId;
   private boolean isDepartmentInfoRequired;
   private String jimsLogonId;
   
   /**
    * @roseuid 450ACF500085
    */
   public GetSPProfileEvent() 
   {
    
   }
	/**
	 * @return Returns the employeeId.
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId The employeeId to set.
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * @return Returns the isDepartmentInfoRequired.
	 */
	public boolean isDepartmentInfoRequired() {
		return isDepartmentInfoRequired;
	}
	/**
	 * @param isDepartmentInfoRequired The isDepartmentInfoRequired to set.
	 */
	public void setDepartmentInfoRequired(boolean isDepartmentInfoRequired) {
		this.isDepartmentInfoRequired = isDepartmentInfoRequired;
	}
	/**
	 * @return Returns the logonId.
	 */
	public String getLogonId() {
		return logonId;
	}
	/**
	 * @param logonId The logonId to set.
	 */
	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}
/**
 * @return Returns the jimsLogonId.
 */
public String getJimsLogonId() {
    return jimsLogonId;
}
/**
 * @param jimsLogonId The jimsLogonId to set.
 */
public void setJimsLogonId(String jimsLogonId) {
    this.jimsLogonId = jimsLogonId;
}
}
