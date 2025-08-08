/*
 * Created on Feb 23, 2006
 *
  */
package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetSupervisionOrderDetailsEvent extends RequestEvent
{
	private String supervisionOrderId;
	private boolean legacyDataRefreshable;
	private boolean isDeleteAction;
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
     * @return Returns the legacyDataRefreshable.
     */
    public boolean isLegacyDataRefreshable() {
        return legacyDataRefreshable;
    }
    /**
     * @param legacyDataRefreshable The legacyDataRefreshable to set.
     */
    public void setLegacyDataRefreshable(boolean legacyDataRefreshable) {
        this.legacyDataRefreshable = legacyDataRefreshable;
    }

	public boolean isDeleteAction() {
		return isDeleteAction;
	}

	public void setDeleteAction(boolean isDeleteAction) {
		this.isDeleteAction = isDeleteAction;
	}
}
