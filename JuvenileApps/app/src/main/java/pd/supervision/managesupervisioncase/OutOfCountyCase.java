//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\managesupervisioncase\\OutOfCountyCase.java

package pd.supervision.managesupervisioncase;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.domintf.managesupervisioncase.IParty;
import messaging.domintf.managesupervisioncase.ISupervisionCase;
import messaging.managesupervisioncase.CloseOutOfCountyCaseEvent;
import messaging.managesupervisioncase.GetOutOfCountyCaseEvent;
import messaging.managesupervisioncase.reply.ValidateResponseEvent;
import messaging.managetask.CreateCSTaskEvent;
import messaging.party.GetPartyDataEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.persistence.Home;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import naming.CSCAssessmentConstants;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.UIConstants;
import pd.common.util.NameUtil;
import pd.contact.party.Party;
import pd.criminalcase.CriminalCase;
import pd.km.util.Name;
import pd.security.PDSecurityHelper;
import pd.supervision.csts.CSTSHelper;
import pd.supervision.managetask.PDTaskHelper;
import pd.supervision.manageworkgroup.WorkGroup;
import pd.supervision.manageworkgroup.WorkGroupHelper;

public abstract class OutOfCountyCase extends CriminalCase
{
	public static boolean newCase(ISupervisionCase oocCase)
	{
		// check for new or update
		if (oocCase.isCreate())
		{
			return true;
		}
		return false;
	}
	private String contactFirstName;
	private String contactJobTitle;
	private String contactLastName;
	private String contactMiddleName;
	private String contactPhoneExt;
	private String contactPhoneNum;
	private Date offenseDate;
	private String originalCaseNum;
	private String originalCountyId;
	private String originalCourtNum;
	private String outOfCountyCaseTypeId;
	private String spn;
	private String stateId;
	private String reasonForUpdateId; //added for defect JIMS200072162
	public String getReasonForUpdateId() {
		return reasonForUpdateId;
	}

	public void setReasonForUpdateId(String reasonForUpdateId) {
		this.reasonForUpdateId = reasonForUpdateId;
	}
	private Date supervisionBeginDate;
	private Date supervisionEndDate;
	private Date transferInDate;
	private String nameSeqNum;
	private String namePtr;
	private String updateT11Record;
	private String updateT30Record;
	// added to resolve the Name issue.  This is NOT a good resolution.

	protected transient boolean valid = true;
	/**
	 * @roseuid 4445472601F9
	 */
	public OutOfCountyCase()
	{

	}

	public abstract boolean canBeReactivated();

	/**
	 * Closes an OOC case by setting the appropriate values for an 
	 * OutOfCountyCase being closed.
	 * 
	 * @param closedStatus
	 */
	public void closeOutOfCountyCase(String closedStatus)
	{
		// set Case Status
		setCaseStatusId("C");
	}

	/**
	 * Gets all common values for an OutOfCountyCase.
	 * 
	 * @param oocCase
	 */
	public void fillOutOfCountyCase(ISupervisionCase oocCase, boolean isReactivate)
	{
		oocCase.setOffenseDate(getOffenseDate());
		oocCase.setTransferInDate(getTransferInDate());
		oocCase.setOriginalCaseNum(getOriginalCaseNum());
		oocCase.setOriginalCountyId(getOriginalCountyId());
		oocCase.setStateId(getStateId());
		oocCase.setOriginalCourtNum(getOriginalCourtNum());
		oocCase.setContactFirstName(getContactFirstName());
		oocCase.setContactMiddleName(getContactMiddleName());
		oocCase.setContactLastName(getContactLastName());
		oocCase.setContactJobTitle(getContactJobTitle());
		oocCase.setContactPhoneNum(getContactPhoneNum());
		oocCase.setContactPhoneExt(getContactPhoneExt());
		oocCase.setDefendantId(getDefendantId());
		oocCase.setDefendantName(getDefendantName());
		//oocCase.setFilingDate(DateUtil.convertYYYYMMDD(getFilingDate()));
		oocCase.setFilingDate(DateUtil.stringToDate(getFilingDate(), "yyyyMMdd"));
		oocCase.setSpn(getSpn());
		oocCase.setCaseNum(getCaseNum());
		oocCase.setCdi(getCourtDivisionId());
		oocCase.setStateOffenseCodeId(getOffenseCodeId());
		// set the indicator for whether the case can be reactivated
		oocCase.setReactivationInd(canBeReactivated()); 
		oocCase.setReasonForUpdateId(getReasonForUpdateId()); //added for defect JIMS200072162
		oocCase.setSupervisionBeginDate(getSupervisionBeginDate());
		oocCase.setSupervisionEndDate(getSupervisionEndDate());
		/*if (getCourt() != null)
		{
			oocCase.setCourtNum(getCourt().getCourtNumber());
		}*/
		oocCase.setCourtNum(getOutOfCountyCaseTypeId());
		oocCase.setOutOfCountyCaseTypeId(getOutOfCountyCaseTypeId());
	}

