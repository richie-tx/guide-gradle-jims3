package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.Calendar;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.districtCourtHearings.UpdateJJSCLCourtSettingEvent;
import messaging.referral.GetJJSDecisionHistoryEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.referral.JJSDecisionHistory;
import pd.security.PDSecurityHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

/**
 * @author sthyagarajan
 */
public class UpdateJJSCLCourtSettingCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	UpdateJJSCLCourtSettingEvent courtEvent = (UpdateJJSCLCourtSettingEvent) event;
	DocketEventResponseEvent docket = new DocketEventResponseEvent();
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	String updateFlg = "false";
	String resetHearingUpdateFlag = "false";
	JJSCourt court = null;
	Iterator<JJSCourt> courtItr = null;

	if (!courtEvent.isNewRecordCreated())
	{
	    courtItr = JJSCourt.findAll("OID", courtEvent.getDocketEventId());
	}
	else
	{
	    court = new JJSCourt();
	}

	//court
	if (courtItr != null && courtItr.hasNext())
	{
	    court = courtItr.next();
	    if (court != null)
	    {
		if (courtEvent.getCourtId() != null && !courtEvent.getCourtId().equals(court.getCourtId()))
		{
		    if (courtEvent.getCourtId().isEmpty())
		    {
			if (court.getCourtId() != null)
			{
			    court.setCourtId(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setCourtId(courtEvent.getCourtId());
			updateFlg = "true";
		    }
		}
		//courtDate
		if (courtEvent.getCourtDate()!=null && !courtEvent.getCourtDate().equals(court.getCourtDate()))
		{
		    court.setCourtDate(courtEvent.getCourtDate());
		    updateFlg = "true";
		}
		//RESET DATE task 187260
		if (courtEvent.getResetDate()!=null && !courtEvent.getResetDate().equals(court.getResetDate()))
		{
		    court.setResetDate(courtEvent.getResetDate());
		    updateFlg = "true";
		}		
				
		//courtTime
		if (courtEvent.getCourtTime() != null && !courtEvent.getCourtTime().equals(JuvenileFacilityHelper.getHoursMinsFromTime(court.getCourtTime())))
		{
		    if (courtEvent.getCourtTime().isEmpty())
		    {
			if (court.getCourtTime() != null)
			{
			    court.setCourtTime(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setCourtTime(courtEvent.getCourtTime());
			updateFlg = "true";
		    }
		}
		
		
		//attorneyConnection
		if (courtEvent.getAttorneyConnection() != null && !courtEvent.getAttorneyConnection().equals(court.getAttorneyConnection()))
		{
		    if (courtEvent.getAttorneyConnection().isEmpty())
		    {
			if (court.getAttorneyConnection() != null)
			{
			    court.setAttorneyConnection(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setAttorneyConnection(courtEvent.getAttorneyConnection());
			updateFlg = "true";
		    }
		}
		//bar number 
		if (courtEvent.getBarNumber() != null && !courtEvent.getBarNumber().equals(court.getBarNumber()))
		{
		    if (courtEvent.getBarNumber().isEmpty())
		    {
			if (court.getBarNumber() != null)
			{
			    court.setBarNumber(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setBarNumber(courtEvent.getBarNumber());
			updateFlg = "true";
		    }
		}
		if (courtEvent.getAtyConfirmation() != null && !courtEvent.getAtyConfirmation().equals(court.getAtyConfirmation()))
		{
		    if (courtEvent.getAtyConfirmation().isEmpty())
		    {
			if (court.getAtyConfirmation() != null)
			{
			    court.setAtyConfirmation(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setAtyConfirmation(courtEvent.getAtyConfirmation());
			updateFlg = "true";
		    }
		}
		//attorney name
		if (courtEvent.getAttorneyName() != null && !courtEvent.getAttorneyName().equals(court.getAttorneyName()))
		{
		    if (courtEvent.getAttorneyName().isEmpty())
		    {
			if (court.getAttorneyName() != null)
			{
			    court.setAttorneyName(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setAttorneyName(courtEvent.getAttorneyName());
			updateFlg = "true";
		    }
		}
		
		//attorney Adlitem
		if (courtEvent.getGalName() != null && !courtEvent.getGalName().equals(court.getGalName()))
		{
		    if (courtEvent.getGalName().isEmpty())
		    {
			if (court.getGalName() != null)
			{
			    court.setGalName(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setGalName(courtEvent.getGalName());
			updateFlg = "true";
		    }
		}
		//adlitem bar number 
		if (courtEvent.getGalBarNum() != null && !courtEvent.getGalBarNum().equals(court.getGalBarNumber()))
		{
		    if (courtEvent.getGalBarNum().isEmpty())
		    {
			if (court.getGalBarNumber() != null)
					{
			    court.setGalBarNumber(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			if (StringUtils.isNotEmpty( courtEvent.getGalBarNum()) && StringUtils.isNotEmpty( courtEvent.getGalName())){
					   
			   court.setGalBarNumber(courtEvent.getGalBarNum());
			   updateFlg = "true";
			}
		   }
		}
		//attorney 2 details
				if (courtEvent.getAttorney2Name() != null && !courtEvent.getAttorney2Name().equals(court.getAttorney2Name()))
				{
				    if (courtEvent.getAttorney2Name().isEmpty())
				    {
					if (court.getAttorney2Name() != null)
					{
					    court.setAttorney2Name(null);
					    updateFlg = "true";
					}
				    }
				    else
				    {
					court.setAttorney2Name(courtEvent.getAttorney2Name());
					updateFlg = "true";
				    }
				}
				if (courtEvent.getAttorney2BarNum() != null && !courtEvent.getAttorney2BarNum().equals(court.getAttorney2BarNum()))
				{
				    if (courtEvent.getAttorney2BarNum().isEmpty())
				    {
					if (court.getAttorney2BarNum() != null)
					{
					    court.setAttorney2BarNum(null);
					    updateFlg = "true";
					}
				    }
				    else
				    {
					if (StringUtils.isNotEmpty( courtEvent.getAttorney2BarNum()) && StringUtils.isNotEmpty( courtEvent.getAttorney2Name())){
							   
					   court.setAttorney2BarNum(courtEvent.getAttorney2BarNum());
					   updateFlg = "true";
					}
				   }
				}
				if (courtEvent.getAttorney2Connection() != null && !courtEvent.getAttorney2Connection().equals(court.getAttorney2Connection()))
				{
				    if (courtEvent.getAttorney2Connection().isEmpty())
				    {
					if (court.getAttorney2Connection() != null)
					{
					    court.setAttorney2Connection(null);
					    updateFlg = "true";
					}
				    }
				    else
				    {
					court.setAttorney2Connection(courtEvent.getAttorney2Connection());
					updateFlg = "true";
				    }
				}
				//
		//hearingType
		if (courtEvent.getHearingType() != null && !courtEvent.getHearingType().equals(court.getHearingType()))
		{
		    if (courtEvent.getHearingType().isEmpty())
		    {
			if (court.getHearingType() != null)
			{
			    court.setHearingType(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setHearingType(courtEvent.getHearingType());
			///92458
			JuvenileHearingTypeCode hearingType = null;
			hearingType = JuvenileHearingTypeCode.find(courtEvent.getHearingType());
			court.setOptionFlag(hearingType.getOptionInd());
			//
			updateFlg = "true";
		    }
		}
		//resetHearingType
		if (courtEvent.getResetHearingType() != null && !courtEvent.getResetHearingType().equals(court.getResetHearingType()))
		{
		    if (courtEvent.getResetHearingType().isEmpty())
		    {
			if (court.getResetHearingType() != null)
			{
			    court.setResetHearingType(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setResetHearingType(courtEvent.getResetHearingType());			
			resetHearingUpdateFlag= "true"; //83690
			updateFlg = "true";
		    }
		}
		//hearingCategory
		if (courtEvent.getHearingCategory() != null && !courtEvent.getHearingCategory().equals(court.getHearingCategory()))
		{
		    if (courtEvent.getHearingCategory().isEmpty())
		    {
			if (court.getHearingCategory() != null)
			{
			    court.setHearingCategory(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setHearingCategory(courtEvent.getHearingCategory());
			updateFlg = "true";
		    }
		}
		//issueFlag
		if (courtEvent.getIssueFlag() != null && !courtEvent.getIssueFlag().equals(court.getIssueFlag()))
		{
		    if (courtEvent.getIssueFlag().isEmpty())
		    {
			if (court.getIssueFlag() != null)
			{
			    court.setIssueFlag(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setIssueFlag(courtEvent.getIssueFlag());
			updateFlg = "true";
		    }
		}
		//juryflag
		if (courtEvent.getJuryFlag() != null && !courtEvent.getJuryFlag().equals(court.getJuryFlag()))
		{
		    if (courtEvent.getJuryFlag().isEmpty())
		    {
			if (court.getJuryFlag() != null)
			{
			    court.setJuryFlag(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setJuryFlag(courtEvent.getJuryFlag());
			updateFlg = "true";
		    }
		}
		//hearing Result:
		if (courtEvent.getHearingResult() != null && !courtEvent.getHearingResult().equals(court.getHearingResult()))
		{
		    if (courtEvent.getHearingResult().isEmpty())
		    {
			if (court.getHearingResult() != null)
			{
			    court.setHearingResult(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setHearingResult(courtEvent.getHearingResult());
			updateFlg = "true";

		    }
		}
		//hearing disposition:
		if (courtEvent.getHearingDisposition() != null && !courtEvent.getHearingDisposition().equals(court.getHearingDisposition()))
		{
		    if (courtEvent.getHearingDisposition().isEmpty())
		    {
			if (court.getHearingDisposition() != null)
			{
			    court.setHearingDisposition(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setHearingDisposition(courtEvent.getHearingDisposition());
			updateFlg = "true";
		    }
		}
		
		//tjpcdisp
		if (courtEvent.getTJPCDisp() != null && !courtEvent.getTJPCDisp().equals(court.getTJPCDisp()))
		{
		    if (courtEvent.getTJPCDisp().isEmpty())
			{
				if (court.getTJPCDisp() != null)
				{
				    court.setTJPCDisp(null);
				    updateFlg = "true";
				}
			}
			else
			{
			    court.setTJPCDisp(courtEvent.getTJPCDisp());
			    updateFlg = "true";
			    
			    // add to JJS_DECISION_HISTORY
			    JJSDecisionHistory history = new JJSDecisionHistory();
			    
			    int juvNum = Integer.parseInt(courtEvent.getJuvenileNumber());
			    
			    history.setJuvenileNum(String.valueOf(juvNum));
			    history.setReferralNum(court.getReferralNumber());
			    history.setInDecisionId(null);
			    history.setCourtDecisionId(courtEvent.getHearingDisposition());
			    history.setDecisionDate(courtEvent.getCourtDate());
			    history.setTjpcDisp(courtEvent.getTJPCDisp());
			    
			    GetJJSDecisionHistoryEvent req = new GetJJSDecisionHistoryEvent();
			    req.setJuvenileNum(String.valueOf(juvNum));
			    req.setReferralNum(court.getReferralNumber());
			    
			    Iterator<JJSDecisionHistory> historyIter = JJSDecisionHistory.findAll(req);
			    
			    if(!historyIter.hasNext()) {
				
				 history.setTjpcDispSeqNum("1");
			    }else {
				
				int ctr =0;
				while( historyIter.hasNext()) {
				    
				    historyIter.next();				    
				    ctr++;
				}				
				history.setTjpcDispSeqNum(String.valueOf( ctr+ 1 ));				
			    }			    			    
			}
		}		
		
		//transferTo:
		if (courtEvent.getTransferTo() != null && !courtEvent.getTransferTo().equals(court.getTransferTo()))
		{
		    if (courtEvent.getTransferTo().isEmpty())
		    {
			if (court.getTransferTo() != null)
			{
			    court.setTransferTo(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setTransferTo(courtEvent.getTransferTo());
			updateFlg = "true";
		    }
		}

		//notes
		if (courtEvent.getPrevNotes() != null && !courtEvent.getPrevNotes().equals(court.getPrevNotes()))
		{
		    if (courtEvent.getPrevNotes().isEmpty())
		    {
			if (court.getPrevNotes() != null)
			{
			    court.setPrevNotes(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setPrevNotes(courtEvent.getPrevNotes());
			updateFlg = "true";
		    }
		}
		//comments
		if (courtEvent.getComments() != null && !courtEvent.getComments().equals(court.getComments()))
		{
		    if (courtEvent.getComments().isEmpty())
		    {
			if (court.getComments() != null)
			{
			    court.setComments(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setComments(courtEvent.getComments());
			updateFlg = "true";
		    }
		}
		if (courtEvent.getAppAttorney() != null && !courtEvent.getAppAttorney().equals(court.getAppAttorney()))
		{
		    if (courtEvent.getAppAttorney().isEmpty())
		    {
			if (court.getAppAttorney() != null)
			{
			    court.setAppAttorney(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setAppAttorney(courtEvent.getAppAttorney());
			updateFlg = "true";
		    }
		}
		////  task 168662
		if (courtEvent.getInterpreter() != null && !courtEvent.getInterpreter().equals(court.getInterpreter()))
		{
		    if (courtEvent.getInterpreter().isEmpty())
		    {
			if (court.getInterpreter() != null)
			{
			    court.setInterpreter(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			court.setInterpreter(courtEvent.getInterpreter());
			updateFlg = "true";
		    }
		}
		court.setLcDate(DateUtil.getCurrentDate());
		court.setLcTime(Calendar.getInstance().getTime());
		court.setLcUser(PDSecurityHelper.getLogonId());
		court.setRectype("COURT");
	    }
	}
	else
	{ //creates new record.
	    court.setChainNumber(courtEvent.getChainNumber());
	    court.setSeqNumber(courtEvent.getSeqNumber());
	    //action Date
	    court.setAttorneyConnection(courtEvent.getAttorneyConnection());
	    if (courtEvent.getAttorneyName() != null)
	    {
		if (courtEvent.getAttorneyName().isEmpty())
		{
		    court.setAttorneyName(null);
		}
		else
		{
		    court.setAttorneyName(courtEvent.getAttorneyName());
		}
	    }
	    if (courtEvent.getBarNumber() != null)
	    {
		if (courtEvent.getBarNumber().isEmpty())
		{
		    court.setBarNumber(null);
		}
		else
		{
		    court.setBarNumber(courtEvent.getBarNumber());
		}
	    }
	    
	    if (courtEvent.getGalName() != null)
	    {
		if (courtEvent.getGalName().isEmpty())
		{
		    court.setGalName(null);
		}
		else
		{
		    court.setGalName(courtEvent.getGalName());
		}
	    }
	    if (courtEvent.getGalBarNum() != null)
	    {
		if (courtEvent.getGalBarNum().isEmpty())
		{
		    court.setGalBarNumber(null);
		}
		else
		{
		    court.setGalBarNumber(courtEvent.getGalBarNum());
		}
	    }
	    //add attorney 2 details
	    if (courtEvent.getAttorney2Name() != null)
	    {
		if (courtEvent.getAttorney2Name().isEmpty())
		{
		    court.setAttorney2Name(null);
		}
		else
		{
		    court.setAttorney2Name(courtEvent.getAttorney2Name());
		}
	    }
	    if (courtEvent.getAttorney2BarNum() != null)
	    {
		if (courtEvent.getAttorney2BarNum().isEmpty())
		{
		    court.setAttorney2BarNum(null);
		}
		else
		{
		    court.setAttorney2BarNum(courtEvent.getAttorney2BarNum());
		}
	    }
	    if (courtEvent.getAttorney2Connection() != null)
	    {
		if (courtEvent.getAttorney2Connection().isEmpty())
		{
		    court.setAttorney2Connection(null);
		}
		else
		{
		    court.setAttorney2Connection(courtEvent.getAttorney2Connection());
		}
	    }
	    //
	    court.setComments(courtEvent.getComments());
	    court.setCourtDate(courtEvent.getCourtDate());
	    court.setCourtId(courtEvent.getCourtId());
	    if (courtEvent.getCourtTime() != null)
	    {
		if (courtEvent.getCourtTime().isEmpty())
		{
		    court.setCourtTime(null);
		}
		else
		{
		    court.setCourtTime(courtEvent.getCourtTime());
		}
	    }
	    court.setFilingDate(courtEvent.getFilingDate());
	    court.setHearingCategory(courtEvent.getHearingCategory()); //category Code
	    court.setHearingDisposition(courtEvent.getHearingDisposition());
	    court.setHearingResult(courtEvent.getHearingResult());
	    court.setHearingType(courtEvent.getHearingType()); //hearing Type
	    ///92458
	    JuvenileHearingTypeCode hearingType = null;
	    hearingType = JuvenileHearingTypeCode.find(courtEvent.getHearingType());
	    court.setOptionFlag(hearingType.getOptionInd());
	    //
	    //court.setOptionFlag(courtEvent.getOptionFlag());
	    court.setIssueFlag(courtEvent.getIssueFlag());
	    court.setJuryFlag(courtEvent.getJuryFlag());
	    court.setJuvenileNumber(courtEvent.getJuvenileNumber());
	    court.setPetitionAllegation(courtEvent.getPetitionAllegation());
	    court.setPetitionAmendment(courtEvent.getPetitionAmendment());
	    court.setAmendmentDate(courtEvent.getAmendmentDate());
	    court.setPetitionNumber(courtEvent.getPetitionNumber());
	    court.setPetitionStatus(courtEvent.getPetitionStatus());
	    court.setPrevNotes(courtEvent.getPrevNotes());
	    court.setRectype(courtEvent.getRecType());
	    court.setReferralNumber(courtEvent.getReferralNumber());
	    court.setResetHearingType(courtEvent.getResetHearingType());
	    court.setTjpcSeqNumber(courtEvent.getTjpcSeqNumber());
	    court.setTransferTo(courtEvent.getTransferTo());
	    court.setAppAttorney(courtEvent.getAppAttorney());
	    //  task 168662
	    court.setInterpreter(courtEvent.getInterpreter());
	    court.setLcDate(DateUtil.getCurrentDate());
	    court.setLcTime(Calendar.getInstance().getTime());
	    court.setLcUser(PDSecurityHelper.getLogonId());
	    court.setRectype("COURT");
	    
	    IHome home = new Home();
	    home.bind(court);
	    docket.setIsNewRecordCreated("true");
	}

	if (updateFlg.equalsIgnoreCase("true"))
	{
	    IHome home = new Home();
	    home.bind(court);
	    if( courtEvent.isUpdateSubAdLitem()){
		 updateAdlitemSubsequent(courtEvent);
	      }
	}
	docket.setResetHearingUpdateFlag(resetHearingUpdateFlag);	
	docket.setUpdateFlag(updateFlg);
	dispatch.postEvent(docket);
    }
    
    /**
     * 
     * @param updateEvent
     */
    private void updateAdlitemSubsequent( UpdateJJSCLCourtSettingEvent updateEvent ){
	
	int OID = Integer.parseInt( updateEvent.getDocketEventId() );
	try
	{
	    int juvId = Integer.parseInt( updateEvent.getJuvenileNumber() );
	    Iterator<JJSCourt> courtItr = JJSCourt.findAll("juvenileNumber", String.valueOf( juvId) );
		while (courtItr.hasNext())
		{
		    JJSCourt foundRec = courtItr.next();
		    // added check for chainnumber too task 148593
		    if( Integer.parseInt(foundRec.getOID()) > OID && foundRec.getChainNumber().equalsIgnoreCase(updateEvent.getChainNumber()))
		    {// only update higher oid records
			
			 foundRec.setGalBarNumber(updateEvent.getGalBarNum());
			 foundRec.setGalName( updateEvent.getGalName() );
			    
			 IHome home = new Home();
			 home.bind( foundRec );
		    }
		}
	}
	catch (Exception e)
	{
	    // TODO: handle exception
	}
	
    }

}
