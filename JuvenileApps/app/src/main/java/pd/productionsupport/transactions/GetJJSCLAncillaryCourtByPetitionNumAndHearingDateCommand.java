package pd.productionsupport.transactions;


import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;

import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.juvenilecase.districtCourtHearings.JJSCLAncillary;
import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetJuvenileHearingTypesEvent;
import messaging.productionsupport.GetJJSCLAncillaryCourtByPetitionNumAndHearingDateEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;

public class GetJJSCLAncillaryCourtByPetitionNumAndHearingDateCommand implements ICommand
{ 
    @Override
    public void execute(IEvent event) throws Exception
    {
	GetJJSCLAncillaryCourtByPetitionNumAndHearingDateEvent getJJSCLAncillaryEvent = (GetJJSCLAncillaryCourtByPetitionNumAndHearingDateEvent)event;
	GetJuvenileHearingTypesEvent hearingTypeEvt = new GetJuvenileHearingTypesEvent();
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator<JJSCLAncillary> ancillaryItr = JJSCLAncillary.findAll(getJJSCLAncillaryEvent);
	Iterator<JuvenileHearingTypeCode> hearingTypeCodes = new Home().findAll(hearingTypeEvt, JuvenileHearingTypeCode.class);
	List<JuvenileHearingTypeCode> hearingTypeCodesList = IteratorUtils.toList(hearingTypeCodes); 
	while (ancillaryItr.hasNext())
	{
	    JJSCLAncillary ancillaryCrt = (JJSCLAncillary) ancillaryItr.next();
	    if (ancillaryCrt != null)
	    {
		DocketEventResponseEvent resp = ancillaryCrt.valueObject();
		System.out.println("Petition number: " + resp.getPetitionNumber());
		System.out.println("Respondent name: " + resp.getRespondantName());
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
