package pd.productionsupport.transactions;

import messaging.error.reply.ErrorResponseEvent;
import messaging.productionsupport.DeleteProductionSupportInterviewReportDocumentEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.juvenilecase.interviewinfo.InterviewDocument;

public class DeleteProductionSupportInterviewReportDocumentCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public DeleteProductionSupportInterviewReportDocumentCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
    	System.out.println("DeleteProductionSupportInterviewReportDocumentCommand");
    	DeleteProductionSupportInterviewReportDocumentEvent deleteInterviewReportDocumentEvent = (DeleteProductionSupportInterviewReportDocumentEvent) event;
    	InterviewDocument interviewDocument =  InterviewDocument.find(deleteInterviewReportDocumentEvent.getDocumentId());
    	try{
	    	if(interviewDocument != null) {
				interviewDocument.delete();
				new Home().bind(interviewDocument);
	    	}		
		}catch(Throwable t){
			System.out.println("Could not delete the Interview Report Document record");
		}
		// create an error if the record is not deleted
		interviewDocument =  InterviewDocument.find(deleteInterviewReportDocumentEvent.getDocumentId());
		if(interviewDocument != null){
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			ErrorResponseEvent error = new ErrorResponseEvent();
			error.setMessage("Interview Report Document record not deleted (JCIVIEWDOC).");
			dispatch.postEvent(error);
			return;
		}
    }

    /**
     * @param event
     * @roseuid 4526B083011E
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 4526B083012B
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 4526B083012D
     */
    public void update(Object anObject)
    {

    }

}
