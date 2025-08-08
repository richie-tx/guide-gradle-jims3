package pd.juvenile.transactions;

import pd.juvenile.JuvenileCharterVEP;
import messaging.juvenile.SaveCharterVEPEvent;
import mojo.km.messaging.IEvent;
import mojo.km.context.ICommand;

public class SaveCharterVEPCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		SaveCharterVEPEvent request = (SaveCharterVEPEvent) anEvent;
		JuvenileCharterVEP charterVEP = new JuvenileCharterVEP();
		if (request.getJuvenileCharterVEPId() != null) {
			charterVEP = JuvenileCharterVEP.find(request.getJuvenileCharterVEPId());
		} 
		charterVEP.setCompleted(request.isCompleted());
		charterVEP.setCompletionDate(request.getCompletionDate());
		charterVEP.setJuvenileNum(request.getJuvenileNum());
		charterVEP.setProgramCodeId(request.getProgramCodeId());
		charterVEP.setStartDate(request.getStartDate());
	}

	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
}
