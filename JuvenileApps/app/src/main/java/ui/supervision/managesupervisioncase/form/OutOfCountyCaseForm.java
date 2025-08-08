/*
 * Created on Apr 25, 2006
 */
package ui.supervision.managesupervisioncase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.address.reply.AddressResponseEvent;
import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.codetable.reply.ICode;
import messaging.contact.domintf.IAddress;
import messaging.domintf.managesupervisioncase.IParty;
import messaging.domintf.managesupervisioncase.ISupervisionCase;
import messaging.domintf.managesupervisioncase.IUpdateHistory;
import messaging.managesupervisioncase.reply.OOCCaseUpdateHistoryEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.CloseCaseConstants;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;

import ui.common.CodeHelper;
import ui.common.CodeTableHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.common.SocialSecurity;
import ui.common.UIUtil;
import ui.security.SecurityUIHelper;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administercaseload.helper.CloseCaseResultBean;
/**
 * @author hrodriguez
 */
public class OutOfCountyCaseForm extends ActionForm {
	private static final String Y = "Y";

	private static final String YES = "YES";

	private static final String N = "N";

	private static final String NO = "NO";

	private static final String ZERO2 = "00";
	
	private static final String ZERO3 = "000";
	
	private String action = "";

	// Fields
	private String addressTypeId;

	private String agencyId;

	private String agencyName;

	private Date arrestDate;

	private String caseNum;

	private Collection caseList;

	private String caseListSize;

	private String caseStatusId;

	private String caseTypeId;

	private String cdi;

	private String cjisNum1;

	private String cjisNum2;

	private String city;

	private String confinementLengthDay;

	private String confinementLengthMonth;

	private String confinementLengthYear;

	private String contactJobTitle;

	private String contactFirstName;

	private String contactLastName;

	private String contactMiddleName;

	private Name contactName = new Name();

	private String contactPhone;

	private String contactPhone1;

	private String contactPhone2;

	private String contactPhone3;

	private String contactPhoneExt;

	private String courtNum;

	private String countyCodeId;

	private Date dateOfBirth;

	private String defendantId;

	private Date dispositionDate;

	private String dispositionTypeId;

	private String driverLicenseNum;

	private String familyViolenceInd;

	private String fbiNum;

	private Date filingDate;

	private String firstName;

	private String instrumentTypeId;

	private String lastName;

	private String middleName;

	private Date offenseDate;

	private String offenseDesc;

	private String offenseId;

	private String offenseListSize;

	private String orgJurisCaseNum;

	private String orgJurisCountyId;

	private String orgJurisCourt;

	private String orgJurisPID;

	private Collection partyList;

	private String partyListSize;

	private Date pretrialInterventionBegin;

	private Date pretrialInterventionEnd;

	private String previousDispositionTypeId;

	private String primaryKey;

	private String raceId;

	private String secondaryAction = "";

	private String selectedValue = "";

	private Date sentenceDate;

	private String sexId;

	private String sid;

	private String spn;

	private Date supervisionBeginDate;

	private Date supervisionEndDate;

	private String supervisionLengthDay;

	private String supervisionLengthMonth;

	private String supervisionLengthYear;

	private SocialSecurity ssn = new SocialSecurity("");

	private String ssn1;

	private String ssn2;

	private String ssn3;

	private String stateId;

	private String streetName;

	private String streetNum;

	private String aptNum;

	private String streetTypeId;

	private Date transferInDate;

	private String zipCode;

	private String additionalZipCode;

	private String addressStateId;

	private boolean reactivateInd;

	private String reasonForUpdateId;

	private Collection reasonForUpdateHistoryList = new ArrayList();
	 

	// for Offense Code search
	private String searchOffenseId;

	private String offenseLiteral;

	private String levelCodeId;

	private String degreeCodeId;

	private String penalCodeId;

	private String stateOffenseCodeId;

	private Collection offenseResultList = new ArrayList();

	private int offenseResultListSize = 0;

	private String message = null;
	
//	contains only a single CaseAssignmentTo object to be closed
	private List closeCaseAssignmentList = new ArrayList();
	
	private String transferOutDateStr;
	
	private String closureReasonId;
	
	private String namePtr;
	
	private String nameSeqNum;
	private String currentNameSeqNum;
	
	private String currentNamePtr;
	
//	to decide the contents of the close case confirmation page
	private boolean closeCaseSuccess = false;
	
	 private List closeCasesResultBeanList = new ArrayList();
	
	/**
	 * 
	 */
	public OutOfCountyCaseForm() {
		clear();
		// UISupervisionOrderLoadCodeTables.getInstance().setOutOfCountyCaseForm(this);
	}

	public void clear() {
		// Never clear the action
		// action = "";

		arrestDate = null;
		caseNum = "";
		caseTypeId = "";
		cdi = "";
		cjisNum1 = "";
		cjisNum2 = "";
		confinementLengthDay = ZERO2;
		confinementLengthMonth = ZERO2;
		confinementLengthYear = ZERO2;
		contactJobTitle = "";
		contactFirstName = "";
		contactLastName = "";
		contactMiddleName = "";
		contactName = new Name();
		contactPhone = "";
		contactPhone1 = "";
		contactPhone2 = "";
		contactPhone3 = "";
		contactPhoneExt = "";
		countyCodeId = "";
		courtNum = "";
		city = "";
		agencyName = "";
		dateOfBirth = null;
		defendantId = "";
		dispositionTypeId = "";
		dispositionDate = null;
		familyViolenceInd = N;
		filingDate = null;
		offenseId = "";
		offenseDate = null;
		offenseDesc = "";
		pretrialInterventionBegin = null;
		pretrialInterventionEnd = null;
		reasonForUpdateId = "";
		reasonForUpdateHistoryList = new ArrayList();
		sentenceDate = null;
		supervisionBeginDate = null;
		supervisionEndDate = null;
		supervisionLengthDay = ZERO2;
		supervisionLengthMonth = ZERO2;
		supervisionLengthYear = ZERO2;
		transferInDate = null;
		addressTypeId = "";
		stateId = "";
		streetTypeId = "";
		streetName = "";
		streetNum = "";
		aptNum = "";
		zipCode = "";
		additionalZipCode = "";
		addressStateId = "";
		orgJurisCaseNum = "";
		orgJurisCountyId = "";
		orgJurisCourt = "";
		orgJurisPID = "";
		setNamePtr("");
		setNameSeqNum("");
		
//		close OOC Case
		closeCaseAssignmentList = new ArrayList();
		transferOutDateStr = "";
		closureReasonId = "";
		closeCaseSuccess = false;
		closeCasesResultBeanList = new ArrayList();
		
		// clear Offense Search fields
		clearOffenseSearchCriteria();
	}

	public void clearOffenseSearchCriteria() {
		degreeCodeId = "";
		levelCodeId = "";
		offenseLiteral = "";
		penalCodeId = "";
		searchOffenseId = "";
		stateOffenseCodeId = "";
		this.offenseResultList.clear();
		this.offenseResultListSize = 0;
	}

