/*
 * Created on Feb 7, 2007
 *
 */
package messaging.calendar.reply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 *
 */
public class ServiceEventAttendanceResponseEvent extends ResponseEvent implements Comparable{
	private String serviceEventAttendanceId;
	private String serviceEventId;
	private String attendanceStatusDescription;
	private String attendanceStatusCd;
	private String progressNotes;
	private String existingProgressNotes;
	private String monthlySummary;
	private String juvenileId;
	private String juvenileName;
	
	private String serviceName;
	private String programId;
	private Date startDate;
	private Date startDateTime;
	private Date endDateTime;
	private String programReferralId;
	private Object document;
	private String docTypeCd;
	
	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJims2User;
	private String updateJims2User;

	//Activity JIMS200057657 - Document number of add'l attendees
	private String addlAttendees="";
	private Collection addlAttendeeNames=new ArrayList();
	private float creditHours;
		
	/**
	 * @return Returns the attendanceStatusCd.
	 */
	public String getAttendanceStatusCd() {
		return attendanceStatusCd;
	}
	/**
	 * @param attendanceStatusCd The attendanceStatusCd to set.
	 */
	public void setAttendanceStatusCd(String attendanceStatusCd) {
		this.attendanceStatusCd = attendanceStatusCd;
	}
	/**
	 * @return Returns the juvenileId.
	 */
	public String getJuvenileId() {
		return juvenileId;
	}
	/**
	 * @param juvenileId The juvenileId to set.
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
	/**
	 * @return Returns the progressNotes.
	 */
	public String getProgressNotes() {
		return progressNotes;
	}
	/**
	 * @param progressNotes The progressNotes to set.
	 */
	public void setProgressNotes(String progressNotes) {
		this.progressNotes = progressNotes;
	}
	/**
	 * @return Returns the serviceEventId.
	 */
	public String getServiceEventId() {
		return serviceEventId;
	}
	/**
	 * @param serviceEventId The serviceEventId to set.
	 */
	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}
	/**
	 * @return Returns the attendanceStatusDescription.
	 */
	public String getAttendanceStatusDescription() {
		return attendanceStatusDescription;
	}
	/**
	 * @param attendanceStatusDescription The attendanceStatusDescription to set.
	 */
	public void setAttendanceStatusDescription(String attendanceStatusDescription) {
		this.attendanceStatusDescription = attendanceStatusDescription;
	}
	/**
	 * @return Returns the serviceEventAttendanceId.
	 */
	public String getServiceEventAttendanceId() {
		return serviceEventAttendanceId;
	}
	/**
	 * @param serviceEventAttendanceId The serviceEventAttendanceId to set.
	 */
	public void setServiceEventAttendanceId(String serviceEventAttendanceId) {
		this.serviceEventAttendanceId = serviceEventAttendanceId;
	}

	/**
	 * @return Returns the juvenileName.
	 */
	public String getJuvenileName() {
		return juvenileName;
	}
	/**
	 * @param juvenileName The juvenileName to set.
	 */
	public void setJuvenileName(String juvenileName) {
		this.juvenileName = juvenileName;
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
	 * @return Returns the programReferralId.
	 */
	public String getProgramReferralId() {
		return programReferralId;
	}
	/**
	 * @param programReferralId The programReferralId to set.
	 */
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}
	/**
	 * @return the document
	 */
	public Object getDocument() {
		return document;
	}
	/**
	 * @param document the document to set
	 */
	public void setDocument(Object document) {
		this.document = document;
	}
	/**
	 * @return the docTypeCd
	 */
	public String getDocTypeCd() {
		return docTypeCd;
	}
	/**
	 * @param docTypeCd the docTypeCd to set
	 */
	public void setDocTypeCd(String docTypeCd) {
		this.docTypeCd = docTypeCd;
	}
	/**
	 * @return Returns the serviceName.
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * @param serviceName The serviceName to set.
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	/**
	 * @return Returns the startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public float getCreditHours( )
	{
		return this.creditHours;
	}
	
	public void setCreditHours(float credithours){
	    
	    this.creditHours = credithours;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		if (obj==null || ! (obj instanceof ServiceEventAttendanceResponseEvent))
			return 0;		
		ServiceEventAttendanceResponseEvent resp = (ServiceEventAttendanceResponseEvent)obj;
		return ((this.getStartDate()==null || resp.getStartDate()==null) ? 0 : this.getStartDate().compareTo(resp.getStartDate()));
	}
	
	public static Comparator JuvNameComparator = new Comparator() {
		public int compare(Object attendanceResp1, Object attendanceResp2) {
			String name1 = ((ServiceEventAttendanceResponseEvent)attendanceResp1).getJuvenileName();
			String name2 = ((ServiceEventAttendanceResponseEvent)attendanceResp2).getJuvenileName();
			return name1.compareTo(name2);
		}
	};
	public String getAddlAttendees() {
		return addlAttendees;
	}
	public void setAddlAttendees(String addlAttendees) {
		this.addlAttendees = addlAttendees;
	}
	public Collection getAddlAttendeeNames() {
		return addlAttendeeNames;
	}
	public void setAddlAttendeeNames(Collection addlAttendeeNames) {
		this.addlAttendeeNames = addlAttendeeNames;
	}
	public String getExistingProgressNotes() {
		return existingProgressNotes;
	}
	public void setExistingProgressNotes(String existingProgressNotes) {
		this.existingProgressNotes = existingProgressNotes;
	}
	public String getMonthlySummary() {
		return monthlySummary;
	}
	public void setMonthlySummary(String monthlySummary) {
		this.monthlySummary = monthlySummary;
	}
	
	/**
	 * @return the startDateTime
	 */
	public Date getStartDateTime() {
		return startDateTime;
	}
	/**
	 * @param startDateTime the startDateTime to set
	 */
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	/**
	 * @return the endDateTime
	 */
	public Date getEndDateTime() {
		return endDateTime;
	}
	/**
	 * @param endDateTime the endDateTime to set
	 */
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreateJims2User() {
		return createJims2User;
	}
	public void setCreateJims2User(String createJims2User) {
		this.createJims2User = createJims2User;
	}
	public String getUpdateJims2User() {
		return updateJims2User;
	}
	public void setUpdateJims2User(String updateJims2User) {
		this.updateJims2User = updateJims2User;
	}
	
	
}
