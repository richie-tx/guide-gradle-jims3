/*
 * Created on Mar 25, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administercasenotes;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetConditionCasenotesForCaseOrderChainEvent extends RequestEvent{
    private String caseId;
    private int orderChainNumber;
    private int conditionId;
    
	/**
	 * @return Returns the conditionId.
	 */
	public int getConditionId() {
		return conditionId;
	}
	/**
	 * @param conditionId The conditionId to set.
	 */
	public void setConditionId(int conditionId) {
		this.conditionId = conditionId;
	}
	/**
	 * @return Returns the caseId.
	 */
	public String getCaseId() {
		return caseId;
	}
	/**
	 * @param caseId The caseId to set.
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	/**
	 * @return Returns the orderChainNumber.
	 */
	public int getOrderChainNumber() {
		return orderChainNumber;
	}
	/**
	 * @param orderChainNumber The orderChainNumber to set.
	 */
	public void setOrderChainNumber(int orderChainNumber) {
		this.orderChainNumber = orderChainNumber;
	}
}
