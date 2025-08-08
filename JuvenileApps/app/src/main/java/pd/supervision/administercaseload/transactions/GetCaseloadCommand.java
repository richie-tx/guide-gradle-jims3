package pd.supervision.administercaseload.transactions;

import java.util.List;

import messaging.administercaseload.GetCaseloadEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.supervision.administercaseload.SearchCaseloadUtil;

public class GetCaseloadCommand extends SearchCaseloadUtil implements ICommand
{
    /**
     * @roseuid 464DAC3F0391
     */
    public GetCaseloadCommand()
    {

    }

    /**
     * @param event
     * @roseuid 464DA69202EA
     */
    public void execute(IEvent anEvent)
    {
        GetCaseloadEvent event = (GetCaseloadEvent) anEvent;        

        List results = SearchCaseloadUtil.getCaseload(event);
        sendBySupervisee(results, true);
    }

	/**
     * @param event
     * @roseuid 464DA69202F9
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 464DA69202FB
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 464DA6920308
     */
    public void update(Object anObject)
    {

    }

}
