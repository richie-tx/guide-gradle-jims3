/*
 * Created on Jun 9, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.supervision.administerserviceprovider;

import java.util.HashMap;
import java.util.Iterator;

import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.administerserviceprovider.reply.JuvenileServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.administerserviceprovider.reply.ServiceTypeResponseEvent;
import messaging.codetable.criminal.reply.ServiceTypeCdResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import naming.PDAdministerServiceProviderConstants;

/**
 * @author C_NRaveendran To change the template for this generated type comment
 *         go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 *         Comments
 */
public final class PDAdminsterServiceProviderHelper
{

    /**
     * 
     */
    private PDAdminsterServiceProviderHelper()
    {
	super();
    }

    /**
     * Creates Juvenile Service Provider response event from Juvenile Service
     * Provider entity.
     * 
     * @param JuvenileServiceProvider
     * @return JuvenileServiceProviderResponseEvent
     */
    public static JuvenileServiceProviderResponseEvent getJuvenileServiceProviderResponseEvent(JuvenileServiceProvider juvServiceProvider)
    {
	JuvenileServiceProviderResponseEvent spre = new JuvenileServiceProviderResponseEvent();

	spre.setServiceProviderId(juvServiceProvider.getOID().toString());
	//		spre.setAdminUserProfile(juvServiceProvider.getAdminUserProfile());
	spre.setAdminUserProfileId(juvServiceProvider.getAdminUserProfileId());
	//		spre.setBillingAddress(juvServiceProvider.getBillingAddress());
	spre.setBillingAddressId(juvServiceProvider.getBillingAddressId());
	//		spre.setContactUserProfile(juvServiceProvider.getContactUserProfile());
	spre.setContactUserProfileId(juvServiceProvider.getContactUserProfileId());
	spre.setExtnNum(juvServiceProvider.getExtnNum());
	spre.setFax(juvServiceProvider.getFax());
	spre.setFtpSite(juvServiceProvider.getFtpSite());
	spre.setIfasNumber(juvServiceProvider.getIfasNumber());
	spre.setInactiveDate(juvServiceProvider.getInactiveDate());
	spre.setInactiveReason(juvServiceProvider.getInactiveReason());
	spre.setInHouse(juvServiceProvider.getInHouse());
	//		spre.setMailingAddress(juvServiceProvider.getMailingAddress());
	spre.setMailingAddressId(juvServiceProvider.getMailingAddressId());
	spre.setOriginatingDepartment(juvServiceProvider.getOriginatingDepartment());
	spre.setPhone(juvServiceProvider.getPhone());
	spre.setProviderPrograms(juvServiceProvider.getProviderPrograms());
	spre.setReactivateDate(juvServiceProvider.getReactivateDate());
	//spre.setServer();
	//		spre.setStatus(juvServiceProvider.getStatus());
	spre.setMaxYouth(juvServiceProvider.getMaxYouth()); //Added for US 177341, 171491
	spre.setServiceProviderName(juvServiceProvider.getServiceProviderName());
	spre.setSpProfiles(juvServiceProvider.getSpProfiles());
	spre.setStartDate(juvServiceProvider.getStartDate());
	spre.setStatusId(juvServiceProvider.getStatusId());
	spre.setTopic(PDAdministerServiceProviderConstants.JWSERVICE_PROVIDER_EVENT_TOPIC);
	spre.setWebSite(juvServiceProvider.getWebSite());
	spre.setWorkflowID(juvServiceProvider.getWorkflowID());
	spre.setEmail(juvServiceProvider.getEmail());
	spre.setEmailCheck(juvServiceProvider.isEmailCheck());
	return spre;
    }

