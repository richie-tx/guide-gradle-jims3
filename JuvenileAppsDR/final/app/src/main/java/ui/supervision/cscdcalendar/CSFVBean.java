/*
 * Created on Feb 13, 2008
 *
 */
package ui.supervision.cscdcalendar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.to.Address;
import messaging.contact.to.PhoneNumberBean;
import messaging.cscdcalendar.reply.CSFieldVisitResponseEvent;
import messaging.manageassociate.reply.AssociateResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import ui.common.ComplexCodeTableHelper;
import ui.common.StringUtil;
import ui.supervision.manageassociate.UIManageAssociateHelper;

/**
 * @author awidjaja
 * 
 *         This bean is responsible to store information specific to a field
 *         visit, and also know how to format the data to be presented to the
 *         user.
 */
public class CSFVBean {
	public static final String EVENT_TYPE = "FV";
	public static final String[] FV_OUTCOME_USER_SELECTABLE = { "CO", "IC" };

	private String fvEventId;
	private String purposeCd;
	private String otherPurpose;
	private String fieldVisitTypeCd;
	private Date fieldVisitDate;
	private String sexOffenderCategoryCd;
	private String comments;
	private String noteworthyConditions;
	private String startTime;
	private String endTime;
	private String superviseeId;
	private String superviseeName;
	private String keyMap;
	private Address alternateAddress;
	private PhoneNumberBean alternatePhone;
	private String addressDescription;
	private String caution;

	private Address superviseeAddress;
	private PhoneNumberBean superviseePhone;

	private String outcomeCd;
	private String measurementResultCd;
	private String methodOfContactCd;
	private List superviseeAssociates;
	private String[] associateIds;
	private List selectedAssociates;

	private String narrative;
	private String sequenceNum;

	// This is only used to store the previous field visit event Id in
	// reschedule flow
	private String rescheduleFVEventId;

	// These are controls that do now show up in the UI
	private String statusCd;
	private String agencyId;
	private String AMPMId1;
	private String AMPMId2;

	private CSFVBean() {

	}

	public void clear() {
		fvEventId = null;
		purposeCd = null;
		fieldVisitTypeCd = null;
		sexOffenderCategoryCd = null;
		measurementResultCd = null;
		otherPurpose = null;
		comments = null;
		noteworthyConditions = null;
		startTime = null;
		endTime = null;
		keyMap = null;
		alternateAddress = new Address();
		alternatePhone = new PhoneNumberBean();
		addressDescription = null;
		caution = null;
		AMPMId1 = "";
		AMPMId2 = "";
		statusCd = null;

	}
	
	public void reset() {
		fvEventId = null;
		purposeCd = null;
		fieldVisitTypeCd = null;
		sexOffenderCategoryCd = null;
		measurementResultCd = null;
		methodOfContactCd = null;
		otherPurpose = null;		
		noteworthyConditions = null;
		startTime = null;
		endTime = null;
		keyMap = null;
		alternateAddress = new Address();
		alternatePhone = new PhoneNumberBean();
        AMPMId1 = "";
		AMPMId2 = "";
		statusCd = null;
		outcomeCd = null; 
		narrative = null;
	}


	public CSFVBean(String agencyId) {
		this.agencyId = agencyId;
		alternateAddress = new Address();
		superviseeAddress = new Address();
		superviseePhone = new PhoneNumberBean();
		alternatePhone = new PhoneNumberBean();
	}

