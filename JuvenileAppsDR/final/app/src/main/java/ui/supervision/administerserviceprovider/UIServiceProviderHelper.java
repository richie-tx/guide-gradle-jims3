package ui.supervision.administerserviceprovider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import messaging.address.GetAddressByIdEvent;
import messaging.address.reply.AddressResponseEvent;
import messaging.administerlocation.GetJuvenileLocationEvent;
import messaging.administerlocation.reply.LocationNotificationResponseEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.administerserviceprovider.CreateProviderProgramEvent;
import messaging.administerserviceprovider.CreateServiceEvent;
import messaging.administerserviceprovider.CreateServiceLocationRequestEvent;
import messaging.administerserviceprovider.CreateServiceProviderContactEvent;
import messaging.administerserviceprovider.CreateServiceProviderEvent;
import messaging.administerserviceprovider.GetContactsEvent;
import messaging.administerserviceprovider.GetLocationsByServiceIdEvent;
import messaging.administerserviceprovider.GetProgramByProgramCodeEvent;
import messaging.administerserviceprovider.GetProgramByProgramIdEvent;
import messaging.administerserviceprovider.GetProgramsByProviderIdEvent;
import messaging.administerserviceprovider.GetProviderProgramsByCodeEvent;
import messaging.administerserviceprovider.GetProviderProgramsEvent;
import messaging.administerserviceprovider.GetServiceByServiceIdEvent;
import messaging.administerserviceprovider.GetServiceProviderActiveInstructorsEvent;
import messaging.administerserviceprovider.GetServiceProviderContactsEvent;
import messaging.administerserviceprovider.GetServiceProviderEvent;
import messaging.administerserviceprovider.GetServiceProvidersEvent;
import messaging.administerserviceprovider.GetServicesByProgramEvent;
import messaging.administerserviceprovider.UpdateServiceProviderContactEvent;
import messaging.administerserviceprovider.UpdateServiceProviderStatusRequestEvent;
import messaging.administerserviceprovider.ValidateAdminUserIdEvent;
import messaging.administerserviceprovider.ValidateProgramCodeEvent;
import messaging.administerserviceprovider.ValidateProgramNameEvent;
import messaging.administerserviceprovider.ValidateServiceCodeEvent;
import messaging.administerserviceprovider.ValidateServiceNameEvent;
import messaging.administerserviceprovider.ValidateServiceProviderEvent;
import messaging.administerserviceprovider.reply.JuvenileServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ProgramSourceFundResponseEvent;
import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.administerserviceprovider.reply.ServiceLocationResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderErrorResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import messaging.codetable.GetCodesEvent;
import messaging.codetable.GetServiceTypeCdByGroupEvent;
import messaging.codetable.GetServiceTypeCdEvent;
import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import messaging.codetable.criminal.reply.ServiceTypeCdResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.domintf.IPhoneNumber;
import messaging.contact.to.PhoneNumberBean;
import messaging.error.reply.ErrorResponseEvent;
import messaging.notification.CreateNotificationEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.CodeTableControllerServiceNames;
import naming.PDAdministerServiceProviderConstants;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.PDSecurityConstants;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;
import pd.supervision.administerserviceprovider.InhouseSP_Profile;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.administerserviceprovider.ProgramSourceFund;
import pd.supervision.administerserviceprovider.ProviderProgram;
import pd.supervision.administerserviceprovider.SP_Profile;
import pd.supervision.administerserviceprovider.SearchServiceProvider;
import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.CodeTableHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.UIUtil;
import ui.contact.department.form.DepartmentForm;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administerserviceprovider.form.ServiceProviderForm;

/**
 * @author ugopinath This is a utility class for common operations required in
 *         Service Provider actions.
 */
public final class UIServiceProviderHelper
{
    private static final String MONTH_DAY_YEAR_STR = "MM/dd/yyyy";
    private static final String COMMA_SPACE_STR = ", ";
    private static final String YES_STR = "YES";
    private static final String NO_STR = "NO";

    /*
     * 
     */
    public static CreateServiceProviderEvent getCreateServiceProviderEvent(ServiceProviderForm sp)
    {
	CreateServiceProviderEvent createEvent = (CreateServiceProviderEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.CREATESERVICEPROVIDER);
	createEvent.setServiceProviderName(sp.getProviderName());
	createEvent.setStartDate(DateUtil.stringToDate(sp.getStartDate(), MONTH_DAY_YEAR_STR));
	String inHouse = sp.getIsInHouse().toUpperCase();

	sp.setIsInHouse(inHouse);
	if (sp.getIsInHouse().equalsIgnoreCase(YES_STR))
	{
	    createEvent.setIsInHouse(true);
	}
	else
	{
	    createEvent.setIsInHouse(false);
	}

	createEvent.setAdminLogonId(sp.getAdminContactId());
	//createEvent.setContactLogonId(sp.getContactUserId()); //86318
	createEvent.setPhone(sp.getPhoneNum());
	createEvent.getPhone().setExt(sp.getPhoneNum().getExt());
	createEvent.setFax(sp.getFax());
	createEvent.setIfasNum(sp.getIfasNumber());
	createEvent.setWebSite(sp.getWebSite());
	createEvent.setEmail(sp.getEmail());
	
	String emailCheck = sp.getIsEmailCheck().toUpperCase();

	sp.setIsEmailCheck(emailCheck);
	if (sp.getIsEmailCheck().equalsIgnoreCase(YES_STR))
	{
	    createEvent.setEmailCheck(true);;
	}
	else
	{
	    createEvent.setEmailCheck(false);
	}
	
	if(sp.getFtp() != null && !sp.getFtp().equalsIgnoreCase("null")){
	    createEvent.setFtpSite(sp.getFtp());
	}	
	    
	if (sp.getMaxYouth() != null && sp.getMaxYouth().equalsIgnoreCase(""))
	{
	    createEvent.setMaxYouth(null);
	}else
	{
	    createEvent.setMaxYouth(sp.getMaxYouth());
	}		
	

	UpdateServiceProviderStatusRequestEvent updateSpEvent = new UpdateServiceProviderStatusRequestEvent();
	updateSpEvent.setServiceProviderId(sp.getProviderId());

	if (sp.getActionType().equalsIgnoreCase("createSummary"))
	{
	    IUserInfo user = getCreateUser();
	    createEvent.setUserID(user.getJIMSLogonId());
	    createEvent.setDepartmentId(user.getDepartmentId());
	    createEvent.setStatusId("P");
	    updateSpEvent.setStatusId("P");
	    createEvent.setCreate(true);
	    createEvent.setInactivate(false);
	}
	else
	    if (sp.getActionType().equalsIgnoreCase("updateSummary"))
	    {
		createEvent.setCreate(false);
		createEvent.setInactivate(false);
		createEvent.setServiceProviderId(sp.getProviderId());
		createEvent.setStatusId(sp.getStatusId());
		updateSpEvent.setStatusId(sp.getStatusId());
		createEvent.setDepartmentId(sp.getDepartment());
	    } 
	    else if (sp.getActionType().equalsIgnoreCase("saveReactivatedSP"))
	    {
		createEvent.setCreate(false);
		createEvent.setInactivate(false);
		createEvent.setServiceProviderId(sp.getProviderId());
		createEvent.setStatusId("A");
		updateSpEvent.setStatusId("A");
		updateSpEvent.setStatusChangeDate(DateUtil.getCurrentDate());
		createEvent.setDepartmentId(sp.getDepartment());
	    }
	    else
		if (sp.getActionType().equalsIgnoreCase("inactivateSummary"))
		{
		    createEvent.setCreate(false);
		    createEvent.setInactivate(true);
		    createEvent.setServiceProviderId(sp.getProviderId());
		    createEvent.setStatusId("I");
		    updateSpEvent.setStatusId("I");
		    createEvent.setDepartmentId(sp.getDepartment());
		}

	sp.getMailingAddress().setValidated(sp.getMailingAddrStatus());
	sp.getBillingAddress().setValidated(sp.getBillingAddrStatus());
	createEvent.addRequest(UIUtil.getAddressRequestEvent(sp.getMailingAddress()));
	createEvent.addRequest(UIUtil.getAddressRequestEvent(sp.getBillingAddress()));

	createEvent.addRequest(updateSpEvent);

	return createEvent;
    }

    /*
     * 
     */
    public static CreateProviderProgramEvent getCreateProviderProgramEvent(ServiceProviderForm sp)
    {
	CreateProviderProgramEvent createEvent = (CreateProviderProgramEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.CREATEPROVIDERPROGRAM);

	createEvent.setJuvenileServiceProviderId(sp.getProviderId());
	createEvent.setProgramCode(sp.getCurrentProgram().getProgramCode());
	createEvent.setProgramName(sp.getCurrentProgram().getProgramName());
	createEvent.setTjjdEdiCode(sp.getCurrentProgram().getTjjdEdiCode());
	createEvent.setTransferredProgRef(sp.getCurrentProgram().getTransferredProgRef());
	createEvent.setStateProgramCode(sp.getCurrentProgram().getStateProgramCodeId());
	createEvent.setTargetIntervention(sp.getCurrentProgram().getTargetInterventionId());
	createEvent.setProgramScheduleType(sp.getCurrentProgram().getProgramScheduleTypeId()); //added for U.S #11099
	createEvent.setStartDate(DateUtil.stringToDate(sp.getCurrentProgram().getStartDate(), MONTH_DAY_YEAR_STR));
	createEvent.setEndDate(DateUtil.stringToDate(sp.getCurrentProgram().getEndDate(), MONTH_DAY_YEAR_STR));
	createEvent.setType(sp.getCurrentProgram().getTypeId());
	createEvent.setSubType(sp.getCurrentProgram().getSubTypeId());
	//added for US #11376
	createEvent.setProgramLocation(sp.getCurrentProgram().getProgramLocationCd());
	createEvent.setProgramCategory(sp.getCurrentProgram().getProgramCategoryCd());
	createEvent.setSourceFund(sp.getCurrentProgram().getProgramFundSourceCd());
	createEvent.setFundStartDate(DateUtil.stringToDate(sp.getCurrentProgram().getFundStartDate(), MONTH_DAY_YEAR_STR));
	createEvent.setFundEndDate(DateUtil.stringToDate(sp.getCurrentProgram().getFundEndDate(), MONTH_DAY_YEAR_STR));	
	createEvent.setDiscontinueDate(DateUtil.stringToDate(sp.getCurrentProgram().getDiscontinueDate(), MONTH_DAY_YEAR_STR)); //US 174149
	createEvent.setSupervisionCategory(sp.getCurrentProgram().getSelectedSupervisions());
	if(sp.getCurrentProgram() !=null)
	    {
	    if(!sp.getCurrentProgram().getMaxYouth().isEmpty())//US 190589
		createEvent.setMaxYouth(Integer.parseInt(sp.getCurrentProgram().getMaxYouth())); 
	    else
		createEvent.setMaxYouth(0); 
	    }
	if (sp.getActionType().equalsIgnoreCase("addProgram"))
	{
	    if (sp.getProviderId() != null || (sp.getProviderId().length() > 0))
	    {
		createEvent.setJuvenileServiceProviderId(sp.getProviderId());
	    }

	    IUserInfo user = getCreateUser();
	    createEvent.setUserID(user.getJIMSLogonId());
	    createEvent.setCreate(true);
	    createEvent.setInactivate(false);
	    createEvent.setStatusId("P");
	}
	
	if (sp.getActionType().equalsIgnoreCase("inactivateProgram"))
	{
	    IUserInfo user = getCreateUser();
	    createEvent.setUserID(user.getJIMSLogonId());
	    createEvent.setCreate(false);
	    createEvent.setInactivate(true);
	    createEvent.setProviderProgramId(sp.getCurrentProgram().getProgramId());
	    createEvent.setStatusId("I");
	    
	    if (sp.getCurrentProgram().getCurrentSourceFund() != null)
		createEvent.setOldSourceFundId(sp.getCurrentProgram().getCurrentSourceFund().getProgramSourceFundId());
	}

	if (sp.getActionType().equalsIgnoreCase("updateProgram"))
	{
	    //added for US #11376
	    //check if the program source fund has changed or a fund is being added for the first time
	    if (sp.getCurrentProgram().getCurrentSourceFund() == null || !sp.getCurrentProgram().getProgramFundSourceCd().equals(sp.getCurrentProgram().getCurrentSourceFund().getProgramSourceFundCd()))
	    {
		//new one needs to be created and old one inactivated
		createEvent.setNewSourceFund(true);

	    }
	    if (sp.getCurrentProgram().getCurrentSourceFund() != null)
		createEvent.setOldSourceFundId(sp.getCurrentProgram().getCurrentSourceFund().getProgramSourceFundId());
	    createEvent.setCreate(false);
	    createEvent.setInactivate(false);
	    createEvent.setStatusId(sp.getCurrentProgram().getStatusId());
	}

	createEvent.setDescription(sp.getCurrentProgram().getDescription());
	createEvent.setProviderProgramId(sp.getCurrentProgram().getProgramId());
	createEvent.setProgramID(sp.getCurrentProgram().getProgramID());
	createEvent.setProgramSubTypeId(sp.getCurrentProgram().getTypeProgramCodeId());

	return createEvent;
    }

