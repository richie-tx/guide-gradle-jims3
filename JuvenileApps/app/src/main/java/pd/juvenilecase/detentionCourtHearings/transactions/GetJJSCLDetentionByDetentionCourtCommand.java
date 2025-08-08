package pd.juvenilecase.detentionCourtHearings.transactions;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetJuvenileHearingTypesEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByDetentionCourtEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;

/**
 * @author ryoung
 */
public class GetJJSCLDetentionByDetentionCourtCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	GetJJSCLDetentionByDetentionCourtEvent evt = (GetJJSCLDetentionByDetentionCourtEvent) event;
	// performance pre-load hearing types
	GetJuvenileHearingTypesEvent hearingTypeEvt = new GetJuvenileHearingTypesEvent();
	//83426
	//Iterator<JuvenileHearingTypeCode> hearingTypeCodes = new Home().findAll(hearingTypeEvt, JuvenileHearingTypeCode.class);
	//List<JuvenileHearingTypeCode> hearingTypeCodesList = IteratorUtils.toList(hearingTypeCodes); 

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator<JJSCLDetention> detentionItr = JJSCLDetention.findAll(evt);
	while (detentionItr.hasNext())
	{
	    JJSCLDetention detention = (JJSCLDetention) detentionItr.next();
	    if (detention != null)
	    {
		DocketEventResponseEvent resp = detention.valueObject();
		if (resp != null)
		{
		    /*if (hearingTypeCodes != null)
		    {
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
		    }*/
		    dispatch.postEvent(resp);
		}
		  //hearingTypeCodes = hearingTypeCodesList.iterator(); //83426
	    }
	}
    }
}
