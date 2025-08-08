package pd.juvenilecase.referral.transactions;

import java.util.Calendar;
import java.util.Iterator;

import messaging.referral.GetJJSReferralEvent;
import messaging.referral.UpdateJJSReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import pd.juvenilecase.referral.JJSReferral;
import pd.security.PDSecurityHelper;


/**
 * 
 * @author sthyagarajan
 *
 */
public class UpdateJJSReferralCommand implements ICommand {

    @Override
    public void execute(IEvent event) throws Exception
    {
	UpdateJJSReferralEvent evt = (UpdateJJSReferralEvent)event;
	    // update Referral
	    GetJJSReferralEvent jjsEvt = new GetJJSReferralEvent();
	    jjsEvt.setJuvenileNum(evt.getJuvenileNum());
	    jjsEvt.setReferralNum(evt.getReferralNum());
	    Iterator<JJSReferral> refIter = JJSReferral.findAll(jjsEvt);

	    if (refIter.hasNext())
	    {
		JJSReferral ref = refIter.next();
		if (ref != null && ref.getCloseDate() == null)
		{
		    ref.setTotalPetitions("1");
		    ref.setLcDate(DateUtil.getCurrentDate());
		    ref.setLcTime(Calendar.getInstance().getTime());
		    ref.setLcUser(PDSecurityHelper.getLogonId());
		    IHome home = new Home();
		    home.bind(ref);
		}
	    }
        
    }

}
