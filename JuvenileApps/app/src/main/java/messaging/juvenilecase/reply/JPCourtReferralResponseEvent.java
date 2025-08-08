package messaging.juvenilecase.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 *
 */
public class JPCourtReferralResponseEvent extends ResponseEvent
{
	public String m204JuvNumber;
	public String offenseId;
	public String offenseDescription;
	public String courtName;
	public Date convictionDate;
	public Date fileDate;
	public String caseNumber; 
	public String disposition;

	
	/**
	 * @return Returns the disposition.
	 */
	public String getDisposition() {
		return disposition;
	}
	/**
	 * @param disposition The disposition to set.
	 */
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
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
	 * @return Returns the convictionDate.
	 */
	public Date getConvictionDate() {
		return convictionDate;
	}
	/**
	 * @param convictionDate The convictionDate to set.
	 */
	public void setConvictionDate(Date convictionDate) {
		this.convictionDate = convictionDate;
	}
	/**
	 * @return Returns the courtName.
	 */
	public String getCourtName() {
		return courtName;
	}
	/**
	 * @param courtName The courtName to set.
	 */
	public void setCourtName(String courtName) {
		this.courtName = courtName;
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
	/**
	 * @return Returns the offenseId.
	 */
	public String getOffenseId() {
		return offenseId;
	}
	/**
	 * @param offenseId The offenseId to set.
	 */
	public void setOffenseId(String offenseId) {
		this.offenseId = offenseId;
	}
	/**
	 * @return Returns the caseNumber.
	 */
	public String getCaseNumber() {
		return caseNumber;
	}
	/**
	 * @param caseNumber The caseNumber to set.
	 */
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
}
