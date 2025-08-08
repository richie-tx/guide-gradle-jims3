//Source file: C:\\views\\dev\\app\\src\\pd\\juvenile\\transactions\\SaveJuvenileGEDProgramCommand.java

package pd.juvenile.transactions;

import messaging.juvenile.SaveJuvenileGEDProgramEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileGEDProgram;

public class SaveJuvenileGEDProgramCommand implements ICommand {

	/**
	 * @roseuid 42BC4D31002A
	 */
	public SaveJuvenileGEDProgramCommand() {

	}

	/**
	 * @param event
	 * @roseuid 42BC4BE0005B
	 */
	public void execute(IEvent event) {
		SaveJuvenileGEDProgramEvent saveEvent = (SaveJuvenileGEDProgramEvent) event;
		JuvenileGEDProgram pgm = new JuvenileGEDProgram();
		pgm.setCompletionDate(saveEvent.getCompletionDate());
		pgm.setEnrollmentStatusCd(saveEvent.getEnrollmentStatusCd());
		pgm.setEnrollmentDate(saveEvent.getEnrollmentDate());
		pgm.setGedAwarded(saveEvent.isAwarded());
		pgm.setGedAwardedDate(saveEvent.getAwardedDate());
		pgm.setGedProgramCd(saveEvent.getProgramCd());
		pgm.setJuvenileNum(saveEvent.getJuvenileNum());
		pgm.setOtherProgramDesc(saveEvent.getProgramOtherDesc());
		pgm.setParticipationLevelCd(saveEvent.getParticipationCd());
		pgm.setVerifiedDate(saveEvent.getVerificationDate());
		pgm.setGedCompleted(saveEvent.isGedCompleted());
	}

	/**
	 * @param event
	 * @roseuid 42BC4BE0005D
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 42BC4BE0006A
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param updateObject
	 * @roseuid 42BC4D31003A
	 */
	public void update(Object updateObject) {

	}
}
