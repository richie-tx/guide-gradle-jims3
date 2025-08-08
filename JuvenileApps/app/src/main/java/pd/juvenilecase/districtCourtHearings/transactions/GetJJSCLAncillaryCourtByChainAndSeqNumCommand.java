package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetJuvenileHearingTypesEvent;
import messaging.districtCourtHearings.GetJJSCLAncillaryCourtByChainAndSeqNumEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;

import org.apache.commons.collections.IteratorUtils;

import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.juvenilecase.districtCourtHearings.JJSCLAncillary;

public class GetJJSCLAncillaryCourtByChainAndSeqNumCommand implements ICommand
{
    @Override
    public void execute(IEvent event) throws Exception
    {
	GetJJSCLAncillaryCourtByChainAndSeqNumEvent evt = (GetJJSCLAncillaryCourtByChainAndSeqNumEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	GetJuvenileHearingTypesEvent hearingTypeEvt = new GetJuvenileHearingTypesEvent();
	//83426
	Iterator<JuvenileHearingTypeCode> hearingTypeCodes = new Home().findAll(hearingTypeEvt, JuvenileHearingTypeCode.class);
	List<JuvenileHearingTypeCode> hearingTypeCodesList = IteratorUtils.toList(hearingTypeCodes); 

	if (evt.getChainNumber() != null && evt.getSeqNumber() != null)
	{
	    Iterator<JJSCLAncillary> ancillaryItr = JJSCLAncillary.findAll(evt);
	    while (ancillaryItr.hasNext())
	    {
		JJSCLAncillary ancillaryCrt = (JJSCLAncillary) ancillaryItr.next();
		if (ancillaryCrt != null)
		{
		    DocketEventResponseEvent resp = ancillaryCrt.valueObject();
		    if (resp != null)
		    {
			if (hearingTypeCodes != null)
			{
			    hearingTypeCodes = hearingTypeCodesList.iterator();//83426
			    while (hearingTypeCodes.hasNext())
			    {
				JuvenileHearingTypeCode hearingTypeCode = hearingTypeCodes.next();
				if (hearingTypeCode != null && hearingTypeCode.getInactiveInd() != null && !hearingTypeCode.getInactiveInd().equalsIgnoreCase("Y"))
				{
				    if (resp.getSettingReason() != null && hearingTypeCode.getCode()!=null && hearingTypeCode.getCode().equalsIgnoreCase(resp.getSettingReason()))
				    {
					resp.setHearingTypeDesc(hearingTypeCode.getDescription());
				    }
				    if (resp.getHearingTypeDesc() != null && !resp.getHearingTypeDesc().isEmpty() && resp.getHearingType() != null && hearingTypeCode.getCode()!=null && hearingTypeCode.getCode().equalsIgnoreCase(resp.getHearingType()))
				    {
					resp.setHearingTypeDesc(hearingTypeCode.getDescription());
				    }

				    if (resp.getResetHearingType() != null && hearingTypeCode.getCode()!=null && hearingTypeCode.getCode().equalsIgnoreCase(resp.getResetHearingType()))
				    {
					resp.setResetHearingTypeDesc(hearingTypeCode.getDescription());
				    }
				}
			    }
			}
			dispatch.postEvent(resp);
		    }
		}
	    }
	}

	//chain number
	if (evt.getChainNumber() != null && (evt.getSeqNumber() == null || (evt.getSeqNumber() != null && evt.getSeqNumber().isEmpty())))
	{
	    Iterator<JJSCLAncillary> ancillaryItr = JJSCLAncillary.findAll("chainNumber", evt.getChainNumber());
	    while (ancillaryItr.hasNext())
	    {
		JJSCLAncillary ancillaryCrt = (JJSCLAncillary) ancillaryItr.next();
		if (ancillaryCrt != null)
		{
		    DocketEventResponseEvent resp = ancillaryCrt.valueObject();
		    if (resp != null)
		    {
			if (hearingTypeCodes != null)
			{
			    hearingTypeCodes = hearingTypeCodesList.iterator();//83426
			    while (hearingTypeCodes.hasNext())
			    {
				JuvenileHearingTypeCode hearingTypeCode = hearingTypeCodes.next();
				if (hearingTypeCode != null && hearingTypeCode.getInactiveInd() != null && !hearingTypeCode.getInactiveInd().equalsIgnoreCase("Y"))
				{
				    if (resp.getSettingReason() != null && hearingTypeCode.getCode()!=null && hearingTypeCode.getCode().equalsIgnoreCase(resp.getSettingReason()))
				    {
					resp.setHearingTypeDesc(hearingTypeCode.getDescription());
				    }
				    if (resp.getHearingTypeDesc() != null && !resp.getHearingTypeDesc().isEmpty() && resp.getHearingType() != null && hearingTypeCode.getCode()!=null && hearingTypeCode.getCode().equalsIgnoreCase(resp.getHearingType()))
				    {
					resp.setHearingTypeDesc(hearingTypeCode.getDescription());
				    }

				    if (resp.getResetHearingType() != null && hearingTypeCode.getCode()!=null && hearingTypeCode.getCode().equalsIgnoreCase(resp.getResetHearingType()))
				    {
					resp.setResetHearingTypeDesc(hearingTypeCode.getDescription());
				    }
				}
			    }
			}
			 dispatch.postEvent(resp);
		    }
		}
	    }
	}
    }
}
