package messaging.interviewinfo.to;

import java.util.Date;

/**
 *
 */
public class WarrantInformationTO extends EntryDateTO 
{
	private String warrantNumber = "";
	private String warrantType = "";
	private String warrantStatus = "";
	private Date serviceDate = null;
	private String serviceStatus = "";
	
	
	/**
	 * @return Returns the dateOfIssue.
	 */
	public Date getDateOfIssue() {
		return getEntryDate();
	}
	/**
	 * @param dateOfIssue The dateOfIssue to set.
	 */
	public void setDateOfIssue(Date dateOfIssue) {
		setEntryDate( dateOfIssue );
	}
	/**
	 * @return Returns the serviceDate.
	 */
	public Date getServiceDate() {
		return serviceDate;
	}
	/**
	 * @param serviceDate The serviceDate to set.
	 */
	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}
	/**
	 * @return Returns the serviceStatus.
	 */
	public String getServiceStatus() {
		return serviceStatus;
	}
	/**
	 * @param serviceStatus The serviceStatus to set.
	 */
	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	/**
	 * @return Returns the warrantNumber.
	 */
	public String getWarrantNumber() {
		return warrantNumber;
	}
	/**
	 * @param warrantNumber The warrantNumber to set.
	 */
	public void setWarrantNumber(String warrantNumber) {
		this.warrantNumber = warrantNumber;
	}
	/**
	 * @return Returns the warrantStatus.
	 */
	public String getWarrantStatus() {
		return warrantStatus;
	}
	/**
	 * @param warrantStatus The warrantStatus to set.
	 */
	public void setWarrantStatus(String warrantStatus) {
		this.warrantStatus = warrantStatus;
	}
	/**
	 * @return Returns the warrantType.
	 */
	public String getWarrantType() {
		return warrantType;
	}
	/**
	 * @param warrantType The warrantType to set.
	 */
	public void setWarrantType(String warrantType) {
		this.warrantType = warrantType;
	}
}
