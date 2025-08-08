package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.juvenilecase.GetCourtActivityByYouthEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.DateUtil;

import org.apache.commons.lang.StringUtils;

import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;
import pd.juvenilecase.referral.JJSTransferredOffenseReferral;

/**
 * GETS FROM THE JIMS2.JJSCLCOURT TABLE. MIGRATED FROM M204.
 */
public class GetCourtActivityByYouthCommand implements ICommand
{

    @SuppressWarnings("unchecked")
    @Override
    public void execute(IEvent event) throws Exception
    {
	GetCourtActivityByYouthEvent evt = (GetCourtActivityByYouthEvent) event;
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

	Iterator<JJSCLDetention> detentionItr = JJSCLDetention.findAll("juvenileNumber", evt.getJuvenileNum());
	while (detentionItr.hasNext())
	{
	    JJSCLDetention detDocket = detentionItr.next();
	    DocketEventResponseEvent detResp = new DocketEventResponseEvent();
	    detResp = setDetentionInfo(detDocket);
	    dispatch.postEvent(detResp);

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
	resp.setDocketEventId( court.getOID()  );
	if ( court.getCourtId() != null 
		&& court.getCourtId().length() > 0 ) {
	    resp.setCourt(court.getCourtId());
	}
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
		// Default to these else show transferred
		resp.setPetitionAllegationDesc(offCode.getShortDescription());
		resp.setPetitionAllegation(petitionCode);
		resp.setPetitionallegationCategory(offCode.getCategory());
		
		if ( "TRNDSP".equals(offCode.getOffenseCode()) 
		  || "TRNSIN".equals(offCode.getOffenseCode())
		  || "REGION".equals(offCode.getOffenseCode())
		  || "ISCOIN".equals(offCode.getOffenseCode())){
		    
		    // Find transferred offense code
		    Iterator transIter = JJSTransferredOffenseReferral.findAll("juvenileNumber", court.getJuvenileNumber());
		    while( transIter.hasNext()){
			
			JJSTransferredOffenseReferral transRef = (JJSTransferredOffenseReferral) transIter.next();
			if ( court.getReferralNumber().equals( transRef.getReferralNumber()) ) {
			    JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode",transRef.getOffenseCode());
			    resp.setPetitionAllegationDesc( offenseCode.getShortDescription() + "-" + offenseCode.getCategory() );
			       break;
			   }
		    }		    
		}		
	    }	    
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
	resp.setTransferTo(court.getTransferTo());

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
	//resp.setTransferTo(detRec.getTransferTo()); as transfer needs to show only in district

	return resp;
    }
}
