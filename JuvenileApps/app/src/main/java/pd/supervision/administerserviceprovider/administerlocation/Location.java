package pd.supervision.administerserviceprovider.administerlocation;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import messaging.administerlocation.CreateUpdateLocationEvent;
import messaging.administerlocation.GetLocationsByAgencyEvent;
import messaging.administerlocation.GetLocationsEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.CollectionUtil;
import naming.PDAdministerServiceProviderConstants;
import naming.PDSecurityConstants;
import pd.address.Address;
import pd.codetable.Code;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProvider;

/**
* @roseuid 447357C70049
*/
public class Location extends PersistentObject {
	private String addressId;
	private String locationName;
	private String agencyId;
	/**
	* Properties for address
	* @useParent true
	* @detailerDoNotGenerate false
	*/
	private Address address = null;
	private String locationId;
	private boolean inHouse;
	private String statusId;
	private Code status = null;
	private String serviceProviderName;
	private String serviceProviderId;
	
	private String facilityTypeId;
	private Code facilityType;
	
	private String phoneNumber;
	private String locationFax;
	
	private String locationRegionId;
	private Code locationRegion = null;
	
	//Address Details from Location Search view - Only for Location Search	
	private String streetNum;
	private String streetName;
	private String city;
	private String aptNumber;
	private String stateId;
	private String zipCode;
	private String locationCd;
	
	private Collection<CSServiceProvider> serviceProviders;
	
	
	/**
	* @roseuid 447357C70049
	*/
	public Location() {
	}
	
    /**
	* Initialize list of program objects
	*/
	private void initServiceProviders() 
	{
		if (serviceProviders == null) 
		{
				//allocate array of serviceProvider objects
			serviceProviders = (mojo.km.persistence.ArrayList)
			    new mojo.km.persistence.ArrayList(CSServiceProvider.class, 
			            								"locationId", String.valueOf(getLocationId()));
		}
	}//end of initServiceProviders()

    /**
     * @return Returns the service providers.
     */
    public Collection getServiceProviders() 
    {
    		//retrieve location
		fetch();
		
			//initialize then return list of service providers
		initServiceProviders();
    
        return serviceProviders;
    }	
    
	/**
	* Access method for the locationName property.
	* @return the current value of the locationName property
	*/
	public String getLocationName() {
		fetch();
		return locationName;
	}
	/**
	* Sets the value of the locationName property.
	* @param aLocationName the new value of the locationName property
	*/
	public void setLocationName(String aLocationName) {
		if (this.locationName == null || !this.locationName.equals(aLocationName)) {
			markModified();
		}
		locationName = aLocationName;
	}
	/**
	* Determines if the inHouse property is true.
	* @return <code>true<code> if the inHouse property is true
	*/
	public boolean getInHouse() {
		fetch();
		return inHouse;
	}
	/**
	* Sets the value of the inHouse property.
	* @param aInHouse the new value of the inHouse property
	*/
	public void setInHouse(boolean aInHouse) {
		if (this.inHouse != aInHouse) {
			markModified();
		}
		inHouse = aInHouse;
	}
	/**
	* Access method for the agencyId property.
	* @return the current value of the agencyId property
	*/
	public String getAgencyId() {
		fetch();
		return agencyId;
	}
	/**
	* Sets the value of the agencyId property.
	* @param aAgencyId the new value of the agencyId property
	*/
	public void setAgencyId(String aAgencyId) {
		if (this.agencyId == null || !this.agencyId.equals(aAgencyId)) {
			markModified();
		}
		agencyId = aAgencyId;
	}
	/**
	 * @return Returns the serviceProviderName.
	 */
	public String getServiceProviderName() {
		fetch();
		return serviceProviderName;
	}
	/**
	 * @param serviceProviderName The serviceProviderName to set.
	 */
	public void setServiceProviderName(String serviceProviderName) {
		if (this.serviceProviderName == null || !this.serviceProviderName.equals(serviceProviderName)) {
			markModified();
		}
		this.serviceProviderName = serviceProviderName;
	}	
	
