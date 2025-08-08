package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetJuvenileHearingTypesEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByPetitionNumEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.juvenilecase.JJSCourt;

/**
 * Locate all other calendar records (where the Hearing Result is populated) for
 * the juvenile with the same petition and referral number as the current
 * calendar record. Sort the records in by ascending ChainNumber. Within
 * ChainNumber groupings, sort by descending ChainSequenceNumber. The setting
 * that will be used to update the referral per the ranking logic below is the
 * one that is the highest chain number and the setting with highest
 * SequenceNumber.
 */
public class GetJJSCLCourtByPetitionNumCommand implements ICommand
{
    @Override
    public void execute(IEvent event) throws Exception
    {
	GetJJSCLCourtByPetitionNumEvent evt = (GetJJSCLCourtByPetitionNumEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	GetJuvenileHearingTypesEvent hearingTypeEvt = new GetJuvenileHearingTypesEvent();
	//83426
	Iterator<JuvenileHearingTypeCode> hearingTypeCodes = new Home().findAll(hearingTypeEvt, JuvenileHearingTypeCode.class);
	List<JuvenileHearingTypeCode> hearingTypeCodesList = IteratorUtils.toList(hearingTypeCodes); 

	Iterator<JJSCourt> courtItr = JJSCourt.findAll(evt);
	while (courtItr.hasNext())
	{
	    JJSCourt court = courtItr.next();
	    if (court.getHearingResult() != null && !court.getHearingResult().isEmpty())
	    {
		DocketEventResponseEvent resp = court.valueObject();
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
				if (resp.getHearingType() != null && hearingTypeCode.getCode()!=null && hearingTypeCode.getCode().equalsIgnoreCase(resp.getHearingType()))
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
