/*
 * Created on Jun 23, 2005
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
import messaging.agency.GetDepartmentsEvent;
import messaging.agency.GetDepartmentsForASAEvent;
import messaging.contact.agency.reply.DepartmentContactResponseEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.security.Constraint;
import mojo.km.security.User;

/**
 * @author dnikolis
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetDepartmentsForASACommand extends ResponseCommonUtil implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetDepartmentsForASAEvent deptEvent = (GetDepartmentsForASAEvent) event;
		ResponseContextFactory respFac = new ResponseContextFactory();
		ResponseCreator dCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.DEPARTMENT_RESPONSE_LOCATOR,respFac);

		
		//Get departments for the ASA and put them in a map
		Map departmentMap = new HashMap();
	/*	User user = User.find(deptEvent.getLogonId()); 87191
		if (user != null)
		{
			Collection constraints = user.getConstraintsByConstrainerType(Department.class);
			Iterator constraintsIterator = constraints.iterator();
			while (constraintsIterator.hasNext())
			{
				Constraint constraint = (Constraint) constraintsIterator.next();
				departmentMap.put(constraint.getConstrainerId(),constraint.getConstrainerId());
			}
		}*/
		
		GetDepartmentsEvent getDeptsEvent =	(GetDepartmentsEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENTS);
		getDeptsEvent.setAgencyId(deptEvent.getAgencyId());
		/*Iterator departments = Department.findAll(getDeptsEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while (departments.hasNext())
		{
			Department department = (Department) departments.next();
			if (departmentMap.containsKey(department.getDepartmentId()))
			{								
				DepartmentResponseEvent deptResponseEvent = (DepartmentResponseEvent) dCreator.createThin(department);
				dispatch.postEvent(deptResponseEvent);
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