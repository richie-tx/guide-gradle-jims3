//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\UpdateNCResponseEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateNCReasonForTransferEvent extends RequestEvent 
{
	private String reasonForTransferCodeId;
	private String reasonForTransferId;
	private String reasonForTransferCodeDesc;
	
	
	/**
	 * @return Returns the reasonForTransferId.
	 */
	public String getReasonForTransferId() {
		return reasonForTransferId;
	}
	/**
	 * @param reasonForTransferId The reasonForTransferId to set.
	 */
	public void setReasonForTransferId(String reasonForTransferId) {
		this.reasonForTransferId = reasonForTransferId;
	}
	/**
	 * @return Returns the reasonForTransferCodeId.
	 */
	public String getReasonForTransferCodeId() {
		return reasonForTransferCodeId;
	}
	/**
	 * @param reasonForTransferCodeId The reasonForTransferCodeId to set.
	 */
	public void setReasonForTransferCodeId(String reasonForTransferCodeId) {
		this.reasonForTransferCodeId = reasonForTransferCodeId;
	}
	/**
	 * @return the reasonForTransferCodeDesc
	 */
	public String getReasonForTransferCodeDesc() {
		return reasonForTransferCodeDesc;
	}
	/**
	 * @param reasonForTransferCodeDesc the reasonForTransferCodeDesc to set
	 */
	public void setReasonForTransferCodeDesc(String reasonForTransferCodeDesc) {
		this.reasonForTransferCodeDesc = reasonForTransferCodeDesc;
	}
}
