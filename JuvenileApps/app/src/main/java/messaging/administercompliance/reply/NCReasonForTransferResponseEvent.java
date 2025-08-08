/*
 * Created on Dec 03, 2007
 */
package messaging.administercompliance.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class NCReasonForTransferResponseEvent extends ResponseEvent 
{
    private String reasonForTransferCodeId;
    private String reasonForTransferCodeDesc;
    private String ncResponseId;
    private String reasonForTransferId;
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
	 * @return Returns the ncResponseId.
	 */
	public String getNcResponseId() {
		return ncResponseId;
	}
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
	/**
	 * @return Returns the reasonForTransferCodeDesc.
	 */
	public String getReasonForTransferCodeDesc() {
		return reasonForTransferCodeDesc;
	}
	/**
	 * @param reasonForTransferCodeDesc The reasonForTransferCodeDesc to set.
	 */
	public void setReasonForTransferCodeDesc(String reasonForTransferCodeDesc) {
		this.reasonForTransferCodeDesc = reasonForTransferCodeDesc;
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
 }