	public String getConnectionId()
	{
		// for OOC cases, make it always Defendant
		return PDCodeTableConstants.DEFENDANT;
	}

	/**
	 * Access method for the contactFirstName property.
	 * 
	 * @return   the current value of the contactFirstName property
	 */
	public String getContactFirstName()
	{
		fetch();
		return contactFirstName;
	}

	/**
	 * Access method for the contactJobTitle property.
	 * 
	 * @return   the current value of the contactJobTitle property
	 */
	public String getContactJobTitle()
	{
		fetch();
		return contactJobTitle;
	}

	/**
	 * Access method for the contactLastName property.
	 * 
	 * @return   the current value of the contactLastName property
	 */
	public String getContactLastName()
	{
		fetch();
		return contactLastName;
	}

	/**
	 * Access method for the contactMiddleName property.
	 * 
	 * @return   the current value of the contactMiddleName property
	 */
	public String getContactMiddleName()
	{
		fetch();
		return contactMiddleName;
	}

	/**
	 * Access method for the contactPhoneExt property.
	 * 
	 * @return   the current value of the contactPhoneExt property
	 */
	public String getContactPhoneExt()
	{
		fetch();
		return contactPhoneExt;
	}

	/**
	 * Access method for the contactPhoneNum property.
	 * 
	 * @return   the current value of the contactPhoneNum property
	 */
	public String getContactPhoneNum()
	{
		fetch();
		return contactPhoneNum;
	}

	public String getCriminalCaseId (){
		StringBuffer sb = new StringBuffer(this.getCourtDivisionId());
		sb.append(this.getCaseNum());
		return sb.toString();
	}

	/**
	 * Dummy method to support loading of the case list data. PreTrial cases do not
	 * support this data element.
	 * 
	 * @return   null
	 */
	public Date getDispositionDate()
	{
		return null;
	}

	/**
	 * Dummy method to support loading of the case list data. PreTrial cases do not
	 * support this data element.
	 * 
	 * @return   an empty string
	 */
	public String getDispositionTypeId()
	{
		return "";
	}

	/**
	 * Access method for the offenseDate property.
	 * 
	 * @return   the current value of the offenseDate property
	 */
	public Date getOffenseDate()
	{
		fetch();
		return offenseDate;
	}

	/**
	 * Access method for the originalCaseNum property.
	 * 
	 * @return   the current value of the originalCaseNum property
	 */
	public String getOriginalCaseNum()
	{
		fetch();
		return originalCaseNum;
	}

	/**
	 * Access method for the originalCountyId property.
	 * 
	 * @return   the current value of the originalCountyId property
	 */
	public String getOriginalCountyId()
	{
		fetch();
		return originalCountyId;
	}

	/**
	 * Access method for the originalCourtNum property.
	 * 
	 * @return   the current value of the originalCourtNum property
	 */
	public String getOriginalCourtNum()
	{
		fetch();
		return originalCourtNum;
	}

	/**
	 * Access method for the outOfCountyCaseTypeId property.
	 * 
	 * @return   the current value of the outOfCountyCaseTypeId property
	 */
	public String getOutOfCountyCaseTypeId()
	{
		return outOfCountyCaseTypeId;
	}

