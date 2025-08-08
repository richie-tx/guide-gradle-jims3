package pd.productionsupport.transactions;

import java.util.Iterator;

import messaging.productionsupport.DeleteProductionSupportCasefileClosingEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.casefile.CasefileClosingInfo;

public class DeleteProductionSupportCasefileClosingCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public DeleteProductionSupportCasefileClosingCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
    	System.out.println("DeleteProductionSupportCasefileClosingCommand");
    	DeleteProductionSupportCasefileClosingEvent deleteCasefileClosingEvent = (DeleteProductionSupportCasefileClosingEvent) event;
    	Iterator<CasefileClosingInfo> casefileClosingIter =  CasefileClosingInfo.findAll("supervisionNumber",deleteCasefileClosingEvent.getCasefileId());
    	CasefileClosingInfo casefileClosing = null;
    	while(casefileClosingIter.hasNext()){
			casefileClosing = (CasefileClosingInfo)casefileClosingIter.next();
			break;
		}
		if(deleteCasefileClosingEvent.getCasefileId() != null && !(deleteCasefileClosingEvent.getCasefileId().equals("")) && casefileClosing != null) {
			casefileClosing.delete();
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
