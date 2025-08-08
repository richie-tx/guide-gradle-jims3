package ui.supervision.administercaseload.form;

import org.apache.struts.action.ActionForm;

public class TransferForm extends ActionForm {
	private String transferInDate;
	private String texasCountyId;
	private String stateId;
	private String defendantId;
	private String transferPersonId;
	private String cdi;
	private String caseNum;
	public String getCaseNum() {
		return caseNum;
	}
	public String getCdi() {
		return cdi;
	}
	public String getDefendantId() {
		return defendantId;
	}
	public String getStateId() {
		return stateId;
	}
	public String getTexasCountyId() {
		return texasCountyId;
	}
	public String getTransferInDate() {
		return transferInDate;
	}
	public String getTransferPersonId() {
		return transferPersonId;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	public void setCdi(String cdi) {
		this.cdi = cdi;
	}
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public void setTexasCountyId(String texasCountyId) {
		this.texasCountyId = texasCountyId;
	}
	public void setTransferInDate(String transferInDate) {
		this.transferInDate = transferInDate;
	}
	public void setTransferPersonId(String transferPersonId) {
		this.transferPersonId = transferPersonId;
	}
}
