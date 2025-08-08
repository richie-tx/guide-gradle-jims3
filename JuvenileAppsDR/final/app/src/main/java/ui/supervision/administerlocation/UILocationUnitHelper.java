/*
 * Created on Jun 15, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerlocation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;
import messaging.administerlocation.GetAllJuvLocationUnitsByLocationIdEvent;
import messaging.administerlocation.GetJuvenileLocationEvent;
import messaging.administerlocation.GetLocationsEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.administerserviceprovider.reply.ContactFundSourceResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import ui.common.PhoneNumber;
import ui.common.SimpleCodeTableHelper;
import ui.supervision.administerlocation.form.LocationForm;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UILocationUnitHelper {
	
	/**
	 * Method to fetch Juvenile Location Units Collection.
	 * @param locationId
	 * @param agencyId
	 * @return
	 */
	public static Collection getLocationUnitList(String locationId, String agencyId){
		GetAllJuvLocationUnitsByLocationIdEvent reqEvent = (GetAllJuvLocationUnitsByLocationIdEvent)EventFactory.getInstance("GetAllJuvLocationUnitsByLocationId"); 
		reqEvent.setLocationId(locationId);
		reqEvent.setAgencyId(agencyId);
		PhoneNumber phoneNumber = new PhoneNumber("");
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(reqEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Collection locationUnits = MessageUtil.compositeToCollection(response, LocationResponseEvent.class);
		Iterator iter = locationUnits.iterator();
		Collection temp = new ArrayList();
		while(iter.hasNext()){
			LocationResponseEvent locUnitResp = (LocationResponseEvent)iter.next();
			locUnitResp.setStatus(SimpleCodeTableHelper.getDescrByCode("LOCATION_STATUS" , locUnitResp.getStatusId()));
			locUnitResp.setPhoneNumber(phoneNumber.formatPhoneNumberWithDashes(locUnitResp.getPhoneNumber()));
			locUnitResp.setLocationFax(phoneNumber.formatPhoneNumberWithDashes(locUnitResp.getLocationFax()));
		temp.add(locUnitResp);
		}
	return temp;
	}
	
	
	public static Collection<LocationResponseEvent> getAllLocationUnits(){
	    
	    List<LocationResponseEvent> locationUnits = new ArrayList<LocationResponseEvent>();
	    Iterator<JuvLocationUnit> i = JuvLocationUnit.findAll();		

		//IDispatch dispatch3 = EventManager.getSharedInstance(EventManager.REPLY);

		while (i.hasNext())
		{
			JuvLocationUnit juvLocUnit = (JuvLocationUnit) i.next();
			LocationResponseEvent juvLocResponseEvent = new LocationResponseEvent();
			juvLocResponseEvent.setLocationUnitName(juvLocUnit.getLocationUnitName());
			juvLocResponseEvent.setJuvUnitCd(juvLocUnit.getJuvUnitCd());
			juvLocResponseEvent.setLocationId(juvLocUnit.getLocationId());
			juvLocResponseEvent.setJuvLocationUnitId(juvLocUnit.getJuvLocUnitId());
			juvLocResponseEvent.setStatusId(juvLocUnit.getUnitStatusId());
			
			locationUnits.add(juvLocResponseEvent);
		}
		
		Collections.sort(locationUnits, new Comparator<LocationResponseEvent>(){
		    @Override
		    public int compare(LocationResponseEvent f1, LocationResponseEvent f2){

			return f1.getJuvUnitCd().compareTo(f2.getJuvUnitCd());
		    }
		});
		
		return locationUnits;
	}
	
	public static String getLocationIdFromLocationUnit(String locationUnitId){
	    
	    Collection<LocationResponseEvent> locationUnits = new ArrayList<LocationResponseEvent>();
	    Iterator<JuvLocationUnit> i = JuvLocationUnit.findAll();		

		String locationId = null;

		while (i.hasNext())
		{
		    if(locationUnitId != null && !locationUnitId.equals("")){
			
			JuvLocationUnit juvLocUnit = (JuvLocationUnit) i.next();
			
			if(locationUnitId.equals(juvLocUnit.getJuvLocUnitId())){
			    locationId = juvLocUnit.getLocationId();
			    break;
			}
			
		    }
			
		}		
		
		return locationId;
	}
	
	/**
	 * Method to fetch Location Details.
	 * @param locationForm
	 * @param locationId
	 */
	public static void populateLocationDetails(LocationForm locationForm, String locationId) {
		GetJuvenileLocationEvent gslv = new GetJuvenileLocationEvent();
		gslv.setLocationId(locationId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(gslv);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(response);
		LocationResponseEvent lre = (LocationResponseEvent) MessageUtil.filterComposite(response, LocationResponseEvent.class);
		locationForm.setForm(lre);
	}
	
	/**
	 * Method to fetch Location Details based upon LocationCd.
	 * @param locationForm
	 * @param locationId
	 */
	public static void populateLocationByLocationCd (LocationForm locationForm, String agencyId) {
		
		GetLocationsEvent locationEvent = new GetLocationsEvent();
		locationEvent.setAgencyId(agencyId);
		locationEvent.setLocationCd(locationForm.getLocationCd());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(locationEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(response);
		LocationResponseEvent lre = (LocationResponseEvent) MessageUtil.filterComposite(response, LocationResponseEvent.class);
		locationForm.setForm(lre);
	}
	
	
}
