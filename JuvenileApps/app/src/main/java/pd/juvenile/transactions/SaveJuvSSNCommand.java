/*
 * Created on Aug 14, 2017
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenile.transactions;

import naming.PDConstants;

import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.SaveJuvSSNEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author ugopinath
 *
 */
public class SaveJuvSSNCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		SaveJuvSSNEvent requestEvent = (SaveJuvSSNEvent) event;

		if (requestEvent.getSsn() == null || requestEvent.getSsn().equals(PDConstants.BLANK)){
			return;
		}
		
		JuvenileCore juv = JuvenileCore.findCore(requestEvent.getJuvenileNum());
		if (juv != null)
		{
			juv.setSSN(requestEvent.getSsn());
			//Hot Fix Bug 90645 - reset the race if it was flipped when retrieving
			//Juvenile jcJuv = Juvenile.find(requestEvent.getJuvenileNum());//stripping fix to directly take from jcjuvenile.
			Juvenile jcJuv = Juvenile.findJCJuvenile(requestEvent.getJuvenileNum());
			if(jcJuv!=null && (jcJuv.getHispanic()!=null && jcJuv.getHispanic().equalsIgnoreCase("Y")))
			    juv.setRaceIdForHispanic("L");
		}
		else{
			ErrorResponseEvent errRespEvt=new ErrorResponseEvent();
			errRespEvt.setMessage("Error: Unable to find Juvenile Core record");
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(errRespEvt);
		}
	}

		/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub

	}
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}
}

