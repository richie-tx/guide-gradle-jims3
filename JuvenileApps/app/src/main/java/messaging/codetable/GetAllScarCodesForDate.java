package messaging.codetable;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class GetAllScarCodesForDate extends RequestEvent {
	private Date entryDate;
	private String parentId;
	private String OID;
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getOID() {
		return OID;
	}
	public void setOID(String oid) {
		OID = oid;
	}	
}
