//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\agency\\transactions\\UpdateContactCommand.java

/*
 * Created on Aug 29, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
 
package pd.contact.agency.transactions;

import messaging.agency.UpdateContactEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.contact.PDContactHelper;
import pd.contact.agency.Department;
import pd.contact.agency.DepartmentContact;

public class UpdateContactCommand implements ICommand
{

	/**
	 * @roseuid 430638D001AB
	 */
	public UpdateContactCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4306266C0230
	 */
	public void execute(IEvent event)
	{
		UpdateContactEvent contactEvent = (UpdateContactEvent) event;
		DepartmentContact departmentContact = DepartmentContact.find(contactEvent.getDepartmentId() + " " + contactEvent.getContactId());
			   
		if(departmentContact == null){
		    departmentContact = new DepartmentContact();
		}
		//departmentContact.setDepartmentContact(contactEvent); 87191
 
		Department department = Department.find(departmentContact.getDepartmentId());
		if(department != null){
		   department.insertContacts(departmentContact);
		}
	}

	/**
	 * @param event
	 * @roseuid 4306266C0232
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4306266C0234
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 4306266C023F
	 */
	public void update(Object anObject)
	{

	}
}