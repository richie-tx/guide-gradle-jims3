package pd.productionsupport.transactions;

import java.util.logging.Logger;

import messaging.productionsupport.DeleteProductionSupportJuvenileSchoolHistoryEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileSchoolHistory;


public class DeleteProductionSupportJuvenileSchoolHistoryCommand implements ICommand
{
	private Logger log = Logger.getLogger("DeleteProdSupportJuvenileSchoolHistoryCommand");

	/**
	 * @roseuid 42B18DC600AB
	 */
	public DeleteProductionSupportJuvenileSchoolHistoryCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42B18B3E032E
	 */
	public void execute(IEvent event)
	{
		DeleteProductionSupportJuvenileSchoolHistoryEvent schoolEvent = (DeleteProductionSupportJuvenileSchoolHistoryEvent) event;
		JuvenileSchoolHistory schoolHistory = JuvenileSchoolHistory.find(schoolEvent.getSchoolHistoryId());
		if(schoolHistory != null){
			schoolHistory.delete();
		}
	}

	/**
	 * @param event
	 * @roseuid 42B18B3E0330
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42B18B3E033C
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 42B18DC600CB
	 */
	public void update(Object updateObject)
	{

	}
}
