// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\transactions\\SaveCSFVReorderItenaryCommand.java

package pd.supervision.cscdcalendar.transactions;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import messaging.cscdcalendar.SaveCSFVReorderItineraryEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.cscdcalendar.CSEvent;
import pd.supervision.cscdcalendar.FieldVisitEvent;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveCSFVReorderItineraryCommand implements ICommand {

	/**
	 * @roseuid 479A0EB703A6
	 */
	public SaveCSFVReorderItineraryCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4798EEDA00BA
	 */
	public void execute(IEvent event) {

		SaveCSFVReorderItineraryEvent reorderEvent = (SaveCSFVReorderItineraryEvent) event;
		
		Map fvEventIdMap = reorderEvent.getFvIdToOrderMap();
		Iterator fvEventsIte = FieldVisitEvent.findAll(reorderEvent.getFvIteneraryId());
		while (fvEventsIte.hasNext()) {
			FieldVisitEvent fieldVisit = (FieldVisitEvent) fvEventsIte.next();
			Set keySet = fvEventIdMap.entrySet();
			Iterator keyIter = keySet.iterator();
			while (keyIter.hasNext()) {
				Map.Entry mapObj = (Map.Entry) keyIter.next();
				if (fieldVisit.getOID().equals(mapObj.getKey().toString())){
					fieldVisit.setSequenceNum(mapObj.getValue().toString());
					break;					
				}
			}
		}
	
			Map fvStartEndMap = reorderEvent.getFvStartEndTimeMap();			
			Set keySet = fvStartEndMap.entrySet();
			Iterator keyIter = keySet.iterator();
			while (keyIter.hasNext()) {
				Map.Entry mapObj = (Map.Entry) keyIter.next();
				CSEvent csEvent = CSEvent.find(mapObj.getKey().toString());
				List times = (List) fvStartEndMap.get(mapObj.getKey().toString());
				csEvent.setStartTime((Date) times.get(0));
				csEvent.setEndTime((Date)times.get(1));
			}
			
			
	
	}

	/**
	 * @param event
	 * @roseuid 4798EEDA00BC
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4798EEDA00C8
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4798EEDA00CA
	 */
	public void update(Object anObject) {

	}

	/**
	 * @param updateObject
	 * @roseuid 479A0EB703C5
	 */
	/*
	 * public void update(Object updateObject) {
	 *  }
	 */
}
