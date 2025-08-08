package pd.juvenile.transactions;

import java.util.Calendar;
import java.util.Iterator;

import messaging.juvenile.reply.TrackingControlNumberResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.util.DateUtil;
import pd.juvenile.TrackingControlNumber;
import pd.security.PDSecurityHelper;

/**
 * 
 * @author sthyagarajan
 *
 */
public class UpdateTrackingControlNumberCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator<TrackingControlNumber> trackingControlNumberEvt = TrackingControlNumber.findAll();
	
	Home home= new Home();

	if (trackingControlNumberEvt.hasNext())
	{
	    TrackingControlNumberResponseEvent replyEvent = new TrackingControlNumberResponseEvent();
	    TrackingControlNumber trackingControlNumber = trackingControlNumberEvt.next();

	    //update next tracking control number.
	    if (trackingControlNumber != null && trackingControlNumber.getNextTrackingNum()!=null)
	    {
		//get the next tracking number //84071
		replyEvent.setNextTrackingNumberControl(trackingControlNumber.getNextTrackingNum());
		
		//Then increment and update the table. //84071
		String trackingControlDigits = trackingControlNumber.getNextTrackingNum().substring(2, trackingControlNumber.getNextTrackingNum().length());
		int trackingCtrlNum = Integer.parseInt(trackingControlDigits) + 1;
		trackingControlNumber.setNextTrackingNum("V0" + String.valueOf(trackingCtrlNum)); //increment the tracking control number by One.
		trackingControlNumber.setLcDate(DateUtil.getCurrentDate());
		trackingControlNumber.setLcTime(Calendar.getInstance().getTime());
		trackingControlNumber.setLcUser(PDSecurityHelper.getLogonId());
		home.bind(trackingControlNumber);
	    }
	    dispatch.postEvent(replyEvent);
	}
    }
}
