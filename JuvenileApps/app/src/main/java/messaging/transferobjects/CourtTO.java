/*
 * Created on Aug 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.transferobjects;


/**
 * @author cc_mdsouza
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CourtTO
extends PersistentObjectTO
{
	
	private String courtNumber;
	private String courtCategory;
	private String judgeFirstName;
	private String judgeLastName;
	private String description;
	private String address;
	
	/**
	 * @return Returns the courtCategory.
	 */
	public String getCourtCategory() {
		return courtCategory;
	}
	/**
	 * @param courtCategory The courtCategory to set.
	 */
	public void setCourtCategory(String courtCategory) {
		this.courtCategory = courtCategory;
	}
	/**
	 * @return Returns the courtNumber.
	 */
	public String getCourtNumber() {
		return courtNumber;
	}
	/**
	 * @param courtNumber The courtNumber to set.
	 */
	public void setCourtNumber(String courtNumber) {
		this.courtNumber = courtNumber;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the judgeFirstName.
	 */
	public String getJudgeFirstName() {
		return judgeFirstName;
	}
	/**
	 * @param judgeFirstName The judgeFirstName to set.
	 */
	public void setJudgeFirstName(String judgeFirstName) {
		this.judgeFirstName = judgeFirstName;
	}
	/**
	 * @return Returns the judgeLastName.
	 */
	public String getJudgeLastName() {
		return judgeLastName;
	}
	/**
	 * @param judgeLastName The judgeLastName to set.
	 */
	public void setJudgeLastName(String judgeLastName) {
		this.judgeLastName = judgeLastName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}
