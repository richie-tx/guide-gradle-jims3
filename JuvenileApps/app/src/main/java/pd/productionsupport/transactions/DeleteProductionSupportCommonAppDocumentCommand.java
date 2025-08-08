package pd.productionsupport.transactions;

import messaging.error.reply.ErrorResponseEvent;
import messaging.productionsupport.DeleteProductionSupportCommonAppDocumentEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.juvenilecase.casefile.CommonAppDocMetadata;

public class DeleteProductionSupportCommonAppDocumentCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public DeleteProductionSupportCommonAppDocumentCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
    	System.out.println("DeleteProductionSupportCommonAppDocumentCommand");
    	DeleteProductionSupportCommonAppDocumentEvent deleteCommonAppDocumentEvent = (DeleteProductionSupportCommonAppDocumentEvent) event;
    	CommonAppDocMetadata commonAppDocMetadata =  CommonAppDocMetadata.find(deleteCommonAppDocumentEvent.getDocumentId());
    	try{
	    	if(commonAppDocMetadata != null) {
	    		commonAppDocMetadata.delete();
				new Home().bind(commonAppDocMetadata);
	    	}		
		}catch(Throwable t){
			System.out.println("Could not delete the Common App Document record");
		}
		// create an error if the record is not deleted
		commonAppDocMetadata =  CommonAppDocMetadata.find(deleteCommonAppDocumentEvent.getDocumentId());
		if(commonAppDocMetadata != null){
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			ErrorResponseEvent error = new ErrorResponseEvent();
			error.setMessage("Common App Document record not deleted (JCIVIEWDOC).");
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
