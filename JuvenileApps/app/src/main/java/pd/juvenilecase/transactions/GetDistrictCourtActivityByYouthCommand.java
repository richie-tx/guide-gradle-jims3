package pd.juvenilecase.transactions;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.juvenilecase.GetCourtActivityByYouthEvent;
import messaging.juvenilecase.GetDistrictCourtActivityByYouthEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.DateUtil;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;

/**
 * GETS FROM THE JIMS2.JJSCLCOURT TABLE. MIGRATED FROM M204.
 */
public class GetDistrictCourtActivityByYouthCommand implements ICommand
{

    @SuppressWarnings("unchecked")
    @Override
    public void execute(IEvent event) throws Exception
    {
	GetDistrictCourtActivityByYouthEvent evt = (GetDistrictCourtActivityByYouthEvent) event;
	DocketEventResponseEvent resp = null;

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator<JJSCourt> courtItr = JJSCourt.findAll("juvenileNumber", evt.getJuvenileNum());
	while (courtItr.hasNext())
	{
	    JJSCourt court = courtItr.next();
	    resp = new DocketEventResponseEvent();
	    resp = setCourtInfo(court);
	    dispatch.postEvent(resp);
	}	
    }

    private String padCourt(String courtId)
    {
	String court = "";
	if (courtId != null)
	{

	    court = courtId.trim();
	    if (court.length() < 3)
	    {
		StringBuffer sb = new StringBuffer(court);
		for (int i = 0; i < 2; i++)
		{
		    sb.insert(0, "0");
		}
		court = sb.toString();
	    }
	}
	return court;
    }

    /**
     * @param court
     * @return
     */
    private DocketEventResponseEvent setCourtInfo(JJSCourt court)
    {

	DocketEventResponseEvent resp = new DocketEventResponseEvent();
	resp.setCourt(court.getCourtId());
	resp.setCourtDate(DateUtil.dateToString(court.getCourtDate(), DateUtil.DATE_FMT_1));

	String hearingDisp = court.getHearingDisposition();
	String hearingResult = court.getHearingResult();

	if (hearingDisp != null && !hearingDisp.isEmpty())
	{
	    JuvenileDispositionCode code = JuvenileDispositionCode.find("codeAlpha",hearingDisp);
	    resp.setDispositionDesc(code.getShortDesc());

	}
	else
	{
	    if (StringUtils.isNotEmpty(hearingResult) && hearingResult != null)
	    {
		JuvenileDispositionCode code = JuvenileDispositionCode.find("codeAlpha",hearingResult);
		if (code != null)
		{
		    resp.setDispositionDesc(code.getShortDesc());
		}
	    }
	}
	resp.setJuvenileNumber(court.getJuvenileNumber());
	resp.setReferralNum(court.getReferralNumber());
	resp.setPetitionNumber(court.getPetitionNumber());

	String petitionCode = court.getPetitionAllegation();
	if (petitionCode != null && StringUtils.isNotEmpty(petitionCode))
	{

	    JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode",petitionCode);
	    if (offCode != null)
	    {
		resp.setPetitionAllegationDesc(offCode.getShortDescription());
	    }
	    resp.setPetitionAllegation(petitionCode);
	}

	String petStatusCode = court.getPetitionStatus();
	if (petStatusCode != null)
	{

	    if ("F".equalsIgnoreCase(petStatusCode))
	    {
		resp.setPetitionStatus("FILED");
	    }
	    else
		if ("R".equalsIgnoreCase(petStatusCode))
		{
		    resp.setPetitionStatus("REOPENED");
		}
	    resp.setPetitionStatusCd(petStatusCode);
	}

	resp.setPetitionAmendment(court.getPetitionAmendment());
	String hearingType = court.getHearingType();
	if (hearingType != null)
	{

	    resp.setHearingType(hearingType);
	    resp.setHearingTypeDesc(JuvenileHearingTypeCode.find(hearingType).getDescription());

	}

	resp.setSeqNum( court.getSeqNumber() );
	resp.setChainNumber(court.getChainNumber());
	resp.setFilingDate(DateUtil.dateToString(court.getFilingDate(), DateUtil.DATE_FMT_1));

	return resp;
    }

    /**
     * @param detRec
     * @return
     */
    private DocketEventResponseEvent setDetentionInfo(JJSCLDetention detRec)
    {

	DocketEventResponseEvent resp = new DocketEventResponseEvent();
	resp.setCourt(padCourt(detRec.getCourtId()));
	resp.setCourtDate(DateUtil.dateToString(detRec.getCourtDate(), DateUtil.DATE_FMT_1));

	String hearingDisp = detRec.getHearingDisposition();
	String hearingResult = detRec.getHearingResult();

	if (hearingDisp != null && !hearingDisp.isEmpty())
	{
	    JuvenileDispositionCode code = JuvenileDispositionCode.find("codeAlpha",hearingDisp);
	    resp.setDispositionDesc(code.getShortDesc());

	}
	else
	{
	    if (StringUtils.isNotEmpty(hearingResult) && hearingResult != null)
	    {
		JuvenileDispositionCode code = JuvenileDispositionCode.find("codeAlpha",hearingResult);
		if (code != null)
		{
		    resp.setDispositionDesc(code.getShortDesc());
		}
	    }
	}
	
	String hearingType = detRec.getHearingType();
	if (hearingType != null)
	{

	    resp.setHearingType(hearingType);
	    resp.setHearingTypeDesc(JuvenileHearingTypeCode.find(hearingType).getDescription());

	}
	resp.setJuvenileNumber(detRec.getJuvenileNumber());
	resp.setReferralNum(detRec.getReferralNumber());
	resp.setPetitionNumber(detRec.getPetitionNumber());
	resp.setSeqNum( detRec.getSeqNumber());
	resp.setChainNumber(detRec.getChainNumber());

	return resp;
    }
}
