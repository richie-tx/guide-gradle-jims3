package pd.productionsupport.transactions;

import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.NoJuvenileCasefilesResponseEvent;
import messaging.productionsupport.DeleteProductionSupportCasefileNonComplianceNoticeEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.juvenilecase.casefile.CasefileNonComplianceNotice;

public class DeleteProductionSupportCasefileNonComplianceNoticeCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public DeleteProductionSupportCasefileNonComplianceNoticeCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
    	System.out.println("DeleteProductionSupportCasefileNonComplianceNoticeCommand");
    	DeleteProductionSupportCasefileNonComplianceNoticeEvent deleteNonComplianceNoticeEvent = (DeleteProductionSupportCasefileNonComplianceNoticeEvent) event;
    	CasefileNonComplianceNotice casefileNonComplianceNotice =  CasefileNonComplianceNotice.find(deleteNonComplianceNoticeEvent.getDocumentId());
    	try{
	    	if(casefileNonComplianceNotice != null) {
	    		casefileNonComplianceNotice.delete();
				new Home().bind(casefileNonComplianceNotice);
	    	}		
		}catch(Throwable t){
			System.out.println("Could not delete the Casefile Non Compliance Notification record");
		}
		// create an error if the record is not deleted
		casefileNonComplianceNotice =  CasefileNonComplianceNotice.find(deleteNonComplianceNoticeEvent.getDocumentId());
		if(casefileNonComplianceNotice != null){
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			ErrorResponseEvent error = new ErrorResponseEvent();
			error.setMessage("Casefile Non Compliance Notification record not deleted (JCCASENONCOMNOTE).");
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
