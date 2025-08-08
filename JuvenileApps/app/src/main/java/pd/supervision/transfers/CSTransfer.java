//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\transfers\\CSTransfer.java
package pd.supervision.transfers;

import java.util.Iterator;

import naming.PDConstants;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class CSTransfer extends PersistentObject {
	public String getCriminalCaseId(){
		StringBuffer sb = new StringBuffer(this.getCdi());
		sb.append(this.getCaseNum());
		return sb.toString();
	}
	static public CSTransfer find(String oid) {
		IHome home = new Home();
		return (CSTransfer) home.find(oid, CSTransfer.class);
	}
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, CSTransfer.class);
	}
	private String caseNum;
	private String cdi;
	/**
	 * Legacy database record sequence number for harris-county transfer-in type record
	 */
	private String subclassificationSeqNumForTransferIn;
	/**
	 * Legacy database record sequence number for harris-county transfer-out type record
	 */
	private String subclassificationSeqNumForTransferOut;
	/**
	 * Legacy database record sequence number for out-of-county transfer-in type record
	 */
	private String classificationSeqNumForTransferIn;
	/**
	 * Legacy database record sequence number for out-of-county transfer-out type record
	 */
	private String classificationSeqNumForTransferOut;
	private String courtNum;
	/**
	 * Transfer-in date for Harris county cases
	 */
	private String hcTransferInDate;
	/**
	 * Transfer-out date for Harris county cases
	 */
	private String hcTransferOutDate;
	private String otherCountyCaseNumber;
	/**
	 * ???
	 */
	private String otherCountyStatePersonIdNumber;
	/**
	 * Transfer-in date for out of county cases
	 */
	private String outOfCountyTransferInDate;
	/**
	 * Transfer-out date for out of county cases
	 */
	private String outOfCountyTransferOutDate;
	/**
	 * Receiving county or state code
	 */
	private String receivingCountyState;
	
	/**
	 * Case rejection flag
	 */
	private String rejected;
	/**
	 * Transferring county or state code
	 */
	private String transferringCountyState;
	/**
	 * @roseuid 484E860D0214
	 */
	public CSTransfer() {

	} 
	public String getCaseNum() {
		fetch();
		return caseNum;
	}
	public String getCdi() {
		fetch();
		return cdi;
	}

	
	/**
	 * @return the subclassificationSeqNumForTransferIn
	 */
	public String getSubclassificationSeqNumForTransferIn() {
		fetch();
		return subclassificationSeqNumForTransferIn;
	}
	
	/**
	 * @return the cicsRecordSeqNumberForHcTransferOut
	 */
	public String getSubclassificationSeqNumForTransferOut() {
		fetch();
		return subclassificationSeqNumForTransferOut;
	}
	/**
	 * @return the cicsRecordSeqNumberForOocTransferIn
	 */
	public String getClassificationSeqNumForTransferIn() {
		fetch();
		return classificationSeqNumForTransferIn;
	}
	/**
	 * @return the classificationSeqNumForTransferOut
	 */
	public String getClassificationSeqNumForTransferOut() {
		fetch();
		return classificationSeqNumForTransferOut;
	}
	public String getCourtNum() {
		fetch();
		return courtNum;
	}
		
	public String getHcTransferInDate() {
		fetch();
		return hcTransferInDate;
	}

	public String getHcTransferOutDate() {
		fetch();
		return hcTransferOutDate;
	}

	public String getOID(){
		StringBuffer sb = new StringBuffer(this.getCdi());
		sb.append(this.getCaseNum());
		if (!this.getSubclassificationSeqNumForTransferOut().trim().equals(PDConstants.BLANK)){
			sb.append(this.getSubclassificationSeqNumForTransferOut().trim());
		} else if (!this.getSubclassificationSeqNumForTransferIn().trim().equals(PDConstants.BLANK)){
			sb.append(this.getSubclassificationSeqNumForTransferIn().trim());
		} 
		return sb.toString();
	}

	public String getOtherCountyCaseNumber() {
		return otherCountyCaseNumber;
	}

	public String getOtherCountyStatePersonIdNumber() {
		fetch();
		return otherCountyStatePersonIdNumber;
	}

	public String getOutOfCountyTransferInDate() {
		fetch();
		return outOfCountyTransferInDate;
	}
	
	public String getOutOfCountyTransferOutDate() {
		fetch();
		return outOfCountyTransferOutDate;
	}	

	public String getReceivingCountyState() {
		fetch();
		return receivingCountyState;
	}
	/**
	 * @return the rejected
	 */
	public String getRejected() {
		fetch();
		return rejected;
	}
	public String getTransferringCountyState() {
		fetch();
		return transferringCountyState;
	}
	public void setCaseNum(String caseNum) {
		if (this.caseNum == null || !this.caseNum.equals(caseNum)) {
			markModified();
		}
		this.caseNum = caseNum;
	}
	public void setCdi(String cdi) {
		if (this.cdi == null || !this.cdi.equals(cdi)) {
			markModified();
		}
		this.cdi = cdi;
	}
	/**
	 * @param subclassificationSeqNumForTransferIn the subclassificationSeqNumForTransferIn to set
	 */
	public void setSubclassificationSeqNumForTransferIn(
			String subclassificationSeqNumForTransferIn) {
		if (this.subclassificationSeqNumForTransferIn == null || !this.subclassificationSeqNumForTransferIn.equals(subclassificationSeqNumForTransferIn)) {
			markModified();
		}
		this.subclassificationSeqNumForTransferIn = subclassificationSeqNumForTransferIn;
	}

	/**
	 * @param cicsRecordSeqNumberForHcTransferOut the cicsRecordSeqNumberForHcTransferOut to set
	 */
	public void setSubclassificationSeqNumForTransferOut(
			String subclassificationSeqNumForTransferOut) {
		if (this.subclassificationSeqNumForTransferOut == null || !this.subclassificationSeqNumForTransferOut.equals(subclassificationSeqNumForTransferOut)) {
			markModified();
		}
		this.subclassificationSeqNumForTransferOut = subclassificationSeqNumForTransferOut;
	}

	/**
	 * @param cicsRecordSeqNumberForOocTransferIn the cicsRecordSeqNumberForOocTransferIn to set
	 */
	public void setClassificationSeqNumForTransferIn(
			String classificationSeqNumForTransferIn) {
		if (this.classificationSeqNumForTransferIn == null || !this.classificationSeqNumForTransferIn.equals(classificationSeqNumForTransferIn)) {
			markModified();
		}
		this.classificationSeqNumForTransferIn = classificationSeqNumForTransferIn;
	}

	/**
	 * @param cicsRecordSeqNumberForOocTransferOut the cicsRecordSeqNumberForOocTransferOut to set
	 */
	public void setClassificationSeqNumForTransferOut(
			String classificationSeqNumForTransferOut) {
		if (this.classificationSeqNumForTransferOut == null || !this.classificationSeqNumForTransferOut.equals(classificationSeqNumForTransferOut)) {
			markModified();
		}
		this.classificationSeqNumForTransferOut = classificationSeqNumForTransferOut;
	}

	public void setCourtNum(String courtNum) {
		if (this.courtNum == null || !this.courtNum.equals(courtNum)) {
			markModified();
		}
		this.courtNum = courtNum;
	}
	public void setHcTransferInDate(String transferInDate) {
		if (this.hcTransferInDate == null || !this.hcTransferInDate.equals(transferInDate)) {
			markModified();
		}
		this.hcTransferInDate = transferInDate;
	}

	public void setHcTransferOutDate(String transferOutDate) {
		if (this.hcTransferOutDate == null || !this.hcTransferOutDate.equals(transferOutDate)) {
			markModified();
		}
		this.hcTransferOutDate = transferOutDate;
	}

	public void setOtherCountyCaseNumber(String otherCountyCaseNumber) {
		this.otherCountyCaseNumber = otherCountyCaseNumber;
	}

	public void setOtherCountyStatePersonIdNumber(String otherCountyStatePersonIdNumber) {
		if (this.otherCountyStatePersonIdNumber == null || !this.otherCountyStatePersonIdNumber.equals(otherCountyStatePersonIdNumber)) {
			markModified();
		}
		this.otherCountyStatePersonIdNumber = otherCountyStatePersonIdNumber;
	}

	public void setOutOfCountyTransferInDate(String outOfCountyTransferInDate) {
		if (this.outOfCountyTransferInDate == null || !this.outOfCountyTransferInDate.equals(outOfCountyTransferInDate)) {
			markModified();
		}
		this.outOfCountyTransferInDate = outOfCountyTransferInDate;
	}
	public void setOutOfCountyTransferOutDate(String outOfCountyTransferOutDate) {
		if (this.outOfCountyTransferOutDate == null || !this.outOfCountyTransferOutDate.equals(outOfCountyTransferOutDate)) {
			markModified();
		}
		this.outOfCountyTransferOutDate = outOfCountyTransferOutDate;
	}
	public void setReceivingCountyState(String receivingCountyState) {
		this.receivingCountyState = receivingCountyState;
	}
	/**
	 * @param rejected the rejected to set
	 */
	public void setRejected(String rejected) {
		if (this.rejected == null || !this.rejected.equals(rejected)) {
			markModified();
		}
		this.rejected = rejected;
	}
	public void setTransferringCountyState(String transferringCountyState) {
		this.transferringCountyState = transferringCountyState;
	}
		
}
