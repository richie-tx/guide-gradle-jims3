// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\mentalhealth\\transactions\\GetMentalHealthHospitalizationListCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import java.util.Iterator;

import pd.juvenilecase.mentalhealth.Hospitalization;
import messaging.mentalhealth.GetMentalHealthHospitalizationListEvent;
import messaging.mentalhealth.reply.HospitalizationResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author cc_vnarsingoju
 * This Command retrieves all the Hospitalization Details for a given Juvenile Number
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetMentalHealthHospitalizationListCommand implements ICommand {

	/**
	 * @roseuid 45B0DEBC021F
	 */
	public GetMentalHealthHospitalizationListCommand() {

	}

	/**
	 * @param event
	 * @roseuid 45B0CB76015B
	 */
	public void execute(IEvent event) {
		GetMentalHealthHospitalizationListEvent mEvent = (GetMentalHealthHospitalizationListEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator hospIterator = Hospitalization.findAll(mEvent);
		while (hospIterator.hasNext()) {
			Hospitalization hospitalization = (Hospitalization) hospIterator.next();
			HospitalizationResponseEvent hospEvent = getHospitalizationResponseEvent(hospitalization);
			dispatch.postEvent(hospEvent);
		}

	}

	private HospitalizationResponseEvent getHospitalizationResponseEvent(Hospitalization hospitalization) {
		
		HospitalizationResponseEvent responseEvent = new HospitalizationResponseEvent();
		responseEvent.setAdmissionDate(hospitalization.getAdmitDate());
		responseEvent.setFacilityName(hospitalization.getFacilityName());
		responseEvent.setAdmittingPhysicianName(hospitalization.getAdmitPhysicianName());
		responseEvent.setReleaseDate(hospitalization.getReleaseDate());
		responseEvent.setPhysicianPhone(hospitalization.getPhysicianPhone());
		responseEvent.setHospitalizationReason(hospitalization.getHospitalizationReason());
		responseEvent.setHospitalizationId(hospitalization.getHospitalizationId());
		return responseEvent;
		
	}

	/**
	 * @param event
	 * @roseuid 45B0CB76015D
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 45B0CB76016A
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 45B0CB76016C
	 */
	public void update(Object anObject) {

	}

}
