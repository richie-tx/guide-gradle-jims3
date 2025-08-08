package pd.juvenilewarrant.transactions;

import java.util.ArrayList;
import java.util.List;

import pd.juvenilewarrant.JuvenileWarrantServiceOfficer;
import pd.juvenilewarrant.ProcessReturnOfServiceBuilder;

import messaging.info.reply.CountInfoMessage;
import messaging.juvenilewarrant.GetJuvenileWarrantsForProcessServiceEvent;
import messaging.juvenilewarrant.reply.ProcessReturnOfServiceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;

/**
 * 
 * @author Jim Fisher
 * 
 * Command to get Juvenile Warrants for the Process Of Service functionality
 */
public class GetJuvenileWarrantsForProcessServiceCommand implements ICommand
{

    /**
     * @roseuid 41FFDABE038A
     */
    public GetJuvenileWarrantsForProcessServiceCommand()
    {

    }

    /**
     * @param event
     * @roseuid 41FFC5E700BD
     */
    public void execute(IEvent event)
    {
        GetJuvenileWarrantsForProcessServiceEvent warEvent = (GetJuvenileWarrantsForProcessServiceEvent) event;
        String warrantNum = warEvent.getWarrantNum();

        List results = new ArrayList();

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        if (warrantNum.length() < 10)
        {
	        if (warrantNum != null && warrantNum.trim().length() > 0)
	        {
	            JuvenileWarrantServiceOfficer jwso = JuvenileWarrantServiceOfficer.findByWarrantNum(warrantNum);
	            if (jwso != null)
	            {
	                results.add(jwso);
	            }
	        }
	        else
	        {
	                results = JuvenileWarrantServiceOfficer.findByJuvenileName(warEvent);
	        }
        }

        // find out which warrants are truly read for process return of service
        // (based on business rules)
        results = this.processResults(results);

        if (results.size() == 1)
        {
            JuvenileWarrantServiceOfficer jwso = (JuvenileWarrantServiceOfficer) results.get(0);
            this.sendSingle(jwso);
        }
        else if (results.size() > 1)
        {
            MetaDataResponseEvent metaData = (MetaDataResponseEvent) JuvenileWarrantServiceOfficer.findMeta(event);
            if(metaData.getCount()> 2000)
            {
                CountInfoMessage infoEvent = new CountInfoMessage();
                infoEvent.setMessage("Record count exeeded - total records found = " + metaData.getCount());
                infoEvent.setCount(metaData.getCount());
                dispatch.postEvent(infoEvent);
            }
            else
            {
                this.sendMultiple(results);
            }
        }
    }

    /**
     * @param warrant
     */
    private void sendSingle(JuvenileWarrantServiceOfficer jwso)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        if (jwso.isReadyForProcessReturnOfService())
        {
            ProcessReturnOfServiceBuilder builder = new ProcessReturnOfServiceBuilder(false);
            builder.setWarrantServiceOfficer(jwso);
            builder.build();
            ProcessReturnOfServiceResponseEvent response = (ProcessReturnOfServiceResponseEvent) builder.getResult();
            dispatch.postEvent(response);
        }
        else
        {
            dispatch.postEvent(jwso.getInvalidWarrantStageErrorEvent());
        }
    }

    private List processResults(List results)
    {
        int len = results.size();
        List thinResults = new ArrayList(len);

        if (len == 1)
        {
            thinResults.add(results.get(0));
        }
        else
        {
            for (int i = 0; i < len; i++)
            {
                JuvenileWarrantServiceOfficer jwso = (JuvenileWarrantServiceOfficer) results.get(i);
                if (jwso.isReadyForProcessReturnOfService())
                {
                    thinResults.add(jwso);
                }
            }
        }
        return thinResults;
    }

    /**
     * @param results
     */
    private void sendMultiple(List results)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        ProcessReturnOfServiceBuilder builder = new ProcessReturnOfServiceBuilder(true);

        ProcessReturnOfServiceResponseEvent response;

        int len = results.size();
        for (int i = 0; i < len; i++)
        {
            JuvenileWarrantServiceOfficer jwso = (JuvenileWarrantServiceOfficer) results.get(i);
            builder.setWarrantServiceOfficer(jwso);
            builder.build();
            response = (ProcessReturnOfServiceResponseEvent) builder.getResult();
            dispatch.postEvent(response);
        }
    }

    /**
     * @param event
     * @roseuid 41FFC5E700BF
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 41FFC5E700CB
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 41FFDABE0399
     */
    public void update(Object updateObject)
    {

    }

}
