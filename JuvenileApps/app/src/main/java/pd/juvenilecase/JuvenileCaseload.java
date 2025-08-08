package pd.juvenilecase;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

import org.apache.commons.lang.StringUtils;

public class JuvenileCaseload extends PersistentObject{

	private String juvenileNum;
	private String firstName;
	private String middleName;
	private String lastName;
	private String nameSuffix;
	private String facilityLocation;
	private String facilityStatus;
	
	public JuvenileCaseload()
	{
	}
	
	/**
	* @param juvenileId
	* @return JuvenileCaseload
	*/
	static public JuvenileCaseload find(String juvenileNum)
	{
		IHome home = new Home();
		JuvenileCaseload caseload = (JuvenileCaseload) home.find(juvenileNum, JuvenileCaseload.class);
		return caseload;
	}
	/**
	* Finds caseloads by a certain event
	* @param event
	* @return Iterator of caseloads
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator caseloads = home.findAll(event, JuvenileCaseload.class);
		return caseloads;
	}
	/**
	* Finds all caseloads by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		Iterator caseloads = home.findAll(attributeName, attributeValue, JuvenileCaseload.class);
		return caseloads;
	}
	/**
	 * @return the juvenileId
	 */
	public String getJuvenileNum() {
		fetch();
		return juvenileNum;
	}
	/**
	 * @param juvenileId the juvenileId to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		fetch();
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		fetch();
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		fetch();
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the nameSuffix
	 */
	public String getNameSuffix() {
		fetch();
		return nameSuffix;
	}

	/**
	 * @param nameSuffix the nameSuffix to set
	 */
	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	/**
	 * @return the facilityLocation
	 */
	public String getFacilityLocation() {
		fetch();
		return facilityLocation;
	}
	/**
	 * @param facilityLocation the facilityLocation to set
	 */
	public void setFacilityLocation(String facilityLocation) {
		this.facilityLocation = facilityLocation;
	}
	/**
	 * @return the facilityStatus
	 */
	public String getFacilityStatus() {
		fetch();
		return facilityStatus;
	}
	/**
	 * @param facilityStatus the facilityStatus to set
	 */
	public void setFacilityStatus(String facilityStatus) {
		this.facilityStatus = facilityStatus;
	}
	/**
	 * @return the JuvenileName
	 */
	public String getOfficerName() {
		String name = null;
		StringBuffer full = new StringBuffer();
		if (StringUtils.isNotEmpty(lastName)) {
			full.append(lastName);
		}
		if (StringUtils.isNotEmpty(firstName)) {
			full.append(", ");
			full.append(firstName);
			if (StringUtils.isNotEmpty(middleName)) {
				full.append(" " + middleName);
			}
		}
		name = full.toString();

		return name;
	}
}
