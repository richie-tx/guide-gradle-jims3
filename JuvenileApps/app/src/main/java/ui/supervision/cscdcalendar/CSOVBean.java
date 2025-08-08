/*
 * Created on Mar 10, 2008
 *
 */
package ui.supervision.cscdcalendar;

import java.util.Date;
import java.util.List;

import ui.common.ComplexCodeTableHelper;
import ui.common.StringUtil;

import messaging.contact.to.PhoneNumberBean;
import messaging.cscdcalendar.reply.CSOfficeVisitResponseEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author awidjaja
 * 
 *         This is a bean that stores information about a office visit and
 *         handles any display formatting required by UI
 */
public class CSOVBean {
	private String eventId;
	private Date eventDate;
	private String eventName;
	private String startTime;
	private String endTime;
	private String superviseeId;
	private PhoneNumberBean superviseePhone;
	private PhoneNumberBean altPhone;
	private String purpose;
	private String outcomeCd;
	private String narrative;

	private String positionId;
	private String rescheduleReason;
	private String resultUserId;
	private String resultPositionId;

	private String eventTypeCd;

	private String status;
	private String AMPMId1;
	private String AMPMId2;

	public CSOVBean() {
		superviseePhone = new PhoneNumberBean();
		altPhone = new PhoneNumberBean();
	}

	public CSOVBean(CSOfficeVisitResponseEvent re) {
		this.load(re);
	}

	public void clear() {
		eventName = null;
		startTime = null;
		endTime = null;
		altPhone = new PhoneNumberBean();
		purpose = null;
		outcomeCd = null;
		narrative = null;
		rescheduleReason = null;
		status = null;	
		AMPMId1 ="";
		AMPMId2 ="";
		superviseePhone = new PhoneNumberBean();
	}

	public void load(CSOfficeVisitResponseEvent re) {
		this.eventId = re.getEventId();
		this.eventDate = re.getEventDate();
		this.eventName = re.getEventName();
		if (!StringUtil.isEmpty(re.getStartTime())) {
			if (re.getStartTime().length() > 5) {
				this.startTime = re.getStartTime().substring(0, 5);
				this.AMPMId1 = re.getStartTime().substring(5).trim();
			}
		} else {
			this.startTime = re.getStartTime();
			this.AMPMId1 = "";
		}

		if (!StringUtil.isEmpty(re.getEndTime())) {
			if (re.getEndTime().length() > 5) {
				this.endTime = re.getEndTime().substring(0, 5);
				this.AMPMId2 = re.getEndTime().substring(5).trim();
			}
		} else {
			this.endTime = re.getEndTime();
			this.AMPMId2 = "";
		}

		this.superviseeId = re.getSuperviseeId();
		this.altPhone = new PhoneNumberBean(re.getPhonenum());
		this.superviseePhone = new PhoneNumberBean(re.getPartyEvent()
				.getPhoneNum());
		this.purpose = re.getPurpose();
		this.outcomeCd = re.getOutcome();
		if (re.getNarrative() == null) {
			this.narrative = re.getPurpose() != null ? re.getPurpose() : "";
		} else {
			this.narrative = re.getNarrative();
		}

		this.eventTypeCd = re.getEventType();
		this.positionId = re.getPositionId();
		this.rescheduleReason = re.getRescheduleReason();
		this.resultUserId = re.getResultUserId();
		this.resultPositionId = re.getResultPositionId();
		this.status = re.getStatus();

	}

	/**
	 * @return Returns the altPhone.
	 */
	public PhoneNumberBean getAltPhone() {
		return altPhone;
	}

	/**
	 * @param altPhone
	 *            The altPhone to set.
	 */
	public void setAltPhone(PhoneNumberBean altPhone) {
		this.altPhone = altPhone;
	}

	/**
	 * @return Returns the endTime.
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            The endTime to set.
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAMPMId1() {
		return AMPMId1;
	}

	public void setAMPMId1(String id1) {
		AMPMId1 = id1;
	}

	public String getAMPMId2() {
		return AMPMId2;
	}

	public void setAMPMId2(String id2) {
		AMPMId2 = id2;
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
	 * @return Returns the outcomeCd.
	 */
	public String getOutcomeCd() {
		return outcomeCd;
	}

	/**
	 * @param outcomeCd
	 *            The outcomeCd to set.
	 */
	public void setOutcomeCd(String outcomeCd) {
		this.outcomeCd = outcomeCd;
	}

	/**
	 * @return Returns the purpose.
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * @param purpose
	 *            The purpose to set.
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	/**
	 * @return Returns the startTime.
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            The startTime to set.
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return Returns the superviseeId.
	 */
	public String getSuperviseeId() {
		return superviseeId;
	}

	/**
	 * @param superviseeId
	 *            The superviseeId to set.
	 */
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}

	/**
	 * @return Returns the superviseePhone.
	 */
	public PhoneNumberBean getSuperviseePhone() {
		return superviseePhone;
	}

	/**
	 * @param superviseePhone
	 *            The superviseePhone to set.
	 */
	public void setSuperviseePhone(PhoneNumberBean superviseePhone) {
		this.superviseePhone = superviseePhone;
	}

	/**
	 * @return Returns the eventTypeCd.
	 */
	public String getEventTypeCd() {
		return eventTypeCd;
	}

	/**
	 * @param eventTypeCd
	 *            The eventTypeCd to set.
	 */
	public void setEventTypeCd(String eventTypeCd) {
		this.eventTypeCd = eventTypeCd;
	}

	/**
	 * @return Returns the eventId.
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            The eventId to set.
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
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
	 * @return the rescheduleReason
	 */
	public String getRescheduleReason() {
		return rescheduleReason;
	}

	/**
	 * @param rescheduleReason the rescheduleReason to set
	 */
	public void setRescheduleReason(String rescheduleReason) {
		this.rescheduleReason = rescheduleReason;
	}

	/**
	 * @return Returns the resultPositionId.
	 */
	public String getResultPositionId() {
		return resultPositionId;
	}

	/**
	 * @param resultPositionId
	 *            The resultPositionId to set.
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
	 * @param resultUserId
	 *            The resultUserId to set.
	 */
	public void setResultUserId(String resultUserId) {
		this.resultUserId = resultUserId;
	}

	public void setEventDateStr(String dateString) {
		eventDate = DateUtil.stringToDate(dateString, DateUtil.DATE_FMT_1);
	}

	public String getEventDateStr() {
		return DateUtil.dateToString(eventDate, DateUtil.DATE_FMT_1);
	}

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getOutcomeDesc() {
		List outcomeList = cscdCalendarUIUtil.getFilteredOVOutcomeList("CSC");
		return ComplexCodeTableHelper.getDescrBySupervisionCode(outcomeList,
				outcomeCd);

	}
}
