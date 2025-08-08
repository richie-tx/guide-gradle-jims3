package pd.codetable.transactions;

import java.util.Iterator;

import pd.codetable.criminal.DrugTypeCode;

import messaging.codetable.drug.reply.DrugTypeCodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetDrugTypeCodesCommand implements ICommand
{
    public void execute(IEvent event) throws Exception{
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator drugTypeCodes = DrugTypeCode.findAll();
	while (drugTypeCodes.hasNext() ) {
	    DrugTypeCode drugTypeCode = (DrugTypeCode) drugTypeCodes.next();
	    DrugTypeCodeResponseEvent response = DrugTypeCodeResponseEvent.getResponseValues(drugTypeCode);
	    dispatch.postEvent(response);
	}
	
    }
	
}
