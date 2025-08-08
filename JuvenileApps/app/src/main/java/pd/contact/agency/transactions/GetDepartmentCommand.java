//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\agency\\transactions\\GetDepartmentCommand.java
/*
 * Created on Aug 29, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
 
package pd.contact.agency.transactions;

import java.util.Collection;
import java.util.Iterator;

import naming.PDAddressConstants;
import naming.PDSecurityConstants;
import naming.ResponseLocatorConstants;
import messaging.agency.GetDepartmentEvent;
import messaging.contact.agency.reply.DepartmentContactResponseEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.address.PDAddressHelper;
import pd.common.ResponseCommonUtil;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.contact.PDContactHelper;
import pd.contact.agency.Department;
import pd.contact.agency.DepartmentContact;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class GetDepartmentCommand extends ResponseCommonUtil implements ICommand
{

	/**
	 * @roseuid 42E67E5F009E
	 */
	public GetDepartmentCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42E65EA60130
	 */
	public void execute(IEvent event)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetDepartmentEvent departmentEvent = (GetDepartmentEvent) event;
		ResponseContextFactory respFac = new ResponseContextFactory();

		String departmentId = departmentEvent.getDepartmentId();
		if (departmentId == null || departmentId.equals(""))
		{
			throw new IllegalArgumentException("Department Id cannot be blank or null.");
		}
		Department department = Department.find(departmentId);
		
		if(department != null){
		    // post department
			this.postDepartment(department,respFac,dispatch);

			if (departmentEvent.isGetAddressAndContact())
			{
				// post Billing Addresses
				PDAddressHelper.postAddress(department.getBillingAddress(), PDAddressConstants.ADDRESS_EVENT_TOPIC);
			
				// post mailing Address
				PDAddressHelper.postAddress(department.getMailingAddress(), PDAddressConstants.ADDRESS_EVENT_TOPIC);
		
				// post physical address
				PDAddressHelper.postAddress(department.getAddress(), PDAddressConstants.ADDRESS_EVENT_TOPIC);
		
				// post contact list
				this.postDepartmentContactList(department.getContacts(),respFac,dispatch);
			}
		}
	}

	/**
	 * @param contacts
	 * @param respFac
	 * @param dispatch
	 */
	private void postDepartmentContactList(Collection contacts, ResponseContextFactory respFac, IDispatch dispatch) {
		ResponseCreator dcCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.DEPARTMENT_CONTACT_RESPONSE_LOCATOR,respFac);
		Iterator contactsIter = contacts.iterator();
		while (contactsIter.hasNext())
		{
			DepartmentContact contact = (DepartmentContact) contactsIter.next();
			if (contact != null)
			{
				DepartmentContactResponseEvent resp = (DepartmentContactResponseEvent) dcCreator.create(contact);
				
				if (resp.getContactTypeId() != null
					&& PDSecurityConstants.DEAPRTMENT_SETCIC_CONTACTTYPE.equals(
							resp.getContactTypeId()))
				{
					resp.setTopic(
						PDSecurityConstants.DEPARTMENT_SETCIC_CONTACT_LISTITEM_EVENT_TOPIC);
				}
				else
				{
					resp.setTopic(PDSecurityConstants.DEPARTMENT_CONTACT_LISTITEM_EVENT_TOPIC);
				}
				dispatch.postEvent(resp);
			}
		}
	}

	/**
	 * @param department
	 * @param respFac
	 * @param dispatch
	 */
	private void postDepartment(Department department, ResponseContextFactory respFac, IDispatch dispatch) {
		ResponseCreator dCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.DEPARTMENT_RESPONSE_LOCATOR,respFac);
		DepartmentResponseEvent deptResponseEvent = (DepartmentResponseEvent) dCreator.create(department);
		dispatch.postEvent(deptResponseEvent);
	}
	
	/**
	 * @param event
	 * @roseuid 42E65EA60132
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42E65EA60134
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42E65EA6013E
	 */
	public void update(Object anObject)
	{

	}
}
