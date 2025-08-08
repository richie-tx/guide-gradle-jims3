//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\GetServiceProviderServicesCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.Iterator;

import messaging.administerserviceprovider.GetServiceProviderServiceByServiceIdEvent;
import messaging.administerserviceprovider.reply.ServiceProviderServiceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;
import pd.codetable.Code;
import pd.supervision.administerserviceprovider.SearchServiceProvider;
import pd.supervision.administerserviceprovider.ServiceLocation;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetServiceProviderServiceByServiceIdCommand implements ICommand 
{
   
   /**
    * @roseuid 44805C7E0327
    */
   public GetServiceProviderServiceByServiceIdCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 447F49A502A4
    */
   public void execute(IEvent event) 
   {
   	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        GetServiceProviderServiceByServiceIdEvent sps = (GetServiceProviderServiceByServiceIdEvent) event;
		Iterator spIter = SearchServiceProvider.findAll(sps);
	    while(spIter.hasNext()){
	    	SearchServiceProvider sp = (SearchServiceProvider) spIter.next();
			if(sp != null){
				ServiceProviderServiceResponseEvent resp = new ServiceProviderServiceResponseEvent();
				resp.setJuvServProviderName(sp.getServiceProviderName());
				resp.setInHouse(sp.getInHouse());
				resp.setProgramName(sp.getProgramName());
				if(sp.getStartDate() != null){
				    resp.setStartDate(DateUtil.dateToString(sp.getStartDate(),UIConstants.DATE_FMT_1));
				}
				
				String targetInterventionId = sp.getTargetInterventionId();
				if(targetInterventionId != null && !targetInterventionId.equals("")){
					Code code = sp.getTargetIntervention();
					if(code != null){
						resp.setTargetIntervention(code.getDescription());
					}
				}
				resp.setServiceName(sp.getServiceName());
				resp.setServiceName(sp.getServiceName());
				resp.setMaxEnrollment(sp.getMaxEnrollment());
				resp.setServiceCode(sp.getServiceCode());
				resp.setServiceCost(sp.getServiceCost());
				String serviceTypeId = sp.getServiceTypeId();
				if(serviceTypeId != null && !serviceTypeId.equals("")){
					resp.setServiceType(sp.getServiceType().getDescription());
				}
				
				StringBuffer loc = new StringBuffer();
				Iterator locIter = ServiceLocation.findAll("serviceId", sp.getServiceId());
				while(locIter.hasNext()){
					ServiceLocation servLoc =  (ServiceLocation) locIter.next();
					if(servLoc != null){
						JuvLocationUnit locUnit = servLoc.getLocationUnit();
						if(locUnit != null){
							loc.append(locUnit.getLocationUnitName());
							if(locIter.hasNext()){
								loc.append(", ");
							}
						}
					}
				}
				resp.setLocationName(loc.toString());
				dispatch.postEvent(resp);	
			}
		}
	}
 
/**
    * @param event
    * @roseuid 447F49A502A6
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 447F49A502B2
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 447F49A502B4
    */
   public void update(Object anObject) 
   {
    
   }
}