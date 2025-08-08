package pd.juvenile.transactions;

import java.util.Calendar;
import java.util.Iterator;

import messaging.juvenile.reply.JuvenileNumControlResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.util.DateUtil;
import pd.juvenile.JuvenileNumControl;
import pd.security.PDSecurityHelper;

/**
 * @author sthyagarajan
 */
public class UpdateJuvenileNumControlCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator<JuvenileNumControl> juvenileNumCtrlItr = JuvenileNumControl.findAll();
	Home home = new Home();
	
	if (juvenileNumCtrlItr.hasNext())
	{
	    JuvenileNumControlResponseEvent replyEvent = new JuvenileNumControlResponseEvent();
	    JuvenileNumControl juvenileNumControl = (JuvenileNumControl) juvenileNumCtrlItr.next();
	   
	    if(juvenileNumControl!=null)
	    {
		int juvenileNum = juvenileNumControl.getLastJuvenileNum() + 1;
		juvenileNumControl.setLastJuvenileNum(juvenileNum); //increment the tracking control number by One.
	    }
	    juvenileNumControl.setLcDate(DateUtil.getCurrentDate());
	    juvenileNumControl.setLcTime(Calendar.getInstance().getTime());
	    juvenileNumControl.setLcUser(PDSecurityHelper.getLogonId());
	    home.bind(juvenileNumControl);
	    replyEvent.setLastJuvenileNum(String.valueOf(juvenileNumControl.getLastJuvenileNum()));
	    dispatch.postEvent(replyEvent);
	}
    }
}
