/*
 * Created on Jun 16, 2005
 *
 */
package ui.juvenilecase.form;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import pd.juvenile.JuvenileHelper;

import naming.PDCodeTableConstants;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.to.Address;
import messaging.juvenile.reply.JuvenileContactResponseEvent;
import mojo.km.utilities.DateUtil;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import ui.common.form.AddressValidationForm;

/**
 * @author asrvastava
 *  
 */
public class JuvenileContactForm extends AddressValidationForm
{
    private String contactNum;

    private String addressTypeId;

    private String addressType;

    private String agencyNameId;

    private String agencyName;

    private String apartmentNum;

    private PhoneNumber cellPhone = new PhoneNumber("");

    private String countyId;

    private String county;

    private String city;
    
    private String phonePriorityInd="";

    private String currentAgencyInvolvement;
    private String currentAgencyInvolvementId;
    private String previousAgencyInvolvementId;

    private String eMail;

    private String entryDate;

    private PhoneNumber fax = new PhoneNumber("");

    private Name contactName = new Name();

    private String juvenileNum;

    private String pager;

    private String previousAgencyInvolvement;

    private String relationshipId;

    private String relationship;

    private String stateId;

    private String state;

    private String streetName;

    private String streetNum;

    private String streetTypeId;

    private String streetType;
   
    private String streetNumSuffixId;

    private String streetNumSuffix;
    
    private String address;
    
    private String validated;

    private PhoneNumber workPhone = new PhoneNumber("");

    private String zipCode;

    private String additionalZipCode;

    private String action;
    private String secondaryAction;

    private String titleId;
    
    //the following are added for Task#45932, US 43115 /Detention Visit
    
    private boolean detentionVisit;
    private boolean ageOver21;
    private String driverLicenseNum = "";
    private String driverLicenseState = "";
    private String driverLicenseStateId = "";
    private String driverLicenseClass = "";
    private String driverLicenseClassId = "";
    private String driverLicenseExpirationDate;
    private String issuedByState = "";
    private String issuedByStateId = "";
    private String stateIssuedIdNum = "";
    private String passportNum = ""; 
    private String countryOfIssuance = ""; 
    private String countryOfIssuanceId = ""; 
    private String passportExpirationDate; 
    private static List emptyColl = new ArrayList();
    private static List countryOfIssuanceList = emptyColl; 
    private static List driversLicenseClassList = emptyColl;
    private int detVisitContactsCount;
    private boolean daVisit = false;
    private boolean visitorCapRemoved = false;
    private String contactMemberComments;

    // collections
    private Collection contacts = null;
    

    static private final String DATE_FORMAT_1 = "MM/dd/yyyy";

    public String getStreetNumber()
    {
        return getStreetNum();
    }

