/*
 * Created on Oct 20, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.administerserviceprovider.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ServiceProviderServiceResponseEvent extends ResponseEvent implements Comparable
{
	private String juvServProviderId;
	private String juvServProviderName;
	private boolean inHouse;
	private String startDate;
	private String programName;
	private String targetInterventionId;
	private String targetIntervention;
	private String serviceName;
	private String serviceTypeId;
	private String serviceType;
	private String serviceCode;
	private String serviceCost;
	private String maxEnrollment;
	private String serviceDescription;
	private String locationName;
	
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
	 * @return Returns the juvServProviderId.
	 */
	public String getJuvServProviderId() {
		return juvServProviderId;
	}
	/**
	 * @param juvServProviderId The juvServProviderId to set.
	 */
	public void setJuvServProviderId(String juvServProviderId) {
		this.juvServProviderId = juvServProviderId;
	}
	/**
	 * @return Returns the juvServProviderName.
	 */
	public String getJuvServProviderName() {
		return juvServProviderName;
	}
	/**
	 * @param juvServProviderName The juvServProviderName to set.
	 */
	public void setJuvServProviderName(String juvServProviderName) {
		this.juvServProviderName = juvServProviderName;
	}
	/**
	 * @return Returns the locationName.
	 */
	public String getLocationName() {
		return locationName;
	}
	/**
	 * @param locationName The locationName to set.
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	/**
	 * @return Returns the maxEnrollment.
	 */
	public String getMaxEnrollment() {
		return maxEnrollment;
	}
	/**
	 * @param maxEnrollment The maxEnrollment to set.
	 */
	public void setMaxEnrollment(String maxEnrollment) {
		this.maxEnrollment = maxEnrollment;
	}
	/**
	 * @return Returns the programName.
	 */
	public String getProgramName() {
		return programName;
	}
	/**
	 * @param programName The programName to set.
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	/**
	 * @return Returns the serviceCode.
	 */
	public String getServiceCode() {
		return serviceCode;
	}
	/**
	 * @param serviceCode The serviceCode to set.
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	/**
	 * @return Returns the serviceDescription.
	 */
	public String getServiceDescription() {
		return serviceDescription;
	}
	/**
	 * @param serviceDescription The serviceDescription to set.
	 */
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	/**
	 * @return Returns the serviceName.
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * @param serviceName The serviceName to set.
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	/**
	 * @return Returns the serviceType.
	 */
	public String getServiceType() {
		return serviceType;
	}
	/**
	 * @param serviceType The serviceType to set.
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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
	/**
	 * @return Returns the startDate.
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return Returns the targetIntervention.
	 */
	public String getTargetIntervention() {
		return targetIntervention;
	}
	/**
	 * @param targetIntervention The targetIntervention to set.
	 */
	public void setTargetIntervention(String targetIntervention) {
		this.targetIntervention = targetIntervention;
	}
	/**
	 * @return Returns the targetInterventionId.
	 */
	public String getTargetInterventionId() {
		return targetInterventionId;
	}
	/**
	 * @param targetInterventionId The targetInterventionId to set.
	 */
	public void setTargetInterventionId(String targetInterventionId) {
		this.targetInterventionId = targetInterventionId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * @return Returns the serviceCost.
	 */
	public String getServiceCost() {
		return serviceCost;
	}
	/**
	 * @param serviceCost The serviceCost to set.
	 */
	public void setServiceCost(String serviceCost) {
		this.serviceCost = serviceCost;
	}
}
