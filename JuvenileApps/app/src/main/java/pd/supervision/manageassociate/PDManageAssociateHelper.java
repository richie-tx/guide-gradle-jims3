/*
 * Created on Mar 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.manageassociate;

import pd.address.Address;
import pd.contact.party.Party;
import messaging.contact.party.reply.PartyResponseEvent;
import messaging.contact.to.PhoneNumberBean;
import messaging.domintf.contact.party.IParty;
import messaging.manageassociate.AssociateAddressRequestEvent;
import messaging.manageassociate.reply.AssociateAddressResponseEvent;
import messaging.manageassociate.reply.AssociateResponseEvent;

/**
 * @author cc_rsojitrawala
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PDManageAssociateHelper {

	/**
	 * 
	 */
	public PDManageAssociateHelper() {
		super();
	}
	
	/**
	 * @param addressRequestEvent
	 * @param address
	 * @return Address
	 */
	public static AssociateAddress getAssociateAddress(AssociateAddressRequestEvent assocAddressRequestEvent, 
															AssociateAddress assocAddress)
	{
		if (assocAddressRequestEvent == null)
		{
			return null;
		}
		assocAddress.setStreetNumber(assocAddressRequestEvent.getStreetNum());
		assocAddress.setStreetName(assocAddressRequestEvent.getStreetName());
		assocAddress.setStreetTypeId(assocAddressRequestEvent.getStreetTypeId());
		assocAddress.setAptOrSuite(assocAddressRequestEvent.getAptNum());
		assocAddress.setCity(assocAddressRequestEvent.getCity());
		assocAddress.setStateId(assocAddressRequestEvent.getStateId());
		assocAddress.setZipCode(assocAddressRequestEvent.getZipCode());
		assocAddress.setExtendedZipCode(assocAddressRequestEvent.getAdditionalZipCode());
		assocAddress.setComplexName(assocAddressRequestEvent.getComplexName());
		assocAddress.setAddressTypeId(assocAddressRequestEvent.getAddressTypeId());
		assocAddress.setCountyId(assocAddressRequestEvent.getCountyId());
		assocAddress.setIsPrimaryResidenceAddress(assocAddressRequestEvent.getIsPrimaryResidenceAddress());
		
		return assocAddress;
	}
	
	/**
	 * Creates Party response event from entity.
	 * @param party
	 * @return IParty
	 */
	public static IParty getPartyResponseEvent(Party party)
	{
		IParty pre = new PartyResponseEvent();
		fillSuperviseeAddress(party, pre);
		
		return pre;
	}
	
	private static void fillSuperviseeAddress(Party party, IParty pre)
	{
		Address currentAddress = party.getCurrentAddress();
		if (currentAddress != null)
		{
			pre.setCurrentAddressStreetNum(currentAddress.getStreetNum());
			pre.setCurrentAddressStreetName(currentAddress.getStreetName());
			pre.setCurrentAddressCity(currentAddress.getCity());
			pre.setCurrentAddressStateId(currentAddress.getStateId());
			pre.setCurrentAddressZipCode(currentAddress.getZipCode());
		}
	}
	
	/**
	 * Creates a thin Associate response event from SuperviseeAssociate entity.
	 * @param SuperviseeAssociate
	 * @return AssociateResponseEvent
	 */
	public static AssociateResponseEvent getThinAssociateResponseEvent(SuperviseeAssociate associate)
	{
		AssociateResponseEvent assocRespEvt = new AssociateResponseEvent();
		
		if (associate == null)
		{
			return null;
		}
		
		assocRespEvt.setAssociateId(associate.getOID().toString());
		assocRespEvt.setAssocFirstName(associate.getFirstName());
		assocRespEvt.setAssocMiddleName(associate.getMiddleName());
		assocRespEvt.setAssocLastName(associate.getLastName());
		assocRespEvt.setSpn(associate.getSpn());
		assocRespEvt.setRelationshipTypeId(associate.getRelationshipTypeId());
		assocRespEvt.setStatus(associate.getStatus());
		assocRespEvt.setHomePhone(new PhoneNumberBean(associate.getHomePhone()));
		assocRespEvt.setWorkPhone(new PhoneNumberBean(associate.getWorkPhone()));
		assocRespEvt.setCellPhone(new PhoneNumberBean(associate.getCellPhone()));
	
		//anAssociate.setSuperviseeAssociateId(getOID().toString());
		//anAssociate.setAssociateName(getAssociateName());
		
		return assocRespEvt;
	}
	
	/**
	 * Creates Associate response event from SuperviseeAssociate entity.
	 * @param SuperviseeAssociate
	 * @return AssociateResponseEvent
	 */
	public static AssociateResponseEvent getAssociateResponseEvent(SuperviseeAssociate associate)
	{
		AssociateResponseEvent assocRespEvt = new AssociateResponseEvent();
		
		if (associate == null)
		{
			return null;
		}
		assocRespEvt.setSpn(associate.getSpn());
		assocRespEvt.setAssociateId(associate.getOID().toString());
		assocRespEvt.setAssocFirstName(associate.getFirstName());
		assocRespEvt.setAssocMiddleName(associate.getMiddleName());
		assocRespEvt.setAssocLastName(associate.getLastName());
		assocRespEvt.setRelationshipTypeId(associate.getRelationshipTypeId());
		assocRespEvt.setStatus(associate.getStatus());
		assocRespEvt.setComments(associate.getComments());
		assocRespEvt.setHomePhone(new PhoneNumberBean(associate.getHomePhone()));
		assocRespEvt.setWorkPhone(new PhoneNumberBean(associate.getWorkPhone()));
		assocRespEvt.setCellPhone(new PhoneNumberBean(associate.getCellPhone()));
		assocRespEvt.setPager(new PhoneNumberBean(associate.getPager()));
		assocRespEvt.setEmail(associate.getEmail());
		assocRespEvt.setPrimaryResidenceAddressId(associate.getPrimaryResidenceAddressId());
		assocRespEvt.setOtherAddressId(associate.getOtherAddressId());
		
		if (associate.getUpdateDate() != null)
			assocRespEvt.setUpdateDate(associate.getUpdateDate());
		else
			assocRespEvt.setUpdateDate(associate.getCreateDate());
		
		return assocRespEvt;
	}
	
	public static AssociateAddressResponseEvent getAssociateAddressResponseEvent(AssociateAddress assocAddress)
	{
		AssociateAddressResponseEvent addressRespEvt = new AssociateAddressResponseEvent();
		
		if (assocAddress == null)
		{
			return null;
		}
		
		addressRespEvt.setStreetNum(assocAddress.getStreetNumber());
		addressRespEvt.setStreetName(assocAddress.getStreetName());
		addressRespEvt.setStreetTypeId(assocAddress.getStreetTypeId());
		addressRespEvt.setAptNum(assocAddress.getAptOrSuite());
		addressRespEvt.setCity(assocAddress.getCity());
		addressRespEvt.setStateId(assocAddress.getStateId());
		addressRespEvt.setZipCode(assocAddress.getZipCode());
		addressRespEvt.setAdditionalZipCode(assocAddress.getExtendedZipCode());
		addressRespEvt.setComplexName(assocAddress.getComplexName());
		addressRespEvt.setAddressTypeId(assocAddress.getAddressTypeId());
		addressRespEvt.setCountyId(assocAddress.getCountyId());
		addressRespEvt.setPrimaryResidenceAddress(assocAddress.getIsPrimaryResidenceAddress());
		
		return addressRespEvt;
	}

}
