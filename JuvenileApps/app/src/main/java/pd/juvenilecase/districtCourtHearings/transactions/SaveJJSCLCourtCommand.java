package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.Iterator;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.districtCourtHearings.SaveJJSCLCourtEvent;
import messaging.juvenile.reply.JuvenileNumControlResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.juvenilecase.JJSChainNumControl;
import pd.juvenilecase.JJSCourt;

public class SaveJJSCLCourtCommand implements ICommand{

    @Override
    public void execute(IEvent event) throws Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	SaveJJSCLCourtEvent courtEvent = (SaveJJSCLCourtEvent)event;
	JJSCourt court = new JJSCourt();
	IHome home= new Home();
	DocketEventResponseEvent replyEvent = new DocketEventResponseEvent();
	
	court.setAmendmentDate(courtEvent.getAmendmentDate());
	court.setAttorneyConnection(courtEvent.getAttorneyConnection());
	
	if (courtEvent.getAttorneyName() != null)
	{
	    if (courtEvent.getAttorneyName().isEmpty())
	    {
		court.setAttorneyName(null);
	    }
	    else
	    {
		court.setAttorneyName(courtEvent.getAttorneyName());
	    }
	}
	if (courtEvent.getBarNumber() != null)
	{
	    if (courtEvent.getBarNumber().isEmpty())
	    {
		court.setBarNumber(null);
	    }
	    else
	    {
		court.setBarNumber(courtEvent.getBarNumber());
	    }
	}
	JJSChainNumControl chainNumControl = null;
	Iterator<JJSChainNumControl> chainNumIter = JJSChainNumControl.findAll();
	if (chainNumIter.hasNext())
	{
	    chainNumControl = (JJSChainNumControl) chainNumIter.next();
	    String nextChainNum = chainNumControl.getNextChainNum();
	    if (nextChainNum != null && !nextChainNum.equals(" "))
	    {
		int num = Integer.parseInt(nextChainNum);
		court.setChainNumber("" + (num + 1));
	    }
	}
	court.setComments(courtEvent.getComments());
	court.setCourtDate(courtEvent.getCourtDate());
	court.setCourtId(courtEvent.getCourtId());
	if (courtEvent.getCourtTime() != null)
	{
	    if (courtEvent.getCourtTime().isEmpty())
	    {
		court.setCourtTime(null);
	    }
	    else
	    {
		court.setCourtTime(courtEvent.getCourtTime());
	    }
	}
	
	court.setFilingDate(courtEvent.getFilingDate());
	court.setHearingCategory(courtEvent.getHearingCategory());
	court.setHearingDisposition(courtEvent.getHearingDisposition());
	court.setHearingResult(courtEvent.getHearingResult());
	court.setHearingType(courtEvent.getHearingType());
	///92458
	JuvenileHearingTypeCode hearingType = null;
	hearingType = JuvenileHearingTypeCode.find(courtEvent.getHearingType());
	court.setOptionFlag(hearingType.getOptionInd());
	///92458
	//court.setOptionFlag(courtEvent.getOptionFlag());
	court.setIssueFlag(courtEvent.getIssueFlag());
	court.setPetitionAllegation(courtEvent.getPetitionAllegation());
	court.setPetitionAmendment(courtEvent.getPetitionAmendment());
	court.setPetitionNumber(courtEvent.getPetitionNumber());
	court.setPetitionStatus(courtEvent.getPetitionStatus());
	court.setPrevNotes(courtEvent.getPrevNotes());
	court.setRectype("COURT");
	court.setReferralNumber(courtEvent.getReferralNumber());
	court.setResetHearingType(courtEvent.getResetHearingType());
	court.setSeqNumber(courtEvent.getSeqNumber());
	court.setTjpcSeqNumber(courtEvent.getTjpcSeqNumber());
	court.setUpdateFlag(courtEvent.getUpdateFlag());
	court.setJuvenileNumber(courtEvent.getJuvenileNumber());
	court.setLcDate(courtEvent.getLcDate());
	court.setLcTime(courtEvent.getLcTime());
	court.setLcUser(courtEvent.getLcUser());
	home.bind(court);
	replyEvent.setDocketEventId(String.valueOf(court.getOID()));
	dispatch.postEvent(replyEvent);
    }
}