    /*
     * TODO #86318
     */
    public static CreateServiceProviderContactEvent getCreateProviderContactEvent(ServiceProviderContactResponseEvent resp,ServiceProviderForm sp)
    {
	CreateServiceProviderContactEvent createEvent = (CreateServiceProviderContactEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.CREATESERVICEPROVIDERCONTACT);

	//createEvent.setNotes(sp.getCurrentContact().getNotes());
	/*if (sp.getCurrentContact().getIsAdminContact().equalsIgnoreCase(YES_STR))
	{
	*/    createEvent.setIsAdminContact(true);
	/*}*/
	/*else
	{
	    createEvent.setIsAdminContact(false);
	}*/

	if (sp.getIsInHouse().equalsIgnoreCase(YES_STR) || sp.getIsInHouse().equalsIgnoreCase("Y"))
	{
	    createEvent.setLogonId(resp.getLogonId());
	    createEvent.setInHouse(true);
	    createEvent.setEmployeeId(resp.getEmployeeId());
	}
	else
	{
	    createEvent.setEmployeeId(resp.getEmployeeId());
	    createEvent.setInHouse(false);
	}

	createEvent.setFullName(new Name(resp.getFirstName(),"",resp.getLastName()));
	createEvent.setServiceProviderId(sp.getProviderId());
	IPhoneNumber phone = new PhoneNumberBean(resp.getWorkPhone());
	createEvent.setWorkPhone(phone);
	createEvent.setEmail(resp.getEmail());
	//createEvent.setCellPhone(sp.getCurrentContact().getCellPhone());
	//createEvent.setPager(sp.getCurrentContact().getPager());
	//createEvent.setFax(sp.getCurrentContact().getFax());
	//createEvent.setJobTitle(sp.getCurrentContact().getJobTitle());
	createEvent.setJuvServProvProfId(resp.getJuvServProviderProfileId());
	UpdateServiceProviderStatusRequestEvent updateSpEvent = new UpdateServiceProviderStatusRequestEvent();
	updateSpEvent.setServiceProviderId(sp.getProviderId());//why not createEvent.setServiceProviderId(sp.getProviderId())	
	//updateSpEvent.setStatusId("A");
	// above makes the status of SP A when a new contact is been added. add sp.statuscd which makes wts in SP table - bug 164666
	updateSpEvent.setStatusId(sp.getStatusId());
	/*if (sp.getActionType().equalsIgnoreCase("addContact"))
	{*/
	    createEvent.setCreate(true);
	    createEvent.setInactivate(false);
	    //createEvent.setStatusId("A");
	    ///status should be whats coming from SM not A always.. use resp.isInactivated() bug 164666
	    if(resp.isInactivated())
		createEvent.setStatusId("I");
	    else
		createEvent.setStatusId("A");
	//}
/*	else
	    if (sp.getActionType().equalsIgnoreCase("inactivateContact"))
	    {
		createEvent.setCreate(false);
		createEvent.setInactivate(true);
		createEvent.setStatusId("I");
		createEvent.setJuvServProvProfId(sp.getCurrentContact().getContactId());
	    }
	    else
	    {
		createEvent.setCreate(false);
		createEvent.setInactivate(false);
		createEvent.setStatusId(sp.getCurrentContact().getStatus());
		createEvent.setJuvServProvProfId(sp.getCurrentContact().getContactId());

	    }*/
	IUserInfo user = getCreateUser();
	createEvent.setUserID(user.getJIMSLogonId());

	createEvent.addRequest(updateSpEvent);

	return createEvent;
    }
    
    public static UpdateServiceProviderContactEvent getUpdateProviderContactEvent(ServiceProviderContactResponseEvent resp,ServiceProviderForm sp)
    {
	UpdateServiceProviderContactEvent updateEvent = (UpdateServiceProviderContactEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.UPDATESERVICEPROVIDERCONTACT);//change the servicename
	
	if (sp.getIsInHouse().equalsIgnoreCase(YES_STR) || sp.getIsInHouse().equalsIgnoreCase("Y"))
	{
	    updateEvent.setLogonId(resp.getLogonId());
	    updateEvent.setInHouse(true);	    
	}
	else
	{
	    updateEvent.setInHouse(false);
	}
	updateEvent.setEmployeeId(resp.getEmployeeId());
	updateEvent.setFullName(new Name(resp.getFirstName(),"",resp.getLastName()));
	updateEvent.setServiceProviderId(sp.getProviderId());
	if(resp.getWorkPhone()!=null && !resp.getWorkPhone().isEmpty())
	{
        	IPhoneNumber phone = new PhoneNumberBean(resp.getWorkPhone());
        	updateEvent.setWorkPhone(phone);
	}
	else
	    updateEvent.setWorkPhone(null);
	updateEvent.setEmail(resp.getEmail());//check if it has to be contactemail
	//updateEvent.setEmail(resp.getContactEmail());
	//updateEvent.setJuvServProvProfId(resp.getJuvServProviderProfileId());
	//UpdateServiceProviderStatusRequestEvent updateSpEvent = new UpdateServiceProviderStatusRequestEvent();
	//updateSpEvent.setServiceProviderId(sp.getProviderId());
	//
	if(resp.isInactivated())
	    updateEvent.setStatusId("I");
	else
	    updateEvent.setStatusId("A");
	//IUserInfo user = getCreateUser();
	updateEvent.setLogonId(resp.getLogonId());	
	return updateEvent;
    }

    /*
     * 
     */
    public static CreateServiceEvent getCreateServiceEvent(ServiceProviderForm sp)
    {
	CreateServiceEvent createEvent = (CreateServiceEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.CREATESERVICE);

	ServiceProviderForm.Program.Service service = sp.getCurrentProgram().getProgramService();
	createEvent.setServiceName(service.getServiceName());
	createEvent.setServiceCode(service.getCode());
	String serviceTypeId = getServiceTypeId(sp, service.getType(), true);
	createEvent.setType(serviceTypeId);
	createEvent.setMaxErollment(service.getMaxEnrollment());
	createEvent.setServiceCost(service.getCost());
	createEvent.setServiceId(service.getServiceId());
	createEvent.setDescription(service.getDescription());

	IUserInfo user = getCreateUser();
	createEvent.setUserID(user.getJIMSLogonId());
	createEvent.setProviderProgramId(sp.getCurrentProgram().getProgramId());
	createEvent.setRateId(sp.getCurrentProgram().getProgramService().getCostBasisId());

	Collection coll1 = service.getSelectedLocations();
	Collection coll2 = service.getPreviousLocations();
	UpdateServiceProviderStatusRequestEvent updateSpEvent = new UpdateServiceProviderStatusRequestEvent();
	updateSpEvent.setServiceProviderId(sp.getProviderId());
	updateSpEvent.setStatusId("A");
	createEvent.addRequest(updateSpEvent);
	CreateServiceLocationRequestEvent servL1 = new CreateServiceLocationRequestEvent();

	boolean found = false;
	if (sp.getActionType().equalsIgnoreCase("updateService"))
	{
	    createEvent.setCreate(false);
	    createEvent.setInactivate(false);
	    createEvent.setStatusId(sp.getCurrentProgram().getStatusId());
	    Iterator iter1 = coll1.iterator();
	    Iterator iter2 = coll2.iterator();

	    // go thru previous locations and remove the ones not selected
	    while (iter2.hasNext())
	    {
		LocationResponseEvent prevLocation = (LocationResponseEvent) iter2.next();
		while (iter1.hasNext())
		{
		    LocationResponseEvent currLocation = (LocationResponseEvent) iter1.next();
		    if (currLocation.getJuvLocationUnitId().equals(prevLocation.getJuvLocationUnitId()))
		    {
			found = true;
		    }
		}

		if (!found)
		{
		    servL1 = new CreateServiceLocationRequestEvent();
		    servL1.setServiceId(service.getServiceId());
		    servL1.setJuvLocUnitId(prevLocation.getJuvLocationUnitId());
		    servL1.setJuvLocUnitName(prevLocation.getLocationUnitName());
		    servL1.setDeletable(true);
		    createEvent.addRequest(servL1);
		}

		found = false;
		iter1 = coll1.iterator();
	    }// while

	    found = false;
	    iter2 = coll2.iterator();
	    iter1 = coll1.iterator();

	    // add newly selected locations
	    while (iter1.hasNext())
	    {
		LocationResponseEvent currLocation = (LocationResponseEvent) iter1.next();
		while (iter2.hasNext())
		{
		    LocationResponseEvent prevLocation = (LocationResponseEvent) iter2.next();
		    if (currLocation.getJuvLocationUnitId().equals(prevLocation.getJuvLocationUnitId()))
		    {
			found = true;
		    }
		}

		if (!found)
		{
		    servL1 = new CreateServiceLocationRequestEvent();
		    servL1.setServiceId(service.getServiceId());
		    servL1.setJuvLocUnitId(currLocation.getJuvLocationUnitId());
		    servL1.setDeletable(false);
		    createEvent.addRequest(servL1);
		}

		found = false;
		iter2 = coll2.iterator();
	    }// while
	}
	else
	    if (sp.getActionType().equalsIgnoreCase("inactivateService"))
	    {
		createEvent.setCreate(false);
		createEvent.setInactivate(true);
		createEvent.setStatusId("I");
		createEvent.setServiceProviderId(sp.getProviderId());
	    }
	    else
	    {
		createEvent.setCreate(true);
		createEvent.setInactivate(false);
		createEvent.setStatusId("P");
		Iterator iter3 = coll1.iterator();
		while (iter3.hasNext())
		{
		    servL1 = new CreateServiceLocationRequestEvent();
		    LocationResponseEvent location = (LocationResponseEvent) iter3.next();
		    servL1.setServiceId(service.getServiceId());
		    servL1.setJuvLocUnitId(location.getJuvLocationUnitId());
		    servL1.setDeletable(false);
		    createEvent.addRequest(servL1);
		}
	    }

	return createEvent;
    }

