// Source file:
// C:\\views\\MJCW\\app\\src\\pd\\supervision\\calendar\\transactions\\SaveProgramReferralCommand.java

package pd.supervision.programreferral.transactions;

import java.util.Iterator;
import java.util.List;

import naming.PDCalendarConstants;
import naming.ProgramReferralConstants;

import messaging.programreferral.SaveProgramReferralAssociationsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.calendar.ServiceEventAttendance;
import pd.supervision.programreferral.JuvenileEventReferral;
import pd.supervision.programreferral.JuvenileProgramReferral;

public class SaveProgramReferralAssociationsCommand implements ICommand {

	/**
	 * @roseuid 463BA5250318
	 */
	public SaveProgramReferralAssociationsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 463A3F520390
	 */
	public void execute(IEvent event) {
		
		SaveProgramReferralAssociationsEvent saveRefAssocEvent = (SaveProgramReferralAssociationsEvent)event;
		String programReferralId = saveRefAssocEvent.getProgramReferralId();
		List attachedEvents = saveRefAssocEvent.getAttachedEvents();
		JuvenileProgramReferral programReferral = JuvenileProgramReferral.find(programReferralId);
		
		if (attachedEvents!=null){
			Iterator iter = attachedEvents.iterator();
			while (iter.hasNext()){
				String eventId = (String)iter.next();
				//<KISHORE>JIMS200057235 : MJCW Sch Cal Even and View Cal - Attend Status is incorrect
				Iterator iteration = ServiceEventAttendance.findAll("serviceEventId", eventId);
				while(iteration.hasNext()){
					ServiceEventAttendance att = (ServiceEventAttendance)iteration.next();
					// Update the status to CO for all the events requested by the juvenile
					if(programReferral != null && 
							ProgramReferralConstants.ACCEPTED.equalsIgnoreCase(programReferral.getReferralStatusCd()) && 
							programReferral.getJuvenileId().equalsIgnoreCase(att.getJuvenileId()) && 
							PDCalendarConstants.JUV_ATTEND_STATUS_UNCONFIRMED.equalsIgnoreCase(att.getAttendanceStatusCd()))
						att.setAttendanceStatusCd(PDCalendarConstants.JUV_ATTEND_STATUS_CONFIRMED);
				}
				
				JuvenileEventReferral eventReferral = new JuvenileEventReferral();
				eventReferral.setProgramReferralId(programReferralId);
				eventReferral.setServiceEventId(eventId);
			}
		}					
	}
	
	/**
	 * @param event
	 * @roseuid 463A3F52039E
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 463A3F5203A0
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 463A3F5203A2
	 */
	public void update(Object anObject) {

	}

}
