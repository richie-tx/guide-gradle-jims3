package messaging.administersupervisee.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class ProgramTrackerResponseEvent extends ResponseEvent {
	private Date entryDate;
	private Date programTrackerEffectiveDate;
	private Date programTrackerEndDate;
	private String programTrackerId;
	private String superviseeHistoryId;
	private String userName;
	private String programTrackerDesc;
	public Date getEntryDate() {
		return entryDate;
	}
	public Date getProgramTrackerEffectiveDate() {
		return programTrackerEffectiveDate;
	}
	public Date getProgramTrackerEndDate() {
		return programTrackerEndDate;
	}
	public String getProgramTrackerId() {
		return programTrackerId;
	}
	public String getSuperviseeHistoryId() {
		return superviseeHistoryId;
	}
	public String getUserName() {
		return userName;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public void setProgramTrackerEffectiveDate(Date programTrackerEffectiveDate) {
		this.programTrackerEffectiveDate = programTrackerEffectiveDate;
	}
	public void setProgramTrackerEndDate(Date programTrackerEndDate) {
		this.programTrackerEndDate = programTrackerEndDate;
	}
	public void setProgramTrackerId(String programTrackerId) {
		this.programTrackerId = programTrackerId;
	}
	public void setSuperviseeHistoryId(String superviseeHistoryId) {
		this.superviseeHistoryId = superviseeHistoryId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setProgramTrackerDesc(String programTrackerDesc) {
		this.programTrackerDesc = programTrackerDesc;
	}
	public String getProgramTrackerDesc() {
		return programTrackerDesc;
	}
}
