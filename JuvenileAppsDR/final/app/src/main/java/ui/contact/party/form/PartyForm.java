/*
 * Created on Oct 13, 2004
 */
package ui.contact.party.form;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import messaging.contact.party.reply.OfficerCapacityResponseEvent;
import messaging.domintf.contact.party.IParty;
import messaging.party.UpdatePartyEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.PropertyCopier;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;

import ui.common.Address;
import ui.common.FormCollectionsHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SocialSecurity;
import ui.contact.party.helper.UIPartyHelper;

/**
 * @author dapte
 *
 * The base form to hold all the data elements corresponding to a "Party".
 *
 */
public class PartyForm extends ActionForm
{
	//non-user entry fields
	private String partyOid = "";
	private String action = "";
	private String secondaryAction = "";
	
	// Begin New Section - I just added all the fields in this section in order to make
	// PartyDetail page work (it's only used by PASO & AOOC for now). It might need to
	// revisit later when we starts implementing the Manage Party usecase.
	private String dateOfBirth;
	private String fingerPrintedInd;
	private String scarsMarksId;
	//private String relationship;
	private String tattoosId;
	private String usCitizenInd;
	private String spn;
	private String capacityId;
	//	End New Section
	
	// name information
	private Name partyName = new Name();
	private String nameTypeId;
	private String businessName;
	private String nameSourceId;
	// This collection will hold the selected values 
	// of capacities for a particular party instance
	private String[] selectedCapacities;

	// descriptive information
	private String raceId;
	private String sexId;
	private String height;
	private String weight;
	private String buildId;
	private String complexionId;
	private String eyeColorId;
	private String hairColorId;
	// This collection will hold the selected values 
	// of scars for a particular party instance
	private String[] selectedScars;
	// This collection will hold the selected values of 
	// tattoos for a particular party instance
	private String[] selectedTattoos;
	private String languageId;
	///private boolean fingerPrinted;
	private String usCitizenId;
	private String ethnicityId;
	private String maritalStatusId;
	private String placeOfBirth;
	private String birthStateId;
	private String birthCountry;
	private String descriptorSourceId;

	// contact Information
	private PhoneNumber homePhone = new PhoneNumber("");
	private PhoneNumber workPhone = new PhoneNumber("");
	private PhoneNumber cellPhone = new PhoneNumber("");
	private PhoneNumber pager = new PhoneNumber("");
	private PhoneNumber faxNum = new PhoneNumber("");
	private String faxLocation;
	private String email;

	// Address Information
	private Address address = new Address();

	// Drivers License/ID card information
	private String licenseNum;
	private String licenseStateId;
	private String expirationDate;
	private String licenseClassId;
	private String stateIdNum;
	private String issuingStateId;

	// Identification Numbers Information
	private SocialSecurity ssn = new SocialSecurity("");
	private String sid;
	private String fbiNum;
	private String bondsmanLicenseNum;
	private String alienRegistrationNum;
	private String miscIdNumber;
	private String miscIdNumberTypeId;
	private String agencyId;
	private String sheriffOfficeNum;
	private String afisNum;
	private String barNum;
	private String insNum;

	// Employer Information
	private String employer;
	private String employerName;
	private String occupation;
	private PhoneNumber employerPhone = new PhoneNumber("");
	private String employmentStatus;
	private Address empAddress = new Address();

	// Kinship Information
	private Name kinName = new Name();
	private String relationToPartyId;
	
	private Map capacityData = new HashMap();

	/**
	 * Clear Form
	 */
	public void clear()
	{
		//	Never clear the action & secondaryAction
		//	action = "";
		//	secondaryAction = "";
		partyOid = "";
		
		capacityId = "";
		dateOfBirth = "";
		fingerPrintedInd = "";
		scarsMarksId = "";
		////relationship = "";
		tattoosId = "";
		usCitizenInd = "";
		
		// name information
		partyName.clear();
		nameTypeId = "";
		businessName = "";
		nameSourceId = "";

		// descriptive information
		raceId = "";
		sexId = "";
		buildId = "";
		complexionId = "";
		eyeColorId = "";
		hairColorId = "";
		//selectedScars.clear();
		height = "";
		weight = "";
		languageId = "";
		usCitizenId = "";
		ethnicityId = "";
		maritalStatusId = "";
		placeOfBirth = "";
		birthStateId = "";
		birthCountry = "";
		descriptorSourceId = "";

		// contact Information
		homePhone.clear();
		workPhone.clear();
		cellPhone.clear();
		pager.clear();
		faxNum.clear();
		faxLocation = "";
		email = "";

		// Address Information
		address.clear();

		// Drivers License/ID card information
		licenseNum = "";
		licenseStateId = "";
		expirationDate = "";
		licenseClassId = "";
		stateIdNum = "";
		issuingStateId = "";

		// Identification Numbers Information
		ssn.clear();
		sid = "";
		fbiNum = "";
		bondsmanLicenseNum = "";
		alienRegistrationNum = "";
		miscIdNumber = "";
		miscIdNumberTypeId = "";
		agencyId = "";
		sheriffOfficeNum = "";
		afisNum = "";
		barNum = "";
		insNum = "";

		// Employer Information
		employer = "";
		employerName = "";
		employerPhone.clear();
		employmentStatus = "";
		empAddress.clear();

		// Kinship Information
		kinName.clear();
		relationToPartyId = "";
	}

