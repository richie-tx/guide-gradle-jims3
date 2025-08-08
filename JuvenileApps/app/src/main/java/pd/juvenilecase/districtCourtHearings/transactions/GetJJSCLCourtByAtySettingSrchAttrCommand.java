package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetJuvenileHearingTypesEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByAtySettingSrchAttrEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.juvenilecase.JJSCourt;

/**
 * @author sthyagarajan
 */
public class GetJJSCLCourtByAtySettingSrchAttrCommand implements ICommand
{
    @Override
    public void execute(IEvent event) throws Exception
    {
	GetJJSCLCourtByAtySettingSrchAttrEvent evt = (GetJJSCLCourtByAtySettingSrchAttrEvent) event;
	//perfomance pre-load hearingtypes
	GetJuvenileHearingTypesEvent hearingTypeEvt = new GetJuvenileHearingTypesEvent();
	//83426
	Iterator<JuvenileHearingTypeCode> hearingTypeCodes = new Home().findAll(hearingTypeEvt, JuvenileHearingTypeCode.class);
	List<JuvenileHearingTypeCode> hearingTypeCodesList = IteratorUtils.toList(hearingTypeCodes); 

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	if ((evt.getBarNumber() != null || !evt.getBarNumber().isEmpty()) || evt.getCourtDate() != null || !evt.getCourtDate().isEmpty() || evt.getJuvenileNumber() != null || !evt.getJuvenileNumber().isEmpty())
	{
	    Iterator<JJSCourt> courtItr = JJSCourt.findAll(evt);
	    while (courtItr.hasNext())
	    {
		JJSCourt court = (JJSCourt) courtItr.next();
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
		    //add the new coloumn result 1st or second depending on the radio selected.
		    /*if(evt.getBarNumber()!=null)
		    {
			if(resp.getBarNum()!=null)
			{
        		    if(resp.getBarNum().equalsIgnoreCase(evt.getBarNumber()))
        			resp.setAssignedAtty("1st");
			}
			if(resp.getAttorney2BarNum()!=null)
			{
        		    if(resp.getAttorney2BarNum().equalsIgnoreCase(evt.getBarNumber()))
        			resp.setAssignedAtty("2nd");
			}
		    }*/
		    
		    dispatch.postEvent(resp);
		}
	    }
	}
    }
}
