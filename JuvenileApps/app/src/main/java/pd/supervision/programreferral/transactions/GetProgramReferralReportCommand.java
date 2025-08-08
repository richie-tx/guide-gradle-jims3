// Source file:
// C:\\views\\MJCW\\app\\src\\pd\\supervision\\calendar\\transactions\\GetProgramReferralListCommand.java

package pd.supervision.programreferral.transactions;

import java.util.Iterator;

import messaging.programreferral.GetProgramReferralReportEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.programreferral.JuvenileProgramReferral;

public class GetProgramReferralReportCommand implements ICommand {

	/**
	 * @roseuid 463BA5250088
	 */
	public GetProgramReferralReportCommand() {

	}

	/**
	 * @param event
	 * @roseuid 463A437A013B
	 */
	public void execute(IEvent event) {
	    
		GetProgramReferralReportEvent prevent = (GetProgramReferralReportEvent)event;
		
		Iterator progRefIter = JuvenileProgramReferral.findAll(prevent);
		while (progRefIter.hasNext()){
			JuvenileProgramReferral programReferral = (JuvenileProgramReferral)progRefIter.next();
				
			ProgramReferralResponseEvent resp = programReferral.getMinValueObject();
			MessageUtil.postReply(resp);			
				
		}
		
	}
	
	/**
	 * @param event
	 * @roseuid 463A437A013D
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 463A437A014A
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 463A437A014C
	 */
	public void update(Object anObject) {

	}

}
