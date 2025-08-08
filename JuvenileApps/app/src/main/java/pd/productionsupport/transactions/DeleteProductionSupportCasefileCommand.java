package pd.productionsupport.transactions;

import java.util.Date;

import messaging.error.reply.ErrorResponseEvent;
import messaging.productionsupport.DeleteProductionSupportCasefileEvent;
import messaging.productionsupport.reply.ProductionSupportCalendarResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.transaction.multidatasource.TransactionManager;
import pd.common.calendar.CalendarEvent;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.casefile.CasefileNonComplianceNotice;

public class DeleteProductionSupportCasefileCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public DeleteProductionSupportCasefileCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
    	System.out.println("DeleteProductionSupportCasefileCommand");
    	DeleteProductionSupportCasefileEvent deleteCasefileEvent = (DeleteProductionSupportCasefileEvent) event;
    	JuvenileCasefile casefile = JuvenileCasefile.find(deleteCasefileEvent.getCasefileId());
		try{
	    	if(deleteCasefileEvent.getCasefileId() != null && !(deleteCasefileEvent.getCasefileId().equals("")) && casefile != null) {
				casefile.delete();
				new Home().bind(casefile);
			} 
		}catch(Throwable t){
			System.out.println("Could not delete the Casefile record:" + deleteCasefileEvent.getCasefileId());
			t.printStackTrace();
			TransactionManager.getInstance().rollback();
		}
		// create an error if the record is not deleted
		casefile =  JuvenileCasefile.find(deleteCasefileEvent.getCasefileId());
		if(casefile != null){
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			ErrorResponseEvent error = new ErrorResponseEvent();
			error.setMessage("Casefile record not deleted (JCCASEFILE) with casefileId: " + deleteCasefileEvent.getCasefileId());
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
