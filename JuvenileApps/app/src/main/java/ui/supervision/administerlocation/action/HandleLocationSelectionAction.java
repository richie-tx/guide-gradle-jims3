//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerlocation\\action\\HandleLocationSelectionAction.java

package ui.supervision.administerlocation.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.administerlocation.ActivateInactivateLocationEvent;
import messaging.administerlocation.CreateUpdateLocationEvent;
import messaging.administerlocation.GetJuvenileLocationEvent;
import messaging.administerlocation.reply.LocationNotificationResponseEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.administerserviceprovider.GetServiceLocationServiceProvidersEvent;
import messaging.administerserviceprovider.GetServiceProviderServicesEvent;
import messaging.administerserviceprovider.GetServiceProvidersByLocationUnitEvent;
import messaging.administerserviceprovider.reply.JuvenileServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import messaging.calendar.SaveServiceEventCancellationEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.notification.CreateNotificationEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;
import naming.PDCodeTableConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.security.SecurityUIHelper;
import ui.supervision.administerlocation.UILocationUnitHelper;
import ui.supervision.administerlocation.form.LocationForm;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;

public class HandleLocationSelectionAction extends LookupDispatchAction {

	/**
	 * @roseuid 45117C5303D2
	 */
	public HandleLocationSelectionAction() {

	}
	
	public ActionForward updateLocation(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
	
		locationForm.setAction("update");
		
		String locationId = aRequest.getParameter("locationId");
		

		//if (locationForm.getLocationId()!=null && !locationForm.getLocationId().equals("")){
		if (locationId!=null && !locationId.equals("")){
			this.populateLocationForm(locationForm,locationId);	
		}
		locationForm.setOldLocationName(locationForm.getLocationName());
		locationForm.setOldlocationCd(locationForm.getLocationCd());
		Collection streetTypeList=CodeHelper.getCodes(PDCodeTableConstants.STREET_TYPE,true);
		locationForm.setStreetTypeList(streetTypeList);
		
		Collection facilityTypeList = CodeHelper.getCodes(PDCodeTableConstants.FACILITY_TYPE,true);
		locationForm.setFacilityTypeList(facilityTypeList);
	
		return aMapping.findForward(UIConstants.CREATE);
	}
	
	public ActionForward createLocation(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;

		locationForm.clear();
		locationForm.clearAddress();
		
		locationForm.setAction("create");
		
		locationForm.setStatusId("A");
		locationForm.setIsInHouse("YES");
		
		
		Collection streetTypeList=CodeHelper.getCodes(PDCodeTableConstants.STREET_TYPE,true);
		locationForm.setStreetTypeList(streetTypeList);
		
		Collection facilityTypeList = CodeHelper.getCodes(PDCodeTableConstants.FACILITY_TYPE,true);
		locationForm.setFacilityTypeList(facilityTypeList);
	
		return aMapping.findForward(UIConstants.CREATE);
	}

	public ActionForward activateLocation(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
	
		locationForm.setAction("activate");
		
		String locationId = aRequest.getParameter("locationId");
		

		if (locationId!=null && !locationId.equals("")){
			this.populateLocationForm(locationForm,locationId);	
		}
			
		return aMapping.findForward(UIConstants.ACTIVATE);
	}
	
	public ActionForward viewDetail(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
	
		locationForm.setAction("view");
		
		String locationId = aRequest.getParameter("locationId");
		locationForm.setIsLocationUnitPage("false");
		
		if (locationId!=null && !locationId.equals("")){
			this.populateLocationForm(locationForm,locationId);
			//<KISHORE>JIMS200059292 : Administer Location-Unit Location Inactivation(KK)
			locationForm.setActiveLocationUnitExists(false);
			if(StringUtils.isNotEmpty(locationForm.getAgencyId()) && UIConstants.ACTIVE_STATUS_ID.equalsIgnoreCase(locationForm.getStatusId())){
				ArrayList<LocationResponseEvent> locUnits = (ArrayList)UILocationUnitHelper.getLocationUnitList(locationForm.getLocationId(), locationForm.getAgencyId());
				Iterator<LocationResponseEvent> iter = locUnits.iterator();
				while(iter.hasNext()){
					LocationResponseEvent event = iter.next();
					
					if(locationForm.getLocationUnitId() != null && !locationForm.getLocationUnitId().equals("")
						&& event.getJuvLocationUnitId() != null && !event.getJuvLocationUnitId().equals("")){
					    
					    if(locationForm.getLocationUnitId().equals(event.getJuvLocationUnitId())){
						
						locationForm.setLocationUnitId(event.getJuvUnitCd());
						locationForm.setLocationUnitName(event.getLocationUnitName());
						locationForm.setLocationUnitStatus(event.getStatus());
						
						if(UIConstants.ACTIVE_STATUS_ID.equalsIgnoreCase(event.getStatusId())){
						    locationForm.setActiveLocationUnitExists(true);
						    break;
						}
					    }				    
					    					    
					}
					
					if(UIConstants.ACTIVE_STATUS_ID.equalsIgnoreCase(event.getStatusId())){
					    locationForm.setActiveLocationUnitExists(true);
					    //break;
					}
					
				}
			}
		}
			
		return aMapping.findForward(UIConstants.ACTIVATE);
	}
	
