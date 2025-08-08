package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import naming.JuvenileCourtHearingControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ProductionSupportControllerServiceNames;
import net.minidev.json.JSONObject;

import org.apache.commons.collections.IteratorUtils;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetJuvenileHearingTypesEvent;
import messaging.codetable.criminal.reply.JuvenileCourtDecisionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByChainAndSeqNumEvent;
import messaging.districtCourtHearings.GetJJSAllCourtByRefereeCourtEvent;
import messaging.districtCourtHearings.GetJJSCLAncillaryCourtByChainAndSeqNumEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByRefereeCourtEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.productionsupport.GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent;
import messaging.productionsupport.GetProductionSupportCourtRecordsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.utilities.MessageUtil;
import pd.codetable.criminal.JuvenileCourtDecisionCode;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilecase.JJSAllCourts;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.JJSLastAttorney;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;
import pd.juvenilecase.referral.JJSReferral;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

public class GetJJSAllCourtByRefereeCourtCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	GetJJSAllCourtByRefereeCourtEvent evt = (GetJJSAllCourtByRefereeCourtEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	
	GetJuvenileHearingTypesEvent hearingTypeEvt = new GetJuvenileHearingTypesEvent();
	// 83426
	Iterator<JuvenileHearingTypeCode> hearingTypeCodes = new Home().findAll(hearingTypeEvt, JuvenileHearingTypeCode.class);
	List<JuvenileHearingTypeCode> hearingTypeCodesList = IteratorUtils.toList(hearingTypeCodes); 
	List<JuvenileDispositionCodeResponseEvent> juvenileDispositions = JuvenileDistrictCourtHelper.getSortedCourtDecisions();
	
	Map<String, JuvenileCourtDecisionCodeResponseEvent> courtDecisionsMap = new HashMap<String, JuvenileCourtDecisionCodeResponseEvent>();//add to the responseevt
	//

	Iterator<JJSAllCourts> courtItr = JJSAllCourts.findAll(evt);
	while (courtItr.hasNext())
	{
	    JJSAllCourts court = (JJSAllCourts) courtItr.next();
	    DocketEventResponseEvent resp = court.valueObject();
	    if (resp != null && (resp.getRecType().equalsIgnoreCase("COURT")||resp.getRecType().equalsIgnoreCase("I.COURT")||resp.getRecType().equalsIgnoreCase("S.COURT")))//JJSCLCOURT
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
		    hearingTypeCodes = hearingTypeCodesList.iterator(); //83426
		    while (hearingTypeCodes.hasNext())
		    {
			JuvenileHearingTypeCode hearingTypeCode = hearingTypeCodes.next();
			String issueFlag = "";
			if (hearingTypeCode != null && hearingTypeCode.getInactiveInd() != null && !hearingTypeCode.getInactiveInd().equalsIgnoreCase("Y"))
			{
			    if (resp.getHearingType() != null && hearingTypeCode.getCode() != null && hearingTypeCode.getCode().equalsIgnoreCase(resp.getHearingType()))
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
			    if (resp.getResetHearingType() != null && hearingTypeCode.getCode() != null && hearingTypeCode.getCode().equalsIgnoreCase(resp.getResetHearingType()))
			    {
				resp.setResetHearingTypeDesc(hearingTypeCode.getDescription());
			    }

			}
		    }
		}
		JJSLastAttorney jjsRecord = new JJSLastAttorney();
		Iterator it = JJSLastAttorney.findAll("juvenileNum", resp.getJuvenileNumber());// retrive by chain number or referral number
		if (resp.getAtyConfirmation().isEmpty() || resp.getAtyConfirmation() == null)
		{
		    if (it.hasNext())
		    {
			JJSLastAttorney rec = (JJSLastAttorney) it.next();
			//get gal details from last attorney if galadddate is later than resp.createdate
			// gal changes for task 158461
			if (rec.getGaladddate() != null)
			    {
				//if (rec.getGaladddate().after(resp.getCreateDate()))
				if (rec.getGaladddate().compareTo(resp.getCreateDate()) >= 0)
				{
				    resp.setGalBarNum(rec.getGalBarNum());
				    resp.setGalName(rec.getGalName());
				}
			    }
			if(resp.getSeqNum().equalsIgnoreCase("10"))//show from last attorney only for seqnum 10 for district court
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
		//added for task 151692  
		resp.setJuvenileCoactors(JuvenileCaseHelper.getjuvenileCoactors(resp.getJuvenileNumber(), resp.getReferralNum()));
		resp.setCourtTime(JuvenileFacilityHelper.stripColonInDate(resp.getCourtTime()));	
		
		
	    }//if
	  //detention
		
		if (resp != null &&( resp.getRecType().equalsIgnoreCase("DETENTION") ||resp.getRecType().equalsIgnoreCase("I.DETENTION")||resp.getRecType().equalsIgnoreCase("S.DETENTION"))&& resp.getCourtDate().equals(evt.getCourtDate()))
		{
		    //bug fix for 150682
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
		    //
		    if (hearingTypeCodes != null)
		    {
			hearingTypeCodes = hearingTypeCodesList.iterator(); //83426
			while (hearingTypeCodes.hasNext())
			{
			    JuvenileHearingTypeCode hearingTypeCode = hearingTypeCodes.next();
			    if (hearingTypeCode != null && hearingTypeCode.getInactiveInd() != null && !hearingTypeCode.getInactiveInd().equalsIgnoreCase("Y"))
			    {
				if (resp.getHearingType() != null && hearingTypeCode.getCode() != null && hearingTypeCode.getCode().equalsIgnoreCase(resp.getHearingType()))
				{
				    resp.setHearingTypeDesc(hearingTypeCode.getDescription());
				    break;
				}
			    }
			}
		    }
		    JJSLastAttorney jjsRecord = new JJSLastAttorney();
		    Iterator it = JJSLastAttorney.findAll("juvenileNum", resp.getJuvenileNumber());
		    if (resp.getAtyConfirmation().isEmpty() || resp.getAtyConfirmation() == null)
		    {
			if (it.hasNext())
			{
			    JJSLastAttorney rec = (JJSLastAttorney) it.next();
			  //get gal details from last attorney if galadddate is later than resp.createdate
			    // gal changes for task 158461
			    if (rec.getGaladddate() != null)
				{
				    //if (rec.getGaladddate().after(resp.getCreateDate()))
				    if (rec.getGaladddate().compareTo(resp.getCreateDate()) >= 0)
				    {
					resp.setGalBarNum(rec.getGalBarNum());
					resp.setGalName(rec.getGalName());
				    }
				}
			    resp.setBarNum(rec.getAtyBarNum());
			    resp.setAttorneyConnection(rec.getAttConnect());
			    resp.setOldattorneyConnection(rec.getAttConnect());
			    resp.setAttorneyName(rec.getAtyName());
			    resp.setOldattorneyName(rec.getAtyName());
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
		    
		    //added for task 151689  
		    resp.setJuvenileCoactors(JuvenileCaseHelper.getjuvenileCoactors(resp.getJuvenileNumber(), resp.getReferralNum()));
		    resp.setCourtTime(JuvenileFacilityHelper.stripColonInDate(resp.getCourtTime()));
		   
		}//detention if
		
		
	    if (resp != null && (resp.getRecType().equalsIgnoreCase("ANCILLARY")||resp.getRecType().equalsIgnoreCase("I.ANCILLARY")||resp.getRecType().equalsIgnoreCase("S.ANCILLARY")))
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
		
		resp.setCourtTime(JuvenileFacilityHelper.stripColonInDate(resp.getCourtTime()));
		
		//added for CourtAction JuvenileNumber without 0 prefix #100626
		if (resp.getJuvenileNumber() != null)
		 {	
		    resp.setJuvNum(resp.getJuvenileNumber());
		 }
		//pad zero in juv num
		if (resp.getJuvenileNumber() != null && !resp.getJuvenileNumber().substring(0, 1).matches("0"))
		{
		    resp.setJuvenileNumber("0" + resp.getJuvenileNumber());
		}
		if (resp.getSettingReason() != null && !resp.getSettingReason().isEmpty())
		{
		    resp.setResetHearingType(resp.getSettingReason());
		}
		else
		{
		    resp.setResetHearingType(resp.getHearingType());
		}
		//dispatch.postEvent(resp);
	    }
	    dispatch.postEvent(resp);
	}//while
	
    }
}
