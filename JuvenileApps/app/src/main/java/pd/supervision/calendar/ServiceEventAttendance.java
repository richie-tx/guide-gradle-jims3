package pd.supervision.calendar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import messaging.calendar.reply.AttendeeNameResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;

/**
* Properties for eventStatus
* @referencedType pd.codetable.Code
* @contextKey SERVICEEVENT_STATUS
* @detailerDoNotGenerate true
*/
public class ServiceEventAttendance extends PersistentObject {
		
	private String serviceEventId;
	private Code attendanceStatus;
	private String attendanceStatusCd;
	private String progressNotes;
	private String monthlySummary;
	private String juvenileId;
	//private Juvenile juvenile;
	private JuvenileCore juvenile;
	private String provProgramId;
	private Date startDate;
	private Date endDate;
	private String serviceName;
	private String programReferralId;
	private String programId;
	private Object document;
	private String docTypeCd;
	
	//Activity JIMS200057658 - Document number of add'l attendees
	private int addlAttendees;

	/**
	* Properties for additionalAttendeeNames
	* @referencedType pd.supervision.calendar.AdditionalAttendee
	*/
	private Collection additionalAttendeeNames = null;
	
	/**
	* @roseuid 44805C4E0016
	*/
	public ServiceEventAttendance() {
	}
	
	/**
	 * @return
	 */
	public String getServiceEventAttendanceId()
	{
		return "" + getOID();
	}

