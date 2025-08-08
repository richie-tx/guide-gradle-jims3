//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\cscdstaffposition\\VacatePositionEvent.java

package messaging.cscdstaffposition;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class VacateStaffPositionEvent extends RequestEvent 
{
	private String supervisionStaffId;
	private Date effectiveDate;
   /**
    * @roseuid 460BDCEF00F6
    */
   public VacateStaffPositionEvent() 
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
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
}
