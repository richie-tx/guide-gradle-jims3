/*
 * Created on Feb 13, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.supervisionorder;

import java.util.Collection;
import java.util.Map;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetConditionDetailsForSupervisionOrderEvent extends RequestEvent
{
	private Collection conditions;
	private String courtId;
	private Map referenceVariableMap;
	private String orderId;
	/**
	 * @return
	 */
	public Collection getConditions()
	{
		return conditions;
	}

	/**
	 * @return
	 */
	public String getCourtId()
	{
		return courtId;
	}

	/**
	 * @param collection
	 */
	public void setConditions(Collection collection)
	{
		conditions = collection;
	}

	/**
	 * @param string
	 */
	public void setCourtId(String string)
	{
		courtId = string;
	}

	/**
	 * @return
	 */
	public Map getReferenceVariableMap()
	{
		return referenceVariableMap;
	}

	/**
	 * @param map
	 */
	public void setReferenceVariableMap(Map map)
	{
		referenceVariableMap = map;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
