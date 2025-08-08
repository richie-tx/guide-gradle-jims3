/*
 * Create on May 24, 2006
 */

package messaging.administerserviceprovider.reply;

import mojo.km.messaging.ResponseEvent;

public class ServiceTypeResponseEvent extends ResponseEvent
{
    private String serviceTypeId;
    private String serviceTypeName;
    private String serviceTypeCode;

    /**
     * @return
     */
    public String getServiceTypeId()
    {
	return serviceTypeId;
    }

    /**
     * @return
     */
    public String getServiceTypeName()
    {
	return serviceTypeName;
    }

    /**
     * @param string
     */
    public void setServiceTypeId(String string)
    {
	serviceTypeId = string;
    }

    /**
     * @param string
     */
    public void setServiceTypeName(String string)
    {
	serviceTypeName = string;
    }

    public String getServiceTypeCode()
    {
	return serviceTypeCode;
    }

    public void setServiceTypeCode(String serviceTypeCode)
    {
	this.serviceTypeCode = serviceTypeCode;
    }
}