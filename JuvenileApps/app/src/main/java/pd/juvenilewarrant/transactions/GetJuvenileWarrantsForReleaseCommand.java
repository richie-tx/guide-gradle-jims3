package pd.juvenilewarrant.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import naming.PDJuvenileWarrantConstants;

import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantLightBuilder;
import pd.juvenilewarrant.JuvenileWarrantService;

import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.messaging.ResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.juvenilewarrant.GetJuvenileWarrantsForReleaseEvent;

/**
 * 
 * @author asrvastava
 * 
 * This class is used to search the warrants for Manage Release functioanlity.
 */
public class GetJuvenileWarrantsForReleaseCommand implements ICommand
{

    /**
     * @roseuid 41FFDAC30261
     */
    public GetJuvenileWarrantsForReleaseCommand()
    {

    }

    /**
     * @param event
     * @roseuid 41FFC64E006E
     */
    public void execute(IEvent event)
    {
        GetJuvenileWarrantsForReleaseEvent dataReqEvent = (GetJuvenileWarrantsForReleaseEvent) event;

        String warrantNum = dataReqEvent.getWarrantNum();

        List warrantList = new ArrayList();
        if (warrantNum.length() < 10)
        {
	        if (warrantNum != null && warrantNum.trim().length() > 0)
	        {
	            JuvenileWarrant warrant = JuvenileWarrant.find(warrantNum);
	            if (warrant != null)
	            {
	                warrantList.add(warrant);
	            }
	        }
	        else
	        {
	            // make sure that status is executed
	            dataReqEvent.setWarrantStatus(PDJuvenileWarrantConstants.WARRANT_STATUS_EXECUTED);
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
	                Iterator j = JuvenileWarrant.findAll(dataReqEvent);
	                while (j.hasNext())
	                {
	                    warrantList.add(j.next());
	                }
	            }
	        }
        }    
        this.sendWarrants(warrantList);

    }

    private void sendWarrants(List warrants)
    {
        if (warrants.size() == 1)
        {
            JuvenileWarrant warrant = (JuvenileWarrant) warrants.get(0);
            this.sendSingleWarrant(warrant);
        }
        else if (warrants.size() > 1)
        {
            this.sendMultipleWarrants(warrants);
        }
    }

    /**
     * @param warrantList
     */
    private void sendMultipleWarrants(List warrants)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        int len = warrants.size();
        for(int i=0;i<len;i++)
        {
            JuvenileWarrant warrant = (JuvenileWarrant) warrants.get(i);

            if (this.checkPreconditions(warrant) == true)
            {
                JuvenileWarrantLightBuilder builder = new JuvenileWarrantLightBuilder(warrant);
                builder.build();
                builder.setWarrantType();
                ResponseEvent warrantEvent = (ResponseEvent) builder.getResult();
                dispatch.postEvent(warrantEvent);
            }
        }
    }

    private void sendSingleWarrant(JuvenileWarrant aWarrant)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        ResponseEvent response;
        
        if (this.checkPreconditions(aWarrant) == true)
        {
            JuvenileWarrantLightBuilder builder = new JuvenileWarrantLightBuilder(aWarrant);
            builder.build();
            builder.setWarrantDetails();
            builder.setJuvenileValues();
            builder.setWarrantServiceStatuses();
            response = (ResponseEvent) builder.getResult();
            dispatch.postEvent(response);
            
            JuvenileWarrantService service = JuvenileWarrantService.findSuccessfulService(aWarrant.getWarrantNum());
            response = service.valueObject();
            dispatch.postEvent(response);
        }
        else
        {
            response = aWarrant.getInvalidWarrantStageErrorEvent();
            dispatch.postEvent(response);
        }
    }

    private boolean checkPreconditions(JuvenileWarrant warrant)
    {
        boolean passed = false;
        String warrantStatusId = warrant.getWarrantStatusId();
        String releaseDecision = warrant.getReleaseDecisionId();
/*      String serviceReturnSignatureStatus = warrant.getServiceReturnSignatureStatusId();
        String serviceReturnGeneratedStatus = warrant.getServiceReturnGeneratedStatusId();
*/
        if (releaseDecision == null && PDJuvenileWarrantConstants.WARRANT_STATUS_EXECUTED.equals(warrantStatusId))
/*              && PDJuvenileWarrantConstants.SERVICE_RETURN_SIGN_STATUS_FILED.equals(serviceReturnSignatureStatus) 
                && PDJuvenileWarrantConstants.SERVICE_RETURN_GEN_STATUS_PRINTED.equals(serviceReturnGeneratedStatus))
*/      {
            passed = true;
        }
        return passed;
    }

    /**
     * @param event
     * @roseuid 41FFC64E0070
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 41FFC64E0072
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 41FFDAC30271
     */
    public void update(Object updateObject)
    {

    }
}
