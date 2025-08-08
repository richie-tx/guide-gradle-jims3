package messaging.juvenile.reply;

import java.util.Collection;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class CharterPostReleaseResponseEvent extends ResponseEvent {
	private String charterPostRelId;
	private Date statusDate;
	private Collection continuingEducationCodes;
	private String continuingEducationCodesStr;
	private String juvenileNum;
	private String comments;
	private String employedCodeId;
	private String employedCodeDesc;
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
	public String getEmployedCodeDesc() {
		return employedCodeDesc;
	}
	public void setEmployedCodeDesc(String employedCodeDesc) {
		this.employedCodeDesc = employedCodeDesc;
	}
	public String getContinuingEducationCodesStr() {
		return continuingEducationCodesStr;
	}
	public void setContinuingEducationCodesStr(String continuingEducationCodesStr) {
		this.continuingEducationCodesStr = continuingEducationCodesStr;
	}
	public String getCharterPostRelId() {
		return charterPostRelId;
	}
	public void setCharterPostRelId(String charterPostRelId) {
		this.charterPostRelId = charterPostRelId;
	}
}
