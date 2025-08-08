/*
 * Created on Jul 10, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.codetable.criminal.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author C_NAggarwal
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ServiceTypeCdResponseEvent extends ResponseEvent implements Comparable
{
	
	private String serviceTypeId;
	private String serviceTypeCode;
	private String description;
	private String category;
	private String status;

	/**
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @return
	 */
	public String getServiceTypeCode()
	{
		return serviceTypeCode;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string)
	{
		description = string;
	}

	/**
	 * @param string
	 */
	public void setServiceTypeCode(String string)
	{
		serviceTypeCode = string;
	}
	public int compareTo(Object obj) throws ClassCastException
	{
		ServiceTypeCdResponseEvent evt = (ServiceTypeCdResponseEvent) obj;
		return description.compareTo(evt.getDescription());
	}


	/**
	 * @return Returns the category.
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category The category to set.
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return Returns the serviceTypeId.
	 */
	public String getServiceTypeId() {
		return serviceTypeId;
	}
	/**
	 * @param serviceTypeId The serviceTypeId to set.
	 */
	public void setServiceTypeId(String serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
