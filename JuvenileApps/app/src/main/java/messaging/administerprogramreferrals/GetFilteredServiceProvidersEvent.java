package messaging.administerprogramreferrals;

import java.util.List;

import mojo.km.messaging.RequestEvent;

public class GetFilteredServiceProvidersEvent extends RequestEvent
{
	private List referralTypes;
	private List regionCdsList;
	private List languagesOfferedList;
	private String sexSpecific;
	private String contractProgram;
	
	
	/**
	 * @return the referralTypes
	 */
	public List getReferralTypes() {
		return referralTypes;
	}
	/**
	 * @param referralTypes the referralTypes to set
	 */
	public void setReferralTypes(List referralTypes) {
		this.referralTypes = referralTypes;
	}
	/**
	 * @return the regionCdsList
	 */
	public List getRegionCdsList() {
		return regionCdsList;
	}
	/**
	 * @param regionCdsList the regionCdsList to set
	 */
	public void setRegionCdsList(List regionCdsList) {
		this.regionCdsList = regionCdsList;
	}
	/**
	 * @return the languagesOfferedList
	 */
	public List getLanguagesOfferedList() {
		return languagesOfferedList;
	}
	/**
	 * @param languagesOfferedList the languagesOfferedList to set
	 */
	public void setLanguagesOfferedList(List languagesOfferedList) {
		this.languagesOfferedList = languagesOfferedList;
	}
	/**
	 * @return the sexSpecific
	 */
	public String getSexSpecific() {
		return sexSpecific;
	}
	/**
	 * @param sexSpecific the sexSpecific to set
	 */
	public void setSexSpecific(String sexSpecific) {
		this.sexSpecific = sexSpecific;
	}
	/**
	 * @return the contractProgram
	 */
	public String getContractProgram() {
		return contractProgram;
	}
	/**
	 * @param contractProgram the contractProgram to set
	 */
	public void setContractProgram(String contractProgram) {
		this.contractProgram = contractProgram;
	}

}
