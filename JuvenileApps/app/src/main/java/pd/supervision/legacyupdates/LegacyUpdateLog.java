package pd.supervision.legacyupdates;

import java.util.Iterator;
import java.util.List;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.CollectionUtil;
import naming.LegacyUpdateConstants;
import pd.codetable.Code;
import pd.contact.user.UserProfile;

/**
 * Date assigned to officer/cjad number
 */
public class LegacyUpdateLog extends PersistentObject
{
	private String recordData;
	private Code recordType;
	private String recordTypeId;
	private String sourceId;
	private String spn;
	private String opId;
	private String criminalCaseId;
	private Code status = null;
	private String statusId;	
	private String cstsSeqNo;
	private String procMessage;
	private String pmSCS;
	private String fasSeqNo;
	private String pmSeqNo;
	private String replaceXML;
	
	
	/**
	 * @param anEvent
	 * @return
	 */
	public static List findAll(IEvent anEvent){
	    IHome home = new Home();
	    Iterator iter = home.findAll(anEvent, LegacyUpdateLog.class);
	    return CollectionUtil.iteratorToList(iter);
	}
	
	/**
	 * @param anEvent
	 * @return
	 */
	public static List findAll(String attrName, String attrValue){
	    IHome home = new Home();
	    Iterator iter = home.findAll(attrName, attrValue, LegacyUpdateLog.class);
	    return CollectionUtil.iteratorToList(iter);
	}
	
	/**
	 * @param anEvent
	 * @return
	 */
	public String bind(){
	    new Home().bind(this);
		return this.getOID() ;
	}
	
    public String getRecordData() {
		fetch();
		return recordData;
	}
    
    /**
     * @return Returns the legacyProgramId.
     */
    public String getRecordTypeId() {
        fetch();
        return recordTypeId;
    }

	public String getSourceId() {
		fetch();
		return sourceId;
	}
    /**
     * @return Returns the spn.
     */
    public String getSpn() {
        fetch();
        return spn;
    }
    /**
     * @return Returns the status.
     */
    public Code getStatus() {
        fetch();
        return status;
    }
    /**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getStatusId()
	{
		fetch();
		return statusId;
	}
	public void setRecordData(String recordData) {
		
		if (this.recordData == null || !this.recordData.equals(recordData))
		{
			String tempVal = fixAmperChar( recordData );
			markModified();
			this.recordData = tempVal;
		} else {
			this.recordData = recordData;
		}
	}

	public String fixAmperChar( String value ){
		this.replaceXML = "";
		if (value != null){
			replaceXML = value.replaceAll( "&AMP;", "AMP");
		}
		return replaceXML;
	}
	/**
     * @param recordTypeId The recordTypeId to set.
     */
    public void setRecordTypeId(String recordTypeId) {
		if (this.recordTypeId == null || !this.recordTypeId.equals(recordTypeId))
		{
			markModified();
		}
		recordType = null;
        this.recordTypeId = recordTypeId;
    }
	public void setSourceId(String sourceId) {
		if (this.sourceId == null || !this.sourceId.equals(sourceId))
		{
			markModified();
		}
		this.sourceId = sourceId;
	}

	/**
     * @param spn The spn to set.
     */
    public void setSpn(String spn) {
		if (this.spn == null || !this.spn.equals(spn))
		{
			markModified();
		}
        this.spn = spn;
    }

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setStatusId(String statusId)
	{
		if (this.statusId == null || !this.statusId.equals(statusId))
		{
			markModified();
		}
		this.statusId = statusId;
	}

	/**
	 * @return the opId
	 */
	public String getOpId() {
		fetch();
		return opId;
	}
	/**
	 * @param opId the opId to set
	 */
	public void setOpId(String opId) {
		if (this.opId == null || !this.opId.equals(opId))
		{
			markModified();
		}
		this.opId = opId;
	}
	/**
	 * @return the criminalCaseId
	 */
	public String getCriminalCaseId() {
		fetch();
		return criminalCaseId;
	}
	/**
	 * @param criminalCaseId the criminalCaseId to set
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		if (this.criminalCaseId == null || !this.criminalCaseId.equals(criminalCaseId))
		{
			markModified();
		}
		this.criminalCaseId = criminalCaseId;
	}
	
	/**
	 * @return the cstsSeqNo
	 */
	public String getCstsSeqNo() {
		fetch();
		return cstsSeqNo;
	}

