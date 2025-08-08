package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.Calendar;
import java.util.Iterator;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.districtCourtHearings.UpdateJJSCLAncillarySettingEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import pd.juvenilecase.districtCourtHearings.JJSCLAncillary;
import pd.security.PDSecurityHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;


/**
 * @author nemathew
 */
public class UpdateJJSCLAncillarySettingCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {

	UpdateJJSCLAncillarySettingEvent evt = (UpdateJJSCLAncillarySettingEvent) event;
	DocketEventResponseEvent docket = new DocketEventResponseEvent();
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	String updateFlg = "false";
	String resetHearingUpdateFlag= "false"; //83690
	JJSCLAncillary ancillaryCrt = null;
	Iterator<JJSCLAncillary> ancillaryItr = null;

	if (!evt.isNewRecordCreated())
	{
	    ancillaryItr = JJSCLAncillary.findAll("OID", evt.getDocketEventId());
	}
	else
	{
	    ancillaryCrt = new JJSCLAncillary();
	}

	if (!evt.isNewRecordCreated() && (evt.getDocketEventId() != null))
	{
	    if (ancillaryItr != null && ancillaryItr.hasNext())
	    {
		 ancillaryCrt = ancillaryItr.next();
		if (ancillaryCrt!=null)
		{
		    //courtId
		    if (evt.getCourtId() != null && !evt.getCourtId().equals(ancillaryCrt.getCourtId()))
		    {
			if (evt.getCourtId().isEmpty())
			{
			    if (ancillaryCrt.getCourtId() != null)
			    {
				ancillaryCrt.setCourtId(null);
				updateFlg = "true";
			    }
			}
			else
			{
			    ancillaryCrt.setCourtId(evt.getCourtId());
			    updateFlg = "true";
			}
		    }
		    //courtDate
		    if (evt.getCourtDate()!=null && !evt.getCourtDate().equals(ancillaryCrt.getCourtDate()))
		    {
			ancillaryCrt.setCourtDate(evt.getCourtDate());
			updateFlg = "true";
		    }
		    //courtTime
		    if (evt.getCourtTime() != null && !evt.getCourtTime().equals(JuvenileFacilityHelper.getHoursMinsFromTime(ancillaryCrt.getCourtTime())))
		    {
			if (evt.getCourtTime().isEmpty())
			{
			    if (ancillaryCrt.getCourtTime() != null)
			    {
				ancillaryCrt.setCourtTime(null);
				updateFlg = "true";
			    }
			}
			else
			{
			    ancillaryCrt.setCourtTime(evt.getCourtTime());
			    updateFlg = "true";
			}
		    }
		    //petitionNum
		    if (evt.getPetitionNum() != null && !evt.getPetitionNum().equals(ancillaryCrt.getPetitionNumber()))
		    {
			if (evt.getPetitionNum().isEmpty())
			{
			    if (ancillaryCrt.getPetitionNumber() != null)
			    {
				ancillaryCrt.setPetitionNumber(null);
				updateFlg = "true";
			    }
			}
			else
			{
			    ancillaryCrt.setPetitionNumber(evt.getPetitionNum());
			    updateFlg = "true";
			}
		    }
		    //typeCase
		    if (evt.getTypeCase() != null && !evt.getTypeCase().equals(ancillaryCrt.getTypeCase()))
		    {
			if (evt.getTypeCase().isEmpty())
			{
			    if (ancillaryCrt.getTypeCase() != null)
			    {
				ancillaryCrt.setTypeCase(null);
				updateFlg = "true";
			    }
			}
			else
			{
			    ancillaryCrt.setTypeCase(evt.getTypeCase());
			    updateFlg = "true";
			}
		    }
		    //respondantName
		    if (evt.getRespondentName() != null && !evt.getRespondentName().equals(ancillaryCrt.getRespondantName()))
		    {
			if (evt.getRespondentName().isEmpty())
			{
			    if (ancillaryCrt.getRespondantName() != null)
			    {
				ancillaryCrt.setRespondantName(null);
				updateFlg = "true";
			    }
			}
			else
			{
			    ancillaryCrt.setRespondantName(evt.getRespondentName());
			    updateFlg = "true";
			}
		    }
		    //settingReason
		    if (evt.getSettingReason() != null && !evt.getSettingReason().equals(ancillaryCrt.getSettingReason()))
		    {
			if (evt.getSettingReason().isEmpty())
			{
			    if (ancillaryCrt.getSettingReason() != null)
			    {
				ancillaryCrt.setSettingReason(null);
				updateFlg = "true";
			    }
			}
			else
			{
			    ancillaryCrt.setSettingReason(evt.getSettingReason());
			    updateFlg = "true";
			}
		    }
		    //issueFlag
		    if (evt.getIssueFlag() != null)
		    {
			if (evt.getIssueFlag().equalsIgnoreCase("J"))
			{
			    ancillaryCrt.setIssueFlag("I");//If the Issue Flag is �J�, change the Issue Flag to �I�. Ref: BRD
			    updateFlg = "true";
			}
			else
			{
			    if (!evt.getIssueFlag().equals(ancillaryCrt.getIssueFlag()))
			    {
				ancillaryCrt.setIssueFlag(evt.getIssueFlag());
				updateFlg = "true";
			    }
			}
		    }
		    //attorneyConnection
		    if (evt.getAttorneyConnection() != null && !evt.getAttorneyConnection().equals(ancillaryCrt.getAttorneyConnection()))
		    {
			if (evt.getAttorneyConnection().isEmpty())
			{
			    if (ancillaryCrt.getAttorneyConnection() != null)
			    {
				ancillaryCrt.setAttorneyConnection(null);
				updateFlg = "true";
			    }
			}
			else
			{
			    ancillaryCrt.setAttorneyConnection(evt.getAttorneyConnection());
			    updateFlg = "true";
			}
		    }
		    //attorneyName
		    if (evt.getAttorneyName() != null && !evt.getAttorneyName().equals(ancillaryCrt.getAttorneyName()))
		    {
			if (evt.getAttorneyName().isEmpty())
			{
			    if (ancillaryCrt.getAttorneyName() != null)
			    {
				ancillaryCrt.setAttorneyName(null);
				updateFlg = "true";
			    }
			}
			else
			{
			    ancillaryCrt.setAttorneyName(evt.getAttorneyName());
			    updateFlg = "true";
			}
		    }
		    if (evt.getGalName() != null && !evt.getGalName().equals(ancillaryCrt.getGalName()))
		    {
			if (evt.getGalName().isEmpty())
			{
			    if (ancillaryCrt.getGalName() != null)
			    {
				ancillaryCrt.setGalName(null);
				updateFlg = "true";
			    }
			}
			else
			{
			    ancillaryCrt.setGalName(evt.getGalName());
			    updateFlg = "true";
			}
		    }
		    //barNumber
		    if (evt.getBarNum() != null && !evt.getBarNum().equals(ancillaryCrt.getBarNumber()))
		    {
			if (evt.getBarNum().isEmpty())
			{
			    if (ancillaryCrt.getBarNumber() != null)
			    {
				ancillaryCrt.setBarNumber(null);
				updateFlg = "true";
			    }
			}
			else
			{
			    ancillaryCrt.setBarNumber(evt.getBarNum());
			    updateFlg = "true";
			}
		    }
		    if (evt.getGalBarNum() != null && !evt.getGalBarNum().equals(ancillaryCrt.getGalBarNumber()))
		    {
			if (evt.getGalBarNum().isEmpty())
			{
			    if (ancillaryCrt.getGalBarNumber() != null)
			    {
				ancillaryCrt.setGalBarNumber(null);
				updateFlg = "true";
			    }
			}
			else
			{
			    ancillaryCrt.setGalBarNumber(evt.getGalBarNum());
			    updateFlg = "true";
			}
		    }
		    //seqNumber
		    if (evt.getSeqNumber() != null && !evt.getSeqNumber().equals(ancillaryCrt.getSeqNumber()))
		    {
			if (evt.getSeqNumber().isEmpty())
			{
			    if (ancillaryCrt.getSeqNumber() != null)
			    {
				ancillaryCrt.setSeqNumber(null);
				updateFlg = "true";
			    }
			}
			else
			{
			    ancillaryCrt.setSeqNumber(evt.getSeqNumber());
			    updateFlg = "true";
			}
		    }
		    //hearing Result:
		    if (evt.getHearingResult() != null && !evt.getHearingResult().equals(ancillaryCrt.getHearingResult()))
		    {
			if (evt.getHearingResult().isEmpty())
			{
			    if (ancillaryCrt.getHearingResult() != null)
			    {
				ancillaryCrt.setHearingResult(null);
				updateFlg = "true";
			    }
			}
			else
			{
			    ancillaryCrt.setHearingResult(evt.getHearingResult());
			    updateFlg = "true";
			}
		    }
		    //hearing disposition:
		    if (evt.getHearingDisposition() != null && !evt.getHearingDisposition().equals(ancillaryCrt.getHearingDisposition()))
		    {
			if (evt.getHearingDisposition().isEmpty())
			{
			    if (ancillaryCrt.getHearingDisposition() != null)
			    {
				ancillaryCrt.setHearingDisposition(null);
				updateFlg = "true";
			    }
			}
			else
			{
			    ancillaryCrt.setHearingDisposition(evt.getHearingDisposition());
			    updateFlg = "true";
			}
		    }
		    //transferTo
		    if (evt.getTransferTo() != null && !evt.getTransferTo().equals(ancillaryCrt.getTransferTo()))
		    {
			if (evt.getTransferTo().isEmpty())
			{
			    if (ancillaryCrt.getTransferTo() != null)
			    {
				ancillaryCrt.setTransferTo(null);
				updateFlg = "true";
			    }
			}
			else
			{
			    ancillaryCrt.setTransferTo(evt.getTransferTo());
			    updateFlg = "true";
			}
		    }

		    //Reset HearingType 
		    if (evt.getResetHearingType() != null && !evt.getResetHearingType().equals(ancillaryCrt.getResetHearingType()))
		    {
			if (evt.getResetHearingType().isEmpty())
			{
			    if (ancillaryCrt.getResetHearingType() != null)
			    {
				ancillaryCrt.setResetHearingType(null);
				updateFlg = "true";
			    }
			}
			else
			{
			    ancillaryCrt.setResetHearingType(evt.getResetHearingType());
			    resetHearingUpdateFlag= "true"; //83690
			    updateFlg = "true";
			}
		    }
		    //Comments
		    if (evt.getComments() != null && !evt.getComments().equals(ancillaryCrt.getComments()))
		    {
			if (evt.getComments().isEmpty())
			{
			    if (ancillaryCrt.getComments() != null)
			    {
				ancillaryCrt.setComments(null);
				updateFlg = "true";
			    }
			}
			else
			{
			    ancillaryCrt.setComments(evt.getComments());
			    updateFlg = "true";
			}
		    }
			////  task 168662
			if (evt.getInterpreter() != null && !evt.getInterpreter().equals(ancillaryCrt.getInterpreter()))
			{
			    if (evt.getInterpreter().isEmpty())
			    {
				if (ancillaryCrt.getInterpreter() != null)
				{
				    ancillaryCrt.setInterpreter(null);
				    updateFlg = "true";
				}
			    }
			    else
			    {
				ancillaryCrt.setInterpreter(evt.getInterpreter());
				updateFlg = "true";
			    }
			}

		    ancillaryCrt.setLcDate(DateUtil.getCurrentDate());
		    ancillaryCrt.setLcTime(Calendar.getInstance().getTime());
		    ancillaryCrt.setLcUser(PDSecurityHelper.getLogonId());
		    ancillaryCrt.setRectype("ANCILLARY");
		}
	    }
	}
	else
	{
	    ancillaryCrt.setChainNumber(evt.getChainNum());
	    ancillaryCrt.setSeqNumber(evt.getSeqNumber());
	    ancillaryCrt.setAttorneyConnection(evt.getAttorneyConnection());
	    if (evt.getAttorneyName() != null)
	    {
		if (evt.getAttorneyName().isEmpty())
		{
		    ancillaryCrt.setAttorneyName(null);
		}
		else
		{
		    ancillaryCrt.setAttorneyName(evt.getAttorneyName());
		}
	    }
	    if (evt.getGalName() != null)
	    {
		if (evt.getGalName().isEmpty())
		{
		    ancillaryCrt.setGalName(null);
		}
		else
		{
		    ancillaryCrt.setGalName(evt.getGalName());
		}
	    }
	    if (evt.getBarNum() != null)
	    {
		if (evt.getBarNum().isEmpty())
		{
		    ancillaryCrt.setBarNumber(null);
		}
		else
		{
		    ancillaryCrt.setBarNumber(evt.getBarNum());
		}
	    }
	    if (evt.getGalBarNum() != null)
	    {
		if (evt.getGalBarNum().isEmpty())
		{
		    ancillaryCrt.setGalBarNumber(null);
		}
		else
		{
		    ancillaryCrt.setGalBarNumber(evt.getGalBarNum());
		}
	    }
	    ancillaryCrt.setComments(evt.getComments());
	    ancillaryCrt.setCourtDate(evt.getCourtDate());
	    ancillaryCrt.setCourtId(evt.getCourtId());
	    if (evt.getCourtTime() != null)
	    {
		if (evt.getCourtTime().isEmpty())
		{
		    ancillaryCrt.setCourtTime(null);
		}
		else
		{
		    ancillaryCrt.setCourtTime(evt.getCourtTime());
		}
	    }
	    ancillaryCrt.setFilingDate(evt.getFilingDate());

	    ancillaryCrt.setHearingDisposition(evt.getHearingDisposition());
	    ancillaryCrt.setHearingResult(evt.getHearingResult());
	    ancillaryCrt.setSettingReason(evt.getSettingReason()); //hearing Type

	    ancillaryCrt.setIssueFlag(evt.getIssueFlag());
	    ancillaryCrt.setJuryFlag(evt.getJuryFlag());
	    ancillaryCrt.setPetitionAmendment(evt.getPetitionAmendment());
	    ancillaryCrt.setPetitionNumber(evt.getPetitionNum());
	    
	    ancillaryCrt.setPrevNotes(evt.getPrevNotes());
	    ancillaryCrt.setRectype(evt.getRecType());
	    ancillaryCrt.setResetHearingType(evt.getResetHearingType());
	    ancillaryCrt.setTjpcSeqNum(evt.getTjpcSeqNumber());
	    ancillaryCrt.setTransferTo(evt.getTransferTo());
	    ancillaryCrt.setRespondantName(evt.getRespondentName());
	    ancillaryCrt.setTypeCase(evt.getTypeCase());
	    //task 168662
	    ancillaryCrt.setInterpreter(evt.getInterpreter());
	    
	    ancillaryCrt.setLcDate(DateUtil.getCurrentDate());
	    ancillaryCrt.setLcTime(Calendar.getInstance().getTime());
	    ancillaryCrt.setLcUser(PDSecurityHelper.getLogonId());
	    ancillaryCrt.setRectype("ANCILLARY");
	    
	    IHome home = new Home();
	    home.bind(ancillaryCrt);
	    docket.setIsNewRecordCreated("true");
	}
	if (updateFlg.equalsIgnoreCase("true"))
	{
	    IHome home = new Home();
	    home.bind(ancillaryCrt);
	}
	docket.setResetHearingUpdateFlag(resetHearingUpdateFlag);	//83690
	docket.setUpdateFlag(updateFlg);
	dispatch.postEvent(docket);
    }
}