	/**
	 * Gets all information about the party for an OutOfCountyCase.
	 * 
	 * @param oocCase
	 */
	public void getPartyInfo(IParty party)
	{
		//Party defendant = getDefendant();

		GetPartyDataEvent getPartyEvent = new GetPartyDataEvent();
		getPartyEvent.setSpn(this.getSpn());
		if (this.getNameSeqNum() != null && !this.getNameSeqNum().equals(PDConstants.BLANK)){
			getPartyEvent.setNameSeqNum(this.getNameSeqNum());
		} else {
			getPartyEvent.setCurrentNameInd("Y");
		}
		getPartyEvent.setThinResponse(true);
		Party defendant = Party.find(getPartyEvent);
		
		if (defendant != null)
		{
			party.setPartyOid(defendant.getOID().toString());

			if (defendant.getLastName() != null && !defendant.getLastName().equals(PDConstants.BLANK)){
				party.setPartyFirstName(defendant.getFirstName());
				party.setPartyMiddleName(defendant.getMiddleName());
				party.setPartyLastName(defendant.getLastName());
			} else if (this.getDefendantName() != null && !this.getDefendantName().equals(""))
			{
				Name defName = NameUtil.getNameFromString(this.getDefendantName());
				party.setPartyLastName(defName.getLastName());
				party.setPartyFirstName(defName.getFirstName());
				party.setPartyMiddleName(defName.getMiddleName());
			}
			party.setPartyDateOfBirth(defendant.getDateOfBirth());
			party.setPartyRaceId(defendant.getRaceId());
			party.setPartySexId(defendant.getSexId());
			party.setPartySpn(defendant.getSpn());
			party.setPartySSN(defendant.getSsn());
			party.setPartySID(defendant.getSidNum());
			party.setPartyNamePtr(defendant.getNamePtr());
			party.setPartyNameSeqNum(defendant.getNameSeq());
		}
	}

	/**
	 * Access method for the spn property.
	 * 
	 * @return   the current value of the spn property
	 */
	public String getSpn()
	{
		fetch();
		return spn;
	}

	/**
	 * Access method for the stateId property.
	 * 
	 * @return the current value of the stateId property.
	 */
	public String getStateId()
	{
		return stateId;
	}

	/**
	 * Access method for the supervisionBeginDate property.
	 * 
	 * @return   the current value of the supervisionBeginDate property
	 */
	public Date getSupervisionBeginDate()
	{
		fetch();
		return supervisionBeginDate;
	}

	/**
	 * Access method for the supervisionEndDate property.
	 * 
	 * @return   the current value of the supervisionEndDate property
	 */
	public Date getSupervisionEndDate()
	{
		fetch();
		return supervisionEndDate;
	}

	/**
	 * Access method for the transferInDate property.
	 * 
	 * @return   the current value of the transferInDate property
	 */
	public Date getTransferInDate()
	{
		fetch();
		return transferInDate;
	}

	public String getUpdateT11Record() {
		return updateT11Record;
	}

	/* protected boolean isChanged(Object oldValue, Object newValue)
	{
		if ((oldValue == null && newValue != null) || (oldValue != null && !oldValue.equals(newValue)))
		{
			return true;
		}
		return false;
	}*/

	protected void postFailedValidation(String aReason)
	{
		ValidateResponseEvent vre = new ValidateResponseEvent(aReason);
		EventManager.getSharedInstance(EventManager.REPLY).postEvent(vre);
	}

	/**
	 * 
	 * @param oocCase
	 * @roseuid 4434190B0068
	 */
	public void reactivate(ISupervisionCase oocCase)
	{
		// validate first
			setTransferInDate(oocCase.getTransferInDate());
			setContactFirstName(oocCase.getContactFirstName());
			setContactMiddleName(oocCase.getContactMiddleName());
			setContactLastName(oocCase.getContactLastName());
			setContactJobTitle(oocCase.getContactJobTitle());
			setContactPhoneNum(oocCase.getContactPhoneNum());
			setContactPhoneExt(oocCase.getContactPhoneExt());
	}

	/**
	 * Sets the value of the contactFirstName property.
	 * 
	 * @param aContactFirstName the new value of the contactFirstName property
	 */
	public void setContactFirstName(String aContactFirstName)
	{
		if (this.contactFirstName == null || !this.contactFirstName.equals(aContactFirstName))
		{
			markModified();
		}
		contactFirstName = aContactFirstName;
	}

	/**
	 * Sets the value of the contactJobTitle property.
	 * 
	 * @param aContactJobTitle the new value of the contactJobTitle property
	 */
	public void setContactJobTitle(String aContactJobTitle)
	{
		if (this.contactJobTitle == null || !this.contactJobTitle.equals(aContactJobTitle))
		{
			markModified();
		}
		contactJobTitle = aContactJobTitle;
	}

