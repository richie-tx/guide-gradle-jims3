/*
 * Created on Jul 12, 2007
 */
package pd.supervision.supervisionstaff.cscdstaffposition.transactions;

import java.util.Iterator;

import messaging.cscdstaffposition.GetCourtStaffPositionEvent;
import messaging.supervision.reply.SupervisionStaffResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;
import pd.supervision.supervisionstaff.cscdstaffposition.CourtStaffPosition;

/**
 * @author cc_rbhat
 */
public class GetCourtStaffPositionCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {

	    GetCourtStaffPositionEvent requestEvent = (GetCourtStaffPositionEvent) event;
	    
		Iterator staffPositionIter = CSCDStaffPositionHelper.getCourtStaffPositions(requestEvent);
		
		if (staffPositionIter != null) {
			while (staffPositionIter.hasNext()) {
				CourtStaffPosition staff = (CourtStaffPosition) staffPositionIter.next();
				SupervisionStaffResponseEvent responseEvent = staff.valueObject();
				MessageUtil.postReply(responseEvent);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
	}

}
