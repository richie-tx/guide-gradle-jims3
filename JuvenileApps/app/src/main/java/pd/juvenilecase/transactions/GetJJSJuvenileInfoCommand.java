package pd.juvenilecase.transactions;

import java.util.Iterator;

import pd.juvenilecase.JJSJuvenile;
import messaging.juvenilecase.GetJJSJuvenileInfoEvent;
import messaging.juvenilecase.reply.JJSJuvenileInfoResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.util.MessageUtil;

public class GetJJSJuvenileInfoCommand implements ICommand{

	@Override
	public void execute(IEvent event) throws Exception {
		// TODO Auto-generated method stub
		GetJJSJuvenileInfoEvent request = (GetJJSJuvenileInfoEvent) event;
		
		JJSJuvenileInfoResponseEvent respose = new JJSJuvenileInfoResponseEvent();
		
		Iterator iter = JJSJuvenile.findAll(request);
		while ( iter.hasNext()){
			
			JJSJuvenile juvenile = (JJSJuvenile) iter.next();
			respose.setFirstName( juvenile.getFirstName());
			respose.setLastName( juvenile.getLastName());
			respose.setMiddleName( juvenile.getMiddleName());
			respose.setStatusId( juvenile.getStatusId());
			respose.setPropointLevelId( juvenile.getPropointLevelId());
			respose.setPropointUnitId( juvenile.getPropointUnitId());
			respose.setPropointJPOpId( juvenile.getPropointJPOpIdId());
			respose.setOldStatusId( juvenile.getOldStatusId());
			
			MessageUtil.postReply(respose);
		}
		
	}

}
