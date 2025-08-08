package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetJuvenileHearingTypesEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByRefereeCourtEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.codetable.criminal.JuvenileCourtDecisionCode;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.JJSLastAttorney;
import pd.juvenilecase.JuvenileCaseHelper;

public class GetJJSCLCourtByRefereeCourtCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	GetJJSCLCourtByRefereeCourtEvent evt = (GetJJSCLCourtByRefereeCourtEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	
	GetJuvenileHearingTypesEvent hearingTypeEvt = new GetJuvenileHearingTypesEvent();
	// 83426
	Iterator<JuvenileHearingTypeCode> hearingTypeCodes = new Home().findAll(hearingTypeEvt, JuvenileHearingTypeCode.class);
	List<JuvenileHearingTypeCode> hearingTypeCodesList = IteratorUtils.toList(hearingTypeCodes); 

	Iterator<JJSCourt> courtItr = JJSCourt.findAll(evt);
	while (courtItr.hasNext())
	{
	    JJSCourt court = (JJSCourt) courtItr.next();
	    DocketEventResponseEvent resp = court.valueObject();
	    if (resp != null)
	    {
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
		if (hearingTypeCodes != null)
		{
		    hearingTypeCodes = hearingTypeCodesList.iterator(); //83426
		    while (hearingTypeCodes.hasNext())
		    {
			JuvenileHearingTypeCode hearingTypeCode = hearingTypeCodes.next();
			 String issueFlag = "";
			if (hearingTypeCode != null && hearingTypeCode.getInactiveInd() != null && !hearingTypeCode.getInactiveInd().equalsIgnoreCase("Y"))
			{
			    if (resp.getHearingType() != null && hearingTypeCode.getCode()!=null && hearingTypeCode.getCode().equalsIgnoreCase(resp.getHearingType()))
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
			    }
			    if (resp.getResetHearingType() != null && hearingTypeCode.getCode()!=null && hearingTypeCode.getCode().equalsIgnoreCase(resp.getResetHearingType()))
			    {
				resp.setResetHearingTypeDesc(hearingTypeCode.getDescription());
			    }
			   
			}
		    }
		}
		JJSLastAttorney jjsRecord = new JJSLastAttorney();
		Iterator it = JJSLastAttorney.findAll("juvenileNum",resp.getJuvenileNumber());
		if(resp.getAtyConfirmation().isEmpty()||resp.getAtyConfirmation()==null)
		{
        		/*JJSLastAttorney jjsRecord = new JJSLastAttorney();
        		    Iterator it = JJSLastAttorney.findAll("juvenileNum",resp.getJuvenileNumber());
        		  		*/
        		    if(it.hasNext())
        		    {  		    
        		  	JJSLastAttorney rec = (JJSLastAttorney) it.next();
        		  	if(resp.getSeqNum().equalsIgnoreCase("10"))//show from last attorney only for seqnum 10
        			{
                		  	resp.setBarNum(rec.getAtyBarNum());
                		  	resp.setAttorneyConnection(rec.getAttConnect());
                		  	resp.setOldattorneyConnection(rec.getAttConnect());
                		  	resp.setAttorneyName(rec.getAtyName());
                		  	resp.setOldattorneyName(rec.getAtyName());
        			}
        		    }
        		    else
        		    {
        			resp.setBarNum(null);
        		  	resp.setAttorneyConnection(null);
        		  	resp.setOldattorneyConnection(null);
        		  	resp.setAttorneyName(null);
        		  	resp.setOldattorneyName(null);
        		    }
		}
		//get gal details from last attorney if galadddate is later than resp.createdate
		// gal changes for task 158461
		Iterator lastiter = JJSLastAttorney.findAll("juvenileNum",resp.getJuvenileNumber());
		 if(lastiter.hasNext())
		 {  		    
		  	JJSLastAttorney rec = (JJSLastAttorney) lastiter.next();
		  	//if (rec.getGaladddate().compareTo(resp.getCreateDate())>1)
		  	if(rec.getGaladddate()!=null)
		  	{
        		  	//if (rec.getGaladddate().after(resp.getCreateDate()))
		  	    	if (rec.getGaladddate().compareTo(resp.getCreateDate())>=0)
        		  	{
        		  	    resp.setGalBarNum(rec.getGalBarNum());
        		  	    resp.setGalName(rec.getGalName());
        		  	}
		  	}
		  	
		 }
		 
		//added for task 151692  
		resp.setJuvenileCoactors(JuvenileCaseHelper.getjuvenileCoactors(resp.getJuvenileNumber(),resp.getReferralNum()));
		dispatch.postEvent(resp);
	    }
	}
    }
}
