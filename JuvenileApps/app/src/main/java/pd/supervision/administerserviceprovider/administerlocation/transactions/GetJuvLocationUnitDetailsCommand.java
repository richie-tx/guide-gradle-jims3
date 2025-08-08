/*
 * Created on Jun 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.administerlocation.transactions;

import messaging.administerlocation.GetJuvLocationUnitDetailsEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetJuvLocationUnitDetailsCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetJuvLocationUnitDetailsEvent reqEvent =(GetJuvLocationUnitDetailsEvent)event;
		JuvLocationUnit juvLocUnitDetails = (JuvLocationUnit)JuvLocationUnit.find(reqEvent.getJuvLocUnitId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		LocationResponseEvent juvLocResponseEvent = new LocationResponseEvent();  
		juvLocResponseEvent.setLocationUnitName(juvLocUnitDetails.getLocationUnitName());
		juvLocResponseEvent.setJuvUnitCd(juvLocUnitDetails.getJuvUnitCd());
		juvLocResponseEvent.setLocationId(juvLocUnitDetails.getLocationId());
		juvLocResponseEvent.setJuvLocationUnitId(juvLocUnitDetails.getJuvLocUnitId());
		juvLocResponseEvent.setStatusId(juvLocUnitDetails.getUnitStatusId());
		//juvLocResponseEvent.setStatus(SimpleCodeTableHelper.getDescrByCode("LOCATION_STATUS" , juvLocResponseEvent.getStatusId()));
		juvLocResponseEvent.setPhoneNumber(juvLocUnitDetails.getPhoneNumber());
		juvLocResponseEvent.setMaysiFlg(juvLocUnitDetails.getMaysiFlag());
		juvLocResponseEvent.setDrugFlag(juvLocUnitDetails.getDrugFlag());
		juvLocResponseEvent.setInterviewCalFlag(juvLocUnitDetails.getInterviewCalFlag());
		juvLocResponseEvent.setOfficerProfileFlag(juvLocUnitDetails.getOfficerProfileFlag());
		dispatch.postEvent(juvLocResponseEvent);
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
