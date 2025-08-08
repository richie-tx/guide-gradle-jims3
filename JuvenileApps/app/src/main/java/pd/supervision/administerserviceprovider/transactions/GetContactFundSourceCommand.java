package pd.supervision.administerserviceprovider.transactions;

import java.util.Iterator;

import pd.supervision.administerserviceprovider.ContactSourceFund;
import ui.common.CodeHelper;
import messaging.administerserviceprovider.GetContactFundSourceEvent;
import messaging.administerserviceprovider.reply.ContactFundSourceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetContactFundSourceCommand implements ICommand
{
    public GetContactFundSourceCommand(){}
    
    public void execute(IEvent event){
	//System.out.println("getContactFundSourceCommand");
	
	GetContactFundSourceEvent getEvent = (GetContactFundSourceEvent) event;
	
	Iterator contactSourceFundIter = ContactSourceFund.findAll("profileId", getEvent.getProfileId());
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	
	while (contactSourceFundIter.hasNext()) {
	    ContactFundSourceResponseEvent response = new ContactFundSourceResponseEvent();
	    ContactSourceFund sourceFund = (ContactSourceFund) contactSourceFundIter.next();
	    response.setEntryDate(sourceFund.getFundEntryDate());
	    response.setStartDate(sourceFund.getFundStartDate());
	    response.setEndDate(sourceFund.getFundEndDate());
	    response.setSourceFund(sourceFund.getFundSource());
	    if ( response.getSourceFund() != null
		    && response.getSourceFund().length() > 0 ) {
		response.setSourceFundDescription(CodeHelper.getCodeDescription("JUVENILE_SOURCE_FUND", response.getSourceFund()));
	    }
	    response.setStatus(sourceFund.getFundStatus());
	    dispatch.postEvent(response);
	}
	
	
    }
    
}
