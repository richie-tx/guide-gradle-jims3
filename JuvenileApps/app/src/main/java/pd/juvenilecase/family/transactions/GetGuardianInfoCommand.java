/*
 * Created on Jul 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.family.transactions;

import java.util.Iterator;
import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;
import messaging.contact.to.NameBean;
import messaging.contact.to.PhoneNumberBean;
import messaging.family.GetGuardianInfoEvent;
import messaging.juvenilecase.reply.GuardianInfoResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.ReadOnlyTransactional;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.GuardianInfo;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GetGuardianInfoCommand implements ICommand, ReadOnlyTransactional {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetGuardianInfoEvent reqEvent = (GetGuardianInfoEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator guardianIter = GuardianInfo.findAll(reqEvent);
		String guardianOID = "";
		if (guardianIter != null) {
			while (guardianIter.hasNext()) {
				GuardianInfo guardianInfo = (GuardianInfo) guardianIter.next();
				guardianOID = guardianInfo.getOID().substring(0, guardianInfo.getOID().indexOf("-"));
				guardianInfo.setPrimaryContact("false");
				// use OID to find JCCONSRELATION row and set value in guardianInfo for primary contact accordingly
				FamilyConstellationMember familyConstellationMember = FamilyConstellationMember.find(guardianOID);
				if (familyConstellationMember.isPrimaryContact() ){
					guardianInfo.setPrimaryContact("true");
				}
				GuardianInfoResponseEvent respEvent = getGuardianInfoResponseEvent(guardianInfo);
				dispatch.postEvent(respEvent);
			}
		}
		guardianOID = null;
	}

	/**
	 * @param guardianInfo
	 */
	private GuardianInfoResponseEvent getGuardianInfoResponseEvent(GuardianInfo guardianInfo) {

		GuardianInfoResponseEvent respEvent = new GuardianInfoResponseEvent();
		IName name = new NameBean();
		name.setFirstName(guardianInfo.getFirstName());
		name.setMiddleName(guardianInfo.getMiddleName());
		name.setLastName(guardianInfo.getLastName());
		respEvent.setGuardianName(name.getFormattedName());
		respEvent.setEntryDate(guardianInfo.getCreateDate());
		respEvent.setFamMemberId(guardianInfo.getFamMemberId());
		respEvent.setPrimaryContact(guardianInfo.getPrimaryContact());		
		IPhoneNumber phone = new PhoneNumberBean(guardianInfo.getPhone());
		if (guardianInfo.getPhoneTypeId() != null && guardianInfo.getPhone() !=null) {
		    	if (guardianInfo.getPhoneType() != null)
		    	    respEvent.setPhoneType(guardianInfo.getPhoneType().getDescription());// add a null check for guardianInfo.getPhoneType()
			if (guardianInfo.getPhoneTypeId().equalsIgnoreCase("HM")) {
				respEvent.setHomePhone(phone.getFormattedPhoneNumber());
				respEvent.setHomeExtn(guardianInfo.getExtension());
			}
			if (guardianInfo.getPhoneTypeId().equalsIgnoreCase("WK")) {
				respEvent.setWorkPhone(phone.getFormattedPhoneNumber());
				respEvent.setWorkExtn(guardianInfo.getExtension());
			}
			if (guardianInfo.getPhoneTypeId().equalsIgnoreCase("MO")) {
				respEvent.setMobilePhone(phone.getFormattedPhoneNumber());
				respEvent.setExtension(guardianInfo.getExtension());
			}
			if (guardianInfo.getPhoneTypeId().equalsIgnoreCase("JUVC")) {
				respEvent.setJuvMobilePhone(phone.getFormattedPhoneNumber());
				respEvent.setExtension(guardianInfo.getExtension());
			}
		}
		respEvent.setAddressId(guardianInfo.getAddressId());
		if (guardianInfo.getAddressType() != null) {
			respEvent.setAddressType(guardianInfo.getAddressType().getDescription());
		} else {
			respEvent.setAddressType("");
		}
		respEvent.setAddressTypeId(guardianInfo.getAddressTypeId());
		respEvent.setStreetNumber(guardianInfo.getStreetNumber());
		if (guardianInfo.getStreetNumSuffix() != null) {
			respEvent.setStreetNumSuffix(guardianInfo.getStreetNumSuffix().getDescription());
			respEvent.setStreetNumSuffixId(guardianInfo.getStreetNumSuffixId());
		} else {
			respEvent.setStreetNumSuffix("");
			respEvent.setStreetNumSuffixId("");
		}
		respEvent.setStreetName(guardianInfo.getStreetName());
		if (guardianInfo.getStreetType() != null) {
			respEvent.setStreetType(guardianInfo.getStreetType().getDescription());
		} else {
			respEvent.setStreetType("");
		}
		respEvent.setStreetTypeId(guardianInfo.getStreetTypeId());
		//<KISHORE>JIMS200061024 : MJCW - Guardian apartment number is missing
		respEvent.setAptNumber(guardianInfo.getAptNumber());
		respEvent.setCity(guardianInfo.getCity());
		if (guardianInfo.getState() != null) {
			respEvent.setState(guardianInfo.getState().getDescription());
		} else {
			respEvent.setState("");
		}
		respEvent.setStateId(guardianInfo.getStateId());
		respEvent.setZipCode(guardianInfo.getZipCode());
		respEvent.setValidated(guardianInfo.getValidated());
		return respEvent;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
	}
}