package pd.criminalcase;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import naming.PDConstants;
import naming.UIConstants;

import messaging.criminalcase.GetCasesByCriminalCaseIdsEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.DateUtil;
import pd.codetable.Code;
import pd.codetable.criminal.OffenseCode;
import pd.contact.party.Party;
import pd.supervision.Court;

/**
 * @author dgibler
 * @roseuid 43B2E4ED031C
 */
public class CriminalCase extends PersistentObject {
	/**
	 * @param aCaseId
	 */
	static public CriminalCase find(String aCaseId) {
		IHome home = new Home();
		return (CriminalCase) home.find(aCaseId, CriminalCase.class);
	}

	/**
	 * @return
	 * @param event
	 */
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, CriminalCase.class);
	}

	public static Map getCriminalCasesByIds(String criminalcaseIds){
		GetCasesByCriminalCaseIdsEvent tEvent = new GetCasesByCriminalCaseIdsEvent();
		tEvent.setCriminalCaseIds(criminalcaseIds);
		Iterator caseIterator = CriminalCase.findAll(tEvent);
		Map casesMap = new HashMap();
		CriminalCase cCase = null;
		while(caseIterator.hasNext()){
			cCase = (CriminalCase) caseIterator.next();
			if(!casesMap.containsKey(cCase.getCriminalCaseId())){
				casesMap.put(cCase.getCriminalCaseId(), cCase);
			}
		}
		return casesMap;
	}

	private String bondAmt;

	private String caseNum;

	/**
	 * Properties for caseStatus
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey NEW_CONTEXT_KEY_NEEDED
	 * @detailerDoNotGenerate true
	 */
	private Code caseStatus = null;

	private String caseStatusId;

	private String completionDate;

	/**
	 * Properties for costBillSummaries
	 * 
	 * @referencedType pd.criminalcase.CostBillSummary
	 */
	private java.util.Collection costBillSummaries = null;

	/**
	 * Properties for court
	 */
	private Court court = null;

	/**
	 * Properties for courtDivision
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey NEW_CONTEXT_KEY_NEEDED
	 * @detailerDoNotGenerate true
	 */
	private Code courtDivision = null;

	private String courtDivisionId;

	private String courtId;

	// this has been added to accomodate a view, please do not remove unless it is breaking anything
	private String criminalCaseId = null;

	/**
	 * Properties for currentCostBillSummary
	 */
	private CostBillSummary currentCostBillSummary = null;

	private String currentCostBillSummaryId;

	/**
	 * Properties for defendant
	 * 
	 * @referencedType pd.contact.party.Party
	 */
	private Party defendant = null;

	private String defendantId;

	private String defendantName;

	/**
	 * Properties for defendantStatus
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey NEW_CONTEXT_KEY_NEEDED
	 * @detailerDoNotGenerate true
	 */
	private Code defendantStatus = null;

	private String defendantStatusId;

	private String filingDate;

	/**
	 * Properties for instrumentType
	 * 
	 * @referencedType pd.codetable.Code
	 * @contextKey NEW_CONTEXT_KEY_NEEDED
	 * @detailerDoNotGenerate true
	 */
	private Code instrumentType = null;

	private String instrumentTypeId;

	private String nextSettingDate;

	/**
	 * Properties for offenseCode
	 * 
	 * @referencedType pd.codetable.criminal.OffenseCode
	 * @detailerDoNotGenerate true
	 */
	private OffenseCode offenseCode = null;

	private String offenseCodeId;

	/**
	 * Properties for parties
	 * 
	 * @referencedType pd.contact.party.Party
	 */
	private java.util.Collection parties = null;
	private String probationOfficerId = null;
	
	/**
	 * Properties for supervisionOrders
	 * 
	 * @referencedType pd.supervision.supervisionorder.SupervisionOrder
	 */
	private java.util.Collection supervisionOrders = null;
	/**
	 * Properties for supervisions
	 * 
	 * @referencedType pd.criminalcase.Supervision
	 */
	private java.util.Collection supervisions = null;

	private String zipCode = null;

	/**
	 * @roseuid 43B2E4ED031C
	 */
	public CriminalCase() {
	}

	/**
	 * Clears all pd.criminalcase.CostBillSummary from class relationship
	 * collection.
	 */
	public void clearCostBillSummaries() {
		initCostBillSummaries();
		costBillSummaries.clear();
	}

	/**
	 * Clears all pd.contact.party.Party from class relationship collection.
	 */
	public void clearParties() {
		initParties();
		parties.clear();
	}

	/**
	 * Clears all pd.supervision.supervisionorder.SupervisionOrder from class
	 * relationship collection.
	 */
	public void clearSupervisionOrders() {
		initSupervisionOrders();
		supervisionOrders.clear();
	}

	/**
	 * Clears all pd.criminalcase.Supervision from class relationship
	 * collection.
	 */
	public void clearSupervisions() {
		initSupervisions();
		supervisions.clear();
	}

	/**
	 * @roseuid 43B2E2380128
	 */
	public void find() {
		fetch();
	}

	/**
	 * @param spn
	 * @param caseNum
	 * @param courtDivision
	 * @roseuid 43B2E2380129
	 */
	public void findAll(String spn, String caseNum, String courtDivision) {
		fetch();
	}

	/**
	 * @return
	 */
	public String getBondAmt() {
		fetch();
		return bondAmt;
	}

	/**
	 * @return
	 */
	public String getCaseNum() {
		fetch();
		return caseNum;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getCaseStatus() {
		fetch();
		initCaseStatus();
		return caseStatus;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getCaseStatusId() {
		fetch();
		return caseStatusId;
	}

	/**
	 * @return
	 */
	public String getCompletionDate() {
		fetch();
		return completionDate;
	}

	/**
	 * returns a collection of pd.criminalcase.CostBillSummary
	 */
	public java.util.Collection getCostBillSummaries() {
		fetch();
		initCostBillSummaries();
		return costBillSummaries;
	}

	/**
	 * Gets referenced type pd.supervision.Court
	 */
	public Court getCourt() {
		fetch();
		initCourt();
		return court;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getCourtDivision() {
		fetch();
		initCourtDivision();
		return courtDivision;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getCourtDivisionId() {
		fetch();
		return courtDivisionId;
	}

	/**
	 * Get the reference value to class :: pd.supervision.Court
	 */
	public String getCourtId() {
		fetch();
		// fix to avoid spaces coming from VSAM
		return courtId.trim();
	}

	public String getCriminalCaseId() {
		return criminalCaseId;
	}

	/**
	 * Gets referenced type pd.criminalcase.CostBillSummary
	 */
	public CostBillSummary getCurrentCostBillSummary() {
		fetch();
		initCurrentCostBillSummary();
		return currentCostBillSummary;
	}

	/**
	 * Get the reference value to class :: pd.criminalcase.CostBillSummary
	 */
	public String getCurrentCostBillSummaryId() {
		fetch();
		return currentCostBillSummaryId;
	}

	/**
	 * Gets referenced type pd.contact.party.Party
	 */
	public Party getDefendant() {
		fetch();
		initDefendant();
		return defendant;
	}

	/**
	 * Get the reference value to class :: pd.contact.party.Party
	 */
	public String getDefendantId() {
		fetch();
		return defendantId;
	}

	/**
	 * @return
	 */
	public String getDefendantName() {
		fetch();
		return defendantName;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getDefendantStatus() {
		fetch();
		initDefendantStatus();
		return defendantStatus;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getDefendantStatusId() {
		fetch();
		return defendantStatusId;
	}

	/**
	 * @return
	 */
	public String getFilingDate() {
		fetch();
		return filingDate;
	}
	/**
	 * @return
	 */
	public String getFormattedFilingDate() {
		fetch();
		Date aDate = null;
		String aDateString = PDConstants.BLANK;
		if (filingDate != null
				&& !filingDate.equals(PDConstants.SIX_ZEROES)
				&& !filingDate.equals(PDConstants.NONE)) {
			aDate = DateUtil.stringToDate(filingDate,
					PDConstants.DATE_FORMAT_MMDDYY);
			if (aDate != null) {
				aDateString = DateUtil.dateToString(aDate,
						UIConstants.DATE_FMT_1);
			}
		}
		return aDateString;
	}
	/**
	 * @return
	 */
	public String getFormattedNextSettingDate() {
		fetch();
		Date aDate = null;
		String aDateString = PDConstants.BLANK;
		if (nextSettingDate != null
				&& !nextSettingDate.equals(PDConstants.SIX_ZEROES)
				&& !nextSettingDate.equals(PDConstants.NONE)) {
			aDate = DateUtil.stringToDate(nextSettingDate,
					PDConstants.DATE_FORMAT_MMDDYY);
			if (aDate != null) {
				aDateString = DateUtil.dateToString(aDate,
						UIConstants.DATE_FMT_1);
			}
		}
		return aDateString;
	}
	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getInstrumentType() {
		fetch();
		initInstrumentType();
		return instrumentType;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getInstrumentTypeId() {
		fetch();
		return instrumentTypeId;
	}

	/**
	 * @return Returns the nextSettingDate.
	 */
	public String getNextSettingDate() {
		fetch();
		return nextSettingDate;
	}

	/**
	 * Gets referenced type pd.codetable.criminal.OffenseCode
	 */
	public OffenseCode getOffenseCode() {
		fetch();
		initOffenseCode();
		return offenseCode;
	}

	/**
	 * Get the reference value to class :: pd.codetable.criminal.OffenseCode
	 */
	public String getOffenseCodeId() {
		fetch();
		return offenseCodeId;
	}

	/**
	 * returns a collection of pd.contact.party.Party
	 */
	public java.util.Collection getParties() {
		fetch();
		initParties();
		return parties;
	}

	/**
	 * @return Returns the probationOfficerId.
	 */
	public String getProbationOfficerId() {
		return probationOfficerId;
	}

	/**
	 * returns a collection of pd.supervision.supervisionorder.SupervisionOrder
	 */
	public java.util.Collection getSupervisionOrders() {
		fetch();
		initSupervisionOrders();
		return supervisionOrders;
	}

	/**
	 * returns a collection of pd.criminalcase.Supervision
	 */
	public java.util.Collection getSupervisions() {
		fetch();
		initSupervisions();
		return supervisions;
	}

	public String getZipCode() {
		fetch();
		return zipCode;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initCaseStatus() {
		if (caseStatus == null) {
			caseStatus = (Code) new mojo.km.persistence.Reference(
					caseStatusId, Code.class, "CASE_STATUS")
					.getObject();
		}
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.criminalcase.CostBillSummary
	 */
	private void initCostBillSummaries() {
		if (costBillSummaries == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			costBillSummaries = new mojo.km.persistence.ArrayList(
					CostBillSummary.class, "criminalCaseId", ""
							+ getOID());
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.Court
	 */
	private void initCourt() {
		if (court == null) {
			court = (Court) new mojo.km.persistence.Reference(
					courtId, Court.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initCourtDivision() {
		if (courtDivision == null) {
			courtDivision = (Code) new mojo.km.persistence.Reference(
					courtDivisionId, Code.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.criminalcase.CostBillSummary
	 */
	private void initCurrentCostBillSummary() {
		if (currentCostBillSummary == null) {
			currentCostBillSummary = (CostBillSummary) new mojo.km.persistence.Reference(
					currentCostBillSummaryId,
					CostBillSummary.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.contact.party.Party
	 */
	private void initDefendant() {
		if (defendant == null) {
			defendant = (Party) new mojo.km.persistence.Reference(
					defendantId, Party.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initDefendantStatus() {
		if (defendantStatus == null) {
			defendantStatus = (Code) new mojo.km.persistence.Reference(
					defendantStatusId, Code.class,
					"DEFENDANT_STATUS").getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initInstrumentType() {
		if (instrumentType == null) {
			instrumentType = (Code) new mojo.km.persistence.Reference(
					instrumentTypeId, Code.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.criminal.OffenseCode
	 */
	private void initOffenseCode() {
		if (offenseCode == null) {
			offenseCode = (OffenseCode) new mojo.km.persistence.Reference(
					offenseCodeId, OffenseCode.class)
					.getObject();
		}
	}

	/**
	 * Initialize class relationship implementation for pd.contact.party.Party
	 */
	private void initParties() {
		if (parties == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			parties = new mojo.km.persistence.ArrayList(
					Party.class, "criminalCaseId", ""
							+ getOID());
		}
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.supervision.supervisionorder.SupervisionOrder
	 */
	private void initSupervisionOrders() {
		if (supervisionOrders == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			supervisionOrders = new mojo.km.persistence.ArrayList(
					pd.supervision.supervisionorder.SupervisionOrder.class,
					"criminalCaseId", "" + getOID());
		}
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.criminalcase.Supervision
	 */
	private void initSupervisions() {
		if (supervisions == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			supervisions = new mojo.km.persistence.ArrayList(
					Supervision.class, "criminalCaseId", ""
							+ getOID());
		}
	}

	/**
	 * insert a pd.criminalcase.CostBillSummary into class relationship
	 * collection.
	 */
	public void insertCostBillSummaries(CostBillSummary anObject) {
		initCostBillSummaries();
		costBillSummaries.add(anObject);
	}

	/**
	 * insert a pd.contact.party.Party into class relationship collection.
	 */
	public void insertParties(Party anObject) {
		initParties();
		parties.add(anObject);
	}

	/**
	 * insert a pd.supervision.supervisionorder.SupervisionOrder into class
	 * relationship collection.
	 */
	public void insertSupervisionOrders(
			pd.supervision.supervisionorder.SupervisionOrder anObject) {
		initSupervisionOrders();
		supervisionOrders.add(anObject);
	}

	/**
	 * insert a pd.criminalcase.Supervision into class relationship collection.
	 */
	public void insertSupervisions(Supervision anObject) {
		initSupervisions();
		supervisions.add(anObject);
	}

	/**
	 * Removes a pd.criminalcase.CostBillSummary from class relationship
	 * collection.
	 */
	public void removeCostBillSummaries(CostBillSummary anObject) {
		initCostBillSummaries();
		costBillSummaries.remove(anObject);
	}

	/**
	 * Removes a pd.contact.party.Party from class relationship collection.
	 */
	public void removeParties(Party anObject) {
		initParties();
		parties.remove(anObject);
	}

	/**
	 * Removes a pd.supervision.supervisionorder.SupervisionOrder from class
	 * relationship collection.
	 */
	public void removeSupervisionOrders(
			pd.supervision.supervisionorder.SupervisionOrder anObject) {
		initSupervisionOrders();
		supervisionOrders.remove(anObject);
	}

	/**
	 * Removes a pd.criminalcase.Supervision from class relationship collection.
	 */
	public void removeSupervisions(Supervision anObject) {
		initSupervisions();
		supervisions.remove(anObject);
	}

	/**
	 * @param aBondAmt
	 */
	public void setBondAmt(String aBondAmt) {
		if (this.bondAmt == null || !this.bondAmt.equals(aBondAmt)) {
			markModified();
		}
		bondAmt = aBondAmt;
	}

	/**
	 * @param aCaseNum
	 */
	public void setCaseNum(String aCaseNum) {
		if (this.caseNum == null || !this.caseNum.equals(aCaseNum)) {
			markModified();
		}
		caseNum = aCaseNum;
	}

	/**
	 * set the type reference for class member caseStatus
	 */
	public void setCaseStatus(Code aCaseStatus) {
		if (this.caseStatus == null || !this.caseStatus.equals(aCaseStatus)) {
			markModified();
		}
		if (aCaseStatus.getOID() == null) {
			new mojo.km.persistence.Home().bind(aCaseStatus);
		}
		setCaseStatusId("" + aCaseStatus.getOID());
		this.caseStatus = (Code) new mojo.km.persistence.Reference(
				aCaseStatus).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setCaseStatusId(String aCaseStatusId) {
		if (this.caseStatusId == null
				|| !this.caseStatusId.equals(aCaseStatusId)) {
			markModified();
		}
		caseStatus = null;
		this.caseStatusId = aCaseStatusId;
	}

	/**
	 * @param aCompletionDate
	 */
	public void setCompletionDate(String aCompletionDate) {
		if (this.completionDate == null
				|| !this.completionDate.equals(aCompletionDate)) {
			markModified();
		}
		completionDate = aCompletionDate;
	}

	/**
	 * set the type reference for class member court
	 */
	public void setCourt(Court aCourt) {
		if (this.court == null || !this.court.equals(aCourt)) {
			markModified();
		}
		if (aCourt.getOID() == null) {
			new mojo.km.persistence.Home().bind(aCourt);
		}
		setCourtId("" + aCourt.getOID());
		this.court = (Court) new mojo.km.persistence.Reference(
				aCourt).getObject();
	}

	/**
	 * set the type reference for class member courtDivision
	 */
	public void setCourtDivision(Code aCourtDivision) {
		if (this.courtDivision == null
				|| !this.courtDivision.equals(aCourtDivision)) {
			markModified();
		}
		if (aCourtDivision.getOID() == null) {
			new mojo.km.persistence.Home().bind(aCourtDivision);
		}
		setCourtDivisionId("" + aCourtDivision.getOID());
		this.courtDivision = (Code) new mojo.km.persistence.Reference(
				aCourtDivision).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setCourtDivisionId(String aCourtDivisionId) {
		if (this.courtDivisionId == null
				|| !this.courtDivisionId.equals(aCourtDivisionId)) {
			markModified();
		}
		courtDivision = null;
		this.courtDivisionId = aCourtDivisionId;
	}

	/**
	 * Set the reference value to class :: pd.supervision.Court
	 */
	public void setCourtId(String aCourtId) {
		if (this.courtId == null || !this.courtId.equals(aCourtId)) {
			markModified();
		}
		court = null;
		this.courtId = aCourtId;
	}

	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}

	/**
	 * set the type reference for class member currentCostBillSummary
	 */
	public void setCurrentCostBillSummary(
			CostBillSummary aCurrentCostBillSummary) {
		if (this.currentCostBillSummary == null
				|| !this.currentCostBillSummary.equals(aCurrentCostBillSummary)) {
			markModified();
		}
		if (aCurrentCostBillSummary.getOID() == null) {
			new mojo.km.persistence.Home().bind(aCurrentCostBillSummary);
		}
		setCurrentCostBillSummaryId("" + aCurrentCostBillSummary.getOID());
		this.currentCostBillSummary = (CostBillSummary) new mojo.km.persistence.Reference(
				aCurrentCostBillSummary).getObject();
	}

	/**
	 * Set the reference value to class :: pd.criminalcase.CostBillSummary
	 */
	public void setCurrentCostBillSummaryId(String aCurrentCostBillSummaryId) {
		if (this.currentCostBillSummaryId == null
				|| !this.currentCostBillSummaryId
						.equals(aCurrentCostBillSummaryId)) {
			markModified();
		}
		currentCostBillSummary = null;
		this.currentCostBillSummaryId = aCurrentCostBillSummaryId;
	}

	/**
	 * set the type reference for class member defendant
	 */
	public void setDefendant(Party aDefendant) {
		if (this.defendant == null || !this.defendant.equals(aDefendant)) {
			markModified();
		}
		if (aDefendant.getOID() == null) {
			new mojo.km.persistence.Home().bind(aDefendant);
		}
		setDefendantId("" + aDefendant.getOID());
		this.defendant = (Party) new mojo.km.persistence.Reference(
				aDefendant).getObject();
	}

	/**
	 * Set the reference value to class :: pd.contact.party.Party
	 */
	public void setDefendantId(String aDefendantId) {
		if (this.defendantId == null || !this.defendantId.equals(aDefendantId)) {
			markModified();
		}
		defendant = null;
		this.defendantId = aDefendantId;
	}

	/**
	 * @param string
	 */
	public void setDefendantName(String string) {
		if (this.defendantName == null || !this.defendantName.equals(string)) {
			markModified();
		}
		defendantName = string;
	}

	/**
	 * set the type reference for class member defendantStatus
	 */
	public void setDefendantStatus(Code aDefendantStatus) {
		if (this.defendantStatus == null
				|| !this.defendantStatus.equals(aDefendantStatus)) {
			markModified();
		}
		if (aDefendantStatus.getOID() == null) {
			new mojo.km.persistence.Home().bind(aDefendantStatus);
		}
		setDefendantStatusId("" + aDefendantStatus.getOID());
		this.defendantStatus = (Code) new mojo.km.persistence.Reference(
				aDefendantStatus).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setDefendantStatusId(String aDefendantStatusId) {
		if (this.defendantStatusId == null
				|| !this.defendantStatusId.equals(aDefendantStatusId)) {
			markModified();
		}
		defendantStatus = null;
		this.defendantStatusId = aDefendantStatusId;
	}

	/**
	 * @param aFilingDate
	 */
	public void setFilingDate(String aFilingDate) {
		if (this.filingDate == null || !this.filingDate.equals(aFilingDate)) {
			markModified();
		}
		filingDate = aFilingDate;
	}

	/**
	 * set the type reference for class member instrumentType
	 */
	public void setInstrumentType(Code anInstrumentType) {
		if (this.instrumentType == null
				|| !this.instrumentType.equals(anInstrumentType)) {
			markModified();
		}
		if (anInstrumentType.getOID() == null) {
			new mojo.km.persistence.Home().bind(anInstrumentType);
		}
		setInstrumentTypeId("" + anInstrumentType.getOID());
		this.instrumentType = (Code) new mojo.km.persistence.Reference(
				anInstrumentType).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setInstrumentTypeId(String anInstrumentTypeId) {
		if (this.instrumentTypeId == null
				|| !this.instrumentTypeId.equals(anInstrumentTypeId)) {
			markModified();
		}
		instrumentType = null;
		this.instrumentTypeId = anInstrumentTypeId;
	}

	/**
	 * @param nextSettingDate
	 *            The nextSettingDate to set.
	 */
	public void setNextSettingDate(String aNextSettingDate) {
		if (this.nextSettingDate == null
				|| !this.nextSettingDate.equals(aNextSettingDate)) {
			markModified();
		}
		nextSettingDate = aNextSettingDate;
	}
	/**
	 * set the type reference for class member offenseCode
	 */
	public void setOffenseCode(OffenseCode anOffenseCode) {
		if (this.offenseCode == null || !this.offenseCode.equals(anOffenseCode)) {
			markModified();
		}
		if (anOffenseCode.getOID() == null) {
			new mojo.km.persistence.Home().bind(anOffenseCode);
		}
		setOffenseCodeId("" + anOffenseCode.getOID());
		this.offenseCode = (OffenseCode) new mojo.km.persistence.Reference(
				anOffenseCode).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.criminal.OffenseCode
	 */
	public void setOffenseCodeId(String anOffenseCodeId) {
		if (this.offenseCodeId == null
				|| !this.offenseCodeId.equals(anOffenseCodeId)) {
			markModified();
		}
		offenseCode = null;
		this.offenseCodeId = anOffenseCodeId;
	}

	/**
	 * @param probationOfficerId The probationOfficerId to set.
	 */
	public void setProbationOfficerId(String probationOfficerId) {
		this.probationOfficerId = probationOfficerId;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
