/*
 * Created on Sept 19, 2017
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.family.transactions;

import naming.PDConstants;

import pd.juvenilecase.family.JuvenileFamilyMemSSNAccess;
import messaging.family.SaveFamilyMemSSNUserAccessEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author ugopinath
 *
 */
public class SaveFamilyMemSSNUserAccessCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		SaveFamilyMemSSNUserAccessEvent requestEvent = (SaveFamilyMemSSNUserAccessEvent) event;

		if (requestEvent.getSsn() == null || requestEvent.getSsn().equals(PDConstants.BLANK)){
			return;
		}
		
		JuvenileFamilyMemSSNAccess fam = new JuvenileFamilyMemSSNAccess();
		fam.setSsn(requestEvent.getSsn());
		fam.setJuvenileNum(requestEvent.getJuvenileNum());
		fam.setAccessedBy(requestEvent.getAccessedBy());
		fam.setFamilyMemID(requestEvent.getFamilyMemID());
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

