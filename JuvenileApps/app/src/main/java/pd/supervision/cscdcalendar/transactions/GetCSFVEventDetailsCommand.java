// Source file:
// C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\pd\\supervision\\cscdcalendar\\transactions\\GetCSFVEventDetailsCommand.java

package pd.supervision.cscdcalendar.transactions;

import java.util.ArrayList;
import java.util.Iterator;

import naming.PDCodeTableConstants;

import messaging.cscdcalendar.GetCSFVEventDetailsEvent;
import messaging.cscdcalendar.reply.CSFieldVisitResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.cscdcalendar.FieldVisitAssociate;
import pd.supervision.cscdcalendar.FieldVisitEvent;
import pd.supervision.cscdcalendar.FieldVisitHelper;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GetCSFVEventDetailsCommand implements ICommand {

	/**
	 * @roseuid 479A0EB50210
	 */
	public GetCSFVEventDetailsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4798EED80369
	 */
	public void execute(IEvent event) {
		GetCSFVEventDetailsEvent fvEvents = (GetCSFVEventDetailsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		FieldVisitEvent fieldVisitDetails = FieldVisitEvent.find(fvEvents.getEventId());
		CSFieldVisitResponseEvent respEvent = FieldVisitHelper.getCSFVEventResponseEvent(fieldVisitDetails);
		if (fieldVisitDetails.getContactMethodCd() != null
				&& fieldVisitDetails.getContactMethodCd().equalsIgnoreCase(PDCodeTableConstants.CONTACT_WITH_ASSOCIATES)) {
			ArrayList fvAssocList = new ArrayList();
			Iterator fvAssocIdIter = FieldVisitAssociate.findAll("fvEventId", fvEvents.getEventId());
			while (fvAssocIdIter != null && fvAssocIdIter.hasNext()) {
				fvAssocList.add(((FieldVisitAssociate)fvAssocIdIter.next()).getFvAssociateId());
			}
			respEvent.setAssociateId((String[]) fvAssocList.toArray());
		}
		dispatch.postEvent(respEvent);

	}

	/**
	 * @param event
	 * @roseuid 4798EED8036B
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4798EED80378
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4798EED8037A
	 */
	public void update(Object anObject) {

	}

}
