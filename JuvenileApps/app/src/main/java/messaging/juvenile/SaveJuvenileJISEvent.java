package messaging.juvenile;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveJuvenileJISEvent extends RequestEvent {
	
	private String juvenileNum;
	
	private Date entryDate;
	 
	 private String agency;
	 
	 private String otherAgency;
	 
	 private String comments;



	
	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return the agency
	 */
	public String getAgency() {
		return agency;
	}

	/**
	 * @param agency the agency to set
	 */
	public void setAgency(String agency) {
		this.agency = agency;
	}

	/**
	 * @return the otherAgency
	 */
	public String getOtherAgency() {
		return otherAgency;
	}

	/**
	 * @param otherAgency the otherAgency to set
	 */
	public void setOtherAgency(String otherAgency) {
		this.otherAgency = otherAgency;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getJuvenileNum() {
		return juvenileNum;
	}

	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	
	
	
	
}
