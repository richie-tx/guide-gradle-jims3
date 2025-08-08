/*
 * Created on Nov 4, 2004
 *
 */
package pd.contact.party;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import messaging.address.reply.AddressResponseEvent;
import messaging.contact.party.reply.MiscellaneousIdResponseEvent;
import messaging.contact.party.reply.PartyListResponseEvent;
import messaging.contact.party.reply.PartyResponseEvent;
import messaging.contact.reply.EmployerResponseEvent;
import messaging.domintf.contact.party.IParty;
import messaging.domintf.contact.party.ISupervisee;
import messaging.supervisionorder.reply.SuperviseeListResponseEvent;
import messaging.supervisionorder.reply.SuperviseeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import naming.PDPartyConstants;
import pd.address.Address;
import pd.address.PDAddressHelper;
import pd.codetable.Code;
import pd.codetable.person.ScarsMarksTattoosCode;
import pd.contact.Employer;

/**
 * @author dgibler
 *
 */
public final class PDPartyHelper
{
	/**
	 * Constructor
	 */
	private PDPartyHelper()
	{
		super();
	}

	/**
	 * Creates Party response event from entity.
	 * @param party
	 * @return IParty
	 */
	public static IParty getPartyResponseEvent(Party party)
	{

		IParty pre = new PartyResponseEvent();

		fillParty(party, pre);
		
		return pre;
	}
	
