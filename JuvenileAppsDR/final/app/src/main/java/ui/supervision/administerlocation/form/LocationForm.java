/*
 * Created on Sep 28, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerlocation.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import messaging.address.AddressRequestEvent;
import messaging.administerlocation.CreateUpdateLocationEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.PhoneNumber;
import ui.common.UIUtil;
import ui.common.form.AddressValidationForm;
import ui.security.SecurityUIHelper;

/**
 * @author C_NRaveendran
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class LocationForm extends AddressValidationForm {

	private static Collection serviceTypeList = new ArrayList();

	private static Collection serviceProviderList = new ArrayList();

	private static Collection associatedSPList = new ArrayList();

	private static Collection locationStatusList = new ArrayList();
	
	private static Collection locationRegionList = new ArrayList();//for region of the location

	private static Collection stateList = new ArrayList();

	private static Collection streetTypeList = new ArrayList();

	private static Collection facilityTypeList = new ArrayList();

	private Collection serviceEventsList = new ArrayList();
	
	private Collection locationUnitList = new ArrayList();
	
	private Collection<LocationResponseEvent> locationUnitsAll = new ArrayList<LocationResponseEvent>();
	
	private Collection newLocUnitList = new ArrayList(); //used for creating new loc units.

	private String serviceProviderId;

	private String serviceProviderName;

	private String serviceTypeId;

	private String serviceType;

	private String facilityTypeId;

	private String facilityType;

	private String locationName;

	private String statusId;

	private String status;

	private String isInHouse;

	private String locationId;
	
	//private String tempLocation;
	
	private PhoneNumber locationFax = new PhoneNumber("");

	private PhoneNumber phoneNumber = new PhoneNumber("");

	private LocationAddress locationAddress = new LocationAddress();

	private List locationUnits;

	// The collection of locations that matched the search;
	private Collection locationSearchResults;

	// Total Search Results Count
	private int searchResultSize;

	private String action;
	
	private String misc;
	
	private String flag;
	
	private String oldLocationName; // for Location Validate while Update

	private String oldlocationCd; // for Location Validate while Update

	private String locationCd;

	private Boolean period; // for hiding and unhiding future events scheduled
	
	private String locationRegion; 	

	private String locationRegionId;
	
	private String secondaryAction="";
	private boolean update=false;
	private boolean delete=false;
	private String selectedValue="";
	private String agencyId="";
	private boolean activeLocationUnitExists = false;
	private String locationUnitId;
	private String locationUnitName;
	private String locationUnitStatusId;
	private String locationUnitStatus;
	private String isLocationUnitPage;
	
	
	// table for a locatin unit	


	private LocationUnit locUnit = new LocationUnit();

	public void clear() {
		serviceProviderId = "";
		serviceProviderName = "";
		serviceTypeId = "";
		statusId = "";		
		status = "";
		serviceType = "";
		locationName = "";
		locationFax.clear();
		phoneNumber.clear();
		isInHouse = "";
		facilityTypeId = "";
		facilityType = "";
		locationId = "";
		locationCd = "";
		locationUnits = new ArrayList();
		locationUnitsAll = new ArrayList<LocationResponseEvent>();
		locationAddress = null;
		locationRegion = "";
		locationRegionId = "";
		agencyId="";
		this.locationUnitId = null;
		this.locationUnitName = null;
		this.locationUnitStatusId = "";
		this.locationUnitStatus = "";
		this.isLocationUnitPage = null;
	}
	
	
	

	/**
	 * @return Returns the locUnit.
	 */
	public LocationUnit getLocUnit() {
		return locUnit;
	}

	/**
	 * @param locUnit
	 *            The locUnit to set.
	 */
	public void setLocUnit(LocationUnit locUnit) {
		this.locUnit = locUnit;
	}

	public static class LocationUnit {

		private String juvUnitCd = "";

		private String status = "";
		
		private String unitStatusId="";

		private String locFax = "";
		
		private String phone = "";		

		private String locationUnitName = "";
		
		private PhoneNumber locationFax = new PhoneNumber("");

		private PhoneNumber phoneNumber = new PhoneNumber("");
		
		private String juvLocationUnitId = "";
		private String maysiFlag;
		private String drugFlag;
		private String officerProfileFlag ="0";
		private String interviewCalFlag ="0"; 
		
		
		
		public String getOfficerProfileFlag()
		{
		    return officerProfileFlag;
		}


		public void setOfficerProfileFlag(String officerProfileFlag)
		{
		    this.officerProfileFlag = officerProfileFlag;
		}


		public String getInterviewCalFlag()
		{
		    return interviewCalFlag;
		}


		public void setInterviewCalFlag(String interviewCalFlag)
		{
		    this.interviewCalFlag = interviewCalFlag;
		}


		public String getMaysiFlag()
		{
		    return maysiFlag;
		}


		public void setMaysiFlag(String maysiFlag)
		{
		    this.maysiFlag = maysiFlag;
		}
		public String getDrugFlag()
		{
		    return drugFlag;
		}


		public void setDrugFlag(String drugFlag)
		{
		    this.drugFlag = drugFlag;
		}
		/**
		 * @return Returns the juvLocationUnitId.
		 */
		public String getJuvLocationUnitId() {
			return juvLocationUnitId;
		}
		/**
		 * @param juvLocationUnitId The juvLocationUnitId to set.
		 */
		public void setJuvLocationUnitId(String juvLocationUnitId) {
			this.juvLocationUnitId = juvLocationUnitId;
		}
		/**
		 * @return Returns the juvUnitCd.
		 */
		public String getJuvUnitCd() {
			return juvUnitCd;
		}

		/**
		 * @param juvUnitCd
		 *            The juvUnitCd to set.
		 */
		public void setJuvUnitCd(String juvUnitCd) {
			this.juvUnitCd = juvUnitCd;
		}

		/**
		 * @return Returns the locationUnitName.
		 */
		public String getLocationUnitName() {
			return locationUnitName;
		}

		/**
		 * @param locationUnitName
		 *            The locationUnitName to set.
		 */
		public void setLocationUnitName(String locationUnitName) {
			this.locationUnitName = locationUnitName;
		}
		
		/**
		 * @return the locFax
		 */
		public String getLocFax() {
			return locFax;
		}
		/**
		 * @param locFax the locFax to set
		 */
		public void setLocFax(String locFax) {
			this.locFax = locFax;
		}
		/**
		 * @return Returns the phone.
		 */
		public String getPhone() {
			return phone;
		}

		/**
		 * @param phone
		 *            The phone to set.
		 */
		public void setPhone(String phone) {
			this.phone = phone;
		}

		/**
		 * @return the locationFax
		 */
		public PhoneNumber getLocationFax() {
			return locationFax;
		}
		/**
		 * @param locationFax the locationFax to set
		 */
		public void setLocationFax(PhoneNumber locationFax) {
			this.locationFax = locationFax;
		}
		/**
		 * @return Returns the phoneNumber.
		 */
		public PhoneNumber getPhoneNumber() {
			return phoneNumber;
		}

		/**
		 * @param phoneNumber
		 *            The phoneNumber to set.
		 */
		public void setPhoneNumber(PhoneNumber phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		/**
		 * @return Returns the status.
		 */
		public String getStatus() {
			return status;
		}

		/**
		 * @param status
		 *            The status to set.
		 */
		public void setStatus(String status) {
			this.status = status;
		}
		
		/**
		 * @return Returns the unitStatusId.
		 */
		public String getUnitStatusId() {
			return unitStatusId;
		}
		/**
		 * @param unitStatusId The unitStatusId to set.
		 */
		public void setUnitStatusId(String unitStatusId) {
			this.unitStatusId = unitStatusId;
		}
	}
	
	public void clearLocationUnit() {
		this.getLocUnit().setJuvUnitCd("");
		this.getLocUnit().setLocationUnitName("");
		this.getLocUnit().setPhone("");
		this.getLocUnit().setPhoneNumber(new PhoneNumber(""));
		this.getLocUnit().setJuvLocationUnitId("");
		this.getLocUnit().setStatus("");
		//setLocUnit(new LocationUnit());
	}
	
	public void clearAddress() {
		setLocationAddress(new LocationAddress());
		setAddressStatus("");
	}

	/**
	 * @return Returns the serviceTypeList.
	 */
	public Collection getServiceTypeList() {
		return serviceTypeList;
	}

	/**
	 * @param serviceTypeList
	 *            The serviceTypeList to set.
	 */
	public void setServiceTypeList(Collection aServiceTypeList) {
		serviceTypeList = aServiceTypeList;
	}

	/**
	 * @return Returns the serviceProviderList.
	 */
	public Collection getServiceProviderList() {
		return serviceProviderList;
	}

	/**
	 * @param serviceProviderList
	 *            The serviceProviderList to set.
	 */
	public void setServiceProviderList(Collection aServiceProviderList) {
		serviceProviderList = aServiceProviderList;
	}

	/**
	 * @return Returns the locationStatusList.
	 */
	public Collection getLocationStatusList() {
		return locationStatusList;
	}

	/**
	 * @param locationStatusList
	 *            The locationStatusList to set.
	 */
	public void setLocationStatusList(Collection aLocationStatusList) {
		locationStatusList = aLocationStatusList;
	}

	
	/**
	 * Region List Collection
	 * @return
	 */
	public Collection getLocationRegionList() {
		return locationRegionList;
	}

	/**
	 * 
	 * @param aRegionList
	 */
	public void setLocationRegionList(Collection aLocationRegionList) {
		locationRegionList = aLocationRegionList;
	}
	
	
	/**
	 * @return Returns the stateList.
	 */
	public Collection getStateList() {
		return stateList;
	}

	/**
	 * @param stateList
	 *            The stateList to set.
	 */
	public void setStateList(Collection aStateList) {
		stateList = aStateList;
	}

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId
	 *            The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
		if (locationStatusList != null) {
			this.status = CodeHelper.getCodeDescriptionByCode(locationStatusList, statusId);
		}
	}
	
	public String getLocationUnitStatus() {
		return this.locationUnitStatus;
	}

	public void setLocationUnitStatus(String locationUnitStatus) {
		this.locationUnitStatus = locationUnitStatus;
	}
	
	public String getLocationUnitStatusId() {
		return locationUnitStatusId;
	}

	public void setLocationUnitStatusId(String locationUnitStatusId) {
		this.locationUnitStatusId = locationUnitStatusId;
		if (locationStatusList != null) {
		    this.locationUnitStatus = CodeHelper.getCodeDescriptionByCode(locationStatusList, locationUnitStatusId);
		}
	}
	
	public String getIsLocationUnitPage() {
		return this.isLocationUnitPage;
	}

	public void setIsLocationUnitPage(String isLocationUnitPage) {
		this.isLocationUnitPage = isLocationUnitPage;
	}

	/**
	 * 
	 * @return
	 */
	public String getLocationRegion() {
		return locationRegion;
	}
	
	
	/**
	 * 
	 * @param region
	 */
	public void setLocationRegion(String locationRegion) {
		this.locationRegion = locationRegion;
	}
	
	
	/**
	 * returns locationRegionId
	 * @return
	 */
	public String getLocationRegionId() {
		return locationRegionId;
	}

	/**
	 * 
	 * @param locationRegionId
	 */
	public void setLocationRegionId(String locationRegionId) {
		this.locationRegionId = locationRegionId;
		if (locationRegionList != null) {
			this.locationRegion = CodeHelper.getCodeDescriptionByCode(locationRegionList, locationRegionId);
		}
	}
	
	
	/**
	 * @return Returns the serviceProviderName.
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}

	/**
	 * @param serviceProviderName
	 *            The serviceProviderName to set.
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}

	/**
	 * @return Returns the serviceType.
	 */
	public String getServiceType() {
		return serviceType;
	}

	/**
	 * @param serviceType
	 *            The serviceType to set.
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * @return Returns the serviceTypeId.
	 */
	public String getServiceTypeId() {
		return serviceTypeId;
	}

	/**
	 * @param serviceTypeId
	 *            The serviceTypeId to set.
	 */
	public void setServiceTypeId(String serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	/**
	 * @return Returns the locationName.
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * @param locationName
	 *            The locationName to set.
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * @return Returns the serviceProviderId.
	 */
	public String getServiceProviderId() {
		return serviceProviderId;
	}

	/**
	 * @param serviceProviderId
	 *            The serviceProviderId to set.
	 */
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	/**
	 * @return Returns the locationSearchResults.
	 */
	public Collection getLocationSearchResults() {
		return locationSearchResults;
	}

	/**
	 * @param locationSearchResults
	 *            The locationSearchResults to set.
	 */
	public void setLocationSearchResults(Collection locationSearchResults) {
		this.locationSearchResults = locationSearchResults;
	}

	/**
	 * @return Returns the searchResultSize.
	 */
	public int getSearchResultSize() {
		return searchResultSize;
	}

	/**
	 * @param searchResultSize
	 *            The searchResultSize to set.
	 */
	public void setSearchResultSize(int searchResultSize) {
		this.searchResultSize = searchResultSize;
	}

	/**
	 * @return Returns the streetTypeList.
	 */
	public Collection getStreetTypeList() {
		return streetTypeList;
	}

	/**
	 * @param streetTypeList
	 *            The streetTypeList to set.
	 */
	public void setStreetTypeList(Collection aStreetTypeList) {
		streetTypeList = aStreetTypeList;
	}

	/**
	 * @return Returns the facilityTypeList.
	 */
	public Collection getFacilityTypeList() {
		return facilityTypeList;
	}

	/**
	 * @param facilityTypeList
	 *            The facilityTypeList to set.
	 */
	public void setFacilityTypeList(Collection aFacilityTypeList) {
		facilityTypeList = aFacilityTypeList;
	}

	public LocationAddress getLocationAddress() {
		return locationAddress;
	}

	/**
	 * @param address
	 */
	public void setLocationAddress(LocationAddress address) {
		locationAddress = address;

	}

	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return Returns the misc.
	 */
	public String getMisc() {
		return misc;
	}
	/**
	 * @param misc The misc to set.
	 */
	public void setMisc(String misc) {
		this.misc = misc;
	}
	/**
	 * @return Returns the facilityType.
	 */
	public String getFacilityType() {
		return facilityType;
	}

	/**
	 * @param facilityType
	 *            The facilityType to set.
	 */
	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	/**
	 * @return Returns the facilityTypeId.
	 */
	public String getFacilityTypeId() {
		return facilityTypeId;
	}

	/**
	 * @param facilityTypeId
	 *            The facilityTypeId to set.
	 */
	public void setFacilityTypeId(String facilityTypeId) {
		this.facilityTypeId = facilityTypeId;
		if (facilityTypeList != null) {
			facilityType = CodeHelper.getCodeDescriptionByCode(facilityTypeList, facilityTypeId);
		}
	}

	/**
	 * @return Returns the serviceEventsList.
	 */
	public Collection getServiceEventsList() {
		return serviceEventsList;
	}

	/**
	 * @param serviceEventsList
	 *            The serviceEventsList to set.
	 */
	public void setServiceEventsList(Collection aServiceEventsList) {
		serviceEventsList = aServiceEventsList;
	}

	/**
	 * @param lre
	 */
	public void setForm(LocationResponseEvent response) {
		this.setLocationName(response.getLocationName());
		this.setLocationCd(response.getLocationCd());
		this.setStatusId(response.getStatusId());
		this.setStatus(response.getStatus());
		this.setFacilityTypeId(response.getFacilityTypeId());
		this.setFacilityType(response.getFacilityType());
		this.setLocationId(response.getLocationId());
		this.setIsInHouse(response.getInHouse() ? "YES" : "NO");
		this.setPhoneNumber(response.getPhoneNumber());
		this.setLocationFax(response.getLocationFax());
		this.setLocationUnits(response.getLocationUnits());
		this.setAgencyId(response.getAgencyId());

		Address locationAddress = UIUtil.convertAddressResponseEvent(response.getLocationAddress());
		this.setLocationAddress(new LocationAddress(locationAddress));
		
		this.setServiceProviderName(response.getServiceProviderName()); 
		this.setLocationRegionId(response.getLocationRegionId());
		this.setLocationRegion(response.getLocationRegion());
		
		//this.setLocationUnitName(response.getJuvUnitCd());

	}

	/**
	 * @return Returns the isInHouse.
	 */
	public String getIsInHouse() {
		return isInHouse;
	}

	/**
	 * @param isInHouse
	 *            The isInHouse to set.
	 */
	public void setIsInHouse(String isInHouse) {
		this.isInHouse = isInHouse;
	}

	/**
	 * @return Returns the locationId.
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId
	 *            The locationId to set.
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	public String getLocationUnitId() {
		return locationUnitId;
	}

	public void setLocationUnitId(String locationUnitId) {
		this.locationUnitId = locationUnitId;
	}
	
	public String getLocationUnitName() {
		return locationUnitName;
	}

	public void setLocationUnitName(String locationUnitName) {
		this.locationUnitName = locationUnitName;
	}
	
	/**
	 * @param locationFax
	 *            The locationFax to set.
	 */
	public void setLocationFax(String locationFax ) {
		PhoneNumber fx = new PhoneNumber(locationFax);
		this.locationFax = fx;
	}

	/**
	 * @return Returns the phoneNumber.
	 */
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            The phoneNumber to set.
	 */
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber pn = new PhoneNumber(phoneNumber);
		this.phoneNumber = pn;
	}
	
	/**
	 * @return the locationFax
	 */
	public PhoneNumber getLocationFax() {
		return locationFax;
	}
	
	/**
	 * @return
	 */
	public CreateUpdateLocationEvent getCreateUpdateEvent() {
		CreateUpdateLocationEvent cule = new CreateUpdateLocationEvent();
		cule.setAgencyId(SecurityUIHelper.getUserAgencyId()); //changed vj.
		cule.setLocationName(locationName);
		cule.setLocationId(locationId);
		cule.setInHouse(isInHouse.equals("YES") ? true : false);
		cule.setFacilityTypeId(facilityTypeId);
		cule.setStatusId(statusId);
		cule.setLocationRegionId(locationRegionId);
		cule.setLocationFax(this.getLocationFax().getPhoneNumber());
		cule.setPhoneNumber(this.getPhoneNumber().getPhoneNumber());
		cule.setLocationCd(locationCd);
		// Address event
		AddressRequestEvent addressEvent = new AddressRequestEvent();

		addressEvent.setStreetNum(locationAddress.getStreetNumber());
		addressEvent.setStreetName(locationAddress.getStreetName());
		addressEvent.setCity(locationAddress.getCity());
		addressEvent.setStateId(locationAddress.getStateId());
		addressEvent.setZipCode(locationAddress.getZipCode());
		addressEvent.setAptNum(locationAddress.getAptNumber());
		addressEvent.setStreetTypeId(locationAddress.getStreetTypeId());
		addressEvent.setAdditionalZipCode(locationAddress.getAdditionalZipCode());
		addressEvent.setValidated(this.getAddressStatus());
		if (this.getAddressStatus().equals("")) {
			addressEvent.setValidated("U");
		}
		cule.addRequest(addressEvent);

		return cule;
	}

	/**
	 * @return Returns the oldLocationName.
	 */
	public String getOldLocationName() {
		return oldLocationName;
	}

	/**
	 * @param oldLocationName
	 *            The oldLocationName to set.
	 */
	public void setOldLocationName(String oldLocationName) {
		this.oldLocationName = oldLocationName;
	}

	/**
	 * @param phoneNumber
	 *            The phoneNumber to set.
	 */
	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @param locationFax the locationFax to set
	 */
	public void setLocationFax(PhoneNumber locationFax) {
		this.locationFax = locationFax;
	}

	public class LocationAddress extends Address {

		public LocationAddress(Address address) {
			this.setStreetNumber(address.getStreetNumber());
			this.setStreetName(address.getStreetName());
			this.setCity(address.getCity());
			this.setStateId(address.getStateId());
			this.setZipCode(address.getZipCode());
			this.setStreetTypeId(address.getStreetTypeId());
			this.setAdditionalZipCode(address.getAdditionalZipCode());
			this.setAptNumber(address.getAptNumber());
			this.setValidated(address.getValidated());
		}

		/**
		 *  
		 */
		public LocationAddress() {

		}

		public void setStreetTypeId(String streetTypeId) {
			super.setStreetTypeId(streetTypeId);
			if (streetTypeList != null && streetTypeList.size() > 0) {
				setStreetType(CodeHelper.getCodeDescriptionByCode(streetTypeList, streetTypeId));
			}
		}

		public void setStateId(String stateId) {
			super.setStateId(stateId);
			if (stateList != null && stateList.size() > 0) {
				setState(CodeHelper.getCodeDescriptionByCode(stateList, stateId));
			}
		}

		public String toString() {
			String addressString = this.getStreetNumber() + ", " + this.getStreetName() + this.getStreetTypeId() + ", " + this.getCity() + ", "
					+ this.getState() + ", " + this.getZipCode();
			return addressString;
		}
	}

	/**
	 * @return Returns the associatedSPList.
	 */
	public Collection getAssociatedSPList() {
		return associatedSPList;
	}

	/**
	 * @param associatedSPList
	 *            The associatedSPList to set.
	 */
	public void setAssociatedSPList(Collection aAssociatedSPList) {
		associatedSPList = aAssociatedSPList;
	}

	/**
	 * @return Returns the locationUnitList.
	 */
	public Collection getLocationUnitList() {
		return locationUnitList;
	}

	public void setLocationUnitsAll(Collection<LocationResponseEvent> locationUnitsAll) {
		this.locationUnitsAll = locationUnitsAll;
	}
	
	public Collection<LocationResponseEvent> getLocationUnitsAll() {
		return locationUnitsAll;
	}
	/**
	 * @param locationUnitList The locationUnitList to set.
	 */
	public void setLocationUnitList(Collection locationUnitList) {
		this.locationUnitList = locationUnitList;
	}
	
	/**
	 * @return Returns the locationUnits.
	 */
	public List getLocationUnits() {
		return locationUnits;
	}

	/**
	 * @param locationUnits
	 *            The locationUnits to set.
	 */
	public void setLocationUnits(List locationUnits) {
		this.locationUnits = locationUnits;
	}

	/**
	 * @return Returns the locationCd.
	 */
	public String getLocationCd() {
		return locationCd;
	}

	/**
	 * @param locationCd
	 *            The locationCd to set.
	 */
	public void setLocationCd(String locationCd) {
		this.locationCd = locationCd;
	}

	/**
	 * @return Returns the oldlocationCd.
	 */
	public String getOldlocationCd() {
		return oldlocationCd;
	}

	/**
	 * @param oldlocationCd
	 *            The oldlocationCd to set.
	 */
	public void setOldlocationCd(String oldlocationCd) {
		this.oldlocationCd = oldlocationCd;
	}

	/**
	 * @return Returns the period.
	 */
	public Boolean getPeriod() {
		return period;
	}

	/**
	 * @param period
	 *            The period to set.
	 */
	public void setPeriod(Boolean period) {
		this.period = period;
	}
	
	/**
	 * @return Returns the newLocUnitList.
	 */
	public Collection getNewLocUnitList() {
		return newLocUnitList;
	}
	/**
	 * @param newLocUnitList The newLocUnitList to set.
	 */
	public void setNewLocUnitList(Collection newLocUnitList) {
		this.newLocUnitList = newLocUnitList;
	}
	
	/**
	 * @return Returns the tempLocation.
	 *//*
	public String getTempLocation() {
		return tempLocation;
	}
	*//**
	 * @param tempLocation The tempLocation to set.
	 *//*
	public void setTempLocation(String tempLocation) {
		this.tempLocation = tempLocation;
	}*/
	//to clear form data.
	public void clearForm(){
		this.action = "";
		this.secondaryAction="";
		this.locationName="";
		this.isInHouse="";
		//this.locationId="";
		this.locationAddress = null;
		this.locationUnitList = new ArrayList();
		this.newLocUnitList = new ArrayList();
		this.locUnit.juvLocationUnitId = "";
		this.locUnit.juvUnitCd = "";
		this.locUnit.phone = "";
		this.locUnit.phoneNumber.clear();	
	}
	/**
	 * @return Returns the delete.
	 */
	public boolean isDelete() {
		return delete;
	}
	/**
	 * @param delete The delete to set.
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	/**
	 * @return Returns the secondaryAction.
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}
	/**
	 * @param secondaryAction The secondaryAction to set.
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}
	/**
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue() {
		return selectedValue;
	}
	/**
	 * @param selectedValue The selectedValue to set.
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	/**
	 * @return Returns the update.
	 */
	public boolean isUpdate() {
		return update;
	}
	/**
	 * @param update The update to set.
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}




	public String getAgencyId() {
		return agencyId;
	}




	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}




	public boolean isActiveLocationUnitExists() {
		return activeLocationUnitExists;
	}




	public void setActiveLocationUnitExists(boolean activeLocationUnitExists) {
		this.activeLocationUnitExists = activeLocationUnitExists;
	}
	
}