    /**
     * @param serviceProviderForm
     * @param addresses
     */
    public static void setAddressResponseEvents(DepartmentForm departmentForm, Collection addresses)
    {
	if (addresses == null)
	{
	    return;
	}

	AddressResponseEvent addressResponseEvent = null;
	Iterator<AddressResponseEvent> iter = addresses.iterator();
	while (iter.hasNext())
	{
	    if ((addressResponseEvent = iter.next()) != null)
	    {
		if (addressResponseEvent.getAddressTypeId().equals(PDSecurityConstants.DEPARTMENT_BILLINGADDRESS_TYPE))
		{
		    departmentForm.setSetcicBillingAddress(UIUtil.convertAddressResponseEvent(addressResponseEvent));
		}
		else
		    if (addressResponseEvent.getAddressTypeId().equals(PDSecurityConstants.DEPARTMENT_MAILINGADDRESS_TYPE))
		    {
			departmentForm.setMailingAddress(UIUtil.convertAddressResponseEvent(addressResponseEvent));
		    }
		    else
			if (addressResponseEvent.getAddressTypeId().equals(PDSecurityConstants.DEPARTMENT_PHYSICALADDRESS_TYPE))
			{
			    departmentForm.setPhysicalAddress(UIUtil.convertAddressResponseEvent(addressResponseEvent));
			}
	    }
	} // while
    }

    /*
     * 
     */
    public static void setProgramCodes(ServiceProviderForm spForm)
    {
	//bug Fix:13165.Showing only the codes with are active by status
	Collection coll = CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.JUV_PROGRAM_GROUP, true);
	spForm.setTargetInterventionList(coll);

	coll = CodeHelper.getCodes(PDCodeTableConstants.PROGRAM_TYPE, true);
	spForm.setTypeList(coll);

	coll = CodeHelper.getCodes(PDCodeTableConstants.PROGRAM_SUB_TYPE, true);
	spForm.setSubTypeList(coll);

	coll = CodeHelper.getCodes(PDCodeTableConstants.STATE_PROGRAM_CODE, true);
	spForm.setStateProgramCodeList(coll);
	coll = CodeHelper.getCodes(PDCodeTableConstants.TYPE_PROGRAM_CODE, true);
	spForm.setTypeProgramCodeList(coll);
	

