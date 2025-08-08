package pd.supervision.calendar.transactions;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang.StringUtils;

import messaging.calendar.GetDocketEventsEvent;
import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetJuvenileHearingTypesEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByDateRangeEvent;
import messaging.districtCourtHearings.GetJJSCourtByDateRangeEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.utilities.DateUtil;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;
import pd.supervision.Court;

public class GetDocketEventsCommand implements ICommand
{

	/**
	 * @roseuid 45F1B0E400AA
	 */
	public GetDocketEventsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 45F080F00340
	 */
    public void execute(IEvent event)
    {
	GetDocketEventsEvent utEvent = (GetDocketEventsEvent) event;

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	String juvenileId = utEvent.getJuvenileId();
	//changes for #81390
	/*if (utEvent.getJuvenileId() != null && !"".equals(utEvent.getJuvenileId()))
	{
		String pattern = "00000000";
		StringBuffer sb = new StringBuffer();
		sb.append(pattern.substring(0, 8 - utEvent.getJuvenileId().length()));
		sb.append(utEvent.getJuvenileId());
		juvenileId = sb.toString();
	}*/

	String startDate = null;
	if (utEvent.getStartDate() != null)
	{
	    startDate = DateUtil.dateToString(utEvent.getStartDate(), "yyyy-MM-dd");
	}

	String endDate = null;
	if (utEvent.getEndDate() == null)
	{
	    endDate = startDate;
	}
	else
	{
	    endDate = DateUtil.dateToString(utEvent.getEndDate(), "yyyy-MM-dd");
	}

	if (juvenileId != null || startDate != null)
	{
	    //81390 changes
	    GetJJSCourtByDateRangeEvent jjs = new GetJJSCourtByDateRangeEvent();
	    jjs.setJuvenileNumber(juvenileId);
	    jjs.setStartDate(startDate);
	    jjs.setEndDate(endDate);
	    Iterator<JJSCourt> iter = JJSCourt.findAll(jjs);

	    while (iter.hasNext())
	    {
		JJSCourt court = iter.next();
		DocketEventResponseEvent resp = court.valueObject();
		resp.setJuvenileNumber(court.getJuvenileNumber());

		Court courtObj = Court.find(court.getCourtId());
		resp.setLocation(courtObj.getAddress());
		GetJuvenileHearingTypesEvent hearingTypeEvt = new GetJuvenileHearingTypesEvent();
		Iterator<JuvenileHearingTypeCode> hearingTypeCodes = new Home().findAll(hearingTypeEvt, JuvenileHearingTypeCode.class);
		List<JuvenileHearingTypeCode> hearingTypeCodesList = IteratorUtils.toList(hearingTypeCodes);
		if (resp != null)
		{
		    if (resp.getCourtResult() != null && !resp.getCourtResult().isEmpty())
		    {
			JuvenileDispositionCode juvenileDispositionCode = JuvenileDispositionCode.find("codeAlpha", resp.getCourtResult());
			if (juvenileDispositionCode != null)
			{
			    resp.setCourtResultDesc(juvenileDispositionCode.getShortDesc());
			}
		    }
		    if (resp.getDisposition() != null && !resp.getDisposition().isEmpty())
		    {
			JuvenileDispositionCode juvenileDispositionCode = JuvenileDispositionCode.find("codeAlpha", resp.getDisposition());
			if (juvenileDispositionCode != null)
			{
			    resp.setDispositionDesc(juvenileDispositionCode.getShortDesc());
			}
		    }
		    if (hearingTypeCodes != null)
		    {
			hearingTypeCodes = hearingTypeCodesList.iterator();//83426
			while (hearingTypeCodes.hasNext())
			{
			    JuvenileHearingTypeCode hearingTypeCode = hearingTypeCodes.next();
			    if (hearingTypeCode != null && hearingTypeCode.getInactiveInd() != null && !hearingTypeCode.getInactiveInd().equalsIgnoreCase("Y"))
			    {
				if (resp.getHearingType() != null && hearingTypeCode.getCode() != null && hearingTypeCode.getCode().equalsIgnoreCase(resp.getHearingType()))
				{
				    resp.setHearingTypeDesc(hearingTypeCode.getDescription());
				}
			    }
			}
		    }
		    if (resp.getAllegation() != null && StringUtils.isNotEmpty(resp.getAllegation()))
		    {

			    JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode",resp.getAllegation());
			    if (offCode != null)
			    {
				// Default to these else show transferred
				resp.setPetitionAllegationDesc(offCode.getShortDescription());
			    }
		    }
		    dispatch.postEvent(resp);
		}
	    }

	    //81390 changes
	    GetJJSCLDetentionByDateRangeEvent det = new GetJJSCLDetentionByDateRangeEvent();
	    det.setJuvenileNumber(juvenileId);
	    det.setStartDate(startDate);
	    det.setEndDate(endDate);

	    Iterator<JJSCLDetention> detentionItr = JJSCLDetention.findAll(det);

	    while (detentionItr.hasNext())
	    {
		JJSCLDetention detention = detentionItr.next();
		DocketEventResponseEvent resp = detention.valueObject();
		resp.setJuvenileNumber(detention.getJuvenileNumber());

		Court courtObj = Court.find(detention.getCourtId());
		resp.setLocation(courtObj.getAddress());
		GetJuvenileHearingTypesEvent hearingTypeEvt = new GetJuvenileHearingTypesEvent();
		Iterator<JuvenileHearingTypeCode> hearingTypeCodes = new Home().findAll(hearingTypeEvt, JuvenileHearingTypeCode.class);
		List<JuvenileHearingTypeCode> hearingTypeCodesList = IteratorUtils.toList(hearingTypeCodes);
		if (resp != null)
		{
		    if (resp.getCourtResult() != null && !resp.getCourtResult().isEmpty())
		    {
			JuvenileDispositionCode juvenileDispositionCode = JuvenileDispositionCode.find("codeAlpha", resp.getCourtResult());
			if (juvenileDispositionCode != null)
			{
			    resp.setCourtResultDesc(juvenileDispositionCode.getShortDesc());
			}
		    }
		    if (resp.getDisposition() != null && !resp.getDisposition().isEmpty())
		    {
			JuvenileDispositionCode juvenileDispositionCode = JuvenileDispositionCode.find("codeAlpha", resp.getDisposition());
			if (juvenileDispositionCode != null)
			{
			    resp.setDispositionDesc(juvenileDispositionCode.getShortDesc());
			}
		    }
		    if (hearingTypeCodes != null)
		    {
			hearingTypeCodes = hearingTypeCodesList.iterator();//83426
			while (hearingTypeCodes.hasNext())
			{
			    JuvenileHearingTypeCode hearingTypeCode = hearingTypeCodes.next();
			    if (hearingTypeCode != null && hearingTypeCode.getInactiveInd() != null && !hearingTypeCode.getInactiveInd().equalsIgnoreCase("Y"))
			    {
				if (resp.getHearingType() != null && hearingTypeCode.getCode() != null && hearingTypeCode.getCode().equalsIgnoreCase(resp.getHearingType()))
				{
				    resp.setHearingTypeDesc(hearingTypeCode.getDescription());
				}
			    }
			}
		    }
		    if (resp.getAllegation() != null && StringUtils.isNotEmpty(resp.getAllegation()))
		    {

			    JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode",resp.getAllegation());
			    if (offCode != null)
			    {
				// Default to these else show transferred
				resp.setPetitionAllegationDesc(offCode.getShortDescription());
			    }
		    }
		    dispatch.postEvent(resp);
		}
	    }
	}

    }

	/**
	 * @param event
	 * @roseuid 45F080F0034E
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 45F080F00350
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 45F080F0035D
	 */
	public void update(Object anObject)
	{

	}

}
