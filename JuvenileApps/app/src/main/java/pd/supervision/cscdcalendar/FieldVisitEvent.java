// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\FieldVisitEvent.java

package pd.supervision.cscdcalendar;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import messaging.cscdcalendar.DeleteCSFVEvent;
import messaging.scheduling.RegisterTaskEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import pd.codetable.Code;
import pd.codetable.supervision.CSEventType;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.contact.party.Party;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FieldVisitEvent extends PersistentObject {

	private SupervisionCode fvPurpose;

	private String fvPurposeCd;

	private SupervisionCode fvType;

	private String fvTypeCd;

	private String comments;

	private String noteWorthyconditions;

	private String addressDescription;

	private String caution;

	private String fvOutcomeCd;

	private SupervisionCode fvOutcome;

	private String sequenceNum;

	private String contactMethodCd;

	private SupervisionCode contactMethod;

	private String sexOffendarTypeCd;

	private SupervisionCode sexOffendarType;

	private String fvMeasureTypeCd;

	private SupervisionCode fvMeasureType;

	private String fvIteneraryId;

	private String csEventId;

	private String streetNumber;

	private String streetName;

	private String aptOrSuite;

	private String city;

	private String zipcode;

	private Code streetType;

	private String streetTypeCd;

	private Code addressType;

	private String addressTypeCd;

	private Code state;

	private String stateCd;

	private Code county;

	private String countyCd;

	private String keyMap;

	private Date startDatetime;

	private Date endDatetime;

	private String alternatePhone;

	private String narrative;

	private SupervisionCode status;

	private String statusId;

	private Date markedForDeleteOn;
	
	private CSEventType eventType;
	
	private String eventTypeId;
	
	private Date eventDate;
	
	/**
	 * Properties for party
	 * 
	 * @useParent true
	 * @detailerDoNotGenerate true
	 */
	private Party party;

	private String partyId;
	
	private String otherPurpose;

	/**
	 * @roseuid 479A0E52004B
	 */
	public FieldVisitEvent() {

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
	
	private void initEventType()
	{
		if (eventType == null)
		{
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
		setEventTypeId(eventType.getCode());
		this.eventType = (CSEventType) new mojo.km.persistence.Reference(eventType)
				.getObject();
	}

	
	

	/**
	 * @return Returns the addressDescription.
	 */
	public String getAddressDescription() {
		fetch();
		return addressDescription;
	}

	/**
	 * @param addressDescription
	 *            The addressDescription to set.
	 */
	public void setAddressDescription(String addressDescription) {
		if (this.addressDescription == null || !this.addressDescription.equals(addressDescription)) {
			markModified();
		}
		this.addressDescription = addressDescription;
	}

	/**
	 * @return Returns the caution.
	 */
	public String getCaution() {
		fetch();
		return caution;
	}

	/**
	 * @param caution
	 *            The caution to set.
	 */
	public void setCaution(String caution) {
		if (this.caution == null || !this.caution.equals(caution)) {
			markModified();
		}
		this.caution = caution;
	}

	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		fetch();
		return comments;
	}

	/**
	 * @param comments
	 *            The comments to set.
	 */
	public void setComments(String comments) {
		if (this.comments == null || !this.comments.equals(comments)) {
			markModified();
		}
		this.comments = comments;
	}

	/**
	 * Initialize class relationship to class
	 * pd.codetable.supervision.SupervisionCode
	 */
	private void initContactMethod() {
		if (contactMethod == null && contactMethodCd != null && (!contactMethodCd.equals(""))) {
			contactMethod = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY,
					PDCodeTableConstants.CAL_CONTACT_METHOD, contactMethodCd);
		}
	}

	/**
	 * @return Returns the contactMethod.
	 */
	public SupervisionCode getContactMethod() {
		fetch();
		initContactMethod();
		return contactMethod;
	}

	/**
	 * @param contactMethod
	 *            The contactMethod to set.
	 */
	public void setContactMethod(SupervisionCode contactMethod) {
		if (this.contactMethod == null || !this.contactMethod.equals(contactMethod)) {
			markModified();
		}
		this.contactMethod = contactMethod;
	}

	/**
	 * @return Returns the contactMethodCd.
	 */
	public String getContactMethodCd() {
		fetch();
		return contactMethodCd;
	}

	/**
	 * @param contactMethodCd
	 *            The contactMethodCd to set.
	 */
	public void setContactMethodCd(String contactMethodCd) {
		if (this.contactMethodCd == null || !this.contactMethodCd.equals(contactMethodCd)) {
			markModified();
		}
		contactMethod = null;
		this.contactMethodCd = contactMethodCd;
	}

	/**
	 * Initialize class relationship to class
	 * pd.codetable.supervision.SupervisionCode
	 */
	private void initFvMeasureType() {
		if (fvMeasureType == null && fvMeasureTypeCd != null && (!fvMeasureTypeCd.equals(""))) {
			fvMeasureType = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY,
					PDCodeTableConstants.FV_MEASUREMENT_TYPES, fvMeasureTypeCd);
		}
	}

	/**
	 * @return Returns the fvMeasureType.
	 */
	public SupervisionCode getFvMeasureType() {
		fetch();
		initFvMeasureType();
		return fvMeasureType;
	}

	/**
	 * @param fvMeasureType
	 *            The fvMeasureType to set.
	 */
	public void setFvMeasureType(SupervisionCode fvMeasureType) {
		if (this.fvMeasureType == null || !this.fvMeasureType.equals(fvMeasureType)) {
			markModified();
		}
		this.fvMeasureType = fvMeasureType;
	}

	/**
	 * @return Returns the fvMeasureTypeCd.
	 */
	public String getFvMeasureTypeCd() {
		fetch();
		return fvMeasureTypeCd;
	}

	/**
	 * @param fvMeasureTypeCd
	 *            The fvMeasureTypeCd to set.
	 */
	public void setFvMeasureTypeCd(String fvMeasureTypeCd) {
		if (this.fvMeasureTypeCd == null || !this.fvMeasureTypeCd.equals(fvMeasureTypeCd)) {
			markModified();
		}
		fvMeasureType = null;
		this.fvMeasureTypeCd = fvMeasureTypeCd;
	}

	/**
	 * Initialize class relationship to class
	 * pd.codetable.supervision.SupervisionCode
	 */
	private void initFvPurpose() {
		if (fvPurpose == null && fvPurposeCd != null && (!fvPurposeCd.equals(""))) {
			fvPurpose = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY,
					PDCodeTableConstants.FV_PURPOSE, fvPurposeCd);
		}
	}

	/**
	 * @return Returns the fvPurpose.
	 */
	public SupervisionCode getFvPurpose() {
		fetch();
		initFvPurpose();
		return fvPurpose;
	}

	/**
	 * @param fvPurpose
	 *            The fvPurpose to set.
	 */
	public void setFvPurpose(SupervisionCode fvPurpose) {
		if (this.fvPurpose == null || !this.fvPurpose.equals(fvPurpose)) {
			markModified();
		}
		this.fvPurpose = fvPurpose;
	}

	/**
	 * @return Returns the fvPurposeCd.
	 */
	public String getFvPurposeCd() {
		fetch();
		return fvPurposeCd;
	}

	/**
	 * @param fvPurposeCd
	 *            The fvPurposeCd to set.
	 */
	public void setFvPurposeCd(String fvPurposeCd) {
		if (this.fvPurposeCd == null || !this.fvPurposeCd.equals(fvPurposeCd)) {
			markModified();
		}
		fvPurpose = null;
		this.fvPurposeCd = fvPurposeCd;
	}

	/**
	 * Initialize class relationship to class
	 * pd.codetable.supervision.SupervisionCode
	 */
	private void initFvOutcome() {
		if (fvOutcome == null && fvOutcomeCd != null && (!fvOutcomeCd.equals(""))) {
			fvOutcome = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY,
					PDCodeTableConstants.FV_OUTCOME, fvOutcomeCd);
		}
	}

	/**
	 * @return Returns the fvPurpose.
	 */
	public SupervisionCode getFvOutcome() {
		fetch();
		initFvOutcome();
		return fvOutcome;
	}

	/**
	 * @param fvPurpose
	 *            The fvPurpose to set.
	 */
	public void setFvOutcome(SupervisionCode fvOutcome) {
		if (this.fvOutcome == null || !this.fvOutcome.equals(fvOutcome)) {
			markModified();
		}
		this.fvOutcome = fvOutcome;
	}

	/**
	 * @return Returns the fvOutcomeCd.
	 */
	public String getFvOutcomeCd() {
		fetch();
		return fvOutcomeCd;
	}

	/**
	 * @param fvOutcomeCd
	 *            The fvOutcomeCd to set.
	 */
	public void setFvOutcomeCd(String fvOutcomeCd) {
		if (this.fvOutcomeCd == null || !this.fvOutcomeCd.equals(fvOutcomeCd)) {
			markModified();
		}
		fvOutcome = null;
		this.fvOutcomeCd = fvOutcomeCd;
	}

	/**
	 * Initialize class relationship to class
	 * pd.codetable.supervision.SupervisionCode
	 */
	private void initFvType() {
		if (fvType == null && fvTypeCd != null && (!fvTypeCd.equals(""))) {
			fvType = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY,
					PDCodeTableConstants.FV_TYPES, fvTypeCd);
		}
	}

	/**
	 * @return Returns the fvType.
	 */
	public SupervisionCode getFvType() {
		fetch();
		initFvType();
		return fvType;
	}

	/**
	 * @param fvType
	 *            The fvType to set.
	 */
	public void setFvType(SupervisionCode fvType) {
		if (this.fvType == null || !this.fvType.equals(fvType)) {
			markModified();
		}
		this.fvType = fvType;
	}

	/**
	 * @return Returns the fvTypeCd.
	 */
	public String getFvTypeCd() {
		fetch();
		return fvTypeCd;
	}

	/**
	 * @param fvTypeCd
	 *            The fvTypeCd to set.
	 */
	public void setFvTypeCd(String fvTypeCd) {
		if (this.fvTypeCd == null || !this.fvTypeCd.equals(fvTypeCd)) {
			markModified();
		}
		fvType = null;
		this.fvTypeCd = fvTypeCd;
	}

	/**
	 * @return Returns the noteWorthyconditions.
	 */
	public String getNoteWorthyconditions() {
		fetch();
		return noteWorthyconditions;
	}

	/**
	 * @param noteWorthyconditions
	 *            The noteWorthyconditions to set.
	 */
	public void setNoteWorthyconditions(String noteWorthyconditions) {
		if (this.noteWorthyconditions == null || !this.noteWorthyconditions.equals(noteWorthyconditions)) {
			markModified();
		}
		this.noteWorthyconditions = noteWorthyconditions;
	}

	/**
	 * @return Returns the sequenceNum.
	 */
	public String getSequenceNum() {
		fetch();
		return sequenceNum;
	}

	/**
	 * @param sequenceNum
	 *            The sequenceNum to set.
	 */
	public void setSequenceNum(String sequenceNum) {
		if (this.sequenceNum == null || !this.sequenceNum.equals(sequenceNum)) {
			markModified();
		}
		this.sequenceNum = sequenceNum;
	}

	/**
	 * Initialize class relationship to class
	 * pd.codetable.supervision.SupervisionCode
	 */

	private void initSexOffendarType() {
		if (sexOffendarType == null && sexOffendarTypeCd != null && (!sexOffendarTypeCd.equals(""))) {
			sexOffendarType = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY,
					PDCodeTableConstants.SEX_OFFENDER_TYPES, sexOffendarTypeCd);
		}
	}

	/**
	 * @return Returns the sexOffendarType.
	 */
	public SupervisionCode getSexOffendarType() {
		fetch();
		initSexOffendarType();
		return sexOffendarType;
	}

	/**
	 * @param sexOffendarType
	 *            The sexOffendarType to set.
	 */
	public void setSexOffendarType(SupervisionCode sexOffendarType) {
		if (this.sexOffendarType == null || !this.sexOffendarType.equals(sexOffendarType)) {
			markModified();
		}
		this.sexOffendarType = sexOffendarType;
	}

	/**
	 * @return Returns the sexOffendarTypeCd.
	 */
	public String getSexOffendarTypeCd() {
		fetch();
		return sexOffendarTypeCd;
	}

	/**
	 * @param sexOffendarTypeCd
	 *            The sexOffendarTypeCd to set.
	 */
	public void setSexOffendarTypeCd(String sexOffendarTypeCd) {
		if (this.sexOffendarTypeCd == null || !this.sexOffendarTypeCd.equals(sexOffendarTypeCd)) {
			markModified();
		}
		sexOffendarType = null;
		this.sexOffendarTypeCd = sexOffendarTypeCd;
	}

	/**
	 * @return Returns the csEventId.
	 */
	public String getCsEventId() {
		fetch();
		return csEventId;
	}

	/**
	 * @param csEventId
	 *            The csEventId to set.
	 */
	public void setCsEventId(String csEventId) {
		if (this.csEventId == null || !this.csEventId.equals(csEventId)) {
			markModified();
		}
		this.csEventId = csEventId;
	}

	/**
	 * @return Returns the fvIteneraryId.
	 */
	public String getFvIteneraryId() {
		fetch();
		return fvIteneraryId;
	}

	/**
	 * @param fvIteneraryId
	 *            The fvIteneraryId to set.
	 */
	public void setFvIteneraryId(String fvIteneraryId) {
		if (this.fvIteneraryId == null || !this.fvIteneraryId.equals(fvIteneraryId)) {
			markModified();
		}
		this.fvIteneraryId = fvIteneraryId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initAddressType() {
		if (addressType == null) {
			try {
				addressType = (Code) new mojo.km.persistence.Reference(addressTypeCd,
						Code.class, PDCodeTableConstants.ADDRESS_TYPE).getObject();
			} catch (Throwable t) {
				addressType = null;
			}
		}
	}

	/**
	 * @return Returns the addressType.
	 */
	public Code getAddressType() {
		fetch();
		initAddressType();
		return addressType;
	}

	/**
	 * set the type reference for class member addressType
	 * 
	 * @param addressType
	 */
	public void setAddressType(Code aAddressType) {
		if (this.addressType == null || !this.addressType.equals(aAddressType)) {
			markModified();
		}
		if (aAddressType.getOID() == null) {
			new mojo.km.persistence.Home().bind(aAddressType);
		}
		setAddressTypeCd("" + aAddressType.getOID());
		this.addressType = (Code) new mojo.km.persistence.Reference(aAddressType).getObject();
	}

	/**
	 * @return Returns the addressTypeCd.
	 */
	public String getAddressTypeCd() {
		fetch();
		return addressTypeCd;
	}

	/**
	 * @param addressTypeCd
	 *            The addressTypeCd to set.
	 */
	public void setAddressTypeCd(String addressTypeCd) {
		if (this.addressTypeCd == null || !this.addressTypeCd.equals(addressTypeCd)) {
			markModified();
		}
		addressType = null;
		this.addressTypeCd = addressTypeCd;
	}

	/**
	 * @return Returns the aptOrSuite.
	 */
	public String getAptOrSuite() {
		fetch();
		return aptOrSuite;
	}

	/**
	 * @param aptOrSuite
	 *            The aptOrSuite to set.
	 */
	public void setAptOrSuite(String aptOrSuite) {
		if (this.aptOrSuite == null || !this.aptOrSuite.equals(aptOrSuite)) {
			markModified();
		}
		this.aptOrSuite = aptOrSuite;
	}

	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		fetch();
		return city;
	}

	/**
	 * @param city
	 *            The city to set.
	 */
	public void setCity(String city) {
		if (this.city == null || !this.city.equals(city)) {
			markModified();
		}
		this.city = city;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setCountyCd(String countyCd) {
		if (this.countyCd == null || !this.countyCd.equals(countyCd)) {
			markModified();
		}
		county = null;
		this.countyCd = countyCd;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getcountyCd() {
		fetch();
		return countyCd;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initCounty() {
		if (county == null) {
			county = (Code) new mojo.km.persistence.Reference(countyCd, Code.class,
					PDCodeTableConstants.COUNTY).getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getCounty() {
		initCounty();
		return county;
	}

	/**
	 * set the type reference for class member county
	 */
	public void setCounty(Code county) {
		if (this.county == null || !this.county.equals(county)) {
			markModified();
		}
		if (county.getOID() == null) {
			new mojo.km.persistence.Home().bind(county);
		}
		setCountyCd("" + county.getOID());
		county.setContext(PDCodeTableConstants.COUNTY);
		this.county = (Code) new mojo.km.persistence.Reference(county).getObject();
	}

	/**
	 * @return Returns the keyMap.
	 */
	public String getKeyMap() {
		fetch();
		return keyMap;
	}

	/**
	 * Sets the value of the keymap property.
	 * 
	 * @param aKeymap
	 *            the new value of the keymap property
	 */
	public void setKeymap(String keyMap) {
		if (this.keyMap == null || !this.keyMap.equals(keyMap)) {
			markModified();
		}
		this.keyMap = keyMap;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initState() {
		if (state == null) {
			try {
				state = (Code) new mojo.km.persistence.Reference(stateCd, Code.class,
						PDCodeTableConstants.STATE_ABBR).getObject();
			} catch (Throwable t) {
				state = null;
			}
		}
	}

	/**
	 * @return Returns the state.
	 */
	public Code getState() {
		fetch();
		initState();
		return state;
	}

	/**
	 * set the type reference for class member state
	 * 
	 * @param stateCode
	 */
	public void setState(Code aState) {
		if (this.state == null || !this.state.equals(aState)) {
			markModified();
		}
		if (aState.getOID() == null) {
			new mojo.km.persistence.Home().bind(aState);
		}
		setStateCd("" + aState.getOID());
		this.state = (Code) new mojo.km.persistence.Reference(aState).getObject();
	}

	/**
	 * @return Returns the stateCd.
	 */
	public String getStateCd() {
		fetch();
		return stateCd;
	}

	/**
	 * @param stateCd
	 *            The stateCd to set.
	 */
	public void setStateCd(String stateCd) {
		if (this.stateCd == null || !this.stateCd.equals(stateCd)) {
			markModified();
		}
		state = null;
		this.stateCd = stateCd;
	}

	/**
	 * @return Returns the streetName.
	 */
	public String getStreetName() {
		fetch();
		return streetName;
	}

	/**
	 * @param streetName
	 *            The streetName to set.
	 */
	public void setStreetName(String streetName) {
		if (this.streetName == null || !this.streetName.equals(streetName)) {
			markModified();
		}
		this.streetName = streetName;
	}

	/**
	 * @return Returns the streetNumber.
	 */
	public String getStreetNumber() {
		fetch();
		return streetNumber;
	}

	/**
	 * @param streetNumber
	 *            The streetNumber to set.
	 */
	public void setStreetNumber(String streetNumber) {
		if (this.streetNumber == null || !this.streetNumber.equals(streetNumber)) {
			markModified();
		}
		this.streetNumber = streetNumber;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initStreetType() {
		if (streetType == null) {
			try {
				streetType = (Code) new mojo.km.persistence.Reference(streetTypeCd,
						Code.class, PDCodeTableConstants.STREET_TYPE).getObject();
			} catch (Throwable t) {
				streetType = null;
			}
		}
	}

	/**
	 * @return Returns the streetType.
	 */
	public Code getStreetType() {
		fetch();
		initStreetType();
		return streetType;
	}

	/**
	 * set the type reference for class member streetType
	 * 
	 * @param streetTypeCode
	 */
	public void setStreetType(Code aStreetType) {
		if (this.streetType == null || !this.streetType.equals(aStreetType)) {
			markModified();
		}
		if (aStreetType.getOID() == null) {
			new mojo.km.persistence.Home().bind(aStreetType);
		}
		setStreetTypeCd("" + aStreetType.getOID());
		this.streetType = (Code) new mojo.km.persistence.Reference(aStreetType).getObject();
	}

	/**
	 * @return Returns the streetTypeCd.
	 */
	public String getStreetTypeCd() {
		fetch();
		return streetTypeCd;
	}

	/**
	 * @param streetTypeCd
	 *            The streetTypeCd to set.
	 */
	public void setStreetTypeCd(String streetTypeCd) {
		if (this.streetTypeCd == null || !this.streetTypeCd.equals(streetTypeCd)) {
			markModified();
		}
		streetType = null;
		this.streetTypeCd = streetTypeCd;
	}

	/**
	 * @return Returns the zipcode.
	 */
	public String getZipcode() {
		fetch();
		return zipcode;
	}

	/**
	 * @param zipcode
	 *            The zipcode to set.
	 */
	public void setZipcode(String zipcode) {
		if (this.zipcode == null || !this.zipcode.equals(zipcode)) {
			markModified();
		}
		this.zipcode = zipcode;
	}

	/**
	 * @return Returns the endDatetime.
	 */
	public Date getEndDatetime() {
		fetch();
		return endDatetime;
	}

	/**
	 * @param endDatetime
	 *            The endDatetime to set.
	 */
	public void setEndDatetime(Date endDatetime) {
		if (this.endDatetime == null || !this.endDatetime.equals(endDatetime)) {
			markModified();
		}
		this.endDatetime = endDatetime;
	}

	/**
	 * @return Returns the startDatetime.
	 */
	public Date getStartDatetime() {
		fetch();
		return startDatetime;
	}

	/**
	 * @param startDatetime
	 *            The startDatetime to set.
	 */
	public void setStartDatetime(Date startDatetime) {
		if (this.startDatetime == null || !this.startDatetime.equals(startDatetime)) {
			markModified();
		}
		this.startDatetime = startDatetime;
	}

	
	
	public Date getEventDate() {
		fetch();
		return eventDate;
	}
	
	public void setEventDate(Date eventDate) {
		if (this.eventDate == null || !this.eventDate.equals(eventDate)) {
			markModified();
		}
		this.eventDate = eventDate;
	}
	/**
	 * Initialize class relationship to class pd.contact.party.Party
	 */
	private void initParty() {
		if (party == null) {
			try {
				party = (Party) new mojo.km.persistence.Reference(partyId,
						Party.class).getObject();
			} catch (Throwable t) {
				party = null;
			}
		}
	}

	/**
	 * Gets referenced type pd.contact.party.Party
	 * 
	 * @return pd.contact.party.Party
	 */
	public Party getParty() {
		fetch();
		initParty();
		return party;
	}

	/**
	 * set the type reference for class member party
	 * 
	 * @param aParty
	 */
	public void setParty(Party aParty) {
		if (this.party == null || !this.party.equals(aParty)) {
			markModified();
		}
		if (aParty.getOID() == null) {
			new mojo.km.persistence.Home().bind(aParty);
		}
		setPartyId("" + aParty.getOID());
		this.party = (Party) new mojo.km.persistence.Reference(aParty).getObject();
	}

	/**
	 * @param aPartyId
	 *            The partyId to set.
	 */
	public void setPartyId(String aPartyId) {
		if (this.partyId == null || !this.partyId.equals(aPartyId)) {
			markModified();
		}
		party = null;
		this.partyId = aPartyId;
	}

	/**
	 * @return Returns the partyId.
	 */
	public String getPartyId() {
		fetch();
		return partyId;
	}

	/**
	 * @return Returns the alternatePhone.
	 */
	public String getAlternatePhone() {
		fetch();
		return alternatePhone;
	}

	/**
	 * @param alternatePhone
	 *            The alternatePhone to set.
	 */
	public void setAlternatePhone(String alternatePhone) {
		if (this.alternatePhone == null || !this.alternatePhone.equals(alternatePhone)) {
			markModified();
		}
		this.alternatePhone = alternatePhone;
	}

	/**
	 * @return Returns the narrative.
	 */
	public String getNarrative() {
		fetch();
		return narrative;
	}

	/**
	 * @param narrative
	 *            The narrative to set.
	 */
	public void setNarrative(String narrative) {
		if (this.narrative == null || !this.narrative.equals(narrative)) {
			markModified();
		}
		this.narrative = narrative;
	}

	/**
	 * Set the reference value to class :: SupervisionCode
	 */
	public void setStatusId(String statusId) {
		if (this.statusId == null || !this.statusId.equals(statusId)) {
			markModified();
		}
		this.statusId = statusId;
	}

	/**
	 * Get the reference value to class :: SupervisionCode
	 */
	public String getStatusId() {
		fetch();
		return statusId;
	}

	/**
	 * Initialize class relationship to class SupervisionCode
	 */
	private void initStatus() {
		if (status == null) {
			status = PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY,
					PDCodeTableConstants.CAL_EVENT_STATUS, statusId);
		}
	}

	/**
	 * Gets referenced type SupervisionCode
	 */
	public SupervisionCode getStatus() {
		fetch();
		initStatus();
		return status;
	}

	/**
	 * set the type reference for class member status
	 */
	public void setStatus(SupervisionCode status) {
		if (this.status == null || !this.status.equals(status)) {
			markModified();
		}
		this.status = status;

	}
	
	/**
	 * @return Returns the otherPurpose.
	 */
	public String getOtherPurpose() {
		fetch();
		return otherPurpose;
	}
	/**
	 * @param otherPurpose The otherPurpose to set.
	 */
	public void setOtherPurpose(String otherPurpose) {
		if (this.otherPurpose == null || !this.otherPurpose.equals(otherPurpose)) {
			markModified();
		}
		this.otherPurpose = otherPurpose;
	}
	/**
	 * @return Returns the markedForDeleteOn.
	 */
	public Date getMarkedForDeleteOn() {
		fetch();
		return markedForDeleteOn;
	}
	
	/**
	 * @roseuid 4798EED802CD
	 */
	static public Iterator findAll() {
		IHome home = new Home();
		return home.findAll(FieldVisitEvent.class);
	}

	/**
	 * @roseuid 4798EED802CD
	 */
	static public Iterator findAll(String fvIteneraryId) {
		IHome home = new Home();
		return home.findAll("fvIteneraryId", fvIteneraryId, FieldVisitEvent.class);
		
	}
	
	/**
	 * @roseuid 4798EED802CD
	 */
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, FieldVisitEvent.class);
	}
	
	  static public Iterator findAll(String attrName, String attrValue) {
        IHome home = new Home();
        return home.findAll(attrName, attrValue, FieldVisitEvent.class);
	  }
	        
	/**
	 * @roseuid 4798EED802DB
	 */
	static public FieldVisitEvent find(String fvEventId) {
		IHome home = new Home();
		return (FieldVisitEvent) home.find(fvEventId, FieldVisitEvent.class);
	}

	/**
	 * @param fieldVisitEvent
	 * @param class1
	 * @return
	 */
	public static MetaDataResponseEvent findMeta(IEvent fieldVisitEvent ) {
		IHome home = new Home();
		MetaDataResponseEvent metaResp = home.findMeta(fieldVisitEvent, FieldVisitEvent.class);
		return metaResp;
	}
	
	/**
	 * @roseuid 4798EED802DC
	 */
	public void bind() {

	}
	
	
	/**
	 * @param deleteEvent
	 * @param csEvent
	 */
	public void delete(DeleteCSFVEvent deleteEvent, CSEvent csEvent) {

		if ((this.statusId).equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_STATUS_OPEN)
				|| (csEvent.getMarkedForDeleteOn() != null)) {
			//Completely deletes the record from the table.
			this.delete();
			csEvent.delete();
		} else {
			//Marked for deletion and is scheduled to delete after 90 days. 
			csEvent.setMarkedForDeleteOn(new Date());
			RegisterTaskEvent rtEvent = new RegisterTaskEvent();
			rtEvent.setScheduleClassName(mojo.naming.CalendarConstants.ONCE_SCHEDULE_CLASS);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, 90);
			Date deleteDate = calendar.getTime();
			rtEvent.setFirstNotificationDate(deleteDate);
			rtEvent.setNextNotificationDate(deleteDate);
			StringBuffer taskName = new StringBuffer(deleteEvent.getClass().getName());
			taskName.append("-");
			taskName.append(deleteEvent.getFvEventid());
			rtEvent.setTaskName(taskName.toString());
			rtEvent.setNotificationEvent(deleteEvent);
			EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent);

		}

	}

}