	/**
	 * Sets the value of the contactLastName property.
	 * 
	 * @param aContactLastName the new value of the contactLastName property
	 */
	public void setContactLastName(String aContactLastName)
	{
		if (this.contactLastName == null || !this.contactLastName.equals(aContactLastName))
		{
			markModified();
		}
		contactLastName = aContactLastName;
	}

	/**
	 * Sets the value of the contactMiddleName property.
	 * 
	 * @param aContactMiddleName the new value of the contactMiddleName property
	 */
	public void setContactMiddleName(String aContactMiddleName)
	{
		if (this.contactMiddleName == null || !this.contactMiddleName.equals(aContactMiddleName))
		{
			markModified();
		}
		contactMiddleName = aContactMiddleName;
	}

	/**
	 * Sets the value of the contactPhoneExt property.
	 * 
	 * @param aContactPhoneExt the new value of the contactPhoneExt property
	 */
	public void setContactPhoneExt(String aContactPhoneExt)
	{
		if (this.contactPhoneExt == null || !this.contactPhoneExt.equals(aContactPhoneExt))
		{
			markModified();
		}
		contactPhoneExt = aContactPhoneExt;
	}

	/**
	 * Sets the value of the contactPhoneNum property.
	 * 
	 * @param aContactPhoneNum the new value of the contactPhoneNum property
	 */
	public void setContactPhoneNum(String aContactPhoneNum)
	{
		if (this.contactPhoneNum == null || !this.contactPhoneNum.equals(aContactPhoneNum))
		{
			markModified();
		}
		contactPhoneNum = aContactPhoneNum;
	}

	/**
	 * Sets the value of the offenseDate property.
	 * 
	 * @param anOffenseDate the new value of the offenseDate property
	 */
	public void setOffenseDate(Date anOffenseDate)
	{
		if (this.offenseDate == null || !this.offenseDate.equals(anOffenseDate))
		{
			markModified();
		}
		offenseDate = anOffenseDate;
	}

	/**
	 * Sets the value of the originalCaseNum property.
	 * 
	 * @param anOriginalCaseNum the new value of the originalCaseNum property
	 */
	public void setOriginalCaseNum(String anOriginalCaseNum)
	{
		if (this.originalCaseNum == null || !this.originalCaseNum.equals(anOriginalCaseNum))
		{
			markModified();
		}
		originalCaseNum = anOriginalCaseNum;
	}

	/**
	 * Sets the value of the originalCountyId property.
	 * 
	 * @param aCountyId the new value of the originalCountyId property
	 */
	public void setOriginalCountyId(String aCountyId)
	{
		if (this.originalCountyId == null || !this.originalCountyId.equals(aCountyId))
		{
			markModified();
		}
		originalCountyId = aCountyId;
	}
	/**
	 * Sets the value of the originalCourtNum property.
	 * 
	 * @param anOriginalCourtNum the new value of the originalCaseNum property
	 */
	public void setOriginalCourtNum(String anOriginalCourtNum)
	{
		if (this.originalCourtNum == null || !this.originalCourtNum.equals(anOriginalCourtNum))
		{
			markModified();
		}
		originalCourtNum = anOriginalCourtNum;
	}
	/**
	 * Sets the value of the outOfCountyCaseTypeId property.
	 * 
	 * @param anOutOfCountyCaseTypeId the new value of the outOfCountyCaseTypeId property
	 */
	public void setOutOfCountyCaseTypeId(String anOutOfCountyCaseTypeId)
	{
		if (this.outOfCountyCaseTypeId == null || !this.outOfCountyCaseTypeId.equals(anOutOfCountyCaseTypeId))
		{
			markModified();
		}
		outOfCountyCaseTypeId = anOutOfCountyCaseTypeId;
	}

	/**
	 * Sets the value of the spn property.
	 * 
	 * @param aSpn the new value of the spn property
	 */
	public void setSpn(String aSpn)
	{
		if (this.spn == null || !this.spn.equals(aSpn))
		{
			markModified();
		}
		spn = aSpn;
	}

	/**
	 * Set the value of the stateId property.
	 * 
	 * @param anStateId the new value of the stateId property
	 */
	public void setStateId(String anStateId) 
	{
		if (this.stateId == null || !this.stateId.equals(anStateId))
		{
			markModified();
		}
		stateId = anStateId;
	}
	
