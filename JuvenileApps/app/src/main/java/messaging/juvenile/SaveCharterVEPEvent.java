package messaging.juvenile;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveCharterVEPEvent extends RequestEvent
{
	private Date startDate;
	private boolean completed;
	private Date completionDate;
	private String juvenileNum;
	private String programCodeId;
	private String juvenileCharterVEPId;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public Date getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}
	public String getJuvenileNum() {
		return juvenileNum;
	}
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	public String getProgramCodeId() {
		return programCodeId;
	}
	public void setProgramCodeId(String programCodeId) {
		this.programCodeId = programCodeId;
	}
	public String getJuvenileCharterVEPId() {
		return juvenileCharterVEPId;
	}
	public void setJuvenileCharterVEPId(String juvenileCharterVEPId) {
		this.juvenileCharterVEPId = juvenileCharterVEPId;
	}

}
