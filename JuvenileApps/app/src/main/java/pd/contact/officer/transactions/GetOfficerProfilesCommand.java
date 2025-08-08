//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\officer\\transactions\\GetOfficerProfilesCommand.java

package pd.contact.officer.transactions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.agency.GetASADepartmentsEvent;
import messaging.agency.GetDepartmentsEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.officer.GetOfficerProfilesEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.security.Constraint;
import mojo.km.security.User;
import naming.AgencyControllerServiceNames;
import pd.contact.agency.Department;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.security.PDSecurityHelper;
import pd.transferobjects.helper.DepartmentHelper;

/**
 * @author mchowdhury
 * @description Get Officer profile To change the template for this generated
 *              type comment go to Window&gt;Preferences&gt;Java&gt;Code
 *              Generation&gt;Code and Comments
 */

public class GetOfficerProfilesCommand implements ICommand
{

    /**
     * @roseuid 42E67C2602B0
     */
    public GetOfficerProfilesCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42E65EA70239
     */
    public void execute(IEvent event)
    {
	GetOfficerProfilesEvent officerEvent = (GetOfficerProfilesEvent) event;

	if (PDSecurityHelper.isUserASA())
	{
	    //this.processASASearchRequest(officerEvent); 87191. DO not worry about asa.
	}
	else
	{
	    //this.processNonASARequests(officerEvent);
	    this.sendResponseEventsToUI(officerEvent);
	}
    }

    /**
     * @param officerEvent
     */
    private void processNonASARequests(GetOfficerProfilesEvent officerEvent)
    {
	String departmentName = officerEvent.getDepartmentName();
	GetDepartmentsEvent departEvent = new GetDepartmentsEvent();

	Object obj = DepartmentHelper.getDepartments(departEvent.getDepartmentId(), departEvent.getDepartmentName(), departEvent.getAgencyId());
	if (obj != null)
	{
	    if (obj instanceof Department)
	    {
		Department dept = (Department) obj;
		if (dept != null)
		{
		    officerEvent.setDepartmentId(dept.getDepartmentId());
		    this.sendResponseEventsToUI(officerEvent);
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
			    officerEvent.setDepartmentId(department.getDepartmentId());
			    this.sendResponseEventsToUI(officerEvent);
			}
		    }
		}
	    }
	}

	//87191
	/*if (departmentName != null && !(departmentName.equals("")) && !(departmentName.equals("*")))
	{
		departEvent.setDepartmentName(departmentName);
		departEvent.setAgencyId(officerEvent.getAgencyId());
		Iterator deptIter = Department.findAll(departEvent);
		while (deptIter.hasNext())
		{
			Department department = (Department) deptIter.next();
			officerEvent.setDepartmentId(department.getDepartmentId());
			this.sendResponseEventsToUI(officerEvent);
		}
	}
	else
	{
		if (officerEvent.getAgencyId() != null && !(officerEvent.getAgencyId().equals("")))
		{
			departEvent.setAgencyId(officerEvent.getAgencyId());
			Iterator agencyDeptIter = Department.findAll(departEvent);
			while (agencyDeptIter.hasNext())
			{
				Department department = (Department) agencyDeptIter.next();
				officerEvent.setDepartmentId(department.getDepartmentId());
				this.sendResponseEventsToUI(officerEvent);
			}
		}
		else
		{   
			if (officerEvent.getDepartmentId() == null){
				officerEvent.setDepartmentId("");
			}
			
			this.sendResponseEventsToUI(officerEvent);
		}
	}*/
    }

    /**
     * 87191
     * 
     * @param officerEvent
     */
    /*private void processASASearchRequest(GetOfficerProfilesEvent officerEvent)
    {
    	Map departmentMap = new HashMap();
    	User user = User.find(PDSecurityHelper.getLogonId());
    	if (user != null)
    	{
    		Collection constraints = user.getConstraintsByConstrainerType(Department.class);
    		Iterator constraintsIterator = constraints.iterator();
    		while (constraintsIterator.hasNext())
    		{
    			Constraint constraint = (Constraint) constraintsIterator.next();
    			departmentMap.put(constraint.getConstrainerId(), constraint.getConstrainerId());
    		}
    	} //87191

    	GetDepartmentsEvent getDeptsEvent =	(GetDepartmentsEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENTS);
    	getDeptsEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
    	Iterator departments = Department.findAll(getDeptsEvent);
    	StringBuffer departmentIds = new StringBuffer();
    	while (departments.hasNext())
    	{
    		Department department = (Department) departments.next();
    		if (departmentMap.containsKey(department.getDepartmentId()))
    		{
    			if (officerEvent.getDepartmentName() == null || officerEvent.getDepartmentName().trim().equals(""))
    			{
    				officerEvent.setDepartmentId(department.getDepartmentId());
    				this.sendResponseEventsToUI(officerEvent);
    			}
    			else
    			{
    				departmentIds.append(department.getDepartmentId());
    			}
    		}
    	}

    	if (officerEvent.getDepartmentName() != null && !(officerEvent.getDepartmentName().trim().equals("")))
    	{
    		GetASADepartmentsEvent aSAEvent = new GetASADepartmentsEvent();
    		aSAEvent.setDepartmentId(departmentIds.toString());
    		aSAEvent.setDepartmentName(officerEvent.getDepartmentName());
    		Iterator aSADeptIter = Department.findAll(aSAEvent);
    		while (aSADeptIter.hasNext())
    		{
    			Department department = (Department) aSADeptIter.next();
    			officerEvent.setDepartmentId(department.getDepartmentId());
    			this.sendResponseEventsToUI(officerEvent);
    		} //87191
    	}
    }*///87191

    /**
     * @param officerEvent
     */
    private void sendResponseEventsToUI(GetOfficerProfilesEvent officerEvent)
    {
	MetaDataResponseEvent metaData = (MetaDataResponseEvent) OfficerProfile.findMeta(officerEvent);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	if (metaData.getCount() > 2000)
	{
	    CountInfoMessage infoEvent = new CountInfoMessage();
	    //       	infoEvent.setMessage("Record count exceeded - total records found = " + metaData.getCount());
	    infoEvent.setCount(metaData.getCount());
	    dispatch.postEvent(infoEvent);
	}
	else
	{
	    Iterator iter = OfficerProfile.findAll(officerEvent);
	    while (iter.hasNext())
	    {
		OfficerProfile officerProfile = (OfficerProfile) iter.next();
		OfficerProfileResponseEvent officerProfileResponseEvent = PDOfficerProfileHelper.getThinOfficerProfileResponseEvent(officerProfile);
		dispatch.postEvent(officerProfileResponseEvent);
	    }
	}
    }

    /**
     * @param event
     * @roseuid 42E65EA7023B
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 42E65EA7023D
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 42E65EA7023F
     */
    public void update(Object anObject)
    {

    }
}