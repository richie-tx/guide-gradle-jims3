//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\transactions\\GetAgenciesCommand.java

package pd.contact.agency.transactions;

import java.util.Collection;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import messaging.agency.GetAgenciesDepartmentsEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.info.reply.CountInfoMessage;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import naming.PDContactConstants;
import naming.ResponseLocatorConstants;
import pd.common.ResponseCommonUtil;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.contact.agency.Agency;
import pd.contact.agency.Department;

/**
 * @author mchowdhury
 *
 */
public class GetAgenciesDepartmentsCommand extends ResponseCommonUtil implements ICommand
{

	/**
	@roseuid 4107F7FF02BA
	 */
	public GetAgenciesDepartmentsCommand()
	{

	}

	/**
	@param event
	@roseuid 4107F6F00208
	 */
	public void execute(IEvent event)
	{/*
		GetAgenciesDepartmentsEvent gaEvent = (GetAgenciesDepartmentsEvent) event;
		ResponseContextFactory respFac = new ResponseContextFactory();
		ResponseCreator dCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.DEPARTMENT_RESPONSE_LOCATOR,respFac);
		ResponseCreator aCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.AGENCY_RESPONSE_LOCATOR,respFac);

		SortedMap agenciesMap = new TreeMap();
		MetaDataResponseEvent metaData = (MetaDataResponseEvent) Agency.findMeta(gaEvent);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        if (metaData.getCount() > 2000){
        	CountInfoMessage infoEvent = new CountInfoMessage();
//        	infoEvent.setMessage("Record count exceeded - total records found = " + metaData.getCount());
          	infoEvent.setCount(metaData.getCount());  
        	dispatch.postEvent(infoEvent);
        }else{
		// Send the Event to find all of the agencies
        	Iterator agencies = Agency.findAll(gaEvent);

		// Iterate through the agencies and post the AgencyResponseEvent for each
         	while (agencies.hasNext()){
        		Agency agency = (Agency) agencies.next();
        		if(agency != null){
        			AgencyResponseEvent agencyEvent = (AgencyResponseEvent) aCreator.create(agency);
				
        			Collection departments = agency.getDepartments();
        			SortedMap departmentsMap = new TreeMap();
        			Iterator iter = departments.iterator();
        			while(iter.hasNext()){
        				Department department = (Department) iter.next();
        				if(department != null){
        					DepartmentResponseEvent departmentResponseEvent = (DepartmentResponseEvent) dCreator.createThin(department);
        					departmentsMap.put(departmentResponseEvent.getDepartmentName() + departmentResponseEvent.getDepartmentId(),departmentResponseEvent);
        				}
        			}
        			agencyEvent.setDepartments(departmentsMap.values());
        			agenciesMap.put(agencyEvent.getAgencyName(),agencyEvent);
        		}
        	}
        }	
		this.postSortedAgencies(agenciesMap);
	}

	*//**
	 * @param agenciesMap
	 *//*
	private void postSortedAgencies(SortedMap agenciesMap)
	{
		// Get the IDispatch to post to
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator iter = agenciesMap.values().iterator();
		while(iter.hasNext()){
			AgencyResponseEvent agencyEvent = (AgencyResponseEvent) iter.next();
    		agencyEvent.setTopic(PDContactConstants.AGENCY_EVENT_TOPIC);
			dispatch.postEvent(agencyEvent);
		}*/ //87191
	}

	/**
	@param event
	@roseuid 4107F6F0020A
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	@param event
	@roseuid 4107F6F0020C
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	@param updateObject
	@roseuid 4107F7FF02CE
	 */
	public void update(Object updateObject)
	{

	}
}