package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetJuvenileHearingTypesEvent;
import messaging.districtCourtHearings.GetJJSCLAncillaryCourtByReferreeCourtEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;

import org.apache.commons.collections.IteratorUtils;

import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.juvenilecase.districtCourtHearings.JJSCLAncillary;

public class GetJJSCLAncillaryCourtByReferreeCourtCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	GetJJSCLAncillaryCourtByReferreeCourtEvent evt = (GetJJSCLAncillaryCourtByReferreeCourtEvent) event;

	GetJuvenileHearingTypesEvent hearingTypeEvt = new GetJuvenileHearingTypesEvent();
	//83426
	Iterator<JuvenileHearingTypeCode> hearingTypeCodes = new Home().findAll(hearingTypeEvt, JuvenileHearingTypeCode.class);
	List<JuvenileHearingTypeCode> hearingTypeCodesList = IteratorUtils.toList(hearingTypeCodes); 

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator<JJSCLAncillary> ancillaryItr = JJSCLAncillary.findAll(evt);
	while (ancillaryItr.hasNext())
	{
	    JJSCLAncillary ancillaryCrt = (JJSCLAncillary) ancillaryItr.next();
	    if (ancillaryCrt != null)
	    {
		
		DocketEventResponseEvent resp = ancillaryCrt.valueObject();
		if (resp != null)
		{
		  //bug fix for 150682
		    if (resp.getCourtResult() != null && !resp.getCourtResult().isEmpty())
		    {
        		    JuvenileDispositionCode juvenileDispositionCode = JuvenileDispositionCode.find("codeAlpha",resp.getCourtResult());
        		    if (juvenileDispositionCode != null)
        		    {
        			resp.setCourtResultDesc(juvenileDispositionCode.getShortDesc());
        		    }
		    }
		    if (resp.getDisposition() != null && !resp.getDisposition().isEmpty())
			{		    
			    JuvenileDispositionCode juvenileDispositionCode = JuvenileDispositionCode.find("codeAlpha",resp.getDisposition());
			    if (juvenileDispositionCode != null)
			    {
				resp.setDispositionDesc(juvenileDispositionCode.getShortDesc());
			    }
			}
		    //
		    if (hearingTypeCodes != null)
		    {
			hearingTypeCodes = hearingTypeCodesList.iterator();//83426
			while (hearingTypeCodes.hasNext())
			{
			    JuvenileHearingTypeCode hearingTypeCode = hearingTypeCodes.next();
			    String issueFlag = "";
			    if (hearingTypeCode != null && hearingTypeCode.getInactiveInd() != null && !hearingTypeCode.getInactiveInd().equalsIgnoreCase("Y"))
			    {
				if (resp.getSettingReason() != null && hearingTypeCode.getCode()!=null && hearingTypeCode.getCode()!=null && hearingTypeCode.getCode().equalsIgnoreCase(resp.getSettingReason()))
				{
				    resp.setHearingTypeDesc(hearingTypeCode.getDescription());
				    // BUG 84372 - code added here so I can avoid this step in the docket print (redundant call to DB)
				    issueFlag = hearingTypeCode.getIssueInd();
					if (issueFlag.equalsIgnoreCase("J"))
					{
					    resp.setJuryFlagDesc("(JURY)");
					    resp.setJuryFlag("J");
					}
					else
					    if (issueFlag.equalsIgnoreCase("I"))
					    {
						resp.setJuryFlagDesc("(TRIAL)");
						resp.setJuryFlag("I");
					    }
					//ends BUG 84372
				}
				if (resp.getHearingTypeDesc() != null && !resp.getHearingTypeDesc().isEmpty() && resp.getHearingType() != null && hearingTypeCode.getCode()!=null && hearingTypeCode.getCode()!=null && hearingTypeCode.getCode().equalsIgnoreCase(resp.getHearingType()))
				{
				    resp.setHearingTypeDesc(hearingTypeCode.getDescription());
				    issueFlag = hearingTypeCode.getIssueInd();
					if (issueFlag.equalsIgnoreCase("J"))
					{
					    resp.setJuryFlagDesc("(JURY)");
					    resp.setJuryFlag("J");
					}
					else
					    if (issueFlag.equalsIgnoreCase("I"))
					    {
						resp.setJuryFlagDesc("(TRIAL)");
						resp.setJuryFlag("I");
					    }
					//ends BUG 84372
				}

				if (resp.getResetHearingType() != null && hearingTypeCode.getCode()!=null && hearingTypeCode.getCode()!=null && hearingTypeCode.getCode().equalsIgnoreCase(resp.getResetHearingType()))
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
