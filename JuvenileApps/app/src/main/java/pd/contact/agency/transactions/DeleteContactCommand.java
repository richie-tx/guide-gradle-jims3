//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\agency\\transactions\\DeleteContactCommand.java

/*
 * Created on Aug 29, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
 
package pd.contact.agency.transactions;

import java.util.Iterator;

import pd.contact.agency.Department;
import pd.contact.agency.DepartmentContact;
import messaging.agency.DeleteContactEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class DeleteContactCommand implements ICommand
{

	/**
	 * @roseuid 430638CD0313
	 */
	public DeleteContactCommand()
	{
		
	}

	/**
	 * @param event
	 * @roseuid 4306266800F9
	 */
	public void execute(IEvent event)
	{
		DeleteContactEvent departmentEvent = (DeleteContactEvent) event;
		DepartmentContact departmentContact = DepartmentContact.find(departmentEvent.getDepartmentId() + " " + departmentEvent.getContactId());
		if(departmentContact != null){
			departmentContact.delete();	
		}
		// clear from the list
		Department department = Department.find(departmentEvent.getDepartmentId());
		if(department != null){
		    department.removeContacts(departmentContact);
		}
	}

	/**
	 * @param event
	 * @roseuid 4306266800FB
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4306266800FD
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 430626680107
	 */
	public void update(Object anObject)
	{

	}
}