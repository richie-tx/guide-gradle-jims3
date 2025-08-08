//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\CreateServiceEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.Composite.CompositeRequest;

public class CreateServiceEvent extends CompositeRequest
{
	public String serviceName;
	public String locationName;
	public String maxErollment;
	public String serviceCode;
	public String serviceCost;
	public String type;
	private String serviceId;
	private boolean isCreate;
	private String providerProgramId;
	private String rateId;
	private boolean inactivate;
	private String statusId;
	private String description;
	private String serviceProviderId;

	/**
	 * @roseuid 4473534B0143
	 */
	public CreateServiceEvent()
	{

	}

	/**
	 * Access method for the serviceName property.
	 * 
	 * @return   the current value of the serviceName property
	 */
	public String getServiceName()
	{
		return serviceName;
	}

	/**
	 * Access method for the locationName property.
	 * 
	 * @return   the current value of the locationName property
	 */
	public String getLocationName()
	{
		return locationName;
	}

	/**
	 * Access method for the maxErollment property.
	 * 
	 * @return   the current value of the maxErollment property
	 */
	public String getMaxErollment()
	{
		return maxErollment;
	}

	/**
	 * Access method for the serviceCode property.
	 * 
	 * @return   the current value of the serviceCode property
	 */
	public String getServiceCode()
	{
		return serviceCode;
	}

	/**
	 * Access method for the serviceCost property.
	 * 
	 * @return   the current value of the serviceCost property
	 */
	public String getServiceCost()
	{
		return serviceCost;
	}

	/**
	 * Access method for the type property.
	 * 
	 * @return   the current value of the type property
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Sets the value of the serviceName property.
	 * 
	 * @param aserviceName the new value of the serviceName property
	 */
	public void setServiceName(String aServiceName)
	{
		serviceName = aServiceName;
	}

	/**
	 * Sets the value of the locationName property.
	 * 
	 * @param aLocationName the new value of the locationName property
	 */
	public void setLocationName(String aLocationName)
	{
		locationName = aLocationName;
	}

	/**
	 * Sets the value of the maxErollment property.
	 * 
	 * @param aMaxErollment the new value of the maxErollment property
	 */
	public void setMaxErollment(String aMaxErollment)
	{
		maxErollment = aMaxErollment;
	}

	/**
	 * Sets the value of the serviceCode property.
	 * 
	 * @param aServiceCode the new value of the serviceCode property
	 */
	public void setServiceCode(String aServiceCode)
	{
		serviceCode = aServiceCode;
	}

	/**
	 * Sets the value of the serviceCost property.
	 * 
	 * @param aServiceCost the new value of the serviceCost property
	 */
	public void setServiceCost(String aServiceCost)
	{
		serviceCost = aServiceCost;
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param aType the new value of the type property
	 */
	public void setType(String aType)
	{
		type = aType;
	}
	/**
	 * @return
	 */
	public String getServiceId()
	{
		return serviceId;
	}

	/**
	 * @param string
	 */
	public void setServiceId(String string)
	{
		serviceId = string;
	}

	/**
	 * @return
	 */
	public boolean isCreate()
	{
		return isCreate;
	}

	/**
	 * @param b
	 */
	public void setCreate(boolean b)
	{
		isCreate = b;
	}

	/**
	 * @return
	 */
	public String getProviderProgramId()
	{
		return providerProgramId;
	}

	/**
	 * @return
	 */
	public String getRateId()
	{
		return rateId;
	}

	/**
	 * @param string
	 */
	public void setProviderProgramId(String string)
	{
		providerProgramId = string;
	}

	/**
	 * @param string
	 */
	public void setRateId(String string)
	{
		rateId = string;
	}

	/**
	 * @return Returns the inactivate.
	 */
	public boolean isInactivate() {
		return inactivate;
	}
	/**
	 * @param inactivate The inactivate to set.
	 */
	public void setInactivate(boolean inactivate) {
		this.inactivate = inactivate;
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
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the serviceProviderId.
	 */
	public String getServiceProviderId() {
		return serviceProviderId;
	}
	/**
	 * @param serviceProviderId The serviceProviderId to set.
	 */
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
}