	/**
	 * Calls Clear for the form
	 */
	public void reset()
	{
		clear();
	}

	/**
	 * @return Address
	 */
	public Address getAddress()
	{
		return address;
	}

	/**
	 * @return String
	 */
	public String getAfisNum()
	{
		return afisNum;
	}

	/**
	 * @return String
	 */
	public String getAlienRegistrationNum()
	{
		return alienRegistrationNum;
	}

	/**
	 * @return String
	 */
	public String getBarNum()
	{
		return barNum;
	}

	/**
	 * @return String
	 */
	public String getBirthCountry()
	{
		return birthCountry;
	}

	/**
	 * @return String
	 */
	public String getBondsmanLicenseNum()
	{
		return bondsmanLicenseNum;
	}

	/**
	 * @return String
	 */
	public String getBusinessName()
	{
		return businessName;
	}

	/**
	 * @return Collection
	 */
	public Collection getCapacities()
	{
		return UIPartyHelper.getInstance().getCapacities();
	}

	/**
	 * @return OfficerCapacityResponseEvent
	 */
	public OfficerCapacityResponseEvent getOfficer()
	{
		
		return UIPartyHelper.getInstance().getOfficer();
	}

	/**
	 * @param id The id to be matched
	 * @param scars If true, the scars collection is searched, else tattoos collection is searched.
	 * @return String
	 */
	public String getScarsTattooDescription(String id, boolean scars)
	{
		if (scars)
		{
			return FormCollectionsHelper.getInstance().getScarMarkDescr(id);
		}
		else
		{
			return FormCollectionsHelper.getInstance().getTattooDescr(id);
		}

	}

	/**
	 * @return String
	 */
	public String getAgencyDesc()
	{
		String aId = getOfficer().getAgencyId();
		return FormCollectionsHelper.getInstance().getAgencyDescr(aId);
	}

	/**
	 * @return PhoneNumber
	 */
	public PhoneNumber getCellPhone()
	{
		return cellPhone;
	}

	/**
	 * @return
	 */
	//	public String getFormattedCellPhone()
	//	{
	//		return cellPhone.getFormattedPhoneNumber();
	//	}
	//		
	/**
	 * @return String
	 */
	public String getDateOfBirth()
	{
		return dateOfBirth;
	}

	/**
	 * @return String
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @return String
	 */
	public Address getEmpAddress()
	{
		return empAddress;
	}

	/**
	 * @return String
	 */
	public String getEmployer()
	{
		return employer;
	}

	/**
	 * @return String
	 */
	public String getEmployerName()
	{
		return employerName;
	}

	/**
	 * @return String
	 */
	public PhoneNumber getEmployerPhone()
	{
		return employerPhone;
	}

	//	public String getFormattedEmployerPhone() {
	//		return employerPhone.getFormattedPhoneNumber();
	//	}

	/**
	 * @return String
	 */
	public String getEmploymentStatus()
	{
		return employmentStatus;
	}

	/**
	 * @return String
	 */
	public String getEmploymentStatusDesc()
	{
		return UIPartyHelper.getInstance().getEmploymentStatusDescr(employmentStatus);
	}

	/**
	 * @return String
	 */
	public String getExpirationDate()
	{
		return expirationDate;
	}

	/**
	 * @return String
	 */
	public String getFaxLocation()
	{
		return faxLocation;
	}

	/**
	 * @return PhoneNumber
	 */
	public PhoneNumber getFaxNum()
	{
		return faxNum;
	}

	//	public String getFormattedFaxNum() {
	//		return faxNumber.getFormattedPhoneNumber();
	//	}

	/**
	 * @return String
	 */
	public String getFbiNum()
	{
		return fbiNum;
	}