	private static void fillParty(Party party, IParty pre)
	{
		pre.setAfisNum(party.getAfisNum());
		pre.setAge(party.getAge());
		pre.setAlienRegistrationNum(party.getAlienRegistrationNum());
		pre.setBarNum(party.getBarNum());
		pre.setBuildId(party.getBuildId());
		// JM - We don't need this info for now.
//		Collection capacities = new ArrayList();
//		for (Iterator iter = party.getCapacities().iterator(); iter.hasNext();)
//		{
//			Capacity capacity = (Capacity) iter.next();
//			String capacityId = capacity.getCapacityId();
//		}

		pre.setCellPhone(party.getCellPhone());
		pre.setCitizenshipId(party.getCitizenshipId());
		pre.setCurrentNameInd(party.getCurrentNameInd());
		pre.setDateOfBirth(party.getDateOfBirth());
		pre.setDescriptorSourceId(party.getDescriptorSourceId());
		pre.setDriversLicenseClassId(party.getDriversLicenseClassId());
		pre.setEmail(party.getEmail());
		pre.setDriversLicenseExpirationYear(party.getDriversLicenseExpirationYear());
		pre.setDriversLicenseNum(party.getDriversLicenseNum());
		pre.setDriversLicenseStateId(party.getDriversLicenseStateId());

		Address currentAddress = party.getCurrentAddress();
		if (currentAddress != null)
		{
			pre.setCurrentAddressStreetNum(currentAddress.getStreetNum());
			pre.setCurrentAddressStreetName(currentAddress.getStreetName());
			pre.setCurrentAddressStreetName2(currentAddress.getAddress2());
			pre.setCurrentAddressAptNum(currentAddress.getAptNum());
			pre.setCurrentAddressCity(currentAddress.getCity());
			pre.setCurrentAddressStateId(currentAddress.getStateId());
			pre.setCurrentAddressZipCode(currentAddress.getZipCode());
			pre.setCurrentAddressTypeId(currentAddress.getAddressTypeId());
		}

		Employer currentEmployer = party.getCurrentEmployer();
		if (currentEmployer != null)
		{
			pre.setEmploymentStatusId(currentEmployer.getEmploymentStatusId());
			pre.setEmployerName(currentEmployer.getEmployerName());
			pre.setEmployerPhoneNum(currentEmployer.getPhoneNum());
			pre.setOccupation(currentEmployer.getOccupation());
			if (currentEmployer.getAddress() != null)
			{
				Address address = currentEmployer.getAddress();
				if (address != null)
				{
					pre.setEmployerAddressCity(address.getCity());
					pre.setEmployerAddressId(address.getAddressId());
					pre.setEmployerAddressStateId(address.getStateId());
					pre.setEmployerAddressStreetName(address.getStreetName());
					pre.setEmployerAddressStreetName2(address.getAddress2());
					pre.setEmployerAddressStreetNum(address.getStreetNum());
					pre.setEmployerAddressZipCode(address.getZipCode());
				}
			}
		}
		pre.setEthnicityId(party.getEthnicityId());
		pre.setEyeColorId(party.getEyeColorId());
		pre.setFaxLocation(party.getFaxLocation());
		pre.setFaxNum(party.getFaxNum());
		pre.setFirstName(party.getFirstName());
		pre.setFbiNum(party.getFbiNum());
		pre.setFingerPrintedInd(party.getFingerprintInd());
		pre.setHairColorId(party.getHaircolorId());
		pre.setHeight(party.getHeight());
		pre.setHomePhoneNum(party.getHomePhoneNum());
		pre.setIdCardNum(party.getIdCardNum());
		pre.setIdCardStateId(party.getIdCardStateId());
		pre.setLanguageId(party.getLanguageId());
		pre.setLastName(party.getLastName());
		//pre.setLogonId();
		pre.setMaritalStatusId(party.getMaritalStatusId());
		pre.setMiddleName(party.getMiddleName());

		pre.setNameSourceId(party.getNameSourceId());
		pre.setNamePtr(party.getNamePtr());
		pre.setNameTypeId(party.getNameTypeId());
		pre.setNextOfKinFirstName(party.getNextOfKinFirstName());
		pre.setNextOfKinLastName(party.getNextOfKinLastName());
		pre.setNextOfKinMiddleName(party.getNextOfKinMiddleName());
		pre.setNextOfKinRelationshipId(party.getNextOfKinRelationshipId());
		pre.setOID(party.getOID().toString());
		pre.setPager(party.getPager());
		pre.setPartyId(party.getOID().toString());
		pre.setPhoneExt(party.getPhoneExt());
		pre.setPhoneNum(party.getPhoneNum());
		pre.setPlaceOfBirth(party.getPlaceOfBirth());
		pre.setPlaceOfBirthStateId(party.getPlaceOfBirthStateId());
		pre.setRaceId(party.getRaceId());
		pre.setSexId(party.getSexId());
		pre.setSheriffOfficeNum(party.getSheriffOfficeNum());
		pre.setSidNum(party.getSidNum());
		pre.setSkinToneId(party.getSkinToneId());
		pre.setSpn(party.getSpn());
		pre.setSsn(party.getSsn());
		pre.setTitle(party.getTitle());
		pre.setWeight(party.getWeight());
		pre.setWorkPhoneNum(party.getWorkPhoneNum());
		if (party.getScarsMarks() != null && party.getScarsMarks().size() > 0) {
			//Collection tempScarsList = party.getScarsMarks();
			Collection newScarsList = new ArrayList();
			if (party.getScarsMarks().size() > 0) {
				for (Iterator it = party.getScarsMarks().iterator (); it.hasNext (); ) {
					Object object = it.next();
					if (object instanceof ScarsMarksTattoosCode) {
						newScarsList.add(object);
					} 
				}
			}
			pre.setScarsMarks(newScarsList);
		}
		if (party.getTattoos() != null && party.getTattoos().size() > 0) {
			Collection newTattooList = new ArrayList();
			for (Iterator it = party.getTattoos().iterator (); it.hasNext (); ) {
				Object object = it.next();
				if (object instanceof ScarsMarksTattoosCode) {
					newTattooList.add(object);
				} 
			}			
			pre.setTattoos(newTattooList);
		}
		pre.setTopic(PDPartyConstants.PARTY_EVENT_TOPIC);
	}
	
