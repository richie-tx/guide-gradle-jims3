// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerlocation\\action\\SubmitLocationUnitUpdateAction.java

package ui.supervision.administerlocation.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerlocation.ActivateInactivateLocationUnitEvent;
import messaging.administerlocation.CreateUpdateJuvLocationUnitEvent;
import messaging.administerlocation.reply.LocationNotificationResponseEvent;
import messaging.calendar.SaveServiceEventCancellationEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.Name;
import ui.security.SecurityUIHelper;
import ui.supervision.administerlocation.UILocationUnitHelper;
import ui.supervision.administerlocation.form.LocationForm;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SubmitLocationUnitUpdateAction extends LookupDispatchAction {

	/**
	 * @roseuid 466465EE03D1
	 */
	public SubmitLocationUnitUpdateAction() {

	}

	/**
	 * Method called when the user clicked on finish button to save Edit or
	 * Inactivate or Activate location unit summary page.
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4664620502AF
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
		String action = locationForm.getAction();
		
		if (action.equals(UIConstants.UPDATE)) {
			CreateUpdateJuvLocationUnitEvent updateEvent = new CreateUpdateJuvLocationUnitEvent();
			updateEvent.setLocationId(locationForm.getLocationId());
			updateEvent.setLocationUnitId(locationForm.getLocUnit().getJuvLocationUnitId());
			updateEvent.setLocationUnitName(locationForm.getLocUnit().getLocationUnitName());
			updateEvent.setJuvUnitCd(locationForm.getLocUnit().getJuvUnitCd());
			updateEvent.setUnitStatusCd(locationForm.getLocUnit().getUnitStatusId());
			updateEvent.setPhone(locationForm.getLocUnit().getPhoneNumber());
			if(locationForm.getLocUnit().getMaysiFlag().equals("1"))
			    updateEvent.setMaysiFlag(1);
			else
			    updateEvent.setMaysiFlag(0);
			if(locationForm.getLocUnit().getDrugFlag().equals("1"))
			    updateEvent.setDrugFlag(1);
			else
			    updateEvent.setDrugFlag(0);
			
			if ( locationForm.getLocUnit().getInterviewCalFlag().length() > 0 ) {
			    updateEvent.setInterviewCalFlag( Integer.parseInt( locationForm.getLocUnit().getInterviewCalFlag()  ) );
			}
			
			if ( locationForm.getLocUnit().getOfficerProfileFlag().length() > 0 ) {
			    updateEvent.setOfficerProfileFlag( Integer.parseInt( locationForm.getLocUnit().getOfficerProfileFlag() ) );
			}
			updateEvent.setCreate(false);
			//CompositeResponse compositeResponse = postRequestEvent(updateEvent);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(updateEvent);
			CompositeResponse updateResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(updateResponse);
			locationForm.setMisc(UIConstants.CONFIRM);
		}

		else if (action.equals(UIConstants.ACTIVATE)) {
			ActivateInactivateLocationUnitEvent activateEvent = new ActivateInactivateLocationUnitEvent();
			activateEvent.setJuvLocUnitId(locationForm.getLocUnit().getJuvLocationUnitId());
			activateEvent.setInactivate(false);
			//CompositeResponse compositeResponse = postRequestEvent(activateEvent);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(activateEvent);
			CompositeResponse activateResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(activateResponse);
			locationForm.setMisc(UIConstants.CONFIRM);
		}

		else if (action.equals(UIConstants.INACTIVATE)) {
			ActivateInactivateLocationUnitEvent activateEvent = new ActivateInactivateLocationUnitEvent();
			activateEvent.setJuvLocUnitId(locationForm.getLocUnit().getJuvLocationUnitId());
			activateEvent.setInactivate(true);
			//CompositeResponse compositeResponse = postRequestEvent(activateEvent);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(activateEvent);
			CompositeResponse inactivateResponse = (CompositeResponse) dispatch.getReply();
			MessageUtil.processReturnException(inactivateResponse);
			//Code to cancel the events for that particular Juvenile LocationUnitId.
			SaveServiceEventCancellationEvent cancelEvent = new SaveServiceEventCancellationEvent();
			Collection serviceEvents = locationForm.getServiceEventsList();
			if (serviceEvents != null && !serviceEvents.isEmpty()) {
				Iterator eventsIterator = serviceEvents.iterator();
				while (eventsIterator.hasNext()) {
					CalendarServiceEventResponseEvent servRespEvent = (CalendarServiceEventResponseEvent) eventsIterator
							.next();
					cancelEvent.setServiceEventId(servRespEvent.getEventId());
					//CompositeResponse response = postRequestEvent(cancelEvent);
					IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
					dispatch1.postEvent(cancelEvent);
					CompositeResponse cancelResponse = (CompositeResponse) dispatch1.getReply();
					MessageUtil.processReturnException(cancelResponse);	
				}
			}
			locationForm.setMisc(UIConstants.CONFIRM);
			this.sendInactivateNotification(locationForm);
		}

		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public void sendInactivateNotification(LocationForm locationForm){
		Iterator serviceEventsIterator = locationForm.getServiceEventsList().iterator();
		HashMap serviceProviderMap = new HashMap();
		HashMap juvenilesMap = new HashMap();

		while (serviceEventsIterator.hasNext()){
			CalendarServiceEventResponseEvent serviceEvent = (CalendarServiceEventResponseEvent)serviceEventsIterator.next();
			if(serviceEvent.getEventTypeCategory().equalsIgnoreCase("P")) {
				//send location removal notification to service provider for pre-scheduled events
				String serviceProviderId = serviceEvent.getServiceProviderId()+"";
				if (serviceProviderMap.containsKey(serviceProviderId)){
					LocationNotificationResponseEvent nevt = (LocationNotificationResponseEvent)serviceProviderMap.get(serviceProviderId);				
					ArrayList services = nevt.getServices();
					services.add(serviceEvent);
				}else{
					LocationNotificationResponseEvent nevt = new LocationNotificationResponseEvent();
					nevt.setSubject("LOCATION UNIT INACTIVATION");				
					ArrayList services = new ArrayList();
					services.add(serviceEvent);
					nevt.setServices(services);
					nevt.setServiceProviderId(serviceProviderId);
					nevt.setServiceProviderName(serviceEvent.getServiceProviderName());
					nevt.setIdentity(serviceEvent.getAdminUserProfileId());
					nevt.setLocationAddress(locationForm.getLocationAddress()+"");
					nevt.setServiceProviderFax(serviceEvent.getFax());
					serviceProviderMap.put(serviceProviderId,nevt);				
				}
				String juvenileId = serviceEvent.getJuvenileNum();
				if (juvenilesMap.containsKey(juvenileId)){
					LocationNotificationResponseEvent nevt = (LocationNotificationResponseEvent)juvenilesMap.get(juvenileId);				
					ArrayList services = nevt.getServices();
					services.add(serviceEvent);
				}else{
					LocationNotificationResponseEvent nevt = new LocationNotificationResponseEvent();
					nevt.setSubject("LOCATION UNIT INACTIVATION");				
					ArrayList services = new ArrayList();
					services.add(serviceEvent);
					nevt.setServices(services);
					nevt.setServiceProviderId(serviceProviderId);
					nevt.setServiceProviderName(serviceEvent.getServiceProviderName());
					nevt.setIdentity(serviceEvent.getProbationOfficerLogonId());
					nevt.setLocationAddress(locationForm.getLocationAddress()+"");
					nevt.setServiceProviderFax(serviceEvent.getFax());
					nevt.setJuvenileNum(serviceEvent.getJuvenileNum());
					juvenilesMap.put(juvenileId, nevt);
				}
			}
			else {
				//send location removal notification to JPO for non pre-scheduled events 
				LocationNotificationResponseEvent nevt = new LocationNotificationResponseEvent();
				nevt.setSubject("LOCATION UNIT INACTIVATION");
				nevt.setServiceType(serviceEvent.getEventType());
				nevt.setLocationAddress(locationForm.getLocationAddress().toString());
				SimpleDateFormat dfmt = new SimpleDateFormat("MM/dd/yyyy");				
				nevt.setSessionDate(dfmt.format(serviceEvent.getEventDate()));
				dfmt.applyPattern("h:mm a");
				nevt.setSessionTime(dfmt.format(serviceEvent.getEventDate()));	

				StringBuffer sb = new StringBuffer("");
				sb.append("Please be advised that ");
				sb.append(nevt.getServiceType() + " will no longer be provided at ");
				sb.append(nevt.getLocationAddress() + ".");
				sb.append("\n");

				Name name = new Name(serviceEvent.getJuvenileFirstName(),serviceEvent.getJuvenileMiddleName(),serviceEvent.getJuvenileLastName());						
				sb.append(name.getFormattedName());	
				sb.append(" Juvenile # "+ serviceEvent.getJuvenileNum());	
				sb.append(" was scheduled to attend a session at this location on ");
				sb.append(nevt.getSessionDate() + " " + nevt.getSessionTime() + " and will need to be rescheduled.");
				nevt.setIdentity(serviceEvent.getProbationOfficerLogonId());	
				nevt.setNotificationMessage(sb.toString());
				UIServiceProviderHelper.sendNotification(nevt,UIConstants.JPO_SERVICE_LOCATION_UNIT_REMOVAL_NOTIFICATION );
			}
		}

		//<KISHORE>JIMS200059292 : Administer Location-Unit Location Inactivation(KK)
		//send location removal notification to service provider for pre-scheduled events
		HashMap serviceMap = null;
		Iterator iter = serviceProviderMap.values().iterator();
		while (iter.hasNext()){
			serviceMap = new HashMap();
			LocationNotificationResponseEvent nevt = (LocationNotificationResponseEvent)iter.next();
			ArrayList serviceList = nevt.getServices();
			StringBuffer notificationMessage = new StringBuffer();
			Iterator serviceListIter = serviceList.iterator();
			String messagePart1 = "Please be advised that ";
			String messagePart2 = " will no longer be provided at " + nevt.getLocationAddress() + ".\n";
			String serviceName = "";
			while (serviceListIter.hasNext()){
				CalendarServiceEventResponseEvent serviceEvent = (CalendarServiceEventResponseEvent)serviceListIter.next();
				serviceName = serviceEvent.getServiceName();
				if(!serviceMap.containsKey(serviceName)){
					notificationMessage.append(messagePart1);
					notificationMessage.append(serviceName);
					notificationMessage.append(messagePart2);
					if(!UIConstants.STATUS_CODE_PENDING.equalsIgnoreCase(serviceEvent.getEventStatusCode()))
						notificationMessage.append(appendJuveniles(serviceName,serviceList));
					serviceMap.put(serviceName, true);
				}
			}
			nevt.setNotificationMessage(notificationMessage.toString());					
			UIServiceProviderHelper.sendNotification(nevt,UIConstants.JPO_SERVICE_LOCATION_UNIT_REMOVAL_NOTIFICATION);																											
		}
		//send location removal notification to Juvenile's JPO for pre-scheduled events
		Iterator<String> keys = juvenilesMap.keySet().iterator();
		while(keys.hasNext()){
			String juvenileId = keys.next();
			serviceMap = new HashMap();
			LocationNotificationResponseEvent nevt = (LocationNotificationResponseEvent)juvenilesMap.get(juvenileId);
			ArrayList serviceList = nevt.getServices();
			StringBuffer notificationMessage = new StringBuffer();
			Iterator serviceListIter = serviceList.iterator();
			String messagePart1 = "Please be advised that ";
			String messagePart2 = " will no longer be provided at " + nevt.getLocationAddress() + ".\n";
			String serviceName = "";
			while(serviceListIter.hasNext()){
				CalendarServiceEventResponseEvent serviceEvent = (CalendarServiceEventResponseEvent)serviceListIter.next();
				serviceName = serviceEvent.getServiceName();
				if(!serviceMap.containsKey(serviceName)){
					notificationMessage.append(messagePart1);
					notificationMessage.append(serviceName);
					notificationMessage.append(messagePart2);
					notificationMessage.append("Please be advised that the " + serviceName + " for "+serviceEvent.getServiceProviderName());
					notificationMessage.append(" had the following session(s) scheduled that have been cancelled:\n");
					if(!UIConstants.STATUS_CODE_PENDING.equalsIgnoreCase(serviceEvent.getEventStatusCode()))
						notificationMessage.append(appendServices(juvenileId,serviceName,serviceList));
					serviceMap.put(serviceName, true);
				}
			}
			nevt.setNotificationMessage(notificationMessage.toString());					
			UIServiceProviderHelper.sendNotification(nevt,UIConstants.JPO_SERVICE_LOCATION_UNIT_REMOVAL_NOTIFICATION);																											
		}
	}
	
	private String appendJuveniles(String service, ArrayList serviceEvents){
		String message = "";
		String serviceName = "";
		Iterator<CalendarServiceEventResponseEvent> serviceListIter = serviceEvents.iterator();
		while (serviceListIter.hasNext()){
			CalendarServiceEventResponseEvent serviceEvent = serviceListIter.next();
			serviceName = serviceEvent.getServiceName();
			if(service.equalsIgnoreCase(serviceName)){
				Name name = new Name(serviceEvent.getJuvenileFirstName(),serviceEvent.getJuvenileMiddleName(),serviceEvent.getJuvenileLastName());						
				message += (name.getFormattedName());	
				message += (" Juvenile # "+ serviceEvent.getJuvenileNum());	
				message += (" was scheduled to attend a session at this location on ");
				SimpleDateFormat dfmt = new SimpleDateFormat("MM/dd/yyyy");				
				String eventDate = dfmt.format(serviceEvent.getEventDate());
				dfmt.applyPattern("h:mm a");
				String eventTime = dfmt.format(serviceEvent.getEventDate());
				message += (eventDate + " " + eventTime + " and will need to be rescheduled.\n");
			}
		}
		return message;
	}

	private String appendServices(String juvenileId,String service, ArrayList serviceEvents){
		String message = "";
		Iterator<CalendarServiceEventResponseEvent> serviceListIter = serviceEvents.iterator();
		while (serviceListIter.hasNext()){
			CalendarServiceEventResponseEvent serviceEvent = serviceListIter.next();
			if(service.equalsIgnoreCase(serviceEvent.getServiceName()) && 
					juvenileId.equalsIgnoreCase(serviceEvent.getJuvenileNum())){
				SimpleDateFormat dfmt = new SimpleDateFormat("MM/dd/yyyy");				
				String eventDate = dfmt.format(serviceEvent.getEventDate());
				dfmt.applyPattern("h:mm a");
				String eventTime = dfmt.format(serviceEvent.getEventDate());
				message += (eventDate + " " + eventTime + " \n");
				Name name = new Name(serviceEvent.getJuvenileFirstName(),serviceEvent.getJuvenileMiddleName(),serviceEvent.getJuvenileLastName());						
				message += (name.getFormattedName());	
				message += (" was scheduled to attend these event(s).");
			}
		}
		return message;
	}
	
	/**
	 * Method to display Updated Location Unit List after doing Update or
	 * Activate or Inactivate.
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward locationUnitList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
		String agencyId = SecurityUIHelper.getUserAgencyId();//changed vj.
		UILocationUnitHelper.populateLocationDetails(locationForm, locationForm.getLocationId());
		locationForm.setLocationUnitList(UILocationUnitHelper.getLocationUnitList(locationForm.getLocationId(),
				agencyId));
		locationForm.setAction(UIConstants.MANAGE_LOCATION_UNITS);
		locationForm.clearLocationUnit();
		locationForm.setNewLocUnitList(new ArrayList());
		return aMapping.findForward(UIConstants.RETURN_SUCCESS);
	}

	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
		locationForm.clear();
		locationForm.clearAddress();
		return aMapping.findForward(UIConstants.CANCEL);
	}
	
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		HashMap keyMap = new HashMap();
		keyMap.put("button.finish", "finish");
		keyMap.put("button.locationUnitList", "locationUnitList");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}
}