    /**
     * @param serviceservice.getServiceType()
     *            != nullservice.getServiceType() !=
     *            nullservice.getServiceType() != nullservice.getServiceType()
     *            != null
     * @return
     */
    public static ServiceTypeResponseEvent getServiceTypeResponseEvent(Service service)
    {
	ServiceTypeResponseEvent sTRvent = new ServiceTypeResponseEvent();
	sTRvent.setServiceTypeId(service.getServiceTypeId());
	pd.codetable.criminal.JuvenileEventTypeCode code = service.getServiceType();
	if (service.getServiceTypeId() != null && !service.getServiceTypeId().equals("") && code != null)
	{
	    sTRvent.setServiceTypeName(code.getDescription());
	}
	return sTRvent;
    }

    public static ServiceTypeCdResponseEvent getServiceTypeCdResponseEvent(Service service)
    {
	ServiceTypeCdResponseEvent sTCdRvent = new ServiceTypeCdResponseEvent();
	//sTCdRvent.setServiceTypeId(service.getServiceTypeId());
	sTCdRvent.setServiceTypeCode(service.getServiceTypeId());
	pd.codetable.criminal.JuvenileEventTypeCode code = service.getServiceType();
	if (service.getServiceTypeId() != null && !service.getServiceTypeId().equals("") && code != null)
	{
	    sTCdRvent.setDescription(code.getDescription());
	}
	return sTCdRvent;
    }

    public static LocationResponseEvent getServiceLocationResponseEvent(JuvenileServiceProvider juvServiceProvider)
    {
	LocationResponseEvent slre = new LocationResponseEvent();

	slre.setLocationId(juvServiceProvider.getLocationId() + "");
	slre.setJuvLocationUnitId(juvServiceProvider.getJuvLocUnitId() + "");
	slre.setLocationName(juvServiceProvider.getLocationName());
	slre.setLocationUnitName(juvServiceProvider.getLocationUnitName());
	slre.setTopic(PDAdministerServiceProviderConstants.JWSERVICE_LOCATION_EVENT_TOPIC);
	slre.setServiceId(juvServiceProvider.getServiceId() + "");
	return slre;
    }

    /**
     * @param in
     * @return
     */
    public static ServiceProviderContactResponseEvent getInHouseContactRespnseEvent(InhouseSP_Profile in)
    {
	ServiceProviderContactResponseEvent cResp = new ServiceProviderContactResponseEvent();
	cResp.setJuvServProviderProfileId(in.getEmployeeId());
	cResp.setLastName(in.getLastName());
	cResp.setFirstName(in.getFirstName());
	cResp.setMiddleName(in.getMiddleName());
	cResp.setId(in.getOID().toString());
	return cResp;
    }

    /**
     * @param out
     * @return
     */
    public static ServiceProviderContactResponseEvent getInHouseContactRespnseEvent(OutSourcedSP_Profile out)
    {
	ServiceProviderContactResponseEvent cResp = new ServiceProviderContactResponseEvent();
	cResp.setJuvServProviderProfileId(out.getEmployeeId());
	cResp.setLastName(out.getLastName());
	cResp.setFirstName(out.getFirstName());
	cResp.setMiddleName(out.getMiddleName());
	cResp.setId(out.getOID().toString());
	return cResp;
    }

    /**
     * @param in
     * @return
     */
    public static ServiceProviderContactResponseEvent getSPContactResponseEvent(SP_Profile in)
    {
	ServiceProviderContactResponseEvent cResp = new ServiceProviderContactResponseEvent();
	cResp.setJuvServProviderProfileId(in.getServiceProviderId());
	cResp.setLastName(in.getLastName());
	cResp.setFirstName(in.getFirstName());
	cResp.setMiddleName(in.getMiddleName());
	cResp.setId(in.getOID().toString());
	return cResp;
    }

    public static void sendServiceLocationResponseEvent(HashMap map)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	Iterator iter = map.values().iterator();
	while (iter.hasNext())
	{
	    LocationResponseEvent serviceLocationResponseEvent = (LocationResponseEvent) iter.next();
	    dispatch.postEvent(serviceLocationResponseEvent);
	}
    }
}
