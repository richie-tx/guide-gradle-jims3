package pd.supervision.cscdcalendar;

import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import pd.codetable.supervision.CSEventType;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.contact.user.UserProfile;
import pd.codetable.supervision.SupervisionCode;
import java.util.Date;
import java.util.Iterator;

import pd.contact.party.Party;

/**
 * 
 * @author Nagalla
 TODO To change the template for this generated type comment go to
 Window - Preferences - Java - Code Style - Code Templates
 */
public class CSEvent extends PersistentObject
{
	/**
	 * Properties for type
	 * @referencedType pd.codetable.supervision.CSEventType
	 * @detailerDoNotGenerate false
	 */
	private CSEventType eventType = null;
	private String eventTypeId;
	private Date eventDate;
	private Date startTime;
	private Date endTime;
	private String location;
	/**
	 * Properties for type
	 * @referencedType pd.codetable.supervision.SupervisionCode
	 * @detailerDoNotGenerate false
	 * @contextKey CAL_EVENT_STATUS
	 */
	private SupervisionCode status = null;
	private String statusId;
	private String createdBy;
	private String timeZone;
	private String eventName;
	private String phoneNumber;
	private String narrative;
	private Date markedForDeleteOn;
	private String otherEventType;
	private String purpose;
	private String outCome;
	private String rescheduleReason;
	/**
	 * Properties for officerPosition
	 * @detailerDoNotGenerate false
	 * @referencedType pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	private CSCDStaffPosition position = null;
	private String positionId;
	/**
	 * Properties for party
	 * @useParent true
	 * @detailerDoNotGenerate true
	 */
	private Party party;
	private String partyId;
	/**
	 * Properties for assigned staff position 
	 * @useParent true
	 * @detailerDoNotGenerate true
	 */
	private String assignStaffPos_Id;
	/**
	 * Properties for resultUser
	 * @detailerDoNotGenerate false
	 * @referencedType pd.contact.user.UserProfile
	 */
	private UserProfile resultUser = null;
	private String resultUserId;
	/**
	 * Properties for resultPosition
	 * @detailerDoNotGenerate false
	 * @referencedType pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	private CSCDStaffPosition resultPosition = null;
	private String resultPositionId;
	
	/**
	 * 
	 * @roseuid 479A0E510154
	 */
	public CSEvent() {
	}

	/**
	 * Set the reference value to class :: CSEventType
	 */
	public void setEventTypeId(String eventTypeId)
	{
		if (this.eventTypeId == null || !this.eventTypeId.equals(eventTypeId))
		{
			markModified();
		}
		this.eventTypeId = null;
		this.eventTypeId = eventTypeId;
	}

	/**
	 * Get the reference value to class :: CSEventType
	 */
	public String getEventTypeId()
	{
		fetch();
		return eventTypeId;
	}

	/**
	 * Initialize class relationship to class CSEventType
	 */
	private void initEventType()
	{
		if (eventType == null)
		{
//			eventType = (CSEventType) new mojo.km.persistence.Reference(eventTypeId,
//					CSEventType.class).getObject();
			CSEventType csType = CSEventType.find("code", eventTypeId);
			this.eventType = (CSEventType) new mojo.km.persistence.Reference(csType)
			.getObject();
		}
	}

	/**
	 * Gets referenced type CSEventType
	 */
	public CSEventType getEventType()
	{
		initEventType();
		return eventType;
	}

	/**
	 * set the type reference for class member eventType
	 */
	public void setEventType(CSEventType eventType)
	{
		if (this.eventType == null || !this.eventType.equals(eventType))
		{
			markModified();
		}
//		if (eventType.getOID() == null)
//		{
//			new mojo.km.persistence.Home().bind(eventType);
//		}
//		setEventTypeId("" + eventType.getOID());
//		this.eventType = (CSEventType) new mojo.km.persistence.Reference(eventType)
//				.getObject();
		setEventTypeId(eventType.getCode());
		this.eventType = (CSEventType) new mojo.km.persistence.Reference(eventType)
				.getObject();
	}

	/**
	 * Set the reference value to class :: SupervisionCode
	 */
	public void setStatusId(String statusId)
	{
		if (this.statusId == null || !this.statusId.equals(statusId))
		{
			markModified();
		}
		this.statusId = null;
		this.statusId = statusId;
	}

	/**
	 * Get the reference value to class :: SupervisionCode
	 */
	public String getStatusId()
	{
		fetch();
		return statusId;
	}