	/**
	 * @return Returns the serviceProviderId.
	 */
	public String getServiceProviderId() {
		fetch();
		return serviceProviderId;
	}
	/**
	 * @param serviceProviderId The serviceProviderId to set.
	 */
	public void setServiceProviderId(String serviceProviderId) {
		if (this.serviceProviderId == null || !this.serviceProviderId.equals(serviceProviderId)) {
			markModified();
		}
		this.serviceProviderId = serviceProviderId;
	}	
	/**
	* @roseuid 44734FE40066
	*/
	static public Iterator findAll()
    {
        IHome home = new Home();
        return home.findAll(Location.class);
    }
	/**
	* @return pd.administerLocation.Location
	* @param locationId
	* @roseuid 4107B06D01B5
	*/
	static public Location find(String locationId)
	{
	    IHome home = new Home();
		return (Location) home.find(locationId, Location.class);
	}
	
	public void createOID()
	{
		IHome home = new Home();
		home.bind(this);
	}
	/**
	* @param locationEvent
	*/
	public void setLocation(CreateUpdateLocationEvent locationEvent)
	{
		this.setLocationId(""+locationEvent.getLocationId());
		this.setLocationName(locationEvent.getLocationName());
		this.setLocationCd(locationEvent.getLocationCd());
		//this.setAgencyId(PDSecurityHelper.getUserAgencyId());//changed vj
		this.setAgencyId(locationEvent.getAgencyId());
		this.setFacilityTypeId(locationEvent.getFacilityTypeId());
		this.setStatusId(locationEvent.getStatusId());		
		this.setInHouse(locationEvent.getIsInHouse());
		this.setPhoneNumber(locationEvent.getPhoneNumber());
		this.setLocationFax(locationEvent.getLocationFax());
		if (locationEvent.getStatusId() == null || locationEvent.getStatusId().trim().equals(""))
		{
			this.setStatusId(PDSecurityConstants.ACTIVE);
		}
		else
		{
			this.setStatusId(locationEvent.getStatusId());
		}	
		
		this.setLocationRegionId(locationEvent.getLocationRegionId());
		if (locationEvent.getLocationRegionId() == null || locationEvent.getLocationRegionId().trim().equals(""))
		{
			this.setLocationRegionId(PDSecurityConstants.NORTH);
		}
		else
		{
			this.setLocationRegionId(locationEvent.getLocationRegionId());
		}		
	}
	