	/**
	 * @return boolean
	 */
	public boolean isFingerPrinted()
	{
		return fingerPrintedInd.equals("Y");
	}

	/**
	 * @return String
	 */
	public String getHeight()
	{
		return height;
	}

	/** 
	 * @return PhoneNumber
	 */
	public PhoneNumber getHomePhone()
	{
		return homePhone;
	}

	//	/**
	//	 * @return
	//	 */
	//	public String getFormattedHomePhone()
	//	{
	//		return homePhone.getFormattedPhoneNumber();
	//	}

	/**
	 * @return String
	 */
	public String getInsNum()
	{
		return insNum;
	}

	/**
	 * @return Name
	 */
	public Name getKinName()
	{
		return kinName;
	}

	/**
	 * @return String
	 */
	public String getFormattedKinName()
	{
		return kinName.getFormattedName();
	}

	/**
	 * @return String
	 */
	public String getLicenseNumber()
	{
		return licenseNum;
	}

	/**
	 * @return String
	 */
	public String getMiscIdNumber()
	{
		return miscIdNumber;
	}

	/**
	 * @return String
	 */
	public String getNameSourceId()
	{
		return nameSourceId;
	}

	/**
	 * @return String
	 */
	public String getNameSource()
	{
		return UIPartyHelper.getInstance().getNameSourceDescr(nameSourceId);
	}

	/**
	 * @return String
	 */
	public String getNameTypeId()
	{
		return nameTypeId;
	}

	/**
	 * @return String
	 */
	public String getNameTypeDesc()
	{
		return UIPartyHelper.getInstance().getNameTypeDescr(nameTypeId);
	}

	/**
	 * @return PhoneNumber
	 */
	public PhoneNumber getPager()
	{
		return pager;
	}

//	/**
//	 * @return
//	 */
//	public String getFormattedPager()
//	{
//		return pager.getFormattedPhoneNumber();
//	}

	/**
	 * @return Name
	 */
	public Name getPartyName()
	{
		return partyName;
	}

	/**
	 * @return String
	 */
	public String getPartyOid()
	{
		return partyOid;
	}

	/**
	 * @return String
	 */
	public String getFormattedPartyName()
	{
		return partyName.getFormattedName();
	}

	/**
	 * @return String
	 */
	public String getPlaceOfBirth()
	{
		return placeOfBirth;
	}

	/**
	 * @return String
	 */
	public String getSheriffOfficeNum()
	{
		return sheriffOfficeNum;
	}

	/**
	 * @return String
	 */
	public String getSid()
	{
		return sid;
	}

	/**
	 * @return String
	 */
	public SocialSecurity getSsn()
	{
		return ssn;
	}

	/**
	 * @return String
	 */
	public String getStateIdNum()
	{
		return stateIdNum;
	}

	/**
	 * @return String
	 */
	public String getWeight()
	{
		return weight;
	}

	/**
	 * @return String
	 */
	public PhoneNumber getWorkPhone()
	{
		return workPhone;
	}

	//	public String getFormattedWorkPhone()
	//	{
	//		return workPhone.getFormattedPhoneNumber();
	//	}

	/**
	 * @param address
	 */
	public void setAddress(Address aaddress)
	{
		this.address = aaddress;
	}

	/**
	 * @param string
	 */
	public void setAfisNum(String string)
	{
		afisNum = string;
	}

	/**
	 * @param string
	 */
	public void setAlienRegistrationNum(String string)
	{
		alienRegistrationNum = string;
	}

	/**
	 * @param string
	 */
	public void setBarNum(String string)
	{
		barNum = string;
	}

	/**
	 * @param string
	 */
	public void setBirthCountry(String string)
	{
		birthCountry = string;
	}

	/**
	 * @param string
	 */
	public void setBondsmanLicenseNum(String string)
	{
		bondsmanLicenseNum = string;
	}

	/**
	 * @param string
	 */
	public void setBusinessName(String string)
	{
		businessName = string;
	}

	/**
	 * @param string
	 */
	//	public void setCellPhone(String string)
	//	{
	//		cellPhone = new PhoneNumber(string);
	//	}

	/**
	 * @param string
	 */
	public void setDateOfBirth(String string)
	{
		dateOfBirth = string;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string)
	{
		email = string;
	}

	/**
	 * @param address
	 */
	public void setEmpAddress(Address aaddress)
	{
		empAddress = aaddress;
	}

	/**
	 * @param string
	 */
	public void setEmployer(String string)
	{
		employer = string;
	}

	/**
	 * @param string
	 */
	public void setEmployerName(String string)
	{
		employerName = string;
	}

