package pd.supervision.calendar.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.calendar.GetViewCalendarDocketEventsByJuvenilesEvent;
import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByDateRangeEvent;
import messaging.districtCourtHearings.GetJJSCourtByDateRangeEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import pd.codetable.criminal.JuvenileEventTypeCode;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;

public class GetViewCalendarDocketEventsByJuvenilesCommand implements ICommand
{

    private static final int MAX_COUNT = 2000;

    /**
     * @roseuid 45F1B0D902AD
     */
    public GetViewCalendarDocketEventsByJuvenilesCommand()
    {

    }

    /**
     * @param event
     * @roseuid 45F080FA035F
     */
    public void execute(IEvent event)
    {
	GetViewCalendarDocketEventsByJuvenilesEvent searchDocketEvent = (GetViewCalendarDocketEventsByJuvenilesEvent) event;

	ErrorResponseEvent errorResp = new ErrorResponseEvent();
	//83275
	//String juvenileId = buildJuvenileIdByCasefiles(searchDocketEvent.getJuvCasefileResponses());

	String startDate = null;
	if (searchDocketEvent.getStartDate() != null)
	{
	    startDate = DateUtil.dateToString(searchDocketEvent.getStartDate(), "yyyy-MM-dd"); //81390 changes
	}

	String endDate = null;
	if (searchDocketEvent.getEndDate() == null)
	{
	    endDate = startDate;
	}
	else
	{
	    endDate = DateUtil.dateToString(searchDocketEvent.getEndDate(), "yyyy-MM-dd"); //81390
	}

	String eventTypeId = searchDocketEvent.getEventTypeId();
	String eventTypeCd = null;
	if (eventTypeId != null && !"".equals(eventTypeId))
	{
	    JuvenileEventTypeCode eventType = JuvenileEventTypeCode.find(eventTypeId);
	    if (eventType != null)
	    {
		eventTypeCd = eventType.getCode();
	    }
	}
	//83275
	if (searchDocketEvent.getJuvCasefileResponses() != null)
	{
	    Iterator<JuvenileCasefileResponseEvent> juvenileCasefileRespItr = searchDocketEvent.getJuvCasefileResponses().iterator();
	    while (juvenileCasefileRespItr.hasNext())
	    {
		JuvenileCasefileResponseEvent casefile = juvenileCasefileRespItr.next();

		if (casefile != null && casefile.getJuvenileNum() != null && startDate != null)
		{
		    int result = 0;
		    if (eventTypeCd == null || "".equals(eventTypeCd) || eventTypeCd.equals(UIConstants.COURT_DATE))
		    {

			GetJJSCourtByDateRangeEvent jjs = new GetJJSCourtByDateRangeEvent();
			jjs.setJuvenileNumber(casefile.getJuvenileNum());
			jjs.setStartDate(startDate);
			jjs.setEndDate(endDate);

			result += this.getJJSCourts(jjs);

		    }

		    if (result < MAX_COUNT)
		    {
			if (eventTypeCd == null || "".equals(eventTypeCd) || eventTypeCd.equals(UIConstants.DETENTION_HEARING))
			{
			    GetJJSCLDetentionByDateRangeEvent det = new GetJJSCLDetentionByDateRangeEvent();
			    det.setJuvenileNumber(casefile.getJuvenileNum());
			    det.setStartDate(startDate);
			    det.setEndDate(endDate);

			    result += this.getJJSCLDetentions(det);
			}
		    }

		    if (result >= MAX_COUNT)
		    {
			errorResp.setMessage("Number of Docket Events matching this criteria is greater than 2000");
			MessageUtil.postReply(errorResp);
		    }
		}
	    }
	}

    }

    /**
     * Get JJSCourtEvents & post them to dispatch
     * 
     * @param jjs
     *            GetJJSCourtEventByDateEvent
     * @return JJSCourtEvent count 81390 changes
     */
    private int getJJSCourts(GetJJSCourtByDateRangeEvent jjs)
    {
	IHome home = new Home();
	MetaDataResponseEvent resp = home.findMeta(jjs, JJSCourt.class);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	if (resp.getCount() < MAX_COUNT)
	{
	    Iterator i = home.findAll(jjs, JJSCourt.class);
	    while (i.hasNext())
	    {
		JJSCourt court = (JJSCourt) i.next();
		DocketEventResponseEvent docketResp = court.valueObject();
		docketResp.setJuvenileNumber(court.getJuvenileNumber());
		dispatch.postEvent(docketResp);
	    }
	}

	return resp.getCount();
    }

    /**
     * Get JJSDetentionEvents & post them to dispatch
     * 
     * @param jjs
     *            GetJJSDetentionEventByDateEvent
     * @return JJSDetentionEvent count3 //83910 changes
     */
    private int getJJSCLDetentions(GetJJSCLDetentionByDateRangeEvent det)
    {
	IHome home = new Home();
	MetaDataResponseEvent resp = home.findMeta(det, JJSCLDetention.class);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	if (resp.getCount() < MAX_COUNT)
	{
	    Iterator i = home.findAll(det, JJSCLDetention.class);
	    while (i.hasNext())
	    {
		JJSCLDetention detention = (JJSCLDetention) i.next();
		DocketEventResponseEvent docketResp = detention.valueObject();
		docketResp.setJuvenileNumber(detention.getJuvenileNumber());
		dispatch.postEvent(docketResp);
	    }
	}
	return resp.getCount();
    }

    /**
     * Builds a single line string containing all juveniles ids (exp.
     * 3109003109001 etc.)
     * 
     * @param results
     *            JuvenileCasefileResponseEvent List
     * @return
     */
   /* private String buildJuvenileIdByCasefiles(List results)
    {
	StringBuilder sb = new StringBuilder();
	String pattern = "00000000"; //81390 changes
	int len = results.size();
	for (int i = 0; i < len; i++)
	{
	    JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent) results.get(i);
	    //sb.append(pattern.substring(0, 8 - casefile.getJuvenileNum().length()));
	    sb.append(casefile.getJuvenileNum().length());
	}

	return sb.toString();
    }*/

    /**
     * @param event
     * @roseuid 45F080FA036D
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 45F080FA036F
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 45F080FA037C
     */
    public void update(Object anObject)
    {

    }
}
