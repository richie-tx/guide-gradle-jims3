/*
 * Created on Mar 29, 2006
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
public class GetSupervisionOrderConditionValuesEvent extends RequestEvent
{
	private String agencyId;
	private String orderId;
	private String conditionId;
	private String orderStatus;
	private String defendantId;
	private String criminalCaseId;
	private String variableElementTypeId;
	private String courtId;
	private String value;

	/**
	 * @return
	 */
	public String getConditionId()
	{
		return conditionId;
	}

	/**
	 * @return
	 */
	public String getCourtId()
	{
		return courtId;
	}

	/**
	 * @return
	 */
	public String getCriminalCaseId()
	{
		return criminalCaseId;
	}

	/**
	 * @return
	 */
	public String getDefendantId()
	{
		return defendantId;
	}

	/**
	 * @return
	 */
	public String getOrderId()
	{
		return orderId;
	}

	/**
	 * @return
	 */
	public String getOrderStatus()
	{
		return orderStatus;
	}

	/**
	 * @return
	 */
	public String getVariableElementTypeId()
	{
		return variableElementTypeId;
	}

	/**
	 * @param string
	 */
	public void setConditionId(String string)
	{
		conditionId = string;
	}

	/**
	 * @param string
	 */
	public void setCourtId(String string)
	{
		courtId = string;
	}

	/**
	 * @param string
	 */
	public void setCriminalCaseId(String string)
	{
		criminalCaseId = string;
	}

	/**
	 * @param string
	 */
	public void setDefendantId(String string)
	{
		defendantId = string;
	}

	/**
	 * @param string
	 */
	public void setOrderId(String string)
	{
		orderId = string;
	}

	/**
	 * @param string
	 */
	public void setOrderStatus(String string)
	{
		orderStatus = string;
	}

	/**
	 * @param string
	 */
	public void setVariableElementTypeId(String string)
	{
		variableElementTypeId = string;
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
	public String getValue()
	{
		return value;
	}

	/**
	 * @param string
	 */
	public void setValue(String string)
	{
		value = string;
	}

}
