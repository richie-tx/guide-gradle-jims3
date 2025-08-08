package messaging.legacyupdates;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class LegacyUpdateEvent extends RequestEvent {
	private Date transactionDate;
	private String legacyRecType;
	private String spn;
	private String jims2SourceId;
	public String getJims2SourceId() {
		return jims2SourceId;
	}
	public void setJims2SourceId(String jims2SourceId) {
		this.jims2SourceId = jims2SourceId;
	}
	public String getLegacyRecType() {
		return legacyRecType;
	}
	public void setLegacyRecType(String legacyRecType) {
		this.legacyRecType = legacyRecType;
	}
	public String getSpn() {
		return spn;
	}
	public void setSpn(String spn) {
		this.spn = spn;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

}
