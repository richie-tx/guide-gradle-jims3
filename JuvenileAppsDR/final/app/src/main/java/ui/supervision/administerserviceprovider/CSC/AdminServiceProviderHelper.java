/*
 * Created on Dec 28, 2007
 *
 */
package ui.supervision.administerserviceprovider.CSC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import naming.PDCodeTableConstants;
import ui.common.Address;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SimpleCodeTableHelper;
import ui.common.StringUtil;
import ui.common.UIUtil;
import ui.security.SecurityUIHelper;
import ui.supervision.administerserviceprovider.CSC.form.ServiceProviderCSCDForm;
import ui.supervision.administerserviceprovider.CSC.form.ServiceProviderContactForm;
import ui.supervision.administerserviceprovider.CSC.form.ServiceProviderProgramForm;
import messaging.csserviceprovider.GetSPProgramEvent;
import messaging.csserviceprovider.GetSPContactEvent;
import messaging.csserviceprovider.GetCSServiceProviderEvent;
import messaging.csserviceprovider.SaveProgramEvent;
import messaging.csserviceprovider.SaveSPContactEvent;
import messaging.csserviceprovider.SaveServiceProviderEvent;
import messaging.csserviceprovider.reply.CSProgramResponseEvent;
import messaging.csserviceprovider.reply.CSServiceProviderContactResponseEvent;
import messaging.csserviceprovider.reply.CSServiceProviderResponseEvent;
import messaging.organization.GetDivisionsProgramsForAgencyEvent;
import messaging.organization.GetProgramUnitsForDivisionEvent;
import messaging.organization.reply.GetDivisionForAgencyResponseEvent;
import messaging.organization.reply.GetProgramUnitResponseEvent;
import messaging.supervisionoptions.GetSubGroupsEvent;
import messaging.supervisionoptions.reply.GroupResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminServiceProviderHelper {

	/**
	 * Used to get the request event to do a check on the service provider name validation
	 * @param aForm
	 * @return
	 */
	public static RequestEvent getServProvNameValidationEvent(ServiceProviderCSCDForm aForm){
		return null;
	}
	
	/**
	 * Used to do an address Validation check on the service provider data
	 * @param aForm
	 * @return
	 */
	public static RequestEvent getServProvAddrValidationEvent(ServiceProviderCSCDForm aForm){
		return null;
	}
	
	/**
	 * Method gets the request event to save a service provider and populates it from form data
	 * @param aForm
	 * @return
	 */
	public static RequestEvent getServProvSaveEvent(ServiceProviderCSCDForm aForm){
		SaveServiceProviderEvent myServProvEvent=new SaveServiceProviderEvent();
		myServProvEvent.setServiceProviderId(aForm.getServiceProviderId());
		myServProvEvent.setServiceProviderName(aForm.getName());
		myServProvEvent.setStartDate(aForm.getStartDate());
		myServProvEvent.setInHouse(aForm.isInHouse());
		myServProvEvent.setStatusCode(aForm.getStatusId());
		myServProvEvent.setIfasNumber(aForm.getIfasNumber());
		myServProvEvent.setPhoneNumber(aForm.getPhoneNumber().getPhoneNumber());
		myServProvEvent.setExtension(aForm.getPhoneNumber().getExtension());
		myServProvEvent.setFaxNumber(aForm.getFaxNumber().getPhoneNumber());
		myServProvEvent.setWebsite(aForm.getWebsite());
		myServProvEvent.setEmailAddress(aForm.getEmail());
		myServProvEvent.setFtpAddress(aForm.getFtp());
		myServProvEvent.setComments(aForm.getComments());
		if (!aForm.isInHouse()) {
			aForm.getBillingAddress().setValidated(aForm.getBillingAddressStatus());
			if ( "".equals( aForm.getBillingAddressStatus() ) || aForm.getBillingAddressStatus() == null ) {
				aForm.getBillingAddress().setValidated("U");
			}
			myServProvEvent.setBillingAddress(aForm.getBillingAddress());
		}
		aForm.getMailingAddress().setValidated(aForm.getMailingAddressStatus());
		if ( "".equals( aForm.getMailingAddressStatus() ) || aForm.getMailingAddressStatus() == null ) {
			aForm.getMailingAddress().setValidated("U");
		}
		myServProvEvent.setShippingAddress(aForm.getMailingAddress());
		myServProvEvent.setFaithBased(aForm.getIsFaithBased());
		return myServProvEvent;
	}
	
	
	/*
	 * Method gets a service provider response event and populates the service provider form from it
	 */
	public static void setServProvFormFromRespEvent(ServiceProviderCSCDForm aForm, CSServiceProviderResponseEvent myEvent){
		aForm.clear();
		aForm.setServiceProviderId(Integer.toString(myEvent.getServiceProviderId()));
		aForm.setBillingAddress(UIUtil.getUIAddressObjFromIAddress(myEvent.getBillingAddress()));
		aForm.setBillingAddressStatus(myEvent.getBillingAddress().getValidated());
		aForm.setComments(myEvent.getComments());
		ArrayList contacts=new ArrayList();
		aForm.setContacts(contacts);
		if(myEvent.getContacts()!=null && myEvent.getContacts().size()>0){
			for(int loopX=0; loopX<myEvent.getContacts().size(); loopX++){
				ResponseEvent myContactEvent=(ResponseEvent)myEvent.getContacts().get(loopX);
				Object obj=AdminServiceProviderHelper.getContactLightBeanFromRespEvt(myContactEvent);
				if(obj!=null)
					contacts.add(obj);
			}
		}
		Collections.sort((List)contacts);
		ArrayList programs=new ArrayList();
		aForm.setPrograms(programs);
		if(myEvent.getPrograms()!=null && myEvent.getPrograms().size()>0){
			for(int loopX=0; loopX<myEvent.getPrograms().size(); loopX++){
				ResponseEvent myProgramEvent=(ResponseEvent)myEvent.getPrograms().get(loopX);
				Object obj=AdminServiceProviderHelper.getProgramLightBeanFromRespEvt(myProgramEvent);
				if(obj!=null)
					programs.add(obj);
			}
		}
		Collections.sort((List)programs);
		aForm.setEmail(myEvent.getEmailAddress());
		aForm.setFaxNumber(new PhoneNumber(myEvent.getFaxNumber()));
		aForm.setFtp(myEvent.getFtpAddress());
		aForm.setIfasNumber(myEvent.getIfasNumber());
		aForm.setInHouse(myEvent.isInHouse());
		aForm.setIsFaithBased(myEvent.isFaithBased());
		aForm.setMailingAddress(UIUtil.getUIAddressObjFromIAddress(myEvent.getShippingAddress()));
		aForm.setMailingAddressStatus(myEvent.getShippingAddress().getValidated());
		aForm.setName(myEvent.getServiceProviderName());
		aForm.setPhoneNumber(new PhoneNumber(myEvent.getPhoneNumber()));
		aForm.setStartDate(myEvent.getStartDate());
		aForm.setStatusId(myEvent.getServiceProviderStatus());	
		aForm.setWebsite(myEvent.getWebsite());
		UIUtil.setAddressDescComponents((Address)aForm.getBillingAddress());
		UIUtil.setAddressDescComponents((Address)aForm.getMailingAddress());
		aForm.setMailingAddressStatus(aForm.getMailingAddress().getValidated());
		aForm.setBillingAddressStatus(aForm.getBillingAddress().getValidated());
	
	}
	
	/**
	 * REturns the ID of the last admin contact
	 * @param aContacts
	 * @return
	 */
	public static boolean hasAdminContact(List aContacts){
		if(aContacts==null || aContacts.size()<1){
			return false;
		}
		else{
			for(int loopX=0; loopX<aContacts.size();loopX++){
				ServiceProviderLightContactBean myBean=(ServiceProviderLightContactBean)aContacts.get(loopX);
				if(myBean!=null && myBean.getContactStatusId()!=null &&!myBean.getContactStatusId().equals(PDCodeTableConstants.ASP_CS_CONTACT_INACTIVE) && myBean.isAdminContact()){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * REturns the ID of the last admin contact
	 * @param aContacts
	 * @return
	 */
	public static String getLastAdminContact(List aContacts){
		String lastAdminContactId=null;
		if(aContacts==null || aContacts.size()<1){
			
		}
		else{
			for(int loopX=0; loopX<aContacts.size();loopX++){
				ServiceProviderLightContactBean myBean=(ServiceProviderLightContactBean)aContacts.get(loopX);
				if(myBean!=null && myBean.getContactStatusId()!=null &&!myBean.getContactStatusId().equals(PDCodeTableConstants.ASP_CS_CONTACT_INACTIVE) && myBean.isAdminContact()){
					if(lastAdminContactId==null)
						lastAdminContactId=myBean.getContactId();
					else{
						return null;
					}
				}
			}
		}
		return lastAdminContactId;
	}
	
	/*
	 * Method gets the request event for getting the service provider details for a single service provider
	 */
	public static RequestEvent getServProvDetailRequestEvent(ServiceProviderCSCDForm aForm){
		GetCSServiceProviderEvent myServProvEvt=new GetCSServiceProviderEvent();
		myServProvEvt.setServiceProviderId(aForm.getServiceProviderId());
		return myServProvEvt;
	}
	
	/*
	 * Method that will update the service provider form with the latest details given a service provider id
	 */
	public static String updateServiceProviderForm(ServiceProviderCSCDForm aForm){
		String errorMsg=null;
		RequestEvent myEvent=AdminServiceProviderHelper.getServProvDetailRequestEvent(aForm);
		CompositeResponse myResp=MessageUtil.postRequest(myEvent);
		if(myResp!=null){
			IEvent myfinalEvent=MessageUtil.filterComposite(myResp,CSServiceProviderResponseEvent.class);
			if(myfinalEvent!=null){
				CSServiceProviderResponseEvent myFinalResp=(CSServiceProviderResponseEvent)myfinalEvent;
				setServProvFormFromRespEvent(aForm,myFinalResp);
			}
		}
		else{
			errorMsg="Service Provider information not found for service provider id= " + aForm.getServiceProviderId();
		}
		return errorMsg;
	}
	
	
	/**
	 * Updates the Program Form with the latest detials of the given program id
	 * @param aForm
	 * @return error Msg
	 */
	public static String updateProgramForm(ServiceProviderProgramForm aForm){
		String errorMsg=null;
		RequestEvent myEvent=AdminServiceProviderHelper.getProgramDetailRequestEvent(aForm);
		CompositeResponse myResp=MessageUtil.postRequest(myEvent);
		IEvent myfinalEvent=MessageUtil.filterComposite(myResp,CSProgramResponseEvent.class);
		if(myfinalEvent!=null){
			CSProgramResponseEvent myFinalResp=(CSProgramResponseEvent)myfinalEvent;
			setProgramFormFromRespEvent(aForm,myFinalResp);
		}
		else{
			errorMsg= "Program could not be found";
		}
		return errorMsg;
	}
	
	/**
	 * Updatest eh contact form with the latest details for a contact given the contact Id
	 * @param aForm
	 */
	public static String updateContactForm(ServiceProviderContactForm aForm){
		String errorMsg=null;
		RequestEvent myEvent=AdminServiceProviderHelper.getContactDetailRequestEvent(aForm);
		CompositeResponse myResp=MessageUtil.postRequest(myEvent);
		IEvent myfinalEvent=MessageUtil.filterComposite(myResp,CSServiceProviderContactResponseEvent.class);
		if(myfinalEvent!=null){
			CSServiceProviderContactResponseEvent myFinalResp=(CSServiceProviderContactResponseEvent)myfinalEvent;
			setContactFormFromRespEvent(aForm,myFinalResp);
		}
		else{
			errorMsg= "Contact could not be found";
		}
		
		return errorMsg;
	}
	
	/**
	 * Gets the save event for saving a contact
	 * @param aForm
	 * @return
	 */
	public static RequestEvent getContactSaveEvent(ServiceProviderContactForm aForm){
		SaveSPContactEvent myEvent=new SaveSPContactEvent();
		myEvent.setCellNumber(aForm.getCellPhone().getPhoneNumber());
		myEvent.setEmailAddress(aForm.getEmail());
		myEvent.setFaxNumber(aForm.getFax().getPhoneNumber());
		myEvent.setFirstName(aForm.getContactName().getFirstName());
		myEvent.setIsAdminContact(aForm.isAdminContact());
		myEvent.setLastName(aForm.getContactName().getLastName());
		myEvent.setMiddleName(aForm.getContactName().getMiddleName());
		myEvent.setNotes(aForm.getNotes());
		myEvent.setOfficeNumber(aForm.getOfficePhone().getPhoneNumber());
		myEvent.setExtension(aForm.getOfficePhone().getExtension());		
		myEvent.setPagerNumber(aForm.getPager().getPhoneNumber());
		myEvent.setServiceProviderContactId(aForm.getContactId());
		myEvent.setServiceProviderId(aForm.getServiceProviderId());
		myEvent.setJobTitle(aForm.getJobTitle());
		myEvent.setStatusCode(aForm.getContactStatusId());
		return myEvent;
	}
	
	/**
	 * Save event for saving a program
	 * @param aForm
	 * @return
	 */
	public static RequestEvent getProgramSaveEvent(ServiceProviderProgramForm aForm){
		SaveProgramEvent myEvent=new SaveProgramEvent();
		myEvent.setContractProgram(aForm.isContractProgram());
		myEvent.setOfficeHours(aForm.getOfficeHours());
		myEvent.setProgramDescription(aForm.getDescription());
		myEvent.setProgramEndDate(aForm.getProgramEndDate());
		myEvent.setProgramGroupCode(aForm.getProgramGroupId());
		myEvent.setProgramId(aForm.getProgramId());
		myEvent.setProgramIdentifier(aForm.getIdentifier());
		myEvent.setPrice(aForm.getPrice()); 
		myEvent.setOldProgLocations(aForm.getOldProgLocations());
		myEvent.setProgramUnitId(aForm.getSelectedProgramUnit());
		myEvent.setIncarcerationConditionId(aForm.getSelectedIncarcerationCondition());
		ArrayList stringList=new ArrayList();
		if(aForm.getLanguagesOfferedIds()!=null && aForm.getLanguagesOfferedIds().length>0){
			for(int loopX=0;loopX<aForm.getLanguagesOfferedIds().length;loopX++){
				String myId=aForm.getLanguagesOfferedIds()[loopX];
				if(myId!=null && !myId.equals("")){
					stringList.add(myId);
				}
			}
		}
		myEvent.setProgramLanguages(stringList);
		stringList=new ArrayList();
		if(aForm.getSelectedLocationIds()!=null && aForm.getSelectedLocationIds().length>0){
			for(int loopX=0;loopX<aForm.getSelectedLocationIds().length;loopX++){
				String myId=aForm.getSelectedLocationIds()[loopX];
				if(myId!=null && !myId.equals("")){
					stringList.add(myId);
				}
			}
		}
		myEvent.setProgramLocations(stringList);
		stringList=new ArrayList();
		if(aForm.getSelectedProgLocationIds()!=null && aForm.getSelectedProgLocationIds().length>0){
			for(int loopX=0;loopX<aForm.getSelectedProgLocationIds().length;loopX++){
				String myId=aForm.getSelectedProgLocationIds()[loopX];
				if(myId!=null && !myId.equals("")){
					stringList.add(myId);
				}
			}
		}
		myEvent.setSelectedProgLocationIds(stringList);
		myEvent.setProgramName(aForm.getName());
		myEvent.setProgramStartDate(aForm.getProgramStartDate());
		myEvent.setProgramEndDate(aForm.getProgramEndDate());
		myEvent.setProgramSubTypeCode(aForm.getProgramSubTypeId());
		myEvent.setProgramTypeCode(aForm.getProgramTypeId());
		myEvent.setServiceProviderId(aForm.getServiceProviderId());
		myEvent.setSexSpecificCode(aForm.getSexSpecificId());
		myEvent.setProgramStatus(aForm.getChangeToStatusId());
		myEvent.setStatusChangeComments(aForm.getChangeToStatusReason());
		myEvent.setStatusChangeDate(aForm.getChangeToStatusDate());
		return myEvent;
	}
	
	/**
	 * Gets the detail (view) request event for a contact given a contact id
	 * @param aForm
	 * @return
	 */
	public static RequestEvent getContactDetailRequestEvent(ServiceProviderContactForm aForm){
		GetSPContactEvent myEvent = new GetSPContactEvent();
		myEvent.setContactId(aForm.getContactId());
		return myEvent;
	}
	
	/**
	 * Gets the program detail (view) event for a program given a program Id
	 * @param aForm
	 * @return
	 */
	public static RequestEvent getProgramDetailRequestEvent(ServiceProviderProgramForm aForm){
		GetSPProgramEvent myEvent = new GetSPProgramEvent();
		myEvent.setProgramId(aForm.getProgramId());
		return myEvent;
	}
	
	/**
	 * Gets the SP Light Bean for use in the service provider dashboard given a Service Provider 
	 * Response event
	 * @param myEvent
	 * @return
	 */
	public static ServiceProviderLightBean getSPLightBeanFromServProvRespEvt(ResponseEvent myEvent){
		ServiceProviderLightBean myBean=null;
		if(myEvent!=null && myEvent instanceof CSServiceProviderResponseEvent){
			myBean= new ServiceProviderLightBean();
			CSServiceProviderResponseEvent myRespEvt=(CSServiceProviderResponseEvent)myEvent;
			myBean.setProgramIdentifier(myRespEvt.getProgramIdentifier());
			myBean.setProgramName(myRespEvt.getProgramName());
			myBean.setReferralType(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ASP_CS_PROGRAM_GROUP,myRespEvt.getProgramGroupCode())+ " / " + SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.ASP_CS_PROGRAM_TYPE,myRespEvt.getProgramTypeCode()));
			myBean.setServiceProviderId(Integer.toString(myRespEvt.getServiceProviderId()));
			myBean.setServiceProviderInContractProgramId(Boolean.toString(myRespEvt.getHasContractProgram()));
			myBean.setServiceProviderInHouseId(Boolean.toString(myRespEvt.isInHouse()));
			myBean.setServiceProviderName(myRespEvt.getServiceProviderName());
			myBean.setServiceProviderStatusId(myRespEvt.getServiceProviderStatus());
			myBean.setStatusChangeDate(myRespEvt.getStatusChangeDate());

		}
		return myBean;
	}
	
	/**
	 * Updating the service provider Light Contact bean with a contact response event
	 * @param myEvent
	 * @return
	 */
	public static ServiceProviderLightContactBean getContactLightBeanFromRespEvt(ResponseEvent myEvent){
		ServiceProviderLightContactBean myBean=null;
		if(myEvent!=null && myEvent instanceof CSServiceProviderContactResponseEvent){
			myBean= new ServiceProviderLightContactBean();
			CSServiceProviderContactResponseEvent myRespEvt=(CSServiceProviderContactResponseEvent)myEvent;
			myBean.setAdminContact(myRespEvt.getIsAdminContact());
			myBean.setContactEmail(myRespEvt.getEmailAddress());
			myBean.setContactId(myRespEvt.getServiceProviderContactId());
			Name myName=new Name();
			if(myRespEvt.getFirstName()!=null){
				myName.setFirstName(myRespEvt.getFirstName());
			}
			if(myRespEvt.getMiddleName()!=null){
				myName.setMiddleName(myRespEvt.getMiddleName());
			}
			if(myRespEvt.getLastName()!=null){
				myName.setLastName(myRespEvt.getLastName());
			}
			myBean.setContactName(myName);
			PhoneNumber myPhoneNumber=new PhoneNumber("");
			if(myRespEvt.getOfficeNumber()!=null)
				myPhoneNumber.setPhoneNumber(myRespEvt.getOfficeNumber());
			myBean.setContactOfficePhoneNumber(myPhoneNumber);
			myBean.setContactStatusId(myRespEvt.getStatusCode());
			myBean.setServiceProviderId(myRespEvt.getServiceProviderId());
			myBean.setJobTitle(myRespEvt.getJobTitle());
		}
		return myBean;
	}
	
	/**
	 * Updating the sp light program bean with a CSProgramResponseEvent
	 * @param myEvent
	 * @return
	 */
	public static ServiceProviderLightProgramBean getProgramLightBeanFromRespEvt(ResponseEvent myEvent){
		ServiceProviderLightProgramBean myBean=null;
		if(myEvent!=null && myEvent instanceof CSProgramResponseEvent){
			myBean=new ServiceProviderLightProgramBean();
			CSProgramResponseEvent myFinalRespEvt=(CSProgramResponseEvent)myEvent;
			myBean.setProgramGroupId(myFinalRespEvt.getProgramGroupCode());
			myBean.setProgramId(myFinalRespEvt.getProgramId());
			myBean.setProgramIdentifier(myFinalRespEvt.getProgramIdentifier());
			myBean.setProgramName(myFinalRespEvt.getProgramName());
			myBean.setProgramStatusId(myFinalRespEvt.getStatusCode());
			myBean.setServiceProviderId(myFinalRespEvt.getServiceProviderId());
			
		}
		return myBean;
	}
	
	/**
	 * Sets the contact form from a contact response event
	 * @param aForm
	 * @param myEvent
	 */
	public static void setContactFormFromRespEvent(ServiceProviderContactForm aForm, ResponseEvent myEvent){
		if(myEvent!=null && aForm!=null){
			if(myEvent instanceof CSServiceProviderContactResponseEvent){
				PhoneNumber myPhoneNumber=null;
				CSServiceProviderContactResponseEvent myFinalEvt=(CSServiceProviderContactResponseEvent)myEvent;
				aForm.setAdminContact(myFinalEvt.getIsAdminContact());
				myPhoneNumber=new PhoneNumber("");
				if(myFinalEvt.getCellNumber()!=null)
					myPhoneNumber.setPhoneNumber(myFinalEvt.getCellNumber());
				aForm.setCellPhone(myPhoneNumber);
				
				aForm.setContactId(myFinalEvt.getServiceProviderContactId());
				Name myName=new Name();
				if(myFinalEvt.getFirstName()!=null){
					myName.setFirstName(myFinalEvt.getFirstName());
				}
				if(myFinalEvt.getMiddleName()!=null){
					myName.setMiddleName(myFinalEvt.getMiddleName());
				}
				if(myFinalEvt.getLastName()!=null){
					myName.setLastName(myFinalEvt.getLastName());
				}
				aForm.setContactName(myName);
				aForm.setEmail(myFinalEvt.getEmailAddress());
				myPhoneNumber=new PhoneNumber("");
				if(myFinalEvt.getFaxNumber()!=null)
					myPhoneNumber.setPhoneNumber(myFinalEvt.getFaxNumber());
				aForm.setFax(myPhoneNumber);
				aForm.setJobTitle(myFinalEvt.getJobTitle());
				aForm.setNotes(myFinalEvt.getNotes());
				myPhoneNumber=new PhoneNumber("");
				if(myFinalEvt.getPagerNumber()!=null)
					myPhoneNumber.setPhoneNumber(myFinalEvt.getPagerNumber());
				aForm.setPager(myPhoneNumber);
				myPhoneNumber=new PhoneNumber("");
				if(myFinalEvt.getOfficeNumber()!=null)
					myPhoneNumber.setPhoneNumber(myFinalEvt.getOfficeNumber());
				aForm.setOfficePhone(myPhoneNumber);
				aForm.setContactStatusId(myFinalEvt.getStatusCode());
				aForm.setServiceProviderId(myFinalEvt.getServiceProviderId());
			}
		}
	}

	/**
	 * Set available divisions
	 * @param aForm
	 */
	public static void setAvailableDivisions(ServiceProviderProgramForm aForm)
	{
		if (aForm.getAvailableDivisions() == null)
		{
			//retrieve list of divisions if not previously set
			GetDivisionsProgramsForAgencyEvent gEvent = new GetDivisionsProgramsForAgencyEvent();
			gEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());			
		
			GetDivisionForAgencyResponseEvent divisionList = (GetDivisionForAgencyResponseEvent)MessageUtil.postRequest(gEvent, GetDivisionForAgencyResponseEvent.class);
			
			//set divisions collection	
			List divisions = (List)divisionList.getAgencyDivisionsCollection();
			Collections.sort(divisions, GetDivisionForAgencyResponseEvent.divisionNameComparator);
			aForm.setAvailableDivisions(divisions);
		}		
	}//end of set available divisions

	/**
	 * Set incarceration conditions
	 * @param aForm
	 */
	public static void setIncarcerationConditions(ServiceProviderProgramForm aForm)
	{
		if (aForm.getIncarcerationConditions() == null)
		{
				//retrieve group2 shock conditions
			GetSubGroupsEvent group2_shock_cond_event = new GetSubGroupsEvent();
			group2_shock_cond_event.setParentGroupName("SHOCK");
			group2_shock_cond_event.setGroupLevel(2);
			
			List<GroupResponseEvent> group_2_shock_cond_list =
				MessageUtil.postRequestListFilter(
						group2_shock_cond_event, GroupResponseEvent.class);
			
				//set group 2 conditions list
			Collections.sort(group_2_shock_cond_list, GroupResponseEvent.groupNameComparator);
			aForm.setIncarcerationConditions(group_2_shock_cond_list);
		}		
	}
	
	/**
	 * Sets the program form from a program Response Event
	 * @param aForm
	 * @param myEvent
	 */
	public static void setProgramFormFromRespEvent(ServiceProviderProgramForm aForm, ResponseEvent myEvent){
		if(myEvent!=null && aForm!=null){
			if(myEvent instanceof CSProgramResponseEvent){
				CSProgramResponseEvent myFinalEvt=(CSProgramResponseEvent)myEvent;
				aForm.setOfficeHours(myFinalEvt.getOfficeHours());
				aForm.setDescription(myFinalEvt.getProgramDescription());
				aForm.setProgramGroupId(myFinalEvt.getProgramGroupCode());
				aForm.setProgramId(myFinalEvt.getProgramId());
				aForm.setIdentifier(myFinalEvt.getProgramIdentifier());
				aForm.setOldProgLocations(myFinalEvt.getOldProgLocations());
				String[] mySelectedIds=null;
				if(myFinalEvt.getProgramLanguageCodes()!=null && myFinalEvt.getProgramLanguageCodes().size()>0){
					mySelectedIds=(String[])myFinalEvt.getProgramLanguageCodes().toArray(new String[1]);
				}
				aForm.setLanguagesOfferedIds(mySelectedIds);
				mySelectedIds=null;
				if(myFinalEvt.getLocationIds()!=null && myFinalEvt.getLocationIds().size()>0){
					mySelectedIds=(String[])myFinalEvt.getLocationIds().toArray(new String[1]);
				}
				aForm.setSelectedLocationIds(mySelectedIds);
				mySelectedIds=null;
				if(myFinalEvt.getSelectedProglocationIds()!=null && myFinalEvt.getSelectedProglocationIds().size()>0){
					mySelectedIds=(String[])myFinalEvt.getSelectedProglocationIds().toArray(new String[1]);
				}
				aForm.setSelectedProgLocationIds(mySelectedIds);
				aForm.setContractProgram(myFinalEvt.isContractProgram());
				aForm.setName(myFinalEvt.getProgramName());
				aForm.setProgramStartDate(myFinalEvt.getProgramStartDate());
				aForm.setProgramEndDate(myFinalEvt.getProgramEndDate());
				aForm.setProgramSubTypeId(myFinalEvt.getProgramSubtypeCode());
				aForm.setProgramTypeId(myFinalEvt.getProgramTypeCode());
				aForm.setPrice(String.valueOf(myFinalEvt.getPrice()));
				
				// FinalEvt.getReferralTypeCode());
				aForm.setServiceProviderId(myFinalEvt.getServiceProviderId());
				aForm.setSexSpecificId(myFinalEvt.getSexSpecificCode());
				
				
				aForm.setContractProgram(myFinalEvt.isContractProgram());
				
				aForm.setChangeToStatusReason(myFinalEvt.getStatusChangeComments());
				aForm.setChangeToStatusDate(myFinalEvt.getStatusChangeDate());
				aForm.setChangeToStatusId(myFinalEvt.getStatusCode());
				
				aForm.setStatusReason(myFinalEvt.getStatusChangeComments());
				aForm.setStatusDate(myFinalEvt.getStatusChangeDate());
				aForm.setCurrentStatusId(myFinalEvt.getStatusCode());
				aForm.setServiceProviderId(myFinalEvt.getServiceProviderId());
				aForm.setSelectedProgramUnit(myFinalEvt.getProgramUnitId());
				aForm.setSelectedProgramUnitName(myFinalEvt.getProgramUnitName());
				aForm.setSelectedDivision(myFinalEvt.getDivisionId());
				aForm.setSelectedDivisionName(myFinalEvt.getDivisionName());
				aForm.setSelectedIncarcerationCondition(myFinalEvt.getIncarcerationConditionId());
				aForm.setSelectedIncarcerationConditionName(myFinalEvt.getIncarcerationConditionName());
				
					//set available divisions
				setAvailableDivisions(aForm);

				
				if (!StringUtil.isEmpty(aForm.getSelectedProgramUnit()) 
						&& ((aForm.getDivisionProgramUnits() == null) 
								|| aForm.getDivisionProgramUnits().size() == 0))
				{
						//retrieve program units for selected event
					GetProgramUnitsForDivisionEvent 
						get_program_units_event = new GetProgramUnitsForDivisionEvent();
					get_program_units_event.setDivisionId(aForm.getSelectedDivision());
					List program_unit_list = 
						MessageUtil.postRequestListFilter(get_program_units_event, 
															GetProgramUnitResponseEvent.class);
					
					Collections.sort(program_unit_list, 
										GetProgramUnitResponseEvent.programUnitNameComparator);
					
						//set program units on form
					aForm.setDivisionProgramUnits(program_unit_list);
					
				}
				
					//set incarceration conditions
				setIncarcerationConditions(aForm);
				
			}
		}
	}
	
	
	
}// END CLASS