    public void setContactProperties(JuvenileContactResponseEvent responseEvent) throws Exception
    {

    	this.setPhonePriorityInd(responseEvent.getPhonePriorityInd());
        this.setContactNum(responseEvent.getContactNum());
        this.setAddressTypeId(responseEvent.getAddressTypeId());
        this.setAddressType(responseEvent.getAddressType());
        this.setAgencyName(responseEvent.getAgencyName());
        this.setCity(responseEvent.getCity());
        this.setCounty(responseEvent.getCounty());
        this.setCountyId(responseEvent.getCountyId());
        this.setRelationshipId(responseEvent.getRelationshipId());
        this.setRelationship(responseEvent.getRelationship());
        this.setStateId(responseEvent.getStateId());
        this.setState(responseEvent.getState());
        this.setStreetType(responseEvent.getStreetType());
        this.setStreetTypeId(responseEvent.getStreetTypeId());
        this.setJuvenileNum(responseEvent.getJuvenileNum());
        this.setApartmentNum(responseEvent.getApartmentNum());
        this.setCellPhone(new PhoneNumber(responseEvent.getCellPhone()));
        this.setCurrentAgencyInvolvementId(responseEvent.getCurrentAgencyInvolvementId());
        this.setEMail(responseEvent.getEMail());
        this.setFax(new PhoneNumber(responseEvent.getFax()));
        this.setPreviousAgencyInvolvementId(responseEvent.getPreviousAgencyInvolvementId());
        this.setStreetName(responseEvent.getStreetName());
        this.setStreetNum(responseEvent.getStreetNum());
        this.setStreetNumSuffix(responseEvent.getStreetNumSuffix());
        this.setStreetNumSuffixId(responseEvent.getStreetNumSuffixId());
        this.setWorkPhone(new PhoneNumber(responseEvent.getWorkPhone()));
        this.setWorkExtension(responseEvent.getWorkPhoneExtn());
        this.setZipCode(responseEvent.getZipCode());
        this.setAdditionalZipCode(responseEvent.getAdditionalZipCode());
        this.setValidated(responseEvent.getValidated());
        
        this.setContactName(new Name(responseEvent.getFirstName(), responseEvent.getMiddleName(), responseEvent
                .getLastName(), responseEvent.getTitle()));

        /*
         * get date from response event and setting it on form to display
         * MM/dd/yyyy
         */
        Date entryDate = responseEvent.getEntryDate();
        if (entryDate != null)
        {
            String myDate = DateUtil.dateToString(entryDate, DATE_FORMAT_1);
            this.setEntryDate(myDate);
        }
        this.setDisplayAddress(responseEvent);
        this.setAgeOver21(responseEvent.isAgeOver21());
        this.setDetentionVisit(responseEvent.isDetentionVisit());
        this.setDriverLicenseNum(responseEvent.getDriverLicenseNum());
        this.setDriverLicenseState(responseEvent.getDriverLicenseState());
        this.setDriverLicenseStateId(responseEvent.getDriverLicenseStateId());
        this.setDriverLicenseClass(responseEvent.getDriverLicenseClass());
        this.setDriverLicenseClassId(responseEvent.getDriverLicenseClassId());
        String expiryDate = responseEvent.getDriverLicenseExpirationDate();
        if (expiryDate != null){
           this.setDriverLicenseExpirationDate(JuvenileHelper.dateConvert(expiryDate));
        }
        this.setIssuedByState(responseEvent.getIssuedByState());
        this.setIssuedByStateId(responseEvent.getIssuedByStateId());
        this.setStateIssuedIdNum(responseEvent.getStateIssuedIdNum());
        this.setPassportNum(responseEvent.getPassportNum());
        this.setCountryOfIssuance(responseEvent.getCountryOfIssuance());
        this.setCountryOfIssuanceId(responseEvent.getCountryOfIssuanceId());
        String passExpiryDate = responseEvent.getPassportExpirationDate();
        if (passExpiryDate != null){
            this.setPassportExpirationDate(JuvenileHelper.dateConvert(passExpiryDate));
        }
        this.contactMemberComments = responseEvent.getContactMemberComments();
    }

    /**
     * Clear
     */
    public void clear()
    {
    	phonePriorityInd="";
    	
        contactNum = "";
        addressTypeId = "";
        addressType = "";
        agencyNameId = "";
        agencyName = "";
        apartmentNum = "";
        cellPhone.clear();
        countyId = "";
        county = "";
        city = "";
        currentAgencyInvolvementId="";
        previousAgencyInvolvementId="";
        currentAgencyInvolvement = "";
        eMail = "";
        titleId = "";
        entryDate = "";
        fax.clear();
        pager = "";
        previousAgencyInvolvement = "";
        relationshipId = "";
        relationship = "";
        stateId = "";
        state = "";
        streetName = "";
        address = "";
        streetNum = "";
        streetTypeId = "";
        streetType = "";
        workPhone.clear();
        workPhone.setExt("");
        zipCode = "";
        additionalZipCode = "";
        contactName.clear();
        setAddressStatus("");
        secondaryAction="";
        validated = "";
        streetNumSuffixId = "";
        streetNumSuffix = "";
        countryOfIssuanceId = ""; //added for Detention Visit details
        countryOfIssuance = ""; 
        detentionVisit = false;
        ageOver21 = false;
        driverLicenseNum = "";
        driverLicenseState = "";
        driverLicenseStateId = "";
        driverLicenseClass = "";
        driverLicenseExpirationDate = "";
        issuedByState = "";
        issuedByStateId = "";
        stateIssuedIdNum = "";
        passportNum = "";
        countryOfIssuance = "";
        countryOfIssuanceId = "";
        passportExpirationDate = "";
        driverLicenseClassId = "";
        daVisit = false;
        visitorCapRemoved = false;
        detVisitContactsCount = 0;
        contactMemberComments = "";
        
    }