	/**
	 * @param string
	 */
	public void setServiceEventAttendanceId(String string)
	{
		if (this.serviceEventId == null || !this.serviceEventId.equals(string)) {
			markModified();
		}
		setOID(string);
		this.serviceEventId = string;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initAttendanceStatus() {
		if (attendanceStatus == null) {
			attendanceStatus = (Code) new mojo.km.persistence.Reference(attendanceStatusCd, Code.class, "SERVEVENT_ATTENDANCE_STATUS").getObject();
		}
	}
	
	/**
	* set the type reference for class member eventStatus
	*/
	public void setAttendanceStatus(Code attendanceStatus) {
		if (this.attendanceStatus == null || !this.attendanceStatus.equals(attendanceStatus)) {
			markModified();
		}
		if (attendanceStatus.getOID() == null) {
			new mojo.km.persistence.Home().bind(attendanceStatus);
		}
		setAttendanceStatusCd("" + attendanceStatus.getOID());
		attendanceStatus.setContext("SERVEVENT_ATTENDANCE_STATUS");
		this.attendanceStatus = (Code) new mojo.km.persistence.Reference(attendanceStatus).getObject();
	}
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setAttendanceStatusCd(String attendanceStatusCd) {
		if (this.attendanceStatusCd == null || !this.attendanceStatusCd.equals(attendanceStatusCd)) {
			markModified();
		}
		attendanceStatus = null;
		this.attendanceStatusCd = attendanceStatusCd;
	}
	
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getAttendanceStatus() {
		initAttendanceStatus();
		return attendanceStatus;
	}
	
	/**
	 * @return Returns the attendanceStatusCd.
	 */
	public String getAttendanceStatusCd() {
		fetch();
		return attendanceStatusCd;
	}
	/**
	* @return java.util.Iterator
	* @param attrName, attrValue
	* @roseuid 4177C29D03A9
	*/
	static public Iterator findAll(String attrName, Object attrValue) {
		IHome home = new Home();
		return home.findAll(attrName, attrValue, ServiceEventAttendance.class);

	}
	
	/**
	* @return java.util.Iterator
	* @param event
	* @roseuid 4177C29D03A9
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return (Iterator) home.findAll(event, ServiceEventAttendance.class);
	}
	/**
	 * @return Returns the juvenileId.
	 */
	public String getJuvenileId() {
		fetch();
		return juvenileId;
	}
	/**
	 * @param juvenileId The juvenileId to set.
	 */
	public void setJuvenileId(String juvenileId) {
		if (this.juvenileId == null || !this.juvenileId.equals(juvenileId)) {
			markModified();
		}
		this.juvenileId = juvenileId;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initJuvenile() {
		if (juvenile == null) {
            // juvenile = (Juvenile) new mojo.km.persistence.Reference(juvenileId, pd.juvenile.Juvenile.class).getObject();
			//juvenile = Juvenile.find(juvenileId);
			// Profile stripping fix - task 97643
			juvenile=JuvenileCore.findCore(juvenileId);
			//
		}
	}
	
	/**
	 * @return Returns the juvenile
	 */
	//public Juvenile getJuvenile() {
	// Profile stripping fix - task 97643
	public JuvenileCore getJuvenile() {
		initJuvenile();
		return juvenile;
	}

	/**
	 * @return Returns the progressNotes.
	 */
	public String getProgressNotes() {
		fetch();
		return progressNotes;
	}
	/**
	 * @param progressNotes The progressNotes to set.
	 */
	public void setProgressNotes(String progressNotes) {
		if (this.progressNotes == null || !this.progressNotes.equals(progressNotes)) {
			markModified();
		}
		this.progressNotes = progressNotes;
	}
	/**
	 * @return Returns the serviceEventId.
	 */
	public String getServiceEventId() {
		fetch();
		return serviceEventId;
	}
	/**
	 * @param serviceEventId The serviceEventId to set.
	 */
	public void setServiceEventId(String serviceEventId) {
		if (this.serviceEventId == null || !this.serviceEventId.equals(serviceEventId)) {
			markModified();
		}
		this.serviceEventId = serviceEventId;
	}

	/**
	 * 
	 */
	public ServiceEventAttendanceResponseEvent getServiceAttendanceResponseEvent() {
		ServiceEventAttendanceResponseEvent servResp = new ServiceEventAttendanceResponseEvent();
		
		servResp.setServiceEventAttendanceId(this.getServiceEventAttendanceId());
		servResp.setJuvenileId(this.getJuvenileId());
		servResp.setServiceEventId(this.getServiceEventId());
		//second check added to fix the application blowing out when its blank
		if (this.getAttendanceStatusCd()!=null && !this.getAttendanceStatusCd().equals("")){			
			servResp.setAttendanceStatusCd(this.getAttendanceStatus().getCode());
			servResp.setAttendanceStatusDescription(this.getAttendanceStatus().getDescription());
		}
		servResp.setProgressNotes(this.getProgressNotes());
		servResp.setExistingProgressNotes(this.getProgressNotes());
		servResp.setMonthlySummary(this.getMonthlySummary());
		servResp.setProgramId(this.getProgramId());
		servResp.setServiceName(this.getServiceName());
		servResp.setStartDate(this.getStartDate());
		servResp.setStartDateTime(this.getStartDate());
		servResp.setEndDateTime(this.getEndDate());
		servResp.setProgramReferralId(this.getProgramReferralId());
		servResp.setDocTypeCd(this.getDocTypeCd());
		servResp.setDocument(this.getDocument());
		if(this.getCreateUserID() != null){
			servResp.setCreateUser(this.getCreateUserID());
		}
		if(this.getCreateTimestamp() != null){
			servResp.setCreateDate(new Date(this.getCreateTimestamp().getTime()));
		}
		if(this.getUpdateUserID() != null){
			servResp.setUpdateUser(this.getUpdateUserID());
		}
		if(this.getUpdateTimestamp() != null){
			servResp.setUpdateDate(new Date(this.getUpdateTimestamp().getTime()));
		}
		if(this.getCreateJIMS2UserID() != null){
			servResp.setCreateJims2User(this.getCreateJIMS2UserID());
		}
		if(this.getUpdateJIMS2UserID() != null){
			servResp.setUpdateJims2User(this.getUpdateJIMS2UserID());
		}
		if(this.addlAttendees !=0)
			servResp.setAddlAttendees(""+this.addlAttendees);
		else
			servResp.setAddlAttendees("");
		Iterator iter = getAddlAttendeeNames().iterator();
		HashMap names = new HashMap();
		while(iter.hasNext())
		{
			AdditionalAttendee attendee = (AdditionalAttendee)iter.next();
			AttendeeNameResponseEvent attendeeName = new AttendeeNameResponseEvent();
			attendeeName.setAddlAttendeeId(attendee.getOID());
			attendeeName.setFirstName(attendee.getFirstName());
			attendeeName.setMiddleName(attendee.getMiddleName());
			attendeeName.setLastName(attendee.getLastName());
			if( names.get(attendeeName.getFormattedName()) == null )
			{
				names.put( attendeeName.getFormattedName(), attendeeName );
			}
			//servResp.getAddlAttendeeNames().add(attendeeName);
		}
		if(!names.isEmpty())
		{
			List namesList = new ArrayList();
			namesList.addAll(names.values());
			Collections.sort(namesList);
			servResp.setAddlAttendeeNames(namesList);
		}
		return servResp;
	}
	/**
	 * @return Returns the provProgramId.
	 */
	public String getProvProgramId() {
		fetch();
		return provProgramId;
	}
	/**
	 * @param provProgramId The provProgramId to set.
	 */
	public void setProvProgramId(String provProgramId) {
		if (this.provProgramId == null || !this.provProgramId.equals(provProgramId)) {
			markModified();
		}
		this.provProgramId = provProgramId;
	}

	/**
	 * @return Returns the startDate.
	 */
	public Date getStartDate() {
		fetch();
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(Date startDate) {
		if (this.startDate == null || !this.startDate.equals(startDate)) {
			markModified();
		}
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		fetch();
		return endDate;
	}

	public void setEndDate(Date endDate) {
		if (this.endDate == null || !this.endDate.equals(endDate)) {
			markModified();
		}
		this.endDate = endDate;
	}
	/**
	 * @return Returns the serviceName.
	 */
	public String getServiceName() {
		fetch();
		return serviceName;
	}
	/**
	 * @param serviceName The serviceName to set.
	 */
	public void setServiceName(String serviceName) {
		if (this.serviceName == null || !this.serviceName.equals(serviceName)) {
			markModified();
		}
		this.serviceName = serviceName;
	}
	/**
	 * @return Returns the programId.
	 */
	public String getProgramId() {
		fetch();
		return programId;
	}
	/**
	 * @param programId The programId to set.
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
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
	 * @return Returns the programReferralId.
	 */
	public String getProgramReferralId() {
		fetch();
		return programReferralId;
	}
	/**
	 * @param programReferralId The programReferralId to set.
	 */
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}
	public void createOID() {
		new Home().bind(this);
	}

	public int getAddlAttendees() {
		fetch();
		return addlAttendees;
	}

	public void setAddlAttendees(int addlAttendees) {
		markModified();
		this.addlAttendees = addlAttendees;
	}	
	/**
	* Initialize class relationship implementation for pd.supervision.calendar.AdditionalAttendee
	*/
	private void initAddlAttendeeNames() 
	{
		if (additionalAttendeeNames == null) 
		{
			if (this.getOID() == null) 
			{
				new mojo.km.persistence.Home().bind(this);
			}
			additionalAttendeeNames = new mojo.km.persistence.ArrayList(AdditionalAttendee.class, "serviceEventAttendanceId", "" + getOID());
		}
	}
	
	/**
	* returns a collection of pd.supervision.calendar.AdditionalAttendee
	*/
	public Collection getAddlAttendeeNames()
	{
		initAddlAttendeeNames();
		return additionalAttendeeNames;
	}
	
	/**
	* insert a pd.supervision.calendar.AdditionalAttendee into class relationship collection.
	*/
	public void insertAddlAttendeeNames(AdditionalAttendee anObject)
	{
		initAddlAttendeeNames();
		additionalAttendeeNames.add(anObject);
	}

	public String getMonthlySummary() {
		fetch();
		return monthlySummary;
	}

	public void setMonthlySummary(String monthlySummary) {
		if (this.monthlySummary == null || !this.monthlySummary.equals(monthlySummary)) {
			markModified();
		}
		this.monthlySummary = monthlySummary;
	}
}
