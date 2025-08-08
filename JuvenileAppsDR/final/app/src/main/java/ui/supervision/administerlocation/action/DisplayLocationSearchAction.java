//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerlocation\\action\\DisplayLocationSearchAction.java

package ui.supervision.administerlocation.action;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerlocation.GetJuvLocationUnitsByAgencyEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.administerserviceprovider.GetServiceProviderFromDepartmentIdEvent;
import messaging.administerserviceprovider.reply.JuvenileServiceProviderResponseEvent;
import messaging.codetable.GetServiceTypeCdEvent;
import messaging.codetable.criminal.reply.ServiceTypeCdResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;

import ui.common.CodeHelper;
import ui.supervision.administerlocation.UILocationUnitHelper;
import ui.supervision.administerlocation.form.LocationForm;


public class DisplayLocationSearchAction extends Action
{
   
   /**
    * @roseuid 45117C5201AF
    */
   public DisplayLocationSearchAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 45104B420018
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		LocationForm locationForm = (LocationForm)aForm;
   		
   		locationForm.clear();
   		locationForm.clearAddress();
		GetServiceTypeCdEvent serviceTypeEvent =
			(GetServiceTypeCdEvent) EventFactory.getInstance(
					CodeTableControllerServiceNames.GETSERVICETYPECD);	
		serviceTypeEvent.setCodeTableName("SERVICE_TYPE");
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(serviceTypeEvent);
		//Getting PD Response Event		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		Collection serviceTypeResponses = MessageUtil.compositeToCollection(compositeResponse, ServiceTypeCdResponseEvent.class);
		Collections.sort((List)serviceTypeResponses);
		locationForm.setServiceTypeList(serviceTypeResponses);
		
		GetServiceProviderFromDepartmentIdEvent serviceProviderEvent
		= (GetServiceProviderFromDepartmentIdEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDERFROMDEPARTMENTID);
		serviceProviderEvent.setDepartmentId("JUV");
		IDispatch dispatch2 = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch2.postEvent(serviceProviderEvent);
		CompositeResponse compositeResponse1 = (CompositeResponse) dispatch2.getReply();
		Collection serviceProviders =
					MessageUtil.compositeToCollection(compositeResponse1, JuvenileServiceProviderResponseEvent.class);
		Collections.sort((List)serviceProviders);
		locationForm.setServiceProviderList(serviceProviders);
		
	   	Collection locationStatusList = CodeHelper.getCodes(PDCodeTableConstants.LOCATION_STATUS);
	   	locationForm.setLocationStatusList(locationStatusList);
	   	
		Collection stateList = CodeHelper.getCodes(PDCodeTableConstants.STATE_ABBR,true);
		locationForm.setStateList(stateList);
		
		Collection<LocationResponseEvent> locationUnits = UILocationUnitHelper.getAllLocationUnits();
		locationForm.setLocationUnitsAll(locationUnits);
					
		
		
	   	return aMapping.findForward(UIConstants.SUCCESS);
				   		
   }   
   
}
