// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\FieldVisitItenary.java

package pd.supervision.cscdcalendar;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class FieldVisitItinerary extends PersistentObject {
	private Date inOfficeFrom;

	private Date inOfficeTo;

	private Date inFieldFrom;

	private Date inFieldTo;

	private String phoneNum;

	private String p1LastName;
	
	private String p1MiddleName;
	
	private String p1FirstName;
	
	private String p2LastName;
	
	private String p2MiddleName;
	
	private String p2FirstName;
	
	private String p3LastName;
	
	private String p3MiddleName;
	
	private String p3FirstName;
	
	private int mileageIn;

	private int mileageOut;

	private String autoLicenseNum;

	private String autoYear;

	private String autoModel;

	private String autoMake;

	private String autoColor;

	private Date itineraryDate;

	private SupervisionCode carType;

	private String carTypeCd;

	private SupervisionCode quadrant;

	private String quadrantCd;

	private String positionId;
	
	private String radioCallNum;

	private final String AGENCYID = "CSC";

	private final String QUADRANT = "QUADRANT";

	private final String CARTYPE = "CAR_TYPE";

	/**
	 * @roseuid 479A0E5201C2
	 */
	public FieldVisitItinerary() {

	}

	/**
	 * @param fvItineraryID
	 * @return
	 */
	static public FieldVisitItinerary find(String fvItineraryID) {
		IHome home = new Home();
		return (FieldVisitItinerary) home.find(fvItineraryID, FieldVisitItinerary.class);
	}

	/**
	 * @roseuid 45AF7A0A0192
	 * @return Iterator of FieldVisitItinerary
	 * @param event
	 */
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, FieldVisitItinerary.class);
	}

	/**
	 * @return java.util.Iterator
	 * @param attrName
	 * @param attrValue
	 * @return Iterator of FieldVisitItinerary
	 * @roseuid 4236ED950316
	 */
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		return (Iterator) home.findAll(attrName, attrValue, FieldVisitItinerary.class);
	}

	/**
	 * @return Returns the autoColor.
	 */
	public String getAutoColor() {
		fetch();
		return autoColor;
	}

	/**
	 * @param autoColor
	 *            The autoColor to set.
	 */
	public void setAutoColor(String autoColor) {

		if (this.autoColor == null || !this.autoColor.equals(autoColor)) {
			markModified();
		}
		this.autoColor = autoColor;
	}

	/**
	 * @return Returns the autoLicenseNum.
	 */
	public String getAutoLicenseNum() {
		fetch();
		return autoLicenseNum;
	}

	/**
	 * @param autoLicenseNum
	 *            The autoLicenseNum to set.
	 */
	public void setAutoLicenseNum(String autoLicenseNum) {
		if (this.autoLicenseNum == null || !this.autoLicenseNum.equals(autoLicenseNum)) {
			markModified();
		}
		this.autoLicenseNum = autoLicenseNum;
	}

	/**
	 * @return Returns the autoMake.
	 */
	public String getAutoMake() {
		fetch();
		return autoMake;
	}

	/**
	 * @param autoMake
	 *            The autoMake to set.
	 */
	public void setAutoMake(String autoMake) {
		if (this.autoMake == null || !this.autoMake.equals(autoMake)) {
			markModified();
		}
		this.autoMake = autoMake;
	}

	/**
	 * @return Returns the autoModel.
	 */
	public String getAutoModel() {
		fetch();
		return autoModel;
	}

	/**
	 * @param autoModel
	 *            The autoModel to set.
	 */
	public void setAutoModel(String autoModel) {
		if (this.autoModel == null || !this.autoModel.equals(autoModel)) {
			markModified();
		}
		this.autoModel = autoModel;
	}

	/**
	 * @return Returns the autoYear.
	 */
	public String getAutoYear() {
		fetch();
		return autoYear;
	}

	/**
	 * @param autoYear
	 *            The autoYear to set.
	 */
	public void setAutoYear(String autoYear) {
		if (this.autoYear == null || !this.autoYear.equals(autoYear)) {
			markModified();
		}
		this.autoYear = autoYear;
	}

	/**
	 * @return Returns the inFieldFrom.
	 */
	public Date getInFieldFrom() {
		fetch();
		return inFieldFrom;
	}

	/**
	 * @param inFieldFrom
	 *            The inFieldFrom to set.
	 */
	public void setInFieldFrom(Date inFieldFrom) {
		if (this.inFieldFrom == null || !this.inFieldFrom.equals(inFieldFrom)) {
			markModified();
		}
		this.inFieldFrom = inFieldFrom;
	}

	/**
	 * @return Returns the inFieldTo.
	 */
	public Date getInFieldTo() {
		fetch();
		return inFieldTo;
	}

	/**
	 * @param inFieldTo
	 *            The inFieldTo to set.
	 */
	public void setInFieldTo(Date inFieldTo) {
		if (this.inFieldTo == null || !this.inFieldTo.equals(inFieldTo)) {
			markModified();
		}
		this.inFieldTo = inFieldTo;
	}

	/**
	 * @return Returns the inOfficeFrom.
	 */
	public Date getInOfficeFrom() {
		fetch();
		return inOfficeFrom;
	}

	/**
	 * @param inOfficeFrom
	 *            The inOfficeFrom to set.
	 */
	public void setInOfficeFrom(Date inOfficeFrom) {
		if (this.inOfficeFrom == null || !this.inOfficeFrom.equals(inOfficeFrom)) {
			markModified();
		}
		this.inOfficeFrom = inOfficeFrom;
	}

	/**
	 * @return Returns the inOfficeTo.
	 */
	public Date getInOfficeTo() {
		fetch();
		return inOfficeTo;
	}

	/**
	 * @param inOfficeTo
	 *            The inOfficeTo to set.
	 */
	public void setInOfficeTo(Date inOfficeTo) {
		if (this.inOfficeTo == null || !this.inOfficeTo.equals(inOfficeTo)) {
			markModified();
		}
		this.inOfficeTo = inOfficeTo;
	}

	/**
	 * @return Returns the itineraryDate.
	 */
	public Date getItineraryDate() {
		fetch();
		return itineraryDate;
	}

	/**
	 * @param itineraryDate
	 *            The itineraryDate to set.
	 */
	public void setItineraryDate(Date itineraryDate) {
		if (this.itineraryDate == null || !this.itineraryDate.equals(itineraryDate)) {
			markModified();
		}
		this.itineraryDate = itineraryDate;
	}

	/**
	 * @return Returns the p3FirstName.
	 */
	public String getP1FirstName() {
		fetch();
		return p1FirstName;
	}
	/**
	 * @param firstName The p3FirstName to set.
	 */
	public void setP1FirstName(String p1FirstName) {
		if (this.p1FirstName == null || !this.p3FirstName.equals(p1FirstName)) {
			markModified();
		}
		this.p1FirstName = p1FirstName;
	}
	/**
	 * @return Returns the p3LastName.
	 */
	public String getP1LastName() {
		fetch();
		return p1LastName;
	}
	/**
	 * @param lastName The p3LastName to set.
	 */
	public void setP1LastName(String p1LastName) {
		if (this.p3LastName == null || !this.p3LastName.equals(p3LastName)) {
			markModified();
		}
		this.p1LastName = p1LastName;
	}
	/**
	 * @return Returns the p3MiddleName.
	 */
	public String getP1MiddleName() {
		fetch();
		return p1MiddleName;
	}
	/**
	 * @param middleName The p3MiddleName to set.
	 */
	public void setP1MiddleName(String p1MiddleName) {
		if (this.p1MiddleName == null || !this.p1MiddleName.equals(p1MiddleName)) {
			markModified();
		}
		this.p1MiddleName = p1MiddleName;
	}
	
	
	/**
	 * @return Returns the p3FirstName.
	 */
	public String getP2FirstName() {
		fetch();
		return p2FirstName;
	}
	/**
	 * @param firstName The p3FirstName to set.
	 */
	public void setP2FirstName(String p2FirstName) {
		if (this.p2FirstName == null || !this.p3FirstName.equals(p2FirstName)) {
			markModified();
		}
		this.p2FirstName = p2FirstName;
	}
	/**
	 * @return Returns the p3LastName.
	 */
	public String getP2LastName() {
		fetch();
		return p2LastName;
	}
	/**
	 * @param lastName The p3LastName to set.
	 */
	public void setP2LastName(String p2LastName) {
		if (this.p3LastName == null || !this.p3LastName.equals(p3LastName)) {
			markModified();
		}
		this.p2LastName = p2LastName;
	}
	/**
	 * @return Returns the p3MiddleName.
	 */
	public String getP2MiddleName() {
		fetch();
		return p2MiddleName;
	}
	/**
	 * @param middleName The p3MiddleName to set.
	 */
	public void setP2MiddleName(String p2MiddleName) {
		if (this.p2MiddleName == null || !this.p2MiddleName.equals(p2MiddleName)) {
			markModified();
		}
		this.p2MiddleName = p2MiddleName;
	}
	
	
	/**
	 * @return Returns the p3FirstName.
	 */
	public String getP3FirstName() {
		fetch();
		return p3FirstName;
	}
	/**
	 * @param firstName The p3FirstName to set.
	 */
	public void setP3FirstName(String p3FirstName) {
		if (this.p3FirstName == null || !this.p3FirstName.equals(p3FirstName)) {
			markModified();
		}
		this.p3FirstName = p3FirstName;
	}
	/**
	 * @return Returns the p3LastName.
	 */
	public String getP3LastName() {
		fetch();
		return p3LastName;
	}
	/**
	 * @param lastName The p3LastName to set.
	 */
	public void setP3LastName(String p3LastName) {
		if (this.p3LastName == null || !this.p3LastName.equals(p3LastName)) {
			markModified();
		}
		this.p3LastName = p3LastName;
	}
	/**
	 * @return Returns the p3MiddleName.
	 */
	public String getP3MiddleName() {
		fetch();
		return p3MiddleName;
	}
	/**
	 * @param middleName The p3MiddleName to set.
	 */
	public void setP3MiddleName(String p3MiddleName) {
		if (this.p3MiddleName == null || !this.p3MiddleName.equals(p3MiddleName)) {
			markModified();
		}
		this.p3MiddleName = p3MiddleName;
	}
	/**
	 * @return Returns the phoneNum.
	 */
	public String getPhoneNum() {
		fetch();
		return phoneNum;
	}

	/**
	 * @param phoneNum
	 *            The phoneNum to set.
	 */
	public void setPhoneNum(String phoneNum) {
		if (this.phoneNum == null || !this.phoneNum.equals(phoneNum)) {
			markModified();
		}
		this.phoneNum = phoneNum;
	}

	/**
	 * @return Returns the positionId.
	 */
	public String getPositionId() {
		fetch();
		return positionId;
	}

	/**
	 * @param positionId
	 *            The positionId to set.
	 */
	public void setPositionId(String positionId) {
		if (this.positionId == null || !this.positionId.equals(positionId)) {
			markModified();
		}
		this.positionId = positionId;
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
	 * Initialize class relationship to class
	 * pd.codetable.supervision.SupervisionCode
	 */
	private void initQuadrant() {
		if (quadrant == null && quadrantCd != null && (!quadrantCd .equals(""))) {
			quadrant = PDSupervisionCodeHelper.getSupervisionCodeByValue(AGENCYID, QUADRANT, quadrantCd);
		}
	}

	/**
	 * Gets referenced type pd.codetable.supervision.SupervisionCode.
	 */
	public SupervisionCode getQuadrant() {
		fetch();
		initQuadrant();
		return quadrant;
	}

	/**
	 * @param quadrant
	 *            The quadrant to set.
	 */

	public void setQuadrant(SupervisionCode quadrant) {
		if (this.quadrant == null || !this.quadrant.equals(quadrant)) {
			markModified();
		}
		this.quadrant = quadrant;
	}

	/**
	 * Initialize class relationship to class
	 * pd.codetable.supervision.SupervisionCode
	 */
	private void initCarType() {
		if (carType == null && carTypeCd != null && (!carTypeCd .equals(""))) {
			carType = PDSupervisionCodeHelper.getSupervisionCodeByValue(AGENCYID, CARTYPE, carTypeCd);
		}
	}

	/**
	 * @return Returns the carType.
	 */
	public SupervisionCode getCarType() {
		fetch();
		initCarType();
		return carType;
	}

	/**
	 * @param carType
	 *            The carType to set.
	 */

	public void setCarType(SupervisionCode carType) {
		if (this.carType == null || !this.carType.equals(carType)) {
			markModified();
		}
		this.carType = carType;
	}

	/**
	 * @return Returns the carTypeCd.
	 */
	public String getCarTypeCd() {
		fetch();
		return carTypeCd;
	}

	/**
	 * @param carTypeCd
	 *            The carTypeCd to set.
	 */
	public void setCarTypeCd(String carTypeCd) {
		if (this.carTypeCd == null || !this.carTypeCd.equals(carTypeCd)) {
			markModified();
		}
		carType = null;
		this.carTypeCd = carTypeCd;
	}

	/**
	 * @return Returns the quadrantCd.
	 */

	public String getQuadrantCd() {
		fetch();
		return quadrantCd;
	}

	/**
	 * @param quadrantCd
	 *            The quadrantCd to set.
	 */
	public void setQuadrantCd(String quadrantCd) {
		if (this.quadrantCd == null || !this.quadrantCd.equals(quadrantCd)) {
			markModified();
		}
		quadrant = null;
		this.quadrantCd = quadrantCd;
	}
	
	public int getMileageIn() {
		
		fetch();
		return mileageIn;
	}

	public void setMileageIn(int mileageIn) {
		
		if (this.mileageIn != mileageIn)
		{
			markModified();
		}

		this.mileageIn = mileageIn;
	}

	public int getMileageOut() {
		fetch();
		return mileageOut;
	}

	public void setMileageOut(int mileageOut) {
		
		if (this.mileageOut != mileageOut)
		{
			markModified();
		}
		
		this.mileageOut = mileageOut;
	}
}
