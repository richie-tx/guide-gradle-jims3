/*
 * Created on Jul 15, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.codetable.transactions;

import java.util.Iterator;

import naming.PDCodeTableConstants;

import pd.codetable.person.RaceEthnicityCode;
import messaging.codetable.GetRaceEthnicityCodesEvent;
import messaging.codetable.GetScarsMarksTattoosCodesEvent;
import messaging.codetable.person.reply.RaceEthnicityCodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;




/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetRaceEthnicityCodesCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator jjsRace = RaceEthnicityCode.findAll((GetRaceEthnicityCodesEvent) event);
		while (jjsRace.hasNext())
		{
			RaceEthnicityCodeResponseEvent jjsRaceReply = new RaceEthnicityCodeResponseEvent();
			RaceEthnicityCode jrf = (RaceEthnicityCode) jjsRace.next();			
			jjsRaceReply.setJjsRaceCode(jrf.getJjsRaceCode());
			jjsRaceReply.setNcicRaceCode(jrf.getNcicRaceCode());
			jjsRaceReply.setNcicEthnicity(jrf.getNcicEthnicity());
			jjsRaceReply.setDescription(jrf.getDescription());
			jjsRaceReply.setTopic(PDCodeTableConstants.JJSRACE);
			
			dispatch.postEvent(jjsRaceReply);
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