	public static PartyListResponseEvent getPartyLightResponseEvent(Party party)
	{

		PartyListResponseEvent plre = new PartyListResponseEvent();

		plre.setDateOfBirth(party.getDateOfBirth());
		plre.setSpn(party.getSpn());
		plre.setFirstName(party.getFirstName());
		plre.setMiddleName(party.getMiddleName());
		plre.setLastName(party.getLastName());
		plre.setRaceId(party.getRaceId());
		plre.setSexId(party.getSexId());
		plre.setJailInd(party.getJailInd());
		plre.setName(party.getName());
		
		return plre;
		
	}

	/**
	 * Creates and posts response events for objects contained in Party entity.
	 * @param party
	 */
	public static void createPartyChildrenResponseEvents(Party party)
	{
		IDispatch replyDispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		Collection coll = party.getAddresses();
		for (Iterator iter = coll.iterator(); iter.hasNext();)
		{
			Address address = (Address) iter.next();
			AddressResponseEvent are = PDAddressHelper.getAddressResponseEvent(address);
			if (are != null)
			{
				replyDispatch.postEvent(are);
			}
		}
		coll = party.getEmployers();
		for (Iterator iter = coll.iterator(); iter.hasNext();)
		{
			Employer employer = (Employer) iter.next();
			EmployerResponseEvent ere = getEmployerResponseEvent(employer);
			if (ere != null)
			{
				replyDispatch.postEvent(ere);
			}
		}
		coll = party.getMiscellaneousNums();
		for (Iterator iter = coll.iterator(); iter.hasNext();)
		{
			MiscellaneousId miscId = (MiscellaneousId) iter.next();
			MiscellaneousIdResponseEvent mre = getMiscellaneousIdResponseEvent(miscId);
			if (mre != null)
			{
				replyDispatch.postEvent(mre);
			}
		}

	}
	/**
	 * Creates Employer response event.
	 * @param employer
	 * @return EmployerResponseEvent
	 */
	public static EmployerResponseEvent getEmployerResponseEvent(Employer employer)
	{

		EmployerResponseEvent ere = new EmployerResponseEvent();
		ere.setCurrentEmployerInd(employer.getCurrentEmployerInd());
		ere.setEmployerId(employer.getEmployerId());
		ere.setEmployerName(employer.getEmployerName());
		Code code = employer.getEmploymentStatus();
		if (code != null)
		{
			ere.setEmploymentStatusId(code.getCode());
			ere.setEmploymentStatus(code.getDescription());
		}
		ere.setOccupation(employer.getOccupation());
		ere.setPartyId(employer.getPartyId());
		ere.setPhoneNum(employer.getPhoneNum());
		Address address = employer.getAddress();
		if (address != null)
		{
			ere.setAdditionalZipCode(address.getAdditionalZipCode());
			ere.setAddress2(address.getAddress2());
			ere.setCity(address.getCity());
			code = address.getState();
			if (code != null)
			{
				ere.setState(code.getDescription());
				ere.setStateId(code.getCodeId());
			}
			ere.setStreetName(address.getStreetName());
			ere.setStreetNum(address.getStreetNum());
			code = address.getStreetType();
			if (code != null)
			{
				ere.setStreetTypeId(code.getCodeId());
				ere.setStreetType(code.getDescription());
			}
			ere.setZipCode(address.getZipCode());
		}
		ere.setTopic(PDPartyConstants.PARTY_EMPLOYER_TOPIC);
		return ere;

	}
	
	/**
	 * Creates MiscellaneousId response event.
	 * @param miscId
	 * @return MiscellaneousIdResponseEvent
	 */
	public static MiscellaneousIdResponseEvent getMiscellaneousIdResponseEvent(MiscellaneousId miscId)
	{
		MiscellaneousIdResponseEvent mre = new MiscellaneousIdResponseEvent();
		mre.setIdNum(miscId.getIdNum());
		Code code = miscId.getIdNumType();
		if (code != null)
		{
			mre.setIdNumTypeId(code.getCode());
			mre.setIdNumType(code.getDescription());
		}
		code = miscId.getStateAgency();
		if (code != null)
		{
			mre.setStateAgency(code.getDescription());
			mre.setStateAgencyId(code.getCode());
		}
		mre.setTopic(PDPartyConstants.PARTY_MISCELLANEOUS_ID_EVENT_TOPIC);
		return mre;
	}

