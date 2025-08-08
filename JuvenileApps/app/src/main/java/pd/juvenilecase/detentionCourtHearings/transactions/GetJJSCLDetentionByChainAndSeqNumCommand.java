package pd.juvenilecase.detentionCourtHearings.transactions;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetJuvenileHearingTypesEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByChainAndSeqNumEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;

public class GetJJSCLDetentionByChainAndSeqNumCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	GetJJSCLDetentionByChainAndSeqNumEvent evt = (GetJJSCLDetentionByChainAndSeqNumEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	GetJuvenileHearingTypesEvent hearingTypeEvt = new GetJuvenileHearingTypesEvent();
	//83426
	Iterator<JuvenileHearingTypeCode> hearingTypeCodes = new Home().findAll(hearingTypeEvt, JuvenileHearingTypeCode.class);
	List<JuvenileHearingTypeCode> hearingTypeCodesList = IteratorUtils.toList(hearingTypeCodes); 

	if (evt.getChainNumber() != null && evt.getSeqNumber() != null)
	{
	    Iterator<JJSCLDetention> detentionItr = JJSCLDetention.findAll(evt);
	    while (detentionItr.hasNext())
	    {
		JJSCLDetention detentionCrt = (JJSCLDetention) detentionItr.next();
		if (detentionCrt != null)
		{
		    DocketEventResponseEvent resp = detentionCrt.valueObject();
		    if (resp != null)
		    {
			if (hearingTypeCodes != null)
			{
			    hearingTypeCodes = hearingTypeCodesList.iterator(); //83426
			    while (hearingTypeCodes.hasNext())
			    {
				JuvenileHearingTypeCode hearingTypeCode = hearingTypeCodes.next();
				if (hearingTypeCode != null && hearingTypeCode.getInactiveInd() != null && !hearingTypeCode.getInactiveInd().equalsIgnoreCase("Y"))
				{
				    if (resp.getHearingType() != null && hearingTypeCode.getCode()!=null &&  hearingTypeCode.getCode().equalsIgnoreCase(resp.getHearingType()))
				    {
					resp.setHearingTypeDesc(hearingTypeCode.getDescription());
					break;
				    }
				}
			    }
			}
			dispatch.postEvent(resp);
		    } //resp
		}
	    }
	}

	//chain number
	if (evt.getChainNumber() != null && (evt.getSeqNumber() == null || (evt.getSeqNumber() != null && evt.getSeqNumber().isEmpty())))
	{
	    Iterator<JJSCLDetention> detentionItr = JJSCLDetention.findAll("chainNumber", evt.getChainNumber());
	    while (detentionItr.hasNext())
	    {
		JJSCLDetention detentionCrt = (JJSCLDetention) detentionItr.next();
		if (detentionCrt != null)
		{
		    DocketEventResponseEvent resp = detentionCrt.valueObject();
		    if (resp != null)
		    {
			if (hearingTypeCodes != null)
			{
			    hearingTypeCodes = hearingTypeCodesList.iterator(); //83426
			    while (hearingTypeCodes.hasNext())
			    {
				JuvenileHearingTypeCode hearingTypeCode = hearingTypeCodes.next();
				if (hearingTypeCode != null && hearingTypeCode.getInactiveInd() != null && !hearingTypeCode.getInactiveInd().equalsIgnoreCase("Y"))
				{
				    if (resp.getHearingType() != null && hearingTypeCode.getCode()!=null && hearingTypeCode.getCode().equalsIgnoreCase(resp.getHearingType()))
				    {
					resp.setHearingTypeDesc(hearingTypeCode.getDescription());
					break;
				    }
				}
			    }
			}
			dispatch.postEvent(resp);
		    }//resp
		}
	    }
	}
    }
}