	/**
	 * Initialize class relationship to class SupervisionCode
	 */
	private void initStatus()
	{
		if (status == null)
		{
//			status = (SupervisionCode) new mojo.km.persistence.Reference(statusId,
//					SupervisionCode.class, "CAL_EVENT_STATUS").getObject();
			status = PDSupervisionCodeHelper.getSupervisionCodeByValue(
//	        		PDSecurityHelper.getUserAgencyId(), 
					PDCodeTableConstants.CSCD_AGENCY,
					PDCodeTableConstants.CAL_EVENT_STATUS, statusId);
		}
	}

	/**
	 * Gets referenced type SupervisionCode
	 */
	public SupervisionCode getStatus()
	{
		initStatus();
		return status;
	}

	/**
	 * set the type reference for class member status
	 */
	public void setStatus(SupervisionCode status)
	{
		if (this.status == null || !this.status.equals(status))
		{
			markModified();
		}
//		if (status.getOID() == null)
//		{
//			new mojo.km.persistence.Home().bind(status);
//		}
//		setStatusId("" + status.getOID());
//		status.setContext("CAL_EVENT_STATUS");
//		this.status = (SupervisionCode) new mojo.km.persistence.Reference(status).getObject();
		setStatusId(status.getCode());
		status.setContext("CAL_EVENT_STATUS");
		this.status = (SupervisionCode) new mojo.km.persistence.Reference(status).getObject();

	}
	
	/**
	 * @return Returns the party.
	 */
	public Party getParty()
	{
		fetch();
		initParty();
		return party;
	}

	/**
	 * @return Returns the partyId.
	 */
	public String getPartyId()
	{
		fetch();
		return partyId;
	}

	/**
	 * Initialize class relationship to class Party
	 */
	private void initParty()
	{
		if (party == null)
		{
//			party = (Party) new mojo.km.persistence.Reference(
//					partyId,
//						Party.class,
//						(mojo.km.persistence.PersistentObject) this,
//						"party").getObject();
			party = (Party) new mojo.km.persistence.Reference(
					partyId, Party.class).getObject();
		}
	}
	
	/**
	 * @param aParty The party to set.
	 */
	public void setParty(Party aParty)
	{
		if (this.party == null || !this.party.equals(aParty))
		{
			markModified();
		}
		setPartyId(""+aParty.getOID());
		this.party = (Party) new mojo.km.persistence.Reference(aParty).getObject();
	}

	/**
	 * @param aPartyId  The partyId to set.
	 */
	public void setPartyId(String aPartyId)
	{
		if (partyId == null || !partyId.equals(aPartyId))
		{
			markModified();
		}
		this.partyId = null;
		this.partyId = aPartyId;
	}

	/**
	 * @return the assignStaffPos_Id
	 */
	public String getAssignStaffPos_Id() {
		return assignStaffPos_Id;
	}

	/**
	 * @param assignStaffPos_Id the assignStaffPos_Id to set
	 */
	public void setAssignStaffPos_Id(String assignStaffPos_Id) {
		this.assignStaffPos_Id = assignStaffPos_Id;
	}

	/**
	 * Set the reference value to class :: CSCDStaffPosition
	 */
	public void setPositionId(String positionId)
	{
		if (this.positionId == null || !this.positionId.equals(positionId))
		{
			markModified();
		}
		this.positionId = null;
		this.positionId = positionId;
	}

	/**
	 * Get the reference value to class :: CSCDStaffPosition
	 */
	public String getPositionId()
	{
		fetch();
		return positionId;
	}

	/**
	 * Initialize class relationship to class CSCDStaffPosition
	 */
	private void initPosition()
	{
		if (position == null)
		{
			position = (CSCDStaffPosition) new mojo.km.persistence.Reference(
					positionId, CSCDStaffPosition.class).getObject();
		}
	}

	/**
	 * Gets referenced type CSCDStaffPosition
	 */
	public CSCDStaffPosition getPosition()
	{
		initPosition();
		return position;
	}

	/**
	 * set the type reference for class member position
	 */
	public void setPosition(CSCDStaffPosition position)
	{
		if (this.position == null || !this.position.equals(position))
		{
			markModified();
		}
//		if (position.getOID() == null)
//		{
//			new mojo.km.persistence.Home().bind(position);
//		}
		setPositionId("" + position.getOID());
		this.position = (CSCDStaffPosition) new mojo.km.persistence.Reference(
				position).getObject();
	}

	/**
	 * Set the reference value to class :: UserProfile
	 */
	public void setResultUserId(String resultUserId)
	{
		if (this.resultUserId == null || !this.resultUserId.equals(resultUserId))
		{
			markModified();
		}
		this.resultUserId = null;
		this.resultUserId = resultUserId;
	}

