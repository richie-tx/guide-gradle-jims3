package ui.supervision.programReferral;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CSServProvByPrgRefCaseloadBean 
{
	private String serviceProviderId;
	private String serviceProviderName;
	private String inHouse;
	private Set locRegionCdsSet;
	private String locationRegions;
	
	private Set spProgramIdsSet;
	private List spProgramsList; //CSProgramByPrgRefCaseloadBean
	
	private List spProgReferralList; //CSProgRefCaseloadBean

	private List openProgReferralList; //CSProgRefCaseloadBean
	private List allProgReferralList; //CSProgRefCaseloadBean
	
	
	/**
	 * @return the serviceProviderId
	 */
	public String getServiceProviderId() {
		return serviceProviderId;
	}

	/**
	 * @param serviceProviderId the serviceProviderId to set
	 */
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	/**
	 * @return the serviceProviderName
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}

	/**
	 * @param serviceProviderName the serviceProviderName to set
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}

	/**
	 * @return the inHouse
	 */
	public String getInHouse() {
		return inHouse;
	}

	/**
	 * @param inHouse the inHouse to set
	 */
	public void setInHouse(String inHouse) {
		this.inHouse = inHouse;
	}
	/**
	 * @return the locRegionCdsSet
	 */
	public Set getLocRegionCdsSet() {
		return locRegionCdsSet;
	}

	/**
	 * @param locRegionCdsSet the locRegionCdsSet to set
	 */
	public void setLocRegionCdsSet(Set locRegionCdsSet) {
		this.locRegionCdsSet = locRegionCdsSet;
	}

	/**
	 * @return the locationRegions
	 */
	public String getLocationRegions() 
	{
		this.locationRegions = "";
		Set regionCdsSet = this.locRegionCdsSet;
		if(regionCdsSet!=null)
		{
			Iterator<String> iter = regionCdsSet.iterator();
			while(iter.hasNext())
			{
				locationRegions = iter.next() + " ";
			}
		}
		return locationRegions;
	}

	/**
	 * @param locationRegions the locationRegions to set
	 */
	public void setLocationRegions(String locationRegions) {
		this.locationRegions = locationRegions;
	}

	/**
	 * @return the spProgramsList
	 */
	public List getSpProgramsList() {
		return spProgramsList;
	}

	/**
	 * @param spProgramsList the spProgramsList to set
	 */
	public void setSpProgramsList(List spProgramsList) {
		this.spProgramsList = spProgramsList;
	}

	/**
	 * @return the spProgReferralList
	 */
	public List getSpProgReferralList() {
		return spProgReferralList;
	}

	/**
	 * @param spProgReferralList the spProgReferralList to set
	 */
	public void setSpProgReferralList(List spProgReferralList) {
		this.spProgReferralList = spProgReferralList;
	}

	/**
	 * @return the spProgramIdsSet
	 */
	public Set getSpProgramIdsSet() {
		return spProgramIdsSet;
	}

	/**
	 * @param spProgramIdsSet the spProgramIdsSet to set
	 */
	public void setSpProgramIdsSet(Set spProgramIdsSet) {
		this.spProgramIdsSet = spProgramIdsSet;
	}

	/**
	 * @return the openProgReferralList
	 */
	public List getOpenProgReferralList() {
		return openProgReferralList;
	}

	/**
	 * @param openProgReferralList the openProgReferralList to set
	 */
	public void setOpenProgReferralList(List openProgReferralList) {
		this.openProgReferralList = openProgReferralList;
	}

	/**
	 * @return the allProgReferralList
	 */
	public List getAllProgReferralList() {
		return allProgReferralList;
	}

	/**
	 * @param allProgReferralList the allProgReferralList to set
	 */
	public void setAllProgReferralList(List allProgReferralList) {
		this.allProgReferralList = allProgReferralList;
	}
}
