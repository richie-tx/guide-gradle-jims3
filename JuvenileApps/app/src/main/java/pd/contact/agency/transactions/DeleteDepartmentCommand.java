//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\agency\\transactions\\DeleteDepartmentCommand.java

/*
 * Created on Aug 29, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
 
package pd.contact.agency.transactions;

import java.util.Collection;
import java.util.Iterator;

import pd.address.Address;
import pd.contact.Contact;
import pd.contact.agency.Department;
import pd.contact.agency.DepartmentContact;
import messaging.agency.DeleteDepartmentEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * 
 * 
 * @author mchowdhury
 * @description delete an department  
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class DeleteDepartmentCommand implements ICommand
{

	/**
	 * @roseuid 430638CF00D1
	 */
	public DeleteDepartmentCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4306266D007E
	 */
	public void execute(IEvent event)
	{
		DeleteDepartmentEvent departmentEvent = (DeleteDepartmentEvent) event;
		Department department = Department.find(departmentEvent.getDepartmentId());
		if (department != null){
		   // The following line is commented after talking with Robbie, Shirley, and Jason, M204 program will take care of that.
		   // delete the association with contacts
		   //this.deleteContacts(department);
		   // delete the association with mailingAddress
		   this.deleteMailingAddress(department);
           // delete the association with physicalAddress
		   this.deletePhysicalAddress(department);
           // delete the association with mailingAddress
		   this.deleteBillingAddress(department);
		   // delete the Department
		   department.delete();
		}
	}

	/**
	 * @param department
	 */
	private void deleteBillingAddress(Department department)
	{
		Address billingAddress = department.getBillingAddress();
		if(billingAddress != null){
		   billingAddress.delete();
		}
	}

	/**
	 * @param department
	 */
	private void deletePhysicalAddress(Department department)
	{
		Address physicalAddress = department.getAddress();
		if(physicalAddress != null){
		   physicalAddress.delete();
		}
	}

	/**
	 * @param department
	 */
	private void deleteMailingAddress(Department department)
	{
		Address mailingAddress = department.getMailingAddress();
		if(mailingAddress != null){
		   mailingAddress.delete();
		}
	}

	/**
	 * @param department
	 */
	private void deleteContacts(Department department)
	{
		Collection contacts = department.getContacts();
		Iterator iter = contacts.iterator();
		while(iter.hasNext()){
			DepartmentContact contact = (DepartmentContact) iter.next();
			if(contact != null){
			   contact.delete();
			}
		}
		department.getContacts().clear();
	}

	/**
	 * @param event
	 * @roseuid 4306266D0080
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4306266D008B
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 4306266D008D
	 */
	public void update(Object anObject)
	{

	}
}