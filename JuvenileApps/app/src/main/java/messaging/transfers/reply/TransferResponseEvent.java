package messaging.transfers.reply;

import mojo.km.messaging.ResponseEvent;

public class TransferResponseEvent extends ResponseEvent {
	private boolean caseHasActiveSupervisionOrder;
	
	private String caseNum; 
	//caseNum & cdi make up criminal case id
	private String cdi;
	private String classificationSeqNumForTransferIn;
	private String classificationSeqNumForTransferOut;
	private String courtNum;
	private String hcTransferInDate;
	private String hcTransferOutDate;
	private String otherCountyCaseNumber;
	private String otherCountyStatePersonIdNumber;
	private String outOfCountyTransferInDate;
	private String outOfCountyTransferOutDate;
	private String receivingCountyState;
	private String receivingCountyStateName;
	private boolean rejected;
	private String rejectedAsStr;
	private String subclassificationSeqNumForTransferIn;
	private String subclassificationSeqNumForTransferOut;
	private String supervisionOrderId;
	private String transferringCountyState;
	private String transferringCountyStateName;
	private String outOfCountyClosureReason;
	
	/**
	 * @return the caseHasActiveSupervisionOrder
	 */
	public boolean isCaseHasActiveSupervisionOrder() {
		return caseHasActiveSupervisionOrder;
	}
	public boolean caseHasActiveSupervisionOrder() {
		return caseHasActiveSupervisionOrder;
	}
	public String getCaseNum() {
		return caseNum;
	}
	
	public String getCaseSelectedKey(){
		StringBuffer sb = new StringBuffer(caseNum);
		if (classificationSeqNumForTransferIn != null && !classificationSeqNumForTransferIn.equals("")){
			sb.append(classificationSeqNumForTransferIn);
		} else {
			sb.append("000");
		}
		if (subclassificationSeqNumForTransferIn != null && !subclassificationSeqNumForTransferIn.equals("")){
			sb.append(subclassificationSeqNumForTransferIn);
		} else {
			sb.append("00");
		}
		if (classificationSeqNumForTransferOut != null && !classificationSeqNumForTransferOut.equals("")){
			sb.append(classificationSeqNumForTransferOut);
		} else {
			sb.append("000");
		}
		if (subclassificationSeqNumForTransferOut != null && !subclassificationSeqNumForTransferOut.equals("")){
			sb.append(subclassificationSeqNumForTransferOut);
		} else {
			sb.append("00");
		}
		return sb.toString();
	}
	
	/**
	 * @return cdi
	 */
	public String getCdi() {
		return cdi;
	}
	public String getClassificationSeqNumForTransferIn() {
		return classificationSeqNumForTransferIn;
	}
	public String getClassificationSeqNumForTransferOut() {
		return classificationSeqNumForTransferOut;
	}
	/**
	 * @return courtNum
	 */
	public String getCourtNum() {
		return courtNum;
	}
	public String getCriminalCaseId(){
		StringBuffer sb = new StringBuffer(this.cdi);
		sb.append(this.caseNum);
		return sb.toString();
	}
	/**
	 * @return hcTransferInDate
	 */
	public String getHcTransferInDate() {
		return hcTransferInDate;
	}
	/**
	 * @return hcTransferOutDate
	 */
	public String getHcTransferOutDate() {
		return hcTransferOutDate;
	}
	
	public String getOtherCountyCaseNumber() {
		return otherCountyCaseNumber;
	}
	
	/**
	 * @return otherCountyState
	 */
	public String getOtherCountyStatePersonIdNumber() {
		return otherCountyStatePersonIdNumber;
	}
	/**
	 * @return outOfCountyTransferInDate
	 */
	public String getOutOfCountyTransferInDate() {
		return outOfCountyTransferInDate;
	}

	/**
	 * @return outOfCountyTransferOutDate
	 */
	public String getOutOfCountyTransferOutDate() {
		return outOfCountyTransferOutDate;
	}
	public String getReceivingCountyState() {
		return receivingCountyState;
	}
	public String getReceivingCountyStateName() {
		return receivingCountyStateName;
	}

	public String getRejectedAsStr() {
		return rejectedAsStr;
	}

