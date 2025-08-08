// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\security\\authentication\\transactions\\ValidateServiceProviderCommand.java

package pd.security.authentication.transactions;

import java.util.Iterator;

import naming.AgencyControllerServiceNames;
import naming.PDContactConstants;

import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.agency.GetDepartmentEvent;
import messaging.authentication.ValidateServiceProviderSecurityEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import pd.security.JIMS2AccountType;
import pd.supervision.administerserviceprovider.InhouseSP_Profile;
import pd.supervision.administerserviceprovider.OutSourcedSP_Profile;
import pd.supervision.administerserviceprovider.ServiceProvider;

public class ValidateServiceProviderSecurityCommand implements ICommand {

	/**
	 * @roseuid 4399C1890093
	 */
	public ValidateServiceProviderSecurityCommand() {

	}

	/**
	 * @param event
	 * @roseuid 439711A801E3
	 */
	public void execute(IEvent event) {
		ValidateServiceProviderSecurityEvent serviceProviderEvent = (ValidateServiceProviderSecurityEvent) event;
		ServiceProvider sp = ServiceProvider.find(serviceProviderEvent.getServiceProviderId());

		String departmentId = sp.getOriginatingDepartment();
		String deptName = getDeptName(departmentId);

		if (sp != null) {
			if (sp.getAdminUserProfileId().equals(sp.getContactUserProfileId())) {
				checkInHouseProfile(serviceProviderEvent.getEmployeeId(), "adminOrContactUserId", sp
						.getServiceProviderName(), deptName, serviceProviderEvent.getServiceProviderId());

			}
			if (sp.getAdminUserProfileId().equalsIgnoreCase(serviceProviderEvent.getUserID())) {
				if (sp.getInHouse())
					checkInHouseProfile(serviceProviderEvent.getEmployeeId(), "adminGenericUserId", sp
							.getServiceProviderName(), deptName, serviceProviderEvent.getServiceProviderId());
				else
					checkOutSourceProfile(serviceProviderEvent.getEmployeeId(), "adminGenericUserId", sp
							.getServiceProviderName(), deptName, serviceProviderEvent.getServiceProviderId());
			} else if (sp.getContactUserProfileId().equalsIgnoreCase(serviceProviderEvent.getUserID())) {
				if (sp.getInHouse())
					checkInHouseProfile(serviceProviderEvent.getEmployeeId(), "contactGenericUserId", sp
							.getServiceProviderName(), deptName, serviceProviderEvent.getServiceProviderId());
				else
					checkOutSourceProfile(serviceProviderEvent.getEmployeeId(), "contactGenericUserId", sp
							.getServiceProviderName(), deptName, serviceProviderEvent.getServiceProviderId());
			}
		}

	}

	private void checkInHouseProfile(String employeeIdFrmEvent, String userIdType, String providerName,
			String deptName, String serviceProviderId) {
		Iterator jims2AccountTypes = JIMS2AccountType.findAll("userAccountOID", employeeIdFrmEvent);
		while (jims2AccountTypes.hasNext()) {
			JIMS2AccountType j2a = (JIMS2AccountType) jims2AccountTypes.next();
			if (j2a.getUserAccountTypeId().equals("S")) {
				ServiceProviderContactResponseEvent contactEvent = new ServiceProviderContactResponseEvent();
				contactEvent.setMessage("error.existing.employeeId");
				contactEvent.setDepartmentName(deptName);
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
				dispatch.postEvent(contactEvent);
			}
		}
		Iterator iter1 = InhouseSP_Profile.findAll("employeeId", employeeIdFrmEvent);
		if (iter1.hasNext()) {
			InhouseSP_Profile inHouse = (InhouseSP_Profile) iter1.next();
			if (inHouse.getServiceProviderId().equals(serviceProviderId)) {
				if (inHouse.getIsAdminContact()) {
					if (userIdType.equalsIgnoreCase("adminGenericUserId")) {

						ServiceProviderContactResponseEvent contactEvent = setInHouseRespEvent(inHouse, providerName);
						IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
						dispatch.postEvent(contactEvent);
					}
					if (userIdType.equalsIgnoreCase("adminOrContactUserId")) {

						ServiceProviderContactResponseEvent contactEvent = setInHouseRespEvent(inHouse, providerName);
						IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
						dispatch.postEvent(contactEvent);
					} else {
						ServiceProviderContactResponseEvent contactEvent = new ServiceProviderContactResponseEvent();
						contactEvent.setMessage("error.contact.genericId");
						IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
						dispatch.postEvent(contactEvent);
					}
				} else {
					if (userIdType.equalsIgnoreCase("adminGenericUserId")) {
						ServiceProviderContactResponseEvent contactEvent = new ServiceProviderContactResponseEvent();
						contactEvent.setMessage("error.admin.genericId");
						IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
						dispatch.postEvent(contactEvent);
					} else {
						ServiceProviderContactResponseEvent contactEvent = setInHouseRespEvent(inHouse, providerName);
						IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
						dispatch.postEvent(contactEvent);
					}
				}
			}
		} else {
			ServiceProviderContactResponseEvent contactEvent = new ServiceProviderContactResponseEvent();
			contactEvent.setMessage("error.invalid.juvEmployeeId");
			contactEvent.setDepartmentName(deptName);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(contactEvent);
		}
	}

