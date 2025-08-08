/*
 * Created on Oct 3, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetJuvenileDetentionFacilitiesByJuvenileIdEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JJSFacility;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetJuvenileDetentionFacilitiesByJuvenileIdCommand  implements ICommand{
	
	public GetJuvenileDetentionFacilitiesByJuvenileIdCommand() {}

	public void execute(IEvent event)
	{
		GetJuvenileDetentionFacilitiesByJuvenileIdEvent pet = (GetJuvenileDetentionFacilitiesByJuvenileIdEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);	
		Iterator i = JJSFacility.findAll(pet);
		while (i.hasNext())
		{
			JJSFacility fac = (JJSFacility) i.next();
			JuvenileDetentionFacilitiesResponseEvent resp = new JuvenileDetentionFacilitiesResponseEvent();
			resp.setAdmitDate(fac.getAdmitDate());
			resp.setReleaseDate(fac.getReleaseDate());
			resp.setAdmitTime(fac.getAdmitTime());
			resp.setReleaseTime(fac.getReleaseTime());
			resp.setDetainedFacility( fac.getDetainedFacility());
			resp.setAdmitReason(fac.getAdmitReason());
			resp.setOriginalAdmitDate(fac.getOriginalAdmitDate());
			dispatch.postEvent(resp);
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