	public void load(CSFieldVisitResponseEvent re) {
		this.setFvEventId(re.getFvEventId());
		this.setFieldVisitDate(re.getEventDate());
		this.setPurposeCd(re.getPurposeCd());
		this.setOtherPurpose(re.getOtherPurpose());
		this.setFieldVisitTypeCd(re.getFvTypeCd());
		this.setSexOffenderCategoryCd(re.getSexOffendarTypeCd());
		this.setComments(re.getComments());
		this.setNoteworthyConditions(re.getConditions());
		String sTime = DateUtil.dateToString(re.getStartTime(), "hh:mm a");
		String eTime = DateUtil.dateToString(re.getEndTime(), "hh:mm a");
		if (!StringUtil.isEmpty(sTime)) {
			if (sTime.length() > 5) {
				this.setStartTime(sTime.substring(0, 5));
				this.setAMPMId1(sTime.substring(5).trim());
			}
		}

		else {
			this.setStartTime(sTime);
			this.setAMPMId1("");
		}

		if (!StringUtil.isEmpty(eTime)) {
			if (eTime.length() > 5) {
				this.setEndTime(eTime.substring(0, 5));
				this.setAMPMId2(eTime.substring(5).trim());
			}
		}

		else {
			this.setEndTime(eTime);
			this.setAMPMId2("");
		}
		// this.setStartTime(DateUtil.dateToString(re.getStartTime(),
		// "hh:mm a"));
		// this.setEndTime(DateUtil.dateToString(re.getEndTime(), "hh:mm a"));
		this.setSuperviseeId(re.getSuperviseeId());
		this.setSuperviseeName(re.getSuperviseeName());
		this.setKeyMap(re.getKeyMap());

		this.setSuperviseeAddress(re.getSuperviseeAddress());
		if (re.getSuperviseeAddress().getFullZipCode() != null && re.getSuperviseeAddress().getFullZipCode().length() > 5) {
			this.getSuperviseeAddress().setAdditionalZipCode( re.getSuperviseeAddress().getFullZipCode().substring(5));
			this.getSuperviseeAddress().setZipCode(re.getSuperviseeAddress().getFullZipCode().substring(0,5));
		}
		this.setAlternateAddress(re.getAlternateAddress());
		if (re.getAlternateAddress().getFullZipCode() != null && re.getAlternateAddress().getFullZipCode().length() > 5) {
			this.getAlternateAddress().setAdditionalZipCode( re.getAlternateAddress().getFullZipCode().substring(5));
			this.getAlternateAddress().setZipCode(re.getAlternateAddress().getFullZipCode().substring(0,5));
		}
		if (re.getSuperviseePhone() != null && re.getSuperviseePhone().length() > 0) {
		    this.setSuperviseePhone(new PhoneNumberBean(re.getSuperviseePhone()));
		}
		if (re.getAltPhone() != null && re.getAltPhone().length() > 0) {
			this.setAlternatePhone(new PhoneNumberBean(re.getAltPhone()));
		}

		this.setAddressDescription(re.getAddrDesc());
		this.setCaution(re.getCaution());

		this.setStatusCd(re.getEventStatusCd());
		this.setOutcomeCd(re.getOutcomeCd());
		this.setMethodOfContactCd(re.getContactMethodCd());
		this.setNarrative(re.getNarrative());
		this.setSequenceNum(re.getSequenceNum());
		this.setAssociateIds(re.getAssociateId());

		this.setMeasurementResultCd(re.getMeasureTypeCd());

	}

	/**
	 * @return Returns the addressDescription.
	 */
	public String getAddressDescription() {
		return addressDescription;
	}

	/**
	 * @param addressDescription
	 *            The addressDescription to set.
	 */
	public void setAddressDescription(String addressDescription) {
		this.addressDescription = addressDescription;
	}

	/**
	 * @return Returns the alternateAddress.
	 */
	public Address getAlternateAddress() {
		return alternateAddress;
	}

	/**
	 * @param alternateAddress
	 *            The alternateAddress to set.
	 */
	public void setAlternateAddress(Address alternateAddress) {
		this.alternateAddress = alternateAddress;
	}

	/**
	 * @return Returns the alternatePhone.
	 */
	public PhoneNumberBean getAlternatePhone() {
		return alternatePhone;
	}

