package messaging.csts;

import mojo.km.messaging.RequestEvent;

public class GetCSTSStatusEvent extends RequestEvent {
	private String caseNum;
	private String recordType;
	private String seqNum;
	private String spn;

	public String getCaseNum() {
		return caseNum;
	}
	public String getRecordType() {
		return recordType;
	}
	public String getSeqNum() {
		return seqNum;
	}
	public String getSpn() {
		return spn;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}
	public void setSpn(String spn) {
		this.spn = spn;
	}
}
