/*
 * Created on Dec 28, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.csserviceprovider;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.persistence.Reference;
import pd.address.Address;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSServiceProvider extends PersistentObject 
{
    /************ CSServiceProvider Member Variables ***********************/
    private int serviceProviderId;
    private String serviceProviderName;
    private Date startDate;
    private boolean isInHouse;
    private String serviceProviderStatus;
    private Date statusChangeDate;
    private String ifasNumber;
    private String phoneNumber;
    private String extension;
    private String faxNumber;
    private String website;
    private String emailAddress;
    private String ftpSite;
    private String comments;
    private Address billingAddress;
    private String billingAddressId;
    private Address shippingAddress;
    private String shippingAddressId;
    private Collection programs;
    private Collection contacts;
    private boolean isFaithBased;
    
	//indicates whether or not service provider has a contract program
    private boolean hasContractProgram;
    
    /**** Attributes of CSSERVPROVPROG view that shows a service provider's programs *****/
    private String programId;
    private String programIdentifier;
    private String programName;
    private String programGroupCode;
    private String programTypeCode;
    private boolean isContractProgram;
    private String programStatusCode;
    private String programPrice;
    
    /**** Attributes of CSSERVPROVPROGLOC view that shows a service provider program's locations *****/
    
    private String referralTypeCd;
    
    private String programLocationId;
    private String locationId;
    private String regionCode;
    private String streetNumber;
    private String streetName;
    private String streetTypeCd;
    private String aptNum;
    private String city;
    private String state;
    private String zipCode;
    private String locationFax;
    private String locationPhone;
    private String validLocStatus;
    
    private String programLanguageId;
    private String programLanguage;
    private String sexSpecificCode;
    private String cstsCode;
    
	/**
	 * @return Returns the locationId.
	 */
	public String getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId The locationId to set.
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	/**
	 * @return Returns the regionCode.
	 */
	public String getRegionCode() {
		return regionCode;
	}
	/**
	 * @param regionCode The regionCode to set.
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
    /************ Member Variable Getters & Setters ***********************/

	/**
	* Initialize class relationship to class pd.address.Address
	*/
	private void initBillingAddress() 
	{
		if (billingAddress == null) 
		{		//create reference to billing address object
		    billingAddress = 
		        (Address)new Reference(billingAddressId, Address.class, 
		                (PersistentObject) this, "billingAddress").getObject();
		}
	}//end of initBillingAddress()
    
    /**
     * @return Returns the billingAddress.
     */
    public Address getBillingAddress() 
    {	
        fetch();
        initBillingAddress();
        return billingAddress;
    }
    
    /**
     * @param billingAddress The billingAddress to set.
     */
    public void setBillingAddress(Address billingAddress) 
    {        
        	//indicate that address is modified if new or changed from original
		if (this.billingAddress == null || !this.billingAddress.equals(billingAddress)) 
		{
			markModified();
		}
		
			//save address to database
		//billingAddress.setContext(this, "billingAddress");
		if (billingAddress.getOID() == null) 
		{
			(new mojo.km.persistence.Home()).bind(billingAddress);
		}
		
			//set properties of new address on service provider
		setBillingAddressId(billingAddress.getOID());
		this.billingAddress = 
		    (Address) new mojo.km.persistence.Reference(billingAddress).getObject();       
    }//end of setBillingAddress()
    
    /**
     * @return Returns the billingAddressId.
     */
    public String getBillingAddressId() {
        return billingAddressId;
    }
    /**
     * @param billingAddressId The billingAddressId to set.
     */
    public void setBillingAddressId(String billingAddressId) {
        this.billingAddressId = billingAddressId;
    }
    /**
     * @return Returns the shippingAddressId.
     */
    public String getShippingAddressId() {
        return shippingAddressId;
    }
    /**
     * @param shippingAddressId The shippingAddressId to set.
     */
    public void setShippingAddressId(String shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }
    /**
     * @return Returns the comments.
     */
    public String getComments() {
        return comments;
    }
    /**
     * @param comments The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    /**
     * @return Returns the contacts.
     */
    public Collection getContacts() 
    {

        //retrieve service provider from DB
		fetch();
		
			//initialize then return list of contact objects
		initContacts();
            
        return contacts;
    }
    /**
     * @param contacts The contacts to set.
     */
    public void setContacts(List contacts) {
        this.contacts = contacts;
    }
    /**
     * @return Returns the emailAddress.
     */
    public String getEmailAddress() {
        return emailAddress;
    }
    /**
     * @param emailAddress The emailAddress to set.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    /**
     * @return Returns the extension.
     */
    public String getExtension() {
        return extension;
    }
    /**
     * @param extension The extension to set.
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }
    /**
     * @return Returns the faxNumber.
     */
    public String getFaxNumber() {
        return faxNumber;
    }
    /**
     * @param faxNumber The faxNumber to set.
     */
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }
    /**
     * @return Returns the ftpSite.
     */
    public String getFtpSite() {
        return ftpSite;
    }
    /**
     * @param ftpSite The ftpSite to set.
     */
    public void setFtpSite(String ftpSite) {
        this.ftpSite = ftpSite;
    }
    /**
     * @return Returns the ifasNumber.
     */
    public String getIfasNumber() {
        return ifasNumber;
    }
    /**
     * @param ifasNumber The ifasNumber to set.
     */
    public void setIfasNumber(String ifasNumber) {
        this.ifasNumber = ifasNumber;
    }
    /**
     * @return Returns the isInHouse.
     */
    public boolean getIsInHouse() {
        return isInHouse;
    }
    /**
     * @param isInHouse The isInHouse to set.
     */
    public void setIsInHouse(boolean isInHouse) {
        this.isInHouse = isInHouse;
    }
    /**
     * @return Returns the phoneNumber.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * @param phoneNumber The phoneNumber to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    /**
	* Initialize list of program objects
	*/
	private void initPrograms() 
	{
		if (programs == null) 
		{
			if (this.getOID() == null) 
			{
			    	//save service provider to DB if not yet saved
				bind();
			}

				//allocate array of program objects
			programs = (mojo.km.persistence.ArrayList)
			    new mojo.km.persistence.ArrayList(CSProgram.class, 
			            								"serviceProviderId", String.valueOf(getServiceProviderId()));
		}
	}//end of initPrograms()

    /**
	* Initialize list of program contact objects
	*/
	private void initContacts() 
	{
		if (contacts == null) 
		{
			if (this.getOID() == null) 
			{
			    	//save service provider to DB if not yet saved
				bind();
			}

				//allocate array of contact objects
			contacts = (mojo.km.persistence.ArrayList)
			    new mojo.km.persistence.ArrayList(CSServiceProviderContact.class, 
			            								"serviceProviderId", String.valueOf(getServiceProviderId()));
		}
	}//end of initContacts()
    
	
    /**
     * @return Returns the programs.
     */
    public Collection getPrograms() 
    {
    		//retrieve service provider from DB
		fetch();
		
			//initialize then return list of program objects
		initPrograms();
    
        return programs;
    }
    
    /**
     * @param programs The programs to set.
     */
    public void setPrograms(List programs) {
        this.programs = programs;
    }
    /**
     * @return Returns the serviceProviderId.
     */
    public int getServiceProviderId() {
        return serviceProviderId;
    }
    /**
     * @param serviceProviderId The serviceProviderId to set.
     */
    public void setServiceProviderId(int serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }
    /**
     * @return Returns the serviceProviderName.
     */
    public String getServiceProviderName() {
        return serviceProviderName;
    }
    /**
     * @param serviceProviderName The serviceProviderName to set.
     */
    public void setServiceProviderName(String serviceProviderName) {
        this.serviceProviderName = serviceProviderName;
    }
    
	/**
	* Initialize class relationship to class pd.address.Address
	*/
	private void initShippingAddress() 
	{
		if (shippingAddress == null) 
		{		//create reference to shipping address object
		    shippingAddress = 
		        (Address)new Reference(shippingAddressId, Address.class, 
		                (PersistentObject) this, "shippingAddress").getObject();
		}
	}//end of initShippingAddress()    
	
    /**
     * @return Returns the shippingAddress.
     */
    public Address getShippingAddress() 
    {
        initShippingAddress();
        return shippingAddress;
    }
    
    /**
     * @param shippingAddress The shippingAddress to set.
     */
    public void setShippingAddress(Address shippingAddress) 
    {
    	//indicate that address is modified if new or changed from original
		if (this.shippingAddress == null || !this.shippingAddress.equals(shippingAddress)) 
		{
			markModified();
		}
		
			//save address to database
		//shippingAddress.setContext(this, "shippingAddress");
		if (shippingAddress.getOID() == null) 
		{
			(new mojo.km.persistence.Home()).bind(shippingAddress);
		}
		
			//set properties of new address on service provider
		setShippingAddressId(shippingAddress.getOID());
		this.shippingAddress = 
		    (Address) new mojo.km.persistence.Reference(shippingAddress).getObject();       
    }//end of setShippingAddress()
    
    /**
     * @return Returns the startDate.
     */
    public Date getStartDate() {
        return startDate;
    }
    /**
     * @param startDate The startDate to set.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    /**
     * @return Returns the serviceProviderStatus.
     */
    public String getServiceProviderStatus() {
        return serviceProviderStatus;
    }
    /**
     * @param statusCode The serviceProviderStatus to set.
     */
    public void setServiceProviderStatus(String serviceProviderStatus) {
        this.serviceProviderStatus = serviceProviderStatus;
    }
    /**
     * @return Returns the statusChangeDate.
     */
    public Date getStatusChangeDate() {
        return statusChangeDate;
    }
    /**
     * @param statusChangeDate The statusChangeDate to set.
     */
    public void setStatusChangeDate(Date statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }
    /**
     * @return Returns the website.
     */
    public String getWebsite() {
        return website;
    }
    /**
     * @param website The website to set.
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * @return Returns the isContractProgram.
     */
    public boolean getIsContractProgram() {
        return isContractProgram;
    }
    /**
     * @param isContractProgram The isContractProgram to set.
     */
    public void setIsContractProgram(boolean isContractProgram) {
        this.isContractProgram = isContractProgram;
    }
    /**
     * @return Returns the programGroupCode.
     */
    public String getProgramGroupCode() {
        return programGroupCode;
    }
    /**
     * @param programGroupCode The programGroupCode to set.
     */
    public void setProgramGroupCode(String programGroupCode) {
        this.programGroupCode = programGroupCode;
    }
    /**
     * @return Returns the programName.
     */
    public String getProgramName() {
        return programName;
    }
    /**
     * @param programName The programName to set.
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }
    /**
     * @return Returns the programIdentifier.
     */
    public String getProgramIdentifier() {
        return programIdentifier;
    }
    /**
     * @param programIdentifier The programIdentifier to set.
     */
    public void setProgramIdentifier(String programIdentifier) {
        this.programIdentifier = programIdentifier;
    }
    /**
     * @return Returns the programStatusCode.
     */
    public String getProgramStatusCode() {
        return programStatusCode;
    }
    /**
     * @param programStatusCode The programStatusCode to set.
     */
    public void setProgramStatusCode(String programStatusCode) {
        this.programStatusCode = programStatusCode;
    }
    /**
     * @return Returns the programTypeCode.
     */
    public String getProgramTypeCode() {
        return programTypeCode;
    }
    /**
     * @param programTypeCode The programTypeCode to set.
     */
    public void setProgramTypeCode(String programTypeCode) {
        this.programTypeCode = programTypeCode;
    }
    
    /************ CSServiceProvider Lookup Methods ***********************/

    /**
     * Find CSServiceProvider by OID
     */
	static public CSServiceProvider find(String serviceProviderId)
	{
	    	//initialize lookup objects
	    CSServiceProvider csServiceProvider = null;
		IHome home = new Home();

			//use delegate to locate given cs service provider entity
		csServiceProvider = (CSServiceProvider) home.find(serviceProviderId, CSServiceProvider.class);
		return csServiceProvider;
	}//end of find()
    
    /**
     * Find all CSServiceProvider objects
     */
	static public Iterator findAll()
	{
	    	//initialize lookup objects
	    IHome home = new Home();
	    
	    	//use delegate to locate all cs service provider objects
		Iterator iter = home.findAll(CSServiceProvider.class);
		return iter;
	}//end of findAll()
	
    /**
     * Find all CSServiceProvider objects matching the given event attributes
     */
	static public Iterator findAll(IEvent event)
	{
	    	//initialize lookup objects
		IHome home = new Home();
		
			//use delegate to lookup cs service providers matching the given event values
		return home.findAll(event, CSServiceProvider.class);
	}//end of findAll()

    /**
     * Find all CSServiceProvider objects matching the given event attributes
     */	
	static public Iterator findAll(String attrName, String attrValue) {
    		
	    	//initialize lookup objects
	    IHome home = new Home();
		Iterator csServiceProviders = null;
		
			//use delegate to lookup cs service providers with the given attribute/value matches
		csServiceProviders = home.findAll(attrName, attrValue, CSServiceProvider.class);
		return csServiceProviders;
	}
	
	/**
	 * Bind entity to database
	 *
	 */
    public void bind()
    {
        IHome home = new Home();
        home.bind(this);
        
        	//set service provider id equal to oid
        setServiceProviderId(Integer.parseInt(getOID()));
    }//end of bind()
	
        /**
         * @return Returns the hasContractProgram.
         */
        public boolean getHasContractProgram() {
            return hasContractProgram;
        }
        /**
         * @param hasContractProgram The hasContractProgram to set.
         */
        public void setHasContractProgram(boolean hasContractProgram) {
            this.hasContractProgram = hasContractProgram;
        }
	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return Returns the streetName.
	 */
	public String getStreetName() {
		return streetName;
	}
	/**
	 * @param streetName The streetName to set.
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	/**
	 * @return Returns the streetNumber.
	 */
	public String getStreetNumber() {
		return streetNumber;
	}
	/**
	 * @param streetNumber The streetNumber to set.
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	/**
	 * @return Returns the zipCode.
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode The zipCode to set.
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the validLocStatus
	 */
	public String getValidLocStatus() {
		return validLocStatus;
	}
	/**
	 * @param validLocStatus the validLocStatus to set
	 */
	public void setValidLocStatus(String validLocStatus) {
		this.validLocStatus = validLocStatus;
	}
	/**
	 * @return Returns the cstsCode.
	 */
	public String getCstsCode() {
		return cstsCode;
	}
	/**
	 * @param cstsCode The cstsCode to set.
	 */
	public void setCstsCode(String cstsCode) {
		this.cstsCode = cstsCode;
	}
	/**
	 * @return Returns the programLanguage.
	 */
	public String getProgramLanguage() {
		return programLanguage;
	}
	/**
	 * @param programLanguage The programLanguage to set.
	 */
	public void setProgramLanguage(String programLanguage) {
		this.programLanguage = programLanguage;
	}
	/**
	 * @return Returns the sexSpecificCode.
	 */
	public String getSexSpecificCode() {
		return sexSpecificCode;
	}
	/**
	 * @param sexSpecificCode The sexSpecificCode to set.
	 */
	public void setSexSpecificCode(String sexSpecificCode) {
		this.sexSpecificCode = sexSpecificCode;
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
		this.locationFax = locationFax;
	}
	/**
	 * @return Returns the locationPhone.
	 */
	public String getLocationPhone() {
		return locationPhone;
	}
	/**
	 * @param locationPhone The locationPhone to set.
	 */
	public void setLocationPhone(String locationPhone) {
		this.locationPhone = locationPhone;
	}
	/**
	 * @return Returns the programId.
	 */
	public String getProgramId() {
		return programId;
	}
	/**
	 * @param programId The programId to set.
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	/**
	 * @return the programLanguageId
	 */
	public String getProgramLanguageId() {
		return programLanguageId;
	}
	/**
	 * @param programLanguageId the programLanguageId to set
	 */
	public void setProgramLanguageId(String programLanguageId) {
		this.programLanguageId = programLanguageId;
	}
	/**
	 * @return the streetTypeCd
	 */
	public String getStreetTypeCd() {
		return streetTypeCd;
	}
	/**
	 * @param streetTypeCd the streetTypeCd to set
	 */
	public void setStreetTypeCd(String streetTypeCd) {
		this.streetTypeCd = streetTypeCd;
	}
	/**
	 * @return the aptNum
	 */
	public String getAptNum() {
		return aptNum;
	}
	/**
	 * @param aptNum the aptNum to set
	 */
	public void setAptNum(String aptNum) {
		this.aptNum = aptNum;
	}
	/**
	 * @return the referralTypeCd
	 */
	public String getReferralTypeCd() {
		return referralTypeCd;
	}
	/**
	 * @param referralTypeCd the referralTypeCd to set
	 */
	public void setReferralTypeCd(String referralTypeCd) {
		this.referralTypeCd = referralTypeCd;
	}

	public boolean getIsFaithBased() {
		return isFaithBased;
	}
	public void setIsFaithBased(boolean isFaithBased) {
		this.isFaithBased = isFaithBased;
	}
	/**
	 * @return the programPrice
	 */
	public String getProgramPrice() {
		return programPrice;
	}
	/**
	 * @param programPrice the programPrice to set
	 */
	public void setProgramPrice(String programPrice) {
		this.programPrice = programPrice;
	}
	/**
	 * @return the programLocationId
	 */
	public String getProgramLocationId() {
		return programLocationId;
	}
	/**
	 * @param programLocationId the programLocationId to set
	 */
	public void setProgramLocationId(String programLocationId) {
		this.programLocationId = programLocationId;
	}
	
}//end of CSServiceProviderClass
