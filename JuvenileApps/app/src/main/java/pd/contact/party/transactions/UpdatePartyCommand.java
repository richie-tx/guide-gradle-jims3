//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\transactions\\UpdatePartyCommand.java

package pd.contact.party.transactions;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

import messaging.address.AddressRequestEvent;
import messaging.contact.party.reply.PartyCreateErrorEvent;
import messaging.domintf.contact.party.IParty;
import messaging.party.GetPartyDataEvent;
import messaging.party.MiscellaneousNumRequestEvent;
import messaging.party.UpdatePartyEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.MessageUtil;
import naming.PDConstants;
import naming.PartyControllerServiceNames;
import pd.address.Address;
import pd.contact.Employer;
import pd.contact.party.MiscellaneousId;
import pd.contact.party.PDPartyHelper;
import pd.contact.party.Party;
import pd.exception.CreateException;
import pd.helper.ICompositeRequestHelper;

/**
 * @author dgibler
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UpdatePartyCommand implements ICommand
{
	private static final String HELPER = "Helper";
	private static final String PERIOD = ".";
	private static final String BLANK_STRING = new String();
	/**
	 * @roseuid 416D2E6F03B0
	 */
	public UpdatePartyCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4166D687038C
	 */
	public void execute(IEvent event)
	{
		UpdatePartyEvent updatePartyEvent = (UpdatePartyEvent) event;
		String spn = updatePartyEvent.getSpn();
		Party party = null;
		if ((spn != null) && (!(spn.equals(""))))
		{
			IDispatch requestDispatch = EventManager.getSharedInstance(EventManager.REQUEST);

			GetPartyDataEvent getPartyDataEvent =
				(GetPartyDataEvent) EventFactory.getInstance(PartyControllerServiceNames.GETPARTYDATA);

			getPartyDataEvent.setSpn(spn);
			party = Party.find(getPartyDataEvent);

			CompositeResponse replies = (CompositeResponse) requestDispatch.getReply();

			Object obj = MessageUtil.filterComposite(replies, ReturnException.class);
			if (obj != null)
			{
				return;
			}
		}
		else
		{
			party = new Party();
		}
		try
		{
			setParentFields(updatePartyEvent);
			setChildFields(updatePartyEvent, party);
			createResponseEvents(party);
		}
		catch (CreateException e)
		{
			PartyCreateErrorEvent error = new PartyCreateErrorEvent();
			error.setMessage(e.getMessage());
			IDispatch replyDispatch = EventManager.getSharedInstance(EventManager.REPLY);
			replyDispatch.postEvent(error);
			return;
		}
	}

	/**
	 * Set Party values.
	 * @param partyEvent
	 * @throws CreateException
	 */
	private void setParentFields(UpdatePartyEvent partyEvent) throws CreateException
	{
		this.initializeNullFieldsToBlank(partyEvent);
		Party party = new Party();
		party.setAfisNum(partyEvent.getAfisNum());
		party.setAge(partyEvent.getAge());
		party.setAlienRegistrationNum(partyEvent.getAlienRegistrationNum());
		party.setBuildId(partyEvent.getBuildId());
		party.setCellPhone(partyEvent.getCellPhone());
		party.setCitizenshipId(partyEvent.getCitizenship());
		party.setDateOfBirth(partyEvent.getDateOfBirth());
		party.setDescriptorSourceId(partyEvent.getDescriptorSource());
		party.setDriversLicenseClassId(partyEvent.getDriversLicenseClass());
		party.setDriversLicenseExpirationYear(partyEvent.getDriversLicenseExpirationYear());
		party.setDriversLicenseNum(partyEvent.getDriversLicenseNum());
		party.setDriversLicenseStateId(partyEvent.getDriversLicenseStateId());
		party.setEmail(partyEvent.getEmail());
		party.setEthnicityId(partyEvent.getEthnicity());
		party.setEyeColorId(partyEvent.getEyeColor());
		//party.setFaxLocation();
		party.setFaxNum(partyEvent.getFaxNum());
		party.setFirstName(partyEvent.getFirstName());
		party.setFbiNum(partyEvent.getFbiNum());
		party.setFingerprintInd(partyEvent.getFingerPrintInd());
		party.setHaircolorId(partyEvent.getHairColor());
		party.setHeight(partyEvent.getHeight());
		party.setHomePhoneNum(partyEvent.getPhone());
		party.setLanguageId(partyEvent.getLanguage());
		party.setLastName(partyEvent.getLastName());
		//party.setLogonId();
		party.setMaritalStatusId(partyEvent.getMaritalStatus());
		party.setMiddleName(partyEvent.getMiddleName());
		party.setNameSourceId(partyEvent.getNameSource());
		party.setNameTypeId(partyEvent.getNameTypeInd());
		party.setNextOfKinFirstName(partyEvent.getNextOfKinFirstName());
		party.setNextOfKinLastName(partyEvent.getNextOfKinLastName());
		party.setNextOfKinMiddleName(partyEvent.getNextOfKinMiddleName());
		party.setNextOfKinRelationshipId(partyEvent.getNextOfKinRelationship());
		party.setPager(partyEvent.getPager());
		//party.setPhoneExt();
		party.setPhoneNum(partyEvent.getPhone());
		party.setPlaceOfBirth(partyEvent.getPlaceOfBirth());
		party.setPlaceOfBirthStateId(partyEvent.getPlaceOfBirthState());
		party.setRaceId(partyEvent.getRace());
		party.setSexId(partyEvent.getSex());
		party.setSheriffOfficeNum(partyEvent.getSheriffOfficeNum());
		party.setSidNum(partyEvent.getSidNum());
		party.setSkinToneId(partyEvent.getSkinTone());
		party.setSsn(partyEvent.getSsn());
		//party.setTitle();
		party.setWeight(partyEvent.getWeight());
		party.setWorkPhoneNum(partyEvent.getWorkPhone());

		Employer employer = null;

		if (this.employmentInfoExistsOnEvent(partyEvent))
		{
			employer = this.createEmployer(partyEvent, employer, party);
			if (employer != null)
			{
				party.insertEmployers(employer);
			}
		}

		if (party.getOID() == null)
		{
			IHome home = new Home();
			home.bind(party);

			IDispatch requestDispatch = EventManager.getSharedInstance(EventManager.REQUEST);

			CompositeResponse replies = (CompositeResponse) requestDispatch.getReply();

			Object obj = MessageUtil.filterComposite(replies, ReturnException.class);
			if (obj != null)
			{
				Exception e = (Exception) obj;
				throw new CreateException(e.getMessage());
			}
			else if (party.getOID() == null)
			{
				throw new CreateException("Error creating Party");
							}
		}
	}

	/**
	 * Iterates through requests attached to UpdatePartyEvent (CompositeRequest) and invokes the appropriate processor method based on event class name.
	 * @param updatePartyEvent
	 */
	private void setChildFields(UpdatePartyEvent updatePartyEvent, Party party)
	{

		Enumeration requests = updatePartyEvent.getRequests();
		while (requests.hasMoreElements())
		{
			RequestEvent requestEvent = (RequestEvent) requests.nextElement();

			if (requestEvent instanceof AddressRequestEvent)
			{
				this.processAddress(requestEvent, party);
			}
			else if (requestEvent instanceof MiscellaneousNumRequestEvent)
			{
				this.processMiscellaneousNum(requestEvent, party);
			}
			else
			{
				this.processCapacityRequestEvent(updatePartyEvent, requestEvent, party);
			}
		}
	}
	/**
	 * Creates response events
	 * @param party
	 */
	private void createResponseEvents(Party aParty)
	{
		IDispatch replyDispatch = EventManager.getSharedInstance(EventManager.REPLY);

		IParty partyInfo = PDPartyHelper.getPartyResponseEvent(aParty);
		if (partyInfo != null)
		{
			replyDispatch.postEvent((IEvent)partyInfo);
			//PDContactHelper.createPartyChildrenResponseEvents(aparty);
		}

	}
	/**
	 * Invokes Capacity helper class based on event class name.
	 * @param updatePartyEvent
	 * @param capacityRequestEvent
	 */
	private void processCapacityRequestEvent(UpdatePartyEvent updatePartyEvent, RequestEvent capacityRequestEvent, Party party)
	{
		StringBuffer className = new StringBuffer();

		//Determine package name of this command
		String classNameWithPkg = this.getClass().getName();
		int lastPeriod = classNameWithPkg.lastIndexOf(PERIOD);
		String pkgName = classNameWithPkg.substring(0, lastPeriod);

		//Determine class name of RequestEvent being processed
		classNameWithPkg = capacityRequestEvent.getClass().getName();
		lastPeriod = classNameWithPkg.lastIndexOf(PERIOD);
		String eventClassName = classNameWithPkg.substring(lastPeriod, classNameWithPkg.length());

		//Build helper class name
		className.append(pkgName);
		className.append(eventClassName);
		className.append(HELPER);

		ICompositeRequestHelper crHelper = null;
		try
		{
			crHelper = (ICompositeRequestHelper) Class.forName(className.toString()).newInstance();
		}
		catch (Exception e)
		{
			mojo.km.exceptionhandling.ExceptionHandler.executeCallbacks(e);
		}

		crHelper.update(updatePartyEvent, capacityRequestEvent, party);

	}
	/**
	 * Attaches miscellaneous Id numbers to Party.
	 * @param requestEvent
	 */
	private void processMiscellaneousNum(RequestEvent requestEvent, Party party)
	{
		MiscellaneousNumRequestEvent miscRequestEvent = (MiscellaneousNumRequestEvent) requestEvent;
		MiscellaneousId miscellaneousId = null;

		//Determine if new MiscellaneousId
		if ((miscRequestEvent.getMiscellaneousNumId() == null)
			|| (miscRequestEvent.getMiscellaneousNumId().equals("")))
		{
			miscellaneousId = new MiscellaneousId();
		}
		else
		{
			//Find existing MiscellaneousId
			Collection miscellaneousNums = party.getMiscellaneousNums();
			MiscellaneousId thisMiscellaneousId = null;
			for (Iterator iter = miscellaneousNums.iterator(); iter.hasNext();)
			{
				thisMiscellaneousId = (MiscellaneousId) iter.next();
				if (thisMiscellaneousId.getMiscellaneousIdId().equals(miscRequestEvent.getMiscellaneousNumId()))
				{
					miscellaneousId = thisMiscellaneousId;
					break;
				}
			}
		}
		miscellaneousId.setIdNum(miscRequestEvent.getMiscellaneousNum());
		miscellaneousId.setIdNumTypeId(miscRequestEvent.getMiscellaneousNumType());
		miscellaneousId.setStateAgencyId(miscRequestEvent.getStateAgency());
		miscellaneousId.setPartyId(party.getOID().toString());
		party.insertMiscellaneousNums(miscellaneousId);
	}

	/**
	 * Attaches addresses to Party
	 * @param requestEvent
	 */
	private void processAddress(RequestEvent requestEvent, Party party)
	{
		AddressRequestEvent addressEvent = (AddressRequestEvent) requestEvent;

		Address address = null;

		//Determine if new Address.
		if ((addressEvent.getAddressId() == null) || (addressEvent.getAddressId().equals("")))
		{
			address = new Address();
		}
		else
		{
			//Find existing Address
			Collection addresses = party.getAddresses();
			if (addresses != null)
			{
				for (Iterator iter = addresses.iterator(); iter.hasNext();)
				{
					Address thisAddress = (Address) iter.next();
					if (thisAddress.getAddressId().equals(addressEvent.getAddressId()))
					{
						address = thisAddress;
					}
				}
			}
		}

		address.setAddress2(addressEvent.getAddress2());
		address.setAddressTypeId(addressEvent.getAddressType());
		address.setCity(addressEvent.getCity());
		address.setCountryId(addressEvent.getCountry());
		address.setKeymap(addressEvent.getKeymap());
		if ((addressEvent.getLatitude() != null) && (!addressEvent.getLatitude().equals("")))
		{
			address.setLatitude(Double.parseDouble(addressEvent.getLatitude()));
		}
		if ((addressEvent.getLongitude() != null) && (!addressEvent.getLongitude().equals("")))
		{
			address.setLongitude(Double.parseDouble(addressEvent.getLongitude()));
		}
		address.setStateId(addressEvent.getState());
		address.setStreetName(addressEvent.getStreetName());
		address.setStreetNum(addressEvent.getStreetNum());
		address.setStreetTypeId(addressEvent.getStreetType());
		address.setZipCode(addressEvent.getZipCode());
		address.setAdditionalZipCode(addressEvent.getAdditionalZipCode());
		address.setValidated(addressEvent.getValidated());
		party.insertAddresses(address);
	}
	/**
	 * @param partyEvent
	 * @return
	 */
	private boolean employmentInfoExistsOnEvent(UpdatePartyEvent partyEvent)
	{
		//This check has been created because the employment information is not included in the event when an officer is created.
		//The employment information for an officer is created from the agency in the OfferCapacityRequestEventHelper
		if (partyEvent.getEmployerName().equals(BLANK_STRING)
			&& partyEvent.getEmploymentStatus().equals(BLANK_STRING)
			&& partyEvent.getEmployerSuite().equals(BLANK_STRING)
			&& partyEvent.getEmployerCity().equals(BLANK_STRING)
			&& partyEvent.getEmployerState().equals(BLANK_STRING)
			&& partyEvent.getEmployerStreetType().equals(BLANK_STRING)
			&& partyEvent.getEmployerZipcode().equals(BLANK_STRING)
			&& partyEvent.getEmployerAdditionalZipCode().equals(BLANK_STRING))
		{
			return false;
		}
			return true;
		
	}
	/**
	 * @param partyEvent
	 */
	private Employer createEmployer(UpdatePartyEvent partyEvent, Employer employer, Party party)
	{
		//Determine if new Employer
		if ((partyEvent.getEmployerId() == null) || (partyEvent.getEmployerId().equals("")))
		{
			employer = new Employer();
			employer.setCurrentEmployerInd(PDConstants.YES);
		}
		else
		{
			//Find existing Employer
			Collection employers = party.getEmployers();
			for (Iterator iter = employers.iterator(); iter.hasNext();)
			{
				Employer thisEmployer = (Employer) iter.next();
				if (thisEmployer.getEmployerId().equals(partyEvent.getEmployerId()))
				{
					employer = thisEmployer;
					break;
				}
			}
		}
		employer.setEmployerName(partyEvent.getEmployerName());
		employer.setEmploymentStatusId(partyEvent.getEmploymentStatus());
		employer.setPhoneNum(partyEvent.getEmployerPhoneNum());
		employer.setOccupation(partyEvent.getOccupation());

		Address address = employer.getAddress();
		if (address == null)
		{
			address = new Address();
		}
		address.setAddress2(partyEvent.getEmployerSuite());
		address.setCity(partyEvent.getEmployerCity());
		address.setStateId(partyEvent.getEmployerState());
		address.setStreetTypeId(partyEvent.getEmployerStreetType());
		address.setZipCode(partyEvent.getEmployerZipcode());
		address.setAdditionalZipCode(partyEvent.getEmployerAdditionalZipCode());
		employer.setAddress(address);
		return employer;
	}

	/**
	 * @param partyEvent
	 */
	private void initializeNullFieldsToBlank(UpdatePartyEvent partyEvent)
	{
		if (partyEvent.getAfisNum() == null)
		{
			partyEvent.setAfisNum(BLANK_STRING);
		}
		if (partyEvent.getAge() == null)
		{
			partyEvent.setAge(BLANK_STRING);
		}
		if (partyEvent.getAlienRegistrationNum() == null)
		{
			partyEvent.setAlienRegistrationNum(BLANK_STRING);
		}
		if (partyEvent.getBuildId() == null)
		{
			partyEvent.setBuildId(BLANK_STRING);
		}
		if (partyEvent.getCellPhone() == null)
		{
			partyEvent.setCellPhone(BLANK_STRING);
		}
		if (partyEvent.getCitizenship() == null)
		{
			partyEvent.setCitizenship(BLANK_STRING);
		}
		if (partyEvent.getDescriptorSource() == null)
		{
			partyEvent.setDescriptorSource(BLANK_STRING);
		}
		if (partyEvent.getDriversLicenseClass() == null)
		{
			partyEvent.setDriversLicenseClass(BLANK_STRING);
		}
		if (partyEvent.getDriversLicenseExpirationYear() == null)
		{
			partyEvent.setDriversLicenseExpirationYear(BLANK_STRING);
		}
		if (partyEvent.getDriversLicenseNum() == null)
		{
			partyEvent.setDriversLicenseNum(BLANK_STRING);
		}
		if (partyEvent.getDriversLicenseStateId() == null)
		{
			partyEvent.setDriversLicenseStateId(BLANK_STRING);
		}
		if (partyEvent.getEmail() == null)
		{
			partyEvent.setEmail(BLANK_STRING);
		}
		if (partyEvent.getEthnicity() == null)
		{
			partyEvent.setEthnicity(BLANK_STRING);
		}
		if (partyEvent.getEyeColor() == null)
		{
			partyEvent.setEyeColor(BLANK_STRING);
		}
		//partyEvent.setFaxLocation();
		if (partyEvent.getFaxNum() == null)
		{
			partyEvent.setFaxNum(BLANK_STRING);
		}
		//partyEvent.setFaxLocation();
		if (partyEvent.getFbiNum() == null)
		{
			partyEvent.setFbiNum(BLANK_STRING);
		}
		if (partyEvent.getFingerPrintInd() == null)
		{
			partyEvent.setFingerPrintInd(BLANK_STRING);
		}
		if (partyEvent.getFirstName() == null)
		{
			partyEvent.setFirstName(BLANK_STRING);
		}
		if (partyEvent.getHairColor() == null)
		{
			partyEvent.setHairColor(BLANK_STRING);
		}
		if (partyEvent.getHeight() == null)
		{
			partyEvent.setHeight(BLANK_STRING);
		}
		if (partyEvent.getPhone() == null)
		{
			partyEvent.setPhone(BLANK_STRING);
		}
		if (partyEvent.getInsNum() == null)
		{
			partyEvent.setInsNum(BLANK_STRING);
		}
		if (partyEvent.getLanguage() == null)
		{
			partyEvent.setLanguage(BLANK_STRING);
		}
		if (partyEvent.getLastName() == null)
		{
			partyEvent.setLastName(BLANK_STRING);
		}
		//partyEvent.setLogonId();
		if (partyEvent.getMaritalStatus() == null)
		{
			partyEvent.setMaritalStatus(BLANK_STRING);
		}
		if (partyEvent.getMiddleName() == null)
		{
			partyEvent.setMiddleName(BLANK_STRING);
		}
		if (partyEvent.getNameSource() == null)
		{
			partyEvent.setNameSource(BLANK_STRING);
		}
		if (partyEvent.getNameTypeInd() == null)
		{
			partyEvent.setNameTypeInd(BLANK_STRING);
		}
		if (partyEvent.getNextOfKinFirstName() == null)
		{
			partyEvent.setNextOfKinFirstName(BLANK_STRING);
		}
		if (partyEvent.getNextOfKinFirstName() == null)
		{
			partyEvent.setNextOfKinFirstName(BLANK_STRING);
		}
		if (partyEvent.getNextOfKinLastName() == null)
		{
			partyEvent.setNextOfKinLastName(BLANK_STRING);
		}
		if (partyEvent.getNextOfKinMiddleName() == null)
		{
			partyEvent.setNextOfKinMiddleName(BLANK_STRING);
		}
		if (partyEvent.getNextOfKinRelationship() == null)
		{
			partyEvent.setNextOfKinRelationship(BLANK_STRING);
		}
		if (partyEvent.getPager() == null)
		{
			partyEvent.setPager(BLANK_STRING);
		}
		//partyEvent.setPhoneExt();
		if (partyEvent.getPhone() == null)
		{
			partyEvent.setPhone(BLANK_STRING);
		}
		if (partyEvent.getPlaceOfBirthState() == null)
		{
			partyEvent.setPlaceOfBirthState(BLANK_STRING);
		}
		if (partyEvent.getRace() == null)
		{
			partyEvent.setRace(BLANK_STRING);
		}
		if (partyEvent.getSex() == null)
		{
			partyEvent.setSex(BLANK_STRING);
		}
		if (partyEvent.getSheriffOfficeNum() == null)
		{
			partyEvent.setSheriffOfficeNum(BLANK_STRING);
		}
		if (partyEvent.getSidNum() == null)
		{
			partyEvent.setSidNum(BLANK_STRING);
		}
		if (partyEvent.getSkinTone() == null)
		{
			partyEvent.setSkinTone(BLANK_STRING);
		}
		if (partyEvent.getSsn() == null)
		{
			partyEvent.setSsn(BLANK_STRING);
		}
		//partyEvent.setTitle();
		if (partyEvent.getWeight() == null)
		{
			partyEvent.setWeight(BLANK_STRING);
		}
		if (partyEvent.getWorkPhone() == null)
		{
			partyEvent.setWorkPhone(BLANK_STRING);
		}
		if (partyEvent.getEmployerName() == null)
		{
			partyEvent.setEmployerName(BLANK_STRING);
		}
		if (partyEvent.getEmploymentStatus() == null)
		{
			partyEvent.setEmploymentStatus(BLANK_STRING);
		}
		if (partyEvent.getEmployerPhoneNum() == null)
		{
			partyEvent.setEmployerPhoneNum(BLANK_STRING);
		}
		if (partyEvent.getOccupation() == null)
		{
			partyEvent.setOccupation(BLANK_STRING);
		}
		if (partyEvent.getEmployerSuite() == null)
		{
			partyEvent.setEmployerSuite(BLANK_STRING);
		}
		if (partyEvent.getEmployerCity() == null)
		{
			partyEvent.setEmployerCity(BLANK_STRING);
		}
		if (partyEvent.getEmployerState() == null)
		{
			partyEvent.setEmployerState(BLANK_STRING);
		}
		if (partyEvent.getEmployerStreetType() == null)
		{
			partyEvent.setEmployerStreetType(BLANK_STRING);
		}
		if (partyEvent.getEmployerZipcode() == null)
		{
			partyEvent.setEmployerZipcode(BLANK_STRING);
		}
		if (partyEvent.getEmployerAdditionalZipCode() == null)
		{
			partyEvent.setEmployerAdditionalZipCode(BLANK_STRING);
		}
	}
	/**
	* @param anObject
	* @roseuid 4166D687039A
	*/
	public void update(Object anObject)
	{

	}
	/**
	* @param event
	* @roseuid 4166D687038E
	*/
	public void onRegister(IEvent event)
	{

	}
	/**
	 * @param event
	 * @roseuid 4166D6870390
	 */
	public void onUnregister(IEvent event)
	{

	}
}
