package pd.juvenilecase.detentionCourtHearings.transactions;

import java.util.Calendar;
import java.util.Iterator;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.detentionCourtHearings.UpdateJJSCLDetentionSettingEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;
import pd.security.PDSecurityHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

/**
 * Used only in district court
 * 
 * @author sthyagarajan
 */
public class UpdateJJSCLDetentionSettingCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	UpdateJJSCLDetentionSettingEvent evt = (UpdateJJSCLDetentionSettingEvent) event;
	DocketEventResponseEvent docket = new DocketEventResponseEvent();
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	String updateFlg = "false";
	JJSCLDetention detention = null;
	Iterator<JJSCLDetention> detentionItr = null;

	if (!evt.isNewRecordCreated())
	{
	    detentionItr = JJSCLDetention.findAll("OID", evt.getDocketEventId());
	}
	else{
	    detention = new JJSCLDetention();
	}

	if (detentionItr != null && detentionItr.hasNext())
	{
	    detention = detentionItr.next();

	    if (detention != null)
	    {
		//courtId
		if (evt.getCourtId() != null && !evt.getCourtId().equals(detention.getCourtId()))
		{
		    if (evt.getCourtId().isEmpty())
		    {
			if (detention.getCourtId() != null)
			{
			    detention.setCourtId(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			detention.setCourtId(evt.getCourtId());
			updateFlg = "true";
		    }
		}
		//courtDate
		if (evt.getCourtDate() == null)
		{
		    if (detention.getCourtDate() != null)
		    {
			detention.setCourtDate(null);
			updateFlg = "true";
		    }
		}
		else
		{
		    if (!evt.getCourtDate().equals(detention.getCourtDate()))
		    {
			detention.setCourtDate(evt.getCourtDate());
			updateFlg = "true";
		    }
		}
		//RESET DATE task 187260
		if (evt.getResettoDate() == null)
		{
		    if (detention.getResettoDate() != null)
		    {
			detention.setResettoDate(null);
			updateFlg = "true";
		    }
		}
		else
		{
		    if (!evt.getResettoDate().equals(detention.getResettoDate()))
		    {
			detention.setResettoDate(evt.getResettoDate());
			updateFlg = "true";
		    }
		}
		//courtTime
		if (evt.getCourtTime() != null && !evt.getCourtTime().equals(JuvenileFacilityHelper.getHoursMinsFromTime(detention.getCourtTime())))
		{
		    if (evt.getCourtTime().isEmpty())
		    {
			if (detention.getCourtTime() != null)
			{
			    detention.setCourtTime(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			detention.setCourtTime(evt.getCourtTime());
			updateFlg = "true";
		    }
		}
		//petitionNum
		if (evt.getPetitionNumber() != null && !evt.getPetitionNumber().equals(detention.getPetitionNumber()))
		{
		    if (evt.getPetitionNumber().isEmpty())
		    {
			if (detention.getPetitionNumber() != null)
			{
			    detention.setPetitionNumber(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			detention.setPetitionNumber(evt.getPetitionNumber());
			updateFlg = "true";
		    }
		}
		//hearingType
		if (evt.getHearingType() != null && !evt.getHearingType().equals(detention.getHearingType()))
		{
		    if (evt.getHearingType().isEmpty())
		    {
			if (detention.getHearingType() != null)
			{
			    detention.setHearingType(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			detention.setHearingType(evt.getHearingType());
			updateFlg = "true";
		    }
		}
		//issueFlag
		if (evt.getIssueFlag() != null)
		{

		    if (!evt.getIssueFlag().equals(detention.getIssueFlag()))
		    {
			detention.setIssueFlag(evt.getIssueFlag());
			updateFlg = "true";
		    }

		}
		//attorneyConnection
		if (evt.getAttorneyConnection() != null && !evt.getAttorneyConnection().equals(detention.getAttorneyConnection()))
		{
		    if (evt.getAttorneyConnection().isEmpty())
		    {
			if (detention.getAttorneyConnection() != null)
			{
			    detention.setAttorneyConnection(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			detention.setAttorneyConnection(evt.getAttorneyConnection());
			updateFlg = "true";
		    }
		}
		//attorneyName
		if (evt.getAttorneyName() != null && !evt.getAttorneyName().equals(detention.getAttorneyName()))
		{
		    if (evt.getAttorneyName().isEmpty())
		    {
			if (detention.getAttorneyName() != null)
			{
			    detention.setAttorneyName(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			detention.setAttorneyName(evt.getAttorneyName());
			updateFlg = "true";
		    }
		}
		//atyConfirmation
		if (evt.getAtyConfirmation() != null && !evt.getAtyConfirmation().equals(detention.getAtyConfirmation()))
		{
		    if (evt.getAtyConfirmation().isEmpty())
		    {
			if (detention.getAtyConfirmation() != null)
			{
			    detention.setAtyConfirmation(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			detention.setAtyConfirmation(evt.getAtyConfirmation());
			updateFlg = "true";
		    }
		}
		////  task 168662
		if (evt.getInterpreter() != null && !evt.getInterpreter().equals(detention.getInterpreter()))
		{
		    if (evt.getInterpreter().isEmpty())
		    {
			if (detention.getInterpreter() != null)
			{
			    detention.setInterpreter(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			detention.setInterpreter(evt.getInterpreter());
			updateFlg = "true";
		    }
		}
		//adlitemName
		if (evt.getGalName() != null && !evt.getGalName().equals(detention.getGalName()))
		{
		    if (evt.getGalName().isEmpty())
		    {
			if (detention.getGalName() != null)
			{
			    detention.setGalName(null);
			    updateFlg = "true";
			}
		    }
		else
		    {
		    detention.setGalName(evt.getGalName());
		    updateFlg = "true";
		    }
		}
		//seqNumber
		if (evt.getSeqNumber() != null && !evt.getSeqNumber().equals(detention.getSeqNumber()))
		{
		    if (evt.getSeqNumber().isEmpty())
		    {
			if (detention.getSeqNumber() != null)
			{
			    detention.setSeqNumber(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			detention.setSeqNumber(evt.getSeqNumber());
			updateFlg = "true";
		    }
		}
		//barNumber
		if (evt.getBarNumber() != null && !evt.getBarNumber().equals(detention.getBarNumber()))
		{
		    if (evt.getBarNumber().isEmpty())
		    {
			if (detention.getBarNumber() != null)
			{
			    detention.setBarNumber(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			detention.setBarNumber(evt.getBarNumber());
			updateFlg = "true";
		    }
		}
		//Adlitem barNumber
		if (evt.getGalBarNum() != null && !evt.getGalBarNum().equals(detention.getGalBarNumber()))
		{
		    if (evt.getGalBarNum().isEmpty())
		    {
			if (detention.getGalBarNumber() != null)
			{
			    detention.setGalBarNumber(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			detention.setGalBarNumber(evt.getGalBarNum());
			updateFlg = "true";
		    }
		}
		//attorney 2 details
		//attorneyConnection
				if (evt.getAttorney2Connection() != null && !evt.getAttorney2Connection().equals(detention.getAttorney2Connection()))
				{
				    if (evt.getAttorney2Connection().isEmpty())
				    {
					if (detention.getAttorney2Connection() != null)
					{
					    detention.setAttorney2Connection(null);
					    updateFlg = "true";
					}
				    }
				    else
				    {
					detention.setAttorney2Connection(evt.getAttorney2Connection());
					updateFlg = "true";
				    }
				}
				//attorneyName
				if (evt.getAttorney2Name() != null && !evt.getAttorney2Name().equals(detention.getAttorney2Name()))
				{
				    if (evt.getAttorney2Name().isEmpty())
				    {
					if (detention.getAttorney2Name() != null)
					{
					    detention.setAttorney2Name(null);
					    updateFlg = "true";
					}
				    }
				    else
				    {
					detention.setAttorney2Name(evt.getAttorney2Name());
					updateFlg = "true";
				    }
				}
				if (evt.getAttorney2BarNum() != null && !evt.getAttorney2BarNum().equals(detention.getAttorney2BarNum()))
				{
				    if (evt.getAttorney2BarNum().isEmpty())
				    {
					if (detention.getAttorney2BarNum() != null)
					{
					    detention.setAttorney2BarNum(null);
					    updateFlg = "true";
					}
				    }
				    else
				    {
					detention.setAttorney2BarNum(evt.getAttorney2BarNum());
					updateFlg = "true";
				    }
				}
				//


		//hearing Result:
		if (evt.getHearingResult() != null && !evt.getHearingResult().equals(detention.getHearingResult()))
		{
		    if (evt.getHearingResult().isEmpty())
		    {
			if (detention.getHearingResult() != null)
			{
			    detention.setHearingResult(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			detention.setHearingResult(evt.getHearingResult());
			updateFlg = "true";
		    }
		}
		//alternate Setting Indicator
		if (evt.getAlternateSettingInd() != null && !evt.getAlternateSettingInd().equals(detention.getAlternateSettingInd()))
		{
		    if (evt.getAlternateSettingInd().isEmpty())
		    {
			if (detention.getAlternateSettingInd() != null)
			{
			    detention.setAlternateSettingInd(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			detention.setAlternateSettingInd(evt.getAlternateSettingInd());
			updateFlg = "true";
		    }
		}
		//hearing disposition:
		if (evt.getHearingDisposition() != null && !evt.getHearingDisposition().equals(detention.getHearingDisposition()))
		{
		    if (evt.getHearingDisposition().isEmpty())
		    {
			if (detention.getHearingDisposition() != null)
			{
			    detention.setHearingDisposition(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			detention.setHearingDisposition(evt.getHearingDisposition());
			updateFlg = "true";
		    }
		}

		//transferTo needs to be set null explicitly from end, Bug #81119
		if (evt.getTransferTo() != null && !evt.getTransferTo().equals(detention.getTransferTo()))
		{
		    detention.setTransferTo(evt.getTransferTo());
		    updateFlg = "true";
		}
		else
		{
		    detention.setTransferTo(null);
		    updateFlg = "true";
		}
		
		//comments
		if (evt.getComments() != null && !evt.getComments().equals(detention.getComments()))
		{
		    if (evt.getComments().isEmpty())
		    {
			if (detention.getComments() != null)
			{
			    detention.setComments(null);
			    updateFlg = "true";
			}
		    }
		    else
		    {
			detention.setComments(evt.getComments());
			updateFlg = "true";
		    }
		}
		detention.setLcDate(DateUtil.getCurrentDate());
		detention.setLcTime(Calendar.getInstance().getTime());
		detention.setLcUser(PDSecurityHelper.getLogonId());
		detention.setRecType("DETENTION");
	    }
	}
	else
	{
	    detention.setAlternateSettingInd(evt.getAlternateSettingInd());
	    detention.setAttorneyConnection(evt.getAttorneyConnection());
	    detention.setSeqNumber(evt.getSeqNumber());
	    if (evt.getAttorneyName() != null)
	    {
		if (evt.getAttorneyName().isEmpty())
		{
		    detention.setAttorneyName(null);
		}
		else
		{
		    detention.setAttorneyName(evt.getAttorneyName());
		}
	    }
	    if (evt.getBarNumber() != null)
	    {
		if (evt.getBarNumber().isEmpty())
		{
		    detention.setBarNumber(null);
		}
		else
		{
		    detention.setBarNumber(evt.getBarNumber());
		}
	    }
	 // gal changes for task 158461
	    if (evt.getGalName() != null)
	    {
		if (evt.getGalName().isEmpty())
		{
		    detention.setGalName(null);
		}
		else
		{
		    detention.setGalName(evt.getGalName());
		}
	    }
	    if (evt.getGalBarNum() != null)
	    {
		if (evt.getGalBarNum().isEmpty())
		{
		    detention.setGalBarNumber(null);
		}
		else
		{
		    detention.setGalBarNumber(evt.getGalBarNum());
		}
	    }
	    //
	    //attorney 2 details
	    detention.setAttorney2Connection(evt.getAttorney2Connection());
	    if (evt.getAttorney2Name() != null)
	    {
		if (evt.getAttorney2Name().isEmpty())
		{
		    detention.setAttorney2Name(null);
		}
		else
		{
		    detention.setAttorney2Name(evt.getAttorney2Name());
		}
	    }
	    if (evt.getAttorney2BarNum() != null)
	    {
		if (evt.getAttorney2BarNum().isEmpty())
		{
		    detention.setAttorney2BarNum(null);
		}
		else
		{
		    detention.setAttorney2BarNum(evt.getAttorney2BarNum());
		}
	    }
	    //
	    detention.setComments(evt.getComments());
	    detention.setCourtDate(evt.getCourtDate());
	    detention.setCourtId(evt.getCourtId());
	    
	    if (evt.getCourtTime() != null)
	    {
		if (evt.getCourtTime().isEmpty())
		{
		    detention.setCourtTime(null);
		}
		else
		{
		    detention.setCourtTime(evt.getCourtTime());
		}
	    }
	    detention.setHearingDisposition(evt.getHearingDisposition());
	    detention.setHearingResult(evt.getHearingResult());
	    detention.setHearingType(evt.getHearingType()); //hearing Type
	    detention.setIssueFlag(evt.getIssueFlag());
	    detention.setPetitionNumber(evt.getPetitionNumber());
	    detention.setRecType(evt.getRecType());
	    detention.setReferralNumber(evt.getReferralNumber());
	    detention.setTjpcseqnum(evt.getTjpcseqnum());
	    detention.setTransferTo(evt.getTransferTo());
	    detention.setJuvenileNumber(evt.getJuvenileNumber());
	    detention.setChainNumber(evt.getChainNumber());
	//  task 168662
	    detention.setInterpreter(evt.getInterpreter());
	    detention.setLcDate(DateUtil.getCurrentDate());
	    detention.setLcTime(Calendar.getInstance().getTime());
	    detention.setLcUser(PDSecurityHelper.getLogonId());
	    detention.setDetentionId(evt.getDetentionId());
	    detention.setRecType("DETENTION");

	    IHome home = new Home();
	    home.bind(detention);
	    docket.setIsNewRecordCreated("true");
	}

	if (updateFlg.equalsIgnoreCase("true"))
	{
	    IHome home = new Home();
	    home.bind(detention);
	}
	docket.setUpdateFlag(updateFlg);
	dispatch.postEvent(docket);
    }
}