	/**
	 * Get the reference value to class :: UserProfile
	 */
	public String getResultUserId()
	{
		fetch();
		return resultUserId;
	}

	/**
	 * Initialize class relationship to class UserProfile
	 */
	private void initResultUser()
	{
		if (resultUser == null)
		{
			resultUser = (UserProfile) new mojo.km.persistence.Reference(resultUserId,
					UserProfile.class).getObject();
		}
	}

	/**
	 * Gets referenced type UserProfile
	 */
	public UserProfile getResultUser()
	{
		initResultUser();
		return resultUser;
	}

	/**
	 * set the type reference for class member resultUser
	 */
	public void setResultUser(UserProfile resultUser)
	{
		if (this.resultUser == null || !this.resultUser.equals(resultUser))
		{
			markModified();
		}
//		if (resultUser.getOID() == null)
//		{
//			new mojo.km.persistence.Home().bind(resultUser);
//		}
		setResultUserId("" + resultUser.getOID());
		this.resultUser = (UserProfile) new mojo.km.persistence.Reference(resultUser).getObject();
	}

	/**
	 * Set the reference value to class :: CSCDStaffPosition
	 */
	public void setResultPositionId(String resultPositionId)
	{
		if (this.resultPositionId == null || !this.resultPositionId.equals(resultPositionId))
		{
			markModified();
		}
		this.resultPositionId = null;
		this.resultPositionId = resultPositionId;
	}

	/**
	 * Get the reference value to class :: CSCDStaffPosition
	 */
	public String getResultPositionId()
	{
		fetch();
		return resultPositionId;
	}

	/**
	 * Initialize class relationship to class CSCDStaffPosition
	 */
	private void initResultPosition()
	{
		if (resultPosition == null)
		{
			resultPosition = (CSCDStaffPosition) new mojo.km.persistence.Reference(
					resultPositionId, CSCDStaffPosition.class).getObject();
		}
	}

	/**
	 * Gets referenced type CSCDStaffPosition
	 */
	public CSCDStaffPosition getResultPosition()
	{
		initResultPosition();
		return resultPosition;
	}

	/**
	 * set the type reference for class member resultPosition
	 */
	public void setResultPosition(CSCDStaffPosition resultPosition)
	{
		if (this.resultPosition == null || !this.resultPosition.equals(resultPosition))
		{
			markModified();
		}
//		if (resultPosition.getOID() == null)
//		{
//			new mojo.km.persistence.Home().bind(resultPosition);
//		}
		setResultPositionId("" + resultPosition.getOID());
		this.resultPosition = (CSCDStaffPosition) new mojo.km.persistence.Reference(
				resultPosition).getObject();
	}
	
	
	
