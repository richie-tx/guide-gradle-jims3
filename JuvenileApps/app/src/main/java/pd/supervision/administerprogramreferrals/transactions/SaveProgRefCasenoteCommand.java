package pd.supervision.administerprogramreferrals.transactions;

import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import messaging.administerprogramreferrals.SaveProgRefCasenoteEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SaveProgRefCasenoteCommand implements ICommand
{

	public void execute(IEvent event) throws Exception 
	{
		SaveProgRefCasenoteEvent saveEvent = (SaveProgRefCasenoteEvent)event;
		
		if(saveEvent!=null)
		{
			CSProgramReferralHelper.saveProgRefCasenote(saveEvent);
		}
	}

}
