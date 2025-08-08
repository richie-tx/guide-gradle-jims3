package messaging.juvenile;

import java.util.Collection;
import java.util.Date;
import mojo.km.messaging.RequestEvent;

public class SaveCharterPostReleaseEvent extends RequestEvent
{
	private Date statusDate;
	private Collection continuingEducationCodes;
	private String juvenileNum;
	private String comments;
	private String employedCodeId;
	public Date getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	public Collection getContinuingEducationCodes() {
		return continuingEducationCodes;
	}
	public void setContinuingEducationCodes(Collection continuingEducationCodes) {
		this.continuingEducationCodes = continuingEducationCodes;
	}
	public String getJuvenileNum() {
		return juvenileNum;
	}
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getEmployedCodeId() {
		return employedCodeId;
	}
	public void setEmployedCodeId(String employedCodeId) {
		this.employedCodeId = employedCodeId;
	}
}
