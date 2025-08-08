
//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\calendar\\transactions\\GetJuvenileAttendanceCommand.java

package pd.supervision.calendar.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.calendar.GetProgramReferralWithEventsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import pd.common.calendar.CalendarEventContext;
import pd.supervision.calendar.ServiceEvent;
import pd.supervision.programreferral.JuvenileProgramReferral;
import pd.supervision.programreferral.JuvenileProgramReferralAssignmentHistory;
//import mojo.km.calendaring.CalendarEventContext;

public class GetProgramReferralWithEventsCommand implements ICommand 
{
   
   /**
    * @roseuid 45702FB40066
    */
   public GetProgramReferralWithEventsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850264
    */
	public void execute(IEvent event) 
	{
		
		GetProgramReferralWithEventsEvent evt = (GetProgramReferralWithEventsEvent) event;
		
		
		//updating the JUVPROGREF with the new casefile id 
		JuvenileProgramReferral progRef1 = JuvenileProgramReferral.find(evt.getProgramReferralId());
		progRef1.setCasefileId(evt.getCasefileId());
		//update rest of the stuff. #40734
		progRef1.setControllingReferralNum(evt.getControllingReferralNum());
		progRef1.setAcknowledgementDate(evt.getAcknowlegmentDate());
		progRef1.setEndDate(evt.getEndDate());
		progRef1.setReferralSubStatusCd(evt.getSubstatusCd());
		progRef1.setProgramOutcomeCd(evt.getProgramOutomeCd());
		progRef1.setProgramOutcomeSubcategoryCd(evt.getProgramOutcomeSubcategoryCd());
			
		
		//adding to the history // bug fix #37849
		JuvenileProgramReferralAssignmentHistory prAssignmentHistory = new JuvenileProgramReferralAssignmentHistory();
		prAssignmentHistory.setCasefileId(evt.getCasefileId());
		prAssignmentHistory.setProgramReferralId(progRef1.getOID());
		prAssignmentHistory.setProgramReferralAssignmentDate(evt.getAssignmentDate());
		// bug fix #37849
		//added for bug 12175
		String origJuvNum = progRef1.getJuvenileId();
		//Getting details from CSPROGRAMATTEND 
		Iterator iter = ServiceEvent.findAll(event);
		Date eventDate;
		Date today = new Date();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while (iter.hasNext()) 
		{
			ServiceEvent servEvent = (ServiceEvent) iter.next();
			eventDate = servEvent.getStartDatetime();
			
			//comparing whether future date events			
			if(eventDate.after(today)){
				
				Integer calEventId = servEvent.getCalendarEventId();
				Iterator calEventItr = CalendarEventContext.findByCalendarEventId(calEventId);
				while (calEventItr.hasNext())
				{
					CalendarEventContext calEventContxt = (CalendarEventContext) calEventItr.next();
					//added for bug 12175-- getting the correct juvenile as multiple juvenile may have same calEventId
					String transferredJuvNum = calEventContxt.getJuvenileId();
					if(transferredJuvNum != null && transferredJuvNum.equals(origJuvNum)){
						calEventContxt.setCaseFileId(evt.getCasefileId());						
					}
				}				
			}
			
		}
	}
   
   /**
    * @param event
    * @roseuid 456F2D850272
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 456F2D850274
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 456F2D850276
    */
   public void update(Object anObject) 
   {
    
   }
   

}
