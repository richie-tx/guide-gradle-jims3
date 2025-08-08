/*
 * Created on Feb 12, 2008
 *

 */
package ui.supervision.cscdcalendar;

import java.util.Date;
import java.util.List;

import messaging.contact.to.NameBean;
import messaging.contact.to.PhoneNumberBean;
import messaging.cscdcalendar.reply.CSFVItineraryResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import ui.common.ComplexCodeTableHelper;
import ui.common.StringUtil;

/**
 * @author awidjaja This is a bean that stores information about a field visit
 *         itinerary and handles any display formatting required by UI
 */
public class CSFVItineraryBean {
	private Date eventDate;
	private String itineraryId;

	private String quadrantCd;
	private String inOfficeFrom;
	private String inOfficeTo;
	private String inFieldFrom;
	private String inFieldTo;

	private String inOfficeAMPMId1;
	private String inOfficeAMPMId2;
	private String inFieldAMPMId1;
	private String inFieldAMPMId2;

	private PhoneNumberBean mobilePager;
	private String radioCallNum;
	NameBean passenger1;
	NameBean passenger2;
	NameBean passenger3;

	private String carTypeCd;

	private String mileageIn;
	private String fvOfficer;
	private String mileageOut;
	private String autoLicense;
	private String autoMake;
	private String autoYear;
	private String autoModel;
	private String autoColor;
	private String positionId;

	private String agencyId;

	private CSFVItineraryBean() {
	}

	public void clear() {
		itineraryId = null;
		inOfficeAMPMId1 = "";
		inOfficeAMPMId2 = "";
	}

	public CSFVItineraryBean(String agencyId) {
		this.agencyId = agencyId;

		mobilePager = new PhoneNumberBean();
		radioCallNum = "";
		passenger1 = new NameBean();
		passenger2 = new NameBean();
		passenger3 = new NameBean();
	}

