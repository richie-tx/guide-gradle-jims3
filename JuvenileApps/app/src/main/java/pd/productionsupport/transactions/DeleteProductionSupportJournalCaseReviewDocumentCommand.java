package pd.productionsupport.transactions;

import messaging.error.reply.ErrorResponseEvent;
import messaging.productionsupport.DeleteProductionSupportJournalCaseReviewDocumentEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.juvenilecase.casefile.JournalDocMetadata;

public class DeleteProductionSupportJournalCaseReviewDocumentCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public DeleteProductionSupportJournalCaseReviewDocumentCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
    	System.out.println("DeleteProductionSupportJournalCaseReviewDocumentCommand");
    	DeleteProductionSupportJournalCaseReviewDocumentEvent deleteCaseReviewDocumentEvent = (DeleteProductionSupportJournalCaseReviewDocumentEvent) event;
    	JournalDocMetadata journalDocMetadata =  JournalDocMetadata.find(deleteCaseReviewDocumentEvent.getDocumentId());
    	try{
	    	if(journalDocMetadata != null) {
	    		journalDocMetadata.delete();
				new Home().bind(journalDocMetadata);
	    	}		
		}catch(Throwable t){
			System.out.println("Could not delete the Case Review Report Document record");
		}
		// create an error if the record is not deleted
		journalDocMetadata =  JournalDocMetadata.find(deleteCaseReviewDocumentEvent.getDocumentId());
		if(journalDocMetadata != null){
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			ErrorResponseEvent error = new ErrorResponseEvent();
			error.setMessage("Case Review Report Document record not deleted (JCJOURNALRPRTDOC).");
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
