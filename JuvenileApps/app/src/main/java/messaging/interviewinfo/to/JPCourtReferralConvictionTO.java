package messaging.interviewinfo.to;

import java.util.Date;


/**
 *
 */
public class JPCourtReferralConvictionTO extends ExcludedTO
{
	private String court = "";
	private String m204JuvNumber = "";
	private String offenseDescription = "";
	private Date fileDate;
	private Date dateOfConviction;
	
	
	/**
	 * @return Returns the m204JuvNumber.
	 */
	public String getM204JuvNumber() {
		return m204JuvNumber;
	}
	/**
	 * @param juvNumber The m204JuvNumber to set.
	 */
	public void setM204JuvNumber(String juvNumber) {
		m204JuvNumber = juvNumber;
	}
	/**
	 * @return Returns the court.
	 */
	public String getCourt() {
		return court;
	}
	/**
	 * @param court The court to set.
	 */
	public void setCourt(String court) {
		this.court = court;
	}
	/**
	 * @return Returns the dateOfConviction.
	 */
	public Date getDateOfConviction() {
		return dateOfConviction;
	}
	/**
	 * @param dateOfConviction The dateOfConviction to set.
	 */
	public void setDateOfConviction(Date dateOfConviction) {
		this.dateOfConviction = dateOfConviction;
	}
	/**
	 * @return Returns the fileDate.
	 */
	public Date getFileDate() {
		return fileDate;
	}
	/**
	 * @param fileDate The fileDate to set.
	 */
	public void setFileDate(Date fileDate) {
		this.fileDate = fileDate;
	}
	/**
	 * @return Returns the offenseDescription.
	 */
	public String getOffenseDescription() {
		return offenseDescription;
	}
	/**
	 * @param offenseDescription The offenseDescription to set.
	 */
	public void setOffenseDescription(String offenseDescription) {
		this.offenseDescription = offenseDescription;
	}
}
