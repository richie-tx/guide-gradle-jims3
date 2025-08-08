package pd.supervision.cscdcalendar.transactions;

import java.util.Iterator;
import java.util.List;

import pd.supervision.cscdcalendar.CSEventsReport;
import pd.supervision.cscdcalendar.CSEventsReportPDHelper;
import messaging.cscdcalendar.GetCSEventsReportEvent;
import messaging.cscdcalendar.GetCalendarEventsReportEvent;
import messaging.cscdcalendar.reply.CSEventsReportReponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;


/**
 * 
 * @author cc_bjangay
 *
 */
public class GetCSEventsReportCommand implements ICommand
{
	
	/**
	 * 
	 */
	public void execute(IEvent anEvent)
	{
		GetCSEventsReportEvent csEventsReportEvt = (GetCSEventsReportEvent)anEvent;
		
		if(csEventsReportEvt != null)
		{
			GetCalendarEventsReportEvent queryEvent = CSEventsReportPDHelper.getCalendarEventsReportQueryEvent(csEventsReportEvt);
			
			Iterator iterator = CSEventsReport.findAll(queryEvent);
			List csEventsReportList = CollectionUtil.iteratorToList(iterator);
			
			List responseList = CSEventsReportPDHelper.getCSEventsReportResponseEvent(csEventsReportList);
			Iterator reponseListIter = responseList.iterator();
			while(reponseListIter.hasNext())
			{
				CSEventsReportReponseEvent responseEvent = (CSEventsReportReponseEvent)reponseListIter.next();
				MessageUtil.postReply(responseEvent);
			}
		}
	}//end of execute()
		

	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
}
