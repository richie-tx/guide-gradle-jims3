/*
 * Created on Aug 29, 2005
 *
 */
package pd.contact.agency.transactions;

import java.util.Iterator;

import naming.PDSecurityConstants;
import naming.ResponseLocatorConstants;

import messaging.agency.GetDepartmentContactsEvent;
import messaging.contact.agency.reply.DepartmentContactResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.common.ResponseCommonUtil;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.contact.PDContactHelper;
import pd.contact.agency.Department;
import pd.contact.agency.DepartmentContact;

/**
 * @author mchowdhury
 *  
 */
public class GetDepartmentContactsCommand extends ResponseCommonUtil implements ICommand
{

    /**
     * @roseuid 42E67E5F009E
     */
    public GetDepartmentContactsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42E65EA60130
     */
    public void execute(IEvent event)
    {
		GetDepartmentContactsEvent departmentContactsEvent = (GetDepartmentContactsEvent) event;
		Department department = Department.find(departmentContactsEvent.getDepartmentId());
		
		if(department != null){
			this.postDepartmentContacts(department);
		}
    }

    /**
	 * @param department
	 */
	private void postDepartmentContacts(Department department) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		ResponseContextFactory respFac = new ResponseContextFactory();
		ResponseCreator dcCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.DEPARTMENT_CONTACT_RESPONSE_LOCATOR,respFac);
		Iterator contactsIter = department.getContacts().iterator();
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