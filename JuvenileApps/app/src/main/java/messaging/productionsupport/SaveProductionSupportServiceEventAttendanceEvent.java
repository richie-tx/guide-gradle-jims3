//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\SaveServiceEventAttendanceEvent.java

package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class SaveProductionSupportServiceEventAttendanceEvent extends RequestEvent 
{
   
	private String serviceEventId;
	private String juvenileId;
	private Integer AddlAttendees;
	private String AttendanceStatusCd;
	
	
	
	
   
   /**
    * @roseuid 45702FFC0393
    */
   public SaveProductionSupportServiceEventAttendanceEvent() 
   {
    
   }

   /**
    * 
    * @return
    */
	public String getServiceEventId() {
		return serviceEventId;
	}

	/**
	 * 
	 * @param serviceEventId
	 */
	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}
	
	/**
	 * 
	 * @return
	 */
	public Integer getAddlAttendees() {
		return AddlAttendees;
	}
	
	/**
	 * 
	 * @param addlAttendees
	 */
	public void setAddlAttendees(Integer addlAttendees) {
		AddlAttendees = addlAttendees;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getAttendanceStatusCd() {
		return AttendanceStatusCd;
	}

	/**
	 * 
	 * @param attendanceStatusCd
	 */
	public void setAttendanceStatusCd(String attendanceStatusCd) {
		AttendanceStatusCd = attendanceStatusCd;
	}

	/**
	 * @return the juvenileId
	 */
	public String getJuvenileId() {
		return juvenileId;
	}

	/**
	 * @param juvenileId the juvenileId to set
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}   
}