    /**
     * @return
     */
    public String getAdditionalZipCode()
    {
        return additionalZipCode;
    }

    /**
     * @return
     */
    public String getAddressType()
    {
        return addressType;
    }

    /**
     * @return
     */
    public String getAddressTypeId()
    {
        return addressTypeId;
    }

    /**
     * @return
     */
    public String getAgencyName()
    {
        return agencyName;
    }

    /**
     * @return
     */
    public String getAgencyNameId()
    {
        return agencyNameId;
    }

    /**
     * @return
     */
    public String getApartmentNum()
    {
        return apartmentNum;
    }

    /**
     * @return
     */
    public String getCity()
    {
        return city;
    }

    /**
     * @return
     */
    public String getCounty()
    {
        return county;
    }

    /**
     * @return
     */
    public String getCountyId()
    {
        return countyId;
    }

    /**
     * @return
     */
    public String getCurrentAgencyInvolvement()
    {
        return currentAgencyInvolvement;
    }

    /**
     * @return
     */
    public String getEMail()
    {
        return eMail;
    }

    /**
     * @return
     */
    public String getEntryDate()
    {
        return entryDate;
    }

    /**
     * @return
     */
    public String getJuvenileNum()
    {
        return juvenileNum;
    }

    /**
     * @return
     */
    public String getPager()
    {
        return pager;
    }

    /**
     * @return
     */
    public String getPreviousAgencyInvolvement()
    {
        return previousAgencyInvolvement;
    }

    /**
     * @return
     */
    public String getRelationship()
    {
        return relationship;
    }

    /**
     * @return
     */
    public String getRelationshipId()
    {
        return relationshipId;
    }

    /**
     * @return
     */
    public String getState()
    {
        return state;
    }

    /**
     * @return
     */
    public String getStateId()
    {
        return stateId;
    }

    /**
     * @return
     */
    public String getStreetName()
    {
        return streetName;
    }

    /**
     * @return
     */
    public String getStreetNum()
    {
        return streetNum;
    }
    /**
     * @return
     */
    public String getStreetNumSuffix()
    {
        return streetNumSuffix;
    }

    /**
     * @return
     */
    public String getStreetNumSuffixId()
    {
        return streetNumSuffixId;
    }

    /**
     * @return
     */
    public String getStreetType()
    {
        return streetType;
    }

    /**
     * @return
     */
    public String getStreetTypeId()
    {
        return streetTypeId;
    }

    /**
     * @return
     */
    public String getZipCode()
    {
        return zipCode;
    }

    /**
     * @param string
     */
    public void setAdditionalZipCode(String string)
    {
        additionalZipCode = string;
    }

    /**
     * @param string
     */
    public void setAddressType(String string)
    {
        addressType = string;
    }

    /**
     * @param string
     */
    public void setAddressTypeId(String string)
    {
        addressTypeId = string;
    }

    /**
     * @param string
     */
    public void setAgencyName(String string)
    {
        agencyName = string;
    }

    /**
     * @param string
     */
    public void setAgencyNameId(String string)
    {
        agencyNameId = string;
    }

    /**
     * @param string
     */
    public void setApartmentNum(String string)
    {
        apartmentNum = string;
    }

    /**
     * @param string
     */
    public void setCity(String string)
    {
        city = string;
    }

    /**
     * @param string
     */
    public void setCounty(String string)
    {
        county = string;
    }

    /**
     * @param string
     */
    public void setCountyId(String string)
    {
        countyId = string;
    }

    /**
     * @param b
     */
    public void setCurrentAgencyInvolvement(String b)
    {
        currentAgencyInvolvement = b;
    }

   

