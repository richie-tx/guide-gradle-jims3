// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\SaveCSFVEventEvent.java

package messaging.cscdcalendar;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveCSFVEventEvent extends RequestEvent {

	private String purposeCd;

	private String fvTypeCd;

	private String addressTypeCd;

	private String sexOffendarTypeCd;

	private String measureTypeCd;

	private String contactMethodCd;

	private String fvOutcomeCd;

	private String rescheduleFVEventId;

	private String deleteFVId;

	private String fvIteneraryId;

	private String csEventId;

	private String eventTypeId;

	private String partyId; // superviseeId

	private String positionId;

	private String eventName;

	private Date eventDate;

	private String comments;

	private String conditions;

	private Date startTime;

	private Date endTime;

	private String streetNum;

	private String streetName;

	private String streetType;

	private String aptNum;

	private String city;

	private String state;

	private String zipcode;

	private String county;

	private String altPhone;

	private String addrDesc;

	private String caution;

	private String narrative;

	private String keyMap;

	private String sequenceNum;

	private String fvEventid;

	private String otherPurpose;

	private boolean create;

	private boolean update;

	private boolean reschedule;

	private boolean results;

	private boolean delete;

	private String agencyId;
	
	private String assignStaffPos_Id;

	private String associateName;

	private String[] associateId;

	private String currentContext;;
	
	private String resultUserId;
	
	private String resultPositionId;

	/**
	 * @roseuid 479A0E21003B
	 */
	public SaveCSFVEventEvent() {

	}

	/**
	 * @return Returns the addrDesc.
	 */
	public String getAddrDesc() {
		return addrDesc;
	}

	/**
	 * @param addrDesc
	 *            The addrDesc to set.
	 */
	public void setAddrDesc(String addrDesc) {
		this.addrDesc = addrDesc;
	}

	/**
	 * @return Returns the altPhone.
	 */
	public String getAltPhone() {
		return altPhone;
	}

	/**
	 * @param altPhone
	 *            The altPhone to set.
	 */
	public void setAltPhone(String altPhone) {
		this.altPhone = altPhone;
	}

	/**
	 * @return Returns the aptNum.
	 */
	public String getAptNum() {
		return aptNum;
	}

	/**
	 * @param aptNum
	 *            The aptNum to set.
	 */
	public void setAptNum(String aptNum) {
		this.aptNum = aptNum;
	}

	/**
	 * @return Returns the caution.
	 */
	public String getCaution() {
		return caution;
	}

	/**
	 * @param caution
	 *            The caution to set.
	 */
	public void setCaution(String caution) {
		this.caution = caution;
	}

	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return Returns the conditions.
	 */
	public String getConditions() {
		return conditions;
	}

	/**
	 * @param conditions
	 *            The conditions to set.
	 */
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	/**
	 * @return Returns the county.
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * @param county
	 *            The county to set.
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**
	 * @return Returns the create.
	 */
	public boolean isCreate() {
		return create;
	}

	/**
	 * @param create
	 *            The create to set.
	 */
	public void setCreate(boolean create) {
		this.create = create;
	}

	/**
	 * @return Returns the delete.
	 */
	public boolean isDelete() {
		return delete;
	}

	/**
	 * @param delete
	 *            The delete to set.
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	/**
	 * @return Returns the deleteFVId.
	 */
	public String getDeleteFVId() {
		return deleteFVId;
	}

	/**
	 * @param deleteFVId
	 *            The deleteFVId to set.
	 */
	public void setDeleteFVId(String deleteFVId) {
		this.deleteFVId = deleteFVId;
	}

	/**
	 * @return Returns the eventDate.
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate
	 *            The eventDate to set.
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * @return Returns the eventName.
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName
	 *            The eventName to set.
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * @return Returns the narrative.
	 */
	public String getNarrative() {
		return narrative;
	}

	/**
	 * @param narrative
	 *            The narrative to set.
	 */
	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	/**
	 * @return Returns the reschedule.
	 */
	public boolean isReschedule() {
		return reschedule;
	}

	/**
	 * @param reschedule
	 *            The reschedule to set.
	 */
	public void setReschedule(boolean reschedule) {
		this.reschedule = reschedule;
	}

	/**
	 * @return Returns the rescheduleFVEventId.
	 */
	public String getRescheduleFVEventId() {
		return rescheduleFVEventId;
	}

	/**
	 * @param rescheduleFVEventId
	 *            The rescheduleFVEventId to set.
	 */
	public void setRescheduleFVEventId(String rescheduleFVEventId) {
		this.rescheduleFVEventId = rescheduleFVEventId;
	}

	/**
	 * @return Returns the results.
	 */
	public boolean isResults() {
		return results;
	}

	/**
	 * @param results
	 *            The results to set.
	 */
	public void setResults(boolean results) {
		this.results = results;
	}

	/**
	 * @return Returns the endTime.
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            The endTime to set.
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return Returns the startTime.
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            The startTime to set.
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return Returns the streetName.
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName
	 *            The streetName to set.
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return Returns the streetNum.
	 */
	public String getStreetNum() {
		return streetNum;
	}

	/**
	 * @param streetNum
	 *            The streetNum to set.
	 */
	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
	}

	/**
	 * @return Returns the streetType.
	 */
	public String getStreetType() {
		return streetType;
	}

	/**
	 * @param streetType
	 *            The streetType to set.
	 */
	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}

	/**
	 * @return Returns the update.
	 */
	public boolean isUpdate() {
		return update;
	}

	/**
	 * @param update
	 *            The update to set.
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}

	/**
	 * @return Returns the zipcode.
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode
	 *            The zipcode to set.
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return Returns the csEventId.
	 */
	public String getCsEventId() {
		return csEventId;
	}

	/**
	 * @param csEventId
	 *            The csEventId to set.
	 */
	public void setCsEventId(String csEventId) {
		this.csEventId = csEventId;
	}

	/**
	 * @return Returns the fvIteneraryId.
	 */
	public String getFvIteneraryId() {
		return fvIteneraryId;
	}

	/**
	 * @param fvIteneraryId
	 *            The fvIteneraryId to set.
	 */
	public void setFvIteneraryId(String fvIteneraryId) {
		this.fvIteneraryId = fvIteneraryId;
	}

	/**
	 * @return Returns the eventTypeId.
	 */
	public String getEventTypeId() {
		return eventTypeId;
	}

	/**
	 * @param eventTypeId
	 *            The eventTypeId to set.
	 */
	public void setEventTypeId(String eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	/**
	 * @return Returns the partyId.
	 */
	public String getPartyId() {
		return partyId;
	}

	/**
	 * @param partyId
	 *            The partyId to set.
	 */
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	/**
	 * @return Returns the positionId.
	 */
	public String getPositionId() {
		return positionId;
	}

	/**
	 * @param positionId
	 *            The positionId to set.
	 */
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	/**
	 * @return Returns the addressTypeCd.
	 */
	public String getAddressTypeCd() {
		return addressTypeCd;
	}

	/**
	 * @param addressTypeCd
	 *            The addressTypeCd to set.
	 */
	public void setAddressTypeCd(String addressTypeCd) {
		this.addressTypeCd = addressTypeCd;
	}

	/**
	 * @return Returns the keyMap.
	 */
	public String getKeyMap() {
		return keyMap;
	}

	/**
	 * @param keyMap
	 *            The keyMap to set.
	 */
	public void setKeyMap(String keyMap) {
		this.keyMap = keyMap;
	}

	/**
	 * @return Returns the measureTypeCd.
	 */
	public String getMeasureTypeCd() {
		return measureTypeCd;
	}

	/**
	 * @param measureTypeCd
	 *            The measureTypeCd to set.
	 */
	public void setMeasureTypeCd(String measureTypeCd) {
		this.measureTypeCd = measureTypeCd;
	}

	/**
	 * @return Returns the sequenceNum.
	 */
	public String getSequenceNum() {
		return sequenceNum;
	}

	/**
	 * @param sequenceNum
	 *            The sequenceNum to set.
	 */
	public void setSequenceNum(String sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	/**
	 * @return Returns the sexOffendarTypeCd.
	 */
	public String getSexOffendarTypeCd() {
		return sexOffendarTypeCd;
	}

	/**
	 * @param sexOffendarTypeCd
	 *            The sexOffendarTypeCd to set.
	 */
	public void setSexOffendarTypeCd(String sexOffendarTypeCd) {
		this.sexOffendarTypeCd = sexOffendarTypeCd;
	}

	/**
	 * @return Returns the fvTypeCd.
	 */
	public String getFvTypeCd() {
		return fvTypeCd;
	}

	/**
	 * @param fvTypeCd
	 *            The fvTypeCd to set.
	 */
	public void setFvTypeCd(String fvTypeCd) {
		this.fvTypeCd = fvTypeCd;
	}

	/**
	 * @return Returns the contactMethodCd.
	 */
	public String getContactMethodCd() {
		return contactMethodCd;
	}

	/**
	 * @param contactMethodCd
	 *            The contactMethodCd to set.
	 */
	public void setContactMethodCd(String contactMethodCd) {
		this.contactMethodCd = contactMethodCd;
	}

	/**
	 * @return Returns the purposeCd.
	 */
	public String getPurposeCd() {
		return purposeCd;
	}

	/**
	 * @param purposeCd
	 *            The purposeCd to set.
	 */
	public void setPurposeCd(String purposeCd) {
		this.purposeCd = purposeCd;
	}

	/**
	 * @return Returns the fvOutcomeCd.
	 */
	public String getFvOutcomeCd() {
		return fvOutcomeCd;
	}

	/**
	 * @param fvOutcomeCd
	 *            The fvOutcomeCd to set.
	 */
	public void setFvOutcomeCd(String fvOutcomeCd) {
		this.fvOutcomeCd = fvOutcomeCd;
	}

	/**
	 * @return Returns the fvEventid.
	 */
	public String getFvEventid() {
		return fvEventid;
	}

	/**
	 * @param fvEventid
	 *            The fvEventid to set.
	 */
	public void setFvEventid(String fvEventid) {
		this.fvEventid = fvEventid;
	}

	public String getOtherPurpose() {
		return otherPurpose;
	}

	public void setOtherPurpose(String otherPurpose) {
		this.otherPurpose = otherPurpose;
	}

	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}

	/**
	 * @param agencyId
	 *            The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
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
	 * @return Returns the associateName.
	 */
	public String getAssociateName() {
		return associateName;
	}

	/**
	 * @param associateName
	 *            The associateName to set.
	 */
	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}

	/**
	 * @return Returns the associateId.
	 */
	public String[] getAssociateId() {
		return associateId;
	}

	/**
	 * @param associateId
	 *            The associateId to set.
	 */
	public void setAssociateId(String[] associateId) {
		this.associateId = associateId;
	}

	/**
	 * @return Returns the currentContext.
	 */
	public String getCurrentContext() {
		return currentContext;
	}

	/**
	 * @param currentContext
	 *            The currentContext to set.
	 */
	public void setCurrentContext(String currentContext) {
		this.currentContext = currentContext;
	}
	
	
	/**
	 * @return Returns the resultPositionId.
	 */
	public String getResultPositionId() {
		return resultPositionId;
	}
	/**
	 * @param resultPositionId The resultPositionId to set.
	 */
	public void setResultPositionId(String resultPositionId) {
		this.resultPositionId = resultPositionId;
	}
	/**
	 * @return Returns the resultUserId.
	 */
	public String getResultUserId() {
		return resultUserId;
	}
	/**
	 * @param resultUserId The resultUserId to set.
	 */
	public void setResultUserId(String resultUserId) {
		this.resultUserId = resultUserId;
	}
}