	public ActionForward inactivateLocation(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
	
		locationForm.setAction("inactivate");
	
		String locationId = aRequest.getParameter("locationId");	
		
		if (locationId!=null && !locationId.equals("")){
			this.populateLocationForm(locationForm,locationId);	
		}
		//<KISHORE>JIMS200059292 : Administer Location-Unit Location Inactivation(KK)
/*		GetCalendarServiceEventsEvent gse = new GetCalendarServiceEventsEvent();
		gse.setRetriever(CalendarRetrieverFactory.getRetrieverName(CalendarRetrieverFactory.LOCATION_RETRIEVER));	
		
		if (locationForm.getLocationUnits()!=null ){
			Iterator unitIter = locationForm.getLocationUnits().iterator();		
			while (unitIter.hasNext()){
				LocationResponseEvent lre = (LocationResponseEvent)unitIter.next();			
				ServiceLocationAttribute serviceLocationAttribute = new ServiceLocationAttribute();
				serviceLocationAttribute.setServiceLocationId(Integer.parseInt(lre.getJuvLocationUnitId()));
				gse.addCalendarAttribute(serviceLocationAttribute);
			}
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(gse);
			
			CompositeResponse response = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(response);
					
			Collection eventsList = MessageUtil.compositeToCollection(response, CalendarServiceEventResponseEvent.class);
			Collections.sort((List)eventsList);
			locationForm.setServiceEventsList(eventsList);
		}						
*/		
		return aMapping.findForward(UIConstants.INACTIVATE);
	}
	
	public ActionForward viewServiceProviders(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
	
		
		String locationId = aRequest.getParameter("locationId") != null ? aRequest.getParameter("locationId") : "";		
		String isLocationUnitPage = aRequest.getParameter("locationUnitPage") != null ? aRequest.getParameter("locationUnitPage") : "";
		String juvLocationUnitId = aRequest.getParameter("juvLocationUnitId");
		int locationUnitId = juvLocationUnitId != null ? Integer.parseInt(juvLocationUnitId) : 0;
		
		if(isLocationUnitPage != null && isLocationUnitPage.equalsIgnoreCase("true")){
		    locationForm.setIsLocationUnitPage("true");
		} else {
		    locationForm.setIsLocationUnitPage("false");
		}
				
		GetServiceLocationServiceProvidersEvent gslv = new GetServiceLocationServiceProvidersEvent();
		
		gslv.setLocationId(Integer.parseInt(locationId));
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(gslv);
		
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(response);	
		Collection serviceProviderList = MessageUtil.compositeToCollection(response, JuvenileServiceProviderResponseEvent.class);
		Collections.sort((List)serviceProviderList);
		locationForm.setAssociatedSPList(serviceProviderList);		
		
		//search by locationUnitId - if the value is supplied
		if(isLocationUnitPage.equalsIgnoreCase("true") && locationUnitId > 0){	
		    
		    GetServiceProvidersByLocationUnitEvent sps = new GetServiceProvidersByLocationUnitEvent() ;
        		sps.setJuvLocUnitId(locationUnitId);
        		
        		Collection<JuvenileServiceProviderResponseEvent> serviceProviderList2 = new ArrayList<JuvenileServiceProviderResponseEvent>();
        		ArrayList serviceProviderListByLocationUnit = new ArrayList();
        		Iterator spIter = JuvenileServiceProvider.findAll( sps ) ;
        		while( spIter.hasNext( ) )
        		{
        			JuvenileServiceProvider sp = (JuvenileServiceProvider)spIter.next( ) ;
        			JuvenileServiceProviderResponseEvent spe = new JuvenileServiceProviderResponseEvent();
        			
        			spe.setServiceProviderId(sp.getServiceProviderId());
        			spe.setServiceProviderName(sp.getServiceProviderName());
        			spe.setProgramName(sp.getProgramName());
        			spe.setServiceName(sp.getServiceName());
        			//service status
        			String serviceStatus = sp.getServiceStatusCode() != null && sp.getServiceStatusCode().equalsIgnoreCase("A") ? "ACTIVE" : "INACTIVE";
        			spe.setStatus(serviceStatus);        			
        			
        			spe.setInHouse(sp.getInHouse());
        			serviceProviderList2.add(spe);

        		}        

        		Collections.sort((List)serviceProviderList2);
        		locationForm.setAssociatedSPList(serviceProviderList2);
		}
		
		return aMapping.findForward(UIConstants.VIEW);
	}
	
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
		locationForm.clear();
		locationForm.clearAddress();
		return aMapping.findForward(UIConstants.CANCEL);
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
	    	
