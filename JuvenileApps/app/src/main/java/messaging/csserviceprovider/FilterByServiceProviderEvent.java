/*
 * Created on Apr 10, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.csserviceprovider;

import java.util.List;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FilterByServiceProviderEvent extends SearchServiceProviderEvent 
{
	private List referralTypes;
	private List regiondCdsList;
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
	 * @return the regiondCdsList
	 */
	public List getRegiondCdsList() {
		return regiondCdsList;
	}
	/**
	 * @param regiondCdsList the regiondCdsList to set
	 */
	public void setRegiondCdsList(List regiondCdsList) {
		this.regiondCdsList = regiondCdsList;
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
