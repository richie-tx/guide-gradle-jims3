/*
 * Created on Jul 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.family.transactions;

import java.util.Collection;
import java.util.Iterator;

import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.JuvenileFamilyHelper;
import pd.juvenilecase.family.JuvenileFamilyMemberView;
import messaging.family.AddFamilyMemberEvent;
import messaging.family.GetAllJuvFamilyMembersEvent;
import messaging.family.SaveFamilyMemberEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.MessageUtil;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetAllJuvFamilyMembersCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetAllJuvFamilyMembersEvent myEvt = (GetAllJuvFamilyMembersEvent) event;
		Iterator iter=JuvenileFamilyMemberView.findAll(myEvt);
		if(iter!=null && iter.hasNext())
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			while(iter.hasNext()){
				JuvenileFamilyMemberView myMember = (JuvenileFamilyMemberView)iter.next();
				dispatch.postEvent(myMember.getResponseEvent());
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
