package pd.juvenilecase.referral.transactions;

import java.util.Calendar;
import java.util.Iterator;

import messaging.juvenilecase.GetJuvenileReferralPactRiskNeedsLevelEvent;
import messaging.referral.GetJJSReferralEvent;
import messaging.referral.UpdateJuvenileReferralProbDatesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import pd.juvenilecase.PACTRiskNeedLevel;
import pd.juvenilecase.referral.JJSReferral;
import pd.security.PDSecurityHelper;

/**
 * @author anpillai
 */
public class UpdateJuvenileReferralProbDatesCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	UpdateJuvenileReferralProbDatesEvent evt = (UpdateJuvenileReferralProbDatesEvent) event;
	// update Referral
	GetJJSReferralEvent jjsEvt = new GetJJSReferralEvent();
	jjsEvt.setJuvenileNum(evt.getJuvenileNum());
	jjsEvt.setReferralNum(evt.getReferralNum());
	Iterator<JJSReferral> refIter = JJSReferral.findAll(jjsEvt);

	if (refIter.hasNext())
	    ;
	{
	    JJSReferral ref = refIter.next();
	    if (ref != null)
	    {
		ref.setProbationStartDate(evt.getProbationStartDate());
		ref.setProbationEndDate(evt.getProbationEndDate());
		ref.setCloseDate(evt.getClosedDate());
		ref.setLcDate(DateUtil.getCurrentDate());
		ref.setLcTime(Calendar.getInstance().getTime());
		ref.setLcUser(PDSecurityHelper.getLogonId());
		IHome home = new Home();
		home.bind(ref);

		if ("R2C".equalsIgnoreCase(ref.getIntakeDecisionId()))
		{

		    GetJuvenileReferralPactRiskNeedsLevelEvent pactEvent = new GetJuvenileReferralPactRiskNeedsLevelEvent();
		    pactEvent.setJuvenileNum(evt.getJuvenileNum());
		    pactEvent.setReferralNumber(evt.getReferralNum());

		    Iterator<PACTRiskNeedLevel> riskNeedIter = PACTRiskNeedLevel.findAll(pactEvent);
		    if (!riskNeedIter.hasNext())
		    {
			// create pact risk
			PACTRiskNeedLevel pactRNLevel = new PACTRiskNeedLevel();
			pactRNLevel.setCaseFileId( evt.getCasefileId() );
			pactRNLevel.setJuvenileNumber(evt.getJuvenileNum());
			pactRNLevel.setReferralNumber(evt.getReferralNum());
			pactRNLevel.setRiskLvl("A");
			pactRNLevel.setNeedsLvl("A");
			pactRNLevel.setLastPactDate( ref.getIntakeDate() );
			pactRNLevel.setStatus("SYS GENERATE");
			pactRNLevel.setPactId(0);

		    }

		}
	    }
	}

    }

}
