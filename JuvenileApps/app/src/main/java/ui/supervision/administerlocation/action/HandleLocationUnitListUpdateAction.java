// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerlocation\\action\\HandleLocationUnitListUpdateAction.java

package ui.supervision.administerlocation.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerlocation.GetJuvLocationUnitDetailsEvent;
import messaging.administerlocation.ValidateLocationUnitDetailsEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.calendar.GetCalendarServiceEventsEvent;
import messaging.calendar.GetJuvLocUnitIdAttribute;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCalendarConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.PhoneNumber;
import ui.common.SimpleCodeTableHelper;
import ui.security.SecurityUIHelper;
import ui.supervision.administerlocation.UILocationUnitHelper;
import ui.supervision.administerlocation.form.LocationForm;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HandleLocationUnitListUpdateAction extends LookupDispatchAction {

	/**
	 * @roseuid 4664660A03E2
	 */
	public HandleLocationUnitListUpdateAction() {

	}

	/**
	 * 
	 * Method called when the user clicks on activate hyperlink.
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 466462050251
	 */
	public ActionForward activate(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
		PhoneNumber phoneNumber = new PhoneNumber("");
		locationForm.setAction(UIConstants.ACTIVATE);
		String locationUnitId = aRequest.getParameter("locationUnitId");
		LocationResponseEvent respEvent = getJuvLocationUnitDetails(locationUnitId);
		if (respEvent != null) {
			locationForm.getLocUnit().setJuvLocationUnitId(respEvent.getJuvLocationUnitId());
			locationForm.getLocUnit().setJuvUnitCd(respEvent.getJuvUnitCd());
			locationForm.getLocUnit().setUnitStatusId(respEvent.getStatusId());
			locationForm.getLocUnit().setLocationUnitName(respEvent.getLocationUnitName());
			phoneNumber.setPhoneNumber(respEvent.getPhoneNumber());
			locationForm.getLocUnit().setPhoneNumber(phoneNumber);
		}
		locationForm.setMisc(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.ACTIVATE_SUCCESS);
	}

	/**
	 * 
	 * Method called when the user clicks on Inactivate hyperlink.
	 * Displays all the Future Events whose date is after the current time and whose status is schedule and available.
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward inActivate(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
		PhoneNumber phoneNumber = new PhoneNumber("");
		locationForm.setAction(UIConstants.INACTIVATE);
		String locationUnitId = aRequest.getParameter("locationUnitId");
		LocationResponseEvent respEvent = getJuvLocationUnitDetails(locationUnitId);
		if (respEvent != null) {
			locationForm.getLocUnit().setJuvLocationUnitId(respEvent.getJuvLocationUnitId());
			locationForm.getLocUnit().setJuvUnitCd(respEvent.getJuvUnitCd());
			locationForm.getLocUnit().setLocationUnitName(respEvent.getLocationUnitName());
			phoneNumber.setPhoneNumber(respEvent.getPhoneNumber());
			locationForm.getLocUnit().setPhoneNumber(phoneNumber);
			locationForm.getLocUnit().setUnitStatusId(respEvent.getStatusId());			
		}
		GetCalendarServiceEventsEvent calendarServicesEventsEvent = new GetCalendarServiceEventsEvent();
		//Add start date here!
		calendarServicesEventsEvent.setStartDatetime(new Date());
		GetJuvLocUnitIdAttribute idAttr = new GetJuvLocUnitIdAttribute();
		idAttr.setJuvLocUnitId(Integer.parseInt(locationUnitId));
		calendarServicesEventsEvent.addCalendarAttribute(idAttr);
		//<KISHORE>JIMS200060153 : MJCW - Schedule Calendar Event is Timing out on SP Events
		calendarServicesEventsEvent.setRequestType(PDCalendarConstants.CALENDAR_EVENTS_FOR_INACTIVATE_LOCATION);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(calendarServicesEventsEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Collection serviceLocations = MessageUtil.compositeToCollection(response,
				CalendarServiceEventResponseEvent.class);
		Collection futureServiceLocations = new ArrayList();;
		if (serviceLocations != null && !serviceLocations.isEmpty()) {
			Iterator servLocIterator = serviceLocations.iterator();
			while(servLocIterator.hasNext())
			{
				CalendarServiceEventResponseEvent servRespEvent = (CalendarServiceEventResponseEvent) servLocIterator
				.next();
				Date testDate = servRespEvent.getEventDate();
				if(testDate!=null){
					if(compareDate(testDate) && 
							((UIConstants.STATUS_CODE_AVAILABLE.equalsIgnoreCase(servRespEvent.getEventStatusCode()))||
							 (UIConstants.STATUS_CODE_PENDING.equalsIgnoreCase(servRespEvent.getEventStatusCode()))||
							 (UIConstants.STATUS_CODE_SCHEDULED.equalsIgnoreCase(servRespEvent.getEventStatusCode())))){
						futureServiceLocations.add(servRespEvent);
					}
				}
				
			}
		}
		locationForm.setServiceEventsList(futureServiceLocations);
		locationForm.setMisc(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.INACTIVATE_SUCCESS);
	}

	
	/**
	 * Date Comparision.
	 * @param testDate
	 */
	private static boolean compareDate(Date testDate)
	{
		boolean dateFlag = false;
		Date now = new Date();
		Calendar c1 = Calendar.getInstance(); 
	    Calendar c2 = Calendar.getInstance(); 
	    c1.setTime(now);
	    c2.setTime(testDate);
	    if(c2.after(c1)){
	    	dateFlag = true;	    	
	    }
	 return dateFlag;   	
	}
	
	
	/**
	 *  Method called when the user clicks on edit hyperlink.
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
		locationForm.setAction(UIConstants.UPDATE);
		String locationUnitId = aRequest.getParameter("locationUnitId");
		LocationResponseEvent respEvent = getJuvLocationUnitDetails(locationUnitId);
		PhoneNumber phoneNumber = new PhoneNumber("");
		if (respEvent != null) {
			locationForm.getLocUnit().setJuvLocationUnitId(respEvent.getJuvLocationUnitId());
			locationForm.getLocUnit().setJuvUnitCd(respEvent.getJuvUnitCd());
			locationForm.getLocUnit().setUnitStatusId(respEvent.getStatusId());
			locationForm.getLocUnit().setLocationUnitName(respEvent.getLocationUnitName());
			phoneNumber.setPhoneNumber(respEvent.getPhoneNumber());
			locationForm.getLocUnit().setPhoneNumber(phoneNumber);
			locationForm.getLocUnit().setMaysiFlag(""+respEvent.getMaysiFlg()+"");
			locationForm.getLocUnit().setDrugFlag(""+respEvent.getDrugFlag()+"");
			locationForm.getLocUnit().setInterviewCalFlag( ""+respEvent.getInterviewCalFlag()+"" );
			locationForm.getLocUnit().setOfficerProfileFlag( ""+respEvent.getOfficerProfileFlag()+"");
			
		}
		return aMapping.findForward(UIConstants.EDIT);
	}

	/**
	 *  Method to display location units when the user clicks manage location
	 * units button for the first time.
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward manageLocationUnits(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
		locationForm.clearLocationUnit();
		locationForm.setNewLocUnitList(new ArrayList());
		String agencyId = SecurityUIHelper.getUserAgencyId(); //changed vj
		// whole if block is to get the locationId ..need to be replaced later.
		if (locationForm.getLocationId().equals("") || locationForm.getLocationId() == null){
			locationForm.setLocationId(aRequest.getParameter("locationId"));	
			if(aRequest.getParameter("locationId").equals("") || aRequest.getParameter("locationId") == null){
				UILocationUnitHelper.populateLocationByLocationCd(locationForm, agencyId);
			}
		}	
		UILocationUnitHelper.populateLocationDetails(locationForm, locationForm.getLocationId());
		Collection<LocationResponseEvent> locationUnits = UILocationUnitHelper.getLocationUnitList(locationForm.getLocationId(), agencyId);
		ArrayList<LocationResponseEvent> activeLocationUnits = new ArrayList<LocationResponseEvent>();
		
		Iterator<LocationResponseEvent> locationIter = locationUnits.iterator();
		while(locationIter != null && locationIter.hasNext())
		{
		    LocationResponseEvent locationResponse = (LocationResponseEvent)locationIter.next();
		    if(locationResponse != null && locationResponse.getStatusId() != null && !"".equals(locationResponse.getStatusId()))
		    {
			if(!locationResponse.getStatusId().equalsIgnoreCase("I"))
			{
			    activeLocationUnits.add(locationResponse);
			}
		    }
		    
		}
		
		locationForm.setLocationUnitList(activeLocationUnits);
		locationForm.setAction(UIConstants.MANAGE_LOCATION_UNITS);
		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
	}

	/**
	 *  method to check whether location unit already exists in both the
	 * newly added location unit list and the existing location unit list in the
	 * database.
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward addLocationUnit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
		locationForm.setAction(UIConstants.ADD);
		String locationId = locationForm.getLocationId();
		if (!locationForm.getNewLocUnitList().isEmpty()) {
			Collection newUnitList = locationForm.getNewLocUnitList();
			LocationForm.LocationUnit newLocUnit1;
			Iterator unitIterator = newUnitList.iterator();
			while (unitIterator.hasNext()) {
				newLocUnit1 = (LocationForm.LocationUnit) unitIterator.next();
				if (newLocUnit1.getJuvUnitCd().equalsIgnoreCase(locationForm.getLocUnit().getJuvUnitCd())
						|| newLocUnit1.getLocationUnitName().equalsIgnoreCase(
								locationForm.getLocUnit().getLocationUnitName())) {
					//sendToErrorPage(aRequest, "error.duplicate.locationUnit");
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.duplicate.locationUnit"));
					saveErrors(aRequest, errors);
					return aMapping.findForward(UIConstants.ADD_SUCCESS);
				}
			}
		}

		LocationForm.LocationUnit newLocationUnit = new LocationForm.LocationUnit();
		newLocationUnit.setJuvUnitCd(locationForm.getLocUnit().getJuvUnitCd());
		newLocationUnit.setLocationUnitName(locationForm.getLocUnit().getLocationUnitName());
		newLocationUnit.setPhoneNumber(locationForm.getLocUnit().getPhoneNumber());
		ValidateLocationUnitDetailsEvent validateEvent = new ValidateLocationUnitDetailsEvent();
		validateEvent.setLocationId(locationId);
		validateEvent.setJuvUnitCd(newLocationUnit.getJuvUnitCd());
		validateEvent.setLocationUnitName(newLocationUnit.getLocationUnitName());

		//CompositeResponse compositeResponse = postRequestEvent(validateEvent);
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(validateEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);	
		
		
		if (compositeResponse != null && compositeResponse.hasResponses()) {
			ErrorResponseEvent errorResp = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse,
					ErrorResponseEvent.class);
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorResp.getMessage()));
			saveErrors(aRequest, errors);
			//sendToErrorPage(aRequest, errorResp.getMessage());
		} else {
			locationForm.getNewLocUnitList().add(newLocationUnit);
			locationForm.clearLocationUnit();
			//locationForm.setAction(UIConstants.ADD);
		}
		return aMapping.findForward(UIConstants.ADD_SUCCESS);

	}

	/**
	 *  method to remove the selected location unit from the newly added
	 * location units
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward removeLocationUnit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		LocationForm locationForm = (LocationForm) aForm;
		Collection newLocUnitList = locationForm.getNewLocUnitList();
		String locationUnitId = aRequest.getParameter("locationUnitId");
		if (!newLocUnitList.isEmpty() && newLocUnitList != null) {
			Iterator iterator = newLocUnitList.iterator();
			while (iterator.hasNext()) {
				LocationForm.LocationUnit locUnitData = (LocationForm.LocationUnit) iterator.next();
				if ((locUnitData.getJuvUnitCd().equalsIgnoreCase(locationUnitId))) {
					newLocUnitList.remove(locUnitData);
					break;
				}
			}
			locationForm.setNewLocUnitList(newLocUnitList);
		}
		locationForm.clearLocationUnit();
		locationForm.setAction(UIConstants.ADD);
		return aMapping.findForward(UIConstants.ADD_SUCCESS);

	}

	/**
	 *  method to fetch location JuvenileLocationUnit details for an given
	 * location unit id.
	 * 
	 * @param locationUnitId
	 * @return
	 */
	private LocationResponseEvent getJuvLocationUnitDetails(String locationUnitId) {
		GetJuvLocationUnitDetailsEvent reqEvent = new GetJuvLocationUnitDetailsEvent();
		reqEvent.setJuvLocUnitId(locationUnitId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(reqEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		LocationResponseEvent respEvent = (LocationResponseEvent) MessageUtil.filterComposite(response,
				LocationResponseEvent.class);
		respEvent.setStatus(SimpleCodeTableHelper.getDescrByCode("LOCATION_STATUS" , respEvent.getStatusId()));
		return respEvent;
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
		Map keyMap = new HashMap();
		keyMap.put("button.manageLocationUnits", "manageLocationUnits");
		keyMap.put("button.addLocationUnit", "addLocationUnit");
		keyMap.put("button.remove", "removeLocationUnit");
		keyMap.put("button.activate", "activate");
		keyMap.put("button.inactivate", "inActivate");
		keyMap.put("button.update", "update");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}

		
}
