package pd.codetable.transactions;

import java.util.Iterator;

import pd.codetable.criminal.JuvenileOffenseCode;
import ui.common.StringUtil;
import messaging.codetable.GetAllJuvenileOffenseCodesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetAllJuvenileOffenseCodesCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	JuvenileCasefileOffenseCodeResponseEvent codeResponse = null;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetAllJuvenileOffenseCodesEvent request = (GetAllJuvenileOffenseCodesEvent) event;
	
	Iterator iter = JuvenileOffenseCode.findAll();
	while(iter.hasNext()){
	    
	    JuvenileOffenseCode offense = (JuvenileOffenseCode) iter.next();
	    //include all inactives - US 148479
	    //if( "Y".equalsIgnoreCase( offense.getInactiveInd()) || "Y".equalsIgnoreCase( offense.getDiscontCode() )){
		
		//System.out.println(offense.getCode());
	    //}else{
		
		codeResponse = offense.valueObject();
		dispatch.postEvent(codeResponse);
	    //}
	}
	
    }
 
}
