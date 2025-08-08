package messaging.interviewinfo.reply;

import java.util.Date;

import mojo.km.messaging.reporting.ReportResponseEvent;

/**
 * @author bschwartz
 *
 */
public class InterviewReportResponseEvent extends ReportResponseEvent
{
	private String reportId;
	private String casefileId;
	private Date creationDate;
	private String reportType;
	

	/**
	 * @return
	 */
	public String getCasefileId()
	{
		return casefileId;
	}

	/**
	 * @param string
	 */
	public void setCasefileId(String string)
	{
		casefileId = string;
	}


	/**
	 * @return Returns the creationDate.
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate The creationDate to set.
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return Returns the reportId.
	 */
	public String getReportId() {
		return reportId;
	}
	/**
	 * @param reportId The reportId to set.
	 */
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	/**
	 * @return Returns the reportType.
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType The reportType to set.
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
}
