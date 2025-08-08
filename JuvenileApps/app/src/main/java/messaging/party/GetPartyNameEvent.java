package messaging.party;

import mojo.km.messaging.RequestEvent;

public class GetPartyNameEvent extends RequestEvent {
	private String spn;
	private String nameSeqNum;
	public String getSpn() {
		return spn;
	}
	public void setSpn(String spn) {
		this.spn = spn;
	}
	public String getNameSeqNum() {
		return nameSeqNum;
	}
	public void setNameSeqNum(String nameSeqNum) {
		this.nameSeqNum = nameSeqNum;
	}
}
