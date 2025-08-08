/*
 * Created on Mar 29, 2006
 *
 */
package messaging.supervisionorder;

import java.util.Collection;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetSupervisionOrderVariableElementReferencesEvent extends RequestEvent
{
	private String criminalCaseId;
	private String orderId;
	private boolean refreshable;
	private Collection variableElementNames;

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
	public String getOrderId()
	{
		return orderId;
	}

	/**
	 * @return Returns the variableElementNames.
	 */
	public Collection getVariableElementNames() {
		return variableElementNames;
	}
    /**
     * @return Returns the refreshable.
     */
    public boolean isRefreshable() {
        return refreshable;
    }

	/**
	 * @param aCriminalCaseId
	 */
	public void setCriminalCaseId(String aCriminalCaseId)
	{
		criminalCaseId = aCriminalCaseId;
	}

	/**
	 * @param anOrderId
	 */
	public void setOrderId(String anOrderId)
	{
		orderId = anOrderId;
	}
    /**
     * @param refreshable The refreshable to set.
     */
    public void setRefreshable(boolean refreshable) {
        this.refreshable = refreshable;
    }
	/**
	 * @param variableElementNames The variableElementNames to set.
	 */
	public void setVariableElementNames(Collection variableElementNames) {
		this.variableElementNames = variableElementNames;
	}
}
