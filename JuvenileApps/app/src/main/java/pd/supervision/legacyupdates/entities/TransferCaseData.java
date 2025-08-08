package pd.supervision.legacyupdates.entities;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class TransferCaseData extends PersistentObject
{
	private String spn;
	private String recordType;
	private String action;
	private String seqNumber;
	private String criminalCaseId;
	private Date transferDate;
	private String transferringCntySprvnCode;
	private String receivingCntySprvnCode;
	private String transferTypeCode;
	private String oriCntyPersonId;
	private String supervisingCntyPersonId;
	private String jurisdictionCodeChanged;
	private String supervisionRejected;
	private String filler;
	private String cdi;
	private String cas;
	private String opId;
	
	/**
	 * @return the spn
	 */
	public String getSpn() {
		fetch();
		return spn;
	}
	/**
	 * @param spn the spn to set
	 */
	public void setSpn(String spn) {
		if (this.spn == null || !this.spn.equals(spn)) {
			markModified();
		}
		this.spn = spn;
	}
	/**
	 * @return the recordType
	 */
	public String getRecordType() {
		fetch();
		return recordType;
	}
	/**
	 * @param recordType the recordType to set
	 */
	public void setRecordType(String recordType) {
		if (this.recordType == null || !this.recordType.equals(recordType)) {
			markModified();
		}
		this.recordType = recordType;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		fetch();
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		if (this.action == null || !this.action.equals(action)) {
			markModified();
		}
		this.action = action;
	}
	/**
	 * @return the seqNumber
	 */
	public String getSeqNumber() {
		fetch();
		return seqNumber;
	}
	/**
	 * @param seqNumber the seqNumber to set
	 */
	public void setSeqNumber(String seqNumber) {
		if (this.seqNumber == null || !this.seqNumber.equals(seqNumber)) {
			markModified();
		}
		this.seqNumber = seqNumber;
	}
	/**
	 * @return the transferDate
	 */
	public Date getTransferDate() {
		fetch();
		return transferDate;
	}
	/**
	 * @param transferDate the transferDate to set
	 */
	public void setTransferDate(Date transferDate) {
		if (this.transferDate == null || !this.transferDate.equals(transferDate)) {
			markModified();
		}
		this.transferDate = transferDate;
	}
	/**
	 * @return the filler
	 */
	public String getFiller() {
		fetch();
		return filler;
	}
	/**
	 * @param filler the filler to set
	 */
	public void setFiller(String filler) {
		if (this.filler == null || !this.filler.equals(filler)) {
			markModified();
		}
		this.filler = filler;
	}
	/**
	 * @return the cdi
	 */
	public String getCdi() {
		fetch();
		return cdi;
	}
	/**
	 * @param cdi the cdi to set
	 */
	public void setCdi(String cdi) {
		if (this.cdi == null || !this.cdi.equals(cdi)) {
			markModified();
		}
		this.cdi = cdi;
	}
	/**
	 * @return the cas
	 */
	public String getCas() {
		fetch();
		return cas;
	}
	/**
	 * @param cas the cas to set
	 */
	public void setCas(String cas) {
		if (this.cas == null || !this.cas.equals(cas)) {
			markModified();
		}
		this.cas = cas;
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
		if (this.opId == null || !this.opId.equals(opId)) {
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
		if (this.criminalCaseId == null || !this.criminalCaseId.equals(criminalCaseId)) {
			markModified();
		}
		this.criminalCaseId = criminalCaseId;
	}
	/**
	 * @return the transferTypeCode
	 */
	public String getTransferTypeCode() {
		fetch();
		return transferTypeCode;
	}
	/**
	 * @param transferTypeCode the transferTypeCode to set
	 */
	public void setTransferTypeCode(String transferTypeCode) {
		if (this.transferTypeCode == null || !this.transferTypeCode.equals(transferTypeCode)) {
			markModified();
		}
		this.transferTypeCode = transferTypeCode;
	}
	/**
	 * @return the transferringCntySprvnCode
	 */
	public String getTransferringCntySprvnCode() {
		fetch();
		return transferringCntySprvnCode;
	}
	/**
	 * @param transferringCntySprvnCode the transferringCntySprvnCode to set
	 */
	public void setTransferringCntySprvnCode(String transferringCntySprvnCode) {
		if (this.transferringCntySprvnCode == null || !this.transferringCntySprvnCode.equals(transferringCntySprvnCode)) {
			markModified();
		}
		this.transferringCntySprvnCode = transferringCntySprvnCode;
	}
	/**
	 * @return the receivingCntySprvnCode
	 */
	public String getReceivingCntySprvnCode() {
		fetch();
		return receivingCntySprvnCode;
	}
	/**
	 * @param receivingCntySprvnCode the receivingCntySprvnCode to set
	 */
	public void setReceivingCntySprvnCode(String receivingCntySprvnCode) {
		if (this.receivingCntySprvnCode == null || !this.receivingCntySprvnCode.equals(receivingCntySprvnCode)) {
			markModified();
		}
		this.receivingCntySprvnCode = receivingCntySprvnCode;
	}
	/**
	 * @return the oriCntyPersonId
	 */
	public String getOriCntyPersonId() {
		fetch();
		return oriCntyPersonId;
	}
	/**
	 * @param oriCntyPersonId the oriCntyPersonId to set
	 */
	public void setOriCntyPersonId(String oriCntyPersonId) {
		if (this.oriCntyPersonId == null || !this.oriCntyPersonId.equals(oriCntyPersonId)) {
			markModified();
		}
		this.oriCntyPersonId = oriCntyPersonId;
	}
	/**
	 * @return the supervisingCntyPersonId
	 */
	public String getSupervisingCntyPersonId() {
		fetch();
		return supervisingCntyPersonId;
	}
	/**
	 * @param supervisingCntyPersonId the supervisingCntyPersonId to set
	 */
	public void setSupervisingCntyPersonId(String supervisingCntyPersonId) {
		if (this.supervisingCntyPersonId == null || !this.supervisingCntyPersonId.equals(supervisingCntyPersonId)) {
			markModified();
		}
		this.supervisingCntyPersonId = supervisingCntyPersonId;
	}
	/**
	 * @return the jurisdictionCodeChanged
	 */
	public String getJurisdictionCodeChanged() {
		fetch();
		return jurisdictionCodeChanged;
	}
	/**
	 * @param jurisdictionCodeChanged the jurisdictionCodeChanged to set
	 */
	public void setJurisdictionCodeChanged(String jurisdictionCodeChanged) {
		if (this.jurisdictionCodeChanged == null || !this.jurisdictionCodeChanged.equals(jurisdictionCodeChanged)) {
			markModified();
		}
		this.jurisdictionCodeChanged = jurisdictionCodeChanged;
	}
	/**
	 * @param supervisionRejected the supervisionRejected to set
	 */
	public void setSupervisionRejected(String supervisionRejected) {
		if (this.supervisionRejected == null || !this.supervisionRejected.equals(supervisionRejected)) {
			markModified();
		}
		this.supervisionRejected = supervisionRejected;
	}
	/**
	 * @return the supervisionRejected
	 */
	public String getSupervisionRejected() {
		fetch();
		return supervisionRejected;
	}
	
	
	public String bind() 
	{
		IHome home = new Home();
		home.bind(this);
		return this.getSeqNumber();
	}

	public static Iterator findAll(IEvent event) 
	{
		return new Home().findAll(event, TransferCaseData.class);
	}
}