	/**
	 * @return the fasSeqNo
	 */
	public String getFasSeqNo() {
		fetch();
		return fasSeqNo;
	}

	/**
	 * @return the pmSCS
	 */
	public String getPmSCS() {
		fetch();
		return pmSCS;
	}

	/**
	 * @return the pmSeqNo
	 */
	public String getPmSeqNo() {
		fetch();
		return pmSeqNo;
	}

	/**
	 * @return the procMessage
	 */
	public String getProcMessage() {
		fetch();
		return procMessage;
	}

	/**
	 * @param cstsSeqNo the cstsSeqNo to set
	 */
	public void setCstsSeqNo(String cstsSeqNo) {
		if (this.cstsSeqNo == null || !this.cstsSeqNo.equals(cstsSeqNo))
		{
			markModified();
		}
		this.cstsSeqNo = cstsSeqNo;
	}

	/**
	 * @param fasSeqNo the fasSeqNo to set
	 */
	public void setFasSeqNo(String fasSeqNo) {
		if (this.fasSeqNo == null || !this.fasSeqNo.equals(fasSeqNo))
		{
			markModified();
		}
		this.fasSeqNo = fasSeqNo;
	}

	/**
	 * @param pmSCS the pmSCS to set
	 */
	public void setPmSCS(String pmSCS) {
		if (this.pmSCS == null || !this.pmSCS.equals(pmSCS))
		{
			markModified();
		}
		this.pmSCS = pmSCS;
	}

	/**
	 * @param pmSeqNo the pmSeqNo to set
	 */
	public void setPmSeqNo(String pmSeqNo) {
		if (this.pmSeqNo == null || !this.pmSeqNo.equals(pmSeqNo))
		{
			markModified();
		}
		this.pmSeqNo = pmSeqNo;
	}

	/**
	 * @param procMessage the procMessage to set
	 */
	public void setProcMessage(String procMessage) {
		if (this.procMessage == null || !this.procMessage.equals(procMessage))
		{
			markModified();
		}
		if (procMessage != null && procMessage.length() > LegacyUpdateConstants.MAX_MSG_LENGTH){
			procMessage.substring(0,LegacyUpdateConstants.MAX_MSG_LENGTH);
		}
		this.procMessage = procMessage;
	}
	
	public LegacyUpdateLog setLegacyUpdateLogger(String spn, String recordTypeId, String statusId, String sourceId, String caseId, String logonId, String recordData) {
		this.setSpn(spn);
		this.setRecordTypeId(recordTypeId);
		this.setSourceId(sourceId);
		this.setStatusId(statusId);
		this.setCriminalCaseId(caseId);
		UserProfile up = UserProfile.find(logonId);
		if(up != null){
		    this.setOpId(up.getOperatorId());
		}
		this.setRecordData(recordData);
		this.bind();
		return this;
	}
	
	public LegacyUpdateLog updateLegacyUpdateLogger(String recordData) {
		this.setStatusId(LegacyUpdateConstants.STATUS_PENDING_UPDATE);
		this.setRecordData(recordData);
		this.bind();
		return this;
	}
	
	public LegacyUpdateLog updateUpdateLoggerOnly(String recordData) {	
		this.setStatusId(LegacyUpdateConstants.STATUS_NEW);
		this.setRecordData(recordData);
		this.bind();
		return this;
	}
	
	public LegacyUpdateLog deleteLegacyUpdateLogger() {
		this.setStatusId(LegacyUpdateConstants.STATUS_PENDING_DELETE);
		this.bind();
		return this;
	}
	private static final String MSG1="CICS.TRANSACTION.GATEWAY";
	private static final String MSG2="NOSUCHELEMENTEXCEPTION";
	
	public LegacyUpdateLog setError(String error) {
		if (error != null && error.length() > LegacyUpdateConstants.MAX_MSG_LENGTH){
			error = error.substring(0,LegacyUpdateConstants.MAX_MSG_LENGTH);
		}
		String errorCaps = error.toUpperCase();
		if (!errorCaps.contains( MSG1 ) && !errorCaps.contains( MSG2 )){
			this.setProcMessage(error);
			this.setStatusId(LegacyUpdateConstants.STATUS_ERROR);
			this.bind();
		}
		return this;
	}
	
	public void update(){
		
	}
}
