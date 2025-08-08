package pd.codetable.transactions;

import java.util.Iterator;

import pd.codetable.criminal.DrugTestResultCode;
import pd.codetable.criminal.DrugTypeCode;
import messaging.codetable.drug.reply.DrugTestResultCodeResponseEvent;
import messaging.codetable.drug.reply.DrugTypeCodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetDrugTestResultCodesCommand implements ICommand
{
    public void execute(IEvent event) throws Exception{
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator drugTestResultCodes = DrugTestResultCode.findAll();
	while (drugTestResultCodes.hasNext() ) {
	    DrugTestResultCode drugTestResultCode = (DrugTestResultCode) drugTestResultCodes.next();
	    DrugTestResultCodeResponseEvent response = DrugTestResultCodeResponseEvent.getResponseValues(drugTestResultCode);
	    dispatch.postEvent(response);
	}
    }

}