	/**
	 * @param alternatePhone
	 *            The alternatePhone to set.
	 */
	public void setAlternatePhone(PhoneNumberBean alternatePhone) {
		this.alternatePhone = alternatePhone;
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
	 * @return Returns the fieldVisitDate.
	 */
	public Date getFieldVisitDate() {
		return fieldVisitDate;
	}

	/**
	 * @param fieldVisitDate
	 *            The fieldVisitDate to set.
	 */
	public void setFieldVisitDate(Date fieldVisitDate) {
		this.fieldVisitDate = fieldVisitDate;
	}

	/**
	 * @return Returns the fieldVisitTypeCd.
	 */
	public String getFieldVisitTypeCd() {
		return fieldVisitTypeCd;
	}

	/**
	 * @param fieldVisitTypeCd
	 *            The fieldVisitTypeCd to set.
	 */
	public void setFieldVisitTypeCd(String fieldVisitTypeCd) {
		this.fieldVisitTypeCd = fieldVisitTypeCd;
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
	 * @return Returns the noteworthyConditions.
	 */
	public String getNoteworthyConditions() {
		return noteworthyConditions;
	}

	/**
	 * @param noteworthyConditions
	 *            The noteworthyConditions to set.
	 */
	public void setNoteworthyConditions(String noteworthyConditions) {
		this.noteworthyConditions = noteworthyConditions;
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

	public String getPurposeDesc() {
		List purposeList = ComplexCodeTableHelper.getSupervisionCodes(agencyId,
				PDCodeTableConstants.FV_PURPOSE);
		return ComplexCodeTableHelper.getDescrBySupervisionCode(purposeList,
				purposeCd);
	}

	public String getFieldVisitTypeDesc() {
		List purposeList = ComplexCodeTableHelper.getSupervisionCodes(agencyId,
				PDCodeTableConstants.FV_TYPES);
		return ComplexCodeTableHelper.getDescrBySupervisionCode(purposeList,
				fieldVisitTypeCd);
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
	 * @return Returns the sexOffenderCategoryCd.
	 */
	public String getSexOffenderCategoryCd() {
		return sexOffenderCategoryCd;
	}

	/**
	 * @param sexOffenderCategoryCd
	 *            The sexOffenderCategoryCd to set.
	 */
	public void setSexOffenderCategoryCd(String sexOffenderCategoryCd) {
		this.sexOffenderCategoryCd = sexOffenderCategoryCd;
	}

	/**
	 * @return Returns the superviseeAddress.
	 */
	public Address getSuperviseeAddress() {
		return superviseeAddress;
	}

	/**
	 * @param superviseeAddress
	 *            The superviseeAddress to set.
	 */
	public void setSuperviseeAddress(Address superviseeAddress) {
		this.superviseeAddress = superviseeAddress;
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
	 * @return Returns the fvEventId.
	 */
	public String getFvEventId() {
		return fvEventId;
	}

	/**
	 * @param fvEventId
	 *            The fvEventId to set.
	 */
	public void setFvEventId(String fvEventId) {
		this.fvEventId = fvEventId;
	}

	/**
	 * @return Returns the measurementResultCd.
	 */
	public String getMeasurementResultCd() {
		return measurementResultCd;
	}

	/**
	 * @param measurementResultCd
	 *            The measurementResultCd to set.
	 */
	public void setMeasurementResultCd(String measurementResultCd) {
		this.measurementResultCd = measurementResultCd;
	}

	/**
	 * @return Returns the methodOfContactCd.
	 */
	public String getMethodOfContactCd() {
		return methodOfContactCd;
	}

	/**
	 * @param methodOfContactCd
	 *            The methodOfContactCd to set.
	 */
	public void setMethodOfContactCd(String methodOfContactCd) {
		this.methodOfContactCd = methodOfContactCd;
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
	 * @return Returns the statusCd.
	 */
	public String getStatusCd() {
		return statusCd;
	}

	/**
	 * @param statusCd
	 *            The statusCd to set.
	 */
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	private List getFVOutcomeList() {
		return ComplexCodeTableHelper.getSupervisionCodes(agencyId,
				PDCodeTableConstants.FV_OUTCOME);
	}

	public String getOutcomeDesc() {
		List ovOutcomeList = getFVOutcomeList();
		return ComplexCodeTableHelper.getDescrBySupervisionCode(ovOutcomeList,
				outcomeCd);
	}

	public List getFilteredFVOutcomeList() {
		List fvOutcomeList = getFVOutcomeList();
		List tobeRemoved = new ArrayList();

		List selectableList = Arrays.asList(FV_OUTCOME_USER_SELECTABLE);

		for (Iterator iter = fvOutcomeList.iterator(); iter.hasNext();) {
			CodeResponseEvent code = (CodeResponseEvent) iter.next();

			if (!selectableList.contains(code.getSupervisionCode())) {
				tobeRemoved.add(code);
			}
		}
		fvOutcomeList.removeAll(tobeRemoved);

		return fvOutcomeList;
	}

	public List getMethodOfContactList() {
		return ComplexCodeTableHelper.getSupervisionCodes(agencyId,
				PDCodeTableConstants.CONTACT_METHOD);
	}

	public String getMethodOfContactDesc() {
		return ComplexCodeTableHelper.getDescrBySupervisionCode(agencyId,
				PDCodeTableConstants.CONTACT_METHOD, methodOfContactCd);
	}

	public String getMeasurementResultDesc() {
		return ComplexCodeTableHelper.getDescrBySupervisionCode(agencyId,
				PDCodeTableConstants.FV_MEASUREMENT_TYPES, measurementResultCd);
	}

	public String getSexOffenderCategoryDesc() {
		return ComplexCodeTableHelper.getDescrBySupervisionCode(agencyId,
				PDCodeTableConstants.SEX_OFFENDER_TYPES, sexOffenderCategoryCd);
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
	 * @return Returns the superviseeAssociates.
	 */
	public List getSuperviseeAssociates() {
		if (superviseeId != null && superviseeId.length() > 0) {
			return (List) UIManageAssociateHelper
					.fetchAssociatesListSortedOnDisplayName(superviseeId);

		} else
			return new ArrayList();
	}

	/**
	 * @param superviseeAssociates
	 *            The superviseeAssociates to set.
	 */
	public void setSuperviseeAssociates(List superviseeAssociates) {
		this.superviseeAssociates = superviseeAssociates;
	}

	/**
	 * @return Returns the associateIds.
	 */
	public String[] getAssociateIds() {
		return associateIds;
	}

	/**
	 * @param associateIds
	 *            The associateIds to set.
	 */
	public void setAssociateIds(String[] associateIds) {
		this.associateIds = associateIds;

		if (associateIds != null && associateIds.length > 0) {
			List selectedAssociates = new ArrayList();
			for (int i = 0; i < associateIds.length; i++) {
				for (Iterator iter = getSuperviseeAssociates().iterator(); iter
						.hasNext();) {
					AssociateResponseEvent assoc = (AssociateResponseEvent) iter
							.next();
					if (assoc.getAssociateId().equals(associateIds[i])) {
						selectedAssociates.add(assoc);
						break;
					}
				}
			}
			this.selectedAssociates = selectedAssociates;
		}
	}

	/**
	 * @return Returns the selectedAssociates.
	 */
	public List getSelectedAssociates() {
		return selectedAssociates;
	}

	/**
	 * @param selectedAssociates
	 *            The selectedAssociates to set.
	 */
	public void setSelectedAssociates(List selectedAssociates) {
		this.selectedAssociates = selectedAssociates;
	}

	/**
	 * @return Returns the otherPurpose.
	 */
	public String getOtherPurpose() {
		return otherPurpose;
	}

	/**
	 * @param otherPurpose
	 *            The otherPurpose to set.
	 */
	public void setOtherPurpose(String otherPurpose) {
		this.otherPurpose = otherPurpose;
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

	public String getSuperviseeName() {
		return superviseeName;
	}

	public void setSuperviseeName(String superviseeName) {
		this.superviseeName = superviseeName;
	}
}
