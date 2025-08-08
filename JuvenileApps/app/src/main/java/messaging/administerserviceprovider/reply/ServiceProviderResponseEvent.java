/*
 * Created on Jun 20, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.administerserviceprovider.reply;

import java.util.Collection;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author C_NAggarwal
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ServiceProviderResponseEvent extends ResponseEvent implements Comparable
{
	private String juvServProviderId;
	private String juvServProviderName;
	private boolean inHouse;
	private String statusId;
	private Date statusChangeDate;
	
	private Collection serviceResponseEvents;

	/**
	 * @return
	 */
	public String getJuvServProviderId()
	{
		return juvServProviderId;
	}

	/**
	 * @param string
	 */
	public void setJuvServProviderId(String string)
	{
		juvServProviderId = string;
	}

	/**
	 * @return
	 */
	public String getJuvServProviderName()
	{
		return juvServProviderName;
	}

	/**
	 * @param string
	 */
	public void setJuvServProviderName(String string)
	{
		juvServProviderName = string;
	}

	/**
	 * @return
	 */
	public Collection getServiceResponseEvents()
	{
		return serviceResponseEvents;
	}

	/**
	 * @param collection
	 */
	public void setServiceResponseEvents(Collection collection)
	{
		serviceResponseEvents = collection;
	}

	/**
	 * @return Returns the inHouse.
	 */
	public boolean isInHouse() {
		return inHouse;
	}
	/**
	 * @param inHouse The inHouse to set.
	 */
	public void setInHouse(boolean inHouse) {
		this.inHouse = inHouse;
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
	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	
	public int compareTo(Object obj)
	{
		if(obj==null)
			return -1;
		ServiceProviderResponseEvent evt = (ServiceProviderResponseEvent) obj;
		int myVal=juvServProviderName.compareTo(evt.getJuvServProviderName());	
		if(myVal>0){
			myVal= 1;
		}
		else if (myVal<0){
			myVal= -1;
		}
		else{
			myVal=0;
		}
		return myVal;
	}	
	
}
