// Source file:
// C:\\views\\MJCW\\app\\src\\pd\\supervision\\calendar\\transactions\\SaveProgramReferralCommand.java

package pd.supervision.programreferral.transactions;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.programreferral.GetProgramReferralsByCasefileEvent;
import messaging.programreferral.SaveProgramReferralEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;
import naming.PDCalendarConstants;
import naming.ProgramReferralConstants;
import pd.supervision.administerserviceprovider.ProgramSourceFund;
import pd.supervision.administerserviceprovider.SearchServiceProvider;
import pd.supervision.calendar.ServiceEvent;
import pd.supervision.calendar.ServiceEventAttendance;
import pd.supervision.programreferral.JuvenileProgramReferral;
import pd.supervision.programreferral.JuvenileProgramReferralAssignmentHistory;
import pd.supervision.programreferral.JuvenileProgramReferralComment;

public class SaveProgramReferralCommand implements ICommand
{

    /**
     * @roseuid 463BA5250318
     */
    public SaveProgramReferralCommand()
    {

    }

    /**
     * @param event
     * @roseuid 463A3F520390
     */
    public void execute(IEvent event)
    {

	SaveProgramReferralEvent saveRefEvent = (SaveProgramReferralEvent) event;

	GetProgramReferralsByCasefileEvent gpre = new GetProgramReferralsByCasefileEvent();
	gpre.setCasefileId(saveRefEvent.getCasefileId());
	gpre.setAssignedHours(saveRefEvent.getAssignedHours());
	gpre.setBeginDate(saveRefEvent.getBeginDate());
	gpre.setCourtOrdered(saveRefEvent.isCourtOrdered());
	gpre.setProvProgramId(saveRefEvent.getProgramId());

	Iterator progRefIter = JuvenileProgramReferral.findAll(gpre);
	boolean programReferralAlreadyExists = false;
	boolean createProgramReferral = false;
	JuvenileProgramReferral programReferral = null;
	while (progRefIter.hasNext())
	{
	    programReferral = (JuvenileProgramReferral) progRefIter.next();
	    programReferralAlreadyExists = true;
	    createProgramReferral = false;
	}
	if (!programReferralAlreadyExists)
	{
	    if (saveRefEvent.getReferralId() != null)
	    {
		programReferral = JuvenileProgramReferral.find(saveRefEvent.getReferralId());
		createProgramReferral = false;
	    }
	    else
	    {
		programReferral = new JuvenileProgramReferral();
		createProgramReferral = true;
	    }
	    programReferral.setBeginDate(saveRefEvent.getBeginDate());
	    programReferral.setCourtOrdered(saveRefEvent.isCourtOrdered());
	    programReferral.setProgramOutcomeCd(saveRefEvent.getOutComeCd());
	    programReferral.setProgramOutcomeSubcategoryCd(saveRefEvent.getOutComeSubcategoryCd());
	    if (isInHouseServiceProvider(saveRefEvent.getProgramId()))
	    {
		programReferral.setReferralStatusCd(ProgramReferralConstants.ACCEPTED);
		programReferral.setReferralSubStatusCd(null);
	    }
	    else
	    {
		programReferral.setReferralStatusCd(saveRefEvent.getReferralStatusCd());
		programReferral.setReferralSubStatusCd(saveRefEvent.getReferralSubStatusCd());
	    }
	    programReferral.setAssignedHours(saveRefEvent.getAssignedHours());
	    programReferral.setLastActionDate(new Date());
	    if (saveRefEvent.getSentDate() != null)
	    {
		programReferral.setSentDate(saveRefEvent.getSentDate());
	    }
	    if (saveRefEvent.getAcknowledementDate() != null)
	    {
		programReferral.setAcknowledgementDate(saveRefEvent.getAcknowledementDate());
	    }
	    if (saveRefEvent.getEndDate() != null)
	    {
		programReferral.setEndDate(saveRefEvent.getEndDate());
	    }
	    if (saveRefEvent.getProgramId() != null)
	    {
		programReferral.setProvProgramId(saveRefEvent.getProgramId());
	    }
	    if (saveRefEvent.getCasefileId() != null)
	    {
		programReferral.setCasefileId(saveRefEvent.getCasefileId());
	    }
	    if (saveRefEvent.getControllingReferralNum() != null)
	    {
		programReferral.setControllingReferralNum(saveRefEvent.getControllingReferralNum());
	    }
	    //add code to save active fundsource and modify mapping to save too task 129620
	    Iterator programSourceFundIter = ProgramSourceFund.findAll("provProgramId", saveRefEvent.getProgramId());
	    while (programSourceFundIter.hasNext())
	    {
		ProgramSourceFund fund = (ProgramSourceFund) programSourceFundIter.next();
		if (fund.getFundStatus().equalsIgnoreCase("ACTIVE"))
		{
		    programReferral.setFundSource(fund.getProgramSourceFund());
		    break;
		}
	    }
	    IHome home = new Home();
	    home.bind(programReferral);

	    String programReferralId = programReferral.getOID() + "";

	    if (createProgramReferral)
	    {
		JuvenileProgramReferralAssignmentHistory prAssignmentHistory = new JuvenileProgramReferralAssignmentHistory();
		prAssignmentHistory.setCasefileId(saveRefEvent.getCasefileId());
		prAssignmentHistory.setProgramReferralId(programReferral.getOID());
		prAssignmentHistory.setProgramReferralAssignmentDate(saveRefEvent.getBeginDate());
	    }

	    // <KISHORE>JIMS200057235 : MJCW Sch Cal Even and View Cal - Attend
	    // Status is incorrect
	    if (saveRefEvent != null)
	    {
		if (saveRefEvent.getAttachedEvents() != null)
		{
		    Iterator events = saveRefEvent.getAttachedEvents().iterator();
		    while (events.hasNext())
		    {
			CalendarServiceEventResponseEvent sre = (CalendarServiceEventResponseEvent) events.next();
			ServiceEvent serv = new ServiceEvent();
			if (sre.getEventId() != null)
			    serv = (ServiceEvent) ServiceEvent.find(sre.getEventId());
			else
			    serv = (ServiceEvent) ServiceEvent.find(saveRefEvent.getEventId());
			Iterator iter = ServiceEventAttendance.findAll("serviceEventId", serv.getOID());
			while (iter.hasNext())
			{
			    ServiceEventAttendance att = (ServiceEventAttendance) iter.next();
			    // Update the unconfirmed events of the juvenile who
			    // requested the referral
			    if (ProgramReferralConstants.ACCEPTED.equalsIgnoreCase(programReferral.getReferralStatusCd()) && att.getJuvenileId().equalsIgnoreCase(programReferral.getJuvenileId()) && PDCalendarConstants.JUV_ATTEND_STATUS_UNCONFIRMED.equalsIgnoreCase(att.getAttendanceStatusCd()))
				att.setAttendanceStatusCd(PDCalendarConstants.JUV_ATTEND_STATUS_CONFIRMED);
			}
		    }
		}
	    }
	    if (saveRefEvent.getComments() != null)
	    {
		JuvenileProgramReferralComment referralComment = new JuvenileProgramReferralComment();
		referralComment.setProgramReferralId(programReferralId);
		referralComment.setCommentDate(new Date());
		referralComment.setCommentText(saveRefEvent.getComments());
		referralComment.setUserName(saveRefEvent.getCurrentUserName());
	    }
	}
	ProgramReferralResponseEvent resp = programReferral.getValueObject(true);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	dispatch.postEvent(resp);
    }

    private boolean isInHouseServiceProvider(String programId)
    {

	List<SearchServiceProvider> progList = CollectionUtil.iteratorToList(SearchServiceProvider.findAll("programId", programId));

	boolean isInHouse = false;

	if (progList.size() > 0)
	{
	    SearchServiceProvider ssp = progList.get(0);
	    if (ssp.getInHouse())
	    {
		isInHouse = true;
	    }
	}
	return isInHouse;
    }

    /**
     * @param event
     * @roseuid 463A3F52039E
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 463A3F5203A0
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 463A3F5203A2
     */
    public void update(Object anObject)
    {

    }

}
