/*
 * Created on Dec 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerserviceprovider.CSC;

import naming.PDCodeTableConstants;
import ui.common.SimpleCodeTableHelper;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceProviderLightLocationBean implements Comparable{
	
	private String locationId;
	private String locationName;
	private String locationStatusId;
	private String locationStatusDesc;
	

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * @return Returns the locationId.
	 */
	public String getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId The locationId to set.
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
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
	 * @return Returns the locationStatusDesc.
	 */
	public String getLocationStatusDesc() {
		return locationStatusDesc;
	}
	/**
	 * @param locationStatusDesc The locationStatusDesc to set.
	 */
	public void setLocationStatusDesc(String locationStatusDesc) {
		this.locationStatusDesc = locationStatusDesc;
	}
	/**
	 * @return Returns the locationStatusId.
	 */
	public String getLocationStatusId() {
		return locationStatusId;
	}
	/**
	 * @param locationStatusId The locationStatusId to set.
	 */
	public void setLocationStatusId(String locationStatusId) {
		this.locationStatusId = locationStatusId;
		this.locationStatusDesc="";
		locationStatusDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.LOCATION_STATUS,locationStatusId);
	}
}