        	    LocationForm locationForm = (LocationForm)aForm;
        	    locationForm.setLocationUnitId(null);
        	    locationForm.setLocationUnitName(null);
	    
		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm)aForm;
		String action = locationForm.getAction();
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		
		if (action.equals("inactivate")||action.equals("activate")){
			ActivateInactivateLocationEvent aievt = new ActivateInactivateLocationEvent();
			aievt.setLocationId(locationForm.getLocationId());
			//<KISHORE>JIMS200060421 : Inactivating a location removes agency code
			aievt.setAgencyId(SecurityUIHelper.getUserAgencyId());
			aievt.setAction(action);
			dispatch.postEvent(aievt);
		}else if (action.equals("create")||action.equals("update")){
			CreateUpdateLocationEvent culevt = locationForm.getCreateUpdateEvent();
			dispatch.postEvent(culevt);
		}

		//<KISHORE>JIMS200059292 : Administer Location-Unit Location Inactivation(KK)
/*		if (action.equals("inactivate")){			
			this.sendInactivateNotification(locationForm);
		}
*/		
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(response); 
		
		if (action.equals("inactivate")){
			locationForm.setStatusId("I");
			locationForm.setAction("confirmInactivate");
		}else if (action.equals("activate")){
			locationForm.setStatusId("A");
			locationForm.setAction("confirmActivate");
		}else if (action.equals("create")){
			locationForm.setAction("confirmCreate");
		}else if (action.equals("update")){
			locationForm.setAction("confirmUpdate");
		}
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward locationSearch(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			LocationForm locationForm = (LocationForm) aForm;

			locationForm.clear();
			locationForm.clearAddress();
		
			return aMapping.findForward(UIConstants.CANCEL);
		}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.activateLocation", "activateLocation");
		keyMap.put("button.updateLocation", "updateLocation");
		keyMap.put("button.createLocation", "createLocation");
		keyMap.put("button.inactivateLocation", "inactivateLocation");
		keyMap.put("button.viewAll", "viewServiceProviders");
		keyMap.put("button.view", "viewDetail");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.locationSearch","locationSearch");
		
		return keyMap;
	}
	
	public void populateLocationForm(LocationForm locationForm,String locationId){
		
		Collection locationSearchResults = locationForm.getLocationSearchResults();
		
		Iterator iter = locationSearchResults.iterator();
		
		while (iter.hasNext()){
			LocationResponseEvent lre = (LocationResponseEvent)iter.next();
			if (lre.getLocationId().equals(locationId)){
				
				GetJuvenileLocationEvent gslv = new GetJuvenileLocationEvent();				
				gslv.setLocationId(locationId);
				
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(gslv);
				
				CompositeResponse response = (CompositeResponse) dispatch.getReply();
				MessageUtil.processReturnException(response);
				lre = (LocationResponseEvent) MessageUtil.filterComposite(response, LocationResponseEvent.class);	
			
				locationForm.setForm(lre);
				return;
				
			}
		}
		

	}
	
	public void sendInactivateNotification(LocationForm locationForm){
		Iterator serviceEventsIterator = locationForm.getServiceEventsList().iterator();
		HashMap serviceProviderMap = new HashMap();
		
		while (serviceEventsIterator.hasNext()){
			CalendarServiceEventResponseEvent serviceEvent = (CalendarServiceEventResponseEvent)serviceEventsIterator.next();
			cancelFutureServiceEvents(serviceEvent);
			if(serviceEvent.getEventTypeCategory().equalsIgnoreCase("P")) {
				//send location removal notification to service provider for pre-scheduled events
				String serviceProviderId = serviceEvent.getServiceProviderId()+"";
				if (serviceProviderMap.containsKey(serviceProviderId)){
					LocationNotificationResponseEvent nevt = (LocationNotificationResponseEvent)serviceProviderMap.get(serviceProviderId);				
					ArrayList services = nevt.getServices();
					services.add(serviceEvent.getServiceName());
				}else{
					LocationNotificationResponseEvent nevt = new LocationNotificationResponseEvent();
					nevt.setSubject("LOCATION INACTIVATION");				
					ArrayList services = new ArrayList();
					services.add(serviceEvent.getServiceName());
					nevt.setServices(services);
					nevt.setServiceProviderId(serviceProviderId);
					nevt.setServiceProviderName(serviceEvent.getServiceProviderName());
					nevt.setIdentity(serviceEvent.getAdminUserProfileId());
					nevt.setLocationAddress(locationForm.getLocationAddress()+"");
					nevt.setServiceProviderFax(serviceEvent.getFax());
					serviceProviderMap.put(serviceProviderId,nevt);				
				}
				
			}
			else {
				//send location removal notification to JPO for non pre-scheduled events 
				LocationNotificationResponseEvent nevt = new LocationNotificationResponseEvent();
				nevt.setSubject("LOCATION INACTIVATION");
				nevt.setServiceType(serviceEvent.getEventType());
				nevt.setLocationAddress(locationForm.getLocationAddress().toString());
				SimpleDateFormat dfmt = new SimpleDateFormat("MM/dd/yyyy");				
				nevt.setSessionDate(dfmt.format(serviceEvent.getEventDate()));
				dfmt.applyPattern("h:mm a");
				nevt.setSessionTime(dfmt.format(serviceEvent.getEventDate()));	
				Iterator associatedContextsIter = serviceEvent.getAssociatedContexts().iterator();
				
				StringBuffer sb = new StringBuffer("");
				sb.append("Please be advised that ");
				sb.append(nevt.getServiceType() + " will no longer be provided at ");
				sb.append(nevt.getLocationAddress() + ".");
				sb.append("\n");
				sb.append("Please be advised that the following session(s) scheduled  have been cancelled: ");
				sb.append(nevt.getSessionDate() + " " + nevt.getSessionTime());
				sb.append("\n");
				
				while (associatedContextsIter.hasNext()){
				JuvenileCasefileResponseEvent juvResp = (JuvenileCasefileResponseEvent)associatedContextsIter.next();
					Name name = new Name(juvResp.getJuvenileFirstName(),juvResp.getJuvenileMiddleName(),juvResp.getJuvenileLastName());						
					sb.append(name.getFormattedName());					
					sb.append(" was scheduled to attend these event(s).");
					nevt.setIdentity(juvResp.getProbationOfficerLogonId());	
					nevt.setNotificationMessage(sb.toString());
					UIServiceProviderHelper.sendNotification(nevt,UIConstants.JPO_SERVICE_LOCATION_REMOVAL_NOTIFICATION );
				}
			}
			
		}
		
		Iterator iter = serviceProviderMap.values().iterator();
		while (iter.hasNext()){
			LocationNotificationResponseEvent nevt = (LocationNotificationResponseEvent)iter.next();
			ArrayList serviceList = nevt.getServices();
			StringBuffer notificationMessage = new StringBuffer();
			Iterator serviceListIter = serviceList.iterator();
			String messagePart1 = "Please be advised that ";
			String messagePart2 = " will no longer be provided at " + nevt.getLocationAddress() + ".\n";
			while (serviceListIter.hasNext()){
				notificationMessage.append(messagePart1);
				notificationMessage.append((String)serviceListIter.next());
				notificationMessage.append(messagePart2);					
			}
			nevt.setNotificationMessage(notificationMessage.toString());					
			sendNotification(nevt,UIConstants.LOCATION_INACTIVATE_NOTIFICATION);																											
		}		
	}
	
	public void sendNotification(LocationNotificationResponseEvent nevt, String topic){	
	//		LoadNotificationDefinitions.main(null);
			CreateNotificationEvent notificationEvent = (CreateNotificationEvent) 
						 EventFactory.getInstance(
						 NotificationControllerSerivceNames.CREATENOTIFICATION);
			notificationEvent.setNotificationTopic(topic);			
			notificationEvent.setSubject(nevt.getSubject());
			notificationEvent.addIdentity(UIConstants.NOTIFICATON_RESPONSE_EVENT_CONTEXT, nevt);
			notificationEvent.addContentBean(nevt);
			EventManager.getSharedInstance(EventManager.REQUEST).postEvent(notificationEvent);
	}
		
	private void cancelFutureServiceEvents(CalendarServiceEventResponseEvent evt) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		SaveServiceEventCancellationEvent saveCancellationEvent = (SaveServiceEventCancellationEvent)EventFactory.getInstance(ServiceEventControllerServiceNames.SAVESERVICEEVENTCANCELLATION);
	 	saveCancellationEvent.setServiceEventId(evt.getEventId());
		dispatch.postEvent(saveCancellationEvent);						
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
	}

}