	//added for U.S #11099
	coll = CodeHelper.getCodes(PDCodeTableConstants.PROGRAM_SCHEDULE_TYPE, true);
	spForm.setProgramScheduleTypeList(coll);
	//added for U.S #11099
    }

    /*
     * 
     */
    public static void setContactCodes(ServiceProviderForm spForm)
    {
	Collection coll = CodeHelper.getCodes(PDCodeTableConstants.NAME_PREFIX, true);
	spForm.setPrefixList(coll);

	coll = new ArrayList();
	coll = CodeHelper.getCodes(PDCodeTableConstants.NAME_SUFFIX, true);
	spForm.setSuffixList(coll);
    }

    /*
     * 
     */
    public static void setServiceCodes(ServiceProviderForm providerForm)
    {
	Collection coll = CodeHelper.getCodes(PDCodeTableConstants.COST_BASIS, true);
	providerForm.setCostBasisList(coll);

	GetServiceTypeCdByGroupEvent serviceTypeEvent = (GetServiceTypeCdByGroupEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETSERVICETYPECDBYGROUP);

	serviceTypeEvent.setCodeTableName(UIConstants.SERVICE_TYPE);
	serviceTypeEvent.setGroup(UIConstants.PRESCHEDULED_SERVICE_TYPE);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(serviceTypeEvent);

	// Get PD Response Event
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	MessageUtil.processReturnException(dataMap);

	Collection serviceTypeResponses = MessageUtil.compositeToCollection(compositeResponse, ServiceTypeCdResponseEvent.class);
	Collections.sort((List) serviceTypeResponses);
	providerForm.setServiceTypeList(serviceTypeResponses);
    }

    /*
     * 
     */
    public static void setServiceCodesForSearch(ServiceProviderForm providerForm)
    {
	Collection coll = CodeHelper.getCodes(PDCodeTableConstants.COST_BASIS, true);
	providerForm.setCostBasisList(coll);

	GetServiceTypeCdEvent serviceTypeEvent = (GetServiceTypeCdEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETSERVICETYPECD);
	serviceTypeEvent.setCodeTableName("SERVICE_TYPE");
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(serviceTypeEvent);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	MessageUtil.processReturnException(dataMap);

	Collection serviceTypeResponses = MessageUtil.compositeToCollection(compositeResponse, ServiceTypeCdResponseEvent.class);
	Collections.sort((List) serviceTypeResponses);
	providerForm.setServiceTypeList(serviceTypeResponses);
    }

    /*
     * 
     */
    public static String getServiceType(ServiceProviderForm providerForm, String serviceTypeId)
    {
	Collection coll = providerForm.getServiceTypeList();

	Iterator<ServiceTypeCdResponseEvent> iter = coll.iterator();
	while (iter.hasNext())
	{
	    ServiceTypeCdResponseEvent resp = iter.next();
	    if (resp.getServiceTypeId().equals(serviceTypeId))
	    {
		return resp.getDescription();
	    }
	}

	return null;
    }

    /*
     * 
     */
    public static String getServiceTypeCode(ServiceProviderForm providerForm, String serviceType)
    {
	Collection coll = providerForm.getServiceTypeList();

	Iterator<ServiceTypeCdResponseEvent> iter = coll.iterator();
	while (iter.hasNext())
	{
	    ServiceTypeCdResponseEvent resp = iter.next();
	    if (resp.getDescription().equals(serviceType))
	    {
		return resp.getServiceTypeCode();
	    }
	}

	return null;
    }

    /*
     * 
     */
    public static String getServiceTypeId(ServiceProviderForm providerForm, String serviceType, boolean typeFlag)
    {
	Collection coll = providerForm.getServiceTypeList();
	Iterator<ServiceTypeCdResponseEvent> iter = coll.iterator();
	while (iter.hasNext())
	{
	    ServiceTypeCdResponseEvent resp = iter.next();

	    if (!typeFlag)
	    {
		if (resp.getServiceTypeCode().equals(serviceType))
		{
		    return resp.getServiceTypeId();
		}
	    }
	    else
		if (resp.getDescription().equals(serviceType))
		{
		    return resp.getServiceTypeId();
		}
	} //while 

	return null;
    }

    /*
     * 
     */
    public static Collection setServiceLocations(ServiceProviderForm spForm)
    {
	return ComplexCodeTableHelper.getActiveJuvenileLocationUnits();
    }

    /*
     * 
     */
    public static void fillServiceProviderDetails(ServiceProviderForm spForm, JuvenileServiceProviderResponseEvent resp)
    {
	spForm.setProviderName(resp.getServiceProviderName());
	spForm.setProviderId(resp.getServiceProviderId());
	spForm.setStartDate(DateUtil.dateToString(resp.getStartDate(), MONTH_DAY_YEAR_STR));
	spForm.setAdminContactId(resp.getAdminUserProfileId());
	spForm.setContactUserId(resp.getContactUserProfileId());
	spForm.setIfasNumber(resp.getIfasNumber());
	spForm.setPhoneNum(new PhoneNumber(resp.getPhone()));
	spForm.getPhoneNum().setExt(resp.getExtnNum());
	spForm.setFax(new PhoneNumber(resp.getFax()));
	spForm.setWebSite(resp.getWebSite());
	spForm.setFtp(resp.getFtpSite());
	spForm.setStatusId(resp.getStatusId());
	spForm.setDepartment(resp.getOriginatingDepartment());
	spForm.setMaxYouth(resp.getMaxYouth());
	spForm.setEmail(resp.getEmail());

	if (resp.isInHouse())
	{
	    spForm.setIsInHouse(YES_STR);
	}
	else
	{
	    spForm.setIsInHouse(NO_STR);
	}

	if (resp.isEmailCheck())
	{
	    spForm.setIsEmailCheck(YES_STR);
	}
	else
	{
	    spForm.setIsEmailCheck(NO_STR);
	}
	
	AddressResponseEvent addrResp = getAddress(resp.getMailingAddressId());
	setAddress(spForm.getMailingAddress(), addrResp);
	spForm.setMailingAddrStatus(addrResp.getValidated());
	addrResp = getAddress(resp.getBillingAddressId());
	setAddress(spForm.getBillingAddress(), addrResp);
	spForm.setBillingAddrStatus(addrResp.getValidated());
    }

    /*
     * 
     */
    public static IUserInfo getCreateUser()
    {
	ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	IUserInfo userInfo = securityManager.getIUserInfo();

	return userInfo;
    }

    /*
     * 
     */
    public static Collection searchPrograms(ServiceProviderForm sp) throws GeneralFeedbackMessageException
    {
	GetProviderProgramsEvent prEvent = (GetProviderProgramsEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETPROVIDERPROGRAMS);

	prEvent.setProgramName(sp.getCurrentProgram().getProgramName());
	prEvent.setStateProgramCode(sp.getCurrentProgram().getStateProgramCodeId());
	prEvent.setTargetInterventionId(sp.getCurrentProgram().getTargetInterventionId());
	prEvent.setStatusId(sp.getCurrentProgram().getStatusId());
	prEvent.setEndDateFrom(DateUtil.stringToDate(sp.getFrmEndDate(), MONTH_DAY_YEAR_STR));
	prEvent.setEndDateTo(DateUtil.stringToDate(sp.getToEndDate(), MONTH_DAY_YEAR_STR));
	prEvent.setAgencyId(getCreateUser().getAgencyId());

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(prEvent);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	/* Handle error thrown as ErrorResponseEvent from the command;
	 * Expected error: Number of results matching this criteria is greater than 2000.
	 */
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	if (error != null)
	{
	    throw new GeneralFeedbackMessageException(error.getMessage());
	}

	MessageUtil.processReturnException(compositeResponse);
	Collection coll = MessageUtil.compositeToCollection(compositeResponse, ProviderProgramResponseEvent.class);
	Iterator<ProviderProgramResponseEvent> iter = coll.iterator();
	while (iter.hasNext())
	{
	    ProviderProgramResponseEvent prResp = iter.next();
	    String targetInterventionString = CodeHelper.getCodeDescription(PDCodeTableConstants.JUV_PROGRAM_GROUP, prResp.getTargetInterventionId());
	    prResp.setTargetInterventionId(targetInterventionString);
	}
	Collections.sort((ArrayList) coll);

	return coll;
    }

    /*
     * Add program code as search criteria ER JIMS200075756 --- Start
     */
    public static Collection searchProgramsByCode(ServiceProviderForm sp) throws GeneralFeedbackMessageException
    {
	GetProviderProgramsByCodeEvent prEvent = (GetProviderProgramsByCodeEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETPROVIDERPROGRAMSBYCODE);

	prEvent.setAgencyId(getCreateUser().getAgencyId());
	prEvent.setProgramCode(sp.getCurrentProgram().getProgramCode());

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(prEvent);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	/* Handle error thrown as ErrorResponseEvent from the command;
	 * Expected error: Number of results matching this criteria is greater than 2000.
	 */
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	if (error != null)
	{
	    throw new GeneralFeedbackMessageException(error.getMessage());
	}

	MessageUtil.processReturnException(compositeResponse);
	Collection coll = MessageUtil.compositeToCollection(compositeResponse, ProviderProgramResponseEvent.class);
	Iterator<ProviderProgramResponseEvent> iter = coll.iterator();
	while (iter.hasNext())
	{
	    ProviderProgramResponseEvent prResp = iter.next();
	    String targetInterventionString = CodeHelper.getCodeDescription(PDCodeTableConstants.JUV_PROGRAM_GROUP, prResp.getTargetInterventionId());
	    prResp.setTargetInterventionId(targetInterventionString);
	}
	Collections.sort((ArrayList) coll);

	return coll;
    }

    //Add program code as search criteria ER JIMS200075756 --- End

    /*
     * 
     */
    public static Collection getPrograms(ServiceProviderForm sp, String providerId)
    {
	GetProviderProgramsEvent prEvent = (GetProviderProgramsEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETPROVIDERPROGRAMS);
	prEvent.setServiceProviderId(providerId);
	prEvent.setProgramName(sp.getCurrentProgram().getProgramName());
	prEvent.setStateProgramCode(sp.getCurrentProgram().getStateProgramCodeId());
	prEvent.setTargetInterventionId(sp.getCurrentProgram().getTargetInterventionId());
	prEvent.setProgramScheduleTypeId(sp.getCurrentProgram().getProgramScheduleTypeId()); //added for U.S #11099
	prEvent.setStatusId(sp.getCurrentProgram().getStatusId());
	prEvent.setEndDateFrom(DateUtil.stringToDate(sp.getFrmEndDate(), MONTH_DAY_YEAR_STR));
	prEvent.setEndDateTo(DateUtil.stringToDate(sp.getToEndDate(), MONTH_DAY_YEAR_STR));	
	prEvent.setAgencyId(getCreateUser().getAgencyId());

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(prEvent);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	MessageUtil.processReturnException(compositeResponse);
	Collection coll = MessageUtil.compositeToCollection(compositeResponse, ProviderProgramResponseEvent.class);
	Iterator<ProviderProgramResponseEvent> iter = coll.iterator();
	while (iter.hasNext())
	{
	    ProviderProgramResponseEvent prResp = iter.next();
	    String targetInterventionString = CodeHelper.getCodeDescription(PDCodeTableConstants.JUV_PROGRAM_GROUP, prResp.getTargetInterventionId());
	    prResp.setTargetInterventionId(targetInterventionString);
	    //added for U.S #11099
	    String setProgramScheduleType = CodeHelper.getCodeDescription(PDCodeTableConstants.PROGRAM_SCHEDULE_TYPE, prResp.getProgramScheduleTypeId());
	    prResp.setProgramScheduleTypeId(setProgramScheduleType);
	    //added for U.S #11099
	}
	Collections.sort((ArrayList) coll);

	return coll;
    }

    /*
     * 
     */
    public static Collection getPrograms(String providerId)
    {
	GetProviderProgramsEvent prEvent = (GetProviderProgramsEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETPROVIDERPROGRAMS);
	prEvent.setServiceProviderId(providerId);
	prEvent.setProgramName("");
	prEvent.setStateProgramCode("");
	prEvent.setTargetInterventionId("");
	prEvent.setStatusId("");
	prEvent.setEndDateFrom(null);
	prEvent.setEndDateTo(null);
	prEvent.setAgencyId("");

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(prEvent);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	MessageUtil.processReturnException(compositeResponse);
	Collection coll = MessageUtil.compositeToCollection(compositeResponse, ProviderProgramResponseEvent.class);
	Iterator<ProviderProgramResponseEvent> iter = coll.iterator();
	while (iter.hasNext())
	{
	    ProviderProgramResponseEvent prResp = iter.next();
	    String targetInterventionString = CodeHelper.getCodeDescription(PDCodeTableConstants.JUV_PROGRAM_GROUP, prResp.getTargetInterventionId());
	    prResp.setTargetInterventionId(targetInterventionString);
	}
	Collections.sort((ArrayList) coll);

	return coll;
    }
    
    public static Collection<ProviderProgramResponseEvent> getProgramsByServiceProvider(String providerId)
    {
	GetProgramsByProviderIdEvent prEvent = (GetProgramsByProviderIdEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETPROGRAMSBYPROVIDERID);
	prEvent.setJuvServiceProviderId(providerId);
	prEvent.setStatusId(null);
	prEvent.setServer(null);
	prEvent.setTopic(null);
	prEvent.setUserID(null);
	prEvent.setWithUR(false);

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(prEvent);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	MessageUtil.processReturnException(compositeResponse);
	Collection<ProviderProgramResponseEvent> coll = MessageUtil.compositeToCollection(compositeResponse, ProviderProgramResponseEvent.class);
	Iterator<ProviderProgramResponseEvent> iter = coll.iterator();
	while (iter.hasNext())
	{
	    ProviderProgramResponseEvent prResp = iter.next();
	    String targetInterventionString = CodeHelper.getCodeDescription(PDCodeTableConstants.JUV_PROGRAM_GROUP, prResp.getTargetInterventionId());
	    prResp.setTargetInterventionId(targetInterventionString);
	}
	Collections.sort((ArrayList<ProviderProgramResponseEvent>) coll);

	return coll;
    }
    
    public static Collection getActivePrograms(String providerId)
    {
	GetProviderProgramsEvent prEvent = (GetProviderProgramsEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETPROVIDERPROGRAMS);
	prEvent.setServiceProviderId(providerId);
	prEvent.setProgramName("");
	prEvent.setStateProgramCode("");
	prEvent.setTargetInterventionId("");
	prEvent.setStatusId("");
	prEvent.setEndDateFrom(null);
	prEvent.setEndDateTo(null);
	prEvent.setAgencyId("");

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(prEvent);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	MessageUtil.processReturnException(compositeResponse);
	Collection coll = MessageUtil.compositeToCollection(compositeResponse, ProviderProgramResponseEvent.class);

	ArrayList<ProviderProgramResponseEvent> activePrograms = new ArrayList<ProviderProgramResponseEvent>();
	Iterator<ProviderProgramResponseEvent> iter = coll.iterator();
	
	while (iter.hasNext())
	{
	    ProviderProgramResponseEvent prResp = iter.next();	   
	    String targetInterventionString = CodeHelper.getCodeDescription(PDCodeTableConstants.JUV_PROGRAM_GROUP, prResp.getTargetInterventionId());
	    prResp.setTargetInterventionId(targetInterventionString);
	    
	    if(prResp.getProgramStatusId().equalsIgnoreCase("A")){
		activePrograms.add(prResp);
	    }
	}
	
	Collections.sort(activePrograms);

	return activePrograms;
    }
    
    public static Boolean IsActiveProgram(String providerId)
    {
	GetProviderProgramsEvent prEvent = (GetProviderProgramsEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETPROVIDERPROGRAMS);
	prEvent.setServiceProviderId(providerId);
	prEvent.setProgramName("");
	prEvent.setStateProgramCode("");
	prEvent.setTargetInterventionId("");
	prEvent.setStatusId("");
	prEvent.setEndDateFrom(null);
	prEvent.setEndDateTo(null);
	prEvent.setAgencyId("");

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(prEvent);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	MessageUtil.processReturnException(compositeResponse);
	Collection coll = MessageUtil.compositeToCollection(compositeResponse, ProviderProgramResponseEvent.class);

	ArrayList<ProviderProgramResponseEvent> activePrograms = new ArrayList<ProviderProgramResponseEvent>();
	Iterator<ProviderProgramResponseEvent> iter = coll.iterator();
	
	Boolean result = false;
	while (iter.hasNext())
	{
	    ProviderProgramResponseEvent prResp = iter.next();   
	    
	    if(prResp.getProgramStatusId().equalsIgnoreCase("A") && (prResp.getServiceProviderId() != null && prResp.getServiceProviderId().equalsIgnoreCase(providerId))){
		return true;
	    }
	}
	

	return result;
    }

    /*
     * TODO
	88615
     */
    public static Collection<ServiceProviderContactResponseEvent> getContacts(String spId)
    {
	GetContactsEvent contactEvent = (GetContactsEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETCONTACTS);
	contactEvent.setServiceProviderId(spId);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(contactEvent);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	Collection<ServiceProviderContactResponseEvent> contacts = MessageUtil.compositeToCollection(compositeResponse, ServiceProviderContactResponseEvent.class);
	Collections.sort((ArrayList) contacts);

	return contacts;
    }
    
    /**
     * 88615
     * getContactsFromSecurityManager
     * @param spId
     * @return Collection<ServiceProviderContactResponseEvent>
     */
    public static Collection<ServiceProviderContactResponseEvent> getContactsFromSecurityManager(String spId)
    {
	GetServiceProviderContactsEvent reqEvent = (GetServiceProviderContactsEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDERCONTACTS);
	reqEvent.setAdminUserId(spId);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(reqEvent);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	Collection<ServiceProviderContactResponseEvent> contacts = MessageUtil.compositeToCollection(compositeResponse, ServiceProviderContactResponseEvent.class);
	//Collections.sort((ArrayList) contacts);
	
	return contacts;
    }

    /**
     * 88615
     * @param contacts
     * @param sp
     */
    public static void updateContactInJims2FromSM(Collection<ServiceProviderContactResponseEvent> contacts,ServiceProviderForm sp)
    {
	if(contacts!=null)
	{
	    ArrayList SMemployeeids = new ArrayList();
	    Iterator<ServiceProviderContactResponseEvent> contactsItr =contacts.iterator();
	    while(contactsItr.hasNext())
	    {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		//CHECK IF ALREADY EXISTS. Else create new one.
		ServiceProviderContactResponseEvent contact = contactsItr.next();
		Iterator<SP_Profile> profileItr = SP_Profile.findAll("employeeId",contact.getEmployeeId());
		SMemployeeids.add(contact.getEmployeeId());
		if(profileItr.hasNext())
		{
		   //add code to update contacts from SM  --US 122013		    
		    UpdateServiceProviderContactEvent updateEvent = UIServiceProviderHelper.getUpdateProviderContactEvent(contact,sp);
		    dispatch.postEvent(updateEvent);
		    continue;
		}
		
		CreateServiceProviderContactEvent createEvent = UIServiceProviderHelper.getCreateProviderContactEvent(contact,sp);
		dispatch.postEvent(createEvent);
	    }
		//inactivate all other instructors in jims2 table other than the ones from SM--confirmed with Carla		
	    	GetServiceProviderActiveInstructorsEvent spactiveinstructorsEvent = (GetServiceProviderActiveInstructorsEvent)
			EventFactory.getInstance( ServiceProviderControllerServiceNames.GETSERVICEPROVIDERACTIVEINSTRUCTORS ) ;
		spactiveinstructorsEvent.setServiceProviderId( sp.getProviderId()) ;

		Collection<ServiceProviderContactResponseEvent> instructors = MessageUtil.postRequestListFilter( spactiveinstructorsEvent, ServiceProviderContactResponseEvent.class ) ;
		Iterator<ServiceProviderContactResponseEvent> iSPIter =instructors.iterator();
		Home home = new Home();
        	while( iSPIter.hasNext() )
        	{
        	    ServiceProviderContactResponseEvent prof = iSPIter.next();
        	    if(SMemployeeids!=null)
        	    {
                	    if(!SMemployeeids.contains(prof.getJuvServProviderProfileId()))
                	    {
                		InhouseSP_Profile inSP = null;
                		Iterator<InhouseSP_Profile> profItr = InhouseSP_Profile.findAll("employeeId",prof.getJuvServProviderProfileId());
                		//if( inSP!=null ) bug fix for 165801 task 165803
                		while(profItr.hasNext())
                        	{
                		    inSP=profItr.next();
                		    inSP.setStatusId("I");
                		    try 
                		    {
                			home.bind(inSP);
                		    } 
                		    catch (Exception e) {
                			    e.printStackTrace();}
                        	}
                    	    }
        	    }
        	}
	}
    }
    
    /*
     * 
     */
    public static Collection getServicesByProgram(String programId)
    {
	GetServicesByProgramEvent servicesEvent = (GetServicesByProgramEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSERVICESBYPROGRAM);
	servicesEvent.setProviderProgramId(programId);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(servicesEvent);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	Collection services = MessageUtil.compositeToCollection(compositeResponse, ServiceResponseEvent.class);
	sortResults(services, "S");

	return services;
    }

    /*
     * 
     */
    public static ProviderProgramResponseEvent getProgramByCode(String programCode)
    {
	GetProgramByProgramCodeEvent reqEvent = (GetProgramByProgramCodeEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETPROGRAMBYPROGRAMCODE);
	reqEvent.setProgramCode(programCode);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(reqEvent);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	ProviderProgramResponseEvent prog = (ProviderProgramResponseEvent) MessageUtil.filterComposite(compositeResponse, ProviderProgramResponseEvent.class);

	return prog;
    }

    /*
     * 
     */
    public static ServiceResponseEvent getServiceById(String serviceId)
    {
	GetServiceByServiceIdEvent reqEvent = (GetServiceByServiceIdEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSERVICEBYSERVICEID);
	reqEvent.setServiceId(serviceId);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(reqEvent);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	ServiceResponseEvent serv = (ServiceResponseEvent) MessageUtil.filterComposite(compositeResponse, ServiceResponseEvent.class);

	return serv;
    }

    /*
     * 
     */
    public static void fillProgramDetails(ServiceProviderForm sp, ProviderProgramResponseEvent resp)
    {
	ServiceProviderForm.Program pr = sp.getCurrentProgram();
	pr.setDescription(resp.getProgramDescription());

	if (resp.getEndDate() != null)
	{
	    pr.setEndDate(DateUtil.dateToString(resp.getEndDate(), MONTH_DAY_YEAR_STR));
	}
	
	pr.setTjjdEdiCode( resp.getTjjdEdiCode() );
	pr.setProgramCode(resp.getProgramCodeId());
	pr.setProgramId(resp.getProviderProgramId());
	pr.setProgramID(resp.getProgramID());
	pr.setProgramName(resp.getProgramName());

	if (resp.getStartDate() != null)
	{
	    pr.setStartDate(DateUtil.dateToString(resp.getStartDate(), MONTH_DAY_YEAR_STR));
	}
	

	if (resp.getDiscontinueDate() != null)
	{
	    pr.setDiscontinueDate(DateUtil.dateToString(resp.getDiscontinueDate(), MONTH_DAY_YEAR_STR));
	}

	pr.setStateProgramCodeId(resp.getStateProgramCodeId());
	pr.setTypeProgramCodeId(resp.getTypeProgramCodeId());
	pr.setStatusId(resp.getProgramStatusId());

	Collection interventionList = sp.getTargetInterventionList();
	Iterator<CodeResponseEvent> iter = interventionList.iterator();
	while (iter.hasNext())
	{
	    CodeResponseEvent cds = iter.next();
	    if (cds.getDescription().equalsIgnoreCase(resp.getTargetInterventionId()))
	    {
		if (sp.getActionType().equalsIgnoreCase("updateProgram"))
		{
		    pr.setTargetInterventionId(cds.getCode());
		}
		else
		{
		    pr.setTargetInterventionId(cds.getDescription());
		}

		pr.setTargetIntervention(cds.getDescription());
	    }
	}
	//added for U.S #11099
	Collection programScheduleTypeList = sp.getProgramScheduleTypeList();
	Iterator<CodeResponseEvent> programScheduleTypeListItr = programScheduleTypeList.iterator();
	while (programScheduleTypeListItr.hasNext())
	{
	    CodeResponseEvent cds = programScheduleTypeListItr.next();
	    if (cds.getDescription().equalsIgnoreCase(resp.getProgramScheduleTypeId()))
	    {
		if (sp.getActionType().equalsIgnoreCase("updateProgram"))
		{
		    pr.setProgramScheduleTypeId(cds.getCode());
		}
		else
		{
		    pr.setProgramScheduleTypeId(cds.getDescription());
		}

		pr.setProgramScheduleType(cds.getDescription());
	    }
	}//added for U.S #11099

	//US #11376
	pr.setProgramCategoryCd(resp.getProgramCategory());
	pr.setProgramCategoryStr(CodeHelper.getCodeDescription("JUVENILE_PROGRAM_CATEGORY", resp.getProgramCategory()));
	pr.setProgramLocationCd(resp.getProgramLocation());
	pr.setProgramLocationStr(CodeHelper.getCodeDescription("JUVENILE_PROGRAM_LOCATION", resp.getProgramLocation()));
	ArrayList<ProgramSourceFundResponseEvent> sourceFundList = (ArrayList<ProgramSourceFundResponseEvent>) resp.getProgramSourceFundList();
	//go through the source fund and get the latest source fund
	Iterator fundsIter = sourceFundList.iterator();
	ProgramSourceFundResponseEvent tempFundSource = new ProgramSourceFundResponseEvent();
	while (fundsIter.hasNext())
	{
	    ProgramSourceFundResponseEvent fund = (ProgramSourceFundResponseEvent) fundsIter.next();
	    fund.setProgramSourceFund(CodeHelper.getCodeDescription("JUVENILE_SOURCE_FUND", fund.getProgramSourceFundCd()));
	    //get the most recently created fund which is active
	    if ((tempFundSource.getFundEntryDate() == null || tempFundSource.getFundEntryDate().before(fund.getFundEntryDate())))
		tempFundSource = fund;
	}

	pr.setCurrentSourceFund(tempFundSource);
	if (pr.getCurrentSourceFund().getFundStartDate() == null && sp.getActionType().equalsIgnoreCase("updateProgram"))
	    pr.setFundStartDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	else
	{
	    pr.setFundStartDate(DateUtil.dateToString(pr.getCurrentSourceFund().getFundStartDate(), DateUtil.DATE_FMT_1));
	    pr.setFundEndDate(DateUtil.dateToString(pr.getCurrentSourceFund().getFundEndDate(), DateUtil.DATE_FMT_1));
	    pr.setProgramFundSourceCd(pr.getCurrentSourceFund().getProgramSourceFundCd());
	    pr.setProgramFundSourceStr(pr.getCurrentSourceFund().getProgramSourceFund());
	}
	Collections.sort((List) resp.getProgramSourceFundList());
	pr.setFunds(resp.getProgramSourceFundList());
	pr.setTransferredProgRef(resp.getTransferredProgRef());
	pr.setMaxYouth(resp.getMaxYouth());  //added for US 190589
    }

    /*
     * 
     */
    public static void fillServiceDetails(ServiceProviderForm sp, ServiceResponseEvent resp)
    {
	ServiceProviderForm.Program.Service serv = sp.getCurrentProgram().getProgramService();
	serv.setStatusId(resp.getStatusId());
	serv.setServiceName(resp.getServiceName());
	serv.setUpdatedName(resp.getServiceName());
	serv.setCode(resp.getServiceCode());
	serv.setType(resp.getServiceType());

	if (resp.getServiceType() != null)
	{
	    String serviceType = getServiceTypeCode(sp, resp.getServiceType());
	    if (serviceType != null)
	    {
		serv.setTypeId(serviceType);
	    }
	}
	else
	{
	    serv.setTypeId(UIConstants.EMPTY_STRING);
	}

	serv.setMaxEnrollment(resp.getMaxEnrollment());
	String[] temp = new String[50];
	boolean gotLocUnitID = false;

	serv.setLocationNames(null);
	
	Collection previousLocs = new ArrayList();
	if (resp.getLocationName() != null || (resp.getLocationName().length() > 0))
	{
	    StringTokenizer stok = new StringTokenizer(resp.getLocationName(), ",");
	    int count = 0;
	    while (stok.hasMoreTokens())
	    {
		String str = stok.nextToken();
		Collection locations = sp.getServiceLocationNames();
		Iterator<LocationResponseEvent> iter = locations.iterator();
		while (iter.hasNext())
		{
		    LocationResponseEvent locResp = iter.next();
		    if (locResp.getLocationUnitName().trim().equalsIgnoreCase(str.trim()))
		    {
			temp[count] = locResp.getJuvLocationUnitId();
			previousLocs.add(locResp);
			gotLocUnitID = true;
			break;
		    }
		}// while
		++count;
	    }// while has more tokens

	    serv.setPreviousLocations(previousLocs);

	    if (gotLocUnitID)
	    {
		serv.setLocationNames(temp);
	    }
	}// if

	serv.setLocationString(resp.getLocationName());
	serv.setServiceId(resp.getServiceId());
	serv.setCostBasisId(resp.getRateId());
	serv.setDescription(resp.getDescription());
	if (resp.getCost() != 0.0)
	{
	    serv.setDisplayCost(new StringBuilder("$").append(resp.getCost()).append(" /").append(CodeHelper.getCodeDescription("COST_BASIS", serv.getCostBasisId())).toString());
	    serv.setCost(new Double(resp.getCost()).toString());
	}
    }
    public static ServiceResponseEvent setServiceProviderContacts(ServiceProviderForm form)
    {
	GetServiceProviderContactsEvent reqEvent = (GetServiceProviderContactsEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDERCONTACTS);
	reqEvent.setAdminUserId(form.getAdminContactId());
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(reqEvent);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	ServiceResponseEvent serv = (ServiceResponseEvent) MessageUtil.filterComposite(compositeResponse, ServiceResponseEvent.class);
//	UIServiceProviderHelper.fillContactDetails(form.getCurrentContact(), contactResp);
	return serv;
    }
    
    /*
     * 
     */
    public static void fillContactDetails(ServiceProviderForm.Contact con, ServiceProviderContactResponseEvent resp)
    {
	//86318
	
	con.setContactNameStr(new StringBuilder(resp.getLastName()).append(COMMA_SPACE_STR).append(resp.getFirstName()).append(" ").append(resp.getMiddleName()).toString());
	con.setContactName(new Name(resp.getFirstName(), resp.getMiddleName(), resp.getLastName()));

	/*if (resp.isAdminContact())
	{
	    con.setIsAdminContact(YES_STR);
	}
	else
	{
	    con.setIsAdminContact(NO_STR);
	}
*/
	if( resp.isInactivated()){
	    
	    con.setStatus("I");
	}else{
	    
	    con.setStatus("A");
	}
	con.setEmail(resp.getEmail());
	con.setContactEmail(resp.getContactEmail());
//	con.setFax(new PhoneNumber(resp.getFaxNum()));
//	con.setCellPhone(new PhoneNumber(resp.getCellPhone()));
//	con.setJobTitle(resp.getJobTitle());
//	con.setNotes(resp.getNotes());
//	con.setPager(new PhoneNumber(resp.getPager()));

	IPhoneNumber ph = new PhoneNumber(resp.getWorkPhone());
//	ph.setExt(resp.getExtnNum());
	con.setOfficePhone(ph);

//	con.getContactName().setSuffix(resp.getSuffix());
//	con.getContactName().setPrefix(resp.getPrefix());
	con.setContactId(resp.getJuvServProviderProfileId());
    }

    /*
     * 
     */
    public static JuvenileServiceProviderResponseEvent getProvider(String spId)
    {
	GetServiceProviderEvent spEvent = (GetServiceProviderEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDER);
	spEvent.setServiceProviderId(spId);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(spEvent);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	JuvenileServiceProviderResponseEvent resp = (JuvenileServiceProviderResponseEvent) MessageUtil.filterComposite(compositeResponse, JuvenileServiceProviderResponseEvent.class);

	return resp;
    }
    
    public static ServiceProviderResponseEvent getServiceProviderById(ServiceProviderForm aForm)
    {
	ServiceProviderForm spForm = (ServiceProviderForm) aForm;
   	Collection results = new ArrayList();
	GetServiceProvidersEvent spEvent = (GetServiceProvidersEvent)EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDERS);
		spEvent.setServiceProviderName(spForm.getProviderNameQueryString());
		spEvent.setStatusId("");
		IUserInfo user = getCreateUser();
		spEvent.setAgencyId(user.getAgencyId());
		
		if(spForm.getIsInHouse().equals("Y"))
		{
			spEvent.setInHouse(true);
			spEvent.setAllServiceProviders(false);
		}
		else if(spForm.getIsInHouse().equals("N"))
		{
			spEvent.setInHouse(false);
			spEvent.setAllServiceProviders(false);
		}
		else
			spEvent.setAllServiceProviders(true);
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(spEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	
		Collection coll = MessageUtil.compositeToCollection(compositeResponse, ServiceProviderResponseEvent.class);
		Iterator iter = coll.iterator();
		
		ServiceProviderResponseEvent juvServiceProvider = null;
		
		while(iter.hasNext())
		{	    
			ServiceProviderResponseEvent resp = (ServiceProviderResponseEvent)iter.next();	
			
			if(resp != null){
			    	String statusString = CodeHelper.getCodeDescription( "JUV_SERV_PROVIDER_STATUS", resp.getStatusId());
				resp.setStatusId(statusString);
				
				if(resp.getJuvServProviderId() != null && resp.getJuvServProviderId().equals(spForm.getProviderId())){
				    juvServiceProvider = resp;
				    break;
				}
			}
			
		}
	

	return juvServiceProvider;
    }

    /*
     * 
     */
    public static AddressResponseEvent getAddress(String addressId)
    {
	AddressResponseEvent resp = null;

	if (addressId != null && (addressId.length() > 0))
	{
	    GetAddressByIdEvent addressEvent = (GetAddressByIdEvent) EventFactory.getInstance("GetAddressById");
	    addressEvent.setAddressId(addressId);
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    dispatch.postEvent(addressEvent);

	    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	    MessageUtil.processReturnException(compositeResponse);
	    resp = (AddressResponseEvent) MessageUtil.filterComposite(compositeResponse, AddressResponseEvent.class);
	}

	return resp;
    }

    /*
     * 
     */
    public static void fillAddressDetails(Address addr, AddressResponseEvent resp)
    {
	if (resp == null)
	{
	    resp = new AddressResponseEvent();
	}

	addr.setStreetNumber(resp.getStreetNum());
	addr.setStreetName(resp.getStreetName());
	addr.setStreetTypeId(resp.getStreetTypeId());
	addr.setAptNumber(resp.getAptNum());
	addr.setCity(resp.getCity());
	addr.setStateId(resp.getStateId());
	addr.setZipCode(resp.getZipCode());
	addr.setAdditionalZipCode(resp.getAdditionalZipCode());
	addr.setValidated(resp.getValidated());
    }

    /*
     * 87191
     */
