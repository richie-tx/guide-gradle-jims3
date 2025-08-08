/*
 * Created on Dec 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.family.transactions;

import java.util.Iterator;

import messaging.family.GetJuvenileAgeAtfamilyMemberDeathEvent;
import messaging.juvenilecase.reply.JuvenileAgeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.family.JuvenileAgeInfo;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetJuvenileAgeAtfamilyMemberDeathCommand implements ICommand{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetJuvenileAgeAtfamilyMemberDeathEvent juvenileAgeRequestEvent = (GetJuvenileAgeAtfamilyMemberDeathEvent)event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		if(juvenileAgeRequestEvent.getAction().equalsIgnoreCase("retrieve")){
		//JuvenileAgeInfo juvAgeInfo = JuvenileAgeInfo.find(juvenileAgeRequestEvent.getFamilyMemberId());
		Iterator iterator =	JuvenileAgeInfo.findAll(juvenileAgeRequestEvent);
		JuvenileAgeResponseEvent reply = new JuvenileAgeResponseEvent();
		while(iterator.hasNext()){
			JuvenileAgeInfo juvAgeInfo = (JuvenileAgeInfo)iterator.next();
			reply.setJuvenileNum(juvAgeInfo.getJuvenileNum());
			reply.setJuvenileAgeAtDeath(juvAgeInfo.getJuvenileAge());
			reply.setMemberId(juvAgeInfo.getFamilyMemberId());

		}
		dispatch.postEvent(reply);
		}
		else if(juvenileAgeRequestEvent.getAction().equalsIgnoreCase("update")){
			JuvenileAgeResponseEvent reply = new JuvenileAgeResponseEvent();
			JuvenileAgeInfo juvAgeInfo = null;
			IHome home = new Home();
			juvAgeInfo = JuvenileAgeInfo.find(juvenileAgeRequestEvent.getFamilyMemberId());
			if(juvAgeInfo == null){
				juvAgeInfo = new JuvenileAgeInfo();
				juvAgeInfo.setJuvenileNum(juvenileAgeRequestEvent.getJuvenileNum());
				juvAgeInfo.setJuvenileAge(juvenileAgeRequestEvent.getJuvenileAge());
				juvAgeInfo.setFamilyMemberId(juvenileAgeRequestEvent.getFamilyMemberId());
				home.bind(juvAgeInfo);
			}
			else{
			juvAgeInfo.setJuvenileAge(juvenileAgeRequestEvent.getJuvenileAge());
			}			
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
