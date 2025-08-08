package ui.supervision.cscdcalendar;

import java.util.Date;

import mojo.km.utilities.DateUtil;

public class CSEventsReportBean
{
	private String csEventId = "";
	
	private String defendantId = "";
	
	private String defendantName = "";
	
	private String outcome = "";
	
	private String resultUserName = "";
	
	private String resultPositionName = "";
	
	private String csEventTypeId = "";
	
	private String csEventTypeDesc = "";
	
	private Date csEventDate = null;
	
	private String csEventDateStr = "";
	
	private String resultUserNPositionName = "";
	
	
	
	/**
	 * @return the csEventId
	 */
	public String getCsEventId() {
		return csEventId;
	}

	/**
	 * @param csEventId the csEventId to set
	 */
	public void setCsEventId(String csEventId) {
		this.csEventId = csEventId;
	}

	/**
	 * @return the csEventDateStr
	 */
	public String getCsEventDateStr() {
		return csEventDateStr;
	}

	/**
	 * @param csEventDateStr the csEventDateStr to set
	 */
	public void setCsEventDateStr(String csEventDateStr) {
		this.csEventDateStr = csEventDateStr;
	}

	/**
	 * @return the csEventTypeDesc
	 */
	public String getCsEventTypeDesc() {
		return csEventTypeDesc;
	}

	/**
	 * @param csEventTypeDesc the csEventTypeDesc to set
	 */
	public void setCsEventTypeDesc(String csEventTypeDesc) {
		this.csEventTypeDesc = csEventTypeDesc;
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

	/**
	 * @return the defendantName
	 */
	public String getDefendantName() {
		return defendantName;
	}

	/**
	 * @param defendantName the defendantName to set
	 */
	public void setDefendantName(String defendantName) {
		this.defendantName = defendantName;
	}

	/**
	 * @return the outcome
	 */
	public String getOutcome() {
		return outcome;
	}

	/**
	 * @param outcome the outcome to set
	 */
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	/**
	 * @return the resultPositionName
	 */
	public String getResultPositionName() {
		return resultPositionName;
	}

	/**
	 * @param resultPositionName the resultPositionName to set
	 */
	public void setResultPositionName(String resultPositionName) {
		this.resultPositionName = resultPositionName;
	}

	/**
	 * @return the resultUserName
	 */
	public String getResultUserName() {
		return resultUserName;
	}

	/**
	 * @param resultUserName the resultUserName to set
	 */
	public void setResultUserName(String resultUserName) {
		this.resultUserName = resultUserName;
	}

	/**
	 * @return the resultUserNPositionName
	 */
	public String getResultUserNPositionName() {
		return resultUserNPositionName;
	}

	/**
	 * @param resultUserNPositionName the resultUserNPositionName to set
	 */
	public void setResultUserNPositionName(String resultUserNPositionName) {
		this.resultUserNPositionName = resultUserNPositionName;
	}

	/**
	 * @return the csEventDate
	 */
	public Date getCsEventDate() {
		return csEventDate;
	}

	/**
	 * @param csEventDate the csEventDate to set
	 */
	public void setCsEventDate(Date csEventDate)
	{
		this.csEventDateStr = "";
		if(csEventDate != null)
		{
			this.csEventDateStr = DateUtil.dateToString(csEventDate, DateUtil.DATE_FMT_1);
		}
		this.csEventDate = csEventDate;
	}

	/**
	 * @return the csEventTypeId
	 */
	public String getCsEventTypeId() {
		return csEventTypeId;
	}

	/**
	 * @param csEventTypeId the csEventTypeId to set
	 */
	public void setCsEventTypeId(String csEventTypeId) {
		this.csEventTypeId = csEventTypeId;
	}
	
}
