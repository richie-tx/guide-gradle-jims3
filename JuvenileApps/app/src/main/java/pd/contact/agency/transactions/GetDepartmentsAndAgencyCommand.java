//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\contact\\agency\\transactions\\GetDepartmentsAndAgencyCommand.java

package pd.contact.agency.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import messaging.agency.GetDepartmentsAndAgencyEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.contact.agency.Agency;
import pd.contact.agency.Department;
import pd.transferobjects.helper.AgencyHelper;
import pd.transferobjects.helper.DepartmentHelper;

public class GetDepartmentsAndAgencyCommand implements ICommand
{

    /**
     * @roseuid 442B043900AB
     */
    public GetDepartmentsAndAgencyCommand()
    {

    }

    /**
     * @param event
     * @roseuid 442AFC86010B
     */
    public void execute(IEvent event)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetDepartmentsAndAgencyEvent deptsAgencyEvent = (GetDepartmentsAndAgencyEvent) event;
	Object obj = DepartmentHelper.getDepartments(deptsAgencyEvent.getDepartmentId(), deptsAgencyEvent.getDepartmentName(), "");
	if (obj != null)
	{
	    if (obj instanceof Department)
	    {
		Department dept = (Department) obj;
		if (dept != null && dept.getAgencyId() != null)
		{
		    Agency agency = Agency.find(dept.getAgencyId());
		    if (agency != null)
		    {
			AgencyResponseEvent agencyResp = new AgencyResponseEvent();
			agencyResp.setAgencyId(agency.getAgencyId());
			agencyResp.setAgencyName(agency.getAgencyName());
			agencyResp.setDepartments(agency.getDepartments());
			dispatch.postEvent(agencyResp);
		    }
		}
	    }

	    if (obj instanceof List<?>)
	    {
		List<Department> departments = (List<Department>) obj;
		if (departments != null)
		{
		    Iterator<Department> deptsIter = departments.iterator();
		    Collection<AgencyResponseEvent> deptsAndAgency = generateAgencyDepartmentResponseEvent(deptsIter);
		    if (deptsAndAgency != null)
		    {
			Iterator<AgencyResponseEvent> deptsAndAgencyIter = deptsAndAgency.iterator();
			while (deptsAndAgencyIter.hasNext())
			{
			    AgencyResponseEvent agencyResp = (AgencyResponseEvent) deptsAndAgencyIter.next();
			    dispatch.postEvent(agencyResp);
			}
		    }
		}
	    }

	}
	//87191
	/* MetaDataResponseEvent metaData = (MetaDataResponseEvent) Department.findMeta(deptsAgencyEvent);
	 if (metaData.getCount() > 2000){
	 	CountInfoMessage infoEvent = new CountInfoMessage();
	//        	infoEvent.setMessage("Record count exceeded - total records found = " + metaData.getCount());
	   	infoEvent.setCount(metaData.getCount());  
	 	dispatch.postEvent(infoEvent);
	 }else{	   
	 	// Send the Event to find all of the agencies
	 	Iterator deptsIter = Department.findAll(deptsAgencyEvent);
	 	Collection deptsAndAgency = generateAgencyDepartmentResponseEvent(deptsIter);
	 	if(deptsAndAgency != null)
	 	{
	 		Iterator deptsAndAgencyIter = deptsAndAgency.iterator();
	 		while(deptsAndAgencyIter.hasNext())
	 		{
	 			AgencyResponseEvent agencyResp = (AgencyResponseEvent)deptsAndAgencyIter.next();
	 			dispatch.postEvent(agencyResp);
	 		}
	 	}
	 }	*/

    }

    /**
     * @param event
     * @roseuid 442AFC86010D
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 442AFC86010F
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 442AFC86011A
     */
    public void update(Object anObject)
    {

    }

    private Collection<AgencyResponseEvent> generateAgencyDepartmentResponseEvent(Iterator<Department> deptIter)
    {

	/*   if(departments == null || departments.isEmpty()){
	
	  	 return null;
	
	   }*/

	HashMap agencyMap = new HashMap();

	//Iterator deptIter = departments.iterator();

	while (deptIter.hasNext())
	{

	    Department dResp = (Department) deptIter.next();

	    String agencyId = dResp.getAgencyId();

	    if (agencyMap == null || agencyMap.isEmpty() || !agencyMap.containsKey(agencyId))
	    {

		AgencyResponseEvent aResp = new AgencyResponseEvent();

		aResp.setAgencyId(dResp.getAgencyId());

		aResp.setAgencyName(getAgencyName(agencyId));

		Collection depts = new ArrayList();

		depts.add(dResp);

		aResp.setDepartments(depts);

		agencyMap.put(agencyId, aResp);

	    }
	    else
	    {

		AgencyResponseEvent oldResp = (AgencyResponseEvent) agencyMap.get(agencyId);

		agencyMap.remove(agencyId);

		Collection oldDepts = oldResp.getDepartments();

		oldDepts.add(dResp);

		oldResp.setDepartments(oldDepts);

		agencyMap.put(agencyId, oldResp);

	    }

	}

	return agencyMap.values();

    }

    private String getAgencyName(String agencyId)
    {

	String agencyName = "";

	Agency agency = Agency.find(agencyId);

	if (agency != null)
	{

	    agencyName = agency.getAgencyName();

	}

	return agencyName;

    }

}