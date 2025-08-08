package pd.juvenilecase.detentionCourtHearings.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetJuvenileHearingTypesEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByAtySettingSrchAttrEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;

import org.apache.commons.collections.IteratorUtils;

import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;

/**
 * @author sthyagarajan
 */
public class GetJJSCLDetentionByAtySettingSrchAttrCommand implements ICommand
{
    @Override
    public void execute(IEvent event) throws Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	GetJJSCLDetentionByAtySettingSrchAttrEvent evt = (GetJJSCLDetentionByAtySettingSrchAttrEvent) event;
	GetJuvenileHearingTypesEvent hearingTypeEvt = new GetJuvenileHearingTypesEvent();
	//83426
	Iterator<JuvenileHearingTypeCode> hearingTypeCodes = new Home().findAll(hearingTypeEvt, JuvenileHearingTypeCode.class);
	List<JuvenileHearingTypeCode> hearingTypeCodesList = IteratorUtils.toList(hearingTypeCodes);   

	if (evt.getBarNumber() != null || !evt.getBarNumber().isEmpty() && evt.getCourtDate() != null || !evt.getCourtDate().isEmpty() || evt.getJuvenileNumber() != null || evt.getJuvenileNumber() != null)
	{
	    Iterator<JJSCLDetention> detentionItr = JJSCLDetention.findAll(evt);
	    while (detentionItr.hasNext())
	    {
		JJSCLDetention detention = detentionItr.next();
		DocketEventResponseEvent resp = detention.valueObject();
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
				    break;
				}
			    }
			}
		    }
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
		}//resp
	    }
	}
    }
}
