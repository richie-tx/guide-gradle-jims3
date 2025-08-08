//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\GetJuvenileAttendanceEvent.java

package messaging.calendar;

import mojo.km.messaging.RequestEvent;

public class GetProgramAttendanceEvent extends RequestEvent 
{
   public String programId;
   public String juvenileNum;
   
   /**
    * @roseuid 456F33E603CA
    */
   public GetProgramAttendanceEvent() 
   {
    
   }
   

	/**
	 * @return Returns the programId.
	 */
	public String getProgramId() {
		return programId;
	}
	/**
	 * @param programId The programId to set.
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
}
