package pd.juvenile.transactions;

import pd.juvenile.SsnViewLog;
import ui.security.SecurityUIHelper;
import messaging.juvenile.CreateJuvenileSsnViewLogEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;

public class CreateJuvenileSsnViewLogCommand implements ICommand
{
    public CreateJuvenileSsnViewLogCommand(){}
    
    public void execute(IEvent event) {
	CreateJuvenileSsnViewLogEvent createLogEvent = (CreateJuvenileSsnViewLogEvent)event;
	Home home = new Home();
	IUserInfo user = SecurityUIHelper.getUser( ) ;
	SsnViewLog viewLog = new SsnViewLog();
	System.out.println("Create ssn view log" + createLogEvent.getJuvenileNum() );
	if ( createLogEvent.getJuvenileNum() != null) {
	    viewLog.setJuvenileNum(createLogEvent.getJuvenileNum());
	    viewLog.setViewSsnUser(user.getJIMSLogonId());
	    viewLog.setCreateDate(DateUtil.getCurrentDate());
	    viewLog.setCreateUserID(user.getJIMSLogonId());
	    home.bind(viewLog);
	}
	
	
    }

}
