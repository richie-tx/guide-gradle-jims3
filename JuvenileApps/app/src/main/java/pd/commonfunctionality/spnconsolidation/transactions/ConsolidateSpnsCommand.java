package pd.commonfunctionality.spnconsolidation.transactions;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.FastArrayList;

import messaging.notification.CreateNotificationEvent;
import messaging.spnconsolidation.ConsolidateSpnsEvent;
import mojo.km.context.ContextManager;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import pd.commonfunctionality.spnconsolidation.ISpnHandler;
import pd.commonfunctionality.spnconsolidation.SpnConsolidationConfig;
import pd.contact.agency.Department;
import pd.security.RegionType;

public class ConsolidateSpnsCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public ConsolidateSpnsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {

        ConsolidateSpnsEvent requestEvent = (ConsolidateSpnsEvent) event;

        Iterator configIter = null;

        configIter = SpnConsolidationConfig.findAll();
        List spnConfigs = new FastArrayList();
        while (configIter.hasNext())
        {
            SpnConsolidationConfig config = (SpnConsolidationConfig) configIter.next();
            spnConfigs.add(config);
        }

        Collections.sort(spnConfigs, SpnConsolidationConfig.SeqNumComparator);

        try
        {
            //iterate through spnConfigs until no more
            int len = spnConfigs.size();
            for (int i = 0; i < len; i++)
            {
                SpnConsolidationConfig spnHandlers = (SpnConsolidationConfig) spnConfigs.get(i);
                String handler = spnHandlers.getHandler();

                ISpnHandler iSpnHandler = (ISpnHandler) Class.forName(handler).newInstance();
                iSpnHandler.consolidate(requestEvent.getAliasSpn(), requestEvent.getBaseSpn());
            }
        }
        catch (Exception e)
        {
            ReturnException returnException = new ReturnException( e.getCause() );
            throw returnException;
        }
        
        // Sends out the email notification
        this.sendNotificationSPNConsolidated(requestEvent);
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

    private void sendNotificationSPNConsolidated( ConsolidateSpnsEvent request )
    {
        CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance("CreateNotification");

        ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
        IUserInfo userInfo = mgr.getIUserInfo();
        Department dept = Department.find(userInfo.getDepartmentId());
        
        RegionType regionType = new RegionType();
        
        String region = regionType.getRegion();

        notificationEvent.setSubject("JIMS2 SPN Consolidation from	" + request.getAliasSpn() + " to " + request.getBaseSpn()
                + " initiated in " + region );

        notificationEvent.setNotificationTopic("CS.SPN.CONSOLIDATED");
        notificationEvent.addContentBean( userInfo );
        notificationEvent.addContentBean( dept );

        EventManager.getSharedInstance(EventManager.REQUEST).postEvent( notificationEvent );
    }

}
