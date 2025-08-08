//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\supervisionorder\\ActivateSupervisionOrderEvent.java

package messaging.supervisionorder;


import mojo.km.messaging.RequestEvent;

/**
 * @author ryoung
 *
 */
public class UpdateOrderStatusToPendingEvent extends RequestEvent
{
	private String supervisionOrderId;
	private String killTaskId;
	/**
	 * @roseuid 43B2E402007D
	 */
	public UpdateOrderStatusToPendingEvent()
	{

	}
	/**
	 * @return
	 */
	public String getSupervisionOrderId()
	{
		return supervisionOrderId;
	}

	/**
	 * @param aSupervisionOrderId
	 */
	public void setSupervisionOrderId(String aSupervisionOrderId)
	{
		supervisionOrderId = aSupervisionOrderId;
	}

	/**
	 * @return Returns the killTaskId.
	 */
	public String getKillTaskId() {
		return killTaskId;
	}
	/**
	 * @param killTaskId The killTaskId to set.
	 */
	public void setKillTaskId(String killTaskId) {
		this.killTaskId = killTaskId;
	}
}