    /**
     * @param string
     */
    public void setEMail(String string)
    {
        eMail = string;
    }

    /**
     * @param string
     */
    public void setEntryDate(String string)
    {
        entryDate = string;
    }

    /**
     * @param string
     */
    public void setJuvenileNum(String string)
    {
        juvenileNum = string;
    }

    /**
     * @param string
     */
    public void setPager(String string)
    {
        pager = string;
    }

    /**
     * @param b
     */
    public void setPreviousAgencyInvolvement(String b)
    {
        previousAgencyInvolvement = b;
    }

    
    /**
     * @param string
     */
    public void setRelationship(String string)
    {
        relationship = string;
    }

    /**
     * @param string
     */
    public void setRelationshipId(String string)
    {
        relationshipId = string;
    }

    /**
     * @param string
     */
    public void setState(String string)
    {
        state = string;
    }

    /**
     * @param string
     */
    public void setStateId(String string)
    {
        stateId = string;
    }

    /**
     * @param string
     */
    public void setStreetName(String string)
    {
        streetName = string;
    }

    /**
     * @param string
     */
    public void setStreetNum(String string)
    {
        streetNum = string;
    }

    /**
     * @param string
     */
    public void setStreetType(String string)
    {
        streetType = string;
    }
    /**
     * @param string
     */
    public void setStreetTypeId(String string)
    {
        streetTypeId = string;
    }

    /**
     * @param string
     */
    public void setStreetNumSuffixId(String string)
    {
        streetNumSuffixId = string;
    }
    /**
     * @param string
     */
    public void setStreetNumSuffix(String string)
    {
        streetNumSuffix = string;
    }


    /**
     * @param string
     */
    public void setZipCode(String string)
    {
        zipCode = string;
    }

    /**
     * @return
     */
    public Collection getContacts()
    {
        return contacts;
    }

    /**
     * @param collection
     */
    public void setContacts(Collection collection)
    {
        contacts = collection;
    }

    /**
     * @return
     */
    public String getContactNum()
    {
        return contactNum;
    }

    /**
     * @param string
     */
    public void setContactNum(String string)
    {
        contactNum = string;
    }

    /**
     * @return
     */
    public Name getContactName()
    {
        return contactName;
    }

    /**
     * @return
     */
    public String getFormattedContactName()
    {
        return contactName.getFormattedName();
    }

    /**
     * @param name
     */
    public void setContactName(Name name)
    {
        contactName = name;
    }

    /**
     * @return
     */
    public PhoneNumber getCellPhone()
    {
        return cellPhone;
    }

    /**
     * @return
     */
    public PhoneNumber getFax()
    {
        return fax;
    }

    /**
     * @return
     */
    public PhoneNumber getWorkPhone()
    {
        return workPhone;
    }


    /**
     * @return
     */
    public String getWorkExtension()
    {
        return workPhone.getExt() ;
    }

    /**
     * @return
     */
    public void setWorkExtension(String extension)
    {
        workPhone.setExt(extension) ;
    }


    /**
     * @param number
     */
    public void setCellPhone(PhoneNumber number)
    {
        cellPhone = number;
    }

    /**
     * @param number
     */
    public void setFax(PhoneNumber number)
    {
        fax = number;
    }

    /**
     * @param number
     */
    public void setWorkPhone(PhoneNumber number)
    {
        workPhone = number;
    }

    public Collection getPrefixes()
    {
        return CodeHelper.getPrefixes();
    }

    public Collection getRelationships()
    {
        return CodeHelper.getContactRelationshipsToJuvenile();
    }

    public Collection getJuvenileAgencies()
    {
        return CodeHelper.getJuvenileAgencies();
    }

    public Collection getStreetTypes()
    {
        return CodeHelper.getStreetTypeCodes();
    }

    public Collection getStates()
    {
        return CodeHelper.getStateCodes();
    }

    public Collection getCountries()
    {
        return CodeHelper.getNationalityCodes();
    }
    
