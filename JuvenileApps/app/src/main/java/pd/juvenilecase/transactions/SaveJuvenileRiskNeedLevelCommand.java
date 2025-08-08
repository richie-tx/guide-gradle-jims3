//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\SaveJuvenileRiskNeedLevelCommand.java

package pd.juvenilecase.transactions;

import java.util.ArrayList;
import java.util.Iterator;

import messaging.juvenilecase.GetJuvenileCasefileRiskNeedLevelByStatusEvent;
import messaging.juvenilecase.SaveJuvenileRiskNeedLevelEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.codetable.criminal.JuvenileReferralDispositionCode;
import pd.common.util.StringUtil;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.PACTRiskNeedLevel;
import pd.juvenilecase.referral.JJSReferral;

public class SaveJuvenileRiskNeedLevelCommand implements ICommand
{

	/**
	 * @roseuid 42A75896000B
	 */
	public SaveJuvenileRiskNeedLevelCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42A731DC0318
	 */
	public void execute(IEvent event)
	{
		
		SaveJuvenileRiskNeedLevelEvent saveEvent = (SaveJuvenileRiskNeedLevelEvent) event;
		//create the new record with status current, the previous current record becomes not current
		
		GetJuvenileCasefileRiskNeedLevelByStatusEvent riskNeedLvlEvt = new GetJuvenileCasefileRiskNeedLevelByStatusEvent();
		riskNeedLvlEvt.setCaseFileId(saveEvent.getCaseFileId());
		riskNeedLvlEvt.setJuvenileNum(saveEvent.getJuvenileNum());
		riskNeedLvlEvt.setStatus(saveEvent.getStatus());
				
		Iterator riskNeedIter = PACTRiskNeedLevel.findAll(riskNeedLvlEvt);
		
		while(riskNeedIter.hasNext())
		{
			PACTRiskNeedLevel rnLevelRec = (PACTRiskNeedLevel)riskNeedIter.next();
			//the most recent pact becomes current, old ones become not current -Bug #53158
			if(rnLevelRec.getStatus()!=null && rnLevelRec.getStatus().equalsIgnoreCase("CURRENT"))
			{
				rnLevelRec.setStatus("NOT CURRENT");
				break;
			}
		}
		
		PACTRiskNeedLevel pactRNLevel = new PACTRiskNeedLevel();
		pactRNLevel.setCaseFileId(saveEvent.getCaseFileId());
		pactRNLevel.setJuvenileNumber(saveEvent.getJuvenileNum());
		pactRNLevel.setReferralNumber(saveEvent.getReferralNumber());
		pactRNLevel.setRiskLvl(saveEvent.getRiskLvl());
		pactRNLevel.setNeedsLvl(saveEvent.getNeedsLvl());
		pactRNLevel.setLastPactDate(saveEvent.getLastPactDate());
		pactRNLevel.setStatus(saveEvent.getStatus());
		if( saveEvent.getPactId()!= null && !StringUtil.isEmpty(saveEvent.getPactId())){
		    pactRNLevel.setPactId(Integer.parseInt(saveEvent.getPactId()));
		}
		
		this.saveR2CReferrals(saveEvent);
		
		//Reset RISKNeed Flag.
		JuvenileCasefile casefile = null;
		if(saveEvent.getCaseFileId() != null && !(saveEvent.getCaseFileId().equals(""))) {
			casefile = JuvenileCasefile.find(saveEvent.getCaseFileId());
			casefile.setRiskNeed(false);
			new mojo.km.persistence.Home().bind(casefile);
			
		}
			
	}

	 /**
	     * @param request
	     */
	    private void saveR2CReferrals(SaveJuvenileRiskNeedLevelEvent request)
	    {

		Iterator codeIter = JuvenileReferralDispositionCode.findAll("tjpcCode", "060");
		while ( codeIter.hasNext() )
		{

		    JuvenileReferralDispositionCode code = (JuvenileReferralDispositionCode) codeIter.next();
		    if (!"Y".equalsIgnoreCase( code.getInactiveInd() ))
		    {

			Iterator<String> iter = request.getReferralNumbers().iterator();

			while ( iter.hasNext() )
			{

			    String referralNum = iter.next();
			    GetJJSReferralEvent req = new GetJJSReferralEvent();
			    req.setJuvenileNum(request.getJuvenileNum());
			    req.setReferralNum(referralNum);
			    Iterator referrals = JJSReferral.findAll(req);

			    // Check referrals
			    while (referrals.hasNext())
			    {
				JJSReferral referral = (JJSReferral) referrals.next();
				if ( code.getCode().trim().equalsIgnoreCase(referral.getIntakeDecisionId() ))
				{

				    PACTRiskNeedLevel pactRNLevel = new PACTRiskNeedLevel();
				    pactRNLevel.setCaseFileId(request.getCaseFileId());
				    pactRNLevel.setJuvenileNumber(request.getJuvenileNum());
				    pactRNLevel.setReferralNumber(referralNum);
				    pactRNLevel.setRiskLvl(request.getRiskLvl());
				    pactRNLevel.setNeedsLvl(request.getNeedsLvl());
				    pactRNLevel.setLastPactDate(request.getLastPactDate());
				    pactRNLevel.setStatus("SYS GENERATE");
				    if (request.getPactId() != null && !StringUtil.isEmpty(request.getPactId()))
				    {
					pactRNLevel.setPactId(Integer.parseInt(request.getPactId()));
				    }
				}

			    }

			}
		    }

		}

	    }

	/**
	 * @param event
	 * @roseuid 42A731DC031A
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42A731DC0325
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42A731DC0327
	 */
	public void update(Object anObject)
	{

	}

}
