package pd.productionsupport.transactions;

import java.util.ArrayList;
import java.util.Iterator;

import messaging.calendar.GetJuvenileAttendanceEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.productionsupport.DeleteProductionSupportJuvenileReferralEvent;
import messaging.productionsupport.RetrieveJuvenileProgramReferralByReferralNumberEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.common.calendar.CalendarEvent;
import pd.common.calendar.CalendarEventContext;
import pd.juvenilecase.JuvenileCasefile;
import pd.supervision.calendar.ServiceEvent;
import pd.supervision.calendar.ServiceEventAttendance;
import pd.supervision.programreferral.JuvenileEventReferral;
import pd.supervision.programreferral.JuvenileProgramReferral;

public class DeleteProductionSupportJuvenileReferralCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public DeleteProductionSupportJuvenileReferralCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		DeleteProductionSupportJuvenileReferralEvent deleteJuvenileReferralsEvent = (DeleteProductionSupportJuvenileReferralEvent) event;
		if (deleteJuvenileReferralsEvent.getReferralNum() != null && deleteJuvenileReferralsEvent.getReferralNum().length() > 0){
			// get the juvenile referral record
			RetrieveJuvenileProgramReferralByReferralNumberEvent requestEvent = new RetrieveJuvenileProgramReferralByReferralNumberEvent();
			requestEvent.setReferralNum(deleteJuvenileReferralsEvent.getReferralNum());
			Iterator  juvenileProgramReferralIter = JuvenileProgramReferral.findAll(requestEvent);
			JuvenileProgramReferral juvenileProgramReferralRecord = null;
			while(juvenileProgramReferralIter.hasNext()){
				juvenileProgramReferralRecord = (JuvenileProgramReferral)juvenileProgramReferralIter.next();
			}
			
			// retrieve all CSEVENTREFERRAL by JUVPROGREF_ID and place in collection
			JuvenileEventReferral programEventReferral = null;
			ArrayList<JuvenileEventReferral> juvenileEventReferralList = new ArrayList<JuvenileEventReferral>();
			if(juvenileProgramReferralRecord != null){
				Iterator<JuvenileEventReferral> eventReferralIter = JuvenileEventReferral.findAll("programReferralId", juvenileProgramReferralRecord.getOID());
				if(eventReferralIter != null){
					while(eventReferralIter.hasNext()){
						programEventReferral = (JuvenileEventReferral)eventReferralIter.next();
						juvenileEventReferralList.add(programEventReferral);
					}
				}
			}
			
			// retrieve JUVENILE_ID by CASEFILE_ID
			JuvenileCasefile casefile = JuvenileCasefile.find(juvenileProgramReferralRecord.getCasefileId());
			
			// retrieve and delete each CSSERVATTEND for a given juvenile_id and servevent_id combination
			Iterator<ServiceEventAttendance> serviceEventAttendIter = null;
			// loop through based on each event referral, and delete the serviceEvent/Juvenile combination
			for(JuvenileEventReferral juvenileEventReferral: juvenileEventReferralList){
				if(juvenileEventReferral != null){
					GetJuvenileAttendanceEvent getServiceAttendRecordEvent = new GetJuvenileAttendanceEvent();
					getServiceAttendRecordEvent.setServiceEventId(juvenileEventReferral.getServiceEventId());
					getServiceAttendRecordEvent.setJuvenileId(casefile.getJuvenileId());
					serviceEventAttendIter = ServiceEventAttendance.findAll(getServiceAttendRecordEvent);
				}
				// delete all CSSERVATTEND found for a given juvenilie_id and servevent_id
				if(serviceEventAttendIter != null && serviceEventAttendIter.hasNext()){
					while(serviceEventAttendIter.hasNext()){
						ServiceEventAttendance serviceEventAttend = (ServiceEventAttendance)serviceEventAttendIter.next();
						if(serviceEventAttend != null){
							serviceEventAttend.delete();
							new Home().bind(serviceEventAttend);
							System.out.println("Deleting CSSERVATTEND record with  servAttendId: " + serviceEventAttend.getOID());
							System.out.println("Deleting CSSERVATTEND record with  servEventId : " + serviceEventAttend.getServiceEventId());
							System.out.println("Deleting CSSERVATTEND record with  JuvenileId: " + casefile.getJuvenileId());
							System.out.println("Deleting CSSERVATTEND record with  ProviderProgramId : " + juvenileProgramReferralRecord.getProvProgramId());
						}
					}		
				}
			}
			

			// loop through based on each event referral, and get the calendar event for each service event and add to collection
			ArrayList<CalendarEvent> calendarEventList = new ArrayList<CalendarEvent>();
			for(JuvenileEventReferral juvenileEventReferral: juvenileEventReferralList){
				if(juvenileEventReferral != null){
					CalendarEvent calEvent = ServiceEvent.find(juvenileEventReferral.getServiceEventId());
					calendarEventList.add(calEvent);
				}
			}
			
			// loop through based on each calendar event, get calendar event context, and add context to collection
			ArrayList<CalendarEventContext> calendarEventContextDeleteList = new ArrayList();
			for(CalendarEvent calEvent: calendarEventList){
				// select all JCCALEVENTCONT for a given juvenile_id and calevent_id
				Iterator<CalendarEventContext> calendarEventContextIter = CalendarEventContext.findAll("calendarEventId", calEvent.getCalendarEventId());
				while(calendarEventContextIter.hasNext()){
					CalendarEventContext context = (CalendarEventContext)calendarEventContextIter.next();
					if(context != null){
						if(context.getJuvenileId().equals(casefile.getJuvenileId())){
							calendarEventContextDeleteList.add(context);
						}		
					}
				}
			}
		
			// delete all JCCALEVENTCONT for a given juvenile_id and calevent_id
			for(CalendarEventContext deleteContext: calendarEventContextDeleteList){
				if(deleteContext != null){
					deleteContext.delete();
					new Home().bind(deleteContext);
					System.out.println("Deleting JCCALEVENTCONT record with  CalEventContId: " + deleteContext.getOID());
					System.out.println("Deleting JCCALEVENTCONT record with  JuvenileId: " + deleteContext.getJuvenileId());
					System.out.println("Deleting JCCALEVENTCONT record with  casefileId : " + deleteContext.getCaseFileId());
					System.out.println("Deleting JCCALEVENTCONT record with  calEventId : " + deleteContext.getCalendarEventId());
					System.out.println("Deleting JCCALEVENTCONT record with  probofficerId : " + deleteContext.getProbationOfficerId());
				}
			}
			
			// delete the program referral last since JCCALEVENTCONT and CSSERVATTEND depend on it and child records
			if(juvenileProgramReferralRecord != null) {
				juvenileProgramReferralRecord.delete();
				new Home().bind(juvenileProgramReferralRecord);
				System.out.println("Deleting Program Referral number : " + juvenileProgramReferralRecord.getOID());
				System.out.println("Deleting Program Referral number with Casefile Id: " + juvenileProgramReferralRecord.getCasefileId());
			} 
		}

	}

	/**
	 * @param event
	 * @roseuid 4278C7B8034F
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80359
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4278C7B80364
	 */
	public void update(Object anObject) {

	}
}
