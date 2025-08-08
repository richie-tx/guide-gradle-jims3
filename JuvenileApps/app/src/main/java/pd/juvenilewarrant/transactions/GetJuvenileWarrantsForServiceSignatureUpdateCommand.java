package pd.juvenilewarrant.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import naming.PDJuvenileWarrantConstants;

import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantLightBuilder;
import pd.juvenilewarrant.JuvenileWarrantService;
import pd.juvenilewarrant.JuvenileWarrantServiceBuilder;
import messaging.info.reply.CountInfoMessage;
import messaging.juvenilewarrant.GetJuvenileWarrantsForServiceSignatureUpdateEvent;
import messaging.juvenilewarrant.reply.InvalidWarrantStageErrorEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.pattern.IBuilder;

/**
 * 
 * @author asrvastava
 * 
 * This command is used to return all the warrants based on the search criteria
 * specified for "Request Return of Service Signature" functionality.
 *  
 */
public class GetJuvenileWarrantsForServiceSignatureUpdateCommand implements ICommand
{

    /**
     * @roseuid 41FFDAC6009C
     */
    public GetJuvenileWarrantsForServiceSignatureUpdateCommand()
    {

    }

    /**
     * @param event
     * @roseuid 41FFC66A02F2
     */
    public void execute(IEvent event)
    {
        GetJuvenileWarrantsForServiceSignatureUpdateEvent warEvent = (GetJuvenileWarrantsForServiceSignatureUpdateEvent) event;

        String warrantNum = warEvent.getWarrantNum();
        List warrants = new ArrayList();
        
        if (warrantNum != null)
        {
            if(warrantNum.length() < 10 && warrantNum.trim().length() > 0)
	        {
	            JuvenileWarrant warrant = JuvenileWarrant.find(warrantNum);
	            if (warrant != null)
	            {
	                if (checkPreconditions(warrant))
	                {
	                    warrants.add(warrant);
	                }
	                else
	                {
	                    InvalidWarrantStageErrorEvent errorEvent = warrant.getInvalidWarrantStageErrorEvent();
	                    EventManager.getSharedInstance(EventManager.REPLY).postEvent(errorEvent);
	                }
	            }
	        }
        }
        else
        {
            // sign status to RETURNED
            warEvent.setServiceReturnSignatureStatus(PDJuvenileWarrantConstants.SERVICE_RETURN_SIGN_STATUS_RETURNED);
            // warrant status to EXECUTED
            warEvent.setWarrantStatus(PDJuvenileWarrantConstants.WARRANT_STATUS_EXECUTED);

            MetaDataResponseEvent metaData = (MetaDataResponseEvent) JuvenileWarrant.findMeta(event);
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
            if(metaData.getCount()> 2000)
            {
                CountInfoMessage infoEvent = new CountInfoMessage();
                infoEvent.setMessage("Record count exeeded - total records found = " + metaData.getCount());
                infoEvent.setCount(metaData.getCount());
                dispatch.postEvent(infoEvent);
            }
            else
            {
                Iterator i = JuvenileWarrant.findAll(warEvent);
                while (i.hasNext())
                {
                    warrants.add(i.next());
                }
            }
        }
        
        warrants = this.filterResults(warrants);

        if (warrants.size() == 1)
        {
            this.sendSingleWarrant((JuvenileWarrant) warrants.get(0));
        }
        else if (warrants.size() > 1)
        {
            this.sendMultipleWarrants(warrants);
        }
    }

    /**
     * @param warrants
     * @return
     */
    private List filterResults(List warrants)
    {
        List results = new ArrayList(warrants.size());
        int len = warrants.size();
        for(int i=0;i<len;i++)
        {
            JuvenileWarrant warrant = (JuvenileWarrant) warrants.get(i);
            if(checkPreconditions(warrant) == true)
            {
                results.add(warrant);
            }
        }        
        return results;
    }

    /**
     * @param warrants
     */
    private void sendMultipleWarrants(List warrants)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        int len = warrants.size();

        JuvenileWarrantLightBuilder builder = new JuvenileWarrantLightBuilder();

        for(int i=0;i<len;i++)
        {
            JuvenileWarrant warrant = (JuvenileWarrant) warrants.get(i);
            builder.setWarrant(warrant);
            builder.build();
            builder.setWarrantType();
            ResponseEvent warrantEvent = (ResponseEvent) builder.getResult();
            dispatch.postEvent(warrantEvent);
        }
    }

    /**
     * @param warrant
     */
    private void sendSingleWarrant(JuvenileWarrant warrant)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        if (checkPreconditions(warrant))
        {
            IBuilder builder = new JuvenileWarrantServiceBuilder(warrant);
            builder.build();
            ResponseEvent response = (ResponseEvent) builder.getResult();
            dispatch.postEvent(response);

            JuvenileWarrantService service = JuvenileWarrantService.findSuccessfulService(warrant.getWarrantNum());
            response = service.valueObject();
            dispatch.postEvent(response);
        }
    }

    private boolean checkPreconditions(JuvenileWarrant warrant)
    {
        // Assume the preconditions have not been met
        boolean valid = false;

        // To be valid in the preconditions for processing warrant service
        // signature, the following
        // logic applies:

        // To be valid, the warrant status must be EXECUTED

        String warrantStatus = warrant.getWarrantStatusId();
        if (PDJuvenileWarrantConstants.WARRANT_STATUS_EXECUTED.equals(warrantStatus))
        {
            String warrantType = warrant.getWarrantTypeId();

            // To be valid, the warrant must be either DTA, ARR, or OIC
            // it must have a return generated status of PRINTED
            if (PDJuvenileWarrantConstants.WARRANT_TYPE_ARR.equals(warrantType)
                    || PDJuvenileWarrantConstants.WARRANT_TYPE_DTA.equals(warrantType)
                    || PDJuvenileWarrantConstants.WARRANT_TYPE_OIC.equals(warrantType))
            {
                String serviceReturnGen = warrant.getServiceReturnGeneratedStatusId();
                String serviceReturnSig = warrant.getServiceReturnSignatureStatusId();
                if (PDJuvenileWarrantConstants.SERVICE_RETURN_GEN_STATUS_PRINTED.equals(serviceReturnGen)
                        && PDJuvenileWarrantConstants.SERVICE_RETURN_SIGN_STATUS_RETURNED.equals(serviceReturnSig))
                {
                    valid = true;
                }
            }
        }
        return valid;
    }

    /**
     * @param event
     * @roseuid 41FFC66A02FD
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 41FFC66A02FF
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 41FFDAC600AB
     */
    public void update(Object updateObject)
    {

    }
}
