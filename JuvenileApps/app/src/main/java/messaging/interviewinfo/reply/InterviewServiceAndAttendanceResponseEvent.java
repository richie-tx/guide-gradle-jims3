package messaging.interviewinfo.reply;



import mojo.km.messaging.ResponseEvent;

public class InterviewServiceAndAttendanceResponseEvent extends ResponseEvent {

	private String eventComments;
	private String progressNotes;
	private String attendanceCode;
	private String attendanceDesc;
	private String serviceEventId;
	
	/**
	 * @return the serviceEventId
	 */
	public String getServiceEventId() {
		return serviceEventId;
	}
	/**
	 * @param serviceEventId the serviceEventId to set
	 */
	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}
	public String getEventComments() {
		return eventComments;
	}
	public void setEventComments(String eventComments) {
		this.eventComments = eventComments;
	}
	public String getProgressNotes() {
		return progressNotes;
	}
	public void setProgressNotes(String progressNotes) {
		this.progressNotes = progressNotes;
	}
	public String getAttendanceCode() {
		return attendanceCode;
	}
	public void setAttendanceCode(String attendanceCode) {
		this.attendanceCode = attendanceCode;
	}
	public String getAttendanceDesc() {
		
		
//		Code attCode = null;
//		if (attendanceCode != null) {
//			attCode = (Code) new Reference(attendanceCode, Code.class, "SERVEVENT_ATTENDANCE_STATUS").getObject();
//		}
//		return attCode.getDescription();
//		return CodeHelper.getCodeDescription("SERVEVENT_ATTENDANCE_STATUS", attendanceCode);
		return "";
	}
	public void setAttendanceDesc(String attendanceDesc) {
		this.attendanceDesc = attendanceDesc;
	}
	
}
