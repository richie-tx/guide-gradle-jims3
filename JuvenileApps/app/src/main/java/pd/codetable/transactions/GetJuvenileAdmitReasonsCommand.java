
/*
 * Created on May 15, 2014
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.codetable.transactions;

import java.util.Iterator;

import messaging.codetable.GetJuvenileAdmitReasonsEvent;
import messaging.codetable.GetJuvenileFacilitiesEvent;
import messaging.codetable.criminal.reply.JuvenileAdmitReasonsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.criminal.JuvenileAdmitReasons;
import pd.codetable.criminal.PDCriminalCodeTableHelper;

/**
 * @author sdaripa
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetJuvenileAdmitReasonsCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetJuvenileAdmitReasonsEvent evt = (GetJuvenileAdmitReasonsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		if(evt.getCode()!=null){
			JuvenileAdmitReasons reason = JuvenileAdmitReasons.find("code",evt.getCode());
			JuvenileAdmitReasonsResponseEvent response = PDCriminalCodeTableHelper.getJuvenileAdmitReasonsResponseEvent(reason);
			dispatch.postEvent(response);
		}
		else
		{
			IHome home = new Home();
			Iterator<JuvenileAdmitReasons> i = home.findAll(evt, JuvenileAdmitReasons.class);
		
			while (i.hasNext())
			{
				JuvenileAdmitReasons admitReasons = (JuvenileAdmitReasons) i.next();
				JuvenileAdmitReasonsResponseEvent response = PDCriminalCodeTableHelper.getJuvenileAdmitReasonsResponseEvent(admitReasons);
				dispatch.postEvent(response);
			}
		}
	}


	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
		// TODO Auto-generated method stub

	}

}
