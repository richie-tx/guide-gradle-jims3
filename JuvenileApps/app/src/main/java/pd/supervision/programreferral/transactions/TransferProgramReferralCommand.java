package pd.supervision.programreferral.transactions;

import java.util.Date;
import pd.supervision.programreferral.JuvenileProgramReferral;
import pd.supervision.programreferral.JuvenileProgramReferralAssignmentHistory;
import messaging.programreferral.TransferProgramReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class TransferProgramReferralCommand implements ICommand {

	public TransferProgramReferralCommand() {
	}

	/**
	 * @param event
	 * @roseuid 463A3F520390
	 */
	public void execute(IEvent event) {

		TransferProgramReferralEvent transferRefEvent = (TransferProgramReferralEvent) event;
		JuvenileProgramReferral progRef = JuvenileProgramReferral.find(transferRefEvent.getProgramReferralId());
		progRef.setCasefileId(transferRefEvent.getCasefileId());
		

		JuvenileProgramReferralAssignmentHistory prAssignmentHistory = new JuvenileProgramReferralAssignmentHistory();
		prAssignmentHistory.setCasefileId(transferRefEvent.getCasefileId());
		prAssignmentHistory.setProgramReferralId(transferRefEvent.getProgramReferralId());
		prAssignmentHistory.setProgramReferralAssignmentDate(new Date());
	}

	/**
	 * @param event
	 * @roseuid 463A3F52039E
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 463A3F5203A0
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 463A3F5203A2
	 */
	public void update(Object anObject) {

	}

}
