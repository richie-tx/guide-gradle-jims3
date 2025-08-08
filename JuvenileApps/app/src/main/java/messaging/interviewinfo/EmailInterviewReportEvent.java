package messaging.interviewinfo;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.RequestEvent;


public class EmailInterviewReportEvent extends RequestEvent implements IAddressable
{
	private String reportId;
	private String toAddress;
	private String subject;
	
	/**
	* 
	*/
	public EmailInterviewReportEvent() 
	{
    
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
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return Returns the toAddress.
	 */
	public String getToAddress() {
		return toAddress;
	}
	/**
	 * @param toAddress The toAddress to set.
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
}
