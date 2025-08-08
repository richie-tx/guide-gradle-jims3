package pd.codetable.transactions;

import messaging.codetable.GetJuvenileOffenseCodeEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.criminal.JuvenileOffenseCode;

public class GetJuvenileOffenseCodeCommand implements ICommand {
	/**
	 * 
	 */
	public GetJuvenileOffenseCodeCommand()
	{

	}
	public void execute(IEvent event) throws Exception {
		GetJuvenileOffenseCodeEvent getCodeEvent = (GetJuvenileOffenseCodeEvent) event;

		JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode",getCodeEvent.getAlphaCode());
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		if (offenseCode != null)
		{	
			JuvenileOffenseCodeResponseEvent replyEvent = new JuvenileOffenseCodeResponseEvent();
			replyEvent.setOffenseCode(offenseCode.getOffenseCode());
			replyEvent.setNumericCode(offenseCode.getNumericCode());
			replyEvent.setShortDescription(offenseCode.getShortDescription());
			replyEvent.setDpsOffenseCode(offenseCode.getDpsOffenseCode());
			replyEvent.setLongDescription(offenseCode.getLongDescription());
			replyEvent.setCategory(offenseCode.getCategory());
			replyEvent.setSeverity(offenseCode.getSeverity());
			replyEvent.setSeveritySubtype(offenseCode.getSeveritySubtype());
			replyEvent.setReportGroup(offenseCode.getReportGroup());
			replyEvent.setCitationCode(offenseCode.getCitationCode());
			replyEvent.setCitationSource(offenseCode.getCitationSource());  // added for user-story 32111
			replyEvent.setInactiveInd(offenseCode.getInactiveInd());
			replyEvent.setDiscontCode(offenseCode.getDiscontCode()); //// U.S 58355
			replyEvent.setAgeRestrict( offenseCode.getAgeRestrict());
			replyEvent.setSeverityType(offenseCode.getSeverityType()); //US 184961
			dispatch.postEvent(replyEvent);
		}	
	}
	
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
	
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
	}
}
