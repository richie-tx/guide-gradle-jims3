package pd.juvenilewarrant.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import messaging.info.reply.CountInfoMessage;
import messaging.juvenilewarrant.GetJuvenileAssociatesAddressesForWarrantEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantsForManageServiceEvent;
import messaging.juvenilewarrant.reply.InvalidWarrantStageErrorEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.messaging.ResponseEvent;
import naming.PDJuvenileWarrantConstants;
import pd.juvenilewarrant.JuvenileAssociateAddress;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantLightBuilder;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;

public class GetJuvenileWarrantsForManageServiceCommand implements ICommand
{

    /**
     * @roseuid 41FFDAB80232
     */
    public GetJuvenileWarrantsForManageServiceCommand()
    {

    }

    /**
     * @param event
     * @roseuid 41FFC5E6032E
     */
    public void execute(IEvent event)
    {
        GetJuvenileWarrantsForManageServiceEvent warEvent = (GetJuvenileWarrantsForManageServiceEvent) event;

        String warrantNum = warEvent.getWarrantNum();

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        List warrants = new ArrayList();

        if (warrantNum != null)
        {
            if (warrantNum.length() < 10 && warrantNum.trim().length() > 0)
	        {
	            JuvenileWarrant warrant = JuvenileWarrant.find(warrantNum);
	            if (warrant != null)
	            {
	                String warrantStatus = warrant.getWarrantStatusId();
	                String warrantActivationStatus = warrant.getWarrantActivationStatusId();
	
	                if (PDJuvenileWarrantConstants.WARRANT_STATUS_OPEN.equals(warrantStatus)
	                        && PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE.equals(warrantActivationStatus))
	                {
	                    warrants.add(warrant);
	                }
	                else
	                {
	                    InvalidWarrantStageErrorEvent errorEvent = warrant.getInvalidWarrantStageErrorEvent();
	                    dispatch.postEvent(errorEvent);
	                }
	            }
	        }
        }
        else
        {
            MetaDataResponseEvent metaData = (MetaDataResponseEvent) JuvenileWarrant.findMeta(event);
            if(metaData.getCount()> 2000)
            {
                CountInfoMessage infoEvent = new CountInfoMessage();
                infoEvent.setMessage("Record count exeeded - total records found = " + metaData.getCount());
                infoEvent.setCount(metaData.getCount());
                dispatch.postEvent(infoEvent);
            }
            else
            {
                // set the warrant status and activation status
                warEvent.setWarrantActivationStatus(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE);
                warEvent.setWarrantStatus(PDJuvenileWarrantConstants.WARRANT_STATUS_OPEN);
                Iterator i = JuvenileWarrant.findAll(warEvent);
                while (i.hasNext())
                {
                    warrants.add(i.next());
                }
            }
        }

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
     * @param warrant
     */
    private void sendSingleWarrant(JuvenileWarrant warrant)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        JuvenileWarrantLightBuilder builder = new JuvenileWarrantLightBuilder(warrant);
        builder.build();
        builder.setWarrantOriginator();
        builder.setWarrantType();
        builder.setWarrantActivationStatus();
        builder.setWarrantStatus();
        builder.setServiceDateValidationValues();
        ResponseEvent response = (ResponseEvent) builder.getResult();
        dispatch.postEvent(response);

        // return all warrant services
        Iterator i = warrant.getServiceResponses().iterator();
        while (i.hasNext())
        {
            response = (ResponseEvent) i.next();
            dispatch.postEvent(response);
        }

        this.sendAssociatesFields(dispatch, warrant);
    }

    private void sendAssociatesFields(IDispatch dispatch, JuvenileWarrant juvWarrant)
    {
        GetJuvenileAssociatesAddressesForWarrantEvent addressEvent = new GetJuvenileAssociatesAddressesForWarrantEvent();
        addressEvent.setWarrantNum(juvWarrant.getWarrantNum());

        Iterator addresses = JuvenileAssociateAddress.findAll(addressEvent);

        while (addresses.hasNext())
        {
            JuvenileAssociateAddress address = (JuvenileAssociateAddress) addresses.next();
            JuvenileAssociateAddressResponseEvent associateEvent = PDJuvenileWarrantHelper
                    .getJuvenileAssociateAddressResponseEvent(address);
            dispatch.postEvent(associateEvent);
        }
    }

    /**
     * @param warrants
     */
    private void sendMultipleWarrants(List warrants)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        JuvenileWarrantLightBuilder builder = new JuvenileWarrantLightBuilder();

        int len = warrants.size();
        for(int i=0;i<len;i++)
        {
            JuvenileWarrant warrant = (JuvenileWarrant) warrants.get(i);
            builder.setWarrant(warrant);
            builder.build();
            builder.setWarrantType();

            JuvenileWarrantResponseEvent warrantEvent = (JuvenileWarrantResponseEvent) builder.getResult();

            dispatch.postEvent(warrantEvent);
        }
    }

    /**
     * @param event
     * @roseuid 41FFC5E60330
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 41FFC5E6033C
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 41FFDAB80242
     */
    public void update(Object updateObject)
    {

    }
}
