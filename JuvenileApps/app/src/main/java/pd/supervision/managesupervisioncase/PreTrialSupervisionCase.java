//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\managesupervisioncase\\PreTrialSupervisionCase.java

package pd.supervision.managesupervisioncase;

import java.util.Date;

import messaging.address.reply.AddressResponseEvent;
import messaging.contact.domintf.IAddress;
import messaging.domintf.managesupervisioncase.ISupervisionCase;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import pd.address.Address;

public class PreTrialSupervisionCase extends OutOfCountyCase
{
	private String familyViolence;
	private String originalJurisdictionPID;
	private String contactAgencyName;
	/**
	 * Properties for agencyAddress
	 * @useParent true
	 * @detailerDoNotGenerate true
	 */
	private Address agencyAddress;
	private String agencyAddressId;
	/**
	 * @roseuid 444549B00135
	 */
	public PreTrialSupervisionCase()
	{

	}

	/**
	 * Access method for the familyViolence property.
	 * 
	 * @return   the current value of the familyViolence property
	 */
	public String getFamilyViolence()
	{
		fetch();
		return familyViolence;
	}

	/**
	 * Sets the value of the familyViolence property.
	 * 
	 * @param aFamilyViolence the new value of the familyViolence property
	 */
	public void setFamilyViolence(String aFamilyViolence)
	{
		if (this.familyViolence == null || !this.familyViolence.equals(aFamilyViolence))
		{
			markModified();
		}
		familyViolence = aFamilyViolence;
	}

	/**
	 * Access method for the originalJurisdictionPID property.
	 * 
	 * @return   the current value of the originalJurisdictionPID property
	 */
	public String getOriginalJurisdictionPID()
	{
		fetch();
		return originalJurisdictionPID;
	}

	/**
	 * Sets the value of the originalJurisdictionPID property.
	 * 
	 * @param anOriginalJurisdictionPID the new value of the originalJurisdictionPID property
	 */
	public void setOriginalJurisdictionPID(String anOriginalJurisdictionPID)
	{
		if (this.originalJurisdictionPID == null || !this.originalJurisdictionPID.equals(anOriginalJurisdictionPID))
		{
			markModified();
		}
		originalJurisdictionPID = anOriginalJurisdictionPID;
	}

	/**
	 * Access method for the contactAgencyName property.
	 * 
	 * @return   the current value of the contactAgencyName property
	 */
	public String getContactAgencyName()
	{
		fetch();
		return contactAgencyName;
	}

	/**
	 * Sets the value of the contactAgencyName property.
	 * 
	 * @param aContactAgencyName the new value of the contactAgencyName property
	 */
	public void setContactAgencyName(String aContactAgencyName)
	{
		if (this.contactAgencyName == null || !this.contactAgencyName.equals(aContactAgencyName))
		{
			markModified();
		}
		contactAgencyName = aContactAgencyName;
	}

	/**
	 * Access method for the agencyAddressId property.
	 * 
	 * @return   the current value of the agencyAddressId property
	 */
	public String getAgencyAddressId()
	{
		fetch();
		return agencyAddressId;
	}

	/**
	 * Sets the value of the agencyAddressId property.
	 * 
	 * @param anAgencyAddressId the new value of the agencyAddressId property
	 */
	public void setAgencyAddressId(String anAgencyAddressId)
	{
		if (this.agencyAddressId == null || !this.agencyAddressId.equals(anAgencyAddressId))
		{
			markModified();
		}
		agencyAddress = null;
		agencyAddressId = anAgencyAddressId;
	}

	/**
	 * Initialize class relationship to class pd.address.Address
	 */
	private void initAgencyAddress()
	{
		if (agencyAddress == null)
		{
			agencyAddress = (Address) new mojo.km.persistence.Reference(
						agencyAddressId,
						Address.class,
						(mojo.km.persistence.PersistentObject) this,
						"agencyAddress").getObject();
		}
	}
	/**
	* Gets referenced type pd.address.Address
	*/
	public Address getAgencyAddress()
	{
		initAgencyAddress();
		return agencyAddress;
	}
	
	/**
	 * set the type reference for class member agencyAddress
	 * @param anAddress
	 */
	public void setAgencyAddress(Address anAddress)
	{
		if (this.agencyAddress == null || !this.agencyAddress.equals(anAddress))
		{
			markModified();
		}
		if (anAddress.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(anAddress);
		}
		setAgencyAddressId("" + anAddress.getOID());
		this.agencyAddress = (Address) new mojo.km.persistence.Reference(anAddress).getObject();
	}

	/**
	 * - If updating:
	 * All data elements can be modified except CDI and Case#.
	 * 
	 * @param oocCase
	 * @return boolean - indicates whether valid or not
	 * @roseuid 44342BEB02D2
	 */
	public boolean validateForUpdate(ISupervisionCase oocCase)
	{
		// Offense date must preceed supervision begin date.
		if (oocCase.getOffenseDate().after(oocCase.getSupervisionBeginDate()))
		{
			postFailedValidation("Offense date must preceed supervision begin date.");
			this.valid = false;
		}
		return this.valid;
	}

