package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.interviewinfo.reply.InterviewResponseEvent;
import messaging.productionsupport.GetProductionSupportAssociatedInterviewsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.interviewinfo.Interview;

public class GetProductionSupportAssociatedInterviewsCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public GetProductionSupportAssociatedInterviewsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
		GetProductionSupportAssociatedInterviewsEvent interviewsEvent = (GetProductionSupportAssociatedInterviewsEvent) event;  
		Iterator<Interview> interviewsIter = null;
		if(interviewsEvent.getCalendarEventId() != null){
			interviewsIter = Interview.findAll("calEventId", interviewsEvent.getCalendarEventId());
		}
		else if(interviewsEvent.getCasefileId() != null){
			interviewsIter = Interview.findAll("casefileId", interviewsEvent.getCasefileId());
		}
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while(interviewsIter != null && interviewsIter.hasNext()){
			Interview interview = (Interview)interviewsIter.next();
			InterviewResponseEvent interviewEventResponse = new InterviewResponseEvent();
			interviewEventResponse.setInterviewId(interview.getOID());
			interviewEventResponse.setCasefileId(interview.getCasefileId());
			interviewEventResponse.setCalEventId(interview.getCalEventId());
			interviewEventResponse.setInterviewDate(interview.getInterviewDate());
			interviewEventResponse.setInterviewTypeId(interview.getInterviewTypeId());
			interviewEventResponse.setLocationDescription(interview.getLocationDescription());
			interviewEventResponse.setAddressId(interview.getCustomAddressId());
			interviewEventResponse.setJuvLocationUnitId(interview.getJuvLocUnitId());
			interviewEventResponse.setInterviewStatusCd(interview.getInterviewStatusCd());
			interviewEventResponse.setOtherInventoryRecords(interview.getOtherInventoryRecords());
			interviewEventResponse.setCustomAddressValid(interview.isCustomAddressValid());
			interviewEventResponse.setSummaryNotes(interview.getSummaryNotes());
			if(interview.getCreateUserID() != null){
				interviewEventResponse.setCreateUser(interview.getCreateUserID());
				interviewEventResponse.setCreateUserID(interview.getCreateUserID()); // Prod Support
			}
			if(interview.getCreateTimestamp() != null){
				interviewEventResponse.setCreateDate(new Date(interview.getCreateTimestamp().getTime()));
			}
			if(interview.getUpdateUserID() != null){
				interviewEventResponse.setUpdateUser(interview.getUpdateUserID());
			}
			if(interview.getUpdateTimestamp() != null){
				interviewEventResponse.setUpdateDate(new Date(interview.getUpdateTimestamp().getTime()));
			}
			if(interview.getCreateJIMS2UserID() != null){
				interviewEventResponse.setCreateJims2User(interview.getCreateJIMS2UserID());
				interviewEventResponse.setCreateJIMS2UserID(interview.getCreateJIMS2UserID());
			}
			if(interview.getUpdateJIMS2UserID() != null){
				interviewEventResponse.setUpdateJims2User(interview.getUpdateJIMS2UserID()); // Prod Support
				interviewEventResponse.setUpdateJIMS2UserID(interview.getUpdateJIMS2UserID()); // Prod Support
			}
			
			dispatch.postEvent(interviewEventResponse);
			
		}
    }

    /**
     * @param event
     * @roseuid 4526B083011E
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 4526B083012B
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 4526B083012D
     */
    public void update(Object anObject)
    {

    }


}
