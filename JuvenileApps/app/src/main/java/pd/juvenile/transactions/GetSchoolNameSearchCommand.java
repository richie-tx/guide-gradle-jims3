/*
 * Created on Aug 8, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenile.transactions;

import java.util.Iterator;

import messaging.codetable.person.reply.JuvenileSchoolDistrictCodeResponseEvent;
import messaging.juvenile.GetSchoolNameSearchEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.person.JuvenileSchoolDistrictCode;
import pd.codetable.person.PDPersonCodeTableHelper;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

/*
 * Command written to fetch the School Names along with their corresponding
 * Districts for a given School Name
 */

public class GetSchoolNameSearchCommand implements ICommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetSchoolNameSearchEvent searchEvent = (GetSchoolNameSearchEvent) event;
		IHome home = new Home();
		Iterator attItr = home.findAll(searchEvent, JuvenileSchoolDistrictCode.class);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while (attItr.hasNext()) {
			JuvenileSchoolDistrictCode jsdc = (JuvenileSchoolDistrictCode) attItr.next();
			if (!"Y".equalsIgnoreCase( jsdc.getInactiveInd())) {
				JuvenileSchoolDistrictCodeResponseEvent replyEvent = PDPersonCodeTableHelper.getJuvenileSchoolDistrictCodeResponseEvent(jsdc);
				dispatch.postEvent(replyEvent);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}

}