	/**
	 * @param string
	 */
	//	public void setEmployerPhone(String string)
	//	{
	//		employerPhone = new PhoneNumber(string);
	//	}
	//	

	/**
	 * @param string
	 */
	public void setEmploymentStatus(String string)
	{
		employmentStatus = string;
	}

	/**
	 * @param string
	 */
	public void setExpirationDate(String string)
	{
		expirationDate = string;
	}

	/**
	 * @param string
	 */
	public void setFaxLocation(String string)
	{
		faxLocation = string;
	}

	/**
	 * @param string
	 */
	//	public void setFaxNum(String string)
	//	{
	//		faxNum = new PhoneNumber(string);
	//	}

	/**
	 * @param string
	 */
	public void setFbiNum(String string)
	{
		fbiNum = string;
	}

	/**
	 * @param string
	 */
	public void setHeight(String string)
	{
		height = string;
	}

	/**
	 * @param string
	 */
	//	public void setHomePhone(String string)
	//	{
	//		homePhone = new PhoneNumber(string);
	//	}

	/**
	 * @param string
	 */
	public void setInsNum(String string)
	{
		insNum = string;
	}

	/**
	 * @param name
	 */
	public void setKinName(Name name)
	{
		kinName = name;
	}

	/**
	 * @param string
	 */
	public void setLicenseNumber(String string)
	{
		licenseNum = string;
	}

	/**
	 * @param string
	 */
	public void setMiscIdNumber(String string)
	{
		miscIdNumber = string;
	}

	/**
	 * @param string
	 */
	public void setNameSourceId(String string)
	{
		nameSourceId = string;
	}

	/**
	 * @param string
	 */
	public void setNameTypeId(String string)
	{
		nameTypeId = string;
	}

	//	public void setPager(String string)
	//	{
	//		pager = new PhoneNumber(string);
	//	}
	//	
	
	/**
	 * @param name
	 */
	public void setPartyName(Name name)
	{
		partyName = name;
	}

	/**
	 * @param string
	 */
	public void setPlaceOfBirth(String string)
	{
		placeOfBirth = string;
	}

	/**
	 * @param string
	 */
	public void setSheriffOfficeNum(String string)
	{
		sheriffOfficeNum = string;
	}

	/**
	 * @param string
	 */
	public void setSid(String string)
	{
		sid = string;
	}
	
	//	public void setSsn(String string)
	//	{
	//		ssn.setSSN(string);
	//	}

	/**
	 * @param string
	 */
	public void setStateIdNum(String string)
	{
		stateIdNum = string;
	}

	/**
	 * @param string
	 */
	public void setWeight(String string)
	{
		weight = string;
	}


	//	public void setWorkPhone(String string)
	//	{
	//		workPhone = new PhoneNumber(string);
	//	}

