/*
 * Created on Jun 30, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.administerserviceprovider;
import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author C_NAggarwal
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UpdateServiceProviderStatusRequestEvent extends RequestEvent
{
	private String serviceProviderId;
	private String statusId;
	private Date statusChangeDate;

	/**
	 * @return
	 */
	public String getServiceProviderId()
	{
		return serviceProviderId;
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
	public void setServiceProviderId(String string)
	{
		serviceProviderId = string;
	}

	/**
	 * @param string
	 */
	public void setStatusId(String string)
	{
		statusId = string;
	}

	/**
	 * @return Returns the statusChangeDate.
	 */
	public Date getStatusChangeDate() {
		return statusChangeDate;
	}
	/**
	 * @param statusChangeDate The statusChangeDate to set.
	 */
	public void setStatusChangeDate(Date statusChangeDate) {
		this.statusChangeDate = statusChangeDate;
	}
}