	public String getSubclassificationSeqNumForTransferIn() {
		return subclassificationSeqNumForTransferIn;
	}
	public String getSubclassificationSeqNumForTransferOut() {
		return subclassificationSeqNumForTransferOut;
	}

	public String getSupervisionOrderId() {
		return supervisionOrderId;
	}

	public String getTransferringCountyState() {
		return transferringCountyState;
	}

	public String getTransferringCountyStateName() {
		return transferringCountyStateName;
	}

	/**
	 * @return the rejected
	 */
	public boolean isRejected() {
		return rejected;
	}

	public void setCaseHasActiveSupervisionOrder(
			boolean caseHasActiveSupervisionOrder) {
		this.caseHasActiveSupervisionOrder = caseHasActiveSupervisionOrder;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	/**
	 * @param cdi
	 */
	public void setCdi(String cdi) {
		this.cdi = cdi;
	}

	public void setClassificationSeqNumForTransferIn(
			String classificationSeqNumForTransferIn) {
		this.classificationSeqNumForTransferIn = classificationSeqNumForTransferIn;
	}


	public void setClassificationSeqNumForTransferOut(
			String classificationSeqNumForTransferOut) {
		this.classificationSeqNumForTransferOut = classificationSeqNumForTransferOut;
	}

	/**
	 * @param courtNum
	 */
	public void setCourtNum(String courtNum) {
		this.courtNum = courtNum;
	}

	/**
	 * @param hcTransferInDate
	 */
	public void setHcTransferInDate(String hcTransferInDate) {
		this.hcTransferInDate = hcTransferInDate;
	}

	/**
	 * @param hcTransferOutDate
	 */
	public void setHcTransferOutDate(String hcTransferOutDate) {
		this.hcTransferOutDate = hcTransferOutDate;
	}

	public void setOtherCountyCaseNumber(String otherCountyCaseNumber) {
		this.otherCountyCaseNumber = otherCountyCaseNumber;
	}

	/**
	 * @param otherCountyState
	 */
	public void setOtherCountyStatePersonIdNumber(String otherCountyStatePersonIdNumber) {
		this.otherCountyStatePersonIdNumber = otherCountyStatePersonIdNumber;
	}

	/**
	 * @param outOfCountyTransferInDate
	 */
	public void setOutOfCountyTransferInDate(String outOfCountyTransferInDate) {
		this.outOfCountyTransferInDate = outOfCountyTransferInDate;
	}

	/**
	 * @param outOfCountyTransferOutDate
	 */
	public void setOutOfCountyTransferOutDate(String outOfCountyTransferOutDate) {
		this.outOfCountyTransferOutDate = outOfCountyTransferOutDate;
	}

	public void setReceivingCountyState(String receivingCountyState) {
		this.receivingCountyState = receivingCountyState;
	}

	public void setReceivingCountyStateName(String receivingCountyStateName) {
		this.receivingCountyStateName = receivingCountyStateName;
	}

	/**
	 * @param rejected the rejected to set
	 */
	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}

	public void setRejectedAsStr(String rejectedAsStr) {
		this.rejectedAsStr = rejectedAsStr;
	}

	public void setSubclassificationSeqNumForTransferIn(
			String subclassificationSeqNumForTransferIn) {
		this.subclassificationSeqNumForTransferIn = subclassificationSeqNumForTransferIn;
	}

	public void setSubclassificationSeqNumForTransferOut(
			String subclassificationSeqNumForTransferOut) {
		this.subclassificationSeqNumForTransferOut = subclassificationSeqNumForTransferOut;
	}

	public void setSupervisionOrderId(String supervisionOrderId) {
		this.supervisionOrderId = supervisionOrderId;
	}

	public void setTransferringCountyState(String transferringCountyState) {
		this.transferringCountyState = transferringCountyState;
	}
	public void setTransferringCountyStateName(String transferringCountyStateName) {
		this.transferringCountyStateName = transferringCountyStateName;
	}
	public void setOutOfCountyClosureReason(String outOfCountyClosureReason) {
		this.outOfCountyClosureReason = outOfCountyClosureReason;
	}
	public String getOutOfCountyClosureReason() {
		return outOfCountyClosureReason;
	}

}