	/**
	 * @return Collection
	 */
	public Collection getScars()
	{
		List scars = Arrays.asList(selectedScars);
		//Collections.sort((ArrayList)selectedScars);
		return scars;
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
	public void setEmployerPhone(PhoneNumber number)
	{
		employerPhone = number;
	}

	/**
	 * @param number
	 */
	public void setFaxNum(PhoneNumber number)
	{
		faxNum = number;
	}

	/**
	 * @param number
	 */
	public void setHomePhone(PhoneNumber number)
	{
		homePhone = number;
	}

	/**
	 * @param number
	 */
	public void setPager(PhoneNumber number)
	{
		pager = number;
	}

	/**
	 * @param collection
	 */
	public void setScars(Collection collection)
	{
		if (collection != null)
		{
			selectedScars = (String[]) collection.toArray();
		}
	}

	/**
	 * @param security
	 */
	public void setSsn(SocialSecurity security)
	{
		ssn = security;
	}

	/**
	 * @param number
	 */
	public void setWorkPhone(PhoneNumber number)
	{
		workPhone = number;
	}

	/**
	 * @return String
	 */
	public String getBirthStateId()
	{
		return birthStateId;
	}

	/**
	 * @return String
	 */
	public String getBirthStateDesc()
	{
		return FormCollectionsHelper.getInstance().getStateDescr(birthStateId);
	}

	/**
	 * @return String
	 */
	public String getBuildId()
	{
		return buildId;
	}

	/**
	 * @return String
	 */
	public String getBuildDesc()
	{
		return FormCollectionsHelper.getInstance().getBuildDescr(buildId);
	}

	/**
	 * @return String
	 */
	public String getComplexionId()
	{
		return complexionId;
	}

	/**
	 * @return String
	 */
	public String getComplexionDesc()
	{
		return FormCollectionsHelper.getInstance().getComplexionDescr(complexionId);
	}

	/**
	 * @return String
	 */
	public String getDescriptorSourceId()
	{
		return descriptorSourceId;
	}

	/**
	 * @return String
	 */
	public String getEthnicityId()
	{
		return ethnicityId;
	}

	/**
	 * @return String
	 */
	public String getEthnicityDesc()
	{
		return FormCollectionsHelper.getInstance().getEthnicityDescr(ethnicityId);
	}

	/**
	 * @return String
	 */
	public String getEyeColorId()
	{
		return eyeColorId;
	}

	/**
	 * @return String
	 */
	public String getEyeColorDesc()
	{
		return FormCollectionsHelper.getInstance().getEyeColorDescr(eyeColorId);
	}

	/**
	 * @return String
	 */
	public String getHairColorId()
	{
		return hairColorId;
	}

	/**
	 * @return String
	 */
	public String getHairColorDesc()
	{
		return FormCollectionsHelper.getInstance().getHairColorDescr(hairColorId);
	}

	/**
	 * @return String
	 */
	public String getIssuingStateId()
	{
		return issuingStateId;
	}

	/**
	 * @return String
	 */
	public String getIssuingStateDesc()
	{
		return FormCollectionsHelper.getInstance().getStateDescr(issuingStateId);
	}

	/**
	 * @return String
	 */
	public String getLanguageId()
	{
		return languageId;
	}

	/**
	 * @return String
	 */
	public String getLanguageDesc()
	{
		return UIPartyHelper.getInstance().getLanguageDescr(languageId);
	}

	/**
	 * @return String
	 */
	public String getLicenseClassId()
	{
		return licenseClassId;
	}

	/**
	 * @return String
	 */
	public String getLicenseClassDesc()
	{
		return UIPartyHelper.getInstance().getLicenseClassDescr(licenseClassId);
	}

	/**
	 * @return String
	 */
	public String getLicenseStateId()
	{
		return licenseStateId;
	}

	/**
	 * @return String
	 */
	public String getLicenseStateDesc()
	{
		return FormCollectionsHelper.getInstance().getStateDescr(licenseStateId);
	}

	/**
	 * @return String
	 */
	public String getMaritalStatusId()
	{
		return maritalStatusId;
	}

	/**
	 * @return String
	 */
	public String getMaritalStatusDesc()
	{
		return UIPartyHelper.getInstance().getMaritalStatusDescr(maritalStatusId);
	}

	/**
	 * @return String
	 */
	public String getMiscIdNumberTypeId()
	{
		return miscIdNumberTypeId;
	}

	/**
	 * @return String
	 */
	public String getMiscIdNumberTypeDesc()
	{
		return UIPartyHelper.getInstance().getMiscIdTypeDescr(miscIdNumberTypeId);
	}

	/**
	 * @return String
	 */
	public String getRaceId()
	{
		return raceId;
	}

	/**
	 * @return String
	 */
	public String getRaceDesc()
	{
		return FormCollectionsHelper.getInstance().getRaceDescr(raceId);
	}

	/**
	 * @return String
	 */
	public String getRelationToPartyId()
	{
		return relationToPartyId;
	}

	/**
	 * @return String
	 */
	public String getRelationToPartyDesc()
	{
		return UIPartyHelper.getInstance().getRelationToPartyDescr(relationToPartyId);
	}

	/**
	 * @return String
	 */
	public String getSexId()
	{
		return sexId;
	}

	/**
	 * @return String
	 */
	public String getSexDesc()
	{
		return FormCollectionsHelper.getInstance().getSexDescr(sexId);
	}

	/** 
	 * @return String
	 */
	public String getUsCitizenId()
	{
		return usCitizenId;
	}

	/**
	 * @return String
	 */
	public String getUsCitizenDesc()
	{
		return UIPartyHelper.getInstance().getUsCitizenDescr(usCitizenId);
	}

	/**
	 * @param string
	 */
	public void setBirthStateId(String string)
	{
		birthStateId = string;
	}

	/**
	 * @param string
	 */
	public void setBuildId(String string)
	{
		buildId = string;
	}

	/**
	 * @param string
	 */
	public void setComplexionId(String string)
	{
		complexionId = string;
	}

	/**
	 * @param string
	 */
	public void setDescriptorSourceId(String string)
	{
		descriptorSourceId = string;
	}

	/**
	 * @param string
	 */
	public void setEthnicityId(String string)
	{
		ethnicityId = string;
	}

	/**
	 * @param string
	 */
	public void setEyeColorId(String string)
	{
		eyeColorId = string;
	}

	/**
	 * @param string
	 */
	public void setHairColorId(String string)
	{
		hairColorId = string;
	}

	/**
	 * @param string
	 */
	public void setIssuingStateId(String string)
	{
		issuingStateId = string;
	}

	/**
	 * @param string
	 */
	public void setLanguageId(String string)
	{
		languageId = string;
	}

	/**
	 * @param string
	 */
	public void setLicenseClassId(String string)
	{
		licenseClassId = string;
	}

	/**
	 * @param string
	 */
	public void setLicenseStateId(String string)
	{
		licenseStateId = string;
	}

	/**
	 * @param string
	 */
	public void setMaritalStatusId(String string)
	{
		maritalStatusId = string;
	}

	/**
	 * @param string
	 */
	public void setMiscIdNumberTypeId(String string)
	{
		miscIdNumberTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setPartyOid(String string)
	{
		partyOid = string;
	}

	/**
	 * @param string
	 */
	public void setRaceId(String string)
	{
		raceId = string;
	}

	/**
	 * @param string
	 */
	public void setRelationToPartyId(String string)
	{
		relationToPartyId = string;
	}

	/**
	 * @param string
	 */
	public void setSexId(String string)
	{
		sexId = string;
	}

	/**
	 * @param string
	 */
	public void setUsCitizenId(String string)
	{
		usCitizenId = string;
	}

	/**
	 * @return Collection
	 */
	public Collection getNameTypes()
	{
		return UIPartyHelper.getInstance().getNameTypes();
	}

	/**
	 * @return String[]
	 */
	public String[] getSelectedScars()
	{
		return selectedScars;
	}

	/**
	 * @return String[]
	 */
	public String[] getSelectedTattoos()
	{
		return selectedTattoos;
	}

	/**
	 * @param collection
	 */
	public void setSelectedScars(String[] collection)
	{
		selectedScars = collection;
	}

	/**
	 * @param collection
	 */
	public void setSelectedTattoos(String[] collection)
	{
		selectedTattoos = collection;
	}

	/**
	 * @return Collection
	 */
	public Collection getNameSources()
	{
		return UIPartyHelper.getInstance().getNameSources();
	}

	/**
	 * @return String[]
	 */
	public String[] getSelectedCapacities()
	{
		selectedCapacities = new String[1];
		selectedCapacities[0] = "OFFICER";
		return selectedCapacities;
	}

	/**
	 * @param collection
	 */
	public void setSelectedCapacities(String[] collection)
	{
		selectedCapacities = collection;
		for (int i = 0; i < selectedCapacities.length; i++)
		{
			if (selectedCapacities[i].equalsIgnoreCase("Off"))
			{
				capacityData.put("Officer", new OfficerCapacityResponseEvent());
			}
		}
	}

	/**
	 * @return Collection
	 */
	public Collection getLanguages()
	{
		return UIPartyHelper.getInstance().getLanguages();
	}

	/**
	 * @return Collection
	 */
	public Collection getUsCitizens()
	{
		return UIPartyHelper.getInstance().getUsCitizens();
	}

	/**
	 * @return Collection
	 */
	public Collection getMaritalStatuses()
	{
		return UIPartyHelper.getInstance().getMaritalStatuses();
	}

	/**
	 * @return Collection
	 */
	public Collection getDescriptorSources()
	{
		return UIPartyHelper.getInstance().getDescriptorSources();
	}

	/**
	 * @return Collection
	 */
	public Collection getEmploymentStatuses()
	{
		return UIPartyHelper.getInstance().getEmploymentStatuses();
	}

	/**
	 * @return Collection
	 */
	public Collection getRelationsToParty()
	{
		return UIPartyHelper.getInstance().getRelationsToParty();
	}

	/**
	 * @return String
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
	 * @return String
	 */
	public String getOccupation()
	{
		return occupation;
	}

	/**
	 * @param string
	 */
	public void setOccupation(String string)
	{
		occupation = string;
	}

	/**
	 * @param requestEvent
	 * @return UpdatePartyEvent
	 */
	public UpdatePartyEvent populatePartyEvent(UpdatePartyEvent requestEvent)
	{
		PropertyCopier.copyProperties(this, requestEvent);
		// set the employer address
		requestEvent.setEmployerStreetNum(empAddress.getStreetNumber());
		requestEvent.setEmployerStreetName(empAddress.getStreetName());
		requestEvent.setEmployerStreetType(empAddress.getStreetType());
		requestEvent.setEmployerSuite(empAddress.getAptNumber());
		requestEvent.setEmployerCity(empAddress.getCity());
		requestEvent.setEmployerState(empAddress.getState());
		requestEvent.setEmployerZipcode(empAddress.getZipCode());
		// set the eemployer phone
		requestEvent.setEmployerPhoneNum(employerPhone.getPhoneNumber());

		return requestEvent;
	}

	/**
	 * @return String
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}

	/**
	 * @return
	 */
	public String getSecondaryAction()
	{
		return secondaryAction;
	}

	/**
	 * @param string
	 */
	public void setSecondaryAction(String string)
	{
		secondaryAction = string;
	}

	/**
	 * @return
	 */
	public String getCapacity()
	{
		return UIPartyHelper.getInstance().getCapacityDescr(capacityId);
	}

	/**
	 * @return
	 */
	public String getBuild()
	{
		return FormCollectionsHelper.getInstance().getBuildDescr(buildId);
	}

	/**
	 * @return
	 */
	public Map getCapacityData()
	{
		return capacityData;
	}

	/**
	 * @return
	 */
	public String getComplexion()
	{
		return FormCollectionsHelper.getInstance().getComplexionDescr(complexionId);
	}

	/**
	 * @return
	 */
	public String getDescriptorSource()
	{
		return UIPartyHelper.getInstance().getDescriptorSourceDescr(descriptorSourceId);
	}

	/**
	 * @return
	 */
	public String getDriversLicenseClass()
	{
		return UIPartyHelper.getInstance().getLicenseClassDescr(licenseClassId);
	}

	/**
	 * @return
	 */
	public String getDriversLicenseState()
	{
		return FormCollectionsHelper.getInstance().getStateDescr(licenseStateId);
	}

	/**
	 * @return
	 */
	public String getEthnicity()
	{
		return FormCollectionsHelper.getInstance().getEthnicityDescr(ethnicityId);
	}

	/**
	 * @return
	 */
	public String getEyeColor()
	{
		return FormCollectionsHelper.getInstance().getEyeColorDescr(eyeColorId);
	}

	/**
	 * @return
	 */
	public String getHairColor()
	{
		return FormCollectionsHelper.getInstance().getHairColorDescr(hairColorId);
	}

	/**
	 * @return
	 */
	public String getLanguage()
	{
		return UIPartyHelper.getInstance().getLanguageDescr(languageId);
	}

	/**
	 * @return
	 */
	public String getMaritalStatus()
	{
		return UIPartyHelper.getInstance().getMaritalStatusDescr(maritalStatusId);
	}

	/**
	 * @return
	 */
	public Collection getMiscIdList()
	{
		return UIPartyHelper.getInstance().getMiscIdTypes();
	}

	/**
	 * @return
	 */
	public String getRace()
	{
		return FormCollectionsHelper.getInstance().getRaceDescr(raceId);
	}

	/**
	 * @return
	 */
	public String getRelationship()
	{
		return UIPartyHelper.getInstance().getRelationToPartyDescr(relationToPartyId);
	}

	/**
	 * @return
	 */
	public String getScarsMarks()
	{
		return FormCollectionsHelper.getInstance().getScarMarkDescr(scarsMarksId);
	}

	/**
	 * @return
	 */
	public String getSex()
	{
		return FormCollectionsHelper.getInstance().getSexDescr(sexId);
	}

	/**
	 * @return
	 */
	public String getStateOfBirth()
	{
		return FormCollectionsHelper.getInstance().getStateDescr(birthStateId);
	}

	/**
	 * @return
	 */
	public String getTattoos()
	{
		return FormCollectionsHelper.getInstance().getTattooDescr(tattoosId);
	}

	/**
	 * @return
	 */
	public String getUsCitizenInd()
	{
		return usCitizenInd;
	}

	/**
	 * @return
	 */
	public String getSpn()
	{
		return spn;
	}

	/**
	 * @param map
	 */
	public void setCapacityData(Map map)
	{
		capacityData = map;
	}

	/**
	 * @param anId
	 */
	public void setCapacityId(String anId)
	{
		capacityId = anId;
	}

	/**
	 * @param string
	 */
	public void setUsCitizenInd(String string)
	{
		usCitizenInd = string;
	}

	/**
	 * @return
	 */
	public String getFingerPrintedInd()
	{
		return fingerPrintedInd;
	}

	/**
	 * @param string
	 */
	public void setFingerPrintedInd(String string)
	{
		fingerPrintedInd = string;
	}

	/**
	 * @param string
	 */
	public void setSpn(String string)
	{
		spn = string;
	}

	/**
	 * @param value
	 */
	public void setScarsMarksId(String value)
	{
		scarsMarksId = value;
	}

	public void setPartyData(IParty partyInfo)
	{
		Address currAddress = new Address();
		currAddress.setStreetNumber(partyInfo.getCurrentAddressStreetNum());
		currAddress.setStreetName(partyInfo.getCurrentAddressStreetName());
		currAddress.setAddress2(partyInfo.getCurrentAddressStreetName2());
		currAddress.setCity(partyInfo.getCurrentAddressCity());
		currAddress.setStateId(partyInfo.getCurrentAddressStateId());
		currAddress.setZipCode(partyInfo.getCurrentAddressZipCode());
		setAddress(currAddress);
		setAfisNum(partyInfo.getAfisNum());
		setAlienRegistrationNum(partyInfo.getAlienRegistrationNum());
		setBarNum(partyInfo.getBarNum());
		setBirthStateId(partyInfo.getPlaceOfBirthStateId());
		setBuildId(partyInfo.getBuildId());
		setCapacityId(partyInfo.getCapacityId());
		setComplexionId(partyInfo.getSkinToneId());
		Date tempDate = partyInfo.getDateOfBirth();
		if (tempDate != null)
		{
			setDateOfBirth(DateUtil.dateToString(tempDate, UIConstants.DATE_FMT_1));
		}
		setDescriptorSourceId(partyInfo.getDescriptorSourceId());
		setEmail(partyInfo.getEmail());
		Address emplAddress = new Address();
		emplAddress.setStreetNumber(partyInfo.getEmployerAddressStreetNum());
		emplAddress.setStreetName(partyInfo.getEmployerAddressStreetName());
		emplAddress.setAddress2(partyInfo.getEmployerAddressStreetName2());
		emplAddress.setCity(partyInfo.getEmployerAddressCity());
		emplAddress.setStateId(partyInfo.getEmployerAddressStateId());
		emplAddress.setZipCode(partyInfo.getEmployerAddressZipCode());
		setEmpAddress(emplAddress);
		setEmployerName(partyInfo.getEmployerName());
		setEthnicityId(partyInfo.getEthnicityId());
		setEyeColorId(partyInfo.getEyeColorId());
		setFaxLocation(partyInfo.getFaxLocation());
		String temp = partyInfo.getFaxNum();
		if (temp != null && !temp.equals(""))
		{
			setFaxNum(new PhoneNumber(temp));
		}
		setFbiNum(partyInfo.getFbiNum());
		setFingerPrintedInd(partyInfo.getFingerPrintedInd());
		setHairColorId(partyInfo.getHairColorId());
		// format the height data
		String height = partyInfo.getHeight();
		if (height != null && height.length()> 0)
		{
			setHeight(height.substring(0,1)+"' "+height.substring(1)+'"');
		}
		temp = partyInfo.getHomePhoneNum();
		if (temp != null && !temp.equals(""))
		{
			setHomePhone(new PhoneNumber(temp));
		}
		setLanguageId(partyInfo.getLanguageId());
		setMaritalStatusId(partyInfo.getMaritalStatusId());
		setNameSourceId(partyInfo.getNameSourceId());
		setNameTypeId(partyInfo.getNameTypeId());
		temp = partyInfo.getPager();
		if (temp != null && !temp.equals(""))
		{
			setPager(new PhoneNumber(temp));
		}
		setPartyName(new Name(partyInfo.getFirstName(), partyInfo.getMiddleName(), partyInfo.getLastName()));
		setPartyOid(partyInfo.getOID());
		setPlaceOfBirth(partyInfo.getPlaceOfBirth());
		setOccupation(partyInfo.getOccupation());
		temp = partyInfo.getWorkPhoneNum();
		if (temp != null && !temp.equals(""))
		{
			setWorkPhone(new PhoneNumber(temp));
		}
		setRaceId(partyInfo.getRaceId());
		setScarsMarksId(partyInfo.getScarsMarksId());
		//setTattoosId(partyInfo.getTattoosId());
		setSexId(partyInfo.getSexId());
		setSid(partyInfo.getSidNum());
		setSpn(partyInfo.getSpn());
		String ssnValue = partyInfo.getSsn();
		if (ssnValue != null)
		{
			SocialSecurity theSSN = new SocialSecurity(ssnValue);
			setSsn(theSSN);
		}
		setUsCitizenId(partyInfo.getCitizenshipId());
		setUsCitizenInd(partyInfo.getCitizenshipId().equalsIgnoreCase("US")? "YES" : "NO");
		setWeight(partyInfo.getWeight());
	}
}