	private void checkOutSourceProfile(String employeeIdFrmEvent, String userIdType, String providerName,
			String deptName, String serviceProviderId) {
		Iterator jims2AccountTypes = JIMS2AccountType.findAll("userAccountOID", employeeIdFrmEvent);
		while (jims2AccountTypes.hasNext()) {
			JIMS2AccountType j2a = (JIMS2AccountType) jims2AccountTypes.next();
			if (j2a.getUserAccountTypeId().equals("S")) {
				ServiceProviderContactResponseEvent contactEvent = new ServiceProviderContactResponseEvent();
				contactEvent.setMessage("error.existing.employeeId");
				contactEvent.setDepartmentName(deptName);
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
				dispatch.postEvent(contactEvent);
			}
		}
		Iterator iter1 = OutSourcedSP_Profile.findAll("employeeId", employeeIdFrmEvent);
		if (iter1.hasNext()) {
			OutSourcedSP_Profile outSource = (OutSourcedSP_Profile) iter1.next();
			if (outSource.getServiceProviderId().equals(serviceProviderId)) {
				if (outSource.getIsAdminContact()) {
					if (userIdType.equalsIgnoreCase("adminGenericUserId")) {
						ServiceProviderContactResponseEvent contactEvent = setOutSourceRespEvent(outSource,
								providerName);
						IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
						dispatch.postEvent(contactEvent);

					} else {
						ServiceProviderContactResponseEvent contactEvent = new ServiceProviderContactResponseEvent();
						contactEvent.setMessage("error.contact.genericId");
						IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
						dispatch.postEvent(contactEvent);
					}
				} else {
					if (userIdType.equalsIgnoreCase("adminGenericUserId")) {
						ServiceProviderContactResponseEvent contactEvent = new ServiceProviderContactResponseEvent();
						contactEvent.setMessage("error.admin.genericId");
						IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
						dispatch.postEvent(contactEvent);
					} else {
						ServiceProviderContactResponseEvent contactEvent = setOutSourceRespEvent(outSource,
								providerName);
						IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
						dispatch.postEvent(contactEvent);
					}
				}
			}
		} else {
			ServiceProviderContactResponseEvent contactEvent = new ServiceProviderContactResponseEvent();
			contactEvent.setMessage("error.invalid.juvEmployeeId");
			contactEvent.setDepartmentName(deptName);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(contactEvent);
		}
	}

	private ServiceProviderContactResponseEvent setOutSourceRespEvent(OutSourcedSP_Profile outSource,
			String providerName) {
		ServiceProviderContactResponseEvent contactEvent = new ServiceProviderContactResponseEvent();
		contactEvent.setEmail(outSource.getEmail());
		contactEvent.setWorkPhone(outSource.getPhoneNum());
		contactEvent.setMessage("");
		contactEvent.setFirstName(outSource.getFirstName());
		contactEvent.setMiddleName(outSource.getMiddleName());
		contactEvent.setLastName(outSource.getLastName());
		contactEvent.setProviderName(providerName);
		return contactEvent;
	}

	private ServiceProviderContactResponseEvent setInHouseRespEvent(InhouseSP_Profile inHouse, String providerName) {
		ServiceProviderContactResponseEvent contactEvent = new ServiceProviderContactResponseEvent();
		contactEvent.setEmail(inHouse.getEmail());
		contactEvent.setWorkPhone(inHouse.getPhoneNum());
		contactEvent.setMessage("");
		contactEvent.setFirstName(inHouse.getFirstName());
		contactEvent.setMiddleName(inHouse.getMiddleName());
		contactEvent.setLastName(inHouse.getLastName());
		contactEvent.setProviderName(providerName);
		return contactEvent;
	}

	private String getDeptName(String deptId) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetDepartmentEvent event = (GetDepartmentEvent) EventFactory
				.getInstance(AgencyControllerServiceNames.GETDEPARTMENT);
		event.setDepartmentId(deptId);
		dispatch.postEvent(event);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		DepartmentResponseEvent responseEvent = (DepartmentResponseEvent) MessageUtil.filterComposite(
				compositeResponse, DepartmentResponseEvent.class);
		if (responseEvent != null) {
			return responseEvent.getDepartmentName();
		}
		return null;
	}

	/**
	 * @param event
	 * @roseuid 439711A801E5
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 439711A801E7
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param updateObject
	 * @roseuid 4399C18900A2
	 */
	public void update(Object updateObject) {

	}
}
