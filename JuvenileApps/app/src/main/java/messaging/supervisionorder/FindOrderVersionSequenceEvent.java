/*
 * Created on Jan 12, 2006
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
public class FindOrderVersionSequenceEvent extends RequestEvent 
{
	private String caseNum;
	private String versionType;
	private int orderChainNum;
	
	/**
	 * @return
	 */
	public String getCaseNum()
	{
		return caseNum;
	}

	/**
	 * @return
	 */
	public String getVersionType()
	{
		return versionType;
	}

	/**
	 * @param string
	 */
	public void setCaseNum(String string)
	{
		caseNum = string;
	}

	/**
	 * @param string
	 */
	public void setVersionType(String string)
	{
		versionType = string;
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
}
