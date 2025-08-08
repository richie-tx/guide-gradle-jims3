package pd.juvenile.transactions;

import java.sql.Timestamp;
import java.util.Iterator;

import pd.juvenile.SubstanceAbuse;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.referral.JJSReferral;
import ui.common.UIUtil;
import ui.security.SecurityUIHelper;
import messaging.juvenile.CreateSubstanceAbuseEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.util.DateUtil;

public class CreateSubstanceAbuseCommand implements ICommand
{
    public CreateSubstanceAbuseCommand(){}
    
    public void execute(IEvent event) {
	CreateSubstanceAbuseEvent createEvent = (CreateSubstanceAbuseEvent)event;
	SubstanceAbuse substanceAuse = new SubstanceAbuse();
	substanceAuse.setJuvenileId( createEvent.getJuvenileNumber() );
	substanceAuse.setCasefileId( createEvent.getAssociateCasefile() );
	substanceAuse.setReferralNum( createEvent.getReferralNumber() );
	substanceAuse.setsAbuse( createEvent.getSubstanceAbuse() );
	substanceAuse.setSubstanceType( createEvent.getSubstanceType() );
	substanceAuse.setTreatmentLocation( createEvent.getTreatmentLocation() );
	substanceAuse.setCreateUserID(UIUtil.getCurrentUserID());
	substanceAuse.setCreateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
	substanceAuse.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
	
	Home home = new Home();
	
	try {
	    home.bind(substanceAuse);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	JuvenileCasefile casefile = JuvenileCasefile.find( createEvent.getAssociateCasefile() );
	casefile.setSubabuse(false);
	
	try {
	    home.bind(casefile);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	

	try {
	    GetJJSReferralEvent refEvent = new GetJJSReferralEvent();
	    refEvent.setJuvenileNum( createEvent.getJuvenileNumber() );
	    refEvent.setReferralNum( createEvent.getReferralNumber() );
	    
	    Iterator<JJSReferral> referralRespItr = JJSReferral.findAll(refEvent);
		if (referralRespItr.hasNext())
		{
		    JJSReferral  referralResp = referralRespItr.next();
		    referralResp.setsAbuse( createEvent.getSubstanceAbuse() );
		    home.bind(referralResp);
		}
	} catch (Exception e) {
	    e.printStackTrace();
	}
	System.out.println("Create substane abuse info");
    }

}
