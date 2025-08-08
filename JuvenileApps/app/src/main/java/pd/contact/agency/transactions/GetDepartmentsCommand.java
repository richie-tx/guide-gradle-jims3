
/*
 * Created on May 18, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.contact.agency.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.agency.GetDepartmentsEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.ResponseLocatorConstants;
import pd.common.ResponseCommonUtil;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.contact.agency.Department;
import pd.transferobjects.helper.DepartmentHelper;

/**
 * @author dnikolis To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetDepartmentsCommand extends ResponseCommonUtil implements ICommand
{
    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event) throws Exception
    {
	GetDepartmentsEvent deptEvent = (GetDepartmentsEvent) event;
	ResponseContextFactory respFac = new ResponseContextFactory();
	ResponseCreator dCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.DEPARTMENT_RESPONSE_LOCATOR, respFac);

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	Object obj = DepartmentHelper.getDepartments(deptEvent.getDepartmentId(), deptEvent.getDepartmentName(), deptEvent.getAgencyId());
	if (obj != null)
	{
	    if (obj instanceof Department)
	    {
		Department dept = (Department) obj;
		if (dept != null)
		{
		    DepartmentResponseEvent deptResponseEvent = (DepartmentResponseEvent) dCreator.createThin(dept);
		    dispatch.postEvent(deptResponseEvent);
		}
	    }

	    if (obj instanceof List<?>)
	    {
		List<Department> departments = (List<Department>) obj;
		if (departments != null)
		{
		    Iterator<Department> departmentItr = departments.iterator();
		    while (departmentItr.hasNext())
		    {
			Department department = departmentItr.next();
			if (department != null)
			{
			    DepartmentResponseEvent deptResponseEvent = (DepartmentResponseEvent) dCreator.createThin(department);
			    dispatch.postEvent(deptResponseEvent);
			}
		    }
		}
	    }
	}

	//87191
	/*      MetaDataResponseEvent metaData = (MetaDataResponseEvent) Department.findMeta(deptEvent);
	if (metaData.getCount() > 2000){
		CountInfoMessage infoEvent = new CountInfoMessage();
	//        	infoEvent.setMessage("Record count exceeded - total records found = " + metaData.getCount());
	  	infoEvent.setCount(metaData.getCount());  
		dispatch.postEvent(infoEvent);
	}else{
		Iterator departments = Department.findAll(deptEvent);
		while (departments.hasNext())
		{
			Department department = (Department) departments.next();
			if(department != null){
				DepartmentResponseEvent deptResponseEvent = (DepartmentResponseEvent) dCreator.createThin(department);
				dispatch.postEvent(deptResponseEvent);
			}
		}
		}*/
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