package ui.supervision.administercaseload.helper;

import java.util.List;

public class CloseCaseResultBean
{
	private String defendantId;
	private String caseNum;
	private String result;
	private List reasonList;
	
	
	/**
	 * @return the caseNum
	 */
	public String getCaseNum() {
		return caseNum;
	}
	/**
	 * @param caseNum the caseNum to set
	 */
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	/**
	 * @return the reasonList
	 */
	public List getReasonList() {
		return reasonList;
	}
	/**
	 * @param reasonList the reasonList to set
	 */
	public void setReasonList(List reasonList) {
		this.reasonList = reasonList;
	}
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return the defendantId
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId the defendantId to set
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	
	
	
}