	/**
	* @return java.util.Iterator
	* @param agencyId
	* @roseuid 4177C29D03A9
	*/
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		return home.findAll(attrName, attrValue, Location.class);
	}
	/**
	* Set the reference value to class :: pd.address.Address
	*/
	public void setAddressId(String addressId) {
		if (this.addressId == null || !this.addressId.equals(addressId)) {
			markModified();
		}
		address = null;
		this.addressId = addressId;
	}
	/**
	* Set the reference value to locationId
	*/
	public void setLocationId(String locationId) {
		if (this.locationId == null || !this.locationId.equals(locationId)) {
			markModified();
		}
		this.locationId = locationId;
	}
	/**
	* Get the reference value to class :: pd.address.Address
	*/
	public String getAddressId() {
		fetch();
		return addressId;
	}
	/**
	* Get the reference value to locationId
	*/
	public String getLocationId() {
		return locationId;
	}
	/**
	* Initialize class relationship to class pd.address.Address
	*/
	private void initAddress() {
		if (address == null) {
			address =
				(Address) new mojo.km.persistence.Reference(addressId, Address.class).getObject();
		}
	}
	/**
	* Gets referenced type pd.address.Address
	*/
	public Address getAddress() {
		fetch();
		initAddress();
		return address;
	}
	/**
	* set the type reference for class member address
	*/
	public void setAddress(Address address) {
		if (this.address == null || !this.address.equals(address)) {
			markModified();
		}
		address.setContext(this, "address");
		if (address.getOID() == null) {
			new mojo.km.persistence.Home().bind(address);
		}
		setAddressId("" + address.getOID());
		this.address = (Address) new mojo.km.persistence.Reference(address).getObject();
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initStatus() {
		if (status == null) {
			status = (Code) new mojo.km.persistence.Reference(statusId, Code.class, "LOCATION_STATUS").getObject();
		}
	}	
	/**
	* set the type reference for class member status
	*/
	public void setStatus(Code status) {
		if (this.status == null || !this.status.equals(status)) {
			markModified();
		}
		if (status.getOID() == null) {
			new mojo.km.persistence.Home().bind(status);
		}
		setStatusId("" + status.getOID());
		status.setContext("LOCATION_STATUS");
		this.status = (Code) new mojo.km.persistence.Reference(status).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setStatusId(String statusId) {
		if (this.statusId == null || !this.statusId.equals(statusId)) {
			markModified();
		}
		status = null;
		this.statusId = statusId;
	}
	
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getStatus() {
		fetch();
		initStatus();
		return status;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getStatusId() {
		fetch();
		return statusId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initLocationRegion() {
		if (locationRegion == null) {
			locationRegion = (Code) new mojo.km.persistence.Reference(locationRegionId, Code.class, "LOCATION_REGION").getObject();
		}
	}	
	/**
	 * @return Returns the locationRegion.
	 */
	public Code getLocationRegion() {
		fetch();
		initLocationRegion();		
		return locationRegion;
	}
	/**
	 * @param locationRegion The locationRegion to set.
	 */
	public void setLocationRegion(Code locationRegion) {
		if (this.locationRegion == null || !this.locationRegion.equals(locationRegion)) {
			markModified();
		}
		if (locationRegion.getOID() == null) {
			new mojo.km.persistence.Home().bind(locationRegion);
		}
		setLocationRegionId("" + locationRegion.getOID());
		locationRegion.setContext("LOCATION_REGION");
		this.locationRegion = (Code) new mojo.km.persistence.Reference(locationRegion).getObject();
	}
	/**
	 * @return Returns the locationRegionId.
	 */
	public String getLocationRegionId() {
		fetch();
		return locationRegionId;
	}
	/**
	 * @param locationRegionId The locationRegionId to set.
	 */
	public void setLocationRegionId(String locationRegionId) {
		if (this.locationRegionId == null || !this.locationRegionId.equals(locationRegionId)) {
			markModified();
		}
		locationRegion = null;
		this.locationRegionId = locationRegionId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initFacilityType() {
		if (facilityType == null) {
			facilityType = (Code) new mojo.km.persistence.Reference(facilityTypeId, Code.class, "FACILITY_TYPE").getObject();
		}
	}	
	/**
	* set the type reference for class member facilityType
	*/
	public void setFacilityType(Code facilityType) {
		if (this.facilityType == null || !this.facilityType.equals(facilityType)) {
			markModified();
		}
		if (facilityType.getOID() == null) {
			new mojo.km.persistence.Home().bind(facilityType);
		}
		setFacilityTypeId("" + facilityType.getOID());
		facilityType.setContext("FACILITY_TYPE");
		this.facilityType = (Code) new mojo.km.persistence.Reference(facilityType).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setFacilityTypeId(String facilityTypeId) {
		if (this.facilityTypeId == null || !this.facilityTypeId.equals(facilityTypeId)) {
			markModified();
		}
		facilityType = null;
		this.facilityTypeId = facilityTypeId;
	}
	
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getFacilityType() {
		fetch();
		initFacilityType();
		return facilityType;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getFacilityTypeId() {
		fetch();
		return facilityTypeId;
	}	
	/**
	 * @param anEvent
	 * @return
	 */
	static public List findAll(IEvent anEvent){
		IHome home = new Home();
		Iterator iter = home.findAll(anEvent, Location.class);
		return CollectionUtil.iteratorToList(iter);
	}
	/**
	* @return 
	* @param locationEvent
	*/
	static public Iterator findLocation(GetLocationsEvent locationEvent)
	{
		IHome home = new Home();
		return home.findAll(locationEvent, Location.class);
	}
	/**
	* @return 
	* @param locationEvent
	*/
	static public Iterator findLocationByAgency(GetLocationsByAgencyEvent locationEvent)
	{
		IHome home = new Home();
		return home.findAll(locationEvent, Location.class);
	}
	/**
	* @return 
	*/
	public LocationResponseEvent getValueObject()
	{
		LocationResponseEvent event = new LocationResponseEvent();
		event.setTopic(PDAdministerServiceProviderConstants.LOCATION_EVENT_TOPIC);
		event.setLocationName(this.getLocationName());
		event.setInHouse(this.getInHouse());
		event.setAgencyId(this.getAgencyId());
		event.setLocationId(this.getLocationId());
		event.setServiceProviderName(this.getServiceProviderName());
		event.setStreetNum(this.getStreetNum());
		event.setStreetName(this.getStreetName());
		event.setAptNumber(this.getAptNumber());
		event.setCity(this.getCity());
		event.setStateId(this.getStateId());
		event.setZipCode(this.getZipCode());
		event.setPhoneNumber(this.getPhoneNumber());
		event.setLocationFax(this.getLocationFax());
		event.setLocationCd(this.getLocationCd());
		event.setStatusId(this.getStatusId());
		event.setInHouse(this.getInHouse());
		if (this.getStatusId() != null && !this.getStatusId().equals(""))
		{
			status = this.getStatus();
			if (status != null)
			{
				event.setStatusId(status.getCode());
				event.setStatus(status.getDescription());
			}
		}
		
		if (this.getLocationRegionId() != null && !this.getLocationRegionId().equals(""))
		{
			locationRegion = this.getLocationRegion();
			if (locationRegion != null)
			{
				event.setLocationRegionId(locationRegion.getCode());
				event.setLocationRegion(locationRegion.getDescription());
			}
		}

		if (this.getFacilityTypeId() != null && !this.getFacilityTypeId().equals(""))
		{
			facilityType = this.getFacilityType();
			if (facilityType != null)
			{
				event.setFacilityTypeId(facilityType.getCode());
				event.setFacilityType(facilityType.getDescription());
			}
		}				
		return event;
	}	

	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		fetch();
		return city;
	}
	/**
	 * @return Returns the stateId.
	 */
	public String getStateId() {
		fetch();
		return stateId;
	}
	/**
	 * @return Returns the streetName.
	 */
	public String getStreetName() {
		fetch();
		return streetName;
	}
	/**
	 * @return Returns the streetNum.
	 */
	public String getStreetNum() {
		fetch();
		return streetNum;
	}
	/**
	 * @return Returns the zipCode.
	 */
	public String getZipCode() {
		fetch();
		return zipCode;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		if (this.city == null || !this.city.equals(city)) {
			markModified();
		}
		this.city = city;
	}
	/**
	 * @param stateId The stateId to set.
	 */
	public void setStateId(String stateId) {
		if (this.stateId == null || !this.stateId.equals(stateId)) {
			markModified();
		}
		this.stateId = stateId;
	}
	/**
	 * @param streetName The streetName to set.
	 */
	public void setStreetName(String streetName) {
		if (this.streetName == null || !this.streetName.equals(streetName)) {
			markModified();
		}
		this.streetName = streetName;
	}
	/**
	 * @param streetNum The streetNum to set.
	 */
	public void setStreetNum(String streetNum) {
		if (this.streetNum == null || !this.streetNum.equals(streetNum)) {
			markModified();
		}
		this.streetNum = streetNum;
	}
	/**
	 * @param zipCode The zipCode to set.
	 */
	public void setZipCode(String zipCode) {
		if (this.zipCode == null || !this.zipCode.equals(zipCode)) {
			markModified();
		}
		this.zipCode = zipCode;
	}
	/**
	 * @return Returns the phoneNumber.
	 */
	public String getPhoneNumber() {
		fetch();
		return phoneNumber;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(String phoneNumber) {
		if (this.phoneNumber == null || !this.phoneNumber.equals(phoneNumber)) {
			markModified();
		}
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the locationFax
	 */
	public String getLocationFax() {
		return locationFax;
	}
	/**
	 * @param locationFax the locationFax to set
	 */
	public void setLocationFax(String locationFax) {
		if (this.locationFax == null || !this.locationFax.equals(locationFax)) {
			markModified();
		}
		this.locationFax = locationFax;
	}
	/**
	 * @return Returns the aptNumber.
	 */
	public String getAptNumber() {
		return aptNumber;
	}
	/**
	 * @param aptNumber The aptNumber to set.
	 */
	public void setAptNumber(String aptNumber) {
		this.aptNumber = aptNumber;
	}
	/**
	 * @return Returns the locationCd.
	 */
	public String getLocationCd() {
		fetch();
		return locationCd;
	}
	/**
	 * @param locationCd The locationCd to set.
	 */
	public void setLocationCd(String locationCd) {
		if (this.locationCd == null || !this.locationCd.equals(locationCd)) {
			markModified();
		}
		this.locationCd = locationCd;
	}
	
}