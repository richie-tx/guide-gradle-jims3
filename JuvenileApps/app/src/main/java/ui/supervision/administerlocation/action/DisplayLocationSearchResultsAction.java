//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerlocation\\action\\DisplayLocationSearchResultsAction.java

package ui.supervision.administerlocation.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.administerlocation.GetJuvenileLocationEvent;
import messaging.administerlocation.GetLocationsEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDAdministerServiceProviderConstants;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import pd.supervision.administerserviceprovider.administerlocation.Location;
import ui.common.Address;
import ui.common.CodeHelper;
import ui.security.SecurityUIHelper;
import ui.supervision.administerlocation.UILocationUnitHelper;
import ui.supervision.administerlocation.form.LocationForm;

public class DisplayLocationSearchResultsAction extends LookupDispatchAction {

	/**
	 * @roseuid 45117C530132
	 */
	public DisplayLocationSearchResultsAction() {

	}

	
	public ActionForward submit(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			LocationForm locationForm = (LocationForm) aForm;

			
			GetLocationsEvent locationEvent =
						new GetLocationsEvent();

			locationForm.setIsLocationUnitPage(null);
			// Populate everything from the form.  The Command will use the correct values
			// based off the search type selected by the user.

			
			locationEvent.setServiceProviderName(locationForm.getServiceProviderName());
			locationEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());// changed vj
			locationEvent.setServiceTypeId(locationForm.getServiceTypeId());
			locationEvent.setIsInHouse(locationForm.getIsInHouse());
			locationEvent.setStatusId(locationForm.getStatusId());
			locationEvent.setLocationName(locationForm.getLocationName());			
			
			if(locationForm.getLocationCd() != null && !locationForm.getLocationCd().equals("")){
			    locationEvent.setLocationCd(locationForm.getLocationCd());
			    
			} else {
			    
			    if(locationForm.getLocationUnitId() != null && !locationForm.getLocationUnitId().equals("")){
				    String locId = UILocationUnitHelper.getLocationIdFromLocationUnit(locationForm.getLocationUnitId());
				    
				    Location loc = Location.find(locId);
				    
				    locationEvent.setLocationCd(loc.getLocationCd());
				}
			}
			
			Address locationAddress = locationForm.getLocationAddress();
			
			locationEvent.setStreetNum(locationAddress.getStreetNumber());
			locationEvent.setStreetName(locationAddress.getStreetName());
			locationEvent.setCity(locationAddress.getCity());
			locationEvent.setStateId(locationAddress.getStateId());
			locationEvent.setZipCode(locationAddress.getZipCode());
						
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(locationEvent);
			
			CompositeResponse response = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(response);
			MessageUtil.processReturnException(dataMap);

			//Handle error thrown as ErrorResponseEvent from the command, if there is any
			//Expected error: Number of results matching this criteria is greater than 2000.
			ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(response, ErrorResponseEvent.class);
			if(error != null) {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
				saveErrors(aRequest, errors);
				return aMapping.findForward("searchFailure");
			}

			
			Collection locationsList = null;
			if (dataMap.containsKey(PDAdministerServiceProviderConstants.LOCATION_EVENT_TOPIC))
			{
				locationsList = (Collection) dataMap.get(PDAdministerServiceProviderConstants.LOCATION_EVENT_TOPIC);
				if (locationsList.size() == 1){
					Iterator iter = locationsList.iterator();
					LocationResponseEvent result = (LocationResponseEvent)  iter.next();
					this.setLocationForm(locationForm,result.getLocationId());					
					//<KISHORE>JIMS200059292 : Administer Location-Unit Location Inactivation(KK)
					locationForm.setActiveLocationUnitExists(false);
					if(StringUtils.isNotEmpty(locationForm.getAgencyId()) && UIConstants.ACTIVE_STATUS_ID.equalsIgnoreCase(locationForm.getStatusId())){
						ArrayList<LocationResponseEvent> locUnits = (ArrayList)UILocationUnitHelper.getLocationUnitList(locationForm.getLocationId(), locationForm.getAgencyId());
						Iterator<LocationResponseEvent> iter1 = locUnits.iterator();
						while(iter1.hasNext()){
							LocationResponseEvent event = iter1.next();
							
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
					locationForm.setAction("view");
					return aMapping.findForward(UIConstants.SUCCESS);									
				}
				else if (locationsList.size() > 1){
					Collections.sort((List)locationsList);
					locationForm.setLocationSearchResults(locationsList);
					return aMapping.findForward(UIConstants.LISTSUCCESS);
				}			
			}
			else
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecords"));
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.SEARCH_FAILURE);
			}
			return null;																		
		}
		
		public ActionForward refresh(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse)
			{
				LocationForm locationForm = (LocationForm) aForm;

				locationForm.clear();
				locationForm.clearAddress();
			
				return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
			}

		public ActionForward createLocation(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse)
			{
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

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();	
		keyMap.put("button.submit", "submit");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.createLocation", "createLocation");
		keyMap.put("button.cancel","cancel");
		keyMap.put("button.back","back");
		return keyMap;	}
	
	public void setLocationForm(LocationForm locationForm, String locationId) {
		LocationResponseEvent lre = new LocationResponseEvent();

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
	 /**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    */
	   public ActionForward back(
	   		ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) 
	   {
		    return aMapping.findForward(UIConstants.BACK);
	   }  
	   /**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    */
	   public ActionForward cancel(
	   		ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) 
	   {
		    return aMapping.findForward(UIConstants.CANCEL);
	   }  
	
}
