package pd.codetable.person;

import java.util.Iterator;

import messaging.juvenile.GetSchoolNameSearchEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author dgibler
 *
 * JuvenileSchoolDistrictCode entity
 */
public class JuvenileSchoolDistrictCode extends PersistentObject
{
	private String teaCode;
	private String schoolDescription;
	private String inactiveInd;
	private String districtCode;
	private String districtDescription;
	private String schoolCode;
	private String instructionType;
	private String charterType;
	private String streetName;
	private String city;
	private String state;
	private String zip;
	private String phoneNum;
	private String subGroupInd;
	private String streetNum;
	private String street;
	
	/**
	* @roseuid 41B7596A039C
	* Constructor
	*/
	public JuvenileSchoolDistrictCode()
	{
		super();
	}
	/**
	* Access method for the districtCode property.
	* @return the current value of the districtCode property
	*/
	public String getDistrictCode()
	{
		fetch();
		return districtCode;
	}
	/**
	* Sets the value of the districtCode property.
	* @param aDistrictCode the new value of the districtCode property
	*/
	public void setDistrictCode(String aDistrictCode)
	{
		if (this.districtCode == null || !this.districtCode.equals(aDistrictCode))
		{
			markModified();
		}
		districtCode = aDistrictCode;
	}
	/**
	* Access method for the districtDescription property.
	* @return the current value of the districtDescription property
	*/
	public String getDistrictDescription()
	{
		fetch();
		return districtDescription;
	}
	/**
	* Sets the value of the districtDescription property.
	* @param aDistrictDescription the new value of the districtDescription property
	*/
	public void setDistrictDescription(String aDistrictDescription)
	{
		if (this.districtDescription == null || !this.districtDescription.equals(aDistrictDescription))
		{
			markModified();
		}
		districtDescription = aDistrictDescription;
	}
	/**
	* Access method for the schoolCode property.
	* @return the current value of the schoolCode property
	*/
	public String getSchoolCode()
	{
		fetch();
		return schoolCode;
	}
	/**
	* Sets the value of the schoolCode property.
	* @param aSchoolCode the new value of the schoolCode property
	*/
	public void setSchoolCode(String aSchoolCode)
	{
		if (this.schoolCode == null || !this.schoolCode.equals(aSchoolCode))
		{
			markModified();
		}
		schoolCode = aSchoolCode;
	}
	/**
	* Access method for the schoolDescription property.
	* @return the current value of the schoolDescription property
	*/
	public String getSchoolDescription()
	{
		fetch();
		return schoolDescription;
	}
	/**
	* Sets the value of the schoolDescription property.
	* @param aSchoolDescription the new value of the schoolDescription property
	*/
	public void setSchoolDescription(String aSchoolDescription)
	{
		if (this.schoolDescription == null || !this.schoolDescription.equals(aSchoolDescription))
		{
			markModified();
		}
		schoolDescription = aSchoolDescription;
	}
	/**
	* Access method for the inactiveInd property.
	* @return the current value of the inactiveInd property
	*/
	public String getInactiveInd()
	{
		fetch();
		return inactiveInd;
	}
	/**
	* Sets the value of the inactiveInd property.
	* @param aInactiveInd the new value of the inactiveInd property
	*/
	public void setInactiveInd(String aInactiveInd)
	{
		if (this.inactiveInd == null || !this.inactiveInd.equals(aInactiveInd))
		{
			markModified();
		}
		inactiveInd = aInactiveInd;
	}
	/**
	* Access method for the teaCode property.
	* @return the current value of the teaCode property
	*/
	public String getTeaCode()
	{
		fetch();
		return teaCode;
	}
	/**
	* Sets the value of the teaCode property.
	* @param aTeaCode the new value of the teaCode property
	*/
	public void setTeaCode(String aTeaCode)
	{
		if (this.teaCode == null || !this.teaCode.equals(aTeaCode))
		{
			markModified();
		}
		teaCode = aTeaCode;
	}
	/**
	* @roseuid 41B752C000FA
	* Finds JuvenileSchoolDistrictCode entity object by districtCode
	* @param districtCode
	* @return JuvenileSchoolDistrictCode object
	*/
	public static JuvenileSchoolDistrictCode find(String districtCode)
	{
		JuvenileSchoolDistrictCode code = null;
		IHome home = new Home();

		code = (JuvenileSchoolDistrictCode) home.find(districtCode, JuvenileSchoolDistrictCode.class);

		return code;
	}

	/**
	 * Finds all JuvenileSchoolDistrictCode objects
	 * @return all JuvenileSchoolDistrictCode objects
	 */
	public static Iterator findAll()
	{
		return new Home().findAll(JuvenileSchoolDistrictCode.class);
	}

	/**
	 * Finds all JuvenileSchoolDistrictCode objects
	 * @return all JuvenileSchoolDistrictCode objects
	 */
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		return new Home().findAll(attributeName, attributeValue, JuvenileSchoolDistrictCode.class);
	}
	
	/**
	 * Finds all JuvenileSchoolDistrictCode objects based upon GetSchoolNameSearchEvent
	 * @return all JuvenileSchoolDistrictCode objects
	 */
	public static Iterator findAll(GetSchoolNameSearchEvent event)
	{
		return new Home().findAll(event, JuvenileSchoolDistrictCode.class);
	}
	public String getInstructionType() {
		fetch();
		return instructionType;
	}
	public void setInstructionType(String instructionType) {
		if (this.instructionType == null || !this.instructionType.equals(instructionType))
		{
			markModified();
		}
		this.instructionType = instructionType;
	}
	public String getCharterType() {
		fetch();
		return charterType;
	}
	public void setCharterType(String charterType) {
		if (this.charterType == null || !this.charterType.equals(charterType))
		{
			markModified();
		}
		this.charterType = charterType;
	}
	public String getStreetName() {
		fetch();
		return streetName;
	}
	public void setStreetName(String streetName) {
		if (this.streetName == null || !this.streetName.equals(streetName))
		{
			markModified();
		}
		this.streetName = streetName;
	}
	public String getCity() {
		fetch();
		return city;
	}
	public void setCity(String city) {
		if (this.city == null || !this.city.equals(city))
		{
			markModified();
		}
		this.city = city;
	}
	public String getState() {
		fetch();
		return state;
	}
	public void setState(String state) {
		if (this.state == null || !this.state.equals(state))
		{
			markModified();
		}
		this.state = state;
	}
	public String getZip() {
		fetch();
		return zip;
	}
	public void setZip(String zip) {
		if (this.zip == null || !this.zip.equals(zip))
		{
			markModified();
		}
		this.zip = zip;
	}
	public String getPhoneNum() {
		fetch();
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		if (this.phoneNum == null || !this.phoneNum.equals(phoneNum))
		{
			markModified();
		}
		this.phoneNum = phoneNum;
	}
	public void setSubGroupInd(String subGroupInd) {
		if (this.subGroupInd == null || !this.subGroupInd.equals(subGroupInd))
		{
			markModified();
		}

		this.subGroupInd = subGroupInd;
	}
	public String getSubGroupInd() {
		fetch();
		return subGroupInd;
	}
	public String getStreetNum()
	{
	    fetch();
	    return streetNum;
	}
	
	public void setStreetNum(String streetNum)
	{
	    if ( this.streetNum == null || !this.streetNum.equals(streetNum)) {
		markModified();
	    }
	    this.streetNum = streetNum;
	}
	
	public String getStreet()
	{
	    fetch();
	    return street;
	}
	
	public void setStreet(String street)
	{
	    if ( this.street == null || !this.street.equals(street)) {
		markModified();
	    }
	    this.street = street;
	}
}