	/**
	 * Creates Supervisee List response event.
	 * @param supervisee
	 * @return ISupervisee
	 */
	public static SuperviseeListResponseEvent getSuperviseeListResponseEvent(Party supervisee)
	{
		SuperviseeListResponseEvent sre = new SuperviseeListResponseEvent();
		sre.setName(supervisee.getLastName()+", "+supervisee.getFirstName()+" "+supervisee.getMiddleName());

		sre.setOid(supervisee.getOID().toString());
		sre.setDateOfBirth(supervisee.getDateOfBirth());
		sre.setRaceId(supervisee.getRaceId());
		sre.setSexId(supervisee.getSexId());
		sre.setSpn(supervisee.getSpn());
		
		return sre;
	}

	/**
	 * Creates Supervisee response event.
	 * @param supervisee
	 * @return ISupervisee
	 */
	public static ISupervisee getSuperviseeResponseEvent(Party supervisee)
	{
		IParty baseParty = getPartyResponseEvent(supervisee);
		ISupervisee sre = new SuperviseeResponseEvent();
		fillParty(supervisee, sre);

		// add the supervisee stuff
		Employer currentEmployer = supervisee.getCurrentEmployer();
		if (currentEmployer != null)
		{
			sre.setOccupation(currentEmployer.getOccupation());
		}

		return sre;
	}
	
	public static ISupervisee getSuperviseeLightDetailsResponseEvent(Party party)
	{
		ISupervisee sre = new SuperviseeResponseEvent();
		sre.setDateOfBirth(party.getDateOfBirth());
		sre.setSpn(party.getSpn());
		sre.setFirstName(party.getFirstName());
		sre.setMiddleName(party.getMiddleName());
		sre.setLastName(party.getLastName());
		sre.setRaceId(party.getRaceId());
		sre.setSexId(party.getSexId());
		sre.setFbiNum(party.getFbiNum());
		sre.setHomePhoneNum(party.getHomePhoneNum());	
		sre.setPhoneExt(party.getPhoneExt());
		sre.setSidNum(party.getSidNum());
		sre.setPartyId(party.getOID().toString());		
				
		Address currentAddress = party.getCurrentAddress();
		if (currentAddress != null){
			sre.setCurrentAddressStreetNum(currentAddress.getStreetNum());
			sre.setCurrentAddressStreetName(currentAddress.getStreetName());
			sre.setCurrentAddressStreetName2(currentAddress.getAddress2());
			sre.setCurrentAddressAptNum(currentAddress.getAptNum());
			sre.setCurrentAddressCity(currentAddress.getCity());
			sre.setCurrentAddressStateId(currentAddress.getStateId());
			sre.setCurrentAddressZipCode(currentAddress.getZipCode());
			sre.setCurrentAddressTypeId(currentAddress.getAddressTypeId());
		}

		Employer currentEmployer = party.getCurrentEmployer();
		if (currentEmployer != null){
			sre.setEmploymentStatusId(currentEmployer.getEmploymentStatusId());
			sre.setEmployerName(currentEmployer.getEmployerName());
			sre.setEmployerPhoneNum(currentEmployer.getPhoneNum());
			sre.setOccupation(currentEmployer.getOccupation());
			if (currentEmployer.getAddress() != null)
			{
				Address address = currentEmployer.getAddress();
				if (address != null)
				{
					sre.setEmployerAddressCity(address.getCity());
					sre.setEmployerAddressId(address.getAddressId());
					sre.setEmployerAddressStateId(address.getStateId());
					sre.setEmployerAddressStreetName(address.getStreetName());
					sre.setEmployerAddressStreetName2(address.getAddress2());
					sre.setEmployerAddressStreetNum(address.getStreetNum());
					sre.setEmployerAddressZipCode(address.getZipCode());
				}
			}
		}
		return sre;		
	}
}
