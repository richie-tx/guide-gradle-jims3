//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercasenotes\\UpdateCasenoteEvent.java

package messaging.administercasenotes;

import mojo.km.messaging.RequestEvent;

public class CreateOrderConditionCasenoteEvent extends RequestEvent
{
    private String casenoteDate;
    private String casenoteTime;
    private boolean isAm;
    private String contactMethodId;
    private String[] casenoteSubjects;
    private String collateralId;
    private String casenoteDetails;
	/**
	 * @return Returns the casenoteDate.
	 */
	public String getCasenoteDate() {
		return casenoteDate;
	}
	/**
	 * @param casenoteDate The casenoteDate to set.
	 */
	public void setCasenoteDate(String casenoteDate) {
		this.casenoteDate = casenoteDate;
	}
	/**
	 * @return Returns the casenoteDetails.
	 */
	public String getCasenoteDetails() {
		return casenoteDetails;
	}
	/**
	 * @param casenoteDetails The casenoteDetails to set.
	 */
	public void setCasenoteDetails(String casenoteDetails) {
		this.casenoteDetails = casenoteDetails;
	}
	/**
	 * @return Returns the casenoteSubjects.
	 */
	public String[] getCasenoteSubjects() {
		return casenoteSubjects;
	}
	/**
	 * @param casenoteSubjects The casenoteSubjects to set.
	 */
	public void setCasenoteSubjects(String[] casenoteSubjects) {
		this.casenoteSubjects = casenoteSubjects;
	}
	/**
	 * @return Returns the casenoteTime.
	 */
	public String getCasenoteTime() {
		return casenoteTime;
	}
	/**
	 * @param casenoteTime The casenoteTime to set.
	 */
	public void setCasenoteTime(String casenoteTime) {
		this.casenoteTime = casenoteTime;
	}
	/**
	 * @return Returns the collateralId.
	 */
	public String getCollateralId() {
		return collateralId;
	}
	/**
	 * @param collateralId The collateralId to set.
	 */
	public void setCollateralId(String collateralId) {
		this.collateralId = collateralId;
	}
	/**
	 * @return Returns the contactMethodId.
	 */
	public String getContactMethodId() {
		return contactMethodId;
	}
	/**
	 * @param contactMethodId The contactMethodId to set.
	 */
	public void setContactMethodId(String contactMethodId) {
		this.contactMethodId = contactMethodId;
	}
	/**
	 * @return Returns the isAm.
	 */
	public boolean isAm() {
		return isAm;
	}
	/**
	 * @param isAm The isAm to set.
	 */
	public void setAm(boolean isAm) {
		this.isAm = isAm;
	}
}