	/**
	 * Sets the value of the supervisionBeginDate property.
	 * 
	 * @param aSupervisionBeginDate the new value of the supervisionBeginDate property
	 */
	public void setSupervisionBeginDate(Date aSupervisionBeginDate)
	{
		if (this.supervisionBeginDate == null || !this.supervisionBeginDate.equals(aSupervisionBeginDate))
		{
			markModified();
		}
		supervisionBeginDate = aSupervisionBeginDate;
	}

	/**
	 * Sets the value of the supervisionEndDate property.
	 * 
	 * @param aSupervisionEndDate the new value of the supervisionEndDate property
	 */
	public void setSupervisionEndDate(Date aSupervisionEndDate)
	{
		if (this.supervisionEndDate == null || !this.supervisionEndDate.equals(aSupervisionEndDate))
		{
			markModified();
		}
		supervisionEndDate = aSupervisionEndDate;
	}
	
	/**
	 * Sets the value of the transferInDate property.
	 * 
	 * @param aTransferInDate the new value of the transferInDate property
	 */
	public void setTransferInDate(Date aTransferInDate)
	{
		if (this.transferInDate == null || !this.transferInDate.equals(aTransferInDate))
		{
			markModified();
		}
		transferInDate = aTransferInDate;
	}
	
	public void setUpdateT11Record(String updateT11Record) {
		if (this.updateT11Record == null || !this.updateT11Record.equals(updateT11Record))
		{
			markModified();
		}

		this.updateT11Record = updateT11Record;
	}

	public String getUpdateT30Record() {
		
		fetch();
		return updateT30Record;
	}

	public void setUpdateT30Record(String updateT30Record) {
		
		if (this.updateT30Record == null || !this.updateT30Record.equals(updateT30Record))
		{
			markModified();
		}
		
		this.updateT30Record = updateT30Record;
	}

	public String getNameSeqNum() {
		return nameSeqNum;
	}

	public void setNameSeqNum(String nameSeqNum) {
		if (this.nameSeqNum == null || !this.nameSeqNum.equals(nameSeqNum))
		{
			markModified();
		}

		this.nameSeqNum = nameSeqNum;
	}

	public void setNamePtr(String namePtr) {
		this.namePtr = namePtr;
	}

	public String getNamePtr() {
		return namePtr;
	}

	/**
	 * Sets all common values for creation or update of an OutOfCountyCase.
	 * 
	 * @param oocCase
	 * @roseuid 4434190B0060
	 */
	public void updateOutOfCountyCase(ISupervisionCase oocCase, boolean isUpdate)
	{
		// validate first
		// JM - Validation is now done completely in the Struts UI
//		if (validateForUpdate(oocCase))
//		{
			CSTSHelper helper = new CSTSHelper();
			if (isUpdate){
				boolean sentToState = helper.hasT11RecordBeenSentToState(oocCase.getSpn(), oocCase.getCaseNum()); 
				if (sentToState){
					//Allow change to T11 if supervision begin date date has not changed.
					if (oocCase.getSupervisionBeginDate().equals(this.getSupervisionBeginDate())){
						this.setUpdateT11Record(UIConstants.YES);
					} else {
						this.setUpdateT11Record(UIConstants.NO);
					}
				} else {
					this.setUpdateT11Record(UIConstants.YES);
				}
				if (!oocCase.getOutOfCountyCaseTypeId().equals("CSR")){
					this.createTaskToWorkgroup(this, oocCase);  //04/29/10 - Per Mary - send task whenever there's a change on an ooc case.
				}
			}
			setDefendantId(oocCase.getDefendantId());
			setDefendantName(oocCase.getDefendantName());
			setSpn(oocCase.getSpn());
			setOffenseDate(oocCase.getOffenseDate());
			setTransferInDate(oocCase.getTransferInDate());
			setOutOfCountyCaseTypeId(oocCase.getOutOfCountyCaseTypeId());
			setOriginalCaseNum(oocCase.getOriginalCaseNum());
			setOriginalCountyId(oocCase.getOriginalCountyId());
			setStateId(oocCase.getStateId());
			setOriginalCourtNum(oocCase.getOriginalCourtNum());
			setContactFirstName(oocCase.getContactFirstName());
			setContactMiddleName(oocCase.getContactMiddleName());
			setContactLastName(oocCase.getContactLastName());
			setContactJobTitle(oocCase.getContactJobTitle());
			setContactPhoneNum(oocCase.getContactPhoneNum());
			setContactPhoneExt(oocCase.getContactPhoneExt());
			setOffenseCodeId(oocCase.getStateOffenseCodeId());
			setSupervisionBeginDate(oocCase.getSupervisionBeginDate());
			setSupervisionEndDate(oocCase.getSupervisionEndDate());
			
			// Do not create a T30-2 if one already exists for the county/StateCd
			// RRY
			setUpdateT30Record( helper.isCurrentCaseForJurisdiction( oocCase ) );
//		}
	}
	private static final String CSTS_WORKGROUP_NAME = "CSTS WORKGROUP";

