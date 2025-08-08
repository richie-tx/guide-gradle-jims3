package pd.contact.officer;

import pd.address.Address;

/**
* @roseuid 4356A7690102
*/
public class OfficerProfileAddress extends Address {
	private String officerProfileId;
	/**
	* @roseuid 4356A7690102
	*/
	public OfficerProfileAddress() {
	}
	/**
	* Access method for the officerProfileId property.
	* @return the current value of the officerProfileId property
	*/
	public String getOfficerProfileId() {
		fetch();
		return officerProfileId;
	}
	/**
	* Sets the value of the officerProfileId property.
	* @param aOfficerProfileId the new value of the officerProfileId property
	*/
	public void setOfficerProfileId(String aOfficerProfileId) {
		if (this.officerProfileId == null || !this.officerProfileId.equals(aOfficerProfileId)) {
			markModified();
		}
		officerProfileId = aOfficerProfileId;
	}
}