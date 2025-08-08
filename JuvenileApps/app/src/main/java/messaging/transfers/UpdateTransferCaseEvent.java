package messaging.transfers;

import messaging.transfers.to.TransferCaseBean;
import mojo.km.messaging.RequestEvent;

public class UpdateTransferCaseEvent extends RequestEvent {
	private TransferCaseBean transferCaseBean = new TransferCaseBean();

	boolean updatingHistoricalInfo;

	/**
	 * @return the transferCaseBean
	 */
	public TransferCaseBean getTransferCaseBean() {
		return transferCaseBean;
	}
	public boolean isUpdatingHistoricalInfo() {
		return updatingHistoricalInfo;
	}

	/**
	 * @param transferCaseBean the transferCaseBean to set
	 */
	public void setTransferCaseBean(TransferCaseBean transferCaseBean) {
		this.transferCaseBean = transferCaseBean;
	}

	public void setUpdatingHistoricalInfo(boolean updatingHistoricalInfo) {
		this.updatingHistoricalInfo = updatingHistoricalInfo;
	}
	
	
}
