// Source file:
// C:\\views\\MJCW\\app\\src\\pd\\supervision\\calendar\\transactions\\GetProgramReferralDetailsCommand.java

package pd.supervision.programreferral.transactions;

import pd.supervision.programreferral.JuvenileProgramReferral;
import messaging.programreferral.GetProgramReferralDetailsEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetProgramReferralDetailsCommand implements ICommand {

	/**
	 * @roseuid 463BA52403D4
	 */
	public GetProgramReferralDetailsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 463A437A01F8
	 */
	public void execute(IEvent event) {
		GetProgramReferralDetailsEvent detEvent = (GetProgramReferralDetailsEvent)event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		JuvenileProgramReferral progRef = JuvenileProgramReferral.find(detEvent.getProgramReferralId());
		if (progRef!=null){
			ProgramReferralResponseEvent resp = progRef.getValueObject(true);
			dispatch.postEvent(resp);
		}
	}

	/**
	 * @param event
	 * @roseuid 463A437A0215
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 463A437A0217
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 463A437A0219
	 */
	public void update(Object anObject) {

	}
}