/*    public static Collection getAvailableLogons(String departmentId)
    {
	GetAvailableGenericLogonIdsEvent getEvent = (GetAvailableGenericLogonIdsEvent) EventFactory.getInstance(UserControllerServiceNames.GETAVAILABLEGENERICLOGONIDS);
	getEvent.setDepartmentId(departmentId);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(getEvent);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	Collection userIds = MessageUtil.compositeToCollection(compositeResponse, UserResponseEvent.class);

	return userIds;
    }*/

    /*
     * 
     */
    public static String getLocationString(ServiceResponseEvent serviceResp)
    {
	GetLocationsByServiceIdEvent sps = (GetLocationsByServiceIdEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETLOCATIONSBYSERVICEID);
	sps.setServiceId(serviceResp.getServiceId());
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(sps);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	Collection services = MessageUtil.compositeToCollection(compositeResponse, ServiceLocationResponseEvent.class);

	StringBuffer str = new StringBuffer();
	Iterator<ServiceLocationResponseEvent> iter = services.iterator();
	while (iter.hasNext())
	{
	    ServiceLocationResponseEvent loc = iter.next();
	    if (loc != null)
	    {
		str.append(loc.getLocationUnitName().trim());
		if (iter.hasNext())
		{
		    str.append(COMMA_SPACE_STR);
		}
	    }
	}

	return str.toString();
    }

    /*
     * 
     */
    public static Collection getLocations(ServiceResponseEvent serviceResp)
    {
	GetLocationsByServiceIdEvent sps = (GetLocationsByServiceIdEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETLOCATIONSBYSERVICEID);
	sps.setServiceId(serviceResp.getServiceId());
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(sps);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	Collection services = MessageUtil.compositeToCollection(compositeResponse, ServiceLocationResponseEvent.class);
	Collections.sort((List) services);

	return services;
    }

    /*
     * 
     */
    public static ServiceProviderErrorResponseEvent validateProgramCode(ServiceProviderForm sp)
    {
	ValidateProgramCodeEvent validateEvent = (ValidateProgramCodeEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.VALIDATEPROGRAMCODE);
	validateEvent.setProgramCode(sp.getCurrentProgram().getProgramCode().toUpperCase());
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(validateEvent);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	ServiceProviderErrorResponseEvent nameError = (ServiceProviderErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ServiceProviderErrorResponseEvent.class);

	return nameError;
    }

    /*
     * 
     */
    public static ServiceProviderErrorResponseEvent validateProgramName(ServiceProviderForm sp)
    {
	ValidateProgramNameEvent validateEvent = (ValidateProgramNameEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.VALIDATEPROGRAMNAME);

	validateEvent.setProgramName(sp.getCurrentProgram().getProgramName().toUpperCase());
	validateEvent.setServiceProviderId(sp.getProviderId());
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(validateEvent);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	ServiceProviderErrorResponseEvent nameError = (ServiceProviderErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ServiceProviderErrorResponseEvent.class);

	return nameError;
    }

    /*
     * 
     */
    public static ServiceProviderErrorResponseEvent validateServiceCode(ServiceProviderForm sp)
    {
	ValidateServiceCodeEvent validateEvent = (ValidateServiceCodeEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.VALIDATESERVICECODE);

	validateEvent.setServiceCode(sp.getCurrentProgram().getProgramService().getCode().toUpperCase());
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(validateEvent);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	ServiceProviderErrorResponseEvent nameError = (ServiceProviderErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ServiceProviderErrorResponseEvent.class);

	return nameError;
    }

    /**
     * validate AdminUserId
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public static ServiceProviderErrorResponseEvent validateAdminUserId(String adminUserId)
    {
	
	ValidateAdminUserIdEvent validateEvent = (ValidateAdminUserIdEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.VALIDATEADMINUSERID);

	validateEvent.setAdminUserId(adminUserId);

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(validateEvent);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	ServiceProviderErrorResponseEvent nameError = (ServiceProviderErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ServiceProviderErrorResponseEvent.class);

	return nameError;
    }

    /*
     * 
     */
    public static ServiceProviderErrorResponseEvent validateServiceName(ServiceProviderForm sp)
    {
	ValidateServiceNameEvent validateEvent = (ValidateServiceNameEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.VALIDATESERVICENAME);

	validateEvent.setServiceName(sp.getCurrentProgram().getProgramService().getUpdatedName().toUpperCase());

	validateEvent.setServiceProviderId(sp.getProviderId());
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(validateEvent);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	ServiceProviderErrorResponseEvent nameError = (ServiceProviderErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ServiceProviderErrorResponseEvent.class);

	return nameError;
    }

    /*
     * 
     */
    public static ServiceProviderErrorResponseEvent validateSP(String spName)
    {
	ValidateServiceProviderEvent validateEvent = (ValidateServiceProviderEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.VALIDATESERVICEPROVIDER);
	validateEvent.setServiceProviderName(spName);
	ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");

	IUserInfo userInfo = securityManager.getIUserInfo();
	validateEvent.setAgencyId(userInfo.getAgencyId());
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(validateEvent);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	ServiceProviderErrorResponseEvent nameError = (ServiceProviderErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ServiceProviderErrorResponseEvent.class);

	return nameError;
    }

    /*
     * 
     */
    public static Collection sortResults(Collection coll, String searchTypeId)
    {
	Collection temp = new ArrayList();
	Iterator iter = coll.iterator();

	if (searchTypeId.equals("P"))
	{
	    while (iter.hasNext())
	    {
		ProviderProgramResponseEvent prResp = (ProviderProgramResponseEvent) iter.next();
		if (!prResp.getProgramStatusId().equalsIgnoreCase("I"))
		{
		    temp.add((ProviderProgramResponseEvent) prResp);
		}
	    }
	}
	else //86318
	    if (searchTypeId.equals("C"))
	    {
		while (iter.hasNext())
		{
		    ServiceProviderContactResponseEvent conResp = (ServiceProviderContactResponseEvent) iter.next();
		    if (!conResp.isInactivated())				 
		    {
			temp.add((ServiceProviderContactResponseEvent) conResp);
		    }
		}
	    }
	    else
		if (searchTypeId.equals("SV"))
		{
		    while (iter.hasNext())
		    {
			ServiceResponseEvent svResp = (ServiceResponseEvent) iter.next();
			if (!svResp.getServiceProviderStatusId().equalsIgnoreCase("I"))
			{
			    temp.add((ServiceResponseEvent) svResp);
			}
		    }
		}
		else
		    if (searchTypeId.equals("S"))
		    {
			while (iter.hasNext())
			{
			    ServiceResponseEvent servResp = (ServiceResponseEvent) iter.next();
			    if (!servResp.getStatusId().equalsIgnoreCase("I"))
			    {
				temp.add((ServiceResponseEvent) servResp);
			    }
			}
		    }
		    

	if (searchTypeId.equals("P") || searchTypeId.equals("C"))
	{
	    Collections.sort((ArrayList) temp);
	}
	else
	    if (searchTypeId.equals("SV"))
	    {
		Collections.sort((ArrayList) temp, ServiceResponseEvent.providerNameComparator);
	    }
	    else
		if (searchTypeId.equals("S"))
		{
		    Collections.sort((ArrayList) temp, ServiceResponseEvent.serviceNameComparator);
		}

	Collection inactives = new ArrayList();
	iter = coll.iterator();
	while (iter.hasNext())
	{
	    if (searchTypeId.equals("P"))
	    {
		while (iter.hasNext())
		{
		    ProviderProgramResponseEvent prResp = (ProviderProgramResponseEvent) iter.next();
		    if (prResp.getProgramStatusId().equalsIgnoreCase("I"))
		    {
			inactives.add((ProviderProgramResponseEvent) prResp);
		    }
		}
	    }
	    else
		if (searchTypeId.equals("C"))
		{
		    while (iter.hasNext())
		    {
			ServiceProviderContactResponseEvent conResp = (ServiceProviderContactResponseEvent) iter.next();
			if (conResp.isInactivated())			    
			{
			    inactives.add((ServiceProviderContactResponseEvent) conResp);
			}
		    }
		} //86318
		else
		    if (searchTypeId.equals("SV"))
		    {
			while (iter.hasNext())
			{
			    ServiceResponseEvent svResp = (ServiceResponseEvent) iter.next();
			    if (svResp.getServiceProviderStatusId().equalsIgnoreCase("I"))
			    {
				inactives.add((ServiceResponseEvent) svResp);
			    }
			}
		    }
		    else
			if (searchTypeId.equals("S"))
			{
			    while (iter.hasNext())
			    {
				ServiceResponseEvent svResp = (ServiceResponseEvent) iter.next();
				if (svResp.getStatusId().equalsIgnoreCase("I"))
				{
				    inactives.add((ServiceResponseEvent) svResp);
				}
			    }
			}
	}// while

	if (searchTypeId.equals("P") || searchTypeId.equals("C"))
	{
	    Collections.sort((ArrayList) inactives);
	}
	else
	    if (searchTypeId.equals("SV"))
	    {
		Collections.sort((ArrayList) inactives, ServiceResponseEvent.providerNameComparator);
	    }
	    else
		if (searchTypeId.equals("S"))
		{
		    Collections.sort((ArrayList) inactives, ServiceResponseEvent.serviceNameComparator);
		}

	Iterator inactiveIter = inactives.iterator();
	while (inactiveIter.hasNext())
	{
	    if (searchTypeId.equals("P"))
	    {
		temp.add((ProviderProgramResponseEvent) inactiveIter.next());
	    }
	    else
		if (searchTypeId.equals("C"))
		{
		    temp.add((ServiceProviderContactResponseEvent) inactiveIter.next());
		}
		else
		    if (searchTypeId.equals("SV") || searchTypeId.equals("S"))
		    {
			temp.add((ServiceResponseEvent) inactiveIter.next());
		    }
	}

	return temp;
    }

    /*
     * 
     */
    public static void getAddressCodes(ServiceProviderForm sp)
    {
	Collection coll = CodeHelper.getCodes(PDCodeTableConstants.STREET_TYPE, true);
	sp.setStreetTypeList(UIUtil.sortCodesByCodeId(coll));
	coll = CodeHelper.getCodes(PDCodeTableConstants.STATE_ABBR, true);
	sp.setStateList(UIUtil.sortCodesByCodeId(coll));
    }

    /*
     * 
     */
    private static void setAddress(Address spAddr, AddressResponseEvent addrResp)
    {
	if (addrResp == null)
	{
	    addrResp = new AddressResponseEvent();
	}

	spAddr.setStreetNumber(addrResp.getStreetNum());
	spAddr.setStreetName(addrResp.getStreetName());
	spAddr.setStreetTypeId(addrResp.getStreetTypeId());

	if (addrResp.getStreetTypeId() != null)
	{
	    spAddr.setStreetType(CodeHelper.getCodeDescription(PDCodeTableConstants.STREET_TYPE, addrResp.getStreetTypeId()));
	}

	spAddr.setAptNumber(addrResp.getAptNum());
	spAddr.setCity(addrResp.getCity());
	spAddr.setStateId(addrResp.getStateId());
	spAddr.setState(CodeHelper.getCodeDescription(PDCodeTableConstants.STATE_ABBR, addrResp.getStateId()));
	spAddr.setZipCode(addrResp.getZipCode());
	spAddr.setAdditionalZipCode(addrResp.getAdditionalZipCode());
    }

    /*
     * 
     */
    public static void notifyProvider(ServiceProviderForm sp)
    {
	ServiceProviderForm.Program.Service service = sp.getCurrentProgram().getProgramService();

	LocationNotificationResponseEvent nevt = new LocationNotificationResponseEvent();
	nevt.setSubject("SERVICE INACTIVATION");
	nevt.setServiceName(service.getServiceName());
	nevt.setServiceType(service.getType());
	nevt.setUserName(UIUtil.getCurrentUserName());
	nevt.setIdentity(sp.getAdminContactId());
	sendNotification(nevt, UIConstants.EVENT_CANCELLATION_PROVIDER_NOTIFICATION);
    }

    /*
     * 
     */
    public static void sendNotification(LocationNotificationResponseEvent nevt, String topic)
    {
	CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
	notificationEvent.setNotificationTopic(topic);
	notificationEvent.setSubject(nevt.getSubject());
	notificationEvent.addIdentity(UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT, nevt);
	notificationEvent.addContentBean(nevt);
	EventManager.getSharedInstance(EventManager.REQUEST).postEvent(notificationEvent);
    }

    /*
     * 
     */
    public static String getLocationAddress(String locationId)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetJuvenileLocationEvent locationEvent = new GetJuvenileLocationEvent();
	locationEvent.setLocationId(locationId);
	dispatch.postEvent(locationEvent);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	Object obj = MessageUtil.filterComposite(compositeResponse, LocationResponseEvent.class);
	if (obj != null)
	{
	    LocationResponseEvent resp = (LocationResponseEvent) obj;
	    StringBuilder buff = new StringBuilder(resp.getLocationAddress().getStreetNum());
	    buff.append(" ").append(resp.getLocationAddress().getStreetName()).append(" ").append(resp.getLocationAddress().getCity()).append(" ").append(resp.getLocationAddress().getState()).append(" ").append(resp.getLocationAddress().getZipCode());
	    return buff.toString();
	}

	return null;
    }
    
    public static List<CodeResponseEvent> getJuvenileSupervisionCategories()
    {
	List<CodeResponseEvent> supervisionCategories = new ArrayList<CodeResponseEvent>();
	
	CodeHelper.getCodes(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, true);
	
	GetCodesEvent codesRequestEvent = (GetCodesEvent) EventFactory
		.getInstance(CodeTableControllerServiceNames.GETCODES);

        codesRequestEvent.setCodeTableName("SUPERVISION_CATEGORY");
        
        List<CodeResponseEvent> codes = MessageUtil.postRequestListFilter(codesRequestEvent, CodeResponseEvent.class);
        
        List<CodeResponseEvent> newCodes = new ArrayList<CodeResponseEvent>();
        
        List<String> temp = new ArrayList<String>();
        for (int i = 0; i < codes.size(); i++) {
        	CodeResponseEvent cre = (CodeResponseEvent) codes.get(i);
        	
        	if(cre != null && !cre.equals("")){
        	    
        	    if("ACTIVE".equalsIgnoreCase(cre.getStatus()))
        	    {
        		supervisionCategories.add(cre);
        	    }
        	}
        	
        }
	
	return supervisionCategories;
    }
    
    public static ProviderProgramResponseEvent getServiceProviderProgram( String providerProgramId )
	{
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		GetProgramByProgramIdEvent reqEvent = (GetProgramByProgramIdEvent)
				EventFactory.getInstance( ServiceProviderControllerServiceNames.GETPROGRAMBYPROGRAMID ) ;

		reqEvent.setProviderProgramId( providerProgramId ) ;

		dispatch.postEvent( reqEvent ) ;
		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply() ;
		ProviderProgramResponseEvent resp = (ProviderProgramResponseEvent)
				MessageUtil.filterComposite( compositeResponse, ProviderProgramResponseEvent.class ) ;

		return resp ;

	}
    
    public static void populateFormWithSelectedSupervisionCategories(ServiceProviderForm sp)
    {
	if(sp.getCurrentProgram() != null && sp.getCurrentProgram().getTransferredProgRef().equals("1") && sp.getCurrentProgram().getSelectedSupervisionCategories() != null && sp.getCurrentProgram().getSelectedSupervisionCategories().length > 0)
	{		    
	    String codeValues = StringUtils.join(sp.getCurrentProgram().getSelectedSupervisionCategories(), ", ");
	    sp.getCurrentProgram().setSelectedSupervisions(codeValues);
	    
	    Iterator iter =  sp.getCurrentProgram().getSupervisionCategories().iterator();
	    List<CodeResponseEvent> selectedCategories = new ArrayList<CodeResponseEvent>();
		
		while(iter.hasNext())
		{
		    CodeResponseEvent sprvEvent = (CodeResponseEvent) iter.next();
			
		    for(int i = 0; i < sp.getCurrentProgram().getSelectedSupervisionCategories().length; i++){
			
			String selectedItem = sp.getCurrentProgram().getSelectedSupervisionCategories()[i];
			
			if(sprvEvent != null && sprvEvent.getCode() != null && selectedItem != null)
			{				   
				if(sprvEvent.getCode().equals(selectedItem))
				{
				    selectedCategories.add(sprvEvent);
				    break;
				}
			}
		    }
		
		}		
		
		sp.getCurrentProgram().setSupervisionCategories(selectedCategories);
		
	} else if(sp.getCurrentProgram() != null && sp.getCurrentProgram().getTransferredProgRef().equals("0") ){
	    
	    sp.getCurrentProgram().setSupervisionCategories(null);
	    sp.getCurrentProgram().setSelectedSupervisionCategories(null);
	    sp.getCurrentProgram().setSelectedSupervisions(null);
	}
	
    }
    
    
    public static boolean checkAllProgramsInactiveForServiceProvider(String serviceProviderId){
	boolean allInactive = true;
	
	Collection programs = getPrograms(serviceProviderId);
	
	Iterator<ProviderProgramResponseEvent> programsIter = programs.iterator();
	
	while(programsIter != null && programsIter.hasNext()){
	    
	    ProviderProgramResponseEvent spProgram = programsIter.next();
	    
	    if(spProgram != null && (spProgram.getProgramStatusId().equalsIgnoreCase("A") || spProgram.getProgramStatusId().equalsIgnoreCase("P"))){
		allInactive = false;
		return allInactive;
	    }
	}
	
	return allInactive;
    }
    
    public static boolean checkAllServicesInactiveForServiceProvider(String serviceProviderId){
   	boolean allInactive = true;
   	
   	Collection programs = getPrograms(serviceProviderId);
	
	Iterator<ProviderProgramResponseEvent> programsIter = programs.iterator();
	
	while(programsIter != null && programsIter.hasNext()){
	    
	    ProviderProgramResponseEvent spProgram = programsIter.next();
	    
	    Collection services = getServicesByProgram(spProgram.getProviderProgramId());
	    
	    if(services != null)
	    {
		Iterator<ServiceResponseEvent> servicesIter = services.iterator();
		
		while(servicesIter.hasNext()){
	   	    
	   	    ServiceResponseEvent service = servicesIter.next();
	   	    
	   	    if(service != null && (service.getStatusId().equalsIgnoreCase("A") || service.getStatusId().equalsIgnoreCase("P"))){
	   		allInactive = false;
	   		return allInactive;
	   	    }
	   	}
	    }
	}  
   	
   	return allInactive;
    }
    
    public static void updateServiceProviderStatusOnInactivate(String serviceProviderId)
    {
	
	boolean allInactivePrograms = true;
	boolean allInactiveServices = true;
	    
	allInactivePrograms = checkAllProgramsInactiveForServiceProvider(serviceProviderId); 
	allInactiveServices = checkAllServicesInactiveForServiceProvider(serviceProviderId);
	    
	if(allInactivePrograms && allInactiveServices){
	    IHome home = new Home();
	    
	    JuvenileServiceProvider juvServProv = JuvenileServiceProvider.find(new Integer(serviceProviderId).intValue());
	    juvServProv.setStatusId(PDAdministerServiceProviderConstants.PENDING);
	    juvServProv.setStatusChangeDate(DateUtil.getCurrentDate());
		
	    home.bind(juvServProv);
	    
	    System.out.println("Service Provider updated to pending status. Service ProviderId: " + serviceProviderId);
	}
	
	
    }
    
    public static List<ProgramSourceFundResponseEvent> getFundSourcesByFundSource(String fundSource){
	
	Iterator programSourceFundIter = ProgramSourceFund.findAll("programSourceFund", fundSource);
	
	    ArrayList<ProgramSourceFundResponseEvent> programSourceFundList = new ArrayList();
	    ProgramSourceFundResponseEvent fundResp;
	    while(programSourceFundIter.hasNext())
	    {
	    	ProgramSourceFund fund = (ProgramSourceFund)programSourceFundIter.next();
	    	if(fund != null && fund.getFundStatus().equalsIgnoreCase("active"))
	    	{
        	    	fundResp = new ProgramSourceFundResponseEvent();
        	    	fundResp.setFundStartDate(fund.getFundStartDate());
        	    	fundResp.setFundEndDate(fund.getFundEndDate());
        	    	fundResp.setFundEntryDate(fund.getFundEntryDate());
        	    	fundResp.setFundStatus(fund.getFundStatus());
        	    	fundResp.setProvProgramId(fund.getProvProgramId());
        	    	fundResp.setProgramSourceFundId(fund.getOID());
        	    	fundResp.setProgramSourceFundCd(fund.getProgramSourceFund());
        	    	
        	    	programSourceFundList.add(fundResp);
	    	}	    	
	    }
	    
	return programSourceFundList;
    }
    
    public static List<ProviderProgramResponseEvent> getProgramsByFundSource(String fundSource)
    {
	ArrayList<ProviderProgramResponseEvent> providerPrograms = new ArrayList();
	List<ProgramSourceFundResponseEvent> fundSources = getFundSourcesByFundSource(fundSource);
	
	if(fundSources != null && fundSources.size() > 0)
	{
	    Iterator fsIter = fundSources.iterator();
	    
	    while(fsIter != null && fsIter.hasNext())
	    {
		ProgramSourceFundResponseEvent fund = (ProgramSourceFundResponseEvent)fsIter.next();
		
		if(fund != null && fund.getProvProgramId() != null && !"".equals(fund.getProvProgramId())){
		    
		    ProviderProgramResponseEvent program = getFundSourcePrograms(fund.getProvProgramId()); //ProviderProgram.find(fund.getProvProgramId());
		    
		    if(program != null){
			providerPrograms.add(program);
		    }
		}		
	    }
	}
	
	return providerPrograms;	
    }
    
    public static ProviderProgramResponseEvent getFundSourcePrograms(String programId)
    {
	Collection<ProviderProgramResponseEvent> programsList = new ArrayList<ProviderProgramResponseEvent>();
	ProviderProgramResponseEvent program = null;
	
	GetProviderProgramsEvent prEvent = (GetProviderProgramsEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETPROVIDERPROGRAMS);
	prEvent.setProgramId(programId);
	prEvent.setProgramName("");
	prEvent.setStateProgramCode("");
	prEvent.setTargetInterventionId("");
	prEvent.setStatusId("");
	prEvent.setEndDateFrom(null);
	prEvent.setEndDateTo(null);
	prEvent.setAgencyId("");

	IHome home = new Home();
	MetaDataResponseEvent metaData = (MetaDataResponseEvent) home.findMeta(prEvent, SearchServiceProvider.class);

	int totalRecords = metaData.getCount();
	 
	if (totalRecords > PDConstants.SEARCH_LIMIT){
		ErrorResponseEvent errorResp = new ErrorResponseEvent();
		errorResp.setMessage("error.max.limit.exceeded");			
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(errorResp);
	}
	else
	{
		Iterator iter = SearchServiceProvider.findAll("programId", programId); 	//findAll(prEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while(iter.hasNext()){
			SearchServiceProvider serviceProvider = (SearchServiceProvider) iter.next();		
		    ProviderProgramResponseEvent providerProgramResponseEvent = new ProviderProgramResponseEvent();
		    
		    providerProgramResponseEvent.setServiceProviderId(serviceProvider.getJuvenileServiceProviderId());
		    providerProgramResponseEvent.setServiceProviderName(serviceProvider.getServiceProviderName());
		    providerProgramResponseEvent.setInHouse(serviceProvider.getInHouse());
		    providerProgramResponseEvent.setServiceProviderStatusId(serviceProvider.getServiceProviderStatusId());
		    providerProgramResponseEvent.setProgramName(serviceProvider.getProgramName());
		    providerProgramResponseEvent.setProgramCodeId(serviceProvider.getProgramCodeId());
		    providerProgramResponseEvent.setStateProgramCodeId(serviceProvider.getStateProgramCodeId());
		    providerProgramResponseEvent.setTransferredProgRef(serviceProvider.getTransferredProgRef());
		    providerProgramResponseEvent.setProgramScheduleTypeId(serviceProvider.getProgramScheduleTypeId()); //added for U.S #11099
		    providerProgramResponseEvent.setProgramStatusId(serviceProvider.getProgramStatusId());
		    providerProgramResponseEvent.setStartDate(serviceProvider.getStartDate());
		    providerProgramResponseEvent.setEndDate(serviceProvider.getEndDate());
		    providerProgramResponseEvent.setProgramDescription(serviceProvider.getProgramDescription());
		    providerProgramResponseEvent.setProviderProgramId(serviceProvider.getProgramId());
		    providerProgramResponseEvent.setProgramCategory(serviceProvider.getProgramCategory());
		    providerProgramResponseEvent.setProgramLocation(serviceProvider.getProgramLocation());		    
		    providerProgramResponseEvent.setProgramID(serviceProvider.getContractID());
		    providerProgramResponseEvent.setDiscontinueDate(serviceProvider.getDiscontinueDate());
		    providerProgramResponseEvent.setTypeProgramCodeId(serviceProvider.getTypeProgramCodeId());
		    
		    String targetInterventionString = CodeHelper.getCodeDescription( PDCodeTableConstants.JUV_PROGRAM_GROUP, serviceProvider.getTargetInterventionId());
		    providerProgramResponseEvent.setTargetInterventionId(targetInterventionString);
		    
		    program = providerProgramResponseEvent;
 
		}    
	}
	
	return program;
    }
    
    
}
