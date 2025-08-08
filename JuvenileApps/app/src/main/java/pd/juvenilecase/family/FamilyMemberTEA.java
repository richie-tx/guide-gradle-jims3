package pd.juvenilecase.family;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;


/**
* @version 1.0.0
* @referencedType pd.codetable.Code
* @contextKey LANGUAGE
* @detailerDoNotGenerate false
*/
public class FamilyMemberTEA extends mojo.km.persistence.PersistentObject  {
	
	public FamilyMemberTEA(){
		
	}
	
	private String constellationId;
	private String memberId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String relationToJuvenileCd;
	private boolean guradian;
	private boolean inHome;
	private boolean deceased;
	private boolean incarcerated;
	private String streetName;
	private String streetNumber;
	private String streetNumberSuffix;
	private String streetTypeCd;
	private String apartmentNumber;
	private String streetAddress2;
	private String stateCd;
	private String city;
	private String zipCode;
	private String zipCodeExtension;
	private String phoneNumber;
	private String juvenileId;
	
	/**
	* @return 
	* @param searchEvent
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, FamilyMemberTEA.class);
	}
	
	 /**
     * Finds all Conditions by an attribute value
     * 
     * @return
     * @param attributeName
     * @param attributeValue
     */
     public Iterator findAll(String attributeName, String attributeValue)
    {
        IHome home = new Home();
        Iterator teaMembers = home.findAll(attributeName, attributeValue, FamilyMemberTEA.class);
        return teaMembers;
    }
	
	/**
	 * @return the juvenileId
	 */
	public String getJuvenileId() {
		fetch();
		return juvenileId;
	}
	/**
	 * @param juvenileId the juvenileId to set
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
	/**
	 * @return the constellationId
	 */
	public String getConstellationId() {
		fetch();
		return constellationId;
	}
	/**
	 * @param constellationId the constellationId to set
	 */
	public void setConstellationId(String constellationId) {
		this.constellationId = constellationId;
	}
	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		fetch();
		return memberId;
	}
	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
	 * @return the relationToJuvenileCd
	 */
	public String getRelationToJuvenileCd() {
		fetch();
		return relationToJuvenileCd;
	}
	/**
	 * @param relationToJuvenileCd the relationToJuvenileCd to set
	 */
	public void setRelationToJuvenileCd(String relationToJuvenileCd) {
		this.relationToJuvenileCd = relationToJuvenileCd;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		fetch();
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the streetNumber
	 */
	public String getStreetNumber() {
		fetch();
		return streetNumber;
	}
	/**
	 * @param streetNumber the streetNumber to set
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	/**
	 * @return the streetNumberSuffix
	 */
	public String getStreetNumberSuffix() {
		fetch();
		return streetNumberSuffix;
	}
	/**
	 * @param streetNumberSuffix the streetNumberSuffix to set
	 */
	public void setStreetNumberSuffix(String streetNumberSuffix) {
		this.streetNumberSuffix = streetNumberSuffix;
	}
	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		fetch();
		return streetName;
	}
	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	/**
	 * @return the streetTypeCd
	 */
	public String getStreetTypeCd() {
		fetch();
		return streetTypeCd;
	}
	/**
	 * @param streetTypeCd the streetTypeCd to set
	 */
	public void setStreetTypeCd(String streetTypeCd) {
		this.streetTypeCd = streetTypeCd;
	}
	/**
	 * @return the apartmentNumber
	 */
	public String getApartmentNumber() {
		fetch();
		return apartmentNumber;
	}
	/**
	 * @param apartmentNumber the apartmentNumber to set
	 */
	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}
	/**
	 * @return the streetAddress2
	 */
	public String getStreetAddress2() {
		fetch();
		return streetAddress2;
	}
	/**
	 * @param streetAddress2 the streetAddress2 to set
	 */
	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		fetch();
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the stateCd
	 */
	public String getStateCd() {
		fetch();
		return stateCd;
	}
	/**
	 * @param stateCd the stateCd to set
	 */
	public void setStateCd(String stateCd) {
		this.stateCd = stateCd;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		fetch();
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the zipCodeExtension
	 */
	public String getZipCodeExtension() {
		fetch();
		return zipCodeExtension;
	}
	/**
	 * @param zipCodeExtension the zipCodeExtension to set
	 */
	public void setZipCodeExtension(String zipCodeExtension) {
		this.zipCodeExtension = zipCodeExtension;
	}

	/**
	 * @return the guradian
	 */
	public boolean isGuradian() {
		return guradian;
	}

	/**
	 * @param guradian the guradian to set
	 */
	public void setGuradian(boolean guradian) {
		this.guradian = guradian;
	}

	/**
	 * @return the inHome
	 */
	public boolean isInHome() {
		return inHome;
	}

	/**
	 * @param inHome the inHome to set
	 */
	public void setInHome(boolean inHome) {
		this.inHome = inHome;
	}

	/**
	 * @return the deceased
	 */
	public boolean isDeceased() {
		return deceased;
	}

	/**
	 * @param deceased the deceased to set
	 */
	public void setDeceased(boolean deceased) {
		this.deceased = deceased;
	}

	/**
	 * @return the incarcerated
	 */
	public boolean isIncarcerated() {
		return incarcerated;
	}

	/**
	 * @param incarcerated the incarcerated to set
	 */
	public void setIncarcerated(boolean incarcerated) {
		this.incarcerated = incarcerated;
	}

}
