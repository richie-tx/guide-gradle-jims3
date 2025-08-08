/**
 * 
 */
package messaging.administersupervisee;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author dwilliamson
 *
 */
public class UpdateSuperviseeDetailsEvent extends RequestEvent {
	
	private boolean add;
	
	private boolean update;
	
	private boolean delete;
	
	private Date dnaCollectedDate;
	
	private boolean dnaFlagInd;
	
	private String spn;
	
	private String superviseeHistoryId; //OID - unique ID
	
	private String userID;
	        
    
	/**
	 * @return the add
	 */
	public boolean isAdd() {
		return add;
	}

	/**
	 * @param add the add to set
	 */
	public void setAdd(boolean add) {
		this.add = add;
	}
	
	/**
	 * @return the correct
	 */
	public boolean isUpdate() {
		return update;
	}

	/**
	 * @param correct the correct to set
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isDelete() {
		return delete;
	}

	/**
	 * 
	 * @param delete
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	/**
	 * @return the dnaCollectedDate
	 */
	public Date getDnaCollectedDate() {
		return dnaCollectedDate;
	}

	/**
	 * @param dnaCollectedDate the dnaCollectedDate to set
	 */
	public void setDnaCollectedDate(Date dnaCollectedDate) {
		this.dnaCollectedDate = dnaCollectedDate;
	}

	/**
	 * @return the dnaFlagInd
	 */
	public boolean isDnaFlagInd() {
		return dnaFlagInd;
	}

	/**
	 * @param dnaFlagInd the dnaFlagInd to set
	 */
	public void setDnaFlagInd(boolean dnaFlagInd) {
		this.dnaFlagInd = dnaFlagInd;
	}
		
	/**
	 * @return the spn
	 */
	public String getSpn() {
		return spn;
	}

	/**
	 * @param spn the spn to set
	 */
	public void setSpn(String spn) {
		this.spn = spn;
	}

	/**
	 * @return the superviseeHistoryId
	 */
	public String getSuperviseeHistoryId() {
		return superviseeHistoryId;
	}

	/**
	 * @param superviseeHistoryId the superviseeHistoryId to set
	 */
	public void setSuperviseeHistoryId(String superviseeHistoryId) {
		this.superviseeHistoryId = superviseeHistoryId;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	

	
}
