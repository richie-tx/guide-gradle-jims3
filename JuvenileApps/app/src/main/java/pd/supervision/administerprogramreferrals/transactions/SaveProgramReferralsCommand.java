package pd.supervision.administerprogramreferrals.transactions;


import java.util.Date;

import naming.CSAdministerProgramReferralsConstants;
import pd.supervision.administerprogramreferrals.CSProgramReferral;
import messaging.administerprogramreferrals.SaveProgramReferralsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class SaveProgramReferralsCommand implements ICommand
{
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception 
	{
		
		SaveProgramReferralsEvent saveEvent = (SaveProgramReferralsEvent) event;
		
		CSProgramReferral saveReferral = new CSProgramReferral();
		
		
		saveReferral.setReferralStatusCode(
				CSAdministerProgramReferralsConstants.INITIATED_REFERRAL_STATUS);
		
		if ( saveEvent.getReferralDate() == null ){
			
			saveReferral.setReferralDate( new Date() );
		}else{
			
			saveReferral.setReferralDate( saveEvent.getReferralDate() );
		}
		
		saveReferral.setDefendantId( saveEvent.getDefendantId() );
		saveReferral.setCriminalCaseId( saveEvent.getCriminalCaseId() );
		saveReferral.setReferralTypeCode( saveEvent.getReferralTypeCode() );
		saveReferral.setPlacementReasonCd( saveEvent.getPlacementReasonCd() );
		saveReferral.setAutoReferral( true );
		
		IHome home = new Home();
		home.bind( saveReferral );
		
	}//end of execute()

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}
}
