//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\agency\\transactions\\UpdateDepartmentCommand.java

/*
 * Created on Aug 29, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
 
package pd.contact.agency.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.address.AddressRequestEvent;
import messaging.agency.UpdateContactEvent;
import messaging.agency.UpdateDepartmentEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import naming.PDSecurityConstants;
import pd.address.Address;
import pd.address.PDAddressHelper;
import pd.contact.agency.Department;
import pd.contact.agency.DepartmentContact;

public class UpdateDepartmentCommand implements ICommand
{

	/**
	 * @roseuid 430638D20054
	 */
	public UpdateDepartmentCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4306266B00CC
	 */
	public void execute(IEvent event)
	{
		UpdateDepartmentEvent updateEvent = (UpdateDepartmentEvent) event;
		Department department = Department.find(updateEvent.getDepartmentId());
		
		if(department != null){
			department.setDepartment(updateEvent);
			this.updateAddress(updateEvent,department);
			this.updateContacts(updateEvent,department);
			department.setContacts(null);	
		}else{
		   department = new Department();
		   department.setDepartment(updateEvent);
		  // department.createOID();// 87191
		   this.updateAddress(updateEvent,department);
		   this.updateContacts(updateEvent,department);
		   department.setContacts(null);
		}	
	}

	/**
	 * @param updateEvent
	 * @param department
	 */
	private void updateAddress(UpdateDepartmentEvent updateEvent, Department department)
	{
		Collection addressRequests = MessageUtil.compositeToCollection(updateEvent, AddressRequestEvent.class);
		Iterator iter = addressRequests.iterator();
		while(iter.hasNext()){
			AddressRequestEvent addressRequestEvent = (AddressRequestEvent) iter.next();
			String addressTypeId = addressRequestEvent.getAddressTypeId();
		    if(addressTypeId != null && addressTypeId.equalsIgnoreCase(PDSecurityConstants.DEPARTMENT_BILLINGADDRESS_TYPE)){
		    	Address billingAddress = department.getBillingAddress();
				
		    	if(billingAddress == null){
		    		billingAddress = new Address();   	
		    	}
				billingAddress = PDAddressHelper.getAddress(addressRequestEvent,billingAddress);  
				billingAddress.setOID(department.getDepartmentId().toUpperCase() + " " + addressTypeId);
			    department.setBillingAddress(billingAddress);
		    }else if(addressTypeId != null && addressTypeId.equalsIgnoreCase(PDSecurityConstants.DEPARTMENT_MAILINGADDRESS_TYPE)){   	
				Address mailingAddress = department.getMailingAddress();
				
				if(mailingAddress == null){
					mailingAddress = new Address();   	
				}
				mailingAddress = PDAddressHelper.getAddress(addressRequestEvent,mailingAddress);  
				mailingAddress.setOID(department.getDepartmentId().toUpperCase() + " " + addressTypeId);
			    department.setMailingAddress(mailingAddress);
		    }else if(addressTypeId != null && addressTypeId.equalsIgnoreCase(PDSecurityConstants.DEPARTMENT_PHYSICALADDRESS_TYPE)){   	
				Address physicalAddress = department.getAddress();
				
				if(physicalAddress == null){
					physicalAddress = new Address();   	
				}				
				physicalAddress = PDAddressHelper.getAddress(addressRequestEvent,physicalAddress); 
				physicalAddress.setOID(department.getDepartmentId().toUpperCase() + " " + addressTypeId);
			    department.setAddress(physicalAddress);
		    }
		}
	}

	/**
	 * @param updateEvent
	 */
	private void updateContacts(UpdateDepartmentEvent updateEvent, Department department)
	{
		Collection contactRequests = MessageUtil.compositeToCollection(updateEvent, UpdateContactEvent.class);
		
		Iterator iter = contactRequests.iterator();
		while(iter.hasNext()){
			UpdateContactEvent contactRequestEvent = (UpdateContactEvent) iter.next();
			if(contactRequestEvent != null){
			   DepartmentContact departmentContact = null;
			   
			   String contactId = contactRequestEvent.getContactId();
			   
			   if(contactId != null && contactId.equals("") == false) 
			   {			   
			   		departmentContact = DepartmentContact.find(contactRequestEvent.getDepartmentId() + " " + contactId);
			   }
			   
			   if(departmentContact == null){
				  departmentContact = new DepartmentContact();
			   }
			//   departmentContact.setDepartmentContact(contactRequestEvent); 87191
			   if(contactRequestEvent.isDeletable()){
				   departmentContact.delete();
				   department.removeContacts(departmentContact);
			   }else if(departmentContact.getOID() == null){
				   department.insertContacts(departmentContact);
			   }
			}
		}

	}
	
	/**
	 * @param errorKey
	 */
	private void sendDuplicateRecordErrorResponseEvent(String errorKey)
	{
		DuplicateRecordErrorResponseEvent errorEvent = new DuplicateRecordErrorResponseEvent();
		errorEvent.setMessage(errorKey);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(errorEvent);
	}


	/**
	 * @param event
	 * @roseuid 4306266B00D9
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4306266B00DB
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 4306266B00DD
	 */
	public void update(Object anObject)
	{

	}
}