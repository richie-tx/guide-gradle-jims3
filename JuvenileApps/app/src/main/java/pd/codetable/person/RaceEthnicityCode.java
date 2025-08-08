package pd.codetable.person;

import java.util.Iterator;

import messaging.codetable.GetRaceEthnicityCodesEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 43735B2503C7
*/
public class RaceEthnicityCode extends PersistentObject {
	private String ncicEthnicity;
	private String jjsRaceCode;
	private String ncicRaceCode;
	private String description;
	/**
	* @roseuid 43735B2503C7
	*/
	public RaceEthnicityCode() {
	}
	/**
	* @roseuid 43734F450041
	*/
	public void getRaces() {
		fetch();
	}
	/**
	* @return 
	*/
	public String getDescription() {
		fetch();
		return description;
	}
	/**
	* @return 
	*/
	public String getJjsRaceCode() {
		fetch();
		return jjsRaceCode;
	}
	/**
	* @return 
	*/
	public String getNcicEthnicity() {
		fetch();
		return ncicEthnicity;
	}
	/**
	* @return 
	*/
	public String getNcicRaceCode() {
		fetch();
		return ncicRaceCode;
	}
	/**
	* @param string
	*/
	public void setDescription(String string) {
		if (this.description == null || !this.description.equals(string)) {
			markModified();
		}
		description = string;
	}
	/**
	* @param string
	*/
	public void setJjsRaceCode(String string) {
		if (this.jjsRaceCode == null || !this.jjsRaceCode.equals(string)) {
			markModified();
		}
		jjsRaceCode = string;
	}
	/**
	* @param string
	*/
	public void setNcicEthnicity(String string) {
		if (this.ncicEthnicity == null || !this.ncicEthnicity.equals(string)) {
			markModified();
		}
		ncicEthnicity = string;
	}
	/**
	* @param string
	*/
	public void setNcicRaceCode(String string) {
		if (this.ncicRaceCode == null || !this.ncicRaceCode.equals(string)) {
			markModified();
		}
		ncicRaceCode = string;
	}
	/**
	 * 
	 * @return JJSRaces
	 */
	public static Iterator findAll()
		{
			IHome home = new Home();
			Iterator iter = home.findAll(RaceEthnicityCode.class);
			return iter;
		}
	/**
	 * @param event
	 * @return
	 */
	public static Iterator findAll(GetRaceEthnicityCodesEvent event)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(event, RaceEthnicityCode.class);
		return iter;
	}
}
