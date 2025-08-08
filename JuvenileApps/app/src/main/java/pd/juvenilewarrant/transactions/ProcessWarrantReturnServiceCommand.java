package pd.juvenilewarrant.transactions;

import java.util.Date;

import naming.PDJuvenileWarrantConstants;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantLightBuilder;
import pd.juvenilewarrant.JuvenileWarrantService;
import pd.juvenilewarrant.helper.JuvenileWarrantWorker;
import messaging.juvenilewarrant.ProcessWarrantReturnServiceEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.ResponseEvent;

public class ProcessWarrantReturnServiceCommand implements ICommand
{

    /**
     * @roseuid 41FFDAC702EE
     */
    public ProcessWarrantReturnServiceCommand()
    {
    }

    /**
     * @param event
     * @roseuid 41F950A301E6
     */
    public void execute(IEvent event)
    {
        ProcessWarrantReturnServiceEvent requestEvent = (ProcessWarrantReturnServiceEvent) event;

        String warrantNum = requestEvent.getWarrantNum();
        JuvenileWarrant warrant = JuvenileWarrant.find(warrantNum);

        String warrantType = warrant.getWarrantTypeId();

        if (PDJuvenileWarrantConstants.WARRANT_TYPE_PCW.equals(warrantType)
                || PDJuvenileWarrantConstants.WARRANT_TYPE_VOP.equals(warrantType))
        {
            warrant.setServiceReturnSignatureStatusId(PDJuvenileWarrantConstants.SERVICE_RETURN_SIGN_STATUS_FILED);
            warrant.setServiceReturnGeneratedStatusId(PDJuvenileWarrantConstants.SERVICE_RETURN_GEN_STATUS_PRINTED);
        }
        else if (PDJuvenileWarrantConstants.WARRANT_TYPE_ARR.equals(warrantType)
                || PDJuvenileWarrantConstants.WARRANT_TYPE_DTA.equals(warrantType)
                || PDJuvenileWarrantConstants.WARRANT_TYPE_OIC.equals(warrantType))
        {
            warrant.setServiceReturnGeneratedStatusId(PDJuvenileWarrantConstants.SERVICE_RETURN_GEN_STATUS_PRINTED);

            JuvenileWarrantService service = JuvenileWarrantService.findSuccessfulService(warrant.getWarrantNum());

            Date startDate = null;

            if (service.getExpirationTimeStamp() == null)
            {
                startDate = service.getServiceTimeStamp();
            }
            else
            {
                startDate = service.getExpirationTimeStamp();
            }

            JuvenileWarrantWorker worker = new JuvenileWarrantWorker();
            worker.sendReminderSignReturnOfService(warrant, service, startDate);
            worker.sendExpiredSignReturnServiceSignatureEvent(warrant, service, startDate);            
        }

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        JuvenileWarrantLightBuilder builder = new JuvenileWarrantLightBuilder();        
        builder.setWarrant(warrant);
        builder.build();
        builder.setWarrantServiceStatuses();
        ResponseEvent response = (ResponseEvent) builder.getResult();       

        dispatch.postEvent(response);
    }

    /**
     * @param event
     * @roseuid 41F950A301E8
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 41F950A301F4
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 41FFDAC702FD
     */
    public void update(Object updateObject)
    {

    }
}
