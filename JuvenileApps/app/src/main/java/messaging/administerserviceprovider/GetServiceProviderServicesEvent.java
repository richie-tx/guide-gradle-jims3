//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetServiceProviderServicesEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetServiceProviderServicesEvent extends RequestEvent
{
	private int juvLocUnitId;
	private int serviceProviderId;
	private String serviceTypeId;
	private int inHouse;
	private int serviceId;
	private String serviceStatusCd;
	private String programStatusCd;

	/**
	 * @return Returns the inHouse.
	 */
	public int getInHouse() {
		return inHouse;
	}
	/**
	 * @param inHouse The inHouse to set.
	 */
	public void setInHouse(int inHouse) {
		this.inHouse = inHouse;
	}
	/**
	 * @roseuid 44805C940340
	 */
	public GetServiceProviderServicesEvent()
	{

	}
	
	/**
	 * @return
	 */
	public int getServiceProviderId()
	{
		return serviceProviderId;
	}

	/**
	 * @return
	 */
	public String getServiceTypeId()
	{
		return serviceTypeId;
	}

	/**
	 * @param i
	 */
	public void setServiceProviderId(int i)
	{
		serviceProviderId = i;
	}

	/**
	 * @param string
	 */
	public void setServiceTypeId(String string)
	{
		serviceTypeId = string;
	}

	/**
	 * @return Returns the serviceId.
	 */
	public int getServiceId() {
		return serviceId;
	}
	/**
	 * @param serviceId The serviceId to set.
	 */
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	/**
	 * @return Returns the juvLocUnitId.
	 */
	public int getJuvLocUnitId() {
		return juvLocUnitId;
	}
	/**
	 * @param juvLocUnitId The juvLocUnitId to set.
	 */
	public void setJuvLocUnitId(int juvLocUnitId) {
		this.juvLocUnitId = juvLocUnitId;
	}

	/**
	 * @return Returns the serviceStatusCd.
	 */
	public String getServiceStatusCd() {
		return serviceStatusCd;
	}
	/**
	 * @param serviceStatusCd The serviceStatusCd to set.
	 */
	public void setServiceStatusCd(String serviceStatusCd) {
		this.serviceStatusCd = serviceStatusCd;
	}
	/**
	 * @return Returns the programStatusCd.
	 */
	public String getProgramStatusCd() {
		return programStatusCd;
	}
	/**
	 * @param programStatusCd The programStatusCd to set.
	 */
	public void setProgramStatusCd(String programStatusCd) {
		this.programStatusCd = programStatusCd;
	}
}