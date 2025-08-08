package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author jmcnabb
 */
public class GetPeriodForSupervisionOrderEvent extends RequestEvent {

	private String supervisionOrderId;
	
	/**
	 * @return Returns the supervisionOrderId.
	 */
	public String getSupervisionOrderId()
	{
		return supervisionOrderId;
	}
	
	/**
	 * @param aSupervisionOrderId The supervisionOrderId to set.
	 */
	public void setSupervisionOrderId(String aSupervisionOrderId)
	{
		this.supervisionOrderId = aSupervisionOrderId;
	}
}