    public Collection getdriversLicenseClass()
    {
        return CodeHelper.getDriverLicenseClassCodes(true);
    }
    
    public Collection getAddressTypes()
    {
        return CodeHelper.getAddressTypeCodes();
    }

    public Collection getCounties()
    {
        return CodeHelper.getCountyCodes();
    }

    public Collection getStreetNumSuffixes()
    {
        return CodeHelper.getStreetNumSuffixCodes();
    }

    /**
     * Sets the descriptions of the dropdown codes to be displayed on the
     * summary page.
     */
    public void processCodeDescriptions()
    {
        CodeResponseEvent evt;
        if (titleId != null && !titleId.equals(""))
        {
            evt = UIUtil.findCodeResponseEvent(getPrefixes().iterator(), titleId);
            contactName.setPrefix(evt.getDescription());
        }
        if (relationshipId != null && !relationshipId.equals(""))
        {
            evt = UIUtil.findCodeResponseEvent(getRelationships().iterator(), relationshipId);
            setRelationship(evt.getDescription());
        }
        currentAgencyInvolvement="";
        if (currentAgencyInvolvementId != null && !currentAgencyInvolvementId.equals(""))
        {
            evt = UIUtil.findCodeResponseEvent(getJuvenileAgencies().iterator(), currentAgencyInvolvementId);
            setCurrentAgencyInvolvement(evt.getDescription());
        }
        previousAgencyInvolvement="";
        if (previousAgencyInvolvementId != null && !previousAgencyInvolvementId.equals(""))
        {
            evt = UIUtil.findCodeResponseEvent(getJuvenileAgencies().iterator(), previousAgencyInvolvementId);
            setPreviousAgencyInvolvement(evt.getDescription());
        }
        if (streetTypeId != null && !streetTypeId.equals(""))
        {
            evt = UIUtil.findCodeResponseEvent(getStreetTypes().iterator(), streetTypeId);
            setStreetType(evt.getDescription());
        }else if (streetTypeId.equals("")){
        	setStreetType("");
        }
        if (stateId != null && !stateId.equals(""))
        {
            evt = UIUtil.findCodeResponseEvent(getStates().iterator(), stateId);
            setState(evt.getDescription());
        }
        if (driverLicenseStateId != null && !driverLicenseStateId.equals(""))
        {
            evt = UIUtil.findCodeResponseEvent(getStates().iterator(), driverLicenseStateId);
            setDriverLicenseState(evt.getDescription());
        }else if (driverLicenseStateId.equals("")){
            setDriverLicenseState("");
        }
        if (issuedByStateId != null && !issuedByStateId.equals(""))
        {
            evt = UIUtil.findCodeResponseEvent(getStates().iterator(), issuedByStateId);
            setIssuedByState(evt.getDescription());
        }else if (issuedByStateId.equals("")){
            setIssuedByState("");
        }
        
        if (countryOfIssuanceId != null && !countryOfIssuanceId.equals(""))
        {
            evt = UIUtil.findCodeResponseEvent(getCountries().iterator(), countryOfIssuanceId);
            setCountryOfIssuance(evt.getDescription());
        }else if (countryOfIssuanceId.equals("")){
            setCountryOfIssuance("");
        }
        
        if (driverLicenseClassId != null && !driverLicenseClassId.equals(""))
        {
            evt = UIUtil.findCodeResponseEvent(getdriversLicenseClass().iterator(), driverLicenseClassId);
            setDriverLicenseClass(evt.getDescription());
        }else if (driverLicenseClassId.equals("")){
            setDriverLicenseClass("");
        }
        if (addressTypeId != null && !addressTypeId.equals(""))
        {
            evt = UIUtil.findCodeResponseEvent(getAddressTypes().iterator(), addressTypeId);
            setAddressType(evt.getDescription());
        }else if (addressTypeId.equals("")){
        	setAddressType("");
        }
        if (countyId != null && !countyId.equals(""))
        {
            evt = UIUtil.findCodeResponseEvent(getCounties().iterator(), countyId);
            setCounty(evt.getDescription());
        }else if (countyId.equals("")){
        	setCounty("");
        }
        if (streetNumSuffixId != null && !streetNumSuffixId.equals(""))
        {
            evt = UIUtil.findCodeResponseEvent(getStreetNumSuffixes().iterator(), streetNumSuffixId);
            setStreetNumSuffix(evt.getDescription());
        }else if (streetNumSuffixId.equals("")){
        	setStreetNumSuffix("");
        }

    }