	/**
	 *  Responsible for applying all validation rules for reactivating a PreTrialSupervisionCase.
	 * 
	 * @param oocCase
	 * @return boolean - indicates whether valid or not
	 * @roseuid 44342BEB02DC
	 */
	public boolean validateForReactivation(ISupervisionCase oocCase)
	{
		this.valid = true;
		// Any dates entered cannot be less than the previously set supervision date.
		if (oocCase.getOffenseDate().before(getSupervisionBeginDate()))
		{
			postFailedValidation("Offense date cannot be before the existing supervision begin date.");
			this.valid = false;
		}
		// Enforce the same data element business rules defined for creation of a PreTrialSupervisionCase.
		// JM - Validation is now done completely in the Struts UI
		///this.valid = validateForUpdate(oocCase);  
		return this.valid;  
	}

	/**
	 * Reactivates a PreTrialSupervisionCase.
	 * 
	 * @param oocCase
	 * @roseuid 44342BEB02E7
	 */
	public void reactivate(ISupervisionCase oocCase)
	{
		super.reactivate(oocCase);
	}

	/**
	 * Sets all values for creation or update of a PreTrialSupervisionCase.
	 * 
	 * @param oocCase
	 * @roseuid 443D14B2003C
	 */
	public void updateOutOfCountyCase(ISupervisionCase oocCase) 
	{
		boolean isUpdate = false;
		if (oocCase.isCreate() == false){
			isUpdate = true;
		}
		super.updateOutOfCountyCase(oocCase, isUpdate);

		if (this.valid)
		{
			setFamilyViolence(oocCase.getFamilyViolenceInd());
			setOriginalJurisdictionPID(oocCase.getPersonId());
			setContactAgencyName(oocCase.getOriginalAgencyName());
		
			IAddress addressInfo = oocCase.getAgencyAddress();
			Address address = null;
			// check for new or update
			if (newCase(oocCase))
			{
				// Instrument type = "PTI" (OCS PRETRIAL INTERVENTION)
				setInstrumentTypeId(PDCodeTableConstants.PRETRIAL_INTERVENTION_INSTR);			
				// Need to set the Defendant Status and Case Status
				setCaseAndDefendantStatus();			 
				address = new Address();
				updateAddress(address, addressInfo);
				setAgencyAddress(address);
			}
			else
			{
				address = getAgencyAddress();
				if (address == null)
				{
					address = new Address();
					setAgencyAddress(address);
				}
				updateAddress(address, addressInfo);
			}
		}
	}

	/**
	 * Gets all values for a PreTrialSupervisionCase.
	 * 
	 * @param oocCase
	 */
	public void fillOutOfCountyCase(ISupervisionCase oocCase, boolean isReactivate)
	{
		super.fillOutOfCountyCase(oocCase, isReactivate);

		oocCase.setFamilyViolenceInd(getFamilyViolence());
		oocCase.setPersonId(getOriginalJurisdictionPID());
		oocCase.setOriginalAgencyName(getContactAgencyName());
		
		IAddress addressInfo = new AddressResponseEvent();
		Address address = getAgencyAddress();
		if (address != null)
		{
			addressInfo.setStreetNum(address.getStreetNum());
			addressInfo.setStreetName(address.getStreetName());
			addressInfo.setStreetTypeCode(address.getStreetTypeId());
			addressInfo.setAptNum(address.getAptNum());
			addressInfo.setCity(address.getCity());
			addressInfo.setStateCode(address.getStateId());
			addressInfo.setZipCode(address.getZipCode());
			addressInfo.setAdditionalZipCode(address.getAdditionalZipCode());
			addressInfo.setAddressTypeCode(address.getAddressTypeId());
			addressInfo.setCountyCode(address.getCountyId());
		}
		
		oocCase.setAgencyAddress(addressInfo);
	}

	public boolean canBeReactivated()
	{
		//  For PTS (CDI = 020): 
		//  If SupervisionEnd Date is less than current system date then the case is 
		//  considered Inactive and can be Reactivated.
		Date supEndDate = getSupervisionEndDate();
		if (supEndDate != null)
		{
			if (supEndDate.before(DateUtil.getCurrentDate()))
			{
				return true;
			}
		}
		return false;
	}
	
	// Sets the Defendant Status (DST) and Case Status (CST)
	private void setCaseAndDefendantStatus()
	{
		// Defendant status = "W" (OCS OOC PRETRIAL INTERVENTION)
		setDefendantStatusId(PDCodeTableConstants.OCS_OOC_PRETRIAL_INTERVENTION);
		// set Case Status = "K" (OCS PRETRIAL INTERVENTION)
		setCaseStatusId(PDCodeTableConstants.OCS_PRETRIAL_INTERVENTION);
	}
	
	private void updateAddress(Address address, IAddress addressInfo)
	{
		address.setStreetNum(addressInfo.getStreetNum());
		address.setStreetName(addressInfo.getStreetName());
		address.setStreetTypeId(addressInfo.getStreetTypeCode());
		address.setAptNum(addressInfo.getAptNum());
		address.setCity(addressInfo.getCity());
		address.setStateId(addressInfo.getStateCode());
		address.setZipCode(addressInfo.getZipCode());
		address.setAdditionalZipCode(addressInfo.getAdditionalZipCode());
		address.setAddressTypeId(addressInfo.getAddressTypeCode());
		address.setCountyId(addressInfo.getCountyCode());
	}
}
