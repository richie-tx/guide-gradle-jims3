package pd.juvenilewarrant.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import naming.JuvenileWarrantControllerServiceNames;

import pd.juvenilewarrant.Charge;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantLightBuilder;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;

import messaging.info.reply.CountInfoMessage;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantsListEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.utilities.CollectionUtil;

public class GetJuvenileWarrantsListCommand implements ICommand
{

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event) throws Exception
    {
        boolean errorFound = false;
        
        GetJuvenileWarrantsListEvent warEvent = (GetJuvenileWarrantsListEvent) event;

        List warrants = new ArrayList();

        if (warEvent.getWarrantNum() != null)
        {
            String warrantNum = warEvent.getWarrantNum();
            if (warrantNum.length() < 10)
            {
	            JuvenileWarrant warrant = JuvenileWarrant.find(warEvent.getWarrantNum());
	            if (warrant != null)
	            {
	                warrants.add(warrant);
	            }
            }
        }
        else
        {
            MetaDataResponseEvent metaData = (MetaDataResponseEvent) JuvenileWarrant.findMeta(warEvent);
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
            if(metaData.getCount()> 2000)
            {
                CountInfoMessage infoEvent = new CountInfoMessage();
                infoEvent.setMessage("Record count exeeded - total records found = " + metaData.getCount());
                infoEvent.setCount(metaData.getCount());
                errorFound = true;
                dispatch.postEvent(infoEvent);
            }
            else
            {
                warrants = CollectionUtil.iteratorToList(JuvenileWarrant.findAll(warEvent));
            }
        }
        if(!errorFound)
        {
            this.sendWarrants(warEvent, warrants);
        }
    }

    private void sendSingleWarrant(GetJuvenileWarrantsListEvent warEvent, JuvenileWarrant aWarrant)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        // get the JW response (with charges)
        JuvenileWarrantResponseEvent response = aWarrant.valueObject(true);

        PDJuvenileWarrantHelper.sendAssociatesFields(dispatch, aWarrant);

        dispatch.postEvent(response);

        GetJJSPetitionsEvent event = (GetJJSPetitionsEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.GETJJSPETITIONS);        

         event.setJuvenileNum(String.valueOf(aWarrant.getJuvenileNum()));
        
        // the petitionNum is set when calling the JuvenileWarrant.valueObject(withCodes) above
        event.setPetitionNum(String.valueOf( response.getPetitionNum()));
        event.setReferralNum(String.valueOf( aWarrant.getReferralNum()));
        
        EventManager.getSharedInstance(EventManager.REQUEST).postEvent(event);
    }

    private void sendMultipleWarrants(List aWarrants)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        JuvenileWarrantLightBuilder builder = new JuvenileWarrantLightBuilder();

        int len = aWarrants.size();
        for(int i=0;i<len;i++)
        {
            JuvenileWarrant warrant = (JuvenileWarrant) aWarrants.get(i);
            builder.setWarrant(warrant);
            builder.build();
            JuvenileWarrantResponseEvent warrantEvent = (JuvenileWarrantResponseEvent) builder.getResult();

            // apparently only JJS warrants are using this service
            // TODO Create an JW Charge View, include columns such as
            // PETITIONNUM
            Iterator iter = warrant.getCharges().iterator();
            if (iter.hasNext())
            {
                Charge charge = (Charge) iter.next();
                String petitionNum = charge.getPetitionNum();
                warrantEvent.setPetitionNum(petitionNum);
            }

            dispatch.postEvent(warrantEvent);
        }

    }

    public void sendWarrants(GetJuvenileWarrantsListEvent warEvent, List warrants)
    {
        if (warrants.size() == 1)
        {
            JuvenileWarrant warrant = (JuvenileWarrant) warrants.get(0);
            this.sendSingleWarrant(warEvent, warrant);
        }
        else
        {
            this.sendMultipleWarrants(warrants);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event)
    {
        // TODO Auto-generated method stub

    }

}