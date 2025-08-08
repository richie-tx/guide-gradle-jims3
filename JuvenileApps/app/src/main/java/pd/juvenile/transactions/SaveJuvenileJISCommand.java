//Source file: C:\\views\\dev\\app\\src\\pd\\juvenile\\transactions\\SaveJuvenileJISProgramCommand.java

package pd.juvenile.transactions;


import messaging.juvenile.SaveJuvenileJISEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileGEDProgram;
import pd.juvenile.JuvenileJIS;

public class SaveJuvenileJISCommand implements ICommand {

	/**
	 * @roseuid 42BC4D31002A
	 */
	public SaveJuvenileJISCommand() {

	}

	/**
	 * @param event
	 * @roseuid 42BC4BE0005B
	 */
	public void execute(IEvent event) {
		SaveJuvenileJISEvent saveEvent = (SaveJuvenileJISEvent) event;
		JuvenileJIS jis = new JuvenileJIS();
		jis.setAgency(saveEvent.getAgency());
		jis.setJuvenileNum(saveEvent.getJuvenileNum());
		jis.setOtherAgency(saveEvent.getOtherAgency());
		jis.setEntryDate(saveEvent.getEntryDate());
		jis.setComments(saveEvent.getComments());
		
		
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
