/*
 * Created on Mar 2, 2005
 */
package pd.contact.agency.transactions;

import java.util.Iterator;

import messaging.agency.GetLawEnforcementDepartmentsEvent;
import pd.contact.agency.Department;
import pd.contact.agency.helper.DepartmentSimpleListBuilder;
import pd.pattern.IBuilder;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dnikolis
 */
public class GetLawEnforcementDepartmentsCommand implements ICommand
{

	/**
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{/* //87191
		GetLawEnforcementDepartmentsEvent agyEvent = (GetLawEnforcementDepartmentsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator departments = Department.findAll(agyEvent);
		IBuilder builder = new DepartmentSimpleListBuilder(departments);
		builder.build();
		dispatch.postEvent((IEvent) builder.getResult());*/		//87191
	}

	/**
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
	}

	/**
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
	}

	/** 
	 * @see mojo.km.context.ICommand#update(Object)
	 */
	public void update(Object updateObject)
	{
	}
}