	/**
	 * @return Returns the createdBy.
	 */
	public String getCreatedBy() {
		fetch();
		return createdBy;
	}	
	/**
	 * @return Returns the endTime.
	 */
	public Date getEndTime() {
		fetch();
		return endTime;
	}
	/**
	 * @return Returns the eventName.
	 */
	public String getEventName() {
		fetch();
		return eventName;
	}
	/**
	 * @return Returns the location.
	 */
	public String getLocation() {
		fetch();
		return location;
	}
	/**
	 * @return Returns the markedForDeleteOn.
	 */
	public Date getMarkedForDeleteOn() {
		fetch();
		return markedForDeleteOn;
	}
	/**
	 * @return Returns the narrative.
	 */
	public String getNarrative() {
		fetch();
		return narrative;
	}
	/**
	 * @return Returns the otherEventType.
	 */
	public String getOtherEventType() {
		fetch();
		return otherEventType;
	}
	/**
	 * @return Returns the outCome.
	 */
	public String getOutCome() {
		fetch();
		return outCome;
	}
	/**
	 * @return Returns the phoneNumber.
	 */
	public String getPhoneNumber() {
		fetch();
		return phoneNumber;
	}
	/**
	 * @return Returns the purpose.
	 */
	public String getPurpose() {
		fetch();
		return purpose;
	}
	/**
	 * @return the rescheduleReason
	 */
	public String getRescheduleReason() {
		return rescheduleReason;
	}
	/**
	 * @return Returns the startTime.
	 */
	public Date getStartTime() {
		fetch();
		return startTime;
	}
	/**
	 * @return Returns the timeZone.
	 */
	public String getTimeZone() {
		fetch();
		return timeZone;
	}
	/**
	 * @param createdBy The createdBy to set.
	 */
	public void setCreatedBy(String createdBy) {
		if (this.createdBy == null || !this.createdBy.equals(createdBy))
		{
			markModified();
		}
		this.createdBy = null;
		this.createdBy = createdBy;
	}	
	/**
	 * @param endTime The endTime to set.
	 */
	public void setEndTime(Date endTime) {
		if (this.endTime == null || !this.endTime.equals(endTime))
		{
			markModified();
		}
		this.endTime = null;
		this.endTime = endTime;
	}
	/**
	 * @param eventName The eventName to set.
	 */
	public void setEventName(String eventName) {
		if (this.eventName == null || !this.eventName.equals(eventName))
		{
			markModified();
		}
		this.eventName = null;
		this.eventName = eventName;
	}
	/**
	 * @param location The location to set.
	 */
	public void setLocation(String location) {
		if (this.location == null || !this.location.equals(location))
		{
			markModified();
		}
		this.location = null;
		this.location = location;
	}
	/**
	 * @param markedForDeleteOn The markedForDeleteOn to set.
	 */
	public void setMarkedForDeleteOn(Date markedForDeleteOn) {
		if (this.markedForDeleteOn == null || !this.markedForDeleteOn.equals(markedForDeleteOn))
		{
			markModified();
		}
		this.markedForDeleteOn = null;
		this.markedForDeleteOn = markedForDeleteOn;
	}
	/**
	 * @param narrative The narrative to set.
	 */
	public void setNarrative(String narrative) {
		if (this.narrative == null || !this.narrative.equals(narrative))
		{
			markModified();
		}
		this.narrative = null;
		this.narrative = narrative;
	}
	/**
	 * @param otherEventType The otherEventType to set.
	 */
	public void setOtherEventType(String otherEventType) {
		if (this.otherEventType == null || !this.otherEventType.equals(otherEventType))
		{
			markModified();
		}
		this.otherEventType = null;
		this.otherEventType = otherEventType;
	}
	/**
	 * @param outCome The outCome to set.
	 */
	public void setOutCome(String outCome) {
		if (this.outCome == null || !this.outCome.equals(outCome))
		{
			markModified();
		}
		this.outCome = null;
		this.outCome = outCome;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(String phoneNumber) {
		if (this.phoneNumber == null || !this.phoneNumber.equals(phoneNumber))
		{
			markModified();
		}
		this.phoneNumber = null;
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @param purpose The purpose to set.
	 */
	public void setPurpose(String purpose) {
		if (this.purpose == null || !this.purpose.equals(purpose))
		{
			markModified();
		}
		this.purpose = null;
		this.purpose = purpose;
	}
	/**
	 * @param rescheduleReason the rescheduleReason to set
	 */
	public void setRescheduleReason(String rescheduleReason) {
		this.rescheduleReason = rescheduleReason;
	}
	/**
	 * @param startTime The startTime to set.
	 */
	public void setStartTime(Date startTime) {
		if (this.startTime == null || !this.startTime.equals(startTime))
		{
			markModified();
		}
		this.startTime = null;
		this.startTime = startTime;
	}
	/**
	 * @param timeZone The timeZone to set.
	 */
	public void setTimeZone(String timeZone) {
		if (this.timeZone == null || !this.timeZone.equals(timeZone))
		{
			markModified();
		}
		this.timeZone = null;
		this.timeZone = timeZone;
	}
	
	
	/**
	 * @return Returns the eventDate.
	 */
	public Date getEventDate() {
		fetch();
		return eventDate;
	}
	/**
	 * @param eventDate The eventDate to set.
	 */
	public void setEventDate(Date eventDate) {
		if (this.eventDate == null || !this.eventDate.equals(eventDate))
		{
			markModified();
		}
		this.eventDate = null;
		this.eventDate = eventDate;
	}
	/**
     * @return java.util.Iterator
     * @param attrName, attrValue
     * @roseuid 
     */
    static public Iterator findAll(String attrName, String attrValue) {
        IHome home = new Home();
        return home.findAll(attrName, attrValue, CSEvent.class);
    }

    /**
     * @return java.util.Iterator
     * @param event
     * @roseuid 
     */
    static public Iterator findAll(IEvent event) {
        IHome home = new Home();
        return (Iterator) home.findAll(event, CSEvent.class);
    }

    /**
     * @return CSEvent
     * @param eventId
     * @roseuid 
     */
    static public CSEvent find(String eventId) {
        IHome home = new Home();
        return (CSEvent) home.find(eventId, CSEvent.class);
    }
}
