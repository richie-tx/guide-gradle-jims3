package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.productionsupport.GetProductionSupportAssociatedJuvenileEventReferralsEvent;
import messaging.programreferral.reply.ProgramAssociatedJuvenileEventReferralResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.programreferral.JuvenileEventReferral;

public class GetProductionSupportAssociatedJuvenileEventReferralsCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportAssociatedJuvenileEventReferralsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetProductionSupportAssociatedJuvenileEventReferralsEvent  getAssociatedJuvenileReferralsEvent = (GetProductionSupportAssociatedJuvenileEventReferralsEvent) event;
		Iterator associatedReferralIter = JuvenileEventReferral.findAll("programReferralId", getAssociatedJuvenileReferralsEvent.getJuvenileProgramReferralNum());
		
		while(associatedReferralIter != null && associatedReferralIter.hasNext()){
			JuvenileEventReferral eventReferral = (JuvenileEventReferral)associatedReferralIter.next();	
			ProgramAssociatedJuvenileEventReferralResponseEvent associatedReferralResponseEvent = new ProgramAssociatedJuvenileEventReferralResponseEvent();
			
			associatedReferralResponseEvent.setEventReferralId(eventReferral.getOID());
			associatedReferralResponseEvent.setProgramReferralId(eventReferral.getProgramReferralId());
			associatedReferralResponseEvent.setServiceEventId(eventReferral.getServiceEventId());
			
			//production support 
			if(eventReferral.getCreateUserID() != null){
				associatedReferralResponseEvent.setCreateUserID(eventReferral.getCreateUserID());
			}
			if(eventReferral.getCreateTimestamp() != null){
				associatedReferralResponseEvent.setCreateDate(new Date(eventReferral.getCreateTimestamp().getTime()));
			}
			if(eventReferral.getUpdateUserID() != null){
				associatedReferralResponseEvent.setUpdateUser(eventReferral.getUpdateUserID());
			}
			if(eventReferral.getUpdateTimestamp() != null){
				associatedReferralResponseEvent.setUpdateDate(new Date(eventReferral.getUpdateTimestamp().getTime()));
			}
			if(eventReferral.getCreateJIMS2UserID() != null){
				associatedReferralResponseEvent.setCreateJIMS2UserID(eventReferral.getCreateJIMS2UserID());
			}
			if(eventReferral.getUpdateJIMS2UserID() != null){
				associatedReferralResponseEvent.setUpdateJIMS2UserID(eventReferral.getUpdateJIMS2UserID());
			}
			dispatch.postEvent(associatedReferralResponseEvent);
		}

	}

	/**
	 * @param event
	 * @roseuid 4278C7B8034F
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80359
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4278C7B80364
	 */
	public void update(Object anObject) {

	}
}
