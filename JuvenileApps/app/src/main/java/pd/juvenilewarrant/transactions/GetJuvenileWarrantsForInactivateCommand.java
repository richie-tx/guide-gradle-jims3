package pd.juvenilewarrant.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pd.contact.user.UserProfile;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantLightBuilder;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.juvenilewarrant.GetJuvenileWarrantsForInactivateEvent;
import messaging.juvenilewarrant.reply.InvalidWarrantStageErrorEvent;
import mojo.km.context.ContextManager;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;

/**
 * @author ryoung
 */
public class GetJuvenileWarrantsForInactivateCommand implements ICommand
{

    /**
     * @roseuid 41F7C32A0297
     */
    public GetJuvenileWarrantsForInactivateCommand()
    {

    }

    /**
     * @param event
     * @roseuid 41F7B6830122
     */
    public void execute(IEvent event)
    {
        GetJuvenileWarrantsForInactivateEvent warEvent = (GetJuvenileWarrantsForInactivateEvent) event;

        List warrants = new ArrayList();

        String warrantNum = warEvent.getWarrantNum();

        JuvenileWarrant warrant;

        if (warrantNum != null && warrantNum.trim().length() > 0)
        {
            if (warrantNum.length() < 10)
            {
	            warrant = JuvenileWarrant.find(warrantNum);
	            if (warrant != null)
	            {
	                        if (warrant.isWarrantInactivatable() == true)
	                        {
	                            warrants.add(warrant);
	                        }
	            }
            }
        }
        else
        {
            MetaDataResponseEvent metaData = (MetaDataResponseEvent) JuvenileWarrant.findMeta(warEvent);
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
                Iterator i = JuvenileWarrant.findAll(warEvent);
                while (i.hasNext())
                {
                    warrant = (JuvenileWarrant) i.next();
                    warrants.add(warrant);
                }
            }
        }
        this.sendWarrants(warrants);
    }

    /**
     * @param warrants
     */
    private void sendWarrants(List warrants)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        if (warrants.size() == 1)
        {
            JuvenileWarrant warrant = (JuvenileWarrant) warrants.get(0);
            sendSingleWarrant(dispatch, warrant);
        }
        else if (warrants.size() > 1)
        {
            sendMultipleWarrants(dispatch, warrants);
        }
    }

    /**
     * 
     * @param warrant
     * @return
     */
    private boolean isOriginatingAgency(JuvenileWarrant warrant)
    {
        boolean isOriginatingAgency = false;
        ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");

        IUserInfo userInfo = manager.getIUserInfo();

        if ("MA".equals(userInfo.getUserTypeId()))
        {
            isOriginatingAgency = true;
        }
        else
        {
            String currUserDeptId = manager.getIUserInfo().getDepartmentId();

            if (currUserDeptId != null)
            {
                UserProfile warrantOriginator = warrant.getWarrantOriginatorUser();
                if (warrantOriginator != null)
                {
                    String warrantOriginatorDeptId = warrantOriginator.getDepartmentId();
                    if (currUserDeptId.equals(warrantOriginatorDeptId) || currUserDeptId.equals("JUV"))
                    {
                        isOriginatingAgency = true;
                    }
                }
            }
        }

        return isOriginatingAgency;
    }

    private void sendSingleWarrant(IDispatch dispatch, JuvenileWarrant warrant)
    {

        if (isOriginatingAgency(warrant) == true && warrant.isWarrantInactivatable() == true)
        {
            dispatch.postEvent(warrant.valueObject());

            PDJuvenileWarrantHelper.sendAssociatesFields(dispatch, warrant);
            PDJuvenileWarrantHelper.postResponses(warrant.getChargeResponses());

            if (warrant.getOfficerId() != null)
            {
                OfficerProfileResponseEvent ore = warrant.getOfficer().valueObject(false);
                dispatch.postEvent(ore);
            }
        }
        else
        {
            InvalidWarrantStageErrorEvent errorEvent = warrant.getInvalidWarrantStageErrorEvent();
            dispatch.postEvent(errorEvent);
        }
    }

    private void sendMultipleWarrants(IDispatch dispatch, List warrants)
    {
        JuvenileWarrantLightBuilder builder = new JuvenileWarrantLightBuilder();

        int len = warrants.size();
        for (int i = 0; i < len; i++)
        {
            JuvenileWarrant warrant = (JuvenileWarrant) warrants.get(i);
            if (isOriginatingAgency(warrant) == true && warrant.isWarrantInactivatable() == true)
            {
	            builder.setWarrant(warrant);
	            builder.build();
	            builder.setWarrantType();
	            ResponseEvent warrantEvent = (ResponseEvent) builder.getResult();
	            dispatch.postEvent(warrantEvent);
            }
        }
    }

    /**
     * @param event
     * @roseuid 41F7B6830124
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 41F7B6830131
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 41F7C32A02A7
     */
    public void update(Object updateObject)
    {

    }
}