	/**
	 * @return
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @return
	 */
	public String getAgencyId() {
		if (agencyId == null || agencyId.equals("")) {
			agencyId = SecurityUIHelper.getUserAgencyId();
		}
		return agencyId;
	}

	/**
	 * @return
	 */
	public Date getArrestDate() {
		return arrestDate;
	}

	/**
	 * @return
	 */
	public String getArrestDateAsString() {
		if (arrestDate == null) {
			return "";
		} else {
			return DateUtil.dateToString(arrestDate, UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @return
	 */
	public Collection getCaseList() {
		return caseList;
	}

	/**
	 * @return
	 */
	public String getCaseListSize() {
		return caseListSize;
	}

	/**
	 * @return
	 */
	public String getCaseNum() {
		return caseNum;
	}

	/**
	 * @return
	 */
	public String getCaseStatus() {

		return "";
	}

	/**
	 * @return
	 */
	public String getCaseStatusId() {
		return caseStatusId;
	}

	/**
	 * @return
	 */
	public Collection getCaseStatusList() {
		return new ArrayList();
	}

	/**
	 * @return
	 */
	public String getCdi() {
		return cdi;
	}

	/**
	 * @return
	 */
	public String getCourtDivision() {
		return ComplexCodeTableHelper.getCourtDivision(cdi);
	}

	/**
	 * @return
	 */
	public String getCjis() {
		return cjisNum1 + cjisNum2;
	}

	/**
	 * @return
	 */
	public String getCjisNum1() {
		return cjisNum1;
	}

	/**
	 * @return
	 */
	public String getCjisNum2() {
		return cjisNum2;
	}

	/**
	 * @return
	 */
	public String getConfinementLengthDay() {
		confinementLengthDay = placeLeadingZero(confinementLengthDay);
		return confinementLengthDay;
	}

	/**
	 * @return
	 */
	public String getConfinementLengthMonth() {
		confinementLengthMonth = placeLeadingZero(confinementLengthMonth);
		return confinementLengthMonth;
	}

	/**
	 * @return
	 */
	public String getConfinementLengthYear() {
		confinementLengthYear = placeLeadingZero(confinementLengthYear);
		return confinementLengthYear;
	}

	/**
	 * @return
	 */
	public String getAgencyName() {
		return agencyName;
	}

	/**
	 * @return
	 */
	public String getCaseType() {
		return CodeTableHelper.getDescrByCode(getCaseTypeList(), caseTypeId);
	}

	/**
	 * @return
	 */
	public String getCaseTypeId() {
		return caseTypeId;
	}

	/**
	 * @return
	 */
	public List getCaseTypeList() {
		return ComplexCodeTableHelper.getCaseTypes();
	}

	/**
	 * @return
	 */
	public String getContactFirstName() {
		return contactFirstName;
	}

	/**
	 * @return
	 */
	public String getContactJobTitle() {
		return contactJobTitle;
	}

	/**
	 * @return
	 */
	public String getContactLastName() {
		return contactLastName;
	}

	/**
	 * @return
	 */
	public String getContactMiddleName() {
		return contactMiddleName;
	}

	/**
	 * @return
	 */
	public Name getContactName() {
		return new Name(contactFirstName, contactMiddleName, contactLastName);

	}

	/**
	 * @return
	 */
	public String getContactPhone() {
		contactPhone = "";
		if (contactPhone1 != null && !contactPhone1.equals("")) {
			contactPhone = (contactPhone1 + contactPhone2 + contactPhone3);
		}
		return contactPhone;
	}

	/**
	 * @return
	 */
	public String getContactPhone1() {
		return contactPhone1;
	}

	/**
	 * @return
	 */
	public String getContactPhone2() {
		return contactPhone2;
	}

	/**
	 * @return
	 */
	public String getContactPhone3() {
		return contactPhone3;
	}

	/**
	 * @return
	 */
	public String getContactPhoneExt() {
		return contactPhoneExt;
	}

	/**
	 * @return
	 */
	public String getCounty() {
		return CodeTableHelper.getDescrByCode(getCountyList(), countyCodeId);
	}

	/**
	 * @return
	 */
	public String getCountyCodeId() {
		return countyCodeId;
	}

	/**
	 * @return
	 */
	public List getCountyList() {
		return CodeHelper.getCountyCodes(true);
	}

	/**
	 * @return
	 */
	public String getCourtNum() {
		return courtNum;
	}

	/**
	 * @return
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @return
	 */
	public String getDateOfBirthAsString() {
		if (dateOfBirth == null) {
			return "";
		} else {
			return DateUtil.dateToString(dateOfBirth, UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @return String
	 */
	public String getDefendantId() {
		return defendantId;
	}

	/**
	 * @return
	 */
	public Date getDispositionDate() {
		return dispositionDate;
	}

	/**
	 * @return
	 */
	public String getDispositionDateAsString() {
		if (dispositionDate == null) {
			return "";
		} else {
			return DateUtil.dateToString(dispositionDate,
					UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @return
	 */
	public String getDispositionType()
	{
		if((dispositionTypeId.equalsIgnoreCase(PDCodeTableConstants.PRETRIAL_INTERVENTION)) ||
		(dispositionTypeId.equalsIgnoreCase(PDCodeTableConstants.DEFERRED_ADJUDICATION)) ||
		(dispositionTypeId.equalsIgnoreCase(PDCodeTableConstants.STRAIGHT_PROBATION)))
		{
			return CodeTableHelper.getDescrByCode(getDispositionTypeList(),
				dispositionTypeId);
		}
		else
		{
			String dispositionType = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.OOC_DISPOSITION_TYPE, dispositionTypeId);
			return dispositionType;
		}
	}

	/**
	 * @return
	 */
	public String getDispositionTypeId() {
		return dispositionTypeId;
	}

	/**
	 * @return
	 */
	public List getDispositionTypeList() {
		return ComplexCodeTableHelper.getDispositionTypes();
	}

	/**
	 * @return
	 */
	public String getDriverLicenseNum() {
		return driverLicenseNum;
	}

	/**
	 * @return
	 */
	public String getFbiNum() {
		return fbiNum;
	}

	/**
	 * @return
	 */
	public Date getFilingDate() {
		return filingDate;
	}

	/**
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return
	 */
	public String getInstrumentType() {
		return CodeTableHelper.getDescrByCode(getInstrumentTypeList(),
				instrumentTypeId);
	}

	/**
	 * @return
	 */
	public String getInstrumentTypeId() {
		return instrumentTypeId;
	}

	/**
	 * @return
	 */
	public List getInstrumentTypeList() {
		return new ArrayList();
	}

	/**
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @return
	 */
	public Name getName() {
		return new Name(firstName, middleName, lastName);
	}

	/**
	 * @return
	 */
	public String getOffense() {
		String descr = "";
		if (offenseId != null && !offenseId.equals("")) {
			String tempOffenseId = UIUtil.stripZeroes(offenseId);
			if (tempOffenseId != null && !tempOffenseId.equals("")) {
				descr = CodeHelper.getOffenseCodeDescription(tempOffenseId);
			}
		}
		setOffenseDesc(descr);
		return descr;
	}

	public String getOffenseDesc() {
		return offenseDesc;
	}

	/**
	 * @return
	 */
	public Collection getOffenseResultList() {
		return offenseResultList;
	}

	/**
	 * @return
	 */
	public String getOffenseResultListSize() {
		return "" + offenseResultList.size();
	}

	/**
	 * @param aList
	 */
	public void setOffenseResultList(Collection aList) {
		offenseResultList = aList;
	}

	/**
	 * @return
	 */
	public Date getOffenseDate() {
		return offenseDate;
	}

	/**
	 * @return
	 */
	public String getOffenseDateAsString() {
		if (offenseDate == null) {
			return "";
		} else {
			return DateUtil.dateToString(offenseDate, UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @return
	 */
	public String getOffenseId() {
		return offenseId;
	}

	/**
	 * @return
	 */
	// public List getOffenseList()
	// {
	// return ComplexCodeTableHelper.getOffenseCodes(true);
	// }
	/**
	 * @return
	 */
	public String getOffenseListSize() {
		return offenseListSize;
	}

	/**
	 * @return
	 */
	public String getOrgJurisCounty() {
		return CodeTableHelper
				.getDescrByCode(getCountyList(), orgJurisCountyId);
	}

	/**
	 * @return
	 */
	public String getOrgJurisCountyId() {
		return orgJurisCountyId;
	}

	/**
	 * @return
	 */
	public Collection getPartyList() {
		return partyList;
	}

	/**
	 * @return
	 */
	public String getPartyListSize() {
		return partyListSize;
	}

	/**
	 * @return
	 */
	public Date getPretrialInterventionBegin() {
		return pretrialInterventionBegin;
	}

	/**
	 * @return
	 */
	public String getPretrialInterventionBeginAsString() {
		if (pretrialInterventionBegin == null) {
			return "";
		} else {
			return DateUtil.dateToString(pretrialInterventionBegin,
					UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @return
	 */
	public Date getPretrialInterventionEnd() {
		return pretrialInterventionEnd;
	}

	/**
	 * @return
	 */
	public String getPretrialInterventionEndAsString() {
		if (pretrialInterventionEnd == null) {
			return "";
		} else {
			return DateUtil.dateToString(pretrialInterventionEnd,
					UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @return
	 */
	public String getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * @param aPrimaryKey
	 */
	public void setPrimaryKey(String aPrimaryKey) {
		this.primaryKey = aPrimaryKey;
	}

	/**
	 * @return
	 */
	public String getRace() {
		return CodeTableHelper.getDescrByCode(getRaceList(), raceId);
	}

	/**
	 * @return
	 */
	public String getRaceId() {
		return raceId;
	}

	/**
	 * @return
	 */
	public List getRaceList() {
		return SimpleCodeTableHelper
				.getCodesSortedByDesc(PDCodeTableConstants.RACE);
	}

	/**
	 * @return
	 */
	public String getReasonForUpdate() {
		return CodeTableHelper.getDescrByCode(getReasonForUpdateList(),
				reasonForUpdateId);
	}

	/**
	 * @return
	 */
	public String getReasonForUpdateId() {
		return reasonForUpdateId;
	}

	/**
	 * Access method for the reasonForUpdateHistoryList collection.
	 * 
	 * @return the current value of the reasonForUpdateHistoryList collection
	 */
	public Collection getReasonForUpdateHistoryList() {
		return reasonForUpdateHistoryList;
	}

	/**
	 * @return
	 */
	public List getReasonForUpdateList() {
		return CodeHelper.getCodes("REASON_FOR_UPDATE",true);
	}
	public List getReasonForUpdateList(boolean filterInactiveCodes) {
		List codeList = CodeHelper.getActiveCodes("REASON_FOR_UPDATE", true);
		return codeList;
	}
	public List getActiveReasonForUpdateList(){
		return getReasonForUpdateList(true);
	}
	/**
	 * @return
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}

	/**
	 * @return
	 */
	public String getSelectedValue() {
		return selectedValue;
	}

	/**
	 * @return
	 */
	public Date getSentenceDate() {
		return sentenceDate;
	}

	/**
	 * @return
	 */
	public String getSentenceDateAsString() {
		if (sentenceDate == null) {
			return "";
		} else {
			return DateUtil.dateToString(sentenceDate, UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @return
	 */
	public String getSex() {
		return CodeTableHelper.getDescrByCode(getSexList(), sexId);
	}

	/**
	 * @return
	 */
	public String getSexId() {
		return sexId;
	}

	/**
	 * @return
	 */
	public List getSexList() {
		return SimpleCodeTableHelper
				.getCodesSortedByDesc(PDCodeTableConstants.SEX);
	}

	/**
	 * @return
	 */
	public String getSid() {
		return sid;
	}

	/**
	 * @return
	 */
	public String getSpn() {
		return UICommonSupervisionHelper.getSpn(spn);
	}

	/**
	 * @return
	 */
	public String getSpnForUpdate() {
		return spn;
	}

	/**
	 * @return
	 */
	public SocialSecurity getSsn() {
		return ssn;
	}

	/**
	 * @return
	 */
	public String getSsn1() {
		return ssn1;
	}

	/**
	 * @return
	 */
	public String getSsn2() {
		return ssn2;
	}

	/**
	 * @return
	 */
	public String getSsn3() {
		return ssn3;
	}

	/**
	 * @return
	 */
	public String getStateId() {
		return stateId;
	}

	/**
	 * @return
	 */
	public List getStateList() {
		return SimpleCodeTableHelper
				.getCodesSortedByDesc(PDCodeTableConstants.STATE_ABBR);
	}

	/**
	 * @return
	 */
	public Date getSupervisionBeginDate() {
		return supervisionBeginDate;
	}

	/**
	 * @return
	 */
	public String getSupervisionBeginDateAsString() {
		if (supervisionBeginDate == null) {
			return "";
		} else {
			return DateUtil.dateToString(supervisionBeginDate,
					UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @return
	 */
	public Date getSupervisionEndDate() {
		return supervisionEndDate;
	}

	/**
	 * @return
	 */
	public String getSupervisionEndDateAsString() {
		if (supervisionEndDate == null) {
			return "";
		} else {
			return DateUtil.dateToString(supervisionEndDate,
					UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @return
	 */
	public String getSupervisionLengthDay() {
		supervisionLengthDay = placeLeadingZero(supervisionLengthDay);
		return supervisionLengthDay;
	}

	/**
	 * @return
	 */
	public String getSupervisionLengthMonth() {
		supervisionLengthMonth = placeLeadingZero(supervisionLengthMonth);
		return supervisionLengthMonth;
	}

	/**
	 * @return
	 */
	public String getSupervisionLengthYear() {
		supervisionLengthYear = placeLeadingZero(supervisionLengthYear);
		return supervisionLengthYear;
	}

	/**
	 * @return
	 */
	public Date getTransferInDate() {
		return transferInDate;
	}

	/**
	 * @return
	 */
	public String getTransferInDateAsString() {
		if (transferInDate == null) {
			return "";
		} else {
			return DateUtil
					.dateToString(transferInDate, UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @param string
	 */
	public void setAction(String string) {
		action = string;
	}

	/**
	 * @param string
	 */
	public void setAgencyId(String string) {
		agencyId = string;
	}

	/**
	 * @param date
	 */
	public void setArrestDate(Date aDate) {
		arrestDate = aDate;
	}

	/**
	 * @param string
	 */
	public void setArrestDateAsString(String string) {
		if (string == null || string.trim().equals("")) {
			arrestDate = null;
		} else {
			arrestDate = DateUtil.stringToDate(string, UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @param collection
	 */
	public void setCaseList(Collection collection) {
		caseList = collection;
	}

	/**
	 * @param string
	 */
	public void setCaseListSize(String string) {
		caseListSize = string;
	}

	/**
	 * @param string
	 */
	public void setCaseNum(String string) {
		caseNum = string;
	}

	/**
	 * @param string
	 */
	public void setCaseStatusId(String string) {
		caseStatusId = string;
	}

	/**
	 * @param string
	 */
	public void setCdi(String string) {
		cdi = string;
	}

	/**
	 * @param string
	 */
	public void setCjisNum1(String string) {
		cjisNum1 = string;
	}

	/**
	 * @param string
	 */
	public void setCjisNum2(String string) {
		cjisNum2 = string;
	}

	/**
	 * @param string
	 */
	public void setConfinementLengthDay(String confinementLengthDay) {
        this.confinementLengthDay = confinementLengthDay;
        while(this.confinementLengthDay!=null && this.confinementLengthDay.trim().length() < 2){
        	this.confinementLengthDay = "0" + this.confinementLengthDay.trim();
        }
    }

	/**
	 * @param string
	 */
	public void setConfinementLengthMonth(String confinementLengthMonth) {
        this.confinementLengthMonth = confinementLengthMonth;
        while(this.confinementLengthMonth!=null && this.confinementLengthMonth.trim().length() < 2){
        	this.confinementLengthMonth = "0" + this.confinementLengthMonth.trim();
        }
    }

	/**
	 * @param string
	 */
	public void setConfinementLengthYear(String confinementLengthYear) {
        this.confinementLengthYear = confinementLengthYear;
        while(this.confinementLengthYear!=null && this.confinementLengthYear.trim().length() < 2){
        	this.confinementLengthYear = "0" + this.confinementLengthYear.trim();
        }
    }

	/**
	 * @param string
	 */
	public void setAgencyName(String string) {
		agencyName = string;
	}

	/**
	 * @param string
	 */
	public void setContactFirstName(String string) {
		contactFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setContactJobTitle(String string) {
		contactJobTitle = string;
	}

	/**
	 * @param string
	 */
	public void setContactLastName(String string) {
		contactLastName = string;
	}

	/**
	 * @param string
	 */
	public void setContactMiddleName(String string) {
		contactMiddleName = string;
	}

	/**
	 * @param name
	 */
	public void setContactName(Name name) {
		contactName = name;
	}

	/**
	 * @param string
	 */
	public void setContactPhone(String string) {
		contactPhone = string;
	}

	/**
	 * @param string
	 */
	public void setContactPhone1(String string) {
		contactPhone1 = string;
	}

	/**
	 * @param string
	 */
	public void setContactPhone2(String string) {
		contactPhone2 = string;
	}

	/**
	 * @param string
	 */
	public void setContactPhone3(String string) {
		contactPhone3 = string;
	}

	/**
	 * @param string
	 */
	public void setContactPhoneExt(String string) {
		contactPhoneExt = string;
	}

	/**
	 * @param string
	 */
	public void setCountyCodeId(String string) {
		countyCodeId = string;
	}

	/**
	 * @param string
	 */
	public void setCourtNum(String string) {
		courtNum = string;
	}

	/**
	 * @param date
	 */
	public void setDateOfBirth(Date date) {
		dateOfBirth = date;
	}

	/**
	 * @param string
	 */
	public void setDefendantId(String string) {
		defendantId = string;
	}

	/**
	 * @param date
	 */
	public void setDispositionDate(Date aDate) {
		dispositionDate = aDate;
	}

	/**
	 * @param string
	 */
	public void setDispositionDateAsString(String string) {
		if (string == null || string.trim().equals("")) {
			dispositionDate = null;
		} else {
			dispositionDate = DateUtil.stringToDate(string,
					UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @param string
	 */
	public void setDispositionTypeId(String string) {
		dispositionTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setDriverLicenseNum(String string) {
		driverLicenseNum = string;
	}

	/**
	 * @param string
	 */
	public void setFbiNum(String string) {
		fbiNum = string;
	}

	/**
	 * @param date
	 */
	public void setFilingDate(Date date) {
		filingDate = date;
	}

	/**
	 * @param string
	 */
	public void setFirstName(String string) {
		firstName = string;
	}

	/**
	 * @param string
	 */
	public void setInstrumentTypeId(String string) {
		instrumentTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setLastName(String string) {
		lastName = string;
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String string) {
		middleName = string;
	}

	/**
	 * @param name
	 */
	public void setName(String aName) {
		String fullName = aName;
		String first = "";
		String middle = "";
		String last = "";

		int i = fullName.indexOf(", ");
		if (i > 0) {
			last = fullName.substring(0, i);
			fullName = fullName.substring(i + 2);
		}
		int j = fullName.indexOf(" ");
		if (j > 0) {
			first = fullName.substring(0, j);
			middle = fullName.substring(j + 1);
		} else {
			first = fullName;
		}

		this.firstName = first;
		this.middleName = middle;
		this.lastName = last;
	}

	/**
	 * @param date
	 */
	public void setOffenseDate(Date aDate) {
		offenseDate = aDate;
	}

	/**
	 * @param string
	 */
	public void setOffenseDateAsString(String string) {
		if (string == null || string.trim().equals("")) {
			offenseDate = null;
		} else {
			offenseDate = DateUtil.stringToDate(string, UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @param string
	 */
	public void setOffenseId(String string) {
		offenseId = string;
	}

	/**
	 * @param string
	 */
	public void setOffenseListSize(String string) {
		offenseListSize = string;
	}

	/**
	 * @param string
	 */
	public void setOrgJurisCountyId(String string) {
		orgJurisCountyId = string;
	}

	/**
	 * @param collection
	 */
	public void setPartyList(Collection collection) {
		partyList = collection;
	}

	/**
	 * @param string
	 */
	public void setPartyListSize(String string) {
		partyListSize = string;
	}

	/**
	 * @param date
	 */
	public void setPretrialInterventionBegin(Date date) {
		pretrialInterventionBegin = date;
	}

	/**
	 * @param string
	 */
	public void setPretrialInterventionBeginAsString(String string) {
		if (string == null || string.trim().equals("")) {
			pretrialInterventionBegin = null;
		} else {
			pretrialInterventionBegin = DateUtil.stringToDate(string,
					UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @param date
	 */
	public void setPretrialInterventionEnd(Date date) {
		pretrialInterventionEnd = date;
	}

	/**
	 * @param value
	 */
	public void setPretrialInterventionEndAsString(String value) {
		if (value == null || value.trim().equals("")) {
			pretrialInterventionEnd = null;
		} else {
			pretrialInterventionEnd = DateUtil.stringToDate(value,
					UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @param value
	 */
	public void setPreviousDispositionTypeId(String value) {
		previousDispositionTypeId = value;
	}

	/**
	 * @param value
	 */
	public void setRaceId(String value) {
		raceId = value;
	}

	/**
	 * Sets the value of the reasonForUpdateHistoryList collection.
	 * 
	 * @param aReasonForUpdateHistoryList
	 *            the new value of the reasonForUpdateHistoryList collection
	 */
	public void setReasonForUpdateHistoryList(
			Collection aReasonForUpdateHistoryList) {
		reasonForUpdateHistoryList = aReasonForUpdateHistoryList;
	}

	/**
	 * @param value
	 */
	public void setReasonForUpdateId(String value) {
		reasonForUpdateId = value;
	}

	/**
	 * @param value
	 */
	public void setSecondaryAction(String value) {
		secondaryAction = value;
	}

	/**
	 * @param value
	 */
	public void setSelectedValue(String value) {
		selectedValue = value;
	}

	/**
	 * @param aDate
	 */
	public void setSentenceDate(Date date) {
		sentenceDate = date;
	}

	/**
	 * @param string
	 */
	public void setSentenceDateAsString(String string) {
		if (string == null || string.trim().equals("")) {
			sentenceDate = null;
		} else {
			sentenceDate = DateUtil
					.stringToDate(string, UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @param string
	 */
	public void setSexId(String string) {
		sexId = string;
	}

	/**
	 * @param string
	 */
	public void setSid(String string) {
		sid = string;
	}

	/**
	 * @param string
	 */
	public void setSpn(String string) {
		spn = string;
	}

	/**
	 * @param security
	 */
	public void setSsn(SocialSecurity security) {
		ssn = security;
	}

	/**
	 * @param string
	 */
	public void setSsn1(String string) {
		ssn1 = string;
	}

	/**
	 * @param string
	 */
	public void setSsn2(String string) {
		ssn2 = string;
	}

	/**
	 * @param string
	 */
	public void setSsn3(String string) {
		ssn3 = string;
	}

	/**
	 * @param string
	 */
	public void setStateId(String string) {
		stateId = string;
	}

	/**
	 * @param date
	 */
	public void setSupervisionBeginDate(Date aDate) {
		supervisionBeginDate = aDate;
	}

	/**
	 * @param string
	 */
	public void setSupervisionBeginDateAsString(String string) {
		if (string == null || string.trim().equals("")) {
			supervisionBeginDate = null;
		} else {
			supervisionBeginDate = DateUtil.stringToDate(string,
					UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @param date
	 */
	public void setSupervisionEndDate(Date aDate) {
		supervisionEndDate = aDate;
	}

	/**
	 * @param string
	 */
	public void setSupervisionEndDateAsString(String string) {
		if (string == null || string.trim().equals("")) {
			supervisionEndDate = null;
		} else {
			supervisionEndDate = DateUtil.stringToDate(string,
					UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @param string
	 */
	public void setSupervisionLengthDay(String supervisionLengthDay) {
		this.supervisionLengthDay = supervisionLengthDay;
        while(this.supervisionLengthDay!=null && this.supervisionLengthDay.trim().length() < 2){
        	this.supervisionLengthDay = "0" + this.supervisionLengthDay.trim();

        }
	}   

	/**
	 * @param string
	 */
	public void setSupervisionLengthMonth(String supervisionLengthMonth) {
		this.supervisionLengthMonth = supervisionLengthMonth;
        while(this.supervisionLengthMonth!=null && this.supervisionLengthMonth.trim().length() < 2){
        	this.supervisionLengthMonth = "0" + this.supervisionLengthMonth.trim();
        }
    }

	/**
	 * @param string
	 */
	public void setSupervisionLengthYear(String supervisionLengthYear) {
        this.supervisionLengthYear = supervisionLengthYear;
        while(this.supervisionLengthYear!=null && this.supervisionLengthYear.trim().length() < 2){
        	this.supervisionLengthYear = "0" + this.supervisionLengthYear.trim();
        }
    }

	/**
	 * @param date
	 */
	public void setTransferInDate(Date aDate) {
		transferInDate = aDate;
	}
	/**
	 * @param string
	 */
	public void setTransferInDateAsString(String string) {
		if (string == null || string.trim().equals("")) {
			transferInDate = null;
		} else {
			transferInDate = DateUtil.stringToDate(string,
					UIConstants.DATE_FMT_1);
		}
	}

	/**
	 * @return
	 */
	public String getFamilyViolenceInd() {
		return familyViolenceInd;
	}

	/**
	 * @return
	 */
	public String getFamilyViolenceIndDisplay() {
		return (getFamilyViolenceInd().equals(Y) ? YES : NO);
	}

	/**
	 * @return
	 */
	public String getOffenseViolenceInd() {
		String violentOffenseInd = PDConstants.BLANK;
		String tempOffenseId = UIUtil.stripZeroes(offenseId);
		if (tempOffenseId != null && !tempOffenseId.equals("")) {
			OffenseCodeResponseEvent ocre = CodeHelper
					.getOffenseCode(tempOffenseId);
			if (ocre != null) {
				violentOffenseInd = ocre.getViolentOffenseInd();
			}
		}
		// RRY changed this code to prevent null pointers
		if (violentOffenseInd == null
				|| PDConstants.BLANK.equals(violentOffenseInd)) {

			violentOffenseInd = NO;
		}
		return violentOffenseInd;
	}

	/**
	 * @return
	 */
	public String getOffenseViolenceIndDisplay() {
		return (getOffenseViolenceInd().equals(Y) ? YES : NO);
	}

	/**
	 * @param b
	 */
	public void setFamilyViolenceInd(String value) {
		familyViolenceInd = value;
	}

	/**
	 * @return
	 */
	public void setFamilyViolenceIndDisplay(String value) {
		setFamilyViolenceInd(value.equals(YES) ? Y : N);
	}

	/**
	 * @return
	 */
	public String getOrgJurisPID() {
		return orgJurisPID;
	}

	/**
	 * @return
	 */
	public String getState() {
		return CodeTableHelper.getDescrByCode(getStateList(), stateId);
	}

	/**
	 * @return
	 */
	public String getStreetType() {
		return CodeTableHelper
				.getDescrByCode(getStreetTypeList(), streetTypeId);
	}

	/**
	 * @return
	 */
	public String getStreetTypeId() {
		return streetTypeId;
	}

	/**
	 * @return
	 */
	public List getStreetTypeList() {
		return SimpleCodeTableHelper
				.getCodesSortedByDesc(PDCodeTableConstants.STREET_TYPE);
	}

	/**
	 * @param string
	 */
	public void setOrgJurisPID(String string) {
		orgJurisPID = string;
	}

	/**
	 * @param string
	 */
	public void setStreetTypeId(String string) {
		streetTypeId = string;
	}

	/**
	 * @return
	 */
	public String getOrgJurisCaseNum() {
		return orgJurisCaseNum;
	}

	/**
	 * @return
	 */
	public String getOrgJurisCourt() {
		return orgJurisCourt;
	}

	/**
	 * @param string
	 */
	public void setOrgJurisCaseNum(String string) {
		orgJurisCaseNum = string;
	}

	/**
	 * @param string
	 */
	public void setOrgJurisCourt(String string) {
		orgJurisCourt = string;
	}

	/**
	 * @return
	 */
	public List getAddressTypeList() {
		return SimpleCodeTableHelper
				.getCodesSortedByDesc(PDCodeTableConstants.ADDRESS_TYPE);
	}

	/**
	 * @return
	 */
	public String getAddressType() {
		return CodeTableHelper.getDescrByCode(getAddressTypeList(),
				addressTypeId);
	}

	/**
	 * @return
	 */
	public String getAddressTypeId() {
		return addressTypeId;
	}

	/**
	 * @param string
	 */
	public void setAddressTypeId(String string) {
		addressTypeId = string;
	}

	/**
	 * @return
	 */
	public String getAdditionalZipCode() {
		return additionalZipCode;
	}

	/**
	 * @return
	 */
	public String getAptNum() {
		return aptNum;
	}

	/**
	 * @return
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param string
	 */
	public void setCity(String string) {
		city = string;
	}

	/**
	 * @return
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @return
	 */
	public String getStreetNum() {
		return streetNum;
	}

	/**
	 * @return
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param string
	 */
	public void setAdditionalZipCode(String string) {
		additionalZipCode = string;
	}

	/**
	 * @param string
	 */
	public void setAptNum(String string) {
		aptNum = string;
	}

	/**
	 * @param string
	 */
	public void setStreetName(String string) {
		streetName = string;
	}

	/**
	 * @param string
	 */
	public void setStreetNum(String string) {
		streetNum = string;
	}

	/**
	 * @param string
	 */
	public void setZipCode(String string) {
		zipCode = string;
	}

	/**
	 * @param string
	 */
	public void setCaseTypeId(String string) {
		caseTypeId = string;
	}

	/**
	 * @return
	 */
	public boolean getReactivateInd() {
		return reactivateInd;
	}

	/**
	 * @param b
	 */
	public void setReactivateInd(boolean b) {
		reactivateInd = b;
	}

	/**
	 * @return
	 */
	public String getDegreeCodeId() {
		return degreeCodeId;
	}

	/**
	 * @return
	 */
	public String getLevelCodeId() {
		return levelCodeId;
	}

	/**
	 * @return
	 */
	public String getOffenseLiteral() {
		return offenseLiteral;
	}

	/**
	 * @return
	 */
	public String getPenalCodeId() {
		return penalCodeId;
	}

	/**
	 * @return
	 */
	public String getSearchOffenseId() {
		return searchOffenseId;
	}

	/**
	 * @return
	 */
	public String getStateOffenseCodeId() {
		return stateOffenseCodeId;
	}

	/**
	 * @param value
	 */
	public void setDegreeCodeId(String value) {
		degreeCodeId = value;
	}

	/**
	 * @param value
	 */
	public void setLevelCodeId(String value) {
		levelCodeId = value;
	}

	/**
	 * @param value
	 */
	public void setOffenseLiteral(String value) {
		offenseLiteral = value;
	}

	/**
	 * @param string
	 */
	public void setPenalCodeId(String value) {
		penalCodeId = value;
	}

	/**
	 * @param value
	 */
	public void setSearchOffenseId(String value) {
		searchOffenseId = value;
	}

	/**
	 * @param value
	 */
	public void setStateOffenseCodeId(String value) {
		stateOffenseCodeId = value;
	}

	/**
	 * @return
	 */
	public String getPreviousDispositionType() {
		return CodeTableHelper.getDescrByCode(getDispositionTypeList(),
				previousDispositionTypeId);
	}

	public String getCourtCategory() {
		String crtCategory = "";
		Object myObj = CodeTableHelper.getICodeObjFromCode(getCaseTypeList(),
				caseTypeId);
		if (myObj != null && myObj instanceof CourtResponseEvent) {
			CourtResponseEvent crtRespEvt = (CourtResponseEvent) myObj;
			crtCategory = crtRespEvt.getCourtCategory();
		}
		return crtCategory;
	}

	public String getCourtId() {
		String crtId = "";
		Object myObj = CodeTableHelper.getICodeObjFromCode(getCaseTypeList(),
				caseTypeId);
		if (myObj != null && myObj instanceof CourtResponseEvent) {
			CourtResponseEvent crtRespEvt = (CourtResponseEvent) myObj;
			crtId = crtRespEvt.getCourtId();
		}
		return crtId;
	}

	/**
	 * Gets all common values for an OutOfCountyCase.
	 * 
	 * @param oocCase
	 */
	public void fillOutOfCountyCase(ISupervisionCase oocCase) {
		// set the SPN
		oocCase.setSpn(getSpnForUpdate());
		oocCase.setDefendantId(getDefendantId());
		oocCase.setDefendantName(new Name(getFirstName(), getMiddleName(),
				getLastName()).getFormattedName());
		oocCase.setCdi(getCdi());
		oocCase.setCaseNum(getCaseNum());
		oocCase.setOffenseDate(getOffenseDate());
		oocCase.setOutOfCountyCaseTypeId(getCaseTypeId());
		oocCase.setTransferInDate(getTransferInDate());
		oocCase.setOriginalCaseNum(getOrgJurisCaseNum());
		oocCase.setContactFirstName(getContactFirstName());
		oocCase.setContactMiddleName(getContactMiddleName());
		oocCase.setContactLastName(getContactLastName());
		oocCase.setContactJobTitle(getContactJobTitle());
		oocCase.setContactPhoneNum(getContactPhone());
		oocCase.setContactPhoneExt(getContactPhoneExt());
		oocCase.setStateOffenseCodeId(getOffenseId());
		oocCase.setSupervisionBeginDate(getSupervisionBeginDate());
		oocCase.setSupervisionEndDate(getSupervisionEndDate());
		oocCase.setPersonId(getOrgJurisPID());
		oocCase.setReasonForUpdateId(getReasonForUpdateId()); //added for defect JIMS200072162
		oocCase.setOriginalCourtNum(getOrgJurisCourt());
		oocCase.setDispositionTypeId(getDispositionTypeId());
		oocCase.setDispositionDate(getDispositionDate());
		oocCase.setSentenceDate(getSentenceDate());
		oocCase.setArrestDate(getArrestDate());
		oocCase.setCjisNum(getCjis());
		oocCase.setConfinementDays(stringToInt(getConfinementLengthDay()));
		oocCase.setConfinementMonths(stringToInt(getConfinementLengthMonth()));
		oocCase.setConfinementYears(stringToInt(getConfinementLengthYear()));
		oocCase.setSupervisionDays(stringToInt(getSupervisionLengthDay()));
		oocCase.setSupervisionMonths(stringToInt(getSupervisionLengthMonth()));
		oocCase.setSupervisionYears(stringToInt(getSupervisionLengthYear()));
		oocCase
				.setPretrialInterventionBeginDate(getPretrialInterventionBegin());
		oocCase.setPretrialInterventionEndDate(getPretrialInterventionEnd());
		// //oocCase.setCountyId(getCountyCodeId());
		String rfuId = getReasonForUpdateId();
		if (rfuId != null && !rfuId.equals("")) {
			oocCase.setReasonForUpdateId(rfuId);
		}
		oocCase.setStateId(getStateId());
		// TODO: should we still be sending this or just let it come from the
		// offense
		oocCase.setViolentOffenseInd(getOffenseViolenceInd());
		oocCase.setFamilyViolenceInd(getFamilyViolenceInd());
		oocCase.setOriginalAgencyName(getAgencyName());
		oocCase.setOriginalCountyId(getOrgJurisCountyId());

		IAddress addressInfo = new AddressResponseEvent();
		addressInfo.setStreetNum(getStreetNum());
		addressInfo.setStreetName(getStreetName());
		addressInfo.setStreetTypeCode(getStreetTypeId());
		addressInfo.setAptNum(getAptNum());
		addressInfo.setCity(getCity());
		addressInfo.setStateCode(getAddressStateId());
		// addressInfo.setStateCode(getStateId());
		addressInfo.setZipCode(getZipCode());
		addressInfo.setAdditionalZipCode(getAdditionalZipCode());
		addressInfo.setAddressTypeCode(getAddressTypeId());
		// addressInfo.setCountyCode(getCountyCodeId());

		oocCase.setAgencyAddress(addressInfo);
	}

	/**
	 * Sets all values for an OutOfCountyCase.
	 * 
	 * @param oocCase
	 */
	public void setOutOfCountyCaseValues(ISupervisionCase oocCase) {
		setPrimaryKey(oocCase.getCdi() + oocCase.getCaseNum());
		setCdi(oocCase.getCdi());
		setCaseNum(oocCase.getCaseNum());
		setDefendantId(oocCase.getDefendantId());
		setCaseTypeId(oocCase.getOutOfCountyCaseTypeId());
		setOffenseDate(oocCase.getOffenseDate());
		setTransferInDate(oocCase.getTransferInDate());
		setOrgJurisCaseNum(oocCase.getOriginalCaseNum());
		setContactFirstName(oocCase.getContactFirstName());
		setContactMiddleName(oocCase.getContactMiddleName());
		setContactLastName(oocCase.getContactLastName());
		setContactName(new Name(contactFirstName, contactMiddleName,
				contactLastName));
		setContactJobTitle(oocCase.getContactJobTitle());
		String phoneNum = oocCase.getContactPhoneNum();
		if (phoneNum != null && !phoneNum.equals("")) {
			setContactPhone(phoneNum);
			setContactPhone1(phoneNum.substring(0, 3));
			setContactPhone2(phoneNum.substring(3, 6));
			setContactPhone3(phoneNum.substring(6, 10));
		}
		setContactPhoneExt(oocCase.getContactPhoneExt());
		setCourtNum(oocCase.getCourtNum());
		setOffenseId(oocCase.getStateOffenseCodeId());
		setSupervisionBeginDate(oocCase.getSupervisionBeginDate());
		setSupervisionEndDate(oocCase.getSupervisionEndDate());
		setOrgJurisPID(oocCase.getPersonId());
		setOrgJurisCourt(oocCase.getOriginalCourtNum());
		String previousDispositionType = oocCase.getPreviousDispositionTypeId();
		if (previousDispositionType != null) {
			setPreviousDispositionTypeId(previousDispositionType);
		}
		setDispositionTypeId(oocCase.getDispositionTypeId());
		setDispositionDate(oocCase.getDispositionDate());
		setSentenceDate(oocCase.getSentenceDate());
		setArrestDate(oocCase.getArrestDate());
		if (PDCodeTableConstants.CSCD.equals(oocCase.getCdi())) {
			String cJIS = oocCase.getCjisNum();
			if (cJIS != null && cJIS.length() == 14) {
				setCjisNum1(cJIS.substring(0, 10));
				setCjisNum2(cJIS.substring(10, 14));
			}
			setConfinementLengthDay("" + oocCase.getConfinementDays());
			setConfinementLengthMonth("" + oocCase.getConfinementMonths());
			setConfinementLengthYear("" + oocCase.getConfinementYears());
			setFilingDate(oocCase.getFilingDate());
			setSupervisionLengthDay("" + oocCase.getSupervisionDays());
			setSupervisionLengthMonth("" + oocCase.getSupervisionMonths());
			setSupervisionLengthYear("" + oocCase.getSupervisionYears());
			setPretrialInterventionBegin(oocCase
					.getPretrialInterventionBeginDate());
			setPretrialInterventionEnd(oocCase.getPretrialInterventionEndDate());
			setStateId(oocCase.getStateId());
			setOrgJurisCountyId(oocCase.getOriginalCountyId());

//			for update case closure, Transfer Out Date = Disposition Date, and Closure Reason = Disposition Type
			if(this.getAction().equalsIgnoreCase(CloseCaseConstants.OOC_CASE_UPDATE_CLOSURE))
			{
				Date dispositionDate = oocCase.getDispositionDate();
				if(dispositionDate!= null)
				{
					String transferOutDtStr = DateUtil.dateToString(dispositionDate, DateUtil.DATE_FMT_1);
					setTransferOutDateStr(transferOutDtStr);
				}
				
				String dispositionTypId = oocCase.getDispositionTypeId();
				if((dispositionTypId!= null) && (!dispositionTypId.equalsIgnoreCase("")))
				{
					setClosureReasonId(dispositionTypId);
				}
			}
		} else {
			String familyViolenceInd = oocCase.getFamilyViolenceInd();
			setFamilyViolenceInd(familyViolenceInd != null ? familyViolenceInd
					: N);
			setAgencyName(oocCase.getOriginalAgencyName());
			setOrgJurisCountyId(oocCase.getOriginalCountyId());
			setStateId(oocCase.getStateId());

			IAddress addressInfo = oocCase.getAgencyAddress();
			if (addressInfo != null) {
				setStreetNum(addressInfo.getStreetNum());
				setStreetName(addressInfo.getStreetName());
				setStreetTypeId(addressInfo.getStreetTypeCode());
				setAptNum(addressInfo.getAptNum());
				setCity(addressInfo.getCity());
				setAddressStateId(addressInfo.getStateCode());
				// setStateId(addressInfo.getStateCode());
				setZipCode(addressInfo.getZipCode());
				setAdditionalZipCode(addressInfo.getAdditionalZipCode());
				setAddressTypeId(addressInfo.getAddressTypeCode());
				// setCountyCodeId(addressInfo.getCountyCode());
			}
		}
		setReactivateInd(oocCase.canBeReactivated());
	}

	public void setPartyInfo(IParty party) {
		setFirstName(party.getPartyFirstName());
		setMiddleName(party.getPartyMiddleName());
		setLastName(party.getPartyLastName());
		setDateOfBirth(party.getPartyDateOfBirth());
		setRaceId(party.getPartyRaceId());
		setSexId(party.getPartySexId());
		setSpn(party.getPartySpn());
		setSid(party.getPartySID());
		setDefendantId(party.getPartyOid());
		SocialSecurity ssn = new SocialSecurity(party.getPartySSN());
		setNamePtr(party.getPartyNamePtr());
		setNameSeqNum(party.getPartyNameSeqNum());
		setSsn(ssn);
	}

	/**
	 * Sets all values for update history for an OutOfCountyCase.
	 * 
	 * @param updateHistory
	 */
	public void setOOCCaseUpdateHistoryValues(
			OOCCaseUpdateHistoryEvent updateHistory) {
		Collection historyColl = updateHistory.getUpdateHistory();
		List newList = new ArrayList();
		for (Iterator i = historyColl.iterator(); i.hasNext();) {
			// set the descriptions for the codes
			IUpdateHistory historyRecord = (IUpdateHistory) i.next();
			if (!historyRecord.getReasonForUpdate().equals("CL")){
				historyRecord.setReasonForUpdate(CodeTableHelper.getDescrByCode(
						getReasonForUpdateList(), historyRecord
							.getReasonForUpdate()));
				historyRecord.setUpdateUserName(SecurityUIHelper
					.getUserName(historyRecord.getUpdateUserId()));
				newList.add(historyRecord);
			}
		}
		// sort the list descending by date/time
		Collections.sort(newList);
		setReasonForUpdateHistoryList(newList);

	}

	private int stringToInt(String value) {
		if (value != null && !value.trim().equals("")) {
			return new Integer(value).intValue();
		}
		return 0;
	}

	/**
	 * Access method for the message property.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the value of the message property.
	 */
	public void setMessage(String aMessage) {
		message = aMessage;
	}

	/**
	 * @return Returns the addressStateId.
	 */
	public String getAddressStateId() {
		return addressStateId;
	}

	/**
	 * @param addressStateId
	 *            The addressStateId to set.
	 */
	public void setAddressStateId(String addressStateId) {
		this.addressStateId = addressStateId;
	}

	/**
	 * @return
	 */
	public String getAddressState() {
		return CodeTableHelper.getDescrByCode(getStateList(), addressStateId);
	}

	/**
	 * @param offenseDesc
	 *            The offenseDesc to set.
	 */
	public void setOffenseDesc(String offenseDesc) {
		this.offenseDesc = offenseDesc;
	}
	
	private String placeLeadingZero(String length) {
		if ((length != null) && (length.length() == 1)) {
			length = "0" + length;
		}
		return length;
	}
	
	private String placeTwoLeadingZeroes(String length) {
		if ((length != null) && (length.length() == 1)) {
			length = "00" + length;
		}
		if ((length != null) && (length.length() == 2)) {
			length = "0" + length;
		}
		return length;
	}


	/**
	 * @return the closeCaseAssignmentList
	 */
	public List getCloseCaseAssignmentList() {
		return closeCaseAssignmentList;
	}

	/**
	 * @param closeCaseAssignmentList the closeCaseAssignmentList to set
	 */
	public void setCloseCaseAssignmentList(List closeCaseAssignmentList) {
		this.closeCaseAssignmentList = closeCaseAssignmentList;
	}

	/**
	 * @return the closureReasonId
	 */
	public String getClosureReasonId() {
		return closureReasonId;
	}

	/**
	 * @param closureReasonId the closureReasonId to set
	 */
	public void setClosureReasonId(String closureReasonId) {
		this.closureReasonId = closureReasonId;
	}
 
	/**
	 * @return the transferOutDateStr
	 */
	public String getTransferOutDateStr() {
		return transferOutDateStr;
	}
	private Date transferOutDateAsDate;
	public void setTransferOutDateAsDate(Date aDate){
		this.transferOutDateAsDate = aDate;
	}
	public Date getTransferOutDateAsDate(){
		Date aDate = null;
		if (transferOutDateStr != null && !transferOutDateStr.equals("")){
			aDate = DateUtil.stringToDate(transferOutDateStr, DateUtil.DATE_FMT_1);
		}
		return aDate;
	}
	/**
	 * @param transferOutDateStr the transferOutDateStr to set
	 */
	public void setTransferOutDateStr(String transferOutDateStr) {
		this.transferOutDateStr = transferOutDateStr;
	}
	
	public String getClosureReason()
	{
		return SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.OOC_DISPOSITION_TYPE, this.closureReasonId);
	}
	
	/**
	 * @return the closeCasesResultBeanList
	 */
	public List getCloseCasesResultBeanList() {
		return closeCasesResultBeanList;
	}

	/**
	 * @param closeCasesResultBeanList the closeCasesResultBeanList to set
	 */
	public void setCloseCasesResultBeanList(List closeCasesResultBeanList) {
		this.closeCasesResultBeanList = closeCasesResultBeanList;
	}

	/**
	 * @return the closeCaseSuccess
	 */
	public boolean isCloseCaseSuccess() {
		return closeCaseSuccess;
	}

	/**
	 * @param closeCaseSuccess the closeCaseSuccess to set
	 */
	public void setCloseCaseSuccess(boolean closeCaseSuccess) {
		this.closeCaseSuccess = closeCaseSuccess;
	}
	
	public String getCriminalCaseId()
	{
		String criminalCaseId = this.cdi + this.caseNum;
		return criminalCaseId;
	}

	public void setNamePtr(String namePtr) {
		this.namePtr = namePtr;
	}

	public String getNamePtr() {
		return namePtr;
	}

	/**
	 * @return the currentNamePtr
	 */
	public String getCurrentNamePtr() {
		return currentNamePtr;
	}

	/**
	 * @param currentNamePtr the currentNamePtr to set
	 */
	public void setCurrentNamePtr(String currentNamePtr) {
		this.currentNamePtr = currentNamePtr;
	}

	public void setNameSeqNum(String nameSeqNum) {
		this.nameSeqNum = nameSeqNum;
	}

	public String getNameSeqNum() {
		nameSeqNum = placeTwoLeadingZeroes(nameSeqNum);
		return nameSeqNum;
	}

	public void setCurrentNameSeqNum(String currentNameSeqNum) {
		this.currentNameSeqNum = currentNameSeqNum;
	}

	public String getCurrentNameSeqNum() {
		currentNameSeqNum = placeTwoLeadingZeroes(currentNameSeqNum);
		return currentNameSeqNum;
	}
	
}