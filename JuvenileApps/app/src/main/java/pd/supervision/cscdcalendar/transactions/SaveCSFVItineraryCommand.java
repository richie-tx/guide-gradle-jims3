// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\transactions\\SaveCSFVIteranyCommand.java

package pd.supervision.cscdcalendar.transactions;

import org.apache.commons.lang.StringUtils;

import messaging.cscdcalendar.SaveCSFVItineraryEvent;
import messaging.cscdcalendar.reply.CSFVItineraryResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.cscdcalendar.FieldVisitItinerary;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates Command used to save the
 * Field Visit Itinerary details.
 */
public class SaveCSFVItineraryCommand implements ICommand {

	/**
	 * @roseuid 479A0EB702EA
	 */
	public SaveCSFVItineraryCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4798EED9030C
	 */
	public void execute(IEvent event) {
		SaveCSFVItineraryEvent itineraryEvent = (SaveCSFVItineraryEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		CSFVItineraryResponseEvent respEvent = new CSFVItineraryResponseEvent();
		if (itineraryEvent.isCreate()) {
			FieldVisitItinerary fvCreateItinerary = new FieldVisitItinerary();
			createFVItinerary(fvCreateItinerary, itineraryEvent);
			fvCreateItinerary = (FieldVisitItinerary) new mojo.km.persistence.Home().bind(fvCreateItinerary);
		   	respEvent.setFvItineraryId(fvCreateItinerary.getOID());		   	
		}
		if(itineraryEvent.isUpdate()){
			FieldVisitItinerary fvUpdateItinerary = FieldVisitItinerary.find(itineraryEvent.getUpdateItineraryId());
			createFVItinerary(fvUpdateItinerary, itineraryEvent);
			respEvent.setFvItineraryId(fvUpdateItinerary.getOID());
		}
		dispatch.postEvent(respEvent);
	}

	/**
	 * @param fieldVisitItinerary
	 * @param itineraryEvent
	 */
	private void createFVItinerary(FieldVisitItinerary fieldVisitItinerary, SaveCSFVItineraryEvent itineraryEvent) {
		fieldVisitItinerary.setPositionId(itineraryEvent.getPositionId());
		if (StringUtils.isNotEmpty(itineraryEvent.getRadioCallNum())) {
			fieldVisitItinerary.setRadioCallNum(itineraryEvent.getRadioCallNum());
		}
		fieldVisitItinerary.setItineraryDate(itineraryEvent.getItineraryDate());
		fieldVisitItinerary.setInOfficeFrom(itineraryEvent.getInOfficeFrom());
		fieldVisitItinerary.setInOfficeTo(itineraryEvent.getInOfficeTo());
		fieldVisitItinerary.setInFieldFrom(itineraryEvent.getInFieldFrom());
		fieldVisitItinerary.setInFieldTo(itineraryEvent.getInFieldTo());
		fieldVisitItinerary.setPhoneNum(itineraryEvent.getMobile());
		fieldVisitItinerary.setP1LastName(itineraryEvent.getP1LastName());
		fieldVisitItinerary.setP1MiddleName(itineraryEvent.getP1MiddleName());
		fieldVisitItinerary.setP1FirstName(itineraryEvent.getP1FirstName());
		fieldVisitItinerary.setP2LastName(itineraryEvent.getP2LastName());
		fieldVisitItinerary.setP2MiddleName(itineraryEvent.getP2MiddleName());
		fieldVisitItinerary.setP2FirstName(itineraryEvent.getP2FirstName());
		fieldVisitItinerary.setP3LastName(itineraryEvent.getP3LastName());
		fieldVisitItinerary.setP3MiddleName(itineraryEvent.getP3MiddleName());
		fieldVisitItinerary.setP3FirstName(itineraryEvent.getP3FirstName());
		fieldVisitItinerary.setCarTypeCd(itineraryEvent.getCarTypeCd());
		String mileageIn = itineraryEvent.getMileageIn();
		String mileageOut = itineraryEvent.getMileageOut();
		
		if ( !"".equals( mileageIn ) ){
			
			fieldVisitItinerary.setMileageIn( Integer.parseInt( mileageIn ));
		}
		
		if ( !"".equals( mileageOut )){
			
			fieldVisitItinerary.setMileageOut(Integer.parseInt( mileageOut ));
		}
		
		fieldVisitItinerary.setAutoLicenseNum(itineraryEvent.getAutoLicense());
		fieldVisitItinerary.setAutoYear(itineraryEvent.getAutoYear());
		fieldVisitItinerary.setQuadrantCd(itineraryEvent.getQuadrantCd());
		fieldVisitItinerary.setAutoModel(itineraryEvent.getAutoModel());
		fieldVisitItinerary.setAutoMake(itineraryEvent.getAutoMake());
		fieldVisitItinerary.setAutoColor(itineraryEvent.getAutoColor());
		fieldVisitItinerary.setQuadrantCd(itineraryEvent.getQuadrantCd());
	}

	/**
	 * @param event
	 * @roseuid 4798EED9030E
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4798EED9031A
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4798EED9031C
	 */
	public void update(Object anObject) {

	}

}