	private static final String SUBJECT = "CSTS Record Review Needed";
	
	private static final String SA = "SA";

	private static final String SPN = ", SPN ";
	
	private static final String MSG_TEXT1 = "OOC CASE ";
	
	private static final String MSG_TEXT2_UPDATE = " has been updated.  Please review CSTS T05, T11 and T30 records and update as needed.  The fields changed are: ";
	private static final String MSG_TEXT2_CLOSURE = " has been updated.  Please review CSTS T30 record and update as needed.  The fields changed are: ";
	
	public void createTaskToWorkgroup(OutOfCountyProbationCloseCase outOfCountyCase,
			CloseOutOfCountyCaseEvent closeCaseEvent) {
		StringBuffer message = this.getChangeMessage(outOfCountyCase, closeCaseEvent);		
		if (message.length() > 0){
			this.createTaskToWorkgroup(outOfCountyCase, message.toString(), MSG_TEXT2_CLOSURE);
		} else {
			return;
		}

	}
	private void createTaskToWorkgroup(OutOfCountyCase outOfCountyCase,
			ISupervisionCase oocCase) {
		StringBuffer message = this.getChangeMessage(outOfCountyCase, oocCase);		
		if (message.length() > 0){
			this.createTaskToWorkgroup(outOfCountyCase, message.toString(), MSG_TEXT2_UPDATE);
		} else {
			return;
		}

	}
	private void createTaskToWorkgroup(OutOfCountyCase oocCase, String message, String text2){

		WorkGroup wg = WorkGroupHelper.fetchWorkgroupByName(PDSecurityHelper.getUserAgencyId(), SA, CSTS_WORKGROUP_NAME);

	    if(wg != null){
	    	String taskOwnerId = wg.getOID();
		    CreateCSTaskEvent createTask = new CreateCSTaskEvent();
		    
		    //Set due date to 5 days from current date.
		    Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 5);
			createTask.setDueDate(cal.getTime());
			
			//createTask.setStatusId( STATUS_O );
			createTask.setTaskSubject( SUBJECT );
			
			StringBuffer padSpn = null;
			padSpn = new StringBuffer( oocCase.getSpn() );
		    if ( padSpn.length() < 8 ){
		    	while ( padSpn.length() < 8 ){
		    		padSpn.insert( 0, "0");
		    	}
		    }
		    
			StringBuffer text = new StringBuffer(MSG_TEXT1);
			text.append(oocCase.getCaseNum());
			text.append(CSTSHelper.FOR);
			text.append(oocCase.getDefendantName().trim());
			text.append(CSTSHelper.SPACE);
			text.append(SPN);
			text.append(oocCase.getSpn());
			text.append(text2);
			
			if (message.length() > 0){
				text.append(message);
			} else {
				return;
			}
			createTask.setSuperviseeName( oocCase.getDefendantName() );
			createTask.setTastText( text.toString() );
			createTask.setDefendantId( padSpn.toString() );
			createTask.setTopic( CSCAssessmentConstants.CSTASK_TOPIC_NOTIFY_CSTS_CHANGE );
			createTask.setWorkGroupId( taskOwnerId );

        
        	PDTaskHelper.createCSTask( createTask );
        
	    }
	}
	private static final String DISPOSITION = "DISPOSITION CHANGED FROM ";
	private static final String JURISDICTION = "JURISDICTION CHANGED FROM ";
	private static final String TRANSFER_IN_DATE = "TRANSFER IN DATE CHANGED FROM ";
	private static final String TRANSFER_OUT_DATE = "TRANSFER OUT DATE CHANGED FROM ";
	private static final String ORIGINATING_AGENCY_PID = "ORIGINATING AGENCY PID CHANGED FROM ";
	private static final String ORIGINATING_CASE = "ORIGINATING CASE CHANGED FROM ";
	private static final String SUPERVISION_BEGIN_DATE = "SUPERVISION BEGIN DATE CHANGED FROM ";
	private static final String CJIS_NUM = "CJIS CHANGED FROM ";
	private static final String SEMICOLON = "; ";
	private static final char NEW_LINE = '\n';
	
	private StringBuffer getChangeMessage(OutOfCountyProbationCloseCase prevCaseInfo,
			CloseOutOfCountyCaseEvent closeCaseEvent) {
		StringBuffer msg = new StringBuffer();
		
		if (prevCaseInfo.getDispositionDate() != null && !prevCaseInfo.getDispositionDate().equals(closeCaseEvent.getDispositionDate())){
			msg.append(TRANSFER_OUT_DATE);
			msg.append(DateUtil.dateToString(prevCaseInfo.getDispositionDate(), DateUtil.DATE_FMT_1));
			msg.append(CSTSHelper.TO);
			msg.append(DateUtil.dateToString(closeCaseEvent.getDispositionDate(), DateUtil.DATE_FMT_1));
		} else if (prevCaseInfo.getDispositionDate() == null){
			msg.append(TRANSFER_OUT_DATE);
			msg.append(PDConstants.BLANK);
			msg.append(CSTSHelper.TO);
			msg.append(DateUtil.dateToString(closeCaseEvent.getDispositionDate(), DateUtil.DATE_FMT_1));
		}
		if (prevCaseInfo.getDispositionTypeId() != null && !prevCaseInfo.getDispositionTypeId().equals(closeCaseEvent.getDispositionTypeId())){
			if (msg.length() > 0){
				msg.append(SEMICOLON);
				msg.append(NEW_LINE);
			}
			msg.append(DISPOSITION);
			msg.append(prevCaseInfo.getDispositionTypeId());
			msg.append(CSTSHelper.TO);
			msg.append(closeCaseEvent.getDispositionTypeId());
		} else if (prevCaseInfo.getDispositionTypeId() == null){
			if (msg.length() > 0){
				msg.append(SEMICOLON);
				msg.append(NEW_LINE);
			}
			msg.append(DISPOSITION);
			msg.append(PDConstants.BLANK);
			msg.append(CSTSHelper.TO);
			msg.append(closeCaseEvent.getDispositionTypeId());
		}

		return msg;
		
	}
	private StringBuffer getChangeMessage(OutOfCountyCase prevCaseInfo,
			ISupervisionCase newCaseInfo) {
		StringBuffer msg = new StringBuffer();
		String prevJurisdiction = null;
		if (prevCaseInfo.getOriginalCountyId() != null && !prevCaseInfo.getOriginalCountyId().equals(PDConstants.BLANK)){
			prevJurisdiction = prevCaseInfo.getOriginalCountyId();
		}
		if (prevCaseInfo.getStateId() != null && !prevCaseInfo.getStateId().equals(PDConstants.BLANK)){
			prevJurisdiction = prevCaseInfo.getStateId();
		}
		String currJurisdiction = null;
		if (newCaseInfo.getOriginalCountyId() != null && !newCaseInfo.getOriginalCountyId().equals(PDConstants.BLANK)){
			currJurisdiction = newCaseInfo.getOriginalCountyId();
		}
		if (newCaseInfo.getStateId() != null && !newCaseInfo.getStateId().equals(PDConstants.BLANK)){
			currJurisdiction = newCaseInfo.getStateId();
		}
		if (prevJurisdiction ==  null){
			prevJurisdiction = PDConstants.BLANK;
		}
		if (currJurisdiction == null){
			currJurisdiction = PDConstants.BLANK;
		}
		
		if (!prevJurisdiction.equals(currJurisdiction)){
			msg.append(JURISDICTION);
			msg.append(prevJurisdiction);
			msg.append(CSTSHelper.TO);
			msg.append(currJurisdiction);
		} 
		
		if (prevCaseInfo.getTransferInDate() != null && !prevCaseInfo.getTransferInDate().equals(newCaseInfo.getTransferInDate())){
			if (msg.length() > 0){
				msg.append(SEMICOLON);
				msg.append(NEW_LINE);
			}
			msg.append(TRANSFER_IN_DATE);
			msg.append(DateUtil.dateToString(prevCaseInfo.getTransferInDate(), DateUtil.DATE_FMT_1));
			msg.append(CSTSHelper.TO);
			msg.append(DateUtil.dateToString(newCaseInfo.getTransferInDate(), DateUtil.DATE_FMT_1));
		} else if (prevCaseInfo.getTransferInDate() == null){
			if (msg.length() > 0){
				msg.append(SEMICOLON);
				msg.append(NEW_LINE);
			}
			msg.append(TRANSFER_IN_DATE);
			msg.append(PDConstants.BLANK);
			msg.append(CSTSHelper.TO);
			msg.append(DateUtil.dateToString(newCaseInfo.getTransferInDate(), DateUtil.DATE_FMT_1));
		}
		GetOutOfCountyCaseEvent findEvent = new GetOutOfCountyCaseEvent();
		findEvent.setCaseNum(this.getCaseNum());
		findEvent.setCourtDivisionId(this.getCourtDivisionId());
		Iterator iter = new Home().findAll(findEvent, OutOfCountyProbationCase.class);
		List aList = CollectionUtil.iteratorToList(iter);
		if (aList.size() > 0){
			OutOfCountyProbationCase oocProbCase = (OutOfCountyProbationCase) aList.get(0);
			if (oocProbCase.getPersonId() == null){
				oocProbCase.setPersonId(PDConstants.BLANK);
			}
			if (!oocProbCase.getPersonId().equals(newCaseInfo.getPersonId())){
				if (msg.length() > 0){
					msg.append(SEMICOLON);
					msg.append(NEW_LINE);
				}
				msg.append(ORIGINATING_AGENCY_PID);
				msg.append(oocProbCase.getPersonId());
				msg.append(CSTSHelper.TO);
				msg.append(newCaseInfo.getPersonId().toUpperCase());
			}
			if (oocProbCase.getCJISNum() == null){
				oocProbCase.setCJISNum(PDConstants.BLANK);
			}
			if (!oocProbCase.getCJISNum().equals(newCaseInfo.getCjisNum())){
				if (msg.length() > 0){
					msg.append(SEMICOLON);
					msg.append(NEW_LINE);
				}
				msg.append(CJIS_NUM);
				msg.append(oocProbCase.getCJISNum());
				msg.append(CSTSHelper.TO);
				msg.append(newCaseInfo.getCjisNum().toUpperCase());
			}	

		}
		if (prevCaseInfo.getOriginalCaseNum() == null){
			prevCaseInfo.setOriginalCaseNum(PDConstants.BLANK);
		}
		if (!prevCaseInfo.getOriginalCaseNum().equals(newCaseInfo.getOriginalCaseNum())){
			if (msg.length() > 0){
				msg.append(SEMICOLON);
				msg.append(NEW_LINE);
			}
			msg.append(ORIGINATING_CASE);
			msg.append(prevCaseInfo.getOriginalCaseNum());
			msg.append(CSTSHelper.TO);
			msg.append(newCaseInfo.getOriginalCaseNum().toUpperCase());
		}

		if (prevCaseInfo.getSupervisionBeginDate() != null && !prevCaseInfo.getSupervisionBeginDate().equals(newCaseInfo.getSupervisionBeginDate())){
			if (msg.length() > 0){
				msg.append(SEMICOLON);
				msg.append(NEW_LINE);
			}
			msg.append(SUPERVISION_BEGIN_DATE);
			msg.append(DateUtil.dateToString(prevCaseInfo.getSupervisionBeginDate(), DateUtil.DATE_FMT_1));
			msg.append(CSTSHelper.TO);
			msg.append(DateUtil.dateToString(newCaseInfo.getSupervisionBeginDate(), DateUtil.DATE_FMT_1));
		} else if (prevCaseInfo.getSupervisionBeginDate() == null){
			if (msg.length() > 0){
				msg.append(SEMICOLON);
				msg.append(NEW_LINE);
			}
			msg.append(SUPERVISION_BEGIN_DATE);
			msg.append(PDConstants.BLANK);
			msg.append(CSTSHelper.TO);
			msg.append(DateUtil.dateToString(newCaseInfo.getSupervisionBeginDate(), DateUtil.DATE_FMT_1));
		}


		return msg;
	}

	/**
	 * 
	 * @param oocCase
	 * @return boolean - indicates whether valid or not
	 * @roseuid 4434190B005F
	 */
	public abstract boolean validateForReactivation(ISupervisionCase oocCase);
	/**
	 * 
	 * @param oocCase
	 * @return boolean - indicates whether valid or not
	 * @roseuid 4434190B005E
	 */
	public abstract boolean validateForUpdate(ISupervisionCase oocCase);
	
}
