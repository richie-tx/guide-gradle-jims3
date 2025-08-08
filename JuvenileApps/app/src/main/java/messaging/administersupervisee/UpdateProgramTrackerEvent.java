package messaging.administersupervisee;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateProgramTrackerEvent extends RequestEvent {
	private boolean add;
	private boolean correct;
	private boolean delete;
	private Date effectiveDate;
	private Date endDate;
	private String programTrackerId;
	private boolean remove;
	private String spn;
	private String superviseeHistoryId;
	
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public String getProgramTrackerId() {
		return programTrackerId;
	}
	public String getSpn() {
		return spn;
	}
	public String getSuperviseeHistoryId() {
		return superviseeHistoryId;
	}
	public boolean isAdd() {
		return add;
	}
	public boolean isCorrect() {
		return correct;
	}
	public boolean isDelete() {
		return delete;
	}
	public boolean isRemove() {
		return remove;
	}
	public void setAdd(boolean add) {
		this.add = add;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setProgramTrackerId(String programTrackerId) {
		this.programTrackerId = programTrackerId;
	}
	public void setRemove(boolean remove) {
		this.remove = remove;
	}
	public void setSpn(String spn) {
		this.spn = spn;
	}
	public void setSuperviseeHistoryId(String superviseeHistoryId) {
		this.superviseeHistoryId = superviseeHistoryId;
	}
}
