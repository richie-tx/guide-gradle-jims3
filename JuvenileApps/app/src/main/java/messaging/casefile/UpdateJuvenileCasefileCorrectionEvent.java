/*
 * Created on Nov 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.casefile;

import mojo.km.messaging.RequestEvent;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateJuvenileCasefileCorrectionEvent extends RequestEvent {
	private String supervisionNumber;
	private boolean changeCasefileStatus=false;
	private String casfileStatusId;
	private boolean changeCasefileType=false;
	private String prevSupTypeId;
	private String supTypeId;
	/**
	 * @return Returns the casfileStatusId.
	 */
	public String getCasfileStatusId() {
		return casfileStatusId;
	}
	/**
	 * @param casfileStatusId The casfileStatusId to set.
	 */
	public void setCasfileStatusId(String casfileStatusId) {
		this.casfileStatusId = casfileStatusId;
	}
	/**
	 * @return Returns the changeCasefileStatus.
	 */
	public boolean isChangeCasefileStatus() {
		return changeCasefileStatus;
	}
	/**
	 * @param changeCasefileStatus The changeCasefileStatus to set.
	 */
	public void setChangeCasefileStatus(boolean changeCasefileStatus) {
		this.changeCasefileStatus = changeCasefileStatus;
	}
	/**
	 * @return Returns the changeCasefileType.
	 */
	public boolean isChangeCasefileType() {
		return changeCasefileType;
	}
	/**
	 * @param changeCasefileType The changeCasefileType to set.
	 */
	public void setChangeCasefileType(boolean changeCasefileType) {
		this.changeCasefileType = changeCasefileType;
	}
	/**
	 * @return Returns the prevSupTypeId.
	 */
	public String getPrevSupTypeId() {
		return prevSupTypeId;
	}
	/**
	 * @param prevSupTypeId The prevSupTypeId to set.
	 */
	public void setPrevSupTypeId(String prevSupTypeId) {
		this.prevSupTypeId = prevSupTypeId;
	}
	/**
	 * @return Returns the supervisionNumber.
	 */
	public String getSupervisionNumber() {
		return supervisionNumber;
	}
	/**
	 * @param supervisionNumber The supervisionNumber to set.
	 */
	public void setSupervisionNumber(String supervisionNumber) {
		this.supervisionNumber = supervisionNumber;
	}
	/**
	 * @return Returns the supTypeId.
	 */
	public String getSupTypeId() {
		return supTypeId;
	}
	/**
	 * @param supTypeId The supTypeId to set.
	 */
	public void setSupTypeId(String supTypeId) {
		this.supTypeId = supTypeId;
	}
}// END CLASS
