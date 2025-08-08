/*
 * Created on May 11, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetSupervisionOrderVersionsEvent extends RequestEvent
{
	private String orderId;
	private String caseNum;
	private String agencyId;
	private String statusId;
	private int orderChainNum;
	private boolean allOrderChains=false;

	/**
	 * @return
	 */
	public String getOrderId()
	{
		return orderId;
	}

	/**
	 * @param string
	 */
	public void setOrderId(String string)
	{
		orderId = string;
	}

	/**
	 * @return
	 */
	public String getCaseNum()
	{
		return caseNum;
	}

	/**
	 * @param string
	 */
	public void setCaseNum(String string)
	{
		caseNum = string;
	}

	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}

	/**
	 * @return
	 */
	public String getStatusId()
	{
		return statusId;
	}

	/**
	 * @param string
	 */
	public void setStatusId(String string)
	{
		statusId = string;
	}

	/**
	 * @return Returns the orderChainNum.
	 */
	public int getOrderChainNum() {
		return orderChainNum;
	}
	/**
	 * @param orderChainNum The orderChainNum to set.
	 */
	public void setOrderChainNum(int orderChainNum) {
		this.orderChainNum = orderChainNum;
	}
	/**
	 * @return Returns the allOrderChains.
	 */
	public boolean isAllOrderChains() {
		return allOrderChains;
	}
	/**
	 * @param allOrderChains The allOrderChains to set.
	 */
	public void setAllOrderChains(boolean allOrderChains) {
		this.allOrderChains = allOrderChains;
	}
}
