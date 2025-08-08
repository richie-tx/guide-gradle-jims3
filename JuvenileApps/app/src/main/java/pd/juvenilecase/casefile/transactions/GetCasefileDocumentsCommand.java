package pd.juvenilecase.casefile.transactions;

import java.util.ArrayList;
import java.util.Collections;

import messaging.casefile.GetCasefileDocumentsEvent;
import messaging.casefile.reply.CasefileDocumentsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.casefile.PDCasefileDocumentsHelper;


public class GetCasefileDocumentsCommand implements ICommand  {
	
	public GetCasefileDocumentsCommand() 
	{
	}

	/**
	 * @param event
	 */
	public void execute(IEvent event) 
	{
		GetCasefileDocumentsEvent casefile = (GetCasefileDocumentsEvent)event;
		String casefileId = casefile.getCasefileId();
		IDispatch dispatchReply = EventManager.getSharedInstance(EventManager.REPLY);
		ArrayList documents = new ArrayList<CasefileDocumentsResponseEvent>();
		
		//Interview-related Forms � Parental Written request, Attorney Appointment, and 
		//the Social History Reports (Universal and Generic Court Report)
		documents.addAll(PDCasefileDocumentsHelper.getInterviewRelatedReports(casefileId));
		
		//Common App && Exit Plan � {MJC_Exit Plan 2004 (community) and MJC_Placement Exit Plan}
		documents.addAll(PDCasefileDocumentsHelper.getCommonAppReports(casefileId));
		
		//Case plan
		documents.addAll(PDCasefileDocumentsHelper.getCaseplanReports(casefileId));
		
		//Case plan Review 
		documents.addAll(PDCasefileDocumentsHelper.getCaseplanJPOReviewReports(casefileId));
		
		// journal reports 
		documents.addAll(PDCasefileDocumentsHelper.getJournalReports(casefileId));
		
		// facility reports 
		documents.addAll(PDCasefileDocumentsHelper.getFacilityReports(casefileId));
       
		//Closing Letters are not persisted 
		//Client Satisfaction Survey is not persisted
		//Vendor Survey is not persisted
 
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
