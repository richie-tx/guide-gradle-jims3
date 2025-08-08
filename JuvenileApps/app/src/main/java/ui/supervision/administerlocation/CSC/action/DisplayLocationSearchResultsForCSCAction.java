/*
 * Created on Dec 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerlocation.CSC.action;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerlocation.GetLocationEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.csserviceprovider.GetLocationByCSServiceProviderEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.StringUtil;
import ui.security.SecurityUIHelper;
import ui.supervision.administerlocation.form.LocationForm;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayLocationSearchResultsForCSCAction extends LookupDispatchAction
{
	
	/**
	 * @cc_bjangay
	 */
	public DisplayLocationSearchResultsForCSCAction() {

	}

	
	public ActionForward submit(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			LocationForm locationForm = (LocationForm) aForm;

			
			GetLocationByCSServiceProviderEvent locationEvent =
						new GetLocationByCSServiceProviderEvent();

			// Populate everything from the form.  The Command will use the correct values
			// based on the search type selected by the user.

			
			locationEvent.setServiceProviderName(locationForm.getServiceProviderName());
			locationEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());// changed vj
			//locationEvent.setServiceTypeId(locationForm.getServiceTypeId());
			locationEvent.setIsInHouse(locationForm.getIsInHouse());
			locationEvent.setLocationStatusCode(locationForm.getStatusId());
			locationEvent.setRegionCode(locationForm.getLocationRegionId());
			locationEvent.setLocationName(locationForm.getLocationName());
			locationEvent.setLocationCode(locationForm.getLocationCd());
			
			Address locationAddress = locationForm.getLocationAddress();
			
			locationEvent.setStreetNum(locationAddress.getStreetNumber());
			locationEvent.setStreetName(locationAddress.getStreetName());
			locationEvent.setCity(locationAddress.getCity());
			locationEvent.setStateCode(locationAddress.getStateId());
			locationEvent.setZipCode(locationAddress.getZipCode());
						
			//IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			//dispatch.postEvent(locationEvent);
			//CompositeResponse response = 
			
			List<LocationResponseEvent> location_responses = 
					MessageUtil.postRequestListFilter(locationEvent, LocationResponseEvent.class);
			
			if (location_responses.size() == 1)
			{
				this.setLocationForm(locationForm,location_responses.get(0).getLocationId());					
				locationForm.setAction("view");
				return aMapping.findForward(UIConstants.SUCCESS);									
			}
			else if (location_responses.size() > 1)
			{
				//Collections.sort((List)location_responses);
				locationForm.setLocationSearchResults(location_responses);
				return aMapping.findForward(UIConstants.LISTSUCCESS);
			}
			else
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecords"));
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.SEARCH_FAILURE);
			}
			
			/*
			//CompositeResponse response = (CompositeResponse) dispatch.getReply();
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
			*/
			
			/*Collection locationsList = null;
			if (dataMap.containsKey(PDAdministerServiceProviderConstants.LOCATION_EVENT_TOPIC))
			{
				locationsList = (Collection) dataMap.get(PDAdministerServiceProviderConstants.LOCATION_EVENT_TOPIC);
				if (locationsList.size() == 1){
					Iterator iter = locationsList.iterator();
					LocationResponseEvent result = (LocationResponseEvent)  iter.next();
					this.setLocationForm(locationForm,result.getLocationId());					
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
			return null;*/																		
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
				
				Collection locationRegionList = CodeHelper.getCodes(PDCodeTableConstants.LOCATION_REGION,true);
				Collections.sort((List)locationRegionList);
				locationForm.setLocationRegionList(locationRegionList);
			
				return aMapping.findForward(UIConstants.CREATE);
			}

		
		public ActionForward viewLink(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
				HttpServletResponse aResponse) 
		{
			LocationForm locationForm = (LocationForm) aForm;
		
			locationForm.setAction(UIConstants.VIEW);
			locationForm.setSecondaryAction("");
			
			String selectedLocationId = locationForm.getSelectedValue();
			
			if (!StringUtil.isEmpty(selectedLocationId))
			{
				LocationResponseEvent lre = new LocationResponseEvent();

				GetLocationEvent gslv = new GetLocationEvent();
				gslv.setLocationId(selectedLocationId);

				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(gslv);

				CompositeResponse response = (CompositeResponse) dispatch.getReply();
				MessageUtil.processReturnException(response);
				lre = (LocationResponseEvent) MessageUtil.filterComposite(response, LocationResponseEvent.class);

				locationForm.setForm(lre);
				return aMapping.findForward(UIConstants.SUCCESS);
			}
			else
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecords"));
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.SEARCH_FAILURE);
			}
		}
		
		
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();	
		keyMap.put("button.submit", "submit");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.createLocation", "createLocation");
		keyMap.put("button.viewLink", "viewLink");
		keyMap.put("button.cancel","cancel");
		keyMap.put("button.back","back");
		return keyMap;	}
	
	public void setLocationForm(LocationForm locationForm, String locationId) {
		LocationResponseEvent lre = new LocationResponseEvent();

		GetLocationEvent gslv = new GetLocationEvent();
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
