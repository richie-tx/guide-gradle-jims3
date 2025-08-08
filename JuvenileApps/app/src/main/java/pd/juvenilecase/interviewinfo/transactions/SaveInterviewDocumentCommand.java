/*
 * Created on Oct 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.interviewinfo.transactions;

import messaging.interviewinfo.SaveInterviewDocumentEvent;
import messaging.interviewinfo.reply.InterviewReportResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.interviewinfo.InterviewDocument;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveInterviewDocumentCommand implements ICommand {

	public void execute(IEvent event) 
	{
		
		SaveInterviewDocumentEvent saveEvt = 
   			(SaveInterviewDocumentEvent)event;
   		InterviewDocument document = new InterviewDocument();
		document.setDocument( saveEvt.getDocument() );
		document.setCasefileId( saveEvt.getCasefileId() );
		document.setDocumentTypeCodeId( saveEvt.getDocumentTypeCodeId()); 
		IHome home=new Home();
   		home.bind(document);
   		InterviewReportResponseEvent myRespEvt=new InterviewReportResponseEvent();
   		myRespEvt.setCasefileId(document.getCasefileId());
   		myRespEvt.setReportId(document.getOID().toString());   		
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		dispatch.postEvent(myRespEvt);
	}

	
   /**
    * @param event
    * @roseuid 448D7EEE03B4
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 448D7EEE03BB
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 448D7EEE03BD
    */
   public void update(Object anObject) 
   {
    
   }
}
