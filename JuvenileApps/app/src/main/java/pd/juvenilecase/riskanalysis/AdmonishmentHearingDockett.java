package pd.juvenilecase.riskanalysis;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 433D846400FC
*/
public class AdmonishmentHearingDockett extends PersistentObject {
	private String juvenileNum;
	private String petitionAllegation;
	/**
	* @roseuid 433D846400FC
	*/
	public AdmonishmentHearingDockett() {
	}
	/**
	* @return 
	*/
	public String getJuvenileNum() {
		fetch();
		return juvenileNum;
	}
	/**
	* @return 
	*/
	public String getPetitionAllegation() {
		fetch();
		return petitionAllegation;
	}
	/**
	* @param string
	*/
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	/**
	* @param string
	*/
	public void setPetitionAllegation(String petitionAllegation) {
		this.petitionAllegation = petitionAllegation;
	}
	

	public static AdmonishmentHearingDockett findByJuvenileNum(String juvenileNum) {
		IHome home = new Home();
		return (AdmonishmentHearingDockett)home.find("juvenileNum", juvenileNum, AdmonishmentHearingDockett.class);		

	}
}
