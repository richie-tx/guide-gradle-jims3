// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\messaging\\cscdcalendar\\SaveCSFVIteranyEvent.java

package messaging.cscdcalendar;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SaveCSFVItineraryEvent extends RequestEvent {
	private Date itineraryDate;

	private String radioCallNum;
	
	private Date inOfficeFrom;

	private Date inOfficeTo;

	private Date inFieldFrom;

	private Date inFieldTo;

	private String mobile;

	private String carTypeCd;

	private String quadrantCd;

	private String mileageIn;

	private String mileageOut;

	private String autoLicense;

	private String autoYear;

	private String autoMake;

	private String autoModel;

	private String autoColor;

	private boolean create;

	private boolean update;

	private boolean reschedule;

	private boolean results;

	private boolean delete;

	private String rescheduleItenaryId;

	private String deleteItenaryId;
	
	private String updateItineraryId;

	private String positionId;

	private String p1LastName;

	private String p1MiddleName;

	private String p1FirstName;

	private String p2LastName;

	private String p2MiddleName;

	private String p2FirstName;

	private String p3LastName;

	private String p3MiddleName;

	private String p3FirstName;

	/**
	 * @roseuid 479A0E210099
	 */
	public SaveCSFVItineraryEvent() {

	}

	/**
	 * @return Returns the autoColor.
	 */
	public String getAutoColor() {
		return autoColor;
	}

	/**
	 * @param autoColor
	 *            The autoColor to set.
	 */
	public void setAutoColor(String autoColor) {
		this.autoColor = autoColor;
	}

	/**
	 * @return Returns the autoLicense.
	 */
	public String getAutoLicense() {
		return autoLicense;
	}

	/**
	 * @param autoLicense
	 *            The autoLicense to set.
	 */
	public void setAutoLicense(String autoLicense) {
		this.autoLicense = autoLicense;
	}

	/**
	 * @return Returns the autoMake.
	 */
	public String getAutoMake() {
		return autoMake;
	}

	/**
	 * @param autoMake
	 *            The autoMake to set.
	 */
	public void setAutoMake(String autoMake) {
		this.autoMake = autoMake;
	}

	/**
	 * @return Returns the autoModel.
	 */
	public String getAutoModel() {
		return autoModel;
	}

	/**
	 * @param autoModel
	 *            The autoModel to set.
	 */
	public void setAutoModel(String autoModel) {
		this.autoModel = autoModel;
	}

	/**
	 * @return Returns the autoYear.
	 */
	public String getAutoYear() {
		return autoYear;
	}

	/**
	 * @param autoYear
	 *            The autoYear to set.
	 */
	public void setAutoYear(String autoYear) {
		this.autoYear = autoYear;
	}

	/**
	 * @return the radioCallNum
	 */
	public String getRadioCallNum() {
		return radioCallNum;
	}

	/**
	 * @param radioCallNum the radioCallNum to set
	 */
	public void setRadioCallNum(String radioCallNum) {
		this.radioCallNum = radioCallNum;
	}

	/**
	 * @return Returns the quadrantCd.
	 */
	public String getQuadrantCd() {
		return quadrantCd;
	}

	/**
	 * @param quadrantCd
	 *            The quadrantCd to set.
	 */
	public void setQuadrantCd(String quadrantCd) {
		this.quadrantCd = quadrantCd;
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
	 * @return Returns the deleteItenaryId.
	 */
	public String getDeleteItenaryId() {
		return deleteItenaryId;
	}

	/**
	 * @param deleteItenaryId
	 *            The deleteItenaryId to set.
	 */
	public void setDeleteItenaryId(String deleteItenaryId) {
		this.deleteItenaryId = deleteItenaryId;
	}

	/**
	 * @return Returns the inFieldFrom.
	 */
	public Date getInFieldFrom() {
		return inFieldFrom;
	}

	/**
	 * @param inFieldFrom
	 *            The inFieldFrom to set.
	 */
	public void setInFieldFrom(Date inFieldFrom) {
		this.inFieldFrom = inFieldFrom;
	}

	/**
	 * @return Returns the inFieldTo.
	 */
	public Date getInFieldTo() {
		return inFieldTo;
	}

	/**
	 * @param inFieldTo
	 *            The inFieldTo to set.
	 */
	public void setInFieldTo(Date inFieldTo) {
		this.inFieldTo = inFieldTo;
	}

	/**
	 * @return Returns the inOfficeFrom.
	 */
	public Date getInOfficeFrom() {
		return inOfficeFrom;
	}

	/**
	 * @param inOfficeFrom
	 *            The inOfficeFrom to set.
	 */
	public void setInOfficeFrom(Date inOfficeFrom) {
		this.inOfficeFrom = inOfficeFrom;
	}

	/**
	 * @return Returns the inOfficeTo.
	 */
	public Date getInOfficeTo() {
		return inOfficeTo;
	}

	/**
	 * @param inOfficeTo
	 *            The inOfficeTo to set.
	 */
	public void setInOfficeTo(Date inOfficeTo) {
		this.inOfficeTo = inOfficeTo;
	}

	/**
	 * @return Returns the itineraryDate.
	 */
	public Date getItineraryDate() {
		return itineraryDate;
	}

	/**
	 * @param itineraryDate
	 *            The itineraryDate to set.
	 */
	public void setItineraryDate(Date itineraryDate) {
		this.itineraryDate = itineraryDate;
	}

	/**
	 * @return Returns the mileageIn.
	 */
	public String getMileageIn() {
		return mileageIn;
	}

	/**
	 * @param mileageIn
	 *            The mileageIn to set.
	 */
	public void setMileageIn(String mileageIn) {
		this.mileageIn = mileageIn;
	}

	/**
	 * @return Returns the mileageOut.
	 */
	public String getMileageOut() {
		return mileageOut;
	}

	/**
	 * @param mileageOut
	 *            The mileageOut to set.
	 */
	public void setMileageOut(String mileageOut) {
		this.mileageOut = mileageOut;
	}

	/**
	 * @return Returns the mobile.
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            The mobile to set.
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	 * @return Returns the rescheduleItenaryId.
	 */
	public String getRescheduleItenaryId() {
		return rescheduleItenaryId;
	}

	/**
	 * @param rescheduleItenaryId
	 *            The rescheduleItenaryId to set.
	 */
	public void setRescheduleItenaryId(String rescheduleItenaryId) {
		this.rescheduleItenaryId = rescheduleItenaryId;
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
	 * @return Returns the carTypeCd.
	 */
	public String getCarTypeCd() {
		return carTypeCd;
	}

	/**
	 * @param carTypeCd
	 *            The carTypeCd to set.
	 */
	public void setCarTypeCd(String carTypeCd) {
		this.carTypeCd = carTypeCd;
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
	 * @return Returns the p1FirstName.
	 */
	public String getP1FirstName() {
		return p1FirstName;
	}

	/**
	 * @param firstName
	 *            The p1FirstName to set.
	 */
	public void setP1FirstName(String firstName) {
		p1FirstName = firstName;
	}

	/**
	 * @return Returns the p1LastName.
	 */
	public String getP1LastName() {
		return p1LastName;
	}

	/**
	 * @param lastName
	 *            The p1LastName to set.
	 */
	public void setP1LastName(String lastName) {
		p1LastName = lastName;
	}

	/**
	 * @return Returns the p1MiddleName.
	 */
	public String getP1MiddleName() {
		return p1MiddleName;
	}

	/**
	 * @param middleName
	 *            The p1MiddleName to set.
	 */
	public void setP1MiddleName(String middleName) {
		p1MiddleName = middleName;
	}

	/**
	 * @return Returns the p2FirstName.
	 */
	public String getP2FirstName() {
		return p2FirstName;
	}

	/**
	 * @param firstName
	 *            The p2FirstName to set.
	 */
	public void setP2FirstName(String firstName) {
		p2FirstName = firstName;
	}

	/**
	 * @return Returns the p2LastName.
	 */
	public String getP2LastName() {
		return p2LastName;
	}

	/**
	 * @param lastName
	 *            The p2LastName to set.
	 */
	public void setP2LastName(String lastName) {
		p2LastName = lastName;
	}

	/**
	 * @return Returns the p2MiddleName.
	 */
	public String getP2MiddleName() {
		return p2MiddleName;
	}

	/**
	 * @param middleName
	 *            The p2MiddleName to set.
	 */
	public void setP2MiddleName(String middleName) {
		p2MiddleName = middleName;
	}

	/**
	 * @return Returns the p3FirstName.
	 */
	public String getP3FirstName() {
		return p3FirstName;
	}

	/**
	 * @param firstName
	 *            The p3FirstName to set.
	 */
	public void setP3FirstName(String firstName) {
		p3FirstName = firstName;
	}

	/**
	 * @return Returns the p3LastName.
	 */
	public String getP3LastName() {
		return p3LastName;
	}

	/**
	 * @param lastName
	 *            The p3LastName to set.
	 */
	public void setP3LastName(String lastName) {
		p3LastName = lastName;
	}

	/**
	 * @return Returns the p3MiddleName.
	 */
	public String getP3MiddleName() {
		return p3MiddleName;
	}

	/**
	 * @param middleName
	 *            The p3MiddleName to set.
	 */
	public void setP3MiddleName(String middleName) {
		p3MiddleName = middleName;
	}
	
	/**
	 * @return Returns the updateItineraryId.
	 */
	public String getUpdateItineraryId() {
		return updateItineraryId;
	}
	/**
	 * @param updateItineraryId The updateItineraryId to set.
	 */
	public void setUpdateItineraryId(String updateItineraryId) {
		this.updateItineraryId = updateItineraryId;
	}
}
