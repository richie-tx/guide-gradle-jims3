package pd.juvenilecase.casefile.transactions;

import java.util.ArrayList;
import java.util.Collections;

import messaging.casefile.GetJournalDocsEvent;
import messaging.casefile.reply.CasefileDocumentsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.casefile.PDCasefileDocumentsHelper;


public class GetJournalDocsCommand implements ICommand  {
	
	public GetJournalDocsCommand() 
	{
	}

	/**
	 * @param event
	 */
	public void execute(IEvent event) 
	{
		GetJournalDocsEvent casefile = (GetJournalDocsEvent)event;
		String casefileId = casefile.getCasefileId();
		IDispatch dispatchReply = EventManager.getSharedInstance(EventManager.REPLY);
		ArrayList documents = new ArrayList<CasefileDocumentsResponseEvent>();
		
		// journal reports 
		documents.addAll(PDCasefileDocumentsHelper.getJournalReports(casefileId));
 
		// Sort the documents based on the creation date
		Collections.sort(documents);
		// Finally send all the reports
		for(Object obj:documents){
			CasefileDocumentsResponseEvent doc = (CasefileDocumentsResponseEvent)obj;
			dispatchReply.postEvent(doc);
		}
	}

	/**
	 * @param event
	 */
	public void onRegister(IEvent event) 
	{

	}

	/**
	 * @param event
	 */
	public void onUnregister(IEvent event) 
	{

	}

	/**
	 * @param anObject
	 */
	public void update(Object anObject) 
	{

	}


}
