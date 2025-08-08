/*
 * Created on September 13, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.contact.agency.transactions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import naming.AgencyControllerServiceNames;
import naming.ResponseLocatorConstants;
import pd.common.ResponseCommonUtil;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.contact.PDContactHelper;
import pd.contact.agency.Department;
import messaging.agency.GetASADepartmentsEvent;
import messaging.agency.GetDepartmentsEvent;
import messaging.agency.GetDepartmentsForASAEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.security.Constraint;
import mojo.km.security.User;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetASADepartmentsCommand extends ResponseCommonUtil implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetASADepartmentsEvent deptEvent = (GetASADepartmentsEvent) event;
		ResponseContextFactory respFac = new ResponseContextFactory();
		ResponseCreator dCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.DEPARTMENT_RESPONSE_LOCATOR,respFac);
		/*Iterator iter = Department.findAll(deptEvent); //87191
		while (iter.hasNext())
		{
			Department department = (Department) iter.next();
			if(department != null){
				DepartmentResponseEvent departmentResponseEvent = (DepartmentResponseEvent) dCreator.createThin(department);
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		    	dispatch.postEvent(departmentResponseEvent);
			}
		}*/ //87191
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
	}
}