    /**
     * @return
     */
    public String getAction()
    {
        return action;
    }

    /**
     * @param string
     */
    public void setAction(String string)
    {
        action = string;
    }

    /**
     * @return
     */
    public String getTitleId()
    {
        return titleId;
    }

    /**
     * @param string
     */
    public void setTitleId(String string)
    {
        titleId = string;
    }

	/**
	 * @return Returns the currentAgencyInvolvementId.
	 */
	public String getCurrentAgencyInvolvementId() {
		return currentAgencyInvolvementId;
	}
	/**
	 * @param currentAgencyInvolvementId The currentAgencyInvolvementId to set.
	 */
	public void setCurrentAgencyInvolvementId(String currentAgencyInvolvementId) {
		this.currentAgencyInvolvementId = currentAgencyInvolvementId;
		  currentAgencyInvolvement="";
	        if (currentAgencyInvolvementId != null && !currentAgencyInvolvementId.equals(""))
	        {
	            String desc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_AGENCY, currentAgencyInvolvementId);
	            setCurrentAgencyInvolvement(desc);
	        }
	        
	}
	/**
	 * @return Returns the previousAgencyInvolvementId.
	 */
	public String getPreviousAgencyInvolvementId() {
		return previousAgencyInvolvementId;
	}
	/**
	 * @param previousAgencyInvolvementId The previousAgencyInvolvementId to set.
	 */
	public void setPreviousAgencyInvolvementId(String previousAgencyInvolvementId) {
		this.previousAgencyInvolvementId = previousAgencyInvolvementId;
		previousAgencyInvolvement="";
        if (previousAgencyInvolvementId != null && !previousAgencyInvolvementId.equals(""))
        {
            String desc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_AGENCY, previousAgencyInvolvementId);
            setPreviousAgencyInvolvement(desc);
        }
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
	 * @return Returns the phonePriorityInd.
	 */
	public String getPhonePriorityInd() {
		return phonePriorityInd;
	}
	/**
	 * @param phonePriorityInd The phonePriorityInd to set.
	 */
	public void setPhonePriorityInd(String phonePriorityInd) {
		this.phonePriorityInd = phonePriorityInd;
	}
	/**
	 * @return Returns the address.
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address The address to set.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	   private void setDisplayAddress(JuvenileContactResponseEvent jcForm) {
		Address address = new Address();
		address.setStreetNum(jcForm.getStreetNum());
		address.setStreetNumSuffix(jcForm.getStreetNumSuffix());
		address.setStreetName(jcForm.getStreetName());
		address.setStreetType(jcForm.getStreetType());
		address.setAptNum(jcForm.getApartmentNum());
		address.setCity(jcForm.getCity());
		address.setState(jcForm.getState());
		address.setZipCode(jcForm.getZipCode());
		address.setAdditionalZipCode(jcForm.getAdditionalZipCode());
		if (!"".equals(jcForm.getStreetTypeId()) && jcForm.getStreetTypeId() != null) {
			address.setStreetName(jcForm.getStreetName() + " " + jcForm.getStreetTypeId());
		}
 		String add = address.getStreetAddress() + ", " + address.getCityStateZip();
   		if (add.trim().equals(",")) {
   			this.setAddress("");
   		}
   		else {
   			this.setAddress(add);
   		}
	   }

	public String getValidated() {
		return validated;
	}

	public void setValidated(String validated) {
		this.validated = validated;
	}

	public boolean isDetentionVisit() {
	    return detentionVisit;
	}

	public void setDetentionVisit(boolean detentionVisit) {
	    this.detentionVisit = detentionVisit;
	}

	public boolean isAgeOver21() {
	    return ageOver21;
	}

	public void setAgeOver21(boolean ageOver21) {
	    this.ageOver21 = ageOver21;
	}

	public String getDriverLicenseNum() {
	    return driverLicenseNum;
	}

	public void setDriverLicenseNum(String driverLicenseNum) {
	    this.driverLicenseNum = driverLicenseNum;
	}

	public String getDriverLicenseState() {
	    return driverLicenseState;
	}

	public void setDriverLicenseState(String driverLicenseState) {
	    this.driverLicenseState = driverLicenseState;
	}

	public String getDriverLicenseStateId() {
	    return driverLicenseStateId;
	}

	public void setDriverLicenseStateId(String driverLicenseStateId) {
	    this.driverLicenseStateId = driverLicenseStateId;
	}

	public String getDriverLicenseClass() {
	    return driverLicenseClass;
	}

	public void setDriverLicenseClass(String driverLicenseClass) {
	    this.driverLicenseClass = driverLicenseClass;
	}

	public String getDriverLicenseExpirationDate() {
	    return driverLicenseExpirationDate;
	}

	public void setDriverLicenseExpirationDate(String driverLicenseExpirationDate) {
	    this.driverLicenseExpirationDate = driverLicenseExpirationDate;
	}

	public String getIssuedByState() {
	    return issuedByState;
	}

	public void setIssuedByState(String issuedByState) {
	    this.issuedByState = issuedByState;
	}

	public String getIssuedByStateId() {
	    return issuedByStateId;
	}

	public void setIssuedByStateId(String issuedByStateId) {
	    this.issuedByStateId = issuedByStateId;
	}

	public String getStateIssuedIdNum() {
	    return stateIssuedIdNum;
	}

	public void setStateIssuedIdNum(String stateIssuedIdNum) {
	    this.stateIssuedIdNum = stateIssuedIdNum;
	}

	public String getPassportNum() {
	    return passportNum;
	}

	public void setPassportNum(String passportNum) {
	    this.passportNum = passportNum;
	}

	public String getPassportExpirationDate() {
	    return passportExpirationDate;
	}

	public void setPassportExpirationDate(String passportExpirationDate) {
	    this.passportExpirationDate = passportExpirationDate;
	}
	public String getCountryOfIssuance(){
	    return countryOfIssuance;
	}
		
	public String getCountryOfIssuanceId()	{
	    return countryOfIssuanceId;
	}	
	public List getCountryOfIssuanceList()
	{
	    return countryOfIssuanceList;
	}
		
	public void setCountryOfIssuance(String string)
	{
	    countryOfIssuance = string;
	}	
		
	public void setCountryOfIssuanceId(String string)
	{
	    countryOfIssuanceId= string;
	}
	public void setCountryOfIssuanceList(List collection)
	   {
	    	countryOfIssuanceList = collection;
	   }	
	public String getDriverLicenseClassId()
	    {
	        return driverLicenseClassId;
	    }
		
	public void setDriverLicenseClassId(String string)
	    {
	        driverLicenseClassId = string;
	    }
	
	public List getDriversLicenseClassList()
	    {
	        return driversLicenseClassList;
	    }
	public void setDriversLicenseClassList(List collection)
	    {
		driversLicenseClassList = collection;
	    }

	public int getDetVisitContactsCount() {
	    return detVisitContactsCount;
	}

	public void setDetVisitContactsCount(int detVisitContactsCount) {
	    this.detVisitContactsCount = detVisitContactsCount;
	}

	public boolean isDaVisit()
	{
	    return daVisit;
	}

	public void setDaVisit(boolean daVisit)
	{
	    this.daVisit = daVisit;
	}

	public boolean isVisitorCapRemoved()
	{
	    return visitorCapRemoved;
	}

	public void setVisitorCapRemoved(boolean visitorCapRemoved)
	{
	    this.visitorCapRemoved = visitorCapRemoved;
	}

	public String getContactMemberComments()
	{
	    return contactMemberComments;
	}

	public void setContactMemberComments(String contactMemberComments)
	{
	    this.contactMemberComments = contactMemberComments;
	}
	
	

	

	
}
