package pd.juvenilewarrant.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import pd.contact.officer.OfficerProfile;
import pd.juvenilewarrant.JuvenileAssociate;
import pd.juvenilewarrant.JuvenileAssociateListBuilder;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantBuilder;
import pd.juvenilewarrant.JuvenileWarrantLightBuilder;
import pd.juvenilewarrant.JuvenileWarrantService;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import mojo.pattern.IBuilder;

import messaging.info.reply.CountInfoMessage;
import messaging.juvenilewarrant.GetJuvenileWarrantsForRecallEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.messaging.ResponseEvent;

public class GetJuvenileWarrantsForRecallCommand implements ICommand
{
	private static final String JUVWARRANTSFORRECALL_CONTEXT = "JWWARRANTRECALL";
    /**
     * @roseuid 41F7C3140094
     */
    public GetJuvenileWarrantsForRecallCommand()
    {
    }

    /**
     * @param event
     * @roseuid 41F7B69A0383
     */
    public void execute(IEvent event)
    {
        GetJuvenileWarrantsForRecallEvent warEvent = (GetJuvenileWarrantsForRecallEvent) event;

        List warrants = this.fetchWarrants(warEvent);

        if (warrants.size() == 1)
        {
            JuvenileWarrant warrant = (JuvenileWarrant) warrants.get(0);
            sendSingleWarrant(warrant);
        }
        else if (warrants.size() > 1)
        {
            this.sendMultipleWarrants(warrants);
        }
    }

    /**
     * @param warrants
     */
    private void sendMultipleWarrants(List warrants)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        int len = warrants.size();
        for(int i=0;i<len;i++)
        {
            JuvenileWarrant warrant = (JuvenileWarrant) warrants.get(i);

            JuvenileWarrantLightBuilder builder = new JuvenileWarrantLightBuilder(warrant);
            builder.build();
            builder.setWarrantType();
            builder.setWarrantStatus();

            ResponseEvent warrantEvent = (ResponseEvent) builder.getResult();
            dispatch.postEvent(warrantEvent);
        }
    }

    private List fetchWarrants(GetJuvenileWarrantsForRecallEvent anEvent)
    {
        // build resultset
        List warrants = new ArrayList();
        Iterator i = new ArrayList().iterator();
        String warrantNum = anEvent.getWarrantNum();
        if (warrantNum != null && warrantNum.trim().length() > 0)
        {
            if (warrantNum.length() < 10)
            {
	            JuvenileWarrant warrant = JuvenileWarrant.findByContext(warrantNum,JUVWARRANTSFORRECALL_CONTEXT );
	            if (warrant != null)
	            {
	                    warrants.add(warrant);
	                    i = warrants.iterator();
	            }
            }
        }
        else
        {
            MetaDataResponseEvent metaData = (MetaDataResponseEvent) JuvenileWarrant.findMeta(anEvent);
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
            if(metaData.getCount() > 2000)
            {
                CountInfoMessage infoEvent = new CountInfoMessage();
                infoEvent.setMessage("Record count exceeded - total records found = " + metaData.getCount());
                infoEvent.setCount(metaData.getCount());
                dispatch.postEvent(infoEvent);
            }
            else
            {
                i = JuvenileWarrant.findAll(anEvent);
            }
        }

        // (Using a view instead) filter out warrants that are not recallable
        List recallableWarrants = new ArrayList();
        while (i.hasNext())
        {
            JuvenileWarrant warrant = (JuvenileWarrant) i.next();
         
                recallableWarrants.add(warrant);
        }
        return recallableWarrants;
    }

    /**
     * Builds response events for warrant and supplemental data
     * 
     * @param warrant
     * @param dispatch
     */
    private void sendSingleWarrant(JuvenileWarrant warrant)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        JuvenileWarrantBuilder builder = new JuvenileWarrantBuilder(warrant);
        builder.build();        
        builder.setWarrantDetails();
        builder.setRecallInfo();
        
        JuvenileWarrantResponseEvent warrantEvent = (JuvenileWarrantResponseEvent) builder.getResult();

        sendAssociates(warrant, dispatch);

        //Post Warrant response event after retrieving associate info
        dispatch.postEvent(warrantEvent);

        sendOfficer(warrant);
        sendServices(warrant);

        PDJuvenileWarrantHelper.postResponses(warrant.getChargeResponses());
    }

    /**
     * Retrieves associate information attached to a given warrant
     * 
     * @param warrant
     * @param dispatch
     */
    private void sendAssociates(JuvenileWarrant warrant, IDispatch dispatch)
    {
        Collection associates = warrant.getJuvenileAssociates();
        if (associates != null)
        {
            for (Iterator iter = associates.iterator(); iter.hasNext();)
            {
                JuvenileAssociate associate = (JuvenileAssociate) iter.next();
                IBuilder builder = new JuvenileAssociateListBuilder(associate);
                builder.build();
                JuvenileAssociateResponseEvent jare = (JuvenileAssociateResponseEvent) builder.getResult();
                dispatch.postEvent(jare);
            }
        }
    }

    /**
     * Retrieves officer attached to a given warrant
     * 
     * @param warrant
     * @param dispatch
     */
    private void sendOfficer(JuvenileWarrant warrant)
    {
        if (warrant.getOfficerId() != null && warrant.getOfficerId().equals("") == false)
        {
            OfficerProfile officer = warrant.getOfficer();
            if (officer != null)
            {
                // send officer without security data
                IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
                dispatch.postEvent(officer.valueObject(false));
            }
        }
    }

    /**
     * Retrieves services attached to a given warrant
     * 
     * @param warrant
     * @param dispatch
     */
    private void sendServices(JuvenileWarrant warrant)
    {
        Collection services = warrant.getServices();
        if (services != null)
        {
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
            Iterator i = services.iterator();
            while (i.hasNext())
            {
                JuvenileWarrantService service = (JuvenileWarrantService) i.next();
                dispatch.postEvent(service.valueObject());
            }
        }
    }

    /**
     * @param event
     * @roseuid 41F7B69A0385
     */
    public void onRegister(IEvent event)
    {
    }

    /**
     * @param event
     * @roseuid 41F7B69A0387
     */
    public void onUnregister(IEvent event)
    {
    }

    /**
     * @param updateObject
     * @roseuid 41F7C31400A3
     */
    public void update(Object updateObject)
    {
    }
}