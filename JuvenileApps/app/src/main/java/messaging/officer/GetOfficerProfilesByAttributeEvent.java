//Source file: C:\\views\\dev\\app\\src\\messaging\\officer\\GetOfficerProfilesEvent.java

package messaging.officer;

import mojo.km.messaging.RequestEvent;

public class GetOfficerProfilesByAttributeEvent extends RequestEvent
{
    private String attributeName;
    private String attributeValue;


	public GetOfficerProfilesByAttributeEvent()
	{

	}


	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}


	/**
	 * @param attributeName the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}


	/**
	 * @return the attributeValue
	 */
	public String getAttributeValue() {
		return attributeValue;
	}


	/**
	 * @param attributeValue the attributeValue to set
	 */
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

}
