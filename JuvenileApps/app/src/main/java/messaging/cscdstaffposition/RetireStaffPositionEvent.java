//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\cscdstaffposition\\RetireStaffPositionEvent.java

package messaging.cscdstaffposition;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class RetireStaffPositionEvent extends RequestEvent 
{
	private String supervisionStaffId;
	private Date retirementDate;
	/**
    * @roseuid 460BDCDE022E
    */
   public RetireStaffPositionEvent() 
   {
    
   }
	/**
	 * @return Returns the supervisionStaffId.
	 */
	public String getSupervisionStaffId() {
		return supervisionStaffId;
	}
	/**
	 * @param supervisionStaffId The supervisionStaffId to set.
	 */
	public void setSupervisionStaffId(String supervisionStaffId) {
		this.supervisionStaffId = supervisionStaffId;
	}
	public Date getRetirementDate() {
		return retirementDate;
	}
	public void setRetirementDate(Date retirementDate) {
		this.retirementDate = retirementDate;
	}
}
