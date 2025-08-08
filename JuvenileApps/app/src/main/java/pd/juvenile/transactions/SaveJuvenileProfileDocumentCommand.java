package pd.juvenile.transactions;

import messaging.juvenile.SaveJuvenileProfileDocumentEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileDocuments;

public class SaveJuvenileProfileDocumentCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		SaveJuvenileProfileDocumentEvent requestEvent = (SaveJuvenileProfileDocumentEvent)anEvent;
   		JuvenileDocuments document = new JuvenileDocuments();
		document.setDocument(requestEvent.getDocument());
		document.setJuvenileId(requestEvent.getJuvenileNum());
		document.setDocumentTypeCodeId(requestEvent.getDocumentTypeCodeId());
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
