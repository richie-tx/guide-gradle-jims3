package pd.productionsupport.transactions;

import java.util.Iterator;

import pd.juvenile.SubstanceAbuse;
import pd.juvenilecase.referral.JJSReferral;
import messaging.productionsupport.UpdateProductionSupportSubstanceAbuseInfoEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class UpdateProductionSupportSubstanceAbuseInfoCommand implements ICommand
{
    public void execute(IEvent event) {
	UpdateProductionSupportSubstanceAbuseInfoEvent updateEvent = (UpdateProductionSupportSubstanceAbuseInfoEvent) event;
	
	if ( updateEvent.getSubstanceAbuse() != null
		&& updateEvent.getSubstanceAbuse().getSubstanceAbuseId() != null
		&& updateEvent.getSubstanceAbuse().getSubstanceAbuseId().length() > 0 ){
	   
	    SubstanceAbuse substanceAbuse = SubstanceAbuse.find(updateEvent.getSubstanceAbuse().getSubstanceAbuseId());
	    substanceAbuse.setJuvenileId(updateEvent.getSubstanceAbuse().getJuvenileId());
	    substanceAbuse.setCasefileId(updateEvent.getSubstanceAbuse().getCasefileId());
	    substanceAbuse.setReferralNum(updateEvent.getSubstanceAbuse().getReferralNum());
	    substanceAbuse.setsAbuse(updateEvent.getSubstanceAbuse().getsAbuseCode());
	    if ( updateEvent.getSubstanceAbuse().getSubstanceTypeCode()!= null
		    && updateEvent.getSubstanceAbuse().getSubstanceTypeCode().length > 0 ){
		StringBuffer substanceTypeCodeBuff = new StringBuffer();
		for (int i = 0; i < updateEvent.getSubstanceAbuse().getSubstanceTypeCode().length; i++ ){
		    if( i == 0 ) {
			substanceTypeCodeBuff.append( updateEvent.getSubstanceAbuse().getSubstanceTypeCode()[i]);
		    }
			
		    if ( i > 0 ){
			substanceTypeCodeBuff.append( ", " +  updateEvent.getSubstanceAbuse().getSubstanceTypeCode()[i]);
		    }
		}
		
		substanceAbuse.setSubstanceType( substanceTypeCodeBuff.toString() );
	    } else {
		substanceAbuse.setSubstanceType("");
	    }
	    
	    substanceAbuse.setTreatmentLocation( updateEvent.getSubstanceAbuse().getTreatmentLocation() );
	    
	    IHome home = new Home();
	    home.bind(substanceAbuse);
	        
	    if ( updateEvent.getHighestOID() ){
		GetJJSReferralEvent getReferralEvent = new GetJJSReferralEvent();
		getReferralEvent.setJuvenileNum(updateEvent.getSubstanceAbuse().getJuvenileId());
		getReferralEvent.setReferralNum(updateEvent.getSubstanceAbuse().getReferralNum());
		
		Iterator<JJSReferral> referralIter = JJSReferral.findAllWithoutFilter(getReferralEvent);
		while ( referralIter.hasNext() ) {
		    JJSReferral referral = (JJSReferral) referralIter.next();
		    referral.setsAbuse( updateEvent.getSubstanceAbuse().getsAbuseCode() );
		    home.bind(referral);
		}
		
	    }
	};
    }

}
