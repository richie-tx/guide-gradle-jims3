package pd.supervision.administerprogramreferrals.transactions;

import naming.CSAdministerProgramReferralsConstants;
import pd.supervision.administerprogramreferrals.CSProgramReferral;
import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import messaging.administerprogramreferrals.SaveProgramUnitReferralEvent;
import messaging.administerprogramreferrals.SubmitProgramReferralEvent;
import messaging.administerprogramreferrals.reply.SubmitExitProgRefResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.MessageUtil;

public class SaveProgramUnitReferralCommand implements ICommand
{
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 * Ryoung
	 */
	public void execute(IEvent event) throws Exception 
	{
		
		SaveProgramUnitReferralEvent saveEvent = ( SaveProgramUnitReferralEvent ) event;
		
		CSProgramReferral newReferral = new CSProgramReferral();
		
		
		newReferral.setReferralStatusCode(	CSAdministerProgramReferralsConstants.INITIATED_REFERRAL_STATUS );
		newReferral.setReferralDate( saveEvent.getReferralDate() ); 
		newReferral.setDefendantId( saveEvent.getDefendantId() );
		newReferral.setCriminalCaseId( saveEvent.getCriminalCaseId() );
		newReferral.setNewServiceProviderName( saveEvent.getServiceProviderName() );
		newReferral.setProgramLocationId( saveEvent.getLocationId() );
		newReferral.setReferralTypeCode( saveEvent.getReferralTypeCode() );
		newReferral.setPlacementReasonCd( saveEvent.getPlacementReasonCd() );
		newReferral.setAutoReferral( true );
		newReferral.setProgramUnitReferral( saveEvent.isProgramUnitReferral() );
		
		IHome home = new Home();
		home.bind( newReferral );
		
		SubmitProgramReferralEvent submitEvent = new SubmitProgramReferralEvent();
		
		if ( saveEvent.isSubmitReferral() ){
			
			submitEvent.setProgramReferralId( newReferral.getOID() );
			submitEvent.setProgramBeginDate( saveEvent.getProgramBeginDate() );
			submitEvent.setPlacementReasonCd( saveEvent.getPlacementReasonCd() );
			submitEvent.setReferralDate( saveEvent.getReferralDate() );
			SubmitExitProgRefResponseEvent responseEvent = CSProgramReferralHelper.submitProgramReferral(submitEvent);
			MessageUtil.postReply(responseEvent);
		}
		
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
