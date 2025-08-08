//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\cscdstaffposition\\AssignStaffPositionEvent.java

package messaging.cscdstaffposition;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class AssignStaffPositionEvent extends RequestEvent 
{
	 private String cjadNum;
	 private Date effectiveDate;
	 private String notes;
	 private String staffLogonId;
	 private String staffPositionId;
 
   /**
    * @roseuid 460BDCAD0143
    */
   public AssignStaffPositionEvent() 
   {
   }
	/**
	 * @return Returns the cjadNum.
	 */
	public String getCjadNum() {
		return cjadNum;
	}
	/**
	 * @return Returns the notes.
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @return Returns the staffLogonId.
	 */
	public String getStaffLogonId() {
		return staffLogonId;
	}
    /**
     * @return Returns the staffPositionId.
     */
    public String getStaffPositionId() {
        return staffPositionId;
    }
	/**
	 * @param cjadNum The cjadNum to set.
	 */
	public void setCjadNum(String cjadNum) {
		this.cjadNum = cjadNum;
	}
	/**
	 * @param notes The notes to set.
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @param staffLogonId The staffLogonId to set.
	 */
	public void setStaffLogonId(String staffLogonId) {
		this.staffLogonId = staffLogonId;
	}
    /**
     * @param staffPositionId The staffPositionId to set.
     */
    public void setStaffPositionId(String staffPositionId) {
        this.staffPositionId = staffPositionId;
    }
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
}
