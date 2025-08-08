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
public class HospitalizationAdmissionTypeCdResponseEvent extends ResponseEvent implements Comparable
{
	
	private String admissionTypeCode;
	private String description;
	private String categoryId;

	/**
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string)
	{
		description = string;
	}

	public int compareTo(Object obj) throws ClassCastException
	{
		HospitalizationAdmissionTypeCdResponseEvent evt = (HospitalizationAdmissionTypeCdResponseEvent) obj;
		return description.compareTo(evt.getDescription());
	}


	
	/**
	 * @return Returns the admissionTypeCode.
	 */
	public String getAdmissionTypeCode() {
		return admissionTypeCode;
	}
	/**
	 * @param admissionTypeCode The admissionTypeCode to set.
	 */
	public void setAdmissionTypeCode(String admissionTypeCode) {
		this.admissionTypeCode = admissionTypeCode;
	}
	/**
	 * @return Returns the categoryId.
	 */
	public String getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId The categoryId to set.
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}