	public void load(CSFVItineraryResponseEvent re) {
		this.itineraryId = re.getFvItineraryId();
		this.carTypeCd = re.getCarTypeCd();
		this.eventDate = re.getItineraryDate();
		this.fvOfficer = re.getFVOfficer();
		this.radioCallNum = re.getRadioCallNum();

		String inOfficeTime1 = DateUtil.dateToString(re.getInOfficeFrom(), "hh:mm a");
		String inOfficeTime2 = DateUtil.dateToString(re.getInOfficeTo(), "hh:mm a");
		if (!StringUtil.isEmpty(inOfficeTime1)) {
			if (inOfficeTime1.length() > 5) {
				this.inOfficeFrom = inOfficeTime1.substring(0, 5);
				this.inOfficeAMPMId1 = inOfficeTime1.substring(5).trim();
			}
		}

		else {
			this.inOfficeFrom = inOfficeTime1;
			this.inOfficeAMPMId1 = "";
		}

		if (!StringUtil.isEmpty(inOfficeTime2)) {
			if (inOfficeTime2.length() > 5) {
				this.inOfficeTo = inOfficeTime2.substring(0, 5);
				this.inOfficeAMPMId2 = inOfficeTime2.substring(5).trim();
			}
		}

		else {
			this.inOfficeTo = inOfficeTime2;
			this.inOfficeAMPMId2 = "";
		}

		String inFieldTime1 = DateUtil.dateToString(re.getInFieldFrom(), "hh:mm a");
		String inFieldTime2 = DateUtil.dateToString(re.getInFieldTo(), "hh:mm a");
		if (!StringUtil.isEmpty(inFieldTime1)) {
			if (inFieldTime1.length() > 5) {
				this.inFieldFrom = inFieldTime1.substring(0, 5);
				this.inFieldAMPMId1 = inFieldTime1.substring(5).trim();
			}
		}

		else {
			this.inFieldFrom = inFieldTime1;
			this.inFieldAMPMId1 = "";
		}

		if (!StringUtil.isEmpty(inFieldTime2)) {
			if (inFieldTime2.length() > 5) {
				this.inFieldTo = inFieldTime2.substring(0, 5);
				this.inFieldAMPMId2 = inFieldTime2.substring(5).trim();
			}
		}

		else {
			this.inFieldTo = inFieldTime2;
			this.inFieldAMPMId2 = "";
		}

		this.passenger1 = new NameBean(re.getP1FirstName(), re.getP1MiddleName(), re.getP1LastName());
		this.passenger2 = new NameBean(re.getP2FirstName(), re.getP2MiddleName(), re.getP2LastName());
		this.passenger3 = new NameBean(re.getP3FirstName(), re.getP3MiddleName(), re.getP3LastName());

		PhoneNumberBean phone = new PhoneNumberBean(re.getMobile());
		this.mobilePager = phone;

		this.mileageIn = re.getMileageIn();
		this.mileageOut = re.getMileageOut();
		this.autoLicense = re.getAutoLicense();
		this.autoYear = re.getAutoYear();
		this.autoModel = re.getAutoModel();
		this.autoColor = re.getAutoColor();
		this.autoMake = re.getAutoMake();
		this.positionId = re.getPositionId();
		this.quadrantCd = re.getQuadrantCd();

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
	 * @return the fvOfficer
	 */
	public String getFvOfficer() {
		return fvOfficer;
	}

	/**
	 * @param fvOfficer the fvOfficer to set
	 */
	public void setFvOfficer(String fvOfficer) {
		this.fvOfficer = fvOfficer;
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
	 * @return Returns the inFieldFrom.
	 */
	public String getInFieldFrom() {
		return inFieldFrom;
	}

	/**
	 * @param inFieldFrom
	 *            The inFieldFrom to set.
	 */
	public void setInFieldFrom(String inFieldFrom) {
		this.inFieldFrom = inFieldFrom;
	}

	/**
	 * @return Returns the inFieldTo.
	 */
	public String getInFieldTo() {
		return inFieldTo;
	}

	/**
	 * @param inFieldTo
	 *            The inFieldTo to set.
	 */
	public void setInFieldTo(String inFieldTo) {
		this.inFieldTo = inFieldTo;
	}

	/**
	 * @return Returns the inOfficeFrom.
	 */
	public String getInOfficeFrom() {
		return inOfficeFrom;
	}

	/**
	 * @param inOfficeFrom
	 *            The inOfficeFrom to set.
	 */
	public void setInOfficeFrom(String inOfficeFrom) {
		this.inOfficeFrom = inOfficeFrom;
	}

	/**
	 * @return Returns the inOfficeTo.
	 */
	public String getInOfficeTo() {
		return inOfficeTo;
	}

	/**
	 * @param inOfficeTo
	 *            The inOfficeTo to set.
	 */
	public void setInOfficeTo(String inOfficeTo) {
		this.inOfficeTo = inOfficeTo;
	}

	/**
	 * @return Returns the mileageIn.
	 */

	public String getMileageIn() {
		return mileageIn;
	}

	public String getInOfficeAMPMId1() {
		return inOfficeAMPMId1;
	}

	public void setInOfficeAMPMId1(String inOfficeAMPMId1) {
		this.inOfficeAMPMId1 = inOfficeAMPMId1;
	}

	public String getInOfficeAMPMId2() {
		return inOfficeAMPMId2;
	}

	public void setInOfficeAMPMId2(String inOfficeAMPMId2) {
		this.inOfficeAMPMId2 = inOfficeAMPMId2;
	}

	public String getInFieldAMPMId1() {
		return inFieldAMPMId1;
	}

	public void setInFieldAMPMId1(String inFieldAMPMId1) {
		this.inFieldAMPMId1 = inFieldAMPMId1;
	}

	public String getInFieldAMPMId2() {
		return inFieldAMPMId2;
	}

	public void setInFieldAMPMId2(String inFieldAMPMId2) {
		this.inFieldAMPMId2 = inFieldAMPMId2;
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
	 * @return Returns the passenger1.
	 */
	public NameBean getPassenger1() {
		return passenger1;
	}

	/**
	 * @param passenger1
	 *            The passenger1 to set.
	 */
	public void setPassenger1(NameBean passenger1) {
		this.passenger1 = passenger1;
	}

	/**
	 * @return Returns the passenger2.
	 */
	public NameBean getPassenger2() {
		return passenger2;
	}

	/**
	 * @param passenger2
	 *            The passenger2 to set.
	 */
	public void setPassenger2(NameBean passenger2) {
		this.passenger2 = passenger2;
	}

	/**
	 * @return Returns the passenger3.
	 */
	public NameBean getPassenger3() {
		return passenger3;
	}

	/**
	 * @param passenger3
	 *            The passenger3 to set.
	 */
	public void setPassenger3(NameBean passenger3) {
		this.passenger3 = passenger3;
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

	public String getQuadrantDesc() {
		List quadrantList = ComplexCodeTableHelper.getSupervisionCodes(
				agencyId, PDCodeTableConstants.QUADRANT);
		return ComplexCodeTableHelper.getDescrBySupervisionCode(quadrantList,
				quadrantCd);
	}

	public String getCarTypeDesc() {
		List carTypes = ComplexCodeTableHelper.getSupervisionCodes(agencyId,
				PDCodeTableConstants.CAR_TYPE);
		return ComplexCodeTableHelper.getDescrBySupervisionCode(carTypes,
				carTypeCd);
	}

	/**
	 * @return Returns the itineraryId.
	 */
	public String getItineraryId() {
		return itineraryId;
	}

	/**
	 * @param itineraryId
	 *            The itineraryId to set.
	 */
	public void setItineraryId(String itineraryId) {
		this.itineraryId = itineraryId;
	}

	/**
	 * @return Returns the mobilePager.
	 */
	public PhoneNumberBean getMobilePager() {
		return mobilePager;
	}

	/**
	 * @param mobilePager
	 *            The mobilePager to set.
	 */
	public void setMobilePager(PhoneNumberBean mobilePager) {
		this.mobilePager = mobilePager;
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

}
