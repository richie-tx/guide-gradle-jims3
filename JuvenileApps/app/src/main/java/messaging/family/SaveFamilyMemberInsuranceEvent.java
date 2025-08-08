/*
 * Created on Oct 13, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import mojo.km.messaging.RequestEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveFamilyMemberInsuranceEvent extends RequestEvent
{
	private String typeId;
	private String policyNum;
	private String carrier;
	
	

	/**
	 * @return
	 */
	public String getCarrier()
	{
		return carrier;
	}

	/**
	 * @return
	 */
	public String getPolicyNum()
	{
		return policyNum;
	}

	/**
	 * @return
	 */
	public String getTypeId()
	{
		return typeId;
	}

	/**
	 * @param string
	 */
	public void setCarrier(String string)
	{
		carrier = string;
	}

	/**
	 * @param string
	 */
	public void setPolicyNum(String string)
	{
		policyNum = string;
	}

	/**
	 * @param string
	 */
	public void setTypeId(String string)
	{
		typeId = string;
